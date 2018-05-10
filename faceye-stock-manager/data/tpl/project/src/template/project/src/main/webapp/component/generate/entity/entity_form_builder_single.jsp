<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/component/core/taglib/taglib.jsp" %>
<html>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- This is a form builder -->
 
<link href="<c:url value="/js/component/generate/entity/builder/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/js/component/generate/entity/builder/bootstrap/css/bootstrap-table.min.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/js/component/generate/entity/builder/bootstrap/css/nav-menu.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/js/component/generate/entity/builder/bootstrap/css/nav-menu2.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/js/component/generate/entity/builder/lionbars/css/lionbars.css"/>" rel="stylesheet" type="text/css" />
<link href="http://cdn.kendostatic.com/2014.1.318/styles/kendo.common.min.css" rel="stylesheet" />
<link href="http://cdn.kendostatic.com/2014.1.318/styles/kendo.bootstrap.min.css" rel="stylesheet" />
<link href="<c:url value="/js/component/generate/entity/builder/proto/css.css"/> rel="stylesheet" />
<body>
<div class="container-main">

	<div class="row">

		<div class="sidebar-left">
			<div id="sbid">
				<div class="row">
					<div class="navbar">
						<div class="navbar-inner">
							<ul class="nav nav-pills" style="margin: 0px;">

								<li><a id="dashboard" title="Go to Dashboard" href="http://protostrap.com/Dashboard/designer"><span class="glyphicon glyphicon-dashboard"></span></a></li>
								<li><a id="save" title="Save" data-url="/Dashboard/designer/save" href="edit#"><span class="glyphicon glyphicon-floppy-disk"></span></a></li>
								<li><a id="download" href="edit#" title="Download HTML"><span class="glyphicon glyphicon-cloud-download"></span></a></li>
								<li><a id="preview" title="Preview" data-show="false" href="edit#"><span class="glyphicon glyphicon-search"></span></a></li>
								<li><a id="removeMenu" title="Remove selected component" href="edit#"><i class="glyphicon glyphicon-trash"></i></a></li>
								<li><a id="contactus" title="Contact Us" href="http://protostrap.com/home/contact"><i class="glyphicon glyphicon-send"></i></a></li>
							</ul>
						</div>

					</div>

				</div>
				<div class="row">
					<div class="col-md-12">
						<input id="formid" type="text" class="form-control k-textbox" placeholder="Form name" data-role="text">
					</div>

				</div>

				<div class="row spcr5">
					<div class="col-md-12">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="edit#formSettingGeneral">Controls</a></li>
							<li class=""><a data-toggle="tab" href="edit#formSettingCondition">Properties</a></li>
						</ul>
						<div class="tab-content">
							<div id="formSettingGeneral" class="tab-pane active">
								<div class="tab-canvas">
									<div class="accordion" id="propertytools">
										<div class="accordion-group">
											<div class="accordion-heading">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#propertytools" href="edit#tools"> <i class="icon-wrench"></i>Tools
												</a>
											</div>
											<div id="tools" class="accordion-body collapse in">
												<div class="accordion-inner">
													<div class="row nav nav-pills nav-stacked">

														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_TextTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/textbox.png"/>">Text Box
																</div>
															</div>
															<div class="col-md-6">
																<div class="formControl" id="_SelectTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/dropdown.png"/>">Drop Down
																</div>
															</div>
														</div>

														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_CheckBoxTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/checkbox.png"/>">Check Box
																</div>
															</div>
															<div class="col-md-6">
																<div class="formControl" id="_RadioTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/radio.png"/>">Option
																</div>
															</div>
														</div>

														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_NumericTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/ico_numeric.png"/>">Numeric
																</div>
															</div>
															<div class="col-md-6">
																<div class="formControl" id="_DateTemplate">
																	<span class="glyphicon glyphicon-calendar"></span> Date
																</div>
															</div>
														</div>

														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_TimeTemplate">
																	<span class="glyphicon glyphicon-time"></span> Time
																</div>
															</div>
															<div class="col-md-6">
																<div class="formControl" id="_DateTimeTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/dt.png"/>">Date Time
																</div>
															</div>
														</div>

														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_MaskTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/ico_mask.png"/>">Mask
																</div>
															</div>
															<div class="col-md-6">
																<div class="formControl" id="_TextAreaTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/textarea.png"/>">Text Area
																</div>
															</div>
														</div>

														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_PillsTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/pills.png"/>">Pills
																</div>
															</div>
															<div class="col-md-6">
																<div class="formControl" id="_Button">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/but.png"/>">Button
																</div>
															</div>
														</div>

														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_HeaderTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/ico_header.png"/>">Header
																</div>
															</div>
															<div class="col-md-6">

																<div class="formControl" id="_lookup">
																	<span class="glyphicon glyphicon-search"></span> Lookup
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-6">
																<div class="formControl" id="_ParagraphTemplate">
																	<img src="<c:url value="/js/component/generate/entity/builder/images/pgp.png"/>">Paragraph
																</div>
															</div>
															<div class="col-md-6"></div>
														</div>


													</div>
												</div>
											</div>
										</div>
										<div class="accordion-group">
											<div class="accordion-heading">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#propertytools" href="edit#template"> <i class="icon-tasks"></i>Container
												</a>
											</div>
											<div id="template" class="accordion-body collapse in">
												<div class="accordion-inner">
													<div class="row">

														<div class="col-md-6">
															<div class="formContainer" id="menu1Column">
																<img src="<c:url value="/js/component/generate/entity/builder/images/1col.png"/>">1 Column
															</div>
														</div>
														<div class="col-md-6">
															<div class="formContainer" id="menu2Columns">
																<img src="<c:url value="/js/component/generate/entity/builder/images/2col.png"/>">2 Columns
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="formContainer" id="menu3Columns">
																<img src="<c:url value="/js/component/generate/entity/builder/images/3col.png"/>">3 Columns
															</div>
														</div>
														<div class="col-md-6">
															<div class="formContainer" id="menu4Columns">
																<img src="<c:url value="/js/component/generate/entity/builder/images/4col.png"/>">4 Columns
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="formContainer" id="twoandten">
																<img src="<c:url value="/js/component/generate/entity/builder/images/2-10col.png"/>">2-10 Cols
															</div>
														</div>
														<div class="col-md-6">
															<div class="formContainer" id="_panel">
																<span class="glyphicon glyphicon-tasks"></span> Panel
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="formContainer" id="_tab">
																<img src="<c:url value="/js/component/generate/entity/builder/images/tab.png"/>">Tab
															</div>
														</div>
														<div class="col-md-6">
															<div class="formContainer" id="_collapsible">
																<img src="<c:url value="/js/component/generate/entity/builder/images/accordion.png"/>">Collapse
															</div>
														</div>
													</div>

												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div id="formSettingCondition" class="tab-pane">
								<div id="property" class="tab-canvas">
									<div id="propertysection">
										<div id="fieldproperties">
											<script src="<c:url value="/js/component/generate/entity/builder/jquery/jquery-1.8.3.js"/>" type="text/javascript"></script>
											<script src="http://cdn.kendostatic.com/2013.2.716/js/kendo.all.min.js"></script>

											<div id="textproperties" class="row">
												<div class="prop-mg-lr col-md-12 prop-mg-lr">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textdefautvalue" class="form-control" data-gv-property="value" placeholder="Default Value" />
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textplaceholder" class="form-control" data-gv-property="placeholder" placeholder="Placeholder">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-12 prop-mg-lr">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-4">
															<div class="form-group">
																<div class="row">
																	<label class="control-label">Limit By</label> <select id="textLimitType" class="form-control">
																		<option value="String">String</option>
																		<option value="Word">Word</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="prop-mg-lr col-md-8">
															<div class="row">


																<div class="prop-mg-lr col-md-6">
																	<div class="form-group-prop">
																		<label class="control-label">Min</label>
																		<div class="controls">
																			<input type="text" id="textminvalue" class="form-control" data-gv-property="min" placeholder="min">
																		</div>
																	</div>
																</div>
																<div class="prop-mg-lr col-md-6">
																	<div class="form-group-prop">
																		<label class="control-label">Max</label>
																		<div class="controls">
																			<input type="text" id="textmaxvalue" class="form-control" data-gv-property="max">
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>

													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>

												</div>


												<div class="hidden">
													<input id="TextTemplate" type="text" class="form-control k-textbox" data-role="text">
												</div>

											</div>

											<div id="lookupproperties" class="row">
												<div class="prop-mg-lr col-md-12 prop-mg-lr">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textplaceholder" class="form-control" data-gv-property="placeholder" placeholder="Placeholder">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-12 prop-mg-lr">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>


													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>

												</div>


												<div class="hidden">
													<div id="LookupTemplate" class="input-group">
														<input type="text" class="form-control" data-role="lookup"> <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
													</div>
												</div>


											</div>

											<div id="textareaproperties" class="row">
												<div class="prop-mg-lr col-md-12">

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textdefautvalue" class="form-control" data-gv-property="value" placeholder="Default Value" />
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-4">
															<div class="row">
																<label class="control-label">Limit By</label> <select id="textareaLimitType" class="form-control">
																	<option value="String">String</option>
																	<option value="Word">Word</option>
																</select>
															</div>
														</div>
														<div class="prop-mg-lr col-md-8">
															<div class="row">


																<div class="prop-mg-lr col-md-6">
																	<div class="form-group">
																		<label class="control-label">Min</label>
																		<div class="controls">
																			<input type="text" id="textareaminvalue" class="form-control" data-gv-property="min">
																		</div>
																	</div>
																</div>
																<div class="prop-mg-lr col-md-6">
																	<div class="form-group">
																		<label class="control-label">Max</label>
																		<div class="controls">
																			<input type="text" id="textareamaxvalue" class="form-control" data-gv-property="max">
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>


													<div class="hidden">
														<textarea id="TextAreaTemplate" rows="3" class="form-control k-textbox" data-role="textarea"></textarea>
													</div>


													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div id="headerproperties" class="row">
												<div class="prop-mg-lr col-md-12">

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="headerdefautvalue" placeholder="Header" class="form-control" data-gv-property="value" />
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="subheaderdefautvalue" placeholder="Subheader" class="form-control" data-gv-property="value" />
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Header Size</label>
																<div class="controls">
																	<select id="headeroption" class="form-control">
																		<option value="h1">Header 1</option>
																		<option value="h2">Header 2</option>
																		<option value="h3">Header 3</option>
																		<option value="h4">Header 4</option>
																		<option value="h5">Header 5</option>
																	</select>
																</div>
															</div>
														</div>
													</div>

													<p id="HeaderTemplate" data-default-label="Header" data-default-is-header="true" data-control-type="header"></p>




												</div>
											</div>

											<div id="paragraphproperties" class="row">
												<div class="prop-mg-lr col-md-12">

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Paragraph</label>
																<div class="controls">
																	<textarea id="paragraphdefautvalue" rows="3" class="form-control" style="height: 150px;" data-role="textarea"></textarea>
																</div>
															</div>
														</div>
													</div>



													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>


											<div id="panelproperties" class="row">
												<div class="prop-mg-lr col-md-12">

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Header</label>
																<div class="controls">
																	<input type="text" id="panelHeader" class="form-control" data-gv-property="header">
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Footer</label>
																<div class="controls">
																	<input type="text" id="panelFooter" class="form-control" data-gv-property="footer">
																</div>
															</div>
														</div>
													</div>

													<div class="hidden">

														<div id="panel1xTemplate" class="panel panel-default" data-role="panel">
															<div class="panel-heading">Panel</div>
															<div class="panel-body tab-canvas"></div>
														</div>

													</div>




												</div>
											</div>


											<div id="buttonproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__butgrpinline" type="checkbox"> Inline
																</label>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__butgrpit" type="checkbox"> Group
																</label>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__rightalign" type="checkbox"> Right Align
																</label>
															</div>
														</div>
													</div>

													<div class="row">

														<div class="prop-mg-lr col-md-12 prop-mg-lr">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select id="butpropSize" class="form-control">
																		<option selected="selected" disabled="">Sizes...</option>
																		<option value="btn-default">Default</option>
																		<option value="btn-lg">Large</option>
																		<option value="btn-sm">Small</option>
																		<option value="btn-xs">Extra Small</option>
																	</select>
																</div>
															</div>

														</div>
													</div>

													<div>
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Buttons<a><i class="icon-question-sign" rel="popover" data-trigger="hover" data-content="data content"
																		data-original-title="Title"></i></a></label>
																<div class="controls">
																	<ul id="__buttons" class="nav nav-pills nav-stacked"></ul>
																</div>
															</div>
														</div>


													</div>

													<div class="hidden">
														<button id="ButtonTemplate" type="button" class="btn btn-default">Button</button>
														<div id="ButtonOptions">
															<div class="input-group-btn">
																<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
																	<span class="caret"></span> <span class="sr-only"></span>
																</button>

															</div>
														</div>
													</div>




													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input id="butpropName" type="text" class="form-control" placeholder="Name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input id="butpropId" type="text" class="form-control" placeholder="Id">
																</div>
															</div>
														</div>
													</div>

													<div class="row">

														<div class="prop-mg-lr col-md-12 prop-mg-lr">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select id="butpropStyle" data-placeholder="Please choose field size" class="form-control">
																		<option selected="selected" disabled="">Options...</option>
																		<option value="btn-default">Default</option>
																		<option value="btn-primary">Primary</option>
																		<option value="btn-success">Success</option>
																		<option value="btn-info">Info</option>
																		<option value="btn-warning">Warning</option>
																		<option value="btn-danger">Danger</option>
																		<option value="btn-link">Link</option>
																	</select>
																</div>
															</div>

														</div>
													</div>

												</div>
											</div>

											<div id="pillsproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox">
																<label> <input id="__pillStacked" type="checkbox"> Stacked
																</label>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6"></div>
													</div>


													<div>
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Pills<a><i class="icon-question-sign" rel="popover" data-trigger="hover" data-content="data content"
																		data-original-title="Title"></i></a></label>
																<div class="controls">
																	<ul id="__pills" class="nav nav-pills nav-stacked"></ul>
																</div>
															</div>
														</div>


													</div>

													<div class="hidden">
														<ul id="PillsTemplate" class="nav nav-pills nav-stacked">
															<li><a>Nav 1</a></li>
															<li><a>Nav 2</a></li>
															<li><a>Nav 3</a></li>
														</ul>
														<div id="PillsOptions"></div>
													</div>




													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input id="pillpropName" type="text" class="form-control" placeholder="Name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input id="pillpropId" type="text" class="form-control" placeholder="Id">
																</div>
															</div>
														</div>
													</div>




												</div>
											</div>

											<div id="dateproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="row">
															<div class="prop-mg-lr col-md-12">
																<div class="form-group-prop has-feedback">
																	<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="prop-mg-lr col-md-12">
																<div class="form-group-prop">
																	<label class="control-label sr-only"></label>
																	<div class="controls">
																		<input type="text" id="datedefaultvalue" placeholder="Default Value" data-gv-property="datedefault" data-property="default" class="form-control" value=""
																			data-role="datepicker">
																	</div>
																</div>
															</div>
														</div>

														<div class="row">


															<div class="prop-mg-lr col-md-6">
																<div class="form-group-prop">
																	<label class="control-label sr-only"></label>
																	<div class="controls">
																		<input type="text" data-role="datepicker" placeholder="Min Value" data-gv-property="datemin" data-property="min" id="datepickermin" class="form-control" />
																	</div>
																</div>
															</div>
															<div class="prop-mg-lr col-md-6">
																<div class="form-group-prop">
																	<label class="control-label sr-only"></label>
																	<div class="controls">
																		<input type="text" data-role="datepicker" placeholder="Max Value" data-gv-property="datemax" data-property="max" id="datepickermax" class="form-control" />
																	</div>
																</div>
															</div>

														</div>

														<div class="row">
															<div class="prop-mg-lr col-md-6">
																<div class="form-group-prop">
																	<label class="control-label">Less Than</label>
																	<div class="controls">
																		<select id="dateLessThan" data-gv-property="data-beforedate" class="form-control"></select>
																	</div>
																</div>
															</div>

															<div class="prop-mg-lr col-md-6">
																<div class="form-group-prop">
																	<label class="control-label">Greater Than</label>
																	<div class="controls">
																		<select id="dateGreaterThan" data-gv-property="data-afterdate" class="form-control"></select>
																	</div>
																</div>
															</div>
														</div>

														<div id="dateTemplateContainer" class="hidden">
															<input id="DateTemplate" type="text" class="form-control" data-role="date">
														</div>

														<div class="row">
															<div class="prop-mg-lr col-md-12">
																<div class="form-group-prop">
																	<label class="control-label sr-only"></label>
																	<div class="controls">
																		<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="prop-mg-lr col-md-12">
																<div class="form-group-prop">
																	<label class="control-label sr-only"></label>
																	<div class="controls">
																		<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div id="timeproperties" class="row">
												<div class="row">

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="timedefaultvalue" placeholder="Default Time" data-role="timepicker" class="form-control" data-property="default"
																		data-gv-property="datedefault">
																</div>
															</div>
														</div>
													</div>


													<div class="row">


														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" data-role="timepicker" placeholder="Min Value" data-gv-property="timemin" data-property="min" id="timepickermin" class="form-control" />
																</div>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" data-role="timepicker" placeholder="Max Value" data-gv-property="timemax" data-property="max" id="timepickermax" class="form-control" />
																</div>
															</div>
														</div>

													</div>


													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Less Than</label>
																<div class="controls">
																	<select id="timeLessThan" data-gv-property="data-beforedate" class="form-control"></select>
																</div>
															</div>
														</div>

														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Greater Than</label>
																<div class="controls">
																	<select id="timeGreaterThan" data-gv-property="data-afterdate" class="form-control"></select>
																</div>
															</div>
														</div>
													</div>

													<div class="hidden">
														<input id="TimeTemplate" type="text" class="form-control" data-role="time">

													</div>


													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div id="datetimeproperties" class="row">
												<div class="row">

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" data-role="datetimepicker" placeholder="Default Date" id="datetimedefaultdatevalue" data-property="default" class="form-control"
																		data-gv-property="datetimepickerdefault">
																</div>
															</div>
														</div>


													</div>

													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" data-role="datetimepicker" placeholder="Min Value" data-gv-property="datetimemin" data-property="min" id="datetimepickermin"
																		class="form-control" />
																</div>
															</div>
														</div>


													</div>

													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" data-role="datetimepicker" placeholder="Max Value" data-gv-property="datetimemax" data-property="max" id="datetimepickermax"
																		class="form-control" />
																</div>
															</div>
														</div>

													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Less Than</label>
																<div class="controls">
																	<select id="datetimeLessThan" data-gv-property="data-beforedate" class="form-control"></select>
																</div>
															</div>
														</div>

														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Greater Than</label>
																<div class="controls">
																	<select id="datetimeGreaterThan" data-gv-property="data-afterdate" class="form-control"></select>
																</div>
															</div>
														</div>
													</div>

													<div class="hidden">
														<input id="DateTimeTemplate" type="text" class="form-control" data-role="datetime">

													</div>


													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div id="xxdatetimeproperties" class="row">
												<div class="row">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label" for="chosenTest">Field Size</label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="size" class="form-control">
																		<option>Mini</option>
																		<option>Small</option>
																		<option>Medium</option>
																		<option>Large</option>
																		<option>Extra Large</option>
																		<option Selected>Stretched</option>
																	</select>
																</div>
															</div>

														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
												</div>
											</div>

											<div id="radioproperties" class="row">
												<div class="prop-mg-lr col-md-12">

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="form-inline">
																	<label>Options:&nbsp;</label> <a id="rdselectLabel" class="label label-primary rdselect">Label</a> <a id="rdselectValue" class="label label-primary rdselect">Value</a>
																	<a id="rdselectId" class="label label-primary rdselect">ID</a>
																</div>
																<div id="rdsitem" class="controls">
																	<ul id="__radioItems" class="nav nav-pills nav-stacked"></ul>
																</div>
															</div>
														</div>
													</div>

													<div class="hidden">
														<div id="RadioTemplate" data-default-label="Select an Option" class="form-control " data-role="radio">
															<label class="radio"><input type="radio" value="Option 1">Option 1</label> <label class="radio"><input type="radio" value="Option 2">Option 2</label>
															<label class="radio"><input type="radio" value="Option 3">Option 3</label>
														</div>

													</div>




													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-gv-property="layout" class="form-control">
																		<option>Inline</option>
																		<option>One Column</option>
																		<option>Two Columns</option>
																		<option>Three Columns</option>
																		<option>Four Columns</option>
																	</select>
																</div>
															</div>

														</div>
													</div>


												</div>
											</div>

											<div id="checkboxproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="form-inline">
																	<label>Options:&nbsp;</label> <a id="rdcheckLabel" class="label label-primary rdselect">Label</a> <a id="rdcheckValue" class="label label-primary rdselect">Value</a>
																	<a id="rdcheckId" class="label label-primary rdselect">ID</a>
																</div>
																<div id="rdcitems" class="controls">
																	<ul id="__checkboxOptions" class="nav nav-pills nav-stacked"></ul>
																</div>
															</div>
														</div>
													</div>


													<div class="hidden">

														<div id="CheckBoxTemplate" data-default-label="Check all that apply" class="form-control" data-role="checkbox">
															<label class="checkbox"><input type="checkbox" value="Option 1">Option 1</label> <label class="checkbox"><input type="checkbox" value="Option 2">Option
																2</label> <label class="checkbox"><input type="checkbox" value="Option 3">Option 3</label>
														</div>
													</div>


													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Check at least</label>
																<div class="controls">
																	<input type="text" id="checkboxmin" class="form-control" data-gv-property="min" />
																</div>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Check at Most</label>
																<div class="controls">
																	<input type="text" id="checkboxmax" class="form-control" data-gv-property="max" />
																</div>
															</div>
														</div>
													</div>


													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-gv-property="layout" class="form-control">
																		<option>Inline</option>
																		<option>One Column</option>
																		<option>Two Columns</option>
																		<option>Three Columns</option>
																		<option>Four Columns</option>
																	</select>
																</div>
															</div>

														</div>
													</div>

												</div>
											</div>

											<div id="selectproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="form-inline">
																	<label>Options:&nbsp;</label> <a id="___rdselectLabel" class="label label-primary rdselect">Label</a> <a id="___rdselectValue" class="label label-primary rdselect">Value</a>
																</div>
																<div id="___rdselectitems" class="controls">
																	<ul id="__selectOptions" class="nav nav-pills nav-stacked"></ul>
																</div>
															</div>
														</div>
													</div>

													<div id="selectTemplateContainer" class="hidden">
														<select id="SelectTemplate" class="form-control" data-role="select">
															<option value=""></option>
															<option value="Option 1">Option 1</option>
															<option value="Option 2">Option 2</option>
															<option value="Option 3">Option 3</option>
															<option value="Option 4">Option 4</option>
														</select>
													</div>




													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>

												</div>
											</div>


											<div id="numericproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>





													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textplaceholder" class="form-control" data-gv-property="placeholder" placeholder="Placeholder">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row"></div>

													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">

																	<select id="numericFormat" data-gv-property="format" class="form-control">
																		<option value="integer" selected="selected" disabled>Format...</option>
																		<option value="integer">Integer</option>
																		<option value="decimal">Decimal</option>
																		<option value="currency">Currency</option>
																		<option value="percentage">Percentage</option>
																	</select>
																</div>
															</div>
														</div>

													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" data-role="numerictextbox" id="numdefaultvalue" class="form-control" data-gv-property="value" data-property="value" placeholder="Default Value" />
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only">Min Value</label>
																<div class="controls">
																	<input type="text" data-role="numerictextbox" id="numminvalue" class="form-control" data-gv-property="min" placeholder="Min Value" data-property="min" />
																</div>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" data-role="numerictextbox" id="nummaxvalue" class="form-control" data-gv-property="max" placeholder="Max Value" data-property="max" />
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Less Than</label>
																<div class="controls">
																	<select id="numlessThan" data-gv-property="data-lessthan" class="form-control"></select>
																</div>
															</div>
														</div>

														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label">Greater Than</label>
																<div class="controls">
																	<select id="numGreaterThan" data-gv-property="data-greaterthan" class="form-control"></select>
																</div>
															</div>
														</div>
													</div>


													<div class="hidden">
														<input id="NumericTemplate" value="" type="number" class="form-control" data-role="numeric" data-format="integer">
													</div>




													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>

												</div>
											</div>



											<div id="maskproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<div class="checkbox">
																	<label class=""><input type="checkbox" class="" data-gv-property="required" />Required</label>
																</div>
															</div>
														</div>

													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="row">
																<div class="prop-mg-lr col-md-12">
																	<div class="form-group-prop">
																		<label class="control-label sr-only"></label>
																		<div class="controls">
																			<input type="text" id="textdefautvalue" class="form-control" data-gv-property="value" placeholder="Default Value" />
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="row">
																<div class="prop-mg-lr col-md-12">
																	<div class="form-group-prop">
																		<label class="control-label sr-only"></label>
																		<div class="controls">
																			<input type="text" id="maskman" placeholder="Mask" class="form-control" data-gv-property="mask" data-property="mask">
																		</div>
																	</div>
																</div>
															</div>


															<div class="hidden">
																<input id="MaskTemplate" type="text" class="form-control k-textbox" data-role="mask">
															</div>



														</div>
													</div>


													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textplaceholder" class="form-control" data-gv-property="placeholder" placeholder="Placeholder">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>
													<div class="row">

														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Name" data-gv-property="control-name">
																</div>
															</div>
														</div>
													</div>
													<div class="row">


														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" class="form-control" placeholder="Id" data-gv-property="control-id">
																</div>
															</div>
														</div>
													</div>

												</div>
											</div>

											<div id="columnproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="form-group-prop">
														<label class="control-label">Alignment</label>
														<div class="controls">
															<select data-placeholder="Please choose field size" data-gv-property="groupalignment" class="form-control">
																<option Selected></option>
																<option>Top</option>
																<option>Left</option>
																<option>Right</option>
															</select>
														</div>
													</div>









												</div>
											</div>

											<div id="accordionproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="form-group">
														<label class="control-label">Options<a><i class="icon-question-sign" rel="popover" data-trigger="hover" data-content="data content"
																data-original-title="Title"></i></a></label>
														<div class="controls">
															<ul id="__accordionItems" class="nav nav-pills nav-stacked"></ul>
														</div>
													</div>
												</div>
											</div>



											<div id="tabproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="form-group">
														<label class="control-label">Options<a><i class="icon-question-sign" rel="popover" data-trigger="hover" data-content="data content"
																data-original-title="Title"></i></a></label>
														<div class="controls">
															<ul id="__tabItems" class="nav nav-pills nav-stacked"></ul>
														</div>
													</div>
												</div>
											</div>

											<div id="pageproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Next</label>
																<div class="controls">
																	<input id="nextPage" type="text" class="form-control">
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">previous</label>
																<div class="controls">
																	<input id="prevPage" type="text" class="form-control">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div id="heroproperties" class="row">
												<div class="prop-mg-lr col-md-12"></div>
											</div>

											<div id="sliderproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop has-feedback">
																<label class="control-label"></label> <input type="text" data-gv-property="label" class="form-control" placeholder="Label" />
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input type="text" id="textsublabel" data-gv-property="sublabel" class="form-control" placeholder="Sub Label">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<textarea data-gv-property="hover" style="height: 50px;" class="form-control" placeholder="Hover Text"></textarea>
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr  col-md-12">
															<div class="form-group-prop">
																<div class="controls">

																	<label class="checkbox"><input id="sliderShowButtons" type="checkbox" />Show Buttons</label>
																</div>
															</div>
														</div>
													</div>
													<div class="row">



														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Orientation</label>
																<div class="controls">

																	<select id="sliderOrientation" class="span">
																		<option value="horizontal">Horizontal</option>
																		<option value="vertical">Vertical</option>
																	</select>
																</div>
															</div>
														</div>

													</div>
													<div id="sliderNumber" class="row">
														<div class="prop-mg-lr col-md-4">
															<div class="form-group-prop">
																<label class="control-label">Default Value</label>
																<div class="controls">
																	<input type="text" data-role="numerictextbox" data-property="value" id="sliderdefaultvalue" class="form-control" data-gv-property="value">
																</div>
															</div>
														</div>
														<div class="prop-mg-lr col-md-4">
															<div class="form-group-prop">
																<label class="control-label">Min Value</label>
																<div class="controls">
																	<input type="text" data-role="numerictextbox" data-property="min" id="slidernumminvalue" class="form-control" data-gv-property="min">
																</div>
															</div>
														</div>
														<div class="prop-mg-lr col-md-4">
															<div class="form-group-prop">
																<label class="control-label">Max Value</label>
																<div class="controls">
																	<input type="text" data-role="numerictextbox" data-property="max" id="slidernummaxvalue" class="form-control" data-gv-property="max">
																</div>
															</div>
														</div>
													</div>
													<div class="hidden">

														<input id="SliderTemplate" type="text" class="form-control" data-role="slider">
													</div>


													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label" for="chosenTest">Field Size</label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="size" class="form-control">
																		<option>Mini</option>
																		<option>Small</option>
																		<option>Medium</option>
																		<option>Large</option>
																		<option>Extra Large</option>
																		<option Selected>Stretched</option>
																	</select>
																</div>
															</div>

														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<select data-placeholder="Please choose field size" data-gv-property="alignment" class="form-control">
																		<option selected="selected" disabled>Alignment...</option>
																		<option>Top</option>
																		<option>Left</option>
																		<option>Right</option>
																	</select>
																</div>
															</div>

														</div>
													</div>
												</div>
											</div>

											<div id="datatableproperties" class="row">
												<div class="prop-mg-lr col-md-12">
													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__hover" type="checkbox"> Hover
																</label>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__striped" type="checkbox"> Striped
																</label>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__search" type="checkbox"> Search
																</label>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__pagination" type="checkbox"> Pagination
																</label>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__showrefresh" type="checkbox"> Show Refresh
																</label>
															</div>
														</div>
														<div class="prop-mg-lr col-md-6">
															<div class="checkbox gv-prop-chk">
																<label> <input id="__showcolumns" type="checkbox"> Show Columns
																</label>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label sr-only"></label>
																<div class="controls">
																	<input id="__height" type="text" class="form-control" placeholder="Height">
																</div>
															</div>
														</div>
													</div>




													<div>
														<div class="prop-mg-lr col-md-12">
															<div class="form-group-prop">
																<label class="control-label">Columns<a><i class="icon-question-sign" rel="popover" data-trigger="hover" data-content="data content"
																		data-original-title="Title"></i></a></label>
																<div class="controls">
																	<ul id="__columns" class="nav nav-pills nav-stacked"></ul>
																</div>
															</div>
														</div>


													</div>

													<div class="hidden">
														<!-- DataTable : Begin -->
														<table id="DataTableTemplate" data-role="datatable" data-url="" data-striped="false" data-page-size="10" data-search="false" data-show-columns="false"
															data-show-refresh="false" data-cache="false" data-height="300" data-pagination="false">
															<thead>
																<tr>
																	<th data-field="column1" data-sortable="true">Column 1</th>
																	<th data-field="column2" data-sortable="true">Column 2</th>
																	<th data-field="column3" data-sortable="true">Column 3</th>
																</tr>
															</thead>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>
															<tr>
																<td>-</td>
																<td>-</td>
																<td>-</td>
															</tr>

														</table>
														<!-- DataTable : End -->
													</div>






												</div>
											</div>


										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!--/span-->
		<div id="ctr" class="center">
			<form action="http://protostrap.com/dashboard/submit" role="form" id="designform" data-validate="parsley" data-id="00000000-0000-0000-0000-000000000000" novalidate method="post">



				<div class="form-horizontal shadow">
					<div id="errorcontainer" class="alert alert-block alert-error fade in hidden"></div>
					<div id="design-canvas" class="mcanvas" data-control-count="0">
						<div class="row ui-sortable">
							<div class="col-md-12 gv-droppable-grid ui-sortable" data-alignment="Left"></div>
						</div>
					</div>
				</div>



			</form>
		</div>
		<!--/row-->



	</div>


	<div class="hidden">
		<!-- Form Control Template -->
		<div id="controlGroupTemplate" class="form-group">
			<label class="control-label-left">Untitled</label>
			<div class="controls"></div>

		</div>

		<div id="containerGroupTemplate" class="form-group"></div>

		<div id="horizontalGroupTemplate" class="form-group hori"></div>

		<input id="TextTemplate" type="text" class="form-control" data-role="text"> <input id="MaskTemplate" type="text" class="span" data-role="mask"> <input
			id="SliderTemplate" type="text" class="span" data-role="slider">
		<textarea id="xxTextAreaTemplate" rows="3" class="span" data-role="textarea"></textarea>

		<div id="EditorTemplate" data-role="editor" class="span">this is a test</div>





		<p id="HeaderTemplate" data-default-label="Header" data-default-is-header="true"></p>

		<p id="ParagraphTemplate" data-default-label="Paragraph" data-default-is-header="true"></p>
		<!-- Form Control Template : End -->

		<!-- Tab Template -->
		<div class="row gv-container" id="tabTemplate" data-role="tab">
			<ul class="nav nav-tabs">
				<li class="active"><a href="edit#tab1" data-toggle="tab">Tab 1</a></li>
				<li><a data-toggle="tab">Tab 2</a></li>
				<li><a href="edit#tab3" data-toggle="tab">Tab 3</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab1">
					<div class="tab-canvas"></div>
				</div>
				<div class="tab-pane" id="tab2">
					<div class="tab-canvas"></div>
				</div>
				<div class="tab-pane" id="tab3">
					<div class="tab-canvas"></div>
				</div>
			</div>
		</div>

		<!-- Tab Template : End -->



		<!-- Collapsible Template -->
		<div class="row" id="panelTemplate" data-role="accordion">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion">Collapse</a>
						</h4>
					</div>
					<div class="panel-collapse collapse in">
						<div class="panel-body tab-canvas"></div>
					</div>
				</div>


			</div>
		</div>
		<!-- Collapsible Template : End -->


		<div id="panel1Template" class="panel panel-default" data-role="panel">
			<div class="panel-heading">Panel</div>
			<div class="panel-body tab-canvas"></div>
		</div>



		<div class="hero-unit" id="HeroTemplate" data-role="hero">
			<h1>Hello, world!</h1>
			<p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a
				starting point to create something more unique.</p>
			<p>
				<a class="btn btn-primary btn-large">Learn more »</a>
			</p>
		</div>


		<!-- page break -->
		<div class="row" id="pageBreakTemplate" data-role="pagebreak">
			<div class="gv-page">
				<ul class="pager">
					<li class="previous"><a href="edit#">Back</a></li>
					<li class="next"><a href="edit#">Next</a></li>
				</ul>
			</div>

		</div>
		<!-- page break : End -->

		<!-- Modal -->

	</div>

	<div class="modal fade hidden" id="modalPreview" data-show='false' tabindex="-1" role="dialog" style="background-color: #ffffff;" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="row">
			<div class="col-md-11">
				<ul class="nav nav-pills" style="margin: 0px;">
					<li><a id="previewDownload" href="edit#" title="Download HTML"><span class="glyphicon glyphicon-cloud-download"></span></a></li>
					<li id="pfullsize"><a id="fullsize" href="edit#" title="Fullsize"><span class="glyphicon glyphicon-resize-full"></span></a></li>
					<li id="presize" style="visibility: hidden;"><a id="resize" href="edit#" title="Fullsize"><span class="glyphicon glyphicon-resize-small"></span></a></li>
				</ul>
			</div>
			<div class="col-md-1">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;&nbsp;</button>
			</div>
		</div>
		<div class="modal-body"></div>
	</div>

	<div id="modalAction" class="modal hidden" role="dialog">
		<div class="modal-body"></div>
		<div class="modal-footer">
			<a href="edit#" id="modalActionClose" data-dismiss="modal" class="btn btn-large">Cancel</a> <a href="edit#" id="modalActionSave" class="btn btn-large btn-primary">Save and
				Close</a>
		</div>
	</div>
