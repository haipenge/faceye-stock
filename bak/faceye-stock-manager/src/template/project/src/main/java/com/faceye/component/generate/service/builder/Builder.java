package com.faceye.component.generate.service.builder;

import com.faceye.component.generate.entity.Project;

/**
 * 项目建造接品
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年5月8日
 */
public interface Builder {
	
	/**
	 * 构造项目骨架
	 * @todo
	 * @param project
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年5月8日
	 */
	public boolean buildProjectStruct(Project project);
	
	
	/**
	 * 生成项目源码
	 * @todo
	 * @param project
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年5月8日
	 */
	public boolean generateCode(Project project);

}
