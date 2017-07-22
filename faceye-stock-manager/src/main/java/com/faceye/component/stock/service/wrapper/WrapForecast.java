package com.faceye.component.stock.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.stock.entity.Forecast;

/**
 * 对机构估值进行包装
 * 目标：对数据按时间、机构进行分组
 * @author haipenge
 *
 */
public class WrapForecast {

	//机构
	private String mechanism="";
	//估值报告日期
	private String reportDateStr="";
	private List<Forecast> forecasts=new ArrayList<Forecast>(0);
	public String getMechanism() {
		return mechanism;
	}
	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}
	public String getReportDateStr() {
		return reportDateStr;
	}
	public void setReportDateStr(String reportDateStr) {
		this.reportDateStr = reportDateStr;
	}
	public List<Forecast> getForecasts() {
		return forecasts;
	}
	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}
	
	public void addForecast(Forecast forecast){
		this.forecasts.add(forecast);
	}
	
	
}
