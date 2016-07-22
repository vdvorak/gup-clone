"use strict"

/* Контроллер для управления  основным скелетом документа */
module.exports = function($http, $scope, $location, $timeout, $cookies, $cookieStore) {
  console.log('Main controller loaded')
  console.log($cookies)
  /* Standalone module for bd */
  $scope.db = require('../modules/db')
  $scope.db.init($http)
  window.db = $scope.db

  /* Initialize data */
  this.init = function() {
    /* variables for testing */
    this.hello="hi"
    this.boolean = true
    this.list = [1,2,3]
    /* End variables for testing */

    this.loader = require('../modules/loader')
    this.loader($scope, $timeout)

    console.log("Main controller init")

    this.sortingCategories = (require('../data/sorting')).items
    this.currentCategory = "None"
    this.sortingId = 0

    this.showFilters = false

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

  this.toggleFilters = function() {
    this.showFilters = this.showFilters ? false : true
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

  this.logout = function() {
      db.userLogout()
      $scope.redirectToUrl('/')
  }

  /* Watch all click on the body */
  this.click = () => {
    if(this.showingCategories && !this.settingCat)
      this.showingCategories = false
  }


  /* Correct redirect to url through app router*/
  $scope.redirectToUrl = (url, immediate) => {
    if(immediate)
      $location.path(url)
    else
      $timeout(() => {
        $location.path(url)
      }, 250)
  }

  /* Use this method for global purpose errors */
  $scope.displayError = text => {
    alert(text)
    console.error(new Error("text"))
  }

}
