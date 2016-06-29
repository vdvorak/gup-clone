'use strict';

/* Services */
//var phoneServices = angular.module('phoneServices', ['ngResource']);
var phoneServices = angular.module('phoneServices', []);

/*
phoneServices.factory('Phone', ['$resource', function($resource){
    return $resource('json/:phoneId.json', {}, {
      query: {
		method : 'POST', 
		params : {phoneId:'phonesl'}, 
		isArray : true
	  }
    });
}]);
*/