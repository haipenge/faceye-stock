package com.faceye.test.component.generate.service;

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

import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.service.DataTypeService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * DataType  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class DataTypeServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private DataTypeService dataTypeService = null;
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
		Assert.assertTrue(dataTypeService != null);
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
		//this.dataTypeService.removeAllInBatch();
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
		DataType entity = new DataType();
		this.dataTypeService.save(entity);
		List<DataType> entites = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		DataType entity = new DataType();
		this.dataTypeService.save(entity);
		List<DataType> entites = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataType entity = new DataType();
			this.dataTypeService.save(entity);
		}
		List<DataType> entities = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		DataType entity = new DataType();
		this.dataTypeService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		DataType e = this.dataTypeService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		DataType entity = new DataType();
		this.dataTypeService.save(entity);
		this.dataTypeService.remove(entity);
		List<DataType> entities = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataType entity = new DataType();
			this.dataTypeService.save(entity);
		}
		List<DataType> entities = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.dataTypeService.removeAllInBatch();
		entities = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataType entity = new DataType();
			this.dataTypeService.save(entity);
		}
		this.dataTypeService.removeAll();
		List<DataType> entities = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<DataType> entities = new ArrayList<DataType>();
		for (int i = 0; i < 5; i++) {
			DataType entity = new DataType();
			
			this.dataTypeService.save(entity);
			entities.add(entity);
		}
		this.dataTypeService.removeInBatch(entities);
		entities = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DataType entity = new DataType();
			this.dataTypeService.save(entity);
		}
		List<DataType> entities = this.dataTypeService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			DataType entity = new DataType();
			this.dataTypeService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<DataType> page = this.dataTypeService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.dataTypeService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.dataTypeService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			DataType entity = new DataType();
			this.dataTypeService.save(entity);
			id = entity.getId();
		}
		DataType e = this.dataTypeService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			DataType entity = new DataType();
			this.dataTypeService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<DataType> entities = this.dataTypeService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
