<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Property List</title>
    
    <sx:head />
</head>
<body>
<h1>Property List</h1>

<p><a href="<s:url action="list"/>">Refresh</a></p>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Property</a></p>

<p class="total"><s:property value="totalCount" /> total results</p>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="first" action="first"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{first}">First</s:a>
		
		<s:url id="prev" action="prev"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
		
		<s:url id="goToPage" action="goToPage"><s:param name="nextPage" value="%{maxPage - 1}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{goToPage}">Last</s:a>
	</s:if>
</div>


<s:form method="list" theme="simple" action="list">
<table class="results">
<thead>
	<tr>
		<th colspan="37" style="text-align: left">
			Updated by Me?
			<s:checkbox name="updatedByMe" />
		</th>
	</tr>
	<tr>
		<th><s:textfield name="searchObject.id" size="4"/></th>
		<th><s:textfield name="searchObject.buildingName" size="10"/></th>
		<th><s:textfield name="searchObject.geoNumber" size="4"/></th>
		<th><s:textfield name="searchObject.geoAddress" size="10"/></th>
		<th><s:textfield name="searchObject.geoCity" size="10"/></th>
		<th><s:textfield name="searchObject.geoState" size="2"/></th>
		<th><s:textfield name="searchObject.geoZipCode" size="10"/></th>
		<th><s:textfield name="searchObject.latitude" size="3"/></th>
		<th><s:textfield name="searchObject.longitude" size="3"/></th>
		<th>
			<s:select list="availablePropertyTypes" name="searchObject.propertyType.id" 
		    headerKey="" headerValue="" listValue="name" listKey="id" emptyOption="false"/>
		</th>
		<th><s:textfield name="searchObject.buildingSize" size="5"/></th>
		<th><s:textfield name="searchObject.acreage" size="5"/></th>
		<th><s:textfield name="searchObject.businessType" size="10"/></th>
		<th><s:textfield name="searchObject.center" size="10"/></th>
		<th><s:textfield name="searchObject.commercialSpcFore" size="10"/></th>
		<th><s:textfield name="searchObject.facet" size="10"/></th>
	   	<th><select name="flagPoleSearch"/>
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>		
	   	<th><select name="forSaleSearch"/>
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>		
		<th><s:textfield name="searchObject.frontage" size="10"/></th>
		<th><s:textfield name="searchObject.hcad" size="10"/></th>
		<th><s:textfield name="searchObject.largestVacancy" size="10"/></th>
		<th><s:textfield name="searchObject.notes" size="10"/></th>
		<th><s:textfield name="searchObject.noUnits" size="10"/></th>
		<th><s:textfield name="searchObject.occupancyRate" size="10"/></th>
		<th><s:textfield name="searchObject.occupiedSqFt" size="10"/></th>
		<th><s:textfield name="searchObject.owner" size="10"/></th>
		<th><s:textfield name="searchObject.priceSqFt" size="10"/></th>
		<th><s:textfield name="searchObject.restrictions" size="10"/></th>
	   	<th><select name="singleTenantSearch"/>
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>		
	   	<th><select name="trspassAfdvtSearch"/>
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>		
	   	<th><sx:datetimepicker name="searchObject.trspassDate" displayFormat="MM/dd/yyyy"/></th>
		<th><s:textfield name="searchObject.vacantUnits" size="10"/></th>
	   	<th><select name="willDivideSearch"/>
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>		
		<th><s:textfield name="searchObject.yearBuilt" size="4" maxlength="5"/></th>
	   	<th><sx:datetimepicker name="searchObject.inputDate" displayFormat="MM/dd/yyyy"/></th>
	   	<th><sx:datetimepicker name="searchObject.lastUpdate" displayFormat="MM/dd/yyyy"/></th>
		<th><s:submit value="Search" /></th>
	</tr>
    <tr>
    	<s:url action="sort" id="sortid"><s:param name="orderCol" value="'id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortbuildname"><s:param name="orderCol" value="'buildingName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortgnum"><s:param name="orderCol" value="'geoNumber'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortgaddr"><s:param name="orderCol" value="'geoAddress'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortgcity"><s:param name="orderCol" value="'geoCity'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortgstate"><s:param name="orderCol" value="'geoState'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortgzip"><s:param name="orderCol" value="'geoZipCode'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortlat"><s:param name="orderCol" value="'latitude'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortlong"><s:param name="orderCol" value="'longitude'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortproptype"><s:param name="orderCol" value="'propertyType.id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortavail"><s:param name="orderCol" value="'buildingSize'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortacreage"><s:param name="orderCol" value="'acreage'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortbtype"><s:param name="orderCol" value="'businessType'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortcenter"><s:param name="orderCol" value="'center'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortcommspc"><s:param name="orderCol" value="'commercialSpcFore'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortfacet"><s:param name="orderCol" value="'facet'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortflag"><s:param name="orderCol" value="'flagPole'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortforsale"><s:param name="orderCol" value="'forSale'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortfrtg"><s:param name="orderCol" value="'frontage'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sorthcad"><s:param name="orderCol" value="'hcad'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortlargvac"><s:param name="orderCol" value="'largestVacancy'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortnotes"><s:param name="orderCol" value="'notes'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortnounit"><s:param name="orderCol" value="'noUnits'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortoccrt"><s:param name="orderCol" value="'occupancyRate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortoccsqft"><s:param name="orderCol" value="'occupiedSqFt'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortowner"><s:param name="orderCol" value="'owner'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortprsqft"><s:param name="orderCol" value="'priceSqFt'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortrestr"><s:param name="orderCol" value="'restrictions'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortsingten"><s:param name="orderCol" value="'singleTenant'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sorttrspaf"><s:param name="orderCol" value="'trspassAfdvt'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sorttrsdt"><s:param name="orderCol" value="'trspassDate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortvacunit"><s:param name="orderCol" value="'vacantUnits'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortwilldiv"><s:param name="orderCol" value="'willDivide'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortyrblt"><s:param name="orderCol" value="'yearBuilt'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>    	
    
        <s:url action="sort" id="sortinpt"><s:param name="orderCol" value="'inputDate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortupdt"><s:param name="orderCol" value="'lastUpdate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    
        <th><s:a href="%{sortid}">Id</s:a></th>
        <th><s:a href="%{sortbuildname}">Building Name</s:a></th>
        <th><s:a href="%{sortgnum}">GeoNum</s:a></th>
        <th><s:a href="%{sortgaddr}">Address</s:a></th>
        <th><s:a href="%{sortgcity}">City</s:a></th>
        <th><s:a href="%{sortgstate}">State</s:a></th>
        <th><s:a href="%{sortgzip}">Zip</s:a></th>
        <th><s:a href="%{sortlat}">Latitude</s:a></th>
        <th><s:a href="%{sortlong}">Longitude</s:a></th>
        <th><s:a href="%{sortproptype}">Property Type</s:a></th>
        <th><s:a href="%{sortavail}">Building Size</s:a></th>
        <th><s:a href="%{sortacreage}">Acreage</s:a></th>
        <th><s:a href="%{sortbtype}">Business Type</s:a></th>
        <th><s:a href="%{sortcenter}">Center</s:a></th>
        <th><s:a href="%{sortcommspc}">Commercial Spc Fore</s:a></th>
        <th><s:a href="%{sortfacet}">Facet</s:a></th>
        <th><s:a href="%{sortflag}">Flag Pole</s:a></th>
        <th><s:a href="%{sortforsale}">For Sale</s:a></th>
        <th><s:a href="%{sortfrtg}">Frontage</s:a></th>
        <th><s:a href="%{sorthcad}">HCAD</s:a></th>
        <th><s:a href="%{sortlargvac}">Largest Vacancy</s:a></th>
        <th><s:a href="%{sortnotes}">Notes</s:a></th>
        <th><s:a href="%{sortnounit}">No Units</s:a></th>
        <th><s:a href="%{sortoccrt}">Occ Rate</s:a></th>
        <th><s:a href="%{sortoccsqft}">Occ Sq Ft</s:a></th>
		<th><s:a href="%{sortowner}">Owner</s:a></th>
        <th><s:a href="%{sortprsqft}">Price Sq Ft</s:a></th>
        <th><s:a href="%{sortrestr}">Restrictions</s:a></th>
        <th><s:a href="%{sortsingten}">Single Tenant</s:a></th>
        <th><s:a href="%{sorttrspaf}">Tresspass Afdvt</s:a></th>
        <th><s:a href="%{sorttrsdt}">Tresspass Date</s:a></th>
        <th><s:a href="%{sortvacunit}">Vacant Units</s:a></th>
        <th><s:a href="%{sortwilldiv}">Will Divide</s:a></th>
        <th><s:a href="%{sortyrblt}">Year Built</s:a></th>
        <th><s:a href="%{sortinpt}">Input Date</s:a></th>
        <th><s:a href="%{sortupdt}">Update Date</s:a></th>
        <th></th>
    </tr>
