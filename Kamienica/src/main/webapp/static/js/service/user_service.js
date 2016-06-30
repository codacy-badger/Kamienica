'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllUsers: function() {
					return $http.get('http://localhost:8080/Kamienica/api/v1/apartments')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createUser: function(apartment){
					return $http.post('http://localhost:8080/Kamienica/api/v1/apartments', apartment)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating apartment');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateUser: function(apartment, id){
					return $http.put('http://localhost:8080/Kamienica/api/v1/apartments'+id, apartment)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating apartment');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteUser: function(id){
					return $http.delete('http://localhost:8080/Kamienica/api/v1/apartments'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting apartment');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
