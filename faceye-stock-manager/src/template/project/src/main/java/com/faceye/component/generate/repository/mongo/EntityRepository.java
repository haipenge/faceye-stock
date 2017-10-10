package com.faceye.component.generate.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.generate.entity.Entity;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Entity 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface EntityRepository extends BaseMongoRepository<Entity,Long> {
	
	
}/**@generate-repository-source@**/
