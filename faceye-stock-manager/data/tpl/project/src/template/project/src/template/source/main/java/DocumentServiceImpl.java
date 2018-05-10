package com.faceye.component.@component.name@.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.@component.name@.entity.@entity.name@;

import com.faceye.component.@component.name@.repository.mongo.@entity.name@Repository;
import com.faceye.component.@component.name@.service.@entity.name@Service;

 
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * @entity.name@ 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
import com.mysema.query.types.Predicate;
@Service
public class @entity.name@ServiceImpl extends BaseMongoServiceImpl<@entity.name@, Long, @entity.name@Repository> implements @entity.name@Service {

	@Autowired
	public @entity.name@ServiceImpl(@entity.name@Repository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<@entity.name@> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<@entity.name@> entityPath = resolver.createPath(entityClass);
		// PathBuilder<@entity.name@> builder = new PathBuilder<@entity.name@>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<@entity.name@> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<@entity.name@>("id") {
			// })
			List<@entity.name@> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<@entity.name@>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
