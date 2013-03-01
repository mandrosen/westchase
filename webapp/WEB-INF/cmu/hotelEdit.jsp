<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Edit Westchase CMU Hotel</title>

</head>

<body>

<h1>Westchase CMU Report - Hotel Edit</h1>

<p><a href="hotelQuarter-<c:out value='${cmuHotel.cmuQuarter.id}' />.action">Back to Hotels</a></p>

<s:form name="cmuHotelForm" action="hotelSave">
	<s:hidden name="cmuHotel.id"/>
	<s:hidden name="cmuHotel.property.id"/>
	<s:hidden name="cmuHotel.cmuQuarter.id"/>
	
	<tr>
		<th>Property</th>
		<td>#<s:property value="cmuHotel.property.id" />: <s:property value="cmuHotel.property.buildingName" /></td>
	</tr>
	<tr>
		<th>Quarter</th>
		<td><s:property value="cmuHotel.cmuQuarter.year" />-<s:property value="cmuHotel.cmuQuarter.quarterNum" /></td>
	</tr>	
	
	<s:checkbox label="Verified" name="verified" />
	
	<s:textfield label="Completed By" name="cmuHotel.completedBy" size="100" maxlength="255" />
	
	<s:textfield label="General Manager" name="cmuHotel.generalMgr" size="100" maxlength="255" />
	<s:textfield label="General Manager Email" name="cmuHotel.generalMgrEmail" size="100" maxlength="255" />
	<s:textfield label="General Manager Phone" name="cmuHotel.generalMgrPhone" size="20" maxlength="20" />
	
	<s:textfield label="Occupancy" name="cmuHotel.occupancy" size="10" maxlength="20" />
	
	<s:textarea label="Comments" name="cmuHotel.comments" rows="5" cols="60" />
	
	<s:submit />
</s:form>

<p><a href="hotelQuarter-<c:out value='${cmuHotel.cmuQuarter.id}' />.action">Back to Hotels</a></p>

</body>
</html>