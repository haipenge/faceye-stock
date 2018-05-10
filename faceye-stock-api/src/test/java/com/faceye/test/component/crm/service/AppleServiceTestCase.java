package com.faceye.test.component.crm.service;

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

import com.faceye.component.crm.entity.Apple;
import com.faceye.component.crm.service.AppleService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Apple  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class AppleServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private AppleService appleService = null;
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
		Assert.assertTrue(appleService != null);
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
		//this.appleService.removeAllInBatch();
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
		Apple entity = new Apple();
		this.appleService.save(entity);
		List<Apple> entites = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Apple entity = new Apple();
		this.appleService.save(entity);
		List<Apple> entites = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Apple entity = new Apple();
			this.appleService.save(entity);
		}
		List<Apple> entities = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Apple entity = new Apple();
		this.appleService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Apple e = this.appleService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Apple entity = new Apple();
		this.appleService.save(entity);
		this.appleService.remove(entity);
		List<Apple> entities = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Apple entity = new Apple();
			this.appleService.save(entity);
		}
		List<Apple> entities = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.appleService.removeAllInBatch();
		entities = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Apple entity = new Apple();
			this.appleService.save(entity);
		}
		this.appleService.removeAll();
		List<Apple> entities = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Apple> entities = new ArrayList<Apple>();
		for (int i = 0; i < 5; i++) {
			Apple entity = new Apple();
			
			this.appleService.save(entity);
			entities.add(entity);
		}
		this.appleService.removeInBatch(entities);
		entities = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Apple entity = new Apple();
			this.appleService.save(entity);
		}
		List<Apple> entities = this.appleService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Apple entity = new Apple();
			this.appleService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Apple> page = this.appleService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.appleService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.appleService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Apple entity = new Apple();
			this.appleService.save(entity);
			id = entity.getId();
		}
		Apple e = this.appleService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Apple entity = new Apple();
			this.appleService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Apple> entities = this.appleService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
