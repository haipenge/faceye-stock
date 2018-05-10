package com.faceye.component.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.feature.util.AjaxResult;

@Controller
@RequestMapping("/stock")
public class InitSystemController {
	@Autowired
    private ReportCategoryService reportCategoryService=null;
	@RequestMapping("/init")
	@ResponseBody
	public String init(){
		this.reportCategoryService.initReportCategory();
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
}
