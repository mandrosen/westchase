<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<script type="text/javascript" src="<s:url value='/scripts/gs_sortable.js' encode='false' includeParams='none'/>"></script>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	var TSort_Data = new Array('results_table', '', 'i', 's', 's', 's', 'f', 's', 's');
	tsRegister();
	</script>

</head>

<body>

<h1>Westchase CMU Report - Leases</h1>

<s:form name="cmuLeaseForm" action="lease" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
	<tr><td>Quarter</td>
		<td>
			<s:select label="Quarter" name="quarterId" headerKey="-1" headerValue="-- Please Select --" 
			    list="cmuQuarters" listValue="description" listKey="id" emptyOption="false" 
			    multiple="false" />
		</td>
    </tr>
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
    	<th>Verified</th>
    	<th>Edit</th>
		<th>Type</th>
		<th>Building</th>
		<th>Tenant Name</th>
		<th>Sq. Ft.</th>
		<th>Type</th>
		<th>Tenant's Rep</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status" id="result">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else> <c:if test="${not empty verified}">verified</c:if>"> 
            <td><c:if test="${not empty verified}"><img src="<s:url value="/images/checkmark.gif" />" alt="Verified" /></c:if></td>
            <td><a href="<s:url action="leaseEdit-%{id}" />"><s:property value="id"/></a></td>
        	<td>
        		<c:choose>
        			<c:when test="${result.property.businessType eq 'Office'}">OF</c:when>
        			<c:when test="${result.property.businessType eq 'Service Center'}">SC</c:when>
        			<c:when test="${result.property.businessType eq 'Retail Center'}">R</c:when>
        		</c:choose>
        	</td>
		    <td><s:property value="property.buildingName" /></td>
			<td><s:property value="tenantName" /></td>
			<td><s:property value="sqFt" /></td>
			<td><s:property value="cmuTransactionType.description" /></td>
			<td><s:property value="tenantsRep" /></td>

        </tr>
    </s:iterator>
</tbody>
</table>

</body>
</html>