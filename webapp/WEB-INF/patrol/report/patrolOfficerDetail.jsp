<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	$(function() {
		$("#results_table tr:odd").addClass("odd");
	})
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
					<s:radio name="reportType" list="#{'0':'Call Codes','1':'Category (CFS...)'}" value="0" />
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
		<tfoot>
			<tr>
				<th>TOTAL</th>
				<c:set var="totalTotal" value="0" />
				<c:forEach items="${results.itemList}" var="item">
					<c:set var="totalItemKey" value="0:${item.id}" />
					<c:set var="totalValue" value="${results.officerCounts[totalItemKey]}" />
					<th>
						<c:choose>
							<c:when test="${totalValue.itemTotal > 0}">
								<c:out value="${totalValue.itemTotal}" />
								<c:set var="totalTotal" value="${totalTotal + totalValue.itemTotal}" />
							</c:when>
							<c:otherwise>0</c:otherwise>
						</c:choose>
					</th>
				</c:forEach>
				<th><c:out value="${totalTotal}" />
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${results.officerList}" var="officer">
				<c:set var="officerTotal" value="0" />
				<tr>
				    <td><c:out value="${officer.fullNameReverse}" /></td>
					<c:forEach items="${results.itemList}" var="item">
						<c:set var="officerItemKey" value="${officer.id}:${item.id}" />
						
						<c:set var="resValue" value="${results.officerCounts[officerItemKey]}" />

						<td>
							<c:out value="${resValue.officerItemTotal}" />
							<c:if test="${resValue.officerItemTotal > 0}">
								<c:set var="officerTotal" value="${resValue.officerTotal}" />
								<span class="pct-val" title="% of officer total <c:out value='${resValue.officerItemTotal}' /> / <c:out value='${resValue.officerTotal}' />">(<fmt:formatNumber type="percent" maxFractionDigits="1" value="${resValue.officerItemTotal/resValue.officerTotal}" />)</span>
								<span class="pct-val" title="% of item total <c:out value='${resValue.officerItemTotal}' /> / <c:out value='${resValue.itemTotal}' />">(<fmt:formatNumber type="percent" maxFractionDigits="1" value="${resValue.officerItemTotal/resValue.itemTotal}" />)</span>
							</c:if>
						</td>
					</c:forEach>
				    <th><c:out value="${officerTotal}" /></th>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>