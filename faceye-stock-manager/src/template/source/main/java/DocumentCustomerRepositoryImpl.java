package com.faceye.component.@component.name@.repository.mongo.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.@component.name@.entity.@entity.name@;
import com.faceye.component.@component.name@.repository.mongo.customer.@entity.name@CustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * @entity.name@ 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public class @entity.name@CustomerRepositoryImpl implements @entity.name@CustomerRepository {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;
	
}/**@generate-repository-source@**/
