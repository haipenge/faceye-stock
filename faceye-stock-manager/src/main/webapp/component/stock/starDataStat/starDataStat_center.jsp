<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/starDataStat/starDataStat.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/starDataStat/starDataStat.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.starDataStat.manager"></fmt:message>
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
			<form action="<c:url value="/stock/starDataStat/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|stockId" value="${searchParams.stockId}" placeholder="<fmt:message key="stock.starDataStat.stockId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|max5DayIncreaseRate" value="${searchParams.max5DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max5DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max10DayIncreaseRate" value="${searchParams.max10DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max10DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max20DayIncreaseRate" value="${searchParams.max20DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max20DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max30DayIncreaseRate" value="${searchParams.max30DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max30DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max60DayIncreaseRate" value="${searchParams.max60DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max60DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|starDataDate" value="${searchParams.starDataDate}" placeholder="<fmt:message key="stock.starDataStat.starDataDate"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|starDailyDataId" value="${searchParams.starDailyDataId}" placeholder="<fmt:message key="stock.starDataStat.starDailyDataId"></fmt:message>"
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
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='stock.starDataStat.stockId'></fmt:message></th>

							<th><fmt:message key='stock.starDataStat.max5DayIncreaseRate'></fmt:message></th>
							<th><fmt:message key='stock.starDataStat.max10DayIncreaseRate'></fmt:message></th>
							<th><fmt:message key='stock.starDataStat.max20DayIncreaseRate'></fmt:message></th>
							<th><fmt:message key='stock.starDataStat.max30DayIncreaseRate'></fmt:message></th>
							<th><fmt:message key='stock.starDataStat.max60DayIncreaseRate'></fmt:message></th>
							<th><fmt:message key='stock.starDataStat.starDataDate'></fmt:message></th>
							<th><fmt:message key='stock.starDataStat.starDailyDataId'></fmt:message></th>
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
								<td>${starDataStat.max5DayIncreaseRate}</td>
								<td>${starDataStat.max10DayIncreaseRate}</td>
								<td>${starDataStat.max20DayIncreaseRate}</td>
								<td>${starDataStat.max30DayIncreaseRate}</td>
								<td>${starDataStat.max60DayIncreaseRate}</td>
								<td>${starDataStat.starDataDate}</td>
								<td>${starDataStat.starDailyDataId}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/starDataStat/edit/${starDataStat.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/starDataStat/remove/${starDataStat.id}"/>"> <fmt:message key="global.remove"></fmt:message>
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