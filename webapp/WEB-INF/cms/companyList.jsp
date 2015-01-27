<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<html>
<head>
    <title>Company List</title>
    
    <sx:head />
    
    <script type="text/javascript">
		function confirmremove(cmid) {
			if (confirm("Are you sure you want to remove this company?")) {
				document.location.href = "/westchase/company/delete.action?delId=" + cmid;
			}
		}
    </script>
</head>
<body>
<h1>Company List</h1>

<p><a href="<s:url action="list"/>">Refresh</a></p>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Company</a></p>

<p class="total"><s:property value="totalCount" /> total results</p>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="first" action="first">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="vendorSearch" value="%{vendorSearch}" />
			<s:param name="mapNo" value="%{mapNo}" />
			<s:param name="orphanedOnly" value="%{orphanedOnly}" />
			<s:param name="outsideDistrict" value="%{outsideDistrict}" />
			<s:param name="employeeRange" value="%{employeeRange}" />
		</s:url>
		<s:a href="%{first}">First</s:a>
		
		<s:url id="prev" action="prev">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="vendorSearch" value="%{vendorSearch}" />
			<s:param name="mapNo" value="%{mapNo}" />
			<s:param name="orphanedOnly" value="%{orphanedOnly}" />
			<s:param name="outsideDistrict" value="%{outsideDistrict}" />
			<s:param name="employeeRange" value="%{employeeRange}" />
		</s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="vendorSearch" value="%{vendorSearch}" />
			<s:param name="mapNo" value="%{mapNo}" />
			<s:param name="orphanedOnly" value="%{orphanedOnly}" />
			<s:param name="outsideDistrict" value="%{outsideDistrict}" />
			<s:param name="employeeRange" value="%{employeeRange}" />
		</s:url> 
		<s:a href="%{next}">Next</s:a>
		
		<s:url id="goToPage" action="goToPage">
			<s:param name="nextPage" value="%{maxPage - 1}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="vendorSearch" value="%{vendorSearch}" />
			<s:param name="mapNo" value="%{mapNo}" />
			<s:param name="orphanedOnly" value="%{orphanedOnly}" />
			<s:param name="outsideDistrict" value="%{outsideDistrict}" />
			<s:param name="employeeRange" value="%{employeeRange}" />
		</s:url>
		<s:a href="%{goToPage}">Last</s:a>
	</s:if>
</div>


