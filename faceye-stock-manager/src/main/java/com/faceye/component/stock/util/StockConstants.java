package com.faceye.component.stock.util;

public class StockConstants {

	// 年报
	public static final Integer REPORT_TYPE_YEAR = 0;
	// 一季报
	public static final Integer REPORT_TYPE_1 = 1;
	// 中报
	public static final Integer REPORT_TYPE_2 = 2;
	// 三季报
	public static final Integer REPORT_TYPE_3 = 3;
	// 全部，含一，二，三，四
	public static final Integer REPORT_TYPE_4 = 4;
	
	//财报分类
	//现金流量表
	public static final String REPORT_CATEGORY_CASH_FLOW_SHEET="CASH_FLOW_SHEET";
	//利润表
	public static final String REPORT_CATEGORY_IN_COME_SHEET="IN_COME_SHEET";
	//资产负债表
	public static final String REPORT_CATEGORY_BALANCE_SHEET="BALANCE_SHEET";
	//财务接要
	public static final String REPORT_CATEOGRY_FINANCIAL_SUMMARY="FINANCIAL_SUMMARY";
	
	///////////////////////////////////////////////////////////////////////////
	// 总资产=股东权益+负债
	public static final Long TOTAL_ASSETS = 189L;
	// 净利润
	public static final Long NET_PROFIT = 128L;
	// 营业成本
	public static final Long OPERATING_COSTS = 97L;
	// 营业收入
	public static final Long OPERATING_INCOME = 90L;
	//负债合计 liabilities
	public static final Long TOTAL_LIABILITIES=230L;
	
	
	///////////////////////////星标类型////////////////////////////////////////
	//avg 5，10，20多头排列
	public static final Integer STOCK_STAR_TYPE_1=1;
	//avg(5.10.20）多头，之后五个交易日，macd(dif>dea) 快线在慢线之上。
	public static final Integer STOCK_STAR_TYPE_2=2;
	
}
