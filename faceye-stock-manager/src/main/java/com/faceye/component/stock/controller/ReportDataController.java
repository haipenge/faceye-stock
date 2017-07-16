package com.faceye.component.stock.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.entity.ReportData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.BonusRecordService;
import com.faceye.component.stock.service.DailyStatService;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.wrapper.WrapCompareReporter;
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
	@Autowired
	private DailyStatService dailyStatService = null;
	
	@Autowired
	private BonusRecordService bonusService=null;

	public ReportDataController(ReportDataService service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 报告首页
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月7日 下午1:29:30
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request,Model model){
		Map searchParams=HttpUtil.getRequestParams(request);
		//涨幅前30名
		searchParams.put("GTE|dailyStat.todayIncreaseRate", 0.05);
		searchParams.put("SORT|dailyStat.todayIncreaseRate", "desc");
		Page<Stock> topIncreaseStocks=this.stockService.getPage(searchParams, 1, 30);
		model.addAttribute("topIncreaseStocks", topIncreaseStocks);
		//跌幅前30名
		searchParams.remove("GTE|dailyStat.todayIncreaseRate");
		searchParams.put("SORT|dailyStat.todayIncreaseRate", "asc");
		Page<Stock> footIncreaseStocks=this.stockService.getPage(searchParams, 1, 30);
		model.addAttribute("footIncreaseStocks", footIncreaseStocks);
		return "stock.reportData.home";
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
		StopWatch watch = new StopWatch();
		watch.start("0");
		Map searchParams = HttpUtil.getRequestParams(request);
		Long reportCategoryId = MapUtils.getLong(searchParams, "reportCategoryId");
		Long startDate = MapUtils.getLong(searchParams, "startDate");
		List<ReportCategory> reportCategories = this.reportCategoryService.getPage(null, 0, 0).getContent();
		if (reportCategoryId == null) {
			reportCategoryId = 2L;// 财务摘要
		}
		Long stockId = MapUtils.getLong(searchParams, "stockId");
		Stock stock = this.stockService.get(stockId);
		model.addAttribute("stock", stock);
		String date = MapUtils.getString(searchParams, "date");// Year
		// 报表分类，年报，季报？0（年报），1（一季报），2，3
		Integer type = MapUtils.getInteger(searchParams, "type");
		if (type == null) {
			type = 0;
		}
		if (type == 4) {
			type = null;
		}
		ReportCategory reportCategory = null;
		if (reportCategoryId != null) {
			reportCategory = this.reportCategoryService.get(reportCategoryId);
		} else {
			// 财务摘要
			reportCategory = this.reportCategoryService.getReportCategoryByCode(StockConstants.REPORT_CATEOGRY_FINANCIAL_SUMMARY);
		}
		watch.stop();
		// 获取每日数据分析
		Map dailyStatParams = new HashMap();
		dailyStatParams.put("EQ|stockId", stockId);
		List<DailyStat> dailyStats = this.dailyStatService.getPage(dailyStatParams, 0, 1).getContent();
		DailyStat dailyStat = CollectionUtils.isNotEmpty(dailyStats) ? dailyStats.get(0) : null;
		model.addAttribute("dailyStat", dailyStat);

		if (StringUtils.equals(reportCategory.getCode(), StockConstants.REPORT_CATEOGRY_FINANCIAL_SUMMARY)) {
			// 财务接要,获取财务分析数据
			// 杜邦分析数据
			Map dataStatParams = new HashMap();
			dataStatParams.put("EQ|stockId", stockId);
			if (type != null) {
				dataStatParams.put("EQ|type", type);
			}
			if (startDate != null) {
				dataStatParams.put("LT|dateCycle", new Date(startDate));
			}
			dataStatParams.put("SORT|dateCycle", "desc");
			List<DataStat> dataStats = this.dataStatService.getPage(dataStatParams, 1, 5).getContent();
			model.addAttribute("dataStats", dataStats);
		} else {
			Map params = new HashMap();
			params.put("EQ|stockId", stockId);
			if (type != null) {
				params.put("EQ|type", type);
			}
			if (startDate != null) {
				params.put("LT|date", new Date(startDate));
			}
			params.put("SORT|date", "desc");
			watch.start("1");
			List<ReportData> reportDatas = this.service.getPage(params, 1, 5).getContent();
			watch.stop();
			watch.start("2");
			wrapReporter = this.service.wrapReportData(reportDatas, reportCategory.getCode());
			watch.stop();
			model.addAttribute("wrapReporter", wrapReporter);
		}
		model.addAttribute("reportCategory", reportCategory);
		model.addAttribute("reportCategories", reportCategories);
		logger.error("-------------------------Stop Watch monitor------------------------------------");
		TaskInfo[] infos = watch.getTaskInfo();
		for (TaskInfo info : infos) {
			logger.error("View" + info.getTaskName() + " Cost :" + info.getTimeMillis());
		}
		return "stock.reportData.report";
	}

	/**
	 * 多只股票数据比对
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月3日 上午9:16:04
	 */
	@RequestMapping("/compare")
	public String compare(HttpServletRequest request, Model model) {
		WrapReporter wrapReporter = null;
		Map searchParams = HttpUtil.getRequestParams(request);
		Long reportCategoryId = MapUtils.getLong(searchParams, "reportCategoryId");
		Long startDate = MapUtils.getLong(searchParams, "startDate");
		// Long stockId = MapUtils.getLong(searchParams, "stockId");
		String date = MapUtils.getString(searchParams, "date");// Year
		// 报表分类，年报，季报？0（年报），1（一季报），2，3
		Integer type = MapUtils.getInteger(searchParams, "type");
		String stockIds = MapUtils.getString(searchParams, "stockIds");
		if (StringUtils.isNotEmpty(stockIds)) {
			ReportCategory reportCategory = null;
			if (reportCategoryId != null) {
				reportCategory = this.reportCategoryService.get(reportCategoryId);
			} else {
				// 财务摘要
				reportCategory = this.reportCategoryService.getReportCategoryByCode(StockConstants.REPORT_CATEOGRY_FINANCIAL_SUMMARY);
			}
			List<ReportCategory> reportCategories = this.reportCategoryService.getPage(null, 0, 0).getContent();
			model.addAttribute("reportCategory", reportCategory);
			model.addAttribute("reportCategories", reportCategories);

			String[] ids = StringUtils.split(stockIds, ",");
			List<WrapCompareReporter> wrapCompareReporters = new ArrayList<WrapCompareReporter>(0);
			for (String id : ids) {
				if (StringUtils.isEmpty(id)) {
					continue;
				}
				WrapCompareReporter wrapCompareReporter = new WrapCompareReporter();
				Long stockId = Long.parseLong(id);
				if (reportCategoryId == null) {
					reportCategoryId = 2L;// 财务摘要
				}
				Stock stock = this.stockService.get(stockId);
				wrapCompareReporter.setStock(stock);
				model.addAttribute("stock", stock);

				if (type == null) {
					type = 0;
				}
				// 如果报告类型为4,则为查询所有已发布报表数据
				if (type == 4) {
					type = null;
				}
				// 获取每日数据分析
				Map dailyStatParams = new HashMap();
				dailyStatParams.put("EQ|stockId", stockId);
				List<DailyStat> dailyStats = this.dailyStatService.getPage(dailyStatParams, 0, 1).getContent();
				DailyStat dailyStat = CollectionUtils.isNotEmpty(dailyStats) ? dailyStats.get(0) : null;
				model.addAttribute("dailyStat", dailyStat);
				if (StringUtils.equals(reportCategory.getCode(), StockConstants.REPORT_CATEOGRY_FINANCIAL_SUMMARY)) {
					// 财务接要,获取财务分析数据
					// 杜邦分析数据
					Map dataStatParams = new HashMap();
					dataStatParams.put("EQ|stockId", stockId);
					if (type != null) {
						dataStatParams.put("EQ|type", type);
					}
					if (startDate != null) {
						dataStatParams.put("LT|dateCycle", new Date(startDate));
					}
					dataStatParams.put("SORT|dateCycle", "desc");
					List<DataStat> dataStats = this.dataStatService.getPage(dataStatParams, 1, 2).getContent();
					// model.addAttribute("dataStats", dataStats);
					logger.debug(">>FaceYe data stats size is:" + dataStats.size());
					wrapCompareReporter.setDataStats(dataStats);
				} else {
					Map params = new HashMap();
					params.put("EQ|stockId", stockId);
					if (type != null) {
						params.put("EQ|type", type);
					}
					if (startDate != null) {
						params.put("LT|date", new Date(startDate));
					}
					params.put("SORT|date", "desc");
					List<ReportData> reportDatas = this.service.getPage(params, 1, 2).getContent();
					wrapReporter = this.service.wrapReportData(reportDatas, reportCategory.getCode());
					wrapCompareReporter.setStock(stock);
					wrapCompareReporter.setWrapReporter(wrapReporter);
				}
				wrapCompareReporters.add(wrapCompareReporter);
			}
			model.addAttribute("wrapCompareReporters", wrapCompareReporters);
		}
		return "stock.reportData.compare";
	}
}
