'use strict'

let utils = require('./utils'),
    config = require('../data/config')

let ctx = module.exports = {}

const DEFAULT_IMAGE = ""

module.exports.init = function($http) {
  /* init data from database here */
  ctx.setDefaults()
  ctx.transport = $http

  ctx.checkUserIsLogged(function(err, data) {
    if(err) console.error(err)
    else {
      if(data) ctx.saveUserData(data)
      else console.log("User is not logged in")
    }
  }.bind(this))

  console.log("Database initialized")
}

module.exports.setDefaults = function() {
  console.log("Database :: defaults set")

  ctx.isLogged = false

  ctx.user = {
    emailGeneral : "",
    emailContact : "",
    phoneGeneral : "",
    phoneContact : "",
    workplace : "",
    position : "",
    skypeName : "",
    website : "",
    social : [],
    avatar : ""
  }
  ctx.unreadMessages = 0
  ctx.unreadNotifications = 0
}

module.exports.register = function(data, cb) {
  ctx.transport({
    method : config.routes.register.method,
    url : config.api.auth + config.routes.register.url,
    data : data,
    headers : {
      "Content-Type" : "application/json",
    },
    withCredentials : true
  })
  .then(data => cb(null, data.data))
  .catch(cb)
}

module.exports.checkEmail = function(email, cb) {
  ctx.transport({
    method : config.routes.checkEmail.method,
    url : config.api.auth + config.routes.checkEmail.url,
    data : email,
    headers : {
      "Content-Type" : "text/plain"
    },
    withCredentials : true
  })
  .then(data => cb(null, data.data))
  .catch(cb)
}

module.exports.login = function( data, cb ) {
  ctx.transport({
    method : config.routes.login.method,
    url : config.api.auth + config.routes.login.url,
    data : data,
    headers : {
      "Content-Type" : "application/json",
    },
    withCredentials : true
  })
  .then(data => cb(null, data.data))
  .catch(cb)
}

/* This method does saves user data in this module only, no backend communication */
module.exports.saveUserData = function(data) {
  if( !data ) return this.isLogged = false

  data = data || ""

  this.isLogged = true
  this.user.emailGeneral = data.profile.contact.emailGeneral || ""
  this.user.emailContact = data.profile.contact.contactEmails[0] || ""
  this.user.phoneGeneral = data.profile.mainPhoneNumber
  this.user.phoneContact = data.profile.contact.contactPhones[0] || ""
  this.user.workplace = data.profile.contact.companyName || ""
  this.user.position = data.profile.contact.position || ""
  this.user.skypeName = data.profile.contact.skypeUserName || ""
  this.user.website = data.profile.contact.linkToWebSite || ""
  this.user.social = data.profile.contact.socNetLink || []
  this.user.avatar = data.profile.imgId || DEFAULT_IMAGE
  this.unreadMessages = data.unreadMessages
  this.unreadNotifications = data.unreadEventsCount

  console.log("Database:: User data saved successfully( шутка ) ")
}

module.exports.checkUserIsLogged = function( cb ) {
  ctx.transport({
    method: config.routes.checkLogged.method,
    url: config.api.url + config.routes.checkLogged.url,
    withCredentials : true
  })
  .then(data => cb(null, data.data))
  .catch(cb)
}

module.exports.userLogout = function() {
  ctx.setDefaults()

  ctx.transport({
    method: config.routes.logout.method,
    url : config.api.auth + config.routes.logout.url
  }).then(()=>{}, ()=>{})
}
