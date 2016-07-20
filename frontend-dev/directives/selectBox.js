"use strict"

module.exports = function() {
  return {
    restrict: "E",
    require: '^ngModel',
    scope: {
      ngModel: "=",
      show: "@"
    },
    template: `<div class="selectBox">
                <div class="defaultValue" ng-hide="show">
                  <p>{{ ngModel }}</p>
                </div>
                <div class="listOfValues" ng-show="show">
                  <div class="listItem" ng-repeat="item in items" value="{{item}}">{{ item }}</div>
                </div>
              </div>`,
    replace: true,
    controller: function($scope, $element, $timeout) {
      let defaultVal = $element[0].getElementsByClassName('defaultValue')[0],
          listOfValues = $element[0].getElementsByClassName('listOfValues')[0]

      $timeout(function() {
        defaultVal.addEventListener('click', function(e) {
          this.show = true
        }.bind($scope))

        // displayedElelement = null
        //
        // function handler() {
        //   if(displa)
        // }

        for(let t=0;t<listOfValues.children.length; t++) {

          listOfValues.children[t].addEventListener('click', function(e) {
            document.addEventListener('click')
            this.show = false
            this.ngModel = e.target.innerHTML
          }.bind($scope))
        }
      }.bind(this), 1000)
    },
    link: function(scope, element, attrs) {
      scope.items = JSON.parse(attrs.items)
    }
  }
}
