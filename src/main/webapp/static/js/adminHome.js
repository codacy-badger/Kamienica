$(document).ready(function() {

	var invoice = (new Date() - new Date($('#invoiceDate').text())) / (1000 * 60 * 60 * 24);
	var reading =(new Date() - new Date($('#readingDate').text())) / (1000 * 60 * 60 * 24);
	var apartments = $('#emptyApartments').text();



	if (apartments > 0) {
		$("#apartment").addClass("panel-red");

	} else {
		$("#apartment").addClass("panel-green");

	}
	
	if (invoice <= 30) {
		$("#invoice").addClass("panel-green");

	} else if (invoice > 30 && invoice < 45 ){
		$("#invoice").addClass("panel-yellow");

	} else {
		$("#invoice").addClass("panel-red");
	}
	

	if (reading <= 30) {
		$("#reading").addClass("panel-green");

	} else if (reading > 30 && reading < 45 ){
		$("#reading").addClass("panel-yellow");

	} else {
		$("#reading").addClass("panel-red");
       
	}
	
});