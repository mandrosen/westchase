<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>My Sent Email</title>
	<s:head />
</head>

<body>

<div class="sentemail">
<h1>Sent Email</h1>

<strong>Sent Date</strong>&#160;<fmt:formatDate value="${sentEmail.sentEmail.sentDate}" pattern="MMM dd, yyyy HH:mm" />
<table>
	<thead>
	<tr>
		<td><strong>From</strong>&#160;
			<c:out value="${sentEmail.sentEmail.fromAddress}" />
		</td>
		<td><strong>To</strong>&#160;
			<c:out value="${sentEmail.sentEmail.toAddress}" />
		</td>
	</tr>
	<tr>
		<td colspan="2"><strong>Subject</strong>&#160;
			<c:out value="${sentEmail.sentEmail.subject}" />
		</td>
	</tr>
	<tr>
		<td>Attachment</td>
		<td><a href="<s:url action="sentEmailViewAttachment-%{sentEmail.sentEmail.id}" />" target="_blank"><c:out value="${sentEmail.sentEmail.attachmentName}" /></a></td>
	</tr>
	</thead>
	<tbody>
	<tr><td colspan="2">
<pre>
	<c:out value="${sentEmail.sentEmail.message}" escapeXml="false" />
</pre>	
	</td></tr></tbody>
</table>

</div>

</body>
</html>