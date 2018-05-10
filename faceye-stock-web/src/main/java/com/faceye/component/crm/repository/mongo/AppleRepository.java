package com.faceye.component.crm.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.crm.repository.mongo.customer.AppleCustomerRepository;
import com.faceye.component.crm.repository.mongo.gen.AppleGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Apple 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AppleRepository extends AppleGenRepository{
	
	
}/**@generate-repository-source@**/
