'use strict';



App.factory('PaymentWater', ['$resource', function ($resource) {
    return $resource(
    		'http://localhost:8080/Kamienica/api/v1/payments.json?media=WATER', 
    		{
    			
    			query:  {method:'GET', isArray:true}
    			
    		}
    );
}]);