package com.faceye.test.component.generate.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.repository.mongo.ProjectRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Project DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ProjectRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ProjectRepository projectRepository = null;

	@Before
	public void before() throws Exception {
		//this.projectRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Project entity = new Project();
		this.projectRepository.save(entity);
		Iterable<Project> entities = this.projectRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Project entity = new Project();
		this.projectRepository.save(entity);
        this.projectRepository.delete(entity.getId());
        Iterable<Project> entities = this.projectRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Project entity = new Project();
		this.projectRepository.save(entity);
		Project project=this.projectRepository.findOne(entity.getId());
		Assert.isTrue(project!=null);
	}

	
}
