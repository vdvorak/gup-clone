'use strict'

require("./styles/basic.scss")
require("./styles/favourites.scss")
require("./styles/edit-profile.scss")
require("./styles/profile.scss")

const materials = require('./modules/materials'),
      router = require('./modules/router')

/* TEST json require */
let utils = require('./modules/utils')

let config = require('./config')

/* test request to backend */
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

// App config
app
  .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
    for(let key in router)
      $routeProvider.when(key, router[key])

    $routeProvider.otherwise({
      redirectTo: '/error/404'
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
