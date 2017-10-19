package com.faceye.component.stock.service.impl;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.StockRepository;
import com.faceye.component.stock.repository.mongo.customer.StockCustomerRepository;
import com.faceye.component.stock.service.CategoryService;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.util.StockConstants;
import com.faceye.component.stock.util.StockFetcher;
import com.faceye.feature.service.export.excel.ExcelService;
import com.faceye.feature.service.export.excel.data.DCell;
import com.faceye.feature.service.export.excel.data.DHeader;
import com.faceye.feature.service.export.excel.data.DRecord;
import com.faceye.feature.service.export.excel.data.DSheet;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.http.Http;
import com.faceye.feature.util.regexp.RegexpUtil;

@Service
public class StockServiceImpl extends BaseMongoServiceImpl<Stock, Long, StockRepository> implements StockService {

	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private StockCustomerRepository stockCustomerRepository = null;

	@Autowired
	private ExcelService excelService = null;

	@Autowired
	private DataStatService dataStatService = null;

	private DecimalFormat df = new DecimalFormat("######0.00");

	@Autowired
	public StockServiceImpl(StockRepository dao) {
		super(dao);
	}

	@Override
	public void initStocks() {
		// try {
		// String
		// path="/work/Work/FeatureWorkSpace/feature/faceye-stock/faceye-stock-manager/src/main/resources/stock/sz-sh-a-stocks.txt";
		// BufferedReader in = new BufferedReader(new FileReader(path));
		// List<String> lines = IOUtils.readLines(in);
		// if (CollectionUtils.isNotEmpty(lines)) {
		// for (String line : lines) {
		// this.initOneLinn(line, "");
		// }
		// }
		// String path =
		// "/work/Work/FeatureWorkSpace/feature/faceye-stock/faceye-stock-manager/src/main/resources/stock/sz-a-stocks.txt";
		// BufferedReader in = new BufferedReader(new FileReader(path));
		// List<String> lines = IOUtils.readLines(in);
		// if (CollectionUtils.isNotEmpty(lines)) {
		// for (String line : lines) {
		// this.initOneLinn(line, "sz");
		// }
		// }
		// path =
		// "/work/Work/FeatureWorkSpace/feature/faceye-stock/faceye-stock-manager/src/main/resources/stock/sh-a-stocks.txt";
		// in = new BufferedReader(new FileReader(path));
		// lines = IOUtils.readLines(in);
		// if (CollectionUtils.isNotEmpty(lines)) {
		// for (String line : lines) {
		// this.initOneLinn(line, "sh");
		// }
		// }

		// } catch (FileNotFoundException e) {
		// logger.error(">>FaceYe throws Exception: --->" + e.toString());
		// } catch (IOException e) {
		// logger.error(">>FaceYe throws Exception: --->" + e.toString());
		// }
		this.checkStockFromHexun();
		List<Stock> stocks=this.getAll();
		if(CollectionUtils.isNotEmpty(stocks)){
			for(Stock stock:stocks){
				this.crawlCategory(stock);
			}
		}
	}

	private void initOneLinn(String line, String market) {
		if (StringUtils.isNotEmpty(line)) {
			String[] stockInfo = line.split("	");
			if (null != stockInfo && stockInfo.length == 4) {
				String code = stockInfo[1];
				String name = stockInfo[2];
				// 行业
				String business = stockInfo[3];
				Stock stock = this.dao.getStockByCode(code);
				if (stock == null) {
					stock = new Stock();
					stock.setName(name);
					stock.setCode(code);
					stock.setBusiness(business);
					stock.setMarket(market);
				} else {
					stock.setMarket(market);
				}
				this.save(stock);
				logger.debug(">>FaceYe save stock :" + code + " -> " + name);

			}
		}
	}

