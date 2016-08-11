'use strict';

Apartment.controller('ApartmentController', [
		'$scope',
		'Apartment',
		function($scope, Apartment) {
			$scope.toggle = true;
			var self = this;
			self.apartment = new Apartment();

			self.apartments = [];

			self.fetchAllUsers = function() {
				self.apartments = Apartment.query();
			};

			self.createUser = function() {
				self.apartment.$save(function(response) {
//					if (response.$resolved == true) {
//						console.log(self.apartment);
//						self.apartments.push(self.apartment);
//					}// self.fetchAllUsers();

					self.fetchAllUsers();
				});
			};

			self.updateUser = function() {
				self.apartment.$update(function(response) {
					if (response.$resolved == true) {
						self.apartments.splice(self.apartments
								.indexOf(self.apartment), 1, self.apartment)
					}// self.fetchAllUsers();
				});

			};

			self.deleteUser = function(identity) {
				var apartment = Apartment.get({
					id : identity
				}, function(response) {
					apartment.$delete(function() {
						if (response.$resolved == true) {
							console
									.log('Deleting apartment with id ',
											identity);
							self.apartments.splice(self.apartments
									.indexOf(apartment), 1)
						}
						// console.log('11111')
						// console.log(response.$resolved);
						// console.log('22222')
						// self.fetchAllUsers();
						// self.splice(id, 1);
					})
				});
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
				if (self.apartment.id == null) {
					console.log('Saving New Apartment', self.apartment);
					self.createUser();
				} else {
					console.log('Upddating apartment with id ',
							self.apartment.id);
					self.updateUser();
					console
							.log('Apartment updated with id ',
									self.apartment.id);
				}
				self.reset();
				$scope.toggle = $scope.toggle === false ? true : false;
			};

			self.edit = function(id) {
				console.log('id to be edited', id);
				$scope.toggle = $scope.toggle === false ? true : false;
				for (var i = 0; i < self.apartments.length; i++) {
					if (self.apartments[i].id === id) {
						self.apartment = angular.copy(self.apartments[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.apartment.id === id) {// If it is the one shown on
					// screen, reset screen
					self.reset();
				}
				self.deleteUser(id);
			};

			self.reset = function() {
				self.apartment = new Apartment();
				$scope.myForm.$setPristine(); // reset Form
			};

		} ]);
