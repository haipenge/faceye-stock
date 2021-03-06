package com.faceye.component.stock.service;

import java.util.List;

import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.service.wrapper.WrapReporter;
import com.faceye.feature.service.BaseService;
/**
 * AccountingElement 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ReportDataService extends BaseService<ReportData,Long>{

	/**
	 * 包装报表数据,以供页面展现
	 * @param reportDatas
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月14日 下午5:46:39
	 */
	public WrapReporter wrapReportData(List<ReportData> reportDatas,String categoryName);
	
	/**
	 * 清理已爬取、计算的报告
	 * @param stockId
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月23日 下午3:35:28
	 */
	public void clearReportData(Long stockId);
	
	
}/**@generate-service-source@**/
