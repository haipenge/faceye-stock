package com.faceye.component.stock.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.data.domain.PageImpl;
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
import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.AccountingElementService;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.component.stock.service.StockService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:stock<br>
 * 实体:FinancialData<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/stock/financialData")
public class FinancialDataController extends BaseController<FinancialData, Long, FinancialDataService> {
	@Autowired
	private ReportCategoryService reportCategoryService = null;
	@Autowired
	private StockService stockService = null;
	@Autowired
	private AccountingElementService accountingElementService = null;
	@Autowired
	private AccountingSubjectService accountingSubjectService = null;
	@Autowired
	private CrawlFinancialDataService crawlFinancialDataService=null;

	@Autowired
	public FinancialDataController(FinancialDataService service) {
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
		Long reportCategoryId = MapUtils.getLong(searchParams, "reportCategoryId");
		if (reportCategoryId != null) {

		}
		Page<FinancialData> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		List<ReportCategory> reportCategories = this.reportCategoryService.getAll();
		model.addAttribute("reportCategories", reportCategories);
		Stock stock = null;
		Long stockid = MapUtils.getLong(searchParams, "stockid");
		if (stockid != null) {
			stock = this.stockService.get(stockid);
			model.addAttribute("stock", stock);
		}
		return "stock.financialData.manager";
	}

	/**
	 * 财务报表
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月30日 下午3:19:50
	 */
	@RequestMapping("/report")
	public String report(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Long reportCategoryId = MapUtils.getLong(searchParams, "reportCategoryId");
		if (reportCategoryId == null) {
			reportCategoryId = 3L;// 利润表
		}
		Long stockId = MapUtils.getLong(searchParams, "stockId");
		String date = MapUtils.getString(searchParams, "date");// Year
		// 报表分类，年报，季报？0（年报），1（一季报），2，3
		String type = MapUtils.getString(searchParams, "type");
		if (StringUtils.isEmpty(type)) {
			type = "0";
		}
		ReportCategory reportCategory = null;
		if (reportCategoryId != null) {
			reportCategory = this.reportCategoryService.get(reportCategoryId);
		} else {
			// 财务摘要
			reportCategory = this.reportCategoryService.getReportCategoryByCode("FINANCIAL_SUMMARY");
		}
		Stock stock = this.stockService.get(stockId);
		Map elementsParams = new HashMap();
		elementsParams.put("EQ|reportCategory.$id", reportCategory.getId());
		List<AccountingElement> accountingElements = this.accountingElementService.getPage(elementsParams, 1, 0).getContent();
		Map subjectsParams = new HashMap();
		List<Long> elementsIds = new ArrayList<Long>(0);
		for (AccountingElement element : accountingElements) {
			elementsIds.add(element.getId());
		}
		subjectsParams.put("IN|accountingElement.$id", elementsIds.toArray(new Long[elementsIds.size()]));
		List<AccountingSubject> accountingSubjects = this.accountingSubjectService.getPage(subjectsParams, 1, 0).getContent();
		if (StringUtils.isEmpty(date)) {
			date = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		}
		List<Long> subjectIds = new ArrayList<Long>(0);
		for (AccountingSubject subject : accountingSubjects) {
			subjectIds.add(subject.getId());
		}

		Date startDate = new Date(DateUtil.getDateFromString(date, "yyyy-MM-dd HH:mm:ss").getTime() - 6 * 365 * 24 * 60 * 60 * 1000L);
		Date endDate = DateUtil.getDateFromString(date + "-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");

		List<Date> dates = new ArrayList<Date>(0);

		searchParams.put("GTE|date", startDate);
		searchParams.put("LTE|date", endDate);
		searchParams.put("IN|accountingSubjectId", subjectIds.toArray(new Long[subjectIds.size()]));
		searchParams.put("EQ|stockId", stockId);

		List<FinancialData> filteredDatas = new ArrayList<FinancialData>(0);
		Page<FinancialData> page = this.service.getPage(searchParams, getPage(searchParams), 0);
		if (page != null && CollectionUtils.isNotEmpty(page.getContent())) {
			Map<String, Date> map = new HashMap();
			for (FinancialData data : page.getContent()) {
				Date lineDate = data.getDate();
				String dateStr = DateUtil.formatDate(lineDate, "yyyy-MM-dd");
				int month = lineDate.getMonth();
				// 报表类型（年，季）
				if (StringUtils.isNotEmpty(type)) {
					if (StringUtils.equals(type, "0") && month == 11) {
						if (!map.containsKey(dateStr)) {
							map.put(dateStr, lineDate);
							dates.add(lineDate);
						}
						filteredDatas.add(data);
					} else if (StringUtils.equals(type, "1") && month == 2) {
						if (!map.containsKey(dateStr)) {
							map.put(dateStr, lineDate);
							dates.add(lineDate);
						}
						filteredDatas.add(data);
					} else if (StringUtils.equals(type, "2") && month == 5) {
						if (!map.containsKey(dateStr)) {
							map.put(dateStr, lineDate);
							dates.add(lineDate);
						}
						filteredDatas.add(data);
					} else if (StringUtils.equals(type, "3") && month == 8) {
						if (!map.containsKey(dateStr)) {
							map.put(dateStr, lineDate);
							dates.add(lineDate);
						}
						filteredDatas.add(data);
					}
				} else {
					if (!map.containsKey(dateStr)) {
						map.put(dateStr, lineDate);
						dates.add(lineDate);
						filteredDatas.add(data);
					}
				}
			}
		}
		model.addAttribute("dates", dates);
		this.statZiChanFuZhaiBiao(filteredDatas);
		this.statLiRunBiao(filteredDatas);
		page = new PageImpl(filteredDatas);
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		List<ReportCategory> reportCategories = this.reportCategoryService.getAll();
		model.addAttribute("reportCategories", reportCategories);
		model.addAttribute("reportCategory", reportCategory);
		model.addAttribute("accountingElements", accountingElements);
		model.addAttribute("accountingSubjects", accountingSubjects);
		model.addAttribute("stock", stock);
		return "stock.financialData.report";
	}

