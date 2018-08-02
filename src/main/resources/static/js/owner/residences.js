const formText = "Dodaj nowy element";
const tableText = "Przejdź do tabeli";
const baseUrl = "/api/v1/residences";

let residencesArrayIndex;
let residences;
let table;
const toggler = "#toggler";

$(document).ready(function () {
    $("#form").toggle();
    $(toggler).text(formText);
    $(toggler).click(function () {
        toggleForm()
    });

    $.getJSON(baseUrl, function (result) {
        if (result.length === 0) {
            showModal("Błąd podczas pobierania nieruchomości", "Brak nieruchomości - dodaj przynajmniej jedną pozycję.");
        } else {
            residences = result;
            drawTable();

        }
    }).fail(function (response) {
        showModal("Błąd podczas pobierania nieruchomości", response.responseJSON.message);
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
        }
        const entityToSave = {
            street: $("#street").val(),
            number: $("#number").val(),
            city: $("#city").val(),
            id: entityId
        };

        $.ajax({
            contentType: "application/json",
            data: JSON.stringify(entityToSave),
            dataType: "json",
            success: function (data) {
                showModal("Zapisano dane", "Dane zostały poprawnie zapisane w bazie");
                if (edit) {
                    residences[residencesArrayIndex] = data;
                } else {
                    residences.push(data);
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


    deleteEntity = function (row) {
        const entity = residences[row];
        $.ajax({
            url: baseUrl + "/" + entity.id,
            type: "DELETE",
            success: function () {
                showModal("Usunięto", "Dane zostały usunięte z bazy");
                residences.splice(row, 1);
                drawTable();
            },
            error: function (error) {
                showModal("Błąd podczas usuwania danych", error.responseText);
            }
        });
    };
  
    $("#resetButton").click(function () {
        clearForm();
    });
});

clearForm = function () {
    $("#myForm").trigger("reset");
};

showModal = function (title, message) {
    $("#messageModalMessage").text(message);
    $("#messageModalLabel").text(title);
    $('#messageModal').modal('show');
};

editEntity = function (row) {
    const entity = residences[row];
    residencesArrayIndex = row;
    toggleForm();
    $("#street").val(entity.street);
    $("#number").val(entity.number);
    $("#city").val(entity.city);
    $("#entityId").val(entity.id);
};

toggleForm = function () {
    $("#form").toggle();
    $("#list").toggle();

    if ($(toggler).text() === formText) {
        $(toggler).text(tableText);
        clearForm();
    } else {
        $(toggler).text(formText);
    }
    $("#list").removeAttr("hidden");
};


drawTable = function () {
    if (table) {
        table.destroy();
    }

    $("#tableContent").removeAttr('hidden');
    table = $('#dataTable').DataTable({
        data: residences,
        columns: [
            { data: 'street' },
            { data: 'number' },
            { data: 'city' },
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

tableTranslation = {
    decimal: ",",
    processing: "Przetwarzanie...",
    search: "Szukaj:",
    lengthMenu: "Pokaż _MENU_ pozycji",
    info: "Pozycje od _START_ do _END_ z _TOTAL_ łącznie",
    infoEmpty: "Pozycji 0 z 0 dostępnych",
    infoFiltered: "(filtrowanie spośród _MAX_ dostępnych pozycji)",
    infoPostFix: "",
    loadingRecords: "Wczytywanie...",
    zeroRecords: "Nie znaleziono pasujących pozycji",
    emptyTable: "Brak danych",
    paginate: {
        first: "Pierwsza",
        previous: "Poprzednia",
        next: "Następna",
        last: "Ostatnia"
    },
    aria: {
        sortAscending: ": aktywuj, by posortować kolumnę rosnąco",
        sortDescending: ": aktywuj, by posortować kolumnę malejąco"
    }
};