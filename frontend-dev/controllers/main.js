"use strict"

/* Контроллер для управления  основным скелетом документа */
module.exports = function($scope) {
  console.log('Main controller loaded')
  /* variable for testing */
  this.hello="hi"
  this.boolean = true

  this.init = function() {
    console.log("Main controller init")
  }
  
  $scope.redirectToHome = function(e) {
      if(!e) return
      window.location = "/"
  }
}
