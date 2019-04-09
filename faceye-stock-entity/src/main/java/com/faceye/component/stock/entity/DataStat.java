package com.faceye.component.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * DataStat ORM 实体<br>
 * 数据库表:stock_dataStat<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "stock_data_stat")
@CompoundIndexes({ @CompoundIndex(name = "data_stat_index", def = "{stockId : 1, type : 1}") })
public class DataStat implements Serializable {
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
	 * 说明:数据 日期<br>
	 * 属性名: dateCycle<br>
	 * 类型: Date<br>
	 * 数据库字段:date_cycle<br>
	 * 
	 * @author haipenge<br>
	 */

	private Date dateCycle = null;

	public Date getDateCycle() {
		return dateCycle;
	}

	public void setDateCycle(Date dateCycle) {
		if(dateCycle!=null){
			if(dateCycle.getMonth()==11){
				this.setType(0);
			}
			if(dateCycle.getMonth()==8){
				this.setType(3);
			}
			if(dateCycle.getMonth()==5){
				this.setType(2);
			}
			if(dateCycle.getMonth()==2){
				this.setType(1);
			}
		}
		this.dateCycle = dateCycle;
	}

	/**
	 * 报告类型
	 */
	private Integer type = 0;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 说明:毛利率<br>
	 * 属性名: grossProfitMargin<br>
	 * 类型: Double<br>
	 * 数据库字段:gross_profit_margin<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double grossProfitMargin = null;

	public Double getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(Double grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}

	/**
	 * 说明:净利率<br>
	 * 属性名: netProfitMargin<br>
	 * 类型: Double<br>
	 * 数据库字段:net_profit_margin<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double netProfitMargin = null;

	public Double getNetProfitMargin() {
		return netProfitMargin;
	}

	public void setNetProfitMargin(Double netProfitMargin) {
		this.netProfitMargin = netProfitMargin;
	}

	/**
	 * 说明:资产周转率<br>
	 * 属性名: totalAssetsTurnover<br>
	 * 类型: Double<br>
	 * 数据库字段:total_assets_turn_over<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double totalAssetsTurnover;

	public Double getTotalAssetsTurnover() {
		return totalAssetsTurnover;
	}

	public void setTotalAssetsTurnover(Double totalAssetsTurnover) {
		this.totalAssetsTurnover = totalAssetsTurnover;
	}

	/**
	 * 说明:总资产净利率<br>
	 * 属性名: totalAssetsNetProfitMargin<br>
	 * 类型: Double<br>
	 * 数据库字段:total_assets_netprofit_margin<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double totalAssetsNetProfitMargin;

	public Double getTotalAssetsNetProfitMargin() {
		return totalAssetsNetProfitMargin;
	}

	public void setTotalAssetsNetProfitMargin(Double totalAssetsNetProfitMargin) {
		this.totalAssetsNetProfitMargin = totalAssetsNetProfitMargin;
	}

	/**
	 * 说明:资产负债率<br>
	 * 属性名: debtToAssetsRatio<br>
	 * 类型: Double<br>
	 * 数据库字段:debt_to_assets_ratio<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double debtToAssetsRatio;

	public Double getDebtToAssetsRatio() {
		return debtToAssetsRatio;
	}

	public void setDebtToAssetsRatio(Double debtToAssetsRatio) {
		this.debtToAssetsRatio = debtToAssetsRatio;
	}

	/**
	 * 说明:净资产收益率<br>
	 * 属性名: roe<br>
	 * 类型: Double<br>
	 * 数据库字段:roe<br>
	 * 
	 * @author haipenge<br>
	 */

	private Double roe;

	public Double getRoe() {
		return roe;
	}

	public void setRoe(Double roe) {
		this.roe = roe;
	}

   /**
    * 说明:核心利润率<br>
    * 属性名: coreProfitMargin<br>
    * 类型: Double<br>
    * 数据库字段:core_profit_margin<br>
    * @author haipenge<br>
    */
    
	private  Double coreProfitMargin=0D;
	public Double getCoreProfitMargin() {
		return coreProfitMargin;
	}
	public void setCoreProfitMargin(Double coreProfitMargin) {
		this.coreProfitMargin = coreProfitMargin;
	}
	/**
	 * 每股收益
	 */
	private Double eps=0.0D;

	public Double getEps() {
		return eps;
	}

	public void setEps(Double eps) {
		this.eps = eps;
	}
	/**
	 * 每股帐面价值
	 */
	private Double bps=0.0D;

	public Double getBps() {
		return bps;
	}

	public void setBps(Double bps) {
		this.bps = bps;
	}
	
	private Double dps=0.0D;

	public Double getDps() {
		return dps;
	}

	public void setDps(Double dps) {
		this.dps = dps;
	}
	
	/**
	 * 剩余收益
	 */
	private Double re=0.0D;

	public Double getRe() {
		return re;
	}

	public void setRe(Double re) {
		this.re = re;
	}
	
	/**
	 * 普通股权益报酬率 = 净利润/股东权益
	 */
	private Double roce=0.0D;

	public Double getRoce() {
		return roce;
	}

