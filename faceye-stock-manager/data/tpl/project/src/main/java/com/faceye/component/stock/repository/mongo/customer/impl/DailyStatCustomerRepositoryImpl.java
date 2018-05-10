package com.faceye.component.stock.repository.mongo.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.repository.mongo.customer.DailyStatCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * DailyStat 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Repository
public class DailyStatCustomerRepositoryImpl implements DailyStatCustomerRepository {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;
	@Override
	public void removeDailyStatByStock(Long stockId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("stockId").is(stockId));
		this.mongoOperations.remove(query, DailyStat.class);
	}
	
}/**@generate-repository-source@**/
