"use strict"

const materials = require('../modules/materials')

/* Контроллер для управления  основным скелетом документа */
module.exports = function() {
  console.log('loaded temp')
  this.hello="hi"

  this.init = function() {
    console.log("Main controller init")

    materials.addRipple('.ripple')

    // this.initListeners()
  }

  this.initListeners = function() {
    let textInputs = document.getElementsByClassName("textInputs")
    Array.prototype.forEach.call(textInputs, el => {
      el.addEventListener('focus', this.textOnFocus)
      el.addEventListener('blur', this.textOnBlur)
    })
  }

  this.textOnFocus = function(e) {
    console.log("Got text focus")
  }

  this.textOnBlur = function(e) {
    console.log("Got text blur")
  }
}
