'use strict'

module.exports = {
  "/" : {
    templateUrl:"templates/index.html",
    controller: require('../controllers/index'),
    controllerAs: "index"
  },
  '/403' : {
    templateUrl:"templates/error403.html"
  },
  '/404' : {
    templateUrl:"templates/error404.html"
  },
  '/500' : {
    templateUrl:"templates/error500.html"
  },
  '/bulletinDetails' : {
    templateUrl:"templates/bulletinDetails.html",
    controller: require('../controllers/bulletinDetails'),
    controllerAs: "bdetailed"
  },
  '/bulletinAdd' : {
    templateUrl:"templates/bulletinAdd.html",
    controller: require('../controllers/bulletinAdd'),
    controllerAs: "badd"
  },
  '/editProfile' : {
    templateUrl: "templates/edit-profile.html",
    controller: require('../controllers/editProfile'),
    controllerAs: "profile"
  },
  '/profile' : {
    templateUrl: "templates/profile.html",
    controller: require('../controllers/profile'),
    controllerAs: "profile"
  },
  '/favourites' : {
    templateUrl:"templates/favourites.html",
    controller: require('../controllers/favourites'),
    controllerAs: "favourite"
  },
  '/searchResults' : {
    templateUrl:"templates/searchResults.html",
    controller: require('../controllers/searchResults'),
    controllerAs: "searchResults"
  }
}
