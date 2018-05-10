package com.faceye.component.generate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Validate;
import com.faceye.component.generate.repository.mongo.ValidateRepository;
import com.faceye.component.generate.service.ValidateService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Validate 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service
public class ValidateServiceImpl extends BaseMongoServiceImpl<Validate, Long, ValidateRepository> implements ValidateService {

	@Autowired
	public ValidateServiceImpl(ValidateRepository dao) {
		super(dao);
	}
	
	
}/**@generate-service-source@**/
