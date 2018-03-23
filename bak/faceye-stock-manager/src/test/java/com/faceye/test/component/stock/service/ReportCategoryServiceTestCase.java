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

import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.service.ReportCategoryService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * ReportCategory  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ReportCategoryServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ReportCategoryService reportCategoryService = null;
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
		Assert.assertTrue(reportCategoryService != null);
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
		//this.reportCategoryService.removeAllInBatch();
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
		ReportCategory entity = new ReportCategory();
		this.reportCategoryService.save(entity);
		List<ReportCategory> entites = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		ReportCategory entity = new ReportCategory();
		this.reportCategoryService.save(entity);
		List<ReportCategory> entites = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			ReportCategory entity = new ReportCategory();
			this.reportCategoryService.save(entity);
		}
		List<ReportCategory> entities = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		ReportCategory entity = new ReportCategory();
		this.reportCategoryService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		ReportCategory e = this.reportCategoryService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		ReportCategory entity = new ReportCategory();
		this.reportCategoryService.save(entity);
		this.reportCategoryService.remove(entity);
		List<ReportCategory> entities = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			ReportCategory entity = new ReportCategory();
			this.reportCategoryService.save(entity);
		}
		List<ReportCategory> entities = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.reportCategoryService.removeAllInBatch();
		entities = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ReportCategory entity = new ReportCategory();
			this.reportCategoryService.save(entity);
		}
		this.reportCategoryService.removeAll();
		List<ReportCategory> entities = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<ReportCategory> entities = new ArrayList<ReportCategory>();
		for (int i = 0; i < 5; i++) {
			ReportCategory entity = new ReportCategory();
			
			this.reportCategoryService.save(entity);
			entities.add(entity);
		}
		this.reportCategoryService.removeInBatch(entities);
		entities = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ReportCategory entity = new ReportCategory();
			this.reportCategoryService.save(entity);
		}
		List<ReportCategory> entities = this.reportCategoryService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			ReportCategory entity = new ReportCategory();
			this.reportCategoryService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<ReportCategory> page = this.reportCategoryService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.reportCategoryService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.reportCategoryService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			ReportCategory entity = new ReportCategory();
			this.reportCategoryService.save(entity);
			id = entity.getId();
		}
		ReportCategory e = this.reportCategoryService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			ReportCategory entity = new ReportCategory();
			this.reportCategoryService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<ReportCategory> entities = this.reportCategoryService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
