package com.faceye.component.stock.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.entity.QDailyData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.DailyDataRepository;
import com.faceye.component.stock.repository.mongo.StockRepository;
import com.faceye.component.stock.repository.mongo.customer.DailyDataCustomerRepository;
import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.util.StockFetcher;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.MultiQueueService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.service.job.thread.BaseThread;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.MathUtil;
 
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Service
public class DailyDataServiceImpl extends BaseMongoServiceImpl<DailyData, Long, DailyDataRepository> implements DailyDataService {

	@Autowired
	private StockRepository stockRepository = null;
	@Autowired
	@Qualifier("stockQueueServiceImpl")
	private MultiQueueService<Stock> stockQueueService = null;

	@Autowired
	private DailyDataCustomerRepository dailyDataCustomerRepository = null;

	// 均线周期
	private static Integer[] AVG_DAYS = new Integer[] { 5, 10, 20, 30, 60, 120, 250 };

	@Autowired
	public DailyDataServiceImpl(DailyDataRepository dao) {
		super(dao);
	}

	/**
	 * 初始化某一股票的每日数据(爬取)(全部数据)
	 */
	@Override
	public void initDailyData(String code) {
		// List<Map<String, String>> data = fetcher.getStockDailyData(code, "", "");
		Calendar calendar = Calendar.getInstance();
		Date now = new Date();
		int year = calendar.get(Calendar.YEAR);
		// int [] years=new int[]{year,year-1};
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		String jidu = "1";
		if (currentMonth >= 1 && currentMonth <= 3) {
			jidu = "1";
		}
		if (currentMonth >= 4 && currentMonth <= 6) {
			jidu = "2";
		}
		if (currentMonth >= 7 && currentMonth <= 9) {
			jidu = "3";
		}
		if (currentMonth >= 10 && currentMonth <= 12) {
			jidu = "4";
		}
		String jidus[] = new String[] { "1", "2", "3", "4" };
		logger.debug(">>FaceYe current jidu is:" + jidu + ",year is:" + year);
		if (Integer.parseInt(jidu) > 0) {
			for (int i = 1; i <= Integer.parseInt(jidu); i++) {
				this.fetchHistoryData(code, "" + year, "" + i);
			}
		}
		// 取过去10年数据
		for (int i = 1; i < 10; i++) {
			for (String jd : jidus) {
				this.fetchHistoryData(code, "" + (year - i), jd);
			}
		}

	}

