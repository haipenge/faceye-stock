package com.faceye.component.stock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import com.faceye.component.stock.service.StockService;
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
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		String nameQueryKey = MapUtils.getString(searchParams, "like|name");
		String codeQueryKey = MapUtils.getString(searchParams, "like|code");
		Page<Stock> page = null;
		List<Stock> items=new ArrayList<Stock>(0);
		if (StringUtils.isNotEmpty(nameQueryKey)) {
			nameQueryKey = StringUtils.replace(nameQueryKey, "，", ",");
			String[] nameKeys = StringUtils.split(nameQueryKey, ",");
			searchParams.remove("like|name");
			for (String name : nameKeys) {
				name = StringUtils.trim(name);
				if (StringUtils.isNotEmpty(name)) {
					searchParams.put("like|name", name);
					Page result = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
					if (result != null && CollectionUtils.isNotEmpty(result.getContent())) {
						items.addAll(result.getContent());
					}
				}
			}
			searchParams.remove("like|name");
		}
		
		if (StringUtils.isNotEmpty(codeQueryKey)) {
			codeQueryKey = StringUtils.replace(codeQueryKey, "，", ",");
			String[] codeKeys = StringUtils.split(codeQueryKey, ",");
			searchParams.remove("like|code");
			for (String code : codeKeys) {
				code = StringUtils.trim(code);
				if (StringUtils.isNotEmpty(code)) {
					searchParams.put("like|code", code);
					Page result = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
					if (result != null && CollectionUtils.isNotEmpty(result.getContent())) {
						items.addAll(result.getContent());
					}
				}
			}
		}
		if(CollectionUtils.isNotEmpty(items)){
			page=new PageImpl(items);
		}
		if (page == null || CollectionUtils.isEmpty(page.getContent())) {
			page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		}
		searchParams.put("like|name", nameQueryKey);
		searchParams.put("like|code", codeQueryKey);
		this.resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("page", page);
		List<Category> categories = this.categoryService.getAll();
		model.addAttribute("categories", categories);
		return "stock.stock.manager";
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
	 * 根据关键字查询时股票提示
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月28日 下午12:33:19
	 */
	@RequestMapping("/queryTips")
	@ResponseBody
	public List<Stock> queryTips(HttpServletRequest request) {
		List<Stock> stocks = null;

		return stocks;
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

}
