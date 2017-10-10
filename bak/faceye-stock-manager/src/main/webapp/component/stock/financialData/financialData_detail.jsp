<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/financialData/financialData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialData/financialData.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="stock.financialData.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				
				<tr>
					<td class＝"bg-title-colwidth-p-20"><fmt:message key="stock.financialData.stockId"></fmt:message></td>
					<td>${financialData.stockId}</td>
				</tr>
				<tr>
					<td class＝"bg-title-colwidth-p-20"><fmt:message key="stock.financialData.accountingSubjectId"></fmt:message></td>
					<td>${financialData.accountingSubjectId}</td>
				</tr>
				<tr>
					<td class＝"bg-title-colwidth-p-20"><fmt:message key="stock.financialData.data"></fmt:message></td>
					<td>${financialData.data}</td>
				</tr>
				<tr>
					<td class＝"bg-title-colwidth-p-20"><fmt:message key="stock.financialData.date"></fmt:message></td>
					<td>${financialData.date}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->






			</table>
		</div>
	</div>
</div>