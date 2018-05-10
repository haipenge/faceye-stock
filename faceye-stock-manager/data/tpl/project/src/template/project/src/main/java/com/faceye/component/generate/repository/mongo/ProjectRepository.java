package com.faceye.component.generate.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.generate.entity.Project;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Project 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ProjectRepository extends BaseMongoRepository<Project,Long>{
	
	
}/**@generate-repository-source@**/
