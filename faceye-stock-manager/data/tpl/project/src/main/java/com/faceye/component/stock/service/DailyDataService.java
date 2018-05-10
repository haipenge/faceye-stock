package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.feature.service.BaseService;

public interface DailyDataService extends BaseService<DailyData,Long>{

	
	/**
	 *  初始化某一股票的每日数据(爬取)
	 * @todo
	 * @param code
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月17日
	 */
	public void initDailyData(String code);
	
	/**
	 * 初始化股票交易数据
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月17日
	 */
	public void initDailyData();
	
	
	
	/**
	 * 计算一只股票的均线体系
	 * @todo
	 * @param code
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月17日
	 */
	public void initDailyDataAvg(String code);
	
	/**
	 * 计算所有股票的均线数据，5，10，20，30，60，120，250
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月17日
	 */
	public void computeDailyDataLines();
	/**
	 * 计算一只股票的均线
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月17日 下午5:40:21
	 */
	public void computeDailyDataLines(Stock stock);
	
	/**
	 * 从新浪数据接口爬取每日数据
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月24日
	 */
	public void crawlDailyData();
	/**
	 * 从新浪 数据接口，爬取某一股票的每日数据
	 * @todo
	 * @param stock
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月24日
	 */
	public void crawlDailyData(Stock stock);
	
	/**
	 *  清除某一股票的每日数据
	 * @param stockId
	 */
	public void removeDailyDataByStock(Long stockId);
}/**@generate-service-source@**/
