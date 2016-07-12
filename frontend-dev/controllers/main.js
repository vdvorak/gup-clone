"use strict"

/* Контроллер для управления  основным скелетом документа */
module.exports = function() {
  console.log('loaded temp')
  this.hello="hi"

  this.init = function() {
    console.log("Main controller init")

    this.initListeners()
  }

  this.initListeners = function() {
    let textInputs = document.getElementsByClassName("textInputs")
    Array.prototype.forEach.call(textInputs, el => {
      el.addEventListener('focus', this.textOnFocus)
      el.addEventListener('blur', this.textOnBlur)
    })

    let ripples = document.getElementsByClassName('ripple')
    Array.prototype.forEach.call(ripples, el => {
      el.addEventListener('click', this.rippleHandler)
    })
  }

  this.textOnFocus = function(e) {
    console.log("Got text focus")
  }

  this.textOnBlur = function(e) {
    console.log("Got text blur")
  }


  this.rippleHandler = function handler(e) {
    let parent = e.target.parentNode.parentNode.parentNode
    let ink = parent.getElementsByClassName('ink')[0]
    ink.classList.remove('animate')

    let rect = parent.getBoundingClientRect()

    if( !ink.clientHeight && !ink.clientWidth ) {
      let d = Math.max(parent.clientWidth, parent.clientHeight)
      ink.style.height = ink.style.width = `${d}px`
    }

    ink.style.top = `${e.pageY - rect.top - ink.clientHeight/2}px`
    ink.style.left = `${e.pageX - rect.left - ink.clientWidth/2}px`
    ink.classList.add('animate')
  }
}
