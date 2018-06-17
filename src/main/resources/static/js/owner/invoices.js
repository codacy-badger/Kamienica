const residenceUrl = "/api/v1/tenants?residence=";
const baseUrl = "/api/v1/tenants";
const apartmentForResidenceBaseUrl = "/api/v1/apartments?residence=";

let table;
let tenantArrayIndex;
let tenants = [];
const tenantstorageKey = "tenants";

let apartmentsChoiceIndex;
let apartmentsChoice = [];

$(document).ready(function () {
    $('#residences').change(function () {
        residenceArrayIndex = $(this).val();
        drawTableFromEndpoint();
    });

    $("#submitButton").click(function (e) {
        e.preventDefault();
        let entityId = $("#entityId").val();
        let httpMethod = "POST";
        let url = baseUrl;
        const edit = parseInt(entityId) > 0;
        if (edit) {
            httpMethod = "PUT";
            url += "/" + entityId;
        };
        const tenantToSave = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            phone: $("#phone").val(),
            id: entityId, 
            password: $("#password").val(),
            rentContract: {
                apartment: findChosenApartment(),
                contractStart: $("#contractStart").val(),
                contractEnd: $("#contractEnd").val(),
                rentCost: $("#rentCost").val(),
            }
        };

        $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(tenantToSave),
            dataType: 'json',
            success: function (data) {
                showModal("Sukces", "Dane zostały zapisane w bazie");
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

findChosenApartment= () => {
    const chosenApartment = $("#apartmentsInput").val();
    for(i=0;i<apartmentsChoice.length;i++) {
        if(apartmentsChoice[i].description === chosenApartment) {
            return apartmentsChoice[i];
        }
    }
}

deleteEntity = function (row) {
    entity = tenants[row];
    $.ajax({
        url: baseUrl + "/" + entity.id,
        type: "DELETE",
        success: function (result) {
            showModal("Usunięto", "Dane zostały usunięte z bazy");
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

    $("#firstName").val(entity.firstName);
    $("#lastName").val(entity.lastName);
    $("#email").val(entity.email);
    $("#phone").val(entity.phone);
    $("#apartmentsInput").val(entity.rentContract.apartment.description);
    $("#password").val(entity.password);
    $("#entityId").val(entity.id);
    $("#contractStart").val(entity.rentContract.contractStart);
    $("#contractEnd").val(entity.rentContract.contractEnd);
    $("#rentCost").val(entity.rentContract.rentCost);
};

drawTableFromEndpoint = function () {
    const finalUrl = residenceUrl + residences[residenceArrayIndex].id;
    $.getJSON(finalUrl, function (result) {
        createApartmentsChoice();
        if (result.length === 0) {
            $("#tableContent").hide();
            $('#apartmentsInput').children().remove();
        } else {
            tenants = result;
            drawTable();
        };
    });
};


createApartmentsChoice = function () {
    const finalUrl = apartmentForResidenceBaseUrl + residences[residenceArrayIndex].id;

    $.getJSON(finalUrl, function (result) {
        apartmentsChoice = result;
        $('#apartmentsInput').children().remove();
        for (let i = 0; i < result.length; i++) {
            $("#apartmentsInput").append(
                $('<option></option>').val(result[i].description).html(result[i].description)
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
                    return data.rentContract.contractStart;
                }
            },
            {
                data: null,
                render: function (data, type, row) {
                    return data.rentContract.contractEnd;
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