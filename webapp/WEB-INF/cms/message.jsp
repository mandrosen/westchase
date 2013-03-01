<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Message</title>
    <s:head/>
</head>

<body>
<div class="message">
	<h1>Message</h1>
	<p class="message"><c:out value="${message}" /></p>
	<c:remove var="message" scope="session" />
</div>
</body>
</html>
