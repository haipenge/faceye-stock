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
 * 分红记录
 * BonusRecord ORM 实体<br>
 * 数据库表:stock_bonusRecord<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_bonus_record")
public class BonusRecord implements Serializable {
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
    * 说明:公告日期<br>
    * 属性名: publishDate<br>
    * 类型: Date<br>
    * 数据库字段:publish_date<br>
    * @author haipenge<br>
    */
    
	private  Date publishDate;
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	

	
   /**
    * 说明:送股<br>
    * 属性名: giveStockCount<br>
    * 类型: Double<br>
    * 数据库字段:give_stock_count<br>
    * @author haipenge<br>
    */
    
	private  Double giveStockCount=0D;
	public Double getGiveStockCount() {
		return giveStockCount;
	}
	public void setGiveStockCount(Double giveStockCount) {
		this.giveStockCount = giveStockCount;
	}
	

	
   /**
    * 说明:转增<br>
    * 属性名: increaseStockCount<br>
    * 类型: Double<br>
    * 数据库字段:increase_stock_count<br>
    * @author haipenge<br>
    */
    
	private  Double increaseStockCount=0D;
	public Double getIncreaseStockCount() {
		return increaseStockCount;
	}
	public void setIncreaseStockCount(Double increaseStockCount) {
		this.increaseStockCount = increaseStockCount;
	}
	

	
   /**
    * 说明:派息<br>
    * 属性名: dividend<br>
    * 类型: Double<br>
    * 数据库字段:dividend<br>
    * @author haipenge<br>
    */
    
	private  Double dividend=0D;
	public Double getDividend() {
		return dividend;
	}
	public void setDividend(Double dividend) {
		this.dividend = dividend;
	}
	

	
   /**
    * 说明:进度 <br>
    * 属性名: status<br>
    * 类型: Integer<br>
    * 数据库字段:status<br>
    * @author haipenge<br>
    */
    
	private  String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	
   /**
    * 说明:除权除息日<br>
    * 属性名: exDividendDate<br>
    * 类型: Date<br>
    * 数据库字段:ex_dividend_date<br>
    * @author haipenge<br>
    */
    
	private  Date exDividendDate;
	public Date getExDividendDate() {
		return exDividendDate;
	}
	public void setExDividendDate(Date exDividendDate) {
		this.exDividendDate = exDividendDate;
	}
	

	
   /**
    * 说明:股权登记日<br>
    * 属性名: equityRegistrationDate<br>
    * 类型: Date<br>
    * 数据库字段:equity_registration_date<br>
    * @author haipenge<br>
    */
    
	private  Date equityRegistrationDate;
	public Date getEquityRegistrationDate() {
		return equityRegistrationDate;
	}
	public void setEquityRegistrationDate(Date equityRegistrationDate) {
		this.equityRegistrationDate = equityRegistrationDate;
	}
	

	
   /**
    * 说明:红股上市日<br>
    * 属性名: bonusShareTradingDate<br>
    * 类型: Date<br>
    * 数据库字段:bonus_share_trading_date<br>
    * @author haipenge<br>
    */
    
	private  Date bonusShareTradingDate;
	public Date getBonusShareTradingDate() {
		return bonusShareTradingDate;
	}
	public void setBonusShareTradingDate(Date bonusShareTradingDate) {
		this.bonusShareTradingDate = bonusShareTradingDate;
	}
	
}/**@generate-entity-source@**/
	
