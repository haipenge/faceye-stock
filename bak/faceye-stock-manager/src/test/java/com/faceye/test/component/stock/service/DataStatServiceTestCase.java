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
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.DataStatService;
import com.faceye.component.stock.service.StockService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * DataStat  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class DataStatServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private DataStatService dataStatService = null;
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
		Assert.isTrue(dataStatService != null);
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
		//this.dataStatService.removeAllInBatch();
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
		DataStat entity = new DataStat();
		this.dataStatService.save(entity);
		List<DataStat> entites = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		DataStat entity = new DataStat();
		this.dataStatService.save(entity);
		List<DataStat> entites = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataStat entity = new DataStat();
			this.dataStatService.save(entity);
		}
		List<DataStat> entities = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		DataStat entity = new DataStat();
		this.dataStatService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		DataStat e = this.dataStatService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		DataStat entity = new DataStat();
		this.dataStatService.save(entity);
		this.dataStatService.remove(entity);
		List<DataStat> entities = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataStat entity = new DataStat();
			this.dataStatService.save(entity);
		}
		List<DataStat> entities = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.dataStatService.removeAllInBatch();
		entities = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataStat entity = new DataStat();
			this.dataStatService.save(entity);
		}
		this.dataStatService.removeAll();
		List<DataStat> entities = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<DataStat> entities = new ArrayList<DataStat>();
		for (int i = 0; i < 5; i++) {
			DataStat entity = new DataStat();
			
			this.dataStatService.save(entity);
			entities.add(entity);
		}
		this.dataStatService.removeInBatch(entities);
		entities = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataStat entity = new DataStat();
			this.dataStatService.save(entity);
		}
		List<DataStat> entities = this.dataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			DataStat entity = new DataStat();
			this.dataStatService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<DataStat> page = this.dataStatService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.dataStatService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.dataStatService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			DataStat entity = new DataStat();
			this.dataStatService.save(entity);
			id = entity.getId();
		}
		DataStat e = this.dataStatService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			DataStat entity = new DataStat();
			this.dataStatService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<DataStat> entities = this.dataStatService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
	@Test
	public void statStock() throws Exception{
		Stock stock=this.stockService.getStockByCode("000333");
		this.dataStatService.stat(stock);
		Assert.isTrue(true);
	}
}
