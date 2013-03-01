<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit Company</title>
    <s:head/>
    
    <script type="text/javascript">
		function confirmremove(tid) {
			if (confirm("Are you sure you want to remove this todo?")) {
				document.location.href = "/westchase/todo/delete.action?todoId=" + tid;
			}
		}
		function confirmcomplete(tid) {
			if (confirm("Are you sure you want to complete this todo?")) {
				document.location.href = "/westchase/todo/complete-" + tid + ".action";
			}
		}
    </script>
</head>
<body>
<h1>Available Todos</h1>

<s:form method="list" action="list">
    <s:select list="availableEmployees" name="searchObject.employee.id" 
    headerKey="" headerValue="-- Please Select --" listValue="firstName" listKey="id" emptyOption="true"/>
		<s:submit value="Search" />
</s:form>

<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Todo</a></p>
<s:if test="%{page > 0}">
	<s:url id="prev" action="prev"><s:param name="page" value="%{page}"/></s:url>
	<s:a href="%{prev}">Previous</s:a>
</s:if>  
<s:if test="%{page < (maxPage - 1)}">
	<s:url id="next" action="next"><s:param name="page" value="%{page}"/></s:url> 
	<s:a href="%{next}">Next</s:a>
</s:if>

<table class="results">
<thead>
    <tr>
        <th>Id</th>
        <th>Employee</th>
        <th>Text</th>
        <th>Due Date</th>
        <th>Expire Date</th>
        <th>Complete Date</th>
        <th>&#160;</th>
    </tr>
</thead>
<tbody>
    <s:iterator value="todos" status="status" id="t">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
            <td><s:property value="employee.id" /></td>
		    <td><s:property value="text"/></td>
		    <td><s:date format="MM/dd/yyyy" name="dueDate"/></td>
		    <td><s:date format="MM/dd/yyyy" name="expireDate"/></td>
		    <td><s:date format="MM/dd/yyyy" name="completeDate"/></td>
		    <td>
		    	<s:if test="%{employee.id == loggedInEmpId}">
		    		<c:if test="${empty t.completeDate}">
		    			<a href="javascript:confirmcomplete(${t.id})">Complete</a>
		    		</c:if>
		    		<a href="javascript:confirmremove(${t.id})">Remove</a>
		    	</s:if>
		    </td>
        </tr>
    </s:iterator>
</tbody>
</table>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Todo</a></p>

</body>
</html>