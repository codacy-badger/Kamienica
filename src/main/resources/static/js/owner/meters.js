
const baseUrl = "/api/v1/meters";
const apartmentForResidenceBaseUrl = "/api/v1/apartments?residence=";

let table;
let listIndex;
let objectList = [];
let media = "ENERGY";
let apartmentsChoiceIndex;
let apartmentsChoice = [];

$(document).ready(function () {
    $('#residences').change(function () {
        residenceArrayIndex = $(this).val();
        media = $('input[name=mediaChoice]:checked').val();
        drawTableFromEndpoint();
    });

    $('#mediaRadio').change(function () {
        media = $('input[name=mediaChoice]:checked').val();
        drawTableFromEndpoint();
        setMediaSpecificInput();
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
            apartment: findChosenApartment(),
            residence: findChosenApartment().residence,
            status: "ACTIVE",
            media: media,
            warmWater: setIsWarmWater(),
            cwu: setIsCwu()
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
    })
});

setIsWarmWater = function () {
    if (media === "ENERGY" || media === "GAS") {
        return false;
    }
    return $('input[name=waterOrGasAdditionalInput]:checked').val();
}
setIsCwu = function () {
    if (media === "ENERGY" || media === "WATER") {
        return false;
    }
    return $('input[name=waterOrGasAdditionalInput]:checked').val();
}

setMediaSpecificInput = function () {
    if (media === "ENERGY") {
        $("#radioInputMeterDiv").hide();
    } else {
        $("#radioInputMeterDiv").show();
        if (media === "GAS") {
            $("#radioInputMeterLabel").text("Piec ciepłej wody użytkowej (CWU)");
        } else {
            $("#radioInputMeterLabel").text("Ciepła Woda");
        }
    }
}

findChosenApartment = () => {
    const chosenApartment = $("#apartmentsInput").val();
    for (i = 0; i < unresolvedReadings.length; i++) {
        if (unresolvedReadings[i].description === chosenApartment) {
            return unresolvedReadings[i];
        }
    }
}

deleteEntity = function (row) {
    entity = objectList[row];
    $.ajax({
        url: baseUrl + "/" + entity.id,
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
    if (entity.warmWater || entity.cwu) {
        $('input[name=waterOrGasAdditionalInput][value=true]').attr('checked', true);
        $('input[name=waterOrGasAdditionalInput][value=false]').attr('checked', false);
    } else {
        $('input[name=waterOrGasAdditionalInput][value=true]').attr('checked', false);
        $('input[name=waterOrGasAdditionalInput][value=false]').attr('checked', true);
    }
};

drawTableFromEndpoint = function () {
    const finalUrl = `${baseUrl}?media=${media}&id=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        createApartmentsChoice();
        if (result.length === 0) {
            $("#tableContent").hide();
            $('#apartmentsInput').children().remove();
        } else {
            objectList = result;
            drawTable();
        };
    });
};

createApartmentsChoice = function () {
    const finalUrl = unresolvedReadingsUrl + residences[residenceArrayIndex].id;
    $.getJSON(finalUrl, function (result) {
        unresolvedReadings = result;
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
        data: objectList,
        "columnDefs": [
            {
                "targets": [4],
                "visible": media === "GAS",
                "searchable": media === "GAS"
            },
            {
                "targets": [5],
                "visible": media === "WATER",
                "searchable": media === "WATER"
            }
        ],
        columns: [
            { data: 'serialNumber' },
            { data: 'description' },
            {
                data: null,
                render: function (data, type, row) {
                    const a = data.apartment;
                    if (!a) {
                        return "";
                    }
                    return a.description;
                }
            },
            { data: 'unit' },
            {
                data: null,
                render: function (data, type, row) {
                    return translate(data.cwu);
                }
            },
            {
                data: null,
                render: function (data, type, row) {
                    return translate(data.warmWater);
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

translate = function (bool) {
    if (bool) {
        return "Tak";
    }
    return "Nie"
}