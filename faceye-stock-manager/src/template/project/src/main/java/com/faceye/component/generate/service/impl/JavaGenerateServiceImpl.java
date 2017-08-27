package com.faceye.component.generate.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.entity.Validate;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.component.generate.service.DataTypeService;
import com.faceye.component.generate.service.EntityService;
import com.faceye.component.generate.service.GenerateService;
import com.faceye.component.generate.service.ProjectService;
import com.faceye.component.generate.service.PropertyService;
import com.faceye.component.generate.service.ValidateService;
import com.faceye.component.generate.service.builder.Builder;
import com.faceye.component.generate.service.model.Document;
import com.faceye.component.generate.util.ProjectUtil;
import com.faceye.feature.util.DirectoryUtil;
import com.faceye.feature.util.PathUtil;
import com.faceye.feature.util.ant.Runner;
import com.faceye.feature.util.shell.Shell;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * Java版源码生成器
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年4月9日
 */
@Service
public class JavaGenerateServiceImpl implements GenerateService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProjectService projectService = null;
	@Autowired
	private ComponentService componentService = null;
	@Autowired
	private EntityService entityService = null;
	@Autowired
	@Qualifier("entityPropertyService")
	private PropertyService propertyServce = null;
	@Autowired
	private DataTypeService dataTypeService = null;
	@Autowired
	private ValidateService validateService = null;

	@Autowired
	@Qualifier("javaBuilder")
	private Builder builder = null;
	/**
	 * 生成代码的目录
	 */
	@Value("#{property['generate.output.dir']}")
	private String generateDir = "";
	/**
	 * freemarker 模板目录
	 */
	@Value("#{property['generate.template.dir']}")
	private String templateDir = "";
	/**
	 * 项目源码目录
	 */
	@Value("#{property['source.dir']}")
	private String sourceDir = "";

	/**
	 * 生成源码
	 */
	@Override
	public void generate(Long projectId) {
		Project project = this.projectService.get(projectId);
//		if (project != null && StringUtils.isNotEmpty(project.getPath())) {
//			if (StringUtils.indexOf(project.getPath(), "/work/Work/FeatureWorkSpace/feature/generate") != -1) {
				// this.generateProject(project);
				builder.buildProjectStruct(project);
				builder.generateCode(project);
				this.chmod(projectId);
				project.setIsGenerate(true);
				project.setGenerateDate(new Date());
				this.projectService.save(project);
//			}
//		}
	}

	/**
	 * 生成项目目录树
	 * @todo
	 * @param projetcId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月17日
	 */
	public List<Map> tree(Long projetcId) {
		List<Map> tree = new ArrayList<Map>();
		Project project = this.projectService.get(projetcId);
		if (null != project) {
			String path = ProjectUtil.getProjectDir(project);
		}
		return tree;
	}

	/**
	 * 打gzip包
	 */
	public String gzip(Long projectId) {
		String fileName = "";
		Project project = this.projectService.get(projectId);
		if (project != null) {
			String buildXmlPath = ProjectUtil.getProjectDir(project) + "/build-tar.xml";
			Runner.exec(buildXmlPath, "tar");
			project.setIsPackage(true);
			project.setPackageDate(new Date());
			this.projectService.save(project);
			fileName = project.getRoot() + ".tar.gz";
		}
		return fileName;
	}

	@Override
	public String run(Long projectId) {
		Project project = this.projectService.get(projectId);
		if (project != null) {
			String path =ProjectUtil.getProjectDir(project);
			String shellScript = path + "/bin/start.sh" + (StringUtils.isEmpty(project.getPort()) ? "" : " " + project.getPort());
			Shell.runShell(shellScript);
			project.setIsRun(true);
			project.setRunDate(new Date());
			this.projectService.save(project);
		}
		return null;
	}

	@Override
	public String stop(Long projectId) {
		return null;
	}

	/**
	 * 修改项目bin目录权限，附加执行shell的权限
	 * @todo
	 * @param projectId
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年4月24日
	 */
	private void chmod(Long projectId) {
		Project project = this.projectService.get(projectId);
		if (project != null) {
			String path = ProjectUtil.getProjectDir(project);
			// String shellScript=path+"/bin/start.sh"+(StringUtils.isEmpty(project.getPort())?"":" "+project.getPort());
			String command = " chmod +x " + path + "/bin/*";
			Shell.runCommand(command);
		}
	}

}
