package com.faceye.test.component.generate.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.generate.entity.Validate;
import com.faceye.component.generate.repository.mongo.ValidateRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Validate DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ValidateRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ValidateRepository validateRepository = null;

	@Before
	public void before() throws Exception {
		//this.validateRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Validate entity = new Validate();
		this.validateRepository.save(entity);
		Iterable<Validate> entities = this.validateRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Validate entity = new Validate();
		this.validateRepository.save(entity);
        this.validateRepository.deleteById(entity.getId());
        Iterable<Validate> entities = this.validateRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Validate entity = new Validate();
		this.validateRepository.save(entity);
		Validate validate=this.validateRepository.findById(entity.getId()).get();
		Assert.assertTrue(validate!=null);
	}

	
}
