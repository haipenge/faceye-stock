package com.faceye.component.stock.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.AccountingSubject;
import com.faceye.component.stock.entity.BonusRecord;
import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.entity.Forecast;
import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.entity.Mechanism;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.entity.TotalStock;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.component.stock.service.BonusRecordService;
import com.faceye.component.stock.service.CategoryService;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.component.stock.service.ForecastIndexService;
import com.faceye.component.stock.service.ForecastService;
import com.faceye.component.stock.service.MechanismService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.TotalStockService;
import com.faceye.component.stock.service.job.CrawlFinancialDataThread;
import com.faceye.feature.service.QueueService;
import com.faceye.feature.service.job.thread.ThreadPoolController;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.html.HtmlUtil;
import com.faceye.feature.util.http.Http;
import com.faceye.feature.util.regexp.RegexpUtil;

@Service
public class CrawlFinancialDataServiceImpl implements CrawlFinancialDataService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private StockService stockService = null;
	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private AccountingSubjectService accountingSubjectService = null;
	@Autowired
	private FinancialDataService financialDataService = null;
	@Autowired
	@Qualifier("financialDataQueueService")
	private QueueService financialDataQueueService = null;
	@Autowired
	private ReportDataService reportDataService = null;
	@Autowired
	private TotalStockService totalStockService = null;
	@Autowired
	private BonusRecordService bonusRecordService = null;
	@Autowired
	private ForecastService forecaseService = null;
	@Autowired
	private MechanismService mechanismService = null;
	@Autowired
	private ForecastIndexService forecastIndexService = null;

	@Override
	public void crawl() {
		if (financialDataQueueService.isEmpty()) {
			List<Stock> stocks = this.stockService.getAll();
			// Collections.shuffle(stocks);
			financialDataQueueService.addAll(stocks);
			List<Runnable> runnables = new ArrayList<Runnable>();
			for (int i = 0; i < 1; i++) {
				Runnable runnabe = new CrawlFinancialDataThread();
				runnables.add(runnabe);
			}
			ThreadPoolController.getINSTANCE().execute("Crawl-Finanacial-data-Pool", runnables, 1);

		}
		// if (CollectionUtils.isNotEmpty(stocks)) {
		// for (Stock stock : stocks) {
		// try {
		// this.crawlStock(stock);
		// } catch (Exception e) {
		// logger.error(">>FaceYe throws Exception :" + e);
		// }
		// }
		// }
	}

	/**
	 * 爬取一只股票的数据
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月21日 下午3:17:28
	 */
	public void crawlStock(Stock stock) {
		boolean isStockCrawled = this.isStockFinancialDataCrawled(stock);
		// boolean isStockCrawled=false;
		this.crawlStock(stock, isStockCrawled);

	}

	private boolean isStockFinancialDataCrawled(Stock stock) {
		boolean isCrawled = false;
		Map params = new HashMap();
		params.put("EQ|stockId", stock.getId());
		params.put("SORT|date", "DESC");
		Page<ReportData> reportDatas = this.reportDataService.getPage(params, 1, 1);
		boolean isEmpty = CollectionUtils.isEmpty(reportDatas.getContent());
		if (!isEmpty) {
			ReportData lastReportData = reportDatas.getContent().get(0);
			Date now = new Date();
			if (lastReportData.getDate() != null
					&& now.getTime() - lastReportData.getDate().getTime() < 3 * 30 * 24 * 60 * 60 * 1000L) {
				isCrawled = true;
			}
		}
		return isCrawled;
	}

	List<AccountingSubject> accountingSubjects = null;

	private List<AccountingSubject> getAccountingSubjects() {
		accountingSubjects = this.accountingSubjectService.getAll();
		return accountingSubjects;
	}

	/**
	 * 解析爬取数据
	 * 
	 * @param stock
	 * @param accountSubject
	 * @param content
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月21日 下午3:30:34
	 */
	public void parse(Stock stock, AccountingSubject accountingSubject, String content) {
		String regexp = "<table width=\"775px\" id=\"Table1\">([\\s\\S]*?)<\\/table>";
		List<Map<String, String>> records = new ArrayList<Map<String, String>>(0);
		int x = 0;
		try {
			List<Map<String, String>> matchers = RegexpUtil.match(content, regexp);
			if (CollectionUtils.isNotEmpty(matchers)) {
				String tableContent = matchers.get(0).get("1");
				if (StringUtils.isNotEmpty(tableContent)) {
					regexp = "<tr>([\\s\\S]*?)<\\/tr>";
					Pattern pateern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
					Matcher matcher = pateern.matcher(tableContent);
					while (matcher.find()) {
						if (x < 1) {
							x++;
							continue;
						}
						String trContent = matcher.group(1);
						regexp = "<td style=\"text-align:center\">([\\s\\S]*?)<\\/td>";
						Pattern tdPattern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
						Matcher tdMatcher = tdPattern.matcher(trContent);
						int y = 0;
						Map<String, String> record = new HashMap<String, String>();
						while (tdMatcher.find()) {
							if (y == 0) {
								// 获取时间数据
								String dateStr = tdMatcher.group(1);
								record.put("date", StringUtils.trim(dateStr));
							}
							if (y == 1) {
								String dataStr = tdMatcher.group(1);
								if (StringUtils.equals(dataStr, "&nbsp;")) {
									record.put("data", "");
								} else {
									dataStr = StringUtils.trim(dataStr);
									dataStr = StringUtils.replace(dataStr, ",", "");
									record.put("data", dataStr);
								}
							}
							if (y >= 1) {
								break;
							}
							y++;
						}
						records.add(record);
					}
				}
			}
		} catch (Exception e) {
			logger.error(">>FaceYe Throws Exception when parse stock financial data html:", e);
		}
		if (CollectionUtils.isNotEmpty(records)) {
			this.saveParseData(stock, records, accountingSubject);
			// List<FinancialData> datas = new ArrayList<FinancialData>();
			// Long accountingElementId =
			// accountingSubject.getAccountingElement().getId();
			// for (Map<String, String> record : records) {
			// String date = MapUtils.getString(record, "date");
			// String data = MapUtils.getString(record, "data");
			//
			// // logger.debug(">>FaceYe --> parsed financial data is:" + date +
			// ":" + data);
			// boolean isExist = false;
			//
			// if (!isExist && StringUtils.isNotEmpty(date)) {
			// try {
			// FinancialData financialData = new FinancialData();
			// financialData.setAccountingSubjectId(accountingSubject.getId());
			// financialData.setAccountingElementId(accountingElementId);
			// financialData.setCreateDate(new Date());
			// if (StringUtils.isEmpty(data)) {
			// financialData.setData(null);
			// } else {
			// financialData.setData(NumberUtils.createDouble(data));
			// }
			// financialData.setDate(DateUtil.getDateFromString(date,
			// "yyyy-MM-dd"));
			// financialData.setStockId(stock.getId());
			// datas.add(financialData);
			// } catch (Exception e) {
			// logger.error(">>FaceYe throws Exception when save data,data :date
			// is:" + data + ":" + date, e);
			// }
			// } else {
			// logger.debug(">>FaceYe --> financial data exist.");
			// }
			// }
			// if (CollectionUtils.isNotEmpty(datas)) {
			// this.financialDataService.save(datas);
			// }
		} else {
			logger.error(">>FaceYe -> have got empty record of stock :" + stock.getName() + "(" + stock.getCode()
					+ "),[" + stock.getId() + "]");
		}
	}

	/**
	 * 保存解析数据
	 * 
	 * @param records
	 * @param accountingSubject
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月12日 下午9:49:46
	 */
	private void saveParseData(Stock stock, List<Map<String, String>> records, AccountingSubject accountingSubject) {
		try {
			Map params = new HashMap();
			params.put("EQ|stockId", stock.getId());
			List<ReportData> reportDatas = this.reportDataService.getPage(params, 0, 0).getContent();
			Map<String, ReportData> structs = this.buildReportDataStruct(reportDatas);
			for (Map record : records) {
				String date = MapUtils.getString(record, "date");
				String data = MapUtils.getString(record, "data");
				Date dDate = DateUtil.getDateFromString(date + " 12:00:00", "yyyy-MM-dd HH:mm:ss");
				ReportData reportData = null;
				if (structs.containsKey(date)) {
					reportData = structs.get(date);
				} else {
					reportData = new ReportData();
					reportData.setDate(dDate);
					reportData.setStockId(stock.getId());
					structs.put(date, reportData);
				}
				this.setReportData(reportData, accountingSubject, data);
			}
			this.reportDataService.save(structs.values());
		} catch (Exception e) {
			logger.error(">>FaceYe throws Exception :" + e);
		}
	}

	/**
	 * 设置对像属性值
	 * 
	 * @param reportData
	 * @param accountingSubject
	 * @param date
	 * @param data
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月14日 下午2:31:24
	 */
	private void setReportData(ReportData reportData, AccountingSubject accountingSubject, String data) {
		Long accountingElementId = accountingSubject.getAccountingElement().getId();
		Long accountingSubjectId = accountingSubject.getId();
		String eleClassName = "com.faceye.component.stock.entity.Ele" + accountingElementId;
		String propertyName = accountingSubject.getCode().toLowerCase() + "_" + accountingSubjectId;
		if (StringUtils.isNotEmpty(data)) {
			String reportCategoryClassName = "com.faceye.component.stock.entity."
					+ accountingSubject.getAccountingElement().getReportCategory().getCode();
			reportCategoryClassName = reportCategoryClassName.toLowerCase().replaceAll("_", "");
			Field[] categoryClazz = ReportData.class.getDeclaredFields();
			Object categoryObject = null;
			for (Field field : categoryClazz) {
				if (field.getType().getName().toLowerCase().equals(reportCategoryClassName)) {
					try {
						categoryObject = PropertyUtils.getNestedProperty(reportData, field.getName());
					} catch (IllegalAccessException e) {
						logger.error(">>FaceYe Throws Exception:", e);
					} catch (InvocationTargetException e) {
						logger.error(">>FaceYe Throws Exception:", e);
					} catch (NoSuchMethodException e) {
						logger.error(">>FaceYe Throws Exception:", e);
					}
					//
					break;
				}
			}
			Object element = null;
			if (categoryObject != null) {
				Field elObjects[] = categoryObject.getClass().getDeclaredFields();
				for (Field el : elObjects) {
					if (el.getType().getName().equals(eleClassName)) {
						try {
							element = PropertyUtils.getProperty(categoryObject, el.getName());
						} catch (IllegalAccessException e) {
							logger.error(">>FaceYe Throws Exception:", e);
						} catch (InvocationTargetException e) {
							logger.error(">>FaceYe Throws Exception:", e);
						} catch (NoSuchMethodException e) {
							logger.error(">>FaceYe Throws Exception:", e);
						}
						// element = ReflectionUtils.getField(el,
						// categoryObject);
						break;
					}
				}
			}

			if (element != null) {
				try {
					PropertyUtils.setProperty(element, propertyName, Double.parseDouble(data));
				} catch (NumberFormatException e) {
					logger.error(">>FaceYe Throws Exception:", e);
				} catch (IllegalAccessException e) {
					logger.error(">>FaceYe Throws Exception:", e);
				} catch (InvocationTargetException e) {
					logger.error(">>FaceYe Throws Exception:", e);
				} catch (NoSuchMethodException e) {
					logger.error(">>FaceYe Throws Exception:", e);
				}
			}
		}

	}

	private Map<String, ReportData> buildReportDataStruct(List<ReportData> reportDatas) {
		Map<String, ReportData> struts = new HashMap<String, ReportData>();
		if (CollectionUtils.isNotEmpty(reportDatas)) {
			for (ReportData reportData : reportDatas) {
				Date date = reportData.getDate();
				String sDate = DateUtil.formatDate(date, "yyyy-MM-dd");
				if (!struts.containsKey(sDate)) {
					struts.put(sDate, reportData);
				}
			}
		}
		return struts;
	}

	@Override
	public void crawlStock(Stock stock, boolean isCrawled) {
		if (!isCrawled) {
			String code = stock.getCode();
			String url = "";
			if (accountingSubjects == null) {
				accountingSubjects = this.getAccountingSubjects();
			}
			logger.debug(">>FaceYe start to crawl stock:" + stock.getName() + " report data now.");
			/**
			 * 爬取财报数据
			 */
			int urlCounts = accountingSubjects.size();
			if (CollectionUtils.isNotEmpty(accountingSubjects)) {
				for (AccountingSubject accountingSubject : accountingSubjects) {
					url = accountingSubject.getSinaUrl();
					url = StringUtils.replace(url, "000998", code);
					logger.debug(">>FaceYe --> " + urlCounts-- + "Crawl Financial Data Url is:" + url);
					String content = Http.getInstance().get(url, "gb2312");
					if (StringUtils.isNotEmpty(content)) {
						this.parse(stock, accountingSubject, content);
					} else {
						logger.error(">>FaceYe have not got content of url: " + url);
					}
					url = "";
					// try {
					// Thread.sleep(1000L);
					// } catch (InterruptedException e) {
					// logger.error(">>FaceYe Throws Exception:", e);
					// }
				}
			}
			logger.debug(">>FaceYe finish crawl stock " + stock.getName() + " report data.");
			// 爬取股本变化记录
			this.crawlTotalStocksNum(stock);
			// 爬取分红记录
			this.crawlBonusRecord(stock);
			// 爬取分析师评估数据
			this.crawlForecast(stock);
		} else {
			logger.debug(">>FaceYe --> stock:" + stock.getName() + "(" + stock.getCode() + ") 已爬取");
		}
	}

	/**
	 * 爬取总股本
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月9日 下午9:05:57
	 *                 http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockStructureHistory/stockid/000998/stocktype/TotalStock.phtml
	 */
	private void crawlTotalStocksNum(Stock stock) {
		String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockStructureHistory/stockid/000998/stocktype/TotalStock.phtml";
		url = StringUtils.replace(url, "000998", stock.getCode());
		String content = Http.getInstance().get(url, "gb2312");
		String regexp = "<td><div align=\"center\">([\\S\\s\\.].+?)<\\/div><\\/td>";
		if (StringUtils.isNotEmpty(content)) {
			try {
				List<Map<String, String>> results = RegexpUtil.match(content, regexp);
				
				if (CollectionUtils.isNotEmpty(results)) {
					TotalStock totalStock = null;
					for (int i = 0; i < results.size(); i++) {
						Map<String, String> map = results.get(i);
						String value = StringUtils.trim(map.get("1"));
						if (i % 2 == 0) {
							// boolean isExist =
							// this.totalStockService.isTotalStockExist(stock.getId(),
							// value);
							totalStock = this.totalStockService.getTotalStock(stock.getId(), value);
						}
						if (i % 2 != 0) {
							value = StringUtils.replace(value, "万股", "");
							if (NumberUtils.isNumber(value)) {
								Double stockNum = NumberUtils.toDouble(value) * 10000;
								totalStock.setStockNum(stockNum);
							}
							if (totalStock != null) {
								this.totalStockService.save(totalStock);
								totalStock=null;
							}
						} else {
							if (totalStock == null) {
								totalStock = new TotalStock();
							}
							totalStock.setStockId(stock.getId());
							String format = "yyyy-MM-dd";
							Date changeDate = DateUtil.getDateFromString(value, format);
							totalStock.setChangeDate(changeDate);
						}
						if (i == results.size() - 1) {
							if (totalStock != null) {
								this.totalStockService.save(totalStock);
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
	}

	/**
	 * 爬取分红记录
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月13日 下午9:44:22
	 */
	private void crawlBonusRecord(Stock stock) {
		String url = "http://money.finance.sina.com.cn/corp/go.php/vISSUE_ShareBonus/stockid/000998.phtml";
		url = StringUtils.replace(url, "000998", stock.getCode());
		String regexp = "<table id=\"sharebonus_1\">([\\S\\s]+?)<\\/table>";
		String content = Http.getInstance().get(url, "gb2312");
		try {
			List<Map<String, String>> matcherResult = RegexpUtil.match(content, regexp);
			if (CollectionUtils.isNotEmpty(matcherResult)) {
				String table = matcherResult.get(0).get("1");
				if (StringUtils.isNotEmpty(table)) {
					regexp = "<tbody>([\\s\\S]*?)<\\/tbody>";
					List<Map<String, String>> tbodyMatcher = RegexpUtil.match(table, regexp);
					String tbody = tbodyMatcher.get(0).get("1");
					if (StringUtils.isNotEmpty(tbody)) {
						regexp = "<tr>([\\s\\S]*?)<\\/tr>";
						Pattern pateern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
						Matcher matcher = pateern.matcher(tbody);
						while (matcher.find()) {

							String tr = matcher.group(1);
							if (StringUtils.isNotEmpty(tr)) {
								String reg = "<td>([\\s\\S]*?)<\\/td>";
								Pattern p = Pattern.compile(reg, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
								Matcher m = p.matcher(tr);
								int i = 0;
								// 公告日期
								String publishDateStr = "";
								// 送股
								String giveStockCountStr = "";
								// 转增
								String increaseStockCountStr = "";
								// 派息
								String dividendStr = "";
								// 进度
								String status = "";
								// 除权除息日
								String exDividendDateStr = "";
								// 股权登记日
								String equityRegistrationDateStr = "";
								//  红股上市日
								String bonusShareTradingDateStr = "";
								while (m.find()) {
									if (i == 0) {
										publishDateStr = m.group(1);
										publishDateStr = StringUtils.replace(publishDateStr, "--", "");
									}
									if (i == 1) {
										giveStockCountStr = m.group(1);
									}
									if (i == 2) {
										increaseStockCountStr = m.group(1);
									}
									if (i == 3) {
										dividendStr = m.group(1);
									}
									if (i == 4) {
										status = m.group(1);
									}
									if (i == 5) {
										exDividendDateStr = m.group(1);
										exDividendDateStr = StringUtils.replace(exDividendDateStr, "--", "");
									}
									if (i == 6) {
										equityRegistrationDateStr = m.group(1);
										equityRegistrationDateStr = StringUtils.replace(equityRegistrationDateStr, "--",
												"");
									}
									if (i == 7) {
										bonusShareTradingDateStr = m.group(1);
										bonusShareTradingDateStr = StringUtils.replace(bonusShareTradingDateStr, "--",
												"");
									}
									i++;
								}
								if (StringUtils.isNotEmpty(publishDateStr)) {
									// boolean isExist =
									// this.bonusRecordService.isExistBonusRecord(stock.getId(),
									// publishDateStr);
									BonusRecord bonusRecord = this.bonusRecordService.getBonusRecord(stock.getId(),
											publishDateStr);
									if (bonusRecord == null) {
										bonusRecord = new BonusRecord();
									}
									bonusRecord.setStockId(stock.getId());
									bonusRecord
											.setPublishDate(DateUtil.getDateFromString(publishDateStr, "yyyy-MM-dd"));
									if (StringUtils.isNotEmpty(bonusShareTradingDateStr)) {
										bonusRecord.setBonusShareTradingDate(
												DateUtil.getDateFromString(bonusShareTradingDateStr, "yyyy-MM-dd"));
									}
									bonusRecord.setDividend(NumberUtils.toDouble(dividendStr) / 10);
									bonusRecord.setEquityRegistrationDate(
											DateUtil.getDateFromString(equityRegistrationDateStr, "yyyy-MM-dd"));
									bonusRecord.setExDividendDate(
											DateUtil.getDateFromString(exDividendDateStr, "yyyy-MM-dd"));
									bonusRecord.setGiveStockCount(NumberUtils.toDouble(giveStockCountStr) / 10);
									bonusRecord.setIncreaseStockCount(Double.valueOf(increaseStockCountStr) / 10);
									bonusRecord
											.setPublishDate(DateUtil.getDateFromString(publishDateStr, "yyyy-MM-dd"));
									bonusRecord.setStatus(status);
									this.bonusRecordService.save(bonusRecord);
								}

							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
	}

	/**
	 * 爬取机构估值
	 */
	public void crawlForecast(Stock stock) {
		Map<String, String> urls = new HashMap<String, String>();
		urls.put("eps",
				"http://vip.stock.finance.sina.com.cn/q/go.php/vPerformancePrediction/kind/eps/index.phtml?symbol=sz000998");
		// 营收
		urls.put("sales",
				"http://vip.stock.finance.sina.com.cn/q/go.php/vPerformancePrediction/kind/sales/index.phtml?symbol=sz000998");
		// 净利润
		urls.put("np",
				"http://vip.stock.finance.sina.com.cn/q/go.php/vPerformancePrediction/kind/np/index.phtml?symbol=sz000998");
		// 净资产收益率(ROE)
		urls.put("roe",
				"http://vip.stock.finance.sina.com.cn/q/go.php/vPerformancePrediction/kind/roe/index.phtml?symbol=sz000998");
		Iterator<String> keyIterator = urls.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			String url = urls.get(key);
			this.crawlForecast(stock, key, url);
		}
	}

	/**
	 * 爬取机构对股票的预测
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月15日 下午9:20:48
	 */
	private void crawlForecast(Stock stock, String kind, String url) {

		// String url =
		// "http://vip.stock.finance.sina.com.cn/q/go.php/vPerformancePrediction/kind/eps/index.phtml?symbol=sz000998";
		url = StringUtils.replace(url, "sz000998", stock.getMarket().toLowerCase() + stock.getCode());
		// http://vip.stock.finance.sina.com.cn/q/go.php/vPerformancePrediction/kind/eps/index.phtml?symbol=sz000998&p=1
		// http://vip.stock.finance.sina.com.cn/q/go.php/vPerformancePrediction/kind/eps/index.phtml?symbol=sz000998&p=2
		String content = Http.getInstance().get(url, "gb2312");
		String regexp = "<table class=\"list_table\" id=\"dataTable\">([\\s\\S]*?)<\\/table>";
		List<Map<String, String>> table;

		try {
			table = RegexpUtil.match(content, regexp);
			if (CollectionUtils.isNotEmpty(table)) {
				List<String> years = new ArrayList<String>(0);
				String thRegexp = "<tr class=\"head\">([\\s\\S]*?)<\\/tr>";
				List<Map<String, String>> thContent = RegexpUtil.match(table.get(0).get("1"), thRegexp);
				String tdRegexp = "<td>([\\s\\S]*?)<\\/td>";
				List<Map<String, String>> tdContents = RegexpUtil.match(thContent.get(0).get("1"), tdRegexp);
				if (CollectionUtils.isNotEmpty(tdContents)) {
					for (int i = 2; i < tdContents.size(); i++) {
						if (i <= 5) {
							Map<String, String> map = tdContents.get(i);
							String text = map.get("1");
							text = HtmlUtil.getInstance().replaceAll(text);
							text = StringUtils.replace(text, "年EPS", "");
							text = StringUtils.replace(text, "年营收", "");
							text = StringUtils.replace(text, "年净利", "");
							text = StringUtils.replace(text, "年ROE(%)", "");
							years.add(text);
						}
					}
				}
				List<List> tdResults = new ArrayList<List>(0);
				String trRegexp = "<tr>([\\S\\s]*?)<\\/tr>";
				List<Map<String, String>> trMatchers = RegexpUtil.match(table.get(0).get("1"), trRegexp);
				if (CollectionUtils.isNotEmpty(trMatchers)) {
					for (int i = 0; i < trMatchers.size(); i++) {
						Map<String, String> map = trMatchers.get(i);
						String trText = map.get("1");
						String tdTextRegexp = "<td[\\S\\s]*?>([\\S\\s]*?)<\\/td>";
						List<Map<String, String>> tdMatchers = RegexpUtil.match(trText, tdTextRegexp);
						if (CollectionUtils.isNotEmpty(tdMatchers)) {
							List<String> subList = new ArrayList<String>(0);
							for (int j = 2; j < tdMatchers.size() - 1; j++) {
								Map<String, String> tdMap = tdMatchers.get(j);
								String tdText = tdMap.get("1");
								tdText = HtmlUtil.getInstance().replaceAll(tdText);
								tdText = StringUtils.replace(tdText, "--", "");
								subList.add(tdText);
							}
							tdResults.add(subList);
						}
					}
				}

				for (List<String> items : tdResults) {
					// List<Forecast> forecasts = new ArrayList<Forecast>(0);
					for (int i = 0; i < items.size(); i++) {
						String tdText = items.get(i);
						String reportDate = items.get(4);
						String mechanismName = items.get(5);
						String researcher = items.get(6);
						if (i <= 3) {
							String yearText = years.get(i);
							Forecast forecast = this.forecaseService.getForecast(stock.getId(),
									Integer.parseInt(yearText), reportDate, mechanismName);
							if (forecast == null) {
								forecast = new Forecast();
								forecast.setStockId(stock.getId());
								forecast.setReportDate(DateUtil.getDateFromString(reportDate, "yyyy-MM-dd"));
								forecast.setYear(NumberUtils.toInt(yearText));
								Mechanism mechanism = this.mechanismService.getMechanismByName(mechanismName);
								if (mechanism == null) {
									mechanism = new Mechanism();
									mechanism.setName(mechanismName);
									mechanism = this.mechanismService.save(mechanism);
								}
								ForecastIndex forecastIndex = this.forecastIndexService
										.getForecastIndexByMechanismAndReportDate(stock.getId(), mechanism,
												forecast.getReportDate());
								// f.setMechanismDef(mechanism);
								forecast.setForecastIndex(forecastIndex);
								forecast.setMechanism(mechanismName);
								forecast.setResearcher(researcher);
							}
							if (StringUtils.isNotEmpty(tdText)) {
								if (StringUtils.equals(kind, "eps")) {
									forecast.setEps(NumberUtils.toDouble(tdText));
								} else if (StringUtils.equals(kind, "sales")) {
									forecast.setIncome(NumberUtils.toDouble(tdText));
								} else if (StringUtils.equals(kind, "np")) {
									forecast.setProfit(NumberUtils.toDouble(tdText));
								} else if (StringUtils.equals(kind, "roe")) {
									forecast.setRoe(NumberUtils.toDouble(tdText) / 100);
								}
							}
							this.forecaseService.save(forecast);
							// forecast.setYear(NumberUtils.toInt(yearText));
							// forecasts.add(forecast);
						}
						// if (i == 4) {
						// for (Forecast f : forecasts) {
						// f.setReportDate(DateUtil.getDateFromString(tdText,
						// "yyyy-MM-dd"));
						// }
						// }
						// if (i == 5) {
						// for (Forecast f : forecasts) {
						// Mechanism mechanism =
						// this.mechanismService.getMechanismByName(tdText);
						// if (mechanism == null) {
						// mechanism = new Mechanism();
						// mechanism.setName(tdText);
						// mechanism=this.mechanismService.save(mechanism);
						// }
						// ForecastIndex
						// forecastIndex=this.forecastIndexService.getForecastIndexByMechanismAndReportDate(stock.getId(),
						// mechanism,f.getReportDate() );
						//// f.setMechanismDef(mechanism);
						// f.setForecastIndex(forecastIndex);
						// f.setMechanism(tdText);
						// }
						// }
						// if (i == 6) {
						// for (Forecast f : forecasts) {
						// f.setResearcher(tdText);
						// }
						// }
					}
					// if (CollectionUtils.isNotEmpty(forecasts)) {
					// this.forecaseService.save(forecasts);
					// }
				}

			}
		} catch (Exception e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}

	}

}
