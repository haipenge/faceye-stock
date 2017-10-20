package com.faceye.test.component.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.DailyDataService;
import com.faceye.component.stock.service.DailyStatService;
import com.faceye.component.stock.service.StarDataStatService;
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
	
	@Autowired
	private DailyStatService dailyStatService=null;
	@Autowired
	private StarDataStatService starDataStatService=null;
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
		Assert.assertTrue(dailyDataService != null);
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
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataService.save(entity);
		List<DailyData> entites = this.dailyDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		DailyData e = this.dailyDataService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataService.save(entity);
		this.dailyDataService.remove(entity);
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.dailyDataService.removeAllInBatch();
		entities = this.dailyDataService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		this.dailyDataService.removeAll();
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
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
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		List<DailyData> entities = this.dailyDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			DailyData entity = new DailyData();
			this.dailyDataService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<DailyData> page = this.dailyDataService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.dailyDataService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.dailyDataService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

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
		Assert.assertTrue(e != null);
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
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
	@Test
	@Rollback(false)
	public void testInitDailyData() throws Exception{
		String code="000099";
		code="600518";
		this.dailyDataService.initDailyData(code);
//		this.dailyDataService.initDailyData();
		Page<DailyData> page=this.dailyDataService.getPage(null, 1, 10);
		Assert.assertTrue(page!=null && page.getContent().size()==10);
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
	
	@Test
	public void computeDailyDataLines() throws Exception{
		String code="600518";
//		this.dailyDataService.computeDailyDataLines();
		this.dailyDataService.computeDailyDataLines(this.stockService.getStockByCode(code));
	}
	
	
	/**
	 * 清除股票每日数据
	 * @throws Exception
	 */
	@Test
	public void testRemoveDailyData() throws Exception{
		String code="600518";
		Stock stock=this.stockService.getStockByCode(code);
		boolean res=false;
		if(stock!=null){
			this.dailyDataService.removeDailyDataByStock(stock.getId());
			Map dailyDataParams=new HashMap();
			dailyDataParams.put("EQ|stockId", stock.getId());
			Page<DailyData> dailyDatas=this.dailyDataService.getPage(dailyDataParams, 1, 0);
			Page<DailyStat> dailyStats=this.dailyStatService.getPage(dailyDataParams, 1, 0);
			Page<StarDataStat> starDataStats=this.starDataStatService.getPage(dailyDataParams, 1, 0);
			res=CollectionUtils.isEmpty(dailyDatas.getContent())&&CollectionUtils.isEmpty(dailyStats.getContent())&&CollectionUtils.isEmpty(starDataStats.getContent());
		}
		
		Assert.assertTrue(res);
	}
	
	
}
