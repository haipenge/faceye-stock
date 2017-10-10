package com.faceye.component.generate.service.builder.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.service.builder.BaseBuilder;
import com.faceye.component.generate.service.builder.Builder;
import com.faceye.component.generate.service.model.Document;
import com.faceye.component.generate.util.FreeMarkerHandler;
import com.faceye.component.generate.util.ProjectUtil;
import com.faceye.feature.util.DirectoryUtil;
import com.faceye.feature.util.PathUtil;

import freemarker.template.Template;

@Service
public class JavaBuilder extends BaseBuilder implements Builder {

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

	@Override
	public boolean buildProjectStruct(Project project) {
		// this.generateProject(project);
		this.generateEmptyProjectStruct(project);
		// this.copyBuildXml(project);
		this.replacePackageInJavaFiles(project);
		return true;
	}

	/**
	 * 拷贝faceye-*项目
	 * 
	 * @todo
	 * @param sourceDir
	 * @param targetDir
	 * @param packageName
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年5月8日
	 */
	private void copySource(String sourceDir, String targetDir, String packageName) {
		// 构建包路径
		String packageDirs = "/";
		String[] packages = packageName.split("\\.");
		for (int i = 0; i < packages.length; i++) {
			packageDirs += packages[i];
			packageDirs += "/";
		}
		String sourcePackageDir = targetDir + "/src/main/java" + packageDirs;
		PathUtil.mkdir(sourcePackageDir);
		String sourceTestPackageDir = targetDir + "/src/test/java" + packageDirs;
		PathUtil.mkdir(sourceTestPackageDir);
		try {
			String javaSourceDir = sourceDir + "/src/main/java/com/faceye";
			File file = new File(javaSourceDir);
			if (file.exists()) {
				DirectoryUtil.copyDirectiory(javaSourceDir, sourcePackageDir);
			}
			// Test case
			String javaTestSourceDir = sourceDir + "/src/test/java/com/faceye";
			File testFile = new File(javaTestSourceDir);
			if (testFile.exists()) {
				DirectoryUtil.copyDirectiory(javaTestSourceDir, sourceTestPackageDir);
			}
			DirectoryUtil.copyDirectiory(sourceDir, targetDir);
			if (!StringUtils.equals(packages[0], "com")) {
				DirectoryUtil.delete(targetDir + "/src/test/java/com");
			} else {
				if (!StringUtils.equals(packages[1], "faceye")) {
					DirectoryUtil.delete(targetDir + "/src/test/java/com/faceye");
				}
			}
			if (!StringUtils.equals(packages[0], "com")) {
				DirectoryUtil.delete(targetDir + "/src/main/java/com");
			} else {
				if (!StringUtils.equals(packages[1], "faceye")) {
					DirectoryUtil.delete(targetDir + "/src/main/java/com/faceye");
				}
			}
		} catch (IOException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		}
	}

