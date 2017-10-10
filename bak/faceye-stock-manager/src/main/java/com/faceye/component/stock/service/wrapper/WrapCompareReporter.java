package com.faceye.component.stock.service.wrapper;

import java.util.List;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Stock;

/**
 * 包装报表比对数据
 * @author haipenge
 *
 */
public class WrapCompareReporter {

	private Stock stock=null;
	private WrapReporter wrapReporter=null;
	private List<DataStat> dataStats=null;
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public WrapReporter getWrapReporter() {
		return wrapReporter;
	}
	public void setWrapReporter(WrapReporter wrapReporter) {
		this.wrapReporter = wrapReporter;
	}
	public List<DataStat> getDataStats() {
		return dataStats;
	}
	public void setDataStats(List<DataStat> dataStats) {
		this.dataStats = dataStats;
	}
	
	
}
