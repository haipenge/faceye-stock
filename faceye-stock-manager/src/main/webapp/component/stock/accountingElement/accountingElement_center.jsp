<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/accountingElement/accountingElement.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/accountingElement/accountingElement.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.accountingElement.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/accountingElement/input?reportCategoryid=${reportCategory.id}"/>"> <fmt:message key="stock.accountingElement.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/stock/accountingElement/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}" placeholder="<fmt:message key="stock.accountingElement.name"></fmt:message>" class="form-control input-sm">
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
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='stock.accountingElement.name'></fmt:message></th>
							<th><fmt:message key="stock.reportCategory"/></th>
							<th><fmt:message key="stock.accountingSubject"/></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="accountingElement">
							<tr>
								<td><input type="checkbox" name="check-single" value="${accountingElement.id}"></td>
								<td>${accountingElement.name}</td>
								<td>${accountingElement.reportCategory.name }</td>
								<td><a href="<c:url value="/stock/accountingSubject/home?EQ|accountingElement.$id=${accountingElement.id}"/>"><fmt:message key="stock.accountingSubject"/></a>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/accountingElement/edit/${accountingElement.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/accountingElement/remove/${accountingElement.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/accountingElement/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>