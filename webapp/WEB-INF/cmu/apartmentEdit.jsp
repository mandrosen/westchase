<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Edit Westchase CMU Apartment</title>

</head>

<body>

<h1>Westchase CMU Report - Apartment Edit</h1>

<p><a href="apartmentQuarter-<c:out value='${cmuApartment.cmuQuarter.id}' />.action">Back to Apartments</a></p>

<s:form name="cmuApartmentForm" action="apartmentSave">
	<s:hidden name="cmuApartment.id"/>
	<s:hidden name="cmuApartment.property.id"/>
	<s:hidden name="cmuApartment.cmuQuarter.id"/>
	
	<tr>
		<th>Property</th>
		<td>#<s:property value="cmuApartment.property.id" />: <s:property value="cmuApartment.property.buildingName" /></td>
	</tr>
	<tr>
		<th>Quarter</th>
		<td><s:property value="cmuApartment.cmuQuarter.year" />-<s:property value="cmuApartment.cmuQuarter.quarterNum" /></td>
	</tr>	
	
	<s:checkbox label="Verified" name="verified" />
	
	<s:textfield label="Completed By" name="cmuApartment.completedBy" size="100" maxlength="255" />
	<s:textfield label="Occupancy Rate" name="cmuApartment.occupancyRate" size="5" maxlength="20" />
	
	<!-- <s:textfield label="Community Manager" name="cmuApartment.communityMgr" size="100" maxlength="255"/>
	<s:textfield label="Community Manager Email" name="cmuApartment.communityMgrEmail" size="100" maxlength="255" />
	<s:textfield label="Community Manager Phone" name="cmuApartment.communityMgrPhone" size="20" maxlength="20" />
	<s:textfield label="Community Manager Fax" name="cmuApartment.communityMgrFax" size="20" maxlength="20" />
	
	<s:textfield label="Mgmt Company" name="cmuApartment.mgmtCompany" size="100" maxlength="255" />
	<s:textfield label="Mgmt Company Address" name="cmuApartment.mgmtCompanyAddr" size="100" maxlength="255" />
	
	<s:textfield label="Supervisor" name="cmuApartment.supervisor" size="100" maxlength="255" />
	<s:textfield label="Supervisor Email" name="cmuApartment.supervisorEmail" size="100" maxlength="255" />
	<s:textfield label="Supervisor Phone" name="cmuApartment.supervisorPhone" size="20" maxlength="20" />
	<s:textfield label="Supervisor Fax" name="cmuApartment.supervisorFax" size="20" maxlength="20" />
	
	<s:textfield label="Owner" name="cmuApartment.owner" size="100" maxlength="255" />
	<s:textfield label="Owner Address" name="cmuApartment.ownerAddress" size="100" maxlength="255" />
	<s:textfield label="Owner Phone" name="cmuApartment.ownerPhone" size="20" maxlength="20" />
	<s:textfield label="Owner Fax" name="cmuApartment.ownerFax" size="20" maxlength="20" />-->
	
	<s:textarea label="Comments" name="cmuApartment.comments" rows="5" cols="60" />
	
	<s:submit />
</s:form>

<p><a href="apartmentQuarter-<c:out value='${cmuApartment.cmuQuarter.id}' />.action">Back to Apartments</a></p>

</body>
</html>