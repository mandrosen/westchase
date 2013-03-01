<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Generate Map Xml</title>
    
    <sx:head />
    
    <style type="text/css">
    .result p a {
    	cursor: pointer;
    	text-decoration: underline;
    	color: #00f;
    }
    
    </style>
    
    <script type="text/javascript">
    function copyResultToClipboard() {
    	window.clipboardData.setData("Text", document.getElementById("resultxml").value);
    }
    </script>
</head>

<body>
<h1>Generate Map Xml</h1>


<s:form name="xmlForm">
	<%-- //<s:select label="Property Type" list="availablePropertyTypes" name="propertyTypeId" 
		    //headerKey="" headerValue="-- Please Select --" listValue="name" listKey="id" /> --%>
	<s:select label="Company Type" list="availableCompanyTypes" name="companyTypeId" 
		    headerKey="" headerValue="-- Please Select --" listValue="name" listKey="id" />
		    
    <s:submit value="Submit" />
</s:form>

<c:if test="${not empty xml}">
	<div class="result">
		<h3>Result</h3>
		<p><a onclick="copyResultToClipboard()">Copy to Clipboard</a></p>
		<textarea id="resultxml" rows="20" cols="50"><c:out value="${xml}" escapeXml="false"/></textarea>
	</div>
</c:if>
</body>
</html>
