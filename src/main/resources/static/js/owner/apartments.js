const apartmentsUrl = "/api/v1/apartments";
const apartmentForResidenceBaseUrl = "/api/v1/apartments?residence=";

let table;
let apartmentArrayIndex;
let apartments = [];
const apartmentStorageKey = "apartments";

$(document).ready(function () {
    $('select').change(function () {
        residenceArrayIndex = $(this).val();
        drawTableFromEndpoint();
    });

    $("#submitButton").click(function (e) {
        e.preventDefault();
        let appartmentId = $("#appartmentId").val();
        let httpMethod = "POST";
        let url = apartmentsUrl;
        const edit = parseInt(appartmentId) > 0;
        if (edit) {
            httpMethod = "PUT";
            url += "/" + appartmentId;
        };
        const apartmentToSave = {
            residence: residences[residenceArrayIndex],
            apartmentNumber: $("#apartmentNumber").val(),
            intercom: $("#intercom").val(),
            description: $("#description").val(),
            id: appartmentId,
        }

        $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(apartmentToSave),
            dataType: 'json',
            success: function (data) {
                showModal("Zapisano dane", "Mieszkanie zostało zapisane w bazie");
                if (edit) {
                    apartments[apartmentArrayIndex] = data;
                } else {
                    apartments.push(data);
                }
                drawTable();
                toggleForm();
            },
            error: function (error) {
                showModal("Błąd podczas zapisu danych", error.responseText);
            },
            processData: false,
            type: httpMethod,
            url: url
        });
    })
});

deleteEntity = function (row) {
    entity = apartments[row];
    $.ajax({
        url: apartmentsUrl + "/" + entity.id,
        type: "DELETE",
        success: function (result) {
            showModal("Usunięto dane", "Mieszkanie zostało usunięte z bazy");
            apartments.splice(row, 1);
            drawTable();
        },
        error: function (error) {
            showModal("Błąd podczas usuwania danych", error.responseText);
        }
    });
};

editEntity = function (row) {
    entity = apartments[row];
    apartmentArrayIndex = row;
    toggleForm();
    $("#apartmentNumber").val(entity.apartmentNumber);
    $("#intercom").val(entity.intercom);
    $("#description").val(entity.description);
    $("#appartmentId").val(entity.id);
}

drawTableFromEndpoint = function () {
    const finalUrl = apartmentForResidenceBaseUrl + residences[residenceArrayIndex].id;
    $.getJSON(finalUrl, function (result) {
        apartments = result;
        drawTable();
    });
};

drawTable = function () {
    if (table) {
        table.destroy();
    };

    $("#tableContent").removeAttr('hidden');
    table = $('#dataTable').DataTable({
        data: apartments,
        columns: [
            {
                data: null,
                render: function (data, type, row) {
                    return data.residence.city + ', ' + data.residence.street + ' ' + data.residence.number;
                }
            },
            { data: 'apartmentNumber' },
            { data: 'intercom' },
            { data: 'description' },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return "<button type='button' class='btn-xs btn-warning' onClick=editEntity(" + meta.row + ")><i class='fa fa-pencil-square-o fa-1' aria-hidden='true'></i></button>" +
                        "<button type='button' class='btn-xs btn-danger' onClick=deleteEntity(" + meta.row + ")><i class='fa fa-times fa-1' aria-hidden='true'></button>";
                }
            }
        ],
        language: tableTranslation
    });
};