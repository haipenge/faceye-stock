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

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.DailyStatRepository;
import com.faceye.component.stock.repository.mongo.customer.DailyStatCustomerRepository;
import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.service.DailyStatService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

/**
 * DailyStat 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class DailyStatServiceImpl extends BaseMongoServiceImpl<DailyStat, Long, DailyStatRepository> implements DailyStatService {
	@Autowired
	private DailyStatCustomerRepository dailyStatCustomerRepository = null;
	@Autowired
	private DailyDataService dailyDataService = null;
	@Autowired
	private StockService stockService = null;
	@Autowired
	private ReportDataService reportDataService = null;

	@Autowired
	public DailyStatServiceImpl(DailyStatRepository dao) {
		super(dao);
	}

	@Override
	public Page<DailyStat> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<DailyStat> entityPath = resolver.createPath(entityClass);
		// PathBuilder<DailyStat> builder = new PathBuilder<DailyStat>(entityPath.getType(), entityPath.getMetadata());
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
		Page<DailyStat> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<DailyStat>("id") {
			// })
			List<DailyStat> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<DailyStat>(items);

		}
		return res;
	}

	/**
	 * 分析30天股票价格的变化情况
	 */
	@Override
	public void statPriceIn30Days() {
		List<Stock> stocks = this.stockService.getAll();
		if (CollectionUtils.isNotEmpty(stocks)) {
			for (Stock stock : stocks) {
				this.statPriceIn30Days(stock);
				this.statPe(stock);
			}
		}
	}

	private void statPriceIn30Days(Stock stock) {
		DailyStat dailyStat = null;
		Map statParams = new HashMap();
		statParams.put("EQ|stockId", stock.getId());
		List<DailyStat> dailyStats = this.getPage(statParams, 0, 0).getContent();
		if (CollectionUtils.isNotEmpty(dailyStats)) {
			dailyStat = dailyStats.get(0);
		} else {
			dailyStat = new DailyStat();
			dailyStat.setStockId(stock.getId());
			dailyStat.setCategoryId(stock.getCategory().getId());
		}
		Map params = new HashMap();
		Date now = new Date();
		Date befor30Day = new Date(now.getTime() - 31 * 24 * 60 * 60 * 1000L);
		params.put("EQ|stockId", stock.getId());
		params.put("GTE|createDate", befor30Day);
		List<DailyData> dailyDatas = this.dailyDataService.getPage(params, 0, 0).getContent();
		Double topPriceOf30Days = 0.0D;
		Date topPriceDate = null;
		Double lowerPriceOf30Days = 0.0D;
		Date lowerPriceDate = null;
		Double todayPrice=null;
		if (dailyDatas != null) {
			int index = 0;
			for (DailyData dailyData : dailyDatas) {
				if (index == 0) {
					topPriceOf30Days = dailyData.getJintianzuigaojia();
					topPriceDate = dailyData.getDate();
					lowerPriceOf30Days = dailyData.getJintianzuidijia();
					lowerPriceDate = dailyData.getDate();
					todayPrice=dailyData.getShoupanjia();
					index++;
					
				} else {
					if (dailyData.getJintianzuigaojia().compareTo(topPriceOf30Days) > 0) {
						topPriceOf30Days = dailyData.getJintianzuigaojia();
						topPriceDate = dailyData.getDate();
					}
					if (dailyData.getJintianzuidijia().compareTo(lowerPriceOf30Days) < 0) {
						lowerPriceOf30Days = dailyData.getJintianzuidijia();
						lowerPriceDate = dailyData.getDate();
					}
				}
			}
			dailyStat.setLowPriceOf30Day(lowerPriceOf30Days);
			dailyStat.setLowPriceDate(lowerPriceDate);
			dailyStat.setTopPriceOf30Day(topPriceOf30Days);
			dailyStat.setTopPriceDate(topPriceDate);
			this.save(dailyStat);
		}
	}

	/**
	 * 分析市盈率 同花顺软件中： 市盈率=股价/去年年报EPS 动态市盈率(TTM)=股价/（最新报表EPS*（1/报表截止日占全年的比例）-->同花顺 。<br>
	 * 市净率指的是每股股价与每股净资产的比率。每股净资产= 股东权益÷总股数<br>
	 * 
	 * 另： 动态市盈率＝静态市盈率/(1+年复合增长率)N次方<br>
	 * 动态市盈率，其计算公式是以静态市盈率为基数,乘以动态系数，该系数为1／（1＋i）^n，i为企业每股收益的增长性比率，n为企业的可持续发展的存续期。<br>
	 * 比如说，上市公司目前股价为20元，每股收益为0．38元，去年同期每股收益为0．28元，成长性为35%，即i＝35%，该企业未来保持该增长速度的时间可持续5年，即n＝5.<br>
	 * 则动态系数为1／（1＋35％）^5＝22%。相应地，动态市盈率为11．6倍即：52（静态市盈率：20元／0．38元＝52）×22%
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月24日 下午3:21:51
	 */
	private void statPe(Stock stock) {
		DailyStat dailyStat = null;
		Map statParams = new HashMap();
		statParams.put("EQ|stockId", stock.getId());
		List<DailyStat> dailyStats = this.getPage(statParams, 0, 0).getContent();
		if (CollectionUtils.isNotEmpty(dailyStats)) {
			dailyStat = dailyStats.get(0);
		} else {
			dailyStat = new DailyStat();
			dailyStat.setCategoryId(stock.getCategory().getId());
			dailyStat.setStockId(stock.getId());
		}
		Map params = new HashMap();
		params.put("EQ|stockId", stock.getId());
		params.put("SORT|date", "desc");
		List<DailyData> dailyDatas = this.dailyDataService.getPage(params, 1, 1).getContent();
		DailyData dailyData = CollectionUtils.isEmpty(dailyDatas) ? null : dailyDatas.get(0);
		if (dailyData != null) {
			Double shoupanjia = dailyData.getShoupanjia();
			// 计算市盈率
			Double pe = null;
			params = new HashMap();
			params.put("EQ|stockId", stock.getId());
			params.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
			params.put("SORT|date", "desc");
			// 取得最近一份年报
			List<ReportData> reportDatas = this.reportDataService.getPage(params, 1, 1).getContent();
			// 取得每股盈利
			Double yearEps = null;
			if (CollectionUtils.isNotEmpty(reportDatas)) {
				yearEps = reportDatas.get(0).getInComeSheet().getEle10().getMgsy_131();
			}
			if (yearEps != null && yearEps > 0 && shoupanjia != null) {
				pe = shoupanjia / yearEps;
			}
			// 计算动态市盈率
			Double dynamicPe = null;
			params = new HashMap();
			params.put("EQ|stockId", stock.getId());
			params.put("SORT|date", "desc");
			// 取得最近一份财务报告
			reportDatas = this.reportDataService.getPage(params, 1, 1).getContent();
			if (CollectionUtils.isNotEmpty(reportDatas)) {
				ReportData reportData = reportDatas.get(0);
				if (reportData.getType() == StockConstants.REPORT_TYPE_YEAR) {
					dynamicPe = pe;
				} else {
					Double reportEps = reportData.getInComeSheet().getEle10().getMgsy_131();
					if (reportEps != null && reportEps > 0 && shoupanjia != null) {
						dynamicPe = reportData.getType() * shoupanjia / (4 * reportEps);
					}
				}
			}
			// 计算市净率
			Double pb = null;
			dailyStat.setPe(pe);
			dailyStat.setDynamicPe(dynamicPe);
			this.save(dailyStat);
		}
	}

}/** @generate-service-source@ **/
