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

import com.faceye.component.stock.entity.ReportCategory;

import com.faceye.component.stock.repository.mongo.ReportCategoryRepository;
import com.faceye.component.stock.repository.mongo.customer.ReportCategoryCustomerRepository;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.component.stock.util.StockConstants;
 
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * ReportCategory 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class ReportCategoryServiceImpl extends BaseMongoServiceImpl<ReportCategory, Long, ReportCategoryRepository> implements ReportCategoryService {
	@Autowired
	private ReportCategoryCustomerRepository reportCategoryCustomerRepository = null;

	@Autowired
	public ReportCategoryServiceImpl(ReportCategoryRepository dao) {
		super(dao);
	}

	@Override
	public Page<ReportCategory> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<ReportCategory> entityPath = resolver.createPath(entityClass);
		// PathBuilder<ReportCategory> builder = new PathBuilder<ReportCategory>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.ASC, "orderIndex");
		Page<ReportCategory> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<ReportCategory>("id") {
			// })
			List<ReportCategory> items = (List) this.dao.findAll(predicate,sort);
			res = new PageImpl<ReportCategory>(items);

		}
		return res;
	}

	@Override
	public ReportCategory getReportCategoryByCode(String code) {
		return this.dao.getReportCategoryByCode(code);
	}
	
	public void initReportCategory(){
		boolean isInit=false;
		if(!isInit){
			ReportCategory cashFlow=new ReportCategory();
			cashFlow.setCode(StockConstants.REPORT_CATEGORY_CASH_FLOW_SHEET);
			cashFlow.setName("现金流量表");
			cashFlow.setOrderIndex(3);
			this.save(cashFlow);
			ReportCategory inCome=new ReportCategory();
			inCome.setCode(StockConstants.REPORT_CATEGORY_IN_COME_SHEET);
			inCome.setName("利润表");
			inCome.setOrderIndex(2);
			this.save(inCome);
			ReportCategory blance=new ReportCategory();
			blance.setCode(StockConstants.REPORT_CATEGORY_BALANCE_SHEET);
			blance.setOrderIndex(1);
			blance.setName("资产负债表");
			this.save(blance);
		}
	}
	private boolean isInit(){
		Page<ReportCategory> reportCategories=this.getPage(null, 1, 0);
		return reportCategories.getContent().size()>0;
	}

}/** @generate-service-source@ **/
