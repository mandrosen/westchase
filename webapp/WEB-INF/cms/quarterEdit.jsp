<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Quarter</title>
    <s:head/>
</head>

<body>
<h1>Edit Quarter</h1>


<s:form name="editForm" action="save">
    <s:hidden name="currentQuarter.id"/>
    <s:textfield label="Year" name="currentQuarter.year"/>
    <s:textfield label="Quarter Num" name="currentQuarter.quarterNum"/>
    <s:textfield label="Description" name="currentQuarter.description"/>
    <s:submit value="Save" />
</s:form>
<p><a href="/westchase/quarter/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
