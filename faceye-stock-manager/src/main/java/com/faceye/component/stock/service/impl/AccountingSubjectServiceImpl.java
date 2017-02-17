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

import com.faceye.component.stock.entity.AccountingSubject;

import com.faceye.component.stock.repository.mongo.AccountingSubjectRepository;
import com.faceye.component.stock.repository.mongo.customer.AccountingSubjectCustomerRepository;
import com.faceye.component.stock.service.AccountingSubjectService;

import com.faceye.feature.util.ServiceException;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;
/**
 * AccountingSubject 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class AccountingSubjectServiceImpl extends BaseMongoServiceImpl<AccountingSubject, Long, AccountingSubjectRepository> implements AccountingSubjectService {
	@Autowired
	private AccountingSubjectCustomerRepository accountingSubjectCustomerRepository=null;
	@Autowired
	public AccountingSubjectServiceImpl(AccountingSubjectRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<AccountingSubject> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<AccountingSubject> entityPath = resolver.createPath(entityClass);
		// PathBuilder<AccountingSubject> builder = new PathBuilder<AccountingSubject>(entityPath.getType(), entityPath.getMetadata());
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
		Page<AccountingSubject> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<AccountingSubject>("id") {
			// })
			List<AccountingSubject> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<AccountingSubject>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
