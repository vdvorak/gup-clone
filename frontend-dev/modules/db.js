'use strict'

let utils = require('./utils'),
    config = require('../data/config')

let ctx = module.exports = {}

module.exports.init = function(data) {
  /* init data from database here */
  this.favourites = null
  this.mailbox = null
  this.user = null
  this.notifications = {hello : "preved"}

  console.log("bd initialized")
}

module.exports.checkEmail = function(email, cb) {
  utils.request({
    "method" : config.routes.checkEmail.method,
    "url" : config.api.auth + config.routes.checkEmail.url,
    "data" : {
      "email" : email
    },
    "headers" : {
      "Content-Type" : "application/json"
    }
  }).then(data => cb(null, data), err => cb(err))
}

module.exports.login = function( data, cb ) {
  utils.request({
    "method" : config.routes.login.method,
    "url" : config.api.auth + config.routes.login.url,
    "data" : data,
    "headers" : {
      "Content-Type" : "application/json"
    }
  }).then(data => cb(null, data), err => cb(err))
}

module.exports.saveUserData = function(data) {
  data = JSON.parse(data)
  
  console.log("BD:: User data saved successfully( шутка ) ")
}
