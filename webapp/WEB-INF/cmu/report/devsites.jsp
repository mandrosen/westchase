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
	var TSort_Data = new Array('results_table', '', 'i', 'i', 's', 'f', 's', 's', 's', 's', 's', 's', 's', 's');
	tsRegister();
	</script>

</head>

<body>

<h1>Westchase CMU Report - Development Sites</h1>

<s:form name="cmuDevsiteForm" action="devsite" id="frm" theme="simple">
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

<c:set var="totalAcres" value="0" />
<table class="results" id="results_table">
<thead>
    <tr>
    	<th>Verified</th>
    	<th>Edit</th>
    	<th>Map #</th>
		<th>Location</th>
		<th>Size (Acres)</th>
		<th>Price ($/sq. ft)</th>
		<th>Frontage</th>
		<th>Contact</th>
		<th>Company</th>
		<th>Phone</th>
		<th>Fax</th>
		<th>Restrictions</th>
		<th>Comments</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else> <c:if test="${not empty verified}">verified</c:if>"> 
            <td><c:if test="${not empty verified}"><img src="<s:url value="/images/checkmark.gif" />" alt="Verified" /></c:if></td>
            <td><a href="<s:url action="devsiteEdit-%{id}" />"><s:property value="id"/></a></td>
		    <td><s:property value="property.id" /></td>
			<td><s:property value="property.geoNumber" /> <s:property value="property.geoAddress" /></td>
			<td><s:property value="siteSize" /></td>
			<td><s:property value="priceSqFt" /></td>
			<td><s:property value="frontage" /></td>
			<td><s:property value="contact" /></td>
			<td><s:property value="company" /></td>
			<td><s:property value="phone" /></td>
			<td><s:property value="fax" /></td>
			<td><s:property value="restrictions" /></td>
			<td><s:property value="comments" /></td>
			<c:if test="${not empty siteSize}">
				<c:set var="totalAcres" value="${totalAcres + siteSize}" />
			</c:if>
        </tr>
    </s:iterator>
    <c:if test="${totalAcres > 0}">
    	<tr><td></td><td><strong>TOTAL LAND AVAILABLE</strong></td>
    				 <td><strong><u><c:out value="${totalAcres}" /></u></strong></td>
    		<td colspan="7">&#160;</td></tr>
    </c:if>
</tbody>
</table>

</body>
</html>