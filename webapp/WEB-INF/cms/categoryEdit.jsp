<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Category</title>
    <s:head/>
</head>

<body>
<h1>Edit Category</h1>


<s:form name="editForm" action="save">
	<c:choose>
		<c:when test="${not empty currentCategory.categoryCode}">
    		<s:hidden name="currentCategory.categoryCode" />
    		<tr><th>Code</th><td><c:out value="${currentCategory.categoryCode}" /></td></tr>
    	</c:when>
    	<c:otherwise>
    		<s:textfield label="Code" name="currentCategory.categoryCode" size="2" maxlength="2"/>
    	</c:otherwise>
    </c:choose>
    <s:textfield label="Description" name="currentCategory.categoryDesc" size="40" maxlength="40"/>
    <s:submit value="Save" />
</s:form>
<p><a href="/westchase/category/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
