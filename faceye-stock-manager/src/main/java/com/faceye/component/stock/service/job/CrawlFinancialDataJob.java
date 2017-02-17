package com.faceye.component.stock.service.job;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public void run() {
		this.crawlFinancialDataService.crawl();
	}
}
