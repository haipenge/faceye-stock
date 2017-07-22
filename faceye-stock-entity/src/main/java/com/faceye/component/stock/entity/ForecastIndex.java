package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * ForecastIndex ORM 实体<br>
 * 数据库表:stock_forecastIndex<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_forecast_index")
public class ForecastIndex implements Serializable {
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
    * 说明:报告日期<br>
    * 属性名: reportDate<br>
    * 类型: Date<br>
    * 数据库字段:report_date<br>
    * @author haipenge<br>
    */
    
	private  Date reportDate;
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	

	
   /**
    * 说明:机构<br>
    * 属性名: mechanism<br>
    * 类型: Mechanism<br>
    * 数据库字段:mechanism<br>
    * @author haipenge<br>
    */
	@DBRef
	private  Mechanism mechanism;
	public Mechanism getMechanism() {
		return mechanism;
	}
	public void setMechanism(Mechanism mechanism) {
		this.mechanism = mechanism;
	}
	
	private Long stockId=null;
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	
	
}/**@generate-entity-source@**/
	
