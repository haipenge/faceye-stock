package ${project.packageName}.component.${component.packageName}.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;
<#list properties as property>
<#if property.referenceEntity??>
 <#if  "${property.referenceEntity.packageName}" != "${project.packageName}.component.${component.packageName}.entity">
import ${property.referenceEntity.packageName}.${property.referenceEntity.className};
 </#if>
<#else>
 <#if !property.dataType.typeClass?starts_with("java.lang")>
import ${property.dataType.typeClass};
 </#if>
</#if>
</#list>

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 模块:${component.name}->${component.packageName}->${entity.className}<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}->${entity.packageName}.${entity.className} Mongo 对像<br>
 * mongo数据集:${component.packageName}_${entity.className?uncap_first}<br>
 * @author ${project.author} <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:${.now}<br>
 *spring-data-mongo支持的注释类型<br>
 *@Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。<br>
 *@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。<br>
 *@DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存<br>
 *@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。<br>
 *@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。<br>
 *@GeoSpatialIndexed - 声明该字段为地理信息的索引。<br>
 *@Transient - 映射忽略的字段，该字段不会保存到<br>
 *@PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。<br>
 *@CompoundIndexes({
 *    @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
 *})
 *@Indexed(unique = true)
 */
 
@Document(collection="${project.root}_${project.id}_${component.packageName}_${entity.className?lower_case}")
public class ${entity.className} implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private  Long id=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
<#list properties as property>
  <#if property.referenceEntity??>
   <#if property.isMulti>
   /**
    * 说明:${property.name}s<br>
    * 属性名: ${property.referenceEntity.className?uncap_first}s<br>
    * 类型: List<${property.referenceEntity.packageName}.${property.referenceEntity.className}><br>
    * 数据库字段:${property.referenceEntity.className?uncap_first}s<br>
    * @author ${project.author}<br>
    */
    @DBRef
    private  List<${property.referenceEntity.className}> ${property.referenceEntity.className?uncap_first}s;
	public List<${property.referenceEntity.className}> get${property.referenceEntity.className}s() {
		return ${property.referenceEntity.className?uncap_first}s;
	}
	public void set${property.referenceEntity.className}s(List<${property.referenceEntity.className}> ${property.referenceEntity.className?uncap_first}s) {
		this.${property.referenceEntity.className?uncap_first}s = ${property.referenceEntity.className?uncap_first}s;
	}
   <#else>
     /**
    * 说明:${property.name}<br>
    * 属性名: ${property.referenceEntity.className?uncap_first}<br>
    * 类型: ${property.referenceEntity.packageName}.${property.referenceEntity.className}<br>
    * 数据库字段:${property.referenceEntity.className?uncap_first}<br>
    * @author ${project.author}<br>
    */
    @DBRef
    private  ${property.referenceEntity.className} ${property.referenceEntity.className?uncap_first};
	public ${property.referenceEntity.className} get${property.referenceEntity.className}() {
		return ${property.referenceEntity.className?uncap_first};
	}
	public void set${property.referenceEntity.className}(${property.referenceEntity.className} ${property.referenceEntity.className?uncap_first}) {
		this.${property.referenceEntity.className?uncap_first} = ${property.referenceEntity.className?uncap_first};
	}
   </#if>
  <#else>
   <#assign typeClass = "">
   <#assign shortTypeClass="">
   <#list dataTypes as dataType>
     <#if property?? && property.dataType?? && property.dataType.id?? && dataType?? && dataType.id??>
     <#if property.dataType.id == dataType.id>
       <#assign shortTypeClass = "${dataType.shortTypeClass}">
       <#assign typeClass = "${dataType.typeClass}">
       <#break>
     </#if>
     </#if>
   </#list>
     /**
    * 说明:${property.name}<br>
    * 属性名: ${property.propertyName}<br>
    * 类型: ${typeClass}<br>
    * 数据库字段:${property.columnName!property.property.name}<br>
    * @author ${project.author}<br>
    */
	private  ${shortTypeClass} ${property.propertyName};
	public ${shortTypeClass} get${property.propertyName?cap_first}() {
		return ${property.propertyName};
	}
	public void set${property.propertyName?cap_first}(${shortTypeClass} ${property.propertyName}) {
		this.${property.propertyName} = ${property.propertyName};
	}
  </#if>
</#list>
		
   /**
    * 说明:创建日期<br>
    * 属性名: createDate<br>
    * 类型: Date<br>
    * 数据库字段:createDate<br>
    * @author ${project.author}<br>
    */
    
	private  Date createDate=new Date();
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}/**@generate-entity-source@**/
	
