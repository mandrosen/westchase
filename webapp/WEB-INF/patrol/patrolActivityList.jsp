<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <title>Patrol Activity List</title>
    
    <sx:head />
    
    
    <script type="text/javascript">
		function confirmremove(id) {
			if (confirm("Are you sure you want to remove this patrol?")) {
				document.location.href = "/westchase/patrol/deleteActivity.action?delId=" + id;
			}
		}
    </script>
</head>
<body>

<h1>Patrol Activity List</h1>

<p><a href="<s:url action="listActivity"/>">Refresh</a></p>
<p><a href="<s:url action="editActivity-" includeParams="none"/>">Create new Patrol Activity</a></p>

<s:form method="listActivity" action="listActivity" theme="simple">
	<table>
		<tbody>
			<tr>
				<th>Id</th>
				<td>
					<s:textfield name="searchObject.id" />
				</td>
			</tr>
			<tr>
				<th>Officer</th>
				<td>
					<s:select list="availableOfficers" name="searchObject.officer.id"  headerKey="-1" headerValue="-- Please Select --" listValue="fullNameReverse" listKey="id" />
				</td>
			</tr>
			<tr>
				<th>Start Date</th>
				<td>
					<sx:datetimepicker name="startDate" displayFormat="MMddyyyy"/>
				</td>
			</tr>
			<tr>
				<th>End Date</th>
				<td>
					<sx:datetimepicker name="endDate" displayFormat="MMddyyyy"/>
				</td>
			</tr>
			
			<tr>
				<th>Patrol Type</th>
				<td>
					<s:select name="patrolTypeIdList" headerKey="-1" headerValue="--" 
					    list="availablePatrolTypes" listValue="name" listKey="id" emptyOption="false" 
					    multiple="true" size="10" />
		    	</td>
    		</tr>
		</tbody>
	</table>	
	<s:submit />
</s:form>

<p class="total"><s:property value="totalCount" /> total results</p>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="first" action="firstActivity"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{first}">First</s:a>
		
		<s:url id="prev" action="prevActivity"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="nextActivity"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
		
		<s:url id="goToPage" action="goToPageActivity"><s:param name="nextPage" value="%{maxPage - 1}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{goToPage}">Last</s:a>
	</s:if>
</div>

<table class="results">
	<thead>
	    <tr>
	    	<s:url action="sortActivity" id="sortid"><s:param name="orderCol" value="'id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    	<s:url action="sortActivity" id="sortofficer"><s:param name="orderCol" value="'officer.lastName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    	<s:url action="sortActivity" id="sortdate"><s:param name="orderCol" value="'startDateTime'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
	    	<s:url action="sortActivity" id="sortpatrol"><s:param name="orderCol" value="'patrolType.name'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>

	    
	    
        	<th><s:a href="%{sortid}">Id</s:a></th>
        	<th><s:a href="%{sortofficer}">Officer</s:a></th>
        	<th><s:a href="%{sortdate}">Activity Date</s:a></th>
        	<th><s:a href="%{sortpatrol}">Patrol Type</s:a></th>
	    	<th></th>
	    </tr>
	</thead>
	<tbody>
	    <s:iterator value="patrolActivities" status="status" id="pb">
	        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
	 
	            <td><a href="<s:url action="editActivity-%{id}" />"><s:property value="id"/></a></td>
	            <td><s:property value="officer.fullNameReverse"/></td>
	
	            <td><s:date name="startDateTime" format="MM/dd/yyyy" /></td>
	            <td><s:property value="patrolType.name" /></td>
	            <td>
	           		<a href="<s:url action="editActivity-%{id}" />">Edit</a>
            	<a href="javascript:confirmremove(${pb.id})">Delete</a>
	            </td>
	        </tr>
	    </s:iterator>
	</tbody>
</table>    

<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prevActivity"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="nextActivity"><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>

<p><a href="<s:url action="editActivity-" includeParams="none"/>">Create new Patrol Activity</a></p>

</body>
</html>