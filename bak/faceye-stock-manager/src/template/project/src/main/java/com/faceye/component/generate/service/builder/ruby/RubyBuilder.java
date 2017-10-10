package com.faceye.component.generate.service.builder.ruby;

import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.service.builder.BaseBuilder;
import com.faceye.component.generate.service.builder.Builder;

/**
 * 创建Ruby项目
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年5月8日
 */
public class RubyBuilder extends BaseBuilder implements Builder {

	@Override
	public boolean buildProjectStruct(Project project) {
		return false;
	}

	@Override
	public boolean generateCode(Project project) {
		return false;
	}

}
