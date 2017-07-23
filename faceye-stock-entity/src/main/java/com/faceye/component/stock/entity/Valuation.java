package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * Valuation ORM 实体<br>
 * 数据库表:stock_valuation<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_valuation")
public class Valuation implements Serializable {
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
    * 说明:股票ID <br>
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
    * 说明:价值<br>
    * 属性名: totalValue<br>
    * 类型: Double<br>
    * 数据库字段:total_value<br>
    * @author haipenge<br>
    */
    
	private  Double totalValue;
	public Double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}
	

	
   /**
    * 说明:权益报酬率<br>
    * 属性名: roce<br>
    * 类型: Double<br>
    * 数据库字段:roce<br>
    * @author haipenge<br>
    */
    
	private  Double roce;
	public Double getRoce() {
		return roce;
	}
	public void setRoce(Double roce) {
		this.roce = roce;
	}
	
	

	
   /**
    * 说明:贴现率<br>
    * 属性名: discountRate<br>
    * 类型: Double<br>
    * 数据库字段:discount_rate<br>
    * @author haipenge<br>
    */
    
	private  Double discountRate;
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	

	
   /**
    * 说明:估值周期<br>
    * 属性名: period<br>
    * 类型: Date<br>
    * 数据库字段:period<br>
    * @author haipenge<br>
    */
    
	private  Date period;
	public Date getPeriod() {
		return period;
	}
	public void setPeriod(Date period) {
		this.period = period;
	}
	/**
	 * 机构估值
	 */
	@DBRef
	private ForecastIndex forecastIndex=null;
	public ForecastIndex getForecastIndex() {
		return forecastIndex;
	}
	public void setForecastIndex(ForecastIndex forecastIndex) {
		this.forecastIndex = forecastIndex;
	}
	
	/**
	 * 预测BPS
	 */
	private List<Double> xbps=new ArrayList<Double>(0);
	
	
	public List<Double> getXbps() {
		return xbps;
	}
	public void setXbps(List<Double> xbps) {
		this.xbps = xbps;
	}
	/**
	 * 股利
	 */
	private List<Double> xdps=new ArrayList<Double>(0);
	public List<Double> getXdps() {
		return xdps;
	}
	public void setXdps(List<Double> xdps) {
		this.xdps = xdps;
	}
	
	/**
	 * 股东权益回报率
	 */
	private List<Double> xroces=new ArrayList<Double>(0);
	public List<Double> getXroces() {
		return xroces;
	}
	public void setXroces(List<Double> xroces) {
		this.xroces = xroces;
	}
	
	/**
	 * 剩余价值
	 */
	private List<Double> xres=new ArrayList<Double>(0);
	public List<Double> getXres() {
		return xres;
	}
	public void setXres(List<Double> xres) {
		this.xres = xres;
	}
	/**
	 * 剩余价值的现值
	 */
	private List<Double> xvres=new ArrayList<Double>(0);
	
	
	public List<Double> getXvres() {
		return xvres;
	}
	public void setXvres(List<Double> xvres) {
		this.xvres = xvres;
	}
	/**
	 * 持续价值
	 */
	private Double cv=0D;
	public Double getCv() {
		return cv;
	}
	public void setCv(Double cv) {
		this.cv = cv;
	}
	
	
}/**@generate-entity-source@**/
	
