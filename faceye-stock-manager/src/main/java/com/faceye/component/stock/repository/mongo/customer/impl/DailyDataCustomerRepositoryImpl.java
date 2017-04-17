package com.faceye.component.stock.repository.mongo.customer.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.repository.mongo.customer.DailyDataCustomerRepository;

/**
 * DailyStat 实体DAO<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
@Repository
public class DailyDataCustomerRepositoryImpl implements DailyDataCustomerRepository {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;

	@Override
	public void clearHistoryDailyData() {
		Date now = new Date();
		Date daysBefore35Ago = new Date(now.getTime() - 35 * 24 * 60 * 60 * 1000L);
		Query query = new Query();
		query.addCriteria(Criteria.where("date").lt(daysBefore35Ago));
		// query.addCriteria(Criteria.where("id").is(id));
		this.mongoOperations.remove(query, DailyData.class);
	}

	@Override
	public long getCount(Map params) {
		Long stockId = MapUtils.getLong(params, "EQ|stockId");
		long count = 0;
		if (null != stockId) {
			Query query = new Query();
			query.addCriteria(Criteria.where("stockId").is(stockId));
			count = this.mongoOperations.count(query, DailyData.class);
		}
		return count;
	}

	@Override
	public void removeStockHistoryDailyData(Long stockId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("stockId").is(stockId));
		// query.addCriteria(Criteria.where("id").is(id));
		this.mongoOperations.remove(query, DailyData.class);
	}

}/** @generate-repository-source@ **/
