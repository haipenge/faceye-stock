package com.faceye.component.generate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.repository.mongo.ComponentRepository;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Component 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service
public class ComponentServiceImpl extends BaseMongoServiceImpl<Component, Long, ComponentRepository> implements ComponentService {
	
	@Autowired
	public ComponentServiceImpl(ComponentRepository dao) {
		super(dao);
	}
	
	
}/**@generate-service-source@**/
