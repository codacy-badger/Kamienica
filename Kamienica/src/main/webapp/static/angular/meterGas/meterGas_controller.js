'use strict';

App.controller('MeterGasController', [
    '$scope',
    'MeterGas', 'Apartment',
    function($scope, MeterGas, Apartment) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.meterGas = new MeterGas();
        self.entity;
        self.meterGass = [];
        self.errors = []
        var arrayIndex;

        self.apartments = Apartment.query();

        self.fetchAllUsers = function() {
            self.meterGass = MeterGas.query();
        };
        
        self.fetchAllUsers();

        self.createItem = function() {
            self.meterGas.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.meterGass.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };

        self.updateItem = function() {
            	self.meterGas.$update(function() {}).then(function(ok) {
                self.meterGass.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem = function(identity, indexArray) {
        	var meterGas = self.meterGass[indexArray];
        	
        	meterGas.$delete(function() {}).then(function(ok) {
                self.meterGass.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        };

        self.submit = function() {
            if (self.meterGas.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.meterGas = angular.copy(self.meterGass[indexOfArray]);
            self.entity = angular.copy(self.meterGass[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.meterGas.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem(id, arrayIndex);
        };

        self.reset = function() {
            self.meterGas = new MeterGas();
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