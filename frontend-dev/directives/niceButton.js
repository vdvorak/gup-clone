'use strict'

module.exports = function() {
  return {
    restrict: "E",
    transclude: true,
    scope : {
      class: "@",
      ngClick: "&"
    },
    replace: true,
    template : `<div class="{{ class }}">
                  <span class="ink"></span>
                  <p class="ripple" ng-click="ngClick({e:$event})">
                    <ng-transclude></ng-transclude>
                  </p>
                </div>`,
    controller: function($scope, $element) {
      function onClick(e) {
        if(!e) return

        let parent = e.target.parentNode.parentNode.parentNode
        let ink = parent.getElementsByClassName('ink')[0]
        ink.classList.remove('animate')

        if( !ink.clientHeight && !ink.clientWidth ) {
          let d = Math.max(parent.clientWidth, parent.clientHeight)
          ink.style.height = ink.style.width = `${d}px`
        }

        ink.style.top = `${e.pageY - parent.offsetTop - parent.offsetParent.offsetTop - ink.clientHeight/2}px`
        ink.style.left = `${e.pageX - parent.offsetLeft - parent.offsetParent.offsetLeft -ink.clientWidth/2}px`
        ink.classList.add('animate')
      }

      let el = $element[0].getElementsByClassName('ripple')[0]
      el.addEventListener('click', onClick)
    }
  }
}
