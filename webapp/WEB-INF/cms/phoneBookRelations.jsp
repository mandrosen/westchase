<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Phone Book Relations</title>
    
    <sx:head />
    
    <script type="text/javascript">
		function confirmremove(pbid, rid) {
			if (confirm("Are you sure you want to remove this relation?")) {
				document.location.href = "/westchase/phoneBookRelations/delete.action?phoneBookId=" + pbid + "&relationId=" + rid;
			}
		}
    </script>
</head>
<body>
	<h1>Phone Book Relations</h1>
	
	<p><strong>Current Phone Book:</strong> ${currentPhoneBook.firstName} ${currentPhoneBook.lastName}</p>
	<p><a href="/westchase/phonebook/edit-${phoneBookId}">Back to Phone Book</a></p>
	<p><a href="/westchase/phonebook/list?useLast=1">Back to Current Phone Book List</a></p>
	
	<c:choose>
		<c:when test="${not empty phoneBookRelations}">
			<table class='phonebookrels'>
				<caption>Current Properties for this Phone Book</caption>
				<thead>
					<tr>
						<th>Property</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				    <s:iterator value="phoneBookRelations" status="status" id="pbr">
						<tr>
							<td>(<s:property value="property.id" />) <s:property value="property.buildingName" /></td>
							<!-- <td><s:property value="phoneBookRelationType.name" /></td> -->
							<td><a href="javascript:confirmremove(${phoneBookId}, ${pbr.id})">Delete</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p class='none'>There are no current phone book relations for this phone book.</p>
		</c:otherwise>
	</c:choose>
		
		
	<s:form action="saveNew" theme="simple">
		<div>
			<s:hidden name="relationTypeId" value="1" />
			<s:hidden name="phoneBookId"/>
			<table>
				<tbody>
					<tr>
						<td>Add a new Property:</td>
						<td>
							<s:select list="availableProperties" name="propertyId" 
				    			headerKey="" headerValue="-- Please Select --" 
				    			listValue="summaryString" listKey="id" emptyOption="false"/>
				    	</td>
				    </tr>
				</tbody>
			</table>
			<s:submit value="Save" />
		</div>
	</s:form>
	
</body>
</html>