	/**
	 * 清理输出目录
	 * 
	 * @todo
	 * @param project
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private boolean clearOutputDir(Project project) {
		boolean res = false;
		String dir = this.getProjectDir(project);
		if (null != project && StringUtils.isNotEmpty(dir)) {
			logger.debug(">>FaceYe  will delete dir of:" + dir);
			try {
				if (StringUtils.indexOf(dir, "/work/Work/FeatureWorkSpace/feature/generate") != -1) {
					DirectoryUtil.delete(dir);
				}
			} catch (IOException e) {
				logger.error(">>FaceYe throws Exception: --->", e);
			}
		}
		return res;
	}

	// ///////////////////////////////////////生成空项目结构//////////////////////////////////////////////////////////
	private boolean generateEmptyProjectStruct(Project project) {
		boolean res = false;
		String packageName = project.getPackageName();
		String path = this.getProjectDir(project);
		String[] packages = packageName.split("\\.");
		String projectName = packages[1];
		String faceyeTemplateSourceDir = sourceDir + "/faceye-template";
		if (StringUtils.equals(project.getRepositoryType(), "jpa")) {
			faceyeTemplateSourceDir += "/jpa";
		} else {
			faceyeTemplateSourceDir += "/mongo";
		}
		// Manager
		String emptyProjectTargetDir = path + "/" + project.getName();
		// this.copySource(faceyeTemplateSourceDir + "/faceye-template-manager", emptyProjectTargetDir + "/" + project.getName() + "-manager", packageName);
		try {
			DirectoryUtil.copyDirectiory(faceyeTemplateSourceDir + "/faceye-template-manager", emptyProjectTargetDir + "/" + project.getName() + "-manager");
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		// Entity
		String emptyEntityPRojectTargetDir = emptyProjectTargetDir + "/" + project.getName() + "-entity";
		// this.copySource(faceyeTemplateSourceDir + "/faceye-template-entity", emptyEntityPRojectTargetDir, packageName);
		try {
			DirectoryUtil.copyDirectiory(faceyeTemplateSourceDir + "/faceye-template-entity", emptyEntityPRojectTargetDir);
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		// API
		String apiProjectTargetDir = emptyProjectTargetDir + "/" + project.getName() + "-api";
		// this.copySource(faceyeTemplateSourceDir + "/faceye-template-api", apiProjectTargetDir, packageName);
		try {
			DirectoryUtil.copyDirectiory(faceyeTemplateSourceDir + "/faceye-template-api", apiProjectTargetDir);
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		// WEB
		String webProjectTargetDir = emptyProjectTargetDir + "/" + project.getName() + "-web";
		// this.copySource(faceyeTemplateSourceDir + "/faceye-template-web", webProjectTargetDir, packageName);
		try {
			DirectoryUtil.copyDirectiory(faceyeTemplateSourceDir + "/faceye-template-web", webProjectTargetDir);
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		return res;
	}

	/**
	 * 拷贝构建文件
	 * 
	 * @todo
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年4月17日
	 */
	// private boolean copyBuildXml(Project project) {
	// boolean res = false;
	// try {
	// DirectoryUtil.copyFile(new File(sourceDir + "/build-tar.xml"), new File(this.getProjectDir(project)+ "/build-tar.xml"));
	// } catch (IOException e) {
	// logger.error(">>FaceYe throws Exception: --->", e);
	// }
	// return res;
	// }

