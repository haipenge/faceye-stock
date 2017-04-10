<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/dataStat/dataStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/dataStat/dataStat.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="stock.dataStat.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.stockId"></fmt:message></td>
	<td>${dataStat.stockId}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.returnOnAssets"></fmt:message></td>
	<td>${dataStat.returnOnAssets}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.dateCycle"></fmt:message></td>
	<td>${dataStat.dateCycle}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.grossProfitMargin"></fmt:message></td>
	<td>${dataStat.grossProfitMargin}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.netProfitMargin"></fmt:message></td>
	<td>${dataStat.netProfitMargin}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.totalAssetsTurnover"></fmt:message></td>
	<td>${dataStat.totalAssetsTurnover}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.totalAssetsNetProfitMargin"></fmt:message></td>
	<td>${dataStat.totalAssetsNetProfitMargin}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.debtToAssetsRatio"></fmt:message></td>
	<td>${dataStat.debtToAssetsRatio}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.roe"></fmt:message></td>
	<td>${dataStat.roe}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dataStat.coreProfitMargin"></fmt:message></td>
	<td>${dataStat.coreProfitMargin}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->










			</table>
		</div>
	</div>
</div>