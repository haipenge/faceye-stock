package com.faceye.component.stock.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.repository.mongo.customer.FinancialDataCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * FinancialData 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface FinancialDataGenRepository extends BaseMongoRepository<FinancialData,Long>  {
	 
	
}/**@generate-repository-source@**/
