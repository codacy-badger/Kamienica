'use strict';



App.factory('PaymentGas', ['$resource', function ($resource) {
    return $resource(
    		'http://localhost:8080/Kamienica/api/v1/payments.json?media=GAS', 
    		{
    			
    			query:  {method:'GET', isArray:true}
    			
    		}
    );
}]);