/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	//require("./basic.css")

	/* Controllers */
	var orderController = __webpack_require__(1),
	    contactsController = __webpack_require__(3),
	    galleryController = __webpack_require__(4),
	    indexController = __webpack_require__(5),
	    servicesController = __webpack_require__(6),
	    aboutController = __webpack_require__(7);

	/* Dependencies */
	var utils = __webpack_require__(2);

	var app = angular.module('voytenko', ['ngRoute']);

	/* Plug in controllers */
	app.controller('orderController', orderController);
	app.controller('contactsController', contactsController);
	app.controller('galleryController', galleryController);
	app.controller('indexController', indexController);
	app.controller('servicesController', servicesController);
	app.controller('aboutController', aboutController);

	/* Handles file-change event directive */
	app.directive('fileModel', ['$parse', function ($parse) {
	  return {
	    restrict: 'A',
	    link: function link(scope, element, attrs) {
	      return element.bind('change', function () {
	        return scope.myFile = element[0].files[0];
	      });
	    }
	  };
	}]);

	// Router config
	app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {

	  $routeProvider.when('/', {
	    templateUrl: "templates/index.html",
	    controller: "indexController as index"
	  }).when('/about', {
	    templateUrl: "templates/about.html",
	    controller: "aboutController as about"
	  }).when('/order', {
	    templateUrl: "templates/order.html",
	    controller: "orderController as order"
	  }).when('/contacts', {
	    templateUrl: "templates/contacts.html",
	    controller: "contactsController as contacts"
	  }).when('/gallery-index', {
	    templateUrl: "templates/gallery-index.html",
	    controller: "galleryController as gallery"
	  }).when('/gallery-inner', {
	    templateUrl: "templates/gallery-inner.html",
	    controller: "galleryController as gallery"
	  }).when('/services-index', {
	    templateUrl: "templates/services-index.html",
	    controller: "servicesController as services"
	  }).when('/services-details', {
	    templateUrl: "templates/services-details.html",
	    controller: "servicesController as services"
	  }).otherwise({
	    redirectTo: '/'
	  });

	  $locationProvider.html5Mode({
	    enabled: true,
	    requireBase: false
	  });
		}]).run();

/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var utils = __webpack_require__(2);

	module.exports = function ($scope) {
	  var _this = this;

	  console.log("Hi from order controller");

	  utils.setClassToBody("order");

	  this.init = function () {
	    _this.name = "";
	    _this.surname = "";
	    _this.email = "";
	    _this.phone = "";
	    _this.message = "";
	    _this.file = "";

	    _this.successMessage = "";

	    _this.errorMessage = "";

	    _this.filename = null;
	  };

	  this.init();

	  this.sendForm = function () {

	    var file = _this.sendFile("someFilename.txt");

	    console.log("Sending form with file " + file);
	  };

	  this.sendFile = function () {
	    _this.file = $scope.myFile;

	    console.log("Sending file");
	    return file;
	  };
	};

