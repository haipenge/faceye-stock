<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/security/resource/resource.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/security/resource/resource.js"/>"></script>

<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty resource.id}">
					<fmt:message key="security.resource.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="security.resource.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	
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
