<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<script type="text/javascript" src="<s:url value='/scripts/gs_sortable.js' encode='false' includeParams='none'/>"></script>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	var TSort_Data = new Array('results_table', 's', 's', 's', 's', 's');
	tsRegister();
	</script>
</head>

<body>

<h1>Email Contact by Category Code</h1>

<s:form name="selectForm" action="emailContactsByCategoryCode" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
	<tr><td>Categories</td>
		<td><s:select label="Category Codes" name="categoryCodes" headerKey="-1" headerValue="-- Please Select --" 
    list="availableCategories" listValue="categoryDesc" listKey="categoryCode" emptyOption="true" 
    multiple="true" size="10" /></td>
	</tr>
    <tr><td>Westchase Only?</td><td><s:checkbox name="westchaseOnly" /></td></tr>
    <tr><td>Employee Count</td><td><s:textfield name="employeeCount" size="4" /></td></tr>
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
		<th>Category</th>
		<th>Email</th> 
		<th>Title</th> 
		<th>First Name</th> 
		<th>Last Name</th> 
		<th>Salutation</th> 
		<th>Company</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
		    <td><s:property value="categoryCode"/></td>
		    <td><s:property value="email"/></td>
		    <td><s:property value="title"/></td>
		    <td><s:property value="firstName"/></td>
		    <td><s:property value="lastName"/></td>
		    <td><s:property value="salutation"/></td>
		    <td><s:property value="company"/></td>
        </tr>
    </s:iterator>
</tbody>
</table>

</body>

</html>