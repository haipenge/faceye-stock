package com.faceye.component.stock.service.wrapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.entity.Stock;

/**
 * 会计报表包装类
 * @author haipenge
 *
 */
public class FinancialReportWrapper {

	private Stock stock=null;
	private ReportCategory reportCategory=null;
	private Date date=null;
	private Map<AccountingElement,List<FinancialDataWrapper>> datas=new LinkedMap();
	
}
