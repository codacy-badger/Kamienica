'use strict';

App.controller('PaymentController', [
    '$scope',
    'Payment', '$http',
    function($scope, Payment, $http) {

        $scope.data = {
            show: true
        };
        var testVar = $http.get('http://localhost:8080/Kamienica/api/v1/payments.json').
        success(function(data) {
              

                console.log('data');
                console.log(data);
                self.list = data;
                
                console.log('self.listasasda');
           	 console.log(self.list);
           	 return data;
           
            })
            .error(function(data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        $scope.media = 'Energia';

        var self = this;
        self.payment = new Payment();
        self.entity;
        self.list = [];
        self.payments = [];
        self.errors = []
        var arrayIndex;


      
        

        self.switchForm = function(media) {

            if (media === 'energy') {

                $scope.media = 'Energia';
               
            } else if (media === 'gas') {

                $scope.media = 'Gaz';
               
            }else if (media === 'water') {

                $scope.media = 'Woda';
               
            }
            
        }

        self.fetchAll = function() {
        	self.payments = Payment.query();
        	console.log('testvatr');
        	 console.log(testVar);
        };

        self.fetchAll();
    }
]);