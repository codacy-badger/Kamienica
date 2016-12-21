'use strict';



App.factory('BaseReading', ['$resource', function ($resource) {
    return $resource(
    		'/Kamienica/api/v1/readings/unresolved/GAS.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);