<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>CMU Lease List</title>
</head>
<body>
<h1>CMU Lease List</h1>

<p><a href="<s:url action="list"/>">Refresh</a></p>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Cmu Lease</a></p>
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
    <th><s:textfield name="searchObject.ownersRep"/></th>
    <th><s:textfield name="searchObject.tenantsRep"/></th>
    <th><s:textfield name="searchObject.tenantName"/></th>
    <th><s:textfield name="searchObject.sqFt"/></th>

    <th><s:select list="availableQuarters" name="searchObject.quarter.id" 
    headerKey="" headerValue="-- Please Select --" listValue="description" listKey="id" emptyOption="true"/></th>

    <th><s:select list="availableProperties" name="searchObject.property.id" 
    headerKey="" headerValue="-- Please Select --" listValue="buildingName" listKey="id" emptyOption="true"/></th>

    <th><s:select list="availableTransactionTypes" name="searchObject.cmuTransactionType.id" 
    headerKey="" headerValue="-- Please Select --" listValue="description" listKey="id" emptyOption="true"/></th>
	</tr>
    <tr>
        <th>Id</th>
        <th>Owners Rep</th>
        <th>Tenants Rep</th>
        <th>Tenant Name</th>
        <th>Sq Ft</th>
        <th>Quarter</th>
        <th>Property</th>
        <th>Type</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="cmuLeases" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
		    <td><s:property value="ownersRep"/></td>
		    <td><s:property value="tenantsRep"/></td>
		    <td><s:property value="tenantName"/></td>
		    <td><s:property value="sqFt"/></td>
		    <td><s:property value="quarter.id"/></td>
		    <td><s:property value="property.id"/></td>
		    <td><s:property value="cmuTransactionType.id"/></td>
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
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Cmu Lease</a></p>
</body>
</html>