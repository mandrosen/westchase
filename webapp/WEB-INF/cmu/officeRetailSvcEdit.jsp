<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Edit Westchase CMU Office Retail Svc</title>

</head>

<body>

<h1>Westchase CMU Report - Office Retail Svc Edit</h1>

<c:choose>
	<c:when test="${businessTypeId eq 1}">
		<p><a href="officeQuarter-<c:out value='${cmuOfficeRetailSvc.cmuQuarter.id}' />.action">Back to Offices</a></p>
	</c:when>
	<c:when test="${businessTypeId eq 2}">
		<p><a href="retailQuarter-<c:out value='${cmuOfficeRetailSvc.cmuQuarter.id}' />.action">Back to Retail Centers</a></p>
	</c:when>
	<c:when test="${businessTypeId eq 3}">
		<p><a href="serviceCtrQuarter-<c:out value='${cmuOfficeRetailSvc.cmuQuarter.id}' />.action">Back to Service Centers</a></p>
	</c:when>
</c:choose>

<s:form name="cmuOfficeRetailSvcForm" action="officeRetailSvcSave">
	<s:hidden name="cmuOfficeRetailSvc.id"/>
	<s:hidden name="cmuOfficeRetailSvc.property.id"/>
	<s:hidden name="cmuOfficeRetailSvc.cmuQuarter.id"/>
	<s:hidden name="businessTypeId" />
	
	<tr>
		<th>Property</th>
		<td>#<s:property value="cmuOfficeRetailSvc.property.id" />: <s:property value="cmuOfficeRetailSvc.property.buildingName" /></td>
	</tr>
	<tr>
		<th>Quarter</th>
		<td><s:property value="cmuOfficeRetailSvc.cmuQuarter.year" />-<s:property value="cmuOfficeRetailSvc.cmuQuarter.quarterNum" /></td>
	</tr>	
	
	<s:checkbox label="Verified" name="verified" />
	
	<s:textfield label="Completed By" name="cmuOfficeRetailSvc.completedBy" />
	
	
	<s:checkbox label="For Sale" name="cmuOfficeRetailSvc.forSale" />
	<s:textfield label="For Sale Contact" name="cmuOfficeRetailSvc.forSaleContact" size="100" maxlength="255" />
	<s:textfield label="For Sale Phone" name="cmuOfficeRetailSvc.forSalePhone" size="20" maxlength="20" />
	
	<s:textfield label="Sq. Ft. For Lease" name="cmuOfficeRetailSvc.sqFtForLease" size="10" maxlength="20" />
	<s:textfield label="Occupancy" name="cmuOfficeRetailSvc.occupancy" size="10" maxlength="20" />
	
	<s:textfield label="Largest Space" name="cmuOfficeRetailSvc.largestSpace" size="10" maxlength="20" />
	<s:textfield label="Largest Space 6 Months" name="cmuOfficeRetailSvc.largestSpace6mths" size="10" maxlength="20" />
	<s:textfield label="Largest Space 12 Months" name="cmuOfficeRetailSvc.largestSpace12mths" size="10" maxlength="20" />
	
	<s:textfield label="Property Manager" name="cmuOfficeRetailSvc.propertyMgr" size="100" maxlength="255" />
	<s:textfield label="Property Manager Phone" name="cmuOfficeRetailSvc.propertyMgrPhone" size="20" maxlength="20" />
	<s:textfield label="Property Manager Fax" name="cmuOfficeRetailSvc.propertyMgrFax" size="20" maxlength="20" />
	<s:textfield label="Property Manager Email" name="cmuOfficeRetailSvc.propertyMgrEmail" size="100" maxlength="255" />
	
	<s:textfield label="Mgmt Company" name="cmuOfficeRetailSvc.mgmtCompany" size="100" maxlength="255" />
	<s:textfield label="Mgmt Company Address" name="cmuOfficeRetailSvc.mgmtCompanyAddr" size="100" maxlength="255" />
	
	<s:textfield label="Leasing Company" name="cmuOfficeRetailSvc.leasingCompany" size="100" maxlength="255" />
	<s:textfield label="Leasing Company Address" name="cmuOfficeRetailSvc.leasingCompanyAddr" size="100" maxlength="255" />
	
	<s:textfield label="Leasing Agent" name="cmuOfficeRetailSvc.leasingAgent" size="100" maxlength="255" />
	<s:textfield label="Leasing Agent Phone" name="cmuOfficeRetailSvc.leasingAgentPhone" size="20" maxlength="20" />
	<s:textfield label="Leasing Agent Fax" name="cmuOfficeRetailSvc.leasingAgentFax" size="20" maxlength="20" />
	<s:textfield label="Leasing Agent Email" name="cmuOfficeRetailSvc.leasingAgentEmail" size="100" maxlength="255" />
	
	<s:textarea label="Comments" name="cmuOfficeRetailSvc.comments" rows="5" cols="60" />
	
	<s:submit />
</s:form>


<p><a href="<s:url action="leaseAdd-%{cmuOfficeRetailSvc.id}" />">Add a Lease</a></p>
<c:if test="${not empty cmuLeaseList}">
	<table style="width: 90%;">
		<thead>
			<tr>
				<th>Id</th>
				<th>Type</th>
				<th>Tenant</th>
				<th>Sq. Ft.</th>
				<th>Owners Rep</th>
				<th>Tenants Rep</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="cmuLeaseList" status="status" id="lease">
				<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else> <c:if test="${not empty verified}">verified</c:if>">
					<td><a href="<s:url action="leaseEdit-%{id}-%{cmuOfficeRetailSvc.id}" />"><s:property value="id"/></a></td>
					<td><s:property value="cmuTransactionType.description"/></td>
			        <td><s:property value="tenantName"/></td>
			        <td><s:property value="sqFt"/></td>
			        <td><s:property value="ownersRep"/></td>
			        <td><s:property value="tenantsRep"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</c:if>

<c:choose>
	<c:when test="${businessTypeId eq 1}">
		<p><a href="officeQuarter-<c:out value='${cmuOfficeRetailSvc.cmuQuarter.id}' />.action">Back to Offices</a></p>
	</c:when>
	<c:when test="${businessTypeId eq 2}">
		<p><a href="retailQuarter-<c:out value='${cmuOfficeRetailSvc.cmuQuarter.id}' />.action">Back to Retail Centers</a></p>
	</c:when>
	<c:when test="${businessTypeId eq 3}">
		<p><a href="svcCenterQuarter-<c:out value='${cmuOfficeRetailSvc.cmuQuarter.id}' />.action">Back to Service Centers</a></p>
	</c:when>
</c:choose>

</body>
</html>