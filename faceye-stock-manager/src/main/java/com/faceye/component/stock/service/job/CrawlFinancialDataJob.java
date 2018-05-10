package com.faceye.component.stock.service.job;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.service.job.impl.BaseJob;

@DisallowConcurrentExecution
@Service
public class CrawlFinancialDataJob extends BaseJob {

	@Autowired
	private StockService stockService = null;
	@Autowired
	private CrawlFinancialDataService crawlFinancialDataService = null;

	@Scheduled(cron="0 25 14 * * ?")
	@Override
	public void run() {
		logger.debug(">>FaceYe start run crawl financial data job");
		this.crawlFinancialDataService.crawl();
	}
}
