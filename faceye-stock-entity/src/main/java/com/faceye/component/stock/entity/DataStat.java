package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * DataStat ORM 实体<br>
 * 数据库表:stock_dataStat<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "stock_data_stat")
@CompoundIndexes({ @CompoundIndex(name = "data_stat_index", def = "{stockId : 1, type : 1}") })
public class DataStat implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 说明:创建日期<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:create_date<br>
	 * 
	 * @author haipenge<br>
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
	private Date createDate = new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 说明:股票ID<br>
	 * 属性名: stockId<br>
	 * 类型: Long<br>
	 * 数据库字段:stock_id<br>
	 * 
	 * @author haipenge<br>
	 */

	private Long stockId;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	/**
	 * 说明:总资产回报率 <br>
	 * 属性名: returnOnAssets<br>
	 * 类型: Double<br>
	 * 数据库字段:return_on_assets<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double returnOnAssets = null;

	public Double getReturnOnAssets() {
		return returnOnAssets;
	}

	public void setReturnOnAssets(Double returnOnAssets) {
		this.returnOnAssets = returnOnAssets;
	}

	/**
	 * 说明:数据 日期<br>
	 * 属性名: dateCycle<br>
	 * 类型: Date<br>
	 * 数据库字段:date_cycle<br>
	 * 
	 * @author haipenge<br>
	 */

	private Date dateCycle = null;

	public Date getDateCycle() {
		return dateCycle;
	}

	public void setDateCycle(Date dateCycle) {
		if(dateCycle!=null){
			if(dateCycle.getMonth()==11){
				this.setType(0);
			}
			if(dateCycle.getMonth()==8){
				this.setType(3);
			}
			if(dateCycle.getMonth()==5){
				this.setType(2);
			}
			if(dateCycle.getMonth()==2){
				this.setType(1);
			}
		}
		this.dateCycle = dateCycle;
	}

	/**
	 * 报告类型
	 */
	private Integer type = 0;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 说明:毛利率<br>
	 * 属性名: grossProfitMargin<br>
	 * 类型: Double<br>
	 * 数据库字段:gross_profit_margin<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double grossProfitMargin = null;

	public Double getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(Double grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}

	/**
	 * 说明:净利率<br>
	 * 属性名: netProfitMargin<br>
	 * 类型: Double<br>
	 * 数据库字段:net_profit_margin<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double netProfitMargin = null;

	public Double getNetProfitMargin() {
		return netProfitMargin;
	}

	public void setNetProfitMargin(Double netProfitMargin) {
		this.netProfitMargin = netProfitMargin;
	}

	/**
	 * 说明:资产周围率<br>
	 * 属性名: totalAssetsTurnover<br>
	 * 类型: Double<br>
	 * 数据库字段:total_assets_turn_over<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double totalAssetsTurnover;

	public Double getTotalAssetsTurnover() {
		return totalAssetsTurnover;
	}

	public void setTotalAssetsTurnover(Double totalAssetsTurnover) {
		this.totalAssetsTurnover = totalAssetsTurnover;
	}

	/**
	 * 说明:总资产净利率<br>
	 * 属性名: totalAssetsNetProfitMargin<br>
	 * 类型: Double<br>
	 * 数据库字段:total_assets_netprofit_margin<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double totalAssetsNetProfitMargin;

	public Double getTotalAssetsNetProfitMargin() {
		return totalAssetsNetProfitMargin;
	}

	public void setTotalAssetsNetProfitMargin(Double totalAssetsNetProfitMargin) {
		this.totalAssetsNetProfitMargin = totalAssetsNetProfitMargin;
	}

	/**
	 * 说明:资产负债率<br>
	 * 属性名: debtToAssetsRatio<br>
	 * 类型: Double<br>
	 * 数据库字段:debt_to_assets_ratio<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double debtToAssetsRatio;

	public Double getDebtToAssetsRatio() {
		return debtToAssetsRatio;
	}

	public void setDebtToAssetsRatio(Double debtToAssetsRatio) {
		this.debtToAssetsRatio = debtToAssetsRatio;
	}

	/**
	 * 说明:净资产收益 率<br>
	 * 属性名: roe<br>
	 * 类型: Double<br>
	 * 数据库字段:roe<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double roe;

	public Double getRoe() {
		return roe;
	}

	public void setRoe(Double roe) {
		this.roe = roe;
	}

}/** @generate-entity-source@ **/
