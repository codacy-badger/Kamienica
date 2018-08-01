$(document).ready(function () {

    $.get("/js/common/adminNav.html", function (data) {
        $("#mainNav").html(data);
    });

    $("#versionFooter").append("<small>Kamienica 1.3</small>");
});