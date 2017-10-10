<%@ include file="/component/core/taglib/taglib.jsp"%>
<div class="cl-sidebar" data-position="right">
	<!-- 
	data-step="1" data-intro="<strong>Fixed Sidebar</strong> <br/> It adjust to your needs."
	<div class="cl-toggle">
		<i class="fa fa-bars"></i>
	</div>
	 -->
	<div class="cl-navblock">
		<div class="menu-space">
			<div class="content">
				<div class="side-user">
				   
					<div class="avatar">
						<img src="<c:url value="/js/lib/clean-zone/images/avatar1_50.jpg"/>" alt="Avatar" />
					</div>
					 
					<div class="info">
						<a href="#"><c:if
						test="${not empty pageContext.request.userPrincipal}">
						<sec:authentication property="principal.username" />
					</c:if></a> <img src="<c:url value="/js/lib/clean-zone/images/state_online.png"/>" alt="Status" />
						<span>Online</span>
					</div>
				</div>
				<ul class="cl-vnavigation">
					<li><a href="#"><i class="fa fa-file"></i><span>Pages</span></a>
						<ul class="sub-menu">
							<li class="<%=JspUtil.isActive(request, "project")%>"><a  href="/generate/project/home"><fmt:message key="generate.project.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "component")%>"><a  href="/generate/component/home"><fmt:message key="generate.component.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "entity")%>"><a  href="/generate/entity/home"><fmt:message key="generate.entity.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "property")%>"><a  href="/generate/property/home"><fmt:message key="generate.property.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "dataType")%>"><a  href="/generate/dataType/home"><fmt:message key="generate.dataType.manager"></fmt:message></a></li>
<li  class="<%=JspUtil.isActive(request, "validate")%>"><a  href="/generate/validate/home"><fmt:message key="generate.validate.manager"></fmt:message></a></li>
<!--@generate-entity-manager-list-group-item@-->
						</ul></li>
					<li><a href="#"><i class="fa fa-smile-o"></i><span>Serach</span></a>
						<ul class="sub-menu">
						</ul></li>
					<li><a href="#"><i class="fa fa-list-alt"></i><span>Stocks</span></a>
						<ul class="sub-menu">
						</ul></li>
					<li><a href="#"><i class="fa fa-home"></i><span><fmt:message key="global.manager"></fmt:message></span></a>
						<ul class="sub-menu">
							<li class="<%=JspUtil.isActive(request, "user")%>"><a href="<c:url value="/security/user/home"/>"><fmt:message
										key="security.user.manager"></fmt:message></a></li>
							<li class="<%=JspUtil.isActive(request, "role")%>"><a href="<c:url value="/security/role/home"/>"><fmt:message
										key="security.role.manager"></fmt:message></a></li>
							<li class="<%=JspUtil.isActive(request, "resource")%>"><a href="<c:url value="/security/resource/home"/>"><fmt:message
										key="security.resource.manager"></fmt:message></a></li>
							<li class="<%=JspUtil.isActive(request, "menu")%>"><a href="/security/menu/home"><fmt:message
										key="security.menu.manager"></fmt:message></a></li>
						</ul></li>
					<!-- 
					<li><a href="#"><i class="fa fa-table"></i><span>Tables</span></a>
						<ul class="sub-menu">
							<li><a href="tables-general.html">General</a></li>
							<li><a href="tables-datatables.html"><span class="label label-primary pull-right">New</span>Data Tables</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-map-marker nav-icon"></i><span>Maps</span></a>
						<ul class="sub-menu">
							<li><a href="maps.html">Google Maps</a></li>
							<li><a href="vector-maps.html"><span class="label label-primary pull-right">New</span>Vector Maps</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-envelope nav-icon"></i><span>Email</span></a>
						<ul class="sub-menu">
							<li><a href="email-inbox.html">Inbox</a></li>
							<li><a href="email-read.html">Email Detail</a></li>
							<li><a href="email-compose.html"><span class="label label-primary pull-right">New</span>Email Compose</a></li>
						</ul></li>
					<li><a href="typography.html"><i class="fa fa-text-height"></i><span>Typography</span></a></li>
					<li><a href="charts.html"><i class="fa fa-bar-chart-o"></i><span>Charts</span></a></li>

					<li><a href="#"><i class="fa fa-file"></i><span>Pages</span></a>
						<ul class="sub-menu">
							<li><a href="pages-blank.html">Blank Page</a></li>
							<li><a href="pages-blank-header.html">Blank Page Header</a></li>
							<li><a href="pages-blank-aside.html">Blank Page Aside</a></li>
							<li><a href="pages-login.html">Login</a></li>
							<li><a href="pages-404.html">404 Page</a></li>
							<li><a href="pages-500.html">500 Page</a></li>
							<li><a href="pages-sign-up.html"><span class="label label-primary pull-right">New</span>Sign Up</a></li>
							<li><a href="pages-forgot-password.html"><span class="label label-primary pull-right">New</span>Forgot
									Password</a></li>
							<li><a href="pages-profile.html"><span class="label label-primary pull-right">New</span>Profile</a></li>
							<li><a href="pages-search.html"><span class="label label-primary pull-right">New</span>Search</a></li>
							<li><a href="pages-calendar.html"><span class="label label-primary pull-right">New</span>Calendar</a></li>
							<li><a href="pages-code-editor.html"><span class="label label-primary pull-right">New</span>Code Editor</a></li>
							<li><a href="pages-gallery.html">Gallery</a></li>
							<li><a href="pages-timeline.html">Timeline</a></li>
						</ul></li>
						 -->
				</ul>
			</div>
		</div>
		<div class="text-right collapse-button" style="padding: 7px 9px;">
			<input type="text" class="form-control search" placeholder="Search..." />
			<button id="sidebar-collapse" class="btn btn-default" style="">
				<i style="color: #fff;" class="fa fa-angle-left"></i>
			</button>
		</div>
	</div>
</div>
