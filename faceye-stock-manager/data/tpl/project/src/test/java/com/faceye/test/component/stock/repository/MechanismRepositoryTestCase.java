package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.Mechanism;
import com.faceye.component.stock.repository.mongo.MechanismRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Mechanism DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class MechanismRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private MechanismRepository mechanismRepository = null;

	@Before
	public void before() throws Exception {
		//this.mechanismRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Mechanism entity = new Mechanism();
		this.mechanismRepository.save(entity);
		Iterable<Mechanism> entities = this.mechanismRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Mechanism entity = new Mechanism();
		this.mechanismRepository.save(entity);
        this.mechanismRepository.deleteById(entity.getId());
        Iterable<Mechanism> entities = this.mechanismRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Mechanism entity = new Mechanism();
		this.mechanismRepository.save(entity);
		Mechanism mechanism=this.mechanismRepository.findById(entity.getId()).get();
		Assert.assertTrue(mechanism!=null);
	}

	
}
