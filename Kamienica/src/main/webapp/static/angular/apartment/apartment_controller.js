'use strict';

Apartment.controller('ApartmentController', [
		'$scope',
		'Apartment',
		function($scope, Apartment) {

			$scope.toggle = true;
			$scope.errorField = false;

			
			var self = this;
			self.apartment = new Apartment();
			self.apartments = [];
			self.errors = []
			
			$scope.data = {
				show : true
			};

			self.fetchAllUsers = function() {
				self.apartments = Apartment.query();
			};

			self.createUser = function() {
				self.apartment.$save(function() {

					// self.fetchAllUsers();

				}).then(function(ok) {
					console.log(ok);
					var apr = new Apartment();
					apr.id = ok.id;
					$scope.errorField = true;
					$scope.errorMsg = 'zapisano do bazy';
					console.log(apr);
					self.reset();
					$scope.toggle = $scope.toggle === false ? true : false;
				}, function(error) {
					console.log('blasssd...')
					console.log(error.data.errors[0]);
					$scope.errors = error.data.errors;
					$scope.errorField = true;
					$scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
					

				});
			};

			self.updateUser = function() {
				var tmp = self.apartment;
				var index = self.apartments.indexOf(self.apartment);
				console.log(self.apartment);
				console.log(index);
				self.apartment.$update(function(response) {
					console.log(response);
					console.log(self.apartment);
					if (response.$resolved == true) {
						self.apartments.splice(self.apartments
								.indexOf(self.apartment), 1, tmp);
					}// self.fetchAllUsers();
				});

				self.reset();
				$scope.toggle = $scope.toggle === false ? true : false;
			};

			self.deleteUser = function(identity, indexArray) {

				var apartment = Apartment.get({
					id : identity
				}, function() {

					apartment.$delete(function() {

						console.log('Deleting apartment with id ', identity);
						self.apartments.splice(indexArray, 1);

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

				if ($scope.text === 'Dodaj mieszkanie') {

					$scope.text = 'Lista mieszkań';
					self.reset();
					$scope.toggle = false;
				} else {
					$scope.text = 'Dodaj mieszkanie';
					$scope.toggle = true;
				}

			}

			self.fetchAllUsers();

			$scope.toggleFilter = function() {

				$scope.toggle = $scope.toggle === false ? true : false;

			}
			$scope.$watch('toggle', function() {
				// $scope.toggle ? null : self.reset();

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
				
			};

			self.edit = function(id) {
				self.clearError();
				$scope.toggle = $scope.toggle === false ? true : false;
				for (var i = 0; i < self.apartments.length; i++) {
					if (self.apartments[i].id === id) {
						self.apartment = angular.copy(self.apartments[i]);
						this.arrayIndex = i;
						break;
					}
				}
			};

			self.remove = function(id, arrayIndex) {
				self.clearError();
				if (self.apartment.id === id) {// If it is the one shown on
					// screen, reset screen
					self.reset();
				}

				self.deleteUser(id, arrayIndex);
			};

			self.reset = function() {
				self.apartment = new Apartment();
				$scope.myForm.$setPristine(); // reset Form
			};

			self.clearError = function() {
				$scope.errorField = false;
				$scope.errorMsg = '';
			}

		} ]);

// 'use strict';
//
// Apartment.controller('ApartmentController', [
// '$scope',
// 'Apartment',
// function($scope, Apartment) {
// $scope.toggle = true;
// var self = this;
// self.apartment = new Apartment();
//
// self.apartments = [];
//
// self.fetchAllUsers = function() {
// self.apartments = Apartment.query();
// };
//
// self.createUser = function() {
// self.apartment.$save(function() {
// self.fetchAllUsers();
// });
// };
//
// self.updateUser = function() {
// self.apartment.$update(function() {
// self.fetchAllUsers();
// });
//
// };
//
// self.deleteUser = function(identity) {
// var apartment = Apartment.get({
// id : identity
// }, function() {
// apartment.$delete(function() {
// console.log('Deleting apartment with id ', identity);
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
// : 'Lista mieszkań';
// })
//
// self.submit = function() {
// if (self.apartment.id == null) {
// console.log('Saving New Apartment', self.apartment);
// self.createUser();
// } else {
// console.log('Upddating apartment with id ',
// self.apartment.id);
// self.updateUser();
// console
// .log('Apartment updated with id ',
// self.apartment.id);
// }
// //self.reset();
// //$scope.toggle = $scope.toggle === false ? true : false;
// };
//
// self.edit = function(id) {
// console.log('id to be edited', id);
// $scope.toggle = $scope.toggle === false ? true : false;
// for (var i = 0; i < self.apartments.length; i++) {
// if (self.apartments[i].id === id) {
// self.apartment = angular.copy(self.apartments[i]);
// break;
// }
// }
// };
//
// self.remove = function(id) {
// console.log('id to be deleted', id);
// if (self.apartment.id === id) {// If it is the one shown on
// // screen, reset screen
// self.reset();
// }
// self.deleteUser(id);
// };
//
// self.reset = function() {
// self.apartment = new Apartment();
// $scope.myForm.$setPristine(); // reset Form
// };
//
// } ]);

