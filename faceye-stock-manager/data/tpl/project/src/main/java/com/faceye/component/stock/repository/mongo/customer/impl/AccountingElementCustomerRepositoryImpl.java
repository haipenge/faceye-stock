package com.faceye.component.stock.repository.mongo.customer.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.repository.mongo.customer.AccountingElementCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * AccountingElement 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Repository
public class AccountingElementCustomerRepositoryImpl implements AccountingElementCustomerRepository {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;
	@Override
	public List<AccountingElement> getAccountingElementsByReportCategory(Long reportCategoryId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("reportCategory.$id").is(reportCategoryId));
		List<AccountingElement> accountingElements=this.mongoOperations.find(query,AccountingElement.class);
		return accountingElements;
	}
	
}/**@generate-repository-source@**/
