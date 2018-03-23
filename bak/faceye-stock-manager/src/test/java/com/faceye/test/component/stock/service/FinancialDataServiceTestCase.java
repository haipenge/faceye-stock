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

import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.service.FinancialDataService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * FinancialData  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class FinancialDataServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private FinancialDataService financialDataService = null;
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
		Assert.assertTrue(financialDataService != null);
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
		//this.financialDataService.removeAllInBatch();
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
		FinancialData entity = new FinancialData();
		this.financialDataService.save(entity);
		List<FinancialData> entites = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		FinancialData entity = new FinancialData();
		this.financialDataService.save(entity);
		List<FinancialData> entites = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialData entity = new FinancialData();
			this.financialDataService.save(entity);
		}
		List<FinancialData> entities = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		FinancialData entity = new FinancialData();
		this.financialDataService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		FinancialData e = this.financialDataService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		FinancialData entity = new FinancialData();
		this.financialDataService.save(entity);
		this.financialDataService.remove(entity);
		List<FinancialData> entities = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialData entity = new FinancialData();
			this.financialDataService.save(entity);
		}
		List<FinancialData> entities = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.financialDataService.removeAllInBatch();
		entities = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialData entity = new FinancialData();
			this.financialDataService.save(entity);
		}
		this.financialDataService.removeAll();
		List<FinancialData> entities = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<FinancialData> entities = new ArrayList<FinancialData>();
		for (int i = 0; i < 5; i++) {
			FinancialData entity = new FinancialData();
			
			this.financialDataService.save(entity);
			entities.add(entity);
		}
		this.financialDataService.removeInBatch(entities);
		entities = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialData entity = new FinancialData();
			this.financialDataService.save(entity);
		}
		List<FinancialData> entities = this.financialDataService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			FinancialData entity = new FinancialData();
			this.financialDataService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<FinancialData> page = this.financialDataService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.financialDataService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.financialDataService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			FinancialData entity = new FinancialData();
			this.financialDataService.save(entity);
			id = entity.getId();
		}
		FinancialData e = this.financialDataService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			FinancialData entity = new FinancialData();
			this.financialDataService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<FinancialData> entities = this.financialDataService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
