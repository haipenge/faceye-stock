package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.Valuation;
import com.faceye.feature.service.BaseService;
/**
 * Valuation 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ValuationService extends BaseService<Valuation,Long>{

	/**
	 * 对一只股票进行估值
	 * @param stockId
	 * @param roce:权益报酬率(0,1),如0.2%,表示本投资期望年收益率20%
	 * @param discountRate,贴现率，可选值：默认为长期国债收益率(4.5%),激进情况下使用GDP增长率
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年6月4日 下午3:49:49
	 */
	public void doStockValuation(Long stockId,Double roce,Double discountRate);
	
}/**@generate-service-source@**/
