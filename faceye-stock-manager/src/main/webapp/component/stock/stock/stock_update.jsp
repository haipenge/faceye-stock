<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>



<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty stock.id}">
					<fmt:message key="stock.stock.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.stock.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form action="<c:url value="/stock/stock/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${stock.id}" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="code"> <fmt:message key="stock.stock.code"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="code" value="${stock.code}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="stock.stock.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="name" value="${stock.name}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="business"> <fmt:message key="stock.stock.business"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="business" value="${stock.business}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="market"> <fmt:message key="stock.stock.market"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="market" value="${stock.market}" class="form-control">
					</div>
				</div>
				<!--@generate-entity-jsp-property-update@-->
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>