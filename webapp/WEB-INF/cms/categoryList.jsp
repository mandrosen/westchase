<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Category List</title>
</head>
<body>
<h1>Category List</h1>

<p><a href="<s:url action="list"/>">Refresh</a></p>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Category</a></p>

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

<table class="results">
<thead>
    <tr>
        <th>Code</th>
        <th>Description</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="categories" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{categoryCode}" />"><s:property value="categoryCode"/></a></td>
		    <td><s:property value="categoryDesc"/></td>
        </tr>
    </s:iterator>
</tbody>
</table>
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
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Category</a></p>
</body>
</html>