</thead>
<tbody>
    <s:iterator value="properties" status="status" id="property">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
            <td><s:property value="buildingName"/></td>
            <td><s:property value="geoNumber"/></td>
            <td><s:property value="geoAddress"/></td>
            <td><s:property value="geoCity"/></td>
            <td><s:property value="geoState"/></td>
            <td><s:property value="geoZipCode"/></td>
            <td><s:property value="latitude" default=""/></td>
            <td><s:property value="longitude" default=""/></td>
            <td><s:property value="propertyType.name"/></td>
            <td><s:property value="buildingSize"/></td>
            <td><s:property value="acreage"/></td>
            <td><s:property value="businessType"/></td>
            <td><s:property value="center"/></td>
            <td><s:property value="commercialSpaceFore"/></td>
            <td><s:property value="facet"/></td>
            <td><s:property value="flagPole"/></td>
            <td><s:property value="forSale"/></td>
            <td><s:property value="frontage"/></td>
            <td><s:property value="hcad"/></td>
            <td><s:property value="largestVacancy"/></td>
            <td><s:property value="notes"/></td>
            <td><s:property value="noUnits"/></td>
            <td><s:property value="occupancyRate"/></td>
            <td><s:property value="occupiedSqFt"/></td>
            <td><s:property value="owner"/></td>
            <td><s:property value="priceSqFt"/></td>
            <td><s:property value="restrictions"/></td>
            <td><s:property value="singleTenant"/></td>
            <td><s:property value="trspassAfdvt"/></td>
            <td><s:date name="trspassDate" format="MM/dd/yyyy" /></td>
            <td><s:property value="vacantUnits"/></td>
            <td><s:property value="willDivide"/></td>
            <td><s:property value="yearBuilt"/></td>
            <td><s:date name="inputDate" format="MM/dd/yyyy" /></td>
            <td><s:date name="lastUpdate" format="MM/dd/yyyy" /></td>
            <td></td>
        </tr>
    </s:iterator>
</tbody>
</table>

	<s:submit value="Search" />
</s:form>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prev"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Property</a></p>
</body>
</html>