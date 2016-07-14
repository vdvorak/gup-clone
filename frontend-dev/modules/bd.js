'use strict'

let utils = require('./utils')

let ctx = module.exports = {}

module.exports.init = function(data) {
  /* init data from database here */
  this.favourites = {}
  this.mailbox = {}
  this.user = {}
  this.notifications = {hello : "preved"}

  console.log("bd initialized")
}
