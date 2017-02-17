package com.faceye.component.stock.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.stock.entity.FinancialReport;
import com.faceye.component.stock.repository.mongo.customer.FinancialReportCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * FinancialReport 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface FinancialReportGenRepository extends BaseMongoRepository<FinancialReport,Long>  {
	 
	
}/**@generate-repository-source@**/
