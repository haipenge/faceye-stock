package com.faceye.test.component.generate.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.repository.mongo.ComponentRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Component DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ComponentRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ComponentRepository componentRepository = null;

	@Before
	public void before() throws Exception {
		//this.componentRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Component entity = new Component();
		this.componentRepository.save(entity);
		Iterable<Component> entities = this.componentRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Component entity = new Component();
		this.componentRepository.save(entity);
        this.componentRepository.delete(entity.getId());
        Iterable<Component> entities = this.componentRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Component entity = new Component();
		this.componentRepository.save(entity);
		Component component=this.componentRepository.findOne(entity.getId());
		Assert.isTrue(component!=null);
	}

	
}