<s:form method="list" theme="simple" action="list">
<table class="results">
<thead>
	<tr>
		<th colspan="29" style="text-align: left">
			<label>
				Updated by Me?
				<s:checkbox name="updatedByMe" />
			</label>
			<label>
				Orphaned?
				<s:checkbox name="orphanedOnly" />
			</label>
			<label>
				Outside District?
				<s:checkbox name="outsideDistrict" />
			</label>
			<label>
				Map Number
				<s:textfield name="mapNo" size="4" />
			</label>
		</th>
	</tr>
	<tr>
	    <th><s:textfield name="searchObject.id" size="4"/></th>
	    <th><s:textfield name="searchObject.company" size="10"/></th>
	    
	    <th><s:textfield name="searchObject.stNumber" size="4"/></th>
	    <th><s:textfield name="searchObject.stAddress" size="10"/></th>
	    <th><s:textfield name="searchObject.roomNo" size="4"/></th>
	    <th><s:textfield name="searchObject.city" size="10"/></th>
	    <th><s:textfield name="searchObject.state" size="2"/></th>
	    <th><s:textfield name="searchObject.zipCode" size="5" maxlength="5" /></th>
	    
	    <th><s:textfield name="searchObject.wkPhone" size="10"/></th>
	    <th><s:textfield name="searchObject.faxPhone" size="10"/></th>
		<th><s:textfield name="searchObject.latitude" size="3"/></th>
		<th><s:textfield name="searchObject.longitude" size="3"/></th>
		<th>
			<s:select list="availableCompanyTypes" name="searchObject.companyType.id" 
		    headerKey="" headerValue="" listValue="name" listKey="id" emptyOption="false"/>
		</th>
		<th>
			<s:select list="availableCompanyCodes" name="searchObject.companyCode.code" 
		    headerKey="" headerValue="" listValue="name" listKey="code" emptyOption="false"/>
		</th>
	    <th><s:textfield name="searchObject.website"/></th>
	    
	    <th><s:textfield name="searchObject.owner" size="10"/></th>
	    <th><s:textfield name="searchObject.center" size="10"/></th>
	    <th><s:textfield name="searchObject.hcad" size="10"/></th>
	    <th><s:textfield name="searchObject.naics" size="10"/></th>
	   	<th><select name="vendorSearch">
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>
	    
	    <th><s:textfield name="searchObject.squareFeet" size="4"/></th>
	    <th>
	    	<s:select list="availableClassifications" name="searchObject.classification" 
		    headerKey="" headerValue="" listValue="value" listKey="key" emptyOption="false"/>
	    </th>
	    <th><s:textfield name="searchObject.subClassification" size="10"/></th>
	    <th><s:textfield name="employeeRange" size="3"/></th>
	    <th><s:textfield name="searchObject.notes"/></th>
	    <th><s:textfield name="searchObject.other"/></th>	
	   	<th><sx:datetimepicker name="searchObject.inputDate" displayFormat="MM/dd/yyyy"/></th>
	   	<th><sx:datetimepicker name="searchObject.lastUpdate" displayFormat="MM/dd/yyyy"/></th>
		<th><s:submit value="Search" /></th>
	</tr>

    <tr>

	    <s:url action="sort" id="sortid"><s:param name="orderCol" value="'id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    
	    <s:url action="sort" id="sortcomp"><s:param name="orderCol" value="'company'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortstnum"><s:param name="orderCol" value="'stNumber"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortstaddr"><s:param name="orderCol" value="'stAddress'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	   	<s:url action="sort" id="sortroomno"><s:param name="orderCol" value="'roomNo'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortcity"><s:param name="orderCol" value="'city'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortstate"><s:param name="orderCol" value="'state'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortzip"><s:param name="orderCol" value="'zipCode'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortwkph"><s:param name="orderCol" value="'wkPhone'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortfaxph"><s:param name="orderCol" value="'faxPhone'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortlat"><s:param name="orderCol" value="'latitude'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortlong"><s:param name="orderCol" value="'longitude'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortcomptype"><s:param name="orderCol" value="'companyType.id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortcompcode"><s:param name="orderCol" value="'companyCode.code'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	   	<s:url action="sort" id="sortweb"><s:param name="orderCol" value="'website'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortowner"><s:param name="orderCol" value="'owner'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortcenter"><s:param name="orderCol" value="'center'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sorthcad"><s:param name="orderCol" value="'hcad'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortnaics"><s:param name="orderCol" value="'naics'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortvendor"><s:param name="orderCol" value="'vendor'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	   	<s:url action="sort" id="sortsqft"><s:param name="orderCol" value="'sqareFeet'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:url action="sort" id="sortclass"><s:param name="orderCol" value="'classification'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>	   	
	    <s:url action="sort" id="sortsub"><s:param name="orderCol" value="'subClassification'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    <s:url action="sort" id="sortnoemp"><s:param name="orderCol" value="'noEmployees'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:url action="sort" id="sortnotes"><s:param name="orderCol" value="'notes'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:url action="sort" id="sortother"><s:param name="orderCol" value="'other'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>			    	    	    
    
		<s:url action="sort" id="sortinpt"><s:param name="orderCol" value="'inputDate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortupdt"><s:param name="orderCol" value="'lastUpdate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	    
        <th><s:a href="%{sortid}">Id</s:a></th>
        <th><s:a href="%{sortcomp}">Name</s:a></th>
        <th><s:a href="%{sortstnum}">St Number</s:a></th>
        <th><s:a href="%{sortstaddr}">St Address</s:a></th>
        <th><s:a href="%{sortroomno}">Room No</s:a></th>
        <th><s:a href="%{sortcity}">City</s:a></th>
        <th><s:a href="%{sortstate}">State</s:a></th>
        <th><s:a href="%{sortzip}">Zip Code</s:a></th>
        <th><s:a href="%{sortwkph}">Wk Phone</s:a></th>
        <th><s:a href="%{sortfaxph}">Fax</s:a></th>
        <th><s:a href="%{sortlat}">Latitude</s:a></th>
        <th><s:a href="%{sortlong}">Longitude</s:a></th>
        <th><s:a href="%{sortcomptype}">Company Type</s:a></th>
        <th><s:a href="%{sortcompcode}">Company Code</s:a></th>
        <th><s:a href="%{sortweb}">Website</s:a></th>
        <th><s:a href="%{sortowner}">Owner</s:a></th>
        <th><s:a href="%{sortcenter}">Center</s:a></th>
        <th><s:a href="%{sorthcad}">HCAD</s:a></th>
        <th><s:a href="%{sortnaics}">NAICS</s:a></th>
        <th><s:a href="%{sortvendor}">Vendor</s:a></th>
        <th><s:a href="%{sortsqft}">Square Feet</s:a></th>
        <th><s:a href="%{sortclass}">Classification</s:a></th>
        <th><s:a href="%{sortsub}">Sub Classification</s:a></th>
        <th><s:a href="%{sortnoemp}">No Employees</s:a></th>
        <th><s:a href="%{sortnotes}">Notes</s:a></th>
        <th><s:a href="%{sortother}">Other</s:a></th>
        <th><s:a href="%{sortinpt}">Input Date</s:a></th>
        <th><s:a href="%{sortupdt}">Update Date</s:a></th>
        <th></th>
    </tr>
