package com.faceye.component.stock.repository.mongo.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.repository.mongo.customer.ForecastIndexCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * ForecastIndex 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Repository
public class ForecastIndexCustomerRepositoryImpl implements ForecastIndexCustomerRepository {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;
	
}/**@generate-repository-source@**/
