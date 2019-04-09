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
 * DailyStat ORM 实体<br>
 * 数据库表:stock_dailyStat<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "stock_daily_stat")
public class DailyStat implements Serializable {
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

	private Long stockId = null;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	/**
	 * 说明:30天最高价<br>
	 * 属性名: topPriceOf30Day<br>
	 * 类型: Double<br>
	 * 数据库字段:top_price_of_30_day<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double topPriceOf30Day = null;

	public Double getTopPriceOf30Day() {
		return topPriceOf30Day;
	}

	public void setTopPriceOf30Day(Double topPriceOf30Day) {
		this.topPriceOf30Day = topPriceOf30Day;
	}

	/**
	 * 说明:30天最低价<br>
	 * 属性名: lowerPriceOf30Day<br>
	 * 类型: Double<br>
	 * 数据库字段:lower_price_of_30_day<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double lowPriceOf30Day = null;

	

	public Double getLowPriceOf30Day() {
		return lowPriceOf30Day;
	}

	public void setLowPriceOf30Day(Double lowPriceOf30Day) {
		this.lowPriceOf30Day = lowPriceOf30Day;
	}

	/**
	 * 说明:市盈率<br>
	 * 属性名: pe<br>
	 * 类型: Double<br>
	 * 数据库字段:pe<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double pe = null;

	public Double getPe() {
		return pe;
	}

	public void setPe(Double pe) {
		this.pe = pe;
	}

	/**
	 * 说明:最高价日期<br>
	 * 属性名: topPriceDate<br>
	 * 类型: Date<br>
	 * 数据库字段:top_price_date<br>
	 * 
	 * @author haipenge<br>
	 */

	private Date topPriceDate = null;

	public Date getTopPriceDate() {
		return topPriceDate;
	}

	public void setTopPriceDate(Date topPriceDate) {
		this.topPriceDate = topPriceDate;
	}

	/**
	 * 说明:最低价日期<br>
	 * 属性名: lowerPriceDate<br>
	 * 类型: Date<br>
	 * 数据库字段:lower_price_date<br>
	 * 
	 * @author haipenge<br>
	 */

	private Date lowPriceDate = null;

	public Date getLowPriceDate() {
		return lowPriceDate;
	}

	public void setLowPriceDate(Date lowPriceDate) {
		this.lowPriceDate = lowPriceDate;
	}

	/**
	 * 说明:所属板块<br>
	 * 属性名: categoryId<br>
	 * 类型: Long<br>
	 * 数据库字段:category_id<br>
	 * 
	 * @author haipenge<br>
	 */

	private Long categoryId = null;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 说明:动态市盈率<br>
	 * 属性名: dynamicPe<br>
	 * 类型: Double<br>
	 * 数据库字段:dynamic_pe<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double dynamicPe = null;

	public Double getDynamicPe() {
		return dynamicPe;
	}

	public void setDynamicPe(Double dynamicPe) {
		this.dynamicPe = dynamicPe;
	}
	/**
	 *市盈率TTM：波动市赢率
	 */
	private Double ttmPe=0.0D;

	public Double getTtmPe() {
		return ttmPe;
	}

	public void setTtmPe(Double ttmPe) {
		this.ttmPe = ttmPe;
	}

	/**
	 * 说明:市净率<br>
	 * 属性名: pb<br>
	 * 类型: Double<br>
	 * 数据库字段:pb<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double pb = null;

	public Double getPb() {
		return pb;
	}

	public void setPb(Double pb) {
		this.pb = pb;
	}

   /**
    * 说明:今天收盘价<br>
    * 属性名: todayPrice<br>
    * 类型: Double<br>
    * 数据库字段:today_price<br>
    * @author haipenge<br>
    */
    
	private  Double todayPrice;
	public Double getTodayPrice() {
		return todayPrice;
	}
	public void setTodayPrice(Double todayPrice) {
		this.todayPrice = todayPrice;
	}
	

	
   /**
    * 说明:股价振幅,指定周期内，默认30天<br>
    * 属性名: priceAmplitude<br>
    * 类型: Double<br>
    * 数据库字段:price_amplitude<br>
    * @author haipenge<br>
    */
    
	private  Double priceAmplitude=0D;
	public Double getPriceAmplitude() {
		return priceAmplitude;
	}
	public void setPriceAmplitude(Double priceAmplitude) {
		this.priceAmplitude = priceAmplitude;
	}
	

	
   /**
    * 说明:今日涨跌<br>
    * 属性名: todayIncreaseRate<br>
    * 类型: Double<br>
    * 数据库字段:today_increase_rate<br>
    * @author haipenge<br>
    */
    
	private  Double todayIncreaseRate;
	public Double getTodayIncreaseRate() {
		return todayIncreaseRate;
	}
	public void setTodayIncreaseRate(Double todayIncreaseRate) {
		this.todayIncreaseRate = todayIncreaseRate;
	}
	/**
	 * 市值
	 */
	private Double marketValue=0.0D;

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	
	
}/**@generate-entity-source@**/
	
