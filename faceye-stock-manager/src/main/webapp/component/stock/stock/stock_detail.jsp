<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java"
	import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script src="<c:url value="/js/lib/acharts/acharts.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/component/stock/stock/stockChart.js"/>"></script>

<div class="page-head">
	<h2>
		股票趋势 ${stock.name} <span class="span-suffix">(${stock.code})</span>
		<input type="hidden" name="stockId" value="${stock.id}">
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
		<div class="content">行业:${stock.business}&nbsp;&nbsp;概念:<c:forEach var="category" items="${stock.categories}">${category.name}</c:forEach></div>
		<div class="content">
			<div id="msg"></div>
			<div class="detail-section">
				<div id="show-pe"></div>
			</div>
		</div>
	</div>
</div>