</div>
<!--/container-->
 
<script src="<c:url value="/js/component/generate/entity/builder/jquery/jquery-2.0.3.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/component/generate/entity/builder/jquery/jquery-ui-1.10.3.custom.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/component/generate/entity/builder/jquery/jquery.metadata.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/component/generate/entity/builder/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/component/generate/entity/builder/bootstrap/js/bootstrap-table.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/component/generate/entity/builder/inputmask/js/jquery.inputmask.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/component/generate/entity/builder/lionbars/js/jquery.lionbars.0.3.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/component/generate/entity/builder/kendoui/js/kendo.web.min.js"/>"></script>
<script src="<c:url value="/js/component/generate/entity/builder/wfi.js"/>"></script>
<script src="<c:url value="/js/component/generate/entity/builder/download.js"/>"></script>
<script src="<c:url value="/js/component/generate/entity/builder/proto/js.js"/>"></script>


<script>
	$(function() {

		gvDesigner.Initialize();

		var reBar = ".sidebar-left";
		var bodyheight = $(reBar).height();
		$(reBar).height(bodyheight);

		var otime = false;
		$(window).resize(function() {
			var bodyheight = $(reBar).height();
			$(reBar).height(bodyheight);
			$(".lb-v-scrollbar-slider > " + reBar).removeClass();

			if (otime == false) {
				otime = true;
				$(reBar).waitForImages(function() {

					// All images are loaded, initialize the scroll bar plug-in
					//$(reBar).lionbars();
				});
			}

		});

		$(reBar).waitForImages(function() {

			// All images are loaded, initialize the scroll bar plug-in
			$(reBar).lionbars();

			//$(reBar).slimscroll({
			//    color: '#00f',
			//    size: '10px',
			//    height: bodyheight + 'px'
			//});

		});

	});
</script>
</body>
</html>
