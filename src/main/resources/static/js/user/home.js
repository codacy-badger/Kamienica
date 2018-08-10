$(document).ready(function () {
  $.ajax({
    url: "/api/v1/user/details"
  }).then(function (data) {
    $("#user").text(`Wynajmujący: ${data.firstName} ${data.lastName}`);
    $("#apartment").text(`Mieszkanie: ${data.rentContract.apartment.description}`);
  });


});
