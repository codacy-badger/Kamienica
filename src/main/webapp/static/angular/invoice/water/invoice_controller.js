"use strict";

App.controller("InvoiceController", [
    "$scope",
    "Invoice", "BaseReading",
    function($scope, Invoice, BaseReading) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.invoice = new Invoice();
        self.entity;
        self.invoices = [];
        self.readings = [];
        self.errors = []
        var arrayIndex;

        self.fetchAllUsers = function() {
            self.invoices = Invoice.query();
        };

        self.fetchAllUsers();

        self.createItem = function() {
            self.invoice.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = "zapisano do bazy";
                self.invoices.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });
        };

        self.updateItem = function() {
            self.invoice.$update(function() {}).then(function(ok) {
                self.invoices.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = "Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie";
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteItem = function(identity, indexArray) {
            var invoice = self.invoices[indexArray];

            invoice.$delete(function() {}).then(function(ok) {
                self.invoices.splice(indexArray, 1);
            }, function(error) {
                $scope.errorField = true;
                $scope.errorMsg = error.data.message;
            });
        };


        self.submit = function() {
            if (self.invoice.id == null) {
                self.createItem();
            } else {
                self.updateItem();

            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.invoice = angular.copy(self.invoices[indexOfArray]);
            self.entity = angular.copy(self.invoices[indexOfArray]);
            arrayIndex = indexOfArray;

        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.invoice.id === id) { // If it is the one shown on
                self.reset();
            }

            self.deleteItem(id, arrayIndex);
        };

        self.reset = function() {
            self.invoice = new Invoice();
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
                self.readings = BaseReading.query();
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