	/**
	 * 分sina初始化股票<br>
	 * url:http://vip.stock.finance.sina.com.cn/q/go.php/vIR_RatingNewest/index.phtml?num=60&p=218<br>
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月16日 下午12:06:23
	 */
	private void initStockInfoBySina() {
		int page = 60;
		int index = 1;
		StockFetcher fetcher = new StockFetcher();
		for (int i = 1; i < 300; i++) {
			String url = "http://vip.stock.finance.sina.com.cn/q/go.php/vIR_RatingNewest/index.phtml?num=60&p=" + i;
			try {
				String content = fetcher.getContent(url);
			} catch (Exception e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
	}

	/**
	 * 从和讯财经检查初始化股票 http://quote.eastmoney.com/stocklist.html
	 * 
	 * @todo
	 * @author:@haipenge haipenge@gmail.com 2015年2月17日
	 */
	private void checkStockFromHexun() {
		StockFetcher fetcher = new StockFetcher();
		String url = "http://quote.eastmoney.com/stocklist.html";
		// String content = Http.getInstance().get(url, "gb2312");
		String content = "";
		try {
			content = fetcher.getContent("http://quote.eastmoney.com/stocklist.html");
			logger.debug(">>FaceYe --> fetch hexun stock content is:" + content);
		} catch (Exception e1) {
			logger.error(">>FaceYe Throws Exception:", e1);
		}
		if (StringUtils.isNotEmpty(content)) {
			try {
				List<Map<String, String>> codeNames = fetcher.distillStockNameAndCode(content);
				Category category = this.categoryService.getCategoryByName("默认");
				if (category == null) {
					category = new Category();
					category.setName("默认");
					category.setCode("default");
					this.categoryService.save(category);
				}
				int count = 0;
				if (CollectionUtils.isNotEmpty(codeNames)) {
					for (Map<String, String> map : codeNames) {
						String market = "";
						String code = map.keySet().iterator().next();
						market = StringUtils.substring(code, 0, 2);
						market = StringUtils.upperCase(market);
						code = StringUtils.substring(code, 2);
						String name = map.values().iterator().next();
						name = StringUtils.replace(name, "(", "");
						name = StringUtils.replace(name, ")", "");
						name = StringUtils.replace(name, code, "");
						logger.debug(">>FaceYe check code is:" + code);
						if (StringUtils.isNotEmpty(code) && (StringUtils.startsWith(code, "6")
								|| StringUtils.startsWith(code, "0") || StringUtils.startsWith(code, "3"))) {
							Stock stock = this.getStockByCode(code);
							count++;
							if (stock == null) {
								// if (StringUtils.startsWith(code, "0") ||
								// StringUtils.startsWith(code, "3")) {
								// market = "SZ";
								// }
								logger.debug(">>FaceYe will add stock is:" + code);
								stock = new Stock();
								stock.setName(name);
//								stock.setCategory(category);
								stock.setCode(code);
								stock.setMarket(market);
								this.save(stock);
							}
						}
					}
				}
				logger.debug(">>FaceYe --> stock count:" + count);
				logger.debug(">>FaceYe stock size is:" + this.getPage(null, 1, 1).getTotalElements());
			} catch (Exception e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
	}

	@Override
	public Stock getStockByCode(String code) {
		Stock stock = this.dao.getStockByCode(code);
		return stock;
	}

	/**
	 * 初始化股票分类
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月21日 上午10:43:44
	 */
	public boolean initStockCategory() {
		boolean res = false;
		// List<Stock> stocks = this.getAll();
		// for (Stock stock : stocks) {
		// String categoryName = StringUtils.isNotEmpty(stock.getBusiness()) ?
		// StringUtils.trim(stock.getBusiness()) : "Default";
		// Category category =
		// this.categoryService.getCategoryByName(categoryName);
		// if (category == null) {
		// category = new Category();
		// category.setName(categoryName);
		// this.categoryService.save(category);
		// }
		// stock.setCategory(category);
		// this.save(stock);
		// }
		res = true;
		return res;
	}

	@Override
	public Page<Stock> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver =
		// SimpleEntityPathResolver.INSTANCE;
		// EntityPath<T> entityPath = resolver.createPath(entityClass);
		// PathBuilder<T> builder = new PathBuilder<T>(entityPath.getType(),
		// entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate>
		// predicates=DynamicSpecifications.buildPredicates(searchParams,
		// entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		// Predicate predicate = DynamicSpecifications.builder(searchParams,
		// entityClass);
		// Sort sort = this.buildSort(searchParams);
		// if (predicate != null) {
		// logger.debug(">>FaceYe -->Query predicate is:" +
		// predicate.toString());
		// }
		// if(sort!=null){
		// logger.debug(">>FaceYe --> Query sort is:"+sort.toString());
		// }
		// Page<Stock> res = null;
		// if (size != 0) {
		// Pageable pageable = new PageRequest(page, size, sort);
		// res = this.dao.findAll(predicate, pageable);
		// } else {
		// // OrderSpecifier<Comparable> orderPOrderSpecifier=new
		// OrderSpecifier<Comparable>(new Order(), new NumberExpression<T>("id")
		// {
		// // })
		// List<Stock> items = (List) this.dao.findAll(predicate, sort);
		// res = new PageImpl<Stock>(items);
		// }
		// return res;
		Page<Stock> res = this.stockCustomerRepository.getPage(searchParams, page, size);
		return res;
	}

	@Override
	public void export(Map searchParams,OutputStream stream) {
		Page<Stock> stocks = this.getPage(searchParams, 1, 0);
		DSheet dsheet = new DSheet();
		// 构建表头
		DHeader dheader = new DHeader();
		dheader.add(buildDCell("名称"));
		dheader.add(buildDCell("编码"));
		dheader.add(buildDCell("PB"));
		dheader.add(buildDCell("PE"));
		dheader.add(buildDCell("DPE"));
		dheader.add(buildDCell("概念行业"));
		dheader.add(buildDCell("股价"));
		dheader.add(buildDCell("今日涨跌"));
		dheader.add(buildDCell("30天最高价"));
		dheader.add(buildDCell("30天最低价"));
		dheader.add(buildDCell("30天振幅"));
		for (int i = 0; i < 5; i++) {
			dheader.add(buildDCell("核心利润率" + i));
		}
		for (int i = 0; i < 5; i++) {
			dheader.add(buildDCell("EPS" + i));
		}
		for (int i = 0; i < 5; i++) {
			dheader.add(buildDCell("BPS" + i));
		}
		for (int i = 0; i < 5; i++) {
			dheader.add(buildDCell("DPS" + i));
		}
		dsheet.setHeader(dheader);
		// 填充数据

		for (Stock stock : stocks) {
			DRecord dr = new DRecord();
			dr.add(buildDCell(stock.getName()));
			dr.add(buildDCell(stock.getCode()));
			if(stock.getDailyStat()!=null){
			dr.add(buildDCell(stock.getDailyStat().getPb() == null ? "" : df.format(stock.getDailyStat().getPb())));
			dr.add(buildDCell(stock.getDailyStat().getPe() == null ? "" : df.format(stock.getDailyStat().getPe())));
			dr.add(buildDCell(
					stock.getDailyStat().getDynamicPe() == null ? "" : df.format(stock.getDailyStat().getDynamicPe())));
			}
			List<Category> categories = stock.getCategories();
			StringBuffer sb = new StringBuffer();
			if (CollectionUtils.isNotEmpty(categories)) {
				for (Category c : categories) {
					sb.append(c.getName());
					sb.append(",");
				}
			}
			if (sb != null && StringUtils.isNotEmpty(sb.toString())) {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			dr.add(buildDCell(sb.toString()));
			if(stock.getDailyStat()!=null){
			dr.add(buildDCell(formatValue(stock.getDailyStat().getTodayPrice())));
			dr.add(buildDCell(stock.getDailyStat().getTodayIncreaseRate() == null ? ""
					: df.format(stock.getDailyStat().getTodayIncreaseRate() * 100) + "%"));
			dr.add(buildDCell(stock.getDailyStat().getTopPriceOf30Day() == null ? ""
					: df.format(stock.getDailyStat().getTopPriceOf30Day())));
			dr.add(buildDCell(stock.getDailyStat().getLowPriceOf30Day() == null ? ""
					: df.format(stock.getDailyStat().getLowPriceOf30Day())));
			dr.add(buildDCell(stock.getDailyStat().getPriceAmplitude() == null ? ""
					: df.format(stock.getDailyStat().getPriceAmplitude() * 100) + "%"));
			}
			dsheet.add(dr);
			// 取得财报分析数据
			Map dataStatParams = new HashMap();
			dataStatParams.put("EQ|stockId", stock.getId());
			dataStatParams.put("EQ|type", StockConstants.REPORT_TYPE_YEAR);
			// dataStatParams.put("LT|dateCycle", new
			// Date(System.currentTimeMillis()-5*365*24*60*60*1000L));
			dataStatParams.put("SORT|dateCycle", "desc");
			List<DataStat> dataStats = this.dataStatService.getPage(dataStatParams, 1, 5).getContent();
			if (CollectionUtils.isNotEmpty(dataStats)) {
				// 核心利润率
				for (DataStat dataStat : dataStats) {
					dr.add(buildDCell(formatValue(dataStat.getCoreProfitMargin() * 100) + "%"));
				}
				if (dataStats.size() < 5) {
					for (int i = 0; i < 5 - dataStats.size(); i++) {
						dr.add(buildDCell("--"));
					}
				}
				//EPS
				for (DataStat dataStat : dataStats) {
					dr.add(buildDCell(formatValue(dataStat.getEps())));
				}
				if (dataStats.size() < 5) {
					for (int i = 0; i < 5 - dataStats.size(); i++) {
						dr.add(buildDCell("--"));
					}
				}
				//BPS
				for (DataStat dataStat : dataStats) {
					dr.add(buildDCell(formatValue(dataStat.getBps())));
				}
				if (dataStats.size() < 5) {
					for (int i = 0; i < 5 - dataStats.size(); i++) {
						dr.add(buildDCell("--"));
					}
				}
				//DPS
				for (DataStat dataStat : dataStats) {
					dr.add(buildDCell(formatValue(dataStat.getDps())));
				}
				if (dataStats.size() < 5) {
					for (int i = 0; i < 5 - dataStats.size(); i++) {
						dr.add(buildDCell("--"));
					}
				}
			} else {
				for (int i = 0; i < 20; i++) {
					dr.add(buildDCell("--"));
				}
			}
		}
		excelService.export(dsheet,stream);
	}

	private DCell buildDCell(String v) {
		DCell cell = new DCell();
		cell.setV(v);
		return cell;
	}

	private String formatValue(Double value) {
		String res = "";
		if (value != null) {
			res = df.format(value);
		}
		return res;
	}
	
	/**
	 * 爬取股票所属分类
	 * 
	 * @param stock
	 *            Url:http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/000998/menu_num/5.phtml
	 */
	private void crawlCategory(Stock stock) {
		String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpOtherInfo/stockid/000998/menu_num/5.phtml";
		url = StringUtils.replace(url, "000998", stock.getCode());
		String content = Http.getInstance().get(url, "gb2312");
		String regexp = "<table class=\"comInfo1\" width=\"60%\" align=\"center\">([\\S\\s]*?)</table>";
		try {
			List<Map<String, String>> tables = RegexpUtil.match(content, regexp);
			if (CollectionUtils.isNotEmpty(tables) && tables.size() == 2) {
				// 证监会行业分类
				Map<String, String> businessTable = tables.get(0);
				// 概念行业分类
				Map<String, String> categoriesTable = tables.get(1);

				// 证监会行业分类匹配
				String business = "Default";
				String businessContent = businessTable.get("1");
				String businessRegexp = "<td class=\"ct\" align=\"center\">([\\S\\s]*?)</td>";
				List<Map<String, String>> businessMatchers = RegexpUtil.match(businessContent, businessRegexp);
				if (CollectionUtils.isNotEmpty(businessMatchers) && businessMatchers.size() == 2) {
					business = StringUtils.trim(businessMatchers.get(0).get("1"));
				}
				//概念行业分类
				List<Category> categories=new ArrayList<>(0);
				String categoriesContent=categoriesTable.get("1");
				String categoriesRegexp="<td class=\"ct\" align=\"center\">([\\S\\s]*?)</td>";
				List<Map<String,String>> categoriesMatchers=RegexpUtil.match(categoriesContent, categoriesRegexp);
				if(CollectionUtils.isNotEmpty(categoriesMatchers)){
					for(int i=0;i<categoriesMatchers.size();i+=2){
						String categoryName=categoriesMatchers.get(i).get("1");
						Category category=this.categoryService.getCategoryByName(categoryName);
						if(category!=null){
							categories.add(category);
						}else{
							category=new Category();
							category.setName(categoryName);
							this.categoryService.save(category);
							categories.add(category);
						}
					}
				}
				stock.setBusiness(business);
				stock.setCategories(categories);
				this.save(stock);
			}
		} catch (Exception e) {
			logger.error(">>Exception:" + e);
		}
	}

}
/** @generate-service-source@ **/
