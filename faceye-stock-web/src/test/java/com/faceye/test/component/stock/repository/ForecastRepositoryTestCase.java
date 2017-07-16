package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.Forecast;
import com.faceye.component.stock.repository.mongo.ForecastRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Forecast DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ForecastRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ForecastRepository forecastRepository = null;

	@Before
	public void before() throws Exception {
		//this.forecastRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Forecast entity = new Forecast();
		this.forecastRepository.save(entity);
		Iterable<Forecast> entities = this.forecastRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Forecast entity = new Forecast();
		this.forecastRepository.save(entity);
        this.forecastRepository.delete(entity.getId());
        Iterable<Forecast> entities = this.forecastRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Forecast entity = new Forecast();
		this.forecastRepository.save(entity);
		Forecast forecast=this.forecastRepository.findOne(entity.getId());
		Assert.isTrue(forecast!=null);
	}

	
}
