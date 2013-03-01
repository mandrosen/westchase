<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Company PhoneBooks</title>
    <s:head/>
    
    <script type="text/javascript">
		function confirmremove(pbid) {
			if (confirm("Are you sure you want to remove this phonebook?")) {
				document.location.href = "/westchase/phonebook/delete.action?delId=" + pbid;
			}
		}
    </script>
</head>

<body>
<div class="companypbs">
	<h1>Company - Phone Books</h1>
	<p>Please edit/remove the following PhoneBook entries before deleting this Company.</p>
	
<table class="phonebooks">
	<thead>
	    <tr>
	        <th>Id</th>
	        <th>First Name</th>
	        <th>Last Name</th>
	        <th>Job Title</th>
	        <th>Work Phone</th>
	        <th>Email</th>
	        <th>Edit/Delete</th>
	    </tr>
    </thead>
    <tbody>
		<s:iterator value="phoneBooksToDelete" status="status" id="pb">
			<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
				<td><a href="<s:url action="edit-%{id}" namespace="/phonebook" />"><s:property value="id"/></a></td>
				<td><s:property value="firstName"/></td>
	            <td><s:property value="lastName"/></td>
	            <td><s:property value="jobTitle"/></td>
	            <td><s:property value="wkPhone"/></td>
	            <td><s:property value="email"/></td>
	            <td>
	            	<a href="<s:url action="edit-%{id}" namespace="/phonebook" />">Edit</a>
            		<a href="javascript:confirmremove(${pb.id})">Delete</a>
	            </td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</div>
</body>
</html>
