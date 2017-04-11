package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * StarDataStat ORM 实体<br>
 * 数据库表:stock_starDataStat<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_star_data_stat")
public class StarDataStat implements Serializable {
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
    @Indexed
	private  Long stockId;
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	

	
   
	

	
   /**
    * 说明:5日涨幅<br>
    * 属性名: max5DayIncreaseRate<br>
    * 类型: Double<br>
    * 数据库字段:max_5_day_increase_rate<br>
    * @author haipenge<br>
    */
    
	private  Double max5DayIncreaseRate;
	public Double getMax5DayIncreaseRate() {
		return max5DayIncreaseRate;
	}
	public void setMax5DayIncreaseRate(Double max5DayIncreaseRate) {
		this.max5DayIncreaseRate = max5DayIncreaseRate;
	}
	

	
   /**
    * 说明:10日涨幅<br>
    * 属性名: max10DayIncreaseRate<br>
    * 类型: Double<br>
    * 数据库字段:max_10_day_increase_rate<br>
    * @author haipenge<br>
    */
    
	private  Double max10DayIncreaseRate;
	public Double getMax10DayIncreaseRate() {
		return max10DayIncreaseRate;
	}
	public void setMax10DayIncreaseRate(Double max10DayIncreaseRate) {
		this.max10DayIncreaseRate = max10DayIncreaseRate;
	}
	

	
   /**
    * 说明:20日涨幅<br>
    * 属性名: max20DayIncreaseRate<br>
    * 类型: Double<br>
    * 数据库字段:max_20_day_increase_rate<br>
    * @author haipenge<br>
    */
    
	private  Double max20DayIncreaseRate;
	public Double getMax20DayIncreaseRate() {
		return max20DayIncreaseRate;
	}
	public void setMax20DayIncreaseRate(Double max20DayIncreaseRate) {
		this.max20DayIncreaseRate = max20DayIncreaseRate;
	}
	

	
   /**
    * 说明:30日涨幅<br>
    * 属性名: max30DayIncreaseRate<br>
    * 类型: Double<br>
    * 数据库字段:max_30_day_increase_rate<br>
    * @author haipenge<br>
    */
    
	private  Double max30DayIncreaseRate;
	public Double getMax30DayIncreaseRate() {
		return max30DayIncreaseRate;
	}
	public void setMax30DayIncreaseRate(Double max30DayIncreaseRate) {
		this.max30DayIncreaseRate = max30DayIncreaseRate;
	}
	

	
   /**
    * 说明:60日涨幅<br>
    * 属性名: max60DayIncreaseRate<br>
    * 类型: Double<br>
    * 数据库字段:max_60_day_increase_rate<br>
    * @author haipenge<br>
    */
    
	private  Double max60DayIncreaseRate;
	public Double getMax60DayIncreaseRate() {
		return max60DayIncreaseRate;
	}
	public void setMax60DayIncreaseRate(Double max60DayIncreaseRate) {
		this.max60DayIncreaseRate = max60DayIncreaseRate;
	}
	

	
   /**
    * 说明:星标数据日期<br>
    * 属性名: starDataDate<br>
    * 类型: Date<br>
    * 数据库字段:star_data_date<br>
    * @author haipenge<br>
    */
    
	private  Date starDataDate=null;
	public Date getStarDataDate() {
		return starDataDate;
	}
	public void setStarDataDate(Date starDataDate) {
		this.starDataDate = starDataDate;
	}
	

	
   /**
    * 说明:星标数据ID<br>
    * 属性名: starDailyDataId<br>
    * 类型: Long<br>
    * 数据库字段:star_daily_data_id<br>
    * @author haipenge<br>
    */
    
	private  Long starDailyDataId;
	public Long getStarDailyDataId() {
		return starDailyDataId;
	}
	public void setStarDailyDataId(Long starDailyDataId) {
		this.starDailyDataId = starDailyDataId;
	}
	
}/**@generate-entity-source@**/
	
