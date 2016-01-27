$(document).ready(function () {
    
    jQuery.validator.addMethod(
        'notEqualTo', function(value, element, param) {
            return this.optional(element) || value != param;
        }, 'Proszę wypełnić pole.'
    );
    
    $('form').validate({
        ignore: '.ignore, :hidden',
        rules: {
            'firstName': { required: true, minlength: 5 },
            'form[surname]': { required: true, minlength: 5},
            'form[email]': { required: true, email: true },
            'form[phone]': { required: true, digits: true}
        },
        messages: {
            'firstName': 'TTTTTEEEESSST',
            'form[surname]': 'Podaj Nazwisko',
            'form[email]': 'Podaj Email',
            'form[phone]': 'Podaj Swoją Liczbę',
        }
    });
    
});

////$(document).ready(function () {
//
//    $('form').submit(function (e) {
//
//        var error = 0;
//        e.preventDefault();
//        $('form input').each(function () {
//            $(this).removeClass('error valid');
//
//            //eliminujemy submit z walidacji
//            if ($(this).attr("type") != 'submit') {
//                console.log($(this).val());
//
//                //walidacja cheboxa
//                if ($(this).attr("type") == 'checkbox') {
//
//                    if (!$(this).is(':checked')) {
//                        alert("zaakceptuj");
//                    }
//                }
//
//                //walidacja pol tekstowych
//                else {
//                    var test = $(this).val();
//                    //czy pole jest numerem
//                    if ($(this).attr("data-type") == 'number') {
//                        if (!$.isNumeric(test)) {
//                            $(this).addClass("error");
//                            error = 1;
//                        } else {
//                            $(this).addClass("valid");
//                        }
//                    }
//                    //czy prawidlowy format maila
//                    else if ($(this).attr("data-type") == 'email') {
//
//                        //    ^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$
//
//                    }
//                    //mierzymy dlugosc
//                    else if ($(this).attr("data-type") == 'text') {
//
//                        if ($(this).attr('data-length')) {
//                           
//                            if (test.length < $(this).attr("data-length")) {
//                                $(this).addClass("error");
//                                error = 1;
//                            }
//                        } else {
//                            $(this).addClass("valid");
//                        }
//
//
//                    }
//                }
//
//            }
//
//        });
//
//        if (error == 1) {
//            return false;
//        } else {
//            $(this).unbind('submit').submit();
//        }
//
//
//
//
//    });
//
//});

