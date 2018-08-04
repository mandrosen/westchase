<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Officer List</title>
    
    <s:head />
</head>
<body>
<h1>Officer List</h1>

<p><a href="<s:url action="listOfficer"/>">Refresh</a></p>
<p><a href="<s:url action="editOfficer-" includeParams="none"/>">Create new Officer</a></p>

<p class="total"><s:property value="totalCount" /> total results</p>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="first" action="firstOfficer"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{first}">First</s:a>
		
		<s:url id="prev" action="prevOfficer"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="nextOfficer"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
		
		<s:url id="goToPage" action="goToPageOfficer"><s:param name="nextPage" value="%{maxPage - 1}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{goToPage}">Last</s:a>
	</s:if>
</div>


<s:form theme="simple" action="listOfficer">
<table class="results">
<thead>
	<tr>
		<th><s:textfield name="searchObject.id" size="4"/></th>
		<th><s:textfield name="searchObject.firstName" size="10"/></th>
		<th><s:textfield name="searchObject.lastName" size="10"/></th>
		<th><s:textfield name="searchObject.cellPhone" size="10"/></th>
		<th><s:submit value="Search" /></th>
	</tr>
    <tr>
    	<s:url action="sortOfficer" id="sortid"><s:param name="orderCol" value="'id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sortOfficer" id="sortfirstname"><s:param name="orderCol" value="'firstName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sortOfficer" id="sortlastname"><s:param name="orderCol" value="'lastName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
      	<s:url action="sortOfficer" id="sortcellphone"><s:param name="orderCol" value="'cellPhone'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    
        <th><s:a href="%{sortid}">Id</s:a></th>
        <th><s:a href="%{sortfirstname}">First Name</s:a></th>
        <th><s:a href="%{sortlastname}">Last Name</s:a></th>
        <th><s:a href="%{sortcellphone}">Cell Phone</s:a></th>
        <th></th>
    </tr>
</thead>
<tbody>
    <s:iterator value="officers" status="status" id="officer">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else> active-<s:property value="active"/>">
 
            <td><a href="<s:url action="editOfficer-%{id}" />"><s:property value="id"/></a></td>
            <td><s:property value="firstName"/></td>
            <td><s:property value="lastName"/></td>
            <td><s:property value="cellPhone"/></td>
            <td></td>
        </tr>
    </s:iterator>
</tbody>
</table>

	<s:submit value="Search" />
</s:form>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prevOfficer"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="nextOfficer"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>
<p><a href="<s:url action="editOfficer-" includeParams="none"/>">Create new Officer</a></p>
</body>
</html>
