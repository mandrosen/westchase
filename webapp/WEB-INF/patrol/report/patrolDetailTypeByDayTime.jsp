<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Patrol Call Code by Date/Time Report</title>
    
    <sx:head />
    
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	$(function() {
		$("#results_table tr:odd").addClass("odd");
	<s:if test="%{!includeDay}">
		$("#day-opts").hide();
	</s:if>
	})
	function showHideDayOpts() {
		if ($("#include-day").is(":checked")) {
			$("#day-opts").show();
		} else {
			$("#day-opts").hide();
		}
	}
	</script>
    
	<style type="text/css">
		.odd {
		   background: #ccc;
		}
		#results_table td { 
			white-space: nowrap;
		}
	</style>
</head>

<body>

	<h2>Patrol Call Code by Date/Time Report</h2>
	
	<s:form action="detailTypeByDateTime" id="frm" theme="simple">
		<s:hidden name="type" id="typeparam" />
		<table>
			<tr>
				<th>Call Code</th>
				<td>
					<s:select name="patrolDetailTypeIdList" headerKey="-1" headerValue="--" 
					    list="availableDetailTypes" listValue="name" listKey="id" emptyOption="false" 
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
				<td colspan="2">
					<label>Include Day <s:checkbox name="includeDay" id="include-day" fieldValue="true" onchange="showHideDayOpts()" /></label>
				</td>
			</tr>
			<tr id="day-opts">
				<th>Days</th>
				<td>
					<s:select name="dayIdList" headerKey="-1" headerValue="--" 
					    list="availableDays" listValue="value" listKey="keyAsInteger" emptyOption="false" 
					    multiple="true" size="10" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label>Include Time <s:checkbox name="includeTime" fieldValue="true" /></label>
				</td>
			</tr>
			
		    <tr><td colspan="2"><s:submit value="Run" onclick="$('#typeparam').val('')" /></td></tr>
		    <tr><td colspan="2"><input type="button" value="Export to Excel" onclick="exportreport('excel')" /></td></tr>
	    </table>
	</s:form>
	
	<table class="results" id="results_table">
		<thead>
		    <tr>
				<th>Call Code</th>
				<s:if test="%{includeDay}"><th>Day Name</th></s:if>
				<s:if test="%{includeTime}"><th>Hour of Day</th></s:if>
				<th>TOTAL</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${results}" var="result">
				<tr>
					<td><c:out value="${result.typeName}" /></td>
					<s:if test="%{includeDay}"><td><c:out value="${result.dayName}" /></td></s:if>
					<s:if test="%{includeTime}"><td><c:out value="${result.hourOfDay}" /></td></s:if>
					<td><c:out value="${result.typeTotal}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>