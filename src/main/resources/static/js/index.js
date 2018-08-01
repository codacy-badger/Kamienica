$(document).ready(function () {
    $("#analizeBtn").click(function () {
        const accountChosen = $("#DLState").val();
        const dateStart = $("#dateStart").val();
        const dateEnd = $("#dateEnd").val();
    });

    $.getJSON("api/v1/accounts/ownersaccounts", function (result) {
        if (result.length === 0) {
            showError("Brak kont - dodaj przynajmniej jedno konto.");
        } else {
            fillForm(result)
        }
    }).fail(function (response) {
        showError(response.responseJSON.message);
    });

    showError = function (msg) {
        $("#messageModalMessage").text(msg);
        $("#messageModalLabel").text("Błąd podczas pobierania kont")
        $('#messageModal').modal('show');
    }
});

let accounts = [];

fillForm = function (data) {
    accounts = data;
    const options = $("#options");
    let listItems;

    for (var i = 0; i < data.length; i++) {
        listItems += "<option value='" + data[i].id + "'>" + data[i].description + "</option>";
    }
    $("#accountsLength").html("<div id='accountsLength' class='mr-5'> Liczba kont: " + accounts.length + "</div>");
    $("#DLState").html(listItems);
}