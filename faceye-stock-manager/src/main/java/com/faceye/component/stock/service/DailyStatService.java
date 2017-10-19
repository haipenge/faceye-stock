package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.feature.service.BaseService;

/**
 * DailyStat 服务接品<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
public interface DailyStatService extends BaseService<DailyStat, Long> {

	
	public void statStockDailyData(Stock stock);
	/**
	 * 
	 * 分析30天股价的变化情况
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月24日 下午2:54:29
	 */
	public void statPriceIn30Days();
	
	/**
	 * 分析一只股票 30天的价格变化 
	 * @param stock
	 */
	public void statPriceIn30Days(Stock stock);
	

	/**
	 * 分析每日交易数据，标记星标数据 找出5，10，20线成多头排列的时间点
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月11日 下午2:12:54
	 */
	public void statDailyData2FindStar();

	/**
	 * 分析单个股票
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月17日 上午9:11:19
	 */
	public void statDailyData2FindStar(Stock stock);

	/**
	 * 分析星标数据质量
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月11日 下午4:45:11
	 */
	public void statStarData();

	/**
	 * 分析单个股票的星标
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月17日 上午9:11:26
	 */
	public void statStarData(Stock stock);

}/** @generate-service-source@ **/
