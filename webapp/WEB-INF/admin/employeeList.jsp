<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h1>Employee List</h1>


<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Employee</a></p>

<table class="results">
<thead>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Phone</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="employees" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
		    <td><s:property value="firstName"/></td>
		    <td><s:property value="lastName"/></td>
		    <td><s:property value="email"/></td>
		    <td><s:property value="phone"/></td>
        </tr>
    </s:iterator>
</tbody>
</table>

</body>
</html>