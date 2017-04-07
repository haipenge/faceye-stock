package com.faceye.component.stock.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.StockRepository;
import com.faceye.component.stock.repository.mongo.customer.StockCustomerRepository;
import com.faceye.component.stock.service.CategoryService;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.faceye.feature.util.http.Http;

@Service
public class StockServiceImpl extends BaseMongoServiceImpl<Stock, Long, StockRepository> implements StockService {

	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private StockCustomerRepository stockCustomerRepository=null;

	@Autowired
	public StockServiceImpl(StockRepository dao) {
		super(dao);
	}

	@Override
	public void initStocks() {
		try {
			// String path="/work/Work/FeatureWorkSpace/feature/faceye-stock/faceye-stock-manager/src/main/resources/stock/sz-sh-a-stocks.txt";
			// BufferedReader in = new BufferedReader(new FileReader(path));
			// List<String> lines = IOUtils.readLines(in);
			// if (CollectionUtils.isNotEmpty(lines)) {
			// for (String line : lines) {
			// this.initOneLinn(line, "");
			// }
			// }
			String path = "/work/Work/FeatureWorkSpace/feature/faceye-stock/faceye-stock-manager/src/main/resources/stock/sz-a-stocks.txt";
			BufferedReader in = new BufferedReader(new FileReader(path));
			List<String> lines = IOUtils.readLines(in);
			if (CollectionUtils.isNotEmpty(lines)) {
				for (String line : lines) {
					this.initOneLinn(line, "sz");
				}
			}
			path = "/work/Work/FeatureWorkSpace/feature/faceye-stock/faceye-stock-manager/src/main/resources/stock/sh-a-stocks.txt";
			in = new BufferedReader(new FileReader(path));
			lines = IOUtils.readLines(in);
			if (CollectionUtils.isNotEmpty(lines)) {
				for (String line : lines) {
					this.initOneLinn(line, "sh");
				}
			}

		} catch (FileNotFoundException e) {
			logger.error(">>FaceYe throws Exception: --->" + e.toString());
		} catch (IOException e) {
			logger.error(">>FaceYe throws Exception: --->" + e.toString());
		}
	}

	private void initOneLinn(String line, String market) {
		if (StringUtils.isNotEmpty(line)) {

			String[] stockInfo = line.split("	");
			if (null != stockInfo && stockInfo.length == 4) {
				String code = stockInfo[1];
				String name = stockInfo[2];
				// 行业
				String business = stockInfo[3];
				Stock stock = this.dao.getStockByCode(code);
				if (stock == null) {
					stock = new Stock();
					stock.setName(name);
					stock.setCode(code);
					stock.setBusiness(business);
					stock.setMarket(market);
				} else {
					stock.setMarket(market);
				}
				this.save(stock);
				logger.debug(">>FaceYe save stock :" + code + " -> " + name);

			}
		}
	}

	/**
	 * 从和讯财经检查初始化股票 http://quote.eastmoney.com/stocklist.html
	 * 
	 * @todo
	 * @author:@haipenge haipenge@gmail.com 2015年2月17日
	 */
	private void checkStockFromHexun() {
		String url = "http://quote.eastmoney.com/stocklist.html";
		String content = Http.getInstance().get(url, "gb2312");
		if (StringUtils.isNotEmpty(content)) {
			// todo
		}
	}

	@Override
	public Stock getStockByCode(String code) {
		Stock stock = this.dao.getStockByCode(code);
		return stock;
	}

	/**
	 * 初始化股票分类
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月21日 上午10:43:44
	 */
	public boolean initStockCategory() {
		boolean res = false;
		List<Stock> stocks = this.getAll();
		for (Stock stock : stocks) {
			String categoryName = StringUtils.isNotEmpty(stock.getBusiness()) ? StringUtils.trim(stock.getBusiness()) : "Default";
			Category category = this.categoryService.getCategoryByName(categoryName);
			if (category == null) {
				category = new Category();
				category.setName(categoryName);
				this.categoryService.save(category);
			}
			stock.setCategory(category);
			this.save(stock);
		}
		res = true;
		return res;
	}

	@Override
	public Page<Stock> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<T> entityPath = resolver.createPath(entityClass);
		// PathBuilder<T> builder = new PathBuilder<T>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
//		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
//		Sort sort = this.buildSort(searchParams);
//		if (predicate != null) {
//			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
//		}
//		if(sort!=null){
//			logger.debug(">>FaceYe --> Query sort is:"+sort.toString());
//		}
//		Page<Stock> res = null;
//		if (size != 0) {
//			Pageable pageable = new PageRequest(page, size, sort);
//			res = this.dao.findAll(predicate, pageable);
//		} else {
//			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<T>("id") {
//			// })
//			List<Stock> items = (List) this.dao.findAll(predicate, sort);
//			res = new PageImpl<Stock>(items);
//		}
//		return res;
		Page<Stock> res=this.stockCustomerRepository.getPage(searchParams, page, size);
		return res;
	}

}
/** @generate-service-source@ **/
