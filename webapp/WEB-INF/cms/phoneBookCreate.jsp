<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Create Phone Book</title>
    <s:head/>
</head>

<body>


<h1>New Phone Book / Company</h1>

<s:form name="editForm" action="savenew">
<tr>
	<td>
		Phone Book Entry		
		<table>
<s:textfield label="title" name="currentPhoneBook.title" size="3" maxlength="50" required="true" />
<s:textfield label="First Name" name="currentPhoneBook.firstName" size="50" maxlength="50" required="true"/>
<s:textfield label="Initial" name="currentPhoneBook.middleInitial" size="1" maxlength="1"/>
<s:textfield label="Last Name" name="currentPhoneBook.lastName" size="50" maxlength="50" required="true"/>
<s:textfield label="Suffix" name="currentPhoneBook.suffix" maxlength="10" size="10" />
<s:textfield label="Salutation" name="currentPhoneBook.salutation" maxlength="50" size="50" />
<s:select label="Gender" list="availableGenders" name="currentPhoneBook.gender"
    headerKey="Unsure" headerValue="Select Gender" listValue="value" listKey="key" emptyOption="false" />	

<s:textfield label="Department" name="currentPhoneBook.department" maxlength="75" size="75"/>
<s:textfield label="Job Title" name="currentPhoneBook.jobTitle"  maxlength="50" size="50"/>
<s:textfield label="Email" name="currentPhoneBook.email" size="75" maxlength="255"/>
<s:textfield label="Alt Email" name="currentPhoneBook.altEmail" size="75" maxlength="255"/>
<s:checkbox fieldValue="true" label="Dont Email" name="currentPhoneBook.dontEmail"/>

<s:textfield label="Work Phone" name="currentPhoneBook.wkPhone" size="12" maxlength="50" required="true"/>
<s:textfield label="Work Ext" name="currentPhoneBook.wkext" size="3" maxlength="50"/>
<s:textfield label="Direct Phone" name="currentPhoneBook.directPhone" size="12" maxlength="50"/>
<s:textfield label="Fax Phone" name="currentPhoneBook.faxPhone" size="12" maxlength="50" />
<s:textfield label="Cell Phone" name="currentPhoneBook.mobilePhone" size="12" maxlength="50"/>

<s:textfield label="Home Address" name="currentPhoneBook.homeAddress" size="60" maxlength="255" />
<s:textfield label="Home Phone" name="currentPhoneBook.homePhone" size="12" maxlength="50"/>
<s:textfield label="Home Fax" name="currentPhoneBook.homeFax" size="12" maxlength="50"/>
<%--
<s:checkbox fieldValue="true" label="Investor" name="currentPhoneBook.investor"/>
<s:checkbox fieldValue="true" label="Westchase Today" name="currentPhoneBook.westchaseToday"/>
--%>
<s:textfield label="Notes" name="currentPhoneBook.notes" size="75" maxlength="255" />
<s:textfield label="Other" name="currentPhoneBook.other" size="75" maxlength="255" />
	
<s:optiontransferselect 
      label="Categories"
      name="leftSideCategories"
      leftTitle="Available Categories"
      rightTitle="Current Categories"
      list="availableCategories"
      listKey="categoryCode"
      listValue="categoryDesc"
      
      headerKey="headerKey"
      headerValue="--- Please Select ---"
      doubleName="selectedCategories"
      doubleList="currentCategories"
      doubleListKey="categoryCode"
      doubleListValue="categoryDesc"
      doubleHeaderKey="doubleHeaderKey"
      doubleHeaderValue="--- Please Select ---" />
    </table>
    </td>
    
    <td>
		Company Entry
		<table>
    <s:textfield label="Name" name="currentCompany.company" required="true" size="100" maxlength="100" />
    
    <s:textfield label="St Number" name="currentCompany.stNumber" size="50" maxlength="50"/>
    <!-- 
    <s:textfield label="St Address" name="currentCompany.stAddress" required="true" size="75" maxlength="150" />
     -->
    <s:combobox label="St Address" name="currentCompany.stAddress" headerValue="-- Please Select --" headerKey=""
			list="availableStreets" listValue="name" listKey="name"
		    required="true"/>
		
    <s:textfield label="Room No" name="currentCompany.roomNo" size="50" maxlength="50"/>
    <s:textfield label="City" name="currentCompany.city" required="true" size="100" maxlength="100" />
    <s:select label="State" list="availableStates" name="currentCompany.state" 
		    headerKey="" headerValue="-- Please Select --" listValue="name" listKey="code" emptyOption="false"
		    required="true"/>    
    
    <s:textfield label="Zip Code" name="currentCompany.zipCode" required="true" size="10" maxlength="10" />
    
    <s:textfield label="Website" name="currentCompany.website" size="75" maxlength="255"/>
    
    <s:textfield label="Work Phone" name="currentCompany.wkPhone" size="15" maxlength="15" required="true" />
    <s:textfield label="Fax Phone" name="currentCompany.faxPhone" size="15" maxlength="15"/>
    
    <s:textfield label="Owner" name="currentCompany.owner" size="50" maxlength="50"/>
    <s:textfield label="Center" name="currentCompany.center" size="50" maxlength="50"/>
    
    <s:textfield label="HCAD" name="currentCompany.hcad" size="50" maxlength="50"/>
    <s:textfield label="MapNo" name="currentCompany.mapNo" size="50" maxlength="50"/>
    <s:textfield label="NAICS" name="currentCompany.naics" size="5" maxlength="5"/>
    <s:select label="Classification" list="availableClassifications" name="currentCompany.classification" 
		    headerKey="" headerValue="-- Please Select --" listValue="value" listKey="key" emptyOption="false"
		    required="true"/>
    <s:select label="Sub Classification" list="availableSubClassifications" name="currentCompany.subClassification" 
		    headerKey="" headerValue="-- Please Select --" listValue="value" listKey="key" emptyOption="false"/>
    
    <s:textfield label="Square Feet" name="currentCompany.squareFeet" size="10" maxlength="10"/>
    <s:textfield label="No Employees" name="currentCompany.noEmployees" size="10" maxlength="10"/>
    
    <s:checkbox fieldValue="true" label="Vendor" name="currentCompany.vendor"/>
    <s:textfield label="Notes" name="currentCompany.notes" size="75" maxlength="255" />
    <s:textfield label="Other" name="currentCompany.other" size="75" maxlength="255" />
    	</table>
	</td>
	</tr>

    <tr><td><s:submit value="Save" theme="simple" /></td></tr>
</s:form>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
