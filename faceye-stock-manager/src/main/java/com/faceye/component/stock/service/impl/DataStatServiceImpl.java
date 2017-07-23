package com.faceye.component.stock.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.BonusRecord;
import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.entity.TotalStock;
import com.faceye.component.stock.repository.mongo.DataStatRepository;
import com.faceye.component.stock.repository.mongo.customer.DataStatCustomerRepository;
import com.faceye.component.stock.service.BonusRecordService;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.TotalStockService;
import com.faceye.component.stock.service.ValuationService;
import com.faceye.component.stock.service.wrapper.StatRecord;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.querydsl.core.types.Predicate;

/**
 * DataStat 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class DataStatServiceImpl extends BaseMongoServiceImpl<DataStat, Long, DataStatRepository> implements DataStatService {
	@Autowired
	private DataStatCustomerRepository dataStatCustomerRepository = null;

	@Autowired
	private StockService stockService = null;
	@Autowired
	private ReportDataService reportDataService = null;
	@Autowired
	private TotalStockService totalStockService = null;
	@Autowired
	private BonusRecordService bonusRecordService = null;
	@Autowired
	private ValuationService valuationService=null;

	@Autowired
	public DataStatServiceImpl(DataStatRepository dao) {
		super(dao);
	}

	@Override
	public Page<DataStat> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver =
		// SimpleEntityPathResolver.INSTANCE;
		// EntityPath<DataStat> entityPath = resolver.createPath(entityClass);
		// PathBuilder<DataStat> builder = new
		// PathBuilder<DataStat>(entityPath.getType(),
		// entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate>
		// predicates=DynamicSpecifications.buildPredicates(searchParams,
		// entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = this.buildSort(searchParams);
		if (sort != null) {
			logger.debug(">>FaceYe --> sort is:" + sort.toString());
		}
		Page<DataStat> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new
			// OrderSpecifier<Comparable>(new Order(), new
			// NumberExpression<DataStat>("id") {
			// })
			List<DataStat> items = (List) this.dao.findAll(predicate, sort);
			res = new PageImpl<DataStat>(items);

		}
		return res;
	}
	
	boolean isStated = false;

	@Override
	public void stat() {
		if (!isStated) {
			isStated = true;
			List<Stock> stocks = this.stockService.getAll();
			for (Stock stock : stocks) {
				this.stat(stock);
			}
		}

	}

	/**
	 * 对财务报表进行比率分析
	 */
	@Override
	public void stat(Stock stock) {

		if (stock != null) {
			Map params = new HashMap();
			params.put("EQ|stockId", stock.getId());
			params.put("SORT|date", "asc");
			List<ReportData> reportDatas = this.reportDataService.getPage(params, 0, 0).getContent();
			for (ReportData reportData : reportDatas) {
				try {
					Date date = reportData.getDate();
					String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
					DataStat dataStat = this.getDataStat(stock, sDate);
					if (dataStat == null) {
						dataStat = new DataStat();
						dataStat.setStockId(stock.getId());
//						dataStat.setDateCycle(DateUtil.getDateFromString(sDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
						dataStat.setDateCycle(date);
					}
					// 净利率
					this.statNetProfitMargin(stock, reportData, dataStat);
					// 总资产周转率
					this.statTotalAssetsTurnover(stock, reportData, dataStat);
					// 总资产利润率
					this.statTotalAssetsNeProfitMargin(stock, reportData, dataStat);
					// 负债率
					this.statDebtToAssetsRatio(stock, reportData, dataStat);
					// 净资产回报率
					this.statROE(stock, dataStat);
					// 毛利率
					this.statGrossProfitMargin(stock, reportData, dataStat);
					// 计算核心利润率
					this.statCoreProfitMargin(stock, reportData, dataStat);
					// 分析每股指标
					this.statEveryStockData(stock, reportData, dataStat);
					this.save(dataStat);
					//根据机构评测进行估值
					this.valuationService.doStockValuation(stock.getId());
				} catch (Exception e) {
					logger.error(">>Faceye --> 分析股票:"+stock.getCode()+",抛出异常:" + e);
				}
			}
		}

	}

	/**
	 * 计算营业净利率=净利润/营业收入（不含营业外收入及投资收入等）
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月11日 上午10:56:54
	 */
	private DataStat statNetProfitMargin(Stock stock, ReportData reportData, DataStat dataStat) throws Exception {
		Date date = dataStat.getDateCycle();
		String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
		// 营业收入
		Double operatingIncome = reportData.getInComeSheet().getEle6().getCinst61_89();
		// List<FinancialData> operatingIncomes =
		// this.getFinancialData(stock.getId(), StockConstants.OPERATING_INCOME,
		// sDate);
		// 净利润
		// List<FinancialData> netProfits = this.getFinancialData(stock.getId(),
		// StockConstants.NET_PROFIT, sDate);
		Double netProfit = reportData.getInComeSheet().getEle9().getCinst24_128();
		if (operatingIncome != null && netProfit != null) {
			Double netProfitMargin = netProfit / operatingIncome;
			dataStat.setNetProfitMargin(netProfitMargin);
		}
		return dataStat;
	}

	/**
	 * 计算毛利率=（营业收入-营业成本)/营业收入
	 * 
	 * @param stock
	 * @param reportData
	 * @param dataStat
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月21日 上午10:26:58
	 */
	private DataStat statGrossProfitMargin(Stock stock, ReportData reportData, DataStat dataStat) throws Exception {
		// 营业收入
		Double operatingIncome = reportData.getInComeSheet().getEle6().getCinst61_89();
		// 营业成本
		Double operatingCosts = reportData.getInComeSheet().getEle7().getCinst3_97();
		Double grossProfitMargin = 0.0D;
		if (operatingIncome != null && operatingCosts != null && operatingIncome - operatingCosts != 0 && operatingIncome != 0) {
			grossProfitMargin = (operatingIncome - operatingCosts) / operatingIncome;
		}
		dataStat.setGrossProfitMargin(grossProfitMargin);
		return dataStat;
	}

	/**
	 * 计算总资产周转率=营业收入/总资产 总资产：(期初总资产+期末总资产)/2
	 * 
	 * @param stock
	 * @param dataStat
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月11日 上午11:15:00
	 */
	private DataStat statTotalAssetsTurnover(Stock stock, ReportData reportData, DataStat dataStat) throws Exception {
		Date date = dataStat.getDateCycle();
		String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
		// 营业收入
		// List<FinancialData> operatingIncomes =
		// this.getFinancialData(stock.getId(), StockConstants.OPERATING_INCOME,
		// sDate);
		Double operatingIncome = reportData.getInComeSheet().getEle6().getCinst61_89();
		// 资产总额(期末)
		// List<FinancialData> totalAssets =
		// this.getFinancialData(stock.getId(), StockConstants.TOTAL_ASSETS,
		// sDate);
		Double totalAsset = 0.0D;
		Double endPeriodTotalAsset = reportData.getBalanceSheet().getEle14().getCbsheet46_189();
		if (endPeriodTotalAsset == null) {
			endPeriodTotalAsset = 0.0D;
		}
		// 资产总额(期初)
		Map params = new HashMap();
		params.put("EQ|stockId", stock.getId());
		params.put("LT|date", date);
		if (date.getMonth() == 11) {
			params.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
		}
		params.put("SORT|date", "desc");
		List<ReportData> startPeriodReportData = this.reportDataService.getPage(params, 1, 1).getContent();
		Double startPeriodTotalAsset = 0.0D;
		if (CollectionUtils.isNotEmpty(startPeriodReportData)) {
			startPeriodTotalAsset = startPeriodReportData.get(0).getBalanceSheet().getEle14().getCbsheet46_189();
			if (startPeriodTotalAsset == null) {
				startPeriodTotalAsset = 0.0D;
			}
		}
		if (endPeriodTotalAsset != 0.0D && startPeriodTotalAsset != 0.0D) {
			totalAsset = (endPeriodTotalAsset + startPeriodTotalAsset) / 2;
		} else {
			totalAsset = endPeriodTotalAsset;
		}
		if (operatingIncome != null && totalAsset != null && totalAsset != 0) {
			Double totalAssetsTurnover = operatingIncome / totalAsset;
			dataStat.setTotalAssetsTurnover(totalAssetsTurnover);
		}
		// if (CollectionUtils.isNotEmpty(operatingIncomes) &&
		// CollectionUtils.isNotEmpty(totalAssets) && operatingIncomes.size() +
		// totalAssets.size() == 2) {
		// Double operatingIncome = operatingIncomes.get(0).getData();
		// Double totalAsset = totalAssets.get(0).getData();
		//
		// }
		return dataStat;
	}

	/**
	 * 计算总资产净利率=营业净利润率*总资产周转率
	 * 
	 * @param stock
	 * @param dataStat
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月11日 上午11:42:22
	 */
	private DataStat statTotalAssetsNeProfitMargin(Stock stock, ReportData reportData, DataStat dataStat) throws Exception {
		if (dataStat != null && dataStat.getTotalAssetsTurnover() != null && dataStat.getNetProfitMargin() != null) {
			Double totalAssetsNetProfitMargin = dataStat.getNetProfitMargin() * dataStat.getTotalAssetsTurnover();
			dataStat.setTotalAssetsNetProfitMargin(totalAssetsNetProfitMargin);
		}
		return dataStat;
	}

	/**
	 * 计算资产负债率=总负债/总资产
	 * 
	 * @param stock
	 * @param dataStat
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月11日 上午11:47:53
	 */
	private DataStat statDebtToAssetsRatio(Stock stock, ReportData reportData, DataStat dataStat) throws Exception {
		Date date = dataStat.getDateCycle();
		String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
		// 资产总额
		// List<FinancialData> totalAssets =
		// this.getFinancialData(stock.getId(), StockConstants.TOTAL_ASSETS,
		// sDate);
		Double totalAsset = reportData.getBalanceSheet().getEle14().getCbsheet46_189();
		// 总负债
		Double totalLiabilite = reportData.getBalanceSheet().getEle16().getCbsheet77_230();
		if (totalAsset != null && totalLiabilite != null && totalAsset != 0) {
			Double debtToAssetsRatio = totalLiabilite / totalAsset;
			dataStat.setDebtToAssetsRatio(debtToAssetsRatio);
		}
		return dataStat;
	}

	/**
	 * 计算净资产收益率=总姿产净利率*权益乘数 权益乘数=1/(1-资产负债率)
	 * 
	 * @param stock
	 * @param dataStat
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月11日 上午11:58:31
	 */
	private DataStat statROE(Stock stock, DataStat dataStat) throws Exception {
		if (dataStat.getDebtToAssetsRatio() != null && dataStat.getTotalAssetsNetProfitMargin() != null) {
			Double roe = dataStat.getTotalAssetsNetProfitMargin() * (1 / (1 - dataStat.getDebtToAssetsRatio()));
			dataStat.setRoe(roe);
		}
		return dataStat;
	}

	/**
	 * 计算核心利润率 核心利润率=（毛利-营业税金及附加-三项费用）/营业收入
	 * 
	 * @param stock
	 * @param dataStat
	 * @return
	 * @throws Exception
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月10日 下午6:57:12
	 */
	private DataStat statCoreProfitMargin(Stock stock, ReportData reportData, DataStat dataStat) throws Exception {

		Double coreProfitMargin = 0D;
		// 营业收入
		Double operateIncome = reportData.getInComeSheet().getEle6().getCinst1_90();
		// 营业成本
		Double operateCost = reportData.getInComeSheet().getEle7().getCinst3_97();
		// 营业税金及附加
		Double operateShui = reportData.getInComeSheet().getEle7().getCinst4_108();
		// 管理费用
		Double manageCost = reportData.getInComeSheet().getEle7().getCinst9_110();
		// 销售费用
		Double saleCost = reportData.getInComeSheet().getEle7().getCinst8_109();
		// 财务费用
		Double caiwuCost = reportData.getInComeSheet().getEle7().getCinst10_111();
		Double fenzi = 0D;
		if (operateIncome != null) {
			fenzi += operateIncome;
		}
		if (operateCost != null) {
			fenzi -= operateCost;
		}
		if (operateShui != null) {
			fenzi -= operateShui;
		}
		if (manageCost != null) {
			fenzi -= manageCost;
		}
		if (saleCost != null) {
			fenzi -= saleCost;
		}
		if (caiwuCost != null) {
			fenzi -= caiwuCost;
		}

		if (operateIncome != null && operateIncome > 0) {
			coreProfitMargin = fenzi / operateIncome;
		}
		dataStat.setCoreProfitMargin(coreProfitMargin);
		return dataStat;
	}

	/**
	 * 数据分析结果是否存在
	 * 
	 * @param stock
	 * @param date
	 * @param key
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月22日 下午4:25:20
	 */
	private DataStat getDataStat(Stock stock, String date) {
		DataStat dataStat = null;
		Map params = new HashMap();
		params.put("EQ|stockId", stock.getId());
		params.put("GTE|dateCycle", DateUtil.getDateFromString(date + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		params.put("LTE|dateCycle", DateUtil.getDateFromString(date + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		List<DataStat> dataStats = this.getPage(params, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(dataStats)) {
			dataStat = dataStats.get(0);
		}
		return dataStat;
	}

	

	/**
	 * 根据特定条件筛选股票
	 */
	@Override
	public List<StatRecord> getStatResults(Map params) {
		List<StatRecord> stockRecords = new ArrayList<StatRecord>(0);
		Long reportCategoryId = MapUtils.getLong(params, "reportCategoryId");
		Map stockParams = new HashMap();
		if (reportCategoryId != null) {
			stockParams.put("EQ|reportCategory.$id", reportCategoryId);
		}
		List<Stock> stocks = this.stockService.getPage(stockParams, 0, 0).getContent();
		if (CollectionUtils.isNotEmpty(stocks)) {
			for (Stock stock : stocks) {
				List<DataStat> dataStats = this.totalAssetsNetProfitMarginFilter(stock, params);
				if (CollectionUtils.isNotEmpty(dataStats)) {
					StatRecord statRecord = new StatRecord();
					statRecord.setDataStats(dataStats);
					statRecord.setStock(stock);
					stockRecords.add(statRecord);
				}
			}
		}
		Collections.sort(stockRecords, new StatRecordComparator());
		return stockRecords;
	}

	class StatRecordComparator implements Comparator<StatRecord> {

		@Override
		public int compare(StatRecord o1, StatRecord o2) {
			int res = 0;
			// 以多年平均总资产净收益率排序
			res = o2.getAvgTotalAssetsNetProfitMargin().compareTo(o1.getAvgTotalAssetsNetProfitMargin());
			return res;
		}

	}

	/**
	 * 总资产净利率过滤器
	 * 
	 * @param stock
	 * @param params
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月22日 下午6:00:41
	 */
	private List<DataStat> totalAssetsNetProfitMarginFilter(Stock stock, Map params) {
		List<DataStat> results = null;
		Long stockId = stock.getId();
		Integer type = MapUtils.getInteger(params, "type");
		Integer years = MapUtils.getInteger(params, "years");// 连续多少年

		// 总资产净利率过滤器
		// Double totalAssetsNetProfitMargin = MapUtils.getDouble(params,
		// "totalAssetsNetProfitMargin");
		Double minTotalAssetsNetProfitMargin = MapUtils.getDouble(params, "minTotalAssetsNetProfitMargin");
		Double maxTotalAssetsNetProfitMargin = MapUtils.getDouble(params, "maxTotalAssetsNetProfitMargin");
		if (minTotalAssetsNetProfitMargin == null) {
			minTotalAssetsNetProfitMargin = 0.05D;
		} else {
			minTotalAssetsNetProfitMargin = minTotalAssetsNetProfitMargin / 100;
		}
		if (maxTotalAssetsNetProfitMargin == null) {
			maxTotalAssetsNetProfitMargin = 0.5D;
		} else {
			maxTotalAssetsNetProfitMargin = maxTotalAssetsNetProfitMargin / 100;
		}
		if (years == null) {
			years = 5;
		}
		// 取最后五年的报表
		Date now = new Date();
		Date begin = new Date(now.getTime() - 5 * 365 * 24 * 60 * 60 * 1000L);
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(now.getYear() - 5, 11, 31);
		if (type == null) {
			type = 0;// 年报
		}
		// if (totalAssetsNetProfitMargin == null) {
		// totalAssetsNetProfitMargin = 0.2;// 1%
		// }
		Map dataStatParams = new HashMap();
		dataStatParams.put("EQ|stockId", stockId);
		dataStatParams.put("EQ|type", type);
		dataStatParams.put("GTE|totalAssetsNetProfitMargin", minTotalAssetsNetProfitMargin);
		dataStatParams.put("LTE|totalAssetsNetProfitMargin", maxTotalAssetsNetProfitMargin);
		dataStatParams.put("GTE|dateCycle", begin);
		dataStatParams.put("SORT|dateCycle:0", "desc");
		// dataStatParams.put("SORT|totalAssetsNetProfitMargin:1", "desc");
		List<DataStat> dataStats = this.getPage(dataStatParams, 0, 5).getContent();
		if (CollectionUtils.isNotEmpty(dataStats) && dataStats.size() >= 5) {
			results = dataStats;
		}
		return results;
	}

	///////////////////////////////////////// 分析每股指标///////////////////////////////////////////////////
	private void statEveryStockData(Stock stock, ReportData reportData, DataStat dataStat) {
		try {
			TotalStock totalStock = this.getTotalStock(stock, reportData);
			if (totalStock != null) {
				this.statEps(totalStock, reportData, dataStat);
				this.statBps(totalStock, reportData, dataStat);
				this.statDps(totalStock, reportData, dataStat);
				this.statROCE(totalStock, reportData, dataStat);
			}
		} catch (Exception e) {
			logger.error(">>FaceYe 分析每股指标异常:" + e);
		}
	}

	/**
	 * 分析每股收益
	 * 
	 * @param stock
	 * @param reportData
	 * @param dataStat
	 */
	private void statEps(TotalStock totalStock, ReportData reportData, DataStat dataStat) throws Exception {
		Double profit = reportData.getInComeSheet().getEle9().getCinst24_128();// 净利润
		if (profit != null && totalStock.getStockNum() > 0) {
			Double eps = profit / totalStock.getStockNum();
			dataStat.setEps(eps);
		}
	}

	/**
	 * 分析每股净资产
	 * 
	 * @param totalStock
	 * @param reportData
	 * @param dataStat
	 */
	private void statBps(TotalStock totalStock, ReportData reportData, DataStat dataStat) throws Exception {
		Double data = reportData.getBalanceSheet().getEle17().getCbsheet86_243();// 所有者权益
		if (data != null && totalStock.getStockNum() > 0) {
			Double bps = data / totalStock.getStockNum();
			dataStat.setBps(bps);
		}
	}

	/**
	 * 分析每股股利
	 * 
	 * @param totalStock
	 * @param reportData
	 * @param dataStat
	 */
	private void statDps(TotalStock totalStock, ReportData reportData, DataStat dataStat) throws Exception {
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", reportData.getStockId());
		Date date = reportData.getDate();
		if (date != null) {
			String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
			String sEndDate = DateUtil.formatDate(date, "yyyy");
			Date start = DateUtil.getDateFromString(sDate + " 00:00:01");
			Date end = DateUtil.getDateFromString(NumberUtils.toInt(sEndDate)+1 + "-12-31 23:59:59");
			searchParams.put("GTE|publishDate", start);
			searchParams.put("LTE|publishDate", end);
			searchParams.put("SORT|publishDate", "asc");
			Page<BonusRecord> bonusRecords = this.bonusRecordService.getPage(searchParams, 1, 0);
			if (bonusRecords != null && CollectionUtils.isNotEmpty(bonusRecords.getContent())) {
				dataStat.setDps(bonusRecords.getContent().get(0).getDividend());
			}
		}
	}

	/**
	 * 普通股权益报酬率（净利润/股东权益[前一期]）：注：股份变化化引起本数据的变化 ，因是每股指标，所以，使用 ROCE(1)=ESP(1)/BPS(0)
	 * 本处认为净利润=会计期内股东的综合收益
	 * 同时，没有考虑期初，期末股本变化对数据影响
	 * @param totalStock
	 * @param reportData
	 * @param dataStat
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月15日 下午8:33:10
	 */
	private void statROCE(TotalStock totalStock, ReportData reportData, DataStat dataStat) throws Exception {
		// 综合收益
		Double profit = reportData.getInComeSheet().getEle9().getCinst24_128();
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", reportData.getStockId());
		searchParams.put("LT|dateCycle", reportData.getDate());
		searchParams.put("EQ|type", reportData.getType());
		searchParams.put("SORT|dateCycle", "desc");
		Page<DataStat> dataStats=this.dao.getPage(searchParams, 1, 0);
//		Page<ReportData> reportDatas = this.reportDataService.getPage(searchParams, 1, 0);
		if (dataStats != null && CollectionUtils.isNotEmpty(dataStats.getContent())) {
			DataStat lastPeriodDataStat = dataStats.getContent().get(0);
			// 期初股东权益
			Double bps0=lastPeriodDataStat.getBps();
			Double eps1=dataStat.getEps();
//			Double netAssets = lastPeriodReportData.getBalanceSheet().getEle17().getCbsheet86_243();
			if (bps0 != null && eps1 != null && bps0 > 0) {
				dataStat.setRoce(eps1 / bps0);
			}
		}
	}

	/**
	 * 获取股本
	 * 
	 * @param stock
	 * @param reportData
	 * @return
	 */
	private TotalStock getTotalStock(Stock stock, ReportData reportData) {
		TotalStock totalStock = null;
		Map searchParams = new HashMap();
		String date = DateUtil.formatDate(reportData.getDate(), "yyyy-MM-dd");
		searchParams.put("EQ|stockId", stock.getId());
		searchParams.put("LTE|changeDate", DateUtil.getDateFromString(date + " 23:59:59"));
		searchParams.put("sort|changeDate", "desc");
		Page<TotalStock> page = this.totalStockService.getPage(searchParams, 1, 1);
		if (page != null && CollectionUtils.isNotEmpty(page.getContent())) {
			totalStock = page.getContent().get(0);
		}
		return totalStock;
	}
	///////////////////////////////////////// 结束分析每股指标////////////////////////////////////////////////

}/** @generate-service-source@ **/
