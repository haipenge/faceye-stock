package com.faceye.component.stock.service.job;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.service.DailyStatService;
import com.faceye.feature.service.job.impl.BaseJob;

/**
 * 每日数据分析job
 * @author haipenge
 *
 */

@DisallowConcurrentExecution
@Service
public class DailyStatJob  extends BaseJob {
	@Autowired
    private DailyStatService dailyStatService=null;
//@Scheduled(cron="0 0 16 * * ?")
	@Override
	public void run() {
		//计算30日股价波幅
		this.dailyStatService.statPriceIn30Days();
		//标记星标数据
		this.dailyStatService.statDailyData2FindStar();
		//分析星标数据有效性
		this.dailyStatService.statStarData();
	}

}
