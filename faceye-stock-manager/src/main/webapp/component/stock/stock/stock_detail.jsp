<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="<c:url value="/js/lib/echarts/echarts.min.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stockChart.js"/>"></script>

<div class="page-head">
	<h2>
		股票趋势 ${stock.name} <span class="span-suffix">(${stock.code})</span> <input type="hidden" name="stockId" value="${stock.id}">
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
		<div class="row">
			<div class="content">
				<a href="<c:url value="/stock/reportData/report?stockId=${stock.id}"/>">财报</a> 
				<a href="<c:url value="/stock/starDataStat/home?EQ|stockId=${stock.id}"/>">星标</a>
				<a href="<c:url value="/stock/totalStock/home?EQ|stockId=${stock.id }"/>">股本 </a>
				<a href="<c:url value="/stock/bonusRecord/home?EQ|stockId=${stock.id}"/>">分红</a>
				<a href="<c:url value="/stock/forecast/home?EQ|stockId=${stock.id }"/>">机构估值</a>
			</div>
		</div>
		<div class="content">
			行业:${stock.business}&nbsp;&nbsp;概念:
			<c:forEach var="category" items="${stock.categories}">${category.name}</c:forEach>
		</div>
		<div class="content">
			<div id="msg"></div>

		</div>
		<div class="row dash-cols">
			<div class="col-md-4" style="padding: 0 5px 0 10;">
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-pe" style="height: 300px;"></div>
				</div>

				<div class="block" style="margin-bottom: 10px;">
					<div id="show-bps" style="height: 300px;"></div>
				</div>
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-roce" style="height: 300px;"></div>
				</div>
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-totalAssetsTurnover" style="height: 300px;"></div>
				</div>
			</div>
			<div class="col-md-4" style="padding: 0;">
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-pb" style="height: 300px;"></div>
				</div>
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-grossProfitMargin" style="height: 300px;"></div>
				</div>

				<div class="block" style="margin-bottom: 10px;">
					<div id="show-totalAssetsNetProfitMargin" style="height: 300px;"></div>
				</div>
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-roe" style="height: 300px;"></div>
				</div>
			</div>
			<div class="col-md-4" style="padding: 0 10 0 5px;">
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-eps" style="height: 300px;"></div>
				</div>
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-netProfitMargin" style="height: 300px;"></div>
				</div>
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-coreProfitMargin" style="height: 300px;"></div>
				</div>
				<div class="block" style="margin-bottom: 10px;">
					<div id="show-debtToAssetsRatio" style="height: 300px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
