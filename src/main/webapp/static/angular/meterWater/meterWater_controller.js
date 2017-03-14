"use strict";

App.controller("MeterWaterController", [
    "$scope",
    "MeterWater", "Apartment",
    function($scope, MeterWater, Apartment) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.meterWater = new MeterWater();
        self.entity;
        self.meterWaters = [];
        self.errors = []
        var arrayIndex;

        self.apartments = Apartment.query();

        self.fetchAllUsers = function() {
            self.meterWaters = MeterWater.query();
        };

        self.fetchAllUsers();

        self.createItem = function() {
            self.meterWater.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = "zapisano do bazy";
                self.meterWaters.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });
        };

        self.updateItem = function() {
            self.meterWater.$update(function() {}).then(function(ok) {
                self.meterWaters.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem = function(identity, indexArray) {
            var meterWater = self.meterWaters[indexArray];

            meterWater.$delete(function() {}).then(function(ok) {
                self.meterWaters.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        };

        self.submit = function() {
            if (self.meterWater.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.meterWater = angular.copy(self.meterWaters[indexOfArray]);
            self.entity = angular.copy(self.meterWaters[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.meterWater.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteItem(id, arrayIndex);
        };

        self.reset = function() {
            self.meterWater = new MeterWater();
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