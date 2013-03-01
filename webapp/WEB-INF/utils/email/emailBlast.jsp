<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Email Blast</title>
    
    <sx:head />
    <script type="text/javascript">
	function selectall() {
		var selected = 0;
		var restable = document.getElementById("emailres"); 
		var checks = restable.getElementsByTagName("input"); 
		if (checks) {
			for (var i = 0; i < checks.length; i++) {
				checks[i].checked = true;
				selected++;
			}
		}
		document.getElementById("selcount").innerHTML = "" + selected;
	}
	function unselectall() {
		var restable = document.getElementById("emailres"); 
		var checks = restable.getElementsByTagName("input"); 
		if (checks) {
			for (var i = 0; i < checks.length; i++) {
				checks[i].checked = false;
			}
		}
		document.getElementById("selcount").innerHTML = "0";
	}
	function updateSelectedCount() {
		var selected = 0;
		var tbl = document.getElementById("emailres");
		var chkboxes = tbl.getElementsByTagName("input");
		if (chkboxes) {
			for (var i = 0; i < chkboxes.length; i++) {
				if (chkboxes[i].checked) {
					selected++;
				}
			}
		}
		document.getElementById("selcount").innerHTML = "" + selected;
	}
    </script>
</head>
<body>

<h1>Email Blast</h1>

<div class="actionmessages">
	<s:actionmessage />
</div>

<p>Use the form below to find email addresses.</p>

<s:form action="emailBlastSearch" theme="simple">
	<table>
		<tr><td>Categories</td>
			<td><s:select label="Category Codes" name="selectedCategories" headerKey="-1" headerValue="-- Please Select --" 
	    			list="availableCategories" listValue="categoryDesc" listKey="categoryCode" emptyOption="true" 
	    			multiple="true" size="10" /></td>
	    </tr>
	    <tr><td>NAICS Codes</td>
	    	<td><s:select label="NAICS Codes" name="selectedNaics" headerKey="-1" headerValue="-- Please Select --" 
	    			list="availableNaics" listValue="description" listKey="code" emptyOption="true" 
	    			multiple="true" size="10" /></td></tr>
	    <tr><td>Westchase</td>
	    	<td><select name="westchaseTodaySearch"/>
			   		<option value=""></option>
			   		<option value="true">true</option>
			   		<option value="false">false</option>
			   	</select></td></tr>
		<tr><td>Email?</td>
			<td><select name="dontEmailSearch">
			   		<option value="false">true</option>
			   		<option value="true">false</option>
			   	</select></td></tr>
		<tr><td>Block/Street</td>
			<td><s:textfield name="block" size="5" maxlength="50" />
				<s:textfield name="street" size="20" maxlength="150" /></td></tr>
	    <tr><td colspan="2"><s:submit value="Search" /></td></tr>
    </table>
</s:form>

<s:form action="emailBlastSelect" theme="simple">
<table class="results" id="emailres">
	<tr>
		<th>Select</th>
		<th>Email</th> 
		<th>Last Name</th> 
		<th>First Name</th> 
		<th>Salutation</th> 
		<th>Company</th> 
		<th>St Number</th> 
		<th>St Address</th> 
		<th>Room No</th> 
		<th>City</th> 
		<th>State</th> 
		<th>ZipCode</th> 
	</tr>
    <s:iterator value="contacts" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 			<td>
 				<c:if test="${not empty email}"><input type="checkbox" name="selectedPeople" value="<c:out value='${id}' />" onclick="updateSelectedCount()" /></c:if>
 			</td>
		    <td><s:property value="email"/></td>
		    <td><s:property value="lastName"/></td>
		    <td><s:property value="firstName"/></td>
		    <td><s:property value="salutation"/></td>
		    <td><s:property value="company"/></td>
		    <td><s:property value="stNumber"/></td>
		    <td><s:property value="stAddress"/></td>
		    <td><s:property value="roomNo"/></td>
		    <td><s:property value="city"/></td>
		    <td><s:property value="state"/></td>
		    <td><s:property value="zipCode"/></td>		    
        </tr>
    </s:iterator>
</table>
<p><span id="selcount">0</span> selected</p>
<a href="javascript:selectall()">Select All</a>
<a href="javascript:unselectall()">Unselect All</a>
<s:submit value="Submit" />
</s:form>

</body>

</html>