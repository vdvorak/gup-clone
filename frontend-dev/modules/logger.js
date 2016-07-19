'use strict'

const debug = require('../data/debug')

let ctx = module.exports = {}

module.exports = function() {
  ctx.DEBUG_LEVEL = debug.PRODUCTION

  console.log = (function() {
    let log = console.log
    if( ctx.DEBUG_LEVEL == debug.DEBUG )
      return log
    else return () => {}
  }.bind(ctx))()

  console.error = (function() {
    let error = console.error
    if( ctx.DEBUG_LEVEL == debug.DEBUG || ctx.DEBUG_LEVEL == debug.ONLY_ERRORS )
      return error
    else return () => {}
  }.bind(ctx))()

  console.info = (function() {
    let info = console.info
    if( ctx.DEBUG_LEVEL == debug.DEBUG || ctx.DEBUG_LEVEL == debug.ONLY_ERRORS )
      return info
    else return () => {}
  }.bind(ctx))()
}
