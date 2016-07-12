'use strict'

module.exports = function() {
  return {
    restrict: "E",
    transclude: true,
    scope : {
      class: "@"
    },
    template : `<div class="{{ class }}">
                  <span class="ink"></span>
                  <p class="ripple">
                    <ng-transclude></ng-transclude>
                  </p>
                </div>`
  }
}
