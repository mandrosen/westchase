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
	var TSort_Data = new Array('results_table', '','s','i','i','s','s','s','s','s','s','g','i','g','g','s','s','s','s','s');
	tsRegister();
	</script>

</head>

<body>

<h1>Westchase CMU Report - Service Centers</h1>

<s:form name="cmuServiceCtrForm" action="serviceCtr" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<s:hidden name="businessTypeId" />
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

<c:set var="total" value="0" />
<c:set var="totalSize" value="0" />
<c:set var="occRate" value="0" />
<table class="results" id="results_table">
<thead>
    <tr>
    	<th>Verified</th>
    	<th>Correct?</th>
    	<th>Edit</th>
		<th>Map #</th>
		<th>Building Name</th>  
		<th>Address</th>  
		<th>Leasing Agent</th>  
		<th>Contact Company</th> 
		<th>Leasing Agent Email</th>   
		<th>Phone</th>
		<th>Size (sq. ft.)</th>
		<th>Occupancy Rate</th>
		<th>Largest Contiguous (sq. ft.)</th>
		<th>Occupied (sq. ft.)</th>
		<th>Building Manager</th>
		<th>Mgmt Company</th>
		<th>Manager Email</th>
		<th>Phone</th>
		<th>Comments</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status" id="result">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else> <c:if test="${not empty verified}">verified</c:if>">
 
 			<c:set var="total" value="${total + 1}" />
 			<c:set var="totalSize" value="${totalSize + result.property.buildingSize}" />
 			<c:set var="occRate" value="${occRate + result.occupancy}" />
 			
            <td><c:if test="${not empty result.verified}"><img src="<s:url value="/images/checkmark.gif" />" alt="Verified" /></c:if></td>
            <td><c:if test="${not staticInfoCorrect}">NO</c:if></td>
            <td><a href="<s:url action="officeRetailSvcEdit-%{id}-3" />"><s:property value="id"/></a></td>
			<td><s:property value="property.id" /></td>
			<td><s:property value="property.buildingName" /></td>
			<td><s:property value="property.geoNumber" /> <s:property value="property.geoAddress" /></td>
			<td><s:property value="leasingAgent" /></td>
			<td><s:property value="leasingCompany" /></td>
			<td><s:property value="leasingAgentEmail" /></td>
			<td><s:property value="leasingAgentPhone" /></td>
			
			<td><s:property value="sqFtForLease" /></td>
			<td><fmt:formatNumber maxFractionDigits="2" value="${result.occupancy}" />%</td>
			<td><s:property value="largestSpace" /></td>
			<c:choose>
				<c:when test="${not empty(result.sqFtForLease) and result.sqFtForLease gt 0 and not empty(result.occupancy) and result.occupancy gt 0}">
					<c:choose>
						<c:when test="${result.occupancy lt 100}">
							<td><c:out value="${result.sqFtForLease * result.occupancy / 100}" /></td>
						</c:when>
						<c:otherwise>
							<td><c:out value="${result.sqFtForLease}" /></td>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
				<td>0</td>
				</c:otherwise>
			</c:choose>
			
			<td><s:property value="propertyMgr" /></td>
			<td><s:property value="mgmtCompany" /></td>
			<td><s:property value="propertyMgrEmail" /></td>
			<td><s:property value="propertyMgrPhone" /></td>
			<td><s:property value="comments" /></td>
        </tr>
    </s:iterator>
    <c:if test="${total > 0}">
	    <tr>
	    	<td><strong><c:out value="${total}" /></strong></td>
	    	<td colspan="9"><strong>TOTAL</strong></td>
	    	<td><strong><c:out value="${totalSize}" /></strong></td>
	    	<td><strong><fmt:formatNumber maxFractionDigits="2" value="${occRate / total * 100 }" />%</strong></td>
	    	<td colspan="7">&#160;</td>
	    </tr>
    </c:if>
    
</tbody>
</table>

</body>
</html>
