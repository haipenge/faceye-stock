<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>
<style>
.increase-color {
	color: red;
}

.decrease-color {
	color: green;
}
</style>
<div class="page-head">
	<div class="row" style="margin-top: 0px; margin-bottom: 0px;">
		<div class="col-sm-8">
			<h2>
				<fmt:message key="stock.stock.manager"></fmt:message>
			</h2>
		</div>
		<div class="col-sm-4 text-right">
			<a class="btn btn-primary btn-sm" href="<c:url value="/stock/stock/input"/>"> <fmt:message key="stock.stock.add"></fmt:message></a>
			<button class="btn btn-sm btn-warning" type="button" id="init-stock-category">初始化股票分类</button>
		</div>
	</div>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div id="msg"></div>
		<div class="row" style="margin-top: 0px; margin-bottom: 0px;">
			<div class="col-sm-8">
				<div class="content">
					<form action="<c:url value="/stock/stock/home"/>" method="post" role="form" class="form-horizontal" style="margin-bottom: 0px;">
						<fieldset>
							<div class="form-group" style="margin-top: 0px; margin-bottom: 0px;">
								<div class="col-md-2">
									<select name="EQ|category.$id" class="form-control input-sm">
										<option>请选择股票分类</option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.id}" ${searchParams.categoryid  eq category.id? "selected":""}>${category.name }</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-2">
									<input type="text" name="like|name" value="${searchParams.name}" placeholder="多个名字间使用逗号分隔" class="form-control input-sm">
								</div>
								<div class="col-md-2">
									<input type="text" name="like|code" value="${searchParams.code}" placeholder="多个股票代码间使用逗号分隔" class="form-control input-sm">
								</div>
								<div class="col-md-3">
									<input type="text" name="minPe" value="${searchParams.minPe}" style="width:30px;" placeholder="PE起始值" class="form-control input-sm">-<input type="text" name="maxPe"
										value="${searchParams.maxPe}" style="width:30px;" placeholder="PE最大值" class="form-control input-sm">
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
			<div class="col-sm-4 text-right">
				<button type="button" class="btn btn-sm btn-warning" id="toggle-category">行业</button>
			</div>
		</div>

		<div class="content category-container" style="display: none;">
			<c:forEach items="${categories}" var="category" varStatus="status">
				<!-- 
				<c:choose>
					<c:when test="${status.first }">
						<p>
					</c:when>
					<c:when test="${status.index mod 8==0 && !status.last}">
						</p>
						<p>
					</c:when>
					<c:when test="${status.last && status.index mod 8 !=0 }">
						</p>
					</c:when>
				</c:choose>
				 -->
				<span class="label label-info" style="margin-top: 15px; margin-bottom: 5px; margin-left: 5px; margin-right: 5p;; width: 50px;"><a
					href="<c:url value="/stock/stock/home?EQ|category.$id=${category.id}"/>">${category.name }</a></span>
			</c:forEach>
		</div>
		<div class="content">
			<button type="button" class="btn btm-sm btn-primary" id="btn-multi-stock-compare">多股比对</button>
		</div>
		<div class="content">
			<div classs="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='stock.stock.name'></fmt:message></th>
							<th><fmt:message key='stock.stock.business'></fmt:message></th>
							<th><fmt:message key='stock.stock.market'></fmt:message></th>
							<th>当前股价</th>
							<th>PE</th>
							<th>PB</th>
							<th>今日涨跌</th>
							<th>30日波幅</th>
							<th><fmt:message key="stock.dailyData" /></th>
							<th>财务报表</th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="stock">
							<tr>
								<td><input type="checkbox" name="check-single" value="${stock.id}"></td>
								<td>${stock.name}&nbsp;&nbsp;<small>(<a href="<c:url value="/stock/stock/detail/${stock.id}"/>">${stock.code}</a>)
								</small></td>
								<td>${stock.category.name}</td>
								<td><c:if test="${stock.market eq 'sz'}">深圳(SZ)</c:if> <c:if test="${stock.market eq 'sh'}">上海(SH)</c:if></td>
								<td><fmt:formatNumber value="${stock.dailyStat.todayPrice }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
								<td class="text-right"><fmt:formatNumber value="${stock.dailyStat.pe }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" /><span
									class="span-suffix pull-right"><fmt:formatNumber value="${stock.dailyStat.dynamicPe }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />
										TTM</span></td>
								<td><fmt:formatNumber value="${stock.dailyStat.pb }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
								<td class="text-right ${stock.dailyStat.todayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
										value="${stock.dailyStat.todayIncreaseRate *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
								<td class="text-right ${stock.dailyStat.priceAmplitude lt 0 ? 'decrease-color':'increase-color'}"><fmt:formatNumber value="${stock.dailyStat.priceAmplitude *100 }"
										type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/dailyData/home?EQ|stockId=${stock.id}"/>"><fmt:message key="stock.dailyData" /></a></td>
								<td><a href="<c:url value="/stock/reportData/report?stockId=${stock.id}"/>">财务报表</a></td>
								<td><a href="<c:url value="/stock/stock/edit/${stock.id}"/>"> <fmt:message key="global.edit"></fmt:message></a></td>
								<td><a href="<c:url value="/stock/stock/remove/${stock.id}"/>"> <fmt:message key="global.remove"></fmt:message></a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/stock/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>