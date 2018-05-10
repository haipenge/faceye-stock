package ${project.packageName}.component.${component.packageName}.controller.gen;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


import ${project.packageName}.component.${component.packageName}.entity.${entity.className};
import ${project.packageName}.component.${component.packageName}.service.${entity.className}Service;
<#list properties as property>
	<#if property.referenceEntity?? >
import ${project.packageName}.component.${property.referenceEntity.component.packageName}.entity.${property.referenceEntity.className};
import ${project.packageName}.component.${property.referenceEntity.component.packageName}.service.${property.referenceEntity.className}Service;
    </#if>
</#list>
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.GlobalEntity;

/**
 * 模块:${component.name}->${project.packageName}.compoent.${component.packageName}.controller.gen<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}:${entity.packageName}.entity.${entity.className}<br>
 * @author ${project.author} <br>
 * haipenge@gmail.com<br>
*  创建日期:${.now}<br>
 */

public abstract class ${entity.className}GenController extends BaseController<${entity.className}, Long, ${entity.className}Service> {
	<#list properties as property>
	  <#if property.referenceEntity?? >
	@Autowired
	protected ${property.referenceEntity.className}Service ${property.referenceEntity.className?uncap_first}Service=null; 
	  </#if>
	</#list>


	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:${project.author}<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期${.now}<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<${entity.className}> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("${component.packageName}.${entity.className?uncap_first}"));
		model.addAttribute("global",global);
		return "${component.packageName}.${entity.className?uncap_first}.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			beforeInput(model,request);
			${entity.className} ${entity.className?uncap_first}=this.service.get(id);
			model.addAttribute("${entity.className?uncap_first}", ${entity.className?uncap_first});
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("${component.packageName}.${entity.className?uncap_first}.edit"));
		model.addAttribute("global",global);
		return "${component.packageName}.${entity.className?uncap_first}.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping(value="/input")
	public String input(${entity.className} ${entity.className?uncap_first}, Model model,HttpServletRequest request){
		beforeInput(model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("${component.packageName}.${entity.className?uncap_first}.add"));
		model.addAttribute("global",global);
		return "${component.packageName}.${entity.className?uncap_first}.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid ${entity.className} ${entity.className?uncap_first},BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return "${component.packageName}.${entity.className?uncap_first}.update";
		}else{
		  this.beforeSave(${entity.className?uncap_first},request);
	      this.service.save(${entity.className?uncap_first});
	      this.afterSave(${entity.className?uncap_first},request);
		  return "redirect:/${component.packageName}/${entity.className?uncap_first}/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(id!=null){
			${entity.className} ${entity.className?uncap_first}=this.service.get(id);
				if(${entity.className?uncap_first}!=null){
					if(beforeRemove(${entity.className?uncap_first},model)){
						this.service.remove(${entity.className?uncap_first});		
						//MessageBuilder.getInstance().setMessage(model,${entity.className?uncap_first}.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
		}
		return "redirect:/${component.packageName}/${entity.className?uncap_first}/home";
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				${entity.className} ${entity.className?uncap_first}=this.service.get(Long.parseLong(id));
				if(${entity.className?uncap_first}!=null){
					if(beforeRemove(${entity.className?uncap_first},model)){
						this.service.remove(${entity.className?uncap_first});
						//MessageBuilder.getInstance().setMessage(model,${entity.className?uncap_first}.getName()+" "+ this.getI18N("global.remove.success"));		
					}
				}
			}
		}
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}
	
	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			${entity.className} ${entity.className?uncap_first}=this.service.get(id);
			model.addAttribute("${entity.className?uncap_first}", ${entity.className?uncap_first});
		}
		return "${component.packageName}.${entity.className?uncap_first}.detail";
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param ${entity.className?uncap_first}
	 * @param request
	 * @author:${project.author}
	 * 联系:haipenge@gmail.com
	 * 创建日期:${.now}
	 */
	protected void beforeSave(${entity.className} ${entity.className?uncap_first},HttpServletRequest request){
	  
	}
	
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(${entity.className} ${entity.className?uncap_first},Model model){
		boolean res=true;
		
		return res;
	}
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param ${entity.className?uncap_first}
	 * @param request
	 * @author:${project.author}
	 * 联系:haipenge@gmail.com
	 * 创建日期:${.now}
	 */
	protected void afterSave(${entity.className} ${entity.className?uncap_first},HttpServletRequest request){
	   
	}
	

}
