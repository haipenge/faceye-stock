package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.repository.mongo.ForecastIndexRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * ForecastIndex DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ForecastIndexRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ForecastIndexRepository forecastIndexRepository = null;

	@Before
	public void before() throws Exception {
		//this.forecastIndexRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		ForecastIndex entity = new ForecastIndex();
		this.forecastIndexRepository.save(entity);
		Iterable<ForecastIndex> entities = this.forecastIndexRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		ForecastIndex entity = new ForecastIndex();
		this.forecastIndexRepository.save(entity);
        this.forecastIndexRepository.deleteById(entity.getId());
        Iterable<ForecastIndex> entities = this.forecastIndexRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		ForecastIndex entity = new ForecastIndex();
		this.forecastIndexRepository.save(entity);
		ForecastIndex forecastIndex=this.forecastIndexRepository.findById(entity.getId()).get();
		Assert.assertTrue(forecastIndex!=null);
	}

	
}
