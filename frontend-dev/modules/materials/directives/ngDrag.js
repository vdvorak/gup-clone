'use strict'

function getWindowSize() {
  let w = window,
    d = document,
    e = d.documentElement,
    g = d.getElementsByTagName('body')[0],
    x = w.innerWidth || e.clientWidth || g.clientWidth,
    y = w.innerHeight|| e.clientHeight|| g.clientHeight;

    return { x, y }
}

function clearSelections() {
  if (window.getSelection) {
    if (window.getSelection().empty) {  // Chrome
      window.getSelection().empty();
    } else if (window.getSelection().removeAllRanges) {  // Firefox
      window.getSelection().removeAllRanges();
    }
  } else if (document.selection) {  // IE?
    document.selection.empty();
  }
}

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


      this.dragStart = false

      let size = getWindowSize()
      this.xMax = size.x
      this.yMax = size.y

      let onMouseMoveHandler = function(e) {
        if(!this.dragStart) return

        let x = e.clientX,
            y = e.clientY

        /* Не снимаем показания координат при выходе за границы окна */
        if( x < 50 || y < 50 || x > this.xMax-60 || y > this.yMax-50 )
          return

        /* Если старых координат нет */
        if(typeof this.xOld == "undefined" && typeof this.yOld == "undefined") {
          this.xOld = x
          this.yOld = y
          return
        }

        /* Сохранить старые координаты, рассчитать дельты */
        let deltaX = x - this.xOld,
            deltaY = y - this.yOld

        /* Для следующей итерации */
        this.xOld = x
        this.yOld = y


        /* Изменения позиций */
        let rect = parent.getBoundingClientRect()

        if( rect.left < 50 && deltaX < 0) {
          parent.style.right = ""
          parent.style.left = "0px"
        } else if( rect.right > (this.xMax-60) && deltaX > 0) {
          parent.style.left = ""
          parent.style.right = "0px"
        } else {
          parent.style.right = ""
          if(!parent.style.left.length)
            parent.style.left = `${this.xMax - rect.width-20}px`
          parent.style.left = `${parseInt(parent.style.left)+deltaX}px`
        }

        if( rect.top < 50 && deltaY < 0) {
          parent.style.bottom = ""
          parent.style.top = "0px"
        } else if( rect.bottom > (this.yMax-50) && deltaY > 0) {
          parent.style.top = ""
          parent.style.bottom = "0px"
        } else {
          parent.style.bottom = ""
          if(!parent.style.top.length)
            parent.style.top = `${this.yMax - rect.height}px`
          parent.style.top = `${parseInt(parent.style.top)+deltaY}px`
        }
        clearSelections()
      }.bind(this)

      document.addEventListener('mousemove', onMouseMoveHandler)

      let onMouseDownHandler = function(e) {
        if( this.dragStart || e.which !==1 ) return
        this.dragStart = true
      }.bind(this)

      el.addEventListener('mousedown', onMouseDownHandler)

      let onMouseUpHandler = function(e) {
        if( !this.dragStart ) return

        this.dragStart = false
        let styles = window.getComputedStyle(parent)

        delete this.xOld
        delete this.yOld
      }.bind(this)

      document.addEventListener('mouseup', onMouseUpHandler)

      $scope.$on("$destroy", function() {
        el.removeEventListener('mousedown', onMouseMoveHandler)
        document.removeEventListener('mousemove', onMouseDownHandler)
        document.removeEventListener('mouseup', onMouseUpHandler)
        console.log("Removed handlers")
      }.bind(this))


    }
  }
}
