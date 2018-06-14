const formText = "Dodaj nowy element";
const tableText = "Przejdź do tabeli";

let residenceArrayIndex;
let residences = [];

const toggler = "#toggler";

$(document).ready(function () {
    $("#form").toggle();
    $(toggler).text(formText);
    $(toggler).click(function () {
        toggleForm()
    });

    $.getJSON("/api/v1/residences", function (result) {
        if (result.length === 0) {
            showModal("Błąd podczas pobierania nieruchomości", "Brak nieruchomości - dodaj przynajmniej jedną pozycję.");
        } else {
            residences = result;
            createResidencesChoice();
            if (result.length === 1) {
                residenceArrayIndex = 0;
                drawTableFromEndpoint();
            };
        };
    }).fail(function (response) {
        showModal("Błąd podczas pobierania nieruchomości", response.responseJSON.message);
    });

    $(".dropdown-menu li").click(function () {
        $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
        $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
        $("#tableContent").removeAttr('hidden');
    });

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

    if ($(toggler).text() === formText) {
        $(toggler).text(tableText);
        clearForm();
    } else {
        $(toggler).text(formText);
    }

    $("#list").removeAttr('hidden');
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