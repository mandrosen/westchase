<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Edit Westchase CMU Devsite</title>

</head>

<body>

<h1>Westchase CMU Report - Devsite Edit</h1>

<p><a href="devsiteQuarter-<c:out value='${cmuDevsite.cmuQuarter.id}' />.action">Back to Devsites</a></p>

<s:form name="cmuDevsiteForm" action="devsiteSave">
	<s:hidden name="cmuDevsite.id"/>
	<s:hidden name="cmuDevsite.property.id"/>
	<s:hidden name="cmuDevsite.cmuQuarter.id"/>
	
	<tr>
		<th>Property</th>
		<td>#<s:property value="cmuDevsite.property.id" />: <s:property value="cmuDevsite.property.buildingName" /></td>
	</tr>
	<tr>
		<th>Quarter</th>
		<td><s:property value="cmuDevsite.cmuQuarter.year" />-<s:property value="cmuDevsite.cmuQuarter.quarterNum" /></td>
	</tr>	
	
	<s:checkbox label="Verified" name="verified" />
	
	<s:textfield label="Completed By" name="cmuDevsite.completedBy" size="100" maxlength="255" />
	<s:textfield label="Site Size" name="cmuDevsite.siteSize" size="10" maxlength="20" />
	
	<s:textfield label="Frontage" name="cmuDevsite.frontage" />
	<s:textfield label="Contact" name="cmuDevsite.contact" size="100" maxlength="255" />
	<s:textfield label="Company" name="cmuDevsite.company" size="100" maxlength="255" />
	<s:textfield label="Phone" name="cmuDevsite.phone" size="20" maxlength="20" />
	<s:textfield label="Fax" name="cmuDevsite.fax" size="20" maxlength="20" />
	<s:textfield label="Email" name="cmuDevsite.email" size="100" maxlength="255" />
	<s:checkbox label="Divide" name="cmuDevsite.divide" />
	<s:textfield label="Price Sq. Ft." name="cmuDevsite.priceSqFt" size="10" maxlength="20" />
	
	<s:textfield label="Comments" name="cmuDevsite.restrictions" size="100" maxlength="255" />
	
	<s:textarea label="Comments" name="cmuDevsite.comments" rows="5" cols="60" />
	
	<s:submit />
</s:form>

<p><a href="devsiteQuarter-<c:out value='${cmuDevsite.cmuQuarter.id}' />.action">Back to Devsites</a></p>

</body>
</html>