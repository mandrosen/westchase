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

	<s:if test="%{!includeProperty}">
		$("#property-opts").hide();
	</s:if>
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
	function showHidePropertyOpts() {
		if ($("#include-property").is(":checked")) {
			$("#property-opts").show();
		} else {
			$("#property-opts").hide();
		}
	}
	function toggleResult(id) {
		$("#res" + id).toggle();
	}
	</script>
    
	<style type="text/css">
		.odd {
		   background: #ccc;
		}
		#results_table td { 
			white-space: nowrap;
		}
		a.type-name {
			cursor: pointer;
			text-decoration: underline;
		}
		.result-id-block {
			position: absolute;
			display: none;
			background-color: #fff;
			padding: 5px;
			border: 2px solid #000;
		}
		.result-id-list {
			margin: 0;
			padding: 0;
		}
		.result-id-item {
			list-style-type: none;
			margin: 0;
			padding: 5px;
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
				<td colspan="2">
					<label>Include Property <s:checkbox name="includeProperty" id="include-property" fieldValue="true" onchange="showHidePropertyOpts()" /></label>
				</td>
			</tr>
			<tr id="property-opts">
				<th>Propeties</th>
				<td>
					<s:select name="propertyIdList" headerKey="-1" headerValue="--" 
					    list="availableProperties" 
					    listValue="summaryStringForPublicSafety" listKey="id" 
					    emptyOption="false" 
					    multiple="true" size="10" />
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
				<s:if test="%{includeProperty}"><th>Property</th></s:if>
				<s:if test="%{includeDay}"><th>Day Name</th></s:if>
				<s:if test="%{includeTime}"><th>Hour of Day</th></s:if>
				<th>TOTAL</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${results}" var="result" varStatus="stat">
				<tr>
					<td>
						<a onclick="toggleResult(${stat.index})" class="type-name"><c:out value="${result.typeName}" /></a>
						<div id="res${stat.index}" class="result-id-block">
							<ul class="result-id-list">
								<c:forEach items="${result.detailIdList}" var="detailId">
									<li class="result-id-item"><a href="/westchase/patrol/editActivityDetail-<c:out value='${detailId}' />" target="detailedit"><c:out value="${detailId}" /></a></li>
								</c:forEach>
							</ul>
						</div>
					</td>
					<s:if test="%{includeProperty}"><td>[<c:out value="${result.propertyId}" />] <c:out value="${result.propertyName}" /></td></s:if>
					<s:if test="%{includeDay}"><td><c:out value="${result.dayName}" /></td></s:if>
					<s:if test="%{includeTime}"><td><c:out value="${result.hourOfDay}" /></td></s:if>
					<td><c:out value="${result.typeTotal}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>