<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Todo</title>
    <sx:head/>
</head>

<body>
<h1>Edit Todo <c:if test="${not empty currentTodo.id}"><c:out value="${currentTodo.id}" /></c:if></h1>


<s:form name="editForm" action="save">
	<c:if test="${not empty currentTodo.id}">
   		<s:hidden name="currentTodo.id" />
   		<s:hidden name="currentTodo.createDate" />
   		<tr><th>Id</th><td><c:out value="${currentTodo.id}" /></td></tr>
   	</c:if>
	<sx:datetimepicker name="currentTodo.dueDate" label="Due Date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" required="true" />
   	<sx:datetimepicker name="currentTodo.expireDate" label="Expire Date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
    <s:textarea label="Text" name="currentTodo.text" rows="3" cols="40" required="true" />
    <s:submit value="Save" />
</s:form>
<%--<p><a href="<s:url action="list"/>">Back to List</a></p>--%>
</body>
</html>
