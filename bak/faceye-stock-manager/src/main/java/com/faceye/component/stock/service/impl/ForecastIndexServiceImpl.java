package com.faceye.component.stock.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.entity.Mechanism;
import com.faceye.component.stock.repository.mongo.ForecastIndexRepository;
import com.faceye.component.stock.repository.mongo.customer.ForecastIndexCustomerRepository;
import com.faceye.component.stock.service.ForecastIndexService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;

/**
 * ForecastIndex 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class ForecastIndexServiceImpl extends BaseMongoServiceImpl<ForecastIndex, Long, ForecastIndexRepository> implements ForecastIndexService {
	@Autowired
	private ForecastIndexCustomerRepository forecastIndexCustomerRepository = null;

	@Autowired
	public ForecastIndexServiceImpl(ForecastIndexRepository dao) {
		super(dao);
	}

	@Override
	public Page<ForecastIndex> getPage(Map<String, Object> searchParams, int page, int size) {
//		 if (page != 0) {
//		 page = page - 1;
//		 }
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<ForecastIndex> entityPath = resolver.createPath(entityClass);
		// PathBuilder<ForecastIndex> builder = new PathBuilder<ForecastIndex>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		// Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		// if (predicate != null) {
		// logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		// }
		// Sort sort = new Sort(Direction.DESC, "id");
		// Page<ForecastIndex> res = null;
		// if (size != 0) {
		// Pageable pageable = new PageRequest(page, size, sort);
		// res = this.dao.findAll(predicate, pageable);
		// } else {
		// // OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<ForecastIndex>("id") {
		// // })
		// List<ForecastIndex> items = (List) this.dao.findAll(predicate);
		// res = new PageImpl<ForecastIndex>(items);
		//
		// }
		return super.getPage(searchParams, page, size);
	}

	@Override
	public ForecastIndex getForecastIndexByMechanismAndReportDate(Long stockId, Mechanism mechanism, Date reportDate) {
		ForecastIndex forecastIndex = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", stockId);
		searchParams.put("EQ|mechanism.$id", mechanism.getId());
		String sDate = DateUtil.formatDate(reportDate, "yyyy-MM-dd");
		searchParams.put("GTE|reportDate", DateUtil.getDateFromString(sDate + " 00:00:00","yyyy-MM-dd HH:mm:ss"));
		searchParams.put("LTE|reportDate", DateUtil.getDateFromString(sDate + " 23:59:59","yyyy-MM-dd HH:mm:ss"));
		Page<ForecastIndex> indexs = this.getPage(searchParams, 1, 0);
		if (indexs != null && CollectionUtils.isNotEmpty(indexs.getContent())) {
			forecastIndex = indexs.getContent().get(0);
		} else {
			forecastIndex = new ForecastIndex();
			forecastIndex.setMechanism(mechanism);
			forecastIndex.setReportDate(reportDate);
			forecastIndex.setStockId(stockId);
			this.save(forecastIndex);
		}
		return forecastIndex;
	}

}/** @generate-service-source@ **/
