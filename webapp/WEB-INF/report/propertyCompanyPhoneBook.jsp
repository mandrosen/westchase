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
	var TSort_Data = new Array('results_table', 'i', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's');
	tsRegister();
	</script>

</head>

<body>

<h1>Property Company PhoneBook Report</h1>

<s:form action="propertyCompanyPhoneBook" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
	<tr>
		<td>Mapno</td>
		<td><s:textfield label="Mapno" name="propertyId"/></td>
    </tr>
    <tr><td colspan="2"><input type="button" value="Run" onclick="exportreport('')" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Export to Excel" onclick="exportreport('excel')" /></td></tr>
    </table>
</s:form>

<table class="results" id="results_table">
<thead>
    <tr>
		<th>Mapno</th> 
		<th>Company</th> 
		<th>First Name</th> 
		<th>Last Name</th>
		<th>Address</th> 
		<th>Room No</th>
		<th>City</th> 
		<th>State</th> 
		<th>Zip</th>  
		<th>Phone</th>  
		<th>Email</th> 
		<th>Cats</th> 
    </tr>
</thead>
<tbody>
	
	<c:set var="lastPropertyId" value="0" />
	
    <s:iterator value="results" status="status" id="result">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
		    <td>
	    		<c:if test="${result.propertyId ne lastPropertyId}">
	    			<s:property value="propertyId"/>
	    			<c:set var="lastPropertyId" value="${result.propertyId}" />
	    		</c:if>
		    </td>
		    <td><s:property value="company"/></td>
		    <td><s:property value="firstName"/></td>
		    <td><s:property value="lastName"/></td>
		    <td><s:property value="address"/></td>
		    <td><s:property value="roomNo"/></td>
		    <td><s:property value="city"/></td>
		    <td><s:property value="state"/></td>
		    <td><s:property value="zipCode"/></td>
		    <td><s:property value="wkPhone"/></td>
		    <td><s:property value="email"/></td>
		    <td><s:property value="cats"/></td>
		    
        </tr>
    </s:iterator>
</tbody>
</table>

</body>
</html>