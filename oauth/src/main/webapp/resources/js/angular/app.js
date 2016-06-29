'use strict';

/* App Module */
var app = angular.module('app', ['ngRoute', 'PhoneCtrl', 'phoneFilters', 'phoneServices']);

app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
	/* $httpProvider.defaults.headers.common['Authorization'] = 'Basic YWRtaW46YWRtaW4='; */
				
    $routeProvider
	  .when('/login', {
	    templateUrl: '/WEB-INF/partials/login-form.jsp',
		controller: 'LoginCtrl'
	  })
	  .otherwise({
        redirectTo: '/login'
      });
}]);
