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

import com.faceye.component.stock.entity.Valuation;
import com.faceye.component.stock.service.ValuationService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Valuation  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ValuationServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ValuationService valuationService = null;
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
		Assert.isTrue(valuationService != null);
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
		//this.valuationService.removeAllInBatch();
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
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		List<Valuation> entites = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		List<Valuation> entites = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		List<Valuation> entities = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Valuation e = this.valuationService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Valuation entity = new Valuation();
		this.valuationService.save(entity);
		this.valuationService.remove(entity);
		List<Valuation> entities = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		List<Valuation> entities = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.valuationService.removeAllInBatch();
		entities = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		this.valuationService.removeAll();
		List<Valuation> entities = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Valuation> entities = new ArrayList<Valuation>();
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			
			this.valuationService.save(entity);
			entities.add(entity);
		}
		this.valuationService.removeInBatch(entities);
		entities = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		List<Valuation> entities = this.valuationService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Valuation> page = this.valuationService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.valuationService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.valuationService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
			id = entity.getId();
		}
		Valuation e = this.valuationService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Valuation entity = new Valuation();
			this.valuationService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Valuation> entities = this.valuationService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
