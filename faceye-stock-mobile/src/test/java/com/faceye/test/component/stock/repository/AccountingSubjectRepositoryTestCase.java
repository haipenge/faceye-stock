package com.faceye.test.component.stock.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.entity.AccountingSubject;
import com.faceye.component.stock.repository.mongo.AccountingSubjectRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * AccountingSubject DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class AccountingSubjectRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AccountingSubjectRepository accountingSubjectRepository = null;

	@Before
	public void before() throws Exception {
		//this.accountingSubjectRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		AccountingSubject entity = new AccountingSubject();
		this.accountingSubjectRepository.save(entity);
		Iterable<AccountingSubject> entities = this.accountingSubjectRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		AccountingSubject entity = new AccountingSubject();
		this.accountingSubjectRepository.save(entity);
        this.accountingSubjectRepository.delete(entity.getId());
        Iterable<AccountingSubject> entities = this.accountingSubjectRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		AccountingSubject entity = new AccountingSubject();
		this.accountingSubjectRepository.save(entity);
		AccountingSubject accountingSubject=this.accountingSubjectRepository.findOne(entity.getId());
		Assert.isTrue(accountingSubject!=null);
	}

	
}
