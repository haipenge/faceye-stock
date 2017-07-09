package com.faceye.component.stock.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.repository.mongo.StarDataStatRepository;
import com.faceye.component.stock.repository.mongo.customer.StarDataStatCustomerRepository;
import com.faceye.component.stock.service.StarDataStatService;
import com.faceye.component.stock.service.wrapper.WrapStarDataStat;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;

/**
 * StarDataStat 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class StarDataStatServiceImpl extends BaseMongoServiceImpl<StarDataStat, Long, StarDataStatRepository> implements StarDataStatService {
	@Autowired
	private StarDataStatCustomerRepository starDataStatCustomerRepository = null;

	@Autowired
	public StarDataStatServiceImpl(StarDataStatRepository dao) {
		super(dao);
	}

	@Override
	public Page<StarDataStat> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<StarDataStat> entityPath = resolver.createPath(entityClass);
		// PathBuilder<StarDataStat> builder = new PathBuilder<StarDataStat>(entityPath.getType(), entityPath.getMetadata());
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
		Page<StarDataStat> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<StarDataStat>("id") {
			// })
			List<StarDataStat> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<StarDataStat>(items);

		}
		return res;
	}

	@Override
	public List<Long> getStarStockIds(Map params) {
		List<Long> ids = Lists.newArrayList();
		Date now = new Date();
		Date before15Days = new Date(now.getTime() - 15 * 24 * 60 * 60 * 1000L);
		params.put("SORT|starDataDate", "desc");
		params.put("GTE|starDataDate", before15Days);
		List<StarDataStat> starDataStats = this.getPage(params, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(starDataStats)) {
			for (StarDataStat starDataStat : starDataStats) {
				if (!ids.contains(starDataStat.getStockId())) {
					ids.add(starDataStat.getStockId());
				}
			}
		}
		return ids;
	}

	@Override
	public WrapStarDataStat wrapStarDataStat(Map params, int page, int size) {
		WrapStarDataStat wrapStarDataStat = new WrapStarDataStat();
		Page<StarDataStat> starDataStats = this.getPage(params, page, size);
		wrapStarDataStat.setStarDataStats(starDataStats);
		if (starDataStats != null && starDataStats.getTotalElements() > 0) {
			Map countSearchParams = new HashMap();
			long total=starDataStats.getTotalElements();
			countSearchParams.putAll(params);
			countSearchParams.put("GTE|max5DayIncreaseRate", 0.03);
			long max5DayIncreaseCount = this.starDataStatCustomerRepository.getStarDataStatCount(countSearchParams);
			countSearchParams.remove("GTE|max5DayIncreaseRate");
			wrapStarDataStat.setMax5DayIncreaseSuccessRate(new Double(max5DayIncreaseCount)/total);
			// 10
			countSearchParams.put("GTE|max10DayIncreaseRate", 0.05);
			long max10DayIncreaseCount = this.starDataStatCustomerRepository.getStarDataStatCount(countSearchParams);
			countSearchParams.remove("GTE|max10DayIncreaseRate");
			wrapStarDataStat.setMax10DayIncreaseSuccessRate(new Double(max10DayIncreaseCount)/total);
			// 20
			countSearchParams.put("GTE|max20DayIncreaseRate", 0.05);
			long max20DayIncreaseCount = this.starDataStatCustomerRepository.getStarDataStatCount(countSearchParams);
			countSearchParams.remove("GTE|max20DayIncreaseRate");
			wrapStarDataStat.setMax20DayIncreaseSuccessRate(new Double(max20DayIncreaseCount)/total);
			// 30
			countSearchParams.put("GTE|max30DayIncreaseRate", 0.05);
			long max30DayIncreaseCount = this.starDataStatCustomerRepository.getStarDataStatCount(countSearchParams);
			countSearchParams.remove("GTE|max30DayIncreaseRate");
			wrapStarDataStat.setMax30DayIncreaseSuccessRate(new Double(max30DayIncreaseCount)/total);
			// 60
			countSearchParams.put("GTE|max60DayIncreaseRate", 0.10);
			long max60DayIncreaseCount = this.starDataStatCustomerRepository.getStarDataStatCount(countSearchParams);
			countSearchParams.remove("GTE|max60DayIncreaseRate");
			wrapStarDataStat.setMax60DayIncreaseSuccessRate(new Double(max60DayIncreaseCount)/total);
		}
		return wrapStarDataStat;
	}

	@Override
	public void removeStockStarStatResults(Long stockId) {
		this.starDataStatCustomerRepository.removeStockStarStatResults(stockId);
	}

}/** @generate-service-source@ **/
