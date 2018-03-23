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

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.service.DailyStatService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * DailyStat  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class DailyStatServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private DailyStatService dailyStatService = null;
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
		Assert.assertTrue(dailyStatService != null);
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
		//this.dailyStatService.removeAllInBatch();
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
		DailyStat entity = new DailyStat();
		this.dailyStatService.save(entity);
		List<DailyStat> entites = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		DailyStat entity = new DailyStat();
		this.dailyStatService.save(entity);
		List<DailyStat> entites = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyStat entity = new DailyStat();
			this.dailyStatService.save(entity);
		}
		List<DailyStat> entities = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		DailyStat entity = new DailyStat();
		this.dailyStatService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		DailyStat e = this.dailyStatService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		DailyStat entity = new DailyStat();
		this.dailyStatService.save(entity);
		this.dailyStatService.remove(entity);
		List<DailyStat> entities = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyStat entity = new DailyStat();
			this.dailyStatService.save(entity);
		}
		List<DailyStat> entities = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.dailyStatService.removeAllInBatch();
		entities = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyStat entity = new DailyStat();
			this.dailyStatService.save(entity);
		}
		this.dailyStatService.removeAll();
		List<DailyStat> entities = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<DailyStat> entities = new ArrayList<DailyStat>();
		for (int i = 0; i < 5; i++) {
			DailyStat entity = new DailyStat();
			
			this.dailyStatService.save(entity);
			entities.add(entity);
		}
		this.dailyStatService.removeInBatch(entities);
		entities = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DailyStat entity = new DailyStat();
			this.dailyStatService.save(entity);
		}
		List<DailyStat> entities = this.dailyStatService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			DailyStat entity = new DailyStat();
			this.dailyStatService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<DailyStat> page = this.dailyStatService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.dailyStatService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.dailyStatService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			DailyStat entity = new DailyStat();
			this.dailyStatService.save(entity);
			id = entity.getId();
		}
		DailyStat e = this.dailyStatService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			DailyStat entity = new DailyStat();
			this.dailyStatService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<DailyStat> entities = this.dailyStatService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
