"use strict";

App.controller("ApartmentController", [
    "$scope",
    "Apartment", "Residence", "$http",
    function($scope, Apartment, Residence, $http) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.apartment = new Apartment();
        self.entity;
        self.apartments = [];
        self.errors = [];
        var arrayIndex;
        self.residences = Residence.query();

        self.fetchAll = function() {
            self.apartments = Apartment.query();
        };

        self.fetchAll();

        self.createItem = function() {
            self.apartment.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = "zapisano do bazy";
                self.apartments.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });
        };

        self.updateItem = function() {

            self.apartment.$update(function() {}).then(function(ok) {
                console.log(ok);
                self.apartments.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
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
            $scope.errorMsg = "";
        }

        $scope.toggleFilter = function() {

            $scope.toggle = $scope.toggle === false ? true : false;

        }
        $scope.$watch("toggle", function() {
            $scope.text = $scope.toggle ? "Dodaj" :
                "Lista";
        })


        self.switchForm = function() {

            if ($scope.text === "Dodaj") {

                $scope.text = "Lista";
                self.reset();
                $scope.toggle = false;
                $scope.errors = "";
                $scope.errorField = false;
                $scope.errorMsg = "";
            } else {
                $scope.text = "Dodaj";
                $scope.toggle = true;
                $scope.errors = "";
                $scope.errorField = false;
                $scope.errorMsg = "";
            }

        }


    }
]);