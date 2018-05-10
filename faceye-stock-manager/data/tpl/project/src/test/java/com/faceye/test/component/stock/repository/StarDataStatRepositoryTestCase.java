package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.repository.mongo.StarDataStatRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * StarDataStat DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class StarDataStatRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private StarDataStatRepository starDataStatRepository = null;

	@Before
	public void before() throws Exception {
		//this.starDataStatRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		StarDataStat entity = new StarDataStat();
		this.starDataStatRepository.save(entity);
		Iterable<StarDataStat> entities = this.starDataStatRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		StarDataStat entity = new StarDataStat();
		this.starDataStatRepository.save(entity);
        this.starDataStatRepository.deleteById(entity.getId());
        Iterable<StarDataStat> entities = this.starDataStatRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		StarDataStat entity = new StarDataStat();
		this.starDataStatRepository.save(entity);
		StarDataStat starDataStat=this.starDataStatRepository.findById(entity.getId()).get();
		Assert.assertTrue(starDataStat!=null);
	}

	
}
