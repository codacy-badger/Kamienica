$(document).ready(function () {

    $.get("/js/common/tenantNav.html", function (data) {
        $("#mainNav").html(data);
    });

    $("#versionFooter").append("<small>Kamienica 1.3.1</small>");
});