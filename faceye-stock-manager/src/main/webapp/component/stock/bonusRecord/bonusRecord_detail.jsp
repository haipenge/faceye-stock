<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/bonusRecord/bonusRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/bonusRecord/bonusRecord.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="stock.bonusRecord.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.publishDate"></fmt:message></td>
	<td>${bonusRecord.publishDate}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.giveStockCount"></fmt:message></td>
	<td>${bonusRecord.giveStockCount}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.increaseStockCount"></fmt:message></td>
	<td>${bonusRecord.increaseStockCount}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.dividend"></fmt:message></td>
	<td>${bonusRecord.dividend}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.status"></fmt:message></td>
	<td>${bonusRecord.status}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.exDividendDate"></fmt:message></td>
	<td>${bonusRecord.exDividendDate}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.equityRegistrationDate"></fmt:message></td>
	<td>${bonusRecord.equityRegistrationDate}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.bonusRecord.bonusShareTradingDate"></fmt:message></td>
	<td>${bonusRecord.bonusShareTradingDate}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->








			</table>
		</div>
	</div>
</div>