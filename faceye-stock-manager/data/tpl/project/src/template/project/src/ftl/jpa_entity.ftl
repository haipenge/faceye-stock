package ${project.packageName}.component.${component.packageName}.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

/**
 * 模块:${component.name}->${component.packageName}->${entity.className}<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}->${entity.packageName}.${entity.className} Mongo 对像<br>
 * mongo数据集:${component.packageName}_${entity.className?uncap_first}<br>
 * @author ${project.author} <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:${.now}<br>
 */
 
@Entity
@Table(name="${project.root}_${project.id}_${component.packageName}_${entity.className?lower_case}")
public class ${entity.className} implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="${property.referenceEntity.className?uncap_first}_id")
    private  Set<${property.referenceEntity.className}> ${property.referenceEntity.className?uncap_first}s=new HashSet<${property.referenceEntity.className}>;
	public Set<${property.referenceEntity.className}> get${property.referenceEntity.className}s() {
		return ${property.referenceEntity.className?uncap_first}s;
	}
	public void set${property.referenceEntity.className}s(Set<${property.referenceEntity.className}> ${property.referenceEntity.className?uncap_first}s) {
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
    
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name="${property.referenceEntity.className?uncap_first}_id")
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
    @Column(name = "${property.columnName}")
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
    @Column(name="create_date")
	private  Date createDate=new Date();
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}/**@generate-entity-source@**/
	
