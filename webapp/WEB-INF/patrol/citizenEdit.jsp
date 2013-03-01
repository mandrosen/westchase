<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Citizen</title>
    <s:head/>
</head>

<body>

<c:choose>
<c:when test="${not empty currentCitizen.id}">
	<h1>Edit Citizen - <c:out value="${currentCitizen.id}" /></h1>
</c:when>
<c:otherwise>
	<h1>New Citizen</h1>
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

<s:form name="editForm" action="saveCitizen">
	<c:if test="${not empty currentCitizen.id}">
   		<s:hidden name="currentCitizen.id" />
   	</c:if>
    <s:textfield label="First Name" name="currentCitizen.firstName" size="40" maxlength="255" required="true" />
    <s:textfield label="Middle Name" name="currentCitizen.middleName" size="40" maxlength="255"/>
    <s:textfield label="Last Name" name="currentCitizen.lastName" size="40" maxlength="255" required="true" />
    <!-- <s:textfield label="TX DL" name="currentCitizen.txDl" size="10" maxlength="10"/> -->
    <s:textfield label="Organization" name="currentCitizen.organization" size="40" maxlength="255" required="true" />
    <s:textfield label="Street Address" name="currentCitizen.streetAddress" size="40" maxlength="255" required="true" />
    <s:textfield label="Zip Code" name="currentCitizen.zipCode" size="40" maxlength="255" required="true" />
    <s:submit value="Save" />
</s:form>
<p><a href="/westchase/patrol/listCitizen?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="listCitizen"/>">Back to List</a></p>
</body>
</html>
