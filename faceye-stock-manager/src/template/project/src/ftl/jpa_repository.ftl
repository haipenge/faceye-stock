package ${project.packageName}.component.${component.packageName}.repository.jpa;



import ${project.packageName}.component.${component.packageName}.entity.${entity.className};
import ${project.packageName}.feature.repository.jpa.BaseRepository;
/**
 * 模块:${component.name}->${project.packageName}.compoent.${component.packageName}.repository.jpa<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}->${entity.packageName}.entity.${entity.className} 实体DAO<br>
 * @author ${project.author} <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:${.now}<br>
 */
public interface ${entity.className}Repository extends BaseRepository<${entity.className},Long> {
	
	
}/**@generate-repository-source@**/
