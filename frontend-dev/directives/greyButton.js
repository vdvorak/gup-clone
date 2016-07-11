'use strict'

module.exports = function() {
  return {
    restrict : "E",
    transclude: true,
    scope: true,
    template: `<div class="btn-grey">
                  <span class="ink"></span>
                  <p ng-click="t.rippleHandler($event)" class="ripple">
                    <ng-transclude></ng-transclude>
                  </p>
                </div>`

  }
}
