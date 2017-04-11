package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.feature.service.BaseService;
/**
 * DailyStat 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DailyStatService extends BaseService<DailyStat,Long>{

	/**
	 * 
	 * 分析30天股价的变化情况
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月24日 下午2:54:29
	 */
	public void statPriceIn30Days();
	
	/**
	 * 对星标数据进行分析
	 * 找出5，10，20线成多头排列的时间点
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月11日 下午2:12:54
	 */
    public void starDataStat();
}/**@generate-service-source@**/
