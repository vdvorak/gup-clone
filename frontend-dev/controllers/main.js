"use strict"

/* Контроллер для управления  основным скелетом документа */
module.exports = function($scope, $location) {
  console.log('Main controller loaded')

  /* Standalone module for bd */
  $scope.bd = require('../modules/bd')
  $scope.bd.init()

  /* variables for testing */
  this.hello="hi"
  this.boolean = true

  this.init = function() {
    console.log("Main controller init")

    this.sortingCategories = (require('../data/sorting')).items
    this.currentCategory = "None"
    this.sortingId = 0

    if(this.sortingCategories.length) {
      let title = this.sortingCategories[this.sortingId].title
      let arr = title.split("")
      this.arrow = arr.pop()
      arr.pop()

      this.currentCategory = arr.join("")
    }
    else console.error(new Error("No sorting options found"))

    this.showCategories = false
  }

  this.setCategory = id => {
    this.showCategories = false
    let res = this.sortingCategories.filter(el => el.id === id | 0)[0]
    this.sortingId = id

    if(res) {
      let arr = res.title.split("")
      this.arrow = arr.pop()
      arr.pop()

      this.currentCategory = arr.join("")
    }

  }


  /* Correct redirect to url through app router*/
  $scope.redirectToUrl = url => $location.path(url)

}
