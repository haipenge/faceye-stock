package ${project.packageName}.component.${component.packageName}.repository.mongo.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ${project.packageName}.component.${component.packageName}.repository.mongo.customer.${entity.className}CustomerRepository;

/**
 * 模块:${component.name}->${project.packageName}.compoent.${component.packageName}.repository.mongo.customer<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}->${entity.packageName}.entity.${entity.className}  的数据操作对像<br>
 * @author ${project.author} <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:${.now}<br>
*  本类只会在第一次生成源码时创建，后绪生成将不会被覆盖。
*  用户自定义的一些方法，可以安全的在这里编写
 */
public class ${entity.className}CustomerRepositoryImpl implements  ${entity.className}CustomerRepository{
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;
	
}/**@generate-repository-source@**/
