<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<head>
	<script type="text/javascript" src="<s:url value='/scripts/gs_sortable.js' encode='false' includeParams='none'/>"></script>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	var TSort_Data = new Array('results_table', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's');
	tsRegister();
	</script>
</head>

<body>

<h1>Contacts by NAICS</h1>

<s:form name="selectForm" action="wcBusinessContactsByNAICS" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
    <tr><td>NAICS Codes</td><td><s:select label="NAICS Codes" name="naicsCodes" headerKey="-1" headerValue="-- Please Select --" 
    list="availableNaics" listValue="description" listKey="code" emptyOption="true" 
    multiple="true" size="10" /></td></tr>
    <tr><td>Capitalize Address?</td><td><s:checkbox name="fixAddressCaps" /></td></tr>
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
		<th>Last Name</th> 
		<th>First Name</th> 
		<th>Title</th> 
		<th>Job Title</th> 
		<th>Company</th> 
		<th>St Number</th> 
		<th>St Address</th>
		<th>Room No</th>
		<th>Zip Code</th>
		<th>Email</th>
		<th>Wk Phone</th>
		<th>Category Code</th>
		<th>Classification</th>
		<th>NAICS</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
		    <td><s:property value="lastName"/></td>
		    <td><s:property value="firstName"/></td>
		    <td><s:property value="title"/></td>
		    <td><s:property value="jobTitle"/></td>
		    <td><s:property value="company"/></td>
		    <td><s:property value="stNumber"/></td>
		    <td><s:property value="stAddress"/></td>
		    <td><s:property value="roomNo"/></td>
		    <td><s:property value="zipCode"/></td>
		    <td><s:property value="email"/></td>
		    <td><s:property value="wkPhone"/></td>
		    <td><s:property value="categoryCode"/></td>
		    <td><s:property value="classification"/></td>
		    <td><s:property value="naics"/></td>
        </tr>
    </s:iterator>
</tbody>
</table>

</body>

</html>