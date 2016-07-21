'use strict'

const MAX_SYMBOLS = 1000

module.exports = function() {
  return {
    restrict: "E",
    require: '^ngModel',
    scope : {
      label: "@",
      ngModel: "="
    },
    replace: true,
    template: `<div class="inputForm">
                 <label>{{ label }}</label>
                 <textarea ng-model="ngModel" maxlength=${MAX_SYMBOLS}></textarea>
                 <div class="symbols"></div>
               </div>`,
    controller: function($scope, $element, $timeout) {
      document.addEventListener('keyup', count)

      $scope.$on("$destroy", function() {
        document.removeEventListener('keyup', count)
      }.bind(this))

      let defaultBorder = ""

      let el = $element[0].getElementsByTagName('textarea')[0],
          label = $element[0].getElementsByTagName('label')[0],
          symbols = $element[0].getElementsByClassName('symbols')[0]


      function count() {
        symbols.innerHTML = `${$scope.ngModel.length}/${MAX_SYMBOLS}`
      }

      function onBlur(e) {
        if( !$scope.ngModel.length)
          hideAnimation()
      }

      function onFocus(e) {
        if(!$scope.ngModel.length)
          displayAnimation()
      }

      function displayAnimation() {
        label.style.color = "#1875D0"
        if(!defaultBorder.length) {
          defaultBorder = window.getComputedStyle(label.parentNode).borderBottom
        } else {
          label.parentNode.style.borderBottom = `2px solid #1875D0`
        }

        label.classList.add('textOut')
      }

      function hideAnimation() {
        label.style.color = ""
        label.parentNode.style.borderBottom = defaultBorder
        label.classList.remove('textOut')
      }

      $timeout( () => {
        if( $scope.ngModel && $scope.ngModel.length )
          displayAnimation()
        else
          hideAnimation()
      }, 250)


      el.addEventListener('blur', onBlur.bind(this))
      el.addEventListener('focus', onFocus.bind($scope))

      count()
    }
  }
}
