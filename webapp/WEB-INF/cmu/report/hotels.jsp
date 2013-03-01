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
	var TSort_Data = new Array('results_table', '', 'i', 'i', 's', 's', 'i', 's', 's', 's', 's', 's');
	tsRegister();
	</script>

</head>

<body>

<h1>Westchase CMU Report - Hotels</h1>

<s:form name="cmuHotelForm" action="hotel" id="frm" theme="simple">
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
<c:set var="totRooms" value="0" />
<c:set var="totOcc" value="0" />
<table class="results" id="results_table">
<thead>
    <tr>
    	<th>Verified</th>
    	<th>Edit</th>
		<th>Map #</th>
		<th>Hotel</th>  
		<th>Address</th>  
		<th># of Rooms</th>  
		<th>Occupancy Rate</th>  
		<th>Occupied Rooms</th>  
		<th>General Manager</th>  
		<th>Email</th>  
		<th>Phone</th>  
		<th>Comments</th>  
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status" id="result">
		<c:set var="totRooms" value="${totRooms +  result.property.noUnits}" />
		<c:set var="occRooms" value="${result.property.noUnits * result.occupancy}" />
		<c:set var="totOcc" value="${totOcc + occRooms}" />
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else> <c:if test="${not empty verified}">verified</c:if>">

            <td><c:if test="${not empty result.verified}"><img src="<s:url value="/images/checkmark.gif" />" alt="Verified" /></c:if></td>
            <td><a href="<s:url action="hotelEdit-%{id}" />"><s:property value="id"/></a></td>
			<td><s:property value="property.id"/></td>
			<td><s:property value="property.buildingName"/></td>
			<td><s:property value="property.geoNumber"/> <s:property value="property.geoAddress" /></td>
			<td><s:property value="property.noUnits" /></td>
			<td><fmt:formatNumber maxFractionDigits="2" value="${occRooms}" /> (<fmt:formatNumber maxFractionDigits="2" value="${result.occupancy}" />%)</td>
			<td><s:property value="generalMgr" /></td>
			<td><s:property value="generalMgrEmail" /></td>
			<td><s:property value="generalMgrPhone" /></td>
			<td><s:property value="comments" /></td>
			
        </tr>
    </s:iterator>
    <tr>
    	<td colspan="5"><strong>Total</strong></td>
    	<td><strong><fmt:formatNumber maxFractionDigits="0" value="${totRooms}" /></strong></td>
    	<td><strong><fmt:formatNumber maxFractionDigits="0" value="${totOcc}" /></strong></td>
    	<td colspan="4"></td>
    </tr>
</tbody>
</table>

</body>
</html>