
const baseUrl = "/api/v1/invoices";
const unresolvedReadingsUrl = "/api/v1/readings/unresolved/";

let table;
let listIndex;
let objectList = [];
let media = "ENERGY";
let unresolvedReadingsIndex;
let unresolvedReadings = [];

$(document).ready(function () {
    $('#residences').change(function () {
        residenceArrayIndex = $(this).val();
        media = $('input[name=mediaChoice]:checked').val();
        drawTableFromEndpoint();
    });

    $('#mediaRadio').change(function () {
        media = $('input[name=mediaChoice]:checked').val();
        drawTableFromEndpoint();
        clearForm();
    });

    $("#submitButton").click(function (e) {
        e.preventDefault();
        let httpMethod = "POST";

        const entityToSave = {
            invoiceDate: $("#invoiceDate").val(),
            media: media,
            serialNumber: $("#serialNumber").val(),
            totalAmount: $("#totalAmount").val(),
            readingDetails: unresolvedReadings[$("#unresolvedReadings").val()],
            residence: unresolvedReadings[$("#unresolvedReadings").val()].residence,
            status: "ACTIVE",
            media: media
        };

        $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(entityToSave),
            dataType: 'json',
            success: function (data) {
                showModal("Sukces", "Dane zostały zapisane w bazie");
                objectList.push(data);
                drawTable();
                toggleForm();
            },
            error: function (error) {
                showModal("Błąd podczas zapisu danych", error.responseText);
            },
            processData: false,
            type: httpMethod,
            url: baseUrl
        });
    })
});

deleteEntity = function (row) {
    entity = objectList[row];
    const deleteUrl = `${baseUrl}/${entity.id}`;
    console.log(deleteUrl);
    $.ajax({
        url: deleteUrl,
        type: "DELETE",
        success: function (result) {
            showModal("Usunięto", "Dane zostały usunięte z bazy");
            objectList.splice(row, 1);
            drawTable();
            createUnresolvedReadingsChoice();
        },
        error: function (error) {
            showModal("Błąd podczas usuwania danych", error.responseText);
        }
    });
};


drawTableFromEndpoint = function () {
    const finalUrl = `${baseUrl}?media=${media}&residence=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        createUnresolvedReadingsChoice();
        if (result.length === 0) {
            $("#tableContent").hide();
            $('#unresolvedReadings').children().remove();
        } else {
            objectList = result;
            drawTable();
        };
    });
};

createUnresolvedReadingsChoice = function () {
    const finalUrl = `${unresolvedReadingsUrl}${media}?residence=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        unresolvedReadings = result;
        $('#unresolvedReadings').children().remove();
        for (let i = 0; i < result.length; i++) {
            $("#unresolvedReadings").append(
                $('<option></option>').val(i).html(result[i].readingDate)
            );
        };
    });
};

drawTable = function () {
    if (table) {
        table.destroy();
    };
    $("#tableContent").show();
    $("#tableContent").removeAttr('hidden');
    table = $('#dataTable').DataTable({
        data: objectList,
        columns: [
            { data: 'invoiceDate' },
            { data: 'serialNumber' },
            { data: 'totalAmount' },
            {
                data: null,
                render: function (data, type, row) {
                    return `${data.residence.street} ${data.residence.number}, ${data.residence.city}`;
                }
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return "<button type='button' class='btn-xs btn-danger' onClick=deleteEntity(" + meta.row + ")><i class='fa fa-times fa-1' aria-hidden='true'></button>";
                }
            }
        ],
        language: tableTranslation
    });
};