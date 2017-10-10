package com.faceye.test.component.generate.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.repository.mongo.DataTypeRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * DataType DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class DataTypeRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DataTypeRepository dataTypeRepository = null;

	@Before
	public void before() throws Exception {
		//this.dataTypeRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		DataType entity = new DataType();
		this.dataTypeRepository.save(entity);
		Iterable<DataType> entities = this.dataTypeRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		DataType entity = new DataType();
		this.dataTypeRepository.save(entity);
        this.dataTypeRepository.delete(entity.getId());
        Iterable<DataType> entities = this.dataTypeRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		DataType entity = new DataType();
		this.dataTypeRepository.save(entity);
		DataType dataType=this.dataTypeRepository.findOne(entity.getId());
		Assert.isTrue(dataType!=null);
	}

	
}
