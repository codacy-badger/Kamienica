'use strict';

App.controller('ApartmentController', ['$scope', 'Apartment', function($scope, Apartment) {
          var self = this;
          self.apartment= new Apartment();
          
          self.apartments=[];
              
          self.fetchAllUsers = function(){
        	  self.apartments = Apartment.query();
          };
           
          self.createUser = function(){
        	  self.apartment.$save(function(){
        		  self.fetchAllUsers();
        	  });
          };

          self.updateUser = function(){
        	  self.apartment.$update(function(){
    			  self.fetchAllUsers();
    		  });
          };

         self.deleteUser = function(identity){
        	 var apartment = Apartment.get({id:identity}, function() {
        		  apartment.$delete(function(){
        			  console.log('Deleting apartment with id ', identity);
        			  self.fetchAllUsers();
        		  });
        	 });
          };

          self.fetchAllUsers();

          self.submit = function() {
              if(self.apartment.id==null){
                  console.log('Saving New Apartment', self.apartment);    
                  self.createUser();
              }else{
    			  console.log('Upddating apartment with id ', self.apartment.id);
                  self.updateUser();
                  console.log('Apartment updated with id ', self.apartment.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.apartments.length; i++){
                  if(self.apartments[i].id === id) {
                     self.apartment = angular.copy(self.apartments[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.apartment.id === id) {//If it is the one shown on screen, reset screen
                 self.reset();
              }
              self.deleteUser(id);
          };

          
          self.reset = function(){
              self.apartment= new Apartment();
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
