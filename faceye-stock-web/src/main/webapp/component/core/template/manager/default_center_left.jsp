<%@ include file="/component/core/taglib/taglib.jsp"%>
<!-- Load System manager Menu form DB. -->
<div class="panel-group" id="accordion">
	<c:forEach items="${MENU_MENUS}" var="root">
		<c:if test="${empty root.parent }">
			<div class="panel panel-default">
				<div class="panel-heading">

					<a data-toggle="collapse" data-parent="#accordion" href="#menu-${root.id}">
						<h4 class="panel-title">${root.name}</h4>
					</a>

				</div>
				<c:set var="isHaveChild" value="false" />
				<c:forEach items="${MENU_MENUS}" var="m">
					<c:if test="${root.id eq m.parent.id }">
						<c:set var="isHaveChild" value="true"></c:set>
					</c:if>
				</c:forEach>
				<c:if test="${isHaveChild eq 'true' }">
					<div id="menu-${root.id}" class="panel-collapse collapse">
						<div class="panel-body list-group">
							<c:forEach items="${MENU_MENUS}" var="menu">
								<c:if test="${root.id eq menu.parent.id}">
									<c:choose>
										<c:when test="${menu.type==1}">
											<a href="<c:url value="${menu.resource.url }"/>" class="list-group-item">${menu.name}</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="Menu.load(${menu.resource.url});" class="list-group-item">${menu.name}</a>
										</c:otherwise>
									</c:choose>

								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
		</c:if>
	</c:forEach>
</div>


<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="global.manager"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
		<div class="list-group">

			<a class="list-group-item" href="<c:url value="/security/user/home"/>"><fmt:message key="security.user.manager"></fmt:message></a>
			<a class="list-group-item" href="<c:url value="/security/role/home"/>"><fmt:message key="security.role.manager"></fmt:message></a>
			<a class="list-group-item" href="<c:url value="/security/resource/home"/>"><fmt:message
					key="security.resource.manager"></fmt:message></a> <a class="list-group-item" href="/security/menu/home"><fmt:message
					key="security.menu.manager"></fmt:message></a>
			<!--@generate-entity-manager-list-group-item@-->
		</div>
	</div>
</div>

