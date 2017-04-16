package com.faceye.component.stock.repository.mongo.customer;

import java.util.Map;
/**
 * DailyStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DailyDataCustomerRepository {
	
	public void clearHistoryDailyData();
	
	
	public long getCount(Map params);
}/**@generate-repository-source@**/
