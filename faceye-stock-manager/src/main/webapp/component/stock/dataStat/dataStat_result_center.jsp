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
		<form action="<c:url value="/stock/dataStat/result"/>" method="post" role="form" class="form-horizontal">
			<fieldset>
				<div class="form-group">
					<label for="years" class="col-sm-2 control-label">连续</label>
					<div class="col-md-2">
						<select name="years" class="form-control">
							<option value="5" selected>5年</option>
							<option value="4">4年</option>
							<option value="3">3年</option>
							<option value="2">2年</option>
							<option value="1">1年</option>
						</select>
					</div>
					<label class="col-sm-2 control-label">总资产净利率</label>
					<div class="col-sm-1">
						<input type="text" class="form-control" value="5" name="minTotalAssetsNetProfitMargin" />
					</div>
					<div class="col-sm-1">
						<input type="text" class="form-control" value="50" name="maxTotalAssetsNetProfitMargin" />
					</div>
					<div class="col-md-1">
						<button type="submit" class="btn btn-sm btn-primary">
							<fmt:message key="global.search"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="content">
		<div id="msg"></div>
		<c:forEach items="${statRecords}" var="statRecord">
			<h4>${statRecord.stock.name}
				<small>(${statRecord.stock.code })</small> <small><a href="<c:url value="/stock/reportData/report?stockId=${statRecord.stock.id}"/>">财务报表</a></small>
			</h4>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>财务摘要</th>
							<c:forEach items="${statRecord.dataStats }" var="record">
								<th><fmt:formatDate value="${record.dateCycle }" pattern="yyyy-MM-dd" /></th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>总资产净利率</td>
							<c:forEach items="${statRecord.dataStats}" var="dataStat">
								<td><fmt:formatNumber value="${dataStat.totalAssetsNetProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							</c:forEach>
							<!-- 
							<td><fmt:formatNumber value="${statRecord.avgTotalAssetsNetProfitMargin  *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							 -->
						</tr>
						<tr>
							<td>净资产收益率</td>
							<c:forEach items="${statRecord.dataStats}" var="dataStat">
								<td><fmt:formatNumber value="${dataStat.roe *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							</c:forEach>
							
						</tr>
						<tr>
							<td>净利率</td>
							<c:forEach items="${statRecord.dataStats}" var="dataStat">
								<td><fmt:formatNumber value="${dataStat.netProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							</c:forEach>
							
						</tr>
						<tr>
							<td>毛利率</td>
							<c:forEach items="${statRecord.dataStats}" var="dataStat">
								<td><fmt:formatNumber value="${dataStat.grossProfitMargin  *100}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							</c:forEach>
							
						</tr>
						<tr>
							<td>资产负债率</td>
							<c:forEach items="${statRecord.dataStats}" var="dataStat">
								<td><fmt:formatNumber value="${dataStat.debtToAssetsRatio *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							</c:forEach>
							
						</tr>
						<tr>
							<td>资产周转率</td>
							<c:forEach items="${statRecord.dataStats}" var="dataStat">
								<td><fmt:formatNumber value="${dataStat.totalAssetsTurnover *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
							</c:forEach>
							
						</tr>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</div>
</div>