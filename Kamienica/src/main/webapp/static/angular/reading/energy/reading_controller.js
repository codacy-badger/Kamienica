'use strict';

App.controller('ReadingController', [
    '$scope',
    'Reading', 'MeterEnergy',
    function($scope, Reading, MeterEnergy) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.reading = new Reading();
        self.entity;
        self.readings = [];
        self.meters = MeterEnergy.query();
        self.errors = []
        var arrayIndex;


        self.fetchAllUsers = function() {
            self.readings = Reading.query();
            console.log(self.meters );
        };
        
        self.fetchAllUsers();

        self.createItem = function() {
            self.reading.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.readings.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };

        self.updateItem = function() {
            	self.reading.$update(function() {}).then(function(ok) {
                self.readings.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };
        
        self.deleteItem = function(identity, indexArray) {
        	var reading = self.readings[indexArray];
        	
        	reading.$delete(function() {}).then(function(ok) {
                self.readings.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        }; 

//        self.deleteItem = function(identity, indexArray) {
//            var reading = Reading.get({
//                id: identity
//            }, function() {
//                reading.$delete(function() {}).then(function(ok) {
//                    self.readings.splice(indexArray, 1);
//                }, function(error) {
//                    $scope.errorField = true;
//                    $scope.errorMsg = error.data.message;
//                })
//            })
//        };

        self.submit = function() {
            if (self.reading.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.reading = angular.copy(self.readings[indexOfArray]);
            self.entity = angular.copy(self.readings[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.reading.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem(id, arrayIndex);
        };

        self.reset = function() {
            self.reading = new Reading();
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