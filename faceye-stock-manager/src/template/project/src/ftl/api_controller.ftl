package ${project.packageName}.component.${component.packageName}.controller;

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
 * 模块:${component.name}->${project.packageName}.compoent.${component.packageName}.controller<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}:${entity.packageName}.entity.${entity.className}<br>
 * @author ${project.author} <br>
 * haipenge@gmail.com<br>
*  创建日期:${.now}<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/${component.packageName}/${entity.className?uncap_first}")
public class ${entity.className}Controller extends BaseController<${entity.className}, Long, ${entity.className}Service> {
	<#list properties as property>
	  <#if property.referenceEntity?? >
	@Autowired
	private ${property.referenceEntity.className}Service ${property.referenceEntity.className?uncap_first}Service=null; 
	  </#if>
	</#list>
	
	@Autowired
	public ${entity.className}Controller(${entity.className}Service service) {
		super(service);
	}

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
	@ResponseBody
	public Page<${entity.className}> home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<${entity.className}> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return page;
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
	@ResponseBody
	public ${entity.className} edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		beforeInput(model,request);
		${entity.className} ${entity.className?uncap_first}=this.service.get(id);
		return ${entity.className?uncap_first};
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
		return "${component.packageName}.${entity.className?uncap_first}.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Valid ${entity.className} ${entity.className?uncap_first},BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return AjaxResult.getInstance().buildDefaultResult(false);
		}else{
		  this.beforeSave(${entity.className?uncap_first},request);
	      this.service.save(${entity.className?uncap_first});
	      this.afterSave(${entity.className?uncap_first},request);
	      return  AjaxResult.getInstance().buildDefaultResult(true);
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
	@ResponseBody
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(id!=null){
			this.service.remove(id);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
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
	 * @author:${project.author}<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:${.now}<br>
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public ${entity.className} detail(@PathVariable Long id,Model model){
		${entity.className} ${entity.className?uncap_first}=this.service.get(id);
		return ${entity.className?uncap_first};
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
		<#list properties as property>
		  <#if property.referenceEntity?? >
		    List<${property.referenceEntity.className}> ${property.referenceEntity.className?uncap_first}s=this.${property.referenceEntity.className?uncap_first}Service.getAll();
		    model.addAttribute("${property.referenceEntity.className?uncap_first}s", ${property.referenceEntity.className?uncap_first}s);
		  </#if>
		</#list>
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
	   <#list properties as property>
	      <#if property.referenceEntity??>
	        <#if property.isMulti>
	           String [] ${property.referenceEntity.className?uncap_first}Ids=request.getParameterValues("${property.referenceEntity.className?uncap_first}.id");
	           List<${property.referenceEntity.className}> ${property.referenceEntity.className?uncap_first}s=new ArrayList<${property.referenceEntity.className}>();
		       if(${property.referenceEntity.className?uncap_first}Ids!=null){
			      for(String ${property.referenceEntity.className?uncap_first}Id:${property.referenceEntity.className?uncap_first}Ids){
				     ${property.referenceEntity.className} ${property.referenceEntity.className?uncap_first}=this.${property.referenceEntity.className?uncap_first}Service.get(Long.parseLong(${property.referenceEntity.className?uncap_first}Id));
				    if(null!=${property.referenceEntity.className?uncap_first}){
					   ${property.referenceEntity.className?uncap_first}s.add(${property.referenceEntity.className?uncap_first});
				    }
			      }
		        }
		       ${entity.className?uncap_first}.set${property.referenceEntity.className}s(${property.referenceEntity.className?uncap_first}s);
	        </#if>
	      </#if>
	   </#list>
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
