<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/project/project.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/project/project.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="generate.project.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/generate/project/input"/>"> <fmt:message
				key="generate.project.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">

	<div class="header">
		<ol class="breadcrumb">
			<li><fmt:message key="generate.project"></fmt:message></li>
		</ol>
	</div>

	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/generate/project/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-2">
							<input type="text" name="LIKE|name" value="${searchParams.name}"
								placeholder="<fmt:message key="generate.project.name"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="EQ|packageName" value="${searchParams.packageName}"
								placeholder="<fmt:message key="generate.project.packageName"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="EQ|author" value="${searchParams.author}"
								placeholder="<fmt:message key="generate.project.author"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="EQ|isGenerate" value="${searchParams.isGenerate}"
								placeholder="<fmt:message key="generate.project.isGenerate"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|isRun" value="${searchParams.isRun}"
								placeholder="<fmt:message key="generate.project.isRun"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|isPackage" value="${searchParams.isPackage}"
								placeholder="<fmt:message key="generate.project.isPackage"></fmt:message>" class="form-control input-sm">
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
							<th><fmt:message key='generate.project.name'></fmt:message></th>
							<th><fmt:message key='generate.project.packageName'></fmt:message></th>
							<th><fmt:message key="generate.project.root"></fmt:message></th>
							<th><fmt:message key='generate.project.author'></fmt:message></th>
							<th><fmt:message key="generate.component" /></th>
							<th><fmt:message key='generate.project.isGenerate'></fmt:message></th>
							<th><fmt:message key='generate.project.isRun'></fmt:message></th>
							<th><fmt:message key="generate.project.port" /></th>
							<th><fmt:message key='generate.project.isPackage'></fmt:message></th>
							<th><fmt:message key='generate.project.repositoryType'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="project">
							<tr>
								<td><input type="checkbox" name="check-single" value="${project.id}"></td>
								<td><a href="<c:url value="/generate/project/detail/${project.id }"/>">${project.name}</a></td>
								<td>${project.packageName}</td>
								<td>${project.root }-${project.id}</td>
								<td>${project.author}</td>
								<td><a href="<c:url value="/generate/component/home?EQ|project.$id=${project.id}"/>"><fmt:message key="generate.component" /></a>
								<td><f:boolean value="${project.isGenerate}"/></td>
								<td><f:boolean value="${project.isRun}" /></td>
								<td>${project.port}</td>
								
								<td><f:boolean value="${project.isPackage}" /></td>
								<td>${project.repositoryType }</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/generate/project/edit/${project.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/generate/project/remove/${project.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/generate/project/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
