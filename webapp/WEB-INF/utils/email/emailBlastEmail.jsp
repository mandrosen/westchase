<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Email Blast</title>
    <s:head />
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		function sendTestEmail() {
			var testEmail = document.getElementById("test-email").value;
			if (testEmail == null || testEmail == "") {
				alert("You must fill in test email to send a test");
				return false;
			}
			document.getElementById("send-test").value = "1";
			document.getElementById("email_form").submit();
			return true;
		}
	</script>
</head>
<body>

<h1>Email Blast</h1>

<s:form action="emailBlastSend" theme="simple" enctype="multipart/form-data" method="post" id="email_form">
	<div>
		<s:hidden name="sendTest" value="0" id="send-test" />
		<table>
		    <tr><td>Contacts</td><td><table>
		    <thead><tr><th>Email</th><th>Last Name</th><th>First Name</th><th>Salutation</th><th>Company</th></tr></thead>
		    <tbody>
		        <s:iterator value="contacts">
		 			<tr><td><input type="hidden" name="selectedPeople" value="<c:out value='${id}' />" />
		 			<s:property value="email"/></td>
				    <td><s:property value="lastName"/></td>
				    <td><s:property value="firstName"/></td>
				    <td><s:property value="salutation"/></td>
				    <td><s:property value="company"/></td></tr>
				</s:iterator>
			</tbody>
		    	</table>
		    	<p><c:out value="${selectedCount}" /> contacts</p>
		    </td></tr>
		    <tr><td></td><td>
		    <div style="border: 1px solid #ccc; padding: 2px 5px">
		    <p>You can use the following fields in your email template that will be replaced with actual values when the email is sent.</p>
		    <dl>
		    	<dt>[[title]]</dt><dd>Contact's title</dd>
		    	<dt>[[salutation]]</dt><dd>Contact's salutation</dd>
		    	<dt>[[firstname]]</dt><dd>Contact's first name</dd>
		    	<dt>[[lastname]]</dt><dd>Contact's last name</dd>
		    	<dt>[[company]]</dt><dd>Contact's company name</dd>
		    	<dt>[[email]]</dt><dd>Contact's email address</dd>
		    </dl></div>
		    </td></tr>
		    <tr><td>From Me</td><td><s:checkbox fieldValue="true" name="fromMe"/></td></tr>
		    <tr><td>Subject</td><td><s:textfield name="emailSubject" size="60" maxlength="155" /></td></tr>
		    <tr><td>Attachment</td><td><s:file name="upload" /></td></tr>
		    
		    <tr><td>Message</td>
		    	<td>
		    		<textarea name="emailMessage" rows="6" cols="100"><s:property value="emailMessage" /></textarea>
		    	</td>
		    </tr>
		    <tr><td>Test Email</td><td><s:textfield name="testEmail" size="60" maxlength="255" id="test-email" /></td></tr>
		    <tr><td colspan="2"><s:submit value="Send" /><input type="button" value="Send Test" onclick="sendTestEmail()" /></td></tr>
		</table>
	</div>
</s:form>
<script type="text/javascript">
	window.onload = function()
	{
		CKEDITOR.replace('emailMessage',
	    {
	        filebrowserBrowseUrl : '/westchase/ckeditorBrowse.action',
	        filebrowserUploadUrl : '/westchase/ckeditorUpload.action',
	        on :
	        {
	            instanceReady : function( ev )
	            {
	                // Output paragraphs as <p>Text</p>.
	                this.dataProcessor.writer.setRules( 'p',
	                    {
	                        indent : false,
	                        breakBeforeOpen : false,
	                        breakAfterOpen : false,
	                        breakBeforeClose : false,
	                        breakAfterClose : false
	                    });
	            }
	        }
	    });
				    
	};
</script>
</body>

</html>