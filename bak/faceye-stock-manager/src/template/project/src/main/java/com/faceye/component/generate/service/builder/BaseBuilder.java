package com.faceye.component.generate.service.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.entity.Validate;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.component.generate.service.EntityService;
import com.faceye.component.generate.service.ProjectService;
import com.faceye.component.generate.service.PropertyService;
import com.faceye.component.generate.service.model.Document;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class BaseBuilder {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected ProjectService projectService = null;
	@Autowired
	protected ComponentService componentService = null;
	@Autowired
	protected EntityService entityService = null;
	@Autowired
	@Qualifier("entityPropertyService")
	protected PropertyService propertyService = null;

	/**
	 * 加载Freemaker 模板
	 * @todo
	 * @param name
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年5月8日
	 */
//	protected Template loadTemplate(String templateDir, String name) {
//		Template template = null;
//		Configuration cfg = new Configuration();
//		try {
//			cfg.setDirectoryForTemplateLoading(new File(templateDir));
//			cfg.setObjectWrapper(new DefaultObjectWrapper());
//			template = cfg.getTemplate(name);
//		} catch (TemplateNotFoundException e) {
//			logger.error(">>FaceYe throws Exception: --->", e);
//		} catch (MalformedTemplateNameException e) {
//			logger.error(">>FaceYe throws Exception: --->", e);
//		} catch (ParseException e) {
//			logger.error(">>FaceYe throws Exception: --->", e);
//		} catch (IOException e) {
//			logger.error(">>FaceYe throws Exception: --->", e);
//		}
//		return template;
//	}

//	/**
//	 * 根据模板生成代码
//	 * @todo
//	 * @param template
//	 * @param document
//	 * @param generatePath
//	 * @author:@haipenge
//	 * haipenge@gmail.com
//	 * 2015年4月11日
//	 */
//	protected void processGenerate(Template template, Object document, String generatePath) {
//		FileWriter out = null;
//		try {
//			out = new FileWriter(new File(generatePath));
//			template.process(document, out);
//		} catch (IOException e) {
//			logger.error(">>FaceYe throws Exception: --->"+((Document)document).getEntity().getClassName(), e);
//		} catch (TemplateException e) {
//			logger.error(">>FaceYe throws Exception: --->", e);
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					logger.error(">>FaceYe throws Exception: --->", e);
//				}
//			}
//		}
//	}
//	
	
	protected Document buildDynamicDocument(Project project, Component component, Entity entity) {
		Document document = new Document();
		document.setProject(project);
		document.setComponent(component);
		document.setEntity(entity);
		Map propertiesSearchParams = new HashMap();
		propertiesSearchParams.put("EQ|entity.$id", entity.getId());
		List<Property> properties = this.propertyService.getPage(propertiesSearchParams, 1, 0).getContent();

		document.setProperties(properties);
		if (CollectionUtils.isNotEmpty(properties)) {
			List<DataType> dataTypes = new ArrayList<DataType>();
			List<Validate> validates = new ArrayList<Validate>();
			for (Property property : properties) {
				// Map dataTypeSearchParams = new HashMap();
				// dataTypeSearchParams.put("EQ|propertyId", property.getId());
				DataType dataType = property.getDataType();
				dataTypes.add(dataType);
				// Long validateIds[] = property.getValidateIds();
				// if (validateIds != null) {
				// for (Long validateId : validateIds) {
				// Validate validate = this.validateService.get(validateId);
				// validates.add(validate);
				// }
				// }
			}
			document.setDataTypes(dataTypes);
			document.setValidates(validates);
		}
		return document;
	}
}
