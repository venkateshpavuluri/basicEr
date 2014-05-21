<!-- author pvenkateswarlu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->

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
						
						$('#sumbnid')
								.click(	function(event) {
											
											$("#material")
													.validate(
															{
																rules : {
																	materialCode : {
																		required : true,
																		alphanumeric :true,
																		specialcharacters :true
																		
																	},
																	materialName : {
																		required : true,
																		alphabets :true,
																		specialcharacters :true
																		
																	},
																	
																	materialCategory : {
																		required : true
																	},
																	materialType : {
																		required : true
																	},
																	
																	shelfLifeUOM : {
																		required : true
																	},
																	uom : {
																		required : true
																	},
																	avgVolume : {
																		number : true
																	},
																	
																	shelfLife : {
																		required : true,
																		number : true
																	},
																	reorderLevel : {
																		required : true,
																		number : true
																	},
																	maxDeliveryTime : {
																		number : true
																	},
																	maxDeliveryTimeUOM : {
																		number : true
																	},
																	avgLength : {
																		number : true
																	},
																	avgWeight : {
																		number : true
																	},
																	avgHeight : {
																		number : true
																	}
																},
																messages : {
																	materialCode : {
																		
																		required: "<font style='color: red;'><b>Material Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>",
																	},
																	materialName : 
																		{
																		required : "<font style='color: red;'><b>Material Name is Required.</b></font>",
																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>",
																		},
																	materialCategory : "<font style='color: red;'><b>Material Category is Required</b></font>",
																	materialType : "<font style='color: red;'><b>Material Type is Required</b></font>",
									
																	uom : "<font style='color: red;'><b>UOM is Required</b></font>",
																		avgVolume : "<font style='color: red;'><b>Average Volume Must Be Number</b></font>",
																		dimension : "<font style='color: red;'><b>Dimension Must Be Number</b></font>",
																		shelfLife :
																			{
																			required:"<font style='color: red;'><b>Shelf Life is Required </b></font>",
																			number:"<font style='color: red;'><b>Shelf Life is Must be Number </b></font>"
																			},
																			reorderLevel :
																				{
																				
																			required:"<font style='color: red;'><b>Reorder  Level is Required</b></font>",
																			number:"<font style='color: red;'><b>Reorder  Level is Must Be Number</b></font>"
																				},
																		maxDeliveryTime :"<font style='color: red;'><b>MaxDelivery Time Must Be Number</b></font>",
																		maxDeliveryTimeUOM : "<font style='color: red;'><b>MaxDeliveryTime UOM Must Be Number</b></font>",
																		avgLength : "<font style='color: red;'><b>Average Length Must Be Number</b></font>",
																		avgWeight : "<font style='color: red;'><b>Average Weight Must Be Number</b></font>",
																		avgHeight : "<font style='color: red;'><b>Average Height Must Be Number</b></font>",
																		shelfLifeUOM: "<font style='color: red;'><b>Shelf Life UOM Required </b></font>"

																},
															});
										});
						$('#updateBtn')
								.click(
										function(event) {
											
											$("#materialFormEdit")
													.validate(
															{
																rules : {
																	materialCodeEdit : {
																		required : true,
																		alphanumeric :true,
																		specialcharacters :true
																	
																	},
																	materialNameEdit : {
																		required : true,
																		alphabets :true,
																		specialcharacters :true
																	},
																	materialCategoryEdit : {
																		required : true
																	},
																	
																	materialTypeEdit : {
																		required : true
																	},
																
																	uomEdit : {
																		required : true
																	},avgVolume : {
																		number : true
																	},
																	
																	shelfLifeEdit : {
																		required : true,
																		number : true
																	},
																	shelfLifeUOMEdit : {
																		required : true
																	},
																	reorderLevelEdit : {
																		required : true,
																		number : true
																	},
																	maxDeliveryTimeEdit : {
																		number : true
																	},
																	maxDeliveryTimeUOMEdit : {
																		number : true
																	},
																	avgLengthEdit : {
																		number : true
																	},
																	avgWeightEdit : {
																		number : true
																	},
																	avgHeightEdit : {
																		number : true
																	},
																	avgVolumeEdit : {
																		number : true
																	}

																},
																messages : {
																	materialCodeEdit :{
																		required: "<font style='color: red;'><b>Material Code is Required</b></font>",
																		alphanumeric : "<font style='color: red;'><b>First letter should be an alphanumeric.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>",
																		
																	}, 
																	materialNameEdit : {
																		
																		required : "<font style='color: red;'><b>Material Name is Required.</b></font>",

																		alphabets : "<font style='color: red;'><b>First letter should be an alphabet.</b></font>",
																		specialcharacters : "<font style='color: red;'><b>Special Characters except &,_ are not allowed </b></font>",
																	},
																		materialCategoryEdit:"<font style='color: red;'><b>Material Category is Required</b></font>",
																	materialTypeEdit : "<font style='color: red;'><b>Material Type is Required</b></font>",
																	
																	uomEdit : "<font style='color: red;'><b>UOM is Required</b></font>",
																	avgVolumeEdit : "<font style='color: red;'><b>Average Volume Must Be Number</b></font>",
																	dimensionEdit : "<font style='color: red;'><b>Dimension Must Be Number</b></font>",
																	shelfLifeEdit : 
																		{
																		required:"<font style='color: red;'><b>Shelf Life is Required </b></font>",
																		number:"<font style='color: red;'><b>Shelf Life is Must Be Number</b></font>"
																		},
																		reorderLevelEdit : 
																			{
																			required:"<font style='color: red;'><b>Reorder Level Required and Must Be Number</b></font>",
																		number:"<font style='color: red;'><b>Reorder Level is Must Be Number</b></font>"
																			},
																	maxDeliveryTimeEdit : "<font style='color: red;'><b>MaxDelivery Time Must Be Number</b></font>",
																	maxDeliveryTimeUOMEdit : "<font style='color: red;'><b>MaxDeliveryTime UOM Must Be Number</b></font>",
																	avgLengthEdit : "<font style='color: red;'><b>Average Length Must Be Number</b></font>",
																	avgWeightEdit : "<font style='color: red;'><b>Average Weight Must Be Number</b></font>",
																	avgHeightEdit : "<font style='color: red;'><b>Average Height Must Be Number</b></font>",
																	shelfLifeUOMEdit: "<font style='color: red;'><b>Shelf Life UOM Required</b></font>"
																},
															});

										});

					});
