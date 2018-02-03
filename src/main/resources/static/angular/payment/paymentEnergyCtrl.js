"use strict";

App.controller("PaymentController", [
    "$scope",
    "PaymentEnergy",
    function($scope, PaymentEnergy) {
        var self = this;
        self.payments = [];

        self.fetchAll = function() {
            self.payments = PaymentEnergy.query();
        };

        self.fetchAll("ENERGY");

    }
]);