package com.faceye.component.stock.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.DataStatRepository;
import com.faceye.component.stock.repository.mongo.customer.DataStatCustomerRepository;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.ServiceException;
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
	private FinancialDataService financialDataService = null;
	@Autowired
	private StockService stockService = null;
	@Autowired
	private ReportDataService reportDataService = null;

	@Autowired
	public DataStatServiceImpl(DataStatRepository dao) {
		super(dao);
	}

	@Override
	public Page<DataStat> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<DataStat> entityPath = resolver.createPath(entityClass);
		// PathBuilder<DataStat> builder = new PathBuilder<DataStat>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
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
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<DataStat>("id") {
			// })
			List<DataStat> items = (List) this.dao.findAll(predicate, sort);
			res = new PageImpl<DataStat>(items);

		}
		return res;
	}

	/**
	 * 计算营业净利率=净利润/营业收入（不含营业外收入及投资收入等）
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月11日 上午10:56:54
	 */
	private DataStat statNetProfitMargin(Stock stock, ReportData reportData, DataStat dataStat) {
		Date date = dataStat.getDateCycle();
		String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
		// 营业收入
		Double operatingIncome = reportData.getInComeSheet().getEle6().getCinst61_89();
		// List<FinancialData> operatingIncomes = this.getFinancialData(stock.getId(), StockConstants.OPERATING_INCOME, sDate);
		// 净利润
		// List<FinancialData> netProfits = this.getFinancialData(stock.getId(), StockConstants.NET_PROFIT, sDate);
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
	private DataStat statGrossProfitMargin(Stock stock, ReportData reportData, DataStat dataStat) {
		// 营业收入
		Double operatingIncome = reportData.getInComeSheet().getEle6().getCinst61_89();
		// 营业成本
		Double operatingCosts = reportData.getInComeSheet().getEle7().getCinst3_97();
		Double grossProfitMargin = 0.0D;
		if (operatingIncome - operatingCosts != 0) {
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
	private DataStat statTotalAssetsTurnover(Stock stock, ReportData reportData, DataStat dataStat) {
		Date date = dataStat.getDateCycle();
		String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
		// 营业收入
		// List<FinancialData> operatingIncomes = this.getFinancialData(stock.getId(), StockConstants.OPERATING_INCOME, sDate);
		Double operatingIncome = reportData.getInComeSheet().getEle6().getCinst61_89();
		// 资产总额(期末)
		// List<FinancialData> totalAssets = this.getFinancialData(stock.getId(), StockConstants.TOTAL_ASSETS, sDate);
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
		// if (CollectionUtils.isNotEmpty(operatingIncomes) && CollectionUtils.isNotEmpty(totalAssets) && operatingIncomes.size() + totalAssets.size() == 2) {
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
	private DataStat statTotalAssetsNeProfitMargin(Stock stock, ReportData reportData, DataStat dataStat) {
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
	private DataStat statDebtToAssetsRatio(Stock stock, ReportData reportData, DataStat dataStat) {
		Date date = dataStat.getDateCycle();
		String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
		// 资产总额
		// List<FinancialData> totalAssets = this.getFinancialData(stock.getId(), StockConstants.TOTAL_ASSETS, sDate);
		Double totalAsset = reportData.getBalanceSheet().getEle14().getCbsheet46_189();
		// 总负债
		Double totalLiabilite = reportData.getBalanceSheet().getEle16().getCbsheet77_230();
		if (totalAsset != null && totalLiabilite != null) {
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
	private DataStat statROE(Stock stock, DataStat dataStat) {
		if (dataStat.getDebtToAssetsRatio() != null && dataStat.getTotalAssetsNetProfitMargin() != null) {
			Double roe = dataStat.getTotalAssetsNetProfitMargin() * (1 / (1 - dataStat.getDebtToAssetsRatio()));
			dataStat.setRoe(roe);
		}
		return dataStat;
	}

	/**
	 * 获取指定的基础财务数据
	 * 
	 * @param stockId
	 * @param accountingSubjectId
	 * @param date
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月11日 上午11:03:30
	 */
	private List<FinancialData> getFinancialData(Long stockId, Long accountingSubjectId, String date) {
		List<FinancialData> datas = null;
		Map params = new HashMap();
		params.put("EQ|stockId", stockId);
		params.put("EQ|accountingSubjectId", accountingSubjectId);
		params.put("GTE|date", DateUtil.getDateFromString(date + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		params.put("LTE|date", DateUtil.getDateFromString(date + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		datas = this.financialDataService.getPage(params, 0, 0).getContent();
		return datas;
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

	@Override
	public void stat(Stock stock) {
		try {
			if (stock != null) {
				Map params = new HashMap();
				params.put("EQ|stockId", stock.getId());
				List<ReportData> reportDatas = this.reportDataService.getPage(params, 0, 0).getContent();
				for (ReportData reportData : reportDatas) {
					Date date = reportData.getDate();
					String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
					DataStat dataStat = this.getDataStat(stock, sDate);
					if (dataStat == null) {
						dataStat = new DataStat();
						dataStat.setStockId(stock.getId());
						dataStat.setDateCycle(DateUtil.getDateFromString(sDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
					}
					// 净利率
					this.statNetProfitMargin(stock, reportData, dataStat);
					// 总资产周围率
					this.statTotalAssetsTurnover(stock, reportData, dataStat);
					// 总资产利润率
					this.statTotalAssetsNeProfitMargin(stock, reportData, dataStat);
					// 负债率
					this.statDebtToAssetsRatio(stock, reportData, dataStat);
					// 净资产回报率
					this.statROE(stock, dataStat);
					// 毛利率
					this.statGrossProfitMargin(stock, reportData, dataStat);
					this.save(dataStat);
				}
			}
		} catch (Exception e) {
			logger.error(">>Faceye --> 分析股票总资产回报率抛出异常:", e);
		}
	}

}/** @generate-service-source@ **/
