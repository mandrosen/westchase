<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Company</title>
    <s:head/>
    
    <script type="text/javascript">
    <c:if test="${not empty currentCompany.id}">
		function confirmremove(cmid) {
			if (confirm("Are you sure you want to remove this property?")) {
				document.location.href = "/westchase/companyProperty/remove-" + cmid + ".action?currentCompanyId=<c:out value='${currentCompany.id}' />";
			}
		}
		function confirmremovethis() {
			if (confirm("Are you sure you want to remove this company?")) {
				document.location.href = "/westchase/company/delete.action?delId=" + <c:out value="${currentCompany.id}" />;
			}
		}
		</c:if>
		function confirmremovephonebook(pbid) {
			if (confirm("Are you sure you want to remove this phone book?")) {
				document.location.href = "/westchase/phonebook/delete.action?delId=" + pbid;
			}
		}
    </script>
</head>

<body>
<c:choose>
<c:when test="${not empty currentCompany.id}">
<h1>Edit Company - <c:out value="${currentCompany.id}" /></h1>
</c:when>
<c:otherwise><h1>New Company</h1></c:otherwise>
</c:choose>

<p><a href="/westchase/company/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>

<s:form name="editForm" action="save">
	<c:if test="${not empty currentCompany.id}">
	    <s:hidden name="currentCompany.id"/>
	</c:if>
    <s:textfield label="Name" name="currentCompany.company" required="true" size="100" maxlength="100" />
    
    <s:textfield label="St Number" name="currentCompany.stNumber" size="50" maxlength="50"/>
    <!-- 
    <s:textfield label="St Address" name="currentCompany.stAddress" required="true" size="75" maxlength="150" />
     -->
    <s:combobox label="St Address" name="currentCompany.stAddress" headerValue="-- Please Select --" headerKey=""
		list="availableStreets" listValue="name" listKey="name"
		    required="true"/>

    
    <s:textfield label="Room No" name="currentCompany.roomNo" size="50" maxlength="50"/>
    <s:textfield label="City" name="currentCompany.city" required="true" size="100" maxlength="100" />
    
    <s:select label="State" list="availableStates" name="currentCompany.state" 
		    headerKey="" headerValue="-- Please Select --" listValue="name" listKey="code" emptyOption="false"
		    required="true"/>
 
    <s:textfield label="Zip Code" name="currentCompany.zipCode" required="true" size="10" maxlength="10" />
	<s:textfield label="Latitude" name="currentCompany.latitude" size="20" />
	<s:textfield label="Longitude" name="currentCompany.longitude" size="20" />
	<s:select label="Company Type" list="availableCompanyTypes" name="currentCompany.companyType.id" 
		    headerKey="" headerValue="-- Please Select --" listValue="name" listKey="id" emptyOption="false"/>
    
    <s:textfield label="Website" name="currentCompany.website" size="75" maxlength="255"/>
    <c:if test="${not empty currentCompany.website}">
    <tr><td></td><td><a href="http://<c:out value='${currentCompany.website}' />" target="_blank">Go to <c:out value='${currentCompany.website}' /></a></td></tr>
    </c:if>
    
    <s:textfield label="Work Phone" name="currentCompany.wkPhone" size="15" maxlength="15" required="true" />
    <s:textfield label="Fax Phone" name="currentCompany.faxPhone" size="15" maxlength="15"/>
    
    <s:textfield label="Owner" name="currentCompany.owner" size="50" maxlength="50"/>
    <s:textfield label="Center" name="currentCompany.center" size="50" maxlength="50"/>
    
    <s:textfield label="HCAD" name="currentCompany.hcad" size="15" maxlength="50"/>
    <tr><td>&#160;</td><td><a href="http://www.hcad.org/" target="_hcad">hcad.org</a></td></tr>

    <s:select label="NAICS" list="availableNaics" name="currentCompany.naics" 
		    headerKey="" headerValue="-- Please Select --" listValue="description" listKey="code" emptyOption="false"/>
    
    <s:select label="Classification" list="availableClassifications" name="currentCompany.classification" 
		    headerKey="" headerValue="-- Please Select --" listValue="value" listKey="key" emptyOption="false"
		    required="true"/>
    <s:select label="Sub Classification" list="availableSubClassifications" name="currentCompany.subClassification" 
		    headerKey="" headerValue="-- Please Select --" listValue="value" listKey="key" emptyOption="false"/>		        
    
    <s:textfield label="Square Feet" name="currentCompany.squareFeet" size="10" maxlength="10"/>
    <s:textfield label="No Employees" name="currentCompany.noEmployees" size="10" maxlength="10"/>
    
    <s:checkbox fieldValue="true" label="Vendor" name="currentCompany.vendor"/>
    <s:textfield label="Notes" name="currentCompany.notes" size="75" maxlength="255" />
    <s:textfield label="Other" name="currentCompany.other" size="75" maxlength="255" />
    

    <s:submit value="Save" />
