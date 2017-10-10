package com.faceye.component.generate.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.service.GenerateService;
import com.faceye.component.generate.service.ProjectService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
/**
 * 模块:generate<br>
 * 实体:Project<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/generate/project")
public class ProjectController extends BaseController<Project, Long, ProjectService> {
	@Autowired
    private GenerateService generateService=null;
	@Autowired
	public ProjectController(ProjectService service) {
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
		model.addAttribute("searchParams", searchParams);
		Page<Project> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		return "generate.project.manager";
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
			Project entity=this.service.get(id);
			model.addAttribute("project", entity);
		}
		return "generate.project.update";
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
	public String input(Project project,Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "generate.project.update";
	}
	
	
    

	/**
	 * 数据保存<br>
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid Project project,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()){
//			return "redirect:/generate/project/input";
			return "generate.project.update";
		}
		
		this.service.save(project);
		return "redirect:/generate/project/home";
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
		return "redirect:/generate/project/home";
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
			Project entity=this.service.get(id);
			model.addAttribute("project", entity);
		}
		return "generate.project.detail";
	}
	
	/**
	 * 生成项目
	 * @todo
	 * @param id
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月17日
	 */
	@RequestMapping("/generate")
	public String generate(@RequestParam(required=true) Long id){
		if(id!=null){
			this.generateService.generate(id);
		}
		return "redirect:/generate/project/detail/"+id;
	}
	@RequestMapping("/gzip")
	public String gzip(@RequestParam(required=true) Long id){
		if(id!=null){
			this.generateService.gzip(id);
		}
		return "redirect:/generate/project/detail/"+id;
	}
	/**
	 * 运行项目
	 * @todo
	 * @param id
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月17日
	 */
	@RequestMapping("/run")
	public String run(@RequestParam(required=true) Long id){
		if(id!=null){
			this.generateService.run(id);
		}
		return "redirect:/generate/project/detail/"+id;
	}
	
	
	/**
	 * 停止系统运行
	 * @todo
	 * @param id
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月24日
	 */
	@RequestMapping("/stop")
	public String stop(@RequestParam(required=true) Long id){
		if(id!=null){
			this.generateService.stop(id);
		}
		return "redirect:/generate/project/detail/"+id;
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

}
