<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Edit Company</title>
    <s:head/>
    
    <script type="text/javascript">
		function confirmremove(tid) {
			if (confirm("Are you sure you want to remove this message?")) {
				document.location.href = "/westchase/message/delete.action?messageId=" + tid;
			}
		}
    </script>
</head>
<body>
<h1>Available Messages</h1>

<s:form method="list" action="list">
    <s:select list="availableEmployees" name="searchObject.employee.id" 
    headerKey="" headerValue="-- Please Select --" listValue="firstName" listKey="id" emptyOption="true"/>
		<s:submit value="Search" />
</s:form>

<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Message</a></p>
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
        <th></th>
    </tr>
</thead>
<tbody>
    <s:iterator value="messages" status="status" id="msg">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
            <td><s:property value="employee.id" /></td>
		    <td><s:property value="text"/></td>
		    <td><a href="javascript:confirmremove(${msg.id})">Remove</a></td>
        </tr>
    </s:iterator>
</tbody>
</table>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Message</a></p>

</body>
</html>