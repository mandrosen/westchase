<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Generate Address File</title>
    <s:head/>
</head>

<body>
<h1>Generate Address File</h1>


<s:form name="generateFile" namespace="/hcad" action="addressFile/generate" method="post" enctype="multipart/form-data">
	<s:file name="addressFile" label="Address File" />
	<s:file name="exemptionFile" label="Exemption File" />
    <s:submit value="Generate" />
</s:form>

</body>
</html>