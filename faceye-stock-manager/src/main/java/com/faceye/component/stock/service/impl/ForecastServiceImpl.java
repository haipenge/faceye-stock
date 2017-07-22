package com.faceye.component.stock.service.impl;

import java.util.ArrayList;
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
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.Forecast;
import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.repository.mongo.ForecastRepository;
import com.faceye.component.stock.repository.mongo.customer.ForecastCustomerRepository;
import com.faceye.component.stock.service.ForecastIndexService;
import com.faceye.component.stock.service.ForecastService;
import com.faceye.component.stock.service.wrapper.WrapForecast;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;

/**
 * Forecast 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class ForecastServiceImpl extends BaseMongoServiceImpl<Forecast, Long, ForecastRepository> implements ForecastService {
	@Autowired
	private ForecastCustomerRepository forecastCustomerRepository = null;
	@Autowired
	private ForecastIndexService forecastIndexService = null;

	@Autowired
	public ForecastServiceImpl(ForecastRepository dao) {
		super(dao);
	}

	@Override
	public Page<Forecast> getPage(Map<String, Object> searchParams, int page, int size) {
		// if (page != 0) {
		// page = page - 1;
		// }
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Forecast> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Forecast> builder = new PathBuilder<Forecast>(entityPath.getType(), entityPath.getMetadata());
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
		// Page<Forecast> res = null;
		// if (size != 0) {
		// Pageable pageable = new PageRequest(page, size, sort);
		// res = this.dao.findAll(predicate, pageable);
		// } else {
		// // OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Forecast>("id") {
		// // })
		// List<Forecast> items = (List) this.dao.findAll(predicate);
		// res = new PageImpl<Forecast>(items);
		//
		// }
		if (searchParams == null) {
			searchParams = new HashMap();
		}
		searchParams.put("SORT|reportDate:1", "desc");
		searchParams.put("SORT|mechanism:2", "asc");
		return this.dao.getPage(searchParams, page, size);
	}

	@Override
	public Forecast getForecast(Long stockId, Integer year, String reportDate, String mechanism) {
		Forecast forecast = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", stockId);
		searchParams.put("EQ|year", year);
		// searchParams.put("EQ|mechanism", mechanism);
		Date start = DateUtil.getDateFromString(reportDate + " 00:00:00");
		Date end = DateUtil.getDateFromString(reportDate + " 23:59:59");
		searchParams.put("GTE|reportDate", start);
		searchParams.put("LTE|reportDate", end);
		Page<Forecast> forecasts = this.getPage(searchParams, 1, 0);
		if (forecasts != null && CollectionUtils.isNotEmpty(forecasts.getContent())) {
			forecast = forecasts.getContent().get(0);
		}
		return forecast;
	}

	@Override
	public Page<WrapForecast> getWrapForecasts(Map searchParams, Integer page, Integer size) {
		Page<ForecastIndex> forecastIndexs = this.forecastIndexService.getPage(searchParams, page, size);
		Page<WrapForecast> wrapForecastsPage=null;
		List<WrapForecast> wrapForecasts=new ArrayList<WrapForecast>(0);
		if (forecastIndexs != null && CollectionUtils.isNotEmpty(forecastIndexs.getContent())) {
			for (ForecastIndex forecastIndex : forecastIndexs.getContent()) {
				WrapForecast wrapForecast=new WrapForecast();
				Map searchForecastParams = new HashMap();
				searchForecastParams.put("EQ|forecastIndex.$id", forecastIndex.getId());
				List<Forecast> forecasts=this.getPage(searchForecastParams, 1, 0).getContent();
				wrapForecast.setForecastIndex(forecastIndex);
				wrapForecast.setForecasts(forecasts);
				wrapForecasts.add(wrapForecast);
			}
		}
		Sort sort=null;
		if(page!=0){
			page=page-1;
		}
		Pageable pagable=new PageRequest(page, size, sort);
		wrapForecastsPage=new PageImpl<WrapForecast>(wrapForecasts, pagable, forecastIndexs.getTotalElements());
		return wrapForecastsPage;
	}

	/**
	 * 对估值结果进行包装，包装Key:mechanism,reportDate
	 */
	// @Override
	// public List<WrapForecast> wrapForecasts(Long stockId) {
	// List<WrapForecast> wrapForecasts=new ArrayList<WrapForecast>(0);
	// if(CollectionUtils.isNotEmpty(forecasts)){
	// for(Forecast forecast:forecasts){
	// String mechanism =forecast.getMechanism();
	// String reportDateStr=DateUtil.formatDate(forecast.getReportDate(), "yyyy-MM-dd");
	// WrapForecast wrapForecast=getWrapForecast(mechanism,reportDateStr,wrapForecasts);
	// wrapForecast.addForecast(forecast);
	// }
	// }
	// return wrapForecasts;
	// }
	//
	// private WrapForecast getWrapForecast(String mechanism,String reportDateStr,List<WrapForecast> wrapForecasts){
	// WrapForecast wrapForecast=null;
	// if(CollectionUtils.isNotEmpty(wrapForecasts)){
	// for(WrapForecast wf:wrapForecasts){
	// if(StringUtils.equals(wf.getMechanism(), mechanism)&&StringUtils.equals(wf.getReportDateStr(), reportDateStr)){
	// wrapForecast=wf;
	// break;
	// }
	// }
	// }
	// if(wrapForecast==null){
	// wrapForecast=new WrapForecast();
	// wrapForecast.setMechanism(mechanism);
	// wrapForecast.setReportDateStr(reportDateStr);
	// wrapForecasts.add(wrapForecast);
	// }
	// return wrapForecast;
	// }

}/** @generate-service-source@ **/