	/**
	 * 对资产负债表做同型分析
	 * 
	 * @param datas
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月13日 下午4:58:02
	 */
	private void statZiChanFuZhaiBiao(List<FinancialData> datas) {
		// 总资产 accountingSubject.id=189,code:cbsheet46
		Double total = 0.0D;
		Map<String, Double> map = new HashMap<String, Double>();
		// 找到总资产
		for (FinancialData data : datas) {
			if (data != null && data.getAccountingSubjectId().compareTo(189L) == 0) {
				total = data.getData();
				String date = DateUtil.formatDate(data.getDate(), "yyyy-MM-dd");
				if (!map.containsKey(date)) {
					map.put(date, total);
				}
			}
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Double value = map.get(key);
			if (value.compareTo(0.0D) > 0) {
				for (FinancialData data : datas) {
					Double sData = data.getData();
					if (sData != null && sData.compareTo(0.0D) > 0) {
						String date = DateUtil.formatDate(data.getDate(), "yyyy-MM-dd");
						if (StringUtils.equals(date, key)) {
							Double rate = data.getData() / value;
							// NumberFormat formatter=NumberFormat.getNumberInstance();
							// String s=formatter.format(rate);
							data.setRate(rate);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 对利润表做同型分析
	 * @param datas
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月15日 下午8:56:24
	 */
	private void statLiRunBiao(List<FinancialData> datas){
		Map<String, Double> map = new HashMap<String, Double>();
		// 找到营业总成本
		for (FinancialData data : datas) {
			if (data != null && data.getAccountingSubjectId().compareTo(96L) == 0) {
				Double total = data.getData();
				String date = DateUtil.formatDate(data.getDate(), "yyyy-MM-dd");
				if (!map.containsKey(date)) {
					map.put(date, total);
				}
			}
		}
		//对利润表做同型分析
		Iterator<String> it=map.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			Double value=map.get(key);
			for(FinancialData data : datas){
				Double sData =data.getData();
				String date=DateUtil.formatDate(data.getDate(), "yyyy-MM-dd");
				if(sData!=null && StringUtils.equals(date, key)&& sData.compareTo(0D)>0){
					Double rate = data.getData() / value;
					data.setRate(rate);
				}
			}
		}
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
			beforeInput(model, request);
			FinancialData entity = this.service.get(id);
			model.addAttribute("financialData", entity);
		}
		return "stock.financialData.update";
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
	public String input(FinancialData financialData, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "stock.financialData.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid FinancialData financialData, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "stock.financialData.update";
		} else {
			this.beforeSave(financialData, request);
			this.service.save(financialData);
			return "redirect:/stock/financialData/home";
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
		return "redirect:/stock/financialData/home";
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
			FinancialData entity = this.service.get(id);
			model.addAttribute("financialData", entity);
		}
		return "stock.financialData.detail";
	}

	/**
	 * 报表数据 查询
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月28日 下午12:28:31
	 */
	@RequestMapping("/chartsQuery")
	@ResponseBody
	public List<FinancialData> chartsQuery(HttpServletRequest request) {
		List<FinancialData> datas = null;
		Map params=HttpUtil.getRequestParams(request);
		Long stockId=MapUtils.getLong(params, "stockId");
		Long accountingSubjectId=MapUtils.getLong(params, "accountingSubjectId");
		Map searchParams=new HashMap();
		searchParams.put("EQ|stockId", stockId);
		searchParams.put("EQ|accountingSubjectId", accountingSubjectId);
		datas=this.service.getPage(searchParams, 0, 0).getContent();
		return datas;
	}
	
	/**
	 * 爬取股票财报数据
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月22日 下午3:13:48
	 */
	@RequestMapping("/crawlStockFinancialData")
	@ResponseBody
	public String crawlStockFinancialData(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		Long id = MapUtils.getLong(params, "id");
		if (id != null) {
			Stock stock = this.stockService.get(id);
			if (stock != null) {
				this.crawlFinancialDataService.crawlStock(stock);
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
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
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(FinancialData financialData, HttpServletRequest request) {

	}

}
