'use strict';



App.factory('PaymentEnergy', ['$resource', function ($resource) {
    return $resource(
    		'/Kamienica/api/v1/payments.json?media=ENERGY',
    		{
    			
    			query:  {method:'GET', isArray:true}
    			
    		}
    );
}]);