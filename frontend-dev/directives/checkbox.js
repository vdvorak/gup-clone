'use strict'

module.exports = function() {
  return {
    restrict: "E",
    template: `<div class="checkbox"></div>`,
    controller: function($scope, $element) {
      let el = $element[0]
      console.log(el)
    }
  }
}
