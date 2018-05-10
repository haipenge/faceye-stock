package com.faceye.component.stock.service;

import java.util.Date;

import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.entity.Mechanism;
import com.faceye.feature.service.BaseService;
/**
 * ForecastIndex 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ForecastIndexService extends BaseService<ForecastIndex,Long>{

	public ForecastIndex getForecastIndexByMechanismAndReportDate(Long stockId,Mechanism mechanism,Date reportDate);
}/**@generate-service-source@**/