	/**
	 * 在文件中替换包名 com.faceye -> project.packageName
	 * 
	 * @todo
	 * @param project
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private void replacePackageInJavaFiles(Project project) {
		String[] packages = project.getPackageName().split("\\.");
		String packagePath = "";
		for (int i = 0; i < packages.length; i++) {
			String p = packages[i];
			packagePath += p;
			if (i != packages.length - 1) {
				packagePath += "\\/";
			}

		}
		// String projectName = packages[1];
		String projectName = project.getName();
		String dir = this.getProjectDir(project);
		dir=dir+"/"+project.getName();
		List<Map<String, String>> replaces = new ArrayList<Map<String, String>>(0);
		Map<String, String> rep1 = new HashMap();
		rep1.put("pattern", "@PROJECT-NAME@");
		rep1.put("replacement", project.getName());
		replaces.add(rep1);
		Map<String, String> rep2 = new HashMap();
		rep2.put("pattern", "@PROJECT-PACKAGE@");
		rep2.put("replacement", project.getPackageName());
		replaces.add(rep2);
		Map<String, String> rep3 = new HashMap();
		rep3.put("pattern", "@PROJECT-PACKAGE-PATH@");
		rep3.put("replacement", packagePath);
		replaces.add(rep3);

		// Map<String, String> rep4 = new HashMap();
		// rep4.put("pattern", "com\\/faceye");
		// rep4.put("replacement", packagePath);
		// replaces.add(rep4);
		// Map<String, String> rep5 = new HashMap();
		// rep5.put("pattern", "com\\.faceye");
		// rep5.put("replacement", project.getPackageName());
		// replaces.add(rep5);
		// Map<String, String> rep7 = new HashMap();
		// rep7.put("pattern", "faceye-template");
		// rep7.put("replacement", projectName);
		// replaces.add(rep7);
		// Map<String, String> rep6 = new HashMap();
		// rep6.put("pattern", "faceye");
		// rep6.put("replacement", packages[1]);
		// replaces.add(rep6);
		// DirectoryUtil.replace(dir, "faceye-template-manager", projectName + "-main-manager");
		// DirectoryUtil.replace(dir, "faceye-template-web", projectName + "-main-web");
		// DirectoryUtil.replace(dir, "faceye-template-server", projectName + "-main-server");
		// DirectoryUtil.replace(dir, "com\\/faceye", packagePath);
		// DirectoryUtil.replace(dir, "com\\.faceye", project.getPackageName());
		// DirectoryUtil.replace(dir, "faceye", projectName);
		DirectoryUtil.replace(dir, replaces);
	}

	// //////////////////////////////以下是生成源码相关工操作//////////////////////////////////////////////
	@Override
	public boolean generateCode(Project project) {
		boolean res = false;
		Map searchComponents = new HashMap();
		searchComponents.put("EQ|project.$id", project.getId());
		List<Component> components = this.componentService.getPage(searchComponents, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(components)) {
			for (Component component : components) {
				this.generateComponent(project, component);
				this.generateJavaCode(project, component);
			}
		}
		this.generateLeftMenu(project, "manager");
		return res;
	}

	/**
	 * 生成操作菜单
	 * 
	 * @todo
	 * @param project
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年4月11日
	 */
	private boolean generateLeftMenu(Project project, String projectNameSuffix) {
		boolean res = false;
		List<Entity> allEntities = new ArrayList<Entity>();
		Map searchComponents = new HashMap();
		searchComponents.put("EQ|project.$id", project.getId());
		List<Component> components = this.componentService.getPage(searchComponents, 1, 0).getContent();
		for (Component component : components) {
			Map searchParams = new HashMap();
			searchParams.put("EQ|component.$id", component.getId());
			List<Entity> entities = this.entityService.getPage(searchParams, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(entities)) {
				allEntities.addAll(entities);
			}
		}
		Map map = new HashMap();
		map.put("entities", allEntities);
		map.put("components", components);
		String jspPath = this.getSrcMainWebRootDir(project, null, projectNameSuffix);
		String leftMenuJspPath = jspPath + "/component/core/template/manager/" + project.getName() + "_default_center_left.jsp";
		FreeMarkerHandler.getInstance().processGenerate("jsp_left_menu.ftl", map, leftMenuJspPath, false);
		return res;
	}

