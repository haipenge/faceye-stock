<%@ include file="/component/core/taglib/taglib.jsp"%>
<li><a href="#"><i class="fa fa-file"></i><span>Pages</span></a>
						<ul class="sub-menu">
							<li class="<%=JspUtil.isActive(request, "project")%>"><a  href="/generate/project/home"><fmt:message key="generate.project.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "/generate/component")%>"><a  href="/generate/component/home"><fmt:message key="generate.component.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "/generate/entity")%>"><a  href="/generate/entity/home"><fmt:message key="generate.entity.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "/generate/property")%>"><a  href="/generate/property/home"><fmt:message key="generate.property.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "/generate/dataType")%>"><a  href="/generate/dataType/home"><fmt:message key="generate.dataType.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "/generate/validate")%>"><a  href="/generate/validate/home"><fmt:message key="generate.validate.manager"></fmt:message></a></li>
<!--@generate-entity-manager-list-group-item@-->
						</ul></li>
