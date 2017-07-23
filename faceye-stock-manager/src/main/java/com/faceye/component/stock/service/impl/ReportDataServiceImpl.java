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
import com.faceye.component.stock.repository.mongo.customer.DataStatCustomerRepository;
import com.faceye.component.stock.repository.mongo.customer.ReportDataCustomerRepository;
import com.faceye.component.stock.service.AccountingElementService;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.wrapper.Data2Record;
import com.faceye.component.stock.service.wrapper.WrapRecords;
import com.faceye.component.stock.service.wrapper.WrapReporter;
import com.faceye.component.stock.service.wrapper.WrapReporterTitle;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
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
	private DataStatCustomerRepository dataStatCustomerRepository=null;

	@Autowired
	public ReportDataServiceImpl(ReportDataRepository dao) {
		super(dao);
	}

	@Override
	public Page<ReportData> getPage(Map<String, Object> searchParams, int page, int size)  {
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
			List<ReportData> items = (List) this.dao.findAll(predicate,sort);
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
		wrapReporter.setReportCategory(reportCategory);
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
						data2Record.setAccountingElementId(accountingElement.getId());
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
		// 同型分析
		this.commonSizeAnalysis(wrapReporter);
		//趋势分析
		this.trendAnalysis(wrapReporter);
		return wrapReporter;
	}

	/**
	 * 对结果进行同型分析
	 * 
	 * @param wrapReporter
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月20日 下午3:14:04
	 */
	private void commonSizeAnalysis(WrapReporter wrapReporter) {
		Double base = 0.0D;
		Long seedId = 0L;
		if (wrapReporter.getReportCategory().getCode().equals(StockConstants.REPORT_CATEGORY_BALANCE_SHEET)) {
			// 资产负债表
			seedId = StockConstants.TOTAL_ASSETS;// 总资产
		} else if (wrapReporter.getReportCategory().getCode().equals(StockConstants.REPORT_CATEGORY_CASH_FLOW_SHEET)) {
			// 现金流量表

		} else if (wrapReporter.getReportCategory().getCode().equals(StockConstants.REPORT_CATEGORY_IN_COME_SHEET)) {
			// 利润表
			seedId = StockConstants.OPERATING_INCOME;// 营业收入
		}
		if (seedId.intValue() != 0) {
			for (WrapRecords wrapRecords : wrapReporter.getRecords()) {
				Date date = wrapRecords.getDate();
				for (Data2Record data2Record : wrapRecords.getData2Record()) {
					if (data2Record.getAccountingSubjectId().compareTo(seedId) == 0) {
						base = data2Record.getData();
						break;
					}
				}
				if (base!=null &&base > 0) {
					for (Data2Record data2Record : wrapRecords.getData2Record()) {
						if (data2Record.getData() != null) {
							Double commonSizeAnalysisResult = data2Record.getData() / base;
							data2Record.setCommonSizeAnalysisResult(commonSizeAnalysisResult);
						}
					}
				}

			}
		}
	}

	/**
	 * 趋势分析
	 * 
	 * @param wrapReporter
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月20日 下午4:40:47
	 */
	private void trendAnalysis(WrapReporter wrapReporter) {
		for (int i = 0; i < wrapReporter.getRecords().size(); i++) {
			WrapRecords wrapRecords = wrapReporter.getRecords().get(i);
			WrapRecords lastYearWrapRecord=null;
			if(i<wrapReporter.getRecords().size()-1){
				int index=i+1;
				lastYearWrapRecord=wrapReporter.getRecords().get(index);
			}
			for (int j = 0; j < wrapRecords.getData2Record().size(); j++) {
				Data2Record data2Record = wrapRecords.getData2Record().get(j);
				Data2Record lastYearData2Record=null;
				if(lastYearWrapRecord!=null){
					lastYearData2Record=lastYearWrapRecord.getData2Record().get(j);
				}
				if(lastYearData2Record!=null && data2Record.getData()!=null && lastYearData2Record.getData()!=null){
					Double trendAnalysisResult=0.0D;	
					trendAnalysisResult=(data2Record.getData()-lastYearData2Record.getData())/Math.abs(lastYearData2Record.getData());
					data2Record.setTrendAnalysisResult(trendAnalysisResult);
				}
			}
		}
	}

	@Override
	public void clearReportData(Long stockId) {
		this.reportDataRepositoryCustomerRepository.removeReportData(stockId);
		this.dataStatCustomerRepository.removeDataStat(stockId);
	}

}/** @generate-service-source@ **/
