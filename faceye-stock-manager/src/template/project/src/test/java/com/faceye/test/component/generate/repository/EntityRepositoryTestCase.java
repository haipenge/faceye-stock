package com.faceye.test.component.generate.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.repository.mongo.EntityRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Entity DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class EntityRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private EntityRepository entityRepository = null;

	@Before
	public void before() throws Exception {
		//this.entityRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Entity entity = new Entity();
		this.entityRepository.save(entity);
		Iterable<Entity> entities = this.entityRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Entity entity = new Entity();
		this.entityRepository.save(entity);
        this.entityRepository.delete(entity.getId());
        Iterable<Entity> entities = this.entityRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Entity entity = new Entity();
		this.entityRepository.save(entity);
		entity=this.entityRepository.findOne(entity.getId());
		Assert.isTrue(entity!=null);
	}

	
}
