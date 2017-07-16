package com.faceye.component.stock.service.impl;

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

import com.faceye.component.stock.entity.BonusRecord;
import com.faceye.component.stock.repository.mongo.BonusRecordRepository;
import com.faceye.component.stock.repository.mongo.customer.BonusRecordCustomerRepository;
import com.faceye.component.stock.service.BonusRecordService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.querydsl.core.types.Predicate;

/**
 * BonusRecord 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class BonusRecordServiceImpl extends BaseMongoServiceImpl<BonusRecord, Long, BonusRecordRepository> implements BonusRecordService {
	@Autowired
	private BonusRecordCustomerRepository bonusRecordCustomerRepository = null;

	@Autowired
	public BonusRecordServiceImpl(BonusRecordRepository dao) {
		super(dao);
	}

	@Override
	public Page<BonusRecord> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<BonusRecord> entityPath = resolver.createPath(entityClass);
		// PathBuilder<BonusRecord> builder = new PathBuilder<BonusRecord>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
//		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
//		if (predicate != null) {
//			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
//		}
//		Sort sort = new Sort(Direction.DESC, "id");
//		Page<BonusRecord> res = null;
//		if (size != 0) {
//			Pageable pageable = new PageRequest(page, size, sort);
//			res = this.dao.findAll(predicate, pageable);
//		} else {
//			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<BonusRecord>("id") {
//			// })
//			List<BonusRecord> items = (List) this.dao.findAll(predicate);
//			res = new PageImpl<BonusRecord>(items);
//		}
		searchParams.put("SORT|publishDate", "desc");
		return this.dao.getPage(searchParams, page, size);
	}

	@Override
	public boolean isExistBonusRecord(Long stockId, String date) {
		BonusRecord record=this.getBonusRecord(stockId, date);
		if (record != null ) {
			return true;
		}
		return false;
	}

	@Override
	public BonusRecord getBonusRecord(Long stockId, String date) {
		BonusRecord record=null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", stockId);
		searchParams.put("GTE|publishDate", DateUtil.getDateFromString(date + " 00:00:00"));
		searchParams.put("LTE|publishDate", DateUtil.getDateFromString(date + " 23:59:59"));
		Page<BonusRecord> records = this.getPage(searchParams, 1, 0);
		if(records!=null && CollectionUtils.isNotEmpty(records.getContent())){
			record=records.getContent().get(0);
		}
		return record;
	}

}/** @generate-service-source@ **/
