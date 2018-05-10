<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/dataStat/dataStat.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialData/financialData.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/stock/dataStat/dataStat.js"/>"></script>
<div class="page-head">
	<h2>
		${stock.name }(${stock.code }) &nbsp;&nbsp;
		<fmt:message key="stock.dataStat.manager"></fmt:message>
		&nbsp;&nbsp;<small><input type="hidden" name="stockId" value="${stock.id }">
			<button type="button" class="btn btn-sm btn-success" id="stock_stat">数据分析</button></small>
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
	<!-- 
		<div class="row" style="margin-top: 2px;">
			<div class="col-sm-9">
				<div class="content">
					<form action="<c:url value="/stock/dataStat/home"/>" method="post" role="form" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<div class="col-md-4">
									<input type="text" name="EQ|stockId" value="${searchParams.stockId}" placeholder="<fmt:message key="stock.dataStat.stockId"></fmt:message>" class="form-control input-sm">
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
</div>
<div class="col-sm-3">
	<div class="content"></div>
</div>
</div>
-->
	<div class="row dash-cols" style="margin-top: 0px;">
		<div class="col-sm-6 col-md-6">
			<div class="block-flat">
				<div class="header">
					<h3>
						总资产回报率(净利率)&nbsp;&nbsp;<small><a href="#" class="returnOnAssets_0">年报</a>|<a href="#" class="returnOnAssets_1">一季报</a>|<a href="#" class="returnOnAssets_2">二季报</a>|<a
							href="#" class="returnOnAssets_3">三季报</a>|<a href="#" class="returnOnAssets_4">全部</a></small>
					</h3>
				</div>
				<div class="content blue-chart">
					<div id="returnOnAssets_chart" style="height: 180px;"></div>
				</div>
				<div class="content">
					<p class="text-right">
						<a href="#" id="toggle_returnOnAssets">数据列表</a>
				</div>
				<div class="content" style="display: none;" id="returnOnAssets_list">
					<table>
						<thead>
							<tr>
								<th style="width: 50%;">时间</th>
								<th>回报率</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="dataStat">
								<tr>
									<td style="width: 30%;"><fmt:formatDate value="${dataStat.dateCycle }" pattern="yyyy-MM-dd" /></td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.totalAssetsNetProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
											groupingUsed="true" />%</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="col-sm-6 col-md-6">
			<div class="block-flat">
				<div class="header">
					<h3>
						营业收入&nbsp;&nbsp;<small><a href="#" class="operating_income_0">年报</a>|<a href="#" class="operating_income_1">一季报</a>|<a href="#" class="operating_income_2">中报</a>|<a
							href="#" class="operating_income_3">三季报</a>|<a href="#" class="operating_income_4">全部</a> </small>
					</h3>
				</div>
				<div class="content blue-chart">
					<div id="operating_income" style="height: 180px;"></div>
				</div>
				<div class="content">
					<p class="text-right">
						<a href="#" class="operating_data_detail">数据列表</a>
					</p>
				</div>
				<div class="content" style="display: none;" id="operating_data_detail">
					<table>
						<thead>
							<tr>
								<th style="width: 30%;">时间</th>
								<th class="text-right">收入&nbsp;&nbsp;(元)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${operatingIncome.content}" var="income">
								<tr>
									<td style="width: 30%;"><fmt:formatDate value="${income.date }" pattern="yyyy-MM-dd" /></td>
									<td class="text-right"><fmt:formatNumber value="${income.data }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="block-flat">
				<div class="header">
					<h3>
						净利润&nbsp;&nbsp;<small><a href="#" class="net_profit_0">年报</a>|<a href="#" class="net_profit_1">一季报</a>|<a href="#" class="net_profit_2">中报</a>|<a href="#"
							class="net_profit_3">三报报</a>|<a href="#" class="net_profit_4">全部</a></small>
					</h3>
				</div>
				<div class="content blue-chart">
					<div id="net_profit" style="height: 180px;"></div>
				</div>
			</div>
		</div>
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
							<th><fmt:message key='stock.dataStat.coreProfitMargin'></fmt:message></th>   
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
								<td>${dataStat.coreProfitMargin}</td>   
 <!--@generate-entity-jsp-property-value@-->
								
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/dataStat/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>