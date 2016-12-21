'use strict';



App.factory('Apartment', ['$resource', function ($resource) {
    return $resource(
    		//'/Kamienica/api/v1/apartments/:id.json', 
    		'/api/v1/apartments/:id.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);