package com.faceye.component.stock.service.job;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.service.DataStatService;
import com.faceye.feature.service.job.impl.BaseJob;

@DisallowConcurrentExecution
@Service
public class DataStatJob extends BaseJob {
	@Autowired
	private DataStatService dataStatService = null;

//    @Scheduled(cron="0 0 8 * * ?")
	@Scheduled(cron="${cron.data.stat.job}")
	@Override
	public void run() {
		this.dataStatService.stat();
	}

}
