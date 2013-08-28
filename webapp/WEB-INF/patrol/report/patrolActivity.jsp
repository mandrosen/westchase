<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Patrol Activity Report</title>
    
    <sx:head />
    
	<script type="text/javascript">
	function exportreport(typ) {
		document.getElementById('typeparam').value=typ;
		document.getElementById('frm').submit();
	}
	</script>
    
	<style type="text/css">
	
	</style>
</head>

<body>

	<h2>Patrol Activity Report</h2>
	
	<s:form action="activityReport" id="frm" theme="simple">
		<s:hidden name="type" id="typeparam" />
		<table>
			<tr>
				<th>Officer</th>
				<td>
					<s:select name="officerIdList" headerKey="-1" headerValue="--" 
					    list="availableOfficers" listValue="fullNameReverse" listKey="id" emptyOption="false" 
					    multiple="true" size="10" />
				</td>
			</tr>
			<tr>
				<th>Start Date <span class="format">(mm/dd/yyyy)</span></th>
				<td>
					<sx:datetimepicker name="startDate" displayFormat="MM/dd/yyyy"/>
				</td>
			</tr>
			<tr>
				<th>End Date <span class="format">(mm/dd/yyyy)</span></th>
				<td>
					<sx:datetimepicker name="endDate" displayFormat="MM/dd/yyyy"/>
				</td>
			</tr>
			
			
			<tr>
				<th>Patrol Type</th>
				<td>
					<s:select name="patrolTypeIdList" headerKey="-1" headerValue="--" 
					    list="availablePatrolTypes" listValue="name" listKey="id" emptyOption="false" 
					    multiple="true" size="10" />
		    	</td>
    		</tr>
			
		    <tr><td colspan="2"><s:submit value="Run" onclick="$('#typeparam').val('')" /></td></tr>
		    <tr><td colspan="2"><input type="button" value="Export to Excel" onclick="exportreport('excel')" /></td></tr>
	    </table>
	</s:form>
	
	<table class="results" id="results_table">
		<thead>
			<tr>
				<th colspan="4">&#160;</th>
				
				<th colspan="4">Crime Arrest Activity</th>
				<th colspan="4">Warrants</th>
				<th colspan="4">DRT Investigations</th>
				<th colspan="5">Field Activity</th>
				<th colspan="2">Traffic</th>
				<th colspan="6">Patrol Activity</th>
				<th colspan="6">Directed Patrol Activity</th>
				<th colspan="9">Community Services</th>
			</tr>
		    <tr>
				<th>Officer</th> 
				<th>Duty Hours</th> 
				<th>Miles</th>
				<th>Hike/Bike Patrol Hours</th> 
				
				

				<th>General Patrol Count</th>
				<th>Bike Patrol Count</th>
				<th>Apt Init Count</th>
				<th>Special Ops Count</th>
				<th>Event Count</th>
				<th>Other Count</th>
				
				
				
			    <th>Felony</th>
			    <th>Class A/B Misdemeanor</th>
			    <th>Class C (No Ticket)</th>
			    <th>Non-Traffic/DRT</th>
			    
			    <th>City</th>
			    <th>Felony</th>
			    <th>Misdemeanor</th>
			    <th>SETCIC</th>
			    
			    <th>Warnings</th>
			    <th>Abatements</th>
			    <th>Tickets</th>
			    <th>Offense Reports</th>
			    
			    <th>Parking</th>
			    <th>Charges Filed</th>
			    <th>Suspects In Jail</th>
			    <th>Holds</th>
			    <th>Traffic Stops</th>
			    
			    <th>Moving</th>
			    <th>Non-Moving</th>
			    
			    <th>Primary Calls</th>
			    <th>Secondary Calls</th>
			    <th>On Views Flagged Down</th>
			    <th>Incident Reports</th>
			    <th>Accident Reports</th>
			    <th>Supplement Reports</th>
			    
			    <th>Crime Initiatives</th>
			    <th>Crime Initiatives In WC Vehicle</th>
			    <th>admin Assignments</th>
			    <th>AM Checklist Completed</th>
			    <th>Business Checks Completed East</th>
			    <th>Business Checks Completed West</th>
			    
			    <th>Apartment Liaison Meetings</th>
			    <th>Hotel Liaison Meetings</th>
			    <th>Retail Liaison Meetings</th>
			    <th>Office Building Liason Meetings</th>
			    <th>Citizen Contacts</th>
			    <th>Crime Prevention Pamphlets</th>
			    <th>Events</th>
			    <th>CPTED Inspections</th>
			    <th>Crime Prevention Seminars</th>
		    </tr>
		</thead>
		<tbody>
		    <s:iterator value="results" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		 
				    <td><s:property value="officer.fullNameReverse"/></td>
				    <td><s:property value="dutyHours"/></td>
				    <td><s:property value="miles"/></td>
				    <td><s:property value="hikePatrolHours"/></td>
				    
				    
					<td><s:property value="generalPatrolCount"/></td>
					<td><s:property value="bikePatrolCount"/></td>
					<td><s:property value="aptInitCount"/></td>
					<td><s:property value="specialOpsCount"/></td>
					<td><s:property value="eventCount"/></td>
					<td><s:property value="otherCount"/></td>
				    
				    <td><s:property value="crimeArrestsFelony"/></td>
				    <td><s:property value="crimeArrestsClassAbMisdemeanor"/></td>
				    <td><s:property value="crimeArrestsClassCTicket"/></td>
				    <td><s:property value="crimeArrestsTrafficDrt"/></td>
				    <td><s:property value="warrantsCity"/></td>
				    <td><s:property value="warrantsFelony"/></td>
				    <td><s:property value="warrantsMisdemeanor"/></td>
				    <td><s:property value="warrantsSetcic"/></td>
				    <td><s:property value="drtInvestigationsWarnings"/></td>
				    <td><s:property value="drtInvestigationsAbatements"/></td>
				    <td><s:property value="drtInvestigationsTickets"/></td>
				    <td><s:property value="drtInvestigationsOffenseReports"/></td>
				    <td><s:property value="fieldParking"/></td>
				    <td><s:property value="fieldChargesFiled"/></td>
				    <td><s:property value="fieldSuspectsInJail"/></td>
				    <td><s:property value="fieldHolds"/></td>
				    <td><s:property value="fieldTrafficStops"/></td>
				    <td><s:property value="trafficMoving"/></td>
				    <td><s:property value="trafficNonMoving"/></td>
				    <td><s:property value="primaryCalls"/></td>
				    <td><s:property value="secondaryCalls"/></td>
				    <td><s:property value="onViewsFlaggedDown"/></td>
				    <td><s:property value="incidentReports"/></td>
				    <td><s:property value="accidentReports"/></td>
				    <td><s:property value="supplementReports"/></td>
				    <td><s:property value="crimeInitiatives"/></td>
				    <td><s:property value="crimeInitiativesInWcVehicle"/></td>
				    <td><s:property value="adminAssignments"/></td>
				    <td><s:property value="amChecklistCompleted"/></td>
				    <td><s:property value="businessChecksCompletedEast"/></td>
				    <td><s:property value="businessChecksCompletedWest"/></td>
				    <td><s:property value="communityApartmentLiaisonMeetings"/></td>
				    <td><s:property value="communityHotelLiaisonMeetings"/></td>
				    <td><s:property value="communityRetailLiaisonMeetings"/></td>
				    <td><s:property value="communityOfficeBuildingLiasonMeetings"/></td>
				    <td><s:property value="communityCitizenContacts"/></td>
				    <td><s:property value="communityCrimePreventionPamphlets"/></td>
				    <td><s:property value="communityEvents"/></td>
				    <td><s:property value="communityCptedInspections"/></td>
				    <td><s:property value="communityCrimePreventionSeminars"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table> 

</body>
</html>
