package com.faceye.test.component.crm.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.crm.entity.Emplyee;
import com.faceye.component.crm.repository.mongo.EmplyeeRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Emplyee DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class EmplyeeRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private EmplyeeRepository emplyeeRepository = null;

	@Before
	public void before() throws Exception {
		//this.emplyeeRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Emplyee entity = new Emplyee();
		this.emplyeeRepository.save(entity);
		Iterable<Emplyee> entities = this.emplyeeRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Emplyee entity = new Emplyee();
		this.emplyeeRepository.save(entity);
        this.emplyeeRepository.deleteById(entity.getId());
        Iterable<Emplyee> entities = this.emplyeeRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Emplyee entity = new Emplyee();
		this.emplyeeRepository.save(entity);
		Emplyee emplyee=this.emplyeeRepository.findById(entity.getId()).get();
		Assert.assertTrue(emplyee!=null);
	}

	
}
