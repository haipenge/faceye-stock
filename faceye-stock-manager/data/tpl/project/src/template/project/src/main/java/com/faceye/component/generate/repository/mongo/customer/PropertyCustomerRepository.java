package com.faceye.component.generate.repository.mongo.customer;

import com.faceye.component.generate.entity.Property;

public interface PropertyCustomerRepository{

	
	public Property getPropertyByName(String name);
	
}
