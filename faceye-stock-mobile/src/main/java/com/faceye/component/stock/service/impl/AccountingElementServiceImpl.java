package com.faceye.component.stock.service.impl;

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

import com.faceye.component.stock.entity.AccountingElement;

import com.faceye.component.stock.repository.mongo.AccountingElementRepository;
import com.faceye.component.stock.repository.mongo.customer.AccountingElementCustomerRepository;
import com.faceye.component.stock.service.AccountingElementService;

import com.faceye.feature.util.ServiceException;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;
/**
 * AccountingElement 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class AccountingElementServiceImpl extends BaseMongoServiceImpl<AccountingElement, Long, AccountingElementRepository> implements AccountingElementService {
	@Autowired
	private AccountingElementCustomerRepository accountingElementCustomerRepository=null;
	@Autowired
	public AccountingElementServiceImpl(AccountingElementRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<AccountingElement> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<AccountingElement> entityPath = resolver.createPath(entityClass);
		// PathBuilder<AccountingElement> builder = new PathBuilder<AccountingElement>(entityPath.getType(), entityPath.getMetadata());
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
		Page<AccountingElement> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<AccountingElement>("id") {
			// })
			List<AccountingElement> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<AccountingElement>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
