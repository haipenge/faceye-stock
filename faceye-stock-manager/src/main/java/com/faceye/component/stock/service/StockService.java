package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.Stock;
import com.faceye.feature.service.BaseService;

public interface StockService extends BaseService<Stock,Long>{

	
	/**
	 * 初始化股票数据
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月17日
	 */
	public void initStocks();
	
	public Stock getStockByCode(String code);
	
}/**@generate-service-source@**/
