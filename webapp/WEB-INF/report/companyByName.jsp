<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<script type="text/javascript" src="<s:url value='/scripts/gs_sortable.js' encode='false' includeParams='none'/>"></script>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	var TSort_Data = new Array('results_table', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'i');
	tsRegister();
	</script>

</head>

<body>

<h1>Company by Name Report</h1>

<s:form name="selectForm" action="companyByName" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
	<tr><td>Company Name</td>
		<td><s:textfield label="Company Name" name="companyName"/></td>
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
		<th>Title</th>
		<th>First Name</th> 
		<th>Last Name</th> 
		<th>Job Title</th> 
		<th>Company</th> 
		<th>St Number</th> 
		<th>St Address</th> 
		<th>Room No</th> 
		<th>City</th> 
		<th>State</th> 
		<th>ZipCode</th> 
		<th>Email</th> 
		<th>Wk Phone</th> 
		<th>FaxPhone</th> 
		<%-- <th>Map No</th> --%> 
		<th>Category</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
		    <td><s:property value="phoneBook.title"/></td>
		    <td><s:property value="phoneBook.firstName"/></td>
		    <td><s:property value="phoneBook.lastName"/></td>
		    <td><s:property value="phoneBook.jobTitle"/></td>
		    <td><s:property value="company.company"/></td>
		    <td><s:property value="company.stNumber"/></td>
		    <td><s:property value="company.stAddress"/></td>
		    <td><s:property value="company.roomNo"/></td>
		    <td><s:property value="company.city"/></td>
		    <td><s:property value="company.state"/></td>
		    <td><s:property value="company.zipCode"/></td>
		    <td><s:property value="phoneBook.email"/></td>
		    <td><s:property value="phoneBook.wkPhone"/></td>
		    <td><s:property value="phoneBook.faxPhone"/></td>
		    <%-- <td></td> --%>
		    <td><s:property value="categories"/></td>
        </tr>
    </s:iterator>
</tbody>
</table>

</body>
</html>