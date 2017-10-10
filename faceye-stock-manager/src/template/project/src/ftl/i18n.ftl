#说明:国际化配置信息
#作者:${project.author}
#模块:${component.name} -> ${component.packageName} : ${entity.name} -> ${entity.className}
#业务对像:${entity.packageName}.${entity.className}
${component.packageName}.${entity.className?uncap_first}=${entity.name}
${component.packageName}.${entity.className?uncap_first}.manager=${entity.name}管理
${component.packageName}.${entity.className?uncap_first}.edit=编辑${entity.name}
${component.packageName}.${entity.className?uncap_first}.add=新增${entity.name}
${component.packageName}.${entity.className?uncap_first}.remove=删除${entity.name}
${component.packageName}.${entity.className?uncap_first}.detail=${entity.name}详情
<#list properties as property>
${component.packageName}.${entity.className?uncap_first}.${property.propertyName}=${property.name}
</#list>
${component.packageName}.${entity.className?uncap_first}.createDate=创建日期

#@generate-entity-property-i18n-source@
