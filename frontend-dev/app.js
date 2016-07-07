require("./styles/basic.scss")

/* Controllers */
const basicController = require('./controllers/basic')

let app = angular.module('gup', ['ngRoute', 'ngMaterial', 'ngMessages'])

/* Plug in controllers */
app.controller('basicController', basicController)

// Router config
app
  .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){

    $routeProvider
      .when('/', {
        templateUrl:"templates/index.html",
        controller: "basicController as basic"
      })
      .otherwise({
         redirectTo: '/'
      })

    $locationProvider.html5Mode({
      enabled : true,
      requireBase : false
    })

  }])
  .run()
