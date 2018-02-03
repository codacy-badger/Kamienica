"use strict";

App.factory("PaymentGas", ["$resource", function($resource) {

    //TODO ugly fix to run locally and on heroku. Needs better solution
    var path = location.origin
    if (path.includes("localhost")) {
        path = path + "/Kamienica";
    };

    return $resource(
        path + "/api/v1/payments.json?media=GAS", {
            query: {
                method: "GET",
                isArray: true
            }

        }
    );
}]);