<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/starDataStat/starDataStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/starDataStat/starDataStat.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="stock.starDataStat.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.starDataStat.stockId"></fmt:message></td>
	<td>${starDataStat.stockId}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.starDataStat.avgDataStat"></fmt:message></td>
	<td>${starDataStat.avgDataStat}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.starDataStat.avgDataStatDate"></fmt:message></td>
	<td>${starDataStat.avgDataStatDate}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->



			</table>
		</div>
	</div>
</div>