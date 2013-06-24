<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Property</title>
    <sx:head />
    <script type="text/javascript">
    function updateFlagSizeDisabled() {
    	if (typeof $("#flag-pole-id:checked").val() === "undefined") {
    		$("#flag-pole-size").attr("disabled", "disabled");
    	} else {
    		$("#flag-pole-size").removeAttr("disabled");
    	}
    }
    </script>
</head>

<body>
<c:choose>
<c:when test="${not empty currentProperty.id}">
<h1>Edit Property - <c:out value="${currentProperty.id}" /></h1>
</c:when>
<c:otherwise><h1>New Property</h1></c:otherwise>
</c:choose>

<p><a href="/westchase/property/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>

<s:form name="editForm" action="save">
	<c:if test="${not empty linkCompanyId}">
	    <s:hidden name="linkCompanyId"/>
	    <s:checkbox label="Primary Property" name="linkPrimary" />
	</c:if>
	<c:choose>
	<c:when test="${not empty currentProperty.id}">
	    <s:hidden name="currentProperty.id"/>
	</c:when>
	<c:otherwise>
		<s:textfield label="Id (mapno)" name="currentProperty.id"/>
	</c:otherwise>
	</c:choose>
	<s:textfield label="Building Name" name="currentProperty.buildingName" required="true" size="50" maxlength="50"/>
	<s:textfield label="Geo Num" name="currentProperty.geoNumber" size="10" maxlength="10" required="true"/>
	<!--
	<s:textfield label="Geo Address" name="currentProperty.geoAddress" size="30" maxlength="30" required="true"/>
	-->
    <s:combobox label="St Address" name="currentProperty.geoAddress" headerValue="-- Please Select --" headerKey=""
		list="availableStreets" listValue="name" listKey="name"
		    required="true"/>
		
	<s:textfield label="Geo City" name="currentProperty.geoCity" size="25" maxlength="25" required="true"/>
	
	<s:select label="Geo State" list="availableStates" name="currentProperty.geoState" 
		    headerKey="" headerValue="-- Please Select --" listValue="name" listKey="code" emptyOption="false"
		    required="true"/>
	
	<s:textfield label="Geo Zip Code" name="currentProperty.geoZipCode" size="10" maxlength="10" required="true"/>
	<s:textfield label="Latitude" name="currentProperty.latitude" size="10" />
	<s:textfield label="Longitude" name="currentProperty.longitude" size="10" />
	<s:select label="Property Type" list="availablePropertyTypes" name="currentProperty.propertyType.id" 
		    headerKey="" headerValue="-- Please Select --" listValue="name" listKey="id" emptyOption="false"/>
	         
	<s:textfield label="Building Size" name="currentProperty.buildingSize" size="10" maxlength="10"/>
	<s:textfield label="Acreage" name="currentProperty.acreage" size="20" maxlength="20"/>
    
    <s:select label="Business Type" list="availableBusinessTypes" name="currentProperty.businessType" 
		    headerKey="" headerValue="-- Please Select --" listValue="value" listKey="key" emptyOption="false"
		    required="true"/>
		    	
	<s:textfield label="Center" name="currentProperty.center" size="50" maxlength="50"/>
	<s:textfield label="Commercial Space Fore" name="currentProperty.commercialSpcFore" size="10" maxlength="10"/>
	<s:textfield label="Commercial Space Fore 1yr" name="currentProperty.commSpcFore1yr" size="10" maxlength="10"/>
	<s:textfield label="Facet" name="currentProperty.facet" size="50" maxlength="50"/>
	<s:checkbox label="Flag Pole" name="currentProperty.flagPole" id="flag-pole-id" onchange="updateFlagSizeDisabled()"/>    
    <s:select label="Flag Size" list="availableFlagSizes" name="currentProperty.flagSize.id" id="flag-pole-size"
		    headerKey="" headerValue="-- Please Select --" listValue="name" listKey="id" emptyOption="false"
		    required="false" disabled="%{!currentProperty.flagPole}" />

	<s:checkbox label="For Sale" name="currentProperty.forSale" />
	
	<s:textfield label="Frontage" name="currentProperty.frontage" size="50" maxlength="50"/>
	<s:textfield label="HCAD" name="currentProperty.hcad" size="50" maxlength="50"/>
    <tr><td>&#160;</td><td><a href="http://www.hcad.org/" target="_hcad">hcad.org</a></td></tr>	
	<s:textfield label="Largest Vacancy" name="currentProperty.largestVacancy" size="10" maxlength="10" />

	<s:textfield label="No Units" name="currentProperty.noUnits" size="10" maxlength="10"/>
	
	<s:textfield label="Occupancy Rate" name="currentProperty.occupancyRate" size="10" maxlength="10"/>
	<s:textfield label="Occupancy Sq Ft" name="currentProperty.occupiedSqFt" size="10" maxlength="10"/>
	<s:textfield label="Owner" name="currentProperty.owner" size="50" maxlength="50" required="true" />
	<%--
	<s:textfield label="Person" name="currentProperty.phoneBook.company.company"/>
	--%>
	<s:textfield label="Price sq Ft" name="currentProperty.priceSqFt" size="50" maxlength="50"/>
	<s:textfield label="Restrictions" name="currentProperty.restrictions" size="50" maxlength="50"/>
	<s:checkbox label="Single Tenant" name="currentProperty.singleTenant"/>
	<s:checkbox label="Tresspass Afdvt" name="currentProperty.trpassAfdvt"/>
	<sx:datetimepicker label="Tresspass Date (yyyy-MM-dd)" name="currentProperty.trspassDate" displayFormat="MM/dd/yyyy"/>
	<s:textfield label="Vacant Units" name="currentProperty.vacantUnits" size="10" maxlength="10"/>
	
	<s:checkbox label="Will Divide" name="currentProperty.willDivide"/>
	<s:textfield label="Year Built" name="currentProperty.yearBuilt" size="5" maxlength="5"/>
    
    <s:textfield label="Deleted" name="currentProperty.deleted"/>
    
	<s:textfield label="Notes" name="currentProperty.notes"/>
	
    <s:submit value="Save" />
</s:form>

<h3>Companies</h3>
<table class="companies">
	<thead>
	    <tr>
	        <th>Id</th>
	        <th>Name</th>
	        <th>St. Number</th>
	        <th>Address</th>
	        <th>Room No.</th>
	        <th>City</th>
	        <th>State</th>
	        <th>Zip Code</th>
	        <th>Phone</th>
	        <th>Fax</th>
	        <th>Website</th>
	    </tr>
    </thead>
    <tbody>
		<s:iterator value="currentCompanies" status="status">
			<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
				<td><a href="<s:url action="edit-%{id}" namespace="/company" />"><s:property value="id"/></a></td>
			    <td><s:property value="company"/></td>
			    <td><s:property value="stNumber"/></td>
			    <td><s:property value="stAddress"/></td>
			    <td><s:property value="roomNo"/></td>
			    <td><s:property value="city"/></td>
			    <td><s:property value="state"/></td>
			    <td><s:property value="zipCode"/></td>
			    <td><s:property value="wkPhone"/></td>
			    <td><s:property value="faxPhone"/></td>
			    <td>
			    	<c:if test="${not empty website}"><a href='http://<s:property value="website"/>' target="_blank"><s:property value="website"/></a></c:if>
			    </td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<p><a href="/westchase/property/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
