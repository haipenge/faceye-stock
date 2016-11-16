package com.faceye.component.stock.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.StockRepository;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.http.Http;

@Service
public class StockServiceImpl extends BaseMongoServiceImpl<Stock, Long, StockRepository> implements StockService {

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
	 * 从和讯财经检查初始化股票
	 * http://quote.eastmoney.com/stocklist.html
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月17日
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
		Stock stock=this.dao.getStockByCode(code);
		return stock;
	}

}
/**@generate-service-source@**/
