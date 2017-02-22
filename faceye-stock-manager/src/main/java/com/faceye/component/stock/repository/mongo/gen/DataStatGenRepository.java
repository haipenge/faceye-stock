package com.faceye.component.stock.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.repository.mongo.customer.DataStatCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * DataStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DataStatGenRepository extends BaseMongoRepository<DataStat,Long>  {
	 
	
}/**@generate-repository-source@**/
