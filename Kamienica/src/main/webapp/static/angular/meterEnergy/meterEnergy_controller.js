'use strict';

App.controller('MeterEnergyController', [
    '$scope',
    'MeterEnergy', 'Apartment',
    function($scope, MeterEnergy, Apartment) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.meterEnergy = new MeterEnergy();
        self.entity;
        self.meterEnergys = [];
        self.errors = []
        var arrayIndex;

        self.apartments = Apartment.query();

        self.fetchAllUsers = function() {
            self.meterEnergys = MeterEnergy.query();
        };
        
        self.fetchAllUsers();

        self.createItem = function() {
            self.meterEnergy.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.meterEnergys.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };

        self.updateItem = function() {
            	self.meterEnergy.$update(function() {}).then(function(ok) {
                self.meterEnergys.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem = function(identity, indexArray) {
        	var meterEnergy = self.meterEnergys[indexArray];
        	
        	meterEnergy.$delete(function() {}).then(function(ok) {
                self.meterEnergys.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        }; 

        self.submit = function() {
            if (self.meterEnergy.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.meterEnergy = angular.copy(self.meterEnergys[indexOfArray]);
            self.entity = angular.copy(self.meterEnergys[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.meterEnergy.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem(id, arrayIndex);
        };

        self.reset = function() {
            self.meterEnergy = new MeterEnergy();
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