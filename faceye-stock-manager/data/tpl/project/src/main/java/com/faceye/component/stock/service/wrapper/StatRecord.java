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
		if(this.avgTotalAssetsNetProfitMargin==null){
			Double total=0.0D;
			for(DataStat dataStat:dataStats){
				total+=dataStat.getTotalAssetsNetProfitMargin();
			}
			if(total>0.0D){
				this.setAvgTotalAssetsNetProfitMargin(total/dataStats.size());
			}
		}
		this.dataStats = dataStats;
	}
	
	
	//多年平均总资产净收益率
	private Double avgTotalAssetsNetProfitMargin=null;
	public Double getAvgTotalAssetsNetProfitMargin() {
		return avgTotalAssetsNetProfitMargin;
	}
	public void setAvgTotalAssetsNetProfitMargin(Double avgTotalAssetsNetProfitMargin) {
		this.avgTotalAssetsNetProfitMargin = avgTotalAssetsNetProfitMargin;
	}
	
	
}
