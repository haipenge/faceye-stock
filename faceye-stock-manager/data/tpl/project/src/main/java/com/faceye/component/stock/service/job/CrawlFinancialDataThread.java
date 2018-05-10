package com.faceye.component.stock.service.job;

import java.util.ArrayList;
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
import org.springframework.data.domain.Page;

import com.faceye.component.stock.entity.AccountingSubject;
import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.service.QueueService;
import com.faceye.feature.service.job.thread.BaseThread;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.bean.BeanContextUtil;
import com.faceye.feature.util.http.Http;
import com.faceye.feature.util.regexp.RegexpUtil;

public class CrawlFinancialDataThread extends BaseThread {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private QueueService financialDataQueueService = null;
	private CrawlFinancialDataService crawlFinancialDataService = null;

	public CrawlFinancialDataThread() {
		financialDataQueueService = BeanContextUtil.getBean("financialDataQueueService");

		crawlFinancialDataService = BeanContextUtil.getBean(CrawlFinancialDataService.class);
	}

	@Override
	public void doBusiness() {
		crawl();
	}

	private void crawl() {
		while (true) {
			try {
				Stock stock = (Stock) financialDataQueueService.get();
				if (stock != null) {
					this.crawlFinancialDataService.crawlStock(stock);
					//Thread.sleep(2000L);
				} else {
					logger.error(">>FaceYe --> have not got stock from queue.");
					break;
				}
			} catch (Exception e) {
				logger.error(">>FaceYe -->throws Exception :", e);
			}
		}
	}

}
