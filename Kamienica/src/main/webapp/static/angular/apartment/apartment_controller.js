'use strict';

App.controller('ApartmentController', [
		'$scope',
		'Apartment',
		function($scope, Apartment) {

			$scope.toggle = true;
			$scope.errorField = false;
			$scope.data = {
					show : true
				};
			
			var self = this;
			self.apartment = new Apartment();
			self.entity;
			self.apartments = [];
			self.errors = []
			self.response;
			var arrayIndex;
			

			self.fetchAllUsers = function() {
				self.response = Apartment.query();
			};
			
			self.fetchAllUsers();
			

			self.createUser = function() {
				self.apartment.$save(function() {
				}).then(function(ok) {
					//var apr = new Apartment();
					//apr.id = ok.id;
					$scope.errorField = true;
					$scope.errorMsg = 'zapisano do bazy';
					self.response.objectList.push(ok);
					self.reset();
					$scope.toggle = $scope.toggle === false ? true : false;
				}, function(error) {
					$scope.errors = error.data;
					$scope.errorField = true;
					$scope.errorMsg = 'Nie powiódł się zapis do bazy. Popraw dane i spróbuj ponownie';
				   });
			};
			
			self.updateUser = function() {
				

				console.log('-entity--');
				console.log(self.entity);
//				var tmp = self.apartment;
//				self.apartment.$promise= undefined;
//				self.apartment.$resolved= true;
//				var index = self.apartments.indexOf(self.apartment);
//				console.log(tmp);
//				console.log('updateUser');
//			    console.log(self.apartment);
				self.apartment.$update(function() {
				}).then(function(ok) {
				console.log('ok');
				self.response.objectList.splice(arrayIndex, 1, ok);
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
					id : identity
				}, function() {
					apartment.$delete(function() {
					}).then(function(ok) {
						self.response.objectList.splice(indexArray, 1);
					}, function(error) {
						$scope.errorField = true;
						$scope.errorMsg = error.data.message;
					})
				})
			};

			self.submit = function() {
				console.log(self.apartment);
				if (self.apartment.id == null) {	
					self.createUser();
				} else {	
					self.updateUser();
				
				}
				
			};

			self.edit = function(id, indexOfArray) {
				self.clearError();
				$scope.toggle = $scope.toggle === false ? true : false;
				
//				for (var i = 0; i < self.response.objectList.length; i++) {
//					if (self.response.objectList[i].id === id) {
						self.apartment = angular.copy(self.response.objectList[indexOfArray]);
						self.entity = angular.copy(self.response.objectList[indexOfArray]);
						arrayIndex = indexOfArray;
						
//						break;
//					}
//				}				
//				for (var i = 0; i < self.apartments.length; i++) {
//					if (self.apartments[i].id === id) {
//						self.apartment = angular.copy(self.apartments[i]);
//						this.arrayIndex = i;
//						break;
//					}
//				}
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
			
			$scope.toggleFilter = function() {

				$scope.toggle = $scope.toggle === false ? true : false;

			}
			$scope.$watch('toggle', function() {
				// $scope.toggle ? null : self.reset();

				$scope.text = $scope.toggle ? 'Dodaj mieszkanie'
						: 'Lista mieszkań';
			})

			
			self.switchForm = function() {

				if ($scope.text === 'Dodaj mieszkanie') {

					$scope.text = 'Lista mieszkań';
					self.reset();
					$scope.toggle = false;
					$scope.errors = '';
					$scope.errorField = false;
					$scope.errorMsg = '';
				} else {
					$scope.text = 'Dodaj mieszkanie';
					$scope.toggle = true;
					$scope.errors = '';
					$scope.errorField = false;
					$scope.errorMsg = '';
				}

			}
			
			
		} ]);

