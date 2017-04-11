<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/starDataStat/starDataStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/starDataStat/starDataStat.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.starDataStat.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/starDataStat/input"/>"> <fmt:message
				key="stock.starDataStat.add"></fmt:message>
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
			<form action="<c:url value="/stock/starDataStat/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|stockId" value="${searchParams.stockId}"
		placeholder="<fmt:message key="stock.starDataStat.stockId"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|avgDataStat" value="${searchParams.avgDataStat}"
		placeholder="<fmt:message key="stock.starDataStat.avgDataStat"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|avgDataStatDate" value="${searchParams.avgDataStatDate}"
		placeholder="<fmt:message key="stock.starDataStat.avgDataStatDate"></fmt:message>"
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
							<th><fmt:message key='stock.starDataStat.stockId'></fmt:message></th>   
 <th><fmt:message key='stock.starDataStat.avgDataStat'></fmt:message></th>   
 <th><fmt:message key='stock.starDataStat.avgDataStatDate'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="starDataStat">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${starDataStat.id}"></td>
								<td>${starDataStat.stockId}</td>   
 <td>${starDataStat.avgDataStat}</td>   
 <td>${starDataStat.avgDataStatDate}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/starDataStat/edit/${starDataStat.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/starDataStat/remove/${starDataStat.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/starDataStat/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>