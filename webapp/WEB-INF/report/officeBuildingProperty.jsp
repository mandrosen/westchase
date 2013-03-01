<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<script type="text/javascript" src="<s:url value='/scripts/gs_sortable.js' encode='false' includeParams='none'/>"></script>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	var TSort_Data = new Array('results_table', 'i', 's', 's', 's', 's', 's', 's', 'd', 'i');
	tsRegister();
	</script>

</head>

<body>

<h1>Office Building Property Report</h1>

<s:form name="selectForm" action="officeBuildingProperty" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
	<tr><td>Square Foot</td>
		<td><s:textfield label="Square Foot" name="squareFootage"/></td>
    </tr>
	<tr><td>Occupancy</td>
		<td><s:textfield label="Occupancy" name="occupancy"/> <span class="format">between 0 and 1</span></td>
    </tr>
    <tr><td>Westchase Only?</td><td><s:checkbox name="westchaseOnly" /></td></tr>
    <tr><td colspan="2"><s:submit value="Run" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Export to Excel" onclick="exportreport('excel')" /></td></tr>
    <tr><td colspan="2"><hr /></td></tr>
    <tr><td>Email Addresses</td><td><s:textarea rows="3" cols="40" name="emailAddresses" /></td></tr>
    <tr><td>Subject</td><td><s:textfield name="emailSubject" size="60" maxlength="155" /></td></tr>
    <tr><td>Message</td><td><s:textarea rows="3" cols="40" name="emailMessage" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Email Report" onclick="exportreport('email')" /></td></tr>
    </table>
</s:form>

<table class="results" id="results_table">
<thead>
    <tr>
		<th>Mapno</th> 
		<th>Property</th> 
		<th>First Name</th> 
		<th>Last Name</th> 
		<th>Email</th> 
		<th>Phone</th> 
		<th>Address</th> 
		<th>Occupancy</th> 
		<th>Square Foot</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status" id="result">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
		    <td><s:property value="property.id"/></td>
		    <td><s:property value="property.buildingName"/></td>
		    <td><s:property value="phoneBook.firstName"/></td>
		    <td><s:property value="phoneBook.lastName"/></td>
		    <td><s:property value="phoneBook.email"/></td>
		    <td><s:property value="phoneBook.wkPhone"/> <c:if test="${not empty result.phoneBook.wkext}"><s:property value="phoneBook.wkext"/></c:if></td>
		    <td><s:property value="property.geoNumber" /> <s:property value="property.geoAddress" />, <s:property value="property.geoZipCode" /></td>
		    <td><s:property value="property.occupancyRate" /></td>
		    <td><s:property value="property.buildingSize" /></td>
        </tr>
    </s:iterator>
</tbody>
</table>

</body>
</html>