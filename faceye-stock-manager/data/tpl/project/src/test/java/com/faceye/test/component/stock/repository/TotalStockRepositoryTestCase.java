package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.TotalStock;
import com.faceye.component.stock.repository.mongo.TotalStockRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * TotalStock DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class TotalStockRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private TotalStockRepository totalStockRepository = null;

	@Before
	public void before() throws Exception {
		//this.totalStockRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		TotalStock entity = new TotalStock();
		this.totalStockRepository.save(entity);
		Iterable<TotalStock> entities = this.totalStockRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		TotalStock entity = new TotalStock();
		this.totalStockRepository.save(entity);
        this.totalStockRepository.deleteById(entity.getId());
        Iterable<TotalStock> entities = this.totalStockRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		TotalStock entity = new TotalStock();
		this.totalStockRepository.save(entity);
		TotalStock totalStock=this.totalStockRepository.findById(entity.getId()).get();
		Assert.assertTrue(totalStock!=null);
	}

	
}
