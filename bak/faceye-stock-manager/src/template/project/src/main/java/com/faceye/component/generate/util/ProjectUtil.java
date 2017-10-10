package com.faceye.component.generate.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.faceye.component.generate.entity.Project;
import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.PathUtil;
import com.faceye.feature.util.bean.BeanContextUtil;
/**
 * 项目公共工具
 * @author haipenge
 */
public class ProjectUtil {
	public static String getProjectDir(Project project) {
		String dir = "";
		String generateDir=BeanContextUtil.getInstance().getBean(PropertyService.class).get("generate.output.dir");
//		if (project != null) {
//			StringBuilder sb = new StringBuilder();
//			sb.append(generateDir);
//			if (!StringUtils.endsWith(generateDir, "/")) {
//				sb.append("/");
//			}
//			sb.append(project.getRoot());
//			sb.append("-");
//			sb.append(project.getId());
//			dir = sb.toString();
//		}
		dir=generateDir;
		PathUtil.mkdir(dir);
		return dir;
	}
}
