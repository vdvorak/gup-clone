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

	__webpack_require__(1);
	__webpack_require__(37);
	__webpack_require__(43);
	__webpack_require__(47);

	__webpack_require__(51)();

	var materials = __webpack_require__(74),
	    router = __webpack_require__(59);

	var app = angular.module('gup', ['ngRoute', 'ngCookies']);

	// App config
	app.config(['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {
	  $httpProvider.defaults.useXDomain = true;
	  delete $httpProvider.defaults.headers.common['X-Requested-With'];

	  for (var key in router) {
	    $routeProvider.when(key, router[key]);
	  }$routeProvider.otherwise({
	    redirectTo: '/404'
	  });

	  $locationProvider.html5Mode({
	    enabled: true,
	    requireBase: false
	  });
	}]).controller('mainCtrl', ["$http", "$scope", "$location", "$timeout", "$cookies", "$cookieStore", __webpack_require__(69)]);

	materials.init(app).run();

	/* Event emmitter examples */
	var id = ee.on('muhahaha', function (data) {
	  console.log("bugagashechko");
	  console.log(data);
	});

	ee.emit({
	  name: "muhahaha",
	  data: [1, 2, 3, 4, 5]
	});

	ee.off(id);

	ee.emit({
	  name: "muhahaha",
	  data: [1, 2, 3, 4, 5]
		});

/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(2);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(36)(content, {});
	if(content.locals) module.exports = content.locals;
	// Hot Module Replacement
	if(false) {
		// When the styles change, update the <style> tags
		if(!content.locals) {
			module.hot.accept("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./basic.scss", function() {
				var newContent = require("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./basic.scss");
				if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
				update(newContent);
			});
		}
		// When the module is disposed, remove the <style> tags
		module.hot.dispose(function() { update(); });
	}

/***/ },
/* 2 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, "body {\n  background-color: #ECECEC; }\n\nheader {\n  height: 70px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #1875D0; }\n\n.btn-blue, .btn-grey {\n  position: relative;\n  display: inline-block;\n  overflow: hidden;\n  border-radius: 5px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  cursor: pointer;\n  text-align: center; }\n\n.btn-grey {\n  background-color: #D8D8D8;\n  color: #868686;\n  font: 400 14px / 21px Roboto; }\n\n.container {\n  width: 1280px;\n  position: relative;\n  margin: 0 auto; }\n\n.clearfix:before, .clearfix:after {\n  content: \" \";\n  display: table; }\n\n.clearfix:after {\n  clear: both; }\n\n.ink {\n  display: block;\n  position: absolute;\n  background: rgba(0, 0, 0, 0.15);\n  border-radius: 100%;\n  -webkit-transform: scale(0);\n          transform: scale(0); }\n\n.ink.animate {\n  -webkit-animation: ripple .5s ease-in;\n          animation: ripple .5s ease-in; }\n\n@-webkit-keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n@keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n.headLeft {\n  padding-top: 5px;\n  display: inline-block;\n  width: calc(100% - 490px);\n  height: 70px;\n  box-sizing: border-box; }\n  .headLeft > .logo {\n    cursor: pointer;\n    border-radius: 50%;\n    float: left;\n    height: 60px;\n    width: 60px;\n    background: url(" + __webpack_require__(4) + ") no-repeat;\n    margin-left: 15px; }\n  .headLeft > .inputForm {\n    margin-top: 21px;\n    margin-left: 20px;\n    float: left;\n    border-color: #FDFDFD;\n    box-sizing: border-box;\n    height: auto;\n    width: 200px;\n    border-bottom: 1px solid #FDFDFD; }\n    .headLeft > .inputForm > label, .headLeft > .inputForm input {\n      color: #ffffff;\n      font: 400 14px Roboto; }\n  .headLeft > .selectBox {\n    float: left;\n    margin-top: 21px;\n    margin-left: 20px; }\n    .headLeft > .selectBox > .defaultValue {\n      background: none;\n      padding: 0 5px; }\n  .headLeft > .add {\n    cursor: pointer;\n    float: right;\n    height: 35px;\n    width: 200px;\n    margin-right: 20px;\n    margin-top: 10px;\n    background: url(" + __webpack_require__(5) + ") no-repeat center right 10px;\n    padding-left: 10px;\n    text-align: left;\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    border: 1px solid white;\n    -webkit-transition: all .15s;\n    transition: all .15s; }\n    .headLeft > .add > p {\n      color: #ffffff;\n      font: 400 14px / 35px Roboto; }\n\n.headRight {\n  float: right;\n  width: 490px;\n  height: 70px;\n  border-left: 1px solid grey;\n  box-sizing: border-box;\n  padding-top: 22px; }\n  .headRight > .mail {\n    height: 26px;\n    width: 33px;\n    cursor: pointer;\n    position: relative;\n    float: left;\n    background: url(" + __webpack_require__(6) + ") no-repeat center center;\n    margin-left: 20px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .mail > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -3px;\n      left: 32px;\n      cursor: default; }\n    .headRight > .mail:hover {\n      background: url(" + __webpack_require__(7) + ") no-repeat center center; }\n  .headRight > .bell {\n    height: 24px;\n    width: 23px;\n    cursor: pointer;\n    position: relative;\n    display: inline-block;\n    float: left;\n    margin-left: 30px;\n    background: url(" + __webpack_require__(8) + ") no-repeat center center;\n    border-radius: 15px 0 15px 10px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .bell > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -7px;\n      left: 22px;\n      cursor: default; }\n    .headRight > .bell:hover {\n      background: url(" + __webpack_require__(9) + ") no-repeat center center; }\n  .headRight > .services {\n    height: 27px;\n    width: 28px;\n    cursor: pointer;\n    float: right;\n    margin-right: 30px;\n    background: url(" + __webpack_require__(10) + ") no-repeat center center;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .services:hover {\n      background: url(" + __webpack_require__(11) + ") no-repeat center center; }\n  .headRight > .userName {\n    float: right;\n    margin-right: 25px;\n    background: url(" + __webpack_require__(12) + ") no-repeat center left;\n    padding-left: 30px;\n    max-width: 270px;\n    position: relative; }\n    .headRight > .userName > p {\n      color: #ffffff;\n      font: 400 14px / 27px Roboto;\n      cursor: pointer;\n      white-space: nowrap;\n      overflow: hidden;\n      text-overflow: ellipsis; }\n    .headRight > .userName > div {\n      background-color: #FDFDFD;\n      box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n      position: absolute;\n      top: 0;\n      width: 100%;\n      z-index: 1; }\n      .headRight > .userName > div > p {\n        cursor: pointer;\n        padding: 0 15px;\n        height: 48px;\n        font: 400 16px / 50px Roboto;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        box-sizing: border-box;\n        transition: all .25s; }\n        .headRight > .userName > div > p:hover {\n          background-color: #eeeeee; }\n  .headRight > .auth {\n    color: white;\n    float: right;\n    margin-right: 20px;\n    font: 400 14px / 26px Roboto; }\n    .headRight > .auth span {\n      cursor: pointer;\n      margin: 0 10px; }\n\n.inputSearch {\n  position: relative;\n  display: inline-block;\n  margin-top: 10px;\n  border-bottom: 1px solid white; }\n  .inputSearch > input {\n    border: none;\n    padding: 2px 0;\n    background-color: transparent;\n    outline: none;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto; }\n  .inputSearch > label {\n    position: absolute;\n    top: 0;\n    left: 0;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto;\n    cursor: text;\n    -webkit-transition: .5s;\n    transition: .5s; }\n\n.selectBox {\n  position: relative;\n  display: inline-block;\n  cursor: pointer; }\n  .selectBox > .defaultValue {\n    color: #ffffff;\n    font: 400 14px / 19px Roboto;\n    overflow: hidden;\n    border-bottom: 1px solid white;\n    background: url(" + __webpack_require__(20) + ") no-repeat center right 5px;\n    padding-right: 20px;\n    box-sizing: border-box; }\n    .selectBox > .defaultValue span {\n      font-size: 16px; }\n  .selectBox > .listOfValues {\n    position: absolute;\n    top: 0;\n    left: 0;\n    background-color: white;\n    z-index: 1;\n    box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n    -webkit-animation: animatetop .25s;\n            animation: animatetop .25s; }\n    .selectBox > .listOfValues > div {\n      cursor: pointer;\n      padding: 0 15px;\n      height: 50px;\n      width: 120px;\n      font: 400 16px / 50px Roboto;\n      -webkit-transition: all .25s;\n      transition: all .25s; }\n      .selectBox > .listOfValues > div:hover {\n        background-color: #eeeeee; }\n\n@-webkit-keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\n@keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\naside.bulletinDetails {\n  float: right;\n  margin-top: 5px; }\n\nsection.openAdvertert {\n  background-color: white;\n  float: right;\n  margin-right: 10px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 715px;\n  padding: 25px 100px 45px;\n  box-sizing: border-box;\n  margin-top: 5px; }\n  section.openAdvertert > h3 {\n    color: #202020;\n    font: 400 22px / 26px Roboto;\n    float: left; }\n  section.openAdvertert > .price {\n    color: #1f1f1f;\n    font: 400 18px / 26px Roboto;\n    float: right; }\n  section.openAdvertert > .checkBox {\n    float: right;\n    margin-right: 15px;\n    margin-top: -1px; }\n  section.openAdvertert > .breadCrumbs {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: left;\n    margin-top: 10px;\n    cursor: default; }\n  section.openAdvertert > .id {\n    color: rgba(32, 32, 32, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: right;\n    cursor: default;\n    margin-top: 10px; }\n  section.openAdvertert > .slider {\n    height: 120px;\n    position: relative;\n    margin-top: 10px;\n    float: left;\n    width: 100%;\n    margin-bottom: 10px; }\n    section.openAdvertert > .slider > div {\n      float: left;\n      margin-left: 10px;\n      height: 100%;\n      width: 165px; }\n      section.openAdvertert > .slider > div:nth-of-type(1) {\n        margin: 0; }\n      section.openAdvertert > .slider > div > img {\n        -o-object-fit: contain;\n           object-fit: contain;\n        width: 100%;\n        background-color: #F4F4F4;\n        height: 100%;\n        cursor: pointer; }\n    section.openAdvertert > .slider > .next {\n      position: absolute;\n      background: url(" + __webpack_require__(13) + ") no-repeat left center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      right: -25px; }\n    section.openAdvertert > .slider > .prev {\n      position: absolute;\n      background: url(" + __webpack_require__(14) + ") no-repeat right center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      left: -25px; }\n  section.openAdvertert > #mapForOneAdvertert {\n    float: left;\n    height: 230px;\n    width: 225px;\n    margin-bottom: 25px; }\n  section.openAdvertert > .wrapForDiv {\n    width: 265px;\n    float: right;\n    overflow: hidden;\n    margin-bottom: 25px;\n    border: 1px solid #E9E9E9;\n    box-sizing: border-box; }\n    section.openAdvertert > .wrapForDiv > ul.tab {\n      list-style-type: none;\n      height: 50px;\n      background-color: white;\n      border-bottom: 1px solid #E9E9E9;\n      box-sizing: border-box; }\n      section.openAdvertert > .wrapForDiv > ul.tab > li {\n        float: left; }\n        section.openAdvertert > .wrapForDiv > ul.tab > li > a {\n          color: #939393;\n          font: 400 14px / 50px Roboto;\n          display: block;\n          -webkit-transition: all .25s;\n          transition: all .25s;\n          text-align: center;\n          position: relative; }\n          section.openAdvertert > .wrapForDiv > ul.tab > li > a:after {\n            content: '';\n            display: block;\n            position: absolute;\n            bottom: 0;\n            width: 0;\n            height: 2px;\n            background-color: #FD5151;\n            -webkit-transition: all .25s;\n            transition: all .25s; }\n          section.openAdvertert > .wrapForDiv > ul.tab > li > a:focus, section.openAdvertert > .wrapForDiv > ul.tab > li > a.active {\n            color: #7eafe1; }\n            section.openAdvertert > .wrapForDiv > ul.tab > li > a:focus:after, section.openAdvertert > .wrapForDiv > ul.tab > li > a.active:after {\n              width: 100%; }\n        section.openAdvertert > .wrapForDiv > ul.tab > li:nth-of-type(1) > a {\n          width: 159px; }\n          section.openAdvertert > .wrapForDiv > ul.tab > li:nth-of-type(1) > a:after {\n            right: 0; }\n        section.openAdvertert > .wrapForDiv > ul.tab > li:nth-of-type(2) > a {\n          width: 104px; }\n          section.openAdvertert > .wrapForDiv > ul.tab > li:nth-of-type(2) > a:after {\n            left: 0; }\n    section.openAdvertert > .wrapForDiv > .featuresAndReviews {\n      height: 178px;\n      width: 282px;\n      background-color: #F4F4F4;\n      overflow: auto;\n      box-sizing: border-box; }\n      section.openAdvertert > .wrapForDiv > .featuresAndReviews > .tabcontent {\n        display: none;\n        -webkit-animation: fadeEffect 1s;\n        animation: fadeEffect 1s; }\n        section.openAdvertert > .wrapForDiv > .featuresAndReviews > .tabcontent.active {\n          display: block; }\n      section.openAdvertert > .wrapForDiv > .featuresAndReviews > #reviews {\n        position: relative; }\n        section.openAdvertert > .wrapForDiv > .featuresAndReviews > #reviews > div {\n          padding: 30px 20px 25px 65px;\n          position: relative;\n          border-bottom: 1px solid #E9E9E9; }\n          section.openAdvertert > .wrapForDiv > .featuresAndReviews > #reviews > div:nth-last-of-type(1) {\n            border: none; }\n          section.openAdvertert > .wrapForDiv > .featuresAndReviews > #reviews > div > img {\n            position: absolute;\n            height: 30px;\n            width: 25px;\n            top: 35px;\n            left: 20px; }\n          section.openAdvertert > .wrapForDiv > .featuresAndReviews > #reviews > div > p {\n            color: #0d0d1e;\n            font: 400 12px / 18px Roboto; }\n  section.openAdvertert > .descriptionAd {\n    color: #0c0c1e;\n    font: 400 12px / 18px Roboto;\n    margin-top: 15px;\n    margin-bottom: 20px; }\n  section.openAdvertert > .goToMap {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: left; }\n  section.openAdvertert > .allComments {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right;\n    margin-right: 50px; }\n  section.openAdvertert > .writeAReview {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right; }\n  section.openAdvertert > .nameUser {\n    color: #1976d2;\n    font: 400 14px / 18px Roboto;\n    float: left;\n    margin-left: 20px;\n    cursor: default;\n    margin-bottom: 35px; }\n  section.openAdvertert > .onOrOffLineUser {\n    float: left; }\n  section.openAdvertert .btn-grey {\n    float: right;\n    height: 35px;\n    width: 140px;\n    margin-right: 25px;\n    line-height: 35px; }\n  section.openAdvertert .btn-blue {\n    float: right;\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n\n@-webkit-keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n@keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n.btn-blue {\n  background-color: #1875D0;\n  color: #ffffff;\n  font: 400 14px / 21px Roboto; }\n\n.onOrOffLineUser {\n  background-color: #00C652;\n  border: 1px solid white;\n  box-shadow: 1px 1px 3px 0px rgba(0, 0, 0, 0.65);\n  height: 18px;\n  width: 18px;\n  box-sizing: border-box;\n  border-radius: 50%; }\n\n.error-container {\n  width: 1103px;\n  margin: auto;\n  background-image: url(" + __webpack_require__(15) + ");\n  background-repeat: no-repeat;\n  background-position-x: right;\n  background-position-y: 80px;\n  min-height: 500px; }\n  .error-container h1 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    text-align: center;\n    margin-top: 65px; }\n  .error-container h2 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    margin-top: 189px; }\n  .error-container h3 {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 10px;\n    margin-bottom: 30px; }\n  .error-container > .btn-blue {\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n\n.clicked {\n  box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.4) !important;\n  margin-top: 12px !important; }\n\n.multiple {\n  width: 1150px;\n  margin: 5px auto 0; }\n  .multiple > .bulletin-short {\n    float: left;\n    width: 380px;\n    margin-left: 5px;\n    height: 165px;\n    padding: 15px; }\n    .multiple > .bulletin-short:nth-of-type(3n-2) {\n      margin-left: 0; }\n    .multiple > .bulletin-short:nth-of-type(1) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(2) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(3) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short > .bulletin-center-column {\n      float: none;\n      margin: 0;\n      position: absolute;\n      top: 15px;\n      right: 110px; }\n      .multiple > .bulletin-short > .bulletin-center-column > .bulletin-action {\n        bottom: -43px;\n        width: 110px;\n        right: -30px;\n        z-index: 1; }\n    .multiple > .bulletin-short > .bulletin-left-column > .bulletin-description {\n      color: #0c0c1e;\n      font: 400 12px / 18px Roboto;\n      width: 170px;\n      overflow: hidden;\n      height: 40px; }\n\n.red {\n  background-color: #F74A4A; }\n\n.inputForm-required {\n  position: relative;\n  padding: 6px 2px;\n  font: 400 16px / 24.8px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px solid #9b9b9b;\n  margin-top: 15px; }\n  .inputForm-required label {\n    position: absolute;\n    left: 2px;\n    top: 6px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n  .inputForm-required input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent; }\n\n.inputForm.required {\n  border-bottom: 1px solid #9b9b9b !important; }\n\n.inputForm {\n  position: relative;\n  font: 400 16px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px dotted #999999; }\n  .inputForm label {\n    position: absolute;\n    left: 5px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s;\n    top: -1px; }\n  .inputForm input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent;\n    position: relative;\n    padding: 1px 5px; }\n\n/* Text element animation */\n.textOut {\n  top: -15px !important;\n  font-size: 12px !important;\n  left: 5px !important; }\n\nsection.login, section.register {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  padding: 65px 230px;\n  width: 1100px;\n  margin: 5px auto 0;\n  box-sizing: border-box; }\n  section.login > h2, section.register > h2 {\n    text-align: center;\n    color: #202020;\n    font: 400 22px Roboto;\n    margin-bottom: 55px; }\n  section.login > .inputForm, section.register > .inputForm {\n    margin-bottom: 45px; }\n    section.login > .inputForm > label, section.login > .inputForm input, section.register > .inputForm > label, section.register > .inputForm input {\n      color: #9a9a9a;\n      font: 400 16px Roboto; }\n  section.login > .btn-blue, section.register > .btn-blue {\n    height: 35px;\n    width: 180px;\n    margin-left: 45px;\n    line-height: 35px; }\n    section.login > .btn-blue.vk, section.register > .btn-blue.vk {\n      background: #0186CF url(" + __webpack_require__(16) + ") no-repeat center left 10px;\n      margin-left: 0; }\n    section.login > .btn-blue.facebook, section.register > .btn-blue.facebook {\n      background: #3E50B3 url(" + __webpack_require__(17) + ") no-repeat center left 10px; }\n    section.login > .btn-blue.google, section.register > .btn-blue.google {\n      background: #FD3C00 url(" + __webpack_require__(18) + ") no-repeat center left 10px; }\n\nsection.bulletinAdd {\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #FDFDFD;\n  width: 1100px;\n  margin: 5px auto 0;\n  padding: 65px 225px;\n  box-sizing: border-box; }\n  section.bulletinAdd > h2 {\n    color: #1f1f1f;\n    font: 400 22px Roboto;\n    text-align: center;\n    margin-bottom: 40px; }\n  section.bulletinAdd > .selectBox {\n    margin-bottom: 45px; }\n    section.bulletinAdd > .selectBox > .defaultValue {\n      border-color: grey;\n      min-width: 195px;\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      padding-left: 5px; }\n  section.bulletinAdd > .file {\n    float: right;\n    position: relative;\n    background-color: #FDFDFD;\n    border-bottom: 1px solid #9e9e9e;\n    width: 410px;\n    padding-bottom: 5px;\n    margin-top: -16px; }\n    section.bulletinAdd > .file > input {\n      position: absolute;\n      z-index: -1; }\n    section.bulletinAdd > .file > p {\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      cursor: default;\n      float: left;\n      padding-top: 10px; }\n    section.bulletinAdd > .file > .btn-blue {\n      float: right;\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 30px;\n      width: 85px;\n      line-height: 30px; }\n  section.bulletinAdd > .inputForm {\n    margin-bottom: 45px; }\n    section.bulletinAdd > .inputForm > label, section.bulletinAdd > .inputForm input {\n      color: #999999;\n      font: 400 16px Roboto; }\n  section.bulletinAdd > .color {\n    margin-bottom: 40px; }\n    section.bulletinAdd > .color > p {\n      color: #999999;\n      font: 400 16px Roboto;\n      border-bottom: 1px dotted #999999;\n      width: 160px;\n      float: left;\n      height: 21px;\n      box-sizing: border-box; }\n    section.bulletinAdd > .color > div {\n      float: left;\n      margin-left: 19px;\n      margin-top: 3px;\n      width: 16px;\n      height: 15px;\n      cursor: pointer;\n      box-sizing: border-box;\n      outline: 1px solid transparent;\n      border: 1px solid white; }\n      section.bulletinAdd > .color > div.transparent {\n        border-color: #E9E9E9;\n        overflow: hidden; }\n        section.bulletinAdd > .color > div.transparent > .redStick {\n          -webkit-transform: rotate(-43deg);\n                  transform: rotate(-43deg);\n          background-color: red;\n          height: 2px;\n          margin-top: 5.5px;\n          width: 20px;\n          margin-left: -3px; }\n      section.bulletinAdd > .color > div.red {\n        background-color: red; }\n      section.bulletinAdd > .color > div.orange {\n        background-color: orange; }\n      section.bulletinAdd > .color > div.yellow {\n        background-color: yellow; }\n      section.bulletinAdd > .color > div.green {\n        background-color: green; }\n      section.bulletinAdd > .color > div.lightBlue {\n        background-color: lightBlue; }\n      section.bulletinAdd > .color > div.blue {\n        background-color: blue; }\n      section.bulletinAdd > .color > div.pink {\n        background-color: pink; }\n      section.bulletinAdd > .color > div.purple {\n        background-color: purple; }\n      section.bulletinAdd > .color > div.white {\n        background-color: white;\n        border-color: #E9E9E9; }\n      section.bulletinAdd > .color > div.gray {\n        background-color: gray; }\n      section.bulletinAdd > .color > div.black {\n        background-color: black; }\n      section.bulletinAdd > .color > div.brown {\n        background-color: brown; }\n      section.bulletinAdd > .color > div.multicolor {\n        /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#ff0000+0,ffff00+20,1dff00+40,00ffff+60,0400ff+80,ff00fa+100 */\n        background: #ff0000;\n        /* Old browsers */\n        /* FF3.6-15 */\n        background: -webkit-linear-gradient(left, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* Chrome10-25,Safari5.1-6 */\n        background: linear-gradient(to right, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */\n        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff0000', endColorstr='#ff00fa',GradientType=1 );\n        /* IE6-9 */ }\n      section.bulletinAdd > .color > div.active {\n        outline-color: red; }\n  section.bulletinAdd > .calendar, section.bulletinAdd .addCalendar {\n    height: 20px;\n    position: relative;\n    width: 230px;\n    background: url(" + __webpack_require__(19) + ") no-repeat center left;\n    padding-left: 45px;\n    box-sizing: border-box;\n    margin-bottom: 40px; }\n    section.bulletinAdd > .calendar > .defaultValue, section.bulletinAdd .addCalendar > .defaultValue {\n      border-bottom: 1px solid grey;\n      background: url(" + __webpack_require__(20) + ") no-repeat center right 5px;\n      cursor: pointer; }\n      section.bulletinAdd > .calendar > .defaultValue > p, section.bulletinAdd .addCalendar > .defaultValue > p {\n        text-align: left;\n        color: #262626;\n        font: 400 14px / 20px Roboto; }\n    section.bulletinAdd > .calendar > .listValue, section.bulletinAdd .addCalendar > .listValue {\n      display: none; }\n  section.bulletinAdd > .addCalendar {\n    background-image: url(" + __webpack_require__(21) + "); }\n    section.bulletinAdd > .addCalendar > .defaultValue {\n      border-bottom-color: blue;\n      background: none; }\n      section.bulletinAdd > .addCalendar > .defaultValue > p {\n        color: #1976d2;\n        font-size: 16px; }\n  section.bulletinAdd > .btn-blue {\n    height: 35px;\n    width: 180px;\n    line-height: 35px;\n    display: block;\n    margin: 0 auto; }\n\n.errors {\n  position: absolute;\n  font: 400 12px / 14px Roboto;\n  color: #dd2c00;\n  bottom: -17px; }\n\nnav {\n  width: 255px;\n  float: left;\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  margin-top: 5px; }\n  nav > .map {\n    background: url(" + __webpack_require__(22) + ") no-repeat;\n    height: 145px;\n    width: 255px; }\n    nav > .map > .inputForm {\n      width: 215px;\n      margin: 115px auto 0; }\n      nav > .map > .inputForm.required {\n        border-color: black !important; }\n      nav > .map > .inputForm > input, nav > .map > .inputForm label, nav > .map > .inputForm .textOut {\n        color: black !important; }\n  nav > ul {\n    list-style: none; }\n    nav > ul > li > p {\n      padding-left: 75px;\n      color: #263238;\n      font: 500 13px / 40px Roboto;\n      background: url(" + __webpack_require__(23) + ") no-repeat center right 20px;\n      cursor: pointer; }\n    nav > ul > li:hover {\n      background-color: #E6E6E6 !important; }\n    nav > ul > li:nth-of-type(1) {\n      background: url(" + __webpack_require__(24) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(2) {\n      background: url(" + __webpack_require__(25) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(3) {\n      background: url(" + __webpack_require__(26) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(4) {\n      background: url(" + __webpack_require__(27) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(5) {\n      background: url(" + __webpack_require__(28) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(6) {\n      background: url(" + __webpack_require__(29) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(7) {\n      background: url(" + __webpack_require__(30) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(8) {\n      background: url(" + __webpack_require__(31) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(9) {\n      background: url(" + __webpack_require__(32) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(10) {\n      background: url(" + __webpack_require__(33) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(11) {\n      background: url(" + __webpack_require__(34) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(12) {\n      background: url(" + __webpack_require__(35) + ") no-repeat left 25px center; }\n\n.navBtn {\n  width: 43px;\n  height: 49px;\n  position: absolute;\n  left: 0;\n  top: 90px;\n  cursor: pointer;\n  background: url(" + __webpack_require__(80) + ") no-repeat;\n  -webkit-transition: all .25s;\n  transition: all .25s;\n  z-index: 1; }\n  .navBtn:hover {\n    width: 60px;\n    background-image: url(" + __webpack_require__(81) + "); }\n", ""]);

	// exports


/***/ },
/* 3 */
/***/ function(module, exports) {

	"use strict";

	/*
		MIT License http://www.opensource.org/licenses/mit-license.php
		Author Tobias Koppers @sokra
	*/
	// css base code, injected by the css-loader
	module.exports = function () {
		var list = [];

		// return the list of modules as css string
		list.toString = function toString() {
			var result = [];
			for (var i = 0; i < this.length; i++) {
				var item = this[i];
				if (item[2]) {
					result.push("@media " + item[2] + "{" + item[1] + "}");
				} else {
					result.push(item[1]);
				}
			}
			return result.join("");
		};

		// import a list of modules into the list
		list.i = function (modules, mediaQuery) {
			if (typeof modules === "string") modules = [[null, modules, ""]];
			var alreadyImportedModules = {};
			for (var i = 0; i < this.length; i++) {
				var id = this[i][0];
				if (typeof id === "number") alreadyImportedModules[id] = true;
			}
			for (i = 0; i < modules.length; i++) {
				var item = modules[i];
				// skip already imported module
				// this implementation is not 100% perfect for weird media query combinations
				//  when a module is imported multiple times with different media queries.
				//  I hope this will never occur (Hey this way we have smaller bundles)
				if (typeof item[0] !== "number" || !alreadyImportedModules[item[0]]) {
					if (mediaQuery && !item[2]) {
						item[2] = mediaQuery;
					} else if (mediaQuery) {
						item[2] = "(" + item[2] + ") and (" + mediaQuery + ")";
					}
					list.push(item);
				}
			}
		};
		return list;
	};

/***/ },
/* 4 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/logo.png";

/***/ },
/* 5 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/add.png";

/***/ },
/* 6 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/mail.png";

/***/ },
/* 7 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/mail_shadow.png";

/***/ },
/* 8 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/bell.png";

/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/bell_shadow.png";

/***/ },
/* 10 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/services.png";

/***/ },
/* 11 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/services_shadow.png";

/***/ },
/* 12 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/userName.png";

/***/ },
/* 13 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/next.png";

/***/ },
/* 14 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/prev.png";

/***/ },
/* 15 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/error_bg.png";

/***/ },
/* 16 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/vk.png";

/***/ },
/* 17 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/facebook.png";

/***/ },
/* 18 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/google.png";

/***/ },
/* 19 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/calendar.png";

/***/ },
/* 20 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/caretCalendar.png";

/***/ },
/* 21 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/addCalendar.png";

/***/ },
/* 22 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/map.png";

/***/ },
/* 23 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/caretRightNav.png";

/***/ },
/* 24 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/ForChildren.png";

/***/ },
/* 25 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/ForAnimals.png";

/***/ },
/* 26 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/Business.png";

/***/ },
/* 27 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/TheProperty.png";

/***/ },
/* 28 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/Transport.png";

/***/ },
/* 29 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/ClothingAndCosmetics.png";

/***/ },
/* 30 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/Volunteering.png";

/***/ },
/* 31 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/IsFree.png";

/***/ },
/* 32 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/AHouseAndAGarden.png";

/***/ },
/* 33 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/HobbiesAndSports.png";

/***/ },
/* 34 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/Barter.png";

/***/ },
/* 35 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/Technologies.png";

/***/ },
/* 36 */
/***/ function(module, exports, __webpack_require__) {

	/*
		MIT License http://www.opensource.org/licenses/mit-license.php
		Author Tobias Koppers @sokra
	*/
	var stylesInDom = {},
		memoize = function(fn) {
			var memo;
			return function () {
				if (typeof memo === "undefined") memo = fn.apply(this, arguments);
				return memo;
			};
		},
		isOldIE = memoize(function() {
			return /msie [6-9]\b/.test(window.navigator.userAgent.toLowerCase());
		}),
		getHeadElement = memoize(function () {
			return document.head || document.getElementsByTagName("head")[0];
		}),
		singletonElement = null,
		singletonCounter = 0,
		styleElementsInsertedAtTop = [];

	module.exports = function(list, options) {
		if(false) {
			if(typeof document !== "object") throw new Error("The style-loader cannot be used in a non-browser environment");
		}

		options = options || {};
		// Force single-tag solution on IE6-9, which has a hard limit on the # of <style>
		// tags it will allow on a page
		if (typeof options.singleton === "undefined") options.singleton = isOldIE();

		// By default, add <style> tags to the bottom of <head>.
		if (typeof options.insertAt === "undefined") options.insertAt = "bottom";

		var styles = listToStyles(list);
		addStylesToDom(styles, options);

		return function update(newList) {
			var mayRemove = [];
			for(var i = 0; i < styles.length; i++) {
				var item = styles[i];
				var domStyle = stylesInDom[item.id];
				domStyle.refs--;
				mayRemove.push(domStyle);
			}
			if(newList) {
				var newStyles = listToStyles(newList);
				addStylesToDom(newStyles, options);
			}
			for(var i = 0; i < mayRemove.length; i++) {
				var domStyle = mayRemove[i];
				if(domStyle.refs === 0) {
					for(var j = 0; j < domStyle.parts.length; j++)
						domStyle.parts[j]();
					delete stylesInDom[domStyle.id];
				}
			}
		};
	}

	function addStylesToDom(styles, options) {
		for(var i = 0; i < styles.length; i++) {
			var item = styles[i];
			var domStyle = stylesInDom[item.id];
			if(domStyle) {
				domStyle.refs++;
				for(var j = 0; j < domStyle.parts.length; j++) {
					domStyle.parts[j](item.parts[j]);
				}
				for(; j < item.parts.length; j++) {
					domStyle.parts.push(addStyle(item.parts[j], options));
				}
			} else {
				var parts = [];
				for(var j = 0; j < item.parts.length; j++) {
					parts.push(addStyle(item.parts[j], options));
				}
				stylesInDom[item.id] = {id: item.id, refs: 1, parts: parts};
			}
		}
	}

	function listToStyles(list) {
		var styles = [];
		var newStyles = {};
		for(var i = 0; i < list.length; i++) {
			var item = list[i];
			var id = item[0];
			var css = item[1];
			var media = item[2];
			var sourceMap = item[3];
			var part = {css: css, media: media, sourceMap: sourceMap};
			if(!newStyles[id])
				styles.push(newStyles[id] = {id: id, parts: [part]});
			else
				newStyles[id].parts.push(part);
		}
		return styles;
	}

	function insertStyleElement(options, styleElement) {
		var head = getHeadElement();
		var lastStyleElementInsertedAtTop = styleElementsInsertedAtTop[styleElementsInsertedAtTop.length - 1];
		if (options.insertAt === "top") {
			if(!lastStyleElementInsertedAtTop) {
				head.insertBefore(styleElement, head.firstChild);
			} else if(lastStyleElementInsertedAtTop.nextSibling) {
				head.insertBefore(styleElement, lastStyleElementInsertedAtTop.nextSibling);
			} else {
				head.appendChild(styleElement);
			}
			styleElementsInsertedAtTop.push(styleElement);
		} else if (options.insertAt === "bottom") {
			head.appendChild(styleElement);
		} else {
			throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");
		}
	}

	function removeStyleElement(styleElement) {
		styleElement.parentNode.removeChild(styleElement);
		var idx = styleElementsInsertedAtTop.indexOf(styleElement);
		if(idx >= 0) {
			styleElementsInsertedAtTop.splice(idx, 1);
		}
	}

	function createStyleElement(options) {
		var styleElement = document.createElement("style");
		styleElement.type = "text/css";
		insertStyleElement(options, styleElement);
		return styleElement;
	}

	function createLinkElement(options) {
		var linkElement = document.createElement("link");
		linkElement.rel = "stylesheet";
		insertStyleElement(options, linkElement);
		return linkElement;
	}

	function addStyle(obj, options) {
		var styleElement, update, remove;

		if (options.singleton) {
			var styleIndex = singletonCounter++;
			styleElement = singletonElement || (singletonElement = createStyleElement(options));
			update = applyToSingletonTag.bind(null, styleElement, styleIndex, false);
			remove = applyToSingletonTag.bind(null, styleElement, styleIndex, true);
		} else if(obj.sourceMap &&
			typeof URL === "function" &&
			typeof URL.createObjectURL === "function" &&
			typeof URL.revokeObjectURL === "function" &&
			typeof Blob === "function" &&
			typeof btoa === "function") {
			styleElement = createLinkElement(options);
			update = updateLink.bind(null, styleElement);
			remove = function() {
				removeStyleElement(styleElement);
				if(styleElement.href)
					URL.revokeObjectURL(styleElement.href);
			};
		} else {
			styleElement = createStyleElement(options);
			update = applyToTag.bind(null, styleElement);
			remove = function() {
				removeStyleElement(styleElement);
			};
		}

		update(obj);

		return function updateStyle(newObj) {
			if(newObj) {
				if(newObj.css === obj.css && newObj.media === obj.media && newObj.sourceMap === obj.sourceMap)
					return;
				update(obj = newObj);
			} else {
				remove();
			}
		};
	}

	var replaceText = (function () {
		var textStore = [];

		return function (index, replacement) {
			textStore[index] = replacement;
			return textStore.filter(Boolean).join('\n');
		};
	})();

	function applyToSingletonTag(styleElement, index, remove, obj) {
		var css = remove ? "" : obj.css;

		if (styleElement.styleSheet) {
			styleElement.styleSheet.cssText = replaceText(index, css);
		} else {
			var cssNode = document.createTextNode(css);
			var childNodes = styleElement.childNodes;
			if (childNodes[index]) styleElement.removeChild(childNodes[index]);
			if (childNodes.length) {
				styleElement.insertBefore(cssNode, childNodes[index]);
			} else {
				styleElement.appendChild(cssNode);
			}
		}
	}

	function applyToTag(styleElement, obj) {
		var css = obj.css;
		var media = obj.media;

		if(media) {
			styleElement.setAttribute("media", media)
		}

		if(styleElement.styleSheet) {
			styleElement.styleSheet.cssText = css;
		} else {
			while(styleElement.firstChild) {
				styleElement.removeChild(styleElement.firstChild);
			}
			styleElement.appendChild(document.createTextNode(css));
		}
	}

	function updateLink(linkElement, obj) {
		var css = obj.css;
		var sourceMap = obj.sourceMap;

		if(sourceMap) {
			// http://stackoverflow.com/a/26603875
			css += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(sourceMap)))) + " */";
		}

		var blob = new Blob([css], { type: "text/css" });

		var oldSrc = linkElement.href;

		linkElement.href = URL.createObjectURL(blob);

		if(oldSrc)
			URL.revokeObjectURL(oldSrc);
	}


