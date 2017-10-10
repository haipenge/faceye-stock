package com.faceye.component.stock.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.service.DailyStatService;
import com.faceye.component.stock.service.StarDataStatService;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:stock<br>
 * 实体:DailyStat<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/stock/dailyStat")
public class DailyStatController extends BaseController<DailyStat, Long, DailyStatService> {
	@Autowired
	private StockService stockService = null;
	@Autowired
	private DailyDataService dailyDataService=null;
	

	@Autowired
	public DailyStatController(DailyStatService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<DailyStat> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "stock.dailyStat.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			DailyStat entity = this.service.get(id);
			model.addAttribute("dailyStat", entity);
		}
		return "stock.dailyStat.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月27日<br>
	 */
	@RequestMapping(value = "/input")
	public String input(DailyStat dailyStat, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "stock.dailyStat.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid DailyStat dailyStat, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "stock.dailyStat.update";
		} else {
			this.beforeSave(dailyStat, request);
			this.service.save(dailyStat);
			return "redirect:/stock/dailyStat/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/stock/dailyStat/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			DailyStat entity = this.service.get(id);
			model.addAttribute("dailyStat", entity);
		}
		return "stock.dailyStat.detail";
	}

	/////////////////////////////////////////////// 以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(DailyStat dailyStat, HttpServletRequest request) {

	}

	/**
	 * 启动手工数据分析
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月17日 下午5:25:07
	 */
	@RequestMapping("/statDailyData2FindStar")
	@ResponseBody
	public String statDailyData2FindStar(HttpServletRequest request){
		Map params=HttpUtil.getRequestParams(request);
		Long stockId=MapUtils.getLong(params, "stockId");
		if(stockId!=null){
			Stock stock=this.stockService.get(stockId);
			if(null!=stock){
				this.dailyDataService.computeDailyDataLines(stock);
				this.service.statDailyData2FindStar(stock);
				this.service.statStarData(stock);
				
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

}
