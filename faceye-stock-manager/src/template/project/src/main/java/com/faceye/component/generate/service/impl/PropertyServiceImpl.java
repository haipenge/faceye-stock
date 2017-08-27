package com.faceye.component.generate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.repository.mongo.DataTypeRepository;
import com.faceye.component.generate.repository.mongo.EntityRepository;
import com.faceye.component.generate.repository.mongo.PropertyRepository;
import com.faceye.component.generate.service.PropertyService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Property 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service("entityPropertyService")
public class PropertyServiceImpl extends BaseMongoServiceImpl<Property, Long, PropertyRepository> implements PropertyService {

	@Autowired
	private EntityRepository entityRepository=null;
	@Autowired
	private DataTypeRepository dataTypeRepository=null;
	@Autowired
	public PropertyServiceImpl(PropertyRepository dao) {
		super(dao);
	}
	
	
	
}/**@generate-service-source@**/
