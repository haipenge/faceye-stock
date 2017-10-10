package com.faceye.component.generate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.repository.mongo.ProjectRepository;
import com.faceye.component.generate.service.ProjectService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Project 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service
public class ProjectServiceImpl extends BaseMongoServiceImpl<Project, Long, ProjectRepository> implements ProjectService {

	@Autowired
	public ProjectServiceImpl(ProjectRepository dao) {
		super(dao);
	}
	
	
}/**@generate-service-source@**/
