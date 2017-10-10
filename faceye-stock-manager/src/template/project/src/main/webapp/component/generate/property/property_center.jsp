<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/property/property.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/property/property.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="generate.property.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/generate/property/input?entity.id=${entity.id}"/>"> <fmt:message
				key="generate.property.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="header">
		<ol class="breadcrumb">
			<li><a href="<c:url value="/generate/project/home"/>"><fmt:message key="generate.project"></fmt:message></a></li>
			<li><a href="<c:url value="/generate/component/home"/>"><fmt:message key="generate.component" /></a></li>
			<li><a href="<c:url value="/generate/entity/home"/>"><fmt:message key="generate.entity" /></a></li>
			<li class="active"><fmt:message key="generate.property" /></li>
		</ol>
	</div>
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/generate/property/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}"
								placeholder="<fmt:message key="generate.property.name"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|propertyName" value="${searchParams.propertyName}"
								placeholder="<fmt:message key="generate.property.propertyName"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|entityId" value="${searchParams.entityId}"
								placeholder="<fmt:message key="generate.property.entityId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|dataTypeId" value="${searchParams.dataTypeId}"
								placeholder="<fmt:message key="generate.property.dataTypeId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|validateIds" value="${searchParams.validateIds}"
								placeholder="<fmt:message key="generate.property.validateIds"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|columnName" value="${searchParams.columnName}"
								placeholder="<fmt:message key="generate.property.columnName"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|referenceObjectId" value="${searchParams.referenceObjectId}"
								placeholder="<fmt:message key="generate.property.referenceObjectId"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|isMulti" value="${searchParams.isMulti}"
								placeholder="<fmt:message key="generate.property.isMulti"></fmt:message>" class="form-control input-sm">
						</div>
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<c:if test="${not empty entity }">
			<c:import url="/component/generate/entity/entity_detail.jsp"></c:import>
		</c:if>
		<div class="content">
			<div id="msg"></div>
			<button class="btn btn-primary btn-sm">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='generate.property.name'></fmt:message></th>
							<th><fmt:message key='generate.property.propertyName'></fmt:message></th>
							<th><fmt:message key='generate.property.entityId'></fmt:message></th>
							<th><fmt:message key='generate.property.dataTypeId'></fmt:message></th>
							<th><fmt:message key='generate.property.validateIds'></fmt:message></th>
							<th><fmt:message key='generate.property.columnName'></fmt:message></th>
							<th><fmt:message key='generate.property.referenceObjectId'></fmt:message></th>
							<th><fmt:message key='generate.property.isMulti'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="property">
							<tr>
								<td><input type="checkbox" name="check-single" value="${property.id}"></td>
								<td>${property.name}</td>
								<td>${property.propertyName}</td>
								<td>${property.entity.name}</td>
								<td>${property.dataType.name}</td>
								<td><c:forEach items="${property.validates}" var="validate" varStatus="status">${validate.name}<c:if
											test="${not status.last}">,</c:if>
									</c:forEach></td>
								<td>${property.columnName}</td>
								<td>${property.referenceEntity.name}</td>
								<td><f:boolean value="${property.isMulti}"/></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/generate/property/edit/${property.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/generate/property/remove/${property.id}"/>"> <fmt:message
											key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/generate/property/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
