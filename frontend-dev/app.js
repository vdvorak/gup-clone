require("./styles/basic.scss")
require("./styles/favourites.scss")

/* TEST json require */
let utils = require('./modules/utils')
let config = require('./config')

let data = {
  method : "POST",
  url : ""
}

let app = angular.module('gup', ['ngRoute'])

// Router config
app
  .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){

    $routeProvider
      .when('/', {
        templateUrl:"templates/index.html",
        controller: require('./controllers/basic'),
        controllerAs: "basic"
      })
      .otherwise({
         redirectTo: '/'
      })

    $locationProvider.html5Mode({
      enabled : true,
      requireBase : false
    })

  }])
  .directive('blueButton', require('./directives/blueButton'))
  .directive('greyButton', require('./directives/greyButton'))
  .controller('mainCtrl', require('./controllers/temp'))
  .run()
