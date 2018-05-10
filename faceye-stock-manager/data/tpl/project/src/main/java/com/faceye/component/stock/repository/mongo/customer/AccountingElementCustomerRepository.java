package com.faceye.component.stock.repository.mongo.customer;

import java.util.List;

import com.faceye.component.stock.entity.AccountingElement;
/**
 * AccountingElement 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AccountingElementCustomerRepository {
	
	public List<AccountingElement> getAccountingElementsByReportCategory(Long reportCategoryId);
}/**@generate-repository-source@**/