	public void setRoce(Double roce) {
		this.roce = roce;
	}
	
	/**
	 * 市净率
	 */
	private Double pb=0.0D;

	public Double getPb() {
		return pb;
	}

	public void setPb(Double pb) {
		this.pb = pb;
	}
	/**
	 * 市盈率
	 */
	private Double pe=0.0D;

	public Double getPe() {
		return pe;
	}

	public void setPe(Double pe) {
		this.pe = pe;
	}
	
	
	////以下是唐朝分析指标///
	//资产负债表
	/**
	 * 生产资产/总资产
	 * 生产资产->有形资产：组成部分：固定资产+在建工程+工程物资+土地
	 */
	private Double shengchanZichanAndZongZiChan=0.0D;

	public Double getShengchanZichanAndZongZiChan() {
		return shengchanZichanAndZongZiChan;
	}

	public void setShengchanZichanAndZongZiChan(Double shengchanZichanAndZongZiChan) {
		this.shengchanZichanAndZongZiChan = shengchanZichanAndZongZiChan;
	}
	
	
	/**
	 * 应收/总资产
	 */
	private Double yingShouAndZongZiChan=0.0D;

	public Double getYingShouAndZongZiChan() {
		return yingShouAndZongZiChan;
	}

	public void setYingShouAndZongZiChan(Double yingShouAndZongZiChan) {
		this.yingShouAndZongZiChan = yingShouAndZongZiChan;
	}
	
	/**
	 * 货币资金/有息负债
	 */
	private Double huoBiZiJinAndYouXiFuZhai=0.0D;

	public Double getHuoBiZiJinAndYouXiFuZhai() {
		return huoBiZiJinAndYouXiFuZhai;
	}

	public void setHuoBiZiJinAndYouXiFuZhai(Double huoBiZiJinAndYouXiFuZhai) {
		this.huoBiZiJinAndYouXiFuZhai = huoBiZiJinAndYouXiFuZhai;
	}

	/**
	 * 非主业资产/总资产
	 */
	private Double feiZhuYeZiChanAndZongZiChan=0.0D;

	public Double getFeiZhuYeZiChanAndZongZiChan() {
		return feiZhuYeZiChanAndZongZiChan;
	}

	public void setFeiZhuYeZiChanAndZongZiChan(Double feiZhuYeZiChanAndZongZiChan) {
		this.feiZhuYeZiChanAndZongZiChan = feiZhuYeZiChanAndZongZiChan;
	}
	
	/**
	 * 税前利润总额/生产资产
	 */
	
	private Double shuiQianLiRunZongErAndShengChanZiChan=0.0D;

	public Double getShuiQianLiRunZongErAndShengChanZiChan() {
		return shuiQianLiRunZongErAndShengChanZiChan;
	}

	public void setShuiQianLiRunZongErAndShengChanZiChan(Double shuiQianLiRunZongErAndShengChanZiChan) {
		this.shuiQianLiRunZongErAndShengChanZiChan = shuiQianLiRunZongErAndShengChanZiChan;
	}
	
	/**
	 * 费用率
	 */
	private Double feiYongRate=0.0D;

	public Double getFeiYongRate() {
		return feiYongRate;
	}

	public void setFeiYongRate(Double feiYongRate) {
		this.feiYongRate = feiYongRate;
	}
	/**
	 * 研发费用率
	 */
	private Double researchFeeRate=0.0D;

	public Double getResearchFeeRate() {
		return researchFeeRate;
	}

	public void setResearchFeeRate(Double researchFeeRate) {
		this.researchFeeRate = researchFeeRate;
	}
	
	/**
	 * 经营现金流量净额/净利润
	 */
	private Double moneyInCome=0.0D;

	public Double getMoneyInCome() {
		return moneyInCome;
	}

	public void setMoneyInCome(Double moneyInCome) {
		this.moneyInCome = moneyInCome;
	}
	/**
	 * 以经、投、筹资活动现金流量划分企业;
	 * 1.经>0,投>0,筹>0:妖精;
	 * 2.经>0,投>0,筹<0:老母鸡;
	 * 3.经>0,投<0,筹>0:蛮牛;
	 * 4.经>0,投<0,筹<0:奶牛;
	 * 5.经<0,投>0,筹>0:骗吃骗喝;
	 * 6.经<0,投>0,筹<0:混吃等死;
	 * 7.经<0,投<0,筹>0:赌徒;
	 * 8.经<0,投<0,筹<0:大出血;
	 * 
	 */
	private Integer cashFlowType=0;

	public Integer getCashFlowType() {
		return cashFlowType;
	}

	public void setCashFlowType(Integer cashFlowType) {
		this.cashFlowType = cashFlowType;
	}
	
	@Transient
	private String cashFlowTypeName="未知";

	public String getCashFlowTypeName() {
		return CashFlowTypeNames.getName(getCashFlowType());
	}

	public void setCashFlowTypeName(String cashFlowTypeName) {
		this.cashFlowTypeName = cashFlowTypeName;
	}
	
	
	
	
	
}/**@generate-entity-source@**/
	
