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
	var TSort_Data = new Array('results_table', 'i', 's', 'i', 's', 's');
	tsRegister();
	</script>
</head>

<body>

<h1>Trespass Agreement Map No Report</h1>

<s:form name="selectForm" action="trespassAgreementMapNo" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
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
        <th>MapNo</th>
        <th>Building Name</th>
        <th>Geo Number</th>
        <th>Geo Address</th>
        <th>Tresspass Date</th>
    </tr>
</thead>
<tbody>
    <c:forEach items="${results}" var="res" varStatus="status">
        <tr class="<c:if test="${status.index % 2 == 0}">even</c:if><s:else>odd</s:else>">
 
		    <td><c:out value="${res.id}"/></td>
		    <td><c:out value="${res.buildingName}"/></td>
		    <td><c:out value="${res.geoNumber}"/></td>
		    <td><c:out value="${res.geoAddress}"/></td>
		    <td><fmt:formatDate value="${res.trspassDate}" pattern="MM/dd/yyyy" /></td>
        </tr>
    </c:forEach>
</tbody>
</table>

</body>

</html>