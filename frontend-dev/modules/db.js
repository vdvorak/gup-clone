'use strict'

let utils = require('./utils')

let ctx = module.exports = {}

module.exports.init = function(data) {
  /* init data from database here */
  this.favourites = null
  this.mailbox = null
  this.user = null
  this.notifications = {hello : "preved"}

  console.log("bd initialized")
}