/***/ },
/* 37 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(38);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(36)(content, {});
	if(content.locals) module.exports = content.locals;
	// Hot Module Replacement
	if(false) {
		// When the styles change, update the <style> tags
		if(!content.locals) {
			module.hot.accept("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./favourites.scss", function() {
				var newContent = require("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./favourites.scss");
				if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
				update(newContent);
			});
		}
		// When the module is disposed, remove the <style> tags
		module.hot.dispose(function() { update(); });
	}

/***/ },
/* 38 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, "@charset \"UTF-8\";\n#favourites {\n  width: 990px;\n  margin: 3px auto 0; }\n  #favourites .right-column {\n    width: 490px;\n    float: right; }\n  #favourites .left-column {\n    width: 490px;\n    float: left; }\n\n.bulletin-short {\n  position: relative;\n  padding: 20px 16px;\n  width: 490px;\n  margin-bottom: 7px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  box-sizing: border-box;\n  background-color: white;\n  /* Левая колонка */\n  /* Правая колонка */ }\n  .bulletin-short > div {\n    display: inline-block; }\n  .bulletin-short .bulletin-right-column {\n    float: right;\n    width: 90px;\n    position: relative; }\n    .bulletin-short .bulletin-right-column > .onOrOffLineUser {\n      position: absolute;\n      top: 5px;\n      left: 10px; }\n  .bulletin-short .bulletin-center-column {\n    float: right;\n    margin-right: 15px; }\n  .bulletin-short h3 {\n    margin: 0;\n    font: 400 20px / 24px Roboto;\n    color: #212121;\n    width: 206px;\n    cursor: pointer;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .bulletin-short h3:hover {\n      text-decoration: underline;\n      -webkit-text-decoration-color: gray;\n              text-decoration-color: gray;\n      -webkit-text-decoration-style: dashed;\n              text-decoration-style: dashed; }\n  .bulletin-short .bulletin-category {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 11px;\n    margin-bottom: 16px; }\n  .bulletin-short .bulletin-description {\n    font: 400 12px / 18.6px Roboto;\n    width: 254px;\n    color: #0d0d1e; }\n  .bulletin-short .bulletin-image {\n    width: 90px;\n    height: 91px;\n    background: #1875D0 url(" + __webpack_require__(39) + "); }\n  .bulletin-short .bulletin-price {\n    position: absolute;\n    bottom: -54px;\n    right: 0;\n    color: #202020;\n    font: 400 14px / 16.8px Roboto; }\n  .bulletin-short .bulletin-action {\n    position: absolute;\n    bottom: 20px;\n    right: 117px;\n    color: #212121;\n    font: 400 14px / 16.8px Roboto; }\n  .bulletin-short > .wrapRibbon {\n    position: absolute;\n    position: absolute;\n    bottom: 0;\n    left: 0;\n    z-index: 1; }\n    .bulletin-short > .wrapRibbon > .ribbon {\n      width: 100px;\n      position: relative;\n      background-color: #F5911D;\n      text-align: center; }\n      .bulletin-short > .wrapRibbon > .ribbon:before {\n        content: \"\";\n        position: absolute;\n        display: block;\n        bottom: -14px;\n        border: 13px solid #e57b00;\n        z-index: -1;\n        left: -23px;\n        border-right-width: 1.5em;\n        border-left-color: transparent;\n        box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n      .bulletin-short > .wrapRibbon > .ribbon:after {\n        content: \"\";\n        position: absolute;\n        display: block;\n        bottom: 0;\n        border: 13px solid #F5911D;\n        right: -13px;\n        border-left-width: 0;\n        border-right-color: transparent; }\n      .bulletin-short > .wrapRibbon > .ribbon > .ribbon-content {\n        color: #ffffff;\n        font: 400 14px / 26px Roboto;\n        cursor: default; }\n        .bulletin-short > .wrapRibbon > .ribbon > .ribbon-content:before {\n          content: \"\";\n          position: absolute;\n          display: block;\n          border-style: solid;\n          border-color: #2B4A67 transparent transparent transparent;\n          bottom: -14px;\n          left: 0;\n          border-width: 1em 0 0 1em; }\n\n.checkBox {\n  width: 25px;\n  height: 25px;\n  border: 1px solid grey;\n  border-radius: 5px;\n  cursor: pointer;\n  -webkit-transition: all .25s;\n  transition: all .25s; }\n\n.checked {\n  background: #1875D0 url(" + __webpack_require__(40) + ") no-repeat center center;\n  border-color: #1875D0 !important; }\n\ndiv.exclamationPoint {\n  background: url(" + __webpack_require__(41) + ") no-repeat center center;\n  height: 27px;\n  width: 27px;\n  margin-top: 10px; }\n\n.dollarBill {\n  background: url(" + __webpack_require__(42) + ") no-repeat center center;\n  height: 27px;\n  width: 27px;\n  margin-top: 10px; }\n", ""]);

	// exports


/***/ },
/* 39 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/bulletin-default.png";

/***/ },
/* 40 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/V.png";

/***/ },
/* 41 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/exclamationPoint.png";

/***/ },
/* 42 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/dollarBill.png";

/***/ },
/* 43 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(44);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(36)(content, {});
	if(content.locals) module.exports = content.locals;
	// Hot Module Replacement
	if(false) {
		// When the styles change, update the <style> tags
		if(!content.locals) {
			module.hot.accept("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./edit-profile.scss", function() {
				var newContent = require("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./edit-profile.scss");
				if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
				update(newContent);
			});
		}
		// When the module is disposed, remove the <style> tags
		module.hot.dispose(function() { update(); });
	}

/***/ },
/* 44 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, ".view-edit-profile {\n  width: 652px;\n  margin: 0 auto; }\n  .view-edit-profile h1 {\n    font: 700 22px Roboto;\n    text-align: center; }\n  .view-edit-profile .edit-profile-form {\n    font: 400 16px Roboto; }\n    .view-edit-profile .edit-profile-form .inputForm, .view-edit-profile .edit-profile-form .inputForm-required, .view-edit-profile .edit-profile-form textarea {\n      width: 100%; }\n  .view-edit-profile .edit-profile-form-contacts-container .inputForm {\n    width: 95%;\n    display: inline-block; }\n  .view-edit-profile .edit-profile-form-contacts-container button {\n    width: 16px;\n    height: 16px;\n    display: inline-block;\n    background: url(" + __webpack_require__(45) + ") no-repeat;\n    background-size: contain; }\n  .view-edit-profile .edit-profile-form-foto {\n    float: right; }\n    .view-edit-profile .edit-profile-form-foto input {\n      width: 60%; }\n    .view-edit-profile .edit-profile-form-foto .edit-profile-form-foto-preview {\n      background: url(" + __webpack_require__(46) + ") no-repeat;\n      background-position: center center;\n      background-size: contain;\n      display: inline-block;\n      width: 32px;\n      height: 32px; }\n  .view-edit-profile .social-link-container {\n    height: auto; }\n    .view-edit-profile .social-link-container div {\n      width: 24px;\n      height: 24px;\n      float: left;\n      margin-right: 47px; }\n\n.uploadFileForm {\n  visibility: hidden; }\n  .uploadFileForm input {\n    width: 0px;\n    height: 0px; }\n", ""]);

	// exports


/***/ },
/* 45 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/icon_close_blue.png";

/***/ },
/* 46 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/avatar.jpg";

/***/ },
/* 47 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(48);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(36)(content, {});
	if(content.locals) module.exports = content.locals;
	// Hot Module Replacement
	if(false) {
		// When the styles change, update the <style> tags
		if(!content.locals) {
			module.hot.accept("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./profile.scss", function() {
				var newContent = require("!!./../node_modules/css-loader/index.js!./../node_modules/postcss-loader/index.js!./../node_modules/sass-loader/index.js!./profile.scss");
				if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
				update(newContent);
			});
		}
		// When the module is disposed, remove the <style> tags
		module.hot.dispose(function() { update(); });
	}

/***/ },
/* 48 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, ".view-profile-container {\n  background-color: #fff;\n  box-sizing: border-box;\n  padding: 40px 127px 0px;\n  margin: 5px auto 0;\n  width: 1103px;\n  font: 400 16px/24px Roboto; }\n  .view-profile-container h1 {\n    padding-bottom: 16px;\n    text-align: center;\n    font: 400 22px/26px Roboto; }\n  .view-profile-container input:-moz-read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n  .view-profile-container input:read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n    .view-profile-container input:-moz-read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n    .view-profile-container input:read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n  .view-profile-container .social-link-container {\n    margin-bottom: 45px; }\n\n.profile-containers-wrap {\n  width: 849px;\n  margin: 0 auto; }\n\n.profile-left-container, .profile-right-container, .profile-info-section-left, .profile-info-section-right, .profile-info {\n  float: left; }\n  .profile-left-container::after, .profile-right-container::after, .profile-info-section-left::after, .profile-info-section-right::after, .profile-info::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.view-profile::after, .view-profile-container::after {\n  content: \"\";\n  display: table;\n  clear: both; }\n\n.profile-left-container {\n  width: 190px;\n  padding: 0 52px; }\n  .profile-left-container .btn-blue {\n    width: 180px;\n    height: 36px;\n    line-height: 36px;\n    margin: 0 auto;\n    margin-top: 9px; }\n  .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox {\n    display: block;\n    margin-bottom: 25px; }\n    .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox > .defaultValue {\n      color: #9e9e9e;\n      font: 400 16px / 24px Roboto; }\n\n.profile-right-container {\n  width: 485px;\n  padding-left: 20px;\n  border-left: 1px solid #ebebeb; }\n\n.profile-info-section-left, .profile-info-section-right {\n  width: 50%;\n  box-sizing: border-box; }\n\n.profile-avatar {\n  width: 145px;\n  height: 215px;\n  margin: 0 auto 40px;\n  padding-bottom: 5px;\n  background: url(" + __webpack_require__(46) + ") no-repeat center center;\n  background-size: contain;\n  box-sizing: border-box; }\n\n.profile-settings-dropdown .profile-settings-dropdown-title {\n  cursor: pointer;\n  padding: 13px 0; }\n  .profile-settings-dropdown .profile-settings-dropdown-title::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .profile-settings-dropdown .profile-settings-dropdown-title span {\n    color: #929292;\n    float: left; }\n  .profile-settings-dropdown .profile-settings-dropdown-title div {\n    float: right;\n    background: url(" + __webpack_require__(49) + ");\n    width: 13px;\n    height: 7px; }\n\n.social-link-container div {\n  cursor: pointer;\n  width: 24px;\n  height: 24px;\n  float: left; }\n  .social-link-container div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .social-link-container div:not(:last-of-type) {\n    margin-right: 23px; }\n\n.profile-messages-and-notifications {\n  display: block;\n  width: 100%;\n  margin: 30px 0;\n  float: left; }\n  .profile-messages-and-notifications::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav, .profile-messages-and-notifications-content {\n  border: 1px solid #ebebeb;\n  overflow: hidden; }\n\n.profile-messages-and-notifications-nav div {\n  color: #929292;\n  width: 50%;\n  padding: 24px 0;\n  text-align: center;\n  float: left; }\n  .profile-messages-and-notifications-nav div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav .nav-item-selected {\n  color: #7eaee0;\n  border-bottom: 2px solid #ff5252; }\n\n.profile-messages-and-notifications-content {\n  background-color: #f6f6f6;\n  height: 180px;\n  box-sizing: border-box; }\n\n.profile-messages-and-notifications-content-item {\n  width: 100%;\n  border-bottom: 1px solid #ebebeb;\n  height: 123px;\n  position: relative;\n  overflow: hidden; }\n  .profile-messages-and-notifications-content-item .content-item-logo {\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 27px;\n    height: 27px;\n    padding: 0 23px 23px 7px;\n    background: url(" + __webpack_require__(50) + ") no-repeat;\n    display: inline-block;\n    background-position: center;\n    margin-top: 28px; }\n  .profile-messages-and-notifications-content-item .content-item-text {\n    display: inline-block;\n    float: right;\n    width: 373px;\n    margin: 28px 38px 24px 0;\n    font: 400 12px/18px Roboto;\n    color: #0c0c1e; }\n    .profile-messages-and-notifications-content-item .content-item-text::after {\n      content: \"\";\n      display: table;\n      clear: both; }\n", ""]);

	// exports


/***/ },
/* 49 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/caret_black.png";

/***/ },
/* 50 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/icon_user.png";

/***/ },
/* 51 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var debug = __webpack_require__(52);

	var ctx = module.exports = {};

	module.exports = function () {
	  ctx.DEBUG_LEVEL = debug.DEBUG;

	  console.log = function () {
	    var log = console.log;
	    if (ctx.DEBUG_LEVEL == debug.DEBUG) return log;else return function () {};
	  }.bind(ctx)();

	  console.error = function () {
	    var error = console.error;
	    if (ctx.DEBUG_LEVEL == debug.DEBUG || ctx.DEBUG_LEVEL == debug.ONLY_ERRORS) return error;else return function () {};
	  }.bind(ctx)();

	  console.info = function () {
	    var info = console.info;
	    if (ctx.DEBUG_LEVEL == debug.DEBUG || ctx.DEBUG_LEVEL == debug.ONLY_ERRORS) return info;else return function () {};
	  }.bind(ctx)();

	  /*  Для хохмы */
	  window.ls = "You've missed a window, lol =)";
	};

/***/ },
/* 52 */
/***/ function(module, exports) {

	module.exports = {
		"DEBUG": 0,
		"ONLY_ERRORS": 1,
		"PRODUCTION": 2
	};

/***/ },
/* 53 */,
/* 54 */,
/* 55 */,
/* 56 */,
/* 57 */,
/* 58 */,
/* 59 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	module.exports = {
	  "/": {
	    templateUrl: "templates/index.html",
	    controller: __webpack_require__(60),
	    controllerAs: "index"
	  },
	  '/403': {
	    templateUrl: "templates/error403.html"
	  },
	  '/404': {
	    templateUrl: "templates/error404.html"
	  },
	  '/500': {
	    templateUrl: "templates/error500.html"
	  },
	  '/bulletinDetails': {
	    templateUrl: "templates/bulletinDetails.html",
	    controller: __webpack_require__(61),
	    controllerAs: "bdetailed"
	  },
	  '/bulletinAdd': {
	    templateUrl: "templates/authenticated/bulletinAdd.html",
	    controller: __webpack_require__(62),
	    controllerAs: "badd"
	  },
	  '/login': {
	    templateUrl: "templates/login.html",
	    controller: __webpack_require__(63),
	    controllerAs: "login"
	  },
	  '/register': {
	    templateUrl: "templates/register.html",
	    controller: __webpack_require__(64),
	    controllerAs: "register"
	  },
	  '/editProfile': {
	    templateUrl: "templates/authenticated/edit-profile.html",
	    controller: __webpack_require__(65),
	    controllerAs: "profile"
	  },
	  '/profile': {
	    templateUrl: "templates/authenticated/profile.html",
	    controller: __webpack_require__(66),
	    controllerAs: "profile"
	  },
	  '/favourites': {
	    templateUrl: "templates/authenticated/favourites.html",
	    controller: __webpack_require__(67),
	    controllerAs: "favourite"
	  },
	  '/searchResults': {
	    templateUrl: "templates/searchResults.html",
	    controller: __webpack_require__(68),
	    controllerAs: "searchResults"
	  }
		};

/***/ },
/* 60 */
/***/ function(module, exports) {

	"use strict";

		module.exports = function ($scope) {};

/***/ },
/* 61 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 62 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 63 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope) {
	  var _this = this;

	  this.init = function () {
	    _this.db = $scope.$parent.db;

	    _this.email = "";
	    _this.password = "";

	    _this.emailValid = true;
	    _this.passwordValid = true;

	    _this.loginError = "";

	    _this.handler = function (e) {
	      if (e.which == 13) this.send.call(this);
	    }.bind(_this);

	    document.addEventListener('keyup', _this.handler);
	  };

	  this.deleteListners = function () {
	    document.removeEventListener('keyup', _this.handler);
	  };

	  this.send = function () {
	    ee.emit({ name: "form-submit" });
	    /*
	      - Get data
	      - Validate
	      - Show errors
	      - or
	      - Goto bd and send data
	    */
	    // this.deleteListners()

	    if (_this.emailValid && _this.passwordValid) {
	      _this.db.login({
	        "email": _this.email,
	        "password": _this.password
	      }, function (err, data) {
	        if (err) $scope.$parent.displayError("Ошибка авторизации, проверьте ваши данные");else {
	          _this.deleteListners();
	          _this.db.saveUserData(data);
	          $scope.redirectToUrl('/profile');
	        }
	      });
	    }
	  };

	  this.emailIsValid = function (email) {
	    var error = "";
	    if (!email.length) error += "Обязательное поле. ";
	    if (!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email)) error += "Неверный формат почты. ";
	    return error;
	  };

	  this.passwordIsValid = function (pwd) {
	    var error = "";
	    if (!pwd.length) error += "Обязательное поле. ";
	    if (pwd.length < 6) error += "Пароль должен содержать не менее 6 символов. ";
	    return error;
	  };
	};

/***/ },
/* 64 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope, $q) {
	  var _this = this;

	  this.init = function () {
	    _this.db = $scope.$parent.db;

	    _this.email = "";
	    _this.password = "";
	    _this.password2 = "";

	    _this.emailValid = "";
	    _this.passwordValid = "";
	    _this.password2Valid = "";

	    _this.handler = function (e) {
	      if (e.which == 13) this.send.call(this);
	    }.bind(_this);

	    document.addEventListener('keyup', _this.handler);
	  };

	  this.deleteListners = function () {
	    document.removeEventListener('keyup', _this.handler);
	  };

	  this.send = function () {
	    /*
	      - Get data
	      - Validate
	      - Show errors
	      - or
	      - Goto bd and send data
	    */
	    if (_this.emailValid && _this.passwordValid && _this.password2Valid) {
	      _this.db.login({
	        "email": _this.email,
	        "password": _this.password
	      }, function (err, data) {
	        _this.deleteListners();
	        if (err) $scope.$parent.redirectToUrl('/500');else {
	          /* Save data to db */
	          $scope.redirectToUrl('/profile');
	          console.log(data);
	        }
	      });
	    }
	  };

	  this.emailIsValid = function (email) {
	    return $q(function (resolve, reject) {
	      var error = "";
	      if (!email.length) error += "Обязательное поле. ";
	      if (!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email)) error += "Неверный формат почты. ";

	      window.db.checkEmail(email, function (err, data) {
	        if (err) reject(err);else {
	          console.log(data);
	          if (data !== "false") error += "Такая почта уже используется. ";
	          resolve(error);
	        }
	      }.bind(this));
	    }.bind(this));
	  };

	  this.passwordIsValid = function (pwd) {
	    var error = "";
	    if (!pwd.length) error += "Обязательное поле. ";
	    if (pwd.length < 6) error += "Пароль должен содержать не менее 6 символов. ";
	    return error;
	  };

	  this.password2IsValid = function (pwd) {
	    var error = _this.passwordIsValid(pwd);
	    if (_this.password !== _this.password2) error += "Пароли не совпадают";
	    return error;
	  };
	};

