<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <title>Phone Book List</title>
    
    <sx:head />
    
    <script type="text/javascript">
		function confirmremove(pbid) {
			if (confirm("Are you sure you want to remove this phonebook?")) {
				document.location.href = "/westchase/phonebook/delete.action?delId=" + pbid;
			}
		}
    </script>
</head>
<body>

<h1>Phone Book List</h1>

<p><a href="<s:url action="list"/>">Refresh</a></p>
<p><a href="<s:url action="create" includeParams="none"/>">Create new Phone Book /Company Entry</a></p>
<p><a href="<s:url action="edit-" includeParams="none"/>">Create new Phone Book Entry</a></p>

<p class="total"><s:property value="totalCount" /> total results</p>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="first" action="first">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="dontEmailSearch" value="%{dontEmailSearch}"/>
			<s:param name="investorSearch" value="%{investorSearch}"/>
			<s:param name="westchaseTodaySearch" value="%{westchaseTodaySearch}"/>
			<s:param name="orphanedOnly" value="%{orphanedOnly}"/>
		</s:url>
		<s:a href="%{first}">First</s:a>
		
		<s:url id="prev" action="prev">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="dontEmailSearch" value="%{dontEmailSearch}"/>
			<s:param name="investorSearch" value="%{investorSearch}"/>
			<s:param name="westchaseTodaySearch" value="%{westchaseTodaySearch}"/>
			<s:param name="orphanedOnly" value="%{orphanedOnly}"/>
		</s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="dontEmailSearch" value="%{dontEmailSearch}"/>
			<s:param name="investorSearch" value="%{investorSearch}"/>
			<s:param name="westchaseTodaySearch" value="%{westchaseTodaySearch}"/>
			<s:param name="orphanedOnly" value="%{orphanedOnly}"/>
		</s:url> 
		<s:a href="%{next}">Next</s:a>
		
		<s:url id="goToPage" action="goToPage">
			<s:param name="nextPage" value="%{maxPage - 1}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="dontEmailSearch" value="%{dontEmailSearch}"/>
			<s:param name="investorSearch" value="%{investorSearch}"/>
			<s:param name="westchaseTodaySearch" value="%{westchaseTodaySearch}"/>
			<s:param name="orphanedOnly" value="%{orphanedOnly}"/>
		</s:url>
		<s:a href="%{goToPage}">Last</s:a>
	</s:if>
</div>


