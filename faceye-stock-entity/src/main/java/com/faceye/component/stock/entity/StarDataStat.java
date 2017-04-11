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
@Document(collection="stock_starDataStat")
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
    * 说明:均线数据分析<br>
    * 属性名: avgDataStat<br>
    * 类型: Integer<br>
    * 数据库字段:avg_data_stat<br>
    * @author haipenge<br>
    */
    
	private  Integer avgDataStat=0;
	public Integer getAvgDataStat() {
		return avgDataStat;
	}
	public void setAvgDataStat(Integer avgDataStat) {
		this.avgDataStat = avgDataStat;
	}
	

	
   /**
    * 说明:星标数据日期<br>
    * 属性名: avgDataStatDate<br>
    * 类型: Date<br>
    * 数据库字段:avg_data_stat_date<br>
    * @author haipenge<br>
    */
    
	private  Date avgDataStatDate=null;
	public Date getAvgDataStatDate() {
		return avgDataStatDate;
	}
	public void setAvgDataStatDate(Date avgDataStatDate) {
		this.avgDataStatDate = avgDataStatDate;
	}
	
}/**@generate-entity-source@**/
	
