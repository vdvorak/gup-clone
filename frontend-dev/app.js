'use strict'

require("./styles/basic.scss")
require("./styles/favourites.scss")
require("./styles/edit-profile.scss")
require("./styles/profile.scss")

require("./modules/logger")()

const materials = require('./modules/materials/index.js'),
      router = require('./modules/router')

let app = angular.module('gup', ['ngRoute', 'ngCookies'])

// App config
app
  .config(['$routeProvider', '$locationProvider', '$httpProvider', function( $routeProvider, $locationProvider, $httpProvider){
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    
    for(let key in router)
      $routeProvider.when(key, router[key])

    $routeProvider.otherwise({
      redirectTo: '/404'
    })

    $locationProvider.html5Mode({
      enabled : true,
      requireBase : false
    })
  }])
  .controller('mainCtrl', [ "$http", "$scope", "$location", "$timeout", "$cookies", "$cookieStore", require('./controllers/main')])

materials
  .init(app)
  .run()

  /* Event emmitter examples */
  let id = ee.on('muhahaha', function(data) {
    console.log("bugagashechko")
    console.log(data)
  })

  ee.emit({
    name : "muhahaha",
    data : [1,2,3,4,5]
  })

  ee.off(id)

  ee.emit({
    name : "muhahaha",
    data : [1,2,3,4,5]
  })
