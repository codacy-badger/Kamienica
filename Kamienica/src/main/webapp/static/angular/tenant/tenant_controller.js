'use strict';

Tenant.controller('TenantController', [
		'$scope',
		'Tenant',
		function($scope, Tenant) {
			$scope.toggle = true;
			var self = this;
			self.tenant = new Tenant();

			self.tenants = [];

			self.fetchAllUsers = function() {
				self.tenants = Tenant.query();
			};

			self.createUser = function() {
				self.tenant.$save(function(response) {
					// if (response.$resolved == true) {
					// console.log(self.tenant);
					// self.tenants.push(self.tenant);
					// }// self.fetchAllUsers();

					self.fetchAllUsers();
				});
			};

			self.updateUser = function() {
				self.tenant.$update(function(response) {
					if (response.$resolved == true) {
						self.tenants.splice(self.tenants
								.indexOf(self.tenant), 1, self.tenant)
					}// self.fetchAllUsers();
				});

			};

			self.deleteUser = function(identity) {

				var tenant = Tenant.get({
					id : identity
				}, function(response) {
					console.log('tu jeszcze jestem1');
					tenant.$delete(function(response, success, failure) {
						console.log('tu jeszcze jestem2');
						console.log(failure);
						if (response.$resolved == true) {
							console
									.log('Deleting tenant with id ',
											identity);
							self.tenants.splice(self.tenants
									.indexOf(tenant), 1)
						}
						// console.log('11111')
						// console.log(response.$resolved);
						// console.log('22222')
						// self.fetchAllUsers();
						// self.splice(id, 1);
					})
				})
			};

			self.fetchAllUsers();

			$scope.toggleFilter = function() {
				$scope.toggle = $scope.toggle === false ? true : false;
			}
			$scope.$watch('toggle', function() {
				$scope.text = $scope.toggle ? 'Dodaj mieszkanie'
						: 'Lista mieszkań';
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
				self.reset();
				$scope.toggle = $scope.toggle === false ? true : false;
			};

			self.edit = function(id) {
				console.log('id to be edited', id);
				$scope.toggle = $scope.toggle === false ? true : false;
				for (var i = 0; i < self.tenants.length; i++) {
					if (self.tenants[i].id === id) {
						self.tenant = angular.copy(self.tenants[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
//				if (self.tenant.id === id) {// If it is the one shown on
//					// screen, reset screen
//					self.reset();
//				}
				self.deleteUser(id);
			};

			self.reset = function() {
				self.tenant = new Tenant();
				$scope.myForm.$setPristine(); // reset Form
			};

		} ]);