<c:if test="${not empty currentCompany.id}">
    <tr><td colspan="2">
    <input type="button" value="Delete" onclick="confirmremovethis()" /></td></tr>
    </c:if>
</s:form>

<h3>Properties</h3>
<table class="properties">
	<thead>
	    <tr>
	        <th>Id</th>
	        <th>Building Name</th>
	        <th>GeoNum</th>
	        <th>Address</th>
	        <th>City</th>
	        <th>State</th>
	        <th>Zip</th>
	        <th>Primary?</th>
	        <th>action</th>
	    </tr>
    </thead>
    <tbody>
		<s:iterator value="currentProperties" status="status" id="cp">
			<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
				<c:choose>
					<c:when test="${cp.property.id==-999}">
						<td>999</td>
					</c:when>
					<c:otherwise>
						<td><a href="<s:url action="edit-%{property.id}" namespace="/property" />"><s:property value="property.id"/></a></td>
					</c:otherwise>
				</c:choose>
				<td><s:property value="property.buildingName"/></td>
	            <td><s:property value="property.geoNumber"/></td>
	            <td><s:property value="property.geoAddress"/></td>
	            <td><s:property value="property.geoCity"/></td>
	            <td><s:property value="property.geoState"/></td>
	            <td><s:property value="property.geoZipCode"/></td>
	            <td><s:property value="primary"/></td>
				<td class="actions">
					<a href="<s:url action="selectInit-%{currentCompany.id}?companyPropertyId=%{id}" namespace="/companyProperty" />">Update from current property</a>
					<a href="javascript:confirmremove(<c:out value='${cp.id}' />)">Remove</a>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<c:if test="${not empty currentCompany.id}">
	<a href="<s:url action="selectInit-%{currentCompany.id}" namespace="/companyProperty" />">Add from current property</a>
	<a href="<s:url action="edit-?linkCompanyId=%{currentCompany.id}" namespace="/property" includeParams="none"/>">Add new property</a>
</c:if>

<h3>Phone Book</h3>
<table class="phonebooks">
	<thead>
	    <tr>
	        <th>Id</th>
	        <th>First Name</th>
	        <th>Last Name</th>
	        <th>Job Title</th>
	        <th>Work Phone</th>
	        <th>Email</th>
	        <th>Primary?</th>
	    </tr>
    </thead>
    <tbody>
		<s:iterator value="currentPhoneBooks" status="status" id="pb">
			<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
				<td><a href="<s:url action="edit-%{id}" namespace="/phonebook" />"><s:property value="id"/></a></td>
				<td><s:property value="firstName"/></td>
	            <td><s:property value="lastName"/></td>
	            <td><s:property value="jobTitle"/></td>
	            <td><s:property value="wkPhone"/></td>
	            <td><s:property value="email"/></td>
	            <td><a href="javascript:confirmremovephonebook(${pb.id})">Delete</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<c:if test="${not empty currentCompany.id}">
	<a href="<s:url action="edit-?linkCompanyId=%{currentCompany.id}" namespace="/phonebook" includeParams="none"/>">Add new Phone Book</a>
</c:if>


<p><a href="/westchase/company/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
