package com.faceye.component.stock.service.job;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.service.DailyStatService;
import com.faceye.feature.service.job.impl.BaseJob;

@DisallowConcurrentExecution
@Service
/**
 * 每日数据分析job
 * @author haipenge
 *
 */
public class DailyStatJob  extends BaseJob {
	@Autowired
    private DailyStatService dailyStatService=null;
	@Override
	public void run() {
		this.dailyStatService.statPriceIn30Days();
		this.dailyStatService.starDataStat();
	}

}
