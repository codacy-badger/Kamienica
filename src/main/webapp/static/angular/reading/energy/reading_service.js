'use strict';



App.factory('Reading', ['$resource', function ($resource) {
    return $resource(
    		'http://localhost:8080/Kamienica/api/v1/readings/ENERGY.json', 
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);