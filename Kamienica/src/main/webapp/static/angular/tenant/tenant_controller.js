'use strict';

App.controller('TenantController', [
		'$scope',
		'Tenant',
		function($scope, Tenant) {

			$scope.toggle = true;
			$scope.errorField = false;

			
			var self = this;
			self.tenant = new Tenant();
			self.tenants = [];
			self.errors = []
			
			$scope.data = {
				show : true
			};

			self.fetchAllUsers = function() {
				console.log(Tenant.query())
				self.tenants = Tenant.query();
			};

			self.createUser = function() {
				self.tenant.$save(function() {

					// self.fetchAllUsers();

				}).then(function(ok) {
					console.log(ok);
					var apr = new Tenant();
					apr.id = ok.id;
					$scope.errorField = true;
					$scope.errorMsg = 'zapisano do bazy';
					console.log(apr);
					self.reset();
					$scope.toggle = $scope.toggle === false ? true : false;
				}, function(error) {
					
					$scope.errors = error.data;
					$scope.errorField = true;
					$scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
					

				});
			};

			self.updateUser = function() {
				var tmp = self.tenant;
				var index = self.tenants.indexOf(self.tenant);
				console.log(self.tenant);
				console.log(index);
				self.tenant.$update(function(response) {
					console.log(response);
					console.log(self.tenant);
					if (response.$resolved == true) {
						self.tenants.splice(self.tenants
								.indexOf(self.tenant), 1, tmp);
					}// self.fetchAllUsers();
				});

				self.reset();
				$scope.toggle = $scope.toggle === false ? true : false;
			};

			self.deleteUser = function(identity, indexArray) {

				var tenant = Tenant.get({
					id : identity
				}, function() {

					tenant.$delete(function() {

						console.log('Deleting tenant with id ', identity);
						self.tenants.splice(indexArray, 1);

						// console.log('11111')
						// console.log(response.$resolved);
						// console.log('22222')
						// self.fetchAllUsers();
						// self.splice(id, 1);
					}).then(function(ok) {
						console.log("ok");
						console.log(ok);

					}, function(error) {
						$scope.errorField = true;
						$scope.errorMsg = error.data.message;
						// toastr.success("Item alterado com sucesso.");
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

				$scope.text = $scope.toggle ? 'Dodaj'
						: 'Lista';
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

			self.edit = function(id) {
				self.clearError();
				$scope.toggle = $scope.toggle === false ? true : false;
				for (var i = 0; i < self.tenants.length; i++) {
					if (self.tenants[i].id === id) {
						self.tenant = angular.copy(self.tenants[i]);
						this.arrayIndex = i;
						break;
					}
				}
			};

			self.remove = function(id, arrayIndex) {
				self.clearError();
				if (self.tenant.id === id) {// If it is the one shown on
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

		} ]);

// 'use strict';
//
// Tenant.controller('TenantController', [
// '$scope',
// 'Tenant',
// function($scope, Tenant) {
// $scope.toggle = true;
// var self = this;
// self.tenant = new Tenant();
//
// self.tenants = [];
//
// self.fetchAllUsers = function() {
// self.tenants = Tenant.query();
// };
//
// self.createUser = function() {
// self.tenant.$save(function() {
// self.fetchAllUsers();
// });
// };
//
// self.updateUser = function() {
// self.tenant.$update(function() {
// self.fetchAllUsers();
// });
//
// };
//
// self.deleteUser = function(identity) {
// var tenant = Tenant.get({
// id : identity
// }, function() {
// tenant.$delete(function() {
// console.log('Deleting tenant with id ', identity);
// self.fetchAllUsers();
// });
// });
// };
//
// self.fetchAllUsers();
//
// $scope.toggleFilter = function() {
// $scope.toggle = $scope.toggle === false ? true : false;
// }
// $scope.$watch('toggle', function() {
// $scope.text = $scope.toggle ? 'Dodaj mieszkanie'
// : 'Lista';
// })
//
// self.submit = function() {
// if (self.tenant.id == null) {
// console.log('Saving New Tenant', self.tenant);
// self.createUser();
// } else {
// console.log('Upddating tenant with id ',
// self.tenant.id);
// self.updateUser();
// console
// .log('Tenant updated with id ',
// self.tenant.id);
// }
// //self.reset();
// //$scope.toggle = $scope.toggle === false ? true : false;
// };
//
// self.edit = function(id) {
// console.log('id to be edited', id);
// $scope.toggle = $scope.toggle === false ? true : false;
// for (var i = 0; i < self.tenants.length; i++) {
// if (self.tenants[i].id === id) {
// self.tenant = angular.copy(self.tenants[i]);
// break;
// }
// }
// };
//
// self.remove = function(id) {
// console.log('id to be deleted', id);
// if (self.tenant.id === id) {// If it is the one shown on
// // screen, reset screen
// self.reset();
// }
// self.deleteUser(id);
// };
//
// self.reset = function() {
// self.tenant = new Tenant();
// $scope.myForm.$setPristine(); // reset Form
// };
//
// } ]);

