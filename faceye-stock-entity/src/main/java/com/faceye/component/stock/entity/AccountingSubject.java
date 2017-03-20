package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 会计科目 AccountingSubject ORM 实体<br>
 * 数据库表:stock_accountingSubject<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "stock_accounting_subject")

public class AccountingSubject implements Serializable {
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
	 * 说明:会计科目<br>
	 * 属性名: name<br>
	 * 类型: String<br>
	 * 数据库字段:name<br>
	 * 
	 * @author haipenge<br>
	 */

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 分类编码,使用新浪分类编码 救命：http://money.finance.sina.com.cn/corp/view/vFD_FinanceSummaryHistory.php?stockid=000998&typecode=cfst1
	 */
	private String code = "";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@DBRef
	private AccountingElement accountingElement = null;

	public AccountingElement getAccountingElement() {
		return accountingElement;
	}

	public void setAccountingElement(AccountingElement accountingElement) {
		this.accountingElement = accountingElement;
	}

	/**
	 * 说明:新浪URL<br>
	 * 属性名: sinaUrl<br>
	 * 类型: String<br>
	 * 数据库字段:sina_url<br>
	 * 
	 * @author haipenge<br>
	 */

	private String sinaUrl;

	public String getSinaUrl() {
		return sinaUrl;
	}

	public void setSinaUrl(String sinaUrl) {
		this.sinaUrl = sinaUrl;
	}

}/** @generate-entity-source@ **/
