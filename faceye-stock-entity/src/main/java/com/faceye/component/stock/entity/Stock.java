package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Stock ORM 实体 数据库表:stock_stock
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月21日
 */
@Document(collection = "stock_stock")
public class Stock implements Serializable {

	/**
	 * 
	 */
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
	 * 说明:代码 属性名: code 类型: String 数据库字段:code
	 * 
	 * @author haipenge
	 */

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 说明:名称 属性名: name 类型: String 数据库字段:name
	 * 
	 * @author haipenge
	 */

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:行业 属性名: business 类型: String 数据库字段:business
	 * 
	 * @author haipenge
	 */

	private String business;

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	/**
	 * 说明:市场(上海，深圳) 属性名: market 类型: String 数据库字段:market
	 * 
	 * @author haipenge
	 */

	private String market;

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	@DBRef
	private Category category = null;

	public Category getCategory() {
		return category;
	}
	
	/**
	 * 所属概念板块
	 */
	private List<Category> categories=null;
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	/**
	 * 每日数据分析
	 */
	private DailyStat dailyStat=null;

	public DailyStat getDailyStat() {
		return dailyStat;
	}

	public void setDailyStat(DailyStat dailyStat) {
		this.dailyStat = dailyStat;
	}
	/**
	 * 最近年报分析数据
	 */
	private DataStat dataStat=null;
	
	public DataStat getDataStat() {
		return dataStat;
	}

	public void setDataStat(DataStat dataStat) {
		this.dataStat = dataStat;
	}

	/**
	 * 最后一个星标出现的时间（依据均线）
	 */
	private Date lastStarAppearDate=null;

	public Date getLastStarAppearDate() {
		return lastStarAppearDate;
	}

	public void setLastStarAppearDate(Date lastStarAppearDate) {
		this.lastStarAppearDate = lastStarAppearDate;
	}
	/**
	 * 是否已退市
	 */
	private Boolean isExistMarket=Boolean.FALSE;

	public Boolean getIsExistMarket() {
		return isExistMarket;
	}

	public void setIsExistMarket(Boolean isExistMarket) {
		this.isExistMarket = isExistMarket;
	}
	

}/** @generate-entity-source@ **/
