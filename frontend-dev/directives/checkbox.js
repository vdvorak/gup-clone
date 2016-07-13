'use strict'

module.exports = function() {
  return {
    restrict: "E",
    require: '^ngModel',
    scope: {
      ngModel: "="
    },
    template: `<div class="checkBox"></div>`,
    replace: true,
    controller: function($scope, $element) {
      let el = $element[0]
      //.getElementsByClassName('checkBox')[0]

      if($scope.ngModel && !el.classList.contains('checked'))
        el.classList.add('checked')
      else if(!$scope.ngModel && el.classList.contains('checked'))
        el.classList.remove('cheked')

      el.addEventListener('click', e => {
        el.classList.toggle('checked')
        $scope.ngModel = $scope.ngModel ?  false : true
      })
    }
  }
}
