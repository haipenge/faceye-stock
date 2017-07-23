<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/forecast/forecast.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/forecast/forecast.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.forecast.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/forecast/input"/>"> <fmt:message key="stock.forecast.add"></fmt:message>
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
			<form action="<c:url value="/stock/forecast/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|stockId" value="${searchParams.stockId}" placeholder="<fmt:message key="stock.forecast.stockId"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|eps" value="${searchParams.eps}" placeholder="<fmt:message key="stock.forecast.eps"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|profit" value="${searchParams.profit}" placeholder="<fmt:message key="stock.forecast.profit"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|income" value="${searchParams.income}" placeholder="<fmt:message key="stock.forecast.income"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|roe" value="${searchParams.roe}" placeholder="<fmt:message key="stock.forecast.roe"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|year" value="${searchParams.year}" placeholder="<fmt:message key="stock.forecast.year"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|reportDate" value="${searchParams.reportDate}" placeholder="<fmt:message key="stock.forecast.reportDate"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|mechanism" value="${searchParams.mechanism}" placeholder="<fmt:message key="stock.forecast.mechanism"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|researcher" value="${searchParams.researcher}" placeholder="<fmt:message key="stock.forecast.researcher"></fmt:message>"
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

	<c:forEach var="wrapForecast" items="${wrapForecasts.content}" varStatus="status">
		<c:if test="${status.index mod 2 == 0 }">
			<c:if test="${status.index != 0 }">
</div>
</c:if>
<div class="row" style="margin-top: 0px;">
	</c:if>
	<div class="col-sm-6 col-md-6">
		<div class="block-flat" style="padding-bottom: 3px;">
			<div class="header">
				<h5>${wrapForecast.forecastIndex.mechanism.name}
					<span class="span-suffix"><fmt:formatDate value="${wrapForecast.forecastIndex.reportDate}" pattern="yyyy-MM-dd" /></span>
				</h5>
			</div>
			<div class="content">
				<table class="no-border">
					<thead class="no-border">
						<tr>
							<th>年</th>
							<th class="text-right">EPS</th>
							<th class="text-right">净利润</th>
							<th class="text-right">营业收入</th>
							<th class="text-right" style="border-right: solid #eee 1px;">ROE</th>
							<th class="text-rigth">BPS</th>
							<th class="text-rigth">DPS</th>
							<th class="text-rigth">ROCE</th>
							<th class="text-rigth">RE</th>
							<th class="text-rigth">VRE</th>

						</tr>
					</thead>
					<tbody class="no-border-y">
						<c:set var="eIndex" value="0" />
						<c:forEach var="forecast" items="${wrapForecast.forecasts}" varStatus="inStatus">
							<tr>
								<td>${forecast.year }</td>
								<td class="text-right">${forecast.eps }</td>
								<td class="text-right">${forecast.profit}</td>
								<td class="text-right">${forecast.income }</td>
								<td class="text-right" style="border-right: solid #eee 1px;"><fmt:formatNumber value="${forecast.roe * 100}" type="number" pattern="#,##0.0#" maxFractionDigits="2"
										groupingUsed="true" />%</td>
								<td class="text-rigth"><c:if test="${forecast.eps !=0 and inStatus.index != 0 }">
										<fmt:formatNumber value="${wrapForecast.valuation.xbps[eIndex]}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
									</c:if></td>
								<td class="text-rigth"><c:if test="${forecast.eps !=0 and inStatus.index != 0 }">
										<fmt:formatNumber value="${wrapForecast.valuation.xdps[eIndex]}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
									</c:if></td>
								<td class="text-rigth"><c:if test="${forecast.eps !=0 and inStatus.index != 0 }">
										<fmt:formatNumber value="${wrapForecast.valuation.xroces[eIndex]}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
									</c:if></td>
								<td class="text-rigth"><c:if test="${forecast.eps !=0 and inStatus.index != 0 }">
										<fmt:formatNumber value="${wrapForecast.valuation.xres[eIndex]}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
									</c:if></td>
								<td class="text-rigth"><c:if test="${forecast.eps !=0 and inStatus.index != 0 }">
										<fmt:formatNumber value="${wrapForecast.valuation.xvres[eIndex]}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
									</c:if></td>
							</tr>
							<c:if test="${forecast.eps !=0 and inStatus.index != 0 }">
								<c:set var="eIndex" value="${eIndex + 1 }" />
							</c:if>
							<c:if test="${inStatus.last }">
								<tr>
									<td colspan="10" class="text-right"><span class="span-suffix">研究员:${forecast.researcher }</span></td>
								</tr>
								<tr>
									<td>CV</td>
									<td colspan="4" class="text-right"><fmt:formatNumber value="${wrapForecast.valuation.cv}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
									<td>V0E</td>
									<td colspan="4" class="text-right"><fmt:formatNumber value="${wrapForecast.valuation.totalValue}" type="number" pattern="#,##0.0#" maxFractionDigits="2"
											groupingUsed="true" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<c:if test="${status.last and status.count mod 2 != 0 }">
		<div class="col-sm-6 col-md-6"></div>
		<!-- 
		<c:if test="${(status.count + 1 ) mod 3 != 0}">
			<div class="col-sm-4 col-md-4"></div>
		</c:if>
		 -->
</div>
</c:if>
</c:forEach>
</div>
<f:page page="${wrapForecasts}" url="/stock/forecast/home" params="<%=request.getParameterMap()%>" />
