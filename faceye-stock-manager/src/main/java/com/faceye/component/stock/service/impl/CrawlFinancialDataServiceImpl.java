package com.faceye.component.stock.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.job.CrawlFinancialDataThread;
import com.faceye.feature.service.QueueService;
import com.faceye.feature.service.job.thread.ThreadPoolController;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.http.Http;
import com.faceye.feature.util.regexp.RegexpUtil;

@Service
public class CrawlFinancialDataServiceImpl implements CrawlFinancialDataService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private StockService stockService = null;
	@Autowired
	private AccountingSubjectService accountingSubjectService = null;
	@Autowired
	private FinancialDataService financialDataService = null;
	@Autowired
	@Qualifier("financialDataQueueService")
	private QueueService financialDataQueueService = null;

	@Override
	public void crawl() {
		List<Stock> stocks = this.stockService.getAll();
		Collections.shuffle(stocks);
		financialDataQueueService.addAll(stocks);
		Runnable runnabe = new CrawlFinancialDataThread();
		ThreadPoolController.getINSTANCE().execute("Crawl-Finanacial-data-Pool", runnabe, 8);

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
		if (!isStockCrawled) {
			String code = stock.getCode();
			String url = "";
			if (accountingSubjects == null) {
				accountingSubjects = this.getAccountingSubjects();
			}
			if (CollectionUtils.isNotEmpty(accountingSubjects)) {
				for (AccountingSubject accountingSubject : accountingSubjects) {
					url = accountingSubject.getSinaUrl();
					url = StringUtils.replace(url, "000998", code);
					logger.debug(">>FaceYe --> Crawl Financial Data Url is:" + url);
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
		}
	}

	private boolean isStockFinancialDataCrawled(Stock stock) {
		boolean isCrawled = false;
		Map params = new HashMap();
		params.put("EQ|stockId", stock.getId());
		Page<FinancialData> financialData = this.financialDataService.getPage(params, 0, 1);
		isCrawled = CollectionUtils.isNotEmpty(financialData.getContent());
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
			Long accountingElementId = accountingSubject.getAccountingElement().getId();
			for (Map<String, String> record : records) {
				String date = MapUtils.getString(record, "date");
				String data = MapUtils.getString(record, "data");

				// logger.debug(">>FaceYe --> parsed financial data is:" + date + ":" + data);
				boolean isExist = true;
				try {
					isExist = this.isFinancialDataExist(stock.getId(), accountingSubject.getId(), date);
				} catch (Exception e) {
					logger.error(">>FaceYe throws Exception when check is financial data exist,", e);
				}
				if (!isExist && StringUtils.isNotEmpty(date)) {
					try {
						FinancialData financialData = new FinancialData();
						financialData.setAccountingSubjectId(accountingSubject.getId());
						financialData.setAccountingElementId(accountingElementId);
						financialData.setCreateDate(new Date());
						if (StringUtils.isEmpty(data)) {
							financialData.setData(null);
						} else {
							financialData.setData(NumberUtils.createDouble(data));
						}
						financialData.setDate(DateUtil.getDateFromString(date, "yyyy-MM-dd"));
						financialData.setStockId(stock.getId());
						this.financialDataService.save(financialData);
					} catch (Exception e) {
						logger.error(">>FaceYe throws Exception when save data,data :date is:" + data + ":" + date, e);
					}
				} else {
					logger.debug(">>FaceYe --> financial data exist.");
				}
			}
		} else {
			logger.error(">>FaceYe -> have got empty record of stock :" + stock.getName() + "(" + stock.getCode() + "),[" + stock.getId() + "]");
		}
	}

	/**
	 * 判断财报数据记录是否存在
	 * 
	 * @param stockId
	 * @param accountingSubjectId
	 * @param date
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月13日 下午3:36:57
	 */
	private boolean isFinancialDataExist(Long stockId, Long accountingSubjectId, String date) throws Exception {
		boolean isExist = false;
		DateUtil.getDateFromString(date, "yyyy-MM-dd");
		date = StringUtils.substring(date, 0, 10);
		String start = date + " 00:00:00";
		String end = date + " 23:59:59";
		Map params = new HashMap();
		params.put("EQ|stockId", stockId);
		params.put("EQ|accountingSubjectId", accountingSubjectId);
		params.put("GTE|date", DateUtil.getDateFromString(start, "yyyy-MM-dd HH:mm:ss"));
		params.put("LTE|date", DateUtil.getDateFromString(end, "yyyy-MM-dd HH:mm:ss"));
		Page<FinancialData> page = this.financialDataService.getPage(params, 0, 1);
		isExist = CollectionUtils.isNotEmpty(page.getContent());
		return isExist;
	}
}
