require("./styles/basic.scss")
/* TEST */
let utils = require('./modules/utils')
let config = require('./config')
console.log(config)
let data = {
  method : "POST",
  url : ""
}

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
