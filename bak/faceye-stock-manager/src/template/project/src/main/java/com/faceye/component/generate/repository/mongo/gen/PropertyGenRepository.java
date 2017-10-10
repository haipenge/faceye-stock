package com.faceye.component.generate.repository.mongo.gen;

import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.repository.mongo.customer.PropertyCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Property 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface PropertyGenRepository extends BaseMongoRepository<Property,Long>,PropertyCustomerRepository {
	
	
}/**@generate-repository-source@**/
