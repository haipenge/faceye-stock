package com.faceye.component.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Forecast;
import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Valuation;
import com.faceye.component.stock.repository.mongo.ValuationRepository;
import com.faceye.component.stock.repository.mongo.customer.ValuationCustomerRepository;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.ForecastIndexService;
import com.faceye.component.stock.service.ForecastService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.ValuationService;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.querydsl.core.types.Predicate;

/**
 * Valuation 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class ValuationServiceImpl extends BaseMongoServiceImpl<Valuation, Long, ValuationRepository> implements ValuationService {
	@Autowired
	private ValuationCustomerRepository valuationCustomerRepository = null;
	@Autowired
	private StockService stockService = null;
	@Autowired
	private ReportDataService reportDataService = null;
	@Autowired
	private ForecastService forecastService = null;
	@Autowired
	private DataStatService dataStatService = null;
	@Autowired
	private ForecastIndexService forecastIndexService = null;

	@Autowired
	public ValuationServiceImpl(ValuationRepository dao) {
		super(dao);
	}

	@Override
	public Page<Valuation> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Valuation> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Valuation> builder = new PathBuilder<Valuation>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Valuation> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Valuation>("id") {
			// })
			List<Valuation> items = (List) this.dao.findAll(predicate, sort);
			res = new PageImpl<Valuation>(items);

		}
		return res;
	}

	/**
	 * 
	 * 使用剩余收益模型进行股票估值
	 */
	@Override
	public void doStockValuation(Long stockId, Double roce, Double discountRate) {
		Map searchParams = new HashMap();
		searchParams.put("SORT|date", "asc");
		searchParams.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
		List<ReportData> reportDatas = this.reportDataService.getPage(searchParams, 1, 0).getContent();
		// 普通股权益报酬率
		if (roce == null) {
			roce = 0.2D;
		}
		// 贴现率,GDP增长率
		if (discountRate == null) {
			discountRate = 0.045;
		}
		for (int i = 0; i < reportDatas.size(); i++) {
			ReportData reportData = reportDatas.get(i);
			Valuation valuation = this.getValuationByPeriod(stockId, reportData.getDate());
			if (valuation == null) {
				logger.debug(">>FaceYe valuation is null." + DateUtil.formatDate(reportData.getDate()));
				valuation = new Valuation();
			}
			// valuation = valuation == null ? new Valuation() : valuation;
			int count = 1;
			Double totalValue = 0D;
			// 股东权益初值
			Double bookValue0 = reportData.getBalanceSheet().getEle17().getCbsheet86_243();
			if (bookValue0 == null) {
				continue;
			}
			Double lastPeriodBookValue = bookValue0;
			totalValue += bookValue0;
			for (int j = i + 1; j < reportDatas.size(); j++) {
				if (count <= 5) {
					ReportData nextReportData = reportDatas.get(j);
					Double nextBookValue = nextReportData.getBalanceSheet().getEle17().getCbsheet86_243();
					// 开始计算剩余价值收益
					// 普通股权益报酬
					Double roceValue = lastPeriodBookValue * roce;
					// 净利润
					Double netProfit = nextReportData.getInComeSheet().getEle9().getCinst24_128();
					// 剩余收益（超额收益）
					Double re = roceValue - netProfit;
					// 剩余收益的折现
					Double discountRe = re / Math.pow((1 + discountRate), count);
					totalValue += discountRe;
					lastPeriodBookValue = nextBookValue;
					count++;
					if (count == 6) {
						// 计算持续价值
						Double vc = 0D;
						vc = re / discountRate;
						vc = vc / Math.pow((1 + discountRate), count - 1);
						totalValue += vc;
					}
				} else {
					break;
				}
				if (count == 6) {
					valuation.setPeriod(reportData.getDate());
					valuation.setCreateDate(new Date());
					valuation.setDiscountRate(discountRate);
					valuation.setRoce(roce);
					valuation.setStockId(stockId);
					valuation.setTotalValue(totalValue);
					this.save(valuation);
				}
			}
		}
	}

	/**
	 * 根据时间，取得某一期的估值
	 * 
	 * @param period
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年6月4日 上午9:46:21
	 */
	private Valuation getValuationByPeriod(Long stockId, Date period) {
		Valuation valuation = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", stockId);
		String startDate = DateUtil.formatDate(period, "yyyy-MM-dd") + " 00:00:00";
		String endDate = DateUtil.formatDate(period, "yyyy-MM-dd") + " 23:59:59";
		searchParams.put("GTE|period", DateUtil.getDateFromString(startDate));
		searchParams.put("LTE|period", DateUtil.getDateFromString(endDate));
		List<Valuation> valuations = this.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(valuations)) {
			valuation = valuations.get(0);
		}
		return valuation;
	}

	/**
	 * 根据机构预测进行估值（验证机构估值与现值的偏离程度）
	 * 
	 * @param stockId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月22日 下午5:47:20
	 */
	public Valuation valuationWithMechanismForecast(Long stockId, ForecastIndex forecastIndex) {
		Valuation valuation = this.getValuationByForecastIndex(forecastIndex);
		// 必要报酬率【贴现率】选GDP 增速
		Double pro = 0.075;
		Double dps=0D;
		if (valuation == null) {
			valuation = new Valuation();
			valuation.setStockId(stockId);
			valuation.setDiscountRate(pro);
			valuation.setForecastIndex(forecastIndex);
		}
		logger.debug(">>FaceYe --Start to trace mongo query:"+stockId);
		Map stockReportDataParams = new HashMap();
		stockReportDataParams.put("EQ|stockId", stockId);
		stockReportDataParams.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
		stockReportDataParams.put("SORT|date", "desc");
		Page<ReportData> reportDatas = this.reportDataService.getPage(stockReportDataParams, 1, 5);
	
	
		ReportData reportData = null;
		DataStat dataStat = null;
		if (reportDatas != null && CollectionUtils.isNotEmpty(reportDatas.getContent())) {
			reportData = reportDatas.getContent().get(0);
		}
		Map dataStatSearchParams = new HashMap();
		dataStatSearchParams.put("EQ|stockId", stockId);
		dataStatSearchParams.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
		dataStatSearchParams.put("SORT|dateCycle", "desc");
		Page<DataStat> dataStats = this.dataStatService.getPage(dataStatSearchParams, 1, 5);
	    logger.debug(">>FaceYe end trace of "+stockId);
		if (dataStats != null && CollectionUtils.isNotEmpty(dataStats.getContent())) {
			dataStat = dataStats.getContent().get(0);
			 //计算最近N年平均DPS，并以此为未来DPS的估值
			Double dpsSum=0D;
			for(DataStat ds:dataStats){
				dpsSum+=ds.getDps();
			}
			dps=dpsSum/dataStats.getContent().size();
		}
		if (reportData != null && dataStat != null) {
			if (StringUtils.equals(DateUtil.formatDate(reportData.getDate(), "yyyy-MM-dd"), DateUtil.formatDate(dataStat.getDateCycle(), "yyyy-MM-dd"))) {
				Map forecastSearchParams = new HashMap();
				forecastSearchParams.put("EQ|forecastIndex.$id", forecastIndex.getId());
				forecastSearchParams.put("SORT|year", "asc");
				List<Forecast> forecasts = this.forecastService.getPage(forecastSearchParams, 1, 0).getContent();
				if (CollectionUtils.isNotEmpty(forecasts)) {
					Double bps0 = dataStat.getBps();
					Double eps0 = dataStat.getEps();
					String dataStatYear = DateUtil.formatDate(dataStat.getDateCycle(), "yy");
					List<Double> epss = new ArrayList<Double>(0);
					for (Forecast forecast : forecasts) {
						if (forecast.getYear() > NumberUtils.toInt(dataStatYear)) {
							if (forecast.getEps() > 0) {
								epss.add(forecast.getEps());
							}
						}
					}
					// 默认使用公式二进行估值，即预测期过后，RE不再增长
					valuationOnEps(bps0, pro,dps, epss,valuation);
				}
			} else {
				logger.error(">>FaceYe -->使用机构预测进行估值时,报表时间与Data stat 时间不同，请检查财务摘要。");
				logger.error(">>FaceYe -->Trace:reportDate is:" + DateUtil.formatDate(reportData.getDate(), "yyyy-MM-dd") + ",data stat date is:"
						+ DateUtil.formatDate(dataStat.getDateCycle(), "yyyy-MM-dd") + ",report data is:" + reportData.getId() +":"+reportData.getType()+ ",dataStat id:" + dataStat.getId()+",report data stockID is:"+reportData.getStockId()+",data stat stockId is :"+dataStat.getStockId()+":"+dataStat.getType());
			}
		}
		this.save(valuation);
		return valuation;
	}

	private void valuationOnEps(Double bps0, Double pro,Double dps, List<Double> epss,Valuation valuation) {
		if(CollectionUtils.isEmpty(epss)){
			return;
		}
		Double val = 0D;
		List<Double> bpss = new ArrayList<Double>(0);
		List<Double> roces = new ArrayList<Double>(0);
		List<Double> res = new ArrayList<Double>(0);
		List<Double> vres = new ArrayList<Double>(0);
		Double cv = null;
		Double bps = new Double(bps0);
		// 计算BPS(1-t) bps[t] = bps[t-1]+eps[t]
		for (Double eps : epss) {
			bpss.add(new Double(bps + eps-dps));
			bps = new Double(bps + eps);
		}
		valuation.setXbps(bpss);
		
		// 计算Roce(1-t) roce[t]=bps[t-1]+roce[t]
		if (epss.size() == bpss.size()) {
			for (int i = 0; i < epss.size(); i++) {
				Double eps = epss.get(i);
				Double ibps = null;
				if (i == 0) {
					ibps = bps0;
				} else {
					ibps = bpss.get(i - 1);
				}
				Double roce = eps / ibps;
				roces.add(roce);
			}
		}
		valuation.setXroces(roces);
		// 计算RE(1-t),RE(t)=bps(t-1)*(roce[t]-pro)
		for (int i = 0; i < roces.size(); i++) {
			Double roce = roces.get(i);
			Double ibps = null;
			if (i == 0) {
				ibps = bps0;
			} else {
				ibps = bpss.get(i - 1);
			}
			Double re = ibps * (roce - pro);
			res.add(re);
		}
		valuation.setXres(res);
		// 计算RE的现值 vre =re/(1+pro)[t]
		for (int i = 0; i < res.size(); i++) {
			Double re = res.get(i);
			Double vre = re / (Math.pow((1 + pro), i + 1));
			vres.add(vre);
		}
		valuation.setXvres(vres);
		// 计算持续价值CV,cv=(RE[t]/pro)/pro{t}
		// 注：默认T期之后RE增长率为0
		Double reT = res.get(res.size() - 1);
		// 剩余价值增长率为0是持续价值的计算
		cv = reT / pro / (Math.pow(1 + pro, res.size()));
		// 剩余价值增长率为g时持续价值的计算
		// cv=reT/(pro-g)/Math.pow(1+pro, res.size());
		valuation.setCv(cv);
		// 计算价值
		val += bps0;
		for (Double vre : vres) {
			val += vre;
		}
		val += cv;
		valuation.setTotalValue(val);
//		return val;
	}

	@Override
	public void doStockValuation(Long stockId) {
		
		Map searchForecastIndexParams = new HashMap();
		searchForecastIndexParams.put("EQ|stockId", stockId);
		searchForecastIndexParams.put("SORT|reportDate", "desc");
		Page<ForecastIndex> forecastIndexs = this.forecastIndexService.getPage(searchForecastIndexParams, 1, 0);
		if (forecastIndexs != null && CollectionUtils.isNotEmpty(forecastIndexs.getContent())) {
			for (ForecastIndex forecastIndex : forecastIndexs) {
				try{
				this.valuationWithMechanismForecast(stockId, forecastIndex);
				}catch(Exception e){
					logger.error(">>FaceYe error:股票估值异常:"+e);
				}
			}
		}
		
	}

	@Override
	public Valuation getValuationByForecastIndex(ForecastIndex forecastIndex) {
		Valuation valuation = null;
		Map valuationSearchParams = new HashMap(0);
		valuationSearchParams.put("EQ|forecastIndex.$id", forecastIndex.getId());
		Page<Valuation> valuations = this.getPage(valuationSearchParams, 1, 0);
		if (valuations != null && CollectionUtils.isNotEmpty(valuations.getContent())) {
			valuation = valuations.getContent().get(0);
		}
		return valuation;
	}

}/** @generate-service-source@ **/
