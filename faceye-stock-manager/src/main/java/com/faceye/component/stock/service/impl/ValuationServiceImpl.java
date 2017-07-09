package com.faceye.component.stock.service.impl;

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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Valuation;
import com.faceye.component.stock.repository.mongo.ValuationRepository;
import com.faceye.component.stock.repository.mongo.customer.ValuationCustomerRepository;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.ValuationService;
import com.faceye.component.stock.util.Params;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

/**
 * Valuation 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class ValuationServiceImpl extends BaseMongoServiceImpl<Valuation, Long, ValuationRepository> implements ValuationService {
	@Autowired
	private ValuationCustomerRepository valuationCustomerRepository = null;
	@Autowired
	private StockService stockService = null;
	@Autowired
	private ReportDataService reportDataService = null;

	@Autowired
	public ValuationServiceImpl(ValuationRepository dao) {
		super(dao);
	}

	@Override
	public Page<Valuation> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Valuation> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Valuation> builder = new PathBuilder<Valuation>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Valuation> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Valuation>("id") {
			// })
			List<Valuation> items = (List) this.dao.findAll(predicate, sort);
			res = new PageImpl<Valuation>(items);

		}
		return res;
	}

	/**
	 * 
	 * 使用剩余收益模型进行股票估值
	 */
	@Override
	public void doStockValuation(Long stockId, Double roce, Double discountRate) {
		Map searchParams = new HashMap();
		searchParams.put("SORT|date", "asc");
		searchParams.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
		List<ReportData> reportDatas = this.reportDataService.getPage(searchParams, 1, 0).getContent();
		// 普通股权益报酬率
		if (roce == null) {
			roce = 0.2D;
		}
		// 贴现率,GDP增长率
		if (discountRate == null) {
			discountRate = 0.045;
		}
		for (int i = 0; i < reportDatas.size(); i++) {
			ReportData reportData = reportDatas.get(i);
			Valuation valuation = this.getValuationByPeriod(stockId, reportData.getDate());
			if (valuation == null) {
				logger.debug(">>FaceYe valuation is null." + DateUtil.formatDate(reportData.getDate()));
				valuation = new Valuation();
			}
			// valuation = valuation == null ? new Valuation() : valuation;
			int count = 1;
			Double totalValue = 0D;
			// 股东权益初值
			Double bookValue0 = reportData.getBalanceSheet().getEle17().getCbsheet86_243();
			if (bookValue0 == null) {
				continue;
			}
			Double lastPeriodBookValue = bookValue0;
			totalValue += bookValue0;
			for (int j = i + 1; j < reportDatas.size(); j++) {
				if (count <= 5) {
					ReportData nextReportData = reportDatas.get(j);
					Double nextBookValue = nextReportData.getBalanceSheet().getEle17().getCbsheet86_243();
					// 开始计算剩余价值收益
					// 普通股权益报酬
					Double roceValue = lastPeriodBookValue * roce;
					// 净利润
					Double netProfit = nextReportData.getInComeSheet().getEle9().getCinst24_128();
					// 剩余收益（超额收益）
					Double re = roceValue - netProfit;
					// 剩余收益的折现
					Double discountRe = re / Math.pow((1 + discountRate), count);
					totalValue += discountRe;
					lastPeriodBookValue = nextBookValue;
					count++;
					if (count == 6) {
						// 计算持续价值
						Double vc = 0D;
						vc = re / discountRate;
						vc = vc / Math.pow((1 + discountRate), count - 1);
						totalValue += vc;
					}
				} else {
					break;
				}
				if (count == 6) {
					valuation.setPeriod(reportData.getDate());
					valuation.setCreateDate(new Date());
					valuation.setDiscountRate(discountRate);
					valuation.setRoce(roce);
					valuation.setStockId(stockId);
					valuation.setTotalValue(totalValue);
					this.save(valuation);
				}
			}
		}
	}

	/**
	 * 根据时间，取得某一期的估值
	 * 
	 * @param period
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年6月4日 上午9:46:21
	 */
	private Valuation getValuationByPeriod(Long stockId, Date period) {
		Valuation valuation = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|stockId", stockId);
		String startDate = DateUtil.formatDate(period, "yyyy-MM-dd") + " 00:00:00";
		String endDate = DateUtil.formatDate(period, "yyyy-MM-dd") + " 23:59:59";
		searchParams.put("GTE|period", DateUtil.getDateFromString(startDate));
		searchParams.put("LTE|period", DateUtil.getDateFromString(endDate));
		List<Valuation> valuations = this.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(valuations)) {
			valuation = valuations.get(0);
		}
		return valuation;
	}

}/** @generate-service-source@ **/
