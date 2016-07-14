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
                  <ng-transclude style="display:block; width:100%; height:inherit;"></ng-transclude>
                </div>`,
    controller: function($scope, $element) {
      let onClick = function(e) {
        let ink = this.getElementsByClassName('ink')[0]
        ink.classList.remove('animate')

        let rect = this.getBoundingClientRect()

        if( !ink.clientHeight && !ink.clientWidth ) {
          let d = Math.max(this.clientWidth, this.clientHeight)
          ink.style.height = ink.style.width = `${d}px`
        }

        ink.style.top = `${e.clientY - rect.top - ink.clientHeight/2}px`
        ink.style.left = `${e.clientX - rect.left -ink.clientWidth/2}px`
        ink.classList.add('animate')
      }

      if($scope.ngClick)
        $element[0].addEventListener('click', $scope.ngClick)
        
      $element[0].addEventListener('click', onClick)
    }
  }
}
