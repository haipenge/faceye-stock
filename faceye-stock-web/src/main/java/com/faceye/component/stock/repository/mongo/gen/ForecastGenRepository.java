package com.faceye.component.stock.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.stock.entity.Forecast;
import com.faceye.component.stock.repository.mongo.customer.ForecastCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Forecast 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ForecastGenRepository extends BaseMongoRepository<Forecast,Long>  {
	 
	
}/**@generate-repository-source@**/
