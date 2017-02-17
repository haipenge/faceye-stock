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

import com.faceye.component.stock.entity.FinancialReport;
import com.faceye.component.stock.service.FinancialReportService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * FinancialReport  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class FinancialReportServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private FinancialReportService financialReportService = null;
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
		Assert.isTrue(financialReportService != null);
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
		//this.financialReportService.removeAllInBatch();
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
		FinancialReport entity = new FinancialReport();
		this.financialReportService.save(entity);
		List<FinancialReport> entites = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		FinancialReport entity = new FinancialReport();
		this.financialReportService.save(entity);
		List<FinancialReport> entites = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialReport entity = new FinancialReport();
			this.financialReportService.save(entity);
		}
		List<FinancialReport> entities = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		FinancialReport entity = new FinancialReport();
		this.financialReportService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		FinancialReport e = this.financialReportService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		FinancialReport entity = new FinancialReport();
		this.financialReportService.save(entity);
		this.financialReportService.remove(entity);
		List<FinancialReport> entities = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialReport entity = new FinancialReport();
			this.financialReportService.save(entity);
		}
		List<FinancialReport> entities = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.financialReportService.removeAllInBatch();
		entities = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialReport entity = new FinancialReport();
			this.financialReportService.save(entity);
		}
		this.financialReportService.removeAll();
		List<FinancialReport> entities = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<FinancialReport> entities = new ArrayList<FinancialReport>();
		for (int i = 0; i < 5; i++) {
			FinancialReport entity = new FinancialReport();
			
			this.financialReportService.save(entity);
			entities.add(entity);
		}
		this.financialReportService.removeInBatch(entities);
		entities = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			FinancialReport entity = new FinancialReport();
			this.financialReportService.save(entity);
		}
		List<FinancialReport> entities = this.financialReportService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			FinancialReport entity = new FinancialReport();
			this.financialReportService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<FinancialReport> page = this.financialReportService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.financialReportService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.financialReportService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			FinancialReport entity = new FinancialReport();
			this.financialReportService.save(entity);
			id = entity.getId();
		}
		FinancialReport e = this.financialReportService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			FinancialReport entity = new FinancialReport();
			this.financialReportService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<FinancialReport> entities = this.financialReportService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
