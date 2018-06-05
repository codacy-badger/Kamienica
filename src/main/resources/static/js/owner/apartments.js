const formText = "Dodaj nowy element";
const tableText = "Przejdź do tabeli";
const url = "/api/v1/apartments?residence=";
let table;
let residenceArrayIndex;
let residences = [];

$(document).ready(function () {
    $("#form").toggle();
    $("#toggler").text(formText);
    $("#toggler").click(function () {
        $("#form").toggle();
        $("#list").toggle();

        if ($("#toggler").text() === formText) {
            $("#toggler").text(tableText);
        } else {
            $("#toggler").text(formText);
        }

        $("#list").removeAttr('hidden');
    });



    $.getJSON("/api/v1/residences", function (result) {
        if (result.length === 0) {
            showError("Brak nieruchomości - dodaj przynajmniej jedną pozycję.");
        } else {
            residences = result;
            createResidencesChoice()
            if (result.length === 1) {
                residenceArrayIndex = 0;
                drawTable();
            }
        }
    }).fail(function (response) {
        showError(response.responseJSON.message);
    });


    $('select').change(function () {
        if (table) {
            table.destroy();
        }
        residenceArrayIndex = $(this).val();
        table = drawTable();

    });

    $(".dropdown-menu li").click(function () {
        $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
        $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
        $("#tableContent").removeAttr('hidden');
    });


    $("#submitButtion").click(function(e) {
        e.preventDefault();
        console.log("tadam");
    })
});


showError = function (msg) {
    $("#messageModalMessage").text(msg);
    $("#messageModalLabel").text("Błąd podczas pobierania nieruchomości")
    $('#messageModal').modal('show');
}

createResidencesChoice = function () {
    if (residences.length > 1) {
        $("#residences").append(
            $('<option></option>').html("Wybierz nieruchomość...")
        );
    }

    for (let i = 0; i < residences.length; i++) {
        //const item = "<li><a href='#' class='dropdown-item' data-value='" + data[i].street + " " + data[i].number + "' value='" + data[i].id     +"'>" + data[i].street +" " + data[i].number + "</a></li>";
        //    const item = "<li><a href='#' class='dropdown-item' data-value='" + data[i].street + " " + data[i].number + "' value='" + data[i].id     +"'>" + data[i].street +" " + data[i].number + "</a></li>";
        $("#residences").append(
            $('<option></option>').val(i).html(residences[i].street + " " + residences[i].number)
        );
    }

    // $("#residenceChoice").append(listItems);
}

drawTable = function () {
    const completeUrl = url + residences[residenceArrayIndex].id;
    $("#tableContent").removeAttr('hidden');
    return $('#dataTable').DataTable({
        ajax: {
            url: completeUrl,
            dataSrc: '',
        },
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
            // { data: 'countryCode' }
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
}