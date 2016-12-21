'use strict';

App.controller('ApartmentController', [
    '$scope',
    'Apartment', '$http',
    function($scope, Apartment, $http) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.apartment = new Apartment();
        self.entity;
        self.apartments = [];
        self.errors = []
        var arrayIndex;


        self.fetchAll = function() {
            self.apartments = Apartment.query();
        };

        self.fetchAll();


//        var testVar = $http.get('http://localhost:8080/Kamienica/api/v1/apartments/paginated.json?page=1&size=2').
//        success(function(data, status, headers, config) {
//                console.log('status');
//                console.log(status);
//
//                console.log('headers');
//                console.log(headers);
//
//                console.log('config');
//                console.log(config);
//
//                console.log('data');
//                console.log(data);
//                // this callback will be called asynchronously
//                // when the response is available
//                console.log('myData');
//                console.log(headers()['maxresult']);
//                console.log(headers()['page']);
//            })
//            .error(function(data, status, headers, config) {
//                // called asynchronously if an error occurs
//                // or server returns response with an error status.
   //         });


        self.createItem = function() {
            self.apartment.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.apartments.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };

        self.updateItem = function() {

            self.apartment.$update(function() {}).then(function(ok) {
                console.log(ok);
                self.apartments.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem2 = function(identity, indexArray) {
        	var apartment = self.apartments[indexArray];
        	
        	apartment.$delete(function() {}).then(function(ok) {
                self.apartments.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        }; 
        
//        self.deleteItem = function(identity, indexArray) {
//            var apartment = Apartment.get({
//                id: identity
//            }, function() {
//                apartment.$delete(function() {}).then(function(ok) {
//                    self.apartments.splice(indexArray, 1);
//                }, function(error) {
//                    $scope.errorField = true;
//                    $scope.errorMsg = error.data.message;
//                })
//            })
//        };

        self.submit = function() {
            console.log(self.apartment);
            if (self.apartment.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.apartment = angular.copy(self.apartments[indexOfArray]);
            self.entity = angular.copy(self.apartments[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.apartment.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem2(id, arrayIndex);
        };

        self.reset = function() {
            self.apartment = new Apartment();
            $scope.myForm.$setPristine(); // reset Form

        };

        self.clearError = function() {
            $scope.errorField = false;
            $scope.errorMsg = '';
        }

        $scope.toggleFilter = function() {

            $scope.toggle = $scope.toggle === false ? true : false;

        }
        $scope.$watch('toggle', function() {
            // $scope.toggle ? null : self.reset();

            $scope.text = $scope.toggle ? 'Dodaj' :
                'Lista';
        })


        self.switchForm = function() {

            if ($scope.text === 'Dodaj') {

                $scope.text = 'Lista';
                self.reset();
                $scope.toggle = false;
                $scope.errors = '';
                $scope.errorField = false;
                $scope.errorMsg = '';
            } else {
                $scope.text = 'Dodaj';
                $scope.toggle = true;
                $scope.errors = '';
                $scope.errorField = false;
                $scope.errorMsg = '';
            }

        }


    }
]);