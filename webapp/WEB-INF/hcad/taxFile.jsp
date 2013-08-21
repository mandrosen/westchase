<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Generate Tax File</title>
    <s:head/>
</head>

<body>
<h1>Generate Tax File</h1>


<s:form name="generateFile" namespace="/hcad" action="taxFile/generate" method="post" enctype="multipart/form-data">
	<s:file name="taxFile" label="Tax File" />
	<s:file name="addressFile" label="Address File" />
	<s:file name="exemptionFile" label="Exemption File" />
	<s:textfield name="assessmentRate" label="Assessment Rate" />
    <s:submit value="Generate" />
</s:form>

</body>
</html>