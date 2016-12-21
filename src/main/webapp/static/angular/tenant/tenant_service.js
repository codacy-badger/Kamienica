'use strict';


App

.factory('Tenant', ['$resource', function ($resource) {
	//$resource() function returns an object of resource class
    return $resource(
    		'/api/v1/tenants/:id.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}])




;