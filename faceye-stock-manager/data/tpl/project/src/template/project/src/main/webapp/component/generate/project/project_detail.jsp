<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/project/project.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/project/project.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="generate.project.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td><fmt:message key="generate.project.name"></fmt:message></td>
					<td>${project.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.project.createDate"></fmt:message></td>
					<td><fmt:formatDate value="${project.createDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				</tr>
				<tr>
					<td><fmt:message key="generate.project.packageName"></fmt:message></td>
					<td>${project.packageName}</td>
				</tr>
				
				<tr>
					<td><fmt:message key="generate.project.author"></fmt:message></td>
					<td>${project.author}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.project.isGenerate"></fmt:message></td>
					<td><f:boolean value="${project.isGenerate}"/></td>
				</tr>
				<tr>
				  <td><fmt:message key="generate.project.generateDate" /></td>
				  <td><fmt:formatDate value="${project.generateDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				</tr>
				<tr>
					<td><fmt:message key="generate.project.isRun"></fmt:message></td>
					<td><f:boolean value="${project.isRun}"/></td>
				</tr>
				<tr>
				   <td><fmt:message key="generate.project.runDate"/></td>
				   <td><fmt:formatDate value="${project.runDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				</tr>
				<tr>
					<td><fmt:message key="generate.project.isPackage"></fmt:message></td>
					<td><f:boolean value="${project.isPackage}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="generate.project.packageDate"></fmt:message></td>
					<td><fmt:formatDate value="${project.packageDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<div class="content">
	   <a href="<c:url value="/generate/project/generate?id=${project.id }"/>" class="btn btn-success"><fmt:message
											key="generate.project.generate" /></a>
	  <a href="<c:url value="/generate/project/gzip?id=${project.id}"/>" class="btn btn-primary"><fmt:message
											key="generate.project.gzip" /></a>
	 <a href="<c:url value="/generate/project/run?id=${project.id}"/>" class="btn btn-info"><fmt:message key="generate.project.run"/></a>
	  <a href="<c:url value="/generate/project/stop?id=${project.id}"/>" class="btn btn-danger"><fmt:message key="generate.project.run"/></a>
	</div>
</div>
