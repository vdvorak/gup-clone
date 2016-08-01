'use strict'

module.exports = function() {
  return {
    restrict : "A",
    controller : function($scope, $element, $attrs) {
      let el, parent = $element[0]

      let selector = $attrs.ngDrag
      try {
          el = $attrs.ngDrag ? parent.querySelector($attrs.ngDrag) : parent
      }catch(e) {
        el = parent
      }

      el.style.cursor = "move"


      let dragStart = false
      let xOld, yOld

      let onMouseMoveHandler = function(e) {
        if(!this.dragStart) return

        let x = e.pageX,
            y = e.pageY

        if(typeof this.xOld == "undefined" && typeof this.yOld == "undefined") {
          this.xOld = x
          this.yOld = y
          return
        }

        let deltaX = this.xOld - x,
            deltaY = this.yOld - y

        this.xOld = x
        this.yOld = y

        let styles = window.getComputedStyle(parent)

        let left = parseInt(styles.left),
            right = parseInt(styles.right),
            top = parseInt(styles.top),
            bottom = parseInt(styles.bottom)

        parent.style.left = `${left - deltaX}px`,
        parent.style.right = `${right - deltaX}px`
        parent.style.top = `${top - deltaY}px`
        parent.style.bottom = `${bottom-deltaY}px`

      }.bind(this)

      el.addEventListener('mousemove', onMouseMoveHandler)

      el.addEventListener('mousedown', function(e) {
        this.dragStart = true

      }.bind(this))

      document.addEventListener('mouseup', function(e) {
        this.dragStart = false
      }.bind(this))
    }
  }
}
