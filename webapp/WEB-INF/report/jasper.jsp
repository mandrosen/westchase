<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>Custom Report</title>
	</head>
	
	<body>
	
		<h1>Custom Report</h1>
		
		<%--
		<form method="post" action="/westchase/jasper/run" enctype="multipart/form-data">
			<input type="file" name="reportFile" />
			<input type="submit" value="Run" />
		</form>--%>
		
		
		<s:form name="reportForm" action="customReport" id="frm" theme="simple" method="post" enctype="multipart/form-data">
			<s:textfield name="jrxmlFileName" />
			<s:file name="upload" label="File"/>
			<s:textfield name="caption" label="Caption"/>
			<s:submit />
		</s:form>
	</body>

</html>