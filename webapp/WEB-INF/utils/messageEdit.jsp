<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Message</title>
    
    <sx:head />
</head>

<body>
<h1>Edit Message <c:if test="${not empty currentMessage.id}"><c:out value="${currentMessage.id}" /></c:if></h1>


<s:form name="editForm" action="save">
	<c:if test="${not empty currentMessage.id}">
   		<s:hidden name="currentMessage.id" />
   		<s:hidden name="currentMessage.createDate" />
   		<tr><th>Id</th><td><c:out value="${currentMessage.id}" /></td></tr>
   	</c:if>

   	<sx:datetimepicker name="currentMessage.dueDate" label="Due Date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd" required="true"/>
   	<sx:datetimepicker name="currentMessage.expireDate" label="Expire Date (yyyy-MM-dd)" displayFormat="yyyy-MM-dd"/>
   	
    <s:textarea label="Text" name="currentMessage.text" rows="3" cols="40" required="true" />
    <s:submit value="Save" />
</s:form>
</body>
</html>
