package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * DailyData ORM 实体
 * 数据库表:stock_dailyData
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月21日
 */
@Document(collection="stock_dailyData")
public class DailyData implements Serializable {

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
    * 说明:开盘价
    * 属性名: kaipanjia
    * 类型: Double
    * 数据库字段:kaipanjia
    * @author haipenge
    */
    
	private  Double kaipanjia;
	public Double getKaipanjia() {
		return kaipanjia;
	}
	public void setKaipanjia(Double kaipanjia) {
		this.kaipanjia = kaipanjia;
	}
	

	
   /**
    * 说明:收盘价
    * 属性名: shoupanjia
    * 类型: Double
    * 数据库字段:shoupanjia
    * @author haipenge
    */
    
	private  Double shoupanjia;
	public Double getShoupanjia() {
		return shoupanjia;
	}
	public void setShoupanjia(Double shoupanjia) {
		this.shoupanjia = shoupanjia;
	}
	

	
   /**
    * 说明:当前价格
    * 属性名: dangqianjiage
    * 类型: Double
    * 数据库字段:dangqianjiage
    * @author haipenge
    */
    
	private  Double dangqianjiage;
	public Double getDangqianjiage() {
		return dangqianjiage;
	}
	public void setDangqianjiage(Double dangqianjiage) {
		this.dangqianjiage = dangqianjiage;
	}
	

	
   /**
    * 说明:今天最高价
    * 属性名: jintianzuigaojia
    * 类型: Double
    * 数据库字段:jintianzuigaojia
    * @author haipenge
    */
    
	private  Double jintianzuigaojia;
	public Double getJintianzuigaojia() {
		return jintianzuigaojia;
	}
	public void setJintianzuigaojia(Double jintianzuigaojia) {
		this.jintianzuigaojia = jintianzuigaojia;
	}
	

	
   /**
    * 说明:今天最低价
    * 属性名: jintianzuidijia
    * 类型: Double
    * 数据库字段:jintianzuidijia
    * @author haipenge
    */
    
	private  Double jintianzuidijia;
	public Double getJintianzuidijia() {
		return jintianzuidijia;
	}
	public void setJintianzuidijia(Double jintianzuidijia) {
		this.jintianzuidijia = jintianzuidijia;
	}
	

	
   /**
    * 说明:成交股票数
    * 属性名: chengjiaogupiaoshu
    * 类型: Double
    * 数据库字段:chengjiagupiaoshu
    * @author haipenge
    */
    
	private  Double chengjiaogupiaoshu;
	public Double getChengjiaogupiaoshu() {
		return chengjiaogupiaoshu;
	}
	public void setChengjiaogupiaoshu(Double chengjiaogupiaoshu) {
		this.chengjiaogupiaoshu = chengjiaogupiaoshu;
	}
	

	
   /**
    * 说明:成交金额
    * 属性名: chengjiaojine
    * 类型: Double
    * 数据库字段:chengjiaojine
    * @author haipenge
    */
    
	private  Double chengjiaojine;
	public Double getChengjiaojine() {
		return chengjiaojine;
	}
	public void setChengjiaojine(Double chengjiaojine) {
		this.chengjiaojine = chengjiaojine;
	}
	

	
   /**
    * 说明:数据日期
    * 属性名: date
    * 类型: Date
    * 数据库字段:date
    * @author haipenge
    */
    
	private  Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

	
   /**
    * 说明:股票号
    * 属性名: stockId
    * 类型: Long
    * 数据库字段:stockId
    * @author haipenge
    */
    @Indexed
	private  Long stockId;
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	

	
//   /**
//    * 说明:股票代码
//    * 属性名: stockCode
//    * 类型: String
//    * 数据库字段:stockCode
//    * @author haipenge
//    */
//    
//	private  String stockCode;
//	public String getStockCode() {
//		return stockCode;
//	}
//	public void setStockCode(String stockCode) {
//		this.stockCode = stockCode;
//	}
//	
//
//	
//   /**
//    * 说明:股票名称
//    * 属性名: stockName
//    * 类型: String
//    * 数据库字段:stockName
//    * @author haipenge
//    */
//    
//	private  String stockName;
//	public String getStockName() {
//		return stockName;
//	}
//	public void setStockName(String stockName) {
//		this.stockName = stockName;
//	}
	
	/**
	 * 数据创建日期
	 */
	private Date createDate=new Date();
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

	
   
	

	
   /**
    * 说明:5日线
    * 属性名: avg5
    * 类型: Double
    * 数据库字段:avg5
    * @author haipenge
    */
    
	private  Double avg5=null;
	public Double getAvg5() {
		return avg5;
	}
	public void setAvg5(Double avg5) {
		this.avg5 = avg5;
	}
	

	
   /**
    * 说明:10日线
    * 属性名: avg10
    * 类型: Double
    * 数据库字段:avg10
    * @author haipenge
    */
    
	private  Double avg10=null;
	public Double getAvg10() {
		return avg10;
	}
	public void setAvg10(Double avg10) {
		this.avg10 = avg10;
	}
	

	
   /**
    * 说明:20日线
    * 属性名: avg20
    * 类型: Double
    * 数据库字段:avg20
    * @author haipenge
    */
    
	private  Double avg20=null;
	public Double getAvg20() {
		return avg20;
	}
	public void setAvg20(Double avg20) {
		this.avg20 = avg20;
	}
	

	
   /**
    * 说明:30日线
    * 属性名: avg30
    * 类型: Double
    * 数据库字段:avg30
    * @author haipenge
    */
    
	private  Double avg30=null;
	public Double getAvg30() {
		return avg30;
	}
	public void setAvg30(Double avg30) {
		this.avg30 = avg30;
	}
	

	
   /**
    * 说明:60日线
    * 属性名: avg60
    * 类型: Double
    * 数据库字段:avg60
    * @author haipenge
    */
    
	private  Double avg60=null;
	public Double getAvg60() {
		return avg60;
	}
	public void setAvg60(Double avg60) {
		this.avg60 = avg60;
	}
	

	
   /**
    * 说明:120日线
    * 属性名: avg120
    * 类型: Double
    * 数据库字段:avg120
    * @author haipenge
    */
    
	private  Double avg120=null;
	public Double getAvg120() {
		return avg120;
	}
	public void setAvg120(Double avg120) {
		this.avg120 = avg120;
	}
	

	
   /**
    * 说明:250日线
    * 属性名: avg250
    * 类型: Double
    * 数据库字段:avg250
    * @author haipenge
    */
    
	private  Double avg250=null;
	public Double getAvg250() {
		return avg250;
	}
	public void setAvg250(Double avg250) {
		this.avg250 = avg250;
	}
	

	
   /**
    * 说明:昨日收盘价<br>
    * 属性名: yesterdayPrice<br>
    * 类型: Double<br>
    * 数据库字段:yesterday_price<br>
    * @author haipenge<br>
    */
    
	private  Double yesterdayPrice;
	public Double getYesterdayPrice() {
		return yesterdayPrice;
	}
	public void setYesterdayPrice(Double yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}
	
	/**
	 * 星标数据类型
	 */
	private Integer starDataType=0;
	public Integer getStarDataType() {
		return starDataType;
	}
	public void setStarDataType(Integer starDataType) {
		this.starDataType = starDataType;
	}
	
}/**@generate-entity-source@**/
	
