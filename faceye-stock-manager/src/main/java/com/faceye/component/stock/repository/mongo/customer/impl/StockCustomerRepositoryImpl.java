package com.faceye.component.stock.repository.mongo.customer.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.customer.StockCustomerRepository;
import com.querydsl.core.types.Order;

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
		if (page != 0) {
			page = page - 1;
		}
		Long categoryId = MapUtils.getLong(searchParams, "EQ|category.$id");
		String likeName = MapUtils.getString(searchParams, "like|name");
		String likeCode = MapUtils.getString(searchParams, "like|code");
		Double minPe = MapUtils.getDouble(searchParams, "GTE|dailyStat.pe");
		Double maxPe = MapUtils.getDouble(searchParams, "LTE|dailyStat.pe");
		Query query = new Query();
		if (categoryId != null) {
			query.addCriteria(Criteria.where("category.$id").is(categoryId));
		}
		if (minPe != null) {
			query.addCriteria(Criteria.where("dailyStat.pe").gte(minPe));
		}
		if (maxPe != null) {
			query.addCriteria(Criteria.where("dailyStat.pe").lte(maxPe));
		}
		if (StringUtils.isNotEmpty(likeName)) {
			likeName = StringUtils.replace(likeName, "，", ",");
			likeName = StringUtils.replace(likeName, " ", ",");
			String[] names = StringUtils.split(likeName, ",");
			Criteria nameCriteria = null;
			for (String name : names) {
				if (StringUtils.isNotEmpty(name)) {
					if (nameCriteria == null) {
						nameCriteria = Criteria.where("name").regex(name);
					} else {
						nameCriteria.orOperator(Criteria.where("name").regex(name));
					}
				}
			}
			if (nameCriteria != null) {
				query.addCriteria(nameCriteria);
			}
		}
		if (StringUtils.isNotEmpty(likeCode)) {
			likeCode = StringUtils.replace(likeCode, "，", ",");
			likeCode = StringUtils.replace(likeCode, " ", ",");
			String codes[] = StringUtils.split(likeCode, ",");
			Criteria codeCriteria = null;
			for (String code : codes) {
				if (StringUtils.isNotEmpty(code)) {
					if (codeCriteria == null) {
						codeCriteria = Criteria.where("code").regex(code);
					} else {
						codeCriteria.orOperator(Criteria.where("code").regex(code));
					}
				}
			}
			if (codeCriteria != null) {
				query.addCriteria(codeCriteria);
			}
		}
		query.skip(page * size);
		query.limit(size);
		query.getSortObject().put("dailyStat.pe", Order.ASC);
		logger.debug(">>FaceYe query object is:"+query.toString());
		List<Stock> stocks = this.mongoOperations.find(query, Stock.class);
		long count = this.mongoOperations.count(query, Stock.class);
		Pageable pageable = new PageRequest(page, size);
		Page<Stock> res = new PageImpl(stocks, pageable, count);
		return res;
	}

}/** @generate-repository-source@ **/
