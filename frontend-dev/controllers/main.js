"use strict"

/* Контроллер для управления  основным скелетом документа */
module.exports = function() {
  console.log('loaded temp')
  this.hello="hi"

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
