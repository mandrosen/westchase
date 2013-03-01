<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Employee</title>
    <s:head/>
</head>

<body>
<h1>Edit Employee</h1>

<p><a href="<s:url action="list"/>">Back to List</a></p>

<div class="actionmessages">
	<s:actionmessage />
	<s:actionerror />
</div>

<s:form name="editForm" action="save">
	<c:if test="${not empty currentEmployee.id}">
   		<s:hidden name="currentEmployee.id" />
   		<tr><th>Id</th><td><c:out value="${currentEmployee.id}" /></td></tr>
   	</c:if>
	<c:if test="${not empty currentUser.id}">
   		<s:hidden name="currentUser.id" />
   	</c:if>

    <s:textfield label="First Name" name="currentEmployee.firstName" size="50" maxlength="50"/>
    <s:textfield label="Last Name" name="currentEmployee.lastName" size="50" maxlength="100"/>
    <s:textfield label="Email" name="currentEmployee.email" size="50" maxlength="255"/>
    <s:textfield label="Phone" name="currentEmployee.phone" size="10" maxlength="10"/>
    
    <s:textfield label="Username" name="currentUser.username" size="50" maxlength="100" />
    <s:password label="Password" name="currentUser.password" />
    <s:password label="Confirm Password" name="confirmPassword" />
    <s:checkbox label="Disabled" name="currentUser.disabled" />
    
    <s:submit value="Save" />
</s:form>

<p><a href="<s:url action="list"/>">Back to List</a></p>

</body>
</html>
