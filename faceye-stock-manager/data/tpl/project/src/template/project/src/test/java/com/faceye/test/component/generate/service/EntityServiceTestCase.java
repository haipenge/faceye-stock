package com.faceye.test.component.generate.service;

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

import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.service.EntityService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Entity  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class EntityServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private EntityService entityService = null;
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
		Assert.assertTrue(entityService != null);
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
		//this.entityService.removeAllInBatch();
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
		Entity entity = new Entity();
		this.entityService.save(entity);
		List<Entity> entites = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Entity entity = new Entity();
		this.entityService.save(entity);
		List<Entity> entites = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Entity entity = new Entity();
			this.entityService.save(entity);
		}
		List<Entity> entities = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Entity entity = new Entity();
		this.entityService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Entity e = this.entityService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Entity entity = new Entity();
		this.entityService.save(entity);
		this.entityService.remove(entity);
		List<Entity> entities = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Entity entity = new Entity();
			this.entityService.save(entity);
		}
		List<Entity> entities = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.entityService.removeAllInBatch();
		entities = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Entity entity = new Entity();
			this.entityService.save(entity);
		}
		this.entityService.removeAll();
		List<Entity> entities = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < 5; i++) {
			Entity entity = new Entity();
			
			this.entityService.save(entity);
			entities.add(entity);
		}
		this.entityService.removeInBatch(entities);
		entities = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Entity entity = new Entity();
			this.entityService.save(entity);
		}
		List<Entity> entities = this.entityService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Entity entity = new Entity();
			this.entityService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Entity> page = this.entityService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.entityService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.entityService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Entity entity = new Entity();
			this.entityService.save(entity);
			id = entity.getId();
		}
		Entity e = this.entityService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Entity entity = new Entity();
			this.entityService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Entity> entities = this.entityService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
