"use strict"

module.exports = function() {
  return {
    restrict : "E",
    scope : {
      "items" : "="
    },
    templateUrl: "partials/bulletin-list.html",
    controller : function($scope, $element) {
      console.log($scope.items);
      console.log("I'm a cool directive controller ;D");
			this.list = [
				{
					"title": "Hotwheels недорого по запчастям",
					"category": "Детские игрушки",
					"description": "Lorem ipsum dolor sit amet, ne quod novum mei. Sea omnium invenire mediocrem at, in lobortis conclusionemque nam.",
				}
			];
    },
    controllerAs: "blist"
  }
}
