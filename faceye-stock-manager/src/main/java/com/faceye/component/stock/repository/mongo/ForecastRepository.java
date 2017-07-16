package com.faceye.component.stock.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;



import com.faceye.component.stock.repository.mongo.customer.ForecastCustomerRepository;
import com.faceye.component.stock.repository.mongo.gen.ForecastGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Forecast 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ForecastRepository extends ForecastGenRepository{
	
	
}/**@generate-repository-source@**/
