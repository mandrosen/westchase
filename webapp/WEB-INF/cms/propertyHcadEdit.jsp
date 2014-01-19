<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Property Hcad</title>
    <s:head/>
    
    <script type="text/javascript">
	function confirmremove(id) {
		if (confirm("Are you sure you want to remove this hcad?")) {
			document.location.href = "/westchase/propertyHcad/delete.action?id=" + id;
		}
	}
    </script>
</head>

<body>
	<h1>Edit Property Hcad</h1>
	
	<p><a href="/westchase/property/edit-${propertyHcad.property.id}">Back to Property</a></p>
	
	<s:if test="hasActionErrors()">
	   <div class="message-section error">
	      <s:actionerror />
	   </div>
	</s:if>
	<c:if test="${not empty WCActionWarning}">
	   <div class="message-section actionmessages warnings">
	      <c:out value="${WCActionWarning}" escapeXml="false" />
	      <c:remove var="WCActionWarning" />
	   </div>
	</c:if>
	
	<s:if test="hasActionMessages()">
	   <div class="message-section actionmessages">
	      <s:actionmessage />
	   </div>
	</s:if>
	
	<c:if test="${not empty WCActionMessage}">
	   <div class="message-section actionmessages">
	      <c:out value="${WCActionMessage}" />
	      <c:remove var="WCActionMessage" />
	   </div>
	</c:if>
	
	<s:form name="editForm" action="save">
	    <s:hidden name="propertyHcad.id" />
	    <s:hidden name="propertyHcad.property.id" />
	    <s:textfield label="HCAD" name="propertyHcad.hcad" size="50" maxlength="50" required="true"/>
	    <s:textarea label="Notes" name="propertyHcad.notes"></s:textarea>
	    <s:submit value="Save" />
	</s:form>

</body>
</html>
