
const baseUrl = "/api/v1/payments";
const apartmentForResidenceBaseUrl = "/api/v1/apartments?residence=";

let table;
let objectList = [];
let media = "ENERGY";
let apartmentsChoiceIndex;
let apartmentsChoice = [];

$(document).ready(function () {
    $("#residences").change(function () {
        residenceArrayIndex = $(this).val();
        media = $("input[name=mediaChoice]:checked").val();
        drawTableFromEndpoint();
    });

    $("#mediaRadio").change(function () {
        media = $("input[name=mediaChoice]:checked").val();
        drawTableFromEndpoint();
    });
});


findChosenApartment = () => {
    const chosenApartment = $("#apartmentsInput").val();
    for (let i = 0; i < apartmentsChoice.length; i++) {
        if (apartmentsChoice[i].description === chosenApartment) {
            return apartmentsChoice[i];
        }
    }
};

drawTableFromEndpoint = function () {
    const finalUrl = `${baseUrl}?media=${media}&residence=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        if (result.length === 0) {
            $("#tableContent").hide();
            $("#apartmentsInput").children().remove();
        } else {
            objectList = result;
            drawTable();
        }
    });
};


drawTable = function () {
    if (table) {
        table.destroy();
    }
    $("#tableContent").show();
    $("#tableContent").removeAttr("hidden");
    table = $("#dataTable").DataTable({
        data: objectList,

        columns: [
            { data: "paymentDate" },
            {
                data: null,
                render: function (data, type, row) {
                    return data.tenant.firstName + " " + data.tenant.lastName;
                }
            },
            { data: "paymentAmount" }
        ],
        language: tableTranslation
    });
};