/***/ },
/* 65 */
/***/ function(module, exports) {

	"use strict";

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var ProfileContact = function ProfileContact() {
	    _classCallCheck(this, ProfileContact);

	    this.contactEmails = [''];
	    this.contactPhones = [''];
	};

	var profileCtrl = function () {
	    function profileCtrl($scope) {
	        _classCallCheck(this, profileCtrl);

	        if (!$scope.$parent.db.user) $scope.$parent.redirectToUrl('/403', true);else this.contact = new ProfileContact();
	    }

	    _createClass(profileCtrl, [{
	        key: 'updateProfile',
	        value: function updateProfile() {}
	    }, {
	        key: 'addContacts',
	        value: function addContacts($event, type) {
	            var arr;
	            if (type === 'email') arr = this.contact.contactEmails;else if (type === 'phone') arr = this.contact.contactPhones;else return;

	            if (arr.length < 5 && arr[arr.length - 1].trim()) arr.push('');
	        }
	    }, {
	        key: 'deleteContacts',
	        value: function deleteContacts($event, $index, type) {
	            if (type === 'email') this.contact.contactEmails.splice($index, 1);else if (type === 'phone') this.contact.contactPhones.splice($index, 1);else return;
	        }
	    }]);

	    return profileCtrl;
	}();

		module.exports = profileCtrl;

/***/ },
/* 66 */
/***/ function(module, exports) {

	"use strict";

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var ProfileContact = function ProfileContact() {
	    _classCallCheck(this, ProfileContact);

	    this.contactEmails = [''];
	    this.contactPhones = [''];
	};

	var profileCtrl = function () {
	    function profileCtrl($scope) {
	        _classCallCheck(this, profileCtrl);

	        if (!$scope.$parent.db.user) $scope.$parent.redirectToUrl('/403', true);else this.contact = new ProfileContact();
	    }

	    _createClass(profileCtrl, [{
	        key: 'updateProfile',
	        value: function updateProfile() {}
	    }, {
	        key: 'addContacts',
	        value: function addContacts($event, type) {
	            var arr;
	            if (type === 'email') arr = this.contact.contactEmails;else if (type === 'phone') arr = this.contact.contactPhones;else return;

	            if (arr.length < 5 && arr[arr.length - 1].trim()) arr.push('');
	        }
	    }, {
	        key: 'deleteContacts',
	        value: function deleteContacts($event, $index, type) {
	            if (type === 'email') this.contact.contactEmails.splice($index, 1);else if (type === 'phone') this.contact.contactPhones.splice($index, 1);else return;
	        }
	    }]);

	    return profileCtrl;
	}();

		module.exports = profileCtrl;

/***/ },
/* 67 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope) {};

/***/ },
/* 68 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 69 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	/* Контроллер для управления  основным скелетом документа */

	module.exports = function ($http, $scope, $location, $timeout, $cookies, $cookieStore) {
	  var _this = this;

	  console.log('Main controller loaded');

	  /* Standalone module for bd */
	  $scope.db = __webpack_require__(70);
	  $scope.db.init($http);
	  window.db = $scope.db;

	  /* Initialize data */
	  this.init = function () {
	    /* variables for testing */
	    this.hello = "hi";
	    this.boolean = true;
	    this.list = [1, 2, 3];

	    console.log("Main controller init");

	    this.sortingCategories = __webpack_require__(73).items;
	    this.currentCategory = "None";
	    this.sortingId = 0;

	    if (this.sortingCategories.length) {
	      var title = this.sortingCategories[this.sortingId].title;
	      var arr = title.split("");
	      this.arrow = arr.pop();
	      arr.pop();

	      this.currentCategory = arr.join("");
	    } else console.error(new Error("No sorting options found"));

	    this.showingCategories = false;
	    this.settingCat = true;
	  };

	  this.showCategories = function () {
	    _this.settingCat = true;
	    _this.settingCat = true;
	    $timeout(function () {
	      _this.settingCat = false;
	    }, 250);

	    _this.showingCategories = true;
	  };

	  /* Sorting in header */
	  this.setCategory = function (id) {
	    _this.settingCat = false;

	    var res = _this.sortingCategories.filter(function (el) {
	      return el.id === id | 0;
	    })[0];
	    _this.sortingId = id;

	    if (res) {
	      var arr = res.title.split("");
	      _this.arrow = arr.pop();
	      arr.pop();

	      _this.currentCategory = arr.join("");
	    }
	  };

	  this.logout = function () {
	    db.userLogout();
	    $scope.redirectToUrl('/');
	  };

	  /* Watch all click on the body */
	  this.click = function () {
	    if (_this.showingCategories && !_this.settingCat) _this.showingCategories = false;
	  };

	  /* Correct redirect to url through app router*/
	  $scope.redirectToUrl = function (url, immediate) {
	    if (immediate) $location.path(url);else $timeout(function () {
	      $location.path(url);
	    }, 250);
	  };

	  /* Use this method for global purpose errors */
	  $scope.displayError = function (text) {
	    alert(text);
	    console.error(new Error("text"));
	  };
		};

/***/ },
/* 70 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var utils = __webpack_require__(71),
	    config = __webpack_require__(72);

	var ctx = module.exports = {};

	module.exports.init = function ($http) {
	  /* init data from database here */
	  ctx.setDefaults();
	  this.transport = $http;
	  console.log(this.transport);
	  // ctx.checkUserIsLogged(function(err, data) {
	  //   if(err) console.error(err)
	  //   else {
	  //     if(data) ctx.saveUserData(data)
	  //     else console.log("User is not logged in")
	  //   }
	  // }.bind(this))

	  console.log("Database initialized");
	};

	module.exports.setDefaults = function () {
	  console.log("Database :: defaults set");

	  ctx.favourites = null;
	  ctx.mailbox = null;
	  ctx.user = null;
	  ctx.notifications = { hello: "preved" };
	};

	module.exports.checkEmail = function (email, cb) {
	  utils.request({
	    "method": config.routes.checkEmail.method,
	    "url": config.api.auth + config.routes.checkEmail.url,
	    "data": email,
	    "headers": {
	      "Content-Type": "text/plain"
	    }
	  }).then(function (data) {
	    return cb(null, data);
	  }, function (err) {
	    return cb(err);
	  });
	};

	module.exports.login = function (data, cb) {
	  // utils.request({
	  //   "method" : config.routes.login.method,
	  //   "url" : config.api.auth + config.routes.login.url,
	  //   "data" : data,
	  //   "headers" : {
	  //     "Content-Type" : "application/json",
	  //     "withCredentials" : "true"
	  //   }
	  // }).then(data => cb(null, data), err => cb(err))
	  ctx.transport({
	    method: config.routes.login.method,
	    url: config.api.auth + config.routes.login.url,
	    data: data,
	    headers: {
	      "Content-Type": "application/json"
	    },
	    withCredentials: true
	  }).then(function (data) {
	    return cb(null, data);
	  }).catch(cb);
	};

	/* This method does saves user data in this module only, no backend communication */
	module.exports.saveUserData = function (data) {
	  data = data.length ? JSON.parse(data) : "";
	  this.user = {};
	  /* TODO: распарсить данные в осмысленные переменные */

	  console.log("Database:: User data saved successfully( шутка ) ");
	};

	module.exports.checkUserIsLogged = function (cb) {
	  utils.request({
	    "method": config.routes.checkLogged.method,
	    "url": config.api.url + config.routes.checkLogged.url
	  }).then(function (data) {
	    return cb(null, data);
	  }, function (err) {
	    return cb(err);
	  });
	};

	module.exports.userLogout = function () {
	  ctx.setDefaults();

	  utils.request({
	    "method": config.routes.logout.method,
	    "url": config.api.auth + config.routes.logout.url
	  }).then(function () {}, function () {});
		};

