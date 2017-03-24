<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/dailyStat/dailyStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/dailyStat/dailyStat.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="stock.dailyStat.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/dailyStat/input"/>"> <fmt:message
				key="stock.dailyStat.add"></fmt:message>
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
			<form action="<c:url value="/stock/dailyStat/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|stockId" value="${searchParams.stockId}"
		placeholder="<fmt:message key="stock.dailyStat.stockId"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|topPriceOf30Day" value="${searchParams.topPriceOf30Day}"
		placeholder="<fmt:message key="stock.dailyStat.topPriceOf30Day"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|lowerPriceOf30Day" value="${searchParams.lowerPriceOf30Day}"
		placeholder="<fmt:message key="stock.dailyStat.lowerPriceOf30Day"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|pe" value="${searchParams.pe}"
		placeholder="<fmt:message key="stock.dailyStat.pe"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|topPriceDate" value="${searchParams.topPriceDate}"
		placeholder="<fmt:message key="stock.dailyStat.topPriceDate"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|lowerPriceDate" value="${searchParams.lowerPriceDate}"
		placeholder="<fmt:message key="stock.dailyStat.lowerPriceDate"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|categoryId" value="${searchParams.categoryId}"
		placeholder="<fmt:message key="stock.dailyStat.categoryId"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|dynamicPe" value="${searchParams.dynamicPe}"
		placeholder="<fmt:message key="stock.dailyStat.dynamicPe"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|pb" value="${searchParams.pb}"
		placeholder="<fmt:message key="stock.dailyStat.pb"></fmt:message>"
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
		<div class="content">
		  <div id="msg"></div>
	       <button class="btn btn-primary btn-sm multi-remove"><fmt:message key="global.remove"></fmt:message></button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='stock.dailyStat.stockId'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.topPriceOf30Day'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.lowerPriceOf30Day'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.pe'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.topPriceDate'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.lowerPriceDate'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.categoryId'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.dynamicPe'></fmt:message></th>   
 <th><fmt:message key='stock.dailyStat.pb'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="dailyStat">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${dailyStat.id}"></td>
								<td>${dailyStat.stockId}</td>   
 <td>${dailyStat.topPriceOf30Day}</td>   
 <td>${dailyStat.lowerPriceOf30Day}</td>   
 <td>${dailyStat.pe}</td>   
 <td>${dailyStat.topPriceDate}</td>   
 <td>${dailyStat.lowerPriceDate}</td>   
 <td>${dailyStat.categoryId}</td>   
 <td>${dailyStat.dynamicPe}</td>   
 <td>${dailyStat.pb}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/dailyStat/edit/${dailyStat.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/dailyStat/remove/${dailyStat.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/dailyStat/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>