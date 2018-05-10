package com.faceye.test.component.crm.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.crm.entity.Apple;
import com.faceye.component.crm.repository.mongo.AppleRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Apple DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class AppleRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AppleRepository appleRepository = null;

	@Before
	public void before() throws Exception {
		//this.appleRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Apple entity = new Apple();
		this.appleRepository.save(entity);
		Iterable<Apple> entities = this.appleRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Apple entity = new Apple();
		this.appleRepository.save(entity);
        this.appleRepository.deleteById(entity.getId());
        Iterable<Apple> entities = this.appleRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Apple entity = new Apple();
		this.appleRepository.save(entity);
		Apple apple=this.appleRepository.findById(entity.getId()).get();
		Assert.assertTrue(apple!=null);
	}

	
}
