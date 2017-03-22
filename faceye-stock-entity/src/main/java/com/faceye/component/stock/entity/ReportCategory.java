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
 * ReportCategory ORM 实体<br>
 * 如:利润表，资产负债表等
 * 数据库表:stock_reportCategory<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="stock_report_category")
public class ReportCategory implements Serializable {
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
    * 说明:名称<br>
    * 属性名: name<br>
    * 类型: String<br>
    * 数据库字段:name<br>
    * @author haipenge<br>
    */
    
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
   /**
    * 说明:编码<br>
    * 属性名: code<br>
    * 类型: String<br>
    * 数据库字段:code<br>
    * @author haipenge<br>
    */
    
	private  String code="";
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
	

	
   /**
    * 说明:排序值<br>
    * 属性名: orderIndex<br>
    * 类型: Integer<br>
    * 数据库字段:order_index<br>
    * @author haipenge<br>
    */
    
	private  Integer orderIndex=0;
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	
}/**@generate-entity-source@**/
	
