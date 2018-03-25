package com.faceye.test.component.stock.repository;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.repository.mongo.AccountingElementRepository;
import com.faceye.component.stock.repository.mongo.customer.AccountingElementCustomerRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * AccountingElement DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class AccountingElementRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AccountingElementRepository accountingElementRepository = null;
	@Autowired
	private AccountingElementCustomerRepository accountingElementCustomerRepository=null;

	@Before
	public void before() throws Exception {
		//this.accountingElementRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		AccountingElement entity = new AccountingElement();
		this.accountingElementRepository.save(entity);
		Iterable<AccountingElement> entities = this.accountingElementRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		AccountingElement entity = new AccountingElement();
		this.accountingElementRepository.save(entity);
        this.accountingElementRepository.deleteById(entity.getId());
        Iterable<AccountingElement> entities = this.accountingElementRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		AccountingElement entity = new AccountingElement();
		this.accountingElementRepository.save(entity);
		AccountingElement accountingElement=this.accountingElementRepository.findById(entity.getId()).get();
		Assert.assertTrue(accountingElement!=null);
	}
@Test
	public void testGetAccountingElementsByReportCategoryId() throws Exception{
	   Long reportCategoryId=3L;
		List<AccountingElement> eles=this.accountingElementCustomerRepository.getAccountingElementsByReportCategory(reportCategoryId);
        for(AccountingElement ele : eles){
        	System.out.println(ele.getName()+":"+ele.getReportCategory().getName());
        }
		Assert.assertTrue(CollectionUtils.isNotEmpty(eles));
	}
}
