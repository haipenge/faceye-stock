<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/entity/entity.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/entity/entity.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="generate.entity.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/generate/entity/input?componentId=${component.id}"/>"> <fmt:message key="generate.entity.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">

	<div class="header">
		<ol class="breadcrumb">
			<li><a href="<c:url value="/generate/project/home"/>"><fmt:message key="generate.project"></fmt:message></a></li>
			<li><a href="<c:url value="/generate/component/home"/>"><fmt:message key="generate.component" /></a></li>
			<li><fmt:message key="generate.entity" /></li>
		</ol>
	</div>

	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/generate/entity/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}"
								placeholder="<fmt:message key="generate.entity.name"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|className" value="${searchParams.className}"
								placeholder="<fmt:message key="generate.entity.className"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|componentId" value="${searchParams.componentId}"
								placeholder="<fmt:message key="generate.entity.componentId"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|remark" value="${searchParams.remark}"
								placeholder="<fmt:message key="generate.entity.remark"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|createDate" value="${searchParams.createDate}"
								placeholder="<fmt:message key="generate.entity.createDate"></fmt:message>" class="form-control input-sm">
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
							<th><fmt:message key='generate.entity.name'></fmt:message></th>
							<th><fmt:message key='generate.entity.className'></fmt:message></th>
							<th><fmt:message key='generate.entity.componentId'></fmt:message></th>
							<th><fmt:message key='generate.entity.remark'></fmt:message></th>
							<th><fmt:message key='generate.entity.createDate'></fmt:message></th>
							<th><fmt:message key="generate.property" /></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="generate.entity.form.builder"/></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="entity">
							<tr>
								<td><input type="checkbox" name="check-single" value="${entity.id}"></td>
								<td>${entity.name}</td>
								<td>${entity.className}</td>
								<td>${entity.component.name}</td>
								<td>${entity.remark}</td>
								<td>${entity.createDate}</td>
								<td><a href="<c:url value="/generate/property/home?EQ|entity.$id=${entity.id}"/>"><fmt:message
											key="generate.property" /></a></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/generate/entity/formBuilder"/>"><fmt:message key="generate.entity.form.builder"/></a></td>
								<td><a href="<c:url value="/generate/entity/edit/${entity.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/generate/entity/remove/${entity.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/generate/entity/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
