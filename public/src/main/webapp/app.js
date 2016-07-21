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
	__webpack_require__(39);
	__webpack_require__(45);
	__webpack_require__(48);

	__webpack_require__(53)();

	var materials = __webpack_require__(55),
	    router = __webpack_require__(61);

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
	}]).controller('mainCtrl', ["$http", "$scope", "$location", "$timeout", "$cookies", "$cookieStore", __webpack_require__(71)]);

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
	var update = __webpack_require__(38)(content, {});
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
	exports.push([module.id, "body {\n  background-color: #ECECEC; }\n\nheader {\n  height: 70px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #1875D0; }\n\n.btn-blue, .btn-grey {\n  position: relative;\n  display: inline-block;\n  overflow: hidden;\n  border-radius: 5px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  cursor: pointer;\n  text-align: center; }\n\n.btn-grey {\n  background-color: #D8D8D8;\n  color: #868686;\n  font: 400 14px / 21px Roboto; }\n\n.container {\n  width: 1280px;\n  position: relative;\n  margin: 0 auto; }\n\n.clearfix:before, .clearfix:after {\n  content: \" \";\n  display: table; }\n\n.clearfix:after {\n  clear: both; }\n\n.ink {\n  display: block;\n  position: absolute;\n  background: rgba(0, 0, 0, 0.15);\n  border-radius: 100%;\n  -webkit-transform: scale(0);\n          transform: scale(0); }\n\n.ink.animate {\n  -webkit-animation: ripple .5s ease-in;\n          animation: ripple .5s ease-in; }\n\n@-webkit-keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n@keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n.headLeft {\n  padding-top: 5px;\n  display: inline-block;\n  width: calc(100% - 490px);\n  height: 70px;\n  box-sizing: border-box; }\n  .headLeft > .logo {\n    cursor: pointer;\n    border-radius: 50%;\n    float: left;\n    height: 60px;\n    width: 60px;\n    background: url(" + __webpack_require__(4) + ") no-repeat;\n    margin-left: 15px; }\n  .headLeft > .inputForm {\n    margin-top: 21px;\n    margin-left: 20px;\n    float: left;\n    border-color: #FDFDFD;\n    box-sizing: border-box;\n    height: auto;\n    width: 200px;\n    border-bottom: 1px solid #FDFDFD; }\n    .headLeft > .inputForm > label, .headLeft > .inputForm input {\n      color: #ffffff;\n      font: 400 14px Roboto; }\n  .headLeft > .selectBox {\n    float: left;\n    margin-top: 21px;\n    margin-left: 20px; }\n    .headLeft > .selectBox > .defaultValue {\n      background: none;\n      padding: 0 5px; }\n  .headLeft > .add {\n    cursor: pointer;\n    float: right;\n    height: 35px;\n    width: 200px;\n    margin-right: 20px;\n    margin-top: 10px;\n    background: url(" + __webpack_require__(5) + ") no-repeat center right 10px;\n    padding-left: 10px;\n    text-align: left;\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    border: 1px solid white;\n    -webkit-transition: all .15s;\n    transition: all .15s; }\n    .headLeft > .add > p {\n      color: #ffffff;\n      font: 400 14px / 35px Roboto; }\n\n.headRight {\n  float: right;\n  width: 490px;\n  height: 70px;\n  border-left: 1px solid grey;\n  box-sizing: border-box;\n  padding-top: 22px; }\n  .headRight > .mail {\n    height: 26px;\n    width: 33px;\n    cursor: pointer;\n    position: relative;\n    float: left;\n    background: url(" + __webpack_require__(6) + ") no-repeat center center;\n    margin-left: 20px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .mail > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -3px;\n      left: 32px;\n      cursor: default; }\n    .headRight > .mail:hover {\n      background: url(" + __webpack_require__(7) + ") no-repeat center center; }\n  .headRight > .bell {\n    height: 24px;\n    width: 23px;\n    cursor: pointer;\n    position: relative;\n    display: inline-block;\n    float: left;\n    margin-left: 30px;\n    background: url(" + __webpack_require__(8) + ") no-repeat center center;\n    border-radius: 15px 0 15px 10px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .bell > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -7px;\n      left: 22px;\n      cursor: default; }\n    .headRight > .bell:hover {\n      background: url(" + __webpack_require__(9) + ") no-repeat center center; }\n  .headRight > .services {\n    height: 27px;\n    width: 28px;\n    cursor: pointer;\n    float: right;\n    margin-right: 30px;\n    background: url(" + __webpack_require__(10) + ") no-repeat center center;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .services:hover {\n      background: url(" + __webpack_require__(11) + ") no-repeat center center; }\n  .headRight > .userName {\n    float: right;\n    margin-right: 25px;\n    background: url(" + __webpack_require__(12) + ") no-repeat center left;\n    padding-left: 30px;\n    max-width: 270px;\n    position: relative; }\n    .headRight > .userName > p {\n      color: #ffffff;\n      font: 400 14px / 27px Roboto;\n      cursor: pointer;\n      white-space: nowrap;\n      overflow: hidden;\n      text-overflow: ellipsis; }\n    .headRight > .userName > div {\n      background-color: #FDFDFD;\n      box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n      position: absolute;\n      top: 0;\n      width: 100%;\n      z-index: 1; }\n      .headRight > .userName > div > p {\n        cursor: pointer;\n        padding: 0 15px;\n        height: 48px;\n        font: 400 16px / 50px Roboto;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        box-sizing: border-box;\n        transition: all .25s; }\n        .headRight > .userName > div > p:hover {\n          background-color: #eeeeee; }\n  .headRight > .auth {\n    color: white;\n    float: right;\n    margin-right: 20px;\n    font: 400 14px / 26px Roboto; }\n    .headRight > .auth span {\n      cursor: pointer;\n      margin: 0 10px; }\n\n.inputSearch {\n  position: relative;\n  display: inline-block;\n  margin-top: 10px;\n  border-bottom: 1px solid white; }\n  .inputSearch > input {\n    border: none;\n    padding: 2px 0;\n    background-color: transparent;\n    outline: none;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto; }\n  .inputSearch > label {\n    position: absolute;\n    top: 0;\n    left: 0;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto;\n    cursor: text;\n    -webkit-transition: .5s;\n    transition: .5s; }\n\n.selectBox {\n  position: relative;\n  display: inline-block;\n  cursor: pointer; }\n  .selectBox > .defaultValue {\n    color: #ffffff;\n    font: 400 14px / 19px Roboto;\n    overflow: hidden;\n    border-bottom: 1px solid white;\n    background: url(" + __webpack_require__(13) + ") no-repeat center right 5px;\n    padding-right: 20px;\n    box-sizing: border-box; }\n    .selectBox > .defaultValue span {\n      font-size: 16px; }\n  .selectBox > .listOfValues {\n    position: absolute;\n    top: 0;\n    left: 0;\n    background-color: white;\n    z-index: 1;\n    box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n    -webkit-animation: animatetop .25s;\n            animation: animatetop .25s; }\n    .selectBox > .listOfValues > div {\n      cursor: pointer;\n      padding: 0 15px;\n      height: 50px;\n      width: 120px;\n      font: 400 16px / 50px Roboto;\n      -webkit-transition: all .25s;\n      transition: all .25s; }\n      .selectBox > .listOfValues > div:hover {\n        background-color: #eeeeee; }\n\n@-webkit-keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\n@keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\naside.bulletinDetails {\n  float: right;\n  margin-top: 5px; }\n\nsection.openAdvertert {\n  background-color: white;\n  float: right;\n  margin-right: 10px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 715px;\n  padding: 25px 100px 45px;\n  box-sizing: border-box;\n  margin-top: 5px; }\n  section.openAdvertert > h3 {\n    color: #202020;\n    font: 400 22px / 26px Roboto;\n    float: left; }\n  section.openAdvertert > .price {\n    color: #1f1f1f;\n    font: 400 18px / 26px Roboto;\n    float: right; }\n  section.openAdvertert > .checkBox {\n    float: right;\n    margin-right: 15px;\n    margin-top: -1px; }\n  section.openAdvertert > .breadCrumbs {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: left;\n    margin-top: 10px;\n    cursor: default; }\n  section.openAdvertert > .id {\n    color: rgba(32, 32, 32, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: right;\n    cursor: default;\n    margin-top: 10px; }\n  section.openAdvertert > .slider {\n    height: 120px;\n    position: relative;\n    margin-top: 10px;\n    float: left;\n    width: 100%;\n    margin-bottom: 10px; }\n    section.openAdvertert > .slider > div {\n      float: left;\n      margin-left: 10px;\n      height: 100%;\n      width: 165px; }\n      section.openAdvertert > .slider > div:nth-of-type(1) {\n        margin: 0; }\n      section.openAdvertert > .slider > div > img {\n        -o-object-fit: contain;\n           object-fit: contain;\n        width: 100%;\n        background-color: #F4F4F4;\n        height: 100%;\n        cursor: pointer; }\n    section.openAdvertert > .slider > .next {\n      position: absolute;\n      background: url(" + __webpack_require__(14) + ") no-repeat left center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      right: -25px; }\n    section.openAdvertert > .slider > .prev {\n      position: absolute;\n      background: url(" + __webpack_require__(15) + ") no-repeat right center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      left: -25px; }\n  section.openAdvertert > #mapForOneAdvertert {\n    float: left;\n    height: 230px;\n    width: 225px;\n    margin-bottom: 25px; }\n  section.openAdvertert > .descriptionAd {\n    color: #0c0c1e;\n    font: 400 12px / 18px Roboto;\n    margin-top: 15px;\n    margin-bottom: 20px; }\n  section.openAdvertert > .goToMap {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: left; }\n  section.openAdvertert > .allComments {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right;\n    margin-right: 50px; }\n  section.openAdvertert > .writeAReview {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right; }\n  section.openAdvertert > .nameUser {\n    color: #1976d2;\n    font: 400 14px / 18px Roboto;\n    float: left;\n    margin-left: 20px;\n    cursor: default;\n    margin-bottom: 35px; }\n  section.openAdvertert > .onOrOffLineUser {\n    float: left; }\n  section.openAdvertert .btn-grey {\n    float: right;\n    height: 35px;\n    width: 140px;\n    margin-right: 25px;\n    line-height: 35px; }\n  section.openAdvertert .btn-blue {\n    float: right;\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n\n.wrapForDiv {\n  width: 265px;\n  float: right;\n  overflow: hidden;\n  margin-bottom: 25px;\n  border: 1px solid #E9E9E9;\n  box-sizing: border-box; }\n  .wrapForDiv > ul.tab {\n    list-style-type: none;\n    height: 50px;\n    background-color: white;\n    border-bottom: 1px solid #E9E9E9;\n    box-sizing: border-box; }\n    .wrapForDiv > ul.tab > li {\n      float: left; }\n      .wrapForDiv > ul.tab > li > a {\n        color: #939393;\n        font: 400 14px / 50px Roboto;\n        display: block;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        text-align: center;\n        position: relative; }\n        .wrapForDiv > ul.tab > li > a:after {\n          content: '';\n          display: block;\n          position: absolute;\n          bottom: 0;\n          width: 0;\n          height: 2px;\n          background-color: #FD5151;\n          -webkit-transition: all .25s;\n          transition: all .25s; }\n        .wrapForDiv > ul.tab > li > a:focus, .wrapForDiv > ul.tab > li > a.active {\n          color: #7eafe1; }\n          .wrapForDiv > ul.tab > li > a:focus:after, .wrapForDiv > ul.tab > li > a.active:after {\n            width: 100%; }\n      .wrapForDiv > ul.tab > li:nth-of-type(1) > a {\n        width: 159px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(1) > a:after {\n          right: 0; }\n      .wrapForDiv > ul.tab > li:nth-of-type(2) > a {\n        width: 104px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(2) > a:after {\n          left: 0; }\n  .wrapForDiv > .featuresAndReviews {\n    height: 178px;\n    width: 282px;\n    background-color: #F4F4F4;\n    overflow: auto;\n    box-sizing: border-box; }\n    .wrapForDiv > .featuresAndReviews > .tabcontent {\n      display: none;\n      -webkit-animation: fadeEffect 1s;\n      animation: fadeEffect 1s; }\n      .wrapForDiv > .featuresAndReviews > .tabcontent.active {\n        display: block; }\n    .wrapForDiv > .featuresAndReviews > #reviews {\n      position: relative; }\n      .wrapForDiv > .featuresAndReviews > #reviews > div {\n        padding: 30px 20px 25px 65px;\n        position: relative;\n        border-bottom: 1px solid #E9E9E9; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div:nth-last-of-type(1) {\n          border: none; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > img {\n          position: absolute;\n          height: 30px;\n          width: 25px;\n          top: 35px;\n          left: 20px; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > p {\n          color: #0d0d1e;\n          font: 400 12px / 18px Roboto; }\n\n@-webkit-keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n@keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n.btn-blue {\n  background-color: #1875D0;\n  color: #ffffff;\n  font: 400 14px / 21px Roboto; }\n\n.onOrOffLineUser {\n  background-color: #00C652;\n  border: 1px solid white;\n  box-shadow: 1px 1px 3px 0px rgba(0, 0, 0, 0.65);\n  height: 18px;\n  width: 18px;\n  box-sizing: border-box;\n  border-radius: 50%; }\n\n.error-container {\n  width: 1103px;\n  margin: auto;\n  background-image: url(" + __webpack_require__(16) + ");\n  background-repeat: no-repeat;\n  background-position-x: right;\n  background-position-y: 80px;\n  min-height: 500px; }\n  .error-container h1 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    text-align: center;\n    margin-top: 65px; }\n  .error-container h2 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    margin-top: 189px; }\n  .error-container h3 {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 10px;\n    margin-bottom: 30px; }\n  .error-container > .btn-blue {\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n\n.clicked {\n  box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.4) !important;\n  margin-top: 12px !important; }\n\n.multiple {\n  width: 1150px;\n  margin: 5px auto 0; }\n  .multiple > .bulletin-short {\n    float: left;\n    width: 380px;\n    margin-left: 5px;\n    height: 165px;\n    padding: 15px; }\n    .multiple > .bulletin-short:nth-of-type(3n-2) {\n      margin-left: 0; }\n    .multiple > .bulletin-short:nth-of-type(1) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(2) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(3) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short > .bulletin-center-column {\n      float: none;\n      margin: 0;\n      position: absolute;\n      top: 15px;\n      right: 110px; }\n      .multiple > .bulletin-short > .bulletin-center-column > .bulletin-action {\n        bottom: -43px;\n        width: 110px;\n        right: -30px;\n        z-index: 1; }\n    .multiple > .bulletin-short > .bulletin-left-column > .bulletin-description {\n      color: #0c0c1e;\n      font: 400 12px / 18px Roboto;\n      width: 170px;\n      overflow: hidden;\n      height: 40px; }\n\n.red {\n  background-color: #F74A4A; }\n\n.inputForm-required {\n  position: relative;\n  padding: 6px 2px;\n  font: 400 16px / 24.8px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px solid #9b9b9b;\n  margin-top: 15px; }\n  .inputForm-required label {\n    position: absolute;\n    left: 2px;\n    top: 6px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n  .inputForm-required input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent; }\n\n.inputForm.required {\n  border-bottom: 1px solid #9b9b9b !important; }\n\n.inputForm {\n  position: relative;\n  font: 400 16px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px dotted #999999; }\n  .inputForm label {\n    position: absolute;\n    left: 5px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s;\n    top: -1px; }\n  .inputForm input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent;\n    position: relative;\n    padding: 1px 5px; }\n\n/* Text element animation */\n.textOut {\n  top: -15px !important;\n  font-size: 12px !important;\n  left: 5px !important; }\n\nsection.login, section.register {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  padding: 65px 230px;\n  width: 1100px;\n  margin: 5px auto 0;\n  box-sizing: border-box; }\n  section.login > h2, section.register > h2 {\n    text-align: center;\n    color: #202020;\n    font: 400 22px Roboto;\n    margin-bottom: 55px; }\n  section.login > .inputForm, section.register > .inputForm {\n    margin-bottom: 45px; }\n    section.login > .inputForm > label, section.login > .inputForm input, section.register > .inputForm > label, section.register > .inputForm input {\n      color: #9a9a9a;\n      font: 400 16px Roboto; }\n  section.login > .btn-blue, section.register > .btn-blue {\n    height: 35px;\n    width: 180px;\n    margin-left: 45px;\n    line-height: 35px; }\n    section.login > .btn-blue.vk, section.register > .btn-blue.vk {\n      background: #0186CF url(" + __webpack_require__(17) + ") no-repeat center left 10px;\n      margin-left: 0; }\n    section.login > .btn-blue.facebook, section.register > .btn-blue.facebook {\n      background: #3E50B3 url(" + __webpack_require__(18) + ") no-repeat center left 10px; }\n    section.login > .btn-blue.google, section.register > .btn-blue.google {\n      background: #FD3C00 url(" + __webpack_require__(19) + ") no-repeat center left 10px; }\n\nsection.bulletinAdd {\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #FDFDFD;\n  width: 1100px;\n  margin: 5px auto 0;\n  padding: 65px 225px;\n  box-sizing: border-box; }\n  section.bulletinAdd > h2 {\n    color: #1f1f1f;\n    font: 400 22px Roboto;\n    text-align: center;\n    margin-bottom: 40px; }\n  section.bulletinAdd > .selectBox {\n    margin-bottom: 45px;\n    margin-top: 15px; }\n    section.bulletinAdd > .selectBox > .defaultValue {\n      border-color: grey;\n      min-width: 195px;\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      padding-left: 5px; }\n  section.bulletinAdd > .file {\n    float: right;\n    position: relative;\n    background-color: #FDFDFD;\n    border-bottom: 1px solid #9e9e9e;\n    width: 410px;\n    padding-bottom: 5px; }\n    section.bulletinAdd > .file > input {\n      position: absolute;\n      z-index: -1; }\n    section.bulletinAdd > .file > p {\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      cursor: default;\n      float: left;\n      padding-top: 10px; }\n    section.bulletinAdd > .file > .btn-blue {\n      float: right;\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 30px;\n      width: 85px;\n      line-height: 30px; }\n  section.bulletinAdd > .inputForm {\n    margin-bottom: 45px; }\n    section.bulletinAdd > .inputForm > label, section.bulletinAdd > .inputForm input {\n      color: #999999;\n      font: 400 16px Roboto; }\n  section.bulletinAdd > .color {\n    margin-bottom: 40px; }\n    section.bulletinAdd > .color > p {\n      color: #999999;\n      font: 400 16px Roboto;\n      border-bottom: 1px dotted #999999;\n      width: 160px;\n      float: left;\n      height: 21px;\n      box-sizing: border-box; }\n    section.bulletinAdd > .color > div {\n      float: left;\n      margin-left: 19px;\n      margin-top: 3px;\n      width: 16px;\n      height: 15px;\n      cursor: pointer;\n      box-sizing: border-box;\n      outline: 1px solid transparent;\n      border: 1px solid white; }\n      section.bulletinAdd > .color > div.transparent {\n        border-color: #E9E9E9;\n        overflow: hidden; }\n        section.bulletinAdd > .color > div.transparent > .redStick {\n          -webkit-transform: rotate(-43deg);\n                  transform: rotate(-43deg);\n          background-color: red;\n          height: 2px;\n          margin-top: 5.5px;\n          width: 20px;\n          margin-left: -3px; }\n      section.bulletinAdd > .color > div.red {\n        background-color: red; }\n      section.bulletinAdd > .color > div.orange {\n        background-color: orange; }\n      section.bulletinAdd > .color > div.yellow {\n        background-color: yellow; }\n      section.bulletinAdd > .color > div.green {\n        background-color: green; }\n      section.bulletinAdd > .color > div.lightBlue {\n        background-color: lightBlue; }\n      section.bulletinAdd > .color > div.blue {\n        background-color: blue; }\n      section.bulletinAdd > .color > div.pink {\n        background-color: pink; }\n      section.bulletinAdd > .color > div.purple {\n        background-color: purple; }\n      section.bulletinAdd > .color > div.white {\n        background-color: white;\n        border-color: #E9E9E9; }\n      section.bulletinAdd > .color > div.gray {\n        background-color: gray; }\n      section.bulletinAdd > .color > div.black {\n        background-color: black; }\n      section.bulletinAdd > .color > div.brown {\n        background-color: brown; }\n      section.bulletinAdd > .color > div.multicolor {\n        /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#ff0000+0,ffff00+20,1dff00+40,00ffff+60,0400ff+80,ff00fa+100 */\n        background: #ff0000;\n        /* Old browsers */\n        /* FF3.6-15 */\n        background: -webkit-linear-gradient(left, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* Chrome10-25,Safari5.1-6 */\n        background: linear-gradient(to right, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */\n        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff0000', endColorstr='#ff00fa',GradientType=1 );\n        /* IE6-9 */ }\n      section.bulletinAdd > .color > div.active {\n        outline-color: red; }\n  section.bulletinAdd > .calendar, section.bulletinAdd .addCalendar {\n    height: 20px;\n    position: relative;\n    width: 230px;\n    background: url(" + __webpack_require__(20) + ") no-repeat center left;\n    padding-left: 45px;\n    box-sizing: border-box;\n    margin-bottom: 40px; }\n    section.bulletinAdd > .calendar > .defaultValue, section.bulletinAdd .addCalendar > .defaultValue {\n      border-bottom: 1px solid grey;\n      background: url(" + __webpack_require__(13) + ") no-repeat center right 5px;\n      cursor: pointer; }\n      section.bulletinAdd > .calendar > .defaultValue > p, section.bulletinAdd .addCalendar > .defaultValue > p {\n        text-align: left;\n        color: #262626;\n        font: 400 14px / 20px Roboto; }\n    section.bulletinAdd > .calendar > .listValue, section.bulletinAdd .addCalendar > .listValue {\n      display: none; }\n  section.bulletinAdd > .addCalendar {\n    background-image: url(" + __webpack_require__(21) + "); }\n    section.bulletinAdd > .addCalendar > .defaultValue {\n      border-bottom-color: blue;\n      background: none; }\n      section.bulletinAdd > .addCalendar > .defaultValue > p {\n        color: #1976d2;\n        font-size: 16px; }\n  section.bulletinAdd > .btn-blue {\n    height: 35px;\n    width: 180px;\n    line-height: 35px;\n    display: block;\n    margin: 0 auto; }\n\n.errors {\n  position: absolute;\n  font: 400 12px / 14px Roboto;\n  color: #dd2c00;\n  bottom: -17px; }\n\nnav {\n  width: 255px;\n  float: left;\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  margin-top: 5px; }\n  nav > .map {\n    background: url(" + __webpack_require__(22) + ") no-repeat;\n    height: 145px;\n    width: 255px; }\n    nav > .map > .inputForm {\n      width: 215px;\n      margin: 115px auto 0; }\n      nav > .map > .inputForm.required {\n        border-color: black !important; }\n      nav > .map > .inputForm > input, nav > .map > .inputForm label, nav > .map > .inputForm .textOut {\n        color: black !important; }\n  nav > ul {\n    list-style: none; }\n    nav > ul > li > p {\n      padding-left: 75px;\n      color: #263238;\n      font: 500 13px / 40px Roboto;\n      background: url(" + __webpack_require__(23) + ") no-repeat center right 20px;\n      cursor: pointer; }\n    nav > ul > li:hover {\n      background-color: #E6E6E6 !important; }\n    nav > ul > li:nth-of-type(1) {\n      background: url(" + __webpack_require__(24) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(2) {\n      background: url(" + __webpack_require__(25) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(3) {\n      background: url(" + __webpack_require__(26) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(4) {\n      background: url(" + __webpack_require__(27) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(5) {\n      background: url(" + __webpack_require__(28) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(6) {\n      background: url(" + __webpack_require__(29) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(7) {\n      background: url(" + __webpack_require__(30) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(8) {\n      background: url(" + __webpack_require__(31) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(9) {\n      background: url(" + __webpack_require__(32) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(10) {\n      background: url(" + __webpack_require__(33) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(11) {\n      background: url(" + __webpack_require__(34) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(12) {\n      background: url(" + __webpack_require__(35) + ") no-repeat left 25px center; }\n\n.navBtn {\n  width: 43px;\n  height: 49px;\n  position: absolute;\n  left: 0;\n  top: 90px;\n  cursor: pointer;\n  background: url(" + __webpack_require__(36) + ") no-repeat;\n  -webkit-transition: all .25s;\n  transition: all .25s;\n  z-index: 1; }\n  .navBtn:hover {\n    width: 60px;\n    background-image: url(" + __webpack_require__(37) + "); }\n", ""]);

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

	module.exports = __webpack_require__.p + "images/caretCalendar.png";

/***/ },
/* 14 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/next.png";

/***/ },
/* 15 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/prev.png";

/***/ },
/* 16 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/error_bg.png";

/***/ },
/* 17 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/vk.png";

/***/ },
/* 18 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/facebook.png";

/***/ },
/* 19 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/google.png";

/***/ },
/* 20 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/calendar.png";

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

	module.exports = __webpack_require__.p + "images/rupr.png";

/***/ },
/* 37 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/ruprHover.png";

/***/ },
/* 38 */
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
/* 39 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(40);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(38)(content, {});
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
/* 40 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, "@charset \"UTF-8\";\n#favourites {\n  width: 990px;\n  margin: 3px auto 0; }\n  #favourites .right-column {\n    width: 490px;\n    float: right; }\n  #favourites .left-column {\n    width: 490px;\n    float: left; }\n\n.bulletin-short {\n  position: relative;\n  padding: 20px 16px;\n  width: 490px;\n  margin-bottom: 7px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  box-sizing: border-box;\n  background-color: white;\n  /* Левая колонка */\n  /* Правая колонка */ }\n  .bulletin-short > div {\n    display: inline-block; }\n  .bulletin-short .bulletin-right-column {\n    float: right;\n    width: 90px;\n    position: relative; }\n    .bulletin-short .bulletin-right-column > .onOrOffLineUser {\n      position: absolute;\n      top: 5px;\n      left: 10px; }\n  .bulletin-short .bulletin-center-column {\n    float: right;\n    margin-right: 15px; }\n  .bulletin-short h3 {\n    margin: 0;\n    font: 400 20px / 24px Roboto;\n    color: #212121;\n    width: 206px;\n    cursor: pointer;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .bulletin-short h3:hover {\n      text-decoration: underline;\n      -webkit-text-decoration-color: gray;\n              text-decoration-color: gray;\n      -webkit-text-decoration-style: dashed;\n              text-decoration-style: dashed; }\n  .bulletin-short .bulletin-category {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 11px;\n    margin-bottom: 16px; }\n  .bulletin-short .bulletin-description {\n    font: 400 12px / 18.6px Roboto;\n    width: 254px;\n    color: #0d0d1e; }\n  .bulletin-short .bulletin-image {\n    width: 90px;\n    height: 91px;\n    background: #1875D0 url(" + __webpack_require__(41) + "); }\n  .bulletin-short .bulletin-price {\n    position: absolute;\n    bottom: -54px;\n    right: 0;\n    color: #202020;\n    font: 400 14px / 16.8px Roboto; }\n  .bulletin-short .bulletin-action {\n    position: absolute;\n    bottom: 20px;\n    right: 117px;\n    color: #212121;\n    font: 400 14px / 16.8px Roboto; }\n  .bulletin-short > .wrapRibbon {\n    position: absolute;\n    position: absolute;\n    bottom: 0;\n    left: 0;\n    z-index: 1; }\n    .bulletin-short > .wrapRibbon > .ribbon {\n      width: 100px;\n      position: relative;\n      background-color: #F5911D;\n      text-align: center; }\n      .bulletin-short > .wrapRibbon > .ribbon:before {\n        content: \"\";\n        position: absolute;\n        display: block;\n        bottom: -14px;\n        border: 13px solid #e57b00;\n        z-index: -1;\n        left: -23px;\n        border-right-width: 1.5em;\n        border-left-color: transparent;\n        box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n      .bulletin-short > .wrapRibbon > .ribbon:after {\n        content: \"\";\n        position: absolute;\n        display: block;\n        bottom: 0;\n        border: 13px solid #F5911D;\n        right: -13px;\n        border-left-width: 0;\n        border-right-color: transparent; }\n      .bulletin-short > .wrapRibbon > .ribbon > .ribbon-content {\n        color: #ffffff;\n        font: 400 14px / 26px Roboto;\n        cursor: default; }\n        .bulletin-short > .wrapRibbon > .ribbon > .ribbon-content:before {\n          content: \"\";\n          position: absolute;\n          display: block;\n          border-style: solid;\n          border-color: #2B4A67 transparent transparent transparent;\n          bottom: -14px;\n          left: 0;\n          border-width: 1em 0 0 1em; }\n\n.checkBox {\n  width: 25px;\n  height: 25px;\n  border: 1px solid grey;\n  border-radius: 5px;\n  cursor: pointer;\n  -webkit-transition: all .25s;\n  transition: all .25s; }\n\n.checked {\n  background: #1875D0 url(" + __webpack_require__(42) + ") no-repeat center center;\n  border-color: #1875D0 !important; }\n\ndiv.exclamationPoint {\n  background: url(" + __webpack_require__(43) + ") no-repeat center center;\n  height: 27px;\n  width: 27px;\n  margin-top: 10px; }\n\n.dollarBill {\n  background: url(" + __webpack_require__(44) + ") no-repeat center center;\n  height: 27px;\n  width: 27px;\n  margin-top: 10px; }\n", ""]);

	// exports


/***/ },
/* 41 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/bulletin-default.png";

/***/ },
/* 42 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/V.png";

/***/ },
/* 43 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/exclamationPoint.png";

/***/ },
/* 44 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/dollarBill.png";

/***/ },
/* 45 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(46);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(38)(content, {});
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
/* 46 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, "section.editProfile {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 1105px;\n  margin: 5px auto 40px;\n  padding: 65px 0 45px 0;\n  box-sizing: border-box; }\n  section.editProfile > .view-edit-profile {\n    width: 650px;\n    margin: 0 auto; }\n    section.editProfile > .view-edit-profile > h2 {\n      font: 700 22px Roboto;\n      text-align: center;\n      margin-bottom: 40px; }\n    section.editProfile > .view-edit-profile > .edit-profile-form > .selectBox {\n      float: left;\n      border-bottom: 1px solid #9e9e9e;\n      margin-top: 15px;\n      margin-bottom: 45px; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .selectBox > .defaultValue {\n        color: #9e9e9e;\n        font: 400 16px Roboto;\n        padding-left: 5px; }\n    section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto {\n      float: right;\n      position: relative;\n      border-bottom: 1px solid #9e9e9e;\n      width: 410px;\n      padding-bottom: 5px; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto > input {\n        position: absolute;\n        z-index: -1; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto > p {\n        color: #9e9e9e;\n        font: 400 16px Roboto;\n        float: left;\n        padding-top: 11px;\n        padding-left: 5px;\n        cursor: default; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto > .btn-blue {\n        box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n        height: 30px;\n        width: 85px;\n        float: right;\n        line-height: 30px; }\n    section.editProfile > .view-edit-profile .edit-profile-form-contacts-container .inputForm {\n      width: 95%;\n      display: inline-block; }\n    section.editProfile > .view-edit-profile .edit-profile-form-contacts-container button {\n      width: 16px;\n      height: 16px;\n      display: inline-block;\n      background: url(" + __webpack_require__(47) + ") no-repeat;\n      background-size: contain; }\n    section.editProfile > .view-edit-profile .social-link-container {\n      height: auto; }\n      section.editProfile > .view-edit-profile .social-link-container div {\n        width: 24px;\n        height: 24px;\n        float: left;\n        margin-right: 47px; }\n  section.editProfile .uploadFileForm {\n    visibility: hidden; }\n    section.editProfile .uploadFileForm input {\n      width: 0px;\n      height: 0px; }\n", ""]);

	// exports


/***/ },
/* 47 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/icon_close_blue.png";

/***/ },
/* 48 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(49);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(38)(content, {});
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
/* 49 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, ".view-profile-container {\n  background-color: #fff;\n  box-sizing: border-box;\n  padding: 40px 127px 30px;\n  margin: 5px auto 0;\n  width: 1103px;\n  font: 400 16px/24px Roboto;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n  .view-profile-container h1 {\n    padding-bottom: 16px;\n    text-align: center;\n    font: 400 22px/26px Roboto; }\n  .view-profile-container input:-moz-read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n  .view-profile-container input:read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n    .view-profile-container input:-moz-read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n    .view-profile-container input:read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n  .view-profile-container .social-link-container {\n    margin-bottom: 45px; }\n\n.profile-containers-wrap {\n  width: 849px;\n  margin: 0 auto; }\n\n.profile-left-container, .profile-right-container, .profile-info-section-left, .profile-info-section-right, .profile-info {\n  float: left; }\n  .profile-left-container::after, .profile-right-container::after, .profile-info-section-left::after, .profile-info-section-right::after, .profile-info::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.view-profile::after, .view-profile-container::after {\n  content: \"\";\n  display: table;\n  clear: both; }\n\n.profile-left-container {\n  width: 190px;\n  padding: 0 52px; }\n  .profile-left-container .btn-blue {\n    width: 180px;\n    height: 36px;\n    line-height: 36px;\n    margin: 0 auto;\n    margin-top: 9px; }\n  .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox {\n    display: block;\n    margin-bottom: 25px; }\n    .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox > .defaultValue {\n      color: #9e9e9e;\n      font: 400 16px / 24px Roboto; }\n\n.profile-right-container {\n  width: 485px;\n  padding-left: 20px;\n  border-left: 1px solid #ebebeb; }\n  .profile-right-container > .wrapForDiv {\n    width: auto;\n    float: none;\n    margin-bottom: 0; }\n    .profile-right-container > .wrapForDiv > ul.tab > li {\n      width: 50%; }\n      .profile-right-container > .wrapForDiv > ul.tab > li > a {\n        width: auto; }\n    .profile-right-container > .wrapForDiv > .featuresAndReviews {\n      width: 500px; }\n\n.profile-info-section-left, .profile-info-section-right {\n  width: 50%;\n  box-sizing: border-box; }\n\n.profile-avatar {\n  width: 145px;\n  height: 215px;\n  margin: 0 auto 40px;\n  padding-bottom: 5px;\n  background: url(" + __webpack_require__(50) + ") no-repeat center center;\n  background-size: contain;\n  box-sizing: border-box; }\n\n.profile-settings-dropdown .profile-settings-dropdown-title {\n  cursor: pointer;\n  padding: 13px 0; }\n  .profile-settings-dropdown .profile-settings-dropdown-title::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .profile-settings-dropdown .profile-settings-dropdown-title span {\n    color: #929292;\n    float: left; }\n  .profile-settings-dropdown .profile-settings-dropdown-title div {\n    float: right;\n    background: url(" + __webpack_require__(51) + ");\n    width: 13px;\n    height: 7px; }\n\n.social-link-container div {\n  cursor: pointer;\n  width: 24px;\n  height: 24px;\n  float: left; }\n  .social-link-container div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .social-link-container div:not(:last-of-type) {\n    margin-right: 23px; }\n\n.profile-messages-and-notifications {\n  display: block;\n  width: 100%;\n  margin: 30px 0;\n  float: left; }\n  .profile-messages-and-notifications::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav, .profile-messages-and-notifications-content {\n  border: 1px solid #ebebeb;\n  overflow: hidden; }\n\n.profile-messages-and-notifications-nav div {\n  color: #929292;\n  width: 50%;\n  padding: 24px 0;\n  text-align: center;\n  float: left; }\n  .profile-messages-and-notifications-nav div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav .nav-item-selected {\n  color: #7eaee0;\n  border-bottom: 2px solid #ff5252; }\n\n.profile-messages-and-notifications-content {\n  background-color: #f6f6f6;\n  height: 180px;\n  box-sizing: border-box; }\n\n.profile-messages-and-notifications-content-item {\n  width: 100%;\n  border-bottom: 1px solid #ebebeb;\n  height: 123px;\n  position: relative;\n  overflow: hidden; }\n  .profile-messages-and-notifications-content-item .content-item-logo {\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 27px;\n    height: 27px;\n    padding: 0 23px 23px 7px;\n    background: url(" + __webpack_require__(52) + ") no-repeat;\n    display: inline-block;\n    background-position: center;\n    margin-top: 28px; }\n  .profile-messages-and-notifications-content-item .content-item-text {\n    display: inline-block;\n    float: right;\n    width: 373px;\n    margin: 28px 38px 24px 0;\n    font: 400 12px/18px Roboto;\n    color: #0c0c1e; }\n    .profile-messages-and-notifications-content-item .content-item-text::after {\n      content: \"\";\n      display: table;\n      clear: both; }\n\n.profile-info {\n  margin-bottom: 30px; }\n", ""]);

	// exports


/***/ },
/* 50 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/avatar.jpg";

/***/ },
/* 51 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/caret_black.png";

/***/ },
/* 52 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/icon_user.png";

/***/ },
/* 53 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var debug = __webpack_require__(54);

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
/* 54 */
/***/ function(module, exports) {

	module.exports = {
		"DEBUG": 0,
		"ONLY_ERRORS": 1,
		"PRODUCTION": 2
	};

/***/ },
/* 55 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var MODULES = {
	  "checkbox": __webpack_require__(56),
	  "niceButton": __webpack_require__(57),
	  "text": __webpack_require__(58),
	  "selectBox": __webpack_require__(59)
	};

	window.ee = __webpack_require__(60);
	ee.init();

	var ctx = module.exports = {};

	module.exports.init = function (app) {
	  for (var key in MODULES) {
	    app.directive(key, MODULES[key]);
	  }return app;
		};

/***/ },
/* 56 */
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
/* 57 */
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
/* 58 */
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
/* 59 */
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
/* 60 */
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
/* 61 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	module.exports = {
	  "/": {
	    templateUrl: "templates/index.html",
	    controller: __webpack_require__(62),
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
	    controller: __webpack_require__(63),
	    controllerAs: "bdetailed"
	  },
	  '/bulletinAdd': {
	    templateUrl: "templates/authenticated/bulletinAdd.html",
	    controller: __webpack_require__(64),
	    controllerAs: "badd"
	  },
	  '/login': {
	    templateUrl: "templates/login.html",
	    controller: __webpack_require__(65),
	    controllerAs: "login"
	  },
	  '/register': {
	    templateUrl: "templates/register.html",
	    controller: __webpack_require__(66),
	    controllerAs: "register"
	  },
	  '/editProfile': {
	    templateUrl: "templates/authenticated/edit-profile.html",
	    controller: __webpack_require__(67),
	    controllerAs: "profile"
	  },
	  '/profile': {
	    templateUrl: "templates/authenticated/profile.html",
	    controller: __webpack_require__(68),
	    controllerAs: "profile"
	  },
	  '/favourites': {
	    templateUrl: "templates/authenticated/favourites.html",
	    controller: __webpack_require__(69),
	    controllerAs: "favourite"
	  },
	  '/searchResults': {
	    templateUrl: "templates/searchResults.html",
	    controller: __webpack_require__(70),
	    controllerAs: "searchResults"
	  }
		};

/***/ },
/* 62 */
/***/ function(module, exports) {

	"use strict";

		module.exports = function ($scope) {};

/***/ },
/* 63 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 64 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 65 */
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
/* 66 */
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
/* 67 */
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

	        //        if(!$scope.$parent.db.user)
	        //          $scope.$parent.redirectToUrl('/403', true)
	        //        else
	        this.contact = new ProfileContact();
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
/* 68 */
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
/* 69 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope) {};

/***/ },
/* 70 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 71 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	/* Контроллер для управления  основным скелетом документа */

	module.exports = function ($http, $scope, $location, $timeout, $cookies, $cookieStore) {
	  var _this = this;

	  console.log('Main controller loaded');

	  /* Standalone module for bd */
	  $scope.db = __webpack_require__(72);
	  $scope.db.init($http);
	  window.db = $scope.db;

	  /* Initialize data */
	  this.init = function () {
	    /* variables for testing */
	    this.hello = "hi";
	    this.boolean = true;
	    this.list = [1, 2, 3];

	    console.log("Main controller init");

	    this.sortingCategories = __webpack_require__(75).items;
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
/* 72 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var utils = __webpack_require__(73),
	    config = __webpack_require__(74);

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
/* 73 */
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
/* 74 */
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
/* 75 */
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

/***/ }
/******/ ]);
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXBwLmpzIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vL3dlYnBhY2svYm9vdHN0cmFwIDJhM2YzZTJkYTQwZTk4NTYzYzZiIiwid2VicGFjazovLy9hcHAuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Jhc2ljLnNjc3M/NGY1MyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvYmFzaWMuc2NzcyIsIndlYnBhY2s6Ly8vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzIiwid2VicGFjazovLy8uL2ltYWdlcy9sb2dvLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvbWFpbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL21haWxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYmVsbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvc2VydmljZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy91c2VyTmFtZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9uZXh0LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcHJldi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2Vycm9yX2JnLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvdmsucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9mYWNlYm9vay5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2dvb2dsZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhbGVuZGFyLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9tYXAucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9Gb3JBbmltYWxzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQnVzaW5lc3MucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL1RyYW5zcG9ydC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvSXNGcmVlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9CYXJ0ZXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9ydXByLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcnVwckhvdmVyLnBuZyIsIndlYnBhY2s6Ly8vLi9+L3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzcz9jZjI3Iiwid2VicGFjazovLy8uL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3MiLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9WLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2RvbGxhckJpbGwucG5nIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzcz9kYzJiIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvcHJvZmlsZS5zY3NzPzAxYTEiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL3Byb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYXZhdGFyLmpwZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9pY29uX3VzZXIucG5nIiwid2VicGFjazovLy9tb2R1bGVzL2xvZ2dlci5qcyIsIndlYnBhY2s6Ly8vLi9kYXRhL2RlYnVnLmpzb24iLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dC5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9zZWxlY3RCb3guanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2V2ZW50cy5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9yb3V0ZXIuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2luZGV4LmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvYnVsbGV0aW5BZGQuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2xvZ2luLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9yZWdpc3Rlci5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL3NlYXJjaFJlc3VsdHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL21haW4uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvZGIuanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvdXRpbHMuanMiLCJ3ZWJwYWNrOi8vLy4vZGF0YS9jb25maWcuanNvbiIsIndlYnBhY2s6Ly8vLi9kYXRhL3NvcnRpbmcuanNvbiJdLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSlcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcblxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0ZXhwb3J0czoge30sXG4gXHRcdFx0aWQ6IG1vZHVsZUlkLFxuIFx0XHRcdGxvYWRlZDogZmFsc2VcbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubG9hZGVkID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCJcIjtcblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXygwKTtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHdlYnBhY2svYm9vdHN0cmFwIDJhM2YzZTJkYTQwZTk4NTYzYzZiXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5yZXF1aXJlKFwiLi9zdHlsZXMvYmFzaWMuc2Nzc1wiKVxyXG5yZXF1aXJlKFwiLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXCIpXHJcbnJlcXVpcmUoXCIuL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzc1wiKVxyXG5yZXF1aXJlKFwiLi9zdHlsZXMvcHJvZmlsZS5zY3NzXCIpXHJcblxyXG5yZXF1aXJlKFwiLi9tb2R1bGVzL2xvZ2dlclwiKSgpXHJcblxyXG5jb25zdCBtYXRlcmlhbHMgPSByZXF1aXJlKCcuL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzJyksXHJcbiAgICAgIHJvdXRlciA9IHJlcXVpcmUoJy4vbW9kdWxlcy9yb3V0ZXInKVxyXG5cclxubGV0IGFwcCA9IGFuZ3VsYXIubW9kdWxlKCdndXAnLCBbJ25nUm91dGUnLCAnbmdDb29raWVzJ10pXHJcblxyXG4vLyBBcHAgY29uZmlnXHJcbmFwcFxyXG4gIC5jb25maWcoWyckcm91dGVQcm92aWRlcicsICckbG9jYXRpb25Qcm92aWRlcicsICckaHR0cFByb3ZpZGVyJywgZnVuY3Rpb24oICRyb3V0ZVByb3ZpZGVyLCAkbG9jYXRpb25Qcm92aWRlciwgJGh0dHBQcm92aWRlcil7XHJcbiAgICAkaHR0cFByb3ZpZGVyLmRlZmF1bHRzLnVzZVhEb21haW4gPSB0cnVlO1xyXG4gICAgZGVsZXRlICRodHRwUHJvdmlkZXIuZGVmYXVsdHMuaGVhZGVycy5jb21tb25bJ1gtUmVxdWVzdGVkLVdpdGgnXTtcclxuICAgIFxyXG4gICAgZm9yKGxldCBrZXkgaW4gcm91dGVyKVxyXG4gICAgICAkcm91dGVQcm92aWRlci53aGVuKGtleSwgcm91dGVyW2tleV0pXHJcblxyXG4gICAgJHJvdXRlUHJvdmlkZXIub3RoZXJ3aXNlKHtcclxuICAgICAgcmVkaXJlY3RUbzogJy80MDQnXHJcbiAgICB9KVxyXG5cclxuICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh7XHJcbiAgICAgIGVuYWJsZWQgOiB0cnVlLFxyXG4gICAgICByZXF1aXJlQmFzZSA6IGZhbHNlXHJcbiAgICB9KVxyXG4gIH1dKVxyXG4gIC5jb250cm9sbGVyKCdtYWluQ3RybCcsIFsgXCIkaHR0cFwiLCBcIiRzY29wZVwiLCBcIiRsb2NhdGlvblwiLCBcIiR0aW1lb3V0XCIsIFwiJGNvb2tpZXNcIiwgXCIkY29va2llU3RvcmVcIiwgcmVxdWlyZSgnLi9jb250cm9sbGVycy9tYWluJyldKVxyXG5cclxubWF0ZXJpYWxzXHJcbiAgLmluaXQoYXBwKVxyXG4gIC5ydW4oKVxyXG5cclxuICAvKiBFdmVudCBlbW1pdHRlciBleGFtcGxlcyAqL1xyXG4gIGxldCBpZCA9IGVlLm9uKCdtdWhhaGFoYScsIGZ1bmN0aW9uKGRhdGEpIHtcclxuICAgIGNvbnNvbGUubG9nKFwiYnVnYWdhc2hlY2hrb1wiKVxyXG4gICAgY29uc29sZS5sb2coZGF0YSlcclxuICB9KVxyXG5cclxuICBlZS5lbWl0KHtcclxuICAgIG5hbWUgOiBcIm11aGFoYWhhXCIsXHJcbiAgICBkYXRhIDogWzEsMiwzLDQsNV1cclxuICB9KVxyXG5cclxuICBlZS5vZmYoaWQpXHJcblxyXG4gIGVlLmVtaXQoe1xyXG4gICAgbmFtZSA6IFwibXVoYWhhaGFcIixcclxuICAgIGRhdGEgOiBbMSwyLDMsNCw1XVxyXG4gIH0pXHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGFwcC5qc1xuICoqLyIsIi8vIHN0eWxlLWxvYWRlcjogQWRkcyBzb21lIGNzcyB0byB0aGUgRE9NIGJ5IGFkZGluZyBhIDxzdHlsZT4gdGFnXG5cbi8vIGxvYWQgdGhlIHN0eWxlc1xudmFyIGNvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vYmFzaWMuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9iYXNpYy5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vYmFzaWMuc2Nzc1wiKTtcblx0XHRcdGlmKHR5cGVvZiBuZXdDb250ZW50ID09PSAnc3RyaW5nJykgbmV3Q29udGVudCA9IFtbbW9kdWxlLmlkLCBuZXdDb250ZW50LCAnJ11dO1xuXHRcdFx0dXBkYXRlKG5ld0NvbnRlbnQpO1xuXHRcdH0pO1xuXHR9XG5cdC8vIFdoZW4gdGhlIG1vZHVsZSBpcyBkaXNwb3NlZCwgcmVtb3ZlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0bW9kdWxlLmhvdC5kaXNwb3NlKGZ1bmN0aW9uKCkgeyB1cGRhdGUoKTsgfSk7XG59XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL3N0eWxlcy9iYXNpYy5zY3NzXG4gKiogbW9kdWxlIGlkID0gMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiZXhwb3J0cyA9IG1vZHVsZS5leHBvcnRzID0gcmVxdWlyZShcIi4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXCIpKCk7XG4vLyBpbXBvcnRzXG5cblxuLy8gbW9kdWxlXG5leHBvcnRzLnB1c2goW21vZHVsZS5pZCwgXCJib2R5IHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNFQ0VDRUM7IH1cXG5cXG5oZWFkZXIge1xcbiAgaGVpZ2h0OiA3MHB4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMxODc1RDA7IH1cXG5cXG4uYnRuLWJsdWUsIC5idG4tZ3JleSB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgYm9yZGVyLXJhZGl1czogNXB4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIHRleHQtYWxpZ246IGNlbnRlcjsgfVxcblxcbi5idG4tZ3JleSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRDhEOEQ4O1xcbiAgY29sb3I6ICM4Njg2ODY7XFxuICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvOyB9XFxuXFxuLmNvbnRhaW5lciB7XFxuICB3aWR0aDogMTI4MHB4O1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgbWFyZ2luOiAwIGF1dG87IH1cXG5cXG4uY2xlYXJmaXg6YmVmb3JlLCAuY2xlYXJmaXg6YWZ0ZXIge1xcbiAgY29udGVudDogXFxcIiBcXFwiO1xcbiAgZGlzcGxheTogdGFibGU7IH1cXG5cXG4uY2xlYXJmaXg6YWZ0ZXIge1xcbiAgY2xlYXI6IGJvdGg7IH1cXG5cXG4uaW5rIHtcXG4gIGRpc3BsYXk6IGJsb2NrO1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgYmFja2dyb3VuZDogcmdiYSgwLCAwLCAwLCAwLjE1KTtcXG4gIGJvcmRlci1yYWRpdXM6IDEwMCU7XFxuICAtd2Via2l0LXRyYW5zZm9ybTogc2NhbGUoMCk7XFxuICAgICAgICAgIHRyYW5zZm9ybTogc2NhbGUoMCk7IH1cXG5cXG4uaW5rLmFuaW1hdGUge1xcbiAgLXdlYmtpdC1hbmltYXRpb246IHJpcHBsZSAuNXMgZWFzZS1pbjtcXG4gICAgICAgICAgYW5pbWF0aW9uOiByaXBwbGUgLjVzIGVhc2UtaW47IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgcmlwcGxlIHtcXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAwO1xcbiAgICAtd2Via2l0LXRyYW5zZm9ybTogc2NhbGUoMi41KTtcXG4gICAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDIuNSk7IH0gfVxcblxcbkBrZXlmcmFtZXMgcmlwcGxlIHtcXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAwO1xcbiAgICAtd2Via2l0LXRyYW5zZm9ybTogc2NhbGUoMi41KTtcXG4gICAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDIuNSk7IH0gfVxcblxcbi5oZWFkTGVmdCB7XFxuICBwYWRkaW5nLXRvcDogNXB4O1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgd2lkdGg6IGNhbGMoMTAwJSAtIDQ5MHB4KTtcXG4gIGhlaWdodDogNzBweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIC5oZWFkTGVmdCA+IC5sb2dvIHtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBib3JkZXItcmFkaXVzOiA1MCU7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBoZWlnaHQ6IDYwcHg7XFxuICAgIHdpZHRoOiA2MHB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2xvZ28ucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgbWFyZ2luLWxlZnQ6IDE1cHg7IH1cXG4gIC5oZWFkTGVmdCA+IC5pbnB1dEZvcm0ge1xcbiAgICBtYXJnaW4tdG9wOiAyMXB4O1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGJvcmRlci1jb2xvcjogI0ZERkRGRDtcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gICAgaGVpZ2h0OiBhdXRvO1xcbiAgICB3aWR0aDogMjAwcHg7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjRkRGREZEOyB9XFxuICAgIC5oZWFkTGVmdCA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgLmhlYWRMZWZ0ID4gLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDE0cHggUm9ib3RvOyB9XFxuICAuaGVhZExlZnQgPiAuc2VsZWN0Qm94IHtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIG1hcmdpbi10b3A6IDIxcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiAyMHB4OyB9XFxuICAgIC5oZWFkTGVmdCA+IC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICBiYWNrZ3JvdW5kOiBub25lO1xcbiAgICAgIHBhZGRpbmc6IDAgNXB4OyB9XFxuICAuaGVhZExlZnQgPiAuYWRkIHtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDIwMHB4O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDIwcHg7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYWRkLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDEwcHg7XFxuICAgIHBhZGRpbmctbGVmdDogMTBweDtcXG4gICAgdGV4dC1hbGlnbjogbGVmdDtcXG4gICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgYm9yZGVyOiAxcHggc29saWQgd2hpdGU7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4xNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMTVzOyB9XFxuICAgIC5oZWFkTGVmdCA+IC5hZGQgPiBwIHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTRweCAvIDM1cHggUm9ib3RvOyB9XFxuXFxuLmhlYWRSaWdodCB7XFxuICBmbG9hdDogcmlnaHQ7XFxuICB3aWR0aDogNDkwcHg7XFxuICBoZWlnaHQ6IDcwcHg7XFxuICBib3JkZXItbGVmdDogMXB4IHNvbGlkIGdyZXk7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgcGFkZGluZy10b3A6IDIycHg7IH1cXG4gIC5oZWFkUmlnaHQgPiAubWFpbCB7XFxuICAgIGhlaWdodDogMjZweDtcXG4gICAgd2lkdGg6IDMzcHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9tYWlsLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAubWFpbCA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxMHB4IC8gMTVweCBSb2JvdG87XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJvdHRvbTogLTNweDtcXG4gICAgICBsZWZ0OiAzMnB4O1xcbiAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLm1haWw6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbWFpbF9zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLmJlbGwge1xcbiAgICBoZWlnaHQ6IDI0cHg7XFxuICAgIHdpZHRoOiAyM3B4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLWxlZnQ6IDMwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmVsbC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIGJvcmRlci1yYWRpdXM6IDE1cHggMCAxNXB4IDEwcHg7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuYmVsbCA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxMHB4IC8gMTVweCBSb2JvdG87XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJvdHRvbTogLTdweDtcXG4gICAgICBsZWZ0OiAyMnB4O1xcbiAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLmJlbGw6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmVsbF9zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzIHtcXG4gICAgaGVpZ2h0OiAyN3B4O1xcbiAgICB3aWR0aDogMjhweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMzBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9zZXJ2aWNlcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXM6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvc2VydmljZXNfc2hhZG93LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjsgfVxcbiAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyNXB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3VzZXJOYW1lLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQ7XFxuICAgIHBhZGRpbmctbGVmdDogMzBweDtcXG4gICAgbWF4LXdpZHRoOiAyNzBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBwIHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTRweCAvIDI3cHggUm9ib3RvO1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICB3aGl0ZS1zcGFjZTogbm93cmFwO1xcbiAgICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgICAgdGV4dC1vdmVyZmxvdzogZWxsaXBzaXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IGRpdiB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gICAgICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB0b3A6IDA7XFxuICAgICAgd2lkdGg6IDEwMCU7XFxuICAgICAgei1pbmRleDogMTsgfVxcbiAgICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBkaXYgPiBwIHtcXG4gICAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICAgIHBhZGRpbmc6IDAgMTVweDtcXG4gICAgICAgIGhlaWdodDogNDhweDtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNnB4IC8gNTBweCBSb2JvdG87XFxuICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBkaXYgPiBwOmhvdmVyIHtcXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI2VlZWVlZTsgfVxcbiAgLmhlYWRSaWdodCA+IC5hdXRoIHtcXG4gICAgY29sb3I6IHdoaXRlO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMjBweDtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyNnB4IFJvYm90bzsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLmF1dGggc3BhbiB7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIG1hcmdpbjogMCAxMHB4OyB9XFxuXFxuLmlucHV0U2VhcmNoIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIG1hcmdpbi10b3A6IDEwcHg7XFxuICBib3JkZXItYm90dG9tOiAxcHggc29saWQgd2hpdGU7IH1cXG4gIC5pbnB1dFNlYXJjaCA+IGlucHV0IHtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBwYWRkaW5nOiAycHggMDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7XFxuICAgIG91dGxpbmU6IG5vbmU7XFxuICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvOyB9XFxuICAuaW5wdXRTZWFyY2ggPiBsYWJlbCB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgdG9wOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiB0ZXh0O1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IC41cztcXG4gICAgdHJhbnNpdGlvbjogLjVzOyB9XFxuXFxuLnNlbGVjdEJveCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gIC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgY29sb3I6ICNmZmZmZmY7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMTlweCBSb2JvdG87XFxuICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCB3aGl0ZTtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDVweDtcXG4gICAgcGFkZGluZy1yaWdodDogMjBweDtcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSBzcGFuIHtcXG4gICAgICBmb250LXNpemU6IDE2cHg7IH1cXG4gIC5zZWxlY3RCb3ggPiAubGlzdE9mVmFsdWVzIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICB0b3A6IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgICB6LWluZGV4OiAxO1xcbiAgICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgICAtd2Via2l0LWFuaW1hdGlvbjogYW5pbWF0ZXRvcCAuMjVzO1xcbiAgICAgICAgICAgIGFuaW1hdGlvbjogYW5pbWF0ZXRvcCAuMjVzOyB9XFxuICAgIC5zZWxlY3RCb3ggPiAubGlzdE9mVmFsdWVzID4gZGl2IHtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgcGFkZGluZzogMCAxNXB4O1xcbiAgICAgIGhlaWdodDogNTBweDtcXG4gICAgICB3aWR0aDogMTIwcHg7XFxuICAgICAgZm9udDogNDAwIDE2cHggLyA1MHB4IFJvYm90bztcXG4gICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgLnNlbGVjdEJveCA+IC5saXN0T2ZWYWx1ZXMgPiBkaXY6aG92ZXIge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI2VlZWVlZTsgfVxcblxcbkAtd2Via2l0LWtleWZyYW1lcyBhbmltYXRldG9wIHtcXG4gIDAlIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDE7IH0gfVxcblxcbkBrZXlmcmFtZXMgYW5pbWF0ZXRvcCB7XFxuICAwJSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG5hc2lkZS5idWxsZXRpbkRldGFpbHMge1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgbWFyZ2luLXRvcDogNXB4OyB9XFxuXFxuc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0IHtcXG4gIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgbWFyZ2luLXJpZ2h0OiAxMHB4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIHdpZHRoOiA3MTVweDtcXG4gIHBhZGRpbmc6IDI1cHggMTAwcHggNDVweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBtYXJnaW4tdG9wOiA1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGgzIHtcXG4gICAgY29sb3I6ICMyMDIwMjA7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjZweCBSb2JvdG87XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucHJpY2Uge1xcbiAgICBjb2xvcjogIzFmMWYxZjtcXG4gICAgZm9udDogNDAwIDE4cHggLyAyNnB4IFJvYm90bztcXG4gICAgZmxvYXQ6IHJpZ2h0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuY2hlY2tCb3gge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMTVweDtcXG4gICAgbWFyZ2luLXRvcDogLTFweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmJyZWFkQ3J1bWJzIHtcXG4gICAgY29sb3I6IHJnYmEoMzEsIDMxLCAzMSwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTRweCBSb2JvdG87XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5pZCB7XFxuICAgIGNvbG9yOiByZ2JhKDMyLCAzMiwgMzIsIDAuNTQpO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE0cHggUm9ib3RvO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGN1cnNvcjogZGVmYXVsdDtcXG4gICAgbWFyZ2luLXRvcDogMTBweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciB7XFxuICAgIGhlaWdodDogMTIwcHg7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBtYXJnaW4tYm90dG9tOiAxMHB4OyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiBkaXYge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAxMHB4O1xcbiAgICAgIGhlaWdodDogMTAwJTtcXG4gICAgICB3aWR0aDogMTY1cHg7IH1cXG4gICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gZGl2Om50aC1vZi10eXBlKDEpIHtcXG4gICAgICAgIG1hcmdpbjogMDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiBkaXYgPiBpbWcge1xcbiAgICAgICAgLW8tb2JqZWN0LWZpdDogY29udGFpbjtcXG4gICAgICAgICAgIG9iamVjdC1maXQ6IGNvbnRhaW47XFxuICAgICAgICB3aWR0aDogMTAwJTtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGNEY0RjQ7XFxuICAgICAgICBoZWlnaHQ6IDEwMCU7XFxuICAgICAgICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IC5uZXh0IHtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9uZXh0LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCBjZW50ZXI7XFxuICAgICAgaGVpZ2h0OiAxNHB4O1xcbiAgICAgIHdpZHRoOiAxMHB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICB0b3A6IDUwJTtcXG4gICAgICBtYXJnaW4tdG9wOiAtN3B4O1xcbiAgICAgIHJpZ2h0OiAtMjVweDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gLnByZXYge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3ByZXYucG5nXCIpICsgXCIpIG5vLXJlcGVhdCByaWdodCBjZW50ZXI7XFxuICAgICAgaGVpZ2h0OiAxNHB4O1xcbiAgICAgIHdpZHRoOiAxMHB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICB0b3A6IDUwJTtcXG4gICAgICBtYXJnaW4tdG9wOiAtN3B4O1xcbiAgICAgIGxlZnQ6IC0yNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAjbWFwRm9yT25lQWR2ZXJ0ZXJ0IHtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGhlaWdodDogMjMwcHg7XFxuICAgIHdpZHRoOiAyMjVweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMjVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmRlc2NyaXB0aW9uQWQge1xcbiAgICBjb2xvcjogIzBjMGMxZTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxOHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTVweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMjBweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmdvVG9NYXAge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogbGVmdDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmFsbENvbW1lbnRzIHtcXG4gICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDUwcHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC53cml0ZUFSZXZpZXcge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5uYW1lVXNlciB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE4cHggUm9ib3RvO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7XFxuICAgIGN1cnNvcjogZGVmYXVsdDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMzVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLm9uT3JPZmZMaW5lVXNlciB7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgLmJ0bi1ncmV5IHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxNDBweDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyNXB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0IC5idG4tYmx1ZSB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuXFxuLndyYXBGb3JEaXYge1xcbiAgd2lkdGg6IDI2NXB4O1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gIG1hcmdpbi1ib3R0b206IDI1cHg7XFxuICBib3JkZXI6IDFweCBzb2xpZCAjRTlFOUU5O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgLndyYXBGb3JEaXYgPiB1bC50YWIge1xcbiAgICBsaXN0LXN0eWxlLXR5cGU6IG5vbmU7XFxuICAgIGhlaWdodDogNTBweDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjRTlFOUU5O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkge1xcbiAgICAgIGZsb2F0OiBsZWZ0OyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGEge1xcbiAgICAgICAgY29sb3I6ICM5MzkzOTM7XFxuICAgICAgICBmb250OiA0MDAgMTRweCAvIDUwcHggUm9ib3RvO1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhOmFmdGVyIHtcXG4gICAgICAgICAgY29udGVudDogJyc7XFxuICAgICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgIGJvdHRvbTogMDtcXG4gICAgICAgICAgd2lkdGg6IDA7XFxuICAgICAgICAgIGhlaWdodDogMnB4O1xcbiAgICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkQ1MTUxO1xcbiAgICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6Zm9jdXMsIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhLmFjdGl2ZSB7XFxuICAgICAgICAgIGNvbG9yOiAjN2VhZmUxOyB9XFxuICAgICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhOmZvY3VzOmFmdGVyLCAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYS5hY3RpdmU6YWZ0ZXIge1xcbiAgICAgICAgICAgIHdpZHRoOiAxMDAlOyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgxKSA+IGEge1xcbiAgICAgICAgd2lkdGg6IDE1OXB4OyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDEpID4gYTphZnRlciB7XFxuICAgICAgICAgIHJpZ2h0OiAwOyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgyKSA+IGEge1xcbiAgICAgICAgd2lkdGg6IDEwNHB4OyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDIpID4gYTphZnRlciB7XFxuICAgICAgICAgIGxlZnQ6IDA7IH1cXG4gIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyB7XFxuICAgIGhlaWdodDogMTc4cHg7XFxuICAgIHdpZHRoOiAyODJweDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogI0Y0RjRGNDtcXG4gICAgb3ZlcmZsb3c6IGF1dG87XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gLnRhYmNvbnRlbnQge1xcbiAgICAgIGRpc3BsYXk6IG5vbmU7XFxuICAgICAgLXdlYmtpdC1hbmltYXRpb246IGZhZGVFZmZlY3QgMXM7XFxuICAgICAgYW5pbWF0aW9uOiBmYWRlRWZmZWN0IDFzOyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gLnRhYmNvbnRlbnQuYWN0aXZlIHtcXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrOyB9XFxuICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzIHtcXG4gICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyA+IGRpdiB7XFxuICAgICAgICBwYWRkaW5nOiAzMHB4IDIwcHggMjVweCA2NXB4O1xcbiAgICAgICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNFOUU5RTk7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2Om50aC1sYXN0LW9mLXR5cGUoMSkge1xcbiAgICAgICAgICBib3JkZXI6IG5vbmU7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2ID4gaW1nIHtcXG4gICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICBoZWlnaHQ6IDMwcHg7XFxuICAgICAgICAgIHdpZHRoOiAyNXB4O1xcbiAgICAgICAgICB0b3A6IDM1cHg7XFxuICAgICAgICAgIGxlZnQ6IDIwcHg7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2ID4gcCB7XFxuICAgICAgICAgIGNvbG9yOiAjMGQwZDFlO1xcbiAgICAgICAgICBmb250OiA0MDAgMTJweCAvIDE4cHggUm9ib3RvOyB9XFxuXFxuQC13ZWJraXQta2V5ZnJhbWVzIGZhZGVFZmZlY3Qge1xcbiAgZnJvbSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIHRvIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuQGtleWZyYW1lcyBmYWRlRWZmZWN0IHtcXG4gIGZyb20ge1xcbiAgICBvcGFjaXR5OiAwOyB9XFxuICB0byB7XFxuICAgIG9wYWNpdHk6IDE7IH0gfVxcblxcbi5idG4tYmx1ZSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMTg3NUQwO1xcbiAgY29sb3I6ICNmZmZmZmY7XFxuICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvOyB9XFxuXFxuLm9uT3JPZmZMaW5lVXNlciB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMDBDNjUyO1xcbiAgYm9yZGVyOiAxcHggc29saWQgd2hpdGU7XFxuICBib3gtc2hhZG93OiAxcHggMXB4IDNweCAwcHggcmdiYSgwLCAwLCAwLCAwLjY1KTtcXG4gIGhlaWdodDogMThweDtcXG4gIHdpZHRoOiAxOHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJvcmRlci1yYWRpdXM6IDUwJTsgfVxcblxcbi5lcnJvci1jb250YWluZXIge1xcbiAgd2lkdGg6IDExMDNweDtcXG4gIG1hcmdpbjogYXV0bztcXG4gIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZXJyb3JfYmcucG5nXCIpICsgXCIpO1xcbiAgYmFja2dyb3VuZC1yZXBlYXQ6IG5vLXJlcGVhdDtcXG4gIGJhY2tncm91bmQtcG9zaXRpb24teDogcmlnaHQ7XFxuICBiYWNrZ3JvdW5kLXBvc2l0aW9uLXk6IDgwcHg7XFxuICBtaW4taGVpZ2h0OiA1MDBweDsgfVxcbiAgLmVycm9yLWNvbnRhaW5lciBoMSB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMjJweCAvIDI2LjRweCBSb2JvdG87XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgbWFyZ2luLXRvcDogNjVweDsgfVxcbiAgLmVycm9yLWNvbnRhaW5lciBoMiB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMjJweCAvIDI2LjRweCBSb2JvdG87XFxuICAgIG1hcmdpbi10b3A6IDE4OXB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgzIHtcXG4gICAgY29sb3I6IHJnYmEoMzEsIDMxLCAzMSwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTQuNHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMzBweDsgfVxcbiAgLmVycm9yLWNvbnRhaW5lciA+IC5idG4tYmx1ZSB7XFxuICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcblxcbi5jbGlja2VkIHtcXG4gIGJveC1zaGFkb3c6IDBweCAwcHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCkgIWltcG9ydGFudDtcXG4gIG1hcmdpbi10b3A6IDEycHggIWltcG9ydGFudDsgfVxcblxcbi5tdWx0aXBsZSB7XFxuICB3aWR0aDogMTE1MHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwOyB9XFxuICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQge1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgd2lkdGg6IDM4MHB4O1xcbiAgICBtYXJnaW4tbGVmdDogNXB4O1xcbiAgICBoZWlnaHQ6IDE2NXB4O1xcbiAgICBwYWRkaW5nOiAxNXB4OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgzbi0yKSB7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDA7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDEpIHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjQkNFNkU5OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0JDRTZFOTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQ6bnRoLW9mLXR5cGUoMykge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNCQ0U2RTk7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0ID4gLmJ1bGxldGluLWNlbnRlci1jb2x1bW4ge1xcbiAgICAgIGZsb2F0OiBub25lO1xcbiAgICAgIG1hcmdpbjogMDtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgdG9wOiAxNXB4O1xcbiAgICAgIHJpZ2h0OiAxMTBweDsgfVxcbiAgICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCA+IC5idWxsZXRpbi1jZW50ZXItY29sdW1uID4gLmJ1bGxldGluLWFjdGlvbiB7XFxuICAgICAgICBib3R0b206IC00M3B4O1xcbiAgICAgICAgd2lkdGg6IDExMHB4O1xcbiAgICAgICAgcmlnaHQ6IC0zMHB4O1xcbiAgICAgICAgei1pbmRleDogMTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQgPiAuYnVsbGV0aW4tbGVmdC1jb2x1bW4gPiAuYnVsbGV0aW4tZGVzY3JpcHRpb24ge1xcbiAgICAgIGNvbG9yOiAjMGMwYzFlO1xcbiAgICAgIGZvbnQ6IDQwMCAxMnB4IC8gMThweCBSb2JvdG87XFxuICAgICAgd2lkdGg6IDE3MHB4O1xcbiAgICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgICAgaGVpZ2h0OiA0MHB4OyB9XFxuXFxuLnJlZCB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjc0QTRBOyB9XFxuXFxuLmlucHV0Rm9ybS1yZXF1aXJlZCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBwYWRkaW5nOiA2cHggMnB4O1xcbiAgZm9udDogNDAwIDE2cHggLyAyNC44cHggUm9ib3RvO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWI5YjliO1xcbiAgbWFyZ2luLXRvcDogMTVweDsgfVxcbiAgLmlucHV0Rm9ybS1yZXF1aXJlZCBsYWJlbCB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgbGVmdDogMnB4O1xcbiAgICB0b3A6IDZweDtcXG4gICAgY29sb3I6ICM5YTlhOWE7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAuaW5wdXRGb3JtLXJlcXVpcmVkIGlucHV0IHtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lO1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7IH1cXG5cXG4uaW5wdXRGb3JtLnJlcXVpcmVkIHtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWI5YjliICFpbXBvcnRhbnQ7IH1cXG5cXG4uaW5wdXRGb3JtIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBib3JkZXItYm90dG9tOiAxcHggZG90dGVkICM5OTk5OTk7IH1cXG4gIC5pbnB1dEZvcm0gbGFiZWwge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGxlZnQ6IDVweDtcXG4gICAgY29sb3I6ICM5YTlhOWE7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0b3A6IC0xcHg7IH1cXG4gIC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBwYWRkaW5nOiAxcHggNXB4OyB9XFxuXFxuLyogVGV4dCBlbGVtZW50IGFuaW1hdGlvbiAqL1xcbi50ZXh0T3V0IHtcXG4gIHRvcDogLTE1cHggIWltcG9ydGFudDtcXG4gIGZvbnQtc2l6ZTogMTJweCAhaW1wb3J0YW50O1xcbiAgbGVmdDogNXB4ICFpbXBvcnRhbnQ7IH1cXG5cXG5zZWN0aW9uLmxvZ2luLCBzZWN0aW9uLnJlZ2lzdGVyIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgcGFkZGluZzogNjVweCAyMzBweDtcXG4gIHdpZHRoOiAxMTAwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICBzZWN0aW9uLmxvZ2luID4gaDIsIHNlY3Rpb24ucmVnaXN0ZXIgPiBoMiB7XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgY29sb3I6ICMyMDIwMjA7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IFJvYm90bztcXG4gICAgbWFyZ2luLWJvdHRvbTogNTVweDsgfVxcbiAgc2VjdGlvbi5sb2dpbiA+IC5pbnB1dEZvcm0sIHNlY3Rpb24ucmVnaXN0ZXIgPiAuaW5wdXRGb3JtIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmlucHV0Rm9ybSA+IGxhYmVsLCBzZWN0aW9uLmxvZ2luID4gLmlucHV0Rm9ybSBpbnB1dCwgc2VjdGlvbi5yZWdpc3RlciA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgc2VjdGlvbi5yZWdpc3RlciA+IC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICAgIGNvbG9yOiAjOWE5YTlhO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bzsgfVxcbiAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZSwgc2VjdGlvbi5yZWdpc3RlciA+IC5idG4tYmx1ZSB7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBtYXJnaW4tbGVmdDogNDVweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7IH1cXG4gICAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZS52aywgc2VjdGlvbi5yZWdpc3RlciA+IC5idG4tYmx1ZS52ayB7XFxuICAgICAgYmFja2dyb3VuZDogIzAxODZDRiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3ZrLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQgMTBweDtcXG4gICAgICBtYXJnaW4tbGVmdDogMDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLmZhY2Vib29rLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlLmZhY2Vib29rIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjM0U1MEIzIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZmFjZWJvb2sucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdCAxMHB4OyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuYnRuLWJsdWUuZ29vZ2xlLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlLmdvb2dsZSB7XFxuICAgICAgYmFja2dyb3VuZDogI0ZEM0MwMCB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2dvb2dsZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0IDEwcHg7IH1cXG5cXG5zZWN0aW9uLmJ1bGxldGluQWRkIHtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgd2lkdGg6IDExMDBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDtcXG4gIHBhZGRpbmc6IDY1cHggMjI1cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gaDIge1xcbiAgICBjb2xvcjogIzFmMWYxZjtcXG4gICAgZm9udDogNDAwIDIycHggUm9ib3RvO1xcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuc2VsZWN0Qm94IHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDtcXG4gICAgbWFyZ2luLXRvcDogMTVweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJvcmRlci1jb2xvcjogZ3JleTtcXG4gICAgICBtaW4td2lkdGg6IDE5NXB4O1xcbiAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICBwYWRkaW5nLWxlZnQ6IDVweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWU5ZTllO1xcbiAgICB3aWR0aDogNDEwcHg7XFxuICAgIHBhZGRpbmctYm90dG9tOiA1cHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlID4gaW5wdXQge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB6LWluZGV4OiAtMTsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUgPiBwIHtcXG4gICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICAgICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIHBhZGRpbmctdG9wOiAxMHB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuZmlsZSA+IC5idG4tYmx1ZSB7XFxuICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgICAgaGVpZ2h0OiAzMHB4O1xcbiAgICAgIHdpZHRoOiA4NXB4O1xcbiAgICAgIGxpbmUtaGVpZ2h0OiAzMHB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmlucHV0Rm9ybSB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICAgIGNvbG9yOiAjOTk5OTk5O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bzsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IHAge1xcbiAgICAgIGNvbG9yOiAjOTk5OTk5O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggZG90dGVkICM5OTk5OTk7XFxuICAgICAgd2lkdGg6IDE2MHB4O1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIGhlaWdodDogMjFweDtcXG4gICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAxOXB4O1xcbiAgICAgIG1hcmdpbi10b3A6IDNweDtcXG4gICAgICB3aWR0aDogMTZweDtcXG4gICAgICBoZWlnaHQ6IDE1cHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgICAgb3V0bGluZTogMXB4IHNvbGlkIHRyYW5zcGFyZW50O1xcbiAgICAgIGJvcmRlcjogMXB4IHNvbGlkIHdoaXRlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi50cmFuc3BhcmVudCB7XFxuICAgICAgICBib3JkZXItY29sb3I6ICNFOUU5RTk7XFxuICAgICAgICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuICAgICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnRyYW5zcGFyZW50ID4gLnJlZFN0aWNrIHtcXG4gICAgICAgICAgLXdlYmtpdC10cmFuc2Zvcm06IHJvdGF0ZSgtNDNkZWcpO1xcbiAgICAgICAgICAgICAgICAgIHRyYW5zZm9ybTogcm90YXRlKC00M2RlZyk7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6IHJlZDtcXG4gICAgICAgICAgaGVpZ2h0OiAycHg7XFxuICAgICAgICAgIG1hcmdpbi10b3A6IDUuNXB4O1xcbiAgICAgICAgICB3aWR0aDogMjBweDtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IC0zcHg7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnJlZCB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZWQ7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2Lm9yYW5nZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBvcmFuZ2U7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnllbGxvdyB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiB5ZWxsb3c7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmdyZWVuIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGdyZWVuOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5saWdodEJsdWUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogbGlnaHRCbHVlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5ibHVlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGJsdWU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnBpbmsge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcGluazsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYucHVycGxlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHB1cnBsZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYud2hpdGUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgICAgICBib3JkZXItY29sb3I6ICNFOUU5RTk7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmdyYXkge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogZ3JheTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuYmxhY2sge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogYmxhY2s7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmJyb3duIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGJyb3duOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5tdWx0aWNvbG9yIHtcXG4gICAgICAgIC8qIFBlcm1hbGluayAtIHVzZSB0byBlZGl0IGFuZCBzaGFyZSB0aGlzIGdyYWRpZW50OiBodHRwOi8vY29sb3J6aWxsYS5jb20vZ3JhZGllbnQtZWRpdG9yLyNmZjAwMDArMCxmZmZmMDArMjAsMWRmZjAwKzQwLDAwZmZmZis2MCwwNDAwZmYrODAsZmYwMGZhKzEwMCAqL1xcbiAgICAgICAgYmFja2dyb3VuZDogI2ZmMDAwMDtcXG4gICAgICAgIC8qIE9sZCBicm93c2VycyAqL1xcbiAgICAgICAgLyogRkYzLjYtMTUgKi9cXG4gICAgICAgIGJhY2tncm91bmQ6IC13ZWJraXQtbGluZWFyLWdyYWRpZW50KGxlZnQsICNmZjAwMDAgMCUsICNmZmZmMDAgMjAlLCAjMWRmZjAwIDQwJSwgIzAwZmZmZiA2MCUsICMwNDAwZmYgODAlLCAjZmYwMGZhIDEwMCUpO1xcbiAgICAgICAgLyogQ2hyb21lMTAtMjUsU2FmYXJpNS4xLTYgKi9cXG4gICAgICAgIGJhY2tncm91bmQ6IGxpbmVhci1ncmFkaWVudCh0byByaWdodCwgI2ZmMDAwMCAwJSwgI2ZmZmYwMCAyMCUsICMxZGZmMDAgNDAlLCAjMDBmZmZmIDYwJSwgIzA0MDBmZiA4MCUsICNmZjAwZmEgMTAwJSk7XFxuICAgICAgICAvKiBXM0MsIElFMTArLCBGRjE2KywgQ2hyb21lMjYrLCBPcGVyYTEyKywgU2FmYXJpNysgKi9cXG4gICAgICAgIGZpbHRlcjogcHJvZ2lkOkRYSW1hZ2VUcmFuc2Zvcm0uTWljcm9zb2Z0LmdyYWRpZW50KCBzdGFydENvbG9yc3RyPScjZmYwMDAwJywgZW5kQ29sb3JzdHI9JyNmZjAwZmEnLEdyYWRpZW50VHlwZT0xICk7XFxuICAgICAgICAvKiBJRTYtOSAqLyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5hY3RpdmUge1xcbiAgICAgICAgb3V0bGluZS1jb2xvcjogcmVkOyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNhbGVuZGFyLCBzZWN0aW9uLmJ1bGxldGluQWRkIC5hZGRDYWxlbmRhciB7XFxuICAgIGhlaWdodDogMjBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICB3aWR0aDogMjMwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FsZW5kYXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdDtcXG4gICAgcGFkZGluZy1sZWZ0OiA0NXB4O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICBtYXJnaW4tYm90dG9tOiA0MHB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlLCBzZWN0aW9uLmJ1bGxldGluQWRkIC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCBncmV5O1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCA1cHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUgPiBwLCBzZWN0aW9uLmJ1bGxldGluQWRkIC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUgPiBwIHtcXG4gICAgICAgIHRleHQtYWxpZ246IGxlZnQ7XFxuICAgICAgICBjb2xvcjogIzI2MjYyNjtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjBweCBSb2JvdG87IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jYWxlbmRhciA+IC5saXN0VmFsdWUsIHNlY3Rpb24uYnVsbGV0aW5BZGQgLmFkZENhbGVuZGFyID4gLmxpc3RWYWx1ZSB7XFxuICAgICAgZGlzcGxheTogbm9uZTsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5hZGRDYWxlbmRhciB7XFxuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nXCIpICsgXCIpOyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYWRkQ2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICBib3JkZXItYm90dG9tLWNvbG9yOiBibHVlO1xcbiAgICAgIGJhY2tncm91bmQ6IG5vbmU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSA+IHAge1xcbiAgICAgICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgICAgICBmb250LXNpemU6IDE2cHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYnRuLWJsdWUge1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7XFxuICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5lcnJvcnMge1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgZm9udDogNDAwIDEycHggLyAxNHB4IFJvYm90bztcXG4gIGNvbG9yOiAjZGQyYzAwO1xcbiAgYm90dG9tOiAtMTdweDsgfVxcblxcbm5hdiB7XFxuICB3aWR0aDogMjU1cHg7XFxuICBmbG9hdDogbGVmdDtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgbWFyZ2luLXRvcDogNXB4OyB9XFxuICBuYXYgPiAubWFwIHtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9tYXAucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgaGVpZ2h0OiAxNDVweDtcXG4gICAgd2lkdGg6IDI1NXB4OyB9XFxuICAgIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtIHtcXG4gICAgICB3aWR0aDogMjE1cHg7XFxuICAgICAgbWFyZ2luOiAxMTVweCBhdXRvIDA7IH1cXG4gICAgICBuYXYgPiAubWFwID4gLmlucHV0Rm9ybS5yZXF1aXJlZCB7XFxuICAgICAgICBib3JkZXItY29sb3I6IGJsYWNrICFpbXBvcnRhbnQ7IH1cXG4gICAgICBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSA+IGlucHV0LCBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSBsYWJlbCwgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0gLnRleHRPdXQge1xcbiAgICAgICAgY29sb3I6IGJsYWNrICFpbXBvcnRhbnQ7IH1cXG4gIG5hdiA+IHVsIHtcXG4gICAgbGlzdC1zdHlsZTogbm9uZTsgfVxcbiAgICBuYXYgPiB1bCA+IGxpID4gcCB7XFxuICAgICAgcGFkZGluZy1sZWZ0OiA3NXB4O1xcbiAgICAgIGNvbG9yOiAjMjYzMjM4O1xcbiAgICAgIGZvbnQ6IDUwMCAxM3B4IC8gNDBweCBSb2JvdG87XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDIwcHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNFNkU2RTYgIWltcG9ydGFudDsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0ZvckNoaWxkcmVuLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDIpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0ZvckFuaW1hbHMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMykge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQnVzaW5lc3MucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNCkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVGhlUHJvcGVydHkucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVHJhbnNwb3J0LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDYpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDcpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1ZvbHVudGVlcmluZy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg4KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Jc0ZyZWUucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoOSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxMCkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvSG9iYmllc0FuZFNwb3J0cy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxMSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQmFydGVyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEyKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuXFxuLm5hdkJ0biB7XFxuICB3aWR0aDogNDNweDtcXG4gIGhlaWdodDogNDlweDtcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIGxlZnQ6IDA7XFxuICB0b3A6IDkwcHg7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3J1cHIucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gIHotaW5kZXg6IDE7IH1cXG4gIC5uYXZCdG46aG92ZXIge1xcbiAgICB3aWR0aDogNjBweDtcXG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9ydXBySG92ZXIucG5nXCIpICsgXCIpOyB9XFxuXCIsIFwiXCJdKTtcblxuLy8gZXhwb3J0c1xuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vY3NzLWxvYWRlciEuL34vcG9zdGNzcy1sb2FkZXIhLi9+L3Nhc3MtbG9hZGVyIS4vc3R5bGVzL2Jhc2ljLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSAyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvKlxyXG5cdE1JVCBMaWNlbnNlIGh0dHA6Ly93d3cub3BlbnNvdXJjZS5vcmcvbGljZW5zZXMvbWl0LWxpY2Vuc2UucGhwXHJcblx0QXV0aG9yIFRvYmlhcyBLb3BwZXJzIEBzb2tyYVxyXG4qL1xyXG4vLyBjc3MgYmFzZSBjb2RlLCBpbmplY3RlZCBieSB0aGUgY3NzLWxvYWRlclxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG5cdHZhciBsaXN0ID0gW107XHJcblxyXG5cdC8vIHJldHVybiB0aGUgbGlzdCBvZiBtb2R1bGVzIGFzIGNzcyBzdHJpbmdcclxuXHRsaXN0LnRvU3RyaW5nID0gZnVuY3Rpb24gdG9TdHJpbmcoKSB7XHJcblx0XHR2YXIgcmVzdWx0ID0gW107XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgdGhpcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaXRlbSA9IHRoaXNbaV07XHJcblx0XHRcdGlmKGl0ZW1bMl0pIHtcclxuXHRcdFx0XHRyZXN1bHQucHVzaChcIkBtZWRpYSBcIiArIGl0ZW1bMl0gKyBcIntcIiArIGl0ZW1bMV0gKyBcIn1cIik7XHJcblx0XHRcdH0gZWxzZSB7XHJcblx0XHRcdFx0cmVzdWx0LnB1c2goaXRlbVsxXSk7XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHRcdHJldHVybiByZXN1bHQuam9pbihcIlwiKTtcclxuXHR9O1xyXG5cclxuXHQvLyBpbXBvcnQgYSBsaXN0IG9mIG1vZHVsZXMgaW50byB0aGUgbGlzdFxyXG5cdGxpc3QuaSA9IGZ1bmN0aW9uKG1vZHVsZXMsIG1lZGlhUXVlcnkpIHtcclxuXHRcdGlmKHR5cGVvZiBtb2R1bGVzID09PSBcInN0cmluZ1wiKVxyXG5cdFx0XHRtb2R1bGVzID0gW1tudWxsLCBtb2R1bGVzLCBcIlwiXV07XHJcblx0XHR2YXIgYWxyZWFkeUltcG9ydGVkTW9kdWxlcyA9IHt9O1xyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IHRoaXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGlkID0gdGhpc1tpXVswXTtcclxuXHRcdFx0aWYodHlwZW9mIGlkID09PSBcIm51bWJlclwiKVxyXG5cdFx0XHRcdGFscmVhZHlJbXBvcnRlZE1vZHVsZXNbaWRdID0gdHJ1ZTtcclxuXHRcdH1cclxuXHRcdGZvcihpID0gMDsgaSA8IG1vZHVsZXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGl0ZW0gPSBtb2R1bGVzW2ldO1xyXG5cdFx0XHQvLyBza2lwIGFscmVhZHkgaW1wb3J0ZWQgbW9kdWxlXHJcblx0XHRcdC8vIHRoaXMgaW1wbGVtZW50YXRpb24gaXMgbm90IDEwMCUgcGVyZmVjdCBmb3Igd2VpcmQgbWVkaWEgcXVlcnkgY29tYmluYXRpb25zXHJcblx0XHRcdC8vICB3aGVuIGEgbW9kdWxlIGlzIGltcG9ydGVkIG11bHRpcGxlIHRpbWVzIHdpdGggZGlmZmVyZW50IG1lZGlhIHF1ZXJpZXMuXHJcblx0XHRcdC8vICBJIGhvcGUgdGhpcyB3aWxsIG5ldmVyIG9jY3VyIChIZXkgdGhpcyB3YXkgd2UgaGF2ZSBzbWFsbGVyIGJ1bmRsZXMpXHJcblx0XHRcdGlmKHR5cGVvZiBpdGVtWzBdICE9PSBcIm51bWJlclwiIHx8ICFhbHJlYWR5SW1wb3J0ZWRNb2R1bGVzW2l0ZW1bMF1dKSB7XHJcblx0XHRcdFx0aWYobWVkaWFRdWVyeSAmJiAhaXRlbVsyXSkge1xyXG5cdFx0XHRcdFx0aXRlbVsyXSA9IG1lZGlhUXVlcnk7XHJcblx0XHRcdFx0fSBlbHNlIGlmKG1lZGlhUXVlcnkpIHtcclxuXHRcdFx0XHRcdGl0ZW1bMl0gPSBcIihcIiArIGl0ZW1bMl0gKyBcIikgYW5kIChcIiArIG1lZGlhUXVlcnkgKyBcIilcIjtcclxuXHRcdFx0XHR9XHJcblx0XHRcdFx0bGlzdC5wdXNoKGl0ZW0pO1xyXG5cdFx0XHR9XHJcblx0XHR9XHJcblx0fTtcclxuXHRyZXR1cm4gbGlzdDtcclxufTtcclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2xvZ28ucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9sb2dvLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9hZGQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hZGQucG5nXG4gKiogbW9kdWxlIGlkID0gNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL21haWwucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9tYWlsLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9tYWlsX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL21haWxfc2hhZG93LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9iZWxsLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYmVsbC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYmVsbF9zaGFkb3cucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9iZWxsX3NoYWRvdy5wbmdcbiAqKiBtb2R1bGUgaWQgPSA5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvc2VydmljZXMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9zZXJ2aWNlcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxMFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3NlcnZpY2VzX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3NlcnZpY2VzX3NoYWRvdy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3VzZXJOYW1lLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdXNlck5hbWUucG5nXG4gKiogbW9kdWxlIGlkID0gMTJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxM1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL25leHQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9uZXh0LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvcHJldi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3ByZXYucG5nXG4gKiogbW9kdWxlIGlkID0gMTVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9lcnJvcl9iZy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2Vycm9yX2JnLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvdmsucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy92ay5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2ZhY2Vib29rLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZmFjZWJvb2sucG5nXG4gKiogbW9kdWxlIGlkID0gMThcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9nb29nbGUucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9nb29nbGUucG5nXG4gKiogbW9kdWxlIGlkID0gMTlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9jYWxlbmRhci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhbGVuZGFyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYWRkQ2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hZGRDYWxlbmRhci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL21hcC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL21hcC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0UmlnaHROYXYucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvRm9yQ2hpbGRyZW4ucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Gb3JDaGlsZHJlbi5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0ZvckFuaW1hbHMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Gb3JBbmltYWxzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQnVzaW5lc3MucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9CdXNpbmVzcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyNlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RoZVByb3BlcnR5LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVGhlUHJvcGVydHkucG5nXG4gKiogbW9kdWxlIGlkID0gMjdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9UcmFuc3BvcnQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9UcmFuc3BvcnQucG5nXG4gKiogbW9kdWxlIGlkID0gMjhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9DbG90aGluZ0FuZENvc21ldGljcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvSXNGcmVlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvSXNGcmVlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0FIb3VzZUFuZEFHYXJkZW4ucG5nXG4gKiogbW9kdWxlIGlkID0gMzJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Ib2JiaWVzQW5kU3BvcnRzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvSG9iYmllc0FuZFNwb3J0cy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzM1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0JhcnRlci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0JhcnRlci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3J1cHIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9ydXByLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvcnVwckhvdmVyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvcnVwckhvdmVyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvKlxyXG5cdE1JVCBMaWNlbnNlIGh0dHA6Ly93d3cub3BlbnNvdXJjZS5vcmcvbGljZW5zZXMvbWl0LWxpY2Vuc2UucGhwXHJcblx0QXV0aG9yIFRvYmlhcyBLb3BwZXJzIEBzb2tyYVxyXG4qL1xyXG52YXIgc3R5bGVzSW5Eb20gPSB7fSxcclxuXHRtZW1vaXplID0gZnVuY3Rpb24oZm4pIHtcclxuXHRcdHZhciBtZW1vO1xyXG5cdFx0cmV0dXJuIGZ1bmN0aW9uICgpIHtcclxuXHRcdFx0aWYgKHR5cGVvZiBtZW1vID09PSBcInVuZGVmaW5lZFwiKSBtZW1vID0gZm4uYXBwbHkodGhpcywgYXJndW1lbnRzKTtcclxuXHRcdFx0cmV0dXJuIG1lbW87XHJcblx0XHR9O1xyXG5cdH0sXHJcblx0aXNPbGRJRSA9IG1lbW9pemUoZnVuY3Rpb24oKSB7XHJcblx0XHRyZXR1cm4gL21zaWUgWzYtOV1cXGIvLnRlc3Qod2luZG93Lm5hdmlnYXRvci51c2VyQWdlbnQudG9Mb3dlckNhc2UoKSk7XHJcblx0fSksXHJcblx0Z2V0SGVhZEVsZW1lbnQgPSBtZW1vaXplKGZ1bmN0aW9uICgpIHtcclxuXHRcdHJldHVybiBkb2N1bWVudC5oZWFkIHx8IGRvY3VtZW50LmdldEVsZW1lbnRzQnlUYWdOYW1lKFwiaGVhZFwiKVswXTtcclxuXHR9KSxcclxuXHRzaW5nbGV0b25FbGVtZW50ID0gbnVsbCxcclxuXHRzaW5nbGV0b25Db3VudGVyID0gMCxcclxuXHRzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcCA9IFtdO1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbihsaXN0LCBvcHRpb25zKSB7XHJcblx0aWYodHlwZW9mIERFQlVHICE9PSBcInVuZGVmaW5lZFwiICYmIERFQlVHKSB7XHJcblx0XHRpZih0eXBlb2YgZG9jdW1lbnQgIT09IFwib2JqZWN0XCIpIHRocm93IG5ldyBFcnJvcihcIlRoZSBzdHlsZS1sb2FkZXIgY2Fubm90IGJlIHVzZWQgaW4gYSBub24tYnJvd3NlciBlbnZpcm9ubWVudFwiKTtcclxuXHR9XHJcblxyXG5cdG9wdGlvbnMgPSBvcHRpb25zIHx8IHt9O1xyXG5cdC8vIEZvcmNlIHNpbmdsZS10YWcgc29sdXRpb24gb24gSUU2LTksIHdoaWNoIGhhcyBhIGhhcmQgbGltaXQgb24gdGhlICMgb2YgPHN0eWxlPlxyXG5cdC8vIHRhZ3MgaXQgd2lsbCBhbGxvdyBvbiBhIHBhZ2VcclxuXHRpZiAodHlwZW9mIG9wdGlvbnMuc2luZ2xldG9uID09PSBcInVuZGVmaW5lZFwiKSBvcHRpb25zLnNpbmdsZXRvbiA9IGlzT2xkSUUoKTtcclxuXHJcblx0Ly8gQnkgZGVmYXVsdCwgYWRkIDxzdHlsZT4gdGFncyB0byB0aGUgYm90dG9tIG9mIDxoZWFkPi5cclxuXHRpZiAodHlwZW9mIG9wdGlvbnMuaW5zZXJ0QXQgPT09IFwidW5kZWZpbmVkXCIpIG9wdGlvbnMuaW5zZXJ0QXQgPSBcImJvdHRvbVwiO1xyXG5cclxuXHR2YXIgc3R5bGVzID0gbGlzdFRvU3R5bGVzKGxpc3QpO1xyXG5cdGFkZFN0eWxlc1RvRG9tKHN0eWxlcywgb3B0aW9ucyk7XHJcblxyXG5cdHJldHVybiBmdW5jdGlvbiB1cGRhdGUobmV3TGlzdCkge1xyXG5cdFx0dmFyIG1heVJlbW92ZSA9IFtdO1xyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IHN0eWxlcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaXRlbSA9IHN0eWxlc1tpXTtcclxuXHRcdFx0dmFyIGRvbVN0eWxlID0gc3R5bGVzSW5Eb21baXRlbS5pZF07XHJcblx0XHRcdGRvbVN0eWxlLnJlZnMtLTtcclxuXHRcdFx0bWF5UmVtb3ZlLnB1c2goZG9tU3R5bGUpO1xyXG5cdFx0fVxyXG5cdFx0aWYobmV3TGlzdCkge1xyXG5cdFx0XHR2YXIgbmV3U3R5bGVzID0gbGlzdFRvU3R5bGVzKG5ld0xpc3QpO1xyXG5cdFx0XHRhZGRTdHlsZXNUb0RvbShuZXdTdHlsZXMsIG9wdGlvbnMpO1xyXG5cdFx0fVxyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IG1heVJlbW92ZS5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgZG9tU3R5bGUgPSBtYXlSZW1vdmVbaV07XHJcblx0XHRcdGlmKGRvbVN0eWxlLnJlZnMgPT09IDApIHtcclxuXHRcdFx0XHRmb3IodmFyIGogPSAwOyBqIDwgZG9tU3R5bGUucGFydHMubGVuZ3RoOyBqKyspXHJcblx0XHRcdFx0XHRkb21TdHlsZS5wYXJ0c1tqXSgpO1xyXG5cdFx0XHRcdGRlbGV0ZSBzdHlsZXNJbkRvbVtkb21TdHlsZS5pZF07XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHR9O1xyXG59XHJcblxyXG5mdW5jdGlvbiBhZGRTdHlsZXNUb0RvbShzdHlsZXMsIG9wdGlvbnMpIHtcclxuXHRmb3IodmFyIGkgPSAwOyBpIDwgc3R5bGVzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHR2YXIgaXRlbSA9IHN0eWxlc1tpXTtcclxuXHRcdHZhciBkb21TdHlsZSA9IHN0eWxlc0luRG9tW2l0ZW0uaWRdO1xyXG5cdFx0aWYoZG9tU3R5bGUpIHtcclxuXHRcdFx0ZG9tU3R5bGUucmVmcysrO1xyXG5cdFx0XHRmb3IodmFyIGogPSAwOyBqIDwgZG9tU3R5bGUucGFydHMubGVuZ3RoOyBqKyspIHtcclxuXHRcdFx0XHRkb21TdHlsZS5wYXJ0c1tqXShpdGVtLnBhcnRzW2pdKTtcclxuXHRcdFx0fVxyXG5cdFx0XHRmb3IoOyBqIDwgaXRlbS5wYXJ0cy5sZW5ndGg7IGorKykge1xyXG5cdFx0XHRcdGRvbVN0eWxlLnBhcnRzLnB1c2goYWRkU3R5bGUoaXRlbS5wYXJ0c1tqXSwgb3B0aW9ucykpO1xyXG5cdFx0XHR9XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHR2YXIgcGFydHMgPSBbXTtcclxuXHRcdFx0Zm9yKHZhciBqID0gMDsgaiA8IGl0ZW0ucGFydHMubGVuZ3RoOyBqKyspIHtcclxuXHRcdFx0XHRwYXJ0cy5wdXNoKGFkZFN0eWxlKGl0ZW0ucGFydHNbal0sIG9wdGlvbnMpKTtcclxuXHRcdFx0fVxyXG5cdFx0XHRzdHlsZXNJbkRvbVtpdGVtLmlkXSA9IHtpZDogaXRlbS5pZCwgcmVmczogMSwgcGFydHM6IHBhcnRzfTtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGxpc3RUb1N0eWxlcyhsaXN0KSB7XHJcblx0dmFyIHN0eWxlcyA9IFtdO1xyXG5cdHZhciBuZXdTdHlsZXMgPSB7fTtcclxuXHRmb3IodmFyIGkgPSAwOyBpIDwgbGlzdC5sZW5ndGg7IGkrKykge1xyXG5cdFx0dmFyIGl0ZW0gPSBsaXN0W2ldO1xyXG5cdFx0dmFyIGlkID0gaXRlbVswXTtcclxuXHRcdHZhciBjc3MgPSBpdGVtWzFdO1xyXG5cdFx0dmFyIG1lZGlhID0gaXRlbVsyXTtcclxuXHRcdHZhciBzb3VyY2VNYXAgPSBpdGVtWzNdO1xyXG5cdFx0dmFyIHBhcnQgPSB7Y3NzOiBjc3MsIG1lZGlhOiBtZWRpYSwgc291cmNlTWFwOiBzb3VyY2VNYXB9O1xyXG5cdFx0aWYoIW5ld1N0eWxlc1tpZF0pXHJcblx0XHRcdHN0eWxlcy5wdXNoKG5ld1N0eWxlc1tpZF0gPSB7aWQ6IGlkLCBwYXJ0czogW3BhcnRdfSk7XHJcblx0XHRlbHNlXHJcblx0XHRcdG5ld1N0eWxlc1tpZF0ucGFydHMucHVzaChwYXJ0KTtcclxuXHR9XHJcblx0cmV0dXJuIHN0eWxlcztcclxufVxyXG5cclxuZnVuY3Rpb24gaW5zZXJ0U3R5bGVFbGVtZW50KG9wdGlvbnMsIHN0eWxlRWxlbWVudCkge1xyXG5cdHZhciBoZWFkID0gZ2V0SGVhZEVsZW1lbnQoKTtcclxuXHR2YXIgbGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3AgPSBzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcFtzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5sZW5ndGggLSAxXTtcclxuXHRpZiAob3B0aW9ucy5pbnNlcnRBdCA9PT0gXCJ0b3BcIikge1xyXG5cdFx0aWYoIWxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wKSB7XHJcblx0XHRcdGhlYWQuaW5zZXJ0QmVmb3JlKHN0eWxlRWxlbWVudCwgaGVhZC5maXJzdENoaWxkKTtcclxuXHRcdH0gZWxzZSBpZihsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcC5uZXh0U2libGluZykge1xyXG5cdFx0XHRoZWFkLmluc2VydEJlZm9yZShzdHlsZUVsZW1lbnQsIGxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wLm5leHRTaWJsaW5nKTtcclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdGhlYWQuYXBwZW5kQ2hpbGQoc3R5bGVFbGVtZW50KTtcclxuXHRcdH1cclxuXHRcdHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLnB1c2goc3R5bGVFbGVtZW50KTtcclxuXHR9IGVsc2UgaWYgKG9wdGlvbnMuaW5zZXJ0QXQgPT09IFwiYm90dG9tXCIpIHtcclxuXHRcdGhlYWQuYXBwZW5kQ2hpbGQoc3R5bGVFbGVtZW50KTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0dGhyb3cgbmV3IEVycm9yKFwiSW52YWxpZCB2YWx1ZSBmb3IgcGFyYW1ldGVyICdpbnNlcnRBdCcuIE11c3QgYmUgJ3RvcCcgb3IgJ2JvdHRvbScuXCIpO1xyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gcmVtb3ZlU3R5bGVFbGVtZW50KHN0eWxlRWxlbWVudCkge1xyXG5cdHN0eWxlRWxlbWVudC5wYXJlbnROb2RlLnJlbW92ZUNoaWxkKHN0eWxlRWxlbWVudCk7XHJcblx0dmFyIGlkeCA9IHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLmluZGV4T2Yoc3R5bGVFbGVtZW50KTtcclxuXHRpZihpZHggPj0gMCkge1xyXG5cdFx0c3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3Auc3BsaWNlKGlkeCwgMSk7XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiBjcmVhdGVTdHlsZUVsZW1lbnQob3B0aW9ucykge1xyXG5cdHZhciBzdHlsZUVsZW1lbnQgPSBkb2N1bWVudC5jcmVhdGVFbGVtZW50KFwic3R5bGVcIik7XHJcblx0c3R5bGVFbGVtZW50LnR5cGUgPSBcInRleHQvY3NzXCI7XHJcblx0aW5zZXJ0U3R5bGVFbGVtZW50KG9wdGlvbnMsIHN0eWxlRWxlbWVudCk7XHJcblx0cmV0dXJuIHN0eWxlRWxlbWVudDtcclxufVxyXG5cclxuZnVuY3Rpb24gY3JlYXRlTGlua0VsZW1lbnQob3B0aW9ucykge1xyXG5cdHZhciBsaW5rRWxlbWVudCA9IGRvY3VtZW50LmNyZWF0ZUVsZW1lbnQoXCJsaW5rXCIpO1xyXG5cdGxpbmtFbGVtZW50LnJlbCA9IFwic3R5bGVzaGVldFwiO1xyXG5cdGluc2VydFN0eWxlRWxlbWVudChvcHRpb25zLCBsaW5rRWxlbWVudCk7XHJcblx0cmV0dXJuIGxpbmtFbGVtZW50O1xyXG59XHJcblxyXG5mdW5jdGlvbiBhZGRTdHlsZShvYmosIG9wdGlvbnMpIHtcclxuXHR2YXIgc3R5bGVFbGVtZW50LCB1cGRhdGUsIHJlbW92ZTtcclxuXHJcblx0aWYgKG9wdGlvbnMuc2luZ2xldG9uKSB7XHJcblx0XHR2YXIgc3R5bGVJbmRleCA9IHNpbmdsZXRvbkNvdW50ZXIrKztcclxuXHRcdHN0eWxlRWxlbWVudCA9IHNpbmdsZXRvbkVsZW1lbnQgfHwgKHNpbmdsZXRvbkVsZW1lbnQgPSBjcmVhdGVTdHlsZUVsZW1lbnQob3B0aW9ucykpO1xyXG5cdFx0dXBkYXRlID0gYXBwbHlUb1NpbmdsZXRvblRhZy5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCwgc3R5bGVJbmRleCwgZmFsc2UpO1xyXG5cdFx0cmVtb3ZlID0gYXBwbHlUb1NpbmdsZXRvblRhZy5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCwgc3R5bGVJbmRleCwgdHJ1ZSk7XHJcblx0fSBlbHNlIGlmKG9iai5zb3VyY2VNYXAgJiZcclxuXHRcdHR5cGVvZiBVUkwgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIFVSTC5jcmVhdGVPYmplY3RVUkwgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIFVSTC5yZXZva2VPYmplY3RVUkwgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIEJsb2IgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIGJ0b2EgPT09IFwiZnVuY3Rpb25cIikge1xyXG5cdFx0c3R5bGVFbGVtZW50ID0gY3JlYXRlTGlua0VsZW1lbnQob3B0aW9ucyk7XHJcblx0XHR1cGRhdGUgPSB1cGRhdGVMaW5rLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50KTtcclxuXHRcdHJlbW92ZSA9IGZ1bmN0aW9uKCkge1xyXG5cdFx0XHRyZW1vdmVTdHlsZUVsZW1lbnQoc3R5bGVFbGVtZW50KTtcclxuXHRcdFx0aWYoc3R5bGVFbGVtZW50LmhyZWYpXHJcblx0XHRcdFx0VVJMLnJldm9rZU9iamVjdFVSTChzdHlsZUVsZW1lbnQuaHJlZik7XHJcblx0XHR9O1xyXG5cdH0gZWxzZSB7XHJcblx0XHRzdHlsZUVsZW1lbnQgPSBjcmVhdGVTdHlsZUVsZW1lbnQob3B0aW9ucyk7XHJcblx0XHR1cGRhdGUgPSBhcHBseVRvVGFnLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50KTtcclxuXHRcdHJlbW92ZSA9IGZ1bmN0aW9uKCkge1xyXG5cdFx0XHRyZW1vdmVTdHlsZUVsZW1lbnQoc3R5bGVFbGVtZW50KTtcclxuXHRcdH07XHJcblx0fVxyXG5cclxuXHR1cGRhdGUob2JqKTtcclxuXHJcblx0cmV0dXJuIGZ1bmN0aW9uIHVwZGF0ZVN0eWxlKG5ld09iaikge1xyXG5cdFx0aWYobmV3T2JqKSB7XHJcblx0XHRcdGlmKG5ld09iai5jc3MgPT09IG9iai5jc3MgJiYgbmV3T2JqLm1lZGlhID09PSBvYmoubWVkaWEgJiYgbmV3T2JqLnNvdXJjZU1hcCA9PT0gb2JqLnNvdXJjZU1hcClcclxuXHRcdFx0XHRyZXR1cm47XHJcblx0XHRcdHVwZGF0ZShvYmogPSBuZXdPYmopO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0cmVtb3ZlKCk7XHJcblx0XHR9XHJcblx0fTtcclxufVxyXG5cclxudmFyIHJlcGxhY2VUZXh0ID0gKGZ1bmN0aW9uICgpIHtcclxuXHR2YXIgdGV4dFN0b3JlID0gW107XHJcblxyXG5cdHJldHVybiBmdW5jdGlvbiAoaW5kZXgsIHJlcGxhY2VtZW50KSB7XHJcblx0XHR0ZXh0U3RvcmVbaW5kZXhdID0gcmVwbGFjZW1lbnQ7XHJcblx0XHRyZXR1cm4gdGV4dFN0b3JlLmZpbHRlcihCb29sZWFuKS5qb2luKCdcXG4nKTtcclxuXHR9O1xyXG59KSgpO1xyXG5cclxuZnVuY3Rpb24gYXBwbHlUb1NpbmdsZXRvblRhZyhzdHlsZUVsZW1lbnQsIGluZGV4LCByZW1vdmUsIG9iaikge1xyXG5cdHZhciBjc3MgPSByZW1vdmUgPyBcIlwiIDogb2JqLmNzcztcclxuXHJcblx0aWYgKHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0KSB7XHJcblx0XHRzdHlsZUVsZW1lbnQuc3R5bGVTaGVldC5jc3NUZXh0ID0gcmVwbGFjZVRleHQoaW5kZXgsIGNzcyk7XHJcblx0fSBlbHNlIHtcclxuXHRcdHZhciBjc3NOb2RlID0gZG9jdW1lbnQuY3JlYXRlVGV4dE5vZGUoY3NzKTtcclxuXHRcdHZhciBjaGlsZE5vZGVzID0gc3R5bGVFbGVtZW50LmNoaWxkTm9kZXM7XHJcblx0XHRpZiAoY2hpbGROb2Rlc1tpbmRleF0pIHN0eWxlRWxlbWVudC5yZW1vdmVDaGlsZChjaGlsZE5vZGVzW2luZGV4XSk7XHJcblx0XHRpZiAoY2hpbGROb2Rlcy5sZW5ndGgpIHtcclxuXHRcdFx0c3R5bGVFbGVtZW50Lmluc2VydEJlZm9yZShjc3NOb2RlLCBjaGlsZE5vZGVzW2luZGV4XSk7XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHRzdHlsZUVsZW1lbnQuYXBwZW5kQ2hpbGQoY3NzTm9kZSk7XHJcblx0XHR9XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiBhcHBseVRvVGFnKHN0eWxlRWxlbWVudCwgb2JqKSB7XHJcblx0dmFyIGNzcyA9IG9iai5jc3M7XHJcblx0dmFyIG1lZGlhID0gb2JqLm1lZGlhO1xyXG5cclxuXHRpZihtZWRpYSkge1xyXG5cdFx0c3R5bGVFbGVtZW50LnNldEF0dHJpYnV0ZShcIm1lZGlhXCIsIG1lZGlhKVxyXG5cdH1cclxuXHJcblx0aWYoc3R5bGVFbGVtZW50LnN0eWxlU2hlZXQpIHtcclxuXHRcdHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0LmNzc1RleHQgPSBjc3M7XHJcblx0fSBlbHNlIHtcclxuXHRcdHdoaWxlKHN0eWxlRWxlbWVudC5maXJzdENoaWxkKSB7XHJcblx0XHRcdHN0eWxlRWxlbWVudC5yZW1vdmVDaGlsZChzdHlsZUVsZW1lbnQuZmlyc3RDaGlsZCk7XHJcblx0XHR9XHJcblx0XHRzdHlsZUVsZW1lbnQuYXBwZW5kQ2hpbGQoZG9jdW1lbnQuY3JlYXRlVGV4dE5vZGUoY3NzKSk7XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiB1cGRhdGVMaW5rKGxpbmtFbGVtZW50LCBvYmopIHtcclxuXHR2YXIgY3NzID0gb2JqLmNzcztcclxuXHR2YXIgc291cmNlTWFwID0gb2JqLnNvdXJjZU1hcDtcclxuXHJcblx0aWYoc291cmNlTWFwKSB7XHJcblx0XHQvLyBodHRwOi8vc3RhY2tvdmVyZmxvdy5jb20vYS8yNjYwMzg3NVxyXG5cdFx0Y3NzICs9IFwiXFxuLyojIHNvdXJjZU1hcHBpbmdVUkw9ZGF0YTphcHBsaWNhdGlvbi9qc29uO2Jhc2U2NCxcIiArIGJ0b2EodW5lc2NhcGUoZW5jb2RlVVJJQ29tcG9uZW50KEpTT04uc3RyaW5naWZ5KHNvdXJjZU1hcCkpKSkgKyBcIiAqL1wiO1xyXG5cdH1cclxuXHJcblx0dmFyIGJsb2IgPSBuZXcgQmxvYihbY3NzXSwgeyB0eXBlOiBcInRleHQvY3NzXCIgfSk7XHJcblxyXG5cdHZhciBvbGRTcmMgPSBsaW5rRWxlbWVudC5ocmVmO1xyXG5cclxuXHRsaW5rRWxlbWVudC5ocmVmID0gVVJMLmNyZWF0ZU9iamVjdFVSTChibG9iKTtcclxuXHJcblx0aWYob2xkU3JjKVxyXG5cdFx0VVJMLnJldm9rZU9iamVjdFVSTChvbGRTcmMpO1xyXG59XHJcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcbiAqKiBtb2R1bGUgaWQgPSAzOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9mYXZvdXJpdGVzLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZmF2b3VyaXRlcy5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZmF2b3VyaXRlcy5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDM5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcIkBjaGFyc2V0IFxcXCJVVEYtOFxcXCI7XFxuI2Zhdm91cml0ZXMge1xcbiAgd2lkdGg6IDk5MHB4O1xcbiAgbWFyZ2luOiAzcHggYXV0byAwOyB9XFxuICAjZmF2b3VyaXRlcyAucmlnaHQtY29sdW1uIHtcXG4gICAgd2lkdGg6IDQ5MHB4O1xcbiAgICBmbG9hdDogcmlnaHQ7IH1cXG4gICNmYXZvdXJpdGVzIC5sZWZ0LWNvbHVtbiB7XFxuICAgIHdpZHRoOiA0OTBweDtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG5cXG4uYnVsbGV0aW4tc2hvcnQge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgcGFkZGluZzogMjBweCAxNnB4O1xcbiAgd2lkdGg6IDQ5MHB4O1xcbiAgbWFyZ2luLWJvdHRvbTogN3B4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gIC8qINCb0LXQstCw0Y8g0LrQvtC70L7QvdC60LAgKi9cXG4gIC8qINCf0YDQsNCy0LDRjyDQutC+0LvQvtC90LrQsCAqLyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgPiBkaXYge1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tcmlnaHQtY29sdW1uIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICB3aWR0aDogOTBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tcmlnaHQtY29sdW1uID4gLm9uT3JPZmZMaW5lVXNlciB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHRvcDogNXB4O1xcbiAgICAgIGxlZnQ6IDEwcHg7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tY2VudGVyLWNvbHVtbiB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAxNXB4OyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgaDMge1xcbiAgICBtYXJnaW46IDA7XFxuICAgIGZvbnQ6IDQwMCAyMHB4IC8gMjRweCBSb2JvdG87XFxuICAgIGNvbG9yOiAjMjEyMTIxO1xcbiAgICB3aWR0aDogMjA2cHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmJ1bGxldGluLXNob3J0IGgzOmhvdmVyIHtcXG4gICAgICB0ZXh0LWRlY29yYXRpb246IHVuZGVybGluZTtcXG4gICAgICAtd2Via2l0LXRleHQtZGVjb3JhdGlvbi1jb2xvcjogZ3JheTtcXG4gICAgICAgICAgICAgIHRleHQtZGVjb3JhdGlvbi1jb2xvcjogZ3JheTtcXG4gICAgICAtd2Via2l0LXRleHQtZGVjb3JhdGlvbi1zdHlsZTogZGFzaGVkO1xcbiAgICAgICAgICAgICAgdGV4dC1kZWNvcmF0aW9uLXN0eWxlOiBkYXNoZWQ7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tY2F0ZWdvcnkge1xcbiAgICBjb2xvcjogcmdiYSgzMSwgMzEsIDMxLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNC40cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxMXB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAxNnB4OyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWRlc2NyaXB0aW9uIHtcXG4gICAgZm9udDogNDAwIDEycHggLyAxOC42cHggUm9ib3RvO1xcbiAgICB3aWR0aDogMjU0cHg7XFxuICAgIGNvbG9yOiAjMGQwZDFlOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWltYWdlIHtcXG4gICAgd2lkdGg6IDkwcHg7XFxuICAgIGhlaWdodDogOTFweDtcXG4gICAgYmFja2dyb3VuZDogIzE4NzVEMCB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nXCIpICsgXCIpOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLXByaWNlIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBib3R0b206IC01NHB4O1xcbiAgICByaWdodDogMDtcXG4gICAgY29sb3I6ICMyMDIwMjA7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMTYuOHB4IFJvYm90bzsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1hY3Rpb24ge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGJvdHRvbTogMjBweDtcXG4gICAgcmlnaHQ6IDExN3B4O1xcbiAgICBjb2xvcjogIzIxMjEyMTtcXG4gICAgZm9udDogNDAwIDE0cHggLyAxNi44cHggUm9ib3RvOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBib3R0b206IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIHotaW5kZXg6IDE7IH1cXG4gICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uIHtcXG4gICAgICB3aWR0aDogMTAwcHg7XFxuICAgICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGNTkxMUQ7XFxuICAgICAgdGV4dC1hbGlnbjogY2VudGVyOyB9XFxuICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uOmJlZm9yZSB7XFxuICAgICAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgYm90dG9tOiAtMTRweDtcXG4gICAgICAgIGJvcmRlcjogMTNweCBzb2xpZCAjZTU3YjAwO1xcbiAgICAgICAgei1pbmRleDogLTE7XFxuICAgICAgICBsZWZ0OiAtMjNweDtcXG4gICAgICAgIGJvcmRlci1yaWdodC13aWR0aDogMS41ZW07XFxuICAgICAgICBib3JkZXItbGVmdC1jb2xvcjogdHJhbnNwYXJlbnQ7XFxuICAgICAgICBib3gtc2hhZG93OiAycHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpOyB9XFxuICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uOmFmdGVyIHtcXG4gICAgICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICBib3R0b206IDA7XFxuICAgICAgICBib3JkZXI6IDEzcHggc29saWQgI0Y1OTExRDtcXG4gICAgICAgIHJpZ2h0OiAtMTNweDtcXG4gICAgICAgIGJvcmRlci1sZWZ0LXdpZHRoOiAwO1xcbiAgICAgICAgYm9yZGVyLXJpZ2h0LWNvbG9yOiB0cmFuc3BhcmVudDsgfVxcbiAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbiA+IC5yaWJib24tY29udGVudCB7XFxuICAgICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjZweCBSb2JvdG87XFxuICAgICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbiA+IC5yaWJib24tY29udGVudDpiZWZvcmUge1xcbiAgICAgICAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgICAgYm9yZGVyLXN0eWxlOiBzb2xpZDtcXG4gICAgICAgICAgYm9yZGVyLWNvbG9yOiAjMkI0QTY3IHRyYW5zcGFyZW50IHRyYW5zcGFyZW50IHRyYW5zcGFyZW50O1xcbiAgICAgICAgICBib3R0b206IC0xNHB4O1xcbiAgICAgICAgICBsZWZ0OiAwO1xcbiAgICAgICAgICBib3JkZXItd2lkdGg6IDFlbSAwIDAgMWVtOyB9XFxuXFxuLmNoZWNrQm94IHtcXG4gIHdpZHRoOiAyNXB4O1xcbiAgaGVpZ2h0OiAyNXB4O1xcbiAgYm9yZGVyOiAxcHggc29saWQgZ3JleTtcXG4gIGJvcmRlci1yYWRpdXM6IDVweDtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcblxcbi5jaGVja2VkIHtcXG4gIGJhY2tncm91bmQ6ICMxODc1RDAgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9WLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGJvcmRlci1jb2xvcjogIzE4NzVEMCAhaW1wb3J0YW50OyB9XFxuXFxuZGl2LmV4Y2xhbWF0aW9uUG9pbnQge1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9leGNsYW1hdGlvblBvaW50LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGhlaWdodDogMjdweDtcXG4gIHdpZHRoOiAyN3B4O1xcbiAgbWFyZ2luLXRvcDogMTBweDsgfVxcblxcbi5kb2xsYXJCaWxsIHtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZG9sbGFyQmlsbC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICBoZWlnaHQ6IDI3cHg7XFxuICB3aWR0aDogMjdweDtcXG4gIG1hcmdpbi10b3A6IDEwcHg7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9idWxsZXRpbi1kZWZhdWx0LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYnVsbGV0aW4tZGVmYXVsdC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0MVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1YucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9WLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2V4Y2xhbWF0aW9uUG9pbnQucG5nXG4gKiogbW9kdWxlIGlkID0gNDNcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9kb2xsYXJCaWxsLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZG9sbGFyQmlsbC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0NFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9lZGl0LXByb2ZpbGUuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9lZGl0LXByb2ZpbGUuc2Nzc1wiLCBmdW5jdGlvbigpIHtcblx0XHRcdHZhciBuZXdDb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2VkaXQtcHJvZmlsZS5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL2VkaXQtcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwic2VjdGlvbi5lZGl0UHJvZmlsZSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIHdpZHRoOiAxMTA1cHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDQwcHg7XFxuICBwYWRkaW5nOiA2NXB4IDAgNDVweCAwO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSB7XFxuICAgIHdpZHRoOiA2NTBweDtcXG4gICAgbWFyZ2luOiAwIGF1dG87IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IGgyIHtcXG4gICAgICBmb250OiA3MDAgMjJweCBSb2JvdG87XFxuICAgICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5zZWxlY3RCb3gge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWU5ZTllO1xcbiAgICAgIG1hcmdpbi10b3A6IDE1cHg7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICAgIHBhZGRpbmctbGVmdDogNXB4OyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuZWRpdC1wcm9maWxlLWZvcm0tZm90byB7XFxuICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzllOWU5ZTtcXG4gICAgICB3aWR0aDogNDEwcHg7XFxuICAgICAgcGFkZGluZy1ib3R0b206IDVweDsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuZWRpdC1wcm9maWxlLWZvcm0tZm90byA+IGlucHV0IHtcXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgIHotaW5kZXg6IC0xOyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvID4gcCB7XFxuICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgcGFkZGluZy10b3A6IDExcHg7XFxuICAgICAgICBwYWRkaW5nLWxlZnQ6IDVweDtcXG4gICAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuZWRpdC1wcm9maWxlLWZvcm0tZm90byA+IC5idG4tYmx1ZSB7XFxuICAgICAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICAgICAgaGVpZ2h0OiAzMHB4O1xcbiAgICAgICAgd2lkdGg6IDg1cHg7XFxuICAgICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgICBsaW5lLWhlaWdodDogMzBweDsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIC5lZGl0LXByb2ZpbGUtZm9ybS1jb250YWN0cy1jb250YWluZXIgLmlucHV0Rm9ybSB7XFxuICAgICAgd2lkdGg6IDk1JTtcXG4gICAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSAuZWRpdC1wcm9maWxlLWZvcm0tY29udGFjdHMtY29udGFpbmVyIGJ1dHRvbiB7XFxuICAgICAgd2lkdGg6IDE2cHg7XFxuICAgICAgaGVpZ2h0OiAxNnB4O1xcbiAgICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2ljb25fY2xvc2VfYmx1ZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICAgIGJhY2tncm91bmQtc2l6ZTogY29udGFpbjsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIC5zb2NpYWwtbGluay1jb250YWluZXIge1xcbiAgICAgIGhlaWdodDogYXV0bzsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXYge1xcbiAgICAgICAgd2lkdGg6IDI0cHg7XFxuICAgICAgICBoZWlnaHQ6IDI0cHg7XFxuICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgIG1hcmdpbi1yaWdodDogNDdweDsgfVxcbiAgc2VjdGlvbi5lZGl0UHJvZmlsZSAudXBsb2FkRmlsZUZvcm0ge1xcbiAgICB2aXNpYmlsaXR5OiBoaWRkZW47IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSAudXBsb2FkRmlsZUZvcm0gaW5wdXQge1xcbiAgICAgIHdpZHRoOiAwcHg7XFxuICAgICAgaGVpZ2h0OiAwcHg7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvZWRpdC1wcm9maWxlLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0NlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2ljb25fY2xvc2VfYmx1ZS5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2ljb25fY2xvc2VfYmx1ZS5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0N1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vcHJvZmlsZS5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vcHJvZmlsZS5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL3Byb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDQ4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcIi52aWV3LXByb2ZpbGUtY29udGFpbmVyIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNmZmY7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgcGFkZGluZzogNDBweCAxMjdweCAzMHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwO1xcbiAgd2lkdGg6IDExMDNweDtcXG4gIGZvbnQ6IDQwMCAxNnB4LzI0cHggUm9ib3RvO1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaDEge1xcbiAgICBwYWRkaW5nLWJvdHRvbTogMTZweDtcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBmb250OiA0MDAgMjJweC8yNnB4IFJvYm90bzsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaW5wdXQ6LW1vei1yZWFkLW9ubHkge1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lOyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDpyZWFkLW9ubHkge1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lOyB9XFxuICAgIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0Oi1tb3otcmVhZC1vbmx5Om5vdCg6bGFzdC1vZi10eXBlKSB7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDpyZWFkLW9ubHk6bm90KDpsYXN0LW9mLXR5cGUpIHtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciAuc29jaWFsLWxpbmstY29udGFpbmVyIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcblxcbi5wcm9maWxlLWNvbnRhaW5lcnMtd3JhcCB7XFxuICB3aWR0aDogODQ5cHg7XFxuICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5wcm9maWxlLWxlZnQtY29udGFpbmVyLCAucHJvZmlsZS1yaWdodC1jb250YWluZXIsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0LCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQsIC5wcm9maWxlLWluZm8ge1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLWxlZnQtY29udGFpbmVyOjphZnRlciwgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyOjphZnRlciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLWxlZnQ6OmFmdGVyLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQ6OmFmdGVyLCAucHJvZmlsZS1pbmZvOjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4udmlldy1wcm9maWxlOjphZnRlciwgLnZpZXctcHJvZmlsZS1jb250YWluZXI6OmFmdGVyIHtcXG4gIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgZGlzcGxheTogdGFibGU7XFxuICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLWxlZnQtY29udGFpbmVyIHtcXG4gIHdpZHRoOiAxOTBweDtcXG4gIHBhZGRpbmc6IDAgNTJweDsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXIgLmJ0bi1ibHVlIHtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBoZWlnaHQ6IDM2cHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNnB4O1xcbiAgICBtYXJnaW46IDAgYXV0bztcXG4gICAgbWFyZ2luLXRvcDogOXB4OyB9XFxuICAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciA+IC5oei1jZW50ZXJpbmctd3JhcHBlciA+IC5wcm9maWxlLXNldHRpbmdzID4gLnNlbGVjdEJveCB7XFxuICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICBtYXJnaW4tYm90dG9tOiAyNXB4OyB9XFxuICAgIC5wcm9maWxlLWxlZnQtY29udGFpbmVyID4gLmh6LWNlbnRlcmluZy13cmFwcGVyID4gLnByb2ZpbGUtc2V0dGluZ3MgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgZm9udDogNDAwIDE2cHggLyAyNHB4IFJvYm90bzsgfVxcblxcbi5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciB7XFxuICB3aWR0aDogNDg1cHg7XFxuICBwYWRkaW5nLWxlZnQ6IDIwcHg7XFxuICBib3JkZXItbGVmdDogMXB4IHNvbGlkICNlYmViZWI7IH1cXG4gIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciA+IC53cmFwRm9yRGl2IHtcXG4gICAgd2lkdGg6IGF1dG87XFxuICAgIGZsb2F0OiBub25lO1xcbiAgICBtYXJnaW4tYm90dG9tOiAwOyB9XFxuICAgIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciA+IC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkge1xcbiAgICAgIHdpZHRoOiA1MCU7IH1cXG4gICAgICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYSB7XFxuICAgICAgICB3aWR0aDogYXV0bzsgfVxcbiAgICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3Mge1xcbiAgICAgIHdpZHRoOiA1MDBweDsgfVxcblxcbi5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0LCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQge1xcbiAgd2lkdGg6IDUwJTtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG5cXG4ucHJvZmlsZS1hdmF0YXIge1xcbiAgd2lkdGg6IDE0NXB4O1xcbiAgaGVpZ2h0OiAyMTVweDtcXG4gIG1hcmdpbjogMCBhdXRvIDQwcHg7XFxuICBwYWRkaW5nLWJvdHRvbTogNXB4O1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9hdmF0YXIuanBnXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgYmFja2dyb3VuZC1zaXplOiBjb250YWluO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcblxcbi5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIHtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIHBhZGRpbmc6IDEzcHggMDsgfVxcbiAgLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24gLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24tdGl0bGU6OmFmdGVyIHtcXG4gICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlO1xcbiAgICBjbGVhcjogYm90aDsgfVxcbiAgLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24gLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24tdGl0bGUgc3BhbiB7XFxuICAgIGNvbG9yOiAjOTI5MjkyO1xcbiAgICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24gLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24tdGl0bGUgZGl2IHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NhcmV0X2JsYWNrLnBuZ1wiKSArIFwiKTtcXG4gICAgd2lkdGg6IDEzcHg7XFxuICAgIGhlaWdodDogN3B4OyB9XFxuXFxuLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXYge1xcbiAgY3Vyc29yOiBwb2ludGVyO1xcbiAgd2lkdGg6IDI0cHg7XFxuICBoZWlnaHQ6IDI0cHg7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXY6OmFmdGVyIHtcXG4gICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlO1xcbiAgICBjbGVhcjogYm90aDsgfVxcbiAgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXY6bm90KDpsYXN0LW9mLXR5cGUpIHtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyM3B4OyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMge1xcbiAgZGlzcGxheTogYmxvY2s7XFxuICB3aWR0aDogMTAwJTtcXG4gIG1hcmdpbjogMzBweCAwO1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zOjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYsIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQge1xcbiAgYm9yZGVyOiAxcHggc29saWQgI2ViZWJlYjtcXG4gIG92ZXJmbG93OiBoaWRkZW47IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYgZGl2IHtcXG4gIGNvbG9yOiAjOTI5MjkyO1xcbiAgd2lkdGg6IDUwJTtcXG4gIHBhZGRpbmc6IDI0cHggMDtcXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gIGZsb2F0OiBsZWZ0OyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYgZGl2OjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYgLm5hdi1pdGVtLXNlbGVjdGVkIHtcXG4gIGNvbG9yOiAjN2VhZWUwO1xcbiAgYm9yZGVyLWJvdHRvbTogMnB4IHNvbGlkICNmZjUyNTI7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50IHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNmNmY2ZjY7XFxuICBoZWlnaHQ6IDE4MHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSB7XFxuICB3aWR0aDogMTAwJTtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjZWJlYmViO1xcbiAgaGVpZ2h0OiAxMjNweDtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIG92ZXJmbG93OiBoaWRkZW47IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSAuY29udGVudC1pdGVtLWxvZ28ge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHRvcDogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgd2lkdGg6IDI3cHg7XFxuICAgIGhlaWdodDogMjdweDtcXG4gICAgcGFkZGluZzogMCAyM3B4IDIzcHggN3B4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2ljb25fdXNlci5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgIGJhY2tncm91bmQtcG9zaXRpb246IGNlbnRlcjtcXG4gICAgbWFyZ2luLXRvcDogMjhweDsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIC5jb250ZW50LWl0ZW0tdGV4dCB7XFxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICB3aWR0aDogMzczcHg7XFxuICAgIG1hcmdpbjogMjhweCAzOHB4IDI0cHggMDtcXG4gICAgZm9udDogNDAwIDEycHgvMThweCBSb2JvdG87XFxuICAgIGNvbG9yOiAjMGMwYzFlOyB9XFxuICAgIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSAuY29udGVudC1pdGVtLXRleHQ6OmFmdGVyIHtcXG4gICAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLWluZm8ge1xcbiAgbWFyZ2luLWJvdHRvbTogMzBweDsgfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9wcm9maWxlLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0OVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2F2YXRhci5qcGdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2F2YXRhci5qcGdcbiAqKiBtb2R1bGUgaWQgPSA1MFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0X2JsYWNrLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nXG4gKiogbW9kdWxlIGlkID0gNTFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9pY29uX3VzZXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9pY29uX3VzZXIucG5nXG4gKiogbW9kdWxlIGlkID0gNTJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgZGVidWcgPSByZXF1aXJlKCcuLi9kYXRhL2RlYnVnJylcclxuXHJcbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIGN0eC5ERUJVR19MRVZFTCA9IGRlYnVnLkRFQlVHXHJcblxyXG4gIGNvbnNvbGUubG9nID0gKGZ1bmN0aW9uKCkge1xyXG4gICAgbGV0IGxvZyA9IGNvbnNvbGUubG9nXHJcbiAgICBpZiggY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLkRFQlVHIClcclxuICAgICAgcmV0dXJuIGxvZ1xyXG4gICAgZWxzZSByZXR1cm4gKCkgPT4ge31cclxuICB9LmJpbmQoY3R4KSkoKVxyXG5cclxuICBjb25zb2xlLmVycm9yID0gKGZ1bmN0aW9uKCkge1xyXG4gICAgbGV0IGVycm9yID0gY29uc29sZS5lcnJvclxyXG4gICAgaWYoIGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5ERUJVRyB8fCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuT05MWV9FUlJPUlMgKVxyXG4gICAgICByZXR1cm4gZXJyb3JcclxuICAgIGVsc2UgcmV0dXJuICgpID0+IHt9XHJcbiAgfS5iaW5kKGN0eCkpKClcclxuXHJcbiAgY29uc29sZS5pbmZvID0gKGZ1bmN0aW9uKCkge1xyXG4gICAgbGV0IGluZm8gPSBjb25zb2xlLmluZm9cclxuICAgIGlmKCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuREVCVUcgfHwgY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLk9OTFlfRVJST1JTIClcclxuICAgICAgcmV0dXJuIGluZm9cclxuICAgIGVsc2UgcmV0dXJuICgpID0+IHt9XHJcbiAgfS5iaW5kKGN0eCkpKClcclxuXHJcbiAgLyogINCU0LvRjyDRhdC+0YXQvNGLICovXHJcbiAgd2luZG93LmxzID0gXCJZb3UndmUgbWlzc2VkIGEgd2luZG93LCBsb2wgPSlcIlxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbG9nZ2VyLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSB7XG5cdFwiREVCVUdcIjogMCxcblx0XCJPTkxZX0VSUk9SU1wiOiAxLFxuXHRcIlBST0RVQ1RJT05cIjogMlxufTtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vZGF0YS9kZWJ1Zy5qc29uXG4gKiogbW9kdWxlIGlkID0gNTRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgTU9EVUxFUyA9IHtcclxuICBcImNoZWNrYm94XCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvY2hlY2tib3gnKSxcclxuICBcIm5pY2VCdXR0b25cIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9uaWNlQnV0dG9uJyksXHJcbiAgXCJ0ZXh0XCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvdGV4dCcpLFxyXG4gIFwic2VsZWN0Qm94XCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvc2VsZWN0Qm94JylcclxufVxyXG5cclxud2luZG93LmVlID0gcmVxdWlyZSgnLi9ldmVudHMnKVxyXG5lZS5pbml0KClcclxuXHJcbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XHJcblxyXG5tb2R1bGUuZXhwb3J0cy5pbml0ID0gYXBwID0+IHtcclxuICBmb3IobGV0IGtleSBpbiBNT0RVTEVTKVxyXG4gICAgYXBwLmRpcmVjdGl2ZShrZXksIE1PRFVMRVNba2V5XSlcclxuXHJcbiAgcmV0dXJuIGFwcFxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIHJldHVybiB7XHJcbiAgICByZXN0cmljdDogXCJFXCIsXHJcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxyXG4gICAgc2NvcGU6IHtcclxuICAgICAgbmdNb2RlbDogXCI9XCJcclxuICAgIH0sXHJcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJjaGVja0JveFwiPjwvZGl2PmAsXHJcbiAgICByZXBsYWNlOiB0cnVlLFxyXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCkge1xyXG4gICAgICBsZXQgZWwgPSAkZWxlbWVudFswXVxyXG4gICAgICAvLy5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdjaGVja0JveCcpWzBdXHJcblxyXG4gICAgICBpZigkc2NvcGUubmdNb2RlbCAmJiAhZWwuY2xhc3NMaXN0LmNvbnRhaW5zKCdjaGVja2VkJykpXHJcbiAgICAgICAgZWwuY2xhc3NMaXN0LmFkZCgnY2hlY2tlZCcpXHJcbiAgICAgIGVsc2UgaWYoISRzY29wZS5uZ01vZGVsICYmIGVsLmNsYXNzTGlzdC5jb250YWlucygnY2hlY2tlZCcpKVxyXG4gICAgICAgIGVsLmNsYXNzTGlzdC5yZW1vdmUoJ2NoZWtlZCcpXHJcblxyXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGUgPT4ge1xyXG4gICAgICAgIGVsLmNsYXNzTGlzdC50b2dnbGUoJ2NoZWNrZWQnKVxyXG4gICAgICAgICRzY29wZS5uZ01vZGVsID0gJHNjb3BlLm5nTW9kZWwgPyAgZmFsc2UgOiB0cnVlXHJcbiAgICAgIH0pXHJcbiAgICB9XHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvY2hlY2tib3guanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgcmV0dXJuIHtcclxuICAgIHJlc3RyaWN0OiBcIkVcIixcclxuICAgIHRyYW5zY2x1ZGU6IHRydWUsXHJcbiAgICBzY29wZSA6IHtcclxuICAgICAgY2xhc3M6IFwiQFwiLFxyXG4gICAgICBuZ0NsaWNrOiBcIiZcIlxyXG4gICAgfSxcclxuICAgIHJlcGxhY2U6IHRydWUsXHJcbiAgICB0ZW1wbGF0ZSA6IGA8ZGl2IGNsYXNzPVwie3sgY2xhc3MgfX1cIj5cclxuICAgICAgICAgICAgICAgICAgPHNwYW4gY2xhc3M9XCJpbmtcIj48L3NwYW4+XHJcbiAgICAgICAgICAgICAgICAgIDxuZy10cmFuc2NsdWRlIHN0eWxlPVwiZGlzcGxheTpibG9jazsgd2lkdGg6MTAwJTsgaGVpZ2h0OmluaGVyaXQ7XCI+PC9uZy10cmFuc2NsdWRlPlxyXG4gICAgICAgICAgICAgICAgPC9kaXY+YCxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQpIHtcclxuICAgICAgbGV0IG9uQ2xpY2sgPSBmdW5jdGlvbihlKSB7XHJcbiAgICAgICAgbGV0IGluayA9IHRoaXMuZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnaW5rJylbMF1cclxuICAgICAgICBpbmsuY2xhc3NMaXN0LnJlbW92ZSgnYW5pbWF0ZScpXHJcblxyXG4gICAgICAgIGxldCByZWN0ID0gdGhpcy5nZXRCb3VuZGluZ0NsaWVudFJlY3QoKVxyXG5cclxuICAgICAgICBpZiggIWluay5jbGllbnRIZWlnaHQgJiYgIWluay5jbGllbnRXaWR0aCApIHtcclxuICAgICAgICAgIGxldCBkID0gTWF0aC5tYXgodGhpcy5jbGllbnRXaWR0aCwgdGhpcy5jbGllbnRIZWlnaHQpXHJcbiAgICAgICAgICBpbmsuc3R5bGUuaGVpZ2h0ID0gaW5rLnN0eWxlLndpZHRoID0gYCR7ZH1weGBcclxuICAgICAgICB9XHJcblxyXG4gICAgICAgIGluay5zdHlsZS50b3AgPSBgJHtlLmNsaWVudFkgLSByZWN0LnRvcCAtIGluay5jbGllbnRIZWlnaHQvMn1weGBcclxuICAgICAgICBpbmsuc3R5bGUubGVmdCA9IGAke2UuY2xpZW50WCAtIHJlY3QubGVmdCAtaW5rLmNsaWVudFdpZHRoLzJ9cHhgXHJcbiAgICAgICAgaW5rLmNsYXNzTGlzdC5hZGQoJ2FuaW1hdGUnKVxyXG4gICAgICB9XHJcblxyXG4gICAgICBpZigkc2NvcGUubmdDbGljaylcclxuICAgICAgICAkZWxlbWVudFswXS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsICRzY29wZS5uZ0NsaWNrKVxyXG4gICAgICAgIFxyXG4gICAgICAkZWxlbWVudFswXS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIG9uQ2xpY2spXHJcbiAgICB9XHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvbmljZUJ1dHRvbi5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgQ09MT1JTID0ge1xyXG4gICAgYmx1ZTogXCIjMTg3NUQwXCIsXHJcbiAgICB3aGl0ZTogXCJ3aGl0ZVwiXHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgcmV0dXJuIHtcclxuICAgIHJlc3RyaWN0OiBcIkVcIixcclxuICAgIHJlcXVpcmU6ICdebmdNb2RlbCcsXHJcbiAgICBzY29wZSA6IHtcclxuICAgICAgbGFiZWw6IFwiQFwiLFxyXG4gICAgICBuZ01vZGVsOiBcIj1cIixcclxuICAgICAgY29sb3I6IFwiQFwiLFxyXG4gICAgICB0eXBlOiBcIkBcIixcclxuICAgICAgdmFsaWRhdGU6IFwiPVwiLFxyXG4gICAgICBpc1ZhbGlkOiBcIj1cIlxyXG4gICAgfSxcclxuICAgIHJlcGxhY2U6IHRydWUsXHJcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJpbnB1dEZvcm1cIj5cclxuICAgICAgICAgICAgICAgICA8bGFiZWw+e3sgbGFiZWwgfX08L2xhYmVsPlxyXG4gICAgICAgICAgICAgICAgIDxpbnB1dCB0eXBlPVwie3sgdHlwZSB8fCAndGV4dCd9fVwiIG5nLW1vZGVsPVwibmdNb2RlbFwiPlxyXG4gICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJlcnJvcnNcIj48L2Rpdj5cclxuICAgICAgICAgICAgICAgPC9kaXY+YCxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQsICR0aW1lb3V0KSB7XHJcbiAgICAgIGxldCBpZCA9IGVlLm9uKCdmb3JtLXN1Ym1pdCcsIHZhbGlkYXRlKVxyXG4gICAgICAkc2NvcGUuJG9uKFwiJGRlc3Ryb3lcIiwgZnVuY3Rpb24oKSB7XHJcbiAgICAgICAgZWUub2ZmKGlkKVxyXG4gICAgICB9LmJpbmQodGhpcykpXHJcblxyXG4gICAgICBsZXQgZGVmYXVsdEJvcmRlciA9IFwiXCJcclxuXHJcbiAgICAgIGxldCBlbCA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlUYWdOYW1lKCdpbnB1dCcpWzBdLFxyXG4gICAgICAgICAgbGFiZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgnbGFiZWwnKVswXSxcclxuICAgICAgICAgIGVycm9yID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnZXJyb3JzJylbMF1cclxuXHJcblxyXG4gICAgICBmdW5jdGlvbiB2YWxpZGF0ZSgpIHtcclxuICAgICAgICBpZigkc2NvcGUudmFsaWRhdGUpIHtcclxuICAgICAgICAgIGZ1bmN0aW9uIGhhbmRsZShlcnJvcikge1xyXG4gICAgICAgICAgICBpZih0eXBlb2YgJHNjb3BlLmlzVmFsaWQgIT09IFwidW5kZWZpbmVkXCIpIHtcclxuICAgICAgICAgICAgICBpZihlcnJvci5sZW5ndGgpXHJcbiAgICAgICAgICAgICAgICAkc2NvcGUuaXNWYWxpZCA9IGZhbHNlXHJcbiAgICAgICAgICAgICAgZWxzZVxyXG4gICAgICAgICAgICAgICAgJHNjb3BlLmlzVmFsaWQgPSB0cnVlXHJcblxyXG4gICAgICAgICAgICAgICRzY29wZS4kYXBwbHkoKVxyXG4gICAgICAgICAgICB9XHJcbiAgICAgICAgICB9XHJcblxyXG4gICAgICAgICAgbGV0IHJlc3AgPSAkc2NvcGUudmFsaWRhdGUoZWwudmFsdWUpXHJcblxyXG4gICAgICAgICAgaWYoIHR5cGVvZiByZXNwID09PSBcInN0cmluZ1wiKVxyXG4gICAgICAgICAgICBoYW5kbGUoIGVycm9yLmlubmVySFRNTCA9IHJlc3ApXHJcbiAgICAgICAgICBlbHNlXHJcbiAgICAgICAgICAgIHJlc3AudGhlbiggZnVuY3Rpb24oZGF0YSkge1xyXG4gICAgICAgICAgICAgICAgZXJyb3IuaW5uZXJIVE1MID0gZGF0YVxyXG4gICAgICAgICAgICAgICAgaGFuZGxlKGRhdGEpXHJcbiAgICAgICAgICAgICAgfSwgY29uc29sZS5lcnJvcilcclxuXHJcblxyXG4gICAgICAgIH1cclxuICAgICAgfVxyXG5cclxuICAgICAgZnVuY3Rpb24gb25CbHVyKGUpIHtcclxuICAgICAgICBpZiggISRzY29wZS5uZ01vZGVsLmxlbmd0aClcclxuICAgICAgICAgIGhpZGVBbmltYXRpb24oKVxyXG5cclxuICAgICAgICAgIHZhbGlkYXRlKClcclxuICAgICAgfVxyXG5cclxuICAgICAgZnVuY3Rpb24gb25Gb2N1cyhlKSB7XHJcbiAgICAgICAgaWYoISRzY29wZS5uZ01vZGVsLmxlbmd0aClcclxuICAgICAgICAgIGRpc3BsYXlBbmltYXRpb24oKVxyXG4gICAgICB9XHJcblxyXG4gICAgICBmdW5jdGlvbiBkaXNwbGF5QW5pbWF0aW9uKCkge1xyXG4gICAgICAgIGxhYmVsLnN0eWxlLmNvbG9yID0gQ09MT1JTWyRzY29wZS5jb2xvcl1cclxuICAgICAgICBpZighZGVmYXVsdEJvcmRlci5sZW5ndGgpIHtcclxuICAgICAgICAgIGRlZmF1bHRCb3JkZXIgPSB3aW5kb3cuZ2V0Q29tcHV0ZWRTdHlsZShsYWJlbC5wYXJlbnROb2RlKS5ib3JkZXJCb3R0b21cclxuICAgICAgICB9IGVsc2Uge1xyXG4gICAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBgMnB4IHNvbGlkICR7Q09MT1JTWyRzY29wZS5jb2xvcl19YFxyXG4gICAgICAgIH1cclxuXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LmFkZCgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIGhpZGVBbmltYXRpb24oKSB7XHJcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXHJcbiAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBkZWZhdWx0Qm9yZGVyXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LnJlbW92ZSgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgICR0aW1lb3V0KCAoKSA9PiB7XHJcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXHJcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcclxuICAgICAgICBlbHNlXHJcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcclxuICAgICAgfSwgMjUwKVxyXG5cclxuXHJcbiAgICAgIGVsLmFkZEV2ZW50TGlzdGVuZXIoJ2JsdXInLCBvbkJsdXIuYmluZCh0aGlzKSlcclxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignZm9jdXMnLCBvbkZvY3VzLmJpbmQoJHNjb3BlKSlcclxuICAgIH1cclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0LmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgcmV0dXJuIHtcclxuICAgIHJlc3RyaWN0OiBcIkVcIixcclxuICAgIHJlcXVpcmU6ICdebmdNb2RlbCcsXHJcbiAgICBzY29wZToge1xyXG4gICAgICBuZ01vZGVsOiBcIj1cIixcclxuICAgICAgc2hvdzogXCJAXCJcclxuICAgIH0sXHJcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJzZWxlY3RCb3hcIj5cclxuICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJkZWZhdWx0VmFsdWVcIiBuZy1oaWRlPVwic2hvd1wiPlxyXG4gICAgICAgICAgICAgICAgICA8cD57eyBuZ01vZGVsIH19PC9wPlxyXG4gICAgICAgICAgICAgICAgPC9kaXY+XHJcbiAgICAgICAgICAgICAgICA8ZGl2IGNsYXNzPVwibGlzdE9mVmFsdWVzXCIgbmctc2hvdz1cInNob3dcIj5cclxuICAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cImxpc3RJdGVtXCIgbmctcmVwZWF0PVwiaXRlbSBpbiBpdGVtc1wiIHZhbHVlPVwie3tpdGVtfX1cIj57eyBpdGVtIH19PC9kaXY+XHJcbiAgICAgICAgICAgICAgICA8L2Rpdj5cclxuICAgICAgICAgICAgICA8L2Rpdj5gLFxyXG4gICAgcmVwbGFjZTogdHJ1ZSxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQsICR0aW1lb3V0KSB7XHJcbiAgICAgIGxldCBkZWZhdWx0VmFsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnZGVmYXVsdFZhbHVlJylbMF0sXHJcbiAgICAgICAgICBsaXN0T2ZWYWx1ZXMgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdsaXN0T2ZWYWx1ZXMnKVswXVxyXG5cclxuICAgICAgJHRpbWVvdXQoZnVuY3Rpb24oKSB7XHJcbiAgICAgICAgZGVmYXVsdFZhbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGZ1bmN0aW9uKGUpIHtcclxuICAgICAgICAgIHRoaXMuc2hvdyA9IHRydWVcclxuXHJcbiAgICAgICAgICBkb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGhhbmRsZXIpXHJcbiAgICAgICAgfS5iaW5kKCRzY29wZSkpXHJcblxyXG4gICAgICAgIGZ1bmN0aW9uIGhhbmRsZXIoZSkge1xyXG4gICAgICAgICAgaWYoICEoZS50YXJnZXQgPT0gbGlzdE9mVmFsdWVzIHx8XHJcbiAgICAgICAgICAgICAgICBlLnRhcmdldC5wYXJlbnROb2RlID09IGxpc3RPZlZhbHVlcyB8fFxyXG4gICAgICAgICAgICAgICAgZS50YXJnZXQgPT0gZGVmYXVsdFZhbCB8fFxyXG4gICAgICAgICAgICAgICAgZS50YXJnZXQucGFyZW50Tm9kZSA9PSBkZWZhdWx0VmFsKSApIHtcclxuICAgICAgICAgICAgJHNjb3BlLnNob3cgPSBmYWxzZVxyXG4gICAgICAgICAgICAkc2NvcGUuJGFwcGx5KClcclxuICAgICAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcignY2xpY2snLCBoYW5kbGVyKVxyXG4gICAgICAgICAgfVxyXG4gICAgICAgIH1cclxuXHJcbiAgICAgICAgZm9yKGxldCB0PTA7dDxsaXN0T2ZWYWx1ZXMuY2hpbGRyZW4ubGVuZ3RoOyB0KyspIHtcclxuXHJcbiAgICAgICAgICBsaXN0T2ZWYWx1ZXMuY2hpbGRyZW5bdF0uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBmdW5jdGlvbihlKSB7XHJcbiAgICAgICAgICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcclxuICAgICAgICAgICAgdGhpcy5zaG93ID0gZmFsc2VcclxuICAgICAgICAgICAgdGhpcy5uZ01vZGVsID0gZS50YXJnZXQuaW5uZXJIVE1MXHJcbiAgICAgICAgICB9LmJpbmQoJHNjb3BlKSlcclxuICAgICAgICB9XHJcbiAgICAgIH0uYmluZCh0aGlzKSwgMTAwMClcclxuICAgIH0sXHJcbiAgICBsaW5rOiBmdW5jdGlvbihzY29wZSwgZWxlbWVudCwgYXR0cnMpIHtcclxuICAgICAgc2NvcGUuaXRlbXMgPSBKU09OLnBhcnNlKGF0dHJzLml0ZW1zKVxyXG4gICAgfVxyXG4gIH1cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL3NlbGVjdEJveC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubGV0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cclxuXHJcbmxldCBwcml2YXRlU2NvcGUgPSB7fVxyXG5cclxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGZ1bmN0aW9uKCkge1xyXG4gIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzID0ge31cclxuICBwcml2YXRlU2NvcGUuaGFuZGxlcklkID0gMFxyXG5cclxuICBwcml2YXRlU2NvcGUuZ2V0SGFuZGxlcklkID0gZnVuY3Rpb24oKSB7XHJcbiAgICByZXR1cm4gcHJpdmF0ZVNjb3BlLmhhbmRsZXJJZCsrXHJcbiAgfVxyXG5cclxuICBwcml2YXRlU2NvcGUucmVnaXN0ZXJIYW5kbGVyID0gZnVuY3Rpb24obmFtZSwgaGFuZGxlcikge1xyXG4gICAgaWYoIXByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW25hbWVdKVxyXG4gICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXSA9IFtdXHJcblxyXG4gICAgbGV0IGlkID0gcHJpdmF0ZVNjb3BlLmdldEhhbmRsZXJJZCgpXHJcbiAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXS5wdXNoKHtcclxuICAgICAgaWQgOiBpZCxcclxuICAgICAgaGFuZGxlciA6IGhhbmRsZXJcclxuICAgIH0pXHJcblxyXG4gICAgcmV0dXJuIGlkXHJcbiAgfVxyXG5cclxuICBwcml2YXRlU2NvcGUuaGFuZGxlID0gZnVuY3Rpb24oZXZlbnROYW1lLCBkYXRhKSB7XHJcbiAgICBpZihwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tldmVudE5hbWVdKVxyXG4gICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tldmVudE5hbWVdLmZvckVhY2goaCA9PiBoLmhhbmRsZXIoZGF0YSkpXHJcbiAgfVxyXG5cclxuICBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlciA9IGZ1bmN0aW9uKGlkKSB7XHJcbiAgICBmb3IobGV0IGtleSBpbiBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVycykge1xyXG4gICAgICBmb3IobGV0IHQgPTA7IHQ8IHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV0ubGVuZ3RoOyB0KyspIHtcclxuICAgICAgICBpZihwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldW3RdLmlkID09IGlkKSB7XHJcbiAgICAgICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldID0gcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNba2V5XS5maWx0ZXIoZWwgPT4gZWwuaWQgIT09IGlkKVxyXG4gICAgICAgICAgcmV0dXJuIHRydWVcclxuICAgICAgICB9XHJcbiAgICAgIH1cclxuICAgIH1cclxuXHJcbiAgICByZXR1cm4gZmFsc2VcclxuICB9XHJcblxyXG4gIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyQnlOYW1lID0gZnVuY3Rpb24obmFtZSkge1xyXG4gICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0gPSBbXVxyXG4gIH1cclxufVxyXG5cclxuXHJcbm1vZHVsZS5leHBvcnRzLm9uID0gZnVuY3Rpb24oZXZlbnROYW1lLCBoYW5kbGVyKSB7XHJcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZWdpc3RlckhhbmRsZXIoZXZlbnROYW1lLCBoYW5kbGVyKVxyXG59XHJcblxyXG4vKiBSZW1vdmVzIGhhbmRsZXIgYnkgaWQqL1xyXG5tb2R1bGUuZXhwb3J0cy5vZmYgPSBmdW5jdGlvbihpZCkge1xyXG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlcihpZClcclxufVxyXG5cclxuLyogUmVtb3ZlcyBhbGwgaGFuZGxlcnMgYnkgZXZlbnQgbmFtZSAqL1xyXG5tb2R1bGUuZXhwb3J0cy5yZW1vdmUgPSBmdW5jdGlvbihuYW1lKSB7XHJcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyQnlOYW1lKG5hbWUpXHJcbn1cclxuLypcclxuICB7XHJcbiAgICBcIm5hbWVcIiA6IFwiZm9ybS1zdWJtaXRcIixcclxuICAgIFwiZGF0YVwiIDogXCJ3aGF0ZXZlclwiIDw9PSBvcHRpb25hbFxyXG4gIH1cclxuKi9cclxubW9kdWxlLmV4cG9ydHMuZW1pdCA9IGZ1bmN0aW9uKGV2ZW50KSB7XHJcbiAgbGV0IG5hbWUgPSBldmVudC5uYW1lIHx8ICgoKSA9PiB7IHRocm93IG5ldyBFcnJvcihcIk5vIGV2ZW50IG5hbWVcIikgfSkoKVxyXG4gIGxldCBkYXRhID0gZXZlbnQuZGF0YSB8fCBudWxsXHJcblxyXG4gIHByaXZhdGVTY29wZS5oYW5kbGUobmFtZSwgZGF0YSlcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9ldmVudHMuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0ge1xyXG4gIFwiL1wiIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvaW5kZXguaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvaW5kZXgnKSxcclxuICAgIGNvbnRyb2xsZXJBczogXCJpbmRleFwiXHJcbiAgfSxcclxuICAnLzQwMycgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9lcnJvcjQwMy5odG1sXCJcclxuICB9LFxyXG4gICcvNDA0JyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2Vycm9yNDA0Lmh0bWxcIlxyXG4gIH0sXHJcbiAgJy81MDAnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvZXJyb3I1MDAuaHRtbFwiXHJcbiAgfSxcclxuICAnL2J1bGxldGluRGV0YWlscycgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9idWxsZXRpbkRldGFpbHMuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYnVsbGV0aW5EZXRhaWxzJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwiYmRldGFpbGVkXCJcclxuICB9LFxyXG4gICcvYnVsbGV0aW5BZGQnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9idWxsZXRpbkFkZC5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwiYmFkZFwiXHJcbiAgfSxcclxuICAnL2xvZ2luJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9sb2dpbi5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9sb2dpbicpLFxyXG4gICAgY29udHJvbGxlckFzOiBcImxvZ2luXCJcclxuICB9LFxyXG4gICcvcmVnaXN0ZXInIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL3JlZ2lzdGVyLmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL3JlZ2lzdGVyJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwicmVnaXN0ZXJcIlxyXG4gIH0sXHJcbiAgJy9lZGl0UHJvZmlsZScgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9lZGl0LXByb2ZpbGUuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZScpLFxyXG4gICAgY29udHJvbGxlckFzOiBcInByb2ZpbGVcIlxyXG4gIH0sXHJcbiAgJy9wcm9maWxlJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwicHJvZmlsZVwiXHJcbiAgfSxcclxuICAnL2Zhdm91cml0ZXMnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9mYXZvdXJpdGVzLmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcycpLFxyXG4gICAgY29udHJvbGxlckFzOiBcImZhdm91cml0ZVwiXHJcbiAgfSxcclxuICAnL3NlYXJjaFJlc3VsdHMnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvc2VhcmNoUmVzdWx0cy5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9zZWFyY2hSZXN1bHRzJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwic2VhcmNoUmVzdWx0c1wiXHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvcm91dGVyLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkc2NvcGUpIHtcclxuICBcclxuXHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvaW5kZXguanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgXHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvYnVsbGV0aW5EZXRhaWxzLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG5cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xyXG5cclxuICB0aGlzLmluaXQgPSAoKSA9PiB7XHJcbiAgICB0aGlzLmRiID0gJHNjb3BlLiRwYXJlbnQuZGJcclxuXHJcbiAgICB0aGlzLmVtYWlsID0gXCJcIlxyXG4gICAgdGhpcy5wYXNzd29yZCA9IFwiXCJcclxuXHJcbiAgICB0aGlzLmVtYWlsVmFsaWQgPSB0cnVlXHJcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSB0cnVlXHJcblxyXG4gICAgdGhpcy5sb2dpbkVycm9yID0gXCJcIlxyXG5cclxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcclxuICAgICAgaWYoZS53aGljaCA9PSAxMykgdGhpcy5zZW5kLmNhbGwodGhpcylcclxuICAgIH0uYmluZCh0aGlzKVxyXG5cclxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5kZWxldGVMaXN0bmVycyA9ICgpID0+IHtcclxuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xyXG4gICAgZWUuZW1pdCh7IG5hbWUgOiBcImZvcm0tc3VibWl0XCIgfSlcclxuICAgIC8qXHJcbiAgICAgIC0gR2V0IGRhdGFcclxuICAgICAgLSBWYWxpZGF0ZVxyXG4gICAgICAtIFNob3cgZXJyb3JzXHJcbiAgICAgIC0gb3JcclxuICAgICAgLSBHb3RvIGJkIGFuZCBzZW5kIGRhdGFcclxuICAgICovXHJcbiAgICAvLyB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuXHJcbiAgICBpZiggdGhpcy5lbWFpbFZhbGlkICYmIHRoaXMucGFzc3dvcmRWYWxpZCApIHtcclxuICAgICAgdGhpcy5kYi5sb2dpbih7XHJcbiAgICAgICAgXCJlbWFpbFwiIDogdGhpcy5lbWFpbCxcclxuICAgICAgICBcInBhc3N3b3JkXCI6IHRoaXMucGFzc3dvcmRcclxuICAgICAgfSwgKGVyciwgZGF0YSkgPT4ge1xyXG4gICAgICAgIGlmKGVycilcclxuICAgICAgICAgICRzY29wZS4kcGFyZW50LmRpc3BsYXlFcnJvcihcItCe0YjQuNCx0LrQsCDQsNCy0YLQvtGA0LjQt9Cw0YbQuNC4LCDQv9GA0L7QstC10YDRjNGC0LUg0LLQsNGI0Lgg0LTQsNC90L3Ri9C1XCIpXHJcbiAgICAgICAgZWxzZSB7XHJcbiAgICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuICAgICAgICAgIHRoaXMuZGIuc2F2ZVVzZXJEYXRhKGRhdGEpXHJcbiAgICAgICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnL3Byb2ZpbGUnKVxyXG4gICAgICAgIH1cclxuICAgICAgfSlcclxuICAgIH1cclxuICB9XHJcblxyXG4gIHRoaXMuZW1haWxJc1ZhbGlkID0gZW1haWwgPT4ge1xyXG4gICAgbGV0IGVycm9yID0gXCJcIlxyXG4gICAgaWYoIWVtYWlsLmxlbmd0aCkgIGVycm9yICs9IFwi0J7QsdGP0LfQsNGC0LXQu9GM0L3QvtC1INC/0L7Qu9C1LiBcIlxyXG4gICAgaWYoIS9eKChbXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKFxcLltePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSspKil8KFwiLitcIikpQCgoXFxbWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfV0pfCgoW2EtekEtWlxcLTAtOV0rXFwuKStbYS16QS1aXXsyLH0pKSQvLnRlc3QoZW1haWwpKVxyXG4gICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXHJcbiAgICByZXR1cm4gZXJyb3JcclxuICB9XHJcblxyXG4gIHRoaXMucGFzc3dvcmRJc1ZhbGlkID0gcHdkID0+IHtcclxuICAgIGxldCBlcnJvciA9IFwiXCJcclxuICAgIGlmKCFwd2QubGVuZ3RoKSBlcnJvciArPSBcItCe0LHRj9C30LDRgtC10LvRjNC90L7QtSDQv9C+0LvQtS4gXCJcclxuICAgIGlmKCBwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxyXG4gICAgcmV0dXJuIGVycm9yXHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2xvZ2luLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSwgJHEpIHtcclxuXHJcbiAgdGhpcy5pbml0ID0gKCkgPT4ge1xyXG4gICAgdGhpcy5kYiA9ICRzY29wZS4kcGFyZW50LmRiXHJcblxyXG4gICAgdGhpcy5lbWFpbCA9IFwiXCJcclxuICAgIHRoaXMucGFzc3dvcmQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkMiA9IFwiXCJcclxuXHJcbiAgICB0aGlzLmVtYWlsVmFsaWQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkMlZhbGlkID0gXCJcIlxyXG5cclxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcclxuICAgICAgaWYoZS53aGljaCA9PSAxMykgdGhpcy5zZW5kLmNhbGwodGhpcylcclxuICAgIH0uYmluZCh0aGlzKVxyXG5cclxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5kZWxldGVMaXN0bmVycyA9ICgpID0+IHtcclxuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xyXG4gICAgLypcclxuICAgICAgLSBHZXQgZGF0YVxyXG4gICAgICAtIFZhbGlkYXRlXHJcbiAgICAgIC0gU2hvdyBlcnJvcnNcclxuICAgICAgLSBvclxyXG4gICAgICAtIEdvdG8gYmQgYW5kIHNlbmQgZGF0YVxyXG4gICAgKi9cclxuICAgIGlmKCB0aGlzLmVtYWlsVmFsaWQgJiYgdGhpcy5wYXNzd29yZFZhbGlkICYmIHRoaXMucGFzc3dvcmQyVmFsaWQgKSB7XHJcbiAgICAgIHRoaXMuZGIubG9naW4oe1xyXG4gICAgICAgIFwiZW1haWxcIiA6IHRoaXMuZW1haWwsXHJcbiAgICAgICAgXCJwYXNzd29yZFwiOiB0aGlzLnBhc3N3b3JkXHJcbiAgICAgIH0sIChlcnIsIGRhdGEpID0+IHtcclxuICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuICAgICAgICBpZihlcnIpXHJcbiAgICAgICAgICAkc2NvcGUuJHBhcmVudC5yZWRpcmVjdFRvVXJsKCcvNTAwJylcclxuICAgICAgICBlbHNlIHtcclxuICAgICAgICAgIC8qIFNhdmUgZGF0YSB0byBkYiAqL1xyXG4gICAgICAgICAgJHNjb3BlLnJlZGlyZWN0VG9VcmwoJy9wcm9maWxlJylcclxuICAgICAgICAgIGNvbnNvbGUubG9nKGRhdGEpXHJcbiAgICAgICAgfVxyXG4gICAgICB9KVxyXG4gICAgfVxyXG4gIH1cclxuXHJcbiAgdGhpcy5lbWFpbElzVmFsaWQgPSBmdW5jdGlvbihlbWFpbCkge1xyXG4gICAgcmV0dXJuICRxKCBmdW5jdGlvbihyZXNvbHZlLCByZWplY3QpIHtcclxuICAgICAgbGV0IGVycm9yID0gXCJcIlxyXG4gICAgICBpZighZW1haWwubGVuZ3RoKSAgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXHJcbiAgICAgIGlmKCEvXigoW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKyhcXC5bXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKSopfChcIi4rXCIpKUAoKFxcW1swLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31dKXwoKFthLXpBLVpcXC0wLTldK1xcLikrW2EtekEtWl17Mix9KSkkLy50ZXN0KGVtYWlsKSlcclxuICAgICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXHJcblxyXG4gICAgICB3aW5kb3cuZGIuY2hlY2tFbWFpbChlbWFpbCwgZnVuY3Rpb24oZXJyLCBkYXRhKSB7XHJcbiAgICAgICAgaWYoZXJyKSByZWplY3QoZXJyKVxyXG4gICAgICAgIGVsc2Uge1xyXG4gICAgICAgICAgY29uc29sZS5sb2coZGF0YSlcclxuICAgICAgICAgIGlmKGRhdGEgIT09IFwiZmFsc2VcIilcclxuICAgICAgICAgICAgZXJyb3IgKz0gXCLQotCw0LrQsNGPINC/0L7Rh9GC0LAg0YPQttC1INC40YHQv9C+0LvRjNC30YPQtdGC0YHRjy4gXCJcclxuICAgICAgICAgIHJlc29sdmUoZXJyb3IpXHJcbiAgICAgICAgfVxyXG4gICAgICB9LmJpbmQodGhpcykpXHJcbiAgICB9LmJpbmQodGhpcykpXHJcbiAgfVxyXG5cclxuICB0aGlzLnBhc3N3b3JkSXNWYWxpZCA9IHB3ZCA9PiB7XHJcbiAgICBsZXQgZXJyb3IgPSBcIlwiXHJcbiAgICBpZighcHdkLmxlbmd0aCkgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXHJcbiAgICBpZihwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxyXG4gICAgcmV0dXJuIGVycm9yXHJcbiAgfVxyXG5cclxuICB0aGlzLnBhc3N3b3JkMklzVmFsaWQgPSBwd2QgPT4ge1xyXG4gICAgbGV0IGVycm9yID0gdGhpcy5wYXNzd29yZElzVmFsaWQocHdkKVxyXG4gICAgaWYodGhpcy5wYXNzd29yZCAhPT0gdGhpcy5wYXNzd29yZDIgKSBlcnJvciArPSBcItCf0LDRgNC+0LvQuCDQvdC1INGB0L7QstC/0LDQtNCw0Y7RglwiXHJcbiAgICByZXR1cm4gZXJyb3JcclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvcmVnaXN0ZXIuanNcbiAqKi8iLCJcInVzZSBzdHJpY3RcIjtcclxuXHJcbmNsYXNzIFByb2ZpbGVDb250YWN0e1xyXG4gICAgY29uc3RydWN0b3IoKSB7XHJcbiAgICAgICAgdGhpcy5jb250YWN0RW1haWxzID0gWycnXTtcclxuICAgICAgICB0aGlzLmNvbnRhY3RQaG9uZXMgPSBbJyddO1xyXG4gICAgfVxyXG59XHJcblxyXG5jbGFzcyBwcm9maWxlQ3RybCB7XHJcbiAgICBjb25zdHJ1Y3Rvcigkc2NvcGUpe1xyXG4vLyAgICAgICAgaWYoISRzY29wZS4kcGFyZW50LmRiLnVzZXIpXHJcbi8vICAgICAgICAgICRzY29wZS4kcGFyZW50LnJlZGlyZWN0VG9VcmwoJy80MDMnLCB0cnVlKVxyXG4vLyAgICAgICAgZWxzZVxyXG5cdFx0XHR0aGlzLmNvbnRhY3QgPSBuZXcgUHJvZmlsZUNvbnRhY3QoKTtcclxuICAgIH1cclxuICAgIHVwZGF0ZVByb2ZpbGUoKXtcclxuXHJcbiAgICB9XHJcbiAgICBhZGRDb250YWN0cygkZXZlbnQsIHR5cGUpe1xyXG4gICAgICAgIHZhciBhcnI7XHJcbiAgICAgICAgaWYodHlwZSA9PT0gJ2VtYWlsJykgYXJyID0gdGhpcy5jb250YWN0LmNvbnRhY3RFbWFpbHM7XHJcbiAgICAgICAgZWxzZSBpZih0eXBlID09PSAncGhvbmUnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdFBob25lcztcclxuICAgICAgICBlbHNlIHJldHVybjtcclxuXHJcbiAgICAgICAgaWYoYXJyLmxlbmd0aCA8IDUgJiYgYXJyW2Fyci5sZW5ndGggLSAxXS50cmltKCkpIGFyci5wdXNoKCcnKTtcclxuICAgIH1cclxuXHJcbiAgICBkZWxldGVDb250YWN0cygkZXZlbnQsICRpbmRleCwgdHlwZSl7XHJcbiAgICAgICAgaWYodHlwZSA9PT0gJ2VtYWlsJykgdGhpcy5jb250YWN0LmNvbnRhY3RFbWFpbHMuc3BsaWNlKCRpbmRleCwgMSk7XHJcbiAgICAgICAgZWxzZSBpZih0eXBlID09PSAncGhvbmUnKSB0aGlzLmNvbnRhY3QuY29udGFjdFBob25lcy5zcGxpY2UoJGluZGV4LCAxKTtcclxuICAgICAgICBlbHNlIHJldHVybjtcclxuICAgIH1cclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBwcm9maWxlQ3RybDtcclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qc1xuICoqLyIsIlwidXNlIHN0cmljdFwiO1xyXG5cclxuY2xhc3MgUHJvZmlsZUNvbnRhY3R7XHJcbiAgICBjb25zdHJ1Y3RvcigpIHtcclxuICAgICAgICB0aGlzLmNvbnRhY3RFbWFpbHMgPSBbJyddO1xyXG4gICAgICAgIHRoaXMuY29udGFjdFBob25lcyA9IFsnJ107XHJcbiAgICB9XHJcbn1cclxuXHJcbmNsYXNzIHByb2ZpbGVDdHJsIHtcclxuICAgIGNvbnN0cnVjdG9yKCRzY29wZSl7XHJcbiAgICAgICAgaWYoISRzY29wZS4kcGFyZW50LmRiLnVzZXIpXHJcbiAgICAgICAgICAkc2NvcGUuJHBhcmVudC5yZWRpcmVjdFRvVXJsKCcvNDAzJywgdHJ1ZSlcclxuICAgICAgICBlbHNlIHRoaXMuY29udGFjdCA9IG5ldyBQcm9maWxlQ29udGFjdCgpO1xyXG4gICAgfVxyXG4gICAgdXBkYXRlUHJvZmlsZSgpe1xyXG5cclxuICAgIH1cclxuICAgIGFkZENvbnRhY3RzKCRldmVudCwgdHlwZSl7XHJcbiAgICAgICAgdmFyIGFycjtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG5cclxuICAgICAgICBpZihhcnIubGVuZ3RoIDwgNSAmJiBhcnJbYXJyLmxlbmd0aCAtIDFdLnRyaW0oKSkgYXJyLnB1c2goJycpO1xyXG4gICAgfVxyXG5cclxuICAgIGRlbGV0ZUNvbnRhY3RzKCRldmVudCwgJGluZGV4LCB0eXBlKXtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscy5zcGxpY2UoJGluZGV4LCAxKTtcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG4gICAgfVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHByb2ZpbGVDdHJsO1xyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XHJcblxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcy5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcclxuICBcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9zZWFyY2hSZXN1bHRzLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcclxuXHJcbi8qINCa0L7QvdGC0YDQvtC70LvQtdGAINC00LvRjyDRg9C/0YDQsNCy0LvQtdC90LjRjyAg0L7RgdC90L7QstC90YvQvCDRgdC60LXQu9C10YLQvtC8INC00L7QutGD0LzQtdC90YLQsCAqL1xyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRodHRwLCAkc2NvcGUsICRsb2NhdGlvbiwgJHRpbWVvdXQsICRjb29raWVzLCAkY29va2llU3RvcmUpIHtcclxuICBjb25zb2xlLmxvZygnTWFpbiBjb250cm9sbGVyIGxvYWRlZCcpXHJcblxyXG4gIC8qIFN0YW5kYWxvbmUgbW9kdWxlIGZvciBiZCAqL1xyXG4gICRzY29wZS5kYiA9IHJlcXVpcmUoJy4uL21vZHVsZXMvZGInKVxyXG4gICRzY29wZS5kYi5pbml0KCRodHRwKVxyXG4gIHdpbmRvdy5kYiA9ICRzY29wZS5kYlxyXG5cclxuICAvKiBJbml0aWFsaXplIGRhdGEgKi9cclxuICB0aGlzLmluaXQgPSBmdW5jdGlvbigpIHtcclxuICAgIC8qIHZhcmlhYmxlcyBmb3IgdGVzdGluZyAqL1xyXG4gICAgdGhpcy5oZWxsbz1cImhpXCJcclxuICAgIHRoaXMuYm9vbGVhbiA9IHRydWVcclxuICAgIHRoaXMubGlzdCA9IFsxLDIsM11cclxuXHJcbiAgICBjb25zb2xlLmxvZyhcIk1haW4gY29udHJvbGxlciBpbml0XCIpXHJcblxyXG4gICAgdGhpcy5zb3J0aW5nQ2F0ZWdvcmllcyA9IChyZXF1aXJlKCcuLi9kYXRhL3NvcnRpbmcnKSkuaXRlbXNcclxuICAgIHRoaXMuY3VycmVudENhdGVnb3J5ID0gXCJOb25lXCJcclxuICAgIHRoaXMuc29ydGluZ0lkID0gMFxyXG5cclxuICAgIGlmKHRoaXMuc29ydGluZ0NhdGVnb3JpZXMubGVuZ3RoKSB7XHJcbiAgICAgIGxldCB0aXRsZSA9IHRoaXMuc29ydGluZ0NhdGVnb3JpZXNbdGhpcy5zb3J0aW5nSWRdLnRpdGxlXHJcbiAgICAgIGxldCBhcnIgPSB0aXRsZS5zcGxpdChcIlwiKVxyXG4gICAgICB0aGlzLmFycm93ID0gYXJyLnBvcCgpXHJcbiAgICAgIGFyci5wb3AoKVxyXG5cclxuICAgICAgdGhpcy5jdXJyZW50Q2F0ZWdvcnkgPSBhcnIuam9pbihcIlwiKVxyXG4gICAgfVxyXG4gICAgZWxzZSBjb25zb2xlLmVycm9yKG5ldyBFcnJvcihcIk5vIHNvcnRpbmcgb3B0aW9ucyBmb3VuZFwiKSlcclxuXHJcbiAgICB0aGlzLnNob3dpbmdDYXRlZ29yaWVzID0gZmFsc2VcclxuICAgIHRoaXMuc2V0dGluZ0NhdCA9IHRydWVcclxuICB9XHJcblxyXG4gIHRoaXMuc2hvd0NhdGVnb3JpZXMgPSAoKSA9PiB7XHJcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXHJcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXHJcbiAgICAkdGltZW91dCggKCkgPT4ge1xyXG4gICAgICB0aGlzLnNldHRpbmdDYXQgPSBmYWxzZVxyXG4gICAgfSwgMjUwKVxyXG5cclxuICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSB0cnVlXHJcbiAgfVxyXG5cclxuICAvKiBTb3J0aW5nIGluIGhlYWRlciAqL1xyXG4gIHRoaXMuc2V0Q2F0ZWdvcnkgPSBpZCA9PiB7XHJcbiAgICB0aGlzLnNldHRpbmdDYXQgPSBmYWxzZVxyXG5cclxuICAgIGxldCByZXMgPSB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzLmZpbHRlcihlbCA9PiBlbC5pZCA9PT0gaWQgfCAwKVswXVxyXG4gICAgdGhpcy5zb3J0aW5nSWQgPSBpZFxyXG5cclxuICAgIGlmKHJlcykge1xyXG4gICAgICBsZXQgYXJyID0gcmVzLnRpdGxlLnNwbGl0KFwiXCIpXHJcbiAgICAgIHRoaXMuYXJyb3cgPSBhcnIucG9wKClcclxuICAgICAgYXJyLnBvcCgpXHJcblxyXG4gICAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IGFyci5qb2luKFwiXCIpXHJcbiAgICB9XHJcblxyXG4gIH1cclxuXHJcbiAgdGhpcy5sb2dvdXQgPSBmdW5jdGlvbigpIHtcclxuICAgICAgZGIudXNlckxvZ291dCgpXHJcbiAgICAgICRzY29wZS5yZWRpcmVjdFRvVXJsKCcvJylcclxuICB9XHJcblxyXG4gIC8qIFdhdGNoIGFsbCBjbGljayBvbiB0aGUgYm9keSAqL1xyXG4gIHRoaXMuY2xpY2sgPSAoKSA9PiB7XHJcbiAgICBpZih0aGlzLnNob3dpbmdDYXRlZ29yaWVzICYmICF0aGlzLnNldHRpbmdDYXQpXHJcbiAgICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSBmYWxzZVxyXG4gIH1cclxuXHJcblxyXG4gIC8qIENvcnJlY3QgcmVkaXJlY3QgdG8gdXJsIHRocm91Z2ggYXBwIHJvdXRlciovXHJcbiAgJHNjb3BlLnJlZGlyZWN0VG9VcmwgPSAodXJsLCBpbW1lZGlhdGUpID0+IHtcclxuICAgIGlmKGltbWVkaWF0ZSlcclxuICAgICAgJGxvY2F0aW9uLnBhdGgodXJsKVxyXG4gICAgZWxzZVxyXG4gICAgICAkdGltZW91dCgoKSA9PiB7XHJcbiAgICAgICAgJGxvY2F0aW9uLnBhdGgodXJsKVxyXG4gICAgICB9LCAyNTApXHJcbiAgfVxyXG5cclxuICAvKiBVc2UgdGhpcyBtZXRob2QgZm9yIGdsb2JhbCBwdXJwb3NlIGVycm9ycyAqL1xyXG4gICRzY29wZS5kaXNwbGF5RXJyb3IgPSB0ZXh0ID0+IHtcclxuICAgIGFsZXJ0KHRleHQpXHJcbiAgICBjb25zb2xlLmVycm9yKG5ldyBFcnJvcihcInRleHRcIikpXHJcbiAgfVxyXG5cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9tYWluLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5sZXQgdXRpbHMgPSByZXF1aXJlKCcuL3V0aWxzJyksXHJcbiAgICBjb25maWcgPSByZXF1aXJlKCcuLi9kYXRhL2NvbmZpZycpXHJcblxyXG5sZXQgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxyXG5cclxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGZ1bmN0aW9uKCRodHRwKSB7XHJcbiAgLyogaW5pdCBkYXRhIGZyb20gZGF0YWJhc2UgaGVyZSAqL1xyXG4gIGN0eC5zZXREZWZhdWx0cygpXHJcbiAgdGhpcy50cmFuc3BvcnQgPSAkaHR0cFxyXG4gIGNvbnNvbGUubG9nKHRoaXMudHJhbnNwb3J0KVxyXG4gIC8vIGN0eC5jaGVja1VzZXJJc0xvZ2dlZChmdW5jdGlvbihlcnIsIGRhdGEpIHtcclxuICAvLyAgIGlmKGVycikgY29uc29sZS5lcnJvcihlcnIpXHJcbiAgLy8gICBlbHNlIHtcclxuICAvLyAgICAgaWYoZGF0YSkgY3R4LnNhdmVVc2VyRGF0YShkYXRhKVxyXG4gIC8vICAgICBlbHNlIGNvbnNvbGUubG9nKFwiVXNlciBpcyBub3QgbG9nZ2VkIGluXCIpXHJcbiAgLy8gICB9XHJcbiAgLy8gfS5iaW5kKHRoaXMpKVxyXG5cclxuICBjb25zb2xlLmxvZyhcIkRhdGFiYXNlIGluaXRpYWxpemVkXCIpXHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzLnNldERlZmF1bHRzID0gZnVuY3Rpb24oKSB7XHJcbiAgY29uc29sZS5sb2coXCJEYXRhYmFzZSA6OiBkZWZhdWx0cyBzZXRcIilcclxuXHJcbiAgY3R4LmZhdm91cml0ZXMgPSBudWxsXHJcbiAgY3R4Lm1haWxib3ggPSBudWxsXHJcbiAgY3R4LnVzZXIgPSBudWxsXHJcbiAgY3R4Lm5vdGlmaWNhdGlvbnMgPSB7IGhlbGxvIDogXCJwcmV2ZWRcIiB9XHJcbn1cclxuXHJcblxyXG5tb2R1bGUuZXhwb3J0cy5jaGVja0VtYWlsID0gZnVuY3Rpb24oZW1haWwsIGNiKSB7XHJcbiAgdXRpbHMucmVxdWVzdCh7XHJcbiAgICBcIm1ldGhvZFwiIDogY29uZmlnLnJvdXRlcy5jaGVja0VtYWlsLm1ldGhvZCxcclxuICAgIFwidXJsXCIgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmNoZWNrRW1haWwudXJsLFxyXG4gICAgXCJkYXRhXCIgOiBlbWFpbCxcclxuICAgIFwiaGVhZGVyc1wiIDoge1xyXG4gICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJ0ZXh0L3BsYWluXCJcclxuICAgIH1cclxuICB9KS50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSksIGVyciA9PiBjYihlcnIpKVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cy5sb2dpbiA9IGZ1bmN0aW9uKCBkYXRhLCBjYiApIHtcclxuICAvLyB1dGlscy5yZXF1ZXN0KHtcclxuICAvLyAgIFwibWV0aG9kXCIgOiBjb25maWcucm91dGVzLmxvZ2luLm1ldGhvZCxcclxuICAvLyAgIFwidXJsXCIgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmxvZ2luLnVybCxcclxuICAvLyAgIFwiZGF0YVwiIDogZGF0YSxcclxuICAvLyAgIFwiaGVhZGVyc1wiIDoge1xyXG4gIC8vICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXHJcbiAgLy8gICAgIFwid2l0aENyZWRlbnRpYWxzXCIgOiBcInRydWVcIlxyXG4gIC8vICAgfVxyXG4gIC8vIH0pLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhKSwgZXJyID0+IGNiKGVycikpXHJcbiAgY3R4LnRyYW5zcG9ydCh7XHJcbiAgICBtZXRob2QgOiBjb25maWcucm91dGVzLmxvZ2luLm1ldGhvZCxcclxuICAgIHVybCA6IGNvbmZpZy5hcGkuYXV0aCArIGNvbmZpZy5yb3V0ZXMubG9naW4udXJsLFxyXG4gICAgZGF0YSA6IGRhdGEsXHJcbiAgICBoZWFkZXJzIDoge1xyXG4gICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXHJcbiAgICB9LFxyXG4gICAgd2l0aENyZWRlbnRpYWxzIDogdHJ1ZVxyXG4gIH0pXHJcbiAgLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhKSlcclxuICAuY2F0Y2goY2IpXHJcbn1cclxuXHJcbi8qIFRoaXMgbWV0aG9kIGRvZXMgc2F2ZXMgdXNlciBkYXRhIGluIHRoaXMgbW9kdWxlIG9ubHksIG5vIGJhY2tlbmQgY29tbXVuaWNhdGlvbiAqL1xyXG5tb2R1bGUuZXhwb3J0cy5zYXZlVXNlckRhdGEgPSBmdW5jdGlvbihkYXRhKSB7XHJcbiAgZGF0YSA9IGRhdGEubGVuZ3RoID8gSlNPTi5wYXJzZShkYXRhKSA6IFwiXCJcclxuICB0aGlzLnVzZXIgPSB7fVxyXG4gIC8qIFRPRE86INGA0LDRgdC/0LDRgNGB0LjRgtGMINC00LDQvdC90YvQtSDQsiDQvtGB0LzRi9GB0LvQtdC90L3Ri9C1INC/0LXRgNC10LzQtdC90L3Ri9C1ICovXHJcblxyXG4gIGNvbnNvbGUubG9nKFwiRGF0YWJhc2U6OiBVc2VyIGRhdGEgc2F2ZWQgc3VjY2Vzc2Z1bGx5KCDRiNGD0YLQutCwICkgXCIpXHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzLmNoZWNrVXNlcklzTG9nZ2VkID0gZnVuY3Rpb24oIGNiICkge1xyXG4gIHV0aWxzLnJlcXVlc3Qoe1xyXG4gICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMuY2hlY2tMb2dnZWQubWV0aG9kLFxyXG4gICAgXCJ1cmxcIiA6IGNvbmZpZy5hcGkudXJsICsgY29uZmlnLnJvdXRlcy5jaGVja0xvZ2dlZC51cmxcclxuICB9KS50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSksIGVyciA9PiBjYihlcnIpKVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cy51c2VyTG9nb3V0ID0gZnVuY3Rpb24oKSB7XHJcbiAgY3R4LnNldERlZmF1bHRzKClcclxuXHJcbiAgdXRpbHMucmVxdWVzdCh7XHJcbiAgICBcIm1ldGhvZFwiIDogY29uZmlnLnJvdXRlcy5sb2dvdXQubWV0aG9kLFxyXG4gICAgXCJ1cmxcIiA6IGNvbmZpZy5hcGkuYXV0aCArIGNvbmZpZy5yb3V0ZXMubG9nb3V0LnVybFxyXG4gIH0pLnRoZW4oKCk9Pnt9LCAoKT0+e30pXHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9kYi5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG4vKlxyXG4gIEV4cGVjdCBvcHRpb25zIG9iamVjdCBsaWtlIHRoaXM6XHJcbiAge1xyXG4gICAgXCJtZXRob2RcIiA6IFwiUE9TVFwiLFxyXG4gICAgXCJ1cmxcIiA6IFwiaHR0cDovL3NvbWV1cmwuY29tL1wiLFxyXG4gICAgXCJkYXRhXCIgOiBcImRhdGFcIixcclxuICAgIFwiaGVhZGVyc1wiIDoge1xyXG4gICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXHJcbiAgICAgIFwiQ29udGVudC1MZW5ndGhcIiA6IFwiMTAyM1wiXHJcbiAgICB9XHJcbiAgfVxyXG5cclxuICBERUZBVUxUUzpcclxuICBNZXRob2QgLSBkZWZhdWx0IGlzIEdFVCxcclxuICBVUkwgLSByZXF1aXJlZCxcclxuICBkYXRhIC0gb3B0aW9uYWwsXHJcbiAgaGVhZGVycyAtIG9wdGlvbmFsXHJcbiovXHJcblxyXG5tb2R1bGUuZXhwb3J0cy5yZXF1ZXN0ID0gZnVuY3Rpb24ob3B0aW9ucykge1xyXG4gIHJldHVybiBuZXcgUHJvbWlzZSggKHJlc29sdmUsIHJlamVjdCkgPT4ge1xyXG4gICAgLyogU2V0dGluZyBkZWZhdWx0cyAqL1xyXG4gICAgbGV0IHsgbWV0aG9kPVwiR0VUXCIsIHVybCwgZGF0YSwgaGVhZGVycyB9ID0gb3B0aW9uc1xyXG5cclxuICAgIC8qIFNvbWUgdmFsaWRhdGlvbiAqL1xyXG4gICAgaWYoIXVybClcclxuICAgICAgcmV0dXJuIGNvbnNvbGUuZXJyb3IoXCJVcmwgbm90IHNwZWNpZmllZFwiKVxyXG5cclxuICAgIGlmKCAobWV0aG9kID09IFwiUE9TVFwiIHx8IG1ldGhvZCA9PSBcIlBVVFwiKSAmJiAhZGF0YSlcclxuICAgICAgcmV0dXJuIGNvbnNvbGUuZXJyb3IoXCJOb3RoaW5nIHRvIHNlbmQgaGVyZSA9KVwiKVxyXG5cclxuICAgIC8qIFN0YXJ0IGNvbnN0cnVjdGluZyByZXF1ZXN0ICovXHJcbiAgICB2YXIgeGhyID0gbmV3IFhNTEh0dHBSZXF1ZXN0KClcclxuICAgIHhoci5vcGVuKG1ldGhvZCwgdXJsLCB0cnVlKVxyXG5cclxuICAgIGlmKGhlYWRlcnMpXHJcbiAgICAgIGZvciggdmFyIHByb3AgaW4gaGVhZGVycylcclxuICAgICAgICB4aHIuc2V0UmVxdWVzdEhlYWRlcihwcm9wLCBoZWFkZXJzW3Byb3BdKVxyXG5cclxuICAgIGlmKGRhdGEgJiYgaGVhZGVyc1snQ29udGVudC1UeXBlJ10gIT09IFwidGV4dC9wbGFpblwiKVxyXG4gICAgICB4aHIuc2VuZChKU09OLnN0cmluZ2lmeShkYXRhKSlcclxuICAgIGVsc2UgaWYoZGF0YSlcclxuICAgICAgeGhyLnNlbmQoZGF0YSlcclxuICAgIGVsc2VcclxuICAgICAgeGhyLnNlbmQoKVxyXG5cclxuXHJcblxyXG4gICAgeGhyLm9ucmVhZHlzdGF0ZWNoYW5nZSA9IGZ1bmN0aW9uKCkge1xyXG4gICAgICBpZiAodGhpcy5yZWFkeVN0YXRlICE9IDQpXHJcbiAgICAgICAgcmV0dXJuXHJcblxyXG4gICAgICBpZiAodGhpcy5zdGF0dXMgIT0gMjAwKVxyXG4gICAgICAgIHJldHVybiByZWplY3QoJ0Vycm9yOiAnICsgKHRoaXMuc3RhdHVzID8gYCgke3RoaXMuc3RhdHVzfSkgJHt0aGlzLnN0YXR1c1RleHR9YDogJ3JlcXVlc3QgZmFpbCcpKVxyXG4gICAgICBlbHNlXHJcbiAgICAgICAgcmV0dXJuIHJlc29sdmUodGhpcy5yZXNwb25zZVRleHQpXHJcblxyXG4gICAgfVxyXG5cclxuICB9KVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvdXRpbHMuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IHtcblx0XCJhcGlcIjoge1xuXHRcdFwidXJsXCI6IFwiaHR0cDovLzkzLjczLjEwOS4zODo4MDg0L1wiLFxuXHRcdFwiYXV0aFwiOiBcImh0dHA6Ly85My43My4xMDkuMzg6ODA4My9cIlxuXHR9LFxuXHRcInJvdXRlc1wiOiB7XG5cdFx0XCJnZXRCdWxsZXRpbnNcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImFwaS9yZXN0L29mZmVyc1NlcnZpY2Uvb2ZmZXIvcmVhZC9hbGxcIixcblx0XHRcdFwiYWNjZXB0XCI6IHtcblx0XHRcdFx0XCJza2lwXCI6IDAsXG5cdFx0XHRcdFwibGltaXRcIjogMjBcblx0XHRcdH1cblx0XHR9LFxuXHRcdFwibG9naW5cIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImxvZ2luXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwiZW1haWxcIjogXCJzc3MyQGdtYWlsLmNvbVwiLFxuXHRcdFx0XHRcInBhc3N3b3JkXCI6IFwiMTIzNDU2XCJcblx0XHRcdH1cblx0XHR9LFxuXHRcdFwibG9nb3V0XCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJsb2dvdXRcIlxuXHRcdH0sXG5cdFx0XCJyZWdpc3RlclwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIlBPU1RcIixcblx0XHRcdFwidXJsXCI6IFwicmVnaXN0ZXJcIixcblx0XHRcdFwiYWNjZXB0XCI6IHtcblx0XHRcdFx0XCJlbWFpbFwiOiBcInNzczJAZ21haWwuY29tXCIsXG5cdFx0XHRcdFwicGFzc3dvcmRcIjogXCIxMjM0NTZcIlxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJjaGVja0VtYWlsXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJsb2dpbi9jaGVja0VtYWlsXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwiZW1haWxcIjogXCJzc3MyQGdtYWlsLmNvbVwiXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImNoZWNrTG9nZ2VkXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiR0VUXCIsXG5cdFx0XHRcInVybFwiOiBcImFwaS9yZXN0L3Byb2ZpbGVzU2VydmljZS9wcm9maWxlL3JlYWQvbG9nZ2VkSW5Qcm9maWxlXCJcblx0XHR9XG5cdH1cbn07XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2RhdGEvY29uZmlnLmpzb25cbiAqKiBtb2R1bGUgaWQgPSA3NFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSB7XG5cdFwiaXRlbXNcIjogW1xuXHRcdHtcblx0XHRcdFwiaWRcIjogMSxcblx0XHRcdFwidGl0bGVcIjogXCLRhtC10L3QsCDilr5cIixcblx0XHRcdFwicmVxdWVzdENyaXRlcmlhXCI6IFwiXCJcblx0XHR9LFxuXHRcdHtcblx0XHRcdFwiaWRcIjogMixcblx0XHRcdFwidGl0bGVcIjogXCLRhtC10L3QsCDilrRcIixcblx0XHRcdFwicmVxdWVzdENyaXRlcmlhXCI6IFwiXCJcblx0XHR9LFxuXHRcdHtcblx0XHRcdFwiaWRcIjogMyxcblx0XHRcdFwidGl0bGVcIjogXCLQtNCw0YLQsCAg4pa+XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDQsXG5cdFx0XHRcInRpdGxlXCI6IFwi0LTQsNGC0LAg4pa0XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDUsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YDQtdC50YLQuNC90LMg4pa+XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDYsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YDQtdC50YLQuNC90LMg4pa0XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fVxuXHRdXG59O1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9kYXRhL3NvcnRpbmcuanNvblxuICoqIG1vZHVsZSBpZCA9IDc1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iXSwibWFwcGluZ3MiOiI7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUN0Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTs7QUFFQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUlBO0FBREE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7O0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7Ozs7OztBQ25EQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7Ozs7O0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2hEQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDclBBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FDcEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNQQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNwQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ1BBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDUEE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBOztBQUVBOzs7Ozs7O0FDL0JBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ0pBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSkE7QUFDQTtBQU1BO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUlBOzs7Ozs7QUNuQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBckJBO0FBdUJBOzs7Ozs7QUMxQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBakNBO0FBbUNBOzs7Ozs7QUN0Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBTkE7QUFRQTtBQUNBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBSUE7QUFDQTtBQUFBO0FBQUE7QUFFQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQXBCQTtBQXVCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQWhHQTtBQWtHQTs7Ozs7O0FDMUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFRQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFsREE7QUFvREE7Ozs7OztBQ3ZEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTs7Ozs7OztBQU9BO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDMUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBREE7QUFHQTtBQUNBO0FBREE7QUFHQTtBQUNBO0FBREE7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQWxEQTs7Ozs7Ozs7QUNGQTs7Ozs7O0FDQUE7QUFDQTtBQUNBOzs7Ozs7QUNGQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7Ozs7QUFVQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNsRUE7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7O0FBUUE7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBQ0E7O0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2pGQTtBQUNBOzs7OztBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUFBO0FBQ0E7Ozs7QUFHQTtBQUNBO0FBQ0E7OztBQUFBOzs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7OztBQUVBO0FBQ0E7QUFHQTs7Ozs7O0FBR0E7Ozs7OztBQ25DQTtBQUNBOzs7OztBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUFBO0FBQ0E7QUFBQTtBQUdBO0FBQ0E7OztBQUFBOzs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7OztBQUVBO0FBQ0E7QUFHQTs7Ozs7O0FBR0E7Ozs7OztBQ2xDQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTs7O0FBRUE7QUFBQTtBQUNBO0FBQUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUVBO0FBQ0E7O0FBR0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7Ozs7OztBQzdGQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTs7Ozs7Ozs7O0FBU0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUpBO0FBT0E7QUFBQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7Ozs7QUFVQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUdBO0FBUEE7QUFTQTtBQUFBO0FBRUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFHQTtBQUFBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBOzs7Ozs7QUMxRkE7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBb0JBO0FBQ0E7O0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUNBOzs7QUFJQTtBQUNBO0FBRUE7QUFDQTs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBREE7QUFDQTtBQVdBO0FBQ0E7QUFDQTtBQUVBO0FBS0E7QUFFQTs7Ozs7OztBQzVEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUM5Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7OzsiLCJzb3VyY2VSb290IjoiIn0=