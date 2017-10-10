package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.DailyData;
import com.faceye.component.stock.repository.mongo.DailyDataRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * DailyData DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class DailyDataRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DailyDataRepository dailyDataRepository = null;

	@Before
	public void before() throws Exception {
		this.dailyDataRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataRepository.save(entity);
		Iterable<DailyData> entities = this.dailyDataRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataRepository.save(entity);
        this.dailyDataRepository.delete(entity.getId());
        Iterable<DailyData> entities = this.dailyDataRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		DailyData entity = new DailyData();
		this.dailyDataRepository.save(entity);
		DailyData dailyData=this.dailyDataRepository.findOne(entity.getId());
		Assert.isTrue(dailyData!=null);
	}

	
}
