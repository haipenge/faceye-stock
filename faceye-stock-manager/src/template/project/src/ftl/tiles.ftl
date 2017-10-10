<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tiles-definitions PUBLIC "-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN" "http://tiles.apache.org/dtds/tiles-config_3_0.dtd">
<tiles-definitions>
    <!-- 模块:${component.name} - ${component.packageName} : 业务对像:${entity.name} - ${entity.className}, 管理后台 tiles模板配置 -->
    <!-- 作者:${project.author} -->
    <!-- 创建日期:${.now} -->
    <!-- ${entity.name} : ${entity.className} 管理 首页(列表页) -->
	<definition name="${component.packageName}.${entity.className?uncap_first}.manager" extends="default.manager">
	    <put-attribute name="default-center" value="${component.packageName}.${entity.className?uncap_first}.center"></put-attribute>
	</definition>
	<definition name="${component.packageName}.${entity.className?uncap_first}.center" extends="default.center">
	    <put-attribute name="default-center-right" value="/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}_center.jsp"></put-attribute>
	</definition>
	
	<!-- ${entity.name} 新增,编辑页面 -->
	<definition name="${component.packageName}.${entity.className?uncap_first}.update" extends="default.manager">
	    <put-attribute name="default-center" value="${component.packageName}.${entity.className?uncap_first}.update.center"></put-attribute>
	</definition>
	<definition name="${component.packageName}.${entity.className?uncap_first}.update.center" extends="default.center">
	    <put-attribute name="default-center-right" value="/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}_update.jsp"></put-attribute>
	</definition>
	
	<!-- ${entity.name} 明细页 -->
	<definition name="${component.packageName}.${entity.className?uncap_first}.detail" extends="default.manager">
	    <put-attribute name="default-center" value="${component.packageName}.${entity.className?uncap_first}.detail.center"></put-attribute>
	</definition>
	<definition name="${component.packageName}.${entity.className?uncap_first}.detail.center" extends="default.center">
	    <put-attribute name="default-center-right" value="/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}_detail.jsp"></put-attribute>
	</definition>
	
</tiles-definitions>
