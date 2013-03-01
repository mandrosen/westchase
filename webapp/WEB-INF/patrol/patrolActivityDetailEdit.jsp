<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Patrol Detail</title>
    
    <sx:head />
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
		    headerKey="-1" headerValue="-- Please Select --" listValue="name" listKey="id" emptyOption="false" 
		    required="true" />
		
		<!-- 
		<sx:datetimepicker label="Received" type="time" name="currentPatrolActivityDetail.receivedTime" displayFormat="HH:mm" />
		
		<sx:datetimepicker label="Arrived" type="time" name="currentPatrolActivityDetail.arrivedTime" displayFormat="HH:mm" />
		
		<sx:datetimepicker label="Cleared" type="time" name="currentPatrolActivityDetail.clearedTime" displayFormat="HH:mm" />
		-->
		<s:textfield label="Received (HHMM)" name="receivedTime" size="5" maxlength="5" />
		<s:textfield label="Arrived (HHMM)" name="arrivedTime" size="5" maxlength="5" />
		<s:textfield label="Cleared (HHMM)" name="clearedTime" size="5" maxlength="5" />
		
		<s:select label="Officer Role" list="#{'Primary':'Primary', 'Secondary':'Secondary'}" name="currentPatrolActivityDetail.officerRole"
		    headerKey="" headerValue="-- Please Select --" emptyOption="false" required="true" />
	
		<s:select label="Type" list="availableDetailTypes" name="currentPatrolActivityDetail.patrolDetailType.id"
		    headerKey="-1" headerValue="-- Please Select --" listValue="summaryString" listKey="id" emptyOption="false" 
		    required="true" />
		    
		<s:textfield label="Disposition" name="currentPatrolActivityDetail.disposition" />
		    
		<s:textfield label="Incident ID" name="currentPatrolActivityDetail.incidentId" required="false" />
		
		<s:select label="Property" list="availableProperties" name="currentPatrolActivityDetail.property.id" 
   			headerKey="" headerValue="-- Please Select --" 
   			listValue="summaryString" listKey="id" emptyOption="false" required="true" />
		    
		<s:textfield label="Location Description" name="currentPatrolActivityDetail.locationDesc" />
		
		<s:textarea label="Comments" name="currentPatrolActivityDetail.comments" rows="5" cols="75" required="true"></s:textarea>
		
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
		    
	</s:form>

</body>
</html>