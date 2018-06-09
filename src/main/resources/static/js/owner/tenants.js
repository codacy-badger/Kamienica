const residenceUrl = "/api/v1/tenants?residence=";
const tenantsUrl = "/api/v1/tenants";

let table;
let tenantArrayIndex;
let tenants = [];
const tenantstorageKey = "tenants";

$(document).ready(function () {
    $('select').change(function () {
        residenceArrayIndex = $(this).val();
        drawTableFromEndpoint();
    });

    $("#submitButton").click(function (e) {
        e.preventDefault();
        let appartmentId = $("#appartmentId").val();
        let httpMethod = "POST";
        let url = tenantsUrl;
        const edit = parseInt(appartmentId) > 0;
        if (edit) {
            httpMethod = "PUT";
            url += "/" + appartmentId;
        };
        const tenantToSave = {
            residence: residences[residenceArrayIndex],
            tenantNumber: $("#tenantNumber").val(),
            intercom: $("#intercom").val(),
            description: $("#description").val(),
            id: appartmentId,
        }

        $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(tenantToSave),
            dataType: 'json',
            success: function (data) {
                showModal("Zapisano dane", "Mieszkanie zostało zapisane w bazie");
                if (edit) {
                    tenants[tenantArrayIndex] = data;
                } else {
                    tenants.push(data);
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
    entity = tenants[row];
    $.ajax({
        url: tenantsUrl + "/" + entity.id,
        type: "DELETE",
        success: function (result) {
            showModal("Usunięto dane", "Mieszkanie zostało usunięte z bazy");
            tenants.splice(row, 1);
            drawTable();
        },
        error: function (error) {
            showModal("Błąd podczas usuwania danych", error.responseText);
        }
    });
};

editEntity = function (row) {
    entity = tenants[row];
    tenantArrayIndex = row;
    toggleForm();
    $("#tenantNumber").val(entity.tenantNumber);
    $("#intercom").val(entity.intercom);
    $("#description").val(entity.description);
    $("#appartmentId").val(entity.id);
}

drawTableFromEndpoint = function () {
    const finalUrl = residenceUrl + residences[residenceArrayIndex].id;
    $.getJSON(finalUrl, function (result) {
        tenants = result;
        drawTable();
    });
};

{/* <th>Najemca</th>
<th>E-mail</th>
<th>Telefon</th>
<th>Mieszkanie</th>
<th>Cena Najmu</th>
<th>Początek umowy</th>
<th>Koniec Umowy</th>
<th>Edytuj/Usuń</th> */}

drawTable = function () {
    if (table) {
        table.destroy();
    };

    $("#tableContent").removeAttr('hidden');
    table = $('#dataTable').DataTable({
        data: tenants,
        columns: [
            {
                data: null,
                render: function (data, type, row) {
                    return data.firstName + " " + data.lastName;
                }
            },
            { data: 'email' },
            { data: 'phone' },
            {
                data: null,
                render: function (data, type, row) {
                    return data.rentContract.apartment.description;
                }
            },
            {
                data: null,
                render: function (data, type, row) {
                    return data.rentContract.rentCost;
                }
            },
            {
                data: null,
                render: function (data, type, row) {
                    return data.rentContract.constractStart;
                }
            },
            {
                data: null,
                render: function (data, type, row) {
                    return data.rentContract.constracEnd;
                }
            },
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