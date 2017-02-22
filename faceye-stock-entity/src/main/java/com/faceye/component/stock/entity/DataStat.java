package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * DataStat ORM 实体<br>
 * 数据库表:stock_dataStat<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_data_stat")
public class DataStat implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private  Long id=null;
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
	 * @author haipenge<br>
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd hh24:mi:ss")
	private Date createDate=new Date();

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
    * @author haipenge<br>
    */
    
	private  Long stockId;
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
    * @author haipenge<br>
    */
    
	private  Double returnOnAssets=null;
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
    * @author haipenge<br>
    */
    
	private  Date dateCycle=null;
	public Date getDateCycle() {
		return dateCycle;
	}
	public void setDateCycle(Date dateCycle) {
		this.dateCycle = dateCycle;
	}
	

	
   /**
    * 说明:毛利率<br>
    * 属性名: grossProfitMargin<br>
    * 类型: Double<br>
    * 数据库字段:gross_profit_margin<br>
    * @author haipenge<br>
    */
    
	private  Double grossProfitMargin;
	public Double getGrossProfitMargin() {
		return grossProfitMargin;
	}
	public void setGrossProfitMargin(Double grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}
	
}/**@generate-entity-source@**/
	
