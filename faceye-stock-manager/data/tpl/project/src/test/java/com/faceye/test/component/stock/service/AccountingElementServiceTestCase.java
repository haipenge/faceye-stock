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

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.service.AccountingElementService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * AccountingElement  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class AccountingElementServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private AccountingElementService accountingElementService = null;
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
		Assert.assertTrue(accountingElementService != null);
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
		//this.accountingElementService.removeAllInBatch();
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
		AccountingElement entity = new AccountingElement();
		this.accountingElementService.save(entity);
		List<AccountingElement> entites = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		AccountingElement entity = new AccountingElement();
		this.accountingElementService.save(entity);
		List<AccountingElement> entites = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingElement entity = new AccountingElement();
			this.accountingElementService.save(entity);
		}
		List<AccountingElement> entities = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		AccountingElement entity = new AccountingElement();
		this.accountingElementService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		AccountingElement e = this.accountingElementService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		AccountingElement entity = new AccountingElement();
		this.accountingElementService.save(entity);
		this.accountingElementService.remove(entity);
		List<AccountingElement> entities = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingElement entity = new AccountingElement();
			this.accountingElementService.save(entity);
		}
		List<AccountingElement> entities = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.accountingElementService.removeAllInBatch();
		entities = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingElement entity = new AccountingElement();
			this.accountingElementService.save(entity);
		}
		this.accountingElementService.removeAll();
		List<AccountingElement> entities = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<AccountingElement> entities = new ArrayList<AccountingElement>();
		for (int i = 0; i < 5; i++) {
			AccountingElement entity = new AccountingElement();
			
			this.accountingElementService.save(entity);
			entities.add(entity);
		}
		this.accountingElementService.removeInBatch(entities);
		entities = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			AccountingElement entity = new AccountingElement();
			this.accountingElementService.save(entity);
		}
		List<AccountingElement> entities = this.accountingElementService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			AccountingElement entity = new AccountingElement();
			this.accountingElementService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<AccountingElement> page = this.accountingElementService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.accountingElementService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.accountingElementService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			AccountingElement entity = new AccountingElement();
			this.accountingElementService.save(entity);
			id = entity.getId();
		}
		AccountingElement e = this.accountingElementService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			AccountingElement entity = new AccountingElement();
			this.accountingElementService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<AccountingElement> entities = this.accountingElementService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
