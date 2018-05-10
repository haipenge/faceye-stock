<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="${component.packageName}.${entity.className?uncap_first}.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			  <#list properties as property>
				<tr>
					<td><fmt:message key="${component.packageName}.${entity.className?uncap_first}.${property.propertyName}"></fmt:message></td>
				  <#if property.referenceEntity??>
				    <td>${r'${'}${entity.className?uncap_first}.${property.referenceEntity.className?uncap_first}.name${r'}'}</td>
				  <#else>
					<td>${r'${'}${entity.className?uncap_first}.${property.propertyName}${r'}'}</td>
				  </#if>
				</tr>
			  </#list>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/${component.packageName}/${entity.className?uncap_first}/edit/${r'${'}${entity.className?uncap_first}.id${r'}'}"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/${component.packageName}/${entity.className?uncap_first}/remove/${r'${'}${entity.className?uncap_first}.id${r'}'}"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/${component.packageName}/${entity.className?uncap_first}/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
