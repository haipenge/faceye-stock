package com.faceye.component.stock.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.stock.entity.TotalStock;
import com.faceye.component.stock.repository.mongo.customer.TotalStockCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * TotalStock 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface TotalStockGenRepository extends BaseMongoRepository<TotalStock,Long>  {
	 
	
}/**@generate-repository-source@**/
