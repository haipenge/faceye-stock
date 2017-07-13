<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/bonusRecord/bonusRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/bonusRecord/bonusRecord.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.bonusRecord.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/bonusRecord/input"/>"> <fmt:message
				key="stock.bonusRecord.add"></fmt:message>
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
			<form action="<c:url value="/stock/bonusRecord/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|publishDate" value="${searchParams.publishDate}"
		placeholder="<fmt:message key="stock.bonusRecord.publishDate"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|giveStockCount" value="${searchParams.giveStockCount}"
		placeholder="<fmt:message key="stock.bonusRecord.giveStockCount"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|increaseStockCount" value="${searchParams.increaseStockCount}"
		placeholder="<fmt:message key="stock.bonusRecord.increaseStockCount"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|dividend" value="${searchParams.dividend}"
		placeholder="<fmt:message key="stock.bonusRecord.dividend"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|status" value="${searchParams.status}"
		placeholder="<fmt:message key="stock.bonusRecord.status"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|exDividendDate" value="${searchParams.exDividendDate}"
		placeholder="<fmt:message key="stock.bonusRecord.exDividendDate"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|equityRegistrationDate" value="${searchParams.equityRegistrationDate}"
		placeholder="<fmt:message key="stock.bonusRecord.equityRegistrationDate"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|bonusShareTradingDate" value="${searchParams.bonusShareTradingDate}"
		placeholder="<fmt:message key="stock.bonusRecord.bonusShareTradingDate"></fmt:message>"
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
							<th><fmt:message key='stock.bonusRecord.publishDate'></fmt:message></th>   
 <th><fmt:message key='stock.bonusRecord.giveStockCount'></fmt:message></th>   
 <th><fmt:message key='stock.bonusRecord.increaseStockCount'></fmt:message></th>   
 <th><fmt:message key='stock.bonusRecord.dividend'></fmt:message></th>   
 <th><fmt:message key='stock.bonusRecord.status'></fmt:message></th>   
 <th><fmt:message key='stock.bonusRecord.exDividendDate'></fmt:message></th>   
 <th><fmt:message key='stock.bonusRecord.equityRegistrationDate'></fmt:message></th>   
 <th><fmt:message key='stock.bonusRecord.bonusShareTradingDate'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="bonusRecord">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${bonusRecord.id}"></td>
								<td>${bonusRecord.publishDate}</td>   
 <td>${bonusRecord.giveStockCount}</td>   
 <td>${bonusRecord.increaseStockCount}</td>   
 <td>${bonusRecord.dividend}</td>   
 <td>${bonusRecord.status}</td>   
 <td>${bonusRecord.exDividendDate}</td>   
 <td>${bonusRecord.equityRegistrationDate}</td>   
 <td>${bonusRecord.bonusShareTradingDate}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/bonusRecord/edit/${bonusRecord.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/bonusRecord/remove/${bonusRecord.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/bonusRecord/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>