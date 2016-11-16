<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="stock.stock.detail"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
			<fieldset>
								<div class="form-group">
					<label class="col-md-4 control-label" for="stock">
					  <fmt:message key="stock.stock.code"></fmt:message>
					 </label>
					<div class="col-md-4">
						${stock.code}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="stock">
					  <fmt:message key="stock.stock.name"></fmt:message>
					 </label>
					<div class="col-md-4">
						${stock.name}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="stock">
					  <fmt:message key="stock.stock.business"></fmt:message>
					 </label>
					<div class="col-md-4">
						${stock.business}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="stock">
					  <fmt:message key="stock.stock.market"></fmt:message>
					 </label>
					<div class="col-md-4">
						${stock.market}
					</div>
				</div>
				<!--@generate-entity-jsp-property-detail@-->
				
				
				
				
			</fieldset>
	</div>
</div>