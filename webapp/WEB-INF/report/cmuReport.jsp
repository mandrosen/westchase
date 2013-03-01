<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	</script>
</head>

<body>

<h1>CMU Report</h1>

<s:form name="selectForm" action="cmuReport" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
	<tr><td>Categories</td>
		<td><s:select label="Category Codes" name="categoryCodes" headerKey="-1" headerValue="-- Please Select --" 
    list="availableCategories" listValue="categoryDesc" listKey="categoryCode" emptyOption="true" 
    multiple="true" size="10" /></td>
    </tr>
	<tr><td>Business Type</td>
		<td><s:textfield name="businessType" size="30" /></td>
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

<table class="results">
<thead>
    <tr>
		<th>Map No</th>
		<th>Building Name</th> 
		<th>Geo Address</th> 
		<th>Company Name</th> 
		<th>Wk Phone</th> 
		<th>Fax Phone</th> 
		<th>Building Size</th> 
    </tr>
</thead>
<tbody>
    <s:iterator value="results" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
		    <td><s:property value="property.id"/></td>
		    <td><s:property value="property.buildingName"/></td>
		    <td><s:property value="property.geoAddress"/></td>
		    <td><s:property value="company.company"/></td>
		    <td><s:property value="company.wkPhone"/></td>
		    <td><s:property value="company.faxPhone"/></td>
		    <td><s:property value="property.buildingSize"/></td>

        </tr>
    </s:iterator>
</tbody>
</table>

</body>
</html>