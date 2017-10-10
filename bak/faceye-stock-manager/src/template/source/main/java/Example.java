package com.faceye.component.@component.name@.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @entity.name@ ORM 实体
 * 数据库表:@component.name@_@entity.config.name@
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月21日
 */
@Entity
@Table(name="@component.name@_@entity.config.name@")
public class @entity.name@ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long id=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 *  创建日间<br>
	 */
	@Column(name="create_date",updateable=false)
	private Date createDate=new Date();
	public void setCreateDate(Date createDate){
		this.createDate=createDate;
	}
	public Date getCreateDate(){
		return this.createDate;
	}
}/**@generate-entity-source@**/
