package com.faceye.component.stock.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Stock;

/**
 * 数据分析结果
 * @author haipenge
 *
 */
public class StatRecord {

	private Stock stock=null;
	private List<DataStat> dataStats=new ArrayList(0);
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
	
}
