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

import com.faceye.component.stock.entity.TotalStock;
import com.faceye.component.stock.service.TotalStockService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * TotalStock  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class TotalStockServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private TotalStockService totalStockService = null;
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
		Assert.assertTrue(totalStockService != null);
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
		//this.totalStockService.removeAllInBatch();
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
		TotalStock entity = new TotalStock();
		this.totalStockService.save(entity);
		List<TotalStock> entites = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		TotalStock entity = new TotalStock();
		this.totalStockService.save(entity);
		List<TotalStock> entites = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			TotalStock entity = new TotalStock();
			this.totalStockService.save(entity);
		}
		List<TotalStock> entities = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		TotalStock entity = new TotalStock();
		this.totalStockService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		TotalStock e = this.totalStockService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		TotalStock entity = new TotalStock();
		this.totalStockService.save(entity);
		this.totalStockService.remove(entity);
		List<TotalStock> entities = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			TotalStock entity = new TotalStock();
			this.totalStockService.save(entity);
		}
		List<TotalStock> entities = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.totalStockService.removeAllInBatch();
		entities = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			TotalStock entity = new TotalStock();
			this.totalStockService.save(entity);
		}
		this.totalStockService.removeAll();
		List<TotalStock> entities = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<TotalStock> entities = new ArrayList<TotalStock>();
		for (int i = 0; i < 5; i++) {
			TotalStock entity = new TotalStock();
			
			this.totalStockService.save(entity);
			entities.add(entity);
		}
		this.totalStockService.removeInBatch(entities);
		entities = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			TotalStock entity = new TotalStock();
			this.totalStockService.save(entity);
		}
		List<TotalStock> entities = this.totalStockService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			TotalStock entity = new TotalStock();
			this.totalStockService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<TotalStock> page = this.totalStockService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.totalStockService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.totalStockService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			TotalStock entity = new TotalStock();
			this.totalStockService.save(entity);
			id = entity.getId();
		}
		TotalStock e = this.totalStockService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			TotalStock entity = new TotalStock();
			this.totalStockService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<TotalStock> entities = this.totalStockService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
