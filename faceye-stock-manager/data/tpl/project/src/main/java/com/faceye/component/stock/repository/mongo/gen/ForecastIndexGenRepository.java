package com.faceye.component.stock.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.repository.mongo.customer.ForecastIndexCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * ForecastIndex 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ForecastIndexGenRepository extends BaseMongoRepository<ForecastIndex,Long>  {
	 
	
}/**@generate-repository-source@**/
