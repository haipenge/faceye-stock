package com.faceye.component.stock.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;



import com.faceye.component.stock.repository.mongo.customer.ForecastIndexCustomerRepository;
import com.faceye.component.stock.repository.mongo.gen.ForecastIndexGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * ForecastIndex 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ForecastIndexRepository extends ForecastIndexGenRepository{
	
	
}/**@generate-repository-source@**/
