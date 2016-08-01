'use strict'

const MODULES = {
  "checkbox" : require('./directives/checkbox'),
  "niceButton" : require('./directives/niceButton'),
  "text" : require('./directives/text'),
  "selectBox" : require('./directives/selectBox'),
  "textArea" : require('./directives/textArea'),
  "ngDrag" : require('./directives/ngDrag')
}

window.ee = require('./events')
ee.init()

let ctx = module.exports = {}

module.exports.init = app => {
  for(let key in MODULES)
    app.directive(key, MODULES[key])

  return app
}
