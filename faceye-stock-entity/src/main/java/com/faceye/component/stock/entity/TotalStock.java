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
 * TotalStock ORM 实体<br>
 * 数据库表:stock_totalStock<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_totalStock")
public class TotalStock implements Serializable {
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
    * 说明:ID<br>
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
    * 说明:股本<br>
    * 属性名: stockNum<br>
    * 类型: Integer<br>
    * 数据库字段:stock_num<br>
    * @author haipenge<br>
    */
    
	private  Integer stockNum;
	public Integer getStockNum() {
		return stockNum;
	}
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	

	
   /**
    * 说明:动日期<br>
    * 属性名: changeDate<br>
    * 类型: Date<br>
    * 数据库字段:change_date<br>
    * @author haipenge<br>
    */
    
	private  Date changeDate;
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
}/**@generate-entity-source@**/
	
