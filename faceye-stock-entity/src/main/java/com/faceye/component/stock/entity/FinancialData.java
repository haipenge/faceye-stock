package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * FinancialData ORM 实体<br>
 * 数据库表:stock_financialData<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "stock_financial_data")
public class FinancialData implements Serializable {
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

	private Long stockId;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	/**
	 * 说明:会计科目ID<br>
	 * 属性名: accountingSubjectId<br>
	 * 类型: Long<br>
	 * 数据库字段:account_subject_id<br>
	 * 
	 * @author haipenge<br>
	 */

	private Long accountingSubjectId;

	public Long getAccountingSubjectId() {
		return accountingSubjectId;
	}

	public void setAccountingSubjectId(Long accountingSubjectId) {
		this.accountingSubjectId = accountingSubjectId;
	}

	/**
	 * 会计科目分类ID->AccountingElement
	 */
	private Long accountingElementId;

	public Long getAccountingElementId() {
		return accountingElementId;
	}

	public void setAccountingElementId(Long accountingElementId) {
		this.accountingElementId = accountingElementId;
	}

	/**
	 * 说明:财务数据<br>
	 * 属性名: data<br>
	 * 类型: Double<br>
	 * 数据库字段:data<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double data;

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	/**
	 * 说明:数据日期<br>
	 * 属性名: date<br>
	 * 类型: Date<br>
	 * 数据库字段:date<br>
	 * 
	 * @author haipenge<br>
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 数据在总体中所占的比率 对应财务分析中的同型分析
	 */

	@NumberFormat(pattern = "#,###.####")
	@Transient
	private Double rate = 0.0D;

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}/** @generate-entity-source@ **/
