<div class="form-group">
	<label class="col-md-2 control-label" for="@property.name@"> <spring:message
			code="@component.name@.@entity.config.name@.@property.name@"/>
	</label>
	<div class="col-md-6">
		<form:input path="@property.name@" cssClass="form-control"/>
		<form:errors path="@property.name@" cssClass="error"/>
	</div>
</div>
<!--@generate-entity-jsp-property-update@-->
