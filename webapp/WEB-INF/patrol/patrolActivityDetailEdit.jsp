<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Patrol Detail</title>
    
    <sx:head />
    
    <!--  // these need to point to 172... for westchase office. TODO add to config file -->
    <!-- 
    <script type="text/javascript" src="http://localhost/scripts/patrolActivityDetail.js"></script>
    -->
    <script type="text/javascript" src="http://172.25.16.64/scripts/patrolActivityDetail.js"></script>
</head>

<body>

	<c:choose>
	<c:when test="${not empty currentPatrolActivityDetail.id}">
		<h1>Edit Patrol Detail - <c:out value="${currentPatrolActivityDetail.id}" /></h1>
	</c:when>
	<c:otherwise>
		<h1>New Patrol Detail</h1>
	</c:otherwise>
	</c:choose>
	
	<p><a href="/westchase/patrol/editActivity-${patrolActivityId}">Back to Patrol Activity</a></p>
	<p><a href="/westchase/patrol/listActivity?useLast=1">Back to Current Patrol Activity List</a></p>
	
	<p>
	    <a href="/westchase/patrol/editActivityDetail.action?patrolActivityId=<c:out value='${patrolActivityId}' />">Add new Detail</a>
	</p>
	<p><a href="<s:url action="editActivity-" includeParams="none"/>">Create new Patrol Activity</a></p>
	
	
	<s:if test="hasActionErrors()">
	   <div class="message-section error">
	      <s:actionerror />
	   </div>
	</s:if>
	
	<s:if test="hasActionMessages()">
	   <div class="message-section actionmessages">
	      <s:actionmessage />
	   </div>
	</s:if>
	
	<s:form action="saveActivityDetail">
		<c:if test="${not empty currentPatrolActivityDetail.id}">
			<s:hidden name="currentPatrolActivityDetail.id" />
		</c:if>
		<c:if test="${not empty patrolActivityId}">
			<s:hidden name="patrolActivityId" />
		</c:if>
		<c:if test="${not empty patrolActivityDetail.id}">
			<s:hidden name="patrolActivityDetail.id" />
		</c:if>
		<c:if test="${not empty currentPatrolActivityDetail.patrolActivity.id}">
			<s:hidden name="currentPatrolActivityDetail.patrolActivity.id" />
		</c:if>
	
	
		<s:select label="Category" list="availableDetailCategories" name="currentPatrolActivityDetail.patrolDetailCategory.id"
		    headerKey="-1" headerValue="--" listValue="name" listKey="id" emptyOption="false" 
		    required="true" />
		
		<tr>
			<td class="tdLabel">Received Date</td>
			<td>
		<select name="receivedDate">
			<c:forEach items="${availableDates}" var="d">
				<option value="<fmt:formatDate pattern='yyyy-MM-dd' value='${d}' />"><fmt:formatDate pattern='ddMMyyyy' value='${d}' /></option>
			</c:forEach>
		</select>
			</td>
		</tr>
		
		<s:textfield label="Received (HHMM)" name="receivedTime" size="5" maxlength="5" />
		
		
		
		<tr>
			<td class="tdLabel">Arrived Date</td>
			<td>
		<select name="arrivedDate">
			<c:forEach items="${availableDates}" var="d">
				<option value="<fmt:formatDate pattern='yyyy-MM-dd' value='${d}' />"><fmt:formatDate pattern='ddMMyyyy' value='${d}' /></option>
			</c:forEach>
		</select>
			</td>
		</tr>
		<s:textfield label="Arrived (HHMM)" name="arrivedTime" size="5" maxlength="5" />
		
		
		
		<tr>
			<td class="tdLabel">Cleared Date</td>
			<td>
		<select name="clearedDate">
			<c:forEach items="${availableDates}" var="d">
				<option value="<fmt:formatDate pattern='yyyy-MM-dd' value='${d}' />"><fmt:formatDate pattern='ddMMyyyy' value='${d}' /></option>
			</c:forEach>
		</select>
			</td>
		</tr>
		<s:textfield label="Cleared (HHMM)" name="clearedTime" size="5" maxlength="5" />
		
		<s:select label="Officer Role" list="#{'Primary':'Primary', 'Secondary':'Secondary'}" name="currentPatrolActivityDetail.officerRole"
		    headerKey="" headerValue="--" emptyOption="false"/>
	
		<s:select label="Type" list="availableDetailTypes" name="currentPatrolActivityDetail.patrolDetailType.id"
		    headerKey="-1" headerValue="--" listValue="summaryString" listKey="id" emptyOption="false" 
		    required="true" />
		    
		<s:textfield label="Disposition" name="currentPatrolActivityDetail.disposition" />
		    
		<s:textfield label="Incident ID" name="currentPatrolActivityDetail.incidentId" required="false" />
		
		<s:select label="Property" list="availableProperties" name="currentPatrolActivityDetail.property.id" 
   			headerKey="" headerValue="--" 
   			listValue="summaryStringForPublicSafety" listKey="id" emptyOption="false" required="true" />
		    
		<s:textfield label="Location Description" name="currentPatrolActivityDetail.locationDesc" />
		
		<s:textarea label="Comments" name="currentPatrolActivityDetail.comments" rows="5" cols="75" required="true"></s:textarea>
			
		<s:submit value="Save" />
		<%-- 
		<s:optiontransferselect 
			label="Citizens"
			name="leftSideCitizens"
			leftTitle="Available Citizens"
			rightTitle="Current Citizens"
			list="availableCitizens"
			listKey="id"
			listValue="summaryValue"
			   
			headerKey="headerKey"
			headerValue="--- Please Select ---"
			doubleName="selectedCitizens"
			doubleList="currentCitizens"
			doubleListKey="id"
			doubleListValue="summaryValue"
			doubleHeaderKey="doubleHeaderKey"
			doubleHeaderValue="--- Please Select ---"
			
			allowAddAllToLeft="true"
			allowAddAllToRight="false"
			allowSelectAll="false" />
			
		<s:submit value="Save" />
		--%>
		    
	</s:form>
	
	<p>
	    <a href="/westchase/patrol/editActivityDetail.action?patrolActivityId=<c:out value='${patrolActivityId}' />">Add new Detail</a>
	</p>
	<p><a href="<s:url action="editActivity-" includeParams="none"/>">Create new Patrol Activity</a></p>

</body>
</html>