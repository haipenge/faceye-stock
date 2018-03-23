package com.faceye.test.component.@component.name@.service;

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

import com.faceye.component.@component.name@.entity.@entity.name@;
import com.faceye.feature.repository.SearchFilter;
import com.faceye.component.@component.name@.service.@entity.name@Service;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * @entity.name@  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class @entity.name@ServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private @entity.name@Service @entity.config.name@Service = null;
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
		Assert.assertTrue(@entity.config.name@Service != null);
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
		//this.@entity.config.name@Service.removeAllInBatch();
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
		@entity.name@ entity = new @entity.name@();
		this.@entity.config.name@Service.save(entity);
		List<@entity.name@> entites = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		@entity.name@ entity = new @entity.name@();
		this.@entity.config.name@Service.save(entity);
		List<@entity.name@> entites = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			@entity.name@ entity = new @entity.name@();
			this.@entity.config.name@Service.save(entity);
		}
		List<@entity.name@> entities = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		@entity.name@ entity = new @entity.name@();
		this.@entity.config.name@Service.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		@entity.name@ e = this.@entity.config.name@Service.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		@entity.name@ entity = new @entity.name@();
		this.@entity.config.name@Service.save(entity);
		this.@entity.config.name@Service.remove(entity);
		List<@entity.name@> entities = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			@entity.name@ entity = new @entity.name@();
			this.@entity.config.name@Service.save(entity);
		}
		List<@entity.name@> entities = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.@entity.config.name@Service.removeAllInBatch();
		entities = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			@entity.name@ entity = new @entity.name@();
			this.@entity.config.name@Service.save(entity);
		}
		this.@entity.config.name@Service.removeAll();
		List<@entity.name@> entities = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<@entity.name@> entities = new ArrayList<@entity.name@>();
		for (int i = 0; i < 5; i++) {
			@entity.name@ entity = new @entity.name@();
			
			this.@entity.config.name@Service.save(entity);
			entities.add(entity);
		}
		this.@entity.config.name@Service.removeInBatch(entities);
		entities = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			@entity.name@ entity = new @entity.name@();
			this.@entity.config.name@Service.save(entity);
		}
		List<@entity.name@> entities = this.@entity.config.name@Service.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			@entity.name@ entity = new @entity.name@();
			this.@entity.config.name@Service.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<@entity.name@> page = this.@entity.config.name@Service.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.@entity.config.name@Service.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.@entity.config.name@Service.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			@entity.name@ entity = new @entity.name@();
			this.@entity.config.name@Service.save(entity);
			id = entity.getId();
		}
		@entity.name@ e = this.@entity.config.name@Service.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			@entity.name@ entity = new @entity.name@();
			this.@entity.config.name@Service.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<@entity.name@> entities = this.@entity.config.name@Service.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
