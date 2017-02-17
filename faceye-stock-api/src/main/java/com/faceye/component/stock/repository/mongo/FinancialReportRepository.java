package com.faceye.component.stock.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;



import com.faceye.component.stock.repository.mongo.customer.FinancialReportCustomerRepository;
import com.faceye.component.stock.repository.mongo.gen.FinancialReportGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * FinancialReport 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface FinancialReportRepository extends FinancialReportGenRepository{
	
	
}/**@generate-repository-source@**/
