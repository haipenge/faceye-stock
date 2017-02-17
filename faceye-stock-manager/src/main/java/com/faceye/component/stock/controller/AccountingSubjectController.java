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
import com.faceye.component.stock.entity.AccountingSubject;
import com.faceye.component.stock.service.AccountingElementService;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:stock<br>
 * 实体:AccountingSubject<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/stock/accountingSubject")
public class AccountingSubjectController extends BaseController<AccountingSubject, Long, AccountingSubjectService> {

	@Autowired
	private AccountingElementService accountElementService = null;

	@Autowired
	public AccountingSubjectController(AccountingSubjectService service) {
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
		Long accountingElementId = MapUtils.getLong(searchParams, "EQ|accountingElement.$id");
		if (accountingElementId != null) {
			AccountingElement accountingElement = this.accountElementService.get(accountingElementId);
			model.addAttribute("accountingElement", accountingElement);
		}
		Page<AccountingSubject> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "stock.accountingSubject.manager";
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
			
			AccountingSubject entity = this.service.get(id);
			beforeInput(entity,model, request);
			model.addAttribute("accountingSubject", entity);
		}
		return "stock.accountingSubject.update";
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
	public String input(AccountingSubject accountingSubject, Model model, HttpServletRequest request) {
		beforeInput(accountingSubject,model, request);
		return "stock.accountingSubject.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid AccountingSubject accountingSubject, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(accountingSubject,model, request);
			return "stock.accountingSubject.update";
		} else {
			this.beforeSave(accountingSubject, request);
			this.service.save(accountingSubject);
			return "redirect:/stock/accountingSubject/home";
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
		return "redirect:/stock/accountingSubject/home";
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
			AccountingSubject entity = this.service.get(id);
			model.addAttribute("accountingSubject", entity);
		}
		return "stock.accountingSubject.detail";
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
	protected void beforeInput(AccountingSubject accountingSubject,Model model, HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		Long accountingElementid = MapUtils.getLong(params, "accountingElementid");
		if (accountingElementid != null) {
			AccountingElement accountingElement = this.accountElementService.get(accountingElementid);
			model.addAttribute("accountingElement", accountingElement);
			accountingSubject.setAccountingElement(accountingElement);
		} else {
			List<AccountingElement> accountingElements = this.accountElementService.getAll();
			model.addAttribute("accountingElements", accountingElements);
		}
	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(AccountingSubject accountingSubject, HttpServletRequest request) {

	}

}