	/**
	 * 抓取历史数据
	 * 
	 * @param year
	 * @param jidu
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月9日 上午11:42:59
	 */
	private void fetchHistoryData(String code, String year, String jidu) {
		Stock stock = this.stockRepository.getStockByCode(code);
		if (stock != null) {
			StockFetcher fetcher = new StockFetcher();
			List<Map<String, String>> data = fetcher.getStockDataList(code, "" + year, jidu);
			if (CollectionUtils.isNotEmpty(data)) {
				List<DailyData> willSavedDailyData = new ArrayList<DailyData>(0);
				for (Map<String, String> map : data) {
					try {
						// 日期
						String date = map.get("date");
						// 开盘价
						String open = map.get("open");
						// 最高价
						String high = map.get("high");
						// 最低价
						String low = map.get("low");
						// 收盘价
						String close = map.get("close");
						// 成交股票数（股)
						String volume = map.get("volume");
						// 成交金额(元)
						String money = map.get("money");
						Date dDate = DateUtil.getDateFromString(date + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
						// 只存储最近30天的数据
						// if (now.getTime() - dDate.getTime() > 31 * 24 * 60 * 60 * 1000L) {
						// continue;
						// }
						boolean isDailyDataExist = this.isDailyDataExist(code, date);
						date += " 15:00:00";
						if (!isDailyDataExist) {
							DailyData dailyData = new DailyData();
							// dailyData.setStockCode(stock.getCode());
							dailyData.setStockId(stock.getId());
							// dailyData.setStockName(stock.getName());
							dailyData.setChengjiaogupiaoshu(Double.parseDouble(volume));
							dailyData.setChengjiaojine(Double.parseDouble(money));
							dailyData.setDangqianjiage(Double.parseDouble(close));
							dailyData.setDate(DateUtil.getDateFromString(date));
							dailyData.setJintianzuidijia(Double.parseDouble(low));
							dailyData.setJintianzuigaojia(Double.parseDouble(high));
							dailyData.setKaipanjia(Double.parseDouble(open));
							dailyData.setShoupanjia(Double.parseDouble(close));
							this.save(dailyData);
							// willSavedDailyData.add(dailyData);
						}
					} catch (Exception e) {
						logger.error(">>FaceYe throws Exception when init daily data," + e);
					}
				}
				// this.save(willSavedDailyData);
			}
		}
	}

	@Override
	public void initDailyData() {
		List<Stock> stocks = this.stockRepository.findAll();
		if (CollectionUtils.isNotEmpty(stocks)) {
			for (Stock stock : stocks) {
				Map params = new HashMap();
				params.put("EQ|stockId", stock.getId());
				long count = this.dailyDataCustomerRepository.getCount(params);
				if (count < 30) {
					this.initDailyData(stock.getCode());
				}
			}
		}
	}

	/**
	 * 初始化均线数据
	 * 
	 * @todo
	 * @param code
	 * @author:@haipenge haipenge@gmail.com 2015年2月19日
	 */

	class ComputeThread extends BaseThread {

		public ComputeThread() {

		}

		@Override
		public void doBusiness() {
			try {
				int rand = MathUtil.getRandInt(1, 5);
				Thread.sleep(rand * 100L);
			} catch (InterruptedException e) {
				logger.error(">>FaceYe throws Exception: --->" + e.toString());
			}
			while (!stockQueueService.isEmpty()) {
				Stock stock = stockQueueService.get();
				int size = stockQueueService.getSize();
				logger.debug(">>FaceYe --> stock total size is:" + size);
				initDailyDataAvg(stock);
			}
		}

	}

	@Override
	public void computeDailyDataLines() {
		List<Stock> stocks = this.stockRepository.findAll();
		if (CollectionUtils.isNotEmpty(stocks)) {
			// this.stockQueueService.addAll(stocks);
			for (int i = 0; i < stocks.size(); i++) {
				Stock stock = stocks.get(i);
				logger.debug(">>FaceYe --> stock index is:" + stock.getName() + "(" + stock.getCode() + "),index is:" + i + ",total size is:" + stocks.size());
				this.computeDailyDataLines(stock);
			}
		}
	}

	/**
	 * 初始化一只股票的均线
	 */
	public void computeDailyDataLines(Stock stock) {
		// 初始化均线
		this.initDailyDataAvg(stock);
		// 初始化EMA12/26
		this.initEMA(stock);

	}

	/**
	 * 初始化一只股票的均线
	 * 
	 * @todo
	 * @param stock
	 * @author:@haipenge haipenge@gmail.com 2015年2月24日
	 */
	private void initDailyDataAvg(Stock stock) {
		// Stock stock = stockRepository.getStockByCode(code);
		QDailyData qDailyData = QDailyData.dailyData;
		Predicate predicate = null;
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qDailyData.stockId.eq(stock.getId()));
		OrderSpecifier orderSpecifier = new OrderSpecifier(Order.DESC, qDailyData.date);
		predicate = builder.getValue();
		List<DailyData> willSaveDailyDatas = new ArrayList<DailyData>();
		List<DailyData> dailyDatas = (List) dao.findAll(predicate, orderSpecifier);
		if (CollectionUtils.isNotEmpty(dailyDatas)) {
			int index = 0;
			for (DailyData dailyData : dailyDatas) {
				Integer[] days = new Integer[] { 5, 10, 20, 30, 60, 120, 250 };
				if (index % 50 == 0) {
					logger.debug(">>FaceYe --> current compute daily index is,stock is:" + stock.getName() + "(" + stock.getCode() + "),daily data size:" + dailyDatas.size()
							+ ",index :" + index);
				}
				boolean isNeed2Compute = false;
				if (dailyData.getAvg10() == null || dailyData.getAvg120() == null || dailyData.getAvg20() == null || dailyData.getAvg250() == null || dailyData.getAvg30() == null
						|| dailyData.getAvg5() == null || dailyData.getAvg60() == null) {
					isNeed2Compute = true;
				}
				if (isNeed2Compute) {
					for (Integer day : days) {
						Integer toIndex = index + day;
						if (toIndex <= dailyDatas.size()) {
							List<DailyData> sub = dailyDatas.subList(index, toIndex);
							// logger.debug(">>FaceYe --> Sub size is:"+sub.size()+",day is :"+day);
							if (sub.size() == day.intValue()) {
								Double computeResult = 0D;
								Double total = 0D;
								for (DailyData d : sub) {
									total += d.getShoupanjia();
								}
								computeResult = total / day;
								if (day.intValue() == 5) {
									dailyData.setAvg5(computeResult);
								} else if (day.intValue() == 10) {
									dailyData.setAvg10(computeResult);
								} else if (day.intValue() == 20) {
									dailyData.setAvg20(computeResult);
								} else if (day.intValue() == 30) {
									dailyData.setAvg30(computeResult);
								} else if (day.intValue() == 60) {
									dailyData.setAvg60(computeResult);
								} else if (day.intValue() == 120) {
									dailyData.setAvg120(computeResult);
								} else if (day.intValue() == 250) {
									dailyData.setAvg250(computeResult);
								} else {
									logger.debug(">>FaceYe --> days参数错误，当前Days:" + days);
								}
								// dao.save(dailyData);
								willSaveDailyDatas.add(dailyData);
							} else {
								logger.debug(">>FaceYe --> sub list length not eq day," + sub.size() + "!=" + day);
							}
						}
					}
				}
				index++;
			}
		}
		if (CollectionUtils.isNotEmpty(willSaveDailyDatas)) {
			save(willSaveDailyDatas);
		}
	}

