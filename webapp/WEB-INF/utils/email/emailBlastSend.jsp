<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Email Blast: Email Sent</title>
    
    <s:head />
</head>
<body>

<h1>Email Blast:
<c:choose>
<c:when test="${success}">Email Sent</c:when>
<c:otherwise><span class="error">Email Not Sent</span></c:otherwise>
</c:choose>
</h1>

<p><strong>Email Information:</strong></p>
<p><strong>Email Addresses: </strong><c:out value="${emailAddresses}" /></p>
<p><strong>Subject: </strong><c:out value="${emailSubject}" /></p>
<p><strong>Message: </strong><br/><c:out value="${emailMessage}" escapeXml="false" /></p>

</body>
</html>