<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/financialData/financialData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialData/financialData.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.financialData.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/financialData/input"/>"> <fmt:message key="stock.financialData.add"></fmt:message>
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
			<form action="<c:url value="/stock/financialData/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-1">
							<input type="text" name="EQ|financialReportId" value="${searchParams.financialReportId}"
								placeholder="<fmt:message key="stock.financialData.financialReportId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|stockId" value="${searchParams.stockId}" placeholder="<fmt:message key="stock.financialData.stockId"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|accountElementId" value="${searchParams.accountElementId}" placeholder="<fmt:message key="stock.financialData.accountElementId"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|accountingSubjectId" value="${searchParams.accountingSubjectId}"
								placeholder="<fmt:message key="stock.financialData.accountingSubjectId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|data" value="${searchParams.data}" placeholder="<fmt:message key="stock.financialData.data"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|date" value="${searchParams.date}" placeholder="<fmt:message key="stock.financialData.date"></fmt:message>" class="form-control input-sm">
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
			<ul class="nav nav-pills" role="tablist">
				<c:forEach items="${reportCategories}" var="reportCategory">
					<li role="presentation"><a href="<c:url value="/stock/financialData/home?reportCategoryId=${reportCategory.id}&stockid=${stock.id}"/>">${reportCategory.name }</a></li>
				</c:forEach>
			</ul>
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
							<th><fmt:message key='stock.financialData.stockId'></fmt:message></th>
							<th><fmt:message key='stock.financialData.accountingSubjectId'></fmt:message></th>
							<th><fmt:message key='stock.financialData.data'></fmt:message>(万元)</th>
							<th><fmt:message key='stock.financialData.date'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="financialData">
							<tr>
								<td><input type="checkbox" name="check-single" value="${financialData.id}"></td>
								<td>${financialData.stockId}</td>
								<td>${financialData.accountingSubjectId}</td>
								<td style="text-align: right; font-weight: blod; color: blue;"><fmt:formatNumber value="${financialData.data /10000}" type="number" pattern="0.00"
										maxFractionDigits="2" groupingUsed="true" /></td>
								<td><fmt:formatDate value="${financialData.date}" pattern="yyyy-MM-dd" /></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/financialData/edit/${financialData.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/financialData/remove/${financialData.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/financialData/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>