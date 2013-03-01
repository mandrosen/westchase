<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Phone Book</title>
    <s:head/>  
    <script type="text/javascript" src='/westchase/dwr/interface/CMSUtilities.js'></script>
	<script type="text/javascript" src='/westchase/dwr/engine.js'></script>
	
	<script type="text/javascript">
		function setCompany() {
			var comp = document.getElementById("save_currentPhoneBook_company_id").value;
			CMSUtilities.getCompanyName(comp,
				function setCompanyName(data) {
					var instpt = document.getElementById("edit_comp_link");
					instpt.removeChild(instpt.firstChild);
					var instdiv = document.createElement("div");
					instdiv.appendChild(document.createTextNode("Edit "));
					var lnk = document.createElement("a");
					lnk.setAttribute("href", "/westchase/company/edit-" + comp + ".action");
					lnk.appendChild(document.createTextNode(data));
					instdiv.appendChild(lnk);
					instpt.appendChild(instdiv);
				}
			);

			<c:if test="${not empty currentPhoneBook.id}">
			var pbid = <c:out value="${currentPhoneBook.id}" />;
			CMSUtilities.getProperties(pbid, comp,
				function setProperties(data) {
					var instpt = document.getElementById("comp_props");
					instpt.removeChild(instpt.firstChild);
					//var instdiv = document.createElement("div");
					//var insttbl = document.createElement("table");
					for (var i = 0; i < data.length; i++) {
						var tr = document.createElement("tr");
						var td1 = document.createElement("td");
						var p = data[i];
						//alert(p.property.id);
						//alert(p.property.buildingName);
						var lbl = document.createElement("label");
						lbl.className = "comp-prop";
						var ipt = document.createElement("input");
						ipt.setAttribute("type", "checkbox");
						ipt.setAttribute("name", "selectedProperties_" + i);
						ipt.setAttribute("value", p.property.id);
						if (p.selected) {
							ipt.setAttribute("checked", "checked");
						}
						lbl.appendChild(ipt);
						td1.appendChild(lbl);
						tr.appendChild(td1);
						
						var td2 = document.createElement("td");
						td2.appendChild(document.createTextNode(p.property.id));
						tr.appendChild(td2);

						var td3 = document.createElement("td");
						td3.appendChild(document.createTextNode(p.property.buildingName));
						tr.appendChild(td3);

						var td4 = document.createElement("td");

						var inpt2 = document.createElement("input");
						inpt2.setAttribute("type", "text");
						inpt2.setAttribute("name", "selectedProperties_suiteNum_" + i);
						if (p.selected && p.suiteNumber != null) {
							inpt2.setAttribute("value", p.suiteNumber);
						}
						td4.appendChild(inpt2);

						tr.appendChild(td4);

						//insttbl.appendChild(tr);
						instpt.appendChild(tr);
					}
					//instdiv.appendChild(insttbl);
					//instpt.appendChild(instdiv);
					document.getElementById("phonebook_prop_count").value=data.length;
				}
			);
			</c:if>
		}

		onload = function() {
			setCompany();
		}
	</script>
    <script type="text/javascript">
		function confirmremove(cmid) {
			if (confirm("Are you sure you want to remove this property?")) {
				document.location.href = "/westchase/companyProperty/remove-" + cmid + ".action?currentCompanyId=<c:out value='${currentCompany.id}' />";
			}
		}
		<c:if test="${not empty currentPhoneBook.id}">
		function confirmremovethis() {
			if (confirm("Are you sure you want to remove this phone book?")) {
				document.location.href = "/westchase/phonebook/delete.action?delId=" + <c:out value="${currentPhoneBook.id}" />;
			}
		}
		</c:if>
    </script>
    
</head>

<body>

<c:choose>
<c:when test="${not empty currentPhoneBook.id}">
	<h1>Edit Phone Book - <c:out value="${currentPhoneBook.id}" /></h1>
</c:when>
<c:otherwise>
	<h1>New Phone Book</h1>
</c:otherwise>
</c:choose>

<p><a href="/westchase/phonebook/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>

