"use strict"

const utils = require('../modules/utils')

const ctx = module.exports = {}

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
    this.showServices = false
    this.showMiniContacts = false
    this.showMessagesSelect = false

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

	  this.searchCategories = require('../data/searchCategories');
  }

  this.toggleMessagesSelect = () => {
      this.showMessagesSelect = !this.showMessagesSelect
  }

  this.curr = function(id) {
		this.idName = id
		console.log(this.idName, id)
	}

  this.resetFilters = function() {
    this.idName = -1
  }

  this.displayFilters = function() {
    this.showFilters = true
    const nav = document.getElementsByTagName('nav')[0]

    let handler = function(e) {
      let x = e.clientX,
          y = e.clientY

      if( !utils.coordinatesBelongToElements(x, y, 'nav') &&
          !utils.coordinatesBelongToElements(x, y, '.firstSubcategories') &&
          !utils.coordinatesBelongToElements(x, y, '.secondSubcategories') ) {
            document.removeEventListener('click', handler)
            this.showFilters = false
            this.resetFilters()
            $scope.$apply()
      }
    }.bind(this)

    document.addEventListener('click', handler)
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
