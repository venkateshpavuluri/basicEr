<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 
 @author Srinivas
 @version 1.0    -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>jQuery UI Tabs - Default functionality</title>
<link rel="stylesheet" href="/resources/demos/style.css" />
<link href="accordionmenu.css" rel="stylesheet" type="text/css" />
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet"
	type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="js/jqueryui.css" />
<script src="js/jquery-1.7.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/MntValidator.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//AddForm Validations
						$('#sumbnid')
								.click(
										function(event) {
											//alert($('#sdType').val());

											$('#addhbform')
													.validate(
															{
																rules : {

																	orgid : {
																		required : true
																	},
																	bankcode : {
																		required : true,
																		alphanumeric : true,
																		specialcharacters : true
																	},
																	bankname : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																	branchname : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																	address : {
																		required : true
																	},
																	city : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																	state : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																	country : {
																		required : true
																	},
																	swiftcode : {
																		required : true,
																		alphanumeric : true,
																		specialcharacters : true
																	},
																	ifsccode : {
																		required : true,
																		alphanumeric : true,
																		specialcharacters : true
																	}

																},
																messages : {

																	orgid : "<font style='color: red;'><b>Orgnaization Name is Required</b></font>",
																	bankcode : {
																		required:"<font style='color: red;'><b>Bank Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	bankname : {
																		required:"<font style='color: red;'><b>Bank Name is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	branchname :{
																		required:"<font style='color: red;'><b>Branch Name is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	} ,
																	address : "<font style='color: red;'><b>Address is Required</b></font>",
																	city : {
																		required:"<font style='color: red;'><b>City is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	state :{
																		required:"<font style='color: red;'><b>State is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	} ,
																	country : "<font style='color: red;'><b>Country is Required</b></font>",
																	swiftcode :{
																		required:"<font style='color: red;'><b>Swift Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	} ,
																	ifsccode :{
																		required:"<font style='color: red;'><b>IFSC Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	} 
																},

															});
										});
						//UpdateForm Validations
						$('#updated')
								.click(
										function(event) {
											//var assetedit = $('#assetEditId').val();
											//alert(assetedit);
											$('#edithbForm')
													.validate(
															{
																rules : {

																	orgidedit : {
																		required : true
																	},
																	bankcodeedit : {
																		required : true,
																		alphanumeric : true,
																		specialcharacters : true
																	},
																	branchnameedit : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																	addressedit : {
																		required : true
																	},
																	cityedit : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																	stateedit : {
																		required : true,
																		alphabets : true,
																		specialcharacters : true
																	},
																	countryedit : {
																		required : true
																	},
																	swiftcodeedit : {
																		required : true,
																		alphanumeric : true,
																		specialcharacters : true
																	},
																	ifsccodeedit : {
																		required : true,
																		alphanumeric : true,
																		specialcharacters : true
																	}

																},
																messages : {
																	orgidedit : "<font style='color: red;'><b>Orgnaization Name is Required</b></font>",
																	bankcodeedit : {
																		required:"<font style='color: red;'><b>Bank Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	banknameedit : {
																		required:"<font style='color: red;'><b>Bank Name is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	branchnameedit : {
																		required:"<font style='color: red;'><b>Branch Name is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	addressedit : "<font style='color: red;'><b>Address is Required</b></font>",
																	cityedit : {
																		required:"<font style='color: red;'><b>City is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	stateedit : {
																		required:"<font style='color: red;'><b>State is Required</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	},
																	countryedit : "<font style='color: red;'><b>Country is Required</b></font>",
																	swiftcodeedit :{
																		required:"<font style='color: red;'><b>Swift Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	} ,
																	ifsccodeedit :{
																		required:"<font style='color: red;'><b>IFSC Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>"
																	}
																},
															});

										});

					});
</script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#add,#search').click(function(e) {
			$('#edit').hide();
			$('#successmessage').hide();
			$('#savemessage').hide();

		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		if (document.getElementById("housebankhide").value == 1) {
			var index = $('#tabs li a').index($('a[href="#tabs-3"]').get(0));

			$('#tabs').tabs({
				active : index
			});
		}

	});
</script>

<style type="text/css">
.required {
	color: red;
	font-style: Bold;
}

.TabbedPanelsContent {
	border: 5px;
	font-style: Bold;
}
</style>




</head>
<body>
	<div class="divUserDefault">
		<div class="PageTitle">
			<spring:message code="label.housebank" />
		</div>
		<div id="tabs" class="TabbedPanels">
			<ul class="TabbedPanelsTabGroup">
				<c:forEach var="editvalues" items="${editvalues}">
					<li class="TabbedPanelsTab"><a href="#tabs-1" id="edit"><spring:message
								code="label.edit" /></a></li>

				</c:forEach>
				<li class="TabbedPanelsTab"><a href="#tabs-2" id="search"><spring:message
							code="label.search" /></a></li>
				<li class="TabbedPanelsTab"><a href="#tabs-3" id="add"><spring:message
							code="label.add" /></a></li>
			</ul>

			<div id="tabs-2" class="TabbedPanelsContent">
				<div align="left">
					<form:form action="searchHousebank.mnt" method="GET"
						commandName="HOUSEBANK">
						<table class="tableGeneral">
							<tr>
								<td colspan="2"><c:forEach var="houseBankAdd"
										items="${param.list}">
										<div class="alert-success" id="savemessage">
											<strong><spring:message code="label.success" /></strong>
											<spring:message code="label.housebank" />
											<spring:message code="label.saveSuccess"></spring:message>
										</div>
									</c:forEach> <c:if test="${param.listwar!=null }">
										<div class="alert-danger" id="savemessage">
											<strong><spring:message code="label.error" /> </strong>

											<spring:message code="label.housebank" />
											<spring:message code="label.saveFail" />
										</div>
									</c:if> <c:forEach var="hbupdate" items="${hbupdate}">
										<div class="alert-success" id="successmessage">
											<strong><spring:message code="label.success" /></strong>
											<spring:message code="label.housebank" />
											<spring:message code="label.updateSuccess"></spring:message>
										</div>
									</c:forEach> <c:forEach var="hbupdateError" items="${hbupdateError}">
										<div class="alert-danger" id="successmessage">
											<strong><spring:message code="label.error" /></strong>
											<spring:message code="label.housebank" />
											<spring:message code="label.updateFail"></spring:message>
										</div>
									</c:forEach>
									<c:forEach var="bankDeleted" items="${bankDeleted}">
										<div class="alert-success" id="successmessage">
											<strong><spring:message code="label.success" /></strong>
											<spring:message code="label.housebank" />
											<spring:message code="label.deleteSuccess"></spring:message>
										</div>
									</c:forEach>
									<c:forEach var="bankDeletedError" items="${bankDeletedError}">
										<div class="alert-danger" id="successmessage">
											<strong><spring:message code="label.error" /></strong>
											<spring:message code="label.housebank" />
											<spring:message code="label.deleteFail"></spring:message>
										</div>
									</c:forEach></td>
							</tr>

							<tr>
								<td><spring:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">

										<form:options items="${xmlItems}" />
									</form:select> <spring:bind path="HOUSEBANK.operations">
										<select class="select" name="operations">
											<option value="<spring:message code='assignedOperator'/>">
												<spring:message code="label.equalsTo" />
											</option>
											<option value="<spring:message code='notequalsOperator'/>">
												<spring:message code="label.notEqualsTo" />
											</option>
											<option value="<spring:message code='beginsWithOperator'/>">
												<spring:message code="label.beginsWith" />
											</option>
											<option value="<spring:message code='endsWithOperator'/>">
												<spring:message code="label.endsWith" />
											</option>
											<option value="<spring:message code='containsOperator'/>">
												<spring:message code="label.contains" />
											</option>
										</select>
									</spring:bind> <form:input path="basicSearchId" cssClass="textbox" /> <c:set
										var="save" value="false"></c:set> <c:set var="edit"
										value="false"></c:set> <c:set var="delete" value="false"></c:set>
									<c:set var="update" value="false"></c:set> <c:set var="search"
										value="false"></c:set> <fmt:setBundle basename="button" /> <fmt:message
										key="label.save" var="messagesav" /> <fmt:message
										key="label.search" var="messagesea" /> <fmt:message
										key="label.delete" var="messagedel" /> <fmt:message
										key="label.update" var="messageup" /> <fmt:message
										key="label.edit" var="messageed" /> <c:forEach
										items="${sessionScope.privilegeList}" var="privileges">
										<c:choose>
											<c:when test="${privileges eq messagesav }">
												<c:set var="save" value="true"></c:set>
											</c:when>
											<c:when test="${privileges eq messagesea }">
												<c:set var="search" value="true"></c:set>
											</c:when>
											<c:when test="${privileges eq messagedel }">
												<c:set var="delete" value="true"></c:set>
											</c:when>
											<c:when test="${privileges eq messageed }">
												<c:set var="edit" value="true"></c:set>
											</c:when>
											<c:when test="${privileges eq messageup }">
												<c:set var="update" value="true"></c:set>
											</c:when>
										</c:choose>

									</c:forEach> <c:choose>
										<c:when test="${search}">
											<input type="submit"
												value="<spring:message code="label.search"/>"
												class="btn btn-primary" />
										</c:when>
										<c:otherwise>
											<input type="submit" disabled="disabled"
												value="<spring:message code="label.search"/>"
												class="btn btn-danger" />
										</c:otherwise>
									</c:choose></td>

							</tr>

						</table>
					</form:form>

					<c:if test="${HBBeans!=null}">
						<display:table id="soid" name="HBBeans"
							requestURI="searchHousebank.mnt" pagesize="5" class="table">
							<display:column property="bankid" title="BankId" media="none"
								sortable="true"></display:column>
							<display:column property="orgid" titleKey="label.horg"
								media="html" sortable="true"></display:column>
							<display:column property="bankcode" titleKey="label.bcode"
								media="html" sortable="true"></display:column>
							<display:column property="bankname" titleKey="label.bname"
								media="html" sortable="true"></display:column>
							<display:column property="branchname" titleKey="label.brname"
								media="html" sortable="true"></display:column>
							<display:column property="address" titleKey="label.addr"
								media="html" sortable="true"></display:column>
							<display:column property="city" titleKey="label.hcity"
								media="html" sortable="true"></display:column>
							<display:column property="state" titleKey="label.hstate"
								media="html" sortable="true"></display:column>
							<display:column property="country" titleKey="label.hcountry"
								media="html" sortable="true"></display:column>
							<display:column property="swiftcode" titleKey="label.scode"
								media="html" sortable="true"></display:column>
							<display:column property="ifsccode" titleKey="label.ifsccode"
								media="html" sortable="true"></display:column>
							<display:column titleKey="label.edit" style="color:white">
								<c:choose>
									<c:when test="${edit }">
										<a
											href="housebankEdit.mnt?hbedit=<c:out value="${soid.bankid}"/>"
											style="color: red"><img src="images/Edit.jpg"
											width="20px" height="20px" /> </a>
									</c:when>
									<c:otherwise>
										<a><img src="images/Edit.jpg" width="20px" height="20px"
											class="btn btn-danger" /></a>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column titleKey="label.delete">
								<c:choose>
									<c:when test="${delete }">
										<a
											href="housebankDelete.mnt?housedelete=<c:out value="${soid.bankid}"/>"
											style="color: red"><img src="images/Delete.jpg"
											width="20px" height="20px"
											onclick="return confirm('Do You Want to Delete the Selected Record ?')" /></a>
									</c:when>
									<c:otherwise>

										<a><img src="images/Delete.jpg" class="btn btn-danger"
											width="20px" height="20px" /></a>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:setProperty name="paging.banner.placement"
								value="bottom" />
						</display:table>
					</c:if>

				</div>
			</div>

			<div id="tabs-3" class="TabbedPanelsContent">
				<div align="left" class="TabbedPanelsContent">
					<form:form action="saveHouseBank.mnt" method="POST"
						commandName="HOUSEBANK" id="addhbform">
						<table class="tableGeneral">
							<tr>
								<td colspan="2"><c:forEach var="hbsuccessdup"
										items="${hbsuccessdup}">
										<div class="alert-warning" id="savemessage">
											<strong><spring:message code="label.warning" /></strong>
											<spring:message code="label.housebank" />
											<spring:message code="label.duplicateCheck"></spring:message>
										</div>
									</c:forEach></td>
							</tr>

							<form:hidden path="housebankhide" />

							<tr>
								<td><spring:message code="label.horg" /><span
									class="required">*</span></td>
								<td><form:select path="orgid" class="select">
										<form:option value="">--Select--</form:option>
										<form:options items="${orgid}" />
									</form:select></td>


							</tr>
							<tr>
								<td><spring:message code="label.bcode" /><span
									class="required">*</span></td>
								<td><form:input path="bankcode" class="textbox"
										maxlength="10" /></td>


							</tr>
							<tr>
								<td><spring:message code="label.bname" /><span
									class="required">*</span></td>
								<td><form:input path="bankname" class="textbox"
										maxlength="50" /></td>


							</tr>
							<tr>
								<td><spring:message code="label.brname" /><span
									class="required">*</span></td>
								<td><form:input path="branchname" class="textbox"
										maxlength="50" /></td>


							</tr>
							<tr>
								<td><spring:message code="label.addr" /><span
									class="required">*</span></td>
								<td><form:textarea path="address" class="textbox"
										maxlength="50" /></td>


							</tr>
							<tr>
								<td><spring:message code="label.hcity" /><span
									class="required">*</span></td>
								<td><form:input path="city" class="textbox" maxlength="50" /></td>


							</tr>
							<tr>
								<td><spring:message code="label.hstate" /><span
									class="required">*</span></td>
								<td><form:input path="state" class="textbox" maxlength="50" /></td>


							</tr>
							<tr>
								<td><spring:message code="label.hcountry" /><span
									class="required">*</span></td>
								<td><form:select path="country" class="select">
										<form:option value="">--Select--</form:option>
										<form:options items="${countrylist}" />
									</form:select></td>
							</tr>
							<tr>
								<td><spring:message code="label.scode" /><span
									class="required">*</span></td>
								<td><form:input path="swiftcode" class="textbox"
										maxlength="50" /></td>


							</tr>
							<tr>
								<td><spring:message code="label.ifsccode" /><span
									class="required">*</span></td>
								<td><form:input path="ifsccode" class="textbox"
										maxlength="50" /></td>


							</tr>
							<tr>
								<td colspan="2"><c:choose>
										<c:when test="${save}">
											<input type="submit"
												value="<spring:message code="label.save" />"
												class="btn btn-primary" id="sumbnid" />
										</c:when>
										<c:otherwise>
											<input type="submit" disabled="disabled"
												value='<spring:message code="label.save"/>'
												class="btn btn-danger" id="sumbnid" />
										</c:otherwise>

									</c:choose><input type="reset"
									value="<spring:message code="label.reset" />"
									class="btn btn-primary" /></td>
							</tr>

						</table>


					</form:form>


				</div>
			</div>
			<div id="tabs-1" class="TabbedPanelsContent">
				<div align="left" class="TabbedPanelsContent">
					<c:forEach var="HBValues" items="${editvalues }">
						<form:form action="houseBankUpdate.mnt" method="POST"
							commandName="HOUSEBANK" id="edithbForm">
							<table class="tableGeneral">
								<tr>
									<td colspan="2"><c:forEach var="hbdupedit"
											items="${hbdupedit}">
											<div class="alert-warning" id="savemessage">
												<strong><spring:message code="label.warning" /></strong>
												<spring:message code="label.housebank" />
												<spring:message code="label.duplicateCheck"></spring:message>
											</div>
										</c:forEach></td>
								</tr>
								<form:hidden path="housebankhideedit" />
								<form:hidden path="bankidedit" />
								<tr>
									<td><spring:message code="label.horg" /><span
										class="required">*</span></td>
									<td><form:select path="orgidedit" class="select">
											<form:option value="">--Select--</form:option>
											<form:options items="${orgid}" />
										</form:select></td>


								</tr>
								<tr>
									<td><spring:message code="label.bcode" /><span
										class="required">*</span></td>
									<td><form:input path="bankcodeedit" class="textbox"
											maxlength="10" /></td>


								</tr>
								<tr>
									<td><spring:message code="label.bname" /><span
										class="required">*</span></td>
									<td><form:input path="banknameedit" class="textbox"
											maxlength="50" /></td>


								</tr>
								<tr>
									<td><spring:message code="label.brname" /><span
										class="required">*</span></td>
									<td><form:input path="branchnameedit" class="textbox"
											maxlength="50" /></td>


								</tr>
								<tr>
									<td><spring:message code="label.addr" /><span
										class="required">*</span></td>
									<td><form:textarea path="addressedit" class="textbox"
											maxlength="50" /></td>


								</tr>
								<tr>
									<td><spring:message code="label.hcity" /><span
										class="required">*</span></td>
									<td><form:input path="cityedit" class="textbox"
											maxlength="50" /></td>


								</tr>
								<tr>
									<td><spring:message code="label.hstate" /><span
										class="required">*</span></td>
									<td><form:input path="stateedit" class="textbox"
											maxlength="50" /></td>


								</tr>
								<tr>
									<td><spring:message code="label.hcountry" /><span
										class="required">*</span></td>
									<td><form:select path="countryedit" class="select">
											<form:option value="">--Select--</form:option>
											<form:options items="${countrylist}" />
										</form:select></td>
								</tr>
								<tr>
									<td><spring:message code="label.scode" /><span
										class="required">*</span></td>
									<td><form:input path="swiftcodeedit" class="textbox"
											maxlength="50" /></td>


								</tr>
								<tr>
									<td><spring:message code="label.ifsccode" /><span
										class="required">*</span></td>
									<td><form:input path="ifsccodeedit" class="textbox"
											maxlength="50" /></td>
								</tr>

								<tr>
									<td colspan="2"><c:choose>
											<c:when test="${update}">
												<input type="submit"
													value="<spring:message code="label.update" />"
													class="btn btn-primary" id="updated" />
											</c:when>
											<c:otherwise>
												<td><input type="submit" disabled="disabled"
													value="<spring:message code="label.update"/>"
													class="btn btn-danger" id="updateId" /></td>
											</c:otherwise>

										</c:choose></td>
								</tr>
							</table>
						</form:form>
					</c:forEach>
				</div>
			</div>
		</div>

	</div>
</body>
</html>




