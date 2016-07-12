'use strict'
/* Animation library */
const ctx = module.exports = {}


/* Adds ripple effect */
module.exports.addRipple = function(selector) {
  function handler(e) {
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

  try {
    let elements = document.querySelectorAll(selector)
    Array.prototype.forEach.call(elements, el => el.addEventListener('click', handler))

    return true
  } catch(e) {
    console.error(e)
    return false
  }
}

/* Adds input sliding label effect in and out */
module.exports.addTextEvents = function(selector) {
  this.onFocus = function(e) {
    console.log("Got text focus")
  }

  this.onBlur = function(e) {
    console.log("Got text blur")
  }

  try {
    let elements = document.querySelectorAll(selector)
    Array.prototype.forEach.call(elements, el => el.addEventListener('blur', this.onBlur))
    Array.prototype.forEach.call(elements, el => el.addEventListener('focus', this.onFocus))
    return true
  } catch(e) {
    console.error(e)
    return false
  }
}
