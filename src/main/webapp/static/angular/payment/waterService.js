'use strict';



App.factory('PaymentWater', ['$resource', function ($resource) {
	
	//TODO ugly fix to run locally and on heroku. Needs better solution
	 var path = location.origin
    if ( path.includes('localhost')) {
   	 path = path + '/Kamienica'
   	 
    };
	
    return $resource(
    		path+'/api/v1/payments.json?media=WATER',
    		{
    			
    			query:  {method:'GET', isArray:true}
    			
    		}
    );
}]);