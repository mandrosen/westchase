<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Generate Tax Flat File</title>
    <s:head/>
</head>

<body>
<h1>Generate Tax Flat File</h1>


<s:form name="generateFlatFile" namespace="/hcad" action="taxFlatFile/generate" method="post" enctype="multipart/form-data">
	<s:file name="taxExcelFile" label="Tax Excel File" />
    <s:submit value="Generate" />
</s:form>

</body>
</html>