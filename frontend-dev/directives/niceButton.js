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
    template : `<div class="{{ class }}" ng-click="ngClick({e:$event})">
                  <span class="ink"></span>
                  <ng-transclude style="display:block; width:100%; height:inherit;"></ng-transclude>
                </div>`,
    controller: function($scope, $element) {
      function onClick(e) {
        if(!e) return

        let parent = e.target.parentNode.parentNode
        if(!parent.classList.contains($scope.class.trim()))
          parent = e.target.parentNode

        let ink = parent.getElementsByClassName('ink')[0]
        ink.classList.remove('animate')

        let rect = parent.getBoundingClientRect()

        if( !ink.clientHeight && !ink.clientWidth ) {
          let d = Math.max(parent.clientWidth, parent.clientHeight)
          ink.style.height = ink.style.width = `${d}px`
        }

        ink.style.top = `${e.clientY - rect.top - ink.clientHeight/2}px`
        ink.style.left = `${e.clientX - rect.left -ink.clientWidth/2}px`
        ink.classList.add('animate')
      }

      $element[0].addEventListener('click', onClick)
    }
  }
}
