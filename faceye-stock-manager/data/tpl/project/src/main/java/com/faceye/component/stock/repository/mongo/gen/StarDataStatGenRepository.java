package com.faceye.component.stock.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.repository.mongo.customer.StarDataStatCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * StarDataStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface StarDataStatGenRepository extends BaseMongoRepository<StarDataStat,Long>  {
	 
	
}/**@generate-repository-source@**/
