package com.faceye.component.@component.name@.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.@component.name@.entity.@entity.name@;
import com.faceye.component.@component.name@.repository.mongo.customer.@entity.name@CustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * @entity.name@ 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface @entity.name@GenRepository extends BaseMongoRepository<@entity.name@,Long>  {
	 
	
}/**@generate-repository-source@**/
