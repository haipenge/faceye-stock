package com.faceye.component.stock.repository.mongo;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.gen.StockGenRepository;
/**
 * Stock 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
public interface StockRepository extends StockGenRepository {
	
	public Stock getStockByCode(String code);
}/**@generate-repository-source@**/
