'use strict';

/* Controllers */
var PhoneCtrl = angular.module('PhoneCtrl', []);

PhoneCtrl.controller('LoginCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {
	$scope.user = {};
	$scope.errors = [];

	$scope.submitForm = function() {
		$http({
			method  : 'POST',
			url     : 'http://localhost:8081/login',
			data    : $scope.user,
			headers : {'Content-Type': 'application/json'}
		})
		.success(function(data) {
			if (data.errors) {
				$scope.errorEmail = data.errors.email;
				$scope.errorPassword = data.errors.password;
			} else {
				$scope.message = data.message;
				//$location.path( "/phonesz/motorola-xoom-with-wi-fi" );
				window.location.assign("http://localhost:8081");
			}
		})
		.error(function(data, status) {
			$scope.errors.push(status);
		});
	};
}]);
