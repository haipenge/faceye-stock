<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/dataStat/dataStat.css"/>" />
<div class="page-head">
	<h2>
		${stock.name }(${stock.code }) &nbsp;&nbsp;
		<fmt:message key="stock.dataStat.manager"></fmt:message>
		&nbsp;&nbsp;<small><input type="hidden" name="stockId" value="${stock.id }">
			<button type="button" class="btn btn-sm btn-success" id="stock_stat">数据分析</button></small>
	</h2>
</div>
<div class="block-flat">
	<div class="content">
		<div id="msg"></div>
		<div classs="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><fmt:message key='stock.dataStat.grossProfitMargin'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.dateCycle'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.netProfitMargin'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.totalAssetsTurnover'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.totalAssetsNetProfitMargin'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.debtToAssetsRatio'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.roe'></fmt:message></th>
						<!--@generate-entity-jsp-property-desc@-->
					</tr>
				</thead>
			</table>
		</div>
		<c:forEach items="${statRecords}" var="statRecord">
			<h4>${statRecord.stock.name}
				<small>(${statRecord.stock.code })</small>
			</h4>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<tbody>
						<c:forEach items="${statRecord.dataStats}" var="dataStat">
							<tr>
								<td><fmt:formatNumber value="${dataStat.grossProfitMargin  *100}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								<td><fmt:formatDate value="${dataStat.dateCycle}" pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatNumber value="${dataStat.netProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								<td><fmt:formatNumber value="${dataStat.totalAssetsTurnover *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								<td><fmt:formatNumber value="${dataStat.totalAssetsNetProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								<td><fmt:formatNumber value="${dataStat.debtToAssetsRatio *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								<td><fmt:formatNumber value="${dataStat.roe *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								<!--@generate-entity-jsp-property-value@-->
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>
		<div classs="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><fmt:message key='stock.dataStat.grossProfitMargin'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.dateCycle'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.netProfitMargin'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.totalAssetsTurnover'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.totalAssetsNetProfitMargin'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.debtToAssetsRatio'></fmt:message></th>
						<th><fmt:message key='stock.dataStat.roe'></fmt:message></th>
						<!--@generate-entity-jsp-property-desc@-->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="dataStat">
						<tr>
							<td><fmt:formatNumber value="${dataStat.grossProfitMargin  *100}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							<td><fmt:formatDate value="${dataStat.dateCycle}" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatNumber value="${dataStat.netProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							<td><fmt:formatNumber value="${dataStat.totalAssetsTurnover *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							<td><fmt:formatNumber value="${dataStat.totalAssetsNetProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							<td><fmt:formatNumber value="${dataStat.debtToAssetsRatio *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							<td><fmt:formatNumber value="${dataStat.roe *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							<!--@generate-entity-jsp-property-value@-->
						<tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<f:page page="${page}" url="/stock/dataStat/home" params="<%=request.getParameterMap()%>" />
	</div>
</div>