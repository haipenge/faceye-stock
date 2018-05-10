package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.repository.mongo.CategoryRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Category DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class CategoryRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private CategoryRepository categoryRepository = null;

	@Before
	public void before() throws Exception {
		//this.categoryRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Category entity = new Category();
		this.categoryRepository.save(entity);
		Iterable<Category> entities = this.categoryRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Category entity = new Category();
		this.categoryRepository.save(entity);
        this.categoryRepository.deleteById(entity.getId());
        Iterable<Category> entities = this.categoryRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Category entity = new Category();
		this.categoryRepository.save(entity);
		Category category=this.categoryRepository.findById(entity.getId()).get();
		Assert.assertTrue(category!=null);
	}

	
}
