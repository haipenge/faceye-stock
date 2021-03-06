package com.faceye.test.component.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.junit.Assert;

import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.CategoryService;
import com.faceye.component.stock.service.StockService;
import com.faceye.test.feature.service.BaseServiceTestCase;

/**
 * Stock 服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class StockServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private StockService stockService = null;
	@Autowired
	private CategoryService categoryService = null;

	/**
	 * 初始化
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.assertTrue(stockService != null);
	}

	/**
	 * 清理
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		// this.stockService.removeAllInBatch();
	}

	/**
	 * 保存测试
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Stock entity = new Stock();
		this.stockService.save(entity);
		List<Stock> entites = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Stock entity = new Stock();
		this.stockService.save(entity);
		List<Stock> entites = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Stock entity = new Stock();
			this.stockService.save(entity);
		}
		List<Stock> entities = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Stock entity = new Stock();
		this.stockService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Stock e = this.stockService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Stock entity = new Stock();
		this.stockService.save(entity);
		this.stockService.remove(entity);
		List<Stock> entities = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Stock entity = new Stock();
			this.stockService.save(entity);
		}
		List<Stock> entities = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.stockService.removeAllInBatch();
		entities = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Stock entity = new Stock();
			this.stockService.save(entity);
		}
		this.stockService.removeAll();
		List<Stock> entities = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Stock> entities = new ArrayList<Stock>();
		for (int i = 0; i < 5; i++) {
			Stock entity = new Stock();

			this.stockService.save(entity);
			entities.add(entity);
		}
		this.stockService.removeInBatch(entities);
		entities = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Stock entity = new Stock();
			this.stockService.save(entity);
		}
		List<Stock> entities = this.stockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Stock entity = new Stock();
			this.stockService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Stock> page = this.stockService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.stockService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.stockService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Stock entity = new Stock();
			this.stockService.save(entity);
			id = entity.getId();
		}
		Stock e = this.stockService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Stock entity = new Stock();
			this.stockService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Stock> entities = this.stockService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}

	@Test
	public void testInit() throws Exception {
		this.stockService.initStocks();
		Page<Stock> stocks = this.stockService.getPage(null, 1, 2141);
		Assert.assertTrue(stocks != null && stocks.getContent().size() == 2141);
	}

	@Test
	public void testTimeTip() throws Exception {
		long time = System.currentTimeMillis();
		logger.debug("Time is:" + time + ",length is:" + ("" + time).length());
		Assert.assertTrue(true);
	}

	@Test
	public void testExport() throws Exception {
//		Category category = this.categoryService.getCategoryByName("一带一路");
		Category category = this.categoryService.getCategoryByName("新疆振兴");
		if (category != null) {
			Map searchParams = new HashMap();
			searchParams.put("EQ|category.$id", category.getId());
			this.stockService.export(searchParams, null);
		}
	}

	@Test
	public void testIsStockHaveCategory() throws Exception {
		List<Stock> stocks = this.stockService.getAll();
		for (Stock stock : stocks) {
			List<Category> categories = stock.getCategories();
			if (CollectionUtils.isEmpty(categories)) {
				logger.debug(">>Stock:" + stock.getName() + "(" + stock.getCode() + ") category is null.");
			}
		}
	}

	@Test
	public void testInitStock() throws Exception {
		this.stockService.initStocks();

	}
}
