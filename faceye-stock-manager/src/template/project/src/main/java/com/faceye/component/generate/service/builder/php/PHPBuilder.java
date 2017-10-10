package com.faceye.component.generate.service.builder.php;

import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.service.builder.BaseBuilder;
import com.faceye.component.generate.service.builder.Builder;

public class PHPBuilder extends BaseBuilder implements Builder {

	@Override
	public boolean buildProjectStruct(Project project) {
		return false;
	}

	@Override
	public boolean generateCode(Project project) {
		return false;
	}

}
