'use strict'

module.exports = function($scope) {
  this.message = ""
  this.messages = []

  this.init = function() {
    /* Connect websockets */
    this.wsUri = `ws://${document.location.host}${document.location.pathname}whiteboardendpoint`;
    this.websocket = new WebSocket(wsUri);

    this.websocket.onerror = function(evt) { onError(evt) };

    function onError(evt) {
        console.error(evt.data)
    }
  }

  let handler = function(e) {
    if(e.which == 13)
      this.send.call(this)
  }.bind(this)

  document.addEventListener('keydown', handler)

  this.send = function() {
    
  }
}
