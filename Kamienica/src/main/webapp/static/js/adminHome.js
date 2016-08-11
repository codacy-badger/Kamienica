$(document).ready(function() {
	var invoice = $('#invoiceDays').text().split(" ", 1);
	invoice = parseInt(invoice[0]);

	var reading = $('#readingDays').text().split(" ", 1);
	reading = parseInt(reading[0]);

	var apartments = $('#apartmentNumber').text();
	apartments = parseInt(apartments);

	// APARTMENTS

	if (apartments > 0) {
		$("#apartment").addClass("panel-red");

	} else {
		$("#apartment").addClass("panel-green");

	}
	// INVOICE
	if (invoice <= 30) {
		$("#invoice").addClass("panel-green");

	} else if (invoice > 30 && invoice < 45 ){
		$("#invoice").addClass("panel-yellow");

	} else {
		$("#invoice").addClass("panel-red");
	}
	
	// Reading
	if (reading <= 30) {
		$("#reading").addClass("panel-green");

	} else if (reading > 30 && reading < 45 ){
		$("#reading").addClass("panel-yellow");

	} else {
		$("#reading").addClass("panel-red");
	}
	
});