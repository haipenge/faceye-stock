<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/entity/entity.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/entity/builder.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/jquery-ui/jquery-ui.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/jquery-ui/jquery-ui.theme.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/jquery-ui/jquery-ui.structure.min.css"/>" />
<script type="text/javascript" src="<c:url value="/js/lib/jquery-ui/jquery-ui.min.js"/>"></script>
<style>

.ele-text{
  padding:5px;
}
.add-plus{
  margin-right:5px;
  float:right;
  font:16px;
  font-weight:bold;
  color:blue;
  cursor: pointer;
}
.ele-reduce{
  text-align:center;
  vertical-align:middle;
  font:16px;
  font-weight:bold;
  color:red;
  cursor: pointer;
}
</style>
<div class="page-head">
	<h2>
		<fmt:message key="generate.entity.form.builder"></fmt:message>

	</h2>
</div>
<div class="cl-mcont" style="padding:0px;margin:0px;">

	<div class="header">
		<ol class="breadcrumb">
			<li><a href="<c:url value="/generate/project/home"/>"><fmt:message key="generate.project"></fmt:message></a></li>
			<li><a href="<c:url value="/generate/component/home"/>"><fmt:message key="generate.component" /></a></li>
			<li><fmt:message key="generate.entity" /></li>
		</ol>
	</div>
	<div class="block-flat" style="padding:2px;">
		<div class="row">
			<div class="col-md-3">
				<h5>工具箱</h5>
				<div class="table-responsive" id="form-elements">
					<table class="table">
						<tr>
							<td style="width: 50%;"><img src="<c:url value="/js/component/generate/entity/builder/images/textbox.png"/>"><span class="ele-text">Text</span><span class="add-plus" data-ele="text">+</span></td>
							<td style="width: 50%;"><img src="<c:url value="/js/component/generate/entity/builder/images/textarea.png"/>"><span class="ele-text">TextArea</span><span class="add-plus" data-ele="textarea">+</span></td>
						</tr>
						<tr>
							<td style="width: 50%;"><img src="<c:url value="/js/component/generate/entity/builder/images/radio.png"/>"><span class="ele-text">Radio</span><span class="add-plus" data-ele="radio">+</span></td>
							<td style="width: 50%;"><img src="<c:url value="/js/component/generate/entity/builder/images/checkbox.png"/>"><span class="ele-text">checkbox</span><span class="add-plus" data-ele="checkbox">+</span></td>
						</tr>
						<tr>
						<td style="width: 50%;" colspan="2"><img src="<c:url value="/js/component/generate/entity/builder/images/dropdown.png"/>"><span class="ele-text">Select</span><span class="add-plus" data-ele="select">+</span></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-md-6">
				<h5>工作面版</h5>
				<div class="content" id="form-container">
				  <form class="form-horizontal">
				   
				  </form>
				</div>
			</div>
			<div class="col-md-3">
				<h5>属性设置</h5>
				<div class="content" id="element-properties></div>
			</div>
		</div>

	</div>
</div>
