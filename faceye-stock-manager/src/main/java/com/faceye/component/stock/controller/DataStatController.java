package com.faceye.component.stock.controller;

import java.util.HashMap;
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

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:stock<br>
 * 实体:DataStat<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/stock/dataStat")
public class DataStatController extends BaseController<DataStat, Long, DataStatService> {
    @Autowired
	private FinancialDataService financialDataService=null;
    @Autowired
    private StockService stockService=null;
	@Autowired
	public DataStatController(DataStatService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		Page<DataStat> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		Long stockId=MapUtils.getLong(searchParams, "EQ|stockId");
		Stock stock=this.stockService.get(stockId);
		model.addAttribute("stock", stock);
		//获取营业收入
		Map params=new HashMap();
		params.putAll(searchParams);
		params.put("EQ|accountingSubjectId", StockConstants.OPERATING_INCOME);
		Page<FinancialData> operatingIncome=this.financialDataService.getPage(params, 0, 1);
		model.addAttribute("operatingIncome", operatingIncome);
		
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "stock.dataStat.manager";
	}
	
	/**
	 * 启动数据 分析
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月24日 上午9:39:14
	 */
	public String stat(HttpServletRequest request){
		Map params=HttpUtil.getRequestParams(request);
		Long stockId=MapUtils.getLong(params, "stockId");
		Stock stock=this.stockService.get(stockId);
		this.service.stat(stock);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			beforeInput(model,request);
			DataStat entity=this.service.get(id);
			model.addAttribute("dataStat", entity);
		}
		return "stock.dataStat.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月27日<br>
	 */
	@RequestMapping(value="/input")
	public String input(DataStat dataStat,Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "stock.dataStat.update";
	}
	
	
    

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid DataStat dataStat,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			beforeInput(model,request);
			return "stock.dataStat.update";
		}else{
		   this.beforeSave(dataStat,request);
		   this.service.save(dataStat);
		   return "redirect:/stock/dataStat/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(id!=null){
			this.service.remove(id);
		}
		return "redirect:/stock/dataStat/home";
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			DataStat entity=this.service.get(id);
			model.addAttribute("dataStat", entity);
		}
		return "stock.dataStat.detail";
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2015年4月5日<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		
	}
	/**
	 *
	 *保存数据前的回调函数
	 */
	protected void beforeSave(DataStat dataStat,HttpServletRequest request){
		
	}

}