	/**
	 * 计算每天的EMA,(EMA12,EMA26) EMAtoday=α * ( Pricetoday - EMAyesterday ) + EMAyesterday;
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月12日 下午2:18:36
	 */
	private void initEMA(Stock stock) {
		// 平滑系数
		Double e12 = 2D / 13D;
		Double e26 = 2D / 27D;
		// 1.EMA1=第一天的价格（当前计算采用）
		// 2.EMA1=开始4-5天价格的平均数
		Double ema1 = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", stock.getId());
		searchParams.put("SORT|date", "asc");
		List<DailyData> dailyDatas = this.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(dailyDatas)) {
			for (int i = 0; i < dailyDatas.size(); i++) {
				DailyData dailyData = dailyDatas.get(i);
				if (i == 0) {
					ema1 = dailyData.getShoupanjia();
					dailyData.setEma12(ema1);
					dailyData.setEma26(ema1);
					dailyData.setDif(0D);
					dailyData.setDea(0D);
					dailyData.setMacd(0D);
					this.save(dailyData);
				} else {
					Double ema12Yesterday = dailyDatas.get(i - 1).getEma12();
					Double ema26Yesterday = dailyDatas.get(i - 1).getEma26();
					Double ema12Today = e12 * (dailyData.getShoupanjia() - ema12Yesterday) + ema12Yesterday;
					Double ema26Today = e26 * (dailyData.getShoupanjia() - ema26Yesterday) + ema26Yesterday;
					// 差离值 DIF = EMA（12） - EMA（26）
					Double dif = ema12Today - ema26Today;
					dailyData.setEma12(ema12Today);
					dailyData.setEma26(ema26Today);
					dailyData.setDif(dif);
					// DEA = （前一日DEA X 8/10 + 今日DIF X 2/10）
					Double deaYesterDay = dailyDatas.get(i - 1).getDea();
					Double dea = 8 * deaYesterDay / 10 + 2 * dif / 10;
					dailyData.setDea(dea);
					dailyData.setMacd((dif - dea) * 2);
					this.save(dailyData);
				}
			}
		}
	}

	/**
	 * 当天的交易数据是否存在
	 * 
	 * @todo
	 * @param date
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年2月23日
	 */
	private boolean isDailyDataExist(String code, String date) {
		boolean res = false;
		Stock stock = this.stockRepository.getStockByCode(code);
		if (stock != null) {
			String startDate = date + " 00:00:01";
			String endDate = date + " 23:59:59";
			QDailyData qDailyData = QDailyData.dailyData;
			BooleanBuilder builder = new BooleanBuilder();
			builder.and(qDailyData.stockId.eq(stock.getId()));
			builder.and(qDailyData.date.before(DateUtil.getDateFromString(endDate)));
			builder.and(qDailyData.date.after(DateUtil.getDateFromString(startDate)));
			List<DailyData> dailyDatas = (List) this.dao.findAll(builder.getValue());
			res = CollectionUtils.isNotEmpty(dailyDatas);
		}
		return res;
	}

	/**
	 * 爬取每日数据
	 * 
	 * @todo
	 * @author:@haipenge haipenge@gmail.com 2015年2月23日
	 */
	public void crawlDailyData() {
		// 清理历史数据
		// this.dailyDataCustomerRepository.clearHistoryDailyData();
		List<Stock> stocks = (List) this.stockRepository.findAll();
		if (CollectionUtils.isNotEmpty(stocks)) {
			logger.debug(">>FaceYe crawl stock size is :"+stocks.size());
			for (Stock stock : stocks) {
				try {
					this.crawlDailyData(stock);
					Thread.sleep(2000L);
				} catch (Exception e) {
					logger.debug(">>FaceYe throws Exception wen crawl stock daily data.exception is:" + e.toString() + ",stock code is:" + stock.getCode());
				}
			}
		}else{
			logger.debug(">>FaceYe --> stock is empty ,can not be crawl.");
		}
	}

	/**
	 * 爬取一天的 日数据
	 * 
	 * @todo
	 * @param stock
	 * @author:@haipenge haipenge@gmail.com 2015年2月24日
	 */
	public void crawlDailyData(Stock stock) {
		String queryCode = stock.getMarket() + stock.getCode();
		StockFetcher fetcher = new StockFetcher();
		Map data = fetcher.getLiveDataFromSina(queryCode);
		if (MapUtils.isNotEmpty(data)) {
			// 处理停牌的股票 开盘价，收盘价为0.00
			String open = MapUtils.getString(data, "open");
			String close = MapUtils.getString(data, "close");
			String low = MapUtils.getString(data, "low");
			String high = MapUtils.getString(data, "high");
			String volume = MapUtils.getString(data, "volume");
			String money = MapUtils.getString(data, "money");
			String yesterdayPrice = MapUtils.getString(data, "yesterdayPrice");
			if (StringUtils.isNotEmpty(open) && Double.parseDouble(open) > 0 && !StringUtils.equals(open, "0.00") && !StringUtils.equals(close, "0.00")) {
				String date = MapUtils.getString(data, "date");
				String time = MapUtils.getString(data, "time");
				boolean isDailyDataExist = this.isDailyDataExist(stock.getCode(), date);
				if (!isDailyDataExist) {
					DailyData dailyData = new DailyData();
					dailyData.setChengjiaogupiaoshu(Double.parseDouble(volume));
					dailyData.setChengjiaojine(Double.parseDouble(money));
					dailyData.setDangqianjiage(Double.parseDouble(close));
					dailyData.setShoupanjia(Double.parseDouble(close));
					dailyData.setJintianzuidijia(Double.parseDouble(low));
					dailyData.setJintianzuigaojia(Double.parseDouble(high));
					dailyData.setKaipanjia(Double.parseDouble(open));
					dailyData.setDate(DateUtil.getDateFromString(date + " " + time));
					if (StringUtils.isNotEmpty(yesterdayPrice)) {
						dailyData.setYesterdayPrice(Double.parseDouble(yesterdayPrice));
					}
					// dailyData.setStockCode(stock.getCode());
					dailyData.setStockId(stock.getId());
					// dailyData.setStockName(stock.getName());
					this.save(dailyData);
				}
			}

		}
	}

	/**
	 * 初始化股票的日线数据
	 */
	@Override
	public void initDailyDataAvg(String code) {
		Stock stock = this.stockRepository.getStockByCode(code);
		if (null != stock) {
			this.initDailyDataAvg(stock);
		}
	}

	/**
	 * 根据原始数据，计算某一天的均线体系
	 */
	// @Override
	// public void computeDailyDataAvg(DailyData dailyData) {
	// QDailyData qDailyData = QDailyData.dailyData;
	// Long stockId = dailyData.getStockId();
	// Stock stock = this.stockRepository.findById(stockId);
	// Date date = dailyData.getDate();
	// Predicate predicate = null;
	// BooleanBuilder builder = new BooleanBuilder();
	// builder.and(qDailyData.stockId.eq(stock.getId()));
	// builder.and(qDailyData.date.before(date));
	// OrderSpecifier orderSpecifier = new OrderSpecifier(Order.DESC, qDailyData.date);
	// predicate = builder.getValue();
	// List<DailyData> dailyDatas = (List) dao.findAll(predicate, orderSpecifier);
	// logger.debug(">>FaceYe --> compute daily data avg,query prediate is :" + predicate.toString() + ",query result size is+"
	// + (dailyDatas == null ? "0" : dailyDatas.size()));
	// if (CollectionUtils.isNotEmpty(dailyDatas)) {
	// int index = 0;
	// for (int i = 0; i < AVG_DAYS.length; i++) {
	// Integer day = AVG_DAYS[i];
	// int toIndex = index + AVG_DAYS[i];
	// if (dailyDatas.size() > toIndex) {
	// List<DailyData> sub = dailyDatas.subList(index, toIndex);
	// Double computeResult = 0D;
	// Double total = 0D;
	// for (DailyData d : sub) {
	// total += d.getShoupanjia();
	// }
	// computeResult = total / day;
	// if (day.intValue() == 5) {
	// dailyData.setAvg5(computeResult);
	// } else if (day.intValue() == 10) {
	// dailyData.setAvg10(computeResult);
	// } else if (day.intValue() == 20) {
	// dailyData.setAvg20(computeResult);
	// } else if (day.intValue() == 30) {
	// dailyData.setAvg30(computeResult);
	// } else if (day.intValue() == 60) {
	// dailyData.setAvg60(computeResult);
	// } else if (day.intValue() == 120) {
	// dailyData.setAvg120(computeResult);
	// } else if (day.intValue() == 250) {
	// dailyData.setAvg250(computeResult);
	// } else {
	// logger.debug(">>FaceYe --> days参数错误，当前Days:" + day);
	// }
	// }
	// }
	// this.save(dailyData);
	// }
	// }
	@Override
	public Page<DailyData> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		// Sort sort = new Sort(Direction.DESC, "date");
		// sort.and(new Sort(Direction.DESC, "id"));
		Sort sort = this.buildSort(searchParams);
		Page<DailyData> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<T>("id") {
			// })
			List<DailyData> items = (List) this.dao.findAll(predicate, sort);
			res = new PageImpl<DailyData>(items);
		}
		return res;
	}
	@Override
	public void remove(Long stockId){
		this.dailyDataCustomerRepository.removeStockHistoryDailyData(stockId);
	}

}
/** @generate-service-source@ **/
