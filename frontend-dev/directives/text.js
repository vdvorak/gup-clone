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
    replace: true,
    template: `<div class="{{class}}">
                 <label>{{ label }}</label>
                 <input type="text" ng-model="ngModel">
               </div>`,
    controller: function($scope, $element, $timeout) {
      function onBlur(e) {
        if(!$scope.ngModel.length) {
          label.classList.add('textIn')
          label.classList.remove('textOut')
        }        
      }

      function onFocus(e) {
        if(!$scope.ngModel.length && !label.classList.contains('textOut'))
          label.classList.add('textOut')
        if(label.classList.contains('textIn'))
          label.classList.remove('textIn')
      }

      let el = $element[0].getElementsByTagName('input')[0]
      let label = $element[0].getElementsByTagName('label')[0]

      $timeout( () => {
        if( $scope.ngModel.length ) {
          label.classList.add('textOut')
        } else {
          label.classList.add('textIn')
        }
      }, 250)


      el.addEventListener('blur', onBlur.bind(this))
      el.addEventListener('focus', onFocus.bind(this))
    }
  }
}
