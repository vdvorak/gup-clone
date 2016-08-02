"use strict"

module.exports = function() {
  return {
    restrict : "E",
    scope : {
      "items" : "="
    },
    templateUrl: "partials/bulletin-list.html",
    controller : function($scope, $element) {
      console.log($scope.items)
      console.log("I'm a cool directive controller ;D")
    },
    controllerAs: "blist"
  }
}
