package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.BonusRecord;
import com.faceye.component.stock.repository.mongo.BonusRecordRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * BonusRecord DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class BonusRecordRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private BonusRecordRepository bonusRecordRepository = null;

	@Before
	public void before() throws Exception {
		//this.bonusRecordRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		BonusRecord entity = new BonusRecord();
		this.bonusRecordRepository.save(entity);
		Iterable<BonusRecord> entities = this.bonusRecordRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		BonusRecord entity = new BonusRecord();
		this.bonusRecordRepository.save(entity);
        this.bonusRecordRepository.delete(entity.getId());
        Iterable<BonusRecord> entities = this.bonusRecordRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		BonusRecord entity = new BonusRecord();
		this.bonusRecordRepository.save(entity);
		BonusRecord bonusRecord=this.bonusRecordRepository.findOne(entity.getId());
		Assert.isTrue(bonusRecord!=null);
	}

	
}
