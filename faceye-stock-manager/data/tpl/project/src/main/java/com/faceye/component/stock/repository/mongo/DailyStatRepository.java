package com.faceye.component.stock.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.stock.repository.mongo.customer.DailyStatCustomerRepository;
import com.faceye.component.stock.repository.mongo.gen.DailyStatGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * DailyStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DailyStatRepository extends DailyStatGenRepository{
	
	
}/**@generate-repository-source@**/
