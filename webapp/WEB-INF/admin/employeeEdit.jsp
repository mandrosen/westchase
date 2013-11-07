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

<s:if test="hasActionErrors()">
   <div class="message-section error">
      <s:actionerror />
   </div>
</s:if>
<c:if test="${not empty WCActionWarning}">
   <div class="message-section actionmessages warnings">
      <c:out value="${WCActionWarning}" escapeXml="false" />
      <c:remove var="WCActionWarning" />
   </div>
</c:if>

<s:if test="hasActionMessages()">
   <div class="message-section actionmessages">
      <s:actionmessage />
   </div>
</s:if>

<c:if test="${not empty WCActionMessage}">
   <div class="message-section actionmessages">
      <c:out value="${WCActionMessage}" />
      <c:remove var="WCActionMessage" />
   </div>
</c:if>

<s:form name="editForm" action="save">
	<c:if test="${not empty currentEmployee.id}">
   		<s:hidden name="currentEmployee.id" />
   		<tr><th>Id</th><td><c:out value="${currentEmployee.id}" /></td></tr>
   	</c:if>
	<c:if test="${not empty currentUser.id}">
   		<s:hidden name="currentUser.id" />
   	</c:if>

    <s:textfield label="First Name" name="currentEmployee.firstName" size="50" maxlength="50" required="true" />
    <s:textfield label="Last Name" name="currentEmployee.lastName" size="50" maxlength="100" required="true" />
    <s:textfield label="Email" name="currentEmployee.email" size="50" maxlength="255" required="true" />
    <s:textfield label="Phone" name="currentEmployee.phone" size="10" maxlength="10"/>
    
    <s:textfield label="Username" name="currentUser.username" size="50" maxlength="100" required="true"  />
    <s:password label="Password" name="currentUser.password" />
    <s:password label="Confirm Password" name="confirmPassword" />
    <s:checkbox label="Disabled" name="currentUser.disabled" />
    
    <tr>
    	<td class="tdLabel"><label class="label">Roles:</label></td>
    	<td>
    		<s:checkboxlist label="Roles" list="roleList" name="roleIdList" listValue="name" listKey="id" required="true" theme="simple"  />
    	</td>
    </tr>
    
    <s:submit value="Save" />
</s:form>

<p><a href="<s:url action="list"/>">Back to List</a></p>

</body>
</html>
