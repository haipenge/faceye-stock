package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.service.wrapper.FinancialReportWrapper;
import com.faceye.feature.service.BaseService;
/**
 * FinancialData 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface FinancialDataService extends BaseService<FinancialData,Long>{

	/**
	 * 取得会计报表
	 * @param stockId
	 * @param date
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月30日 下午3:44:36
	 */
	public FinancialReportWrapper getFinancialReportWrapper(Long stockId,String date);
}/**@generate-service-source@**/
