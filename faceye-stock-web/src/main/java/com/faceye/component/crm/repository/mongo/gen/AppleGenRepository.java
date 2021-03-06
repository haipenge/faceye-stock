package com.faceye.component.crm.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.crm.entity.Apple;
import com.faceye.component.crm.repository.mongo.customer.AppleCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Apple 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AppleGenRepository extends BaseMongoRepository<Apple,Long>  {
	 
	
}/**@generate-repository-source@**/
