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
	
	/**
	 * 初始化股票分类
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月21日 上午10:44:05
	 */
	public boolean initStockCategory() ;
	
}/**@generate-service-source@**/
