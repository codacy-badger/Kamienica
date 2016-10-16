'use strict';


App.factory('Payment',  function ($resource) {
    return {
    	
    	getEnergy: function() {
    		return $resource('http://localhost:8080/Kamienica/api/v1/payments.json?media=ENERGY');
    	}
    	
    	
    };
});

//App.factory('Payment', ['$resource', function ($resource) {
//    return $resource(
//    		'http://localhost:8080/Kamienica/api/v1/payments.json', 
//    		{
//    			
//    			'map':  {method:'GET', isArray: false},
//    			 'list':  {method:'GET', isArray: false},
//    	            'update': { method:'PUT' }
//    			
//    		}
//    );
//}]);