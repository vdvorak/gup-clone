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
                 <input type="text" class="textInputs" value="{{ ngModel }}">
               </div>`
  }
}
