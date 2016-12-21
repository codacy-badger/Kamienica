'use strict';


App.factory('MeterWater', ['$resource', function ($resource) {
	//$resource() function returns an object of resource class
    return $resource(
    		'/api/v1/meters/WATER/:id.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);