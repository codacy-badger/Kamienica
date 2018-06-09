const formText = "Dodaj nowy element";
const tableText = "Przejdź do tabeli";
const url = "/api/v1/apartments?residence=";
let table;
let residenceArrayIndex;
let apartmentArrayIndex;
let residences = [];
let apartments = [];
const apartmentStorageKey = "apartments";

$(document).ready(function () {
    $("#form").toggle();
    $("#toggler").text(formText);
    $("#toggler").click(function () {
        toggleForm()
    });

    $.getJSON("/api/v1/residences", function (result) {
        if (result.length === 0) {
            showError("Brak nieruchomości - dodaj przynajmniej jedną pozycję.");
        } else {
            residences = result;
            createResidencesChoice()
            if (result.length === 1) {
                residenceArrayIndex = 0;
                drawTableFromEndpoint();
            };
        };
    }).fail(function (response) {
        showError(response.responseJSON.message);
    });

    $('select').change(function () {
        residenceArrayIndex = $(this).val();
        drawTableFromEndpoint();
    });

    $(".dropdown-menu li").click(function () {
        $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
        $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
        $("#tableContent").removeAttr('hidden');
    });

    $("#submitButton").click(function (e) {
        e.preventDefault();
        let appartmentId = $("#appartmentId").val();
        let httpMethod = "POST";
        let url = "/api/v1/apartments";
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
                $("#messageModalLabel").text("Zapisano dane")
                $('#messageModal').modal('show');
                $("#messageModalMessage").text("Mieszkanie zostało zapisane w bazie");
                if (edit) {
                    apartments[apartmentArrayIndex] = data;
                } else {
                    apartments.push(data);
                }
                drawTable();
                toggleForm();
            },
            error: function (error) {
                $("#messageModalMessage").text(error.responseText);
                $("#messageModalLabel").text("Błąd podczas zapisu danych")
                $('#messageModal').modal('show');
            },
            processData: false,
            type: httpMethod,
            url: url
        });
    })
});

showError = function (msg) {
    $("#messageModalMessage").text(msg);
    $("#messageModalLabel").text("Błąd podczas pobierania nieruchomości");
    $('#messageModal').modal('show');
};

createResidencesChoice = function () {
    if (residences.length > 1) {
        $("#residences").append(
            $('<option></option>').html("Wybierz nieruchomość...")
        );
    };

    for (let i = 0; i < residences.length; i++) {
        $("#residences").append(
            $('<option></option>').val(i).html(residences[i].street + " " + residences[i].number)
        );
    };
};

toggleForm = function () {
    $("#form").toggle();
    $("#list").toggle();

    if ($("#toggler").text() === formText) {
        $("#toggler").text(tableText);
    } else {
        $("#toggler").text(formText);
    }

    $("#list").removeAttr('hidden');
}

drawTableFromEndpoint = function () {
    const finalUrl = url + residences[residenceArrayIndex].id;
    $.getJSON(finalUrl, function (result) {
        apartments = result;
        drawTable();
    });
};

deleteEntity = function (row) {
    entity = apartments[row];
    $.ajax({
        url: "/api/v1/apartments/" + entity.id,
        type: "DELETE",
        success: function (result) {
            $("#messageModalLabel").text("Usunięto dane")
            $('#messageModal').modal('show');
            $("#messageModalMessage").text("Mieszkanie zostało usunięte z bazy");
            apartments.splice(row, 1);
            drawTable();
        },
        error: function (error) {
            $("#messageModalMessage").text(error.responseText);
            $("#messageModalLabel").text("Błąd podczas usuwania danych")
            $('#messageModal').modal('show');
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
        language: {
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
        }
    });
};