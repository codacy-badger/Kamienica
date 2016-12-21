'use strict';

App.factory('Invoice', ['$resource', function ($resource) {
    return $resource(
    		'/Kamienica/api/v1/invoices/GAS.json',
    		{id: '@id'},
    		{
    			query:  {method:'GET', isArray:true},
    			update: {method: 'PUT'}
    			
    		}
    );
}]);