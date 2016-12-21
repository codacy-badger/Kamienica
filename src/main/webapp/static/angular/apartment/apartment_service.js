'use strict';



App.factory('Apartment', ['$resource', function ($resource) {
    return $resource(
    		//'/Kamienica/api/v1/apartments/:id.json', 
    		'https://macfol.herokuapp.com/api/v1/apartments.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);