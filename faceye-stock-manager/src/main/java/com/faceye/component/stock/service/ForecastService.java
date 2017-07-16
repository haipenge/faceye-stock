package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.Forecast;
import com.faceye.feature.service.BaseService;
/**
 * Forecast 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ForecastService extends BaseService<Forecast,Long>{


	public Forecast getForecast(Long stockId,Integer year,String reportDate,String mechanism);
}/**@generate-service-source@**/
