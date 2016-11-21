'use strict';

App.controller('ReadingEnergyController', [
    '$scope',
    'ReadingEnergy', 
    function($scope, ReadingEnergy) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.readingEnergy = new ReadingEnergy();
        self.entity;
        self.readingEnergys = [];
        self.errors = []
        var arrayIndex;


        self.fetchAllUsers = function() {
            self.readingEnergys = ReadingEnergy.query();
            console.log(self.readingEnergys);
        };
        
        self.fetchAllUsers();

        self.createItem = function() {
            self.readingEnergy.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.readingEnergys.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };

        self.updateItem = function() {
            	self.readingEnergy.$update(function() {}).then(function(ok) {
                self.readingEnergys.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };
        
        self.deleteItem = function(identity, indexArray) {
        	var readingEnergy = self.readingEnergys[indexArray];
        	
        	readingEnergy.$delete(function() {}).then(function(ok) {
                self.readingEnergys.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        }; 

//        self.deleteItem = function(identity, indexArray) {
//            var readingEnergy = ReadingEnergy.get({
//                id: identity
//            }, function() {
//                readingEnergy.$delete(function() {}).then(function(ok) {
//                    self.readingEnergys.splice(indexArray, 1);
//                }, function(error) {
//                    $scope.errorField = true;
//                    $scope.errorMsg = error.data.message;
//                })
//            })
//        };

        self.submit = function() {
            if (self.readingEnergy.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.readingEnergy = angular.copy(self.readingEnergys[indexOfArray]);
            self.entity = angular.copy(self.readingEnergys[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.readingEnergy.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem(id, arrayIndex);
        };

        self.reset = function() {
            self.readingEnergy = new ReadingEnergy();
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