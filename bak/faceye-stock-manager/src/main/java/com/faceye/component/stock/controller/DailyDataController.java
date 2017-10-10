package com.faceye.component.stock.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

@Controller
@Scope("prototype")
@RequestMapping("/stock/dailyData")
public class DailyDataController extends BaseController<DailyData, Long, DailyDataService> {
	@Autowired
	private StockService stockService = null;

	@Autowired
	public DailyDataController(DailyDataService service) {
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
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Long stockId = MapUtils.getLong(searchParams, "EQ|stockId");
		if (null != stockId) {
			Stock stock = this.stockService.get(stockId);
			model.addAttribute("stock", stock);
		}
		searchParams.put("SORT|date", "desc");
		Page<DailyData> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		return "stock.dailyData.manager";
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
			DailyData entity = this.service.get(id);
			model.addAttribute("dailyData", entity);
		}
		return "stock.dailyData.update";
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
		return "stock.dailyData.update";
	}

	/**
	 * 数据保存
	 */
	@RequestMapping("/save")
	public String save(DailyData entity, RedirectAttributes redirectAttributes) {
		this.service.save(entity);
		return "redirect:/stock/dailyData/home";
	}

	/**
	 * 数据删除
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		Long stockId = MapUtils.getLong(params, "stockId");
		if (stockId != null) {
			this.service.remove(stockId);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
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
			DailyData entity = this.service.get(id);
			model.addAttribute("dailyData", entity);
		}
		return "stock.dailyData.detail";
	}

	/**
	 * 爬取数据、补数据
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月13日 上午9:23:04
	 */
	@RequestMapping("/crawl")
	@ResponseBody
	public String crawl(HttpServletRequest request) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Long stockId = MapUtils.getLong(searchParams, "stockId");
		if (stockId != null) {
			Stock stock = this.stockService.get(stockId);
			this.service.initDailyData(stock.getCode());
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	
	/**
	 * 用于显示股票价格图
	 * @param request
	 * @return
	 */
	@RequestMapping("/showDailyData")
	@ResponseBody
	public List<DailyData> showDailyData(HttpServletRequest request){
		List dailyDatas=null;
		Map searchParams=HttpUtil.getRequestParams(request);
		Long stockId=MapUtils.getLong(searchParams, "stockId");
		if(stockId!=null){
			Map params=new HashMap();
			params.put("EQ|stockId", stockId);
			dailyDatas=this.service.getPage(params, 1, 0).getContent();
		}
		return dailyDatas;
	}

}
