<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Edit Patrol Activity</title>
    
    <sx:head />
    
    <!--  // these need to point to 172... for westchase office. TODO add to config file -->
 	<!--
    <script type="text/javascript" src="http://localhost/scripts/patrolActivity.js"></script>
    <link rel="stylesheet" type="text/css" href="http://localhost/styles/patrolActivity.css" />
    -->
    <script type="text/javascript" src="http://172.25.16.64/scripts/patrolActivity.js"></script>
    <link rel="stylesheet" type="text/css" href="http://172.25.16.64/styles/patrolActivity.css" />
</head>

<body>

<c:choose>
<c:when test="${not empty currentPatrolActivity.id}">
	<h1>Edit Patrol - <c:out value="${currentPatrolActivity.id}" /></h1>
</c:when>
<c:otherwise>
	<h1>New Patrol</h1>
</c:otherwise>
</c:choose>

<p><a href="/westchase/patrol/listActivity?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="listActivity"/>">Back to List</a></p>
<p><a href="<s:url action="editActivity-" includeParams="none"/>">Create new Patrol Activity</a></p>

<s:if test="hasActionErrors()">
   <div class="message-section error">
      <s:actionerror />
   </div>
</s:if>
<c:if test="${not empty WCActionWarning}">
   <div class="message-section actionmessages warnings">
      <c:out value="${WCActionWarning}" />
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

