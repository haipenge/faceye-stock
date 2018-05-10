package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.repository.mongo.DataStatRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * DataStat DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class DataStatRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DataStatRepository dataStatRepository = null;

	@Before
	public void before() throws Exception {
		//this.dataStatRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		DataStat entity = new DataStat();
		this.dataStatRepository.save(entity);
		Iterable<DataStat> entities = this.dataStatRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		DataStat entity = new DataStat();
		this.dataStatRepository.save(entity);
        this.dataStatRepository.deleteById(entity.getId());
        Iterable<DataStat> entities = this.dataStatRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		DataStat entity = new DataStat();
		this.dataStatRepository.save(entity);
		DataStat dataStat=this.dataStatRepository.findById(entity.getId()).get();
		Assert.assertTrue(dataStat!=null);
	}

	
}