/***/ },
/* 71 */
/***/ function(module, exports) {

	'use strict';
	/*
	  Expect options object like this:
	  {
	    "method" : "POST",
	    "url" : "http://someurl.com/",
	    "data" : "data",
	    "headers" : {
	      "Content-Type" : "application/json",
	      "Content-Length" : "1023"
	    }
	  }

	  DEFAULTS:
	  Method - default is GET,
	  URL - required,
	  data - optional,
	  headers - optional
	*/

	module.exports.request = function (options) {
	  return new Promise(function (resolve, reject) {
	    /* Setting defaults */
	    var _options$method = options.method;
	    var method = _options$method === undefined ? "GET" : _options$method;
	    var url = options.url;
	    var data = options.data;
	    var headers = options.headers;

	    /* Some validation */

	    if (!url) return console.error("Url not specified");

	    if ((method == "POST" || method == "PUT") && !data) return console.error("Nothing to send here =)");

	    /* Start constructing request */
	    var xhr = new XMLHttpRequest();
	    xhr.open(method, url, true);

	    if (headers) for (var prop in headers) {
	      xhr.setRequestHeader(prop, headers[prop]);
	    }if (data && headers['Content-Type'] !== "text/plain") xhr.send(JSON.stringify(data));else if (data) xhr.send(data);else xhr.send();

	    xhr.onreadystatechange = function () {
	      if (this.readyState != 4) return;

	      if (this.status != 200) return reject('Error: ' + (this.status ? "(" + this.status + ") " + this.statusText : 'request fail'));else return resolve(this.responseText);
	    };
	  });
	};

/***/ },
/* 72 */
/***/ function(module, exports) {

	module.exports = {
		"api": {
			"url": "http://93.73.109.38:8084/",
			"auth": "http://93.73.109.38:8083/"
		},
		"routes": {
			"getBulletins": {
				"method": "POST",
				"url": "api/rest/offersService/offer/read/all",
				"accept": {
					"skip": 0,
					"limit": 20
				}
			},
			"login": {
				"method": "POST",
				"url": "login",
				"accept": {
					"email": "sss2@gmail.com",
					"password": "123456"
				}
			},
			"logout": {
				"method": "POST",
				"url": "logout"
			},
			"register": {
				"method": "POST",
				"url": "register",
				"accept": {
					"email": "sss2@gmail.com",
					"password": "123456"
				}
			},
			"checkEmail": {
				"method": "POST",
				"url": "login/checkEmail",
				"accept": {
					"email": "sss2@gmail.com"
				}
			},
			"checkLogged": {
				"method": "GET",
				"url": "api/rest/profilesService/profile/read/loggedInProfile"
			}
		}
	};

/***/ },
/* 73 */
/***/ function(module, exports) {

	module.exports = {
		"items": [
			{
				"id": 1,
				"title": "цена ▾",
				"requestCriteria": ""
			},
			{
				"id": 2,
				"title": "цена ▴",
				"requestCriteria": ""
			},
			{
				"id": 3,
				"title": "дата  ▾",
				"requestCriteria": ""
			},
			{
				"id": 4,
				"title": "дата ▴",
				"requestCriteria": ""
			},
			{
				"id": 5,
				"title": "рейтинг ▾",
				"requestCriteria": ""
			},
			{
				"id": 6,
				"title": "рейтинг ▴",
				"requestCriteria": ""
			}
		]
	};

/***/ },
/* 74 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var MODULES = {
	  "checkbox": __webpack_require__(75),
	  "niceButton": __webpack_require__(76),
	  "text": __webpack_require__(77),
	  "selectBox": __webpack_require__(78)
	};

	window.ee = __webpack_require__(79);
	ee.init();

	var ctx = module.exports = {};

	module.exports.init = function (app) {
	  for (var key in MODULES) {
	    app.directive(key, MODULES[key]);
	  }return app;
		};

/***/ },
/* 75 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {
	  return {
	    restrict: "E",
	    require: '^ngModel',
	    scope: {
	      ngModel: "="
	    },
	    template: '<div class="checkBox"></div>',
	    replace: true,
	    controller: function controller($scope, $element) {
	      var el = $element[0];
	      //.getElementsByClassName('checkBox')[0]

	      if ($scope.ngModel && !el.classList.contains('checked')) el.classList.add('checked');else if (!$scope.ngModel && el.classList.contains('checked')) el.classList.remove('cheked');

	      el.addEventListener('click', function (e) {
	        el.classList.toggle('checked');
	        $scope.ngModel = $scope.ngModel ? false : true;
	      });
	    }
	  };
		};

/***/ },
/* 76 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {
	  return {
	    restrict: "E",
	    transclude: true,
	    scope: {
	      class: "@",
	      ngClick: "&"
	    },
	    replace: true,
	    template: "<div class=\"{{ class }}\">\n                  <span class=\"ink\"></span>\n                  <ng-transclude style=\"display:block; width:100%; height:inherit;\"></ng-transclude>\n                </div>",
	    controller: function controller($scope, $element) {
	      var onClick = function onClick(e) {
	        var ink = this.getElementsByClassName('ink')[0];
	        ink.classList.remove('animate');

	        var rect = this.getBoundingClientRect();

	        if (!ink.clientHeight && !ink.clientWidth) {
	          var d = Math.max(this.clientWidth, this.clientHeight);
	          ink.style.height = ink.style.width = d + "px";
	        }

	        ink.style.top = e.clientY - rect.top - ink.clientHeight / 2 + "px";
	        ink.style.left = e.clientX - rect.left - ink.clientWidth / 2 + "px";
	        ink.classList.add('animate');
	      };

	      if ($scope.ngClick) $element[0].addEventListener('click', $scope.ngClick);

	      $element[0].addEventListener('click', onClick);
	    }
	  };
		};

/***/ },
/* 77 */
/***/ function(module, exports) {

	'use strict';

	var COLORS = {
	  blue: "#1875D0",
	  white: "white"
	};

	module.exports = function () {
	  return {
	    restrict: "E",
	    require: '^ngModel',
	    scope: {
	      label: "@",
	      ngModel: "=",
	      color: "@",
	      type: "@",
	      validate: "=",
	      isValid: "="
	    },
	    replace: true,
	    template: "<div class=\"inputForm\">\n                 <label>{{ label }}</label>\n                 <input type=\"{{ type || 'text'}}\" ng-model=\"ngModel\">\n                 <div class=\"errors\"></div>\n               </div>",
	    controller: function controller($scope, $element, $timeout) {
	      var id = ee.on('form-submit', validate);
	      $scope.$on("$destroy", function () {
	        ee.off(id);
	      }.bind(this));

	      var defaultBorder = "";

	      var el = $element[0].getElementsByTagName('input')[0],
	          label = $element[0].getElementsByTagName('label')[0],
	          error = $element[0].getElementsByClassName('errors')[0];

	      function validate() {
	        if ($scope.validate) {
	          (function () {
	            var handle = function handle(error) {
	              if (typeof $scope.isValid !== "undefined") {
	                if (error.length) $scope.isValid = false;else $scope.isValid = true;

	                $scope.$apply();
	              }
	            };

	            var resp = $scope.validate(el.value);

	            if (typeof resp === "string") handle(error.innerHTML = resp);else resp.then(function (data) {
	              error.innerHTML = data;
	              handle(data);
	            }, console.error);
	          })();
	        }
	      }

	      function onBlur(e) {
	        if (!$scope.ngModel.length) hideAnimation();

	        validate();
	      }

	      function onFocus(e) {
	        if (!$scope.ngModel.length) displayAnimation();
	      }

	      function displayAnimation() {
	        label.style.color = COLORS[$scope.color];
	        if (!defaultBorder.length) {
	          defaultBorder = window.getComputedStyle(label.parentNode).borderBottom;
	        } else {
	          label.parentNode.style.borderBottom = "2px solid " + COLORS[$scope.color];
	        }

	        label.classList.add('textOut');
	      }

	      function hideAnimation() {
	        label.style.color = "";
	        label.parentNode.style.borderBottom = defaultBorder;
	        label.classList.remove('textOut');
	      }

	      $timeout(function () {
	        if ($scope.ngModel && $scope.ngModel.length) displayAnimation();else hideAnimation();
	      }, 250);

	      el.addEventListener('blur', onBlur.bind(this));
	      el.addEventListener('focus', onFocus.bind($scope));
	    }
	  };
		};

/***/ },
/* 78 */
/***/ function(module, exports) {

	"use strict";

	module.exports = function () {
	  return {
	    restrict: "E",
	    require: '^ngModel',
	    scope: {
	      ngModel: "=",
	      show: "@"
	    },
	    template: "<div class=\"selectBox\">\n                <div class=\"defaultValue\" ng-hide=\"show\">\n                  <p>{{ ngModel }}</p>\n                </div>\n                <div class=\"listOfValues\" ng-show=\"show\">\n                  <div class=\"listItem\" ng-repeat=\"item in items\" value=\"{{item}}\">{{ item }}</div>\n                </div>\n              </div>",
	    replace: true,
	    controller: function controller($scope, $element, $timeout) {
	      var defaultVal = $element[0].getElementsByClassName('defaultValue')[0],
	          listOfValues = $element[0].getElementsByClassName('listOfValues')[0];

	      $timeout(function () {
	        defaultVal.addEventListener('click', function (e) {
	          this.show = true;

	          document.addEventListener('click', handler);
	        }.bind($scope));

	        function handler(e) {
	          if (!(e.target == listOfValues || e.target.parentNode == listOfValues || e.target == defaultVal || e.target.parentNode == defaultVal)) {
	            $scope.show = false;
	            $scope.$apply();
	            document.removeEventListener('click', handler);
	          }
	        }

	        for (var t = 0; t < listOfValues.children.length; t++) {

	          listOfValues.children[t].addEventListener('click', function (e) {
	            document.removeEventListener('click', handler);
	            this.show = false;
	            this.ngModel = e.target.innerHTML;
	          }.bind($scope));
	        }
	      }.bind(this), 1000);
	    },
	    link: function link(scope, element, attrs) {
	      scope.items = JSON.parse(attrs.items);
	    }
	  };
		};

/***/ },
/* 79 */
/***/ function(module, exports) {

	'use strict';

	var ctx = module.exports = {};

	var privateScope = {};

	module.exports.init = function () {
	  privateScope.eventHandlers = {};
	  privateScope.handlerId = 0;

	  privateScope.getHandlerId = function () {
	    return privateScope.handlerId++;
	  };

	  privateScope.registerHandler = function (name, handler) {
	    if (!privateScope.eventHandlers[name]) privateScope.eventHandlers[name] = [];

	    var id = privateScope.getHandlerId();
	    privateScope.eventHandlers[name].push({
	      id: id,
	      handler: handler
	    });

	    return id;
	  };

	  privateScope.handle = function (eventName, data) {
	    if (privateScope.eventHandlers[eventName]) privateScope.eventHandlers[eventName].forEach(function (h) {
	      return h.handler(data);
	    });
	  };

	  privateScope.removeHandler = function (id) {
	    for (var key in privateScope.eventHandlers) {
	      for (var t = 0; t < privateScope.eventHandlers[key].length; t++) {
	        if (privateScope.eventHandlers[key][t].id == id) {
	          privateScope.eventHandlers[key] = privateScope.eventHandlers[key].filter(function (el) {
	            return el.id !== id;
	          });
	          return true;
	        }
	      }
	    }

	    return false;
	  };

	  privateScope.removeHandlerByName = function (name) {
	    privateScope.eventHandlers[name] = [];
	  };
	};

	module.exports.on = function (eventName, handler) {
	  return privateScope.registerHandler(eventName, handler);
	};

	/* Removes handler by id*/
	module.exports.off = function (id) {
	  return privateScope.removeHandler(id);
	};

	/* Removes all handlers by event name */
	module.exports.remove = function (name) {
	  return privateScope.removeHandlerByName(name);
	};
	/*
	  {
	    "name" : "form-submit",
	    "data" : "whatever" <== optional
	  }
	*/
	module.exports.emit = function (event) {
	  var name = event.name || function () {
	    throw new Error("No event name");
	  }();
	  var data = event.data || null;

	  privateScope.handle(name, data);
	};

/***/ },
/* 80 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/rupr.png";

/***/ },
/* 81 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/ruprHover.png";

/***/ }
/******/ ]);
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXBwLmpzIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vL3dlYnBhY2svYm9vdHN0cmFwIGU1MzdmZjIwMzYxNDU4Y2M1MWU1Iiwid2VicGFjazovLy9hcHAuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Jhc2ljLnNjc3M/NGY1MyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvYmFzaWMuc2NzcyIsIndlYnBhY2s6Ly8vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzIiwid2VicGFjazovLy8uL2ltYWdlcy9sb2dvLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvbWFpbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL21haWxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYmVsbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvc2VydmljZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy91c2VyTmFtZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL25leHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9wcmV2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXJyb3JfYmcucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy92ay5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2ZhY2Vib29rLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZ29vZ2xlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvY2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9tYXAucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9Gb3JBbmltYWxzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQnVzaW5lc3MucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL1RyYW5zcG9ydC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvSXNGcmVlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9CYXJ0ZXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nIiwid2VicGFjazovLy8uL34vc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qcyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzP2NmMjciLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Zhdm91cml0ZXMuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYnVsbGV0aW4tZGVmYXVsdC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL1YucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9leGNsYW1hdGlvblBvaW50LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZG9sbGFyQmlsbC5wbmciLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2VkaXQtcHJvZmlsZS5zY3NzP2RjMmIiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2VkaXQtcHJvZmlsZS5zY3NzIiwid2VicGFjazovLy8uL2ltYWdlcy9pY29uX2Nsb3NlX2JsdWUucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9hdmF0YXIuanBnIiwid2VicGFjazovLy8uL3N0eWxlcy9wcm9maWxlLnNjc3M/MDFhMSIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvcHJvZmlsZS5zY3NzIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldF9ibGFjay5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2ljb25fdXNlci5wbmciLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbG9nZ2VyLmpzIiwid2VicGFjazovLy8uL2RhdGEvZGVidWcuanNvbiIsIndlYnBhY2s6Ly8vbW9kdWxlcy9yb3V0ZXIuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2luZGV4LmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvYnVsbGV0aW5BZGQuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2xvZ2luLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9yZWdpc3Rlci5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL3NlYXJjaFJlc3VsdHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL21haW4uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvZGIuanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvdXRpbHMuanMiLCJ3ZWJwYWNrOi8vLy4vZGF0YS9jb25maWcuanNvbiIsIndlYnBhY2s6Ly8vLi9kYXRhL3NvcnRpbmcuanNvbiIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvaW5kZXguanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvY2hlY2tib3guanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvbmljZUJ1dHRvbi5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL3NlbGVjdEJveC5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZXZlbnRzLmpzIiwid2VicGFjazovLy8uL2ltYWdlcy9ydXByLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcnVwckhvdmVyLnBuZyJdLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSlcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcblxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0ZXhwb3J0czoge30sXG4gXHRcdFx0aWQ6IG1vZHVsZUlkLFxuIFx0XHRcdGxvYWRlZDogZmFsc2VcbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubG9hZGVkID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCJcIjtcblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXygwKTtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHdlYnBhY2svYm9vdHN0cmFwIGU1MzdmZjIwMzYxNDU4Y2M1MWU1XG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbnJlcXVpcmUoXCIuL3N0eWxlcy9iYXNpYy5zY3NzXCIpXG5yZXF1aXJlKFwiLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXCIpXG5yZXF1aXJlKFwiLi9zdHlsZXMvZWRpdC1wcm9maWxlLnNjc3NcIilcbnJlcXVpcmUoXCIuL3N0eWxlcy9wcm9maWxlLnNjc3NcIilcblxucmVxdWlyZShcIi4vbW9kdWxlcy9sb2dnZXJcIikoKVxuXG5jb25zdCBtYXRlcmlhbHMgPSByZXF1aXJlKCcuL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzJyksXG4gICAgICByb3V0ZXIgPSByZXF1aXJlKCcuL21vZHVsZXMvcm91dGVyJylcblxubGV0IGFwcCA9IGFuZ3VsYXIubW9kdWxlKCdndXAnLCBbJ25nUm91dGUnLCAnbmdDb29raWVzJ10pXG5cbi8vIEFwcCBjb25maWdcbmFwcFxuICAuY29uZmlnKFsnJHJvdXRlUHJvdmlkZXInLCAnJGxvY2F0aW9uUHJvdmlkZXInLCAnJGh0dHBQcm92aWRlcicsIGZ1bmN0aW9uKCAkcm91dGVQcm92aWRlciwgJGxvY2F0aW9uUHJvdmlkZXIsICRodHRwUHJvdmlkZXIpe1xuICAgICRodHRwUHJvdmlkZXIuZGVmYXVsdHMudXNlWERvbWFpbiA9IHRydWU7XG4gICAgZGVsZXRlICRodHRwUHJvdmlkZXIuZGVmYXVsdHMuaGVhZGVycy5jb21tb25bJ1gtUmVxdWVzdGVkLVdpdGgnXTtcbiAgICBcbiAgICBmb3IobGV0IGtleSBpbiByb3V0ZXIpXG4gICAgICAkcm91dGVQcm92aWRlci53aGVuKGtleSwgcm91dGVyW2tleV0pXG5cbiAgICAkcm91dGVQcm92aWRlci5vdGhlcndpc2Uoe1xuICAgICAgcmVkaXJlY3RUbzogJy80MDQnXG4gICAgfSlcblxuICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh7XG4gICAgICBlbmFibGVkIDogdHJ1ZSxcbiAgICAgIHJlcXVpcmVCYXNlIDogZmFsc2VcbiAgICB9KVxuICB9XSlcbiAgLmNvbnRyb2xsZXIoJ21haW5DdHJsJywgWyBcIiRodHRwXCIsIFwiJHNjb3BlXCIsIFwiJGxvY2F0aW9uXCIsIFwiJHRpbWVvdXRcIiwgXCIkY29va2llc1wiLCBcIiRjb29raWVTdG9yZVwiLCByZXF1aXJlKCcuL2NvbnRyb2xsZXJzL21haW4nKV0pXG5cbm1hdGVyaWFsc1xuICAuaW5pdChhcHApXG4gIC5ydW4oKVxuXG4gIC8qIEV2ZW50IGVtbWl0dGVyIGV4YW1wbGVzICovXG4gIGxldCBpZCA9IGVlLm9uKCdtdWhhaGFoYScsIGZ1bmN0aW9uKGRhdGEpIHtcbiAgICBjb25zb2xlLmxvZyhcImJ1Z2FnYXNoZWNoa29cIilcbiAgICBjb25zb2xlLmxvZyhkYXRhKVxuICB9KVxuXG4gIGVlLmVtaXQoe1xuICAgIG5hbWUgOiBcIm11aGFoYWhhXCIsXG4gICAgZGF0YSA6IFsxLDIsMyw0LDVdXG4gIH0pXG5cbiAgZWUub2ZmKGlkKVxuXG4gIGVlLmVtaXQoe1xuICAgIG5hbWUgOiBcIm11aGFoYWhhXCIsXG4gICAgZGF0YSA6IFsxLDIsMyw0LDVdXG4gIH0pXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBhcHAuanNcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Jhc2ljLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vYmFzaWMuc2Nzc1wiLCBmdW5jdGlvbigpIHtcblx0XHRcdHZhciBuZXdDb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Jhc2ljLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvYmFzaWMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwiYm9keSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRUNFQ0VDOyB9XFxuXFxuaGVhZGVyIHtcXG4gIGhlaWdodDogNzBweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMTg3NUQwOyB9XFxuXFxuLmJ0bi1ibHVlLCAuYnRuLWdyZXkge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gIGJvcmRlci1yYWRpdXM6IDVweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICB0ZXh0LWFsaWduOiBjZW50ZXI7IH1cXG5cXG4uYnRuLWdyZXkge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0Q4RDhEODtcXG4gIGNvbG9yOiAjODY4Njg2O1xcbiAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bzsgfVxcblxcbi5jb250YWluZXIge1xcbiAgd2lkdGg6IDEyODBweDtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIG1hcmdpbjogMCBhdXRvOyB9XFxuXFxuLmNsZWFyZml4OmJlZm9yZSwgLmNsZWFyZml4OmFmdGVyIHtcXG4gIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gIGRpc3BsYXk6IHRhYmxlOyB9XFxuXFxuLmNsZWFyZml4OmFmdGVyIHtcXG4gIGNsZWFyOiBib3RoOyB9XFxuXFxuLmluayB7XFxuICBkaXNwbGF5OiBibG9jaztcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIGJhY2tncm91bmQ6IHJnYmEoMCwgMCwgMCwgMC4xNSk7XFxuICBib3JkZXItcmFkaXVzOiAxMDAlO1xcbiAgLXdlYmtpdC10cmFuc2Zvcm06IHNjYWxlKDApO1xcbiAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDApOyB9XFxuXFxuLmluay5hbmltYXRlIHtcXG4gIC13ZWJraXQtYW5pbWF0aW9uOiByaXBwbGUgLjVzIGVhc2UtaW47XFxuICAgICAgICAgIGFuaW1hdGlvbjogcmlwcGxlIC41cyBlYXNlLWluOyB9XFxuXFxuQC13ZWJraXQta2V5ZnJhbWVzIHJpcHBsZSB7XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMDtcXG4gICAgLXdlYmtpdC10cmFuc2Zvcm06IHNjYWxlKDIuNSk7XFxuICAgICAgICAgICAgdHJhbnNmb3JtOiBzY2FsZSgyLjUpOyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIHJpcHBsZSB7XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMDtcXG4gICAgLXdlYmtpdC10cmFuc2Zvcm06IHNjYWxlKDIuNSk7XFxuICAgICAgICAgICAgdHJhbnNmb3JtOiBzY2FsZSgyLjUpOyB9IH1cXG5cXG4uaGVhZExlZnQge1xcbiAgcGFkZGluZy10b3A6IDVweDtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIHdpZHRoOiBjYWxjKDEwMCUgLSA0OTBweCk7XFxuICBoZWlnaHQ6IDcwcHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAuaGVhZExlZnQgPiAubG9nbyB7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgYm9yZGVyLXJhZGl1czogNTAlO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgaGVpZ2h0OiA2MHB4O1xcbiAgICB3aWR0aDogNjBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9sb2dvLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgIG1hcmdpbi1sZWZ0OiAxNXB4OyB9XFxuICAuaGVhZExlZnQgPiAuaW5wdXRGb3JtIHtcXG4gICAgbWFyZ2luLXRvcDogMjFweDtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBib3JkZXItY29sb3I6ICNGREZERkQ7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgIGhlaWdodDogYXV0bztcXG4gICAgd2lkdGg6IDIwMHB4O1xcbiAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0ZERkRGRDsgfVxcbiAgICAuaGVhZExlZnQgPiAuaW5wdXRGb3JtID4gbGFiZWwsIC5oZWFkTGVmdCA+IC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxNHB4IFJvYm90bzsgfVxcbiAgLmhlYWRMZWZ0ID4gLnNlbGVjdEJveCB7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tdG9wOiAyMXB4O1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDsgfVxcbiAgICAuaGVhZExlZnQgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgYmFja2dyb3VuZDogbm9uZTtcXG4gICAgICBwYWRkaW5nOiAwIDVweDsgfVxcbiAgLmhlYWRMZWZ0ID4gLmFkZCB7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAyMDBweDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyMHB4O1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2FkZC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCAxMHB4O1xcbiAgICBwYWRkaW5nLWxlZnQ6IDEwcHg7XFxuICAgIHRleHQtYWxpZ246IGxlZnQ7XFxuICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgIGJvcmRlcjogMXB4IHNvbGlkIHdoaXRlO1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMTVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjE1czsgfVxcbiAgICAuaGVhZExlZnQgPiAuYWRkID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDE0cHggLyAzNXB4IFJvYm90bzsgfVxcblxcbi5oZWFkUmlnaHQge1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgd2lkdGg6IDQ5MHB4O1xcbiAgaGVpZ2h0OiA3MHB4O1xcbiAgYm9yZGVyLWxlZnQ6IDFweCBzb2xpZCBncmV5O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIHBhZGRpbmctdG9wOiAyMnB4OyB9XFxuICAuaGVhZFJpZ2h0ID4gLm1haWwge1xcbiAgICBoZWlnaHQ6IDI2cHg7XFxuICAgIHdpZHRoOiAzM3B4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbWFpbC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIG1hcmdpbi1sZWZ0OiAyMHB4O1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLm1haWwgPiBwIHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTBweCAvIDE1cHggUm9ib3RvO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBib3R0b206IC0zcHg7XFxuICAgICAgbGVmdDogMzJweDtcXG4gICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5tYWlsOmhvdmVyIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL21haWxfc2hhZG93LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjsgfVxcbiAgLmhlYWRSaWdodCA+IC5iZWxsIHtcXG4gICAgaGVpZ2h0OiAyNHB4O1xcbiAgICB3aWR0aDogMjNweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIG1hcmdpbi1sZWZ0OiAzMHB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2JlbGwucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgICBib3JkZXItcmFkaXVzOiAxNXB4IDAgMTVweCAxMHB4O1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLmJlbGwgPiBwIHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTBweCAvIDE1cHggUm9ib3RvO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBib3R0b206IC03cHg7XFxuICAgICAgbGVmdDogMjJweDtcXG4gICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5iZWxsOmhvdmVyIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjsgfVxcbiAgLmhlYWRSaWdodCA+IC5zZXJ2aWNlcyB7XFxuICAgIGhlaWdodDogMjdweDtcXG4gICAgd2lkdGg6IDI4cHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDMwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvc2VydmljZXMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzOmhvdmVyIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3NlcnZpY2VzX3NoYWRvdy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7IH1cXG4gIC5oZWFkUmlnaHQgPiAudXNlck5hbWUge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMjVweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy91c2VyTmFtZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0O1xcbiAgICBwYWRkaW5nLWxlZnQ6IDMwcHg7XFxuICAgIG1heC13aWR0aDogMjcwcHg7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDE0cHggLyAyN3B4IFJvYm90bztcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgd2hpdGUtc3BhY2U6IG5vd3JhcDtcXG4gICAgICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgICAgIHRleHQtb3ZlcmZsb3c6IGVsbGlwc2lzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBkaXYge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICAgICAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgdG9wOiAwO1xcbiAgICAgIHdpZHRoOiAxMDAlO1xcbiAgICAgIHotaW5kZXg6IDE7IH1cXG4gICAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gZGl2ID4gcCB7XFxuICAgICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgICBwYWRkaW5nOiAwIDE1cHg7XFxuICAgICAgICBoZWlnaHQ6IDQ4cHg7XFxuICAgICAgICBmb250OiA0MDAgMTZweCAvIDUwcHggUm9ib3RvO1xcbiAgICAgICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gZGl2ID4gcDpob3ZlciB7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNlZWVlZWU7IH1cXG4gIC5oZWFkUmlnaHQgPiAuYXV0aCB7XFxuICAgIGNvbG9yOiB3aGl0ZTtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDIwcHg7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjZweCBSb2JvdG87IH1cXG4gICAgLmhlYWRSaWdodCA+IC5hdXRoIHNwYW4ge1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICBtYXJnaW46IDAgMTBweDsgfVxcblxcbi5pbnB1dFNlYXJjaCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkIHdoaXRlOyB9XFxuICAuaW5wdXRTZWFyY2ggPiBpbnB1dCB7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgcGFkZGluZzogMnB4IDA7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50O1xcbiAgICBvdXRsaW5lOiBub25lO1xcbiAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bzsgfVxcbiAgLmlucHV0U2VhcmNoID4gbGFiZWwge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHRvcDogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgY29sb3I6ICNmZmZmZmY7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87XFxuICAgIGN1cnNvcjogdGV4dDtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiAuNXM7XFxuICAgIHRyYW5zaXRpb246IC41czsgfVxcblxcbi5zZWxlY3RCb3gge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE5cHggUm9ib3RvO1xcbiAgICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgd2hpdGU7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCA1cHg7XFxuICAgIHBhZGRpbmctcmlnaHQ6IDIwcHg7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUgc3BhbiB7XFxuICAgICAgZm9udC1zaXplOiAxNnB4OyB9XFxuICAuc2VsZWN0Qm94ID4gLmxpc3RPZlZhbHVlcyB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgdG9wOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gICAgei1pbmRleDogMTtcXG4gICAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gICAgLXdlYmtpdC1hbmltYXRpb246IGFuaW1hdGV0b3AgLjI1cztcXG4gICAgICAgICAgICBhbmltYXRpb246IGFuaW1hdGV0b3AgLjI1czsgfVxcbiAgICAuc2VsZWN0Qm94ID4gLmxpc3RPZlZhbHVlcyA+IGRpdiB7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHBhZGRpbmc6IDAgMTVweDtcXG4gICAgICBoZWlnaHQ6IDUwcHg7XFxuICAgICAgd2lkdGg6IDEyMHB4O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IC8gNTBweCBSb2JvdG87XFxuICAgICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAgIC5zZWxlY3RCb3ggPiAubGlzdE9mVmFsdWVzID4gZGl2OmhvdmVyIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNlZWVlZWU7IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgYW5pbWF0ZXRvcCB7XFxuICAwJSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIGFuaW1hdGV0b3Age1xcbiAgMCUge1xcbiAgICBvcGFjaXR5OiAwOyB9XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuYXNpZGUuYnVsbGV0aW5EZXRhaWxzIHtcXG4gIGZsb2F0OiByaWdodDtcXG4gIG1hcmdpbi10b3A6IDVweDsgfVxcblxcbnNlY3Rpb24ub3BlbkFkdmVydGVydCB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gIGZsb2F0OiByaWdodDtcXG4gIG1hcmdpbi1yaWdodDogMTBweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICB3aWR0aDogNzE1cHg7XFxuICBwYWRkaW5nOiAyNXB4IDEwMHB4IDQ1cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgbWFyZ2luLXRvcDogNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBoMyB7XFxuICAgIGNvbG9yOiAjMjAyMDIwO1xcbiAgICBmb250OiA0MDAgMjJweCAvIDI2cHggUm9ib3RvO1xcbiAgICBmbG9hdDogbGVmdDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnByaWNlIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAxOHB4IC8gMjZweCBSb2JvdG87XFxuICAgIGZsb2F0OiByaWdodDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmNoZWNrQm94IHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDE1cHg7XFxuICAgIG1hcmdpbi10b3A6IC0xcHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5icmVhZENydW1icyB7XFxuICAgIGNvbG9yOiByZ2JhKDMxLCAzMSwgMzEsIDAuNTQpO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE0cHggUm9ib3RvO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuaWQge1xcbiAgICBjb2xvcjogcmdiYSgzMiwgMzIsIDMyLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNHB4IFJvYm90bztcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBjdXJzb3I6IGRlZmF1bHQ7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIge1xcbiAgICBoZWlnaHQ6IDEyMHB4O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgbWFyZ2luLWJvdHRvbTogMTBweDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gZGl2IHtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tbGVmdDogMTBweDtcXG4gICAgICBoZWlnaHQ6IDEwMCU7XFxuICAgICAgd2lkdGg6IDE2NXB4OyB9XFxuICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IGRpdjpudGgtb2YtdHlwZSgxKSB7XFxuICAgICAgICBtYXJnaW46IDA7IH1cXG4gICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gZGl2ID4gaW1nIHtcXG4gICAgICAgIC1vLW9iamVjdC1maXQ6IGNvbnRhaW47XFxuICAgICAgICAgICBvYmplY3QtZml0OiBjb250YWluO1xcbiAgICAgICAgd2lkdGg6IDEwMCU7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjRGNEY0O1xcbiAgICAgICAgaGVpZ2h0OiAxMDAlO1xcbiAgICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiAubmV4dCB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbmV4dC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgY2VudGVyO1xcbiAgICAgIGhlaWdodDogMTRweDtcXG4gICAgICB3aWR0aDogMTBweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgdG9wOiA1MCU7XFxuICAgICAgbWFyZ2luLXRvcDogLTdweDtcXG4gICAgICByaWdodDogLTI1cHg7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IC5wcmV2IHtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9wcmV2LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgcmlnaHQgY2VudGVyO1xcbiAgICAgIGhlaWdodDogMTRweDtcXG4gICAgICB3aWR0aDogMTBweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgdG9wOiA1MCU7XFxuICAgICAgbWFyZ2luLXRvcDogLTdweDtcXG4gICAgICBsZWZ0OiAtMjVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gI21hcEZvck9uZUFkdmVydGVydCB7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBoZWlnaHQ6IDIzMHB4O1xcbiAgICB3aWR0aDogMjI1cHg7XFxuICAgIG1hcmdpbi1ib3R0b206IDI1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cmFwRm9yRGl2IHtcXG4gICAgd2lkdGg6IDI2NXB4O1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgIG1hcmdpbi1ib3R0b206IDI1cHg7XFxuICAgIGJvcmRlcjogMXB4IHNvbGlkICNFOUU5RTk7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiB1bC50YWIge1xcbiAgICAgIGxpc3Qtc3R5bGUtdHlwZTogbm9uZTtcXG4gICAgICBoZWlnaHQ6IDUwcHg7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNFOUU5RTk7XFxuICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkge1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7IH1cXG4gICAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhIHtcXG4gICAgICAgICAgY29sb3I6ICM5MzkzOTM7XFxuICAgICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gNTBweCBSb2JvdG87XFxuICAgICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6YWZ0ZXIge1xcbiAgICAgICAgICAgIGNvbnRlbnQ6ICcnO1xcbiAgICAgICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgICBib3R0b206IDA7XFxuICAgICAgICAgICAgd2lkdGg6IDA7XFxuICAgICAgICAgICAgaGVpZ2h0OiAycHg7XFxuICAgICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0ZENTE1MTtcXG4gICAgICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhOmZvY3VzLCBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYS5hY3RpdmUge1xcbiAgICAgICAgICAgIGNvbG9yOiAjN2VhZmUxOyB9XFxuICAgICAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6Zm9jdXM6YWZ0ZXIsIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhLmFjdGl2ZTphZnRlciB7XFxuICAgICAgICAgICAgICB3aWR0aDogMTAwJTsgfVxcbiAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgxKSA+IGEge1xcbiAgICAgICAgICB3aWR0aDogMTU5cHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgxKSA+IGE6YWZ0ZXIge1xcbiAgICAgICAgICAgIHJpZ2h0OiAwOyB9XFxuICAgICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDIpID4gYSB7XFxuICAgICAgICAgIHdpZHRoOiAxMDRweDsgfVxcbiAgICAgICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDIpID4gYTphZnRlciB7XFxuICAgICAgICAgICAgbGVmdDogMDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3Mge1xcbiAgICAgIGhlaWdodDogMTc4cHg7XFxuICAgICAgd2lkdGg6IDI4MnB4O1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGNEY0RjQ7XFxuICAgICAgb3ZlcmZsb3c6IGF1dG87XFxuICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+IC50YWJjb250ZW50IHtcXG4gICAgICAgIGRpc3BsYXk6IG5vbmU7XFxuICAgICAgICAtd2Via2l0LWFuaW1hdGlvbjogZmFkZUVmZmVjdCAxcztcXG4gICAgICAgIGFuaW1hdGlvbjogZmFkZUVmZmVjdCAxczsgfVxcbiAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gLnRhYmNvbnRlbnQuYWN0aXZlIHtcXG4gICAgICAgICAgZGlzcGxheTogYmxvY2s7IH1cXG4gICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyB7XFxuICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2IHtcXG4gICAgICAgICAgcGFkZGluZzogMzBweCAyMHB4IDI1cHggNjVweDtcXG4gICAgICAgICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICAgICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0U5RTlFOTsgfVxcbiAgICAgICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyA+IGRpdjpudGgtbGFzdC1vZi10eXBlKDEpIHtcXG4gICAgICAgICAgICBib3JkZXI6IG5vbmU7IH1cXG4gICAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXYgPiBpbWcge1xcbiAgICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgICBoZWlnaHQ6IDMwcHg7XFxuICAgICAgICAgICAgd2lkdGg6IDI1cHg7XFxuICAgICAgICAgICAgdG9wOiAzNXB4O1xcbiAgICAgICAgICAgIGxlZnQ6IDIwcHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXYgPiBwIHtcXG4gICAgICAgICAgICBjb2xvcjogIzBkMGQxZTtcXG4gICAgICAgICAgICBmb250OiA0MDAgMTJweCAvIDE4cHggUm9ib3RvOyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuZGVzY3JpcHRpb25BZCB7XFxuICAgIGNvbG9yOiAjMGMwYzFlO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE4cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxNXB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAyMHB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuZ29Ub01hcCB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuYWxsQ29tbWVudHMge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogNTBweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyaXRlQVJldmlldyB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLm5hbWVVc2VyIHtcXG4gICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMThweCBSb2JvdG87XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICBtYXJnaW4tYm90dG9tOiAzNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAub25Pck9mZkxpbmVVc2VyIHtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCAuYnRuLWdyZXkge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE0MHB4O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDI1cHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgLmJ0bi1ibHVlIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgZmFkZUVmZmVjdCB7XFxuICBmcm9tIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgdG8ge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIGZhZGVFZmZlY3Qge1xcbiAgZnJvbSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIHRvIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuLmJ0bi1ibHVlIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMxODc1RDA7XFxuICBjb2xvcjogI2ZmZmZmZjtcXG4gIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87IH1cXG5cXG4ub25Pck9mZkxpbmVVc2VyIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMwMEM2NTI7XFxuICBib3JkZXI6IDFweCBzb2xpZCB3aGl0ZTtcXG4gIGJveC1zaGFkb3c6IDFweCAxcHggM3B4IDBweCByZ2JhKDAsIDAsIDAsIDAuNjUpO1xcbiAgaGVpZ2h0OiAxOHB4O1xcbiAgd2lkdGg6IDE4cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYm9yZGVyLXJhZGl1czogNTAlOyB9XFxuXFxuLmVycm9yLWNvbnRhaW5lciB7XFxuICB3aWR0aDogMTEwM3B4O1xcbiAgbWFyZ2luOiBhdXRvO1xcbiAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9lcnJvcl9iZy5wbmdcIikgKyBcIik7XFxuICBiYWNrZ3JvdW5kLXJlcGVhdDogbm8tcmVwZWF0O1xcbiAgYmFja2dyb3VuZC1wb3NpdGlvbi14OiByaWdodDtcXG4gIGJhY2tncm91bmQtcG9zaXRpb24teTogODBweDtcXG4gIG1pbi1oZWlnaHQ6IDUwMHB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgxIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjYuNHB4IFJvYm90bztcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBtYXJnaW4tdG9wOiA2NXB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgyIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjYuNHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTg5cHg7IH1cXG4gIC5lcnJvci1jb250YWluZXIgaDMge1xcbiAgICBjb2xvcjogcmdiYSgzMSwgMzEsIDMxLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNC40cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAzMHB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyID4gLmJ0bi1ibHVlIHtcXG4gICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuXFxuLmNsaWNrZWQge1xcbiAgYm94LXNoYWRvdzogMHB4IDBweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KSAhaW1wb3J0YW50O1xcbiAgbWFyZ2luLXRvcDogMTJweCAhaW1wb3J0YW50OyB9XFxuXFxuLm11bHRpcGxlIHtcXG4gIHdpZHRoOiAxMTUwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7IH1cXG4gIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCB7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICB3aWR0aDogMzgwcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiA1cHg7XFxuICAgIGhlaWdodDogMTY1cHg7XFxuICAgIHBhZGRpbmc6IDE1cHg7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDNuLTIpIHtcXG4gICAgICBtYXJnaW4tbGVmdDogMDsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQ6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNCQ0U2RTk7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDIpIHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjQkNFNkU5OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgzKSB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0JDRTZFOTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQgPiAuYnVsbGV0aW4tY2VudGVyLWNvbHVtbiB7XFxuICAgICAgZmxvYXQ6IG5vbmU7XFxuICAgICAgbWFyZ2luOiAwO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB0b3A6IDE1cHg7XFxuICAgICAgcmlnaHQ6IDExMHB4OyB9XFxuICAgICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0ID4gLmJ1bGxldGluLWNlbnRlci1jb2x1bW4gPiAuYnVsbGV0aW4tYWN0aW9uIHtcXG4gICAgICAgIGJvdHRvbTogLTQzcHg7XFxuICAgICAgICB3aWR0aDogMTEwcHg7XFxuICAgICAgICByaWdodDogLTMwcHg7XFxuICAgICAgICB6LWluZGV4OiAxOyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCA+IC5idWxsZXRpbi1sZWZ0LWNvbHVtbiA+IC5idWxsZXRpbi1kZXNjcmlwdGlvbiB7XFxuICAgICAgY29sb3I6ICMwYzBjMWU7XFxuICAgICAgZm9udDogNDAwIDEycHggLyAxOHB4IFJvYm90bztcXG4gICAgICB3aWR0aDogMTcwcHg7XFxuICAgICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgICBoZWlnaHQ6IDQwcHg7IH1cXG5cXG4ucmVkIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGNzRBNEE7IH1cXG5cXG4uaW5wdXRGb3JtLXJlcXVpcmVkIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIHBhZGRpbmc6IDZweCAycHg7XFxuICBmb250OiA0MDAgMTZweCAvIDI0LjhweCBSb2JvdG87XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5YjliOWI7XFxuICBtYXJnaW4tdG9wOiAxNXB4OyB9XFxuICAuaW5wdXRGb3JtLXJlcXVpcmVkIGxhYmVsIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBsZWZ0OiAycHg7XFxuICAgIHRvcDogNnB4O1xcbiAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gIC5pbnB1dEZvcm0tcmVxdWlyZWQgaW5wdXQge1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDsgfVxcblxcbi5pbnB1dEZvcm0ucmVxdWlyZWQge1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5YjliOWIgIWltcG9ydGFudDsgfVxcblxcbi5pbnB1dEZvcm0ge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJvcmRlci1ib3R0b206IDFweCBkb3R0ZWQgIzk5OTk5OTsgfVxcbiAgLmlucHV0Rm9ybSBsYWJlbCB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgbGVmdDogNXB4O1xcbiAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRvcDogLTFweDsgfVxcbiAgLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgb3V0bGluZTogbm9uZTtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIHBhZGRpbmc6IDFweCA1cHg7IH1cXG5cXG4vKiBUZXh0IGVsZW1lbnQgYW5pbWF0aW9uICovXFxuLnRleHRPdXQge1xcbiAgdG9wOiAtMTVweCAhaW1wb3J0YW50O1xcbiAgZm9udC1zaXplOiAxMnB4ICFpbXBvcnRhbnQ7XFxuICBsZWZ0OiA1cHggIWltcG9ydGFudDsgfVxcblxcbnNlY3Rpb24ubG9naW4sIHNlY3Rpb24ucmVnaXN0ZXIge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBwYWRkaW5nOiA2NXB4IDIzMHB4O1xcbiAgd2lkdGg6IDExMDBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIHNlY3Rpb24ubG9naW4gPiBoMiwgc2VjdGlvbi5yZWdpc3RlciA+IGgyIHtcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDIycHggUm9ib3RvO1xcbiAgICBtYXJnaW4tYm90dG9tOiA1NXB4OyB9XFxuICBzZWN0aW9uLmxvZ2luID4gLmlucHV0Rm9ybSwgc2VjdGlvbi5yZWdpc3RlciA+IC5pbnB1dEZvcm0ge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuaW5wdXRGb3JtID4gbGFiZWwsIHNlY3Rpb24ubG9naW4gPiAuaW5wdXRGb3JtIGlucHV0LCBzZWN0aW9uLnJlZ2lzdGVyID4gLmlucHV0Rm9ybSA+IGxhYmVsLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgICAgY29sb3I6ICM5YTlhOWE7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvOyB9XFxuICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlIHtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiA0NXB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLnZrLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlLnZrIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjMDE4NkNGIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvdmsucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdCAxMHB4O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAwOyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuYnRuLWJsdWUuZmFjZWJvb2ssIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUuZmFjZWJvb2sge1xcbiAgICAgIGJhY2tncm91bmQ6ICMzRTUwQjMgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9mYWNlYm9vay5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZS5nb29nbGUsIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUuZ29vZ2xlIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjRkQzQzAwIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZ29vZ2xlLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQgMTBweDsgfVxcblxcbnNlY3Rpb24uYnVsbGV0aW5BZGQge1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICB3aWR0aDogMTEwMHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwO1xcbiAgcGFkZGluZzogNjVweCAyMjVweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiBoMiB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMjJweCBSb2JvdG87XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5zZWxlY3RCb3gge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgYm9yZGVyLWNvbG9yOiBncmV5O1xcbiAgICAgIG1pbi13aWR0aDogMTk1cHg7XFxuICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgIHBhZGRpbmctbGVmdDogNXB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7XFxuICAgIHdpZHRoOiA0MTBweDtcXG4gICAgcGFkZGluZy1ib3R0b206IDVweDtcXG4gICAgbWFyZ2luLXRvcDogLTE2cHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlID4gaW5wdXQge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB6LWluZGV4OiAtMTsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUgPiBwIHtcXG4gICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICAgICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIHBhZGRpbmctdG9wOiAxMHB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuZmlsZSA+IC5idG4tYmx1ZSB7XFxuICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgICAgaGVpZ2h0OiAzMHB4O1xcbiAgICAgIHdpZHRoOiA4NXB4O1xcbiAgICAgIGxpbmUtaGVpZ2h0OiAzMHB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmlucHV0Rm9ybSB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICAgIGNvbG9yOiAjOTk5OTk5O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bzsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IHAge1xcbiAgICAgIGNvbG9yOiAjOTk5OTk5O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggZG90dGVkICM5OTk5OTk7XFxuICAgICAgd2lkdGg6IDE2MHB4O1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIGhlaWdodDogMjFweDtcXG4gICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAxOXB4O1xcbiAgICAgIG1hcmdpbi10b3A6IDNweDtcXG4gICAgICB3aWR0aDogMTZweDtcXG4gICAgICBoZWlnaHQ6IDE1cHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgICAgb3V0bGluZTogMXB4IHNvbGlkIHRyYW5zcGFyZW50O1xcbiAgICAgIGJvcmRlcjogMXB4IHNvbGlkIHdoaXRlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi50cmFuc3BhcmVudCB7XFxuICAgICAgICBib3JkZXItY29sb3I6ICNFOUU5RTk7XFxuICAgICAgICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuICAgICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnRyYW5zcGFyZW50ID4gLnJlZFN0aWNrIHtcXG4gICAgICAgICAgLXdlYmtpdC10cmFuc2Zvcm06IHJvdGF0ZSgtNDNkZWcpO1xcbiAgICAgICAgICAgICAgICAgIHRyYW5zZm9ybTogcm90YXRlKC00M2RlZyk7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6IHJlZDtcXG4gICAgICAgICAgaGVpZ2h0OiAycHg7XFxuICAgICAgICAgIG1hcmdpbi10b3A6IDUuNXB4O1xcbiAgICAgICAgICB3aWR0aDogMjBweDtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IC0zcHg7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnJlZCB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZWQ7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2Lm9yYW5nZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBvcmFuZ2U7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnllbGxvdyB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiB5ZWxsb3c7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmdyZWVuIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGdyZWVuOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5saWdodEJsdWUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogbGlnaHRCbHVlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5ibHVlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGJsdWU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnBpbmsge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcGluazsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYucHVycGxlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHB1cnBsZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYud2hpdGUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgICAgICBib3JkZXItY29sb3I6ICNFOUU5RTk7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmdyYXkge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogZ3JheTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuYmxhY2sge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogYmxhY2s7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmJyb3duIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGJyb3duOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5tdWx0aWNvbG9yIHtcXG4gICAgICAgIC8qIFBlcm1hbGluayAtIHVzZSB0byBlZGl0IGFuZCBzaGFyZSB0aGlzIGdyYWRpZW50OiBodHRwOi8vY29sb3J6aWxsYS5jb20vZ3JhZGllbnQtZWRpdG9yLyNmZjAwMDArMCxmZmZmMDArMjAsMWRmZjAwKzQwLDAwZmZmZis2MCwwNDAwZmYrODAsZmYwMGZhKzEwMCAqL1xcbiAgICAgICAgYmFja2dyb3VuZDogI2ZmMDAwMDtcXG4gICAgICAgIC8qIE9sZCBicm93c2VycyAqL1xcbiAgICAgICAgLyogRkYzLjYtMTUgKi9cXG4gICAgICAgIGJhY2tncm91bmQ6IC13ZWJraXQtbGluZWFyLWdyYWRpZW50KGxlZnQsICNmZjAwMDAgMCUsICNmZmZmMDAgMjAlLCAjMWRmZjAwIDQwJSwgIzAwZmZmZiA2MCUsICMwNDAwZmYgODAlLCAjZmYwMGZhIDEwMCUpO1xcbiAgICAgICAgLyogQ2hyb21lMTAtMjUsU2FmYXJpNS4xLTYgKi9cXG4gICAgICAgIGJhY2tncm91bmQ6IGxpbmVhci1ncmFkaWVudCh0byByaWdodCwgI2ZmMDAwMCAwJSwgI2ZmZmYwMCAyMCUsICMxZGZmMDAgNDAlLCAjMDBmZmZmIDYwJSwgIzA0MDBmZiA4MCUsICNmZjAwZmEgMTAwJSk7XFxuICAgICAgICAvKiBXM0MsIElFMTArLCBGRjE2KywgQ2hyb21lMjYrLCBPcGVyYTEyKywgU2FmYXJpNysgKi9cXG4gICAgICAgIGZpbHRlcjogcHJvZ2lkOkRYSW1hZ2VUcmFuc2Zvcm0uTWljcm9zb2Z0LmdyYWRpZW50KCBzdGFydENvbG9yc3RyPScjZmYwMDAwJywgZW5kQ29sb3JzdHI9JyNmZjAwZmEnLEdyYWRpZW50VHlwZT0xICk7XFxuICAgICAgICAvKiBJRTYtOSAqLyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5hY3RpdmUge1xcbiAgICAgICAgb3V0bGluZS1jb2xvcjogcmVkOyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNhbGVuZGFyLCBzZWN0aW9uLmJ1bGxldGluQWRkIC5hZGRDYWxlbmRhciB7XFxuICAgIGhlaWdodDogMjBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICB3aWR0aDogMjMwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FsZW5kYXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdDtcXG4gICAgcGFkZGluZy1sZWZ0OiA0NXB4O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICBtYXJnaW4tYm90dG9tOiA0MHB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlLCBzZWN0aW9uLmJ1bGxldGluQWRkIC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCBncmV5O1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCA1cHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUgPiBwLCBzZWN0aW9uLmJ1bGxldGluQWRkIC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUgPiBwIHtcXG4gICAgICAgIHRleHQtYWxpZ246IGxlZnQ7XFxuICAgICAgICBjb2xvcjogIzI2MjYyNjtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjBweCBSb2JvdG87IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jYWxlbmRhciA+IC5saXN0VmFsdWUsIHNlY3Rpb24uYnVsbGV0aW5BZGQgLmFkZENhbGVuZGFyID4gLmxpc3RWYWx1ZSB7XFxuICAgICAgZGlzcGxheTogbm9uZTsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5hZGRDYWxlbmRhciB7XFxuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nXCIpICsgXCIpOyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYWRkQ2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICBib3JkZXItYm90dG9tLWNvbG9yOiBibHVlO1xcbiAgICAgIGJhY2tncm91bmQ6IG5vbmU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSA+IHAge1xcbiAgICAgICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgICAgICBmb250LXNpemU6IDE2cHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYnRuLWJsdWUge1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7XFxuICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5lcnJvcnMge1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgZm9udDogNDAwIDEycHggLyAxNHB4IFJvYm90bztcXG4gIGNvbG9yOiAjZGQyYzAwO1xcbiAgYm90dG9tOiAtMTdweDsgfVxcblxcbm5hdiB7XFxuICB3aWR0aDogMjU1cHg7XFxuICBmbG9hdDogbGVmdDtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgbWFyZ2luLXRvcDogNXB4OyB9XFxuICBuYXYgPiAubWFwIHtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9tYXAucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgaGVpZ2h0OiAxNDVweDtcXG4gICAgd2lkdGg6IDI1NXB4OyB9XFxuICAgIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtIHtcXG4gICAgICB3aWR0aDogMjE1cHg7XFxuICAgICAgbWFyZ2luOiAxMTVweCBhdXRvIDA7IH1cXG4gICAgICBuYXYgPiAubWFwID4gLmlucHV0Rm9ybS5yZXF1aXJlZCB7XFxuICAgICAgICBib3JkZXItY29sb3I6IGJsYWNrICFpbXBvcnRhbnQ7IH1cXG4gICAgICBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSA+IGlucHV0LCBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSBsYWJlbCwgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0gLnRleHRPdXQge1xcbiAgICAgICAgY29sb3I6IGJsYWNrICFpbXBvcnRhbnQ7IH1cXG4gIG5hdiA+IHVsIHtcXG4gICAgbGlzdC1zdHlsZTogbm9uZTsgfVxcbiAgICBuYXYgPiB1bCA+IGxpID4gcCB7XFxuICAgICAgcGFkZGluZy1sZWZ0OiA3NXB4O1xcbiAgICAgIGNvbG9yOiAjMjYzMjM4O1xcbiAgICAgIGZvbnQ6IDUwMCAxM3B4IC8gNDBweCBSb2JvdG87XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDIwcHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNFNkU2RTYgIWltcG9ydGFudDsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0ZvckNoaWxkcmVuLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDIpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0ZvckFuaW1hbHMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMykge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQnVzaW5lc3MucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNCkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVGhlUHJvcGVydHkucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVHJhbnNwb3J0LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDYpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDcpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1ZvbHVudGVlcmluZy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg4KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Jc0ZyZWUucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoOSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxMCkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvSG9iYmllc0FuZFNwb3J0cy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxMSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQmFydGVyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEyKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuXFxuLm5hdkJ0biB7XFxuICB3aWR0aDogNDNweDtcXG4gIGhlaWdodDogNDlweDtcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIGxlZnQ6IDA7XFxuICB0b3A6IDkwcHg7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3J1cHIucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gIHotaW5kZXg6IDE7IH1cXG4gIC5uYXZCdG46aG92ZXIge1xcbiAgICB3aWR0aDogNjBweDtcXG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9ydXBySG92ZXIucG5nXCIpICsgXCIpOyB9XFxuXCIsIFwiXCJdKTtcblxuLy8gZXhwb3J0c1xuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vY3NzLWxvYWRlciEuL34vcG9zdGNzcy1sb2FkZXIhLi9+L3Nhc3MtbG9hZGVyIS4vc3R5bGVzL2Jhc2ljLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSAyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvKlxyXG5cdE1JVCBMaWNlbnNlIGh0dHA6Ly93d3cub3BlbnNvdXJjZS5vcmcvbGljZW5zZXMvbWl0LWxpY2Vuc2UucGhwXHJcblx0QXV0aG9yIFRvYmlhcyBLb3BwZXJzIEBzb2tyYVxyXG4qL1xyXG4vLyBjc3MgYmFzZSBjb2RlLCBpbmplY3RlZCBieSB0aGUgY3NzLWxvYWRlclxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG5cdHZhciBsaXN0ID0gW107XHJcblxyXG5cdC8vIHJldHVybiB0aGUgbGlzdCBvZiBtb2R1bGVzIGFzIGNzcyBzdHJpbmdcclxuXHRsaXN0LnRvU3RyaW5nID0gZnVuY3Rpb24gdG9TdHJpbmcoKSB7XHJcblx0XHR2YXIgcmVzdWx0ID0gW107XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgdGhpcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaXRlbSA9IHRoaXNbaV07XHJcblx0XHRcdGlmKGl0ZW1bMl0pIHtcclxuXHRcdFx0XHRyZXN1bHQucHVzaChcIkBtZWRpYSBcIiArIGl0ZW1bMl0gKyBcIntcIiArIGl0ZW1bMV0gKyBcIn1cIik7XHJcblx0XHRcdH0gZWxzZSB7XHJcblx0XHRcdFx0cmVzdWx0LnB1c2goaXRlbVsxXSk7XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHRcdHJldHVybiByZXN1bHQuam9pbihcIlwiKTtcclxuXHR9O1xyXG5cclxuXHQvLyBpbXBvcnQgYSBsaXN0IG9mIG1vZHVsZXMgaW50byB0aGUgbGlzdFxyXG5cdGxpc3QuaSA9IGZ1bmN0aW9uKG1vZHVsZXMsIG1lZGlhUXVlcnkpIHtcclxuXHRcdGlmKHR5cGVvZiBtb2R1bGVzID09PSBcInN0cmluZ1wiKVxyXG5cdFx0XHRtb2R1bGVzID0gW1tudWxsLCBtb2R1bGVzLCBcIlwiXV07XHJcblx0XHR2YXIgYWxyZWFkeUltcG9ydGVkTW9kdWxlcyA9IHt9O1xyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IHRoaXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGlkID0gdGhpc1tpXVswXTtcclxuXHRcdFx0aWYodHlwZW9mIGlkID09PSBcIm51bWJlclwiKVxyXG5cdFx0XHRcdGFscmVhZHlJbXBvcnRlZE1vZHVsZXNbaWRdID0gdHJ1ZTtcclxuXHRcdH1cclxuXHRcdGZvcihpID0gMDsgaSA8IG1vZHVsZXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGl0ZW0gPSBtb2R1bGVzW2ldO1xyXG5cdFx0XHQvLyBza2lwIGFscmVhZHkgaW1wb3J0ZWQgbW9kdWxlXHJcblx0XHRcdC8vIHRoaXMgaW1wbGVtZW50YXRpb24gaXMgbm90IDEwMCUgcGVyZmVjdCBmb3Igd2VpcmQgbWVkaWEgcXVlcnkgY29tYmluYXRpb25zXHJcblx0XHRcdC8vICB3aGVuIGEgbW9kdWxlIGlzIGltcG9ydGVkIG11bHRpcGxlIHRpbWVzIHdpdGggZGlmZmVyZW50IG1lZGlhIHF1ZXJpZXMuXHJcblx0XHRcdC8vICBJIGhvcGUgdGhpcyB3aWxsIG5ldmVyIG9jY3VyIChIZXkgdGhpcyB3YXkgd2UgaGF2ZSBzbWFsbGVyIGJ1bmRsZXMpXHJcblx0XHRcdGlmKHR5cGVvZiBpdGVtWzBdICE9PSBcIm51bWJlclwiIHx8ICFhbHJlYWR5SW1wb3J0ZWRNb2R1bGVzW2l0ZW1bMF1dKSB7XHJcblx0XHRcdFx0aWYobWVkaWFRdWVyeSAmJiAhaXRlbVsyXSkge1xyXG5cdFx0XHRcdFx0aXRlbVsyXSA9IG1lZGlhUXVlcnk7XHJcblx0XHRcdFx0fSBlbHNlIGlmKG1lZGlhUXVlcnkpIHtcclxuXHRcdFx0XHRcdGl0ZW1bMl0gPSBcIihcIiArIGl0ZW1bMl0gKyBcIikgYW5kIChcIiArIG1lZGlhUXVlcnkgKyBcIilcIjtcclxuXHRcdFx0XHR9XHJcblx0XHRcdFx0bGlzdC5wdXNoKGl0ZW0pO1xyXG5cdFx0XHR9XHJcblx0XHR9XHJcblx0fTtcclxuXHRyZXR1cm4gbGlzdDtcclxufTtcclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2xvZ28ucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9sb2dvLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9hZGQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hZGQucG5nXG4gKiogbW9kdWxlIGlkID0gNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL21haWwucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9tYWlsLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9tYWlsX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL21haWxfc2hhZG93LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9iZWxsLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYmVsbC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYmVsbF9zaGFkb3cucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9iZWxsX3NoYWRvdy5wbmdcbiAqKiBtb2R1bGUgaWQgPSA5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvc2VydmljZXMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9zZXJ2aWNlcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxMFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3NlcnZpY2VzX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3NlcnZpY2VzX3NoYWRvdy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3VzZXJOYW1lLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdXNlck5hbWUucG5nXG4gKiogbW9kdWxlIGlkID0gMTJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9uZXh0LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbmV4dC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxM1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3ByZXYucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9wcmV2LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZXJyb3JfYmcucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9lcnJvcl9iZy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3ZrLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdmsucG5nXG4gKiogbW9kdWxlIGlkID0gMTZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9mYWNlYm9vay5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2ZhY2Vib29rLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZ29vZ2xlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZ29vZ2xlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYWxlbmRhci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxOVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYWRkQ2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hZGRDYWxlbmRhci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL21hcC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL21hcC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0UmlnaHROYXYucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvRm9yQ2hpbGRyZW4ucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Gb3JDaGlsZHJlbi5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0ZvckFuaW1hbHMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Gb3JBbmltYWxzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQnVzaW5lc3MucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9CdXNpbmVzcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyNlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RoZVByb3BlcnR5LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVGhlUHJvcGVydHkucG5nXG4gKiogbW9kdWxlIGlkID0gMjdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9UcmFuc3BvcnQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9UcmFuc3BvcnQucG5nXG4gKiogbW9kdWxlIGlkID0gMjhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9DbG90aGluZ0FuZENvc21ldGljcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvSXNGcmVlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvSXNGcmVlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0FIb3VzZUFuZEFHYXJkZW4ucG5nXG4gKiogbW9kdWxlIGlkID0gMzJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Ib2JiaWVzQW5kU3BvcnRzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvSG9iYmllc0FuZFNwb3J0cy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzM1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0JhcnRlci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0JhcnRlci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLypcclxuXHRNSVQgTGljZW5zZSBodHRwOi8vd3d3Lm9wZW5zb3VyY2Uub3JnL2xpY2Vuc2VzL21pdC1saWNlbnNlLnBocFxyXG5cdEF1dGhvciBUb2JpYXMgS29wcGVycyBAc29rcmFcclxuKi9cclxudmFyIHN0eWxlc0luRG9tID0ge30sXHJcblx0bWVtb2l6ZSA9IGZ1bmN0aW9uKGZuKSB7XHJcblx0XHR2YXIgbWVtbztcclxuXHRcdHJldHVybiBmdW5jdGlvbiAoKSB7XHJcblx0XHRcdGlmICh0eXBlb2YgbWVtbyA9PT0gXCJ1bmRlZmluZWRcIikgbWVtbyA9IGZuLmFwcGx5KHRoaXMsIGFyZ3VtZW50cyk7XHJcblx0XHRcdHJldHVybiBtZW1vO1xyXG5cdFx0fTtcclxuXHR9LFxyXG5cdGlzT2xkSUUgPSBtZW1vaXplKGZ1bmN0aW9uKCkge1xyXG5cdFx0cmV0dXJuIC9tc2llIFs2LTldXFxiLy50ZXN0KHdpbmRvdy5uYXZpZ2F0b3IudXNlckFnZW50LnRvTG93ZXJDYXNlKCkpO1xyXG5cdH0pLFxyXG5cdGdldEhlYWRFbGVtZW50ID0gbWVtb2l6ZShmdW5jdGlvbiAoKSB7XHJcblx0XHRyZXR1cm4gZG9jdW1lbnQuaGVhZCB8fCBkb2N1bWVudC5nZXRFbGVtZW50c0J5VGFnTmFtZShcImhlYWRcIilbMF07XHJcblx0fSksXHJcblx0c2luZ2xldG9uRWxlbWVudCA9IG51bGwsXHJcblx0c2luZ2xldG9uQ291bnRlciA9IDAsXHJcblx0c3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AgPSBbXTtcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24obGlzdCwgb3B0aW9ucykge1xyXG5cdGlmKHR5cGVvZiBERUJVRyAhPT0gXCJ1bmRlZmluZWRcIiAmJiBERUJVRykge1xyXG5cdFx0aWYodHlwZW9mIGRvY3VtZW50ICE9PSBcIm9iamVjdFwiKSB0aHJvdyBuZXcgRXJyb3IoXCJUaGUgc3R5bGUtbG9hZGVyIGNhbm5vdCBiZSB1c2VkIGluIGEgbm9uLWJyb3dzZXIgZW52aXJvbm1lbnRcIik7XHJcblx0fVxyXG5cclxuXHRvcHRpb25zID0gb3B0aW9ucyB8fCB7fTtcclxuXHQvLyBGb3JjZSBzaW5nbGUtdGFnIHNvbHV0aW9uIG9uIElFNi05LCB3aGljaCBoYXMgYSBoYXJkIGxpbWl0IG9uIHRoZSAjIG9mIDxzdHlsZT5cclxuXHQvLyB0YWdzIGl0IHdpbGwgYWxsb3cgb24gYSBwYWdlXHJcblx0aWYgKHR5cGVvZiBvcHRpb25zLnNpbmdsZXRvbiA9PT0gXCJ1bmRlZmluZWRcIikgb3B0aW9ucy5zaW5nbGV0b24gPSBpc09sZElFKCk7XHJcblxyXG5cdC8vIEJ5IGRlZmF1bHQsIGFkZCA8c3R5bGU+IHRhZ3MgdG8gdGhlIGJvdHRvbSBvZiA8aGVhZD4uXHJcblx0aWYgKHR5cGVvZiBvcHRpb25zLmluc2VydEF0ID09PSBcInVuZGVmaW5lZFwiKSBvcHRpb25zLmluc2VydEF0ID0gXCJib3R0b21cIjtcclxuXHJcblx0dmFyIHN0eWxlcyA9IGxpc3RUb1N0eWxlcyhsaXN0KTtcclxuXHRhZGRTdHlsZXNUb0RvbShzdHlsZXMsIG9wdGlvbnMpO1xyXG5cclxuXHRyZXR1cm4gZnVuY3Rpb24gdXBkYXRlKG5ld0xpc3QpIHtcclxuXHRcdHZhciBtYXlSZW1vdmUgPSBbXTtcclxuXHRcdGZvcih2YXIgaSA9IDA7IGkgPCBzdHlsZXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGl0ZW0gPSBzdHlsZXNbaV07XHJcblx0XHRcdHZhciBkb21TdHlsZSA9IHN0eWxlc0luRG9tW2l0ZW0uaWRdO1xyXG5cdFx0XHRkb21TdHlsZS5yZWZzLS07XHJcblx0XHRcdG1heVJlbW92ZS5wdXNoKGRvbVN0eWxlKTtcclxuXHRcdH1cclxuXHRcdGlmKG5ld0xpc3QpIHtcclxuXHRcdFx0dmFyIG5ld1N0eWxlcyA9IGxpc3RUb1N0eWxlcyhuZXdMaXN0KTtcclxuXHRcdFx0YWRkU3R5bGVzVG9Eb20obmV3U3R5bGVzLCBvcHRpb25zKTtcclxuXHRcdH1cclxuXHRcdGZvcih2YXIgaSA9IDA7IGkgPCBtYXlSZW1vdmUubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGRvbVN0eWxlID0gbWF5UmVtb3ZlW2ldO1xyXG5cdFx0XHRpZihkb21TdHlsZS5yZWZzID09PSAwKSB7XHJcblx0XHRcdFx0Zm9yKHZhciBqID0gMDsgaiA8IGRvbVN0eWxlLnBhcnRzLmxlbmd0aDsgaisrKVxyXG5cdFx0XHRcdFx0ZG9tU3R5bGUucGFydHNbal0oKTtcclxuXHRcdFx0XHRkZWxldGUgc3R5bGVzSW5Eb21bZG9tU3R5bGUuaWRdO1xyXG5cdFx0XHR9XHJcblx0XHR9XHJcblx0fTtcclxufVxyXG5cclxuZnVuY3Rpb24gYWRkU3R5bGVzVG9Eb20oc3R5bGVzLCBvcHRpb25zKSB7XHJcblx0Zm9yKHZhciBpID0gMDsgaSA8IHN0eWxlcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0dmFyIGl0ZW0gPSBzdHlsZXNbaV07XHJcblx0XHR2YXIgZG9tU3R5bGUgPSBzdHlsZXNJbkRvbVtpdGVtLmlkXTtcclxuXHRcdGlmKGRvbVN0eWxlKSB7XHJcblx0XHRcdGRvbVN0eWxlLnJlZnMrKztcclxuXHRcdFx0Zm9yKHZhciBqID0gMDsgaiA8IGRvbVN0eWxlLnBhcnRzLmxlbmd0aDsgaisrKSB7XHJcblx0XHRcdFx0ZG9tU3R5bGUucGFydHNbal0oaXRlbS5wYXJ0c1tqXSk7XHJcblx0XHRcdH1cclxuXHRcdFx0Zm9yKDsgaiA8IGl0ZW0ucGFydHMubGVuZ3RoOyBqKyspIHtcclxuXHRcdFx0XHRkb21TdHlsZS5wYXJ0cy5wdXNoKGFkZFN0eWxlKGl0ZW0ucGFydHNbal0sIG9wdGlvbnMpKTtcclxuXHRcdFx0fVxyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0dmFyIHBhcnRzID0gW107XHJcblx0XHRcdGZvcih2YXIgaiA9IDA7IGogPCBpdGVtLnBhcnRzLmxlbmd0aDsgaisrKSB7XHJcblx0XHRcdFx0cGFydHMucHVzaChhZGRTdHlsZShpdGVtLnBhcnRzW2pdLCBvcHRpb25zKSk7XHJcblx0XHRcdH1cclxuXHRcdFx0c3R5bGVzSW5Eb21baXRlbS5pZF0gPSB7aWQ6IGl0ZW0uaWQsIHJlZnM6IDEsIHBhcnRzOiBwYXJ0c307XHJcblx0XHR9XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiBsaXN0VG9TdHlsZXMobGlzdCkge1xyXG5cdHZhciBzdHlsZXMgPSBbXTtcclxuXHR2YXIgbmV3U3R5bGVzID0ge307XHJcblx0Zm9yKHZhciBpID0gMDsgaSA8IGxpc3QubGVuZ3RoOyBpKyspIHtcclxuXHRcdHZhciBpdGVtID0gbGlzdFtpXTtcclxuXHRcdHZhciBpZCA9IGl0ZW1bMF07XHJcblx0XHR2YXIgY3NzID0gaXRlbVsxXTtcclxuXHRcdHZhciBtZWRpYSA9IGl0ZW1bMl07XHJcblx0XHR2YXIgc291cmNlTWFwID0gaXRlbVszXTtcclxuXHRcdHZhciBwYXJ0ID0ge2NzczogY3NzLCBtZWRpYTogbWVkaWEsIHNvdXJjZU1hcDogc291cmNlTWFwfTtcclxuXHRcdGlmKCFuZXdTdHlsZXNbaWRdKVxyXG5cdFx0XHRzdHlsZXMucHVzaChuZXdTdHlsZXNbaWRdID0ge2lkOiBpZCwgcGFydHM6IFtwYXJ0XX0pO1xyXG5cdFx0ZWxzZVxyXG5cdFx0XHRuZXdTdHlsZXNbaWRdLnBhcnRzLnB1c2gocGFydCk7XHJcblx0fVxyXG5cdHJldHVybiBzdHlsZXM7XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGluc2VydFN0eWxlRWxlbWVudChvcHRpb25zLCBzdHlsZUVsZW1lbnQpIHtcclxuXHR2YXIgaGVhZCA9IGdldEhlYWRFbGVtZW50KCk7XHJcblx0dmFyIGxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wID0gc3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3Bbc3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AubGVuZ3RoIC0gMV07XHJcblx0aWYgKG9wdGlvbnMuaW5zZXJ0QXQgPT09IFwidG9wXCIpIHtcclxuXHRcdGlmKCFsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcCkge1xyXG5cdFx0XHRoZWFkLmluc2VydEJlZm9yZShzdHlsZUVsZW1lbnQsIGhlYWQuZmlyc3RDaGlsZCk7XHJcblx0XHR9IGVsc2UgaWYobGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3AubmV4dFNpYmxpbmcpIHtcclxuXHRcdFx0aGVhZC5pbnNlcnRCZWZvcmUoc3R5bGVFbGVtZW50LCBsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcC5uZXh0U2libGluZyk7XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHRoZWFkLmFwcGVuZENoaWxkKHN0eWxlRWxlbWVudCk7XHJcblx0XHR9XHJcblx0XHRzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5wdXNoKHN0eWxlRWxlbWVudCk7XHJcblx0fSBlbHNlIGlmIChvcHRpb25zLmluc2VydEF0ID09PSBcImJvdHRvbVwiKSB7XHJcblx0XHRoZWFkLmFwcGVuZENoaWxkKHN0eWxlRWxlbWVudCk7XHJcblx0fSBlbHNlIHtcclxuXHRcdHRocm93IG5ldyBFcnJvcihcIkludmFsaWQgdmFsdWUgZm9yIHBhcmFtZXRlciAnaW5zZXJ0QXQnLiBNdXN0IGJlICd0b3AnIG9yICdib3R0b20nLlwiKTtcclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIHJlbW92ZVN0eWxlRWxlbWVudChzdHlsZUVsZW1lbnQpIHtcclxuXHRzdHlsZUVsZW1lbnQucGFyZW50Tm9kZS5yZW1vdmVDaGlsZChzdHlsZUVsZW1lbnQpO1xyXG5cdHZhciBpZHggPSBzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5pbmRleE9mKHN0eWxlRWxlbWVudCk7XHJcblx0aWYoaWR4ID49IDApIHtcclxuXHRcdHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLnNwbGljZShpZHgsIDEpO1xyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gY3JlYXRlU3R5bGVFbGVtZW50KG9wdGlvbnMpIHtcclxuXHR2YXIgc3R5bGVFbGVtZW50ID0gZG9jdW1lbnQuY3JlYXRlRWxlbWVudChcInN0eWxlXCIpO1xyXG5cdHN0eWxlRWxlbWVudC50eXBlID0gXCJ0ZXh0L2Nzc1wiO1xyXG5cdGluc2VydFN0eWxlRWxlbWVudChvcHRpb25zLCBzdHlsZUVsZW1lbnQpO1xyXG5cdHJldHVybiBzdHlsZUVsZW1lbnQ7XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGNyZWF0ZUxpbmtFbGVtZW50KG9wdGlvbnMpIHtcclxuXHR2YXIgbGlua0VsZW1lbnQgPSBkb2N1bWVudC5jcmVhdGVFbGVtZW50KFwibGlua1wiKTtcclxuXHRsaW5rRWxlbWVudC5yZWwgPSBcInN0eWxlc2hlZXRcIjtcclxuXHRpbnNlcnRTdHlsZUVsZW1lbnQob3B0aW9ucywgbGlua0VsZW1lbnQpO1xyXG5cdHJldHVybiBsaW5rRWxlbWVudDtcclxufVxyXG5cclxuZnVuY3Rpb24gYWRkU3R5bGUob2JqLCBvcHRpb25zKSB7XHJcblx0dmFyIHN0eWxlRWxlbWVudCwgdXBkYXRlLCByZW1vdmU7XHJcblxyXG5cdGlmIChvcHRpb25zLnNpbmdsZXRvbikge1xyXG5cdFx0dmFyIHN0eWxlSW5kZXggPSBzaW5nbGV0b25Db3VudGVyKys7XHJcblx0XHRzdHlsZUVsZW1lbnQgPSBzaW5nbGV0b25FbGVtZW50IHx8IChzaW5nbGV0b25FbGVtZW50ID0gY3JlYXRlU3R5bGVFbGVtZW50KG9wdGlvbnMpKTtcclxuXHRcdHVwZGF0ZSA9IGFwcGx5VG9TaW5nbGV0b25UYWcuYmluZChudWxsLCBzdHlsZUVsZW1lbnQsIHN0eWxlSW5kZXgsIGZhbHNlKTtcclxuXHRcdHJlbW92ZSA9IGFwcGx5VG9TaW5nbGV0b25UYWcuYmluZChudWxsLCBzdHlsZUVsZW1lbnQsIHN0eWxlSW5kZXgsIHRydWUpO1xyXG5cdH0gZWxzZSBpZihvYmouc291cmNlTWFwICYmXHJcblx0XHR0eXBlb2YgVVJMID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBVUkwuY3JlYXRlT2JqZWN0VVJMID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBVUkwucmV2b2tlT2JqZWN0VVJMID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBCbG9iID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBidG9hID09PSBcImZ1bmN0aW9uXCIpIHtcclxuXHRcdHN0eWxlRWxlbWVudCA9IGNyZWF0ZUxpbmtFbGVtZW50KG9wdGlvbnMpO1xyXG5cdFx0dXBkYXRlID0gdXBkYXRlTGluay5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCk7XHJcblx0XHRyZW1vdmUgPSBmdW5jdGlvbigpIHtcclxuXHRcdFx0cmVtb3ZlU3R5bGVFbGVtZW50KHN0eWxlRWxlbWVudCk7XHJcblx0XHRcdGlmKHN0eWxlRWxlbWVudC5ocmVmKVxyXG5cdFx0XHRcdFVSTC5yZXZva2VPYmplY3RVUkwoc3R5bGVFbGVtZW50LmhyZWYpO1xyXG5cdFx0fTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0c3R5bGVFbGVtZW50ID0gY3JlYXRlU3R5bGVFbGVtZW50KG9wdGlvbnMpO1xyXG5cdFx0dXBkYXRlID0gYXBwbHlUb1RhZy5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCk7XHJcblx0XHRyZW1vdmUgPSBmdW5jdGlvbigpIHtcclxuXHRcdFx0cmVtb3ZlU3R5bGVFbGVtZW50KHN0eWxlRWxlbWVudCk7XHJcblx0XHR9O1xyXG5cdH1cclxuXHJcblx0dXBkYXRlKG9iaik7XHJcblxyXG5cdHJldHVybiBmdW5jdGlvbiB1cGRhdGVTdHlsZShuZXdPYmopIHtcclxuXHRcdGlmKG5ld09iaikge1xyXG5cdFx0XHRpZihuZXdPYmouY3NzID09PSBvYmouY3NzICYmIG5ld09iai5tZWRpYSA9PT0gb2JqLm1lZGlhICYmIG5ld09iai5zb3VyY2VNYXAgPT09IG9iai5zb3VyY2VNYXApXHJcblx0XHRcdFx0cmV0dXJuO1xyXG5cdFx0XHR1cGRhdGUob2JqID0gbmV3T2JqKTtcclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHJlbW92ZSgpO1xyXG5cdFx0fVxyXG5cdH07XHJcbn1cclxuXHJcbnZhciByZXBsYWNlVGV4dCA9IChmdW5jdGlvbiAoKSB7XHJcblx0dmFyIHRleHRTdG9yZSA9IFtdO1xyXG5cclxuXHRyZXR1cm4gZnVuY3Rpb24gKGluZGV4LCByZXBsYWNlbWVudCkge1xyXG5cdFx0dGV4dFN0b3JlW2luZGV4XSA9IHJlcGxhY2VtZW50O1xyXG5cdFx0cmV0dXJuIHRleHRTdG9yZS5maWx0ZXIoQm9vbGVhbikuam9pbignXFxuJyk7XHJcblx0fTtcclxufSkoKTtcclxuXHJcbmZ1bmN0aW9uIGFwcGx5VG9TaW5nbGV0b25UYWcoc3R5bGVFbGVtZW50LCBpbmRleCwgcmVtb3ZlLCBvYmopIHtcclxuXHR2YXIgY3NzID0gcmVtb3ZlID8gXCJcIiA6IG9iai5jc3M7XHJcblxyXG5cdGlmIChzdHlsZUVsZW1lbnQuc3R5bGVTaGVldCkge1xyXG5cdFx0c3R5bGVFbGVtZW50LnN0eWxlU2hlZXQuY3NzVGV4dCA9IHJlcGxhY2VUZXh0KGluZGV4LCBjc3MpO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR2YXIgY3NzTm9kZSA9IGRvY3VtZW50LmNyZWF0ZVRleHROb2RlKGNzcyk7XHJcblx0XHR2YXIgY2hpbGROb2RlcyA9IHN0eWxlRWxlbWVudC5jaGlsZE5vZGVzO1xyXG5cdFx0aWYgKGNoaWxkTm9kZXNbaW5kZXhdKSBzdHlsZUVsZW1lbnQucmVtb3ZlQ2hpbGQoY2hpbGROb2Rlc1tpbmRleF0pO1xyXG5cdFx0aWYgKGNoaWxkTm9kZXMubGVuZ3RoKSB7XHJcblx0XHRcdHN0eWxlRWxlbWVudC5pbnNlcnRCZWZvcmUoY3NzTm9kZSwgY2hpbGROb2Rlc1tpbmRleF0pO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0c3R5bGVFbGVtZW50LmFwcGVuZENoaWxkKGNzc05vZGUpO1xyXG5cdFx0fVxyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gYXBwbHlUb1RhZyhzdHlsZUVsZW1lbnQsIG9iaikge1xyXG5cdHZhciBjc3MgPSBvYmouY3NzO1xyXG5cdHZhciBtZWRpYSA9IG9iai5tZWRpYTtcclxuXHJcblx0aWYobWVkaWEpIHtcclxuXHRcdHN0eWxlRWxlbWVudC5zZXRBdHRyaWJ1dGUoXCJtZWRpYVwiLCBtZWRpYSlcclxuXHR9XHJcblxyXG5cdGlmKHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0KSB7XHJcblx0XHRzdHlsZUVsZW1lbnQuc3R5bGVTaGVldC5jc3NUZXh0ID0gY3NzO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR3aGlsZShzdHlsZUVsZW1lbnQuZmlyc3RDaGlsZCkge1xyXG5cdFx0XHRzdHlsZUVsZW1lbnQucmVtb3ZlQ2hpbGQoc3R5bGVFbGVtZW50LmZpcnN0Q2hpbGQpO1xyXG5cdFx0fVxyXG5cdFx0c3R5bGVFbGVtZW50LmFwcGVuZENoaWxkKGRvY3VtZW50LmNyZWF0ZVRleHROb2RlKGNzcykpO1xyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gdXBkYXRlTGluayhsaW5rRWxlbWVudCwgb2JqKSB7XHJcblx0dmFyIGNzcyA9IG9iai5jc3M7XHJcblx0dmFyIHNvdXJjZU1hcCA9IG9iai5zb3VyY2VNYXA7XHJcblxyXG5cdGlmKHNvdXJjZU1hcCkge1xyXG5cdFx0Ly8gaHR0cDovL3N0YWNrb3ZlcmZsb3cuY29tL2EvMjY2MDM4NzVcclxuXHRcdGNzcyArPSBcIlxcbi8qIyBzb3VyY2VNYXBwaW5nVVJMPWRhdGE6YXBwbGljYXRpb24vanNvbjtiYXNlNjQsXCIgKyBidG9hKHVuZXNjYXBlKGVuY29kZVVSSUNvbXBvbmVudChKU09OLnN0cmluZ2lmeShzb3VyY2VNYXApKSkpICsgXCIgKi9cIjtcclxuXHR9XHJcblxyXG5cdHZhciBibG9iID0gbmV3IEJsb2IoW2Nzc10sIHsgdHlwZTogXCJ0ZXh0L2Nzc1wiIH0pO1xyXG5cclxuXHR2YXIgb2xkU3JjID0gbGlua0VsZW1lbnQuaHJlZjtcclxuXHJcblx0bGlua0VsZW1lbnQuaHJlZiA9IFVSTC5jcmVhdGVPYmplY3RVUkwoYmxvYik7XHJcblxyXG5cdGlmKG9sZFNyYylcclxuXHRcdFVSTC5yZXZva2VPYmplY3RVUkwob2xkU3JjKTtcclxufVxyXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXG4gKiogbW9kdWxlIGlkID0gMzZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8vIHN0eWxlLWxvYWRlcjogQWRkcyBzb21lIGNzcyB0byB0aGUgRE9NIGJ5IGFkZGluZyBhIDxzdHlsZT4gdGFnXG5cbi8vIGxvYWQgdGhlIHN0eWxlc1xudmFyIGNvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZmF2b3VyaXRlcy5zY3NzXCIpO1xuaWYodHlwZW9mIGNvbnRlbnQgPT09ICdzdHJpbmcnKSBjb250ZW50ID0gW1ttb2R1bGUuaWQsIGNvbnRlbnQsICcnXV07XG4vLyBhZGQgdGhlIHN0eWxlcyB0byB0aGUgRE9NXG52YXIgdXBkYXRlID0gcmVxdWlyZShcIiEuLy4uL25vZGVfbW9kdWxlcy9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXCIpKGNvbnRlbnQsIHt9KTtcbmlmKGNvbnRlbnQubG9jYWxzKSBtb2R1bGUuZXhwb3J0cyA9IGNvbnRlbnQubG9jYWxzO1xuLy8gSG90IE1vZHVsZSBSZXBsYWNlbWVudFxuaWYobW9kdWxlLmhvdCkge1xuXHQvLyBXaGVuIHRoZSBzdHlsZXMgY2hhbmdlLCB1cGRhdGUgdGhlIDxzdHlsZT4gdGFnc1xuXHRpZighY29udGVudC5sb2NhbHMpIHtcblx0XHRtb2R1bGUuaG90LmFjY2VwdChcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Zhdm91cml0ZXMuc2Nzc1wiLCBmdW5jdGlvbigpIHtcblx0XHRcdHZhciBuZXdDb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Zhdm91cml0ZXMuc2Nzc1wiKTtcblx0XHRcdGlmKHR5cGVvZiBuZXdDb250ZW50ID09PSAnc3RyaW5nJykgbmV3Q29udGVudCA9IFtbbW9kdWxlLmlkLCBuZXdDb250ZW50LCAnJ11dO1xuXHRcdFx0dXBkYXRlKG5ld0NvbnRlbnQpO1xuXHRcdH0pO1xuXHR9XG5cdC8vIFdoZW4gdGhlIG1vZHVsZSBpcyBkaXNwb3NlZCwgcmVtb3ZlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0bW9kdWxlLmhvdC5kaXNwb3NlKGZ1bmN0aW9uKCkgeyB1cGRhdGUoKTsgfSk7XG59XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSAzN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiZXhwb3J0cyA9IG1vZHVsZS5leHBvcnRzID0gcmVxdWlyZShcIi4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXCIpKCk7XG4vLyBpbXBvcnRzXG5cblxuLy8gbW9kdWxlXG5leHBvcnRzLnB1c2goW21vZHVsZS5pZCwgXCJAY2hhcnNldCBcXFwiVVRGLThcXFwiO1xcbiNmYXZvdXJpdGVzIHtcXG4gIHdpZHRoOiA5OTBweDtcXG4gIG1hcmdpbjogM3B4IGF1dG8gMDsgfVxcbiAgI2Zhdm91cml0ZXMgLnJpZ2h0LWNvbHVtbiB7XFxuICAgIHdpZHRoOiA0OTBweDtcXG4gICAgZmxvYXQ6IHJpZ2h0OyB9XFxuICAjZmF2b3VyaXRlcyAubGVmdC1jb2x1bW4ge1xcbiAgICB3aWR0aDogNDkwcHg7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuXFxuLmJ1bGxldGluLXNob3J0IHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIHBhZGRpbmc6IDIwcHggMTZweDtcXG4gIHdpZHRoOiA0OTBweDtcXG4gIG1hcmdpbi1ib3R0b206IDdweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAvKiDQm9C10LLQsNGPINC60L7Qu9C+0L3QutCwICovXFxuICAvKiDQn9GA0LDQstCw0Y8g0LrQvtC70L7QvdC60LAgKi8gfVxcbiAgLmJ1bGxldGluLXNob3J0ID4gZGl2IHtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLXJpZ2h0LWNvbHVtbiB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgd2lkdGg6IDkwcHg7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLXJpZ2h0LWNvbHVtbiA+IC5vbk9yT2ZmTGluZVVzZXIge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB0b3A6IDVweDtcXG4gICAgICBsZWZ0OiAxMHB4OyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWNlbnRlci1jb2x1bW4ge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMTVweDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IGgzIHtcXG4gICAgbWFyZ2luOiAwO1xcbiAgICBmb250OiA0MDAgMjBweCAvIDI0cHggUm9ib3RvO1xcbiAgICBjb2xvcjogIzIxMjEyMTtcXG4gICAgd2lkdGg6IDIwNnB4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5idWxsZXRpbi1zaG9ydCBoMzpob3ZlciB7XFxuICAgICAgdGV4dC1kZWNvcmF0aW9uOiB1bmRlcmxpbmU7XFxuICAgICAgLXdlYmtpdC10ZXh0LWRlY29yYXRpb24tY29sb3I6IGdyYXk7XFxuICAgICAgICAgICAgICB0ZXh0LWRlY29yYXRpb24tY29sb3I6IGdyYXk7XFxuICAgICAgLXdlYmtpdC10ZXh0LWRlY29yYXRpb24tc3R5bGU6IGRhc2hlZDtcXG4gICAgICAgICAgICAgIHRleHQtZGVjb3JhdGlvbi1zdHlsZTogZGFzaGVkOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWNhdGVnb3J5IHtcXG4gICAgY29sb3I6IHJnYmEoMzEsIDMxLCAzMSwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTQuNHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTFweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMTZweDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1kZXNjcmlwdGlvbiB7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTguNnB4IFJvYm90bztcXG4gICAgd2lkdGg6IDI1NHB4O1xcbiAgICBjb2xvcjogIzBkMGQxZTsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1pbWFnZSB7XFxuICAgIHdpZHRoOiA5MHB4O1xcbiAgICBoZWlnaHQ6IDkxcHg7XFxuICAgIGJhY2tncm91bmQ6ICMxODc1RDAgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9idWxsZXRpbi1kZWZhdWx0LnBuZ1wiKSArIFwiKTsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1wcmljZSB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgYm90dG9tOiAtNTRweDtcXG4gICAgcmlnaHQ6IDA7XFxuICAgIGNvbG9yOiAjMjAyMDIwO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE2LjhweCBSb2JvdG87IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tYWN0aW9uIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBib3R0b206IDIwcHg7XFxuICAgIHJpZ2h0OiAxMTdweDtcXG4gICAgY29sb3I6ICMyMTIxMjE7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMTYuOHB4IFJvYm90bzsgfVxcbiAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24ge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgYm90dG9tOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICB6LWluZGV4OiAxOyB9XFxuICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbiB7XFxuICAgICAgd2lkdGg6IDEwMHB4O1xcbiAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjU5MTFEO1xcbiAgICAgIHRleHQtYWxpZ246IGNlbnRlcjsgfVxcbiAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbjpiZWZvcmUge1xcbiAgICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgIGJvdHRvbTogLTE0cHg7XFxuICAgICAgICBib3JkZXI6IDEzcHggc29saWQgI2U1N2IwMDtcXG4gICAgICAgIHotaW5kZXg6IC0xO1xcbiAgICAgICAgbGVmdDogLTIzcHg7XFxuICAgICAgICBib3JkZXItcmlnaHQtd2lkdGg6IDEuNWVtO1xcbiAgICAgICAgYm9yZGVyLWxlZnQtY29sb3I6IHRyYW5zcGFyZW50O1xcbiAgICAgICAgYm94LXNoYWRvdzogMnB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTsgfVxcbiAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbjphZnRlciB7XFxuICAgICAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgYm90dG9tOiAwO1xcbiAgICAgICAgYm9yZGVyOiAxM3B4IHNvbGlkICNGNTkxMUQ7XFxuICAgICAgICByaWdodDogLTEzcHg7XFxuICAgICAgICBib3JkZXItbGVmdC13aWR0aDogMDtcXG4gICAgICAgIGJvcmRlci1yaWdodC1jb2xvcjogdHJhbnNwYXJlbnQ7IH1cXG4gICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib24gPiAucmliYm9uLWNvbnRlbnQge1xcbiAgICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgICBmb250OiA0MDAgMTRweCAvIDI2cHggUm9ib3RvO1xcbiAgICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib24gPiAucmliYm9uLWNvbnRlbnQ6YmVmb3JlIHtcXG4gICAgICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICAgIGJvcmRlci1zdHlsZTogc29saWQ7XFxuICAgICAgICAgIGJvcmRlci1jb2xvcjogIzJCNEE2NyB0cmFuc3BhcmVudCB0cmFuc3BhcmVudCB0cmFuc3BhcmVudDtcXG4gICAgICAgICAgYm90dG9tOiAtMTRweDtcXG4gICAgICAgICAgbGVmdDogMDtcXG4gICAgICAgICAgYm9yZGVyLXdpZHRoOiAxZW0gMCAwIDFlbTsgfVxcblxcbi5jaGVja0JveCB7XFxuICB3aWR0aDogMjVweDtcXG4gIGhlaWdodDogMjVweDtcXG4gIGJvcmRlcjogMXB4IHNvbGlkIGdyZXk7XFxuICBib3JkZXItcmFkaXVzOiA1cHg7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG5cXG4uY2hlY2tlZCB7XFxuICBiYWNrZ3JvdW5kOiAjMTg3NUQwIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICBib3JkZXItY29sb3I6ICMxODc1RDAgIWltcG9ydGFudDsgfVxcblxcbmRpdi5leGNsYW1hdGlvblBvaW50IHtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICBoZWlnaHQ6IDI3cHg7XFxuICB3aWR0aDogMjdweDtcXG4gIG1hcmdpbi10b3A6IDEwcHg7IH1cXG5cXG4uZG9sbGFyQmlsbCB7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2RvbGxhckJpbGwucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgaGVpZ2h0OiAyN3B4O1xcbiAgd2lkdGg6IDI3cHg7XFxuICBtYXJnaW4tdG9wOiAxMHB4OyB9XFxuXCIsIFwiXCJdKTtcblxuLy8gZXhwb3J0c1xuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vY3NzLWxvYWRlciEuL34vcG9zdGNzcy1sb2FkZXIhLi9+L3Nhc3MtbG9hZGVyIS4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDM4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYnVsbGV0aW4tZGVmYXVsdC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nXG4gKiogbW9kdWxlIGlkID0gMzlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9WLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVi5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0MFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2V4Y2xhbWF0aW9uUG9pbnQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9leGNsYW1hdGlvblBvaW50LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZG9sbGFyQmlsbC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2RvbGxhckJpbGwucG5nXG4gKiogbW9kdWxlIGlkID0gNDJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8vIHN0eWxlLWxvYWRlcjogQWRkcyBzb21lIGNzcyB0byB0aGUgRE9NIGJ5IGFkZGluZyBhIDxzdHlsZT4gdGFnXG5cbi8vIGxvYWQgdGhlIHN0eWxlc1xudmFyIGNvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZWRpdC1wcm9maWxlLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZWRpdC1wcm9maWxlLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9lZGl0LXByb2ZpbGUuc2Nzc1wiKTtcblx0XHRcdGlmKHR5cGVvZiBuZXdDb250ZW50ID09PSAnc3RyaW5nJykgbmV3Q29udGVudCA9IFtbbW9kdWxlLmlkLCBuZXdDb250ZW50LCAnJ11dO1xuXHRcdFx0dXBkYXRlKG5ld0NvbnRlbnQpO1xuXHRcdH0pO1xuXHR9XG5cdC8vIFdoZW4gdGhlIG1vZHVsZSBpcyBkaXNwb3NlZCwgcmVtb3ZlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0bW9kdWxlLmhvdC5kaXNwb3NlKGZ1bmN0aW9uKCkgeyB1cGRhdGUoKTsgfSk7XG59XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDQzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcIi52aWV3LWVkaXQtcHJvZmlsZSB7XFxuICB3aWR0aDogNjUycHg7XFxuICBtYXJnaW46IDAgYXV0bzsgfVxcbiAgLnZpZXctZWRpdC1wcm9maWxlIGgxIHtcXG4gICAgZm9udDogNzAwIDIycHggUm9ib3RvO1xcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7IH1cXG4gIC52aWV3LWVkaXQtcHJvZmlsZSAuZWRpdC1wcm9maWxlLWZvcm0ge1xcbiAgICBmb250OiA0MDAgMTZweCBSb2JvdG87IH1cXG4gICAgLnZpZXctZWRpdC1wcm9maWxlIC5lZGl0LXByb2ZpbGUtZm9ybSAuaW5wdXRGb3JtLCAudmlldy1lZGl0LXByb2ZpbGUgLmVkaXQtcHJvZmlsZS1mb3JtIC5pbnB1dEZvcm0tcmVxdWlyZWQsIC52aWV3LWVkaXQtcHJvZmlsZSAuZWRpdC1wcm9maWxlLWZvcm0gdGV4dGFyZWEge1xcbiAgICAgIHdpZHRoOiAxMDAlOyB9XFxuICAudmlldy1lZGl0LXByb2ZpbGUgLmVkaXQtcHJvZmlsZS1mb3JtLWNvbnRhY3RzLWNvbnRhaW5lciAuaW5wdXRGb3JtIHtcXG4gICAgd2lkdGg6IDk1JTtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrOyB9XFxuICAudmlldy1lZGl0LXByb2ZpbGUgLmVkaXQtcHJvZmlsZS1mb3JtLWNvbnRhY3RzLWNvbnRhaW5lciBidXR0b24ge1xcbiAgICB3aWR0aDogMTZweDtcXG4gICAgaGVpZ2h0OiAxNnB4O1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgIGJhY2tncm91bmQtc2l6ZTogY29udGFpbjsgfVxcbiAgLnZpZXctZWRpdC1wcm9maWxlIC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvIHtcXG4gICAgZmxvYXQ6IHJpZ2h0OyB9XFxuICAgIC52aWV3LWVkaXQtcHJvZmlsZSAuZWRpdC1wcm9maWxlLWZvcm0tZm90byBpbnB1dCB7XFxuICAgICAgd2lkdGg6IDYwJTsgfVxcbiAgICAudmlldy1lZGl0LXByb2ZpbGUgLmVkaXQtcHJvZmlsZS1mb3JtLWZvdG8gLmVkaXQtcHJvZmlsZS1mb3JtLWZvdG8tcHJldmlldyB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9hdmF0YXIuanBnXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgICBiYWNrZ3JvdW5kLXBvc2l0aW9uOiBjZW50ZXIgY2VudGVyO1xcbiAgICAgIGJhY2tncm91bmQtc2l6ZTogY29udGFpbjtcXG4gICAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgICAgd2lkdGg6IDMycHg7XFxuICAgICAgaGVpZ2h0OiAzMnB4OyB9XFxuICAudmlldy1lZGl0LXByb2ZpbGUgLnNvY2lhbC1saW5rLWNvbnRhaW5lciB7XFxuICAgIGhlaWdodDogYXV0bzsgfVxcbiAgICAudmlldy1lZGl0LXByb2ZpbGUgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXYge1xcbiAgICAgIHdpZHRoOiAyNHB4O1xcbiAgICAgIGhlaWdodDogMjRweDtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tcmlnaHQ6IDQ3cHg7IH1cXG5cXG4udXBsb2FkRmlsZUZvcm0ge1xcbiAgdmlzaWJpbGl0eTogaGlkZGVuOyB9XFxuICAudXBsb2FkRmlsZUZvcm0gaW5wdXQge1xcbiAgICB3aWR0aDogMHB4O1xcbiAgICBoZWlnaHQ6IDBweDsgfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDQ0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQ1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYXZhdGFyLmpwZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYXZhdGFyLmpwZ1xuICoqIG1vZHVsZSBpZCA9IDQ2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL3Byb2ZpbGUuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwiLnZpZXctcHJvZmlsZS1jb250YWluZXIge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI2ZmZjtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBwYWRkaW5nOiA0MHB4IDEyN3B4IDBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDtcXG4gIHdpZHRoOiAxMTAzcHg7XFxuICBmb250OiA0MDAgMTZweC8yNHB4IFJvYm90bzsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaDEge1xcbiAgICBwYWRkaW5nLWJvdHRvbTogMTZweDtcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBmb250OiA0MDAgMjJweC8yNnB4IFJvYm90bzsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaW5wdXQ6LW1vei1yZWFkLW9ubHkge1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lOyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDpyZWFkLW9ubHkge1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lOyB9XFxuICAgIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0Oi1tb3otcmVhZC1vbmx5Om5vdCg6bGFzdC1vZi10eXBlKSB7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDpyZWFkLW9ubHk6bm90KDpsYXN0LW9mLXR5cGUpIHtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciAuc29jaWFsLWxpbmstY29udGFpbmVyIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcblxcbi5wcm9maWxlLWNvbnRhaW5lcnMtd3JhcCB7XFxuICB3aWR0aDogODQ5cHg7XFxuICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5wcm9maWxlLWxlZnQtY29udGFpbmVyLCAucHJvZmlsZS1yaWdodC1jb250YWluZXIsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0LCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQsIC5wcm9maWxlLWluZm8ge1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLWxlZnQtY29udGFpbmVyOjphZnRlciwgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyOjphZnRlciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLWxlZnQ6OmFmdGVyLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQ6OmFmdGVyLCAucHJvZmlsZS1pbmZvOjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4udmlldy1wcm9maWxlOjphZnRlciwgLnZpZXctcHJvZmlsZS1jb250YWluZXI6OmFmdGVyIHtcXG4gIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgZGlzcGxheTogdGFibGU7XFxuICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLWxlZnQtY29udGFpbmVyIHtcXG4gIHdpZHRoOiAxOTBweDtcXG4gIHBhZGRpbmc6IDAgNTJweDsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXIgLmJ0bi1ibHVlIHtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBoZWlnaHQ6IDM2cHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNnB4O1xcbiAgICBtYXJnaW46IDAgYXV0bztcXG4gICAgbWFyZ2luLXRvcDogOXB4OyB9XFxuICAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciA+IC5oei1jZW50ZXJpbmctd3JhcHBlciA+IC5wcm9maWxlLXNldHRpbmdzID4gLnNlbGVjdEJveCB7XFxuICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICBtYXJnaW4tYm90dG9tOiAyNXB4OyB9XFxuICAgIC5wcm9maWxlLWxlZnQtY29udGFpbmVyID4gLmh6LWNlbnRlcmluZy13cmFwcGVyID4gLnByb2ZpbGUtc2V0dGluZ3MgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgZm9udDogNDAwIDE2cHggLyAyNHB4IFJvYm90bzsgfVxcblxcbi5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciB7XFxuICB3aWR0aDogNDg1cHg7XFxuICBwYWRkaW5nLWxlZnQ6IDIwcHg7XFxuICBib3JkZXItbGVmdDogMXB4IHNvbGlkICNlYmViZWI7IH1cXG5cXG4ucHJvZmlsZS1pbmZvLXNlY3Rpb24tbGVmdCwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLXJpZ2h0IHtcXG4gIHdpZHRoOiA1MCU7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuXFxuLnByb2ZpbGUtYXZhdGFyIHtcXG4gIHdpZHRoOiAxNDVweDtcXG4gIGhlaWdodDogMjE1cHg7XFxuICBtYXJnaW46IDAgYXV0byA0MHB4O1xcbiAgcGFkZGluZy1ib3R0b206IDVweDtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYXZhdGFyLmpwZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGJhY2tncm91bmQtc2l6ZTogY29udGFpbjtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG5cXG4ucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZSB7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICBwYWRkaW5nOiAxM3B4IDA7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlOjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIHNwYW4ge1xcbiAgICBjb2xvcjogIzkyOTI5MjtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIGRpdiB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldF9ibGFjay5wbmdcIikgKyBcIik7XFxuICAgIHdpZHRoOiAxM3B4O1xcbiAgICBoZWlnaHQ6IDdweDsgfVxcblxcbi5zb2NpYWwtbGluay1jb250YWluZXIgZGl2IHtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIHdpZHRoOiAyNHB4O1xcbiAgaGVpZ2h0OiAyNHB4O1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5zb2NpYWwtbGluay1jb250YWluZXIgZGl2OjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG4gIC5zb2NpYWwtbGluay1jb250YWluZXIgZGl2Om5vdCg6bGFzdC1vZi10eXBlKSB7XFxuICAgIG1hcmdpbi1yaWdodDogMjNweDsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zIHtcXG4gIGRpc3BsYXk6IGJsb2NrO1xcbiAgd2lkdGg6IDEwMCU7XFxuICBtYXJnaW46IDMwcHggMDtcXG4gIGZsb2F0OiBsZWZ0OyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9uczo6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2LCAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50IHtcXG4gIGJvcmRlcjogMXB4IHNvbGlkICNlYmViZWI7XFxuICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IGRpdiB7XFxuICBjb2xvcjogIzkyOTI5MjtcXG4gIHdpZHRoOiA1MCU7XFxuICBwYWRkaW5nOiAyNHB4IDA7XFxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IGRpdjo6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IC5uYXYtaXRlbS1zZWxlY3RlZCB7XFxuICBjb2xvcjogIzdlYWVlMDtcXG4gIGJvcmRlci1ib3R0b206IDJweCBzb2xpZCAjZmY1MjUyOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudCB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjZjZmNmY2O1xcbiAgaGVpZ2h0OiAxODBweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0ge1xcbiAgd2lkdGg6IDEwMCU7XFxuICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI2ViZWJlYjtcXG4gIGhlaWdodDogMTIzcHg7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS1sb2dvIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICB0b3A6IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIHdpZHRoOiAyN3B4O1xcbiAgICBoZWlnaHQ6IDI3cHg7XFxuICAgIHBhZGRpbmc6IDAgMjNweCAyM3B4IDdweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9pY29uX3VzZXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICBiYWNrZ3JvdW5kLXBvc2l0aW9uOiBjZW50ZXI7XFxuICAgIG1hcmdpbi10b3A6IDI4cHg7IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSAuY29udGVudC1pdGVtLXRleHQge1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgd2lkdGg6IDM3M3B4O1xcbiAgICBtYXJnaW46IDI4cHggMzhweCAyNHB4IDA7XFxuICAgIGZvbnQ6IDQwMCAxMnB4LzE4cHggUm9ib3RvO1xcbiAgICBjb2xvcjogIzBjMGMxZTsgfVxcbiAgICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS10ZXh0OjphZnRlciB7XFxuICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgZGlzcGxheTogdGFibGU7XFxuICAgICAgY2xlYXI6IGJvdGg7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9jYXJldF9ibGFjay5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhcmV0X2JsYWNrLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQ5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvaWNvbl91c2VyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvaWNvbl91c2VyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDUwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIndXNlIHN0cmljdCdcblxuY29uc3QgZGVidWcgPSByZXF1aXJlKCcuLi9kYXRhL2RlYnVnJylcblxubGV0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgY3R4LkRFQlVHX0xFVkVMID0gZGVidWcuREVCVUdcblxuICBjb25zb2xlLmxvZyA9IChmdW5jdGlvbigpIHtcbiAgICBsZXQgbG9nID0gY29uc29sZS5sb2dcbiAgICBpZiggY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLkRFQlVHIClcbiAgICAgIHJldHVybiBsb2dcbiAgICBlbHNlIHJldHVybiAoKSA9PiB7fVxuICB9LmJpbmQoY3R4KSkoKVxuXG4gIGNvbnNvbGUuZXJyb3IgPSAoZnVuY3Rpb24oKSB7XG4gICAgbGV0IGVycm9yID0gY29uc29sZS5lcnJvclxuICAgIGlmKCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuREVCVUcgfHwgY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLk9OTFlfRVJST1JTIClcbiAgICAgIHJldHVybiBlcnJvclxuICAgIGVsc2UgcmV0dXJuICgpID0+IHt9XG4gIH0uYmluZChjdHgpKSgpXG5cbiAgY29uc29sZS5pbmZvID0gKGZ1bmN0aW9uKCkge1xuICAgIGxldCBpbmZvID0gY29uc29sZS5pbmZvXG4gICAgaWYoIGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5ERUJVRyB8fCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuT05MWV9FUlJPUlMgKVxuICAgICAgcmV0dXJuIGluZm9cbiAgICBlbHNlIHJldHVybiAoKSA9PiB7fVxuICB9LmJpbmQoY3R4KSkoKVxuXG4gIC8qICDQlNC70Y8g0YXQvtGF0LzRiyAqL1xuICB3aW5kb3cubHMgPSBcIllvdSd2ZSBtaXNzZWQgYSB3aW5kb3csIGxvbCA9KVwiXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2xvZ2dlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcIkRFQlVHXCI6IDAsXG5cdFwiT05MWV9FUlJPUlNcIjogMSxcblx0XCJQUk9EVUNUSU9OXCI6IDJcbn07XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2RhdGEvZGVidWcuanNvblxuICoqIG1vZHVsZSBpZCA9IDUyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSB7XG4gIFwiL1wiIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2luZGV4Lmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9pbmRleCcpLFxuICAgIGNvbnRyb2xsZXJBczogXCJpbmRleFwiXG4gIH0sXG4gICcvNDAzJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9lcnJvcjQwMy5odG1sXCJcbiAgfSxcbiAgJy80MDQnIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2Vycm9yNDA0Lmh0bWxcIlxuICB9LFxuICAnLzUwMCcgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvZXJyb3I1MDAuaHRtbFwiXG4gIH0sXG4gICcvYnVsbGV0aW5EZXRhaWxzJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9idWxsZXRpbkRldGFpbHMuaHRtbFwiLFxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2J1bGxldGluRGV0YWlscycpLFxuICAgIGNvbnRyb2xsZXJBczogXCJiZGV0YWlsZWRcIlxuICB9LFxuICAnL2J1bGxldGluQWRkJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkJyksXG4gICAgY29udHJvbGxlckFzOiBcImJhZGRcIlxuICB9LFxuICAnL2xvZ2luJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvbG9naW4uaHRtbFwiLFxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2xvZ2luJyksXG4gICAgY29udHJvbGxlckFzOiBcImxvZ2luXCJcbiAgfSxcbiAgJy9yZWdpc3RlcicgOiB7XG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL3JlZ2lzdGVyLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9yZWdpc3RlcicpLFxuICAgIGNvbnRyb2xsZXJBczogXCJyZWdpc3RlclwiXG4gIH0sXG4gICcvZWRpdFByb2ZpbGUnIDoge1xuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9hdXRoZW50aWNhdGVkL2VkaXQtcHJvZmlsZS5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZScpLFxuICAgIGNvbnRyb2xsZXJBczogXCJwcm9maWxlXCJcbiAgfSxcbiAgJy9wcm9maWxlJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9wcm9maWxlLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUnKSxcbiAgICBjb250cm9sbGVyQXM6IFwicHJvZmlsZVwiXG4gIH0sXG4gICcvZmF2b3VyaXRlcycgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9mYXZvdXJpdGVzLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMnKSxcbiAgICBjb250cm9sbGVyQXM6IFwiZmF2b3VyaXRlXCJcbiAgfSxcbiAgJy9zZWFyY2hSZXN1bHRzJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9zZWFyY2hSZXN1bHRzLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9zZWFyY2hSZXN1bHRzJyksXG4gICAgY29udHJvbGxlckFzOiBcInNlYXJjaFJlc3VsdHNcIlxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL3JvdXRlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XG4gIFxuXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9pbmRleC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xuICBcbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2J1bGxldGluRGV0YWlscy5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xuXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XG5cbiAgdGhpcy5pbml0ID0gKCkgPT4ge1xuICAgIHRoaXMuZGIgPSAkc2NvcGUuJHBhcmVudC5kYlxuXG4gICAgdGhpcy5lbWFpbCA9IFwiXCJcbiAgICB0aGlzLnBhc3N3b3JkID0gXCJcIlxuXG4gICAgdGhpcy5lbWFpbFZhbGlkID0gdHJ1ZVxuICAgIHRoaXMucGFzc3dvcmRWYWxpZCA9IHRydWVcblxuICAgIHRoaXMubG9naW5FcnJvciA9IFwiXCJcblxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcbiAgICAgIGlmKGUud2hpY2ggPT0gMTMpIHRoaXMuc2VuZC5jYWxsKHRoaXMpXG4gICAgfS5iaW5kKHRoaXMpXG5cbiAgICBkb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdrZXl1cCcsIHRoaXMuaGFuZGxlcilcbiAgfVxuXG4gIHRoaXMuZGVsZXRlTGlzdG5lcnMgPSAoKSA9PiB7XG4gICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcigna2V5dXAnLCB0aGlzLmhhbmRsZXIpXG4gIH1cblxuICB0aGlzLnNlbmQgPSAoKSA9PiB7XG4gICAgZWUuZW1pdCh7IG5hbWUgOiBcImZvcm0tc3VibWl0XCIgfSlcbiAgICAvKlxuICAgICAgLSBHZXQgZGF0YVxuICAgICAgLSBWYWxpZGF0ZVxuICAgICAgLSBTaG93IGVycm9yc1xuICAgICAgLSBvclxuICAgICAgLSBHb3RvIGJkIGFuZCBzZW5kIGRhdGFcbiAgICAqL1xuICAgIC8vIHRoaXMuZGVsZXRlTGlzdG5lcnMoKVxuXG4gICAgaWYoIHRoaXMuZW1haWxWYWxpZCAmJiB0aGlzLnBhc3N3b3JkVmFsaWQgKSB7XG4gICAgICB0aGlzLmRiLmxvZ2luKHtcbiAgICAgICAgXCJlbWFpbFwiIDogdGhpcy5lbWFpbCxcbiAgICAgICAgXCJwYXNzd29yZFwiOiB0aGlzLnBhc3N3b3JkXG4gICAgICB9LCAoZXJyLCBkYXRhKSA9PiB7XG4gICAgICAgIGlmKGVycilcbiAgICAgICAgICAkc2NvcGUuJHBhcmVudC5kaXNwbGF5RXJyb3IoXCLQntGI0LjQsdC60LAg0LDQstGC0L7RgNC40LfQsNGG0LjQuCwg0L/RgNC+0LLQtdGA0YzRgtC1INCy0LDRiNC4INC00LDQvdC90YvQtVwiKVxuICAgICAgICBlbHNlIHtcbiAgICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcbiAgICAgICAgICB0aGlzLmRiLnNhdmVVc2VyRGF0YShkYXRhKVxuICAgICAgICAgICRzY29wZS5yZWRpcmVjdFRvVXJsKCcvcHJvZmlsZScpXG4gICAgICAgIH1cbiAgICAgIH0pXG4gICAgfVxuICB9XG5cbiAgdGhpcy5lbWFpbElzVmFsaWQgPSBlbWFpbCA9PiB7XG4gICAgbGV0IGVycm9yID0gXCJcIlxuICAgIGlmKCFlbWFpbC5sZW5ndGgpICBlcnJvciArPSBcItCe0LHRj9C30LDRgtC10LvRjNC90L7QtSDQv9C+0LvQtS4gXCJcbiAgICBpZighL14oKFtePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSsoXFwuW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKykqKXwoXCIuK1wiKSlAKChcXFtbMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XSl8KChbYS16QS1aXFwtMC05XStcXC4pK1thLXpBLVpdezIsfSkpJC8udGVzdChlbWFpbCkpXG4gICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXG4gICAgcmV0dXJuIGVycm9yXG4gIH1cblxuICB0aGlzLnBhc3N3b3JkSXNWYWxpZCA9IHB3ZCA9PiB7XG4gICAgbGV0IGVycm9yID0gXCJcIlxuICAgIGlmKCFwd2QubGVuZ3RoKSBlcnJvciArPSBcItCe0LHRj9C30LDRgtC10LvRjNC90L7QtSDQv9C+0LvQtS4gXCJcbiAgICBpZiggcHdkLmxlbmd0aCA8IDYpIGVycm9yICs9IFwi0J/QsNGA0L7Qu9GMINC00L7Qu9C20LXQvSDRgdC+0LTQtdGA0LbQsNGC0Ywg0L3QtSDQvNC10L3QtdC1IDYg0YHQuNC80LLQvtC70L7Qsi4gXCJcbiAgICByZXR1cm4gZXJyb3JcbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvbG9naW4uanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkc2NvcGUsICRxKSB7XG5cbiAgdGhpcy5pbml0ID0gKCkgPT4ge1xuICAgIHRoaXMuZGIgPSAkc2NvcGUuJHBhcmVudC5kYlxuXG4gICAgdGhpcy5lbWFpbCA9IFwiXCJcbiAgICB0aGlzLnBhc3N3b3JkID0gXCJcIlxuICAgIHRoaXMucGFzc3dvcmQyID0gXCJcIlxuXG4gICAgdGhpcy5lbWFpbFZhbGlkID0gXCJcIlxuICAgIHRoaXMucGFzc3dvcmRWYWxpZCA9IFwiXCJcbiAgICB0aGlzLnBhc3N3b3JkMlZhbGlkID0gXCJcIlxuXG4gICAgdGhpcy5oYW5kbGVyID0gZnVuY3Rpb24oZSkge1xuICAgICAgaWYoZS53aGljaCA9PSAxMykgdGhpcy5zZW5kLmNhbGwodGhpcylcbiAgICB9LmJpbmQodGhpcylcblxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxuICB9XG5cbiAgdGhpcy5kZWxldGVMaXN0bmVycyA9ICgpID0+IHtcbiAgICBkb2N1bWVudC5yZW1vdmVFdmVudExpc3RlbmVyKCdrZXl1cCcsIHRoaXMuaGFuZGxlcilcbiAgfVxuXG4gIHRoaXMuc2VuZCA9ICgpID0+IHtcbiAgICAvKlxuICAgICAgLSBHZXQgZGF0YVxuICAgICAgLSBWYWxpZGF0ZVxuICAgICAgLSBTaG93IGVycm9yc1xuICAgICAgLSBvclxuICAgICAgLSBHb3RvIGJkIGFuZCBzZW5kIGRhdGFcbiAgICAqL1xuICAgIGlmKCB0aGlzLmVtYWlsVmFsaWQgJiYgdGhpcy5wYXNzd29yZFZhbGlkICYmIHRoaXMucGFzc3dvcmQyVmFsaWQgKSB7XG4gICAgICB0aGlzLmRiLmxvZ2luKHtcbiAgICAgICAgXCJlbWFpbFwiIDogdGhpcy5lbWFpbCxcbiAgICAgICAgXCJwYXNzd29yZFwiOiB0aGlzLnBhc3N3b3JkXG4gICAgICB9LCAoZXJyLCBkYXRhKSA9PiB7XG4gICAgICAgIHRoaXMuZGVsZXRlTGlzdG5lcnMoKVxuICAgICAgICBpZihlcnIpXG4gICAgICAgICAgJHNjb3BlLiRwYXJlbnQucmVkaXJlY3RUb1VybCgnLzUwMCcpXG4gICAgICAgIGVsc2Uge1xuICAgICAgICAgIC8qIFNhdmUgZGF0YSB0byBkYiAqL1xuICAgICAgICAgICRzY29wZS5yZWRpcmVjdFRvVXJsKCcvcHJvZmlsZScpXG4gICAgICAgICAgY29uc29sZS5sb2coZGF0YSlcbiAgICAgICAgfVxuICAgICAgfSlcbiAgICB9XG4gIH1cblxuICB0aGlzLmVtYWlsSXNWYWxpZCA9IGZ1bmN0aW9uKGVtYWlsKSB7XG4gICAgcmV0dXJuICRxKCBmdW5jdGlvbihyZXNvbHZlLCByZWplY3QpIHtcbiAgICAgIGxldCBlcnJvciA9IFwiXCJcbiAgICAgIGlmKCFlbWFpbC5sZW5ndGgpICBlcnJvciArPSBcItCe0LHRj9C30LDRgtC10LvRjNC90L7QtSDQv9C+0LvQtS4gXCJcbiAgICAgIGlmKCEvXigoW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKyhcXC5bXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKSopfChcIi4rXCIpKUAoKFxcW1swLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31dKXwoKFthLXpBLVpcXC0wLTldK1xcLikrW2EtekEtWl17Mix9KSkkLy50ZXN0KGVtYWlsKSlcbiAgICAgICAgZXJyb3IgKz0gXCLQndC10LLQtdGA0L3Ri9C5INGE0L7RgNC80LDRgiDQv9C+0YfRgtGLLiBcIlxuXG4gICAgICB3aW5kb3cuZGIuY2hlY2tFbWFpbChlbWFpbCwgZnVuY3Rpb24oZXJyLCBkYXRhKSB7XG4gICAgICAgIGlmKGVycikgcmVqZWN0KGVycilcbiAgICAgICAgZWxzZSB7XG4gICAgICAgICAgY29uc29sZS5sb2coZGF0YSlcbiAgICAgICAgICBpZihkYXRhICE9PSBcImZhbHNlXCIpXG4gICAgICAgICAgICBlcnJvciArPSBcItCi0LDQutCw0Y8g0L/QvtGH0YLQsCDRg9C20LUg0LjRgdC/0L7Qu9GM0LfRg9C10YLRgdGPLiBcIlxuICAgICAgICAgIHJlc29sdmUoZXJyb3IpXG4gICAgICAgIH1cbiAgICAgIH0uYmluZCh0aGlzKSlcbiAgICB9LmJpbmQodGhpcykpXG4gIH1cblxuICB0aGlzLnBhc3N3b3JkSXNWYWxpZCA9IHB3ZCA9PiB7XG4gICAgbGV0IGVycm9yID0gXCJcIlxuICAgIGlmKCFwd2QubGVuZ3RoKSBlcnJvciArPSBcItCe0LHRj9C30LDRgtC10LvRjNC90L7QtSDQv9C+0LvQtS4gXCJcbiAgICBpZihwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxuICAgIHJldHVybiBlcnJvclxuICB9XG5cbiAgdGhpcy5wYXNzd29yZDJJc1ZhbGlkID0gcHdkID0+IHtcbiAgICBsZXQgZXJyb3IgPSB0aGlzLnBhc3N3b3JkSXNWYWxpZChwd2QpXG4gICAgaWYodGhpcy5wYXNzd29yZCAhPT0gdGhpcy5wYXNzd29yZDIgKSBlcnJvciArPSBcItCf0LDRgNC+0LvQuCDQvdC1INGB0L7QstC/0LDQtNCw0Y7RglwiXG4gICAgcmV0dXJuIGVycm9yXG4gIH1cbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL3JlZ2lzdGVyLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCI7XG5cbmNsYXNzIFByb2ZpbGVDb250YWN0e1xuICAgIGNvbnN0cnVjdG9yKCkge1xuICAgICAgICB0aGlzLmNvbnRhY3RFbWFpbHMgPSBbJyddO1xuICAgICAgICB0aGlzLmNvbnRhY3RQaG9uZXMgPSBbJyddO1xuICAgIH1cbn1cblxuY2xhc3MgcHJvZmlsZUN0cmwge1xuICAgIGNvbnN0cnVjdG9yKCRzY29wZSl7XG4gICAgICAgIGlmKCEkc2NvcGUuJHBhcmVudC5kYi51c2VyKVxuICAgICAgICAgICRzY29wZS4kcGFyZW50LnJlZGlyZWN0VG9VcmwoJy80MDMnLCB0cnVlKVxuICAgICAgICBlbHNlIHRoaXMuY29udGFjdCA9IG5ldyBQcm9maWxlQ29udGFjdCgpO1xuICAgIH1cbiAgICB1cGRhdGVQcm9maWxlKCl7XG5cbiAgICB9XG4gICAgYWRkQ29udGFjdHMoJGV2ZW50LCB0eXBlKXtcbiAgICAgICAgdmFyIGFycjtcbiAgICAgICAgaWYodHlwZSA9PT0gJ2VtYWlsJykgYXJyID0gdGhpcy5jb250YWN0LmNvbnRhY3RFbWFpbHM7XG4gICAgICAgIGVsc2UgaWYodHlwZSA9PT0gJ3Bob25lJykgYXJyID0gdGhpcy5jb250YWN0LmNvbnRhY3RQaG9uZXM7XG4gICAgICAgIGVsc2UgcmV0dXJuO1xuXG4gICAgICAgIGlmKGFyci5sZW5ndGggPCA1ICYmIGFyclthcnIubGVuZ3RoIC0gMV0udHJpbSgpKSBhcnIucHVzaCgnJyk7XG4gICAgfVxuXG4gICAgZGVsZXRlQ29udGFjdHMoJGV2ZW50LCAkaW5kZXgsIHR5cGUpe1xuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscy5zcGxpY2UoJGluZGV4LCAxKTtcbiAgICAgICAgZWxzZSBpZih0eXBlID09PSAncGhvbmUnKSB0aGlzLmNvbnRhY3QuY29udGFjdFBob25lcy5zcGxpY2UoJGluZGV4LCAxKTtcbiAgICAgICAgZWxzZSByZXR1cm47XG4gICAgfVxufVxuXG5tb2R1bGUuZXhwb3J0cyA9IHByb2ZpbGVDdHJsO1xuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qc1xuICoqLyIsIlwidXNlIHN0cmljdFwiO1xuXG5jbGFzcyBQcm9maWxlQ29udGFjdHtcbiAgICBjb25zdHJ1Y3RvcigpIHtcbiAgICAgICAgdGhpcy5jb250YWN0RW1haWxzID0gWycnXTtcbiAgICAgICAgdGhpcy5jb250YWN0UGhvbmVzID0gWycnXTtcbiAgICB9XG59XG5cbmNsYXNzIHByb2ZpbGVDdHJsIHtcbiAgICBjb25zdHJ1Y3Rvcigkc2NvcGUpe1xuICAgICAgICBpZighJHNjb3BlLiRwYXJlbnQuZGIudXNlcilcbiAgICAgICAgICAkc2NvcGUuJHBhcmVudC5yZWRpcmVjdFRvVXJsKCcvNDAzJywgdHJ1ZSlcbiAgICAgICAgZWxzZSB0aGlzLmNvbnRhY3QgPSBuZXcgUHJvZmlsZUNvbnRhY3QoKTtcbiAgICB9XG4gICAgdXBkYXRlUHJvZmlsZSgpe1xuXG4gICAgfVxuICAgIGFkZENvbnRhY3RzKCRldmVudCwgdHlwZSl7XG4gICAgICAgIHZhciBhcnI7XG4gICAgICAgIGlmKHR5cGUgPT09ICdlbWFpbCcpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0RW1haWxzO1xuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzO1xuICAgICAgICBlbHNlIHJldHVybjtcblxuICAgICAgICBpZihhcnIubGVuZ3RoIDwgNSAmJiBhcnJbYXJyLmxlbmd0aCAtIDFdLnRyaW0oKSkgYXJyLnB1c2goJycpO1xuICAgIH1cblxuICAgIGRlbGV0ZUNvbnRhY3RzKCRldmVudCwgJGluZGV4LCB0eXBlKXtcbiAgICAgICAgaWYodHlwZSA9PT0gJ2VtYWlsJykgdGhpcy5jb250YWN0LmNvbnRhY3RFbWFpbHMuc3BsaWNlKCRpbmRleCwgMSk7XG4gICAgICAgIGVsc2UgaWYodHlwZSA9PT0gJ3Bob25lJykgdGhpcy5jb250YWN0LmNvbnRhY3RQaG9uZXMuc3BsaWNlKCRpbmRleCwgMSk7XG4gICAgICAgIGVsc2UgcmV0dXJuO1xuICAgIH1cbn1cblxubW9kdWxlLmV4cG9ydHMgPSBwcm9maWxlQ3RybDtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvcHJvZmlsZS5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xuXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9zZWFyY2hSZXN1bHRzLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcblxuLyog0JrQvtC90YLRgNC+0LvQu9C10YAg0LTQu9GPINGD0L/RgNCw0LLQu9C10L3QuNGPICDQvtGB0L3QvtCy0L3Ri9C8INGB0LrQtdC70LXRgtC+0Lwg0LTQvtC60YPQvNC10L3RgtCwICovXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRodHRwLCAkc2NvcGUsICRsb2NhdGlvbiwgJHRpbWVvdXQsICRjb29raWVzLCAkY29va2llU3RvcmUpIHtcbiAgY29uc29sZS5sb2coJ01haW4gY29udHJvbGxlciBsb2FkZWQnKVxuXG4gIC8qIFN0YW5kYWxvbmUgbW9kdWxlIGZvciBiZCAqL1xuICAkc2NvcGUuZGIgPSByZXF1aXJlKCcuLi9tb2R1bGVzL2RiJylcbiAgJHNjb3BlLmRiLmluaXQoJGh0dHApXG4gIHdpbmRvdy5kYiA9ICRzY29wZS5kYlxuXG4gIC8qIEluaXRpYWxpemUgZGF0YSAqL1xuICB0aGlzLmluaXQgPSBmdW5jdGlvbigpIHtcbiAgICAvKiB2YXJpYWJsZXMgZm9yIHRlc3RpbmcgKi9cbiAgICB0aGlzLmhlbGxvPVwiaGlcIlxuICAgIHRoaXMuYm9vbGVhbiA9IHRydWVcbiAgICB0aGlzLmxpc3QgPSBbMSwyLDNdXG5cbiAgICBjb25zb2xlLmxvZyhcIk1haW4gY29udHJvbGxlciBpbml0XCIpXG5cbiAgICB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzID0gKHJlcXVpcmUoJy4uL2RhdGEvc29ydGluZycpKS5pdGVtc1xuICAgIHRoaXMuY3VycmVudENhdGVnb3J5ID0gXCJOb25lXCJcbiAgICB0aGlzLnNvcnRpbmdJZCA9IDBcblxuICAgIGlmKHRoaXMuc29ydGluZ0NhdGVnb3JpZXMubGVuZ3RoKSB7XG4gICAgICBsZXQgdGl0bGUgPSB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzW3RoaXMuc29ydGluZ0lkXS50aXRsZVxuICAgICAgbGV0IGFyciA9IHRpdGxlLnNwbGl0KFwiXCIpXG4gICAgICB0aGlzLmFycm93ID0gYXJyLnBvcCgpXG4gICAgICBhcnIucG9wKClcblxuICAgICAgdGhpcy5jdXJyZW50Q2F0ZWdvcnkgPSBhcnIuam9pbihcIlwiKVxuICAgIH1cbiAgICBlbHNlIGNvbnNvbGUuZXJyb3IobmV3IEVycm9yKFwiTm8gc29ydGluZyBvcHRpb25zIGZvdW5kXCIpKVxuXG4gICAgdGhpcy5zaG93aW5nQ2F0ZWdvcmllcyA9IGZhbHNlXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gdHJ1ZVxuICB9XG5cbiAgdGhpcy5zaG93Q2F0ZWdvcmllcyA9ICgpID0+IHtcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gdHJ1ZVxuICAgICR0aW1lb3V0KCAoKSA9PiB7XG4gICAgICB0aGlzLnNldHRpbmdDYXQgPSBmYWxzZVxuICAgIH0sIDI1MClcblxuICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSB0cnVlXG4gIH1cblxuICAvKiBTb3J0aW5nIGluIGhlYWRlciAqL1xuICB0aGlzLnNldENhdGVnb3J5ID0gaWQgPT4ge1xuICAgIHRoaXMuc2V0dGluZ0NhdCA9IGZhbHNlXG5cbiAgICBsZXQgcmVzID0gdGhpcy5zb3J0aW5nQ2F0ZWdvcmllcy5maWx0ZXIoZWwgPT4gZWwuaWQgPT09IGlkIHwgMClbMF1cbiAgICB0aGlzLnNvcnRpbmdJZCA9IGlkXG5cbiAgICBpZihyZXMpIHtcbiAgICAgIGxldCBhcnIgPSByZXMudGl0bGUuc3BsaXQoXCJcIilcbiAgICAgIHRoaXMuYXJyb3cgPSBhcnIucG9wKClcbiAgICAgIGFyci5wb3AoKVxuXG4gICAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IGFyci5qb2luKFwiXCIpXG4gICAgfVxuXG4gIH1cblxuICB0aGlzLmxvZ291dCA9IGZ1bmN0aW9uKCkge1xuICAgICAgZGIudXNlckxvZ291dCgpXG4gICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnLycpXG4gIH1cblxuICAvKiBXYXRjaCBhbGwgY2xpY2sgb24gdGhlIGJvZHkgKi9cbiAgdGhpcy5jbGljayA9ICgpID0+IHtcbiAgICBpZih0aGlzLnNob3dpbmdDYXRlZ29yaWVzICYmICF0aGlzLnNldHRpbmdDYXQpXG4gICAgICB0aGlzLnNob3dpbmdDYXRlZ29yaWVzID0gZmFsc2VcbiAgfVxuXG5cbiAgLyogQ29ycmVjdCByZWRpcmVjdCB0byB1cmwgdGhyb3VnaCBhcHAgcm91dGVyKi9cbiAgJHNjb3BlLnJlZGlyZWN0VG9VcmwgPSAodXJsLCBpbW1lZGlhdGUpID0+IHtcbiAgICBpZihpbW1lZGlhdGUpXG4gICAgICAkbG9jYXRpb24ucGF0aCh1cmwpXG4gICAgZWxzZVxuICAgICAgJHRpbWVvdXQoKCkgPT4ge1xuICAgICAgICAkbG9jYXRpb24ucGF0aCh1cmwpXG4gICAgICB9LCAyNTApXG4gIH1cblxuICAvKiBVc2UgdGhpcyBtZXRob2QgZm9yIGdsb2JhbCBwdXJwb3NlIGVycm9ycyAqL1xuICAkc2NvcGUuZGlzcGxheUVycm9yID0gdGV4dCA9PiB7XG4gICAgYWxlcnQodGV4dClcbiAgICBjb25zb2xlLmVycm9yKG5ldyBFcnJvcihcInRleHRcIikpXG4gIH1cblxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvbWFpbi5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5sZXQgdXRpbHMgPSByZXF1aXJlKCcuL3V0aWxzJyksXG4gICAgY29uZmlnID0gcmVxdWlyZSgnLi4vZGF0YS9jb25maWcnKVxuXG5sZXQgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxuXG5tb2R1bGUuZXhwb3J0cy5pbml0ID0gZnVuY3Rpb24oJGh0dHApIHtcbiAgLyogaW5pdCBkYXRhIGZyb20gZGF0YWJhc2UgaGVyZSAqL1xuICBjdHguc2V0RGVmYXVsdHMoKVxuICB0aGlzLnRyYW5zcG9ydCA9ICRodHRwXG4gIGNvbnNvbGUubG9nKHRoaXMudHJhbnNwb3J0KVxuICAvLyBjdHguY2hlY2tVc2VySXNMb2dnZWQoZnVuY3Rpb24oZXJyLCBkYXRhKSB7XG4gIC8vICAgaWYoZXJyKSBjb25zb2xlLmVycm9yKGVycilcbiAgLy8gICBlbHNlIHtcbiAgLy8gICAgIGlmKGRhdGEpIGN0eC5zYXZlVXNlckRhdGEoZGF0YSlcbiAgLy8gICAgIGVsc2UgY29uc29sZS5sb2coXCJVc2VyIGlzIG5vdCBsb2dnZWQgaW5cIilcbiAgLy8gICB9XG4gIC8vIH0uYmluZCh0aGlzKSlcblxuICBjb25zb2xlLmxvZyhcIkRhdGFiYXNlIGluaXRpYWxpemVkXCIpXG59XG5cbm1vZHVsZS5leHBvcnRzLnNldERlZmF1bHRzID0gZnVuY3Rpb24oKSB7XG4gIGNvbnNvbGUubG9nKFwiRGF0YWJhc2UgOjogZGVmYXVsdHMgc2V0XCIpXG5cbiAgY3R4LmZhdm91cml0ZXMgPSBudWxsXG4gIGN0eC5tYWlsYm94ID0gbnVsbFxuICBjdHgudXNlciA9IG51bGxcbiAgY3R4Lm5vdGlmaWNhdGlvbnMgPSB7IGhlbGxvIDogXCJwcmV2ZWRcIiB9XG59XG5cblxubW9kdWxlLmV4cG9ydHMuY2hlY2tFbWFpbCA9IGZ1bmN0aW9uKGVtYWlsLCBjYikge1xuICB1dGlscy5yZXF1ZXN0KHtcbiAgICBcIm1ldGhvZFwiIDogY29uZmlnLnJvdXRlcy5jaGVja0VtYWlsLm1ldGhvZCxcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5jaGVja0VtYWlsLnVybCxcbiAgICBcImRhdGFcIiA6IGVtYWlsLFxuICAgIFwiaGVhZGVyc1wiIDoge1xuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwidGV4dC9wbGFpblwiXG4gICAgfVxuICB9KS50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSksIGVyciA9PiBjYihlcnIpKVxufVxuXG5tb2R1bGUuZXhwb3J0cy5sb2dpbiA9IGZ1bmN0aW9uKCBkYXRhLCBjYiApIHtcbiAgLy8gdXRpbHMucmVxdWVzdCh7XG4gIC8vICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMubG9naW4ubWV0aG9kLFxuICAvLyAgIFwidXJsXCIgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmxvZ2luLnVybCxcbiAgLy8gICBcImRhdGFcIiA6IGRhdGEsXG4gIC8vICAgXCJoZWFkZXJzXCIgOiB7XG4gIC8vICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXG4gIC8vICAgICBcIndpdGhDcmVkZW50aWFsc1wiIDogXCJ0cnVlXCJcbiAgLy8gICB9XG4gIC8vIH0pLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhKSwgZXJyID0+IGNiKGVycikpXG4gIGN0eC50cmFuc3BvcnQoe1xuICAgIG1ldGhvZCA6IGNvbmZpZy5yb3V0ZXMubG9naW4ubWV0aG9kLFxuICAgIHVybCA6IGNvbmZpZy5hcGkuYXV0aCArIGNvbmZpZy5yb3V0ZXMubG9naW4udXJsLFxuICAgIGRhdGEgOiBkYXRhLFxuICAgIGhlYWRlcnMgOiB7XG4gICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXG4gICAgfSxcbiAgICB3aXRoQ3JlZGVudGlhbHMgOiB0cnVlXG4gIH0pXG4gIC50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSkpXG4gIC5jYXRjaChjYilcbn1cblxuLyogVGhpcyBtZXRob2QgZG9lcyBzYXZlcyB1c2VyIGRhdGEgaW4gdGhpcyBtb2R1bGUgb25seSwgbm8gYmFja2VuZCBjb21tdW5pY2F0aW9uICovXG5tb2R1bGUuZXhwb3J0cy5zYXZlVXNlckRhdGEgPSBmdW5jdGlvbihkYXRhKSB7XG4gIGRhdGEgPSBkYXRhLmxlbmd0aCA/IEpTT04ucGFyc2UoZGF0YSkgOiBcIlwiXG4gIHRoaXMudXNlciA9IHt9XG4gIC8qIFRPRE86INGA0LDRgdC/0LDRgNGB0LjRgtGMINC00LDQvdC90YvQtSDQsiDQvtGB0LzRi9GB0LvQtdC90L3Ri9C1INC/0LXRgNC10LzQtdC90L3Ri9C1ICovXG5cbiAgY29uc29sZS5sb2coXCJEYXRhYmFzZTo6IFVzZXIgZGF0YSBzYXZlZCBzdWNjZXNzZnVsbHkoINGI0YPRgtC60LAgKSBcIilcbn1cblxubW9kdWxlLmV4cG9ydHMuY2hlY2tVc2VySXNMb2dnZWQgPSBmdW5jdGlvbiggY2IgKSB7XG4gIHV0aWxzLnJlcXVlc3Qoe1xuICAgIFwibWV0aG9kXCIgOiBjb25maWcucm91dGVzLmNoZWNrTG9nZ2VkLm1ldGhvZCxcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS51cmwgKyBjb25maWcucm91dGVzLmNoZWNrTG9nZ2VkLnVybFxuICB9KS50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSksIGVyciA9PiBjYihlcnIpKVxufVxuXG5tb2R1bGUuZXhwb3J0cy51c2VyTG9nb3V0ID0gZnVuY3Rpb24oKSB7XG4gIGN0eC5zZXREZWZhdWx0cygpXG5cbiAgdXRpbHMucmVxdWVzdCh7XG4gICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMubG9nb3V0Lm1ldGhvZCxcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5sb2dvdXQudXJsXG4gIH0pLnRoZW4oKCk9Pnt9LCAoKT0+e30pXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2RiLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG4vKlxuICBFeHBlY3Qgb3B0aW9ucyBvYmplY3QgbGlrZSB0aGlzOlxuICB7XG4gICAgXCJtZXRob2RcIiA6IFwiUE9TVFwiLFxuICAgIFwidXJsXCIgOiBcImh0dHA6Ly9zb21ldXJsLmNvbS9cIixcbiAgICBcImRhdGFcIiA6IFwiZGF0YVwiLFxuICAgIFwiaGVhZGVyc1wiIDoge1xuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwiYXBwbGljYXRpb24vanNvblwiLFxuICAgICAgXCJDb250ZW50LUxlbmd0aFwiIDogXCIxMDIzXCJcbiAgICB9XG4gIH1cblxuICBERUZBVUxUUzpcbiAgTWV0aG9kIC0gZGVmYXVsdCBpcyBHRVQsXG4gIFVSTCAtIHJlcXVpcmVkLFxuICBkYXRhIC0gb3B0aW9uYWwsXG4gIGhlYWRlcnMgLSBvcHRpb25hbFxuKi9cblxubW9kdWxlLmV4cG9ydHMucmVxdWVzdCA9IGZ1bmN0aW9uKG9wdGlvbnMpIHtcbiAgcmV0dXJuIG5ldyBQcm9taXNlKCAocmVzb2x2ZSwgcmVqZWN0KSA9PiB7XG4gICAgLyogU2V0dGluZyBkZWZhdWx0cyAqL1xuICAgIGxldCB7IG1ldGhvZD1cIkdFVFwiLCB1cmwsIGRhdGEsIGhlYWRlcnMgfSA9IG9wdGlvbnNcblxuICAgIC8qIFNvbWUgdmFsaWRhdGlvbiAqL1xuICAgIGlmKCF1cmwpXG4gICAgICByZXR1cm4gY29uc29sZS5lcnJvcihcIlVybCBub3Qgc3BlY2lmaWVkXCIpXG5cbiAgICBpZiggKG1ldGhvZCA9PSBcIlBPU1RcIiB8fCBtZXRob2QgPT0gXCJQVVRcIikgJiYgIWRhdGEpXG4gICAgICByZXR1cm4gY29uc29sZS5lcnJvcihcIk5vdGhpbmcgdG8gc2VuZCBoZXJlID0pXCIpXG5cbiAgICAvKiBTdGFydCBjb25zdHJ1Y3RpbmcgcmVxdWVzdCAqL1xuICAgIHZhciB4aHIgPSBuZXcgWE1MSHR0cFJlcXVlc3QoKVxuICAgIHhoci5vcGVuKG1ldGhvZCwgdXJsLCB0cnVlKVxuXG4gICAgaWYoaGVhZGVycylcbiAgICAgIGZvciggdmFyIHByb3AgaW4gaGVhZGVycylcbiAgICAgICAgeGhyLnNldFJlcXVlc3RIZWFkZXIocHJvcCwgaGVhZGVyc1twcm9wXSlcblxuICAgIGlmKGRhdGEgJiYgaGVhZGVyc1snQ29udGVudC1UeXBlJ10gIT09IFwidGV4dC9wbGFpblwiKVxuICAgICAgeGhyLnNlbmQoSlNPTi5zdHJpbmdpZnkoZGF0YSkpXG4gICAgZWxzZSBpZihkYXRhKVxuICAgICAgeGhyLnNlbmQoZGF0YSlcbiAgICBlbHNlXG4gICAgICB4aHIuc2VuZCgpXG5cblxuXG4gICAgeGhyLm9ucmVhZHlzdGF0ZWNoYW5nZSA9IGZ1bmN0aW9uKCkge1xuICAgICAgaWYgKHRoaXMucmVhZHlTdGF0ZSAhPSA0KVxuICAgICAgICByZXR1cm5cblxuICAgICAgaWYgKHRoaXMuc3RhdHVzICE9IDIwMClcbiAgICAgICAgcmV0dXJuIHJlamVjdCgnRXJyb3I6ICcgKyAodGhpcy5zdGF0dXMgPyBgKCR7dGhpcy5zdGF0dXN9KSAke3RoaXMuc3RhdHVzVGV4dH1gOiAncmVxdWVzdCBmYWlsJykpXG4gICAgICBlbHNlXG4gICAgICAgIHJldHVybiByZXNvbHZlKHRoaXMucmVzcG9uc2VUZXh0KVxuXG4gICAgfVxuXG4gIH0pXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL3V0aWxzLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSB7XG5cdFwiYXBpXCI6IHtcblx0XHRcInVybFwiOiBcImh0dHA6Ly85My43My4xMDkuMzg6ODA4NC9cIixcblx0XHRcImF1dGhcIjogXCJodHRwOi8vOTMuNzMuMTA5LjM4OjgwODMvXCJcblx0fSxcblx0XCJyb3V0ZXNcIjoge1xuXHRcdFwiZ2V0QnVsbGV0aW5zXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvcmVzdC9vZmZlcnNTZXJ2aWNlL29mZmVyL3JlYWQvYWxsXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwic2tpcFwiOiAwLFxuXHRcdFx0XHRcImxpbWl0XCI6IDIwXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImxvZ2luXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJsb2dpblwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIixcblx0XHRcdFx0XCJwYXNzd29yZFwiOiBcIjEyMzQ1NlwiXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImxvZ291dFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIlBPU1RcIixcblx0XHRcdFwidXJsXCI6IFwibG9nb3V0XCJcblx0XHR9LFxuXHRcdFwicmVnaXN0ZXJcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcInJlZ2lzdGVyXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwiZW1haWxcIjogXCJzc3MyQGdtYWlsLmNvbVwiLFxuXHRcdFx0XHRcInBhc3N3b3JkXCI6IFwiMTIzNDU2XCJcblx0XHRcdH1cblx0XHR9LFxuXHRcdFwiY2hlY2tFbWFpbFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIlBPU1RcIixcblx0XHRcdFwidXJsXCI6IFwibG9naW4vY2hlY2tFbWFpbFwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIlxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJjaGVja0xvZ2dlZFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIkdFVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvcmVzdC9wcm9maWxlc1NlcnZpY2UvcHJvZmlsZS9yZWFkL2xvZ2dlZEluUHJvZmlsZVwiXG5cdFx0fVxuXHR9XG59O1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9kYXRhL2NvbmZpZy5qc29uXG4gKiogbW9kdWxlIGlkID0gNzJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcIml0ZW1zXCI6IFtcblx0XHR7XG5cdFx0XHRcImlkXCI6IDEsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa+XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDIsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa0XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDMsXG5cdFx0XHRcInRpdGxlXCI6IFwi0LTQsNGC0LAgIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA0LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItC00LDRgtCwIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA1LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA2LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH1cblx0XVxufTtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vZGF0YS9zb3J0aW5nLmpzb25cbiAqKiBtb2R1bGUgaWQgPSA3M1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmNvbnN0IE1PRFVMRVMgPSB7XG4gIFwiY2hlY2tib3hcIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9jaGVja2JveCcpLFxuICBcIm5pY2VCdXR0b25cIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9uaWNlQnV0dG9uJyksXG4gIFwidGV4dFwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL3RleHQnKSxcbiAgXCJzZWxlY3RCb3hcIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9zZWxlY3RCb3gnKVxufVxuXG53aW5kb3cuZWUgPSByZXF1aXJlKCcuL2V2ZW50cycpXG5lZS5pbml0KClcblxubGV0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cblxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGFwcCA9PiB7XG4gIGZvcihsZXQga2V5IGluIE1PRFVMRVMpXG4gICAgYXBwLmRpcmVjdGl2ZShrZXksIE1PRFVMRVNba2V5XSlcblxuICByZXR1cm4gYXBwXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9pbmRleC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4ge1xuICAgIHJlc3RyaWN0OiBcIkVcIixcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxuICAgIHNjb3BlOiB7XG4gICAgICBuZ01vZGVsOiBcIj1cIlxuICAgIH0sXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiY2hlY2tCb3hcIj48L2Rpdj5gLFxuICAgIHJlcGxhY2U6IHRydWUsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCkge1xuICAgICAgbGV0IGVsID0gJGVsZW1lbnRbMF1cbiAgICAgIC8vLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2NoZWNrQm94JylbMF1cblxuICAgICAgaWYoJHNjb3BlLm5nTW9kZWwgJiYgIWVsLmNsYXNzTGlzdC5jb250YWlucygnY2hlY2tlZCcpKVxuICAgICAgICBlbC5jbGFzc0xpc3QuYWRkKCdjaGVja2VkJylcbiAgICAgIGVsc2UgaWYoISRzY29wZS5uZ01vZGVsICYmIGVsLmNsYXNzTGlzdC5jb250YWlucygnY2hlY2tlZCcpKVxuICAgICAgICBlbC5jbGFzc0xpc3QucmVtb3ZlKCdjaGVrZWQnKVxuXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGUgPT4ge1xuICAgICAgICBlbC5jbGFzc0xpc3QudG9nZ2xlKCdjaGVja2VkJylcbiAgICAgICAgJHNjb3BlLm5nTW9kZWwgPSAkc2NvcGUubmdNb2RlbCA/ICBmYWxzZSA6IHRydWVcbiAgICAgIH0pXG4gICAgfVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XG4gIHJldHVybiB7XG4gICAgcmVzdHJpY3Q6IFwiRVwiLFxuICAgIHRyYW5zY2x1ZGU6IHRydWUsXG4gICAgc2NvcGUgOiB7XG4gICAgICBjbGFzczogXCJAXCIsXG4gICAgICBuZ0NsaWNrOiBcIiZcIlxuICAgIH0sXG4gICAgcmVwbGFjZTogdHJ1ZSxcbiAgICB0ZW1wbGF0ZSA6IGA8ZGl2IGNsYXNzPVwie3sgY2xhc3MgfX1cIj5cbiAgICAgICAgICAgICAgICAgIDxzcGFuIGNsYXNzPVwiaW5rXCI+PC9zcGFuPlxuICAgICAgICAgICAgICAgICAgPG5nLXRyYW5zY2x1ZGUgc3R5bGU9XCJkaXNwbGF5OmJsb2NrOyB3aWR0aDoxMDAlOyBoZWlnaHQ6aW5oZXJpdDtcIj48L25nLXRyYW5zY2x1ZGU+XG4gICAgICAgICAgICAgICAgPC9kaXY+YCxcbiAgICBjb250cm9sbGVyOiBmdW5jdGlvbigkc2NvcGUsICRlbGVtZW50KSB7XG4gICAgICBsZXQgb25DbGljayA9IGZ1bmN0aW9uKGUpIHtcbiAgICAgICAgbGV0IGluayA9IHRoaXMuZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnaW5rJylbMF1cbiAgICAgICAgaW5rLmNsYXNzTGlzdC5yZW1vdmUoJ2FuaW1hdGUnKVxuXG4gICAgICAgIGxldCByZWN0ID0gdGhpcy5nZXRCb3VuZGluZ0NsaWVudFJlY3QoKVxuXG4gICAgICAgIGlmKCAhaW5rLmNsaWVudEhlaWdodCAmJiAhaW5rLmNsaWVudFdpZHRoICkge1xuICAgICAgICAgIGxldCBkID0gTWF0aC5tYXgodGhpcy5jbGllbnRXaWR0aCwgdGhpcy5jbGllbnRIZWlnaHQpXG4gICAgICAgICAgaW5rLnN0eWxlLmhlaWdodCA9IGluay5zdHlsZS53aWR0aCA9IGAke2R9cHhgXG4gICAgICAgIH1cblxuICAgICAgICBpbmsuc3R5bGUudG9wID0gYCR7ZS5jbGllbnRZIC0gcmVjdC50b3AgLSBpbmsuY2xpZW50SGVpZ2h0LzJ9cHhgXG4gICAgICAgIGluay5zdHlsZS5sZWZ0ID0gYCR7ZS5jbGllbnRYIC0gcmVjdC5sZWZ0IC1pbmsuY2xpZW50V2lkdGgvMn1weGBcbiAgICAgICAgaW5rLmNsYXNzTGlzdC5hZGQoJ2FuaW1hdGUnKVxuICAgICAgfVxuXG4gICAgICBpZigkc2NvcGUubmdDbGljaylcbiAgICAgICAgJGVsZW1lbnRbMF0uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCAkc2NvcGUubmdDbGljaylcbiAgICAgICAgXG4gICAgICAkZWxlbWVudFswXS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIG9uQ2xpY2spXG4gICAgfVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxuY29uc3QgQ09MT1JTID0ge1xuICAgIGJsdWU6IFwiIzE4NzVEMFwiLFxuICAgIHdoaXRlOiBcIndoaXRlXCJcbn1cblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHtcbiAgICByZXN0cmljdDogXCJFXCIsXG4gICAgcmVxdWlyZTogJ15uZ01vZGVsJyxcbiAgICBzY29wZSA6IHtcbiAgICAgIGxhYmVsOiBcIkBcIixcbiAgICAgIG5nTW9kZWw6IFwiPVwiLFxuICAgICAgY29sb3I6IFwiQFwiLFxuICAgICAgdHlwZTogXCJAXCIsXG4gICAgICB2YWxpZGF0ZTogXCI9XCIsXG4gICAgICBpc1ZhbGlkOiBcIj1cIlxuICAgIH0sXG4gICAgcmVwbGFjZTogdHJ1ZSxcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJpbnB1dEZvcm1cIj5cbiAgICAgICAgICAgICAgICAgPGxhYmVsPnt7IGxhYmVsIH19PC9sYWJlbD5cbiAgICAgICAgICAgICAgICAgPGlucHV0IHR5cGU9XCJ7eyB0eXBlIHx8ICd0ZXh0J319XCIgbmctbW9kZWw9XCJuZ01vZGVsXCI+XG4gICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJlcnJvcnNcIj48L2Rpdj5cbiAgICAgICAgICAgICAgIDwvZGl2PmAsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCwgJHRpbWVvdXQpIHtcbiAgICAgIGxldCBpZCA9IGVlLm9uKCdmb3JtLXN1Ym1pdCcsIHZhbGlkYXRlKVxuICAgICAgJHNjb3BlLiRvbihcIiRkZXN0cm95XCIsIGZ1bmN0aW9uKCkge1xuICAgICAgICBlZS5vZmYoaWQpXG4gICAgICB9LmJpbmQodGhpcykpXG5cbiAgICAgIGxldCBkZWZhdWx0Qm9yZGVyID0gXCJcIlxuXG4gICAgICBsZXQgZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgnaW5wdXQnKVswXSxcbiAgICAgICAgICBsYWJlbCA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlUYWdOYW1lKCdsYWJlbCcpWzBdLFxuICAgICAgICAgIGVycm9yID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnZXJyb3JzJylbMF1cblxuXG4gICAgICBmdW5jdGlvbiB2YWxpZGF0ZSgpIHtcbiAgICAgICAgaWYoJHNjb3BlLnZhbGlkYXRlKSB7XG4gICAgICAgICAgZnVuY3Rpb24gaGFuZGxlKGVycm9yKSB7XG4gICAgICAgICAgICBpZih0eXBlb2YgJHNjb3BlLmlzVmFsaWQgIT09IFwidW5kZWZpbmVkXCIpIHtcbiAgICAgICAgICAgICAgaWYoZXJyb3IubGVuZ3RoKVxuICAgICAgICAgICAgICAgICRzY29wZS5pc1ZhbGlkID0gZmFsc2VcbiAgICAgICAgICAgICAgZWxzZVxuICAgICAgICAgICAgICAgICRzY29wZS5pc1ZhbGlkID0gdHJ1ZVxuXG4gICAgICAgICAgICAgICRzY29wZS4kYXBwbHkoKVxuICAgICAgICAgICAgfVxuICAgICAgICAgIH1cblxuICAgICAgICAgIGxldCByZXNwID0gJHNjb3BlLnZhbGlkYXRlKGVsLnZhbHVlKVxuXG4gICAgICAgICAgaWYoIHR5cGVvZiByZXNwID09PSBcInN0cmluZ1wiKVxuICAgICAgICAgICAgaGFuZGxlKCBlcnJvci5pbm5lckhUTUwgPSByZXNwKVxuICAgICAgICAgIGVsc2VcbiAgICAgICAgICAgIHJlc3AudGhlbiggZnVuY3Rpb24oZGF0YSkge1xuICAgICAgICAgICAgICAgIGVycm9yLmlubmVySFRNTCA9IGRhdGFcbiAgICAgICAgICAgICAgICBoYW5kbGUoZGF0YSlcbiAgICAgICAgICAgICAgfSwgY29uc29sZS5lcnJvcilcblxuXG4gICAgICAgIH1cbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gb25CbHVyKGUpIHtcbiAgICAgICAgaWYoICEkc2NvcGUubmdNb2RlbC5sZW5ndGgpXG4gICAgICAgICAgaGlkZUFuaW1hdGlvbigpXG5cbiAgICAgICAgICB2YWxpZGF0ZSgpXG4gICAgICB9XG5cbiAgICAgIGZ1bmN0aW9uIG9uRm9jdXMoZSkge1xuICAgICAgICBpZighJHNjb3BlLm5nTW9kZWwubGVuZ3RoKVxuICAgICAgICAgIGRpc3BsYXlBbmltYXRpb24oKVxuICAgICAgfVxuXG4gICAgICBmdW5jdGlvbiBkaXNwbGF5QW5pbWF0aW9uKCkge1xuICAgICAgICBsYWJlbC5zdHlsZS5jb2xvciA9IENPTE9SU1skc2NvcGUuY29sb3JdXG4gICAgICAgIGlmKCFkZWZhdWx0Qm9yZGVyLmxlbmd0aCkge1xuICAgICAgICAgIGRlZmF1bHRCb3JkZXIgPSB3aW5kb3cuZ2V0Q29tcHV0ZWRTdHlsZShsYWJlbC5wYXJlbnROb2RlKS5ib3JkZXJCb3R0b21cbiAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICBsYWJlbC5wYXJlbnROb2RlLnN0eWxlLmJvcmRlckJvdHRvbSA9IGAycHggc29saWQgJHtDT0xPUlNbJHNjb3BlLmNvbG9yXX1gXG4gICAgICAgIH1cblxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QuYWRkKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gaGlkZUFuaW1hdGlvbigpIHtcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXG4gICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gZGVmYXVsdEJvcmRlclxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QucmVtb3ZlKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgJHRpbWVvdXQoICgpID0+IHtcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXG4gICAgICAgICAgZGlzcGxheUFuaW1hdGlvbigpXG4gICAgICAgIGVsc2VcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcbiAgICAgIH0sIDI1MClcblxuXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdibHVyJywgb25CbHVyLmJpbmQodGhpcykpXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdmb2N1cycsIG9uRm9jdXMuYmluZCgkc2NvcGUpKVxuICAgIH1cbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0LmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHtcbiAgICByZXN0cmljdDogXCJFXCIsXG4gICAgcmVxdWlyZTogJ15uZ01vZGVsJyxcbiAgICBzY29wZToge1xuICAgICAgbmdNb2RlbDogXCI9XCIsXG4gICAgICBzaG93OiBcIkBcIlxuICAgIH0sXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwic2VsZWN0Qm94XCI+XG4gICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cImRlZmF1bHRWYWx1ZVwiIG5nLWhpZGU9XCJzaG93XCI+XG4gICAgICAgICAgICAgICAgICA8cD57eyBuZ01vZGVsIH19PC9wPlxuICAgICAgICAgICAgICAgIDwvZGl2PlxuICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJsaXN0T2ZWYWx1ZXNcIiBuZy1zaG93PVwic2hvd1wiPlxuICAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cImxpc3RJdGVtXCIgbmctcmVwZWF0PVwiaXRlbSBpbiBpdGVtc1wiIHZhbHVlPVwie3tpdGVtfX1cIj57eyBpdGVtIH19PC9kaXY+XG4gICAgICAgICAgICAgICAgPC9kaXY+XG4gICAgICAgICAgICAgIDwvZGl2PmAsXG4gICAgcmVwbGFjZTogdHJ1ZSxcbiAgICBjb250cm9sbGVyOiBmdW5jdGlvbigkc2NvcGUsICRlbGVtZW50LCAkdGltZW91dCkge1xuICAgICAgbGV0IGRlZmF1bHRWYWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdkZWZhdWx0VmFsdWUnKVswXSxcbiAgICAgICAgICBsaXN0T2ZWYWx1ZXMgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdsaXN0T2ZWYWx1ZXMnKVswXVxuXG4gICAgICAkdGltZW91dChmdW5jdGlvbigpIHtcbiAgICAgICAgZGVmYXVsdFZhbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGZ1bmN0aW9uKGUpIHtcbiAgICAgICAgICB0aGlzLnNob3cgPSB0cnVlXG5cbiAgICAgICAgICBkb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGhhbmRsZXIpXG4gICAgICAgIH0uYmluZCgkc2NvcGUpKVxuXG4gICAgICAgIGZ1bmN0aW9uIGhhbmRsZXIoZSkge1xuICAgICAgICAgIGlmKCAhKGUudGFyZ2V0ID09IGxpc3RPZlZhbHVlcyB8fFxuICAgICAgICAgICAgICAgIGUudGFyZ2V0LnBhcmVudE5vZGUgPT0gbGlzdE9mVmFsdWVzIHx8XG4gICAgICAgICAgICAgICAgZS50YXJnZXQgPT0gZGVmYXVsdFZhbCB8fFxuICAgICAgICAgICAgICAgIGUudGFyZ2V0LnBhcmVudE5vZGUgPT0gZGVmYXVsdFZhbCkgKSB7XG4gICAgICAgICAgICAkc2NvcGUuc2hvdyA9IGZhbHNlXG4gICAgICAgICAgICAkc2NvcGUuJGFwcGx5KClcbiAgICAgICAgICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcbiAgICAgICAgICB9XG4gICAgICAgIH1cblxuICAgICAgICBmb3IobGV0IHQ9MDt0PGxpc3RPZlZhbHVlcy5jaGlsZHJlbi5sZW5ndGg7IHQrKykge1xuXG4gICAgICAgICAgbGlzdE9mVmFsdWVzLmNoaWxkcmVuW3RdLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgZnVuY3Rpb24oZSkge1xuICAgICAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcignY2xpY2snLCBoYW5kbGVyKVxuICAgICAgICAgICAgdGhpcy5zaG93ID0gZmFsc2VcbiAgICAgICAgICAgIHRoaXMubmdNb2RlbCA9IGUudGFyZ2V0LmlubmVySFRNTFxuICAgICAgICAgIH0uYmluZCgkc2NvcGUpKVxuICAgICAgICB9XG4gICAgICB9LmJpbmQodGhpcyksIDEwMDApXG4gICAgfSxcbiAgICBsaW5rOiBmdW5jdGlvbihzY29wZSwgZWxlbWVudCwgYXR0cnMpIHtcbiAgICAgIHNjb3BlLml0ZW1zID0gSlNPTi5wYXJzZShhdHRycy5pdGVtcylcbiAgICB9XG4gIH1cbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvc2VsZWN0Qm94LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XG5cbmxldCBwcml2YXRlU2NvcGUgPSB7fVxuXG5tb2R1bGUuZXhwb3J0cy5pbml0ID0gZnVuY3Rpb24oKSB7XG4gIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzID0ge31cbiAgcHJpdmF0ZVNjb3BlLmhhbmRsZXJJZCA9IDBcblxuICBwcml2YXRlU2NvcGUuZ2V0SGFuZGxlcklkID0gZnVuY3Rpb24oKSB7XG4gICAgcmV0dXJuIHByaXZhdGVTY29wZS5oYW5kbGVySWQrK1xuICB9XG5cbiAgcHJpdmF0ZVNjb3BlLnJlZ2lzdGVySGFuZGxlciA9IGZ1bmN0aW9uKG5hbWUsIGhhbmRsZXIpIHtcbiAgICBpZighcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0pXG4gICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXSA9IFtdXG5cbiAgICBsZXQgaWQgPSBwcml2YXRlU2NvcGUuZ2V0SGFuZGxlcklkKClcbiAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXS5wdXNoKHtcbiAgICAgIGlkIDogaWQsXG4gICAgICBoYW5kbGVyIDogaGFuZGxlclxuICAgIH0pXG5cbiAgICByZXR1cm4gaWRcbiAgfVxuXG4gIHByaXZhdGVTY29wZS5oYW5kbGUgPSBmdW5jdGlvbihldmVudE5hbWUsIGRhdGEpIHtcbiAgICBpZihwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tldmVudE5hbWVdKVxuICAgICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbZXZlbnROYW1lXS5mb3JFYWNoKGggPT4gaC5oYW5kbGVyKGRhdGEpKVxuICB9XG5cbiAgcHJpdmF0ZVNjb3BlLnJlbW92ZUhhbmRsZXIgPSBmdW5jdGlvbihpZCkge1xuICAgIGZvcihsZXQga2V5IGluIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzKSB7XG4gICAgICBmb3IobGV0IHQgPTA7IHQ8IHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV0ubGVuZ3RoOyB0KyspIHtcbiAgICAgICAgaWYocHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNba2V5XVt0XS5pZCA9PSBpZCkge1xuICAgICAgICAgIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV0gPSBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldLmZpbHRlcihlbCA9PiBlbC5pZCAhPT0gaWQpXG4gICAgICAgICAgcmV0dXJuIHRydWVcbiAgICAgICAgfVxuICAgICAgfVxuICAgIH1cblxuICAgIHJldHVybiBmYWxzZVxuICB9XG5cbiAgcHJpdmF0ZVNjb3BlLnJlbW92ZUhhbmRsZXJCeU5hbWUgPSBmdW5jdGlvbihuYW1lKSB7XG4gICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0gPSBbXVxuICB9XG59XG5cblxubW9kdWxlLmV4cG9ydHMub24gPSBmdW5jdGlvbihldmVudE5hbWUsIGhhbmRsZXIpIHtcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZWdpc3RlckhhbmRsZXIoZXZlbnROYW1lLCBoYW5kbGVyKVxufVxuXG4vKiBSZW1vdmVzIGhhbmRsZXIgYnkgaWQqL1xubW9kdWxlLmV4cG9ydHMub2ZmID0gZnVuY3Rpb24oaWQpIHtcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyKGlkKVxufVxuXG4vKiBSZW1vdmVzIGFsbCBoYW5kbGVycyBieSBldmVudCBuYW1lICovXG5tb2R1bGUuZXhwb3J0cy5yZW1vdmUgPSBmdW5jdGlvbihuYW1lKSB7XG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlckJ5TmFtZShuYW1lKVxufVxuLypcbiAge1xuICAgIFwibmFtZVwiIDogXCJmb3JtLXN1Ym1pdFwiLFxuICAgIFwiZGF0YVwiIDogXCJ3aGF0ZXZlclwiIDw9PSBvcHRpb25hbFxuICB9XG4qL1xubW9kdWxlLmV4cG9ydHMuZW1pdCA9IGZ1bmN0aW9uKGV2ZW50KSB7XG4gIGxldCBuYW1lID0gZXZlbnQubmFtZSB8fCAoKCkgPT4geyB0aHJvdyBuZXcgRXJyb3IoXCJObyBldmVudCBuYW1lXCIpIH0pKClcbiAgbGV0IGRhdGEgPSBldmVudC5kYXRhIHx8IG51bGxcblxuICBwcml2YXRlU2NvcGUuaGFuZGxlKG5hbWUsIGRhdGEpXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9ldmVudHMuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvcnVwci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3J1cHIucG5nXG4gKiogbW9kdWxlIGlkID0gODBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9ydXBySG92ZXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9ydXBySG92ZXIucG5nXG4gKiogbW9kdWxlIGlkID0gODFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyJdLCJtYXBwaW5ncyI6IjtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ3RDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUlBO0FBREE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBOzs7Ozs7QUNuREE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNwQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7Ozs7O0FDUEE7Ozs7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2hEQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ3JQQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDUEE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FDcEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNQQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FDcEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNQQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUMvQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7Ozs7Ozs7O0FDSkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBbERBOzs7Ozs7OztBQ0ZBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FBT0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2xFQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FBT0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBQ0E7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUVBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNqRkE7QUFDQTs7Ozs7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFBQTtBQUNBO0FBQUE7QUFHQTtBQUNBOzs7QUFBQTs7O0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBOzs7QUFFQTtBQUNBO0FBR0E7Ozs7OztBQUdBOzs7Ozs7QUNsQ0E7QUFDQTs7Ozs7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFBQTtBQUNBO0FBQUE7QUFHQTtBQUNBOzs7QUFBQTs7O0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBOzs7QUFFQTtBQUNBO0FBR0E7Ozs7OztBQUdBOzs7Ozs7QUNsQ0E7QUFDQTtBQUNBOzs7Ozs7QUNGQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBOzs7Ozs7QUM3RkE7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFKQTtBQU9BO0FBQUE7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFHQTtBQVBBO0FBU0E7QUFBQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBR0E7QUFBQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTs7Ozs7O0FDMUZBO0FBQ0E7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFtQkE7QUFDQTtBQUNBO0FBREE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUNBO0FBR0E7QUFDQTtBQUFBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBREE7QUFDQTtBQVdBO0FBQ0E7QUFDQTtBQUVBO0FBS0E7QUFFQTs7Ozs7OztBQzVEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUM5Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ2pDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUpBO0FBQ0E7QUFNQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFJQTs7Ozs7O0FDbkJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBckJBO0FBdUJBOzs7Ozs7QUMxQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBakNBO0FBbUNBOzs7Ozs7QUN0Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBTkE7QUFRQTtBQUNBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBSUE7QUFDQTtBQUFBO0FBQUE7QUFFQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQXBCQTtBQXVCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQWhHQTtBQWtHQTs7Ozs7O0FDMUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFRQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFsREE7QUFvREE7Ozs7OztBQ3ZEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQU1BO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDMUVBOzs7Ozs7QUNBQTs7OyIsInNvdXJjZVJvb3QiOiIifQ==