package com.faceye.component.stock.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.CategoryService;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.ReportDataService;
import com.faceye.component.stock.service.StarDataStatService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.wrapper.ReportResult;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

@Controller
@Scope("prototype")
@RequestMapping("/stock/stock")
public class StockController extends BaseController<Stock, Long, StockService> {
	@Autowired
	private CrawlFinancialDataService crawlFinancialDataService = null;
	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private StarDataStatService starDataStatService = null;
	@Autowired
	private ReportDataService reportDataService=null;

	@Autowired
	public StockController(StockService service) {
		super(service);
	}

	/**
	 * 首页
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		Page page = null;
		Map searchParams = this.filterParams(request);
		page = this.service.getPage(searchParams, getPage(searchParams), 50);
		this.resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("page", page);
		List<Category> categories = this.categoryService.getAll();
		model.addAttribute("categories", categories);
		return "stock.stock.manager";
	}
	
	/**
	 * 导出数据到Excel
	 * @param request
	 * @param response
	 */
	@RequestMapping("/export")
	public String export(HttpServletRequest request,HttpServletResponse response){
		Map searchParams = this.filterParams(request);
		response.setHeader("Content-disposition", "stock-filter.xls");  
        response.setContentType("application/vnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=stock-filter.xls");    
        response.setHeader("Pragma", "No-cache");  
		try {
			this.service.export(searchParams, response.getOutputStream());
		} catch (IOException e) {
			logger.error(">>Exception:"+e);
		}
		return null;
	}
	
	private Map filterParams(HttpServletRequest request){
		Map searchParams = HttpUtil.getRequestParams(request);
		String nameQueryKey = MapUtils.getString(searchParams, "like|name");
		String codeQueryKey = MapUtils.getString(searchParams, "like|code");
		Double minPe = MapUtils.getDouble(searchParams, "GTE|dailyStat.pe");
		Double maxPe = MapUtils.getDouble(searchParams, "LTE|dailyStat.pe");
		String trace = MapUtils.getString(searchParams, "trace");

		String submit = MapUtils.getString(searchParams, "submit", "query");

		if (StringUtils.isNotEmpty(trace)) {
			Map starParams = new HashMap();
			// starParams.put("EQ|stockId", value)
			List<Long> traceStockIds = null;
			if (StringUtils.equals(trace, "1")) {
				// 如果是星标追踪
				starParams.put("EQ|starType", StockConstants.STOCK_STAR_TYPE_1);
			} else if (StringUtils.equals(trace, "2")) {
				starParams.put("EQ|starType", StockConstants.STOCK_STAR_TYPE_2);
			} else if (StringUtils.equals(trace, "3")) {
				starParams.put("EQ|starType", StockConstants.STOCK_STAR_TYPE_3);
			}
			traceStockIds = this.starDataStatService.getStarStockIds(starParams);
			if (CollectionUtils.isNotEmpty(traceStockIds)) {
				searchParams.put("IN|id", traceStockIds);
			}
		}
		searchParams.put("minPe", minPe);
		searchParams.put("maxPe", maxPe);
		String sortPe = MapUtils.getString(searchParams, "SORT|dailyStat.pe");
		searchParams.put("sortPe", sortPe);
		String sortTodayIncreaseRate = MapUtils.getString(searchParams, "SORT|dailyStat.todayIncreaseRate");
		searchParams.put("sortTodayIncreaseRate", sortTodayIncreaseRate);
		String sortPriceAmplitude = MapUtils.getString(searchParams, "SORT|dailyStat.priceAmplitude");
		searchParams.put("sortPriceAmplitude", sortPriceAmplitude);
		return searchParams;
	}

	/**
	 * 转向编辑或新增页面
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		if (id != null) {
			Stock entity = this.service.get(id);
			model.addAttribute("stock", entity);
		}
		return "stock.stock.update";
	}

	/**
	 * 转向新增页面
	 * 
	 * @todo
	 * @param model
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月27日
	 */
	@RequestMapping(value = "/input")
	public String input(Model model) {
		return "stock.stock.update";
	}

	/**
	 * 数据保存
	 */
	@RequestMapping("/save")
	public String save(Stock entity, RedirectAttributes redirectAttributes) {
		this.service.save(entity);
		return "redirect:/stock/stock/home";
	}

	/**
	 * 数据删除
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/stock/stock/home";
	}

	/**
	 * 取得数据明细
	 * 
	 * @todo
	 * @param id
	 * @param model
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月26日
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Stock entity = this.service.get(id);
			model.addAttribute("stock", entity);
		}
		return "stock.stock.detail";
	}

	/**
	 * 股票看板
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月28日 下午12:33:19
	 */
	@RequestMapping("/stockBoard")
	public String stockBoard(HttpServletRequest request,Model model) {
		Map params=HttpUtil.getRequestParams(request);
		Long stockId=MapUtils.getLong(params, "stockId");
		Stock stock=this.service.get(stockId);
		model.addAttribute("stock", stock);
		ReportResult reportResult=reportDataService.getWrapReporter(stockId, StockConstants.REPORT_TYPE_YEAR, 2L, null);
		model.addAttribute("reportResult", reportResult);
		return "stock.stock.board";
	}
	
	

	/**
	 * 初始化股票所属分类
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月21日 上午10:45:01
	 */
	@RequestMapping("/initStockCategory")
	@ResponseBody
	public String initStockCategory() {
		boolean res = this.service.initStockCategory();
		return AjaxResult.getInstance().buildDefaultResult(res);
	}
	/**
	 * 超级初始化
	 * @param stockId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2018年6月18日 下午3:33:15
	 */
	@RequestMapping("/superInit")
	public String superInit(Long stockId){
		this.service.superInitStock(stockId);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

}
