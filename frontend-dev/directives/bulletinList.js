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
					myImage: {
						"background" : "url(../images/vk.png)"
    			},
					"price": "2 345 грн",
					"onOrOffLineUser": "green",
					"exclamationPoint": true,
					"dollarBill": true,
					"action": "Искать похожие",
					"stock": "stock"
				},
				{
					"title": "Hotwheels недорого по запчастям",
					"category": "Детские игрушки",
					"description": "Lorem ipsum dolor sit amet, ne quod novum mei. Sea omnium invenire mediocrem at, in lobortis conclusionemque nam.",
					myImage: {
						"background" : "url(../images/facebook.png)"
    			},
					"price": "2 345 грн",
					"onOrOffLineUser": "red",
					"exclamationPoint": false,
					"dollarBill": false,
					"action": "Искать похожие",
					"stock": ""
				},
				{
					"title": "Hotwheels недорого по запчастям",
					"category": "Детские игрушки",
					"description": "Lorem ipsum dolor sit amet, ne quod novum mei. Sea omnium invenire mediocrem at, in lobortis conclusionemque nam.",
					myImage: {
						"background" : "url(../images/google.png)"
    			},
					"price": "2 345 грн",
					"onOrOffLineUser": "red",
					"exclamationPoint": false,
					"dollarBill": false,
					"action": "Искать похожие",
					"stock": ""
				},
				{
					"title": "Hotwheels недорого по запчастям",
					"category": "Детские игрушки",
					"description": "Lorem ipsum dolor sit amet, ne quod novum mei. Sea omnium invenire mediocrem at, in lobortis conclusionemque nam.",
					myImage: {
						"background" : "url(../images/addCalendar.png)"
    			},
					"price": "2 345 грн",
					"onOrOffLineUser": "red",
					"exclamationPoint": false,
					"dollarBill": false,
					"action": "Искать похожие",
					"stock": ""
				}
			];
    },
    controllerAs: "blist"
  }
}
