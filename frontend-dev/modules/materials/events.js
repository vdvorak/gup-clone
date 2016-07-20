'use strict'

let ctx = module.exports = {}

let privateScope = {}

module.exports.init = function() {
  privateScope.eventHandlers = {}
  privateScope.handlerId = 0

  privateScope.getHandlerId = function() {
    return privateScope.handlerId++
  }

  privateScope.registerHandler = function(name, handler) {
    if(!privateScope.eventHandlers[name])
      privateScope.eventHandlers[name] = []

    let id = privateScope.getHandlerId()
    privateScope.eventHandlers[name].push({
      id : id,
      handler : handler
    })

    return id
  }

  privateScope.handle = function(eventName, data) {
    if(privateScope.eventHandlers[eventName])
      privateScope.eventHandlers[eventName].forEach(h => h.handler(data))
  }

  privateScope.removeHandler = function(id) {
    for(let key in privateScope.eventHandlers) {
      for(let t =0; t< privateScope.eventHandlers[key].length; t++) {
        if(privateScope.eventHandlers[key][t].id == id) {
          privateScope.eventHandlers[key] = privateScope.eventHandlers[key].filter(el => el.id !== id)
          return true
        }
      }
    }

    return false
  }

  privateScope.removeHandlerByName = function(name) {
    privateScope.eventHandlers[name] = []
  }
}


module.exports.on = function(eventName, handler) {
  return privateScope.registerHandler(eventName, handler)
}

/* Removes handler by id*/
module.exports.off = function(id) {
  return privateScope.removeHandler(id)
}

/* Removes all handlers by event name */
module.exports.remove = function(name) {
  return privateScope.removeHandlerByName(name)
}
/*
  {
    "name" : "form-submit",
    "data" : "whatever" <== optional
  }
*/
module.exports.emit = function(event) {
  let name = event.name || (() => { throw new Error("No event name") })()
  let data = event.data || null

  privateScope.handle(name, data)
}
