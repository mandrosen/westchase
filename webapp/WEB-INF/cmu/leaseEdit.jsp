<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Edit Westchase CMU Lease</title>

</head>

<body>

<h1>Westchase CMU Report - Lease Edit</h1>

<p><a href="leaseQuarter-<c:out value='${cmuLease.cmuQuarter.id}' />.action">Back to Leases</a></p>
<c:if test="${!empty officeRetailSvcId}">
	<p><a href="officeRetailSvcEdit-<c:out value='${officeRetailSvcId}' />-0.action">Back to Office/Retail/Svc</a></p>
</c:if>

<s:form name="cmuLeaseForm" action="leaseSave">
	<s:hidden name="cmuLease.id"/>
	<s:hidden name="cmuLease.property.id"/>
	<s:hidden name="cmuLease.cmuQuarter.id"/>
	<!--
	<s:hidden name="cmuLease.cmuTransactionType.id"/>
	-->
	<s:hidden name="officeRetailSvcId"/>
	
	<tr>
		<th>Property</th>
		<td>#<s:property value="cmuLease.property.id" />: <s:property value="cmuLease.property.buildingName" /></td>
	</tr>
	<tr>
		<th>Quarter</th>
		<td><s:property value="cmuLease.cmuQuarter.year" />-<s:property value="cmuLease.cmuQuarter.quarterNum" /></td>
	</tr>	
	
	<s:checkbox label="Verified" name="verified" />
	
	<s:select label="Transaction Type" name="cmuLease.cmuTransactionType.id" list="cmuTransactionTypeList"
		emptyOption="false" listValue="description" listKey="id" />
	
	<s:textfield label="Tenant Name" name="cmuLease.tenantName" />
	
	<s:textfield label="Sq. Ft." name="cmuLease.sqFt" size="10" maxlength="20" />
	<s:textfield label="Owners Rep" name="cmuLease.ownersRep" size="100" maxlength="255" />
	<s:textfield label="Tenants Rep" name="cmuLease.tenantsRep" size="100" maxlength="255" />
	
	<s:submit />
</s:form>

<p><a href="leaseQuarter-<c:out value='${cmuLease.cmuQuarter.id}' />.action">Back to Leases</a></p>

</body>
</html>