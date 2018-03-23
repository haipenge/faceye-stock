package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.FinancialReport;
import com.faceye.component.stock.repository.mongo.FinancialReportRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * FinancialReport DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class FinancialReportRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private FinancialReportRepository financialReportRepository = null;

	@Before
	public void before() throws Exception {
		//this.financialReportRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		FinancialReport entity = new FinancialReport();
		this.financialReportRepository.save(entity);
		Iterable<FinancialReport> entities = this.financialReportRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		FinancialReport entity = new FinancialReport();
		this.financialReportRepository.save(entity);
        this.financialReportRepository.deleteById(entity.getId());
        Iterable<FinancialReport> entities = this.financialReportRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		FinancialReport entity = new FinancialReport();
		this.financialReportRepository.save(entity);
		FinancialReport financialReport=this.financialReportRepository.findById(entity.getId()).get();
		Assert.assertTrue(financialReport!=null);
	}

	
}
