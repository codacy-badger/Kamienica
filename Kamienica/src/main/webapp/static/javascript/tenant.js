function tenant() {
	
	$("input[name='password']").keyup( function() {
		console.log($(this).val().trim());
		if ($(this).val().length < 5){
		
			$(this).css("border-color", "red");
		}
		if ($(this).val().length > 5) {
			$(this).css("border-color", "green");
		}
		 
		});
	
	$("input[type='text']").ready( function() {
		console.log($(this).val().trim());
		if ($(this).val().length < 5){
		
			$(this).css("border-color", "red");
		}
		if ($(this).val().length > 5) {
			$(this).css("border-color", "green");
		}
		 
		});
	
};

$(document).ready(function() {

	tenant();

});