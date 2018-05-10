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

import com.faceye.component.stock.entity.AccountingSubject;
import com.faceye.component.stock.service.AccountingSubjectService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * AccountingSubject  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class AccountingSubjectServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private AccountingSubjectService accountingSubjectService = null;
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
		Assert.assertTrue(accountingSubjectService != null);
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
		//this.accountingSubjectService.removeAllInBatch();
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
		AccountingSubject entity = new AccountingSubject();
		this.accountingSubjectService.save(entity);
		List<AccountingSubject> entites = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		AccountingSubject entity = new AccountingSubject();
		this.accountingSubjectService.save(entity);
		List<AccountingSubject> entites = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingSubject entity = new AccountingSubject();
			this.accountingSubjectService.save(entity);
		}
		List<AccountingSubject> entities = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		AccountingSubject entity = new AccountingSubject();
		this.accountingSubjectService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		AccountingSubject e = this.accountingSubjectService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		AccountingSubject entity = new AccountingSubject();
		this.accountingSubjectService.save(entity);
		this.accountingSubjectService.remove(entity);
		List<AccountingSubject> entities = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingSubject entity = new AccountingSubject();
			this.accountingSubjectService.save(entity);
		}
		List<AccountingSubject> entities = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.accountingSubjectService.removeAllInBatch();
		entities = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingSubject entity = new AccountingSubject();
			this.accountingSubjectService.save(entity);
		}
		this.accountingSubjectService.removeAll();
		List<AccountingSubject> entities = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<AccountingSubject> entities = new ArrayList<AccountingSubject>();
		for (int i = 0; i < 5; i++) {
			AccountingSubject entity = new AccountingSubject();
			
			this.accountingSubjectService.save(entity);
			entities.add(entity);
		}
		this.accountingSubjectService.removeInBatch(entities);
		entities = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingSubject entity = new AccountingSubject();
			this.accountingSubjectService.save(entity);
		}
		List<AccountingSubject> entities = this.accountingSubjectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			AccountingSubject entity = new AccountingSubject();
			this.accountingSubjectService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<AccountingSubject> page = this.accountingSubjectService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.accountingSubjectService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.accountingSubjectService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			AccountingSubject entity = new AccountingSubject();
			this.accountingSubjectService.save(entity);
			id = entity.getId();
		}
		AccountingSubject e = this.accountingSubjectService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			AccountingSubject entity = new AccountingSubject();
			this.accountingSubjectService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<AccountingSubject> entities = this.accountingSubjectService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
