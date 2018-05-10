package com.faceye.component.@component.name@.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.faceye.component.@component.name@.entity.@entity.name@;

import com.faceye.feature.repository.DynamicSpecifications;
import com.faceye.component.@component.name@.repository.jpa.@entity.name@Repository;
import com.faceye.feature.repository.SearchFilter;
import com.faceye.component.@component.name@.service.@entity.name@Service;
import com.faceye.feature.service.impl.BaseServiceImpl;
 
/**
 * 模块:@component.name@<br>
 * 实体:@entity.name@<br>
 * 服务层实现类
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Service
public class @entity.name@ServiceImpl extends BaseServiceImpl<@entity.name@, Long, @entity.name@Repository> implements @entity.name@Service {

	@Autowired
	public @entity.name@ServiceImpl(@entity.name@Repository dao) {
		super(dao);
	}
	
	
}/**@generate-service-source@**/
