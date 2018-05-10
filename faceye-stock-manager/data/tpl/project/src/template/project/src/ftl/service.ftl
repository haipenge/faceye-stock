package ${project.packageName}.component.${component.packageName}.service;

import ${project.packageName}.component.${component.packageName}.entity.${entity.className};
import com.faceye.feature.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
 
/**
 * 模块:${component.name}->${project.packageName}.compoent.${component.packageName}.service<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}->${entity.packageName}.entity.${entity.className} 服务层接口<br>
 * @author ${project.author} <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:${.now}<br>
 */
public interface ${entity.className}Service extends BaseService<${entity.className},Long>{

	
}/**@generate-service-source@**/
