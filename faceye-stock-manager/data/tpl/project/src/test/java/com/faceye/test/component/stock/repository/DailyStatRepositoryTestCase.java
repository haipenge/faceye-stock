package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.DailyStat;
import com.faceye.component.stock.repository.mongo.DailyStatRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * DailyStat DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class DailyStatRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DailyStatRepository dailyStatRepository = null;

	@Before
	public void before() throws Exception {
		//this.dailyStatRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		DailyStat entity = new DailyStat();
		this.dailyStatRepository.save(entity);
		Iterable<DailyStat> entities = this.dailyStatRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		DailyStat entity = new DailyStat();
		this.dailyStatRepository.save(entity);
        this.dailyStatRepository.deleteById(entity.getId());
        Iterable<DailyStat> entities = this.dailyStatRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		DailyStat entity = new DailyStat();
		this.dailyStatRepository.save(entity);
		DailyStat dailyStat=this.dailyStatRepository.findById(entity.getId()).get();
		Assert.assertTrue(dailyStat!=null);
	}

	
}
