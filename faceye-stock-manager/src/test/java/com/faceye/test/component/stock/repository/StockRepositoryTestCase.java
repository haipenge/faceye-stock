package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.repository.mongo.StockRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Stock DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class StockRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private StockRepository stockRepository = null;

	@Before
	public void before() throws Exception {
		this.stockRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Stock entity = new Stock();
		this.stockRepository.save(entity);
		Iterable<Stock> entities = this.stockRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Stock entity = new Stock();
		this.stockRepository.save(entity);
        this.stockRepository.delete(entity.getId());
        Iterable<Stock> entities = this.stockRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Stock entity = new Stock();
		this.stockRepository.save(entity);
		Stock stock=this.stockRepository.findOne(entity.getId());
		Assert.isTrue(stock!=null);
	}

	
}
