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

import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.service.StarDataStatService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * StarDataStat  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class StarDataStatServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private StarDataStatService starDataStatService = null;
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
		Assert.isTrue(starDataStatService != null);
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
		//this.starDataStatService.removeAllInBatch();
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
		StarDataStat entity = new StarDataStat();
		this.starDataStatService.save(entity);
		List<StarDataStat> entites = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		StarDataStat entity = new StarDataStat();
		this.starDataStatService.save(entity);
		List<StarDataStat> entites = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			StarDataStat entity = new StarDataStat();
			this.starDataStatService.save(entity);
		}
		List<StarDataStat> entities = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		StarDataStat entity = new StarDataStat();
		this.starDataStatService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		StarDataStat e = this.starDataStatService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		StarDataStat entity = new StarDataStat();
		this.starDataStatService.save(entity);
		this.starDataStatService.remove(entity);
		List<StarDataStat> entities = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			StarDataStat entity = new StarDataStat();
			this.starDataStatService.save(entity);
		}
		List<StarDataStat> entities = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.starDataStatService.removeAllInBatch();
		entities = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			StarDataStat entity = new StarDataStat();
			this.starDataStatService.save(entity);
		}
		this.starDataStatService.removeAll();
		List<StarDataStat> entities = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<StarDataStat> entities = new ArrayList<StarDataStat>();
		for (int i = 0; i < 5; i++) {
			StarDataStat entity = new StarDataStat();
			
			this.starDataStatService.save(entity);
			entities.add(entity);
		}
		this.starDataStatService.removeInBatch(entities);
		entities = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			StarDataStat entity = new StarDataStat();
			this.starDataStatService.save(entity);
		}
		List<StarDataStat> entities = this.starDataStatService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			StarDataStat entity = new StarDataStat();
			this.starDataStatService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<StarDataStat> page = this.starDataStatService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.starDataStatService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.starDataStatService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			StarDataStat entity = new StarDataStat();
			this.starDataStatService.save(entity);
			id = entity.getId();
		}
		StarDataStat e = this.starDataStatService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			StarDataStat entity = new StarDataStat();
			this.starDataStatService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<StarDataStat> entities = this.starDataStatService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
