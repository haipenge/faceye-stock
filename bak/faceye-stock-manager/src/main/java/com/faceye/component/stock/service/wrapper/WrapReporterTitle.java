package com.faceye.component.stock.service.wrapper;

import java.util.List;

import com.faceye.component.stock.entity.AccountingElement;
import com.faceye.component.stock.entity.AccountingSubject;

public class WrapReporterTitle {
	private AccountingElement accountingElement = null;
	private List<AccountingSubject> accountingSubjects = null;

	public AccountingElement getAccountingElement() {
		return accountingElement;
	}

	public void setAccountingElement(AccountingElement accountingElement) {
		this.accountingElement = accountingElement;
	}

	public List<AccountingSubject> getAccountingSubjects() {
		return accountingSubjects;
	}

	public void setAccountingSubjects(List<AccountingSubject> accountingSubjects) {
		this.accountingSubjects = accountingSubjects;
	}
}
