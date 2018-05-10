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

import com.faceye.component.stock.entity.Mechanism;
import com.faceye.component.stock.service.MechanismService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Mechanism  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class MechanismServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private MechanismService mechanismService = null;
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
		Assert.assertTrue(mechanismService != null);
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
		//this.mechanismService.removeAllInBatch();
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
		Mechanism entity = new Mechanism();
		this.mechanismService.save(entity);
		List<Mechanism> entites = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Mechanism entity = new Mechanism();
		this.mechanismService.save(entity);
		List<Mechanism> entites = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Mechanism entity = new Mechanism();
			this.mechanismService.save(entity);
		}
		List<Mechanism> entities = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Mechanism entity = new Mechanism();
		this.mechanismService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Mechanism e = this.mechanismService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Mechanism entity = new Mechanism();
		this.mechanismService.save(entity);
		this.mechanismService.remove(entity);
		List<Mechanism> entities = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Mechanism entity = new Mechanism();
			this.mechanismService.save(entity);
		}
		List<Mechanism> entities = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.mechanismService.removeAllInBatch();
		entities = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Mechanism entity = new Mechanism();
			this.mechanismService.save(entity);
		}
		this.mechanismService.removeAll();
		List<Mechanism> entities = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Mechanism> entities = new ArrayList<Mechanism>();
		for (int i = 0; i < 5; i++) {
			Mechanism entity = new Mechanism();
			
			this.mechanismService.save(entity);
			entities.add(entity);
		}
		this.mechanismService.removeInBatch(entities);
		entities = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Mechanism entity = new Mechanism();
			this.mechanismService.save(entity);
		}
		List<Mechanism> entities = this.mechanismService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Mechanism entity = new Mechanism();
			this.mechanismService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Mechanism> page = this.mechanismService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.mechanismService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.mechanismService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Mechanism entity = new Mechanism();
			this.mechanismService.save(entity);
			id = entity.getId();
		}
		Mechanism e = this.mechanismService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Mechanism entity = new Mechanism();
			this.mechanismService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Mechanism> entities = this.mechanismService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
