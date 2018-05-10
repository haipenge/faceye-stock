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

import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.service.ProjectService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Project  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ProjectServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ProjectService projectService = null;
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
		Assert.assertTrue(projectService != null);
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
		//this.projectService.removeAllInBatch();
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
		Project entity = new Project();
		this.projectService.save(entity);
		List<Project> entites = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Project entity = new Project();
		this.projectService.save(entity);
		List<Project> entites = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Project entity = new Project();
			this.projectService.save(entity);
		}
		List<Project> entities = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Project entity = new Project();
		this.projectService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Project e = this.projectService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Project entity = new Project();
		this.projectService.save(entity);
		this.projectService.remove(entity);
		List<Project> entities = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Project entity = new Project();
			this.projectService.save(entity);
		}
		List<Project> entities = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.projectService.removeAllInBatch();
		entities = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Project entity = new Project();
			this.projectService.save(entity);
		}
		this.projectService.removeAll();
		List<Project> entities = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Project> entities = new ArrayList<Project>();
		for (int i = 0; i < 5; i++) {
			Project entity = new Project();
			
			this.projectService.save(entity);
			entities.add(entity);
		}
		this.projectService.removeInBatch(entities);
		entities = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Project entity = new Project();
			this.projectService.save(entity);
		}
		List<Project> entities = this.projectService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Project entity = new Project();
			this.projectService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Project> page = this.projectService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.projectService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.projectService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Project entity = new Project();
			this.projectService.save(entity);
			id = entity.getId();
		}
		Project e = this.projectService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Project entity = new Project();
			this.projectService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Project> entities = this.projectService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