<s:form name="editForm" action="saveActivity" theme="simple" id="patrol-activity-form">
	<c:if test="${not empty currentPatrolActivity.id}">
	    <s:hidden name="currentPatrolActivity.id"/>
	</c:if>
	
	<div class="officer-patrol-info">
		<h2>Officer Patrol Info</h2>
		
		<table class="header-fields">
			<tbody>
				<tr>
					<td>
						<label for="officer-id">Officer</label>
						<s:select list="availableOfficers" name="currentPatrolActivity.officer.id" id="officer-id"
						    headerKey="-1" headerValue="-- Please Select --" listValue="fullNameReverse" listKey="id" emptyOption="false" 
						    required="true" tabindex="1" />
					</td>
					<td>
						<label for="patrol-type-id">Patrol Type</label>
						<s:select list="availablePatrolTypes" name="currentPatrolActivity.patrolType.id" id="patrol-type-id"
						    headerKey="-1" headerValue="-- Please Select --" listValue="name" listKey="id" emptyOption="false" 
						    required="true" tabindex="2" />
						<!--  <label for="patrol-type-desc">Description</label> -->
						<!-- <s:textfield name="currentPatrolActivity.patrolTypeDesc" id="patrol-type-desc" tabindex="3" size="10" /> -->
					</td>
				</tr>
			</tbody>
		</table>
		
		<table class="header-fields duty-hours">
			<tbody>
				<tr>
					<th>Duty Hours</th>
					<td><label>Start: 
						<sx:datetimepicker type="date" name="startDate" id="startDate" displayFormat="MMddyyyy" tabindex="4" valueNotifyTopics="/startDateChanged" />
						<!-- <sx:datetimepicker type="time" name="startTime" id="startTime" displayFormat="HH:mm" tabindex="5" /> -->
						<s:textfield name="startTime" id="startTime" size="4" maxlength="4" tabindex="6" onblur="calculateDutySpan()" />
						</label></td>
					<td><label>End:
						<sx:datetimepicker type="date" name="endDate" id="endDate" displayFormat="MMddyyyy" tabindex="5" />
						<!-- <s:hidden name="endDate" id="endDate" />  -->
						<!-- <sx:datetimepicker type="time" name="endTime" id="endTime" displayFormat="HH:mm" tabindex="7" /> -->
						<s:textfield name="endTime" id="endTime" size="4" maxlength="4" tabindex="7" onblur="calculateDutySpan()" />
						</label></td>
					<td><span id="dutySpan" class="calculated">&#160;</span></td>
				</tr>
			</tbody>
		</table>
		
		<div class="field">
			<label for="phone-num">Phone #</label>
			<s:select list="availablePatrolPhones" name="currentPatrolActivity.patrolPhone.id" id="phone-num"
			    headerKey="-1" headerValue="--" listValue="name" listKey="id" emptyOption="false" 
			    tabindex="8" />
		</div>
		
		<table class="header-fields miles">
			<tbody>
				<tr>
					<th>Miles</th>
					<td><label>Start: <s:textfield name="currentPatrolActivity.startMiles" id="startMiles" tabindex="9" size="5" onblur="calculateMileSpan()" /></label></td>
					<td><label>End: <s:textfield name="currentPatrolActivity.endMiles" id="endMiles" tabindex="10" size="5" onblur="calculateMileSpan()" /></label></td>
					<td><span id="milesSpan" class="calculated">&#160</span></td>
				</tr>
			</tbody>
		</table>
		
		<table class="header-fields shop">
			<tbody>
				<tr>
					<th><label for="shop-shop">Shop #</label></th>
					<td>
						<s:select list="availablePatrolShops" name="currentPatrolActivity.patrolShop.id" id="phone-shop"
						    headerKey="-1" headerValue="--" listValue="name" listKey="id" emptyOption="false" 
						    required="true" tabindex="11" />
					</td>
					<td>
						<s:textfield name="currentPatrolActivity.patrolShopComments" maxlength="250" />
					</td>
				</tr>
			</tbody>
		</table>
	
	</div>
	
	
	
	<table class="activity-counts">
	
		<tbody>
		
			<tr>
			
				<td>
					<div class="count-section">
						<h2>Crime Arrest Activity</h2>
						<table>
							<tbody>
								<tr><th>Felony(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.crimeArrestsFelony" tabindex="13" /></td></tr>
								<tr><th>Class A/B Misd.</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.crimeArrestsClassAbMisdemeanor" tabindex="14" /></td></tr>
								<tr><th>Class C (No Tickets)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.crimeArrestsClassCTicket" tabindex="15" /></td></tr>
								<tr><th>Non-Traffic/DRT</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.crimeArrestsTrafficDrt" tabindex="16" /></td></tr>
							</tbody>
						</table>
					</div>
					
					<div class="count-section">
						<h2>Warrants</h2>
						<table>
							<tbody>
								<tr><th>City Warrant(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.warrantsCity" tabindex="17" /></td></tr>
								<tr><th>Felony Warrant(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.warrantsFelony" tabindex="18" /></td></tr>
								<tr><th>Misd. Warrant(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.warrantsMisdemeanor" tabindex="19" /></td></tr>
								<tr><th>Setcic Warrant(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.warrantsSetcic" tabindex="20" /></td></tr>
							</tbody>
						</table>
					</div>
					
					<div class="count-section">
						<h2>DRT Investigations</h2>
						<table>
							<tbody>
								<tr><th>Warnings</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.drtInvestigationsWarnings" tabindex="21" /></td></tr>
								<tr><th>Abatements</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.drtInvestigationsAbatements" tabindex="22" /></td></tr>
								<tr><th>DRT Tickets</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.drtInvestigationsTickets" tabindex="23" /></td></tr>
								<tr><th>Offense Reports</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.drtInvestigationsOffenseReports" tabindex="24" /></td></tr>
							</tbody>
						</table>
					</div>
				</td>
				
				<td>
					<div class="count-section">
						<h2>Field Activity</h2>
						<table>
							<tbody>
								<tr><th>Parking</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.fieldParking" tabindex="25" /></td></tr>
								<tr><th>Charges Filed (Dims)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.fieldChargesFiled" tabindex="26" /></td></tr>
								<tr><th>Suspects Placed in Jail</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.fieldSuspectsInJail" tabindex="27" /></td></tr>
								<tr><th>Hold(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.fieldHolds" tabindex="28" /></td></tr>
								<tr><th>Traffic Stops</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.fieldTrafficStops" tabindex="29" /></td></tr>
							</tbody>
						</table>
					</div>
					
					<div class="count-section">
						<h2>Traffic Enforcement</h2>
						<table>
							<tbody>
								<tr><th>Moving</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.trafficMoving" tabindex="30" /></td></tr>
								<tr><th>Non Moving <span class="field-desc">(Parking Citations)</span></th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.trafficNonMoving" tabindex="31" /></td></tr>
							</tbody>
						</table>
					</div>
					
					<div class="count-section" id="hike">
						<h2>Hike and Bike Trail Patrol</h2>
						<table>
							<tbody>
								<tr>
									<th>Start1</th>
									<td>
										<sx:datetimepicker type="date" name="hikeDateStart1" id="hikeDateStart1" displayFormat="MMddyyyy" tabindex="32" />
										<s:textfield name="hikeTimeStart1" id="hikeTimeStart1" size="4" maxlength="4" tabindex="33" onblur="calculateHikeSpan(1)" />
									</td>
								</tr>
								<tr>
									<th>End1</th>
									<td>
										<sx:datetimepicker type="date" name="hikeDateEnd1" id="hikeDateEnd1" displayFormat="MMddyyyy" tabindex="34" />
										<s:textfield name="hikeTimeEnd1" id="hikeTimeEnd1" size="4" maxlength="4" tabindex="35" onblur="calculateHikeSpan(1)" />
									</td>
								</tr>
								<tr>
									<td colspan="2"><span id="hikeSpan1" class="calculated">&#160;</span></td>
								</tr>
							</tbody>

							<tbody>
								<tr>
									<th>Start2</th>
									<td>
										<sx:datetimepicker type="date" name="hikeDateStart2" id="hikeDateStart2" displayFormat="MMddyyyy" tabindex="36" />
										<s:textfield name="hikeTimeStart2" id="hikeTimeStart2" size="4" maxlength="4" tabindex="37" onblur="calculateHikeSpan(2)" />
									</td>
								</tr>
								<tr>
									<th>End2</th>
									<td>
										<sx:datetimepicker type="date" name="hikeDateEnd2" id="hikeDateEnd2" displayFormat="MMddyyyy" tabindex="38" />
										<s:textfield name="hikeTimeEnd2" id="hikeTimeEnd2" size="4" maxlength="4" tabindex="39" onblur="calculateHikeSpan(2)" />
									</td>
								</tr>
								<tr>
									<td colspan="2"><span id="hikeSpan2" class="calculated">&#160;</span></td>
								</tr>
							</tbody>
							

							<tbody>
								<tr>
									<th>Start3</th>
									<td>
										<sx:datetimepicker type="date" name="hikeDateStart3" id="hikeDateStart3" displayFormat="MMddyyyy" tabindex="40" />
										<s:textfield name="hikeTimeStart3" id="hikeTimeStart3" size="4" maxlength="4" tabindex="41" onblur="calculateHikeSpan(3)" />
									</td>
								</tr>
								<tr>
									<th>End3</th>
									<td>
										<sx:datetimepicker type="date" name="hikeDateEnd3" id="hikeDateEnd3" displayFormat="MMddyyyy" tabindex="42" />
										<s:textfield name="hikeTimeEnd3" id="hikeTimeEnd3" size="4" maxlength="4" tabindex="43" onblur="calculateHikeSpan(3)" />
									</td>
								</tr>
								<tr>
									<td colspan="2"><span id="hikeSpan3" class="calculated">&#160;</span></td>
								</tr>
							</tbody>
						</table>
					</div>
				
				</td>
				
				<td>
					<div class="count-section">
						<h2>Patrol Activity</h2>
						<table>
							<tbody>
								<tr><th>Primary Calls</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.primaryCalls" tabindex="44" /></td></tr>
								<tr><th>Secondary Calls</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.secondaryCalls" tabindex="45" /></td></tr>
								<tr><th>On-Views/Flagged Down</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.onViewsFlaggedDown" tabindex="46" /></td></tr>
								<tr><th>Incident Report(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.incidentReports" tabindex="47" /></td></tr>
								<tr><th>Accident Report(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.accidentReports" tabindex="48" /></td></tr>
								<tr><th>Supplement Report(s)</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.supplementReports" tabindex="49" /></td></tr>
							</tbody>
						</table>
					</div>
					
					<div class="count-section">
						<h2>Directed Patrol Activity</h2>
						<table>
							<tbody>
								<tr><th>Crime Initiatives <span class="field-desc">(Scheduled Special Operations, Apartment Initiatives, etc.)</span></th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.crimeInitiatives" tabindex="50" /></td></tr>
								<tr><th>Crime Initiatives <span class="field-desc">(In Westchase Vehicle)</span></th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.crimeInitiativesInWcVehicle" tabindex="51" /></td></tr>
								<tr><th>Administrative Assignments</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.adminAssignments" tabindex="52" /></td></tr>
								<tr><th>A.M. Checklist Completed (Vagrants)</th><td><s:checkbox name="currentPatrolActivity.amChecklistCompleted" tabindex="53" /></td></tr>
								<tr><th>Business Checks Completed</th>
									<td class="east-west">
										<label>East<s:checkbox name="currentPatrolActivity.businessChecksCompletedEast" tabindex="54" /></label>
										<label>West<s:checkbox name="currentPatrolActivity.businessChecksCompletedWest" tabindex="55" /></label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
									
				</td>
			
			</tr>
			
			<tr>
			
				<td colspan="3" class="community-services">
				
					<div class="count-section">
						<h2>Commnuity Services</h2>
					
						<table>
							<tbody>
			
								<tr>
									<td>
										<table>
											<tbody>
												<tr><th>Citizen Contacts</th><td><s:select list="availableCountsBig" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityCitizenContacts" tabindex="60" /></td></tr>
												<tr><th>Crime Prevention Pamphlets</th><td><s:select list="availableCountsBig" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityCrimePreventionPamphlets" tabindex="61" /></td></tr>
												<tr><th>Apartment Liaison Meetings</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityApartmentLiaisonMeetings" tabindex="56" /></td></tr>
											</tbody>
										</table>
									</td>
									
									<td>
										<table>
											<tbody>
												<tr><th>Hotel Liaison Meetings</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityHotelLiaisonMeetings" tabindex="57" /></td></tr>
												<tr><th>Retail Liaison Meetings</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityRetailLiaisonMeetings" tabindex="58" /></td></tr>
												<tr><th>Office Building Liaison Meetings</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityOfficeBuildingLiasonMeetings" tabindex="59" /></td></tr>
											</tbody>
										</table>				
									</td>
									
									<td>
										<table>
											<tbody>
												<tr><th>Community Events</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityEvents" tabindex="62" /></td></tr>
												<tr><th>CPTED Inspections</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityCptedInspections" tabindex="63" /></td></tr>
												<tr><th>Crime Prevention Seminars</th><td><s:select list="availableCounts" emptyOption="false" headerKey="0" headerValue="0" required="true" name="currentPatrolActivity.communityCrimePreventionSeminars" tabindex="64" /></td></tr>
											</tbody>
										</table>				
									</td>
								</tr>
								
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			
		</tbody>
	
	</table>

	<s:submit value="Save" />
</s:form>

<div class="patrol-details">
	<h3>Patrol Details</h3>
	<table>
		<thead>
		    <tr>
		        <th>ID</th>
		        <th>Incident ID</th>
		        <th>Type</th>
		        <th>Category</th>
		        <th>Received</th>
		        <th>Arrived</th>
		        <th>Cleared</th>
		    </tr>
	    </thead>
	    <tbody>
			<s:iterator value="currentPatrolActivityDetails" status="status" id="det">
				<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
					<td><a href="<s:url action="editActivityDetail-%{id}" namespace="/patrol" />"><s:property value="id"/></a></td>
					<td><a href="<s:url action="editActivityDetail-%{id}" namespace="/patrol" />"><s:property value="incidentId"/></a></td>
					<td><s:property value="patrolDetailType.name"/></td>
		            <td><s:property value="patrolDetailCategory.name"/></td>
		            <td><s:date name="receivedDateTime" format="HH:mm" /></td>
		            <td><s:date name="arrivedDateTime" format="HH:mm" /></td>
		            <td><s:date name="clearedDateTime" format="HH:mm" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<c:if test="${not empty currentPatrolActivity.id}">
	    <a href="/westchase/patrol/editActivityDetail.action?patrolActivityId=<c:out value='${currentPatrolActivity.id}' />">Add new Detail</a>
	</c:if>
	
</div>


<p><a href="/westchase/patrol/listActivity?useLast=1">Back to Current List</a></p>
<p><a href="<s:url action="listActivity"/>">Back to List</a></p>

</body>
</html>
