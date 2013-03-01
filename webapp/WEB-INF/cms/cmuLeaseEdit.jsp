<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Cmu Lease</title>
    <s:head/>
</head>

<body>
<h1>Edit Cmu Lease</h1>


<s:form name="editForm" action="save">
    <s:hidden name="currentCmuLease.id"/>
    <s:textfield label="Owners Rep" name="currentCmuLease.ownersRep"/>
    <s:textfield label="Tenants Rep" name="currentCmuLease.tenantsRep"/>
    <s:textfield label="Tenants Name" name="currentCmuLease.tenantName"/>
    <s:textfield label="Sq Ft" name="currentCmuLease.sqFt"/>

    <s:select label="Quarter" list="availableQuarters" name="currentCmuLease.quarter.id" 
    headerKey="" headerValue="-- Please Select --" listValue="description" listKey="id" emptyOption="true"/>

    <s:select label="Property" list="availableProperties" name="currentCmuLease.property.id" 
    headerKey="" headerValue="-- Please Select --" listValue="buildingName" listKey="id" emptyOption="true"/>

    <s:select label="Transaction" list="availableTransactionTypes" name="currentCmuLease.cmuTransactionType.id" 
    headerKey="" headerValue="-- Please Select --" listValue="description" listKey="id" emptyOption="true"/>
    

    <s:submit value="Save" />
</s:form>
<p><a href="/westchase/cmuLease/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