</thead>
<tbody>
    <s:iterator value="companies" status="status" id="cp">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
		    <td><s:property value="company"/></td>
		    <td><s:property value="stNumber"/></td>
		    <td><s:property value="stAddress"/></td>
		    <td><s:property value="roomNo"/></td>
		    <td><s:property value="city"/></td>
		    <td><s:property value="state"/></td>
		    <td><s:property value="zipCode"/></td>
		    <td><s:property value="wkPhone"/></td>
		    <td><s:property value="faxPhone"/></td>
            <td><s:property value="latitude" default=""/></td>
            <td><s:property value="longitude" default=""/></td>
            <td><s:property value="companyType.name"/></td>
            <td><s:property value="companyCode.name"/></td>
		    <td><a href='http://<s:property value="website"/>' target="_blank"><s:property value="website"/></a></td>
		    <td><s:property value="owner"/></td>
		    <td><s:property value="center"/></td>
		    <td><s:property value="hcad"/></td>
		    <td><s:property value="naics"/></td>
		    <td><s:property value="vendor"/></td>
		    <td><s:property value="squareFeet"/></td>
		    <td><s:property value="classification"/></td>
		    <td><s:property value="subClassification"/></td>
		    <td><s:property value="noEmployees"/></td>
		    <td><s:property value="notes" /></td>
		    <td><s:property value="other"/></td>
            <td><s:date name="inputDate" format="MM/dd/yyyy" /></td>
            <td><s:date name="lastUpdate" format="MM/dd/yyyy" /></td>
            <td>
            	<a href="<s:url action="edit-%{id}" />">Edit</a>
            	<a href="javascript:confirmremove(${cp.id})">Delete</a>
            </td>
        </tr>
    </s:iterator>
</tbody>
</table>    
    <s:submit value="Search" />
</s:form>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prev">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="vendorSearch" value="%{vendorSearch}" />
			<s:param name="mapNo" value="%{mapNo}" />
			<s:param name="orphanedOnly" value="%{orphanedOnly}" />
			<s:param name="outsideDistrict" value="%{outsideDistrict}" />
			<s:param name="employeeRange" value="%{employeeRange}" />
		</s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="vendorSearch" value="%{vendorSearch}" />
			<s:param name="mapNo" value="%{mapNo}" />
			<s:param name="orphanedOnly" value="%{orphanedOnly}" />
			<s:param name="outsideDistrict" value="%{outsideDistrict}" />
			<s:param name="employeeRange" value="%{employeeRange}" />
		</s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Company</a></p>

</body>
</html>