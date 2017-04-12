package com.faceye.component.stock.repository.mongo.customer;

import java.util.Map;
/**
 * StarDataStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface StarDataStatCustomerRepository {
	/**
	 * 获取统计数据
	 * @param params
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月12日 上午8:21:22
	 */
	public long getStarDataStatCount(Map params);
}/**@generate-repository-source@**/
