<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/totalStock/totalStock.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/totalStock/totalStock.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.totalStock.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/totalStock/input"/>"> <fmt:message
				key="stock.totalStock.add"></fmt:message>
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
			<form action="<c:url value="/stock/totalStock/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|stockId" value="${searchParams.stockId}"
		placeholder="<fmt:message key="stock.totalStock.stockId"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|stockNum" value="${searchParams.stockNum}"
		placeholder="<fmt:message key="stock.totalStock.stockNum"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|changeDate" value="${searchParams.changeDate}"
		placeholder="<fmt:message key="stock.totalStock.changeDate"></fmt:message>"
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
	       <button class="btn btn-primary btn-sm multi-remove"><fmt:message key="global.remove"></fmt:message></button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='stock.totalStock.stockId'></fmt:message></th>   
 <th><fmt:message key='stock.totalStock.stockNum'></fmt:message></th>   
 <th><fmt:message key='stock.totalStock.changeDate'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="totalStock">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${totalStock.id}"></td>
								<td>${totalStock.stockId}</td>   
 <td>${totalStock.stockNum}</td>   
 <td>${totalStock.changeDate}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/totalStock/edit/${totalStock.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/totalStock/remove/${totalStock.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/totalStock/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>