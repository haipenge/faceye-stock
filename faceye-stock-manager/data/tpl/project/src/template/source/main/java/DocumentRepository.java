package com.faceye.component.@component.name@.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.@component.name@.repository.mongo.customer.@entity.name@CustomerRepository;
import com.faceye.component.@component.name@.repository.mongo.gen.@entity.name@GenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * @entity.name@ 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface @entity.name@Repository extends @entity.name@GenRepository{
	
	
}/**@generate-repository-source@**/
