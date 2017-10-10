package com.faceye.component.generate.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.component.generate.service.model.Document;
import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.bean.BeanContextUtil;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * Freemarker 工具类
 * 
 * @author haipenge
 *
 */
public class FreeMarkerHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * freemarker文件根目录
	 */
	private static String TPL_DIR = "";
	// 资源文件配置路径
	private static final String TPL_DIR_CONFIG_KEY = "generate.template.dir";

	private FreeMarkerHandler() {
		TPL_DIR = BeanContextUtil.getInstance().getBean(PropertyService.class).get(TPL_DIR_CONFIG_KEY);
	}

	static class FreeMarkerHandlerHolder {
		static FreeMarkerHandler INSTANCE = new FreeMarkerHandler();
	}

	public static FreeMarkerHandler getInstance() {
		return FreeMarkerHandlerHolder.INSTANCE;
	}

	public Template getTemplate(String name) {
		Template template = null;
		Configuration cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File(TPL_DIR));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			template = cfg.getTemplate(name);
		} catch (TemplateNotFoundException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		} catch (MalformedTemplateNameException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		} catch (ParseException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		} catch (IOException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		}
		return template;
	}

	/**
	 * 根据模板生成代码
	 * 
	 * @todo
	 * @param tplName
	 * @param document
	 * @param filePath
	 * @author:@haipenge haipenge@gmail.com 2015年4月11日
	 */
	public void processGenerate(String tplName, Object document, String filePath, Boolean isReWrite) {
		FileWriter out = null;
		try {
			File file = new File(filePath);
			if (!file.exists() || isReWrite) {
				Template template = this.getTemplate(tplName);
				out = new FileWriter(file);
				template.process(document, out);
			}
		} catch (IOException e) {
			logger.error(">>FaceYe throws Exception: --->" + ((Document) document).getEntity().getClassName(), e);
		} catch (TemplateException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				    
				} catch (IOException e) {
					logger.error(">>FaceYe throws Exception: --->", e);
				}
			}
		}
	}
}
