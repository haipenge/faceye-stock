package com.faceye.component.stock.repository.mongo.customer;

import java.util.Map;

/**
 * DailyStat 实体DAO<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
public interface DailyDataCustomerRepository {

	public void clearHistoryDailyData();

	/**
	 * 清除一只股票的历史数据
	 * 
	 * @param stockId
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月17日 下午6:33:44
	 */
	public void removeStockHistoryDailyData(Long stockId);

	public long getCount(Map params);
	
	/**
	 * 重置数据星标类型
	 * @param stockId
	 * @param startType
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月18日 下午5:20:39
	 */
	public void resetDailyDataStatType(Long stockId,Integer starType);
}/** @generate-repository-source@ **/
