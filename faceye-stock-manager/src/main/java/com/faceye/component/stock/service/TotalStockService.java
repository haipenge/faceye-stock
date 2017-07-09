package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.TotalStock;
import com.faceye.feature.service.BaseService;
/**
 * TotalStock 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface TotalStockService extends BaseService<TotalStock,Long>{

	public boolean isTotalStockExist(Long stockId,String changeDate);
}/**@generate-service-source@**/
