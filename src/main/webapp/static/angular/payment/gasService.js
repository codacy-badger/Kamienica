'use strict';



App.factory('PaymentGas', ['$resource', function ($resource) {
    return $resource(
    		'/api/v1/payments.json?media=GAS',
    		{
    			
    			query:  {method:'GET', isArray:true}
    			
    		}
    );
}]);