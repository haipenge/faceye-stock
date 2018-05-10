package com.faceye.component.stock.service.job;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.service.DailyDataService;
import com.faceye.feature.service.job.impl.BaseJob;
/**
 * 爬取股票的每日数据
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年2月24日
 */
@DisallowConcurrentExecution
@Service
public class CrawlStockDailyDataJob extends BaseJob {
    @Autowired
	private DailyDataService dailyDataService=null;
    @Scheduled(cron="0 10 15 * * ?")
	@Override
	public void run() {
		logger.debug(">>FaceYe start 2 crawl daily data.");
		dailyDataService.crawlDailyData();
	}

}
