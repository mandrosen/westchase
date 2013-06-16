<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

<head>
	<title>Westchase</title>
    <link href="<s:url value='/styles/main.css' encode='false' includeParams='none'/>" rel="stylesheet" type="text/css" media="all"/>
    <sj:head />
    <decorator:head/>
</head>

<body>

	<div class="header">
		<div class="line">
			<div class="item logoimg">
				<a href="<s:url action="home" namespace="/" />"><img src="<s:url value="/images/w-logo_dk.gif" />" alt="" width="21" /></a>
				<h2>Westchase District</h2>
			</div>
			<div class="item loginstuff">
				<p>Logged in as: <c:out value="${LOGGED_IN_EMPLOYEE.firstName}" />
				<a href="<s:url action="logout" namespace="/" />">Logout</a></p>
			</div>
		</div>
	</div>

	<div class="content">
		<div class="top">
			<table class="main">
				<tr>
					<td class="links">
						<div class="cms">
							<h4><span>Manage Contacts</span></h4>
							<ul>
								<li><a href="<s:url action="list" namespace="/phonebook" />">Phone Book List</a></li>
								<li><a href="<s:url action="list" namespace="/company" />">Company List</a></li>
								<li><a href="<s:url action="list" namespace="/property" />">Property List</a></li>
								<li><a href="<s:url action="list" namespace="/category" />">Category List</a></li>
								<!-- 
								<li><a href="<s:url action="list" namespace="/quarter" />">Quarterly Information List</a></li>
								<li><a href="<s:url action="list" namespace="/cmuOccupancy" />">CMU Occupancy List</a></li>
								<li><a href="<s:url action="list" namespace="/cmuLease" />">CMU Lease List</a></li>
								-->
							</ul>
						</div>
						<div class="reports">
							<h4><span>Reports</span></h4>
							<ul>
								<li><a href="<s:url action="trespassAgreementMapNoInput" namespace="/reports" />">Trespass MapNo Report</a></li>
								<li><a href="<s:url action="emailContactsByCategoryCodeInput" namespace="/reports" />">Email Contacts by Category Code</a></li>
								<li><a href="<s:url action="contactsByCategoryCodeInput" namespace="/reports" />">Contacts by Category Code</a></li>
								<li><a href="<s:url action="wcBusinessContactsByNAICSInput" namespace="/reports" />">WC Contacts by NAICS</a></li>
								<li><a href="<s:url action="flagPoleContactsInput" namespace="/reports" />">Flag Pole Properties</a></li>
								<li><a href="<s:url action="companyByNameInput" namespace="/reports" />">Company By Name</a></li>
								<li><a href="<s:url action="officeBuildingPropertyInput" namespace="/reports" />">Office Building Property</a></li>
								
								<!-- 
								<li><a href="<s:url action="customReportInput" namespace="/reports" />">Custom Report</a></li>
								-->
							</ul>
						</div>
						<div class="utils">
							<h4><span>Utils</span></h4>
							<ul>
								<li><a href="<s:url action="emailBlastInput" namespace="/" />">Email Blast</a></li>
								<li><a href="<s:url action="sentEmailsList" namespace="/" />">My Sent Emails</a></li>
								<li><a href="<s:url action="list" namespace="/message" />">My Messages</a></li>
								<li><a href="<s:url action="list" namespace="/todo" />">My Todos</a></li>
								<li><a href="<s:url action="mapXml" namespace="/" />">Generate Map Xml</a></li>
							</ul>
						</div>
						<div class="cmu">
							<h4><span>CMU</span></h4>
							<ul>
								<li><a href="<s:url action="apartment" namespace="/cmu" />">Apartments Report</a></li>
								<li><a href="<s:url action="devsite" namespace="/cmu" />">Devsites Report</a></li>
								<li><a href="<s:url action="hotel" namespace="/cmu" />">Hotels Report</a></li>
								<li><a href="<s:url action="office" namespace="/cmu" />">Offices Report</a></li>
								<li><a href="<s:url action="retail" namespace="/cmu" />">Retail Centers Report</a></li>
								<li><a href="<s:url action="serviceCtr" namespace="/cmu" />">Service Centers Report</a></li>
								<li><a href="<s:url action="lease" namespace="/cmu" />">Lease Report</a></li>
								<li><a href="<s:url action="leaseStats" namespace="/cmu" />">Lease Stats Report</a></li>
							</ul>
						</div>
						<div class="public-safety">
							<h4><span>Public Safety</span></h4>
							<ul>
								<li><a href="<s:url action="listActivity" namespace="/patrol" />">Patrol Activity</a></li>
								<li><a href="<s:url action="listOfficer" namespace="/patrol" />">Officers</a></li>
								<li><a href="<s:url action="listCitizen" namespace="/patrol" />">Citizens</a></li>
								<li><a href="<s:url action="activityReportInput" namespace="/patrol" />">Report</a></li>
								<li><a href="<s:url action="officerDetailReportInput" namespace="/patrol" />">Detail Report</a></li>
							</ul>
						</div>
						<div class="hcad">
							<h4><span>HCAD</span></h4>
							<ul>
								<li><a href="<s:url action="addressFile/init" namespace="/hcad" />">Address File</a></li>
							</ul>
						</div>
						<% if (request.isUserInRole("admin")) { %>
						<div class="admin">
							<h4><span>Admin</span></h4>
							<ul>
								<li><a href="<s:url action="list" namespace="/employee" />">List Employees</a></li>
							</ul>
						</div>
						<% } %>
						<p class="backhome"><a href="<s:url action="home" namespace="/" />">Back to Home</a></p>
					</td>
					<td class="wrapper">
        				<decorator:body/>
        			</td>
        		</tr>
        	</table>
        </div>
	</div>

	<div class="footer">
		<p class="temphelp">
			If you are having an issue, please contact 
			<a href="mailto:mandrosen@gmail.com">Marc (mandrosen@gmail.com)</a> for help.
		</p>
		<p class="copyright">Copyright &#169; 2009-2013 Westchase District</p>
	</div>

</body>

</html>