/***/ },
/* 2 */
/***/ function(module, exports) {

	"use strict";

	/*
	  Expect options object like this:
	  {
	    "method" : "POST",
	    "url" : "http://someurl.com/",
	    "data" : "data",
	    "error" : console.error,
	    "success": console.log,
	    "headers" : {
	      "Content-Type" : "application/json",
	      "Content-Length" : "1023"
	    }
	  }

	  DEFAULTS:
	  Method - default is GET,
	  URL - required,
	  data - optional,
	  error - default is console.error
	  success  - default is console.log,
	  headers - optional
	*/
	module.exports.request = function (options) {
	  /* Setting defaults */
	  var _options$method = options.method;
	  var method = _options$method === undefined ? "GET" : _options$method;
	  var url = options.url;
	  var data = options.data;
	  var error = options.error;
	  var success = options.success;
	  var headers = options.headers;

	  /* Some validation */

	  if (!url) return console.error("Url not specified");

	  if ((method == "POST" || method == "PUT") && !data) return console.error("Nothing to send here =)");

	  /* Start constructing request */
	  var xhr = new XMLHttpRequest();
	  xhr.open(method, url, true);

	  if (headers) for (var prop in headers) {
	    xhr.setRequestHeader(prop, headers[prop]);
	  }xhr.send(data);

	  xhr.onreadystatechange = function () {
	    if (this.readyState != 4) return;

	    if (this.status != 200) {
	      if (error) return error('Error: ' + (this.status ? "(" + this.status + ") " + this.statusText : 'request fail'));
	    } else {
	      if (success) return success(this.responseText);
	    }
	  };
	};

	/* Method handlers background image change on change of controllers */
	module.exports.setClassToBody = function (classname) {
	  var validList = ["index", "about", "contacts", "services", "gallery", "order"];

	  if (~validList.indexOf(classname)) document.body.className = classname;else console.error("Passed classname is not valid: " + classname + "!");
		};

/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var utils = __webpack_require__(2);

	module.exports = function ($scope) {
	  console.log("Hi from contacts controller");

	  utils.setClassToBody("contacts");
	};

/***/ },
/* 4 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var utils = __webpack_require__(2);

	module.exports = function ($scope) {
	  console.log("Hi from gallery controller");

	  utils.setClassToBody("gallery");
	};

/***/ },
/* 5 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var utils = __webpack_require__(2);

	module.exports = function ($scope) {
	  console.log("Hi from index controller");

	  utils.setClassToBody("index");
	};

/***/ },
/* 6 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var utils = __webpack_require__(2);

	module.exports = function ($scope) {
	  console.log("Hi from services controller");

	  utils.setClassToBody("services");
	};

/***/ },
/* 7 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var utils = __webpack_require__(2);

	module.exports = function ($scope) {
	  console.log("Hi from about controller");

	  utils.setClassToBody("about");
		};

/***/ }
/******/ ]);
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXBwLmpzIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vL3dlYnBhY2svYm9vdHN0cmFwIGMyMzIyMzQyYzFjZjBjODUyMjBlIiwid2VicGFjazovLy9zcmMvZnJvbnQvYXBwLmpzIiwid2VicGFjazovLy9zcmMvZnJvbnQvY29udHJvbGxlcnMvb3JkZXIuanMiLCJ3ZWJwYWNrOi8vL3NyYy9mcm9udC91dGlscy5qcyIsIndlYnBhY2s6Ly8vc3JjL2Zyb250L2NvbnRyb2xsZXJzL2NvbnRhY3RzLmpzIiwid2VicGFjazovLy9zcmMvZnJvbnQvY29udHJvbGxlcnMvZ2FsbGVyeS5qcyIsIndlYnBhY2s6Ly8vc3JjL2Zyb250L2NvbnRyb2xsZXJzL2luZGV4LmpzIiwid2VicGFjazovLy9zcmMvZnJvbnQvY29udHJvbGxlcnMvc2VydmljZXMuanMiLCJ3ZWJwYWNrOi8vL3NyYy9mcm9udC9jb250cm9sbGVycy9hYm91dC5qcyJdLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSlcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcblxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0ZXhwb3J0czoge30sXG4gXHRcdFx0aWQ6IG1vZHVsZUlkLFxuIFx0XHRcdGxvYWRlZDogZmFsc2VcbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubG9hZGVkID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCJcIjtcblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXygwKTtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHdlYnBhY2svYm9vdHN0cmFwIGMyMzIyMzQyYzFjZjBjODUyMjBlXG4gKiovIiwiLy9yZXF1aXJlKFwiLi9iYXNpYy5jc3NcIilcblxuLyogQ29udHJvbGxlcnMgKi9cbmNvbnN0IG9yZGVyQ29udHJvbGxlciA9IHJlcXVpcmUoJy4vY29udHJvbGxlcnMvb3JkZXInKSxcbiAgICAgIGNvbnRhY3RzQ29udHJvbGxlciA9IHJlcXVpcmUoJy4vY29udHJvbGxlcnMvY29udGFjdHMnKSxcbiAgICAgIGdhbGxlcnlDb250cm9sbGVyID0gcmVxdWlyZSgnLi9jb250cm9sbGVycy9nYWxsZXJ5JyksXG4gICAgICBpbmRleENvbnRyb2xsZXIgPSByZXF1aXJlKCcuL2NvbnRyb2xsZXJzL2luZGV4JyksXG4gICAgICBzZXJ2aWNlc0NvbnRyb2xsZXIgPSByZXF1aXJlKCcuL2NvbnRyb2xsZXJzL3NlcnZpY2VzJyksXG4gICAgICBhYm91dENvbnRyb2xsZXIgPSByZXF1aXJlKCcuL2NvbnRyb2xsZXJzL2Fib3V0JylcblxuLyogRGVwZW5kZW5jaWVzICovXG5jb25zdCB1dGlscyA9IHJlcXVpcmUoJy4vdXRpbHMnKVxuXG5sZXQgYXBwID0gYW5ndWxhci5tb2R1bGUoJ3ZveXRlbmtvJywgWyduZ1JvdXRlJ10pXG5cbi8qIFBsdWcgaW4gY29udHJvbGxlcnMgKi9cbmFwcC5jb250cm9sbGVyKCdvcmRlckNvbnRyb2xsZXInLCBvcmRlckNvbnRyb2xsZXIpXG5hcHAuY29udHJvbGxlcignY29udGFjdHNDb250cm9sbGVyJywgY29udGFjdHNDb250cm9sbGVyKVxuYXBwLmNvbnRyb2xsZXIoJ2dhbGxlcnlDb250cm9sbGVyJywgZ2FsbGVyeUNvbnRyb2xsZXIpXG5hcHAuY29udHJvbGxlcignaW5kZXhDb250cm9sbGVyJywgaW5kZXhDb250cm9sbGVyKVxuYXBwLmNvbnRyb2xsZXIoJ3NlcnZpY2VzQ29udHJvbGxlcicsIHNlcnZpY2VzQ29udHJvbGxlcilcbmFwcC5jb250cm9sbGVyKCdhYm91dENvbnRyb2xsZXInLCBhYm91dENvbnRyb2xsZXIpXG5cbi8qIEhhbmRsZXMgZmlsZS1jaGFuZ2UgZXZlbnQgZGlyZWN0aXZlICovXG5hcHAuZGlyZWN0aXZlKCdmaWxlTW9kZWwnLCBbJyRwYXJzZScsIGZ1bmN0aW9uICgkcGFyc2UpIHtcbiAgICByZXR1cm4ge1xuICAgICAgICByZXN0cmljdDogJ0EnLFxuICAgICAgICBsaW5rOiAoc2NvcGUsIGVsZW1lbnQsIGF0dHJzKSA9PlxuICAgICAgICAgIGVsZW1lbnQuYmluZCgnY2hhbmdlJywgKCkgPT4gc2NvcGUubXlGaWxlID0gZWxlbWVudFswXS5maWxlc1swXSlcbiAgICB9XG59XSlcblxuLy8gUm91dGVyIGNvbmZpZ1xuYXBwXG4gIC5jb25maWcoWyckcm91dGVQcm92aWRlcicsICckbG9jYXRpb25Qcm92aWRlcicsIGZ1bmN0aW9uKCRyb3V0ZVByb3ZpZGVyLCAkbG9jYXRpb25Qcm92aWRlcil7XG5cbiAgICAkcm91dGVQcm92aWRlclxuICAgICAgLndoZW4oJy8nLCB7XG4gICAgICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2luZGV4Lmh0bWxcIixcbiAgICAgICAgY29udHJvbGxlcjogXCJpbmRleENvbnRyb2xsZXIgYXMgaW5kZXhcIlxuICAgICAgfSlcbiAgICAgIC53aGVuKCcvYWJvdXQnLCB7XG4gICAgICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2Fib3V0Lmh0bWxcIixcbiAgICAgICAgY29udHJvbGxlcjogXCJhYm91dENvbnRyb2xsZXIgYXMgYWJvdXRcIlxuICAgICAgfSlcbiAgICAgIC53aGVuKCcvb3JkZXInLCB7XG4gICAgICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL29yZGVyLmh0bWxcIixcbiAgICAgICAgY29udHJvbGxlcjpcIm9yZGVyQ29udHJvbGxlciBhcyBvcmRlclwiXG4gICAgICB9KVxuICAgICAgLndoZW4oJy9jb250YWN0cycsIHtcbiAgICAgICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL2NvbnRhY3RzLmh0bWxcIixcbiAgICAgICAgY29udHJvbGxlcjpcImNvbnRhY3RzQ29udHJvbGxlciBhcyBjb250YWN0c1wiXG4gICAgICB9KVxuICAgICAgLndoZW4oJy9nYWxsZXJ5LWluZGV4Jywge1xuICAgICAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvZ2FsbGVyeS1pbmRleC5odG1sXCIsXG4gICAgICAgIGNvbnRyb2xsZXI6XCJnYWxsZXJ5Q29udHJvbGxlciBhcyBnYWxsZXJ5XCJcbiAgICAgIH0pXG4gICAgICAud2hlbignL2dhbGxlcnktaW5uZXInLCB7XG4gICAgICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9nYWxsZXJ5LWlubmVyLmh0bWxcIixcbiAgICAgICAgY29udHJvbGxlcjpcImdhbGxlcnlDb250cm9sbGVyIGFzIGdhbGxlcnlcIlxuICAgICAgfSlcbiAgICAgIC53aGVuKCcvc2VydmljZXMtaW5kZXgnLCB7XG4gICAgICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9zZXJ2aWNlcy1pbmRleC5odG1sXCIsXG4gICAgICAgIGNvbnRyb2xsZXI6XCJzZXJ2aWNlc0NvbnRyb2xsZXIgYXMgc2VydmljZXNcIlxuICAgICAgfSlcbiAgICAgIC53aGVuKCcvc2VydmljZXMtZGV0YWlscycsIHtcbiAgICAgICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL3NlcnZpY2VzLWRldGFpbHMuaHRtbFwiLFxuICAgICAgICBjb250cm9sbGVyOlwic2VydmljZXNDb250cm9sbGVyIGFzIHNlcnZpY2VzXCJcbiAgICAgIH0pXG4gICAgICAub3RoZXJ3aXNlKHtcbiAgICAgICAgIHJlZGlyZWN0VG86ICcvJ1xuICAgICAgfSlcblxuICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh7XG4gICAgICBlbmFibGVkIDogdHJ1ZSxcbiAgICAgIHJlcXVpcmVCYXNlIDogZmFsc2VcbiAgICB9KVxuXG4gIH1dKVxuICAucnVuKClcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHNyYy9mcm9udC9hcHAuanNcbiAqKi8iLCJjb25zdCB1dGlscyA9IHJlcXVpcmUoJy4uL3V0aWxzJylcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkc2NvcGUpIHtcbiAgY29uc29sZS5sb2coXCJIaSBmcm9tIG9yZGVyIGNvbnRyb2xsZXJcIilcblxuICB1dGlscy5zZXRDbGFzc1RvQm9keShcIm9yZGVyXCIpXG5cbiAgdGhpcy5pbml0ID0gKCkgPT4ge1xuICAgIHRoaXMubmFtZSA9IFwiXCJcbiAgICB0aGlzLnN1cm5hbWUgPSBcIlwiXG4gICAgdGhpcy5lbWFpbCA9IFwiXCJcbiAgICB0aGlzLnBob25lID0gXCJcIlxuICAgIHRoaXMubWVzc2FnZSA9IFwiXCJcbiAgICB0aGlzLmZpbGUgPSBcIlwiXG5cbiAgICB0aGlzLnN1Y2Nlc3NNZXNzYWdlID0gXCJcIlxuXG4gICAgdGhpcy5lcnJvck1lc3NhZ2UgPSBcIlwiXG5cbiAgICB0aGlzLmZpbGVuYW1lID0gbnVsbFxuICB9XG5cbiAgdGhpcy5pbml0KClcblxuICB0aGlzLnNlbmRGb3JtID0gKCkgPT4ge1xuXG4gICAgbGV0IGZpbGUgPSB0aGlzLnNlbmRGaWxlKFwic29tZUZpbGVuYW1lLnR4dFwiKVxuXG4gICAgY29uc29sZS5sb2coXCJTZW5kaW5nIGZvcm0gd2l0aCBmaWxlIFwiICsgZmlsZSlcbiAgfVxuXG4gIHRoaXMuc2VuZEZpbGUgPSAoKSA9PiB7XG4gICAgdGhpcy5maWxlID0gJHNjb3BlLm15RmlsZVxuXG4gICAgY29uc29sZS5sb2coXCJTZW5kaW5nIGZpbGVcIilcbiAgICByZXR1cm4gZmlsZVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBzcmMvZnJvbnQvY29udHJvbGxlcnMvb3JkZXIuanNcbiAqKi8iLCIvKlxuICBFeHBlY3Qgb3B0aW9ucyBvYmplY3QgbGlrZSB0aGlzOlxuICB7XG4gICAgXCJtZXRob2RcIiA6IFwiUE9TVFwiLFxuICAgIFwidXJsXCIgOiBcImh0dHA6Ly9zb21ldXJsLmNvbS9cIixcbiAgICBcImRhdGFcIiA6IFwiZGF0YVwiLFxuICAgIFwiZXJyb3JcIiA6IGNvbnNvbGUuZXJyb3IsXG4gICAgXCJzdWNjZXNzXCI6IGNvbnNvbGUubG9nLFxuICAgIFwiaGVhZGVyc1wiIDoge1xuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwiYXBwbGljYXRpb24vanNvblwiLFxuICAgICAgXCJDb250ZW50LUxlbmd0aFwiIDogXCIxMDIzXCJcbiAgICB9XG4gIH1cblxuICBERUZBVUxUUzpcbiAgTWV0aG9kIC0gZGVmYXVsdCBpcyBHRVQsXG4gIFVSTCAtIHJlcXVpcmVkLFxuICBkYXRhIC0gb3B0aW9uYWwsXG4gIGVycm9yIC0gZGVmYXVsdCBpcyBjb25zb2xlLmVycm9yXG4gIHN1Y2Nlc3MgIC0gZGVmYXVsdCBpcyBjb25zb2xlLmxvZyxcbiAgaGVhZGVycyAtIG9wdGlvbmFsXG4qL1xubW9kdWxlLmV4cG9ydHMucmVxdWVzdCA9IGZ1bmN0aW9uKG9wdGlvbnMpIHtcbiAgLyogU2V0dGluZyBkZWZhdWx0cyAqL1xuICBsZXQgeyBtZXRob2Q9XCJHRVRcIiwgdXJsLCBkYXRhLCBlcnJvciwgc3VjY2VzcywgaGVhZGVycyB9ID0gb3B0aW9uc1xuXG4gIC8qIFNvbWUgdmFsaWRhdGlvbiAqL1xuICBpZighdXJsKVxuICAgIHJldHVybiBjb25zb2xlLmVycm9yKFwiVXJsIG5vdCBzcGVjaWZpZWRcIilcblxuICBpZigobWV0aG9kID09IFwiUE9TVFwiIHx8IG1ldGhvZCA9PSBcIlBVVFwiKSAmJiAhZGF0YSlcbiAgICByZXR1cm4gY29uc29sZS5lcnJvcihcIk5vdGhpbmcgdG8gc2VuZCBoZXJlID0pXCIpXG5cbiAgLyogU3RhcnQgY29uc3RydWN0aW5nIHJlcXVlc3QgKi9cbiAgdmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpXG4gIHhoci5vcGVuKG1ldGhvZCwgdXJsLCB0cnVlKVxuXG4gIGlmKGhlYWRlcnMpXG4gICAgZm9yKCB2YXIgcHJvcCBpbiBoZWFkZXJzKVxuICAgICAgeGhyLnNldFJlcXVlc3RIZWFkZXIocHJvcCwgaGVhZGVyc1twcm9wXSlcblxuICB4aHIuc2VuZChkYXRhKVxuXG4gIHhoci5vbnJlYWR5c3RhdGVjaGFuZ2UgPSBmdW5jdGlvbigpIHtcbiAgICBpZiAodGhpcy5yZWFkeVN0YXRlICE9IDQpXG4gICAgICByZXR1cm5cblxuICAgIGlmICh0aGlzLnN0YXR1cyAhPSAyMDApIHtcbiAgICAgIGlmKCBlcnJvciApIHJldHVybiBlcnJvciggJ0Vycm9yOiAnICsgKHRoaXMuc3RhdHVzID8gYCgke3RoaXMuc3RhdHVzfSkgJHt0aGlzLnN0YXR1c1RleHR9YDogJ3JlcXVlc3QgZmFpbCcpKVxuICAgIH0gZWxzZSB7XG4gICAgICBpZiggc3VjY2VzcyApIHJldHVybiBzdWNjZXNzKHRoaXMucmVzcG9uc2VUZXh0KVxuICAgIH1cbiAgfVxufVxuXG4vKiBNZXRob2QgaGFuZGxlcnMgYmFja2dyb3VuZCBpbWFnZSBjaGFuZ2Ugb24gY2hhbmdlIG9mIGNvbnRyb2xsZXJzICovXG5tb2R1bGUuZXhwb3J0cy5zZXRDbGFzc1RvQm9keSA9IGNsYXNzbmFtZSA9PiB7XG4gIGxldCB2YWxpZExpc3QgPSBbXG4gICAgXCJpbmRleFwiLFxuICAgIFwiYWJvdXRcIixcbiAgICBcImNvbnRhY3RzXCIsXG4gICAgXCJzZXJ2aWNlc1wiLFxuICAgIFwiZ2FsbGVyeVwiLFxuICAgIFwib3JkZXJcIlxuICBdXG5cbiAgaWYoIH52YWxpZExpc3QuaW5kZXhPZihjbGFzc25hbWUpIClcbiAgICBkb2N1bWVudC5ib2R5LmNsYXNzTmFtZSA9IGNsYXNzbmFtZVxuICBlbHNlIGNvbnNvbGUuZXJyb3IoYFBhc3NlZCBjbGFzc25hbWUgaXMgbm90IHZhbGlkOiAke2NsYXNzbmFtZX0hYClcbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHNyYy9mcm9udC91dGlscy5qc1xuICoqLyIsImNvbnN0IHV0aWxzID0gcmVxdWlyZSgnLi4vdXRpbHMnKVxuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xuICBjb25zb2xlLmxvZyhcIkhpIGZyb20gY29udGFjdHMgY29udHJvbGxlclwiKVxuXG4gICAgdXRpbHMuc2V0Q2xhc3NUb0JvZHkoXCJjb250YWN0c1wiKVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogc3JjL2Zyb250L2NvbnRyb2xsZXJzL2NvbnRhY3RzLmpzXG4gKiovIiwiY29uc3QgdXRpbHMgPSByZXF1aXJlKCcuLi91dGlscycpXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XG4gIGNvbnNvbGUubG9nKFwiSGkgZnJvbSBnYWxsZXJ5IGNvbnRyb2xsZXJcIilcblxuICAgIHV0aWxzLnNldENsYXNzVG9Cb2R5KFwiZ2FsbGVyeVwiKVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogc3JjL2Zyb250L2NvbnRyb2xsZXJzL2dhbGxlcnkuanNcbiAqKi8iLCJjb25zdCB1dGlscyA9IHJlcXVpcmUoJy4uL3V0aWxzJylcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkc2NvcGUpIHtcbiAgY29uc29sZS5sb2coXCJIaSBmcm9tIGluZGV4IGNvbnRyb2xsZXJcIilcblxuICAgIHV0aWxzLnNldENsYXNzVG9Cb2R5KFwiaW5kZXhcIilcbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHNyYy9mcm9udC9jb250cm9sbGVycy9pbmRleC5qc1xuICoqLyIsImNvbnN0IHV0aWxzID0gcmVxdWlyZSgnLi4vdXRpbHMnKVxuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xuICBjb25zb2xlLmxvZyhcIkhpIGZyb20gc2VydmljZXMgY29udHJvbGxlclwiKVxuXG4gICAgdXRpbHMuc2V0Q2xhc3NUb0JvZHkoXCJzZXJ2aWNlc1wiKVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogc3JjL2Zyb250L2NvbnRyb2xsZXJzL3NlcnZpY2VzLmpzXG4gKiovIiwiY29uc3QgdXRpbHMgPSByZXF1aXJlKCcuLi91dGlscycpXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XG4gIGNvbnNvbGUubG9nKFwiSGkgZnJvbSBhYm91dCBjb250cm9sbGVyXCIpXG5cbiAgdXRpbHMuc2V0Q2xhc3NUb0JvZHkoXCJhYm91dFwiKVxuXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBzcmMvZnJvbnQvY29udHJvbGxlcnMvYWJvdXQuanNcbiAqKi8iXSwibWFwcGluZ3MiOiI7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7Ozs7OztBQ25DQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFDQTs7QUFPQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFBQTtBQURBO0FBRkE7QUFLQTtBQUNBOztBQUVBO0FBQ0E7QUFFQTtBQUVBO0FBQ0E7QUFGQTtBQUtBO0FBQ0E7QUFGQTtBQUtBO0FBQ0E7QUFGQTtBQUtBO0FBQ0E7QUFGQTtBQUtBO0FBQ0E7QUFGQTtBQUtBO0FBQ0E7QUFGQTtBQUtBO0FBQ0E7QUFGQTtBQUtBO0FBQ0E7QUFGQTtBQUtBO0FBREE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUZBO0FBS0E7Ozs7Ozs7O0FDOUVBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQ2RBOztBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQ0E7OztBQUlBO0FBQ0E7QUFFQTtBQUNBOztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFEQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFRQTtBQUdBOzs7Ozs7OztBQ3JFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7OztBQ0xBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7Ozs7O0FDTEE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7QUNMQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7OztBQ0xBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBOzs7Iiwic291cmNlUm9vdCI6IiJ9