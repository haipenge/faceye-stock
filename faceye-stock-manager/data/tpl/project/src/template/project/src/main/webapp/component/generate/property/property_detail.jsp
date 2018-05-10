<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/generate/property/property.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/generate/property/property.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="generate.property.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="generate.property.name"></fmt:message></td>
	<td>${property.name}</td>
</tr>
<tr>
	<td><fmt:message key="generate.property.propertyName"></fmt:message></td>
	<td>${property.propertyName}</td>
</tr>
<tr>
	<td><fmt:message key="generate.property.entityId"></fmt:message></td>
	<td>${property.entityId}</td>
</tr>
<tr>
	<td><fmt:message key="generate.property.dataTypeId"></fmt:message></td>
	<td>${property.dataTypeId}</td>
</tr>
<tr>
	<td><fmt:message key="generate.property.validateIds"></fmt:message></td>
	<td>${property.validateIds}</td>
</tr>
<tr>
	<td><fmt:message key="generate.property.columnName"></fmt:message></td>
	<td>${property.columnName}</td>
</tr>
<tr>
	<td><fmt:message key="generate.property.referenceObjectId"></fmt:message></td>
	<td>${property.referenceObjectId}</td>
</tr>
<tr>
	<td><fmt:message key="generate.property.isMulti"></fmt:message></td>
	<td>${property.isMulti}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->








			</table>
		</div>
	</div>
</div>
