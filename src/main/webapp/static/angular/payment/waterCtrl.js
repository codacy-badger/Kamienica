"use strict";

App.controller("PaymentWaterCtrl", [
    "$scope",
    "PaymentWater", 
    function($scope, PaymentWater) {
        var self = this;
        self.payments = [];
        self.fetchAll = function() {
            self.payments = PaymentWater.query();
        };
        self.fetchAll("WATER");
    }
]);