dojo.event.topic.subscribe("/startDateChanged", function(textEntered, date, widget){
	var startDate = dojo.widget.byId("startDate");
	if (startDate && startDate.getValue() != "") {
		var endDate = dojo.widget.byId("endDate");
		if (endDate && endDate.getValue() == "") {
			endDate.setValue(startDate.getValue());
		}
		// update end date if it doesn't have a value
		var endDate = dojo.widget.byId("endDate");
		if (endDate && endDate.getValue() == "") {
			endDate.setValue(startDate.getValue());
		}

		// update hike and bike dates if they don't have values
		var hikeDateStart1 = dojo.widget.byId("hikeDateStart1");
		if (hikeDateStart1 && hikeDateStart1.getValue() == "") {
			hikeDateStart1.setValue(startDate.getValue());
		}
		var hikeDateEnd1 = dojo.widget.byId("hikeDateEnd1");
		if (hikeDateEnd1 && hikeDateEnd1.getValue() == "") {
			hikeDateEnd1.setValue(startDate.getValue());
		}
		
		// update hike and bike dates if they don't have values
		var hikeDateStart2 = dojo.widget.byId("hikeDateStart2");
		if (hikeDateStart2 && hikeDateStart2.getValue() == "") {
			hikeDateStart2.setValue(startDate.getValue());
		}
		var hikeDateEnd2 = dojo.widget.byId("hikeDateEnd2");
		if (hikeDateEnd2 && hikeDateEnd2.getValue() == "") {
			hikeDateEnd2.setValue(startDate.getValue());
		}
		
		// update hike and bike dates if they don't have values
		var hikeDateStart3 = dojo.widget.byId("hikeDateStart3");
		if (hikeDateStart3 && hikeDateStart3.getValue() == "") {
			hikeDateStart3.setValue(startDate.getValue());
		}
		var hikeDateEnd3 = dojo.widget.byId("hikeDateEnd3");
		if (hikeDateEnd3 && hikeDateEnd3.getValue() == "") {
			hikeDateEnd3.setValue(startDate.getValue());
		}

		
	}
});
$.strPad = function(i,l,s) {
	var o = i.toString();
	if (!s) { s = '0'; }
	while (o.length < l) {
		o = s + o;
	}
	return o;
};

function addTimeSpans(firstDay, secondDay) {
	//alert(firstDay);
	//alert(secondDay);
	if (firstDay != '' && secondDay != '') {
		var firstDaySplit = firstDay.split(":");
		var secondDaySplit = secondDay.split(":");
		if (firstDaySplit.length == 2 && secondDaySplit.length == 2) {
			var firstHours = parseInt(firstDaySplit[0]);
			var firstMins = parseInt(firstDaySplit[1]);
			var secondHours = parseInt(secondDaySplit[0]);
			var secondMins = parseInt(secondDaySplit[1]);

			var totalHours = firstHours + secondHours;
			var totalMins = firstMins + secondMins;
			if (totalMins >= 60) {
				totalMins = totalMins - 60;
				totalHours++;
			}
			return "" + $.strPad(totalHours, 2) + ":" + $.strPad(totalMins, 2);
		}
	}
	return "";
}

function calculateTimeSpan(startTime, endTime) {
	if (startTime != '' && endTime != '') {
		var startTimeSplit = startTime.split(":");
		var endTimeSplit = endTime.split(":");
		if (startTimeSplit.length == 2 && endTimeSplit.length == 2) {
			var startHours = parseInt(startTimeSplit[0]);
			var startMins = parseInt(startTimeSplit[1]);
			var endHours = parseInt(endTimeSplit[0]);
			var endMins = parseInt(endTimeSplit[1]);

			var hoursSpan = endHours - startHours;
			var minsSpan = 0;
			if (endMins > startMins) {
				minsSpan = endMins - startMins;
			} else if (endMins < startMins) {
				minsSpan = 60 - startMins + endMins;
				hoursSpan--;
			}

			return "" + $.strPad(hoursSpan, 2) + ":" + $.strPad(minsSpan, 2);
		}
	}
	return "";
}

function calculateDateTimeSpan(startDate, startTime, endDate, endTime) {
	//alert("s, s, e, e : " + startDate + ", " + startTime + ", " + endDate + ", " + endTime);
	var dateTimeSpan = "";
	if (startDate != null && startDate != '' && endDate != null && endDate != '') {
		if (startDate != endDate) {
			// overnight shift
			var firstDay = calculateTimeSpan(startTime, "24:00");
			var secondDay = calculateTimeSpan("0:0", endTime);
			dateTimeSpan = addTimeSpans(firstDay, secondDay);
		} else {
			dateTimeSpan = calculateTimeSpan(startTime, endTime);
		}
	}
	return dateTimeSpan;
}


function calculateDutySpan() {
	var startDate = dojo.widget.byId("startDate");
	if (startDate) {
		startDate = startDate.getValue();
	}
	var endDate = dojo.widget.byId("endDate");
	if (endDate) {
		endDate = endDate.getValue();
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	$("#dutySpan").text(calculateDateTimeSpan(startDate, startTime, endDate, endTime));
}
function calculateMileSpan() {
	var startMiles = $("#startMiles").val();
	var endMiles = $("#endMiles").val();
	if (startMiles != '' && endMiles != '') {
		$("#milesSpan").text(parseInt(endMiles) - parseInt(startMiles));
	}
}
function calculateHikeSpan(num) {
	var startDate = dojo.widget.byId("hikeDateStart" + num);
	if (startDate) {
		startDate = startDate.getValue();
	}
	var endDate = dojo.widget.byId("hikeDateEnd" + num);
	if (endDate) {
		endDate = endDate.getValue();
	}
	var startTime = $("#hikeTimeStart" + num).val();
	var endTime = $("#hikeTimeEnd" + num).val();
	
	$("#hikeSpan" + num).text(calculateDateTimeSpan(startDate, startTime, endDate, endTime));
}

$(function() {
	calculateDutySpan();
	calculateMileSpan();
	calculateHikeSpan();
});