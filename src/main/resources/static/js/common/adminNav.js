$(document).ready(function () {

    $.get("/js/common/adminNav.html", function (data) {
        $("#mainNav").html(data);
    });


});