<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script src="<c:url value="/js/lib/acharts/acharts.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/financialReport/financialReport.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialReport/financialReportCharts.js"/>"></script>
<div class="page-head">
	<h2>
		统计报表表</a>
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
			<form action="<c:url value="/stock/financialReport/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}" placeholder="<fmt:message key="stock.financialReport.name"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|date" value="${searchParams.date}" placeholder="<fmt:message key="stock.financialReport.date"></fmt:message>" class="form-control input-sm">
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
			<div class="detail-section">
				<div id="canvas"></div>
			</div>
		</div>
	</div>
</div>