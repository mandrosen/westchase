<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<script type="text/javascript" src="<s:url value='/scripts/gs_sortable.js' encode='false' includeParams='none'/>"></script>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	var TSort_Data = new Array('results_table', 'i', 's', 'i', 's');
	tsRegister();
	</script>
</head>

<body>

<h1>Flag Pole Properties</h1>

<s:form name="selectForm" action="flagPoleContacts" id="frm" theme="simple">
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
		<th>Map No</th> 
		<th>Building Name</th> 
		<th>Geo Number</th> 
		<th>Geo Address</th> 
		<th>Contact</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
		    <td><s:property value="mapNo"/></td>
		    <td><s:property value="buildingName"/></td>
		    <td><s:property value="geoNumber"/></td>
		    <td><s:property value="geoAddress"/></td>
		    <td><s:property value="firstName" /> <s:property value="lastName" /> - <s:property value="wkPhone" /></td>
		</tr>
	</s:iterator>
</tbody>
</table>   

</body>

</html>