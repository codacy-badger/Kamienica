'use strict';

App.controller('TenantController', [
    '$scope',
    'Tenant', 'Apartment',
    function($scope, Tenant, Apartment) {

        $scope.toggle = true;
        $scope.errorField = false;
        $scope.data = {
            show: true
        };

        var self = this;
        self.tenant = new Tenant();
        self.entity;
        self.tenants = [];
        self.errors = []
        var arrayIndex;

        self.apartments = Apartment.query();

        self.fetchAllUsers = function() {
            self.tenants = Tenant.query();
        };

        self.createUser = function() {
            self.tenant.$save(function() {}).then(function(ok) {
                $scope.errorField = true;
                $scope.errorMsg = 'zapisano do bazy';
                self.tenants.push(ok);
                self.reset();
                $scope.toggle = $scope.toggle === false ? true : false;
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });
        };
        
//        self.updateUser = function() {
//            var tmp = self.tenant;
//            var index = self.tenants.indexOf(self.tenant);
//            console.log(self.tenant);
//            console.log(index);
//            self.tenant.$update(function(response) {
//                console.log(response);
//                console.log(self.tenant);
//                if (response.$resolved == true) {
//                    self.tenants.splice(self.tenants
//                        .indexOf(self.tenant), 1, tmp);
//                } // self.fetchAllUsers();
//            });
//
//            self.reset();
//            $scope.toggle = $scope.toggle === false ? true : false;
//        };
        
        
        self.updateUser = function() {
            self.tenant.$update(function() {}).then(function(ok) {
                console.log(ok);
                self.tenants.splice(arrayIndex, 1, ok);
            }, function(error) {
                $scope.errors = error.data;
                $scope.errorField = true;
                $scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
            });;

            self.reset();
            $scope.toggle = $scope.toggle === false ? true : false;
        };

        self.deleteUser = function(identity, indexArray) {
        	 var apartment = Apartment.get({
                 id: identity
             }, function() {
                 apartment.$delete(function() {}).then(function(ok) {
                     self.apartments.splice(indexArray, 1);
                 }, function(error) {
                     $scope.errorField = true;
                     $scope.errorMsg = error.data.message;
                 })
             })
        };

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

        self.fetchAllUsers();

        $scope.toggleFilter = function() {

            $scope.toggle = $scope.toggle === false ? true : false;

        }
        $scope.$watch('toggle', function() {
            // $scope.toggle ? null : self.reset();

            $scope.text = $scope.toggle ? 'Dodaj' :
                'Lista';
        })

        self.submit = function() {
            if (self.tenant.id == null) {
                console.log('Saving New Tenant', self.tenant);
                self.createUser();
            } else {
                console.log('Upddating tenant with id ',
                    self.tenant.id);
                self.updateUser();
                console
                    .log('Tenant updated with id ',
                        self.tenant.id);
            }

        };

        self.edit = function(id, indexOfArray) {
            self.clearError();
            $scope.toggle = $scope.toggle === false ? true : false;
            self.tenant = angular.copy(self.tenants[indexOfArray]);
            self.entity = angular.copy(self.tenants[indexOfArray]);
            arrayIndex = indexOfArray;
        };

        self.remove = function(id, arrayIndex) {
            self.clearError();
            if (self.tenant.id === id) { // If it is the one shown on
                // screen, reset screen
                self.reset();
            }

            self.deleteUser(id, arrayIndex);
        };

        self.reset = function() {
            self.tenant = new Tenant();
            $scope.myForm.$setPristine(); // reset Form

        };

        self.clearError = function() {
            $scope.errorField = false;
            $scope.errorMsg = '';
        }

    }
]);