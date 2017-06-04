<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/valuation/valuation.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/valuation/valuation.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="stock.valuation.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.valuation.stockId"></fmt:message></td>
	<td>${valuation.stockId}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.valuation.totalValue"></fmt:message></td>
	<td>${valuation.totalValue}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.valuation.roce"></fmt:message></td>
	<td>${valuation.roce}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.valuation.discountRate"></fmt:message></td>
	<td>${valuation.discountRate}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.valuation.period"></fmt:message></td>
	<td>${valuation.period}</td>
</tr>

<!--@generate-entity-jsp-property-detail@-->







			</table>
		</div>
	</div>
</div>