<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${r'${empty '}${entity.className?uncap_first}.id${r'}'}">
					<fmt:message key="${component.packageName}.${entity.className?uncap_first}.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="${component.packageName}.${entity.className?uncap_first}.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/${component.packageName}/${entity.className?uncap_first}/save" method="post" role="form" cssClass="form-horizontal" commandName="${entity.className?uncap_first}">
			<form:hidden path="id"/>
			<fieldset>
			   <#list properties as property>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="${component.packageName}.${entity.className?uncap_first}.${property.propertyName}"/>
					</label>
					<div class="col-md-6">
					   <#if property.referenceEntity??>
					     <#if property.isMulti>
					      <c:forEach items ="${r'${'}${property.referenceEntity.className?uncap_first}s${r'}'}" var="${property.referenceEntity.className?uncap_first}">
					       <div class="checkbox">
                             <label>
                              <input type="checkbox" name="${property.referenceEntity.className?uncap_first}.id" value="${r'${'}${property.referenceEntity.className?uncap_first}.id${r'}'}">
                                ${r'${'}${property.referenceEntity.className?uncap_first}.name${r'}'}
                              </label>
                             </div>
                            </c:forEach>
					     <#else>
					      <form:select path="${property.referenceEntity.className?uncap_first}.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${r'${'}${property.referenceEntity.className?uncap_first}s${r'}'}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="${property.referenceEntity.className?uncap_first}.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="${property.referenceEntity.component.packageName}.${property.referenceEntity.className?uncap_first}"></fmt:message></option>
					        <c:forEach items ="${r'${'}${property.referenceEntity.className?uncap_first}s${r'}'}" var="${property.referenceEntity.className?uncap_first}">
					          <option value="${r'${'}${property.referenceEntity.className?uncap_first}.id${r'}'}">${r'${'}${property.referenceEntity.className?uncap_first}.name${r'}'}</option>
					        </c:forEach>
					      </select>
					      -->
					     </#if>
					   <#else>
					     <form:input path="${property.propertyName}" cssClass="form-control"/>
					   </#if>
					   <form:errors path="${property.propertyName}" cssClass="error"/>
					</div>
				</div>
			  </#list>
				<!--@generate-entity-jsp-property-update@-->
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
