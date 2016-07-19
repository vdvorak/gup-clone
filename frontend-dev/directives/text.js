'use strict'

const COLORS = {
    blue: "#1875D0",
    white: "white"
}

module.exports = function() {
  return {
    restrict: "E",
    require: '^ngModel',
    scope : {
      label: "@",
      ngModel: "=",
      error: "=",
      color: "@",
      type: "@"
    },
    replace: true,
    template: `<div class="inputForm">
                 <label>{{ label }}</label>
                 <input type="{{ type || 'text'}}" ng-model="ngModel">
                 <div class="errors">{{ error }} </div>
               </div>`,
    controller: function($scope, $element, $timeout) {
      let defaultBorder = ""

      function onBlur(e) {
        if(!$scope.ngModel.length)
          hideAnimation()
      }

      function onFocus(e) {
        if(!$scope.ngModel.length)
          displayAnimation()
      }

      function displayAnimation() {
        label.style.color = COLORS[$scope.color]
        if(!defaultBorder.length) {
          defaultBorder = window.getComputedStyle(label.parentNode).borderBottom
        } else {
          label.parentNode.style.borderBottom = `2px solid ${COLORS[$scope.color]}`
        }


        label.classList.add('textOut')
      }

      function hideAnimation() {
        label.style.color = ""
        label.parentNode.style.borderBottom = defaultBorder
        label.classList.remove('textOut')
      }

      let el = $element[0].getElementsByTagName('input')[0]
      let label = $element[0].getElementsByTagName('label')[0]

      $timeout( () => {
        if( $scope.ngModel.length )
          displayAnimation()
        else
          hideAnimation()
      }, 250)


      el.addEventListener('blur', onBlur.bind(this))
      el.addEventListener('focus', onFocus.bind(this))
    }
  }
}
