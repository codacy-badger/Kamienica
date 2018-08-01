const baseUrl = "/api/v1/readings";
const metersUrl = "/api/v1/meters?";

let meters = [];
let latestReadings = [];
let table;
let listIndex;
let objectList = [];
let media = "ENERGY";

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
        let entityId = $("#entityId").val();
        let httpMethod = "POST";
        let url = baseUrl;
        const edit = parseInt(entityId) > 0;
        if (edit) {
            httpMethod = "PUT";
        };

        const entityToSave = {
            description: $("#description").val(),
            unit: $("#unit").val(),
            serialNumber: $("#serialNumber").val(),
            id: entityId,
            status: "ACTIVE",
            media: media
        };

        $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(entityToSave),
            dataType: 'json',
            success: function (data) {
                showModal("Sukces", "Dane zostały zapisane w bazie");
                if (edit) {
                    objectList[listIndex] = data;
                } else {
                    objectList.push(data);
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
    });
    if(residences.length === 1) {
        residenceArrayIndex = 0;
    }
});

deleteEntity = function (row) {
    entity = objectList[row];
    $.ajax({
        url: `${baseUrl}/?media=${media}&residence_id=${residences[residenceArrayIndex].id}`,
        type: "DELETE",
        success: function (result) {
            showModal("Usunięto", "Dane zostały usunięte z bazy");
            objectList.splice(row, 1);
            drawTable();
        },
        error: function (error) {
            showModal("Błąd podczas usuwania danych", error.responseText);
        }
    });
};

editEntity = function (row) {
    entity = objectList[row];
    listIndex = row;
    toggleForm();
    $("#description").val(entity.description);
    $("#unit").val(entity.unit);
    $("#serialNumber").val(entity.serialNumber);
    $("#apartmentsInput").val(entity.apartment.description);
    $("#entityId").val(entity.id);

};

drawTableFromEndpoint = function () {
    const finalUrl = `${baseUrl}?media=${media}&residence_id=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        if (result.length === 0) {
            $("#tableContent").hide();
            $('#apartmentsInput').children().remove();
        } else {
            objectList = result;
            findLatestReadings(result);
            drawTable();
            findActiveMeters();
        };
    });
};

findLatestReadings = function(list) {
    latestReadings.length = 0;
    const latestDate = objectList[0].readingDetails.readingDate;
    for(var i=0; i<list.length; i++) {
        if(list[i].readingDetails.readingDate === latestDate) {
            latestReadings.push(list[i]);
        }
    }
}

findActiveMeters = function () {
    const finalUrl = `${metersUrl}media=${media}&id=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        meters = result;
        prepareForm();
    });
}

prepareForm = function () {
    $("#readingsInput").empty();
    console.log("prepareFoirm");
    console.log(latestReadings);
    for (i = 0; i < latestReadings.length; i++) {
        $("#readingsInput").append(
            `<label class='col-sm-3 control-label'>${latestReadings[i].meter.description}</label><div class='col-sm-6'><input id='date' type='number' min=${latestReadings[i].value} step='0.01' value=${latestReadings[i].value} class='form-control'/></div>`
        );
    }
}

drawTable = function () {
    if (table) {
        table.destroy();
    };
    const latestDate = objectList[0].readingDetails.readingDate;
    $("#tableContent").show();
    $("#tableContent").removeAttr('hidden');
   
    table = $('#dataTable').DataTable({
        data: objectList,
        "order": [[0, "desc"]],
        columns: [
            {
                data: null,
                render: function (data, type, row) {
                    return data.readingDetails.readingDate;
                }
            },
            { data: 'value' },
            {
                data: null,
                render: function (data, type, row) {
                    return data.meter.description;
                }
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    if (data.readingDetails.readingDate !== latestDate) {
                        return "";
                    }
                    return "<button type='button' class='btn-xs btn-warning' onClick=editEntity()><i class='fa fa-pencil-square-o fa-1' aria-hidden='true'></i></button>" +
                        "<button type='button' class='btn-xs btn-danger' onClick=deleteEntity()><i class='fa fa-times fa-1' aria-hidden='true'></button>";
                }
            }
        ],
        language: tableTranslation
    });
};