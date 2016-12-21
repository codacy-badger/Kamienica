'use strict';



App.factory('Invoice', ['$resource', function ($resource) {
    return $resource(
    		'/api/v1/invoices/ENERGY.json',
    		{id: '@id'},//Handy for update & delete. id will be set with id of instance
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);