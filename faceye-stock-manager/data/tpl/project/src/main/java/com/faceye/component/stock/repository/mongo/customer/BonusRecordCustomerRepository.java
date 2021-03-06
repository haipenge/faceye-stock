package com.faceye.component.stock.repository.mongo.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.stock.entity.BonusRecord;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * BonusRecord 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface BonusRecordCustomerRepository {
	
	public void removeBonus(Long stockId);
}/**@generate-repository-source@**/
