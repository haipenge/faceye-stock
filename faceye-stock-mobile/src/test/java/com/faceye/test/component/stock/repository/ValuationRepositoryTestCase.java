package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.Valuation;
import com.faceye.component.stock.repository.mongo.ValuationRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Valuation DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ValuationRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ValuationRepository valuationRepository = null;

	@Before
	public void before() throws Exception {
		//this.valuationRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Valuation entity = new Valuation();
		this.valuationRepository.save(entity);
		Iterable<Valuation> entities = this.valuationRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Valuation entity = new Valuation();
		this.valuationRepository.save(entity);
        this.valuationRepository.deleteById(entity.getId());
        Iterable<Valuation> entities = this.valuationRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Valuation entity = new Valuation();
		this.valuationRepository.save(entity);
		Valuation valuation=this.valuationRepository.findById(entity.getId()).get();
		Assert.assertTrue(valuation!=null);
	}

	
}
