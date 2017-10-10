package com.faceye.component.generate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.repository.mongo.DataTypeRepository;
import com.faceye.component.generate.service.DataTypeService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * DataType 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service
public class DataTypeServiceImpl extends BaseMongoServiceImpl<DataType, Long, DataTypeRepository> implements DataTypeService {

	@Autowired
	public DataTypeServiceImpl(DataTypeRepository dao) {
		super(dao);
	}
	
	
}/**@generate-service-source@**/
