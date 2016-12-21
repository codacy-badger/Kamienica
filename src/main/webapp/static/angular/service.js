'use strict';


App

.factory('Apartment', ['$resource', function ($resource) {
	

	//TODO ugly fix to run locally and on heroku. Needs better solution
	 var path = location.origin
    if ( path.includes('localhost')) {
   	 path = path + '/Kamienica'
   	 
    };
    
    
    return $resource(
    		path+'/api/v1/apartments/:id.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}])


.factory('Tenant', ['$resource', function ($resource) {
	//$resource() function returns an object of resource class
    return $resource(
    		path+'/api/v1/tenants/:id.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}])

;