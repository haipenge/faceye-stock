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
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.service.StockService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * DailyData  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class DailyDataServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private DailyDataService dailyDataService = null;
	
	@Autowired
	private StockService stockService=null;
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
		//org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
		Assert.isTrue(dailyDataService != null);
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
//		this.dailyDataService.removeAllInBatch();
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
		DailyData entity = new DailyData();
		this.dailyDataService.save(entity);
		List<DailyData> entites = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataService.save(entity);
		List<DailyData> entites = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		DailyData e = this.dailyDataService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataService.save(entity);
		this.dailyDataService.remove(entity);
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.dailyDataService.removeAllInBatch();
		entities = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		this.dailyDataService.removeAll();
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<DailyData> entities = new ArrayList<DailyData>();
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			
			this.dailyDataService.save(entity);
			entities.add(entity);
		}
		this.dailyDataService.removeInBatch(entities);
		entities = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<DailyData> page = this.dailyDataService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.dailyDataService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.dailyDataService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
			id = entity.getId();
		}
		DailyData e = this.dailyDataService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<DailyData> entities = this.dailyDataService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
	@Test
	@Rollback(false)
	public void testInitDailyData() throws Exception{
		String code="000099";
//		this.dailyDataService.initDailyData(code);
		this.dailyDataService.initDailyData();
		Page<DailyData> page=this.dailyDataService.getPage(null, 1, 10);
		Assert.isTrue(page!=null && page.getContent().size()==10);
	}
	@Test
	public void testInitAvgDailyData() throws Exception{
//		this.dailyDataService.computeDailyDataAvg("000099", 5);
		//this.dailyDataService.initDailyDataAvg();
	}
	
	@Test
	public void testCrawlDailyData() throws Exception{
		String code="000099";
		Stock stock=this.stockService.getStockByCode(code);
		this.dailyDataService.crawlDailyData(stock);
	}
	
	
}
