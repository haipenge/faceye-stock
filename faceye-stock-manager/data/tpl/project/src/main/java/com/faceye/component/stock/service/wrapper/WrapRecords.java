package com.faceye.component.stock.service.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WrapRecords {
	private Date date = null;
	private List<Data2Record> data2Record = new ArrayList<Data2Record>();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Data2Record> getData2Record() {
		return data2Record;
	}

	public void setData2Record(List<Data2Record> data2Record) {
		this.data2Record = data2Record;
	}


}
