<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Lease Stats</title>
</head>

<body>

<h1>Westchase CMU Report - Lease Stats</h1>

<s:form name="cmuLeaseStatsForm" action="leaseStats" id="frm" theme="simple">
	<s:hidden name="type" id="typeparam" />
	<table>
		<tr><td>Quarter</td>
			<td>
				<s:select label="Quarter" name="quarterId" headerKey="-1" headerValue="-- Please Select --" 
				    list="cmuQuarters" listValue="description" listKey="id" emptyOption="false" 
				    multiple="false" />
			</td>
	    </tr>
	    <tr><td colspan="2"><s:submit value="Run" /></td></tr>
    </table>
</s:form>

<c:if test="${not empty leaseStats}">
	<table>
		<thead>
			<tr><th>&#160;</th><th>Count</th><th>Sq. Ft.</th></tr>
		</thead>
		<tbody>
			<c:if test="${not empty leaseStats.transTypeStats}">
				<c:forEach items="${leaseStats.transTypeStats}" var="stats">
					<tr>
						<th><c:out value="${stats.name}" /></th>
						<td><c:out value="${stats.count}" /></td>
						<td><c:out value="${stats.sqFt}" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<tr><td colspan="3">&#160;</td></tr>
			<c:if test="${not empty leaseStats.businessTypeStats}">
				<c:forEach items="${leaseStats.businessTypeStats}" var="stats">
					<tr>
						<th><c:out value="${stats.name}" /></th>
						<td><c:out value="${stats.count}" /></td>
						<td><c:out value="${stats.sqFt}" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</c:if>

</body>
</html>