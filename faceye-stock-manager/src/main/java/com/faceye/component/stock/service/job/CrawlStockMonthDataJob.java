package com.faceye.component.stock.service.job;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.service.job.impl.BaseJob;

/**
 * 按月，季度爬取股票数据
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年2月28日
 */
@DisallowConcurrentExecution
@Service
public class CrawlStockMonthDataJob extends BaseJob {
	@Autowired
    private DailyDataService dailyDataService=null;
	@Autowired
	private StockService stockService=null;
//	@Scheduled(cron="0 45 14 * * ?")
	@Scheduled(cron="${cron.crawl.stock.month.data.job}")
	@Override
	public void run() {
//		this.stockService.initStocks();
		this.dailyDataService.initDailyData();
	}
	
}
