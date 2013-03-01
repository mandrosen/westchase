<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Quarterly Information List</title>
</head>
<body>
<h1>Quarterly Information List</h1>

<p><a href="<s:url action="list"/>">Refresh</a></p>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Quarter</a></p>

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
		<th><s:textfield name="searchObject.year"/></th>
		<th><s:textfield name="searchObject.quarterNum"/></th>
		<th><s:textfield name="searchObject.description"/></th>
	</tr>
    <tr>
        <th>Id</th>
        <th>Year</th>
        <th>Quarter Num</th>
        <th>Description</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="quarters" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
		    <td><s:property value="year"/></td>
		    <td><s:property value="quarterNum"/></td>
		    <td><s:property value="description"/></td>
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
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Quarter</a></p>
</body>
</html>