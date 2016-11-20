'use strict';

App.controller('PaymentController', [
    '$scope',
    'PaymentGas', 
    function($scope, PaymentGas) {

    

        var self = this;
        self.payments = [];


        self.fetchAll = function() {
            self.payments = PaymentGas.query();
        };

        self.fetchAll('GAS');








    }
]);