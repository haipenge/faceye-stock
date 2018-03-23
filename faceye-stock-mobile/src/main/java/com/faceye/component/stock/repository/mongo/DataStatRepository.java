package com.faceye.component.stock.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.stock.repository.mongo.customer.DataStatCustomerRepository;
import com.faceye.component.stock.repository.mongo.gen.DataStatGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * DataStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DataStatRepository extends DataStatGenRepository{
	
	
}/**@generate-repository-source@**/
