package com.faceye.component.@component.name@.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.@component.name@.entity.@entity.name@;
import com.faceye.component.@component.name@.service.@entity.name@Service;


import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.MathUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.regexp.RegexpUtil;
/**
 * 模块:@component.name@<br>
 * 实体:@entity.name@<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/@component.name@/@entity.config.name@")
public class @entity.name@Controller extends BaseController<@entity.name@, Long, @entity.name@Service> {

	@Autowired
	public @entity.name@Controller(@entity.name@Service service) {
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
		Map searchParams=HttpUtil.getRequestParams(request);
		Page<@entity.name@> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		return "@component.name@.@entity.config.name@.manager";
	}

	/**
	 * 转向编辑或新增页面
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model) {
		if(id!=null){
			@entity.name@ entity=this.service.get(id);
			model.addAttribute("@entity.config.name@", entity);
		}
		return "@component.name@.@entity.config.name@.update";
	}
	
	/**
	 * 转向新增页面
	 * @todo
	 * @param model
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月27日
	 */
	@RequestMapping(value="/input")
	public String input(@entity.name@ @entity.config.name@,Model model,HttpServletRequest request){
		this.beforeInput(model,request)
		return "@component.name@.@entity.config.name@.update";
	}
	

	/**
	 * 数据保存
	 */
	@RequestMapping("/save")
	public String save(@Valid @entity.name@ @entity.config.name@,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			this.beforeInput(model,request)
			return "@component.name@.@entity.config.name@.update";
		}else{
		   this.service.save(@entity.config.name@);
		   return "redirect:/@component.name@/@entity.config.name@/home";
		}
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
		if(id!=null){
			this.service.remove(id);
		}
		return "redirect:/@component.name@/@entity.config.name@/home";
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
	 * 取得数据明细
	 * @todo
	 * @param id
	 * @param model
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月26日
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			@entity.name@ @entity.config.name@=this.service.get(id);
			model.addAttribute("@entity.config.name@", @entity.config.name@);
		}
		return "@component.name@.@entity.config.name@.detail";
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
	protected void beforeSave(@entity.name@ @entity.config.name@,HttpServletRequest request){
		
	}


}
