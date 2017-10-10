package com.faceye.component.stock.repository.mongo.customer.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.repository.mongo.customer.StarDataStatCustomerRepository;

/**
 * StarDataStat 实体DAO<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
@Repository
public class StarDataStatCustomerRepositoryImpl implements StarDataStatCustomerRepository {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;

	@Override
	public long getStarDataStatCount(Map params) {
		long count = 0;
		Query query = new Query();
		Criteria criteria = Criteria.where("_id").gt(0L);
		Long stockId = MapUtils.getLong(params, "EQ|stockId");
		Integer starType=MapUtils.getInteger(params, "EQ|starType");
		Double max5DayIncreaseRate = MapUtils.getDouble(params, "GTE|max5DayIncreaseRate");
		Double max10DayIncreaseRate = MapUtils.getDouble(params, "GTE|max10DayIncreaseRate");
		Double max20DayIncreaseRate = MapUtils.getDouble(params, "GTE|max20DayIncreaseRate");
		Double max30DayIncreaseRate = MapUtils.getDouble(params, "GTE|max30DayIncreaseRate");
		Double max60DayIncreaseRate = MapUtils.getDouble(params, "GTE|max60DayIncreaseRate");
		if (null != stockId) {
			criteria.and("stockId").is(stockId);
		}
		criteria.and("starType").is(starType);
		// (max5DayIncreaseRate>0) ? criteria.and("max5DayIncreaseRate").gte(max5DayIncreaseRate):null;
		if (max5DayIncreaseRate != null) {
			criteria.and("max5DayIncreaseRate").gte(max5DayIncreaseRate);
		}
		if (max10DayIncreaseRate != null) {
			criteria.and("max10DayIncreaseRate").gte(max10DayIncreaseRate);
		}
		if (max20DayIncreaseRate != null) {
			criteria.and("max20DayIncreaseRate").gte(max20DayIncreaseRate);
		}
		if (max30DayIncreaseRate != null) {
			criteria.and("max30DayIncreaseRate").gte(max30DayIncreaseRate);
		}
		if (max60DayIncreaseRate != null) {
			criteria.and("max60DayIncreaseRate").gte(max60DayIncreaseRate);
		}
		query.addCriteria(criteria);
		count = this.mongoOperations.count(query, StarDataStat.class);
		logger.debug(">>FaceYe count Query is:" + query.toString() + ",Query Result is:" + count);
		return count;
	}

	@Override
	public void removeStockStarStatResults(Long stockId) {
		Query query=new Query();
		query.addCriteria(Criteria.where("stockId").is(stockId));
		logger.debug(">>FaceYe now remove starData stat result of stock:"+stockId);
		this.mongoOperations.remove(query, StarDataStat.class);
	}

}/** @generate-repository-source@ **/
