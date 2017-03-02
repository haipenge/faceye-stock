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

import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.repository.mongo.CategoryRepository;
import com.faceye.component.stock.repository.mongo.StockRepository;
import com.faceye.component.stock.repository.mongo.customer.CategoryCustomerRepository;
import com.faceye.component.stock.service.CategoryService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;
/**
 * Category 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class CategoryServiceImpl extends BaseMongoServiceImpl<Category, Long, CategoryRepository> implements CategoryService {
	@Autowired
	private CategoryCustomerRepository categoryCustomerRepository=null;
	@Autowired
	private StockRepository stockRepository=null;
	@Autowired
	public CategoryServiceImpl(CategoryRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<Category> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Category> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Category> builder = new PathBuilder<Category>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Category> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Category>("id") {
			// })
			List<Category> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Category>(items);

		}
		return res;
	}


	@Override
	public void init() {
	}
	
	
	
}/**@generate-service-source@**/
