<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/dailyStat/dailyStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/dailyStat/dailyStat.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="stock.dailyStat.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.stockId"></fmt:message></td>
	<td>${dailyStat.stockId}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.topPriceOf30Day"></fmt:message></td>
	<td>${dailyStat.topPriceOf30Day}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.lowerPriceOf30Day"></fmt:message></td>
	<td>${dailyStat.lowerPriceOf30Day}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.pe"></fmt:message></td>
	<td>${dailyStat.pe}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.topPriceDate"></fmt:message></td>
	<td>${dailyStat.topPriceDate}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.lowerPriceDate"></fmt:message></td>
	<td>${dailyStat.lowerPriceDate}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.categoryId"></fmt:message></td>
	<td>${dailyStat.categoryId}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.dynamicPe"></fmt:message></td>
	<td>${dailyStat.dynamicPe}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.pb"></fmt:message></td>
	<td>${dailyStat.pb}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyStat.todayPrice"></fmt:message></td>
	<td>${dailyStat.todayPrice}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->










			</table>
		</div>
	</div>
</div>