"use strict";

App.controller("ResidenceController", [
    "$scope",
    "Residence", "$http", "$window",
    function ($scope, Residence, $http, $window) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.residence = new Residence();
        self.entity;
        self.residences = [];
        self.errors = []
        var arrayIndex;


        self.fetchAll = function () {
            self.residences = Residence.query();
        };

        self.fetchAll();

        self.createItem = function () {
            self.residence.$save(function () {}).then(function (ok) {
                $scope.errorField = true;
                $scope.errorMsg = "zapisano do bazy";
                self.residences.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function (error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });
        };

        self.updateItem = function () {

            self.residence.$update(function () {}).then(function (ok) {
                console.log(ok);
                self.residences.splice(arrayIndex, 1, ok);
            }, function (error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem2 = function (identity, indexArray) {
            var confirmDelete = $window.confirm("UWAGA! Usunięcie nieruchomości spowoduje usunięciem wszystkich powiązanych z nim danych. Kontynuować?");
            var residence = self.residences[indexArray];

            if (confirmDelete) {
                residence.$delete(function () {}).then(function (ok) {
                    self.residences.splice(indexArray, 1);
                }, function (error) {
                    $scope.errorField = true;
                    $scope.errorMsg = error.data.message;
                });
            }
        };

        self.submit = function () {
            console.log(self.residence);
            if (self.residence.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function (id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.residence = angular.copy(self.residences[indexOfArray]);
            self.entity = angular.copy(self.residences[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function (id, arrayIndex) {
            self.clearError();
            if (self.residence.id === id) { // If it is the one shown on
                self.reset();
            }

            self.deleteItem2(id, arrayIndex);
        };

        self.reset = function () {
            self.residence = new Residence();
            $scope.myForm.$setPristine(); // reset Form

        };

        self.clearError = function () {
            $scope.errorField = false;
            $scope.errorMsg = "";
        }

        $scope.toggleFilter = function () {

            $scope.toggle = $scope.toggle === false ? true : false;

        }
        $scope.$watch("toggle", function () {
            $scope.text = $scope.toggle ? "Dodaj" :
                "Lista";
        })


        self.switchForm = function () {

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
