'use strict';



App.factory('PaymentWater', ['$resource', function ($resource) {
    return $resource(
    		'/api/v1/payments.json?media=WATER',
    		{
    			
    			query:  {method:'GET', isArray:true}
    			
    		}
    );
}]);