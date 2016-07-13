'use strict'

module.exports = {
  "/" : {
    templateUrl:"templates/index.html",
    controller: require('../controllers/index'),
    controllerAs: "index"
  },
  '/error/403' : {
    templateUrl:"templates/error403.html"
  },
  '/error/404' : {
    templateUrl:"templates/error404.html"
  },
  '/error/500' : {
    templateUrl:"templates/error500.html"
  },
  '/bulletinDetails' : {
    templateUrl:"templates/bulletinDetails.html",
    controller: require('../controllers/bulletinDetails'),
    controllerAs: "bdetailed"
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
  }
}
