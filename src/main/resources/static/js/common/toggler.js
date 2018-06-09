
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
                drawTableForApartments();
                //drawTable();
            }
        }
    }).fail(function (response) {
        showError(response.responseJSON.message);
    });


    $('select').change(function () {
        residenceArrayIndex = $(this).val();
        drawTableForApartments();
        //drawTable();

    });

    $(".dropdown-menu li").click(function () {
        $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
        $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
        $("#tableContent").removeAttr('hidden');
    });


  
});
