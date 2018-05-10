<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/valuation/valuation.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/valuation/valuation.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.valuation.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/valuation/input"/>"> <fmt:message
				key="stock.valuation.add"></fmt:message>
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
			<form action="<c:url value="/stock/valuation/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|stockId" value="${searchParams.stockId}"
		placeholder="<fmt:message key="stock.valuation.stockId"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|totalValue" value="${searchParams.totalValue}"
		placeholder="<fmt:message key="stock.valuation.totalValue"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|roce" value="${searchParams.roce}"
		placeholder="<fmt:message key="stock.valuation.roce"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|re" value="${searchParams.re}"
		placeholder="<fmt:message key="stock.valuation.re"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|discountRate" value="${searchParams.discountRate}"
		placeholder="<fmt:message key="stock.valuation.discountRate"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|period" value="${searchParams.period}"
		placeholder="<fmt:message key="stock.valuation.period"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|roceValue" value="${searchParams.roceValue}"
		placeholder="<fmt:message key="stock.valuation.roceValue"></fmt:message>"
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
							<th><fmt:message key='stock.valuation.stockId'></fmt:message></th>   
 <th><fmt:message key='stock.valuation.totalValue'></fmt:message></th>   
 <th><fmt:message key='stock.valuation.roce'></fmt:message></th>   
 <th><fmt:message key='stock.valuation.discountRate'></fmt:message></th>   
 <th><fmt:message key='stock.valuation.period'></fmt:message></th>   
 
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="valuation">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${valuation.id}"></td>
								<td>${valuation.stockId}</td>   
 <td>${valuation.totalValue}</td>   
 <td>${valuation.roce}</td>   
 <td>${valuation.discountRate}</td>   
 <td>${valuation.period}</td>   
 
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/valuation/edit/${valuation.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/valuation/remove/${valuation.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/valuation/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>