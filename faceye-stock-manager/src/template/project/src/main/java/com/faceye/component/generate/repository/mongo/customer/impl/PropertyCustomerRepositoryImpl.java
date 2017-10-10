package com.faceye.component.generate.repository.mongo.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.repository.mongo.customer.PropertyCustomerRepository;

public class PropertyCustomerRepositoryImpl  implements PropertyCustomerRepository {

	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;
	
	
	@Override
	public Property getPropertyByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return this.mongoOperations.findOne(query, Property.class);
	}

}
