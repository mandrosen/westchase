<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>CMU Occupancy List</title>
</head>
<body>
<h1>CMU Occupancy List</h1>


<p><a href="<s:url action="list"/>">Refresh</a></p>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Cmu Occupancy</a></p>

<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prev"><s:param name="page" value="%{page}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next"><s:param name="page" value="%{page}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>

<s:form method="list" theme="simple" action="list">
<table class="results">
<thead>
	<tr>
	    <th><s:textfield name="searchObject.id"/></th>
	    <th><s:textfield name="searchObject.occupancyStr"/></th>
	    
	    
	    <th><s:select list="availableQuarters" name="searchObject.quarter.id" 
	    headerKey="" headerValue="-- Please Select --" listValue="description" listKey="id" emptyOption="true"/></th>
	
	    <th><s:select list="availableProperties" name="searchObject.property.id" 
	    headerKey="" headerValue="-- Please Select --" listValue="buildingName" listKey="id" emptyOption="true"/></th>
	     
	    <th><s:textfield name="searchObject.comments"/></th>	
	</tr>
    <tr>
        <th>Id</th>
        <th>Quarter</th>
        <th>Occupancy</th>
        <th>Property</th>
        <th>Comments</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="cmuOccupancies" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
		    <td><s:property value="quarter.id"/></td>
		    <td><s:property value="occupancy"/></td>
		    <td><s:property value="property.id"/></td>
		    <td><s:property value="comments"/></td>
        </tr>
    </s:iterator>
</tbody>
</table>
    <s:submit value="Search" />
</s:form>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prev"><s:param name="page" value="%{page}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next"><s:param name="page" value="%{page}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Cmu Occupancy</a></p>
</body>
</html>