<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>

<html>
<head>
	<title>My Sent Emails</title>
	<sx:head />
</head>

<body>
<div class="sentemails">

<h1>My Sent Emails</h1>

<s:form action="sentEmailsList">
	<sx:datetimepicker label="Start Date" name="startDateStr" displayFormat="yyyy-MM-dd"/>
	<sx:datetimepicker label="End Date" name="endDateStr" displayFormat="yyyy-MM-dd"/>
	<s:textfield label="To Email" name="toEmail" maxlength="255" size="50" />
	<s:submit value="Search" />
</s:form>

<table>
<thead>
	<tr>
		<th>Date</th>
		<th>To</th>
		<th>Subject</th>
		<th>Attachment</th>
		<th></th>
	</tr>
</thead>
<tbody>
    <s:iterator value="sentEmails" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 			<td><s:date name="sentDate" format="yyyy-MM-dd HH:mm" /></td>
 			<td><s:property value="toAddress" /></td>
 			<td><s:property value="subject" /></td>
 			<td><a href="<s:url action="sentEmailViewAttachment-%{id}" />" target="_blank"><s:property value="attachmentName" /></a></td>
            <td><a href="<s:url action="sentEmailView-%{id}" />">View</a></td>
        </tr>
    </s:iterator>
</tbody>
</table>

</div>
</body>
</html>