package com.faceye.test.component.@component.name@.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.@component.name@.entity.@entity.name@;
import com.faceye.component.@component.name@.repository.mongo.@entity.name@Repository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * @entity.name@ DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class @entity.name@RepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private @entity.name@Repository @entity.config.name@Repository = null;

	@Before
	public void before() throws Exception {
		//this.@entity.config.name@Repository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		@entity.name@ entity = new @entity.name@();
		this.@entity.config.name@Repository.save(entity);
		Iterable<@entity.name@> entities = this.@entity.config.name@Repository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		@entity.name@ entity = new @entity.name@();
		this.@entity.config.name@Repository.save(entity);
        this.@entity.config.name@Repository.delete(entity.getId());
        Iterable<@entity.name@> entities = this.@entity.config.name@Repository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		@entity.name@ entity = new @entity.name@();
		this.@entity.config.name@Repository.save(entity);
		@entity.name@ @entity.config.name@=this.@entity.config.name@Repository.findOne(entity.getId());
		Assert.isTrue(@entity.config.name@!=null);
	}

	
}
