'use strict'

require("./styles/basic.scss")
require("./styles/favourites.scss")
require("./styles/edit-profile.scss")
require("./styles/profile.scss")

require("./modules/logger")()

window.ee = require('./modules/events')
window.ee.init()

const materials = require('./modules/materials'),
      router = require('./modules/router')

let app = angular.module('gup', ['ngRoute'])

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