<s:form method="list" theme="simple" action="list">
<table class="results">
<thead>
	<tr>
		<th colspan="23" style="text-align: left">
			<label>
				Updated by Me?
				<s:checkbox name="updatedByMe" />
			</label>
			<label>
				Orphaned?
				<s:checkbox name="orphanedOnly" />
			</label>
		</th>
	</tr>
    <tr>
	    <th><s:textfield name="searchObject.id" size="4" /></th>
	    <th><s:textfield name="searchObject.title" size="2" /></th>
	    <th><s:textfield name="searchObject.firstName" size="10"/></th>
	   	<th><s:textfield name="searchObject.middleInitial" size="1"/></th>
	    <th><s:textfield name="searchObject.lastName" size="10"/></th>
	    <th><s:textfield name="searchObject.suffix" size="2"/></th>
	    <th><s:textfield name="searchObject.salutation" size="8"/></th>
	    <th><s:textfield name="searchObject.company.company" size="10"/></th>
	   	<th><s:textfield name="searchObject.department" size="10"/></th>
	  	<th><s:textfield name="searchObject.jobTitle" size="10"/></th>
	   	<th>
	   	<select name="dontEmailSearch">
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>
	   	<th><s:textfield name="searchObject.email" size="50"/></th>
	   	<th><s:textfield name="searchObject.altEmail" size="10"/></th>
	   	<th><s:textfield name="searchObject.wkPhone" size="10"/></th>
	   	<th><s:textfield name="searchObject.faxPhone" size="10"/></th>
	   	<th><s:textfield name="searchObject.mobilePhone" size="10"/></th>
	   	<th><s:textfield name="searchObject.homePhone" size="10"/></th>
	   	<th><s:textfield name="searchObject.homeFax" size="10"/></th>
	   	<th><s:textfield name="searchObject.homeAddress" size="10"/></th>
	   	<%--
	   	<th><select name="investorSearch"/>
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>
	   	<th><select name="westchaseTodaySearch"/>
	   		<option value=""></option>
	   		<option value="true">true</option>
	   		<option value="true">false</option>
	   	</select></th>
	   	--%>
	   	<th><sx:datetimepicker name="searchObject.inputDate" displayFormat="MM/dd/yyyy"/></th>
	   	<th><sx:datetimepicker name="searchObject.lastupdate" displayFormat="MM/dd/yyyy"/></th>
		<th><s:submit value="Search" /></th>
    </tr>
    <tr>
    	
    	<s:url action="sort" id="sortid"><s:param name="orderCol" value="'id'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
        <s:url action="sort" id="sorttitle"><s:param name="orderCol" value="'title'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
        <s:url action="sort" id="sortfirstname"><s:param name="orderCol" value="'firstName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortinitial"><s:param name="orderCol" value="'middleInitial'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortlastname"><s:param name="orderCol" value="'lastName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortsuffix"><s:param name="orderCol" value="'suffix'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortsalutation"><s:param name="orderCol" value="'salutation'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortcompany"><s:param name="orderCol" value="'companyName'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortdept"><s:param name="orderCol" value="'department'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortjobtitle"><s:param name="orderCol" value="'jobTitle'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortdontemail"><s:param name="orderCol" value="'dontEmail'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortemail"><s:param name="orderCol" value="'email'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortaltemail"><s:param name="orderCol" value="'altEmail'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
		<s:url action="sort" id="sortwkphone"><s:param name="orderCol" value="'wkPhone'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortfaxph"><s:param name="orderCol" value="'faxPhone'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>    	
    	<s:url action="sort" id="sortmobilephone"><s:param name="orderCol" value="'mobilePhone'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sorthomeph"><s:param name="orderCol" value="'homePhone'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sorthomefax"><s:param name="orderCol" value="'homeFax'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sorthomeaddr"><s:param name="orderCol" value="'homeAddress'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<%--
    	<s:url action="sort" id="sortinvestor"><s:param name="orderCol" value="'investor'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortwestchase"><s:param name="orderCol" value="'westchaseToday'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	--%>
    	<s:url action="sort" id="sortinpt"><s:param name="orderCol" value="'inputDate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	<s:url action="sort" id="sortupdt"><s:param name="orderCol" value="'lastupdate'"/><s:param name="page" value="%{page}"/><s:param name="currentOrderCol" value="%{currentOrderCol}"/></s:url>
    	
    	
    	
        <th><s:a href="%{sortid}">Id</s:a></th>
        <th><s:a href="%{sorttitle}">Title</s:a></th>
        <th><s:a href="%{sortfirstname}">First Name</s:a></th>
        <th><s:a href="%{sortinitial}">Initial</s:a></th>
        <th><s:a href="%{sortlastname}">Last Name</s:a></th>
        <th><s:a href="%{sortsuffix}">Suffix</s:a></th>
        <th><s:a href="%{sortsalutation}">Salutation</s:a></th>
        <th><s:a href="%{sortcompany}">Company Name</s:a></th>
        <th><s:a href="%{sortdept}">Department</s:a></th>
        <th><s:a href="%{sortjobtitle}">Job Title</s:a></th>
        <th><s:a href="%{sortdontemail}">Dont Email</s:a></th>
        <th><s:a href="%{sortemail}">Email</s:a></th>
        <th><s:a href="%{sortaltemail}">Alt Email</s:a></th>
        <th><s:a href="%{sortwkphone}">Work Phone</s:a></th>
        <th><s:a href="%{sortfaxph}">Fax Phone</s:a></th>
        <th><s:a href="%{sortmobilephone}">Mobile Phone</s:a></th>
        <th><s:a href="%{sorthomeph}">Home Phone</s:a></th>
        <th><s:a href="%{sorthomefax}">Home Fax</s:a></th>
        <th><s:a href="%{sorthomeaddr}">Home Address</s:a></th>
        <%--
        <th><s:a href="%{sortinvestor}">Investor</s:a></th>
        <th><s:a href="%{sortwestchase}">Westchase Today</s:a></th>
        ---%>
        <th><s:a href="%{sortinpt}">Input Date</s:a></th>
        <th><s:a href="%{sortupdt}">Update Date</s:a></th>
        <th></th>
    </tr>
