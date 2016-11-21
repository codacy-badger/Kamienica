'use strict';

App.controller('ReadingGasController', [
    '$scope',
    'ReadingGas', '$http',
    function($scope, ReadingGas, $http) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.readingGas = new ReadingGas();
        self.entity;
        self.readingGases = [];
        self.errors = []
        var arrayIndex;


        self.fetchAll = function() {
            self.readingGases = ReadingGas.query();
        };

        self.fetchAll();


        self.createItem = function() {
            self.readingGas.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.readingGases.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };

        self.updateItem = function() {

            self.readingGas.$update(function() {}).then(function(ok) {
                console.log(ok);
                self.readingGases.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem2 = function(identity, indexArray) {
        	var readingGas = self.readingGases[indexArray];
        	
        	readingGas.$delete(function() {}).then(function(ok) {
                self.readingGases.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        }; 
        

        self.submit = function() {
            console.log(self.readingGas);
            if (self.readingGas.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.readingGas = angular.copy(self.readingGases[indexOfArray]);
            self.entity = angular.copy(self.readingGases[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.readingGas.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem2(id, arrayIndex);
        };

        self.reset = function() {
            self.readingGas = new ReadingGas();
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