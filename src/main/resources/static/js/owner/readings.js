const baseUrl = "/api/v1/readings";
const metersUrl = "/api/v1/meters?";

let meters = [];
let latestReadings = [];
let secondLatestReadings = [];
let table;
let listIndex;
let objectList = [];
let media = "ENERGY";
let isNewEntity = true;

$(document).ready(function () {
    $("#residences").change(function () {
        residenceArrayIndex = $(this).val();
        media = $("input[name=mediaChoice]:checked").val();
        drawTableFromEndpoint();
    });

    $("#mediaRadio").change(function () {
        media = $("input[name=mediaChoice]:checked").val();
        drawTableFromEndpoint();
        clearForm();
    });

    $("#submitButton").click(function (e) {
        e.preventDefault();
        let httpMethod;
        if (isNewEntity) {
            httpMethod = "POST";
        } else {
            httpMethod = "PUT";
        }

        const entityToSave =  getReadingsFromForm();

        $.ajax({
            contentType: "application/json",
            data: JSON.stringify(entityToSave),
            dataType: "json",
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
            url: baseUrl
        });
    });
    if (residences.length === 1) {
        residenceArrayIndex = 0;
    }

  $(toggler).click(function () {
      if($(toggler).text() === formText) {
        prepareForm();
        isNewEntity = true;
      }
  });
});

getReadingsFromForm = function () {
    let newReadings = [];
    const readingDetails = {
        residence: residences[residenceArrayIndex],
        readingDate: $("#date").val(),
        unit: $("#unit").val(),
        resolvement: "UNRESOLVED",
        media: media,
        id: $("#date").attr("databaseId")
    };

    for (let i = 0; i < meters.length; i++) {
        const meterFromId = `#meter${meters[i].id}`;
        const reading = {
            meter: meters[i],
            readingDetails: readingDetails,
            value: $(meterFromId).val(),
            id: $(meterFromId).attr("databaseId"),
            residence: residences[residenceArrayIndex]
        };
        newReadings.push(reading);
    }

    return newReadings;
};

deleteEntity = function (row) {
    const entity = objectList[row];
    $.ajax({
        url: `${baseUrl}/?media=${media}&residence_id=${residences[residenceArrayIndex].id}`,
        type: "DELETE",
        success: function () {
            showModal("Usunięto", "Dane zostały usunięte z bazy");
            objectList.splice(row, 1);
            drawTable();
        },
        error: function (error) {
            showModal("Błąd podczas usuwania danych", error.responseText);
        }
    });
};

editEntity = function () {
    isNewEntity = false;
    toggleForm();
    for(let i =0; i < secondLatestReadings.length; i++) {
      $(`#meter${secondLatestReadings[i].meter.id}`).attr({
        "min" : secondLatestReadings[i].value
      });
    }
  for(let i =0; i < latestReadings.length; i++) {
    $(`#meter${latestReadings[i].meter.id}`).attr({
      "databaseId" : latestReadings[i].id
    });
  }

  $("#date").val(latestReadings[0].readingDetails.readingDate);
  $("#date").attr({
    "databaseId": latestReadings[0].readingDetails.id
  });
};

drawTableFromEndpoint = function () {
    const finalUrl = `${baseUrl}?media=${media}&residence_id=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        if (result.length === 0) {
            $("#tableContent").hide();
            $("#apartmentsInput").children().remove();
        } else {
            objectList = result;
            findLatestReadings(result);
            drawTable();
            findActiveMeters();
        }
    });
};

findLatestReadings = function (list) {
  latestReadings.length = 0;
  secondLatestReadings.length = 0;
  const latestDate = objectList[0].readingDetails.readingDate;
  let secondLatestDate;
  for (let i = 0; i < list.length; i++) {
    if (list[i].readingDetails.readingDate === latestDate) {
      latestReadings.push(list[i]);
    } else {
      //TODO not a good idea but it will have to be fixed with api 2.0 that has pagination
      secondLatestDate = list[i].readingDetails.readingDate;
      break;
    }
  }
  for (let i = 0; i < list.length; i++) {
    if (list[i].readingDetails.readingDate === secondLatestDate) {
      secondLatestReadings.push(list[i]);
    }
  }
};

findActiveMeters = function () {
    const finalUrl = `${metersUrl}media=${media}&id=${residences[residenceArrayIndex].id}`;
    $.getJSON(finalUrl, function (result) {
        meters = result;
        prepareForm();
    });
};

prepareForm = function () {
    $("#readingsInput").empty();
    //new meters do not have readings yet. We have to add them individually
    idOfMetersThatAlreadyHaveReadings = [];
    for (i = 0; i < latestReadings.length; i++) {
        $("#readingsInput").append(
            `<label class='col-sm-3 control-label'>${latestReadings[i].meter.description}</label><div class='col-lg-6 col-sm-12'><input id='meter${latestReadings[i].meter.id}' type='number' min='${latestReadings[i].value}' step='0.01' value='${latestReadings[i].value}' class='form-control'/></div>`
        );
        idOfMetersThatAlreadyHaveReadings.push(latestReadings[i].meter.id);
    }
    for (let i = 0; i < meters.length; i++) {
        let foundNewMeter = true;
        for (y = 0; y < idOfMetersThatAlreadyHaveReadings.length; y++) {
            if (idOfMetersThatAlreadyHaveReadings[y] === meters[i].id) {
                foundNewMeter = false;
            }
        }

        if (foundNewMeter) {
            $("#readingsInput").append(
                `<label class='col-sm-3 control-label'>${meters[i].description}</label><div class='col-lg-6 col-sm-12'><input id='meter${meters[i].id}' type='number' min=0 step='0.01' value=0 class='form-control'/></div>`
            );
        }
    }
};

drawTable = function () {
    if (table) {
        table.destroy();
    }
    const latestDate = objectList[0].readingDetails.readingDate;
    $("#tableContent").show();
    $("#tableContent").removeAttr("hidden");

    table = $("#dataTable").DataTable({
        data: objectList,
        "order": [[0, "desc"]],
        columns: [
            {
                data: null,
                render: function (data, type, row) {
                    return data.readingDetails.readingDate;
                }
            },
            { data: "value" },
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