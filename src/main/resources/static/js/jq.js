$(document).ready(function () {
    
    jQuery.validator.addMethod(
        "notEqualTo", function(value, element, param) {
            return this.optional(element) || value != param;
        }, "Proszę wypełnić pole."
    );
    
    $("form").validate({
        ignore: ".ignore", 
        rules: {
        	//tenant
            "firstName": { required: true, minlength: 2 },
            "lastName": { required: true, minlength: 2},
            "email": { required: true, email: true },
            "phone": { required: true, digits: true},
            "password": { required: true,  minlength: 5 },
            
          //apartment
            "intercom": { required: true,digits: true, minlength: 4, maxlength: 4},
            "apartmentNumber": { required: true, digits: true},
            "description": { required: true },
            //meters
            "description": { required: true},
            "serialNumber": { required: true},
            "unit": { required: true },
            "apartment": { required: true },
           //invoice
            "serialNumber": { required: true},
            "totalAmount": { required: true, number: true},
           //payment
            "paymentWater": { required: true},
            "paymentGas": { required: true},
            "paymentEnergy": { required: true}
            
           
        },
        messages: {
        	//tenant
//            "firstName": "Podaj Imie",
//            "lastName": "Podaj Nazwisko",
//            "email": "Podaj Email",
//            "phone": "Podaj Prawidłowy Nr Telefonu",
//            "password": "Hasło musi zawierać min. 5 znaków",
            //apartment
//            "intercom": "Intercom musi zawierać 4 cyfry",
//            "apartmentNumber": "Podaj numer mieszkania",
//            "description": "Podaj opis"
        }

    });
    
});

////$(document).ready(function () {
//
//    $("form").submit(function (e) {
//
//        var error = 0;
//        e.preventDefault();
//        $("form input").each(function () {
//            $(this).removeClass("error valid");
//
//            //eliminujemy submit z walidacji
//            if ($(this).attr("type") != "submit") {
//                console.log($(this).val());
//
//                //walidacja cheboxa
//                if ($(this).attr("type") == "checkbox") {
//
//                    if (!$(this).is(":checked")) {
//                        alert("zaakceptuj");
//                    }
//                }
//
//                //walidacja pol tekstowych
//                else {
//                    var test = $(this).val();
//                    //czy pole jest numerem
//                    if ($(this).attr("data-type") == "number") {
//                        if (!$.isNumeric(test)) {
//                            $(this).addClass("error");
//                            error = 1;
//                        } else {
//                            $(this).addClass("valid");
//                        }
//                    }
//                    //czy prawidlowy format maila
//                    else if ($(this).attr("data-type") == "email") {
//
//                        //    ^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$
//
//                    }
//                    //mierzymy dlugosc
//                    else if ($(this).attr("data-type") == "text") {
//
//                        if ($(this).attr("data-length")) {
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
//            $(this).unbind("submit").submit();
//        }
//
//
//
//
//    });
//
//});

