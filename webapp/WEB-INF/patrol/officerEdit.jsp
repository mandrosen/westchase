<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Officer</title>
    <s:head/>
</head>

<body>

<c:choose>
<c:when test="${not empty currentOfficer.id}">
	<h1>Edit Officer - <c:out value="${currentOfficer.id}" /></h1>
</c:when>
<c:otherwise>
	<h1>New Officer</h1>
</c:otherwise>
</c:choose>

	<s:if test="hasActionErrors()">
	   <div class="message-section error">
	      <s:actionerror />
	   </div>
	</s:if>
	
	<s:if test="hasActionMessages()">
	   <div class="message-section actionmessages">
	      <s:actionmessage />
	   </div>
	</s:if>
	
<s:form name="editForm" action="saveOfficer">
	<c:if test="${not empty currentOfficer.id}">
   		<s:hidden name="currentOfficer.id" />
   	</c:if>
    <s:textfield label="First Name" name="currentOfficer.firstName" size="40" maxlength="255" required="true" />
    <s:textfield label="Last Name" name="currentOfficer.lastName" size="40" maxlength="255" required="true" />
    <!-- <s:textfield label="Badge" name="currentOfficer.badge" size="10" maxlength="20"/> -->
    <s:textfield label="Cell Phone" name="currentOfficer.cellPhone" size="10" maxlength="25"/>

    <s:submit value="Save" />
</s:form>
<p><a href="/westchase/patrol/listOfficer?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="listOfficer"/>">Back to List</a></p>
</body>
</html>
