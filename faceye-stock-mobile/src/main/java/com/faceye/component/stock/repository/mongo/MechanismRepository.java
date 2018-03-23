package com.faceye.component.stock.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.stock.repository.mongo.customer.MechanismCustomerRepository;
import com.faceye.component.stock.repository.mongo.gen.MechanismGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Mechanism 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface MechanismRepository extends MechanismGenRepository{
	
	
}/**@generate-repository-source@**/
