$(document).ready(function () {
//TODO should be renamed to ownerNav!!
    $.get("/js/common/adminNav.html", function (data) {
        $("#mainNav").html(data);
    });

    $("#versionFooter").append("<small>Kamienica 1.3.1</small>");
});