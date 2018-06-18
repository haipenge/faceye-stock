package com.faceye.component.stock.service;

import java.io.OutputStream;
import java.util.Map;

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
	
	
	/**
	 * 根据条件，出出股票
	 * @param searchParams
	 */
	public void export(Map searchParams,OutputStream stream);
	
	/**
	 * 对一只股票进行超级初始化，财报，每日数据，以及相关分析
	 * @param stockId
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2018年6月18日 下午3:34:21
	 */
	public void superInitStock(Long stockId);
	
}/**@generate-service-source@**/
