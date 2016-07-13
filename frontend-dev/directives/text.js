'use strict'

module.exports = function() {
  return {
    restrict: "E",
    require: '^ngModel',
    scope : {
      label: "@",
      class: "@",
      ngModel: "="
    },
    template: `<div class="{{class}}">
                 <label>{{ label }}</label>
                 <input type="text" value="{{ ngModel }}">
               </div>`,
    controller: function($scope, $element) {
      function onBlur(e) {
        console.log("Text on blur. Event:")
        console.log(e)
      }

      function onFocus(e) {
        console.log("Text on focus. Event:")
        console.log(e)
      }

      let el = $element[0].getElementsByTagName('input')[0]
      el.addEventListener('blur', onBlur)
      el.addEventListener('focus', onFocus)
    }
  }
}
