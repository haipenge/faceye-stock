package com.faceye.component.stock.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.entity.AccountingSubject;
import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.repository.mongo.ReportDataRepository;
import com.faceye.component.stock.repository.mongo.customer.ReportDataCustomerRepository;
import com.faceye.component.stock.service.AccountingElementService;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.wrapper.Data2Record;
import com.faceye.component.stock.service.wrapper.WrapRecords;
import com.faceye.component.stock.service.wrapper.WrapReporter;
import com.faceye.component.stock.service.wrapper.WrapReporterTitle;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

/**
 * AccountingElement 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class ReportDataServiceImpl extends BaseMongoServiceImpl<ReportData, Long, ReportDataRepository> implements ReportDataService {
	@Autowired
	private ReportDataCustomerRepository reportDataRepositoryCustomerRepository = null;
	@Autowired
	private AccountingSubjectService accountingSubjectService = null;
	@Autowired
	private AccountingElementService accountingElementService = null;
	@Autowired
	private ReportCategoryService reportCategorySerivce = null;

	@Autowired
	public ReportDataServiceImpl(ReportDataRepository dao) {
		super(dao);
	}

	@Override
	public Page<ReportData> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<AccountingElement> entityPath = resolver.createPath(entityClass);
		// PathBuilder<AccountingElement> builder = new PathBuilder<AccountingElement>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = this.buildSort(searchParams);
		if (sort == null) {
			sort = new Sort(Direction.DESC, "id");
		}
		Page<ReportData> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<AccountingElement>("id") {
			// })
			List<ReportData> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<ReportData>(items);

		}
		return res;
	}

	@Override
	public WrapReporter wrapReportData(List<ReportData> reportDatas, String categoryName) {
		WrapReporter wrapReporter = new WrapReporter();
		ReportCategory reportCategory = null;
		String categoryPropertyName = "";
		List<ReportCategory> reportCategories = this.reportCategorySerivce.getAll();
		for (ReportCategory rc : reportCategories) {
			if (rc.getCode().toLowerCase().replaceAll("_", "").equals(categoryName.toLowerCase().replaceAll("_", ""))) {
				reportCategory = rc;
				break;
			}
		}
		String cArray[] = reportCategory.getCode().split("_");
		categoryPropertyName = cArray[0].toLowerCase();
		for (int i = 1; i < cArray.length; i++) {
			categoryPropertyName += cArray[i].charAt(0);
			categoryPropertyName += cArray[i].toLowerCase().substring(1, cArray[i].toLowerCase().length());
		}
		Map params = new HashMap();
		params.put("EQ|reportCategory.$id", reportCategory.getId());
		List<AccountingElement> accountingElements = this.accountingElementService.getPage(params, 0, 0).getContent();
		// 制作表头
		for (AccountingElement accountingElement : accountingElements) {
			WrapReporterTitle title = new WrapReporterTitle();
			title.setAccountingElement(accountingElement);
			params = new HashMap();
			params.put("EQ|accountingElement.$id", accountingElement.getId());
			List<AccountingSubject> accountingSubjects = this.accountingSubjectService.getPage(params, 0, 0).getContent();
			title.setAccountingSubjects(accountingSubjects);
			wrapReporter.getTitles().add(title);
		}
		for (ReportData reportData : reportDatas) {
			try {
				Object reportCategoryObj = PropertyUtils.getNestedProperty(reportData, categoryPropertyName);
				Date date = reportData.getDate();
				WrapRecords wrapRecords = new WrapRecords();
				wrapRecords.setDate(date);
				List<Data2Record> data2Records = new ArrayList<Data2Record>(0);
				wrapRecords.setData2Record(data2Records);
				wrapReporter.getRecords().add(wrapRecords);
				for (WrapReporterTitle title : wrapReporter.getTitles()) {
					AccountingElement accountingElement = title.getAccountingElement();
					String elPropertyName = "ele" + accountingElement.getId();
					Object elObj = PropertyUtils.getNestedProperty(reportCategoryObj, elPropertyName);
					for (AccountingSubject accountingSubject : title.getAccountingSubjects()) {
						Double data = (Double) PropertyUtils.getProperty(elObj, accountingSubject.getCode() + "_" + accountingSubject.getId());
						Data2Record data2Record = new Data2Record();
						data2Record.setAccountingSubjectId(accountingSubject.getId());
						data2Record.setData(data);
						data2Records.add(data2Record);
					}
				}
			} catch (IllegalAccessException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			} catch (InvocationTargetException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			} catch (NoSuchMethodException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
		return wrapReporter;
	}

}/** @generate-service-source@ **/
