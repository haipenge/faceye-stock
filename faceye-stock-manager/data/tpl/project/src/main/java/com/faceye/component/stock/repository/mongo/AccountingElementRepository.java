package com.faceye.component.stock.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;



import com.faceye.component.stock.repository.mongo.customer.AccountingElementCustomerRepository;
import com.faceye.component.stock.repository.mongo.gen.AccountingElementGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * AccountingElement 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AccountingElementRepository extends AccountingElementGenRepository{
	
	
}/**@generate-repository-source@**/
