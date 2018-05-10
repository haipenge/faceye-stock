package com.faceye.component.stock.service.job;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.service.StockService;
import com.faceye.feature.service.job.impl.BaseJob;

/**
 * 对股票进行定期初始化，实现自动发现新上市股票
 * @author songhaipeng
 *
 */
@DisallowConcurrentExecution
@Service
public class InitStockJob extends BaseJob {
	@Autowired
    private StockService stockService=null;
	@Scheduled(cron="0 55 10 * * ?")
	@Override
	public void run() {
		stockService.initStocks();
		
	}

}