	/**
	 * 生成模块
	 * 
	 * @todo
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private boolean generateComponent(Project project, Component component) {
		boolean res = false;
		PathUtil.mkdir(this.getSrcJavaEntityDir(project, component));
		PathUtil.mkdir(this.getSrcJavaServiceImplDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "manager") + "/customer/impl");
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "manager") + "/gen");
		PathUtil.mkdir(this.getSrcJavaControllerDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcJavaServiceImplDir(project, component, "api"));
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "api"));
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "api") + "/customer/impl");
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "api") + "/gen");
		PathUtil.mkdir(this.getSrcJavaControllerDir(project, component, "api"));
		PathUtil.mkdir(this.getSrcJavaServiceImplDir(project, component, "web"));
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "web"));
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "web") + "/customer/impl");
		PathUtil.mkdir(this.getSrcJavaRepositoryDir(project, component, "web") + "/gen");
		PathUtil.mkdir(this.getSrcJavaControllerDir(project, component, "web"));

		PathUtil.mkdir(this.getSrcMainWebRootJspDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcMainWebRootCssDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcMainWebRootJavaScriptDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcMainWebRootJspDir(project, component, "web"));
		PathUtil.mkdir(this.getSrcMainWebRootCssDir(project, component, "web"));
		PathUtil.mkdir(this.getSrcMainWebRootJavaScriptDir(project, component, "web"));

		PathUtil.mkdir(this.getSrcTestJavaDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcTestJavaRepositoryDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcTestJavaServiceDir(project, component, "manager"));
		PathUtil.mkdir(this.getSrcTestJavaDir(project, component, "api"));
		PathUtil.mkdir(this.getSrcTestJavaRepositoryDir(project, component, "api"));
		PathUtil.mkdir(this.getSrcTestJavaServiceDir(project, component, "api"));
		PathUtil.mkdir(this.getSrcTestJavaDir(project, component, "web"));
		PathUtil.mkdir(this.getSrcTestJavaRepositoryDir(project, component, "web"));
		PathUtil.mkdir(this.getSrcTestJavaServiceDir(project, component, "web"));
		return res;
	}

	/**
	 * 生成某一模块的java代码
	 * 
	 * @todo
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private boolean generateJavaCode(Project project, Component component) {
		boolean res = false;
		boolean isJpa = StringUtils.equalsIgnoreCase("jpa", project.getRepositoryType());
		Map searchParams = new HashMap();

		searchParams.put("EQ|component.$id", component.getId());
		List<Entity> entities = this.entityService.getPage(searchParams, 1, 0).getContent();
		String[] projectTypes = new String[] { "manager", "web", "api" };
		if (CollectionUtils.isNotEmpty(entities)) {
			for (Entity entity : entities) {
				Document document = this.buildDynamicDocument(project, component, entity);
				// 生成实体代码
				this.generateEntityCode(project, component, entity);
				for (String projectType : projectTypes) {
					this.generateProjectCode(project, component, entity, projectType);
				}
			}
		}
		return res;
	}

	/**
	 * x-smart-entity 模块生成
	 * @param project
	 * @param component
	 * @param entity
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月29日 下午2:24:14
	 */
	private void generateEntityCode(Project project, Component component, Entity entity) {
		Document document = this.buildDynamicDocument(project, component, entity);
		String repositoryType = project.getRepositoryType();
		String tplName = "";
		String entityPath = this.getSrcJavaEntityDir(project, component);
		entityPath += "/" + entity.getClassName() + ".java";
		logger.debug(">>FaceYe --> Entity path is:" + entityPath);
		if (StringUtils.equalsIgnoreCase(repositoryType, "jpa")) {
			tplName = "jpa_entity.ftl";
		} else {
			tplName = "document.ftl";
		}
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, entityPath, true);
		// i18n
		String i18nPath = this.getSrcJavaEntityI18NDir(project);
		PathUtil.mkdir(i18nPath);
		String filePath = i18nPath + "/" + entity.getClassName().toLowerCase() + "_zh_CN.properties";
		tplName = "i18n.ftl";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
	}

	/**
	 * 生成管理后台代码
	 * 
	 * @param project
	 * @param component
	 * @param entity
	 * @param projectType
	 *            -[manager,api,web]
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月29日 下午3:07:18
	 */
	private void generateProjectCode(Project project, Component component, Entity entity, String projectType) {
		Document document = this.buildDynamicDocument(project, component, entity);
		String tplName = "repository.ftl";

		String filePath = "";
		// String repositoryTestCaseTemplate = "repository_test_case.ftl";
		if (StringUtils.equalsIgnoreCase(project.getRepositoryType(), "jpa")) {
			tplName = "jpa_repository.ftl";
			// repositoryTestCaseTemplate = "jpa_repository_test_case.ftl";
		} else {
			tplName = "repository.ftl";
		}
		// 生成manager Repository
		String path = this.getSrcJavaRepositoryDir(project, component, projectType);
		filePath = path + "/" + entity.getClassName() + "Repository.java";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		// customer repository
		tplName = "mongo_customer_repository.ftl";
		filePath = path + "/customer/" + entity.getClassName() + "CustomerRepository.java";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		filePath = path + "/customer/impl/" + entity.getClassName() + "CustomerRepositoryImpl.java";
		tplName = "mongo_customer_repository_impl.ftl";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		// gen repository
		filePath = path + "/gen/" + entity.getClassName() + "GenRepository.java";
		tplName = "mongo_gen_repository.ftl";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, true);
		// 生成 mananger respository test case
		tplName = "repository_test_case.ftl";
		path = this.getSrcTestJavaRepositoryDir(project, component, projectType);
		filePath = path + "/" + entity.getClassName() + "RepositoryTestCase.java";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		// 生成service 接口
		tplName = "service.ftl";
		path = this.getSrcJavaServiceDir(project, component, projectType);
		filePath = path + "/" + entity.getClassName() + "Service.java";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		// 生成Service 实现类
		path = this.getSrcJavaServiceImplDir(project, component, projectType);
		filePath = path + "/" + entity.getClassName() + "ServiceImpl.java";
		tplName = "service_impl.ftl";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		// 生成Controller
		if (StringUtils.equalsIgnoreCase(projectType, "api")) {
			tplName = "api_controller.ftl";
		} else {
			tplName = "controller.ftl";
		}
		String controllerPath = this.getSrcJavaControllerDir(project, component, projectType);
		filePath = controllerPath + "/" + entity.getClassName() + "Controller.java";
		FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		
		// 生成tiles
		if (!StringUtils.equalsIgnoreCase(projectType, "api")) {
			path = this.getSrcMainResourceDir(project, component, projectType);
			if (StringUtils.equalsIgnoreCase(projectType, "manager")) {
				filePath = path + "/tiles/manager";
				PathUtil.mkdir(filePath);
				filePath += "/tiles-manager-" + entity.getClassName().toLowerCase() + "-defs.xml";
			} else if (StringUtils.equalsIgnoreCase(projectType, "web")) {
				filePath = path + "/tiles/web";
				PathUtil.mkdir(filePath);
				filePath += "/tiles-web-" + entity.getClassName().toLowerCase() + "-defs.xml";
			}

			tplName = "tiles.ftl";
			FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		}
		if (!StringUtils.equalsIgnoreCase(projectType, "api")) {
			// 生成jsp center
			String jspPath = this.getSrcMainWebRootJspDir(project, component, projectType);
			filePath = jspPath + "/" + StringUtils.uncapitalize(entity.getClassName());
			PathUtil.mkdir(filePath);
			filePath = filePath + "/" + StringUtils.uncapitalize(entity.getClassName()) + "_center.jsp";
			tplName = "jsp_center.ftl";
			FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
			// 生成jsp update
			jspPath = this.getSrcMainWebRootJspDir(project, component, projectType);
			filePath = jspPath + "/" + StringUtils.uncapitalize(entity.getClassName());
			PathUtil.mkdir(filePath);
			filePath = filePath + "/" + StringUtils.uncapitalize(entity.getClassName()) + "_update.jsp";
			tplName = "jsp_update.ftl";
			FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
			// 生成jsp detail
			jspPath = this.getSrcMainWebRootJspDir(project, component, projectType);
			filePath = jspPath + "/" + StringUtils.uncapitalize(entity.getClassName());
			PathUtil.mkdir(filePath);
			filePath = filePath + "/" + StringUtils.uncapitalize(entity.getClassName()) + "_detail.jsp";
			tplName = "jsp_detail.ftl";
			FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
			// 生成css
			String cssPath = this.getSrcMainWebRootCssDir(project, component, projectType);
			filePath = cssPath + "/" + StringUtils.uncapitalize(entity.getClassName());
			PathUtil.mkdir(filePath);
			filePath = filePath + "/" + StringUtils.uncapitalize(entity.getClassName()) + ".css";
			tplName = "css.ftl";
			FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
			// 生成js
			String jsPath = this.getSrcMainWebRootJavaScriptDir(project, component, projectType);
			filePath = jsPath + "/" + StringUtils.uncapitalize(entity.getClassName());
			PathUtil.mkdir(filePath);
			filePath = filePath + "/" + StringUtils.uncapitalize(entity.getClassName()) + ".js";
			tplName = "js.ftl";
			FreeMarkerHandler.getInstance().processGenerate(tplName, document, filePath, false);
		}
	}

	// //////////////////////////////////目录操作//////////////////////////////////

	private String[] dirs(Project project, Component component) {
		String[] dirs = null;
		List<String> items = new ArrayList<String>(0);
		String projectName = this.getProjectName(project);
		String projectDir = this.getProjectDir(project);
		String srcMainWebRootDir = projectDir + "/" + project.getName() + "/" + project.getName() + "-manager/src/main/webapp";
		return dirs;
	}

	/**
	 * 取得WebRootDir
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcMainWebRootDir(Project project, Component component, String projectNameSuffix) {
		String srcMainWebRootDir = "";
		String projectName = this.getProjectName(project);
		String projectDir = this.getProjectDir(project);
		srcMainWebRootDir = projectDir + "/" + project.getName() + "/" + project.getName() + "-" + projectNameSuffix + "/src/main/webapp";
		return srcMainWebRootDir;
	}

	/**
	 * 取得指定模块的jsp目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcMainWebRootJspDir(Project project, Component component, String projectNameSuffix) {
		String jspDir = "";
		String srcMainWebRootDir = this.getSrcMainWebRootDir(project, component, projectNameSuffix);
		jspDir = srcMainWebRootDir + "/component/" + component.getPackageName();
		return jspDir;
	}

	/**
	 * 取得模块的css目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcMainWebRootCssDir(Project project, Component component, String projectNameSuffix) {
		String srcMainWebRootDir = this.getSrcMainWebRootDir(project, component, projectNameSuffix);
		String cssDir = srcMainWebRootDir;
		cssDir += "/css/component/" + component.getPackageName();
		return cssDir;
	}

	/**
	 * 取得模块的js目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcMainWebRootJavaScriptDir(Project project, Component component, String projectNameSuffix) {
		String srcMainWebRootDir = this.getSrcMainWebRootDir(project, component, projectNameSuffix);
		String jsDir = srcMainWebRootDir;
		jsDir += "/js/component/" + component.getPackageName();
		return jsDir;
	}

	/**
	 * 取得src/main/resource
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcMainResourceDir(Project project, Component component, String projectNameSuffix) {
		String mainResourceDir = "";
		String projectName = this.getProjectName(project);
		String projectDir = this.getProjectDir(project);
		mainResourceDir = projectDir + "/" + project.getName() + "/" + project.getName() + "-" + projectNameSuffix + "/src/main/resources";
		return mainResourceDir;
	}

	/**
	 * 取得src Doc 目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcJavaEntityDir(Project project, Component component) {
		String srcJava = "";
		String outdir = this.getProjectDir(project);
		String packageDir = this.getProjectPackageDir(project);
		// 生成src/main/java下的三层目录
		String projectName = this.getProjectName(project);
		String mainProjectDir = outdir + "/" + project.getName() + "" + "/" + project.getName() + "-entity";
		srcJava = mainProjectDir += "/src/main/java" + packageDir + "component/" + component.getPackageName() + "/";
		String docDir = "";
		// String srcJavaDir = this.getSrcJavaDir(project, component);
		docDir = srcJava + "entity";
		return docDir;
	}

	/**
	 * 取得项目的包名,如由project.packageName=com.faceye，取得/com/faceye/
	 * 
	 * @todo
	 * @param project
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年5月8日
	 */
	private String getProjectPackageDir(Project project) {
		String packageDir = "/";
		String packageName = project.getPackageName();
		String[] packages = packageName.split("\\.");
		for (String p : packages) {
			packageDir += p;
			packageDir += "/";
		}
		return packageDir;
	}

	/**
	 * 实体资源文件路径
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年4月19日
	 */
	private String getSrcJavaEntityI18NDir(Project project) {
		String srcI18NDir = "";
		String outdir = this.getProjectDir(project);
		// 生成src/main/java下的三层目录
		String projectName = this.getProjectName(project);
		String mainProjectDir = outdir + "/" + project.getName() + "/" + project.getName() + "-entity";
		srcI18NDir = mainProjectDir += "/src/main/resources/i18n/";
		return srcI18NDir;
	}

	/**
	 * 取得repository 目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcJavaRepositoryDir(Project project, Component component, String projectNameSuffix) {
		boolean isJpa = StringUtils.equals("jpa", project.getRepositoryType());
		String repositoryDir = "";
		String srcJavaDir = this.getSrcJavaDir(project, component, projectNameSuffix);
		if (isJpa) {
			repositoryDir = srcJavaDir + "repository/jpa";
		} else {
			repositoryDir = srcJavaDir + "repository/mongo";
		}
		return repositoryDir;
	}

	/**
	 * 取得Service 接口目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcJavaServiceDir(Project project, Component component, String projectNameSuffix) {
		String serviceDir = "";
		String srcJava = this.getSrcJavaDir(project, component, projectNameSuffix);
		serviceDir = srcJava + "service";
		return serviceDir;
	}

	/**
	 * 取得service 实现类的目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcJavaServiceImplDir(Project project, Component component, String projectNameSuffix) {
		String serviceImplDir = "";
		String srcJavaDir = this.getSrcJavaDir(project, component, projectNameSuffix);
		serviceImplDir = srcJavaDir + "service/impl";
		return serviceImplDir;
	}

	/**
	 * 取得Controller的目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getSrcJavaControllerDir(Project project, Component component, String projectNameSuffix) {
		String srcJavaDir = this.getSrcJavaDir(project, component, projectNameSuffix);
		String controllerDir = srcJavaDir + "controller";
		return controllerDir;
	}

	/**
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @param projectNameSuffix
	 *            ==> manager,api,entity
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年5月23日
	 */
	private String getSrcJavaDir(Project project, Component component, String projectNameSuffix) {
		String srcJava = "";
		String outdir = this.getProjectDir(project);
		String packageName = project.getPackageName();
		String[] packages = packageName.split("\\.");
		String packageDir = "/";
		for (String p : packages) {
			packageDir += p;
			packageDir += "/";
		}
		// 生成src/main/java下的三层目录
		String projectName = packages[1];
		String mainProjectDir = outdir + "/" + project.getName() + "/" + project.getName() + "-" + projectNameSuffix;
		srcJava = mainProjectDir += "/src/main/java" + packageDir + "component/" + component.getPackageName() + "/";
		return srcJava;
	}

	/**
	 * 
	 * @todo
	 * @param project
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年5月6日
	 */
	private String getSrcTestJavaDir(Project project, Component component, String projectNameSuffix) {
		String srcTestJavaDir = "";
		String outdir = this.getProjectDir(project);
		String packageDir = this.getProjectPackageDir(project);
		packageDir += "test/";
		// 生成src/main/java下的三层目录
		String projectName = this.getProjectName(project);
		String mainProjectDir = outdir + "/" + project.getName() + "/" + project.getName() + "-" + projectNameSuffix;
		srcTestJavaDir = mainProjectDir += "/src/test/java" + packageDir + "component/" + component.getPackageName() + "/";
		return srcTestJavaDir;
	}

	/**
	 * 取得repository 路径
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年5月6日
	 */
	private String getSrcTestJavaRepositoryDir(Project project, Component component, String projectNameSuffix) {
		String res = "";
		boolean isJpa = StringUtils.equalsIgnoreCase("jpa", project.getRepositoryType());
		String testDir = this.getSrcTestJavaDir(project, component, projectNameSuffix);
		if (isJpa) {
			res = testDir + "repository/jpa";
		} else {
			res = testDir + "repository/mongo";
		}
		return res;
	}

	private String getSrcTestJavaServiceDir(Project project, Component component, String projectNameSuffix) {
		String res = "";
		String testDir = this.getSrcTestJavaDir(project, component, projectNameSuffix);
		res = testDir + "service";
		return res;
	}

	/**
	 * 取得项目根目录
	 * 
	 * @todo
	 * @param project
	 * @param component
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getProjectDir(Project project) {
		String dir = "";
		dir = ProjectUtil.getProjectDir(project);
		return dir;
	}

	/**
	 * 取得项目根目录名称(根据反转包名获得)
	 * 
	 * @todo
	 * @param project
	 * @return
	 * @author:@haipenge haipenge@gmail.com 2015年4月10日
	 */
	private String getProjectName(Project project) {
		String packageName = project.getPackageName();
		String[] packages = packageName.split("\\.");
		return packages[1];
	}

}
