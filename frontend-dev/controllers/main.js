"use strict"

/* Контроллер для управления  основным скелетом документа */
module.exports = function($scope, $location, $timeout) {
  console.log('Main controller loaded')

  /* Standalone module for bd */
  $scope.bd = require('../modules/bd')
  $scope.bd.init()

  /* Initialize data */
  this.init = function() {
    /* variables for testing */
    this.hello="hi"
    this.boolean = true

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

    this.showingCategories = false
    this.settingCat = true
  }

  this.showCategories = () => {
    this.settingCat = true
    this.settingCat = true
    $timeout( () => {
      this.settingCat = false
    }, 250)

    this.showingCategories = true
  }

  /* Sorting in header */
  this.setCategory = id => {
    this.settingCat = false

    let res = this.sortingCategories.filter(el => el.id === id | 0)[0]
    this.sortingId = id

    if(res) {
      let arr = res.title.split("")
      this.arrow = arr.pop()
      arr.pop()

      this.currentCategory = arr.join("")
    }

  }

  /* Watch all click on the body */
  this.click = () => {
    if(this.showingCategories && !this.settingCat)
      this.showingCategories = false
  }


  /* Correct redirect to url through app router*/
  $scope.redirectToUrl = url => {
    $timeout(() => {
      $location.path(url)
    }, 250)
  }

}
