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
	var TSort_Data = new Array('results_table', '', 'i', 'h', 'i', 's', 'i', 'h', 'h', 'h', 's');
	tsRegister();
	</script>

</head>

<body>

<h1>Westchase CMU Report - Apartments</h1>

<s:form name="cmuApartmentForm" action="apartment" id="frm" theme="simple">
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
		<th>Name &amp; Address</th>
		<th># of Units</th>  
		<th>Occupance Rate</th>  
		<th>Available Units</th>  
		<th>Manager</th>  
		<th>Managment Co</th>  
		<th>Owner</th>  
		<th>Comments</th>  
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status" id="result">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else> <c:if test="${not empty verified}">verified</c:if>">

            <td><c:if test="${not empty verified}"><img src="<s:url value="/images/checkmark.gif" />" alt="Verified" /></c:if></td>
            <td><a href="<s:url action="apartmentEdit-%{id}" />"><s:property value="id"/></a></td>
		    <td>
		    	<s:property value="property.buildingName"/><br/>
		    	<s:property value="property.geoNumber"/>&#160;<s:property value="property.geoAddress"/><br/>
		    	<s:property value="property.geoCity"/>,&#160;<s:property value="property.geoState"/>&#160;<s:property value="property.geoZipCode"/><br/>
		    	<strong>Map #<s:property value="property.id"/></strong>
		    </td>
			<td><s:property value="property.noUnits"/></td>
			<td><fmt:formatNumber maxFractionDigits="2" value="${result.occupancyRate}" />%</td>
			<td><fmt:formatNumber maxFractionDigits="0" value="${(100 - result.occupancyRate) * result.property.noUnits / 100}" /></td>
			<td>
				<s:property value="communityMgr" /><br/>
				<s:property value="communityMgrEmail" /><br/>
				<s:property value="communityMgrPhone" />
				<c:if test="${not empty result.communityMgrFax}">
					<br>(f)<s:property value="communityMgrFax" />
				</c:if>
			</td>
			<td>
				<s:property value="mgmtCompany" /><br/>
				<s:property value="supervisor" /><br/>
				<s:property value="mgmtCompanyAddr" /><br/>
				<s:property value="supervisorEmail" /><br/>
				<s:property value="supervisorPhone" />
				<c:if test="${not empty result.supervisorFax}">
					<br>(f)<s:property value="supervisorFax" />
				</c:if>
			</td>
			<td>
				<s:property value="owner" /><br/>
				<s:property value="ownerAddress" /><br/>
				<s:property value="ownerPhone" />
				<c:if test="${not empty result.ownerFax}">
					<br>(f)<s:property value="ownerFax" />
				</c:if>
			</td>
			<td><s:property value="comments" /></td>
        </tr>
    </s:iterator>
</tbody>
</table>

</body>
</html>