</script>


<script type="text/javascript">
	$(document).ready(function() {
		if (document.getElementById("aid").value == 1) {

			var index = $('#tabs li a').index($('a[href="#tabs-3"]').get(0));

			$('#tabs').tabs({
				active : index
			});
		}

	});
</script>

<script>
	$(function() {
		$("#tabs").tabs();
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


<script type="text/javascript">
	$(document).ready(function() {
		$('#sumbnid').click(function(e) {
			var aid = document.getElementById("aid").value = 1;
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#search,#add').click(function(e) {
			$('#edit').hide();
			$('#successmessage').hide();
			$('#savemessage').hide();

		});
	});
</script>

</head>
<body>
	<div class="divUserDefault">
		<div class="PageTitle">Material</div>
		<div id="tabs" class="TabbedPanels">
			<ul class="TabbedPanelsTabGroup">
				<c:forEach var="materialValues" items="${materialValues}">
					<li class="TabbedPanelsTab"><a href="#tabs-1" id="edit"><s:message
								code="label.edit"></s:message> </a></li>

				</c:forEach>
				<li class="TabbedPanelsTab"><a href="#tabs-2" id="search"><s:message
							code="label.search"></s:message></a></li>
				<li class="TabbedPanelsTab"><a href="#tabs-3" id="add"><s:message
							code="label.add"></s:message></a></li>
			</ul>
			<div id="tabs-2" class="TabbedPanelsContent">
				<div align="left">
					<!-- ============================================Begin MaterialSearch ======================================================= -->

					<form:form action="materialSearch.mnt" method="get"
						commandName="materialAdd">
						<table class="tableGeneral">
							<tr>

								<td colspan="3"><c:forEach items="${param.list}">
										<div class="alert-success" id="savemessage">
											<strong><s:message code="label.success"/> </strong>
											<s:message code="label.material"/> <s:message code="label.saveSuccess"/>
										
										</div>
									</c:forEach><c:forEach items="${param.listw}">
										<div class="alert-danger" id="savemessage">
											<strong><s:message code="label.error"/> </strong>
											<s:message code="label.material"/> <s:message code="label.saveFail"/>
										</div>
									</c:forEach>
									<c:forEach 
										items="${materialUpadte}">
										<div class="alert-success" id="successmessage">
										<strong><s:message code="label.success"/> </strong>
											<s:message code="label.material"/> <s:message code="label.updateSuccess"/>
										</div>
									</c:forEach>
									
									
									<c:forEach 
										items="${materialUpadteError}">
										<div class="alert-danger" id="successmessage">
										<strong><s:message code="label.error"/> </strong>
											<s:message code="label.material"/> <s:message code="label.updateFail"/>
										</div>
									</c:forEach>
									
									<c:forEach 
										items="${materialDelete}">
										<div class="alert-success" id="successmessage">
										<strong><s:message code="label.success"/> </strong>
											<s:message code="label.material"/> <s:message code="label.deleteSuccess"/>
										</div>
									</c:forEach>
									
										<c:forEach 
										items="${materialDeleteError}">
										<div class="alert-danger" id="successmessage">
										<strong><s:message code="label.error"/> </strong>
											<s:message code="label.material"/> <s:message code="label.deleteFail"/>
										</div>
									</c:forEach>
									
									</td>
							</tr>
							<%-- <tr>
								<td><s:message code="label.materialCode"></s:message></td>

								<td><form:select path="material_Id" class="select">
										<form:option value="0">--Select--</form:option>
										<form:option value="0" class="select">All</form:option>
										<form:options items="${materialSearchIds}" />
									</form:select></td>

								<td><input type="submit"
									value="<s:message code="label.search"/>"
									class="btn btn-primary" "/></td>
							</tr>
 --%>
							<tr>
								<td><s:message code="label.searchby" /></td>
								<td><form:select path="xmlLabel" cssClass="select">

										<form:options items="${xmlItems}" />
									</form:select> <s:bind path="materialAdd.operations">
								<select class="select" name="operations">
								<option value="<s:message code='assignedOperator'/>"><s:message code="label.equalsTo"/> </option>
								<option value="<s:message code='notequalsOperator'/>"><s:message code="label.notEqualsTo"/> </option>
							 <option value="<s:message code='beginsWithOperator'/>"> <s:message code="label.beginsWith"/> </option> 
								<option value="<s:message code='endsWithOperator'/>"><s:message code="label.endsWith"/> </option>
								<option value="<s:message code='containsOperator'/>"><s:message code="label.contains"/></option>
								</select>
									 </s:bind>  <form:input path="basicSearchId" cssClass="textbox" /></td>
									  <c:set var="save" value="false"></c:set>
                                   <c:set var="edit" value="false"></c:set>
                                   <c:set var="delete" value="false"></c:set>
                                   <c:set var="update" value="false"></c:set>
                                   <c:set var="search" value="false"></c:set>
								<fmt:setBundle basename="button" />
								<fmt:message key="label.save" var="messagesav" />
								<fmt:message key="label.search" var="messagesea" />
								<fmt:message key="label.delete" var="messagedel" />
								<fmt:message key="label.update" var="messageup" />
									<fmt:message key="label.edit" var="messageed" />
								<c:forEach items="${sessionScope.privilegeList}"
									var="privileges">
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
								</c:forEach>
								<c:choose>
								<c:when test="${search }">
								
								
								<td><input type="submit"
									value="<s:message code="label.search"/>"
									class="btn btn-primary" /></td>
								</c:when>
								<c:otherwise>
								<td><input type="submit" disabled="disabled"
									value="<s:message code="label.search"/>"
									class="btn btn-primary" /></td>
								</c:otherwise>
								</c:choose>
							</tr>

						</table>
					</form:form>
					<!-- ============================================End Search ====================================== -->
					<c:forEach var="materialSearch" items="${materialSearch}">
						<c:set var="g" value="${materialSearch}"></c:set>
					</c:forEach>
					<c:if test="${g!=null}">
						<!-- ============================================Begin DisplayTable ================================================= -->
						<display:table id="materialRow" name="materialSearch"
							requestURI="materialSearch.mnt" pagesize="5" class="table">

							<display:column property="materialCodeName"
								titleKey="label.materialCode" sortable="true"></display:column>
							<display:column property="materialName"
								titleKey="label.materialName" sortable="true" />
							<display:column property="materialCategoryName"
								titleKey="label.materialCategory" sortable="true" />
							<display:column property="uomName" titleKey="label.uom"
								media="html" sortable="true" />
							<display:column property="materialTypeName"
								titleKey="label.materialType" sortable="true" />
							<display:column titleKey="label.edit" style="color:white">
							<c:choose>
							<c:when test="${edit}">
							
						
								<a
									href="materialEditHome.mnt?materialCodeEdit=<c:out value="${materialRow.material_Id}"/>"
									style="color: red"><img src="images/Edit.jpg" width="20px"
									height="20px" /> </a>	</c:when>
									<c:otherwise>
									<a><img src="images/Edit.jpg" width="20px"
									height="20px" /> </a>
									</c:otherwise>
							</c:choose>
							</display:column>

							<display:column titleKey="label.delete">
							<c:choose>
							<c:when test="${delete }">
								<a href="materialDelete.mnt?materialCodeDelete=<c:out value="${materialRow.material_Id}"/>"
									style="color: red"
									onclick="return confirm('Do u want to Delete The Record?')"><img
									src="images/Delete.jpg" width="20px" height="20px" /></a></c:when>
									<c:otherwise>
									<a ><img
									src="images/Delete.jpg" width="20px" height="20px" /></a>
									</c:otherwise>
									
							</c:choose>
							</display:column>
							<display:setProperty name="paging.banner.placement"
								value="bottom" />

							<display:setProperty name="paging.banner.group_size" value="3" />

							<display:setProperty name="paging.banner.some_items_found"
								value="<span class='pagebanner'>{0} {1},listing {2} to {3}. </span>" />
						</display:table>
					</c:if>
					<c:set var="f" value="${materialSearchNoData}"></c:set>
					
					<c:if test="${f!=null}">
						<c:out value="${f}"></c:out>
					</c:if>

				</div>
			</div>
			<!-- ============================================End DisplayTable ================================================= -->

			<div id="tabs-3" class="TabbedPanelsContent">
				<div align="left" class="TabbedPanelsContent">


					<!--=================== ============================================Begin MaterialAdd================================================= -->

					<form:form action="materialAdd.mnt" id="material" name="material"
						method="POST" commandName="materialAdd">

						<table class="tableGeneral">
							<tr>
								<td colspan="2"><c:forEach var="a"
										items="${materialDuplicate }">
										<div class="alert-warning">
										<strong><s:message code="label.warning"/></strong>
										<s:message code="label.materialName"/> <s:message code="label.duplicateCheck"/>
										</div>
									</c:forEach></td>
							</tr>
							<form:hidden path="aid" />


							<tr>
								<td><s:message code="label.materialCode" /><font
									color="red">*</font></td>
								<td><form:input path="materialCode" id="materialCode"
										class="textbox" maxlength="20"/></td>
									<td><s:message code="label.avgVolume" /></td>
									<td><form:input path="avgVolume" id="avgVolume"
										class="textbox" /></td>	
										
										
							</tr>

							<tr>
								<td><s:message code="label.materialName" /><font
									color="red">*</font></td>
								<td><form:input path="materialName" id="materialName"
										class="textbox" maxlength="50"/></td>
										<td><s:message code="label.weightUOM" /></td>
									<td><form:select path="weightUOM" id="weightUOM"
										class="select" >
										<form:option value="">--Select--</form:option>
										<form:options  items="${uom}"/></form:select>
										
										</td>	
							</tr>
							<tr>
								<td><s:message code="label.materialDescription"></s:message></td>
								<td><form:textarea path="materialDescription"
										id="materialDescription" class="textbox" maxlength="250"/></td>
										<td><s:message code="label.heightUOM" /></td>
									<td><form:select path="heightUOM" id="heightUOM"
										class="select" >
										<form:option value="0">--Select--</form:option>
												<form:options  items="${uom}"/></form:select>
										
										</td>	
										
							</tr>
							<tr>
								<td><s:message code="label.materialCategory"/><font
									color="red">*</font></td>
								<td><form:select path="materialCategory"
										id="materialCategory" class="select">
										<form:option value="">--Select--</form:option>

										<form:options items="${materialCategory}" />
									</form:select> </td>
									
										<td><s:message code="label.lengthUOM" /></td>
									<td><form:select path="lengthUOM" id="lengthUOM"
										class="select" >
										<form:option value="">--Selcet--</form:option>
												<form:options  items="${uom}"/></form:select>
										
										</td>
							</tr>
							<tr>
								<td><s:message code="label.materialType"/><font
									color="red">*</font></td>
								<td><form:select path="materialType" class="select">
										<form:option value="">--Select--</form:option>
										<form:options items="${materialType}" />
									</form:select>
								</td>
								
									<td><s:message code="label.dimension" /></td>
									<td><form:input path="dimension" id="dimension"
										class="textbox" maxlength="50"/></td>
							</tr>
							<tr>
								<td><s:message code="label.taxCategory"/></td>
								<td><form:select path="taxCatogery" class="select">
										<form:option value="">--Select--</form:option>
										<form:options items="${taxCategory}" />
									</form:select>
								</td>
									<td><s:message code="label.shelfLife" /><font
									color="red">*</font></td>
									<td><form:input path="shelfLife" id="shelfLife"
										class="textbox" maxlength="50"/></td>
								
							</tr>
							<form:hidden path="aid" id="aid" />
							<tr>
								<td><s:message code="label.uom"/><font
									color="red">*</font></td>
								<td><form:select path="uom" class="select">
										<form:option value="">--Select--</form:option>
										<form:options items="${uom}" />
									</form:select></td>
									
										<td><s:message code="label.shelfLifeUOM" /><font
									color="red">*</font></td>
									<td><form:select path="shelfLifeUOM" id="shelfLifeUOM"
										class="select" >
										<form:option value="">--Select--</form:option>
										<form:options items="${uom}" /></form:select>
										</td>
										
									
							</tr>
							<tr><td><s:message code="label.active"/></td>
							<td><form:select path="active" cssClass="select"> 
							
							<form:option value="0">true</form:option>
							<form:option value="1">false</form:option></form:select>
							</td>
							
							<td><s:message code="label.reorderLevel" /><font
									color="red">*</font></td>
									<td><form:input path="reorderLevel" id="reorderLevel"
										class="textbox" maxlength="50"/></td>
							
							</tr>
							<tr><td><s:message code="label.salesUOM"/></td>
							<td><form:select path="salesUOM" cssClass="select">
							<form:option value="">--Select--</form:option>
							<form:options items="${uom }" /></form:select>
							
							 </td>
							
								<td><s:message code="label.maxDeliveryTime" /></td>
									<td><form:input path="maxDeliveryTime" id="maxDeliveryTime"
										class="textbox" maxlength="50"/></td>
							</tr>
							<tr><td><s:message code="label.purchaseUOM"/></td>
							<td><form:select path="purchaseUOM" cssClass="select">
								<form:option value="">--Select--</form:option>
							<form:options items="${uom }" /></form:select>
							 </td>
							
								<td><s:message code="label.maxDeliveryTimeUOM" /></td>
									<td><form:select path="maxDeliveryTimeUOM" id="maxDeliveryTimeUOM"
										class="select" >
											<form:option value="">--Select--</form:option>
							<form:options items="${uom }" />
										</form:select>
										</td>
							</tr>
							<tr><td><s:message code="label.avgWeight"/></td>
							<td><form:input path="avgWeight" id="avgWeight" cssClass="textbox" maxlength="50"/> </td>
							
								<td><s:message code="label.alternateUOM" /></td>
									<td><form:select path="alternateUOM" id="alternateUOM"
										class="select" >
										<form:option value="">--Select--</form:option>
							              <form:options items="${uom }" /></form:select>
										</td>
							</tr>
							<tr><td><s:message code="label.avgHeight"/></td>
							<td><form:input path="avgHeight" id="avgHeight" cssClass="textbox" maxlength="50"/> </td>
							
							<td><s:message code="label.avgLength"/></td>
							<td><form:input path="avgLength" id="avgLength" cssClass="textbox" maxlength="50"/> </td>
							</tr>
							<tr>
							<c:choose>
							<c:when test="${save}">
								<td colspan="2"><input type="submit"
									value="<s:message code="label.save"/>" class="btn btn-primary"
									id="sumbnid" /><input type="reset" class="btn btn-primary"
									value="<s:message code="label.reset"/>" /></td></c:when>
									<c:otherwise>
									<td colspan="2"><input type="submit" disabled="disabled"
									value="<s:message code="label.save"/>" class="btn btn-primary"
									id="sumbnid" /><input type="reset" class="btn btn-primary"
									value="<s:message code="label.reset"/>" /></td>
									</c:otherwise>
							</c:choose>
							</tr>

						</table>
					</form:form>
					<!-- ============================================End MaterialAdd==================================================================================== -->
					<!-- </td></tr></table>	 -->

				</div>
			</div>
			<div id="tabs-1" class="TabbedPanelsContent">
				<div align="left" class="TabbedPanelsContent">
					<!-- ============================================Begin MaterialEdit==================================================================================== -->
					<c:forEach var="materialEditValues" items="${materialValues }">
						<form:form action="materialUpdate.mnt" name="materialFormEdit"
							id="materialFormEdit" method="POST" commandName="materialAdd">
							<table class="tableGeneral">
								<tr>
									<td colspan="2"><c:forEach var="duplicate"
											items="${materialEditDuplicate }">
											<div class="alert-warning">
											<strong> <s:message code="label.warning"/> </strong>
											<s:message code="label.materialName"/> <s:message code="label.duplicateCheck"/>
												
											</div>
										</c:forEach></td>
								</tr>
								<form:hidden path="materialIdEdit" />
								<tr>
									<td><s:message code="label.materialCode" /><font
										color="red">*</font></td>
									<td><form:input path="materialCodeEdit"
											id="materialCodeEdit" class="textbox" maxlength="50"/></td>
											<td><s:message code="label.avgVolume" /></td>
									<td><form:input path="avgVolumeEdit" id="avgVolumeEdit"
										class="textbox" /></td>	
								</tr>

								<tr>
									<td><s:message code="label.materialName" /><font
										color="red">*</font></td>
									<td><form:input path="materialNameEdit" id="materialName"
											class="textbox" maxlength="50"/> </td>
											
											<td><s:message code="label.weightUOM" /></td>
									<td><form:select path="weightUOMEdit" id="weightUOMEdit"
										class="select" >
										<form:option value="0">--Select--</form:option>
										<form:options  items="${uom}"/></form:select>
										
										</td>	
								</tr>
								<tr>
								<td><s:message code="label.materialDescription"></s:message></td>
								<td><form:textarea path="materialDescriptionEdit"
										id="materialDescription" class="textbox" maxlength="250"/></td>
										<td><s:message code="label.heightUOM" /></td>
									<td><form:select path="heightUOMEdit" id="heightUOMEdit"
										class="select" >
										<form:option value="0">--Select--</form:option>
												<form:options  items="${uom}"/></form:select>
										
										</td>	
										
							</tr>
								<tr>
									<td><s:message code="label.materialCategory"/><font
									color="red">*</font></td>
									<td><form:select path="materialCategoryEdit"
											id="materialCategory" class="select">
	                                      <form:option value="">--Select--</form:option>
											<form:options items="${materialCategory}" />
										</form:select></td>
										
										<td><s:message code="label.lengthUOM" /></td>
									<td><form:select path="lengthUOMEdit" id="lengthUOMEdit"
										class="select" >
										<form:option value="">--Selcet--</form:option>
												<form:options  items="${uom}"/></form:select>
										
										</td>
										
								</tr>

								<tr>
									<td><s:message code="label.materialType"/><font
									color="red">*</font></td>
									<td><form:select path="materialTypeEdit" class="select">
											<%-- <form:option value="<c:out value='${materialEditValues.materialType}'/>"><c:out value="${materialEditValues.materialTypeName}"/></form:option> --%>
												<form:option value="">--Select--</form:option>
											<form:options items="${materialType}" />
										</form:select></td>
										<td><s:message code="label.dimension" /></td>
									<td><form:input path="dimensionEdit" id="dimensionEdit"
										class="textbox"  maxlength="150"/></td>
								</tr>
								<tr>
									<td><s:message code="label.taxCategory"/></td>
									<td><form:select path="taxCatogeryEdit" class="select">
	                                       <form:option value="">--Select--</form:option>
											<form:options items="${taxCategory}" />
										</form:select></td>
										<td><s:message code="label.shelfLife" /><font
									color="red">*</font></td>
									<td><form:input path="shelfLifeEdit" id="shelfLifeEdit"
										class="textbox" /></td>
										
								</tr>
								<tr>
									<td><s:message code="label.uom"/><font
									color="red">*</font></td>
									<td><form:select path="uomEdit" class="select">
	                                   <form:option value="">--Select--</form:option>
											<form:options items="${uom}" />
										</form:select></td>
										
											<td><s:message code="label.shelfLifeUOM" /><font
									color="red">*</font></td>
									<td><form:select path="shelfLifeUOMEdit" id="shelfLifeUOMEdit"
										class="select" >
										<form:option value="">--Select--</form:option>
										<form:options items="${uom}" /></form:select>
										</td>
								</tr>
								
							<tr><td><s:message code="label.active"/></td>
							<td><form:select path="activeEdit" cssClass="select">
							<form:option value="0">true</form:option>
							<form:option value="1">false</form:option>
							</form:select> </td>
							
							<td><s:message code="label.reorderLevel" /><font
									color="red">*</font></td>
									<td><form:input path="reorderLevelEdit" id="reorderLevelEdit"
										class="textbox" /></td>
							
							</tr>
							<tr><td><s:message code="label.salesUOM"/></td>
							<td><form:select path="salesUOMEdit" cssClass="select">
							<form:option value="0">--Select--</form:option>
							<form:options items="${uom }" /></form:select>
							
							 </td>
							
								<td><s:message code="label.maxDeliveryTime" /></td>
									<td><form:input path="maxDeliveryTimeEdit" id="maxDeliveryTimeEdit"
										class="textbox" /></td>
							</tr>
							<tr><td><s:message code="label.purchaseUOM"/></td>
							<td><form:select path="purchaseUOMEdit" cssClass="select">
								<form:option value="0">--Select--</form:option>
							<form:options items="${uom }" /></form:select>
							 </td>
							
								<td><s:message code="label.maxDeliveryTimeUOM" /></td>
									<td><form:select path="maxDeliveryTimeUOMEdit" id="maxDeliveryTimeUOMEdit"
										class="select" >
											<form:option value="0">--Select--</form:option>
							<form:options items="${uom }" />
										</form:select>
										</td>
							</tr>
							<tr><td><s:message code="label.avgWeight"/></td>
							<td><form:input path="avgWeightEdit" id="avgWeightEdit" cssClass="textbox"/> </td>
							
								<td><s:message code="label.alternateUOM" /></td>
									<td><form:select path="alternateUOMEdit" id="alternateUOM"
										class="select" >
										<form:option value="0">--Select--</form:option>
							              <form:options items="${uom }" /></form:select>
										</td>
							</tr>
							<tr><td><s:message code="label.avgHeight"/></td>
							<td><form:input path="avgHeightEdit" id="avgHeightEdit" cssClass="textbox"/> </td>
							
							<td><s:message code="label.avgLength"/></td>
							<td><form:input path="avgLengthEdit" id="avgLengthEdit" cssClass="textbox"/> </td>
							</tr>

								<tr>
								<c:choose>
								<c:when test="${update}">
									<td colspan="2"><input type="submit"
										value="<s:message code="label.update"/>"
										class="btn btn-primary" id="updateBtn" /><input type="reset"
										value="<s:message code="label.reset"/>"
										class="btn btn-primary" /></td></c:when>
										<c:otherwise>
										<td colspan="2"><input type="submit" disabled="disabled"
										value="<s:message code="label.update"/>"
										class="btn btn-primary" id="updateBtn" /><input type="reset"
										value="<s:message code="label.reset"/>"
										class="btn btn-primary" /></td>
										</c:otherwise>
								</c:choose>
								</tr>
							</table>
						</form:form>
					</c:forEach>
				</div>
				<!-- ============================================End MaterialEdit==================================================================================== -->
			</div>
		</div>
		</div>
</body>
</html>




