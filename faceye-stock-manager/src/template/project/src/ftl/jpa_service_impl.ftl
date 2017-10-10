package ${project.packageName}.component.${component.packageName}.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import ${project.packageName}.feature.repository.jpa.DynamicSpecifications;
import ${project.packageName}.feature.util.ServiceException;

import ${project.packageName}.component.${component.packageName}.entity.${entity.className};
import ${project.packageName}.component.${component.packageName}.repository.jpa.${entity.className}Repository;
import ${project.packageName}.component.${component.packageName}.service.${entity.className}Service;
import ${project.packageName}.feature.service.impl.BaseServiceImpl;
import com.mysema.query.types.Predicate;

/**
 * 模块:${component.name}->${project.packageName}.compoent.${component.packageName}.service.impl<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}->${entity.packageName}.entity.${entity.className} 服务实现类<br>
 * @author ${project.author} <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:${.now}<br>
 */
@Service
public class ${entity.className}ServiceImpl extends BaseServiceImpl<${entity.className}, Long, ${entity.className}Repository> implements ${entity.className}Service {

	@Autowired
	public ${entity.className}ServiceImpl(${entity.className}Repository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author ${project.author} <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:${.now}<br>
	*/
	@Override
	public Page<${entity.className}> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<${entity.className}> entityPath = resolver.createPath(entityClass);
		// PathBuilder<${entity.className}> builder = new PathBuilder<${entity.className}>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Specification spec = DynamicSpecifications.bySearchFilter(searchParams, entityClass);
		
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Driver> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(spec, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Driver>("id") {
			// })
			List<Driver> items = (List) this.dao.findAll(spec);
			res = new PageImpl<Driver>(items);

		}
		return res;
	}
	
	
}/**@generate-service-source@**/
