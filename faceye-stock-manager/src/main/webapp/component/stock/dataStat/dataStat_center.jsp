<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/dataStat/dataStat.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialData/financialData.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/stock/dataStat/dataStat.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.dataStat.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/dataStat/input"/>"> <fmt:message key="stock.dataStat.add"></fmt:message>
		</a>
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
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/stock/dataStat/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-1">
							<input type="text" name="EQ|stockId" value="${searchParams.stockId}" placeholder="<fmt:message key="stock.dataStat.stockId"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|grossProfitMargin" value="${searchParams.grossProfitMargin}" placeholder="<fmt:message key="stock.dataStat.grossProfitMargin"></fmt:message>"
								class="form-control input-sm">
						</div>
						<!--@generate-entity-jsp-query-detail@-->
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
    <div class="content">
     ${stock.name }<span class="span-suffix">(${stock.code })</span><input type="hidden" name="stockId" value="${stock.id }">
    </div>
	<div class="row">
		<div class="col-sm-6 col-md-6">

			<div class="block-flat">
				<div class="header">
					<h3>总资产回报率</h3>
				</div>
				<div class="content">
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
									<td style="width: 30%;"><fmt:formateDate value="${dataStat.dateCycle }" pattern="yyyy-MM-dd" /></td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.returnOnAssets *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>

			<div class="col-sm-6 col-md-6">
				<div class="block-flat">
					<div class="header">
						<h3>营业收入</h3>
					</div>
					<div class="content blue-chart" data-step="3" data-intro="<strong>Unique Styled Plugins</strong> <br/> We put love in every detail to give a great user experience!.">
						<div id="operating_income" style="height: 180px;"></div>
					</div>
					<div class="content">
						<table>
							<thead>
								<tr>
									<th style="width: 30%;">时间</th>
									<th class="text-right">收入</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${operatingIncome}" var="income">
									<tr>
										<td style="width: 30%;"><fmt:formatDate value="${income.date }" pattern="yyyy-MM-dd" /></td>
										<td class="text-right"><fmt:formatNumber value="${income.data }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="content">
				<div id="msg"></div>
				<button class="btn btn-primary btn-sm multi-remove">
					<fmt:message key="global.remove"></fmt:message>
				</button>
				<div classs="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th><input type="checkbox" name="check-all"></th>
								<th><fmt:message key='stock.dataStat.stockId'></fmt:message></th>
								<th><fmt:message key='stock.dataStat.returnOnAssets'></fmt:message></th>
								<th><fmt:message key='stock.dataStat.dateCycle'></fmt:message></th>
								<th><fmt:message key='stock.dataStat.grossProfitMargin'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="dataStat">
								<tr>
									<td><input type="checkbox" name="check-single" value="${dataStat.id}"></td>
									<td>${dataStat.stockId}</td>
									<td>${dataStat.returnOnAssets}</td>
									<td>${dataStat.dateCycle}</td>
									<td>${dataStat.grossProfitMargin}</td>
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/stock/dataStat/edit/${dataStat.id}"/>"> <fmt:message key="global.edit"></fmt:message>
									</a></td>
									<td><a href="<c:url value="/stock/dataStat/remove/${dataStat.id}"/>"> <fmt:message key="global.remove"></fmt:message>
									</a></td>
								<tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/stock/dataStat/home" params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
</div>