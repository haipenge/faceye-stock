package com.faceye.component.stock.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.DataStatRepository;
import com.faceye.component.stock.repository.mongo.customer.DataStatCustomerRepository;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.ServiceException;
import com.querydsl.core.types.Predicate;

/**
 * DataStat 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */

@Service
public class DataStatServiceImpl extends BaseMongoServiceImpl<DataStat, Long, DataStatRepository> implements DataStatService {
	@Autowired
	private DataStatCustomerRepository dataStatCustomerRepository = null;
	@Autowired
	private FinancialDataService financialDataService = null;
	@Autowired
	private StockService stockService = null;

	@Autowired
	public DataStatServiceImpl(DataStatRepository dao) {
		super(dao);
	}

	@Override
	public Page<DataStat> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<DataStat> entityPath = resolver.createPath(entityClass);
		// PathBuilder<DataStat> builder = new PathBuilder<DataStat>(entityPath.getType(), entityPath.getMetadata());
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
		Page<DataStat> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<DataStat>("id") {
			// })
			List<DataStat> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<DataStat>(items);

		}
		return res;
	}

	@Override
	public void statReturnOnAssets(Stock stock) {
		if (stock != null) {
			// 获取总资产
			Map params = new HashMap();
			params.put("EQ|stockId", stock.getId());
			params.put("EQ|accountingSubjectId", StockConstants.TOTAL_ASSETS);
			List<FinancialData> totalAssets = this.financialDataService.getPage(params, 0, 0).getContent();
			// 获取净利润
			params = new HashMap();
			params.put("EQ|stockId", stock.getId());
			params.put("EQ|accountingSubjectId", StockConstants.NET_PROFIT);
			List<FinancialData> netProfit = this.financialDataService.getPage(params, 0, 0).getContent();
			if (CollectionUtils.isNotEmpty(totalAssets) && CollectionUtils.isNotEmpty(netProfit)) {
				for (FinancialData profit : netProfit) {
					String profitDate = DateUtil.formatDate(profit.getDate(), "yyyy-MM-dd");
					Double profitNum = profit.getData();
					Double assetsNum = null;
					for (FinancialData assets : totalAssets) {
						String assetsDate = DateUtil.formatDate(assets.getDate(), "yyyy-MM-dd");
						if (StringUtils.equals(profitDate, assetsDate)) {
							assetsNum = assets.getData();
							break;
						}
					}
					if (profitNum != null && assetsNum != null) {
						// @TODO 计算总资产回报率，本处存在不严谨之处：分母应为：（期初总资产+期末总资产)/2
						Double returnOnAssets = profitNum / assetsNum;
						DataStat dataStat = this.getDataStat(stock, profitDate);
						if (dataStat == null) {
							dataStat = new DataStat();
							dataStat.setStockId(stock.getId());
							dataStat.setDateCycle(DateUtil.getDateFromString(profitDate+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
							dataStat.setReturnOnAssets(returnOnAssets);
						} else {
							if (dataStat.getReturnOnAssets() != null && returnOnAssets.compareTo(dataStat.getReturnOnAssets()) == 0) {
								logger.debug(">>FaceYe --> 本次计算的总资产回报率与上次相同，股票：" + stock.getName() + "(" + stock.getCode() + ")");
							} else {
								logger.debug(">>FaceYe --> 两次计算的总资产回报率不同，股票：" + stock.getName() + "(" + stock.getCode() + ")，上次：" + dataStat.getReturnOnAssets() + ",本次："
										+ returnOnAssets);
								dataStat.setReturnOnAssets(returnOnAssets);
								dataStat.setDateCycle(DateUtil.getDateFromString(profitDate+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
							}
						}
						this.save(dataStat);
					}
				}
			} else {
				logger.error(">>FaceYe :总资产集合与净利润集合不对等,不可进行总资产回报率计算");
			}
		}
	}

	/**
	 * 数据分析结果是否存在
	 * 
	 * @param stock
	 * @param date
	 * @param key
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月22日 下午4:25:20
	 */
	private DataStat getDataStat(Stock stock, String date) {
		DataStat dataStat = null;
		Map params = new HashMap();
		params.put("EQ|stockId", stock.getId());
		params.put("GTE|dateCycle", DateUtil.getDateFromString(date + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		params.put("LTE|dateCycle", DateUtil.getDateFromString(date + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		List<DataStat> dataStats = this.getPage(params, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(dataStats)) {
			dataStat = dataStats.get(0);
		}
		return dataStat;
	}

	@Override
	public void stat() {
		List<Stock> stocks = this.stockService.getAll();
		for (Stock stock : stocks) {
			// 分析总资产回报率
			this.stat(stock);
		}
	}

	@Override
	public void stat(Stock stock) {
		try {
			this.statReturnOnAssets(stock);
		} catch (Exception e) {
			logger.error(">>Faceye --> 分析股票总资产回报率抛出异常:", e);
		}
	}

}/** @generate-service-source@ **/
