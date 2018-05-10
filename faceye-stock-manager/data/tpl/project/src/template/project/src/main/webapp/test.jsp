<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/component/core/taglib/taglib.jsp"%>
<html>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap/css/bootstrap-theme.min.css"/>" />
<script type="text/javascript" src="<c:url value="/js/lib/jquery/jquery-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/bootstrap/js/bootstrap.min.js"/>"></script>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 20px;
}
</style>
</head>
<body>
	<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/${component.packageName}/${entity.className?lower_case}/${entity.className?lower_case}.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/${component.packageName}/${entity.className?lower_case}/${entity.className?lower_case}.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="${component.packageName}.${entity.className?lower_case}.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/${component.packageName}/${entity.className?lower_case}/input"/>">
				<fmt:message key="${component.packageName}.${entity.className?lower_case}.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/${component.packageName}/${entity.className?lower_case}/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">

							<div class="col-md-1">
								<input type="text" name="EQ|name" value="${searchParams.name}"
									placeholder="<fmt:message key="${component.packageName}.${entity.className?lower_case}.name"></fmt:message>"
									class="form-control input-sm">
							</div>

							<div class="col-md-1">
								<input type="text" name="EQ|createDate" value="${searchParams.createDate}"
									placeholder="<fmt:message key="${component.packageName}.${entity.className?lower_case}.createDate"></fmt:message>"
									class="form-control input-sm">
							</div>

							<div class="col-md-1">
								<input type="text" name="EQ|projectId" value="${searchParams.projectId}"
									placeholder="<fmt:message key="${component.packageName}.${entity.className?lower_case}.projectId"></fmt:message>"
									class="form-control input-sm">
							</div>

							<div class="col-md-1">
								<input type="text" name="EQ|packageName" value="${searchParams.packageName}"
									placeholder="<fmt:message key="${component.packageName}.${entity.className?lower_case}.packageName"></fmt:message>"
									class="form-control input-sm">
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
								<th><fmt:message key='${component.packageName}.${entity.className?lower_case}.name'></fmt:message></th>
								<th><fmt:message key='${component.packageName}.${entity.className?lower_case}.createDate'></fmt:message></th>
								<th><fmt:message key='${component.packageName}.${entity.className?lower_case}.projectId'></fmt:message></th>
								<th><fmt:message key='${component.packageName}.${entity.className?lower_case}.packageName'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="component">
								<tr>
									<td><input type="checkbox" name="check-single" value="${component.id}"></td>
									<td>${component.name}</td>
									<td>${component.createDate}</td>
									<td>${component.projectId}</td>
									<td>${component.packageName}</td>
									<!--@generate-entity-jsp-property-value@-->
									<td><a
										href="<c:url value="/${component.packageName}/${entity.className?lower_case}/edit/${component.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/${component.packageName}/${entity.className?lower_case}/remove/${component.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								<tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/${component.packageName}/${entity.className?lower_case}/home"
					params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
</body>
</html>
