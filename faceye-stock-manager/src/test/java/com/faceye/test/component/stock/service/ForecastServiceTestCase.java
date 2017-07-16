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

import com.faceye.component.stock.entity.Forecast;
import com.faceye.component.stock.service.ForecastService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Forecast  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ForecastServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ForecastService forecastService = null;
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
		Assert.isTrue(forecastService != null);
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
		//this.forecastService.removeAllInBatch();
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
		Forecast entity = new Forecast();
		this.forecastService.save(entity);
		List<Forecast> entites = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Forecast entity = new Forecast();
		this.forecastService.save(entity);
		List<Forecast> entites = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Forecast entity = new Forecast();
			this.forecastService.save(entity);
		}
		List<Forecast> entities = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Forecast entity = new Forecast();
		this.forecastService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Forecast e = this.forecastService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Forecast entity = new Forecast();
		this.forecastService.save(entity);
		this.forecastService.remove(entity);
		List<Forecast> entities = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Forecast entity = new Forecast();
			this.forecastService.save(entity);
		}
		List<Forecast> entities = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.forecastService.removeAllInBatch();
		entities = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Forecast entity = new Forecast();
			this.forecastService.save(entity);
		}
		this.forecastService.removeAll();
		List<Forecast> entities = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Forecast> entities = new ArrayList<Forecast>();
		for (int i = 0; i < 5; i++) {
			Forecast entity = new Forecast();
			
			this.forecastService.save(entity);
			entities.add(entity);
		}
		this.forecastService.removeInBatch(entities);
		entities = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Forecast entity = new Forecast();
			this.forecastService.save(entity);
		}
		List<Forecast> entities = this.forecastService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Forecast entity = new Forecast();
			this.forecastService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Forecast> page = this.forecastService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.forecastService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.forecastService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Forecast entity = new Forecast();
			this.forecastService.save(entity);
			id = entity.getId();
		}
		Forecast e = this.forecastService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Forecast entity = new Forecast();
			this.forecastService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Forecast> entities = this.forecastService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
