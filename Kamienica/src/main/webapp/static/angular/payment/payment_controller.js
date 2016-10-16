'use strict';

App.controller('PaymentController', [
    '$scope',
    'Payment', '$http',
    function($scope, Payment, $http) {
    	var self = this;
    	self.list = [];
    	self.energy = [];
    	self.gas = [];
    	self.water = [];
    	
//        $scope.data = {
//            show: true
//        };
//        var testVar = $http.get('http://localhost:8080/Kamienica/api/v1/payments.json').
//        success(function(data) {
//        	
//        	self.list = data[0];
//           	 return data;
//            })
//            .error(function(data, status, headers, config) {
//            });
        $scope.media = 'Energia';
        
        self.payment = new Payment();
        self.entity;
        self.list = Payment.getEnergy();
        
        self.energy = self.list[0];
    	self.gas = self.list[1];
    	self.water = self.list[2];
        
        console.log(self.list);
        

        self.switchForm = function(media) {

            if (media === 'energy') {

                $scope.media = 'Energia';
               
            } else if (media === 'gas') {

                $scope.media = 'Gaz';
               
            }else if (media === 'water') {

                $scope.media = 'Woda';
               
            }
            
        }

//        self.fetchAll = function() {
//        	self.payments = Payment.query();
//        	console.log('testvatr');
//        	 console.log(testVar);
//        };

//        self.fetchAll();
    }
]);