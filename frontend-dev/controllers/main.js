"use strict"

/* Контроллер для управления  основным скелетом документа */
module.exports = function($scope, $location) {
  console.log('Main controller loaded')
  /* variables for testing */
  this.hello="hi"
  this.boolean = true



  this.init = function() {
    console.log("Main controller init")



    this.sortingCategories = (require('../data/sorting')).items
    if(this.sortingCategories.length)
      this.currentCategory = this.sortingCategories[0].title
    else this.currentCategory = "None"
    this.showCategories = false
  }

  this.setCategory = id => {
    id = id | 0
    this.showCategories = false
    let res = this.sortingCategories.filter(el => el.id === id)[0]

    if(res) this.currentCategory = res.title
  }
  /* Correct redirect to url through app router*/
  $scope.redirectToUrl = url => $location.path(url)

}
