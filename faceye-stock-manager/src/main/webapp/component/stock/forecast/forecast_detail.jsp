<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/forecast/forecast.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/forecast/forecast.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="stock.forecast.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.stockId"></fmt:message></td>
	<td>${forecast.stockId}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.eps"></fmt:message></td>
	<td>${forecast.eps}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.profit"></fmt:message></td>
	<td>${forecast.profit}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.income"></fmt:message></td>
	<td>${forecast.income}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.roe"></fmt:message></td>
	<td>${forecast.roe}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.year"></fmt:message></td>
	<td>${forecast.year}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.reportDate"></fmt:message></td>
	<td>${forecast.reportDate}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.mechanism"></fmt:message></td>
	<td>${forecast.mechanism}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.forecast.researcher"></fmt:message></td>
	<td>${forecast.researcher}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->









			</table>
		</div>
	</div>
</div>