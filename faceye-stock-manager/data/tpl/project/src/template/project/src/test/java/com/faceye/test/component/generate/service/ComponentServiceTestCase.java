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

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.component.generate.service.ProjectService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Component  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ComponentServiceTestCase extends BaseServiceTestCase {
	org.springframework.validation.beanvalidation.LocalValidatorFactoryBean ss;
	org.springframework.web.bind.support.ConfigurableWebBindingInitializer sss;
	@Autowired
	private ComponentService componentService = null;
	
	@Autowired
	private ProjectService projectService=null;
	
	private Project project=null;
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
		project=this.projectService.get(2L);
		Assert.assertTrue(componentService != null);
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
		//this.componentService.removeAllInBatch();
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
		Component entity = new Component();
		entity.setName("dbref");
		entity.setPackageName("dbref");
		entity.setProject(project);
		this.componentService.save(entity);
		List<Component> entites = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Component entity = new Component();
		this.componentService.save(entity);
		List<Component> entites = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Component entity = new Component();
			this.componentService.save(entity);
		}
		List<Component> entities = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Component entity = new Component();
		this.componentService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Component e = this.componentService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Component entity = new Component();
		this.componentService.save(entity);
		this.componentService.remove(entity);
		List<Component> entities = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Component entity = new Component();
			this.componentService.save(entity);
		}
		List<Component> entities = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.componentService.removeAllInBatch();
		entities = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Component entity = new Component();
			this.componentService.save(entity);
		}
		this.componentService.removeAll();
		List<Component> entities = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Component> entities = new ArrayList<Component>();
		for (int i = 0; i < 5; i++) {
			Component entity = new Component();
			
			this.componentService.save(entity);
			entities.add(entity);
		}
		this.componentService.removeInBatch(entities);
		entities = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Component entity = new Component();
			this.componentService.save(entity);
		}
		List<Component> entities = this.componentService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Component entity = new Component();
			this.componentService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Component> page = this.componentService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.componentService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.componentService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = 2L;
		Component e = this.componentService.get(id);
		logger.debug(">>Project is:"+e.getProject().getName());
		Assert.assertTrue(e != null&& null!=e.getProject());
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Component entity = new Component();
			this.componentService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Component> entities = this.componentService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
