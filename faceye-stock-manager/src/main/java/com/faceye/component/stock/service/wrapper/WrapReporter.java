package com.faceye.component.stock.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.stock.entity.ReportCategory;

public class WrapReporter {

	private ReportCategory reportCategory = null;
	private List<WrapReporterTitle> titles = new ArrayList<WrapReporterTitle>(0);
	private List<WrapRecords> records = new ArrayList<WrapRecords>(0);

	public List<WrapReporterTitle> getTitles() {
		return titles;
	}

	public void setTitles(List<WrapReporterTitle> titles) {
		this.titles = titles;
	}

	public List<WrapRecords> getRecords() {
		return records;
	}

	public void setRecords(List<WrapRecords> records) {
		this.records = records;
	}

	public ReportCategory getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(ReportCategory reportCategory) {
		this.reportCategory = reportCategory;
	}

}