</thead>
<tbody>
    <s:iterator value="phoneBooks" status="status" id="pb">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
 
            <td><a href="<s:url action="edit-%{id}" />"><s:property value="id"/></a></td>
            <td><s:property value="title"/></td>
            <td><s:property value="firstName"/></td>
            <td><s:property value="middleInitial"/></td>
            <td><s:property value="lastName"/></td>
            <td><s:property value="suffix"/></td>
            <td><s:property value="salutation"/></td>
            
			<td>
				<c:choose>
					<c:when test="${not empty pb.company.id}"><c:out value="${pb.company.company}" /> [<s:property value="company.id"/>]</c:when>
					<c:otherwise>
						<s:property value="companyName"/>
					</c:otherwise>
				</c:choose>
			</td>
            <td><s:property value="department"/></td>
            <td><s:property value="jobTitle"/></td>
            <td><s:property value="dontEmail"/></td>
            <td><s:property value="email"/></td>
            <td><s:property value="altEmail"/></td>
            <td><s:property value="wkPhone"/> x<s:property value="wkExt"/></td>
            <td><s:property value="faxPhone"/></td>
            <td><s:property value="mobilePhone"/></td>
            <td><s:property value="homePhone"/></td>
            <td><s:property value="homeFax"/></td>
            <td><s:property value="homeAddress"/></td>
            <%--
            <td><s:property value="investor"/></td>
            <td><s:property value="westchaseToday"/></td>
            --%>
            <td><s:date name="inputDate" format="MM/dd/yyyy" /></td>
            <td><s:date name="updatedate" format="MM/dd/yyyy" /></td>
            <td>
           		<a href="<s:url action="edit-%{id}" />">Edit</a>
            	<a href="javascript:confirmremove(${pb.id})">Delete</a>
            	<a href="/westchase/phoneBookRelations/list-${pb.id}">Relations</a>
            </td>
        </tr>
    </s:iterator>
</tbody>
</table>    
<s:submit value="Search" />
</s:form>
<div class="prevnext">
	<s:if test="%{page > 0}">
		<s:url id="prev" action="prev">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="dontEmailSearch" value="%{dontEmailSearch}"/>
			<s:param name="investorSearch" value="%{investorSearch}"/>
			<s:param name="westchaseTodaySearch" value="%{westchaseTodaySearch}"/>
			<s:param name="orphanedOnly" value="%{orphanedOnly}"/>
		</s:url>
		<s:a href="%{prev}">Previous</s:a>
	</s:if>  
	<s:if test="%{page < (maxPage - 1)}">
		<s:url id="next" action="next">
			<s:param name="page" value="%{page}"/>
			<s:param name="currentOrderCol" value="%{currentOrderCol}"/>
			<s:param name="dontEmailSearch" value="%{dontEmailSearch}"/>
			<s:param name="investorSearch" value="%{investorSearch}"/>
			<s:param name="westchaseTodaySearch" value="%{westchaseTodaySearch}"/>
			<s:param name="orphanedOnly" value="%{orphanedOnly}"/>
		</s:url> 
		<s:a href="%{next}">Next</s:a>
	</s:if>
</div>
<p><a href="<s:url action="create" includeParams="none"/>">Create new Phone Book Entry</a></p>

</body>
</html>