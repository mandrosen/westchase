<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<script type="text/javascript" src="<s:url value='/scripts/gs_sortable.js' encode='false' includeParams='none'/>"></script>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	var TSort_Data = new Array('results_table', 's', 'i', 's', 's', 's', 's', 's', 's', 's', 'i', 's', 's', 's', 's', 's', 's', 's', 's', 's');
	tsRegister();
	
	$(function () {
	    $('.select-all').click(function () {
	        $('#results_table').find(':checkbox').attr('checked', 'checked');
	    });
	    $('.unselect-all').click(function () {
	    	$('#results_table').find(':checkbox').removeAttr('checked');
	    });
	});
	</script>
</head>

<body>

<h1>Contact by Category Code</h1>

<s:form name="selectForm" action="contactsByCategoryCode" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
	<tr><td>Categories</td>
		<td><s:select label="Category Codes" name="categoryCodes" headerKey="-1" headerValue="-- Please Select --" 
    list="availableCategories" listValue="categoryDesc" listKey="categoryCode" emptyOption="true" 
    multiple="true" size="10" /></td>
    </tr>
    <tr><td>Capitalize Address?</td><td><s:checkbox name="fixAddressCaps" /></td></tr>
    <tr><td>Westchase Only?</td><td><s:checkbox name="westchaseOnly" /></td></tr>
    <tr><td>Employee Count</td><td><s:textfield name="employeeCount" size="4" /></td></tr>
    <tr><td colspan="2"><s:submit value="Run" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Export to Excel" onclick="exportreport('excel')" /></td></tr>
    <tr><td colspan="2"><hr /></td></tr>
    <tr><td>Email Addresses</td><td><s:textarea rows="3" cols="40" name="emailAddresses" /></td></tr>
    <tr><td>Subject</td><td><s:textfield name="emailSubject" size="60" maxlength="155" /></td></tr>
    <tr><td>Message</td><td><s:textarea rows="3" cols="40" name="emailMessage" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Email Report" onclick="exportreport('email')" /></td></tr>
    <tr><td colspan="2">&#160;</td></tr>
    <tr><td colspan="2"><input type="button" value="Print Labels" onclick="exportreport('labels')" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Print 2x3 Labels" onclick="exportreport('labels23')" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Print 2x3 WD Logo Labels" onclick="exportreport('labels23wdlogo')" /></td></tr>
    <tr><td colspan="2"><input type="button" value="Print Nametags" onclick="exportreport('nametags')" /></td></tr>
    </table>

<table class="results" id="results_table">
<thead>
    <tr>
    	<th>Inc</th>
		<th>Category</th>
		<th>Last Name</th> 
		<th>First Name</th> 
		<th>Salutation</th> 
		<th>Title</th>
		<th>Job Title</th> 
		<th>Company</th> 
		<th>Naics</th> 
		<th>No Employees</th> 
		<th>St Number</th> 
		<th>St Address</th> 
		<th>Room No</th> 
		<th>City</th> 
		<th>State</th> 
		<th>ZipCode</th> 
		<th>Email</th> 
		<th>Mobile Phone</th>
		<th>Work Phone</th>
		<th>Fax Phone</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status" var="result">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
 			<td><s:checkbox name="includedContacts" fieldValue="%{id}" /></td>
		    <td><s:property value="categoryCode"/></td>
		    <td><a href="/westchase/phonebook/edit-${result.id}.action" target="_pb${result.id}"><s:property value="lastName"/></a></td>
		    <td><s:property value="firstName"/></td>
		    <td><s:property value="salutation"/></td>
		    <td><s:property value="title"/></td>
		    <td><s:property value="jobTitle"/></td>
		    <td><s:property value="company"/></td>
		    <td><s:property value="naics"/></td>
		    <td><s:property value="noEmployees"/></td>
		    <td><s:property value="stNumber"/></td>
		    <td><s:property value="stAddress"/></td>
		    <td><s:property value="roomNo"/></td>
		    <td><s:property value="city"/></td>
		    <td><s:property value="state"/></td>
		    <td><s:property value="zipCode"/></td>
		    <td><s:property value="email"/></td>
		    <td><s:property value="mobilePhone"/></td>
		    <td><s:property value="wkPhone"/></td>
		    <td><s:property value="faxPhone"/></td>
        </tr>
    </s:iterator>
</tbody>
</table>

<s:if test="%{results.size() > 0}">
<p class="checkbox-controls"><a onclick="selectAll()" class="select-all">Select All</a><a onclick="unselectAll()" class="unselect-all">Unselect All</a></p>
</s:if>

</s:form>

</body>

</html>