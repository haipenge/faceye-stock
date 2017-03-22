package com.faceye.component.stock.controller;

import java.util.List;
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

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.service.AccountingElementService;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:stock<br>
 * 实体:AccountingElement<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/stock/accountingElement")
public class AccountingElementController extends BaseController<AccountingElement, Long, AccountingElementService> {

	@Autowired
	private ReportCategoryService reportCategoryService=null;
	@Autowired
	public AccountingElementController(AccountingElementService service) {
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
		Long reportCategoryId=MapUtils.getLong(searchParams, "EQ|reportCategory.$id");
		Page<AccountingElement> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		if(reportCategoryId!=null){
			ReportCategory reportCategory=this.reportCategoryService.get(reportCategoryId);
			if(reportCategory!=null){
				model.addAttribute("reportCategory", reportCategory);
			}
		}
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "stock.accountingElement.manager";
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
			AccountingElement entity=this.service.get(id);
			beforeInput(entity,model,request);
			model.addAttribute("accountingElement", entity);
		}
		return "stock.accountingElement.update";
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
	public String input(AccountingElement accountingElement,Model model,HttpServletRequest request){
		beforeInput(accountingElement,model,request);
		return "stock.accountingElement.update";
	}
	
	
    

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid AccountingElement accountingElement,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			beforeInput(accountingElement,model,request);
			return "stock.accountingElement.update";
		}else{
		   this.beforeSave(accountingElement,request);
		   this.service.save(accountingElement);
		   return "redirect:/stock/accountingElement/home";
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
		return "redirect:/stock/accountingElement/home";
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
			AccountingElement entity=this.service.get(id);
			model.addAttribute("accountingElement", entity);
		}
		return "stock.accountingElement.detail";
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
	protected void beforeInput(AccountingElement accountingElement,Model model,HttpServletRequest request){
		Map params=HttpUtil.getRequestParams(request);
		Long reportCategoryId=MapUtils.getLong(params, "reportCategoryid");
		if(reportCategoryId!=null){
			ReportCategory reportCategory=this.reportCategoryService.get(reportCategoryId);
			model.addAttribute("reportCategory", reportCategory);
			accountingElement.setReportCategory(reportCategory);
		}
		else{
		List<ReportCategory> reportCategories=this.reportCategoryService.getAll();
		model.addAttribute("reportCategories", reportCategories);
		}
	}
	/**
	 *
	 *保存数据前的回调函数
	 */
	protected void beforeSave(AccountingElement accountingElement,HttpServletRequest request){
		
	}

}
