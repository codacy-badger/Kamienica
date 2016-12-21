'use strict';



App.factory('PaymentGas', ['$resource', function ($resource) {
    return $resource(
    		'/Kamienica/api/v1/payments.json?media=GAS',
    		{
    			
    			query:  {method:'GET', isArray:true}
    			
    		}
    );
}]);