'use strict';

App.controller('DivisionController', [
    '$scope',
    'Division','Tenant', 'Apartment', '$http',
    function($scope, Division,  Tenant, Apartment,$http) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.division = new Division();
        self.entity;
        self.divisions = [];
        self.errors = []
        var arrayIndex;
       self.tenants = Tenant.query();
        self.apartments = Apartment.query();

        self.fetchAll = function() {
            self.divisions = Division.query();
           console.log(self.divisions);
        };

        self.fetchAll();


//        var testVar = $http.get('http://localhost:8080/Kamienica/api/v1/divisions/paginated.json?page=1&size=2').
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
            self.division.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.divisions.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };

        self.updateItem = function() {

            self.division.$update(function() {}).then(function(ok) {
                console.log(ok);
                self.divisions.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem = function(identity, indexArray) {
            var division = Division.get({
                id: identity
            }, function() {
                division.$delete(function() {}).then(function(ok) {
                    self.divisions.splice(indexArray, 1);
                }, function(error) {
                    $scope.errorField = true;
                    $scope.errorMsg = error.data.message;
                })
            })
        };

        self.submit = function() {
            console.log(self.division);
            if (self.division.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.division = angular.copy(self.divisions[indexOfArray]);
            self.entity = angular.copy(self.divisions[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.division.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem(id, arrayIndex);
        };

        self.reset = function() {
            self.division = new Division();
           // $scope.myForm.$setPristine(); // reset Form

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