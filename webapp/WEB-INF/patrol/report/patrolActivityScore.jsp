<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Patrol Activity Score Report</title>
    
    <sx:head />
    
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	</script>
    
	<style type="text/css">
	
	</style>
</head>

<body>

	<h2>Patrol Activity Score Report</h2>
	
	<s:form action="activityScoreReport" id="frm" theme="simple">
		<s:hidden name="type" id="typeparam" />
		<table>
			<tr>
				<th>Officer</th>
				<td>
					<s:select name="officerIdList" headerKey="-1" headerValue="--" 
					    list="availableOfficers" listValue="fullNameReverse" listKey="id" emptyOption="false" 
					    multiple="true" size="10" />
				</td>
			</tr>
			<tr>
				<th>Start Date <span class="format">(mmddyyyy)</span></th>
				<td>
					<sx:datetimepicker name="startDate" displayFormat="MMddyyyy"/>
				</td>
			</tr>
			<tr>
				<th>End Date <span class="format">(mmddyyyy)</span></th>
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
			
		    <tr><td colspan="2"><s:submit value="Run" onclick="$('#typeparam').val('')" /></td></tr>
		    <tr><td colspan="2"><input type="button" value="Export to Excel" onclick="exportreport('excel')" /></td></tr>
	    </table>
	</s:form>
	
	<table class="results" id="results_table">
		<thead>
		    <tr>
				<th>Officer</th> 
				<th>Duty Minutes</th> 
				<th>Duty Hours</th> 
				<th>Total Points</th> 
				<th>Score</th>
		    </tr>
		</thead>
		<tbody>
		    <s:iterator value="results" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		 
				    <td><s:property value="officer.fullNameReverse"/></td>
				    <td><s:property value="dutyMinutes"/></td>
				    <td><s:property value="dutyHours"/></td>
				    <td><s:property value="points"/></td>
				    <td><s:property value="score"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table> 

</body>
</html>
