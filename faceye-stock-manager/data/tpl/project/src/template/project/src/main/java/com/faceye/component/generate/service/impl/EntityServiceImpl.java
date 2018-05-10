package com.faceye.component.generate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.repository.mongo.ComponentRepository;
import com.faceye.component.generate.repository.mongo.EntityRepository;
import com.faceye.component.generate.service.EntityService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Entity 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service
public class EntityServiceImpl extends BaseMongoServiceImpl<Entity, Long, EntityRepository> implements EntityService {

	@Autowired
	ComponentRepository componentRepository=null;
	@Autowired
	public EntityServiceImpl(EntityRepository dao) {
		super(dao);
	}
	
	
}/**@generate-service-source@**/
