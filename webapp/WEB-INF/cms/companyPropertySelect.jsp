<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Select Company Property</title>
    <s:head/>
</head>

<body>

	<div class="companyproperty">
		<h1>Select Company Property</h1>
		
		<div>
			<s:form action="companyPropertySave" theme="simple">
				<c:if test="${not empty companyPropertyId}">
				    <s:hidden name="companyPropertyId"/>
				</c:if>
			    <s:hidden name="currentCompanyId"/>
			    <s:hidden name="propertyId" value="-999" />
				<s:submit value="Select 'Outside District'" />
			</s:form>	
		</div>
		
		<div>
			<s:form action="companyPropertySave" theme="simple">
				<c:if test="${not empty companyPropertyId}">
				    <s:hidden name="companyPropertyId"/>
				</c:if>
			    <s:hidden name="currentCompanyId"/>
			    <label>
			    	<span>Property</span>
					<s:select list="availableProperties" name="propertyId" 
				    	headerKey="" headerValue="-- Please Select --" 
				    	listValue="summaryString" listKey="id" emptyOption="true"/>
				</label>
				<label>
					<span>Primary?</span>
					<s:checkbox name="primaryProp" />
				</label>
				<s:submit value="Save" />
			</s:form>
		</div>
	
	</div>
</body>

</html>