package com.faceye.component.generate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.entity.Validate;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.component.generate.service.DataTypeService;
import com.faceye.component.generate.service.EntityService;
import com.faceye.component.generate.service.PropertyService;
import com.faceye.component.generate.service.ValidateService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:generate<br>
 * 实体:Property<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/generate/property")
public class PropertyController extends BaseController<Property, Long, PropertyService> {
	@Autowired
	private EntityService entityService = null;
	@Autowired
	private DataTypeService dataTypeService = null;
	@Autowired
	private ValidateService validateService = null;
	@Autowired
	private ComponentService componentService = null;

	@Autowired
	public PropertyController(PropertyService service) {
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
		Map searchParams = HttpUtil.getRequestParams(request);
		model.addAttribute("searchParams", searchParams);
		Long entityId = MapUtils.getLong(searchParams, "EQ|entity.$id");
		if (entityId != null) {
			Entity entity = this.entityService.get(entityId);
			model.addAttribute("entity", entity);
		}
		Page<Property> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		return "generate.property.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			Property entity = this.service.get(id);
			beforeInput(model, request,entity);
			model.addAttribute("property", entity);
		}
		return "generate.property.update";
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
	@RequestMapping(value = "/input")
	public String input(Property property, Model model, HttpServletRequest request) {
		beforeInput(model, request, property);
		return "generate.property.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Property property, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request, property);
			return "generate.property.update";
		} else {
			this.beforeSave(property, request);
			this.service.save(property);
			return "redirect:/generate/property/home?EQ|entity.$id=" + property.getEntity().getId();
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
		Long entityId = null;
		if (id != null) {
			Property property = this.service.get(id);
			entityId = property.getEntity().getId();
			this.service.remove(property);
		}
		return "redirect:/generate/property/home?EQ|entity.$id=" + entityId;
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
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Property entity = this.service.get(id);
			model.addAttribute("property", entity);
		}
		return "generate.property.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request, Property property) {
		String entityId = request.getParameter("entity.id");
		if (StringUtils.isNotEmpty(entityId)) {
			Entity entity = this.entityService.get(Long.parseLong(entityId));
			// model.addAttribute("entity", entity);
			property.setEntity(entity);
		}
		Map searchParams = new HashMap();
		searchParams.put("EQ|project.$id", property.getEntity().getComponent().getProject().getId());
		List<Component> components = this.componentService.getPage(searchParams, 1, 0).getContent();
		List<Long> componentIds=new ArrayList<Long>(0);
		if (CollectionUtils.isNotEmpty(components)) {
			for (Component component : components) {
				componentIds.add(component.getId());
			}
		}
		searchParams = new HashMap();
		searchParams.put("IN|component.$id", componentIds.toArray(new Long[componentIds.size()]));
		List<Entity> entities = this.entityService.getPage(searchParams, 1, 0).getContent();
		model.addAttribute("entities", entities);
		List<DataType> dataTypes = this.dataTypeService.getAll();
		model.addAttribute("dataTypes", dataTypes);
		List<Validate> validates = this.validateService.getAll();
		model.addAttribute("validates", validates);

	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param property
	 * @param request
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年4月11日
	 */
	protected void beforeSave(Property property, HttpServletRequest request) {
		String[] validateIds = request.getParameterValues("validate.id");
		List<Validate> validates = new ArrayList<Validate>();
		if (validateIds != null) {
			for (String validateId : validateIds) {
				Validate validate = this.validateService.get(Long.parseLong(validateId));
				if (null != validate) {
					validates.add(validate);
				}
			}
		}
		property.setValidates(validates);
	}

}
