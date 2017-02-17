package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.repository.mongo.ReportCategoryRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * ReportCategory DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ReportCategoryRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ReportCategoryRepository reportCategoryRepository = null;

	@Before
	public void before() throws Exception {
		//this.reportCategoryRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		ReportCategory entity = new ReportCategory();
		this.reportCategoryRepository.save(entity);
		Iterable<ReportCategory> entities = this.reportCategoryRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		ReportCategory entity = new ReportCategory();
		this.reportCategoryRepository.save(entity);
        this.reportCategoryRepository.delete(entity.getId());
        Iterable<ReportCategory> entities = this.reportCategoryRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		ReportCategory entity = new ReportCategory();
		this.reportCategoryRepository.save(entity);
		ReportCategory reportCategory=this.reportCategoryRepository.findOne(entity.getId());
		Assert.isTrue(reportCategory!=null);
	}

	
}
