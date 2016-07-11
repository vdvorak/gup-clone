"use strict"

/*
  Usage:
  <div class="btn-blue">Click me!</div>
  <div class="btn-grey">Click me!</div>
*/
module.exports = function() {
  let buttons = []

  let blue = document.querySelectorAll(".btn-blue"),
      grey = document.querySelectorAll(".btn-grey")

  Array.prototype.forEach.call(blue, el => buttons.push(el))
  Array.prototype.forEach.call(grey, el => buttons.push(el))

  function handler(event) {
    let parent = event.target.parentNode
    let ink = parent.getElementsByClassName('ink')[0]
    ink.classList.remove('animate')

    let rect = parent.getBoundingClientRect()

    if( !ink.clientHeight && !ink.clientWidth ) {
      let d = Math.max(parent.clientWidth, parent.clientHeight)
      ink.style.height = ink.style.width = `${d}px`
    }

    ink.style.top = `${event.pageY - rect.top - ink.clientHeight/2}px`
    ink.style.left = `${event.pageX - rect.left - ink.clientWidth/2}px`
    ink.classList.add('animate')
  }

  function addChildrenToButton(el) {
    let text = el.textContent
    el.innerHTML = ""

    let span = document.createElement('span')
    span.classList.add('ink')
    el.appendChild(span)

    let p = document.createElement('p')
    p.innerHTML = text
    p.addEventListener("click", handler)
    p.classList.add('ripple')
    el.appendChild(p)
  }

  buttons.forEach(el => addChildrenToButton(el))
}
