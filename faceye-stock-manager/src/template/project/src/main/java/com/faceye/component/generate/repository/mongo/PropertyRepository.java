package com.faceye.component.generate.repository.mongo;

import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.repository.mongo.customer.PropertyCustomerRepository;
import com.faceye.component.generate.repository.mongo.gen.PropertyGenRepository;
/**
 * Property 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface PropertyRepository extends PropertyGenRepository,PropertyCustomerRepository {
	
	public Property getPropertyByPropertyName(String propertyName);
	
}/**@generate-repository-source@**/
