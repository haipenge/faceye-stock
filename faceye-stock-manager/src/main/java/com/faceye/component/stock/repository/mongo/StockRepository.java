package com.faceye.component.stock.repository.mongo;

import com.faceye.component.stock.entity.Stock;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Stock 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
public interface StockRepository extends BaseMongoRepository<Stock,Long> {
	
	public Stock getStockByCode(String code);
}/**@generate-repository-source@**/
