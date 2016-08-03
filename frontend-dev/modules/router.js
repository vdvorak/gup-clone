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
    templateUrl:"templates/authenticated/bulletinAdd.html",
    controller: require('../controllers/authenticated/bulletinAdd'),
    controllerAs: "badd"
  },
  '/login' : {
    templateUrl: "templates/login.html",
    controller: require('../controllers/login'),
    controllerAs: "login"
  },
  '/register' : {
    templateUrl: "templates/register.html",
    controller: require('../controllers/register'),
    controllerAs: "register"
  },
  '/editProfile' : {
    templateUrl: "templates/authenticated/edit-profile.html",
    controller: require('../controllers/authenticated/editProfile'),
    controllerAs: "profile"
  },
  '/profile' : {
    templateUrl: "templates/authenticated/profile.html",
    controller: require('../controllers/authenticated/profile'),
    controllerAs: "profile"
  },
  '/favourites' : {
    templateUrl:"templates/authenticated/favourites.html",
    controller: require('../controllers/authenticated/favourites'),
    controllerAs: "favourite"
  },
  '/searchResults' : {
    templateUrl:"templates/searchResults.html",
    controller: require('../controllers/searchResults'),
    controllerAs: "searchResults"
  },
  '/mail' : {
    templateUrl:"templates/authenticated/mail.html",
  }
}
