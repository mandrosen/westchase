<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Cmu Occupancy</title>
    <s:head/>
</head>

<body>
<h1>Edit Cmu Occupancy</h1>


<s:form name="editForm" action="save">
    <s:hidden label="Id" name="currentCmuOccupancy.id"/>
    
    <s:textfield label="Occupancy" name="currentCmuOccupancy.occupancy" />
    
    <s:select label="Quarter" list="availableQuarters" name="currentCmuOccupancy.quarter.id" 
    headerKey="" headerValue="-- Please Select --" listValue="description" listKey="id" emptyOption="true"/>

    <s:select label="Property" list="availableProperties" name="currentCmuOccupancy.property.id" 
    headerKey="" headerValue="-- Please Select --" listValue="buildingName" listKey="id" emptyOption="true"/>
    
    <s:textfield label="Comments" name="currentCmuOccupancy.comments"/>
    

    <s:submit value="Save" />
</s:form>
<p><a href="/westchase/cmuOccupancy/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
