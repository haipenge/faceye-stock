package com.faceye.component.stock.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.wrapper.WrapReporter;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;

@Controller
@Scope("prototype")
@RequestMapping("/stock/reportData")
public class ReportDataController extends BaseController<ReportData, Long, ReportDataService> {

	@Autowired
	private ReportCategoryService reportCategoryService = null;
	@Autowired
	private StockService stockService = null;
	@Autowired
	private DataStatService dataStatService = null;

	public ReportDataController(ReportDataService service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 财务报告
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月19日 上午11:38:47
	 */
	@RequestMapping("/report")
	public String reporter(HttpServletRequest request, Model model) {
		WrapReporter wrapReporter = null;
		Map searchParams = HttpUtil.getRequestParams(request);
		Long reportCategoryId = MapUtils.getLong(searchParams, "reportCategoryId");
		Long startDate = MapUtils.getLong(searchParams, "startDate");
		List<ReportCategory> reportCategories = this.reportCategoryService.getAll();
		if (reportCategoryId == null) {
			reportCategoryId = 3L;// 利润表
		}
		Long stockId = MapUtils.getLong(searchParams, "stockId");
		Stock stock = this.stockService.get(stockId);
		model.addAttribute("stock", stock);
		String date = MapUtils.getString(searchParams, "date");// Year
		// 报表分类，年报，季报？0（年报），1（一季报），2，3
		String type = MapUtils.getString(searchParams, "type");
		if (StringUtils.isEmpty(type)) {
			type = "0";
		}
		ReportCategory reportCategory = null;
		if (reportCategoryId != null) {
			reportCategory = this.reportCategoryService.get(reportCategoryId);
		} else {
			// 财务摘要
			reportCategory = this.reportCategoryService.getReportCategoryByCode(StockConstants.REPORT_CATEOGRY_FINANCIAL_SUMMARY);
		}
		if (StringUtils.equals(reportCategory.getCode(), StockConstants.REPORT_CATEOGRY_FINANCIAL_SUMMARY)) {
			// 财务接要,获取财务分析数据
			// 杜邦分析数据
			Map dataStatParams = new HashMap();
			dataStatParams.put("EQ|stockId", stockId);
			dataStatParams.put("EQ|type", type);
			if (startDate != null) {
				dataStatParams.put("LT|dateCycle", new Date(startDate));
			}
			dataStatParams.put("SORT|dateCycle", "desc");
			List<DataStat> dataStats = this.dataStatService.getPage(dataStatParams, 1, 5).getContent();
			model.addAttribute("dataStats", dataStats);
		} else {
			Map params = new HashMap();
			params.put("EQ|stockId", stockId);
			params.put("EQ|type", type);
			if (startDate != null) {
				params.put("LT|date", new Date(startDate));
			}
			params.put("SORT|date", "desc");
			List<ReportData> reportDatas = this.service.getPage(params, 1, 5).getContent();
			wrapReporter = this.service.wrapReportData(reportDatas, reportCategory.getCode());
			model.addAttribute("wrapReporter", wrapReporter);
		}
		model.addAttribute("reportCategory", reportCategory);
		model.addAttribute("reportCategories", reportCategories);
		return "stock.reportData.report";
	}
}
