<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/forecast/forecast.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/forecast/forecast.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.forecast.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/forecast/input"/>"> <fmt:message
				key="stock.forecast.add"></fmt:message>
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
			<form action="<c:url value="/stock/forecast/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|stockId" value="${searchParams.stockId}"
		placeholder="<fmt:message key="stock.forecast.stockId"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|eps" value="${searchParams.eps}"
		placeholder="<fmt:message key="stock.forecast.eps"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|profit" value="${searchParams.profit}"
		placeholder="<fmt:message key="stock.forecast.profit"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|income" value="${searchParams.income}"
		placeholder="<fmt:message key="stock.forecast.income"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|roe" value="${searchParams.roe}"
		placeholder="<fmt:message key="stock.forecast.roe"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|year" value="${searchParams.year}"
		placeholder="<fmt:message key="stock.forecast.year"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|reportDate" value="${searchParams.reportDate}"
		placeholder="<fmt:message key="stock.forecast.reportDate"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|mechanism" value="${searchParams.mechanism}"
		placeholder="<fmt:message key="stock.forecast.mechanism"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|researcher" value="${searchParams.researcher}"
		placeholder="<fmt:message key="stock.forecast.researcher"></fmt:message>"
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
 <th><fmt:message key='stock.forecast.eps'></fmt:message></th>   
 <th><fmt:message key='stock.forecast.profit'></fmt:message></th>   
 <th><fmt:message key='stock.forecast.income'></fmt:message></th>   
 <th><fmt:message key='stock.forecast.roe'></fmt:message></th>   
 <th><fmt:message key='stock.forecast.year'></fmt:message></th>   
 <th><fmt:message key='stock.forecast.reportDate'></fmt:message></th>   
 <th><fmt:message key='stock.forecast.mechanism'></fmt:message></th>   
 <th><fmt:message key='stock.forecast.researcher'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="forecast">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${forecast.id}"></td>
 <td>${forecast.eps}</td>   
 <td>${forecast.profit}</td>   
 <td>${forecast.income}</td>   
 <td><fmt:formatNumber value="${forecast.roe * 100}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>   
 <td>${forecast.year}</td>   
 <td><fmt:formatDate value="${forecast.reportDate}" pattern="yyyy-MM-dd"/></td>   
 <td>${forecast.mechanism}</td>   
 <td>${forecast.researcher}</td>   
 <!--@generate-entity-jsp-property-value@-->
								
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/forecast/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>