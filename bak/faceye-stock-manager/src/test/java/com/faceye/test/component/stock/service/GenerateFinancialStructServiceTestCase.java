package com.faceye.test.component.stock.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.stock.service.GenerateFinancialStructService;
import com.faceye.test.feature.service.BaseServiceTestCase;

public class GenerateFinancialStructServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private GenerateFinancialStructService generateFinancialStructService = null;

	@Test
	public void testGenerate() throws Exception {
		this.generateFinancialStructService.generate();
		Assert.isTrue(true);
	}
}
