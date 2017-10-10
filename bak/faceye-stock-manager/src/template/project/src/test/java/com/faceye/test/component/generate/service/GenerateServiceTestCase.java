package com.faceye.test.component.generate.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.faceye.component.generate.service.GenerateService;
import com.faceye.test.feature.service.BaseServiceTestCase;

public class GenerateServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private GenerateService generateService = null;
	
	private Long projectId=null;
	
	@Before
	public void set() throws Exception{
		projectId=2L;
	}
	
	@Test
	public void testGenerate() throws Exception{
		this.generateService.generate(projectId);
	}
	@Test
	public void testRun() throws Exception{
		this.generateService.run(projectId);
	}
}
