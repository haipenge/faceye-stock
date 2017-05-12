package com.faceye.component.stock.service.impl;

import java.util.Collections;
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
import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.DailyStatRepository;
import com.faceye.component.stock.repository.mongo.customer.DailyDataCustomerRepository;
import com.faceye.component.stock.repository.mongo.customer.DailyStatCustomerRepository;
import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.service.DailyStatService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StarDataStatService;
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
public class DailyStatServiceImpl extends BaseMongoServiceImpl<DailyStat, Long, DailyStatRepository>
		implements DailyStatService {
	@Autowired
	private DailyStatCustomerRepository dailyStatCustomerRepository = null;
	@Autowired
	private DailyDataService dailyDataService = null;
	@Autowired
	private StockService stockService = null;
	@Autowired
	private ReportDataService reportDataService = null;
	@Autowired
	private StarDataStatService starDataStatService = null;
	@Autowired
	private DailyDataCustomerRepository dailyDataCustomerRepository = null;

	@Autowired
	public DailyStatServiceImpl(DailyStatRepository dao) {
		super(dao);
	}

	@Override
	public Page<DailyStat> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver =
		// SimpleEntityPathResolver.INSTANCE;
		// EntityPath<DailyStat> entityPath = resolver.createPath(entityClass);
		// PathBuilder<DailyStat> builder = new
		// PathBuilder<DailyStat>(entityPath.getType(),
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
		Page<DailyStat> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new
			// OrderSpecifier<Comparable>(new Order(), new
			// NumberExpression<DailyStat>("id") {
			// })
			List<DailyStat> items = (List) this.dao.findAll(predicate, sort);
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
		params.put("GTE|date", befor30Day);
		params.put("SORT|date", "desc");
		params.put("GT|kaipanjia", 0D);
		List<DailyData> dailyDatas = this.dailyDataService.getPage(params, 0, 0).getContent();
		// 设置昨天交易收盘价
		if (CollectionUtils.isNotEmpty(dailyDatas)) {
			int size = dailyDatas.size();
			for (int i = 0; i < dailyDatas.size(); i++) {
				if (i + 1 < size) {
					DailyData todayData = dailyDatas.get(i);
					DailyData yesterdayData = dailyDatas.get(i + 1);
					todayData.setYesterdayPrice(yesterdayData.getShoupanjia());
					this.dailyDataService.save(todayData);
				}
			}
			// for (int i = dailyDatas.size() - 1; i > 0; i--) {
			// DailyData dailyData = dailyDatas.get(i);
			// if (i - 1 > 0) {
			// DailyData nextDayData = dailyDatas.get(i - 1);
			// nextDayData.setYesterdayPrice(dailyData.getShoupanjia());
			// this.dailyDataService.save(nextDayData);
			// }
			// }
		}
		Double topPriceOf30Days = 0.0D;
		Date topPriceDate = null;
		Double lowerPriceOf30Days = 0.0D;
		Date lowerPriceDate = null;
		Double todayPrice = null;
		Double yesterdayPrice = null;
		if (dailyDatas != null) {
			int index = 0;
			for (DailyData dailyData : dailyDatas) {
				if (index == 0) {
					if (dailyData.getKaipanjia() > 0) {
						topPriceOf30Days = dailyData.getJintianzuigaojia();
						topPriceDate = dailyData.getDate();
						lowerPriceOf30Days = dailyData.getJintianzuidijia();
						lowerPriceDate = dailyData.getDate();
						todayPrice = dailyData.getShoupanjia();
						yesterdayPrice = dailyData.getYesterdayPrice();
						index++;
					}
				} else {
					if (dailyData.getJintianzuigaojia().compareTo(topPriceOf30Days) > 0 && topPriceOf30Days > 0) {
						topPriceOf30Days = dailyData.getJintianzuigaojia();
						topPriceDate = dailyData.getDate();
					}
					if (dailyData.getJintianzuidijia().compareTo(lowerPriceOf30Days) < 0 && lowerPriceOf30Days > 0) {
						lowerPriceOf30Days = dailyData.getJintianzuidijia();
						lowerPriceDate = dailyData.getDate();
					}
				}
			}
			dailyStat.setLowPriceOf30Day(lowerPriceOf30Days);
			dailyStat.setLowPriceDate(lowerPriceDate);
			dailyStat.setTopPriceOf30Day(topPriceOf30Days);
			dailyStat.setTopPriceDate(topPriceDate);
			dailyStat.setTodayPrice(todayPrice);
			// 计算股价振幅
			Double priceAmplitude = null;
			Double priceChangeDeep = topPriceOf30Days - lowerPriceOf30Days;
			if (lowerPriceDate != null && topPriceDate != null) {
				if (lowerPriceDate.getTime() < topPriceDate.getTime() && lowerPriceOf30Days > 0) {
					priceAmplitude = priceChangeDeep / lowerPriceOf30Days;
				} else {
					if (topPriceOf30Days > 0) {
						priceAmplitude = -priceChangeDeep / topPriceOf30Days;
					}
				}
			} else {
				logger.debug(">>FaceYe --> topPriceDate or lowerPriceDate is null.");
			}
			dailyStat.setPriceAmplitude(priceAmplitude);
			if (yesterdayPrice != null && yesterdayPrice > 0) {
				Double increaseRate = (todayPrice - yesterdayPrice) / yesterdayPrice;
				dailyStat.setTodayIncreaseRate(increaseRate);
			}
			this.save(dailyStat);
			stock.setDailyStat(dailyStat);
			this.stockService.save(stock);
		}
	}

	/**
	 * 分析市盈率 同花顺软件中： 市盈率=股价/去年年报EPS 动态市盈率(TTM)=股价/（最新报表EPS*（1/报表截止日占全年的比例）-->同花顺
	 * 。<br>
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
		Integer start = 1;
		Integer size = 100;
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
		params.put("SORT|date", "asc");
		List<DailyData> dailyDatas = this.dailyDataService.getPage(params, start, size).getContent();
		int index = 0;
		while (CollectionUtils.isNotEmpty(dailyDatas)) {
			DailyData dailyData = dailyDatas.get(index);
			index++;
			// 计算市盈率
			Double pe = null;
			// 计算动态市盈率
			Double dynamicPe = null;
			if (dailyData != null) {
				Double shoupanjia = dailyData.getShoupanjia();
				params = new HashMap();
				params.put("EQ|stockId", stock.getId());
				params.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
				params.put("LTE|date", dailyData.getDate());
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
				params = new HashMap();
				params.put("EQ|stockId", stock.getId());
				params.put("SORT|date", "desc");
				params.put("LTE|date", dailyData.getDate());
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
				dailyData.setPe(pe);
				dailyData.setDynamicPe(dynamicPe);
				dailyData.setPb(pb);
				this.dailyDataService.save(dailyData);
				
			}
			// 如果数据循环结束，重新获取下一批数据
			if (index + 1 == dailyDatas.size()) {
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					logger.error(">>FaceYe Throws Exception when thread sleep.");
				}
				start++;
				dailyDatas = this.dailyDataService.getPage(params, start, size).getContent();
				index = 0;
				if(CollectionUtils.isEmpty(dailyDatas)){
					dailyStat.setPe(pe);
					dailyStat.setDynamicPe(dynamicPe);
					this.save(dailyStat);
				}
			}
		}
	}

	/**
	 * 分析星标数据并存储 分析avg数据，是否为多头排列
	 */
	@Override
	public void statDailyData2FindStar() {
		List<Stock> stocks = this.stockService.getAll();
		if (CollectionUtils.isNotEmpty(stocks)) {
			for (Stock stock : stocks) {
				this.statDailyData2FindStar(stock);
			}
		}
	}

	@Override
	public void statDailyData2FindStar(Stock stock) {
		this.dailyDataCustomerRepository.resetDailyDataStatType(stock.getId(), 0);
		this.statDailyData2FindAvgStar(stock);
		this.statDailyData2FindMACDStar(stock);
		this.statDailyData2FindMacdAndAvgStar(stock);
	}

	/**
	 * 发现均线多头排列<br>
	 * 条件：avg5>avg10>avg20,并且连续三天<br>
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月16日 上午10:53:42
	 */
	private void statDailyData2FindAvgStar(Stock stock) {
		int type = 0;
		Date lastStarAppearDate = null;
		Map params = new HashMap();
		params.put("EQ|stockId", stock.getId());
		params.put("SORT|date", "asc");
		params.put("GT|kaipanjia", 0D);
		List<DailyData> dailyDatas = this.dailyDataService.getPage(params, 0, 0).getContent();
		if (CollectionUtils.isNotEmpty(dailyDatas)) {
			boolean isStarData = false;
			DailyData starDailyData = null;
			int count = 0;
			int index = 0;
			int signIndex = 0;
			for (DailyData dailyData : dailyDatas) {
				index++;
				if (dailyData.getAvg5() != null && dailyData.getAvg10() != null && dailyData.getAvg20() != null) {
					if (!isStarData) {
						if (dailyData.getAvg5() > dailyData.getAvg10() && dailyData.getAvg10() > dailyData.getAvg20()) {
							if (starDailyData == null) {
								starDailyData = dailyData;
							}
							if (count == 0) {
								signIndex = index;
							}
							if (index - signIndex == count) {
								count++;
							}
						} else {
							count = 0;
							signIndex = 0;
							starDailyData = null;
						}
					} else {
						if (dailyData.getAvg5() < dailyData.getAvg10() || dailyData.getAvg10() < dailyData.getAvg20()
								|| dailyData.getAvg5() < dailyData.getAvg20()) {
							isStarData = false;
							count = 0;
							signIndex = 0;
							starDailyData = null;
						}
					}
				}
				// 连续三天正排列，则列为星标数据
				// 目的，过滤杂音
				if (count >= 3 && !isStarData) {
					isStarData = true;
					count = 0;
					signIndex = 0;
					type = StockConstants.STOCK_STAR_TYPE_1;
					starDailyData.setStarDataType(type);
					this.dailyDataService.save(starDailyData);
					lastStarAppearDate = starDailyData.getDate();

				}
			}
		}
		// 保存星标最后生成时间
		if (lastStarAppearDate != null) {
			stock.setLastStarAppearDate(lastStarAppearDate);
			this.stockService.save(stock);
		}

	}

	/**
	 * 分析每日数据，寻找macd+avg星标数据，标记type=2<br>
	 * 判断依据：<br>
	 * 1.在avg3日多头后，5日内出现 dif > dea 快线上穿慢线<br>
	 * -----以下目前未实现.<br>
	 * 2.最近10个交易日，最低价触及avg20附近，差距在(+-)5%以内<br>
	 * 3.MACD dif 上传dea dif<0,dea<0
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月13日 下午12:01:01
	 */
	public void statDailyData2FindMacdAndAvgStar(Stock stock) {
		Map dailyDataParams = new HashMap();
		dailyDataParams.put("EQ|stockId", stock.getId());
		dailyDataParams.put("SORT|date", "asc");
		dailyDataParams.put("GT|kaipanjia", 0D);
		dailyDataParams.put("EQ|starDataType", 1);
		// 获取均线连续三日排列整齐的股票(starDataType=1)
		List<DailyData> dailyDatas = this.dailyDataService.getPage(dailyDataParams, 1, 0).getContent();
		for (DailyData dailyData : dailyDatas) {
			Map macdParams = new HashMap();
			macdParams.put("EQ|stockId", stock.getId());
			macdParams.put("GT|date", dailyData.getDate());
			macdParams.put("GT|kaipanjia", 0D);
			macdParams.put("SORT|date", "asc");
			// 五个交易日内快线上穿慢线
			List<DailyData> macdDailyDatas = this.dailyDataService.getPage(macdParams, 1, 5).getContent();
			if (CollectionUtils.isNotEmpty(macdDailyDatas)) {
				int count = 0;
				for (int i = 0; i < macdDailyDatas.size(); i++) {
					DailyData macdDailyData = macdDailyDatas.get(i);
					// DIF > DEA ,快线上穿慢线
					if (macdDailyData.getDif() > macdDailyData.getDea()) {
						macdDailyData.setStarDataType(StockConstants.STOCK_STAR_TYPE_2);
						this.dailyDataService.save(macdDailyData);
						break;
					}
				}
			}
		}
	}

	/**
	 * 寻找macd dif 上穿dea 数据，并标记类型3
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月18日 上午9:39:45
	 */
	public void statDailyData2FindMACDStar(Stock stock) {
		Map dailyDataParams = new HashMap();
		dailyDataParams.put("EQ|stockId", stock.getId());
		dailyDataParams.put("SORT|date", "asc");
		dailyDataParams.put("GT|kaipanjia", 0D);
		List<DailyData> dailyDatas = this.dailyDataService.getPage(dailyDataParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(dailyDatas)) {
			boolean isContinue = true;
			for (DailyData dailyData : dailyDatas) {
				// 发现在0轴下方，dif 上穿dea的数据
				if (isContinue && dailyData.getDif() < 0 && dailyData.getDea() < 0
						&& dailyData.getDif() > dailyData.getDea()) {
					dailyData.setStarDataType(StockConstants.STOCK_STAR_TYPE_3);
					logger.debug(">>FaceYe find a macd star now. dif > dea " + dailyData.getDif() + " > "
							+ dailyData.getDea());
					this.dailyDataService.save(dailyData);
					isContinue = false;
				}
				// 当dif 下穿dea后，再继续 寻找dif > dea的交叉点
				if (!isContinue && dailyData.getDif() < dailyData.getDea()) {
					isContinue = true;
				}
			}
		}
	}

	/**
	 * 分析星标数据
	 */
	@Override
	public void statStarData() {
		List<Stock> stocks = this.stockService.getAll();
		if (CollectionUtils.isNotEmpty(stocks)) {
			for (Stock stock : stocks) {

				this.statStarData(stock);
			}
		}
	}

	/**
	 * 分析单只股票的星标
	 */
	public void statStarData(Stock stock) {
		this.starDataStatService.removeStockStarStatResults(stock.getId());
		Map starParams = new HashMap();
		starParams.put("EQ|stockId", stock.getId());
		starParams.put("GT|starDataType", 0);
		starParams.put("SORT|date", "asc");
		List<DailyData> starDailyDatas = this.dailyDataService.getPage(starParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(starDailyDatas)) {
			for (DailyData starDailyData : starDailyDatas) {
				Map dailyParams = new HashMap();
				dailyParams.put("EQ|stockId", stock.getId());
				dailyParams.put("GTE|date", starDailyData.getDate());
				dailyParams.put("SORT|date", "asc");
				dailyParams.put("GT|kaipanjia", 0D);
				List<DailyData> dailyDatas = this.dailyDataService.getPage(dailyParams, 1, 64).getContent();
				Map starDataStatParams = new HashMap();
				starDataStatParams.put("EQ|starDailyDataId", starDailyData.getId());
				starDataStatParams.put("EQ|starType", starDailyData.getStarDataType());
				List<StarDataStat> starDataStats = this.starDataStatService.getPage(starDataStatParams, 1, 0)
						.getContent();
				StarDataStat starDataStat = CollectionUtils.isNotEmpty(starDataStats) ? starDataStats.get(0)
						: new StarDataStat();
				starDataStat.setStarDailyDataId(starDailyData.getId());
				starDataStat.setStarDataDate(starDailyData.getDate());
				starDataStat.setStockId(stock.getId());
				starDataStat.setStarType(starDailyData.getStarDataType());
				if (CollectionUtils.isNotEmpty(dailyDatas) && dailyDatas.size() > 3) {
					Double max5DayIncreaseRate = 0D;// 5日最大涨幅
					Double max10DayIncreaseRate = 0D;// 10日最大涨幅
					Double max20DayIncreaseRate = 0D;// 20日最大涨幅
					Double max30DayIncreaseRate = 0D;// 30日最大涨
					Double max60DayIncreaseRate = 0D;// 60日最大涨幅
					Double start2BuyPrice = 0.0D;
					Double max5DayPrice = 0D;// 5日最高价
					Double max10DayPrice = 0D;// 10日最高价
					Double max20DayPrice = 0D;// 20日最高价
					Double max30DayPrice = 0D;// 30日最高价
					Double max60DayPrice = 0D;// 60日最高价
					for (int i = 3; i < dailyDatas.size(); i++) {
						DailyData data = dailyDatas.get(i);
						if (i == 3) {
							// 以第四天开盘价买入
							start2BuyPrice = data.getKaipanjia();
						}
						if (i <= 7) {
							max5DayPrice = (data.getShoupanjia() > max5DayPrice) ? data.getShoupanjia() : max5DayPrice;
							max10DayPrice = (data.getShoupanjia() > max10DayPrice) ? data.getShoupanjia()
									: max10DayPrice;
							max20DayPrice = (data.getShoupanjia() > max20DayPrice) ? data.getShoupanjia()
									: max20DayPrice;
							max30DayPrice = (data.getShoupanjia() > max30DayPrice) ? data.getShoupanjia()
									: max30DayPrice;
							max60DayPrice = (data.getShoupanjia() > max60DayPrice) ? data.getShoupanjia()
									: max60DayPrice;
						}
						if (i > 7 && i <= 12) {
							max10DayPrice = (data.getShoupanjia() > max10DayPrice) ? data.getShoupanjia()
									: max10DayPrice;
							max20DayPrice = (data.getShoupanjia() > max20DayPrice) ? data.getShoupanjia()
									: max20DayPrice;
							max30DayPrice = (data.getShoupanjia() > max30DayPrice) ? data.getShoupanjia()
									: max30DayPrice;
							max60DayPrice = (data.getShoupanjia() > max60DayPrice) ? data.getShoupanjia()
									: max60DayPrice;
						}
						if (i > 12 && i <= 22) {
							max20DayPrice = (data.getShoupanjia() > max20DayPrice) ? data.getShoupanjia()
									: max20DayPrice;
							max30DayPrice = (data.getShoupanjia() > max30DayPrice) ? data.getShoupanjia()
									: max30DayPrice;
							max60DayPrice = (data.getShoupanjia() > max60DayPrice) ? data.getShoupanjia()
									: max60DayPrice;
						}
						if (i > 22 && i <= 32) {
							max30DayPrice = (data.getShoupanjia() > max30DayPrice) ? data.getShoupanjia()
									: max30DayPrice;
							max60DayPrice = (data.getShoupanjia() > max60DayPrice) ? data.getShoupanjia()
									: max60DayPrice;
						}
						if (i > 32 && i <= 62) {
							max60DayPrice = (data.getShoupanjia() > max60DayPrice) ? data.getShoupanjia()
									: max60DayPrice;
						}
					}
					if (start2BuyPrice > 0) {
						max5DayIncreaseRate = (max5DayPrice - start2BuyPrice) / start2BuyPrice;
						max10DayIncreaseRate = (max10DayPrice - start2BuyPrice) / start2BuyPrice;
						max20DayIncreaseRate = (max20DayPrice - start2BuyPrice) / start2BuyPrice;
						max30DayIncreaseRate = (max30DayPrice - start2BuyPrice) / start2BuyPrice;
						max60DayIncreaseRate = (max60DayPrice - start2BuyPrice) / start2BuyPrice;
					}
					starDataStat.setMax5DayIncreaseRate(max5DayIncreaseRate);
					starDataStat.setMax10DayIncreaseRate(max10DayIncreaseRate);
					starDataStat.setMax20DayIncreaseRate(max20DayIncreaseRate);
					starDataStat.setMax30DayIncreaseRate(max30DayIncreaseRate);
					starDataStat.setMax60DayIncreaseRate(max60DayIncreaseRate);
					this.starDataStatService.save(starDataStat);
				}
			}
		}
	}

}/** @generate-service-source@ **/
