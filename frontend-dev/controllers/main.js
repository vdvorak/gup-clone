"use strict"

const materials = require('../modules/materials')

const KOSTYL_SIZE = 100

/* Контроллер для управления  основным скелетом документа */
module.exports = function($scope) {
  console.log('loaded temp')
  this.hello="hi"

  this.init = function() {
    console.log("Main controller init")

    $scope.$on('$routeChangeSuccess', function() {
      console.log("Route changed")
      setTimeout(function() {
        materials.addRipple('.ripple')
        materials.addTextEvents('.textInputs')
      }.bind(this), KOSTYL_SIZE)
    }.bind(this))
  }
}
