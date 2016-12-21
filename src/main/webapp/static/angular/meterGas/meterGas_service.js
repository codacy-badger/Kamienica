'use strict';


App.factory('MeterGas', ['$resource', function ($resource) {
	//$resource() function returns an object of resource class
    return $resource(
    		'/Kamienica/api/v1/meters/GAS/:id.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);