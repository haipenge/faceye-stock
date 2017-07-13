package com.faceye.test.component.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.test.feature.service.BaseServiceTestCase;

/**
 * 测试用例
 * 
 * @author haipenge
 *
 */
public class CrawlFinancialDataServiceTestCase extends BaseServiceTestCase {

	@Autowired
	private CrawlFinancialDataService crawlFinancialDataService = null;
	@Autowired
	private StockService stockService = null;

	private List<String> stockCodes = null;

	private Stock stock = null;

	@Before
	public void set() throws Exception {
		// stock = this.stockService.getStockByCode("000998");
		if (stock == null) {
			logger.error(">>FaceYe --> Stock is null.");
		}
		if (CollectionUtils.isEmpty(stockCodes)) {
			stockCodes = new ArrayList<String>(0);
		}
		this.initStockCodes();
	}

	@Test
	public void testCrawlStock() throws Exception {
		for (String code : stockCodes) {
			stock = this.stockService.getStockByCode(code);
			if (stock != null) {
				logger.debug(">>Stock is:" + stock.getName() + "(" + stock.getCode() + ")");
				this.crawlFinancialDataService.crawlStock(stock);
			} else {
				logger.debug(">>Stock:" + code + " is empty.");
			}
		}
		Assert.isTrue(true);
	}
	@Test
   public void testCrawlSingleStock() throws Exception{
	  String code="000998";
	  Stock stock=this.stockService.getStockByCode(code);
	  this.crawlFinancialDataService.crawlStock(stock,false);
	  Assert.isTrue(true);
   }

	/**
	 * 初始化股票代码
	 * 
	 * @throws Exception
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年1月4日 下午2:41:44
	 */
	private void initStockCodes() throws Exception {
//		List<Stock> stocks=this.stockService.getAll();
//		for(Stock stock:stocks){
//			stockCodes.add(stock.getCode());
//		}
		//以下为稀土行业29只股票
//		stockCodes.add("600058");
//		stockCodes.add("600111");
//		stockCodes.add("600193");
//		stockCodes.add("600206");
//		stockCodes.add("600259");
//		stockCodes.add("600330");
//		stockCodes.add("600362");
//		stockCodes.add("600366");
//		stockCodes.add("600392");
//		stockCodes.add("600478");
//		stockCodes.add("600549");
//		stockCodes.add("600614");
//		stockCodes.add("600980");
//		stockCodes.add("601600");
//		stockCodes.add("000511");
//		stockCodes.add("000636");
//		stockCodes.add("000758");
//		stockCodes.add("000795");
//		stockCodes.add("000831");
//		stockCodes.add("000969");
//		stockCodes.add("000970");
//		stockCodes.add("000993");
//		stockCodes.add("002056");
//		stockCodes.add("002057");
//		stockCodes.add("002240");
//		stockCodes.add("002352");
//		stockCodes.add("002600");
//		stockCodes.add("300127");
//		stockCodes.add("300224");
		//稀土行业股票结束

	}
}
