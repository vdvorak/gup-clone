'use strict'

module.exports = function() {
  return {
    restrict : "E",
    transclude: true,
    scope: true,
    template: `<div class="btn-blue">
                  <span class="ink"></span>
                  <p ng-click="main.rippleHandler($event)" class="ripple">
                    <ng-transclude></ng-transclude>
                  </p>
                </div>`

  }
}
