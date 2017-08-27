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
import org.springframework.util.Assert;

import com.faceye.component.generate.entity.Validate;
import com.faceye.component.generate.service.ValidateService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Validate  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ValidateServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ValidateService validateService = null;
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
		Assert.isTrue(validateService != null);
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
		//this.validateService.removeAllInBatch();
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
		Validate entity = new Validate();
		this.validateService.save(entity);
		List<Validate> entites = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Validate entity = new Validate();
		this.validateService.save(entity);
		List<Validate> entites = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Validate entity = new Validate();
			this.validateService.save(entity);
		}
		List<Validate> entities = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Validate entity = new Validate();
		this.validateService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Validate e = this.validateService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Validate entity = new Validate();
		this.validateService.save(entity);
		this.validateService.remove(entity);
		List<Validate> entities = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Validate entity = new Validate();
			this.validateService.save(entity);
		}
		List<Validate> entities = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.validateService.removeAllInBatch();
		entities = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Validate entity = new Validate();
			this.validateService.save(entity);
		}
		this.validateService.removeAll();
		List<Validate> entities = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Validate> entities = new ArrayList<Validate>();
		for (int i = 0; i < 5; i++) {
			Validate entity = new Validate();
			
			this.validateService.save(entity);
			entities.add(entity);
		}
		this.validateService.removeInBatch(entities);
		entities = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Validate entity = new Validate();
			this.validateService.save(entity);
		}
		List<Validate> entities = this.validateService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Validate entity = new Validate();
			this.validateService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Validate> page = this.validateService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.validateService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.validateService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Validate entity = new Validate();
			this.validateService.save(entity);
			id = entity.getId();
		}
		Validate e = this.validateService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Validate entity = new Validate();
			this.validateService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Validate> entities = this.validateService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
