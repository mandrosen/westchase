<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Patrol Activity Report</title>
    
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

	<h2>Patrol Officer Detail Report</h2>
	
	<s:form action="officerDetailReport" id="frm" theme="simple">
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
				<th>Start Date <span class="format">(mm/dd/yyyy)</span></th>
				<td>
					<sx:datetimepicker name="startDate" displayFormat="MM/dd/yyyy"/>
				</td>
			</tr>
			<tr>
				<th>End Date <span class="format">(mm/dd/yyyy)</span></th>
				<td>
					<sx:datetimepicker name="endDate" displayFormat="MM/dd/yyyy"/>
				</td>
			</tr>
			
			
			<tr>
				<th>Report Type</th>
				<td>
					<label><input type="radio" name="reportType" value="0" /> Call Codes</label>
					<label><input type="radio" name="reportType" value="1" /> Categories (CFS ...)</label>
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
				<c:forEach items="${results.itemList}" var="item">
					<th><c:out value="${item.name}" /></th>
				</c:forEach>
				<th>TOTAL</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${results.officerList}" var="officer">
				<c:set var="officerTotal" value="0" />
				<tr>
				    <td><c:out value="${officer.fullNameReverse}" /></td>
					<c:forEach items="${results.itemList}" var="item">
						<c:set var="officerItemKey" value="${officer.id}:${item.id}" />
						
						
						<c:set var="resValue" value="${results.officerCounts[officerItemKey]}" />

						<td><c:out value="${resValue.count}" /></td>
						<c:set var="officerTotal" value="${officerTotal + resValue.count}" />
					</c:forEach>
				    <td><c:out value="${officerTotal}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>