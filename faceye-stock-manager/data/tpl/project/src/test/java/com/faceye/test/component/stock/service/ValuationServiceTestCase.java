package com.faceye.test.component.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.junit.Assert;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.entity.Valuation;
import com.faceye.component.stock.service.CrawlFinancialDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.component.stock.service.ValuationService;
import com.faceye.feature.util.DateUtil;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Valuation  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ValuationServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ValuationService valuationService = null;
	@Autowired
	private CrawlFinancialDataService crawlFinancialDataService=null;
	@Autowired
	private StockService stockService=null;
	
	private String stockCode=null;
	private Stock stock=null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		//美的
		stockCode="000333";
		//茅台
		stockCode="600519";
		stock=this.stockService.getStockByCode(stockCode);
		if(stock!=null){
			//this.crawlFinancialDataService.crawlStock(stock, false);
		}
		Assert.assertTrue(valuationService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.valuationService.removeAllInBatch();
	}

	@Test
	public void testDoValuation() throws Exception{
		StringBuilder sb=new StringBuilder();
		Double [] roces=new Double[]{0.1,0.11,0.12,0.13,0.14,0.15,0.16,0.17,0.18,0.19,0.20,0.21,0.22,0.23,0.24,0.25,0.26,0.27,0.28,0.29,0.30,0.45,0.65,0.85,1.5,2.0,2.5};
		Map searchParams=new HashMap();
		searchParams.put("EQ|stockId", stock.getId());
		searchParams.put("SORT|period", "asc");
		for(Double roce:roces){
			this.valuationService.doStockValuation(stock.getId(), roce, null);
			List<Valuation> valuations=this.valuationService.getPage(searchParams, 1, 0).getContent();
			sb.append(this.valuationResult(valuations, roce));
		}
		logger.error(sb.toString());
		Assert.assertTrue(StringUtils.isNotEmpty(sb.toString()));
	}
	
	private String valuationResult(List<Valuation> valuations,Double roce){
		StringBuilder sb=new StringBuilder();
		sb.append("\n");
		sb.append("-----------------------------"+stock.getName()+" ("+stock.getCode()+") "+"roce is:"+roce+" ,估值结果-----------------------------------\n");
		if(CollectionUtils.isNotEmpty(valuations)){
			sb.append("-----");
			sb.append("TotalValue");
			sb.append("-----");
			sb.append("ROCE");
			sb.append("-----");
			sb.append("Date");
			sb.append("-----");
			sb.append("DiscountRate");
			sb.append("-----");
			sb.append("\n");
			int count =0;
			for(Valuation valuation:valuations){
				sb.append(count++ +"-----  ");
				sb.append(valuation.getTotalValue()/(100000000));
				sb.append("-----");
				sb.append(valuation.getRoce());
				sb.append("-----");
				sb.append(DateUtil.formatDate(valuation.getPeriod(),"yyyy-MM-dd"));
				sb.append("-----");
				sb.append(valuation.getDiscountRate());
				sb.append("-----\n");
			}
		}
		return sb.toString();
	}
	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		List<Valuation> entites = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		List<Valuation> entites = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		List<Valuation> entities = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Valuation e = this.valuationService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		this.valuationService.remove(entity);
		List<Valuation> entities = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		List<Valuation> entities = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.valuationService.removeAllInBatch();
		entities = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		this.valuationService.removeAll();
		List<Valuation> entities = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Valuation> entities = new ArrayList<Valuation>();
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			
			this.valuationService.save(entity);
			entities.add(entity);
		}
		this.valuationService.removeInBatch(entities);
		entities = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		List<Valuation> entities = this.valuationService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Valuation> page = this.valuationService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.valuationService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.valuationService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
			id = entity.getId();
		}
		Valuation e = this.valuationService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Valuation> entities = this.valuationService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
