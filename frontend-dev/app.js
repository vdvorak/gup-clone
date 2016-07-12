require("./styles/basic.scss")
require("./styles/favourites.scss")
require("./styles/edit-profile.scss")

/* TEST json require */
let utils = require('./modules/utils')
let config = require('./config')

utils.request({
  method : config.routes.getBulletins.method,
  url : config.api.url + config.routes.getBulletins.url,
  data : {
    "skip": 0,
    "limit": 20
  },
  success : data => console.log(data),
  headers : {
    "Content-Type" : "application/json"
  }
})

let app = angular.module('gup', ['ngRoute'])

// Router config
app
  .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){

    $routeProvider
      .when('/', {
        templateUrl:"templates/index.html",
        controller: require('./controllers/index'),
        controllerAs: "index"
      })
      .when('/error/403', {
        templateUrl:"templates/error403.html",
        controller: require('./controllers/error'),
        controllerAs: "err"
      })
      .when('/error/404', {
        templateUrl:"templates/error404.html",
        controller: require('./controllers/error'),
        controllerAs: "err"
      })
      .when('/error/500', {
        templateUrl:"templates/error500.html",
        controller: require('./controllers/error'),
        controllerAs: "err"
      })
      .when('/bulletinDetails', {
        templateUrl:"templates/bulletinDetails.html",
        controller: require('./controllers/bulletinDetails'),
        controllerAs: "bdetailed"
      })
      .when('/editProfile', {
        templateUrl: "templates/edit-profile.html",
        controller: require('./controllers/editProfile'),
        controllerAs: "profile"
      })
      .when('/favourites', {
        templateUrl:"templates/favourites.html",
        controller: require('./controllers/favourites'),
        controllerAs: "favourite"
      })
      .otherwise({
         redirectTo: '/'
      })

    $locationProvider.html5Mode({
      enabled : true,
      requireBase : false
    })
  }])
  .directive('text', require('./directives/text'))
  .directive('niceButton', require('./directives/niceButton'))
  .controller('mainCtrl', require('./controllers/main'))
  .run()
