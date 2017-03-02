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
/**
 * Stock ORM 实体
 * 数据库表:stock_stock
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月21日
 */
@Document(collection="stock_stock")
public class Stock implements Serializable {

	/**
	 * 
	 */
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
    * 说明:代码
    * 属性名: code
    * 类型: String
    * 数据库字段:code
    * @author haipenge
    */
    
	private  String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

	
   /**
    * 说明:名称
    * 属性名: name
    * 类型: String
    * 数据库字段:name
    * @author haipenge
    */
    
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

	
   /**
    * 说明:行业
    * 属性名: business
    * 类型: String
    * 数据库字段:business
    * @author haipenge
    */
    
	private  String business;
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	

	
   /**
    * 说明:市场(上海，深圳)
    * 属性名: market
    * 类型: String
    * 数据库字段:market
    * @author haipenge
    */
    
	private  String market;
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	
	
	
}/**@generate-entity-source@**/
	
