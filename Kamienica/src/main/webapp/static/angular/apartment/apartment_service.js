'use strict';


App.factory('Apartment', ['$resource', function ($resource) {
	//$resource() function returns an object of resource class
    return $resource(
    		'http://localhost:8080/Kamienica/api/v1/apartments/:id.json', 
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			update: {
    			      method: 'PUT' // To send the HTTP Put request when calling this custom update method.
    			}
    			
    		}
    );
}]);