<s:form name="editForm" action="save">
	<c:if test="${not empty currentPhoneBook.id}">
	    <s:hidden name="currentPhoneBook.id"/>
	</c:if>
	<tr><td colspan="2"><input type="hidden" name="phoneBookPropCount" id="phonebook_prop_count" value="0" /></td></tr>
<s:textfield label="title" name="currentPhoneBook.title" size="3" maxlength="50" required="true" />
<s:textfield label="First Name" name="currentPhoneBook.firstName" size="50" maxlength="50" required="true"/>
<s:textfield label="Initial" name="currentPhoneBook.middleInitial" size="1" maxlength="1"/>
<s:textfield label="Last Name" name="currentPhoneBook.lastName" size="50" maxlength="50" required="true"/>
<s:textfield label="Suffix" name="currentPhoneBook.suffix" maxlength="10" size="10" />
<s:textfield label="Salutation" name="currentPhoneBook.salutation" maxlength="50" size="50" />

<s:select label="Gender" list="availableGenders" name="currentPhoneBook.gender"
    headerKey="Unsure" headerValue="Select Gender" listValue="value" listKey="key" emptyOption="false" />	

<s:textfield label="Company (deprecated)" name="currentPhoneBook.companyName"/>

<s:select list="availableCompanies" name="currentPhoneBook.company.id" 
		    headerKey="" headerValue="-- Please Select --" listValue="companyString" listKey="id" emptyOption="true" 
		    onchange="setCompany()"
		    required="true"/>
<tr><td>&#160;</td><td id="edit_comp_link"><div>&#160;</div></td></tr>

<tr><td>&#160;</td>
	<td>
		<table class="companyproperties">
			<thead><tr><th></th><th>MapNo</th><th>Property</th><th>Suite No</th></tr></thead>
			<tbody id="comp_props">
				<tr><td></td><td></td><td></td><td></td></tr>
			</tbody>
		</table>
	</td>
</tr>

<s:textfield label="Department" name="currentPhoneBook.department" maxlength="75" size="75"/>
<s:textfield label="Job Title" name="currentPhoneBook.jobTitle"  maxlength="50" size="50"/>
<s:textfield label="Email" name="currentPhoneBook.email" size="75" maxlength="255"/>
<s:textfield label="Alt Email" name="currentPhoneBook.altEmail" size="75" maxlength="255"/>
<s:checkbox fieldValue="true" label="Dont Email" name="currentPhoneBook.dontEmail"/>
<s:textfield label="Work Phone" name="currentPhoneBook.wkPhone" size="12" maxlength="50" required="true"/>
<s:textfield label="Work Ext" name="currentPhoneBook.wkext" size="3" maxlength="50"/>
<s:textfield label="Direct Phone" name="currentPhoneBook.directPhone" size="12" maxlength="50"/>
<s:textfield label="Fax Phone" name="currentPhoneBook.faxPhone" size="12" maxlength="50" required="true" />
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
      doubleHeaderValue="--- Please Select ---"

	  allowAddAllToLeft="true"
      allowAddAllToRight="false"
      allowSelectAll="false" />


	<tr><td class="tdLabel"><label class="label">Phone Book Relations:</label></td>
		<td>
			<c:choose>
				<c:when test="${not empty currentPhoneBook.phoneBookRelations}">
					<table class='phonebookrels'>
						<tbody>
				    		<s:iterator value="currentPhoneBook.phoneBookRelations" status="status" id="pbr">
								<tr>
									<td>(<s:property value="property.id" />) <s:property value="property.buildingName" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<span class='none'>No Current Relations</span>
				</c:otherwise>
			</c:choose>
			<p><a href="/westchase/phoneBookRelations/list-${currentPhoneBook.id}">Edit Relations</a></p>
		</td></tr>
	
    <tr><td><s:submit value="Save" theme="simple" /></td></tr>
    
			<c:if test="${not empty currentPhoneBook.id}">
    <tr><td colspan="2"><input type="button" value="Delete" onclick="confirmremovethis()" /></td></tr>
   
    </c:if>
</s:form>

<p><a href="/westchase/phonebook/list?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="list"/>">Back to List</a></p>
</body>
</html>
