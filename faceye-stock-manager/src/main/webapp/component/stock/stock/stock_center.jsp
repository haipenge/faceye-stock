<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>


<div class="page-head">
	<h2>
		<fmt:message key="stock.stock.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/stock/input"/>"> <fmt:message key="stock.stock.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="content">
			<div classs="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<th>
							<td><fmt:message key='stock.stock.code'></fmt:message></td>
							<td><fmt:message key='stock.stock.name'></fmt:message></td>
							<td><fmt:message key='stock.stock.business'></fmt:message></td>
							<td><fmt:message key='stock.stock.market'></fmt:message></td>
							<!--@generate-entity-jsp-property-desc@-->
							<td><fmt:message key="stock.dailyData"/></td>
							<td><fmt:message key="global.edit"></fmt:message></td>
							<td><fmt:message key="global.remove"></fmt:message></td>
						</th>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="stock">
							<tr>
								<td><a href="<c:url value="/stock/stock/detail/${stock.id}"/>">${stock.code}</a></td>
								<td>${stock.name}</td>
								<td>${stock.business}</td>
								<td>${stock.market}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/stock/dailyData/home?EQ|stock.$id=${stock.id}"/>"><fmt:message key="stock.dailyData"/></a></td>
								<td><a href="<c:url value="/stock/stock/edit/${stock.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/stock/stock/remove/${stock.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/stock/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>