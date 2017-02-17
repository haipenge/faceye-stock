<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.stock.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/stock/input"/>"> <fmt:message key="stock.stock.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/stock/stock/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
					<div class="col-md-2">
							<input type="text" name="like|name" value="${searchParams.name}" placeholder="名称" class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="like|code" value="${searchParams.code}" placeholder="股票代码" class="form-control input-sm">
						</div>
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
			<div classs="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><fmt:message key='stock.stock.code'></fmt:message></th>
							<th><fmt:message key='stock.stock.name'></fmt:message></th>
							<th><fmt:message key='stock.stock.business'></fmt:message></th>
							<th><fmt:message key='stock.stock.market'></fmt:message></th>
							<th><fmt:message key="stock.dailyData" /></th>
							<th>财务报表</th>
							<th>原始财务数据</th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="stock">
							<tr>
								<td><a href="<c:url value="/stock/stock/detail/${stock.id}"/>">${stock.code}</a></td>
								<td>${stock.name}</td>
								<td>${stock.business}</td>
								<td><c:if test="${stock.market eq 'sz'}">深圳(SZ)</c:if> <c:if test="${stock.market eq 'sh'}">上海(SH)</c:if></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/dailyData/home?EQ|stock.$id=${stock.id}"/>"><fmt:message key="stock.dailyData" /></a></td>
								<td><a href="<c:url value="/stock/financialData/report?stockId=${stock.id}"/>">财务报表</a></td>
								<td><a href="<c:url value="/stock/financialData/home?EQ|stockId=${stock.id }"/>">原始财务数据</a></td>
								<td><a href="<c:url value="/stock/stock/edit/${stock.id}"/>"> <fmt:message key="global.edit"></fmt:message></a></td>
								<td><a href="<c:url value="/stock/stock/remove/${stock.id}"/>"> <fmt:message key="global.remove"></fmt:message></a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/stock/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>