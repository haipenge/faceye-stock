package com.faceye.component.stock.service.job;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.service.DailyDataService;
import com.faceye.feature.service.job.impl.BaseJob;

/**
 * 计算股票的每日均线
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年2月24日
 */
@DisallowConcurrentExecution 
@Service
public class ComputeStockDailyDataAvgJob extends BaseJob {

	@Autowired
	private DailyDataService dailyDataService=null;
	
	@Override
	public void run() {
		this.dailyDataService.computeDailyDataLines();;
	}

}
