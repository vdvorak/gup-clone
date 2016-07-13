'use strict'

const MODULES = {
  "checkbox" : require('../directives/checkbox'),
  "niceButton" : require('../directives/niceButton'),
  "text" : require('../directives/text')
}

let ctx = module.exports = {}

module.exports.init = app => {
  for(let key in MODULES)
    app.directive(key, MODULES[key])
    
  return app
}
