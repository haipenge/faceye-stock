package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * Forecast ORM 实体<br>
 * 数据库表:stock_forecast<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_forecast")
public class Forecast implements Serializable {
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
    * 说明:EPS<br>
    * 属性名: eps<br>
    * 类型: Double<br>
    * 数据库字段:eps<br>
    * @author haipenge<br>
    */
    
	private  Double eps=0D;
	public Double getEps() {
		return eps;
	}
	public void setEps(Double eps) {
		this.eps = eps;
	}
	

	
   /**
    * 说明:净利润<br>
    * 属性名: profit<br>
    * 类型: Double<br>
    * 数据库字段:profit<br>
    * @author haipenge<br>
    */
    
	private  Double profit=0D;
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	

	
   /**
    * 说明:营业收入<br>
    * 属性名: income<br>
    * 类型: Double<br>
    * 数据库字段:income<br>
    * @author haipenge<br>
    */
    
	private  Double income=0D;
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	

	
   /**
    * 说明:净资产收益率<br>
    * 属性名: roe<br>
    * 类型: Double<br>
    * 数据库字段:roe<br>
    * @author haipenge<br>
    */
    
	private  Double roe=0D;
	public Double getRoe() {
		return roe;
	}
	public void setRoe(Double roe) {
		this.roe = roe;
	}
	

	
   /**
    * 说明:预测年份<br>
    * 属性名: year<br>
    * 类型: Date<br>
    * 数据库字段:year<br>
    * @author haipenge<br>
    */
    
	private  Integer year=null;
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	

	
   /**
    * 说明:报告日期<br>
    * 属性名: reportDate<br>
    * 类型: Date<br>
    * 数据库字段:report_date<br>
    * @author haipenge<br>
    */
    
	private  Date reportDate=null;
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	

	
   /**
    * 说明:研究机构<br>
    * 属性名: mechanism<br>
    * 类型: String<br>
    * 数据库字段:mechanism<br>
    * @author haipenge<br>
    */
    
	private  String mechanism="";
	public String getMechanism() {
		return mechanism;
	}
	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}
	

	
   /**
    * 说明:研究员<br>
    * 属性名: researcher<br>
    * 类型: String<br>
    * 数据库字段:researcher<br>
    * @author haipenge<br>
    */
    
	private  String researcher="";
	public String getResearcher() {
		return researcher;
	}
	public void setResearcher(String researcher) {
		this.researcher = researcher;
	}
	
	private Mechanism mechanismDef=null;
	public Mechanism getMechanismDef() {
		return mechanismDef;
	}
	public void setMechanismDef(Mechanism mechanismDef) {
		this.mechanismDef = mechanismDef;
	}
	
	
}/**@generate-entity-source@**/
	
