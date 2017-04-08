package com.faceye.component.stock.repository.mongo.customer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.customer.StockCustomerRepository;

/**
 * AccountingElement 实体DAO<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
@Repository
public class StockCustomerRepositoryImpl implements StockCustomerRepository {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;

	@Override
	public Page<Stock> getPage(Map searchParams, int page, int size) {
//		if (page != 0) {
//			page = page - 1;
//		}
		Long categoryId = MapUtils.getLong(searchParams, "EQ|category.$id");
		String likeName = MapUtils.getString(searchParams, "like|name");
		String likeCode = MapUtils.getString(searchParams, "like|code");
		Double minPe = MapUtils.getDouble(searchParams, "GTE|dailyStat.pe");
		Double maxPe = MapUtils.getDouble(searchParams, "LTE|dailyStat.pe");
		Query query = new Query();
		if (categoryId != null) {
			query.addCriteria(Criteria.where("category.$id").is(categoryId));
		}
		Criteria peCriteria = null;
		if (minPe != null) {
			if (peCriteria == null) {
				peCriteria = Criteria.where("dailyStat.pe").gte(minPe);
			}
		}
		if (maxPe != null) {
			if (peCriteria == null) {
				peCriteria = Criteria.where("dailyStat.pe").lte(maxPe);
			} else {
				peCriteria.andOperator(Criteria.where("dailyStat.pe").lte(maxPe));
			}
		}
		if (peCriteria != null) {
			query.addCriteria(peCriteria);
		}
		Criteria orCriterias = null;
		List<Criteria> nameCriterias = new ArrayList<Criteria>(0);
		List<Criteria> codeCriterias = new ArrayList<Criteria>(0);
		if (StringUtils.isNotEmpty(likeName)) {
			likeName = StringUtils.replace(likeName, "，", ",");
			likeName = StringUtils.replace(likeName, " ", ",");
			String[] names = StringUtils.split(likeName, ",");
			for (String name : names) {
				name = StringUtils.trim(name);
				if (StringUtils.isNotEmpty(name)) {
					nameCriterias.add(Criteria.where("name").regex(name));
				}
			}
		}
		if (StringUtils.isNotEmpty(likeCode)) {
			likeCode = StringUtils.replace(likeCode, "，", ",");
			likeCode = StringUtils.replace(likeCode, " ", ",");
			String codes[] = StringUtils.split(likeCode, ",");
			for (String code : codes) {
				code = StringUtils.trim(code);
				if (StringUtils.isNotEmpty(code)) {
					codeCriterias.add(Criteria.where("code").regex(code));
				}
			}
		}
		if (CollectionUtils.isNotEmpty(nameCriterias)) {
			if (orCriterias == null) {
				orCriterias = new Criteria();
			}
			orCriterias.orOperator(nameCriterias.toArray(new Criteria[nameCriterias.size()]));
		}
		if (CollectionUtils.isNotEmpty(codeCriterias)) {
			if (orCriterias == null) {
				orCriterias = new Criteria();
				orCriterias.orOperator(codeCriterias.toArray(new Criteria[codeCriterias.size()]));
			} else {
				orCriterias.andOperator(codeCriterias.toArray(new Criteria[codeCriterias.size()]));
			}
		}
		if (orCriterias != null) {
			query.addCriteria(orCriterias);
		}

		query.skip((page-1) * size);
		if (size > 0) {
			query.limit(size);
		}
		// query.getSortObject().put("dailyStat.pe", Order.ASC);
		Sort sort = new Sort(Direction.ASC, "dailyStat.pe");
		query.with(sort);
		logger.debug(">>FaceYe query object is:" + query.toString());
		List<Stock> stocks = this.mongoOperations.find(query, Stock.class);
		long count = this.mongoOperations.count(query, Stock.class);
		Pageable pageable = new PageRequest(page, size);
		Page<Stock> res = new PageImpl(stocks, pageable, count);
		return res;
	}

}/** @generate-repository-source@ **/
