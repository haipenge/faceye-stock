/**
*财报
*/
package com.faceye.component.stock.entity;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock_report_data")
@CompoundIndexes({ @CompoundIndex(name = "report_data_index", def = "{stockId : 1, type : 1, date : -1}") })
public class ReportData {
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 资产负责表(1 BALANCE_SHEET
	 */
	private BalanceSheet balanceSheet = new BalanceSheet();

	public void setBalanceSheet(BalanceSheet balanceSheet) {
		this.balanceSheet = balanceSheet;
	}

	public BalanceSheet getBalanceSheet() {
		return this.balanceSheet;
	}

	/**
	 * 财务摘要(2 FINANCIAL_SUMMARY
	 */
	// private FinancialSummary financialSummary = new FinancialSummary();
	//
	// public void setFinancialSummary(FinancialSummary financialSummary) {
	// this.financialSummary = financialSummary;
	// }
	//
	// public FinancialSummary getFinancialSummary() {
	// return this.financialSummary;
	// }

	/**
	 * 利润表(3 IN_COME_SHEET
	 */
	private InComeSheet inComeSheet = new InComeSheet();

	public void setInComeSheet(InComeSheet inComeSheet) {
		this.inComeSheet = inComeSheet;
	}

	public InComeSheet getInComeSheet() {
		return this.inComeSheet;
	}

	/**
	 * 现金流量表(4 CASH_FLOW_SHEET
	 */
	private CashFlowSheet cashFlowSheet = new CashFlowSheet();

	public void setCashFlowSheet(CashFlowSheet cashFlowSheet) {
		this.cashFlowSheet = cashFlowSheet;
	}

	public CashFlowSheet getCashFlowSheet() {
		return this.cashFlowSheet;
	}

	/**
	 * 报表日期
	 */
	private Date date = null;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if (date != null) {
			Integer type = 0;// 年报
			if (date.getMonth() == 2) {
				type = 1;// 一季报3.31
			}
			if (date.getMonth() == 5) {
				type = 2;// 中报,6.30
			}
			if (date.getMonth() == 8) {
				type = 3;// 三报报,9.30
			}
			if (date.getMonth() == 11) {
				type = 0;// 年报,12.21
			}
			this.setType(type);
		}
		this.date = date;
	}

	private Long stockId = null;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	/**
	 * 报表分类，年报，季报？0（年报），1（一季报），2，3
	 */
	private Integer type = null;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
