'use strict'

require("./styles/basic.scss")
require("./styles/favourites.scss")
require("./styles/edit-profile.scss")
require("./styles/profile.scss")

require("./modules/logger")()

const materials = require('./modules/materials'),
      router = require('./modules/router')

let app = angular.module('gup', ['ngRoute'])

// App config
app
  .config(['$routeProvider', '$locationProvider', function( $routeProvider, $locationProvider){

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
  .controller('mainCtrl', require('./controllers/main'))

materials
  .init(app)
  .run()
