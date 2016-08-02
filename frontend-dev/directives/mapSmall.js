"use strict"

/* DEPRECATED */
module.exports = function() {
  return {
    restrict : "E",
    templateUrl: "partials/map-small.html",
    controller: function($scope, $element) {
      let data = [
        {
          path:[],
          title : ""
        }
      ]
    },
    replace:true,
    controllerAs: "map"
  }
}
