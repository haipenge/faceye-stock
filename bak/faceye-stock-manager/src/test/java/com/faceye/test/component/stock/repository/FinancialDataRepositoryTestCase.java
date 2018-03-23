package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.FinancialData;
import com.faceye.component.stock.repository.mongo.FinancialDataRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * FinancialData DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class FinancialDataRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private FinancialDataRepository financialDataRepository = null;

	@Before
	public void before() throws Exception {
		//this.financialDataRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		FinancialData entity = new FinancialData();
		this.financialDataRepository.save(entity);
		Iterable<FinancialData> entities = this.financialDataRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		FinancialData entity = new FinancialData();
		this.financialDataRepository.save(entity);
        this.financialDataRepository.deleteById(entity.getId());
        Iterable<FinancialData> entities = this.financialDataRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		FinancialData entity = new FinancialData();
		this.financialDataRepository.save(entity);
		FinancialData financialData=this.financialDataRepository.findById(entity.getId()).get();
		Assert.assertTrue(financialData!=null);
	}

	
}
