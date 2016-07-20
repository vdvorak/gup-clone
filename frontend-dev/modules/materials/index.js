'use strict'

const MODULES = {
  "checkbox" : require('./directives/checkbox'),
  "niceButton" : require('./directives/niceButton'),
  "text" : require('./directives/text'),
  "selectBox" : require('./directives/selectBox')
}

window.ee = require('./events')
ee.init()

let ctx = module.exports = {}

module.exports.init = app => {
  for(let key in MODULES)
    app.directive(key, MODULES[key])

  return app
}
