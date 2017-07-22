package com.faceye.test.component.stock.service;

import java.util.ArrayList;
import java.util.Date;
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

import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.entity.Mechanism;
import com.faceye.component.stock.service.ForecastIndexService;
import com.faceye.component.stock.service.MechanismService;
import com.faceye.test.feature.service.BaseServiceTestCase;

/**
 * ForecastIndex 服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ForecastIndexServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ForecastIndexService forecastIndexService = null;
	@Autowired
	private MechanismService mechanismService = null;

	/**
	 * 初始化
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(forecastIndexService != null);
	}

	/**
	 * 清理
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		// this.forecastIndexService.removeAllInBatch();
	}

	/**
	 * 保存测试
	 * 
	 * @todo
	 * @throws Exception
	 * @author:@haipenge haipenge@gmail.com 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		ForecastIndex entity = new ForecastIndex();
		this.forecastIndexService.save(entity);
		List<ForecastIndex> entites = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		ForecastIndex entity = new ForecastIndex();
		this.forecastIndexService.save(entity);
		List<ForecastIndex> entites = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			ForecastIndex entity = new ForecastIndex();
			this.forecastIndexService.save(entity);
		}
		List<ForecastIndex> entities = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		ForecastIndex entity = new ForecastIndex();
		this.forecastIndexService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		ForecastIndex e = this.forecastIndexService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		ForecastIndex entity = new ForecastIndex();
		this.forecastIndexService.save(entity);
		this.forecastIndexService.remove(entity);
		List<ForecastIndex> entities = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			ForecastIndex entity = new ForecastIndex();
			this.forecastIndexService.save(entity);
		}
		List<ForecastIndex> entities = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.forecastIndexService.removeAllInBatch();
		entities = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ForecastIndex entity = new ForecastIndex();
			this.forecastIndexService.save(entity);
		}
		this.forecastIndexService.removeAll();
		List<ForecastIndex> entities = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<ForecastIndex> entities = new ArrayList<ForecastIndex>();
		for (int i = 0; i < 5; i++) {
			ForecastIndex entity = new ForecastIndex();

			this.forecastIndexService.save(entity);
			entities.add(entity);
		}
		this.forecastIndexService.removeInBatch(entities);
		entities = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ForecastIndex entity = new ForecastIndex();
			this.forecastIndexService.save(entity);
		}
		List<ForecastIndex> entities = this.forecastIndexService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			ForecastIndex entity = new ForecastIndex();
			this.forecastIndexService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<ForecastIndex> page = this.forecastIndexService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.forecastIndexService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.forecastIndexService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			ForecastIndex entity = new ForecastIndex();
			this.forecastIndexService.save(entity);
			id = entity.getId();
		}
		ForecastIndex e = this.forecastIndexService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			ForecastIndex entity = new ForecastIndex();
			this.forecastIndexService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<ForecastIndex> entities = this.forecastIndexService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}

	@Test
	public void testGetForecastIndexByMechanism() throws Exception {
		Mechanism mechanism =null;
		mechanism = this.mechanismService.getMechanismByName("test-mechanism");
		if(mechanism==null){
			mechanism=new Mechanism();
			mechanism.setName("test-mechanism");
			this.mechanismService.save(mechanism);
		}
		Date reportDate = new Date();
		for (int i = 0; i < 10; i++) {
			ForecastIndex forecastIndex = this.forecastIndexService.getForecastIndexByMechanismAndReportDate(17L, mechanism, reportDate);
		}
		Page<ForecastIndex> page = this.forecastIndexService.getPage(null, 1, 0);
		Assert.isTrue(page != null && page.getContent().size() == 1);

	}
}
