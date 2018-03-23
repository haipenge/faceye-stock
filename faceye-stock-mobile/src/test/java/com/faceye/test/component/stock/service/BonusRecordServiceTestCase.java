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

import com.faceye.component.stock.entity.BonusRecord;
import com.faceye.component.stock.service.BonusRecordService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * BonusRecord  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class BonusRecordServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private BonusRecordService bonusRecordService = null;
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
		Assert.assertTrue(bonusRecordService != null);
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
		//this.bonusRecordService.removeAllInBatch();
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
		BonusRecord entity = new BonusRecord();
		this.bonusRecordService.save(entity);
		List<BonusRecord> entites = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		BonusRecord entity = new BonusRecord();
		this.bonusRecordService.save(entity);
		List<BonusRecord> entites = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			BonusRecord entity = new BonusRecord();
			this.bonusRecordService.save(entity);
		}
		List<BonusRecord> entities = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		BonusRecord entity = new BonusRecord();
		this.bonusRecordService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		BonusRecord e = this.bonusRecordService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		BonusRecord entity = new BonusRecord();
		this.bonusRecordService.save(entity);
		this.bonusRecordService.remove(entity);
		List<BonusRecord> entities = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			BonusRecord entity = new BonusRecord();
			this.bonusRecordService.save(entity);
		}
		List<BonusRecord> entities = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.bonusRecordService.removeAllInBatch();
		entities = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			BonusRecord entity = new BonusRecord();
			this.bonusRecordService.save(entity);
		}
		this.bonusRecordService.removeAll();
		List<BonusRecord> entities = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<BonusRecord> entities = new ArrayList<BonusRecord>();
		for (int i = 0; i < 5; i++) {
			BonusRecord entity = new BonusRecord();
			
			this.bonusRecordService.save(entity);
			entities.add(entity);
		}
		this.bonusRecordService.removeInBatch(entities);
		entities = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			BonusRecord entity = new BonusRecord();
			this.bonusRecordService.save(entity);
		}
		List<BonusRecord> entities = this.bonusRecordService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			BonusRecord entity = new BonusRecord();
			this.bonusRecordService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<BonusRecord> page = this.bonusRecordService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.bonusRecordService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.bonusRecordService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			BonusRecord entity = new BonusRecord();
			this.bonusRecordService.save(entity);
			id = entity.getId();
		}
		BonusRecord e = this.bonusRecordService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			BonusRecord entity = new BonusRecord();
			this.bonusRecordService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<BonusRecord> entities = this.bonusRecordService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
