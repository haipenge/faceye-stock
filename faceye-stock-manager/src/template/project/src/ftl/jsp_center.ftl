<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="${component.packageName}.${entity.className?uncap_first}.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/${component.packageName}/${entity.className?uncap_first}/input"/>">
				<fmt:message key="${component.packageName}.${entity.className?uncap_first}.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
			<div class="content">
				<form action="<c:url value="/${component.packageName}/${entity.className?uncap_first}/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
                            <#list properties as property>
							<div class="col-md-2">
							   <#if property.referenceEntity??>
							     <select name="EQ|${property.referenceEntity.className?uncap_first}.${r'$'}id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="${property.referenceEntity.component.packageName}.${property.referenceEntity.className?uncap_first}"/></option>
							       <c:forEach items="${r'${'}${property.referenceEntity.className?uncap_first}s${r'}'}" var ="${property.referenceEntity.className?uncap_first}">
							         <option value="${r'${'}${property.referenceEntity.className?uncap_first}.id${r'}'}"<c:if test="${r'${'}${property.referenceEntity.className?uncap_first}.id eq entity.${property.referenceEntity.className?uncap_first}.id${r'}'}"> selected</c:if>>${r'${'}${property.referenceEntity.className?uncap_first}.name${r'}'}</option>
							       </c:forEach>
							     </select>
							   <#else>
								<input type="text" name="EQ|${property.propertyName}" value="${r'${searchParams'}.${property.propertyName}${r'}'}"
									placeholder="<fmt:message key="${component.packageName}.${entity.className?uncap_first}.${property.propertyName}"></fmt:message>"
									class="form-control input-sm">
							   </#if>
							</div>
							</#list>
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
				
				<button class="btn btn-primary btn-sm multi-remove">
					<fmt:message key="global.remove"></fmt:message>
				</button>
				<div classs="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th><input type="checkbox" name="check-all"></th>
								<#list properties as property>
								  <th><fmt:message key='${component.packageName}.${entity.className?uncap_first}.${property.propertyName}'></fmt:message></th>
								</#list>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${r'${page.content}'}" var="${entity.className?uncap_first}">
								<tr id="${r'${'}${entity.className?uncap_first}.id${r'}'}">
									<td><input type="checkbox" name="check-single" value="${r'${'}${entity.className?uncap_first}.id${r'}'}"></td>
									<#list properties as property>
									 <#if property.referenceEntity??>
									  <td>${r'${'}${entity.className?uncap_first}.${property.referenceEntity.className?uncap_first}.name${r'}'}</td>
									 <#else>
									  <td>${r'${'}${entity.className?uncap_first}.${property.propertyName}${r'}'}</td>
									 </#if>
									</#list>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/${component.packageName}/${entity.className?uncap_first}/detail/${r'${'}${entity.className?uncap_first}.id${r'}'}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/${component.packageName}/${entity.className?uncap_first}/edit/${r'${'}${entity.className?uncap_first}.id${r'}'}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/${component.packageName}/${entity.className?uncap_first}/remove/${r'${'}${entity.className?uncap_first}.id${r'}'}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${r'${page}'}" url="/${component.packageName}/${entity.className?uncap_first}/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>
