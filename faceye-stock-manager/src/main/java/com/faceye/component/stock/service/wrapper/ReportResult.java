package com.faceye.component.stock.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Stock;

/**
 * 财务报告及分析结果最终包装对像
 * @author haipenge
 *
 */
public class ReportResult {
  
	private Stock stock=null;
	private List<DataStat> dataStats=new ArrayList<DataStat>(0);
	private WrapReporter wrapReporter=null;
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public List<DataStat> getDataStats() {
		return dataStats;
	}
	public void setDataStats(List<DataStat> dataStats) {
		this.dataStats = dataStats;
	}
	public WrapReporter getWrapReporter() {
		return wrapReporter;
	}
	public void setWrapReporter(WrapReporter wrapReporter) {
		this.wrapReporter = wrapReporter;
	}
	
}
