package com.faceye.component.stock.repository.mongo.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * DailyStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DailyStatCustomerRepository {
	
	/**
	 * 清除某一股票的每日分析数据
	 * @param stockId
	 */
	public void removeDailyStatByStock(Long stockId);
}/**@generate-repository-source@**/
