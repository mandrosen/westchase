<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Citizen List</title>
    
    <s:head />
</head>
<body>
<h1>Citizen List</h1>

<p><a href="<s:url action="listCitizen"/>">Refresh</a></p>
<p><a href="<s:url action="editCitizen-" includeParams="none"/>">Create new Citizen</a></p>

<p class="total"><s:property value="totalCount" /> total results</p>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="first" action="firstCitizen"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{first}">First</s:a>
		
		<s:url id="prev" action="prevCitizen"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="nextCitizen"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
		
		<s:url id="goToPage" action="goToPageCitizen"><s:param name="nextPage" value="%{maxPage - 1}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{goToPage}">Last</s:a>
	</s:if>
</div>


<s:form theme="simple" action="listCitizen">
<table class="results">
<thead>
	<tr>
		<th><s:textfield name="searchObject.id" size="4"/></th>
		<th><s:textfield name="searchObject.firstName" size="10"/></th>
		<th><s:textfield name="searchObject.middleName" size="10"/></th>
		<th><s:textfield name="searchObject.lastName" size="10"/></th>
		<th><s:textfield name="searchObject.organization" size="10"/></th>
		<th><s:textfield name="searchObject.streetAddress" size="10"/></th>
		<th><s:textfield name="searchObject.zipCode" size="10"/></th>
		<th><s:submit value="Search" /></th>
	</tr>
    <tr>
    	<s:url action="sort" id="sortid"><s:param name="orderCol" value="'id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortfirstname"><s:param name="orderCol" value="'firstName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortmiddlename"><s:param name="orderCol" value="'middleName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortlastname"><s:param name="orderCol" value="'lastName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortorg"><s:param name="orderCol" value="'organization'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortaddr"><s:param name="orderCol" value="'streetAddress'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortzip"><s:param name="orderCol" value="'zipCode'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>

    
        <th><s:a href="%{sortid}">Id</s:a></th>
        <th><s:a href="%{sortfirstname}">First Name</s:a></th>
        <th><s:a href="%{sortmiddlename}">Middle Name</s:a></th>
        <th><s:a href="%{sortlastname}">Last Name</s:a></th>
        <th><s:a href="%{sortorg}">Organization</s:a></th>
        <th><s:a href="%{sortaddr}">Address</s:a></th>
        <th><s:a href="%{sortzip}">Zip Code</s:a></th>
        <th></th>
    </tr>
</thead>
<tbody>
    <s:iterator value="citizens" status="status" id="citizen">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="editCitizen-%{id}" />"><s:property value="id"/></a></td>
            <td><s:property value="firstName"/></td>
            <td><s:property value="middleName"/></td>
            <td><s:property value="lastName"/></td>
            <td><s:property value="organization"/></td>
            <td><s:property value="streetAddress"/></td>
            <td><s:property value="zipCode"/></td>
            <td></td>
        </tr>
    </s:iterator>
</tbody>
</table>

	<s:submit value="Search" />
</s:form>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prevCitizen"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="nextCitizen"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Citizen</a></p>
</body>
</html>