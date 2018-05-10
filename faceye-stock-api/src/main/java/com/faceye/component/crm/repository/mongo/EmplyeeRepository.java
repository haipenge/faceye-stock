package com.faceye.component.crm.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.crm.repository.mongo.customer.EmplyeeCustomerRepository;
import com.faceye.component.crm.repository.mongo.gen.EmplyeeGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Emplyee 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface EmplyeeRepository extends EmplyeeGenRepository{
	
	
}/**@generate-repository-source@**/
