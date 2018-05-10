package com.faceye.component.stock.service.wrapper;

public class Data2Record {

	private Long accountingSubjectId = null;
	private Long accountingElementId = null;
	private Double data = 0.0D;

	public Long getAccountingSubjectId() {
		return accountingSubjectId;
	}

	public void setAccountingSubjectId(Long accountingSubjectId) {
		this.accountingSubjectId = accountingSubjectId;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public Long getAccountingElementId() {
		return accountingElementId;
	}

	public void setAccountingElementId(Long accountingElementId) {
		this.accountingElementId = accountingElementId;
	}

	/**
	 * 同型分析结果
	 */
	private Double commonSizeAnalysisResult = 0.0D;

	public Double getCommonSizeAnalysisResult() {
		return commonSizeAnalysisResult;
	}

	public void setCommonSizeAnalysisResult(Double commonSizeAnalysisResult) {
		this.commonSizeAnalysisResult = commonSizeAnalysisResult;
	}

	/**
	 * 趋势分析结果
	 */
	private Double trendAnalysisResult=0.0D;

	public Double getTrendAnalysisResult() {
		return trendAnalysisResult;
	}

	public void setTrendAnalysisResult(Double trendAnalysisResult) {
		this.trendAnalysisResult = trendAnalysisResult;
	}
	
}
