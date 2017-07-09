package com.faceye.component.stock.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.TotalStock;
import com.faceye.component.stock.repository.mongo.TotalStockRepository;
import com.faceye.component.stock.repository.mongo.customer.TotalStockCustomerRepository;
import com.faceye.component.stock.service.TotalStockService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.querydsl.core.types.Predicate;

/**
 * TotalStock 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class TotalStockServiceImpl extends BaseMongoServiceImpl<TotalStock, Long, TotalStockRepository> implements TotalStockService {
	@Autowired
	private TotalStockCustomerRepository totalStockCustomerRepository = null;

	@Autowired
	public TotalStockServiceImpl(TotalStockRepository dao) {
		super(dao);
	}

	@Override
	public Page<TotalStock> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<TotalStock> entityPath = resolver.createPath(entityClass);
		// PathBuilder<TotalStock> builder = new PathBuilder<TotalStock>(entityPath.getType(), entityPath.getMetadata());
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
		Page<TotalStock> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<TotalStock>("id") {
			// })
			List<TotalStock> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<TotalStock>(items);

		}
		return res;
	}

	@Override
	public boolean isTotalStockExist(Long stockId, String changeDate) {
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", stockId);
		Date startDate = DateUtil.getDateFromString(changeDate + " 00:00:00");
		Date endDate = DateUtil.getDateFromString(changeDate + " 23:59:59");
		searchParams.put("GET|changeDate", startDate);
		searchParams.put("LTE|changeDate", endDate);
		Page<TotalStock> res = this.getPage(searchParams, 1, 1);
		if (res != null && CollectionUtils.isNotEmpty(res.getContent())) {
			return true;
		}
		return false;
	}

}/** @generate-service-source@ **/
