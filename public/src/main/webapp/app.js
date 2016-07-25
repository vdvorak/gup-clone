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
	    router = __webpack_require__(62);

	var app = angular.module('gup', ['ngRoute', 'ngCookies']);

	// App config
	app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
	  for (var key in router) {
	    $routeProvider.when(key, router[key]);
	  }$routeProvider.otherwise({
	    redirectTo: '/404'
	  });

	  $locationProvider.html5Mode({
	    enabled: true,
	    requireBase: false
	  });
	}]).controller('mainCtrl', __webpack_require__(74));

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
	exports.push([module.id, "body {\n  background-color: #ECECEC; }\n\n.calendar, .addCalendar {\n  position: relative;\n  background: url(" + __webpack_require__(4) + ") no-repeat center left;\n  padding-left: 45px;\n  box-sizing: border-box;\n  margin-bottom: 40px;\n  box-sizing: border-box; }\n  .calendar > .defaultValue, .addCalendar > .defaultValue {\n    border-bottom: 1px solid grey;\n    background: url(" + __webpack_require__(5) + ") no-repeat center right 5px;\n    cursor: pointer;\n    padding-right: 15px;\n    box-sizing: border-box; }\n    .calendar > .defaultValue > p, .addCalendar > .defaultValue > p {\n      text-align: left;\n      color: #262626;\n      font: 400 14px / 20px Roboto; }\n  .calendar > .listValue, .addCalendar > .listValue {\n    display: none; }\n\nheader {\n  height: 70px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #1875D0; }\n\n.btn-blue, .btn-grey {\n  position: relative;\n  display: inline-block;\n  overflow: hidden;\n  border-radius: 5px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  cursor: pointer;\n  text-align: center; }\n\n.btn-grey {\n  background-color: #D8D8D8;\n  color: #868686;\n  font: 400 14px / 21px Roboto; }\n\n.container {\n  width: 1280px;\n  position: relative;\n  margin: 0 auto; }\n\n.clearfix:before, .clearfix:after {\n  content: \" \";\n  display: table; }\n\n.clearfix:after {\n  clear: both; }\n\n.ink {\n  display: block;\n  position: absolute;\n  background: rgba(0, 0, 0, 0.15);\n  border-radius: 100%;\n  -webkit-transform: scale(0);\n          transform: scale(0); }\n\n.ink.animate {\n  -webkit-animation: ripple .5s ease-in;\n          animation: ripple .5s ease-in; }\n\n@-webkit-keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n@keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n.headLeft {\n  padding-top: 5px;\n  display: inline-block;\n  width: calc(100% - 490px);\n  height: 70px;\n  box-sizing: border-box; }\n  .headLeft > .logo {\n    cursor: pointer;\n    border-radius: 50%;\n    float: left;\n    height: 60px;\n    width: 60px;\n    background: url(" + __webpack_require__(6) + ") no-repeat;\n    margin-left: 15px; }\n  .headLeft > .inputForm {\n    margin-top: 21px;\n    margin-left: 20px;\n    float: left;\n    border-color: #FDFDFD;\n    box-sizing: border-box;\n    height: auto;\n    width: 200px;\n    border-bottom: 1px solid #FDFDFD; }\n    .headLeft > .inputForm > label, .headLeft > .inputForm input {\n      color: #ffffff;\n      font: 400 14px Roboto; }\n  .headLeft > .selectBox {\n    float: left;\n    margin-top: 21px;\n    margin-left: 20px; }\n    .headLeft > .selectBox > .defaultValue {\n      background: none;\n      padding: 0 5px; }\n  .headLeft > .add {\n    cursor: pointer;\n    float: right;\n    height: 35px;\n    width: 200px;\n    margin-right: 20px;\n    margin-top: 10px;\n    background: url(" + __webpack_require__(7) + ") no-repeat center right 10px;\n    padding-left: 10px;\n    text-align: left;\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    border: 1px solid white;\n    -webkit-transition: all .15s;\n    transition: all .15s; }\n    .headLeft > .add > p {\n      color: #ffffff;\n      font: 400 14px / 35px Roboto; }\n\n.headRight {\n  float: right;\n  width: 490px;\n  height: 70px;\n  border-left: 1px solid grey;\n  box-sizing: border-box;\n  padding-top: 22px; }\n  .headRight > .mail {\n    height: 26px;\n    width: 33px;\n    cursor: pointer;\n    position: relative;\n    float: left;\n    background: url(" + __webpack_require__(8) + ") no-repeat center center;\n    margin-left: 20px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .mail > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -3px;\n      left: 32px;\n      cursor: default; }\n    .headRight > .mail:hover {\n      background: url(" + __webpack_require__(9) + ") no-repeat center center; }\n  .headRight > .bell {\n    height: 24px;\n    width: 23px;\n    cursor: pointer;\n    position: relative;\n    display: inline-block;\n    float: left;\n    margin-left: 30px;\n    background: url(" + __webpack_require__(10) + ") no-repeat center center;\n    border-radius: 15px 0 15px 10px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .bell > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -7px;\n      left: 22px;\n      cursor: default; }\n    .headRight > .bell:hover {\n      background: url(" + __webpack_require__(11) + ") no-repeat center center; }\n  .headRight > .services {\n    height: 27px;\n    width: 28px;\n    cursor: pointer;\n    float: right;\n    margin-right: 30px;\n    background: url(" + __webpack_require__(12) + ") no-repeat center center;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .services:hover {\n      background: url(" + __webpack_require__(13) + ") no-repeat center center; }\n  .headRight > .userName {\n    float: right;\n    margin-right: 25px;\n    background: url(" + __webpack_require__(14) + ") no-repeat center left;\n    padding-left: 30px;\n    max-width: 270px;\n    position: relative; }\n    .headRight > .userName > p {\n      color: #ffffff;\n      font: 400 14px / 27px Roboto;\n      cursor: pointer;\n      white-space: nowrap;\n      overflow: hidden;\n      text-overflow: ellipsis; }\n    .headRight > .userName > div {\n      background-color: #FDFDFD;\n      box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n      position: absolute;\n      top: 0;\n      width: 100%;\n      z-index: 1; }\n      .headRight > .userName > div > p {\n        cursor: pointer;\n        padding: 0 15px;\n        height: 48px;\n        font: 400 16px / 50px Roboto;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        box-sizing: border-box;\n        transition: all .25s; }\n        .headRight > .userName > div > p:hover {\n          background-color: #eeeeee; }\n  .headRight > .auth {\n    color: white;\n    float: right;\n    margin-right: 20px;\n    font: 400 14px / 26px Roboto; }\n    .headRight > .auth span {\n      cursor: pointer;\n      margin: 0 10px; }\n\n.inputSearch {\n  position: relative;\n  display: inline-block;\n  margin-top: 10px;\n  border-bottom: 1px solid white; }\n  .inputSearch > input {\n    border: none;\n    padding: 2px 0;\n    background-color: transparent;\n    outline: none;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto; }\n  .inputSearch > label {\n    position: absolute;\n    top: 0;\n    left: 0;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto;\n    cursor: text;\n    -webkit-transition: .5s;\n    transition: .5s; }\n\n.selectBox {\n  position: relative;\n  display: inline-block;\n  cursor: pointer; }\n  .selectBox > .defaultValue {\n    color: #ffffff;\n    font: 400 14px / 19px Roboto;\n    overflow: hidden;\n    border-bottom: 1px solid white;\n    background: url(" + __webpack_require__(5) + ") no-repeat center right 5px;\n    padding-right: 20px;\n    box-sizing: border-box; }\n    .selectBox > .defaultValue span {\n      font-size: 16px; }\n  .selectBox > .listOfValues {\n    position: absolute;\n    top: 0;\n    left: 0;\n    background-color: white;\n    z-index: 1;\n    box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n    -webkit-animation: animatetop .25s;\n            animation: animatetop .25s; }\n    .selectBox > .listOfValues > div {\n      cursor: pointer;\n      padding: 0 15px;\n      height: 50px;\n      width: 120px;\n      font: 400 16px / 50px Roboto;\n      -webkit-transition: all .25s;\n      transition: all .25s; }\n      .selectBox > .listOfValues > div:hover {\n        background-color: #eeeeee; }\n\n@-webkit-keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\n@keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\naside.bulletinDetails {\n  float: right;\n  margin-top: 5px; }\n\nsection.openAdvertert {\n  background-color: white;\n  float: right;\n  margin-right: 10px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 715px;\n  padding: 25px 100px 45px;\n  box-sizing: border-box;\n  margin-top: 5px; }\n  section.openAdvertert > h3 {\n    color: #202020;\n    font: 400 22px / 26px Roboto;\n    float: left; }\n  section.openAdvertert > .price {\n    color: #1f1f1f;\n    font: 400 18px / 26px Roboto;\n    float: right; }\n  section.openAdvertert > .checkBox {\n    float: right;\n    margin-right: 15px;\n    margin-top: -1px; }\n  section.openAdvertert > .breadCrumbs {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: left;\n    margin-top: 10px;\n    cursor: default; }\n  section.openAdvertert > .id {\n    color: rgba(32, 32, 32, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: right;\n    cursor: default;\n    margin-top: 10px; }\n  section.openAdvertert > .slider {\n    height: 120px;\n    position: relative;\n    margin-top: 10px;\n    float: left;\n    width: 100%;\n    margin-bottom: 10px; }\n    section.openAdvertert > .slider > div {\n      float: left;\n      margin-left: 10px;\n      height: 100%;\n      width: 165px; }\n      section.openAdvertert > .slider > div:nth-of-type(1) {\n        margin: 0; }\n      section.openAdvertert > .slider > div > img {\n        -o-object-fit: contain;\n           object-fit: contain;\n        width: 100%;\n        background-color: #F4F4F4;\n        height: 100%;\n        cursor: pointer; }\n    section.openAdvertert > .slider > .next {\n      position: absolute;\n      background: url(" + __webpack_require__(15) + ") no-repeat left center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      right: -25px; }\n    section.openAdvertert > .slider > .prev {\n      position: absolute;\n      background: url(" + __webpack_require__(16) + ") no-repeat right center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      left: -25px; }\n  section.openAdvertert > div.buyProduct > #mapForOneAdvertert {\n    float: left;\n    height: 230px;\n    width: 225px;\n    margin-bottom: 25px; }\n  section.openAdvertert > div.buyProduct > .descriptionAd {\n    color: #0c0c1e;\n    font: 400 12px / 18px Roboto;\n    margin-top: 15px;\n    margin-bottom: 20px; }\n  section.openAdvertert > div.buyProduct > .goToMap {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: left; }\n  section.openAdvertert > div.buyProduct > .allComments {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right;\n    margin-right: 50px; }\n  section.openAdvertert > div.buyProduct > .writeAReview {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right; }\n  section.openAdvertert > div.buyProduct > .nameUser {\n    color: #1976d2;\n    font: 400 14px / 18px Roboto;\n    float: left;\n    margin-left: 20px;\n    cursor: default;\n    margin-bottom: 35px; }\n  section.openAdvertert > div.buyProduct > .onOrOffLineUser {\n    float: left; }\n  section.openAdvertert > div.buyProduct .btn-grey {\n    float: right;\n    height: 35px;\n    width: 140px;\n    margin-right: 25px;\n    line-height: 35px; }\n  section.openAdvertert > div.buyProduct .btn-blue {\n    float: right;\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n  section.openAdvertert > .rentProduct {\n    margin-top: 45px; }\n    section.openAdvertert > .rentProduct > .rentCalendar {\n      margin-bottom: 45px; }\n      section.openAdvertert > .rentProduct > .rentCalendar > p {\n        color: #263238;\n        font: 400 16px / 21px Roboto;\n        float: left;\n        cursor: default; }\n        section.openAdvertert > .rentProduct > .rentCalendar > p:nth-of-type(2) {\n          margin-left: 55px; }\n      section.openAdvertert > .rentProduct > .rentCalendar > .calendar {\n        float: left;\n        margin-left: 45px;\n        width: 165px;\n        margin-bottom: 0; }\n    section.openAdvertert > .rentProduct > .inputForm {\n      color: #9a9a9a;\n      margin-bottom: 40px; }\n    section.openAdvertert > .rentProduct > .btn-blue {\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 35px;\n      width: 180px;\n      line-height: 35px;\n      float: right; }\n\n.wrapForDiv {\n  width: 265px;\n  float: right;\n  overflow: hidden;\n  margin-bottom: 25px;\n  border: 1px solid #E9E9E9;\n  box-sizing: border-box; }\n  .wrapForDiv > ul.tab {\n    list-style-type: none;\n    height: 50px;\n    background-color: white;\n    border-bottom: 1px solid #E9E9E9;\n    box-sizing: border-box; }\n    .wrapForDiv > ul.tab > li {\n      float: left; }\n      .wrapForDiv > ul.tab > li > a {\n        color: #939393;\n        font: 400 14px / 50px Roboto;\n        display: block;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        text-align: center;\n        position: relative; }\n        .wrapForDiv > ul.tab > li > a:after {\n          content: '';\n          display: block;\n          position: absolute;\n          bottom: 0;\n          width: 0;\n          height: 2px;\n          background-color: #FD5151;\n          -webkit-transition: all .25s;\n          transition: all .25s; }\n        .wrapForDiv > ul.tab > li > a:focus, .wrapForDiv > ul.tab > li > a.active {\n          color: #7eafe1; }\n          .wrapForDiv > ul.tab > li > a:focus:after, .wrapForDiv > ul.tab > li > a.active:after {\n            width: 100%; }\n      .wrapForDiv > ul.tab > li:nth-of-type(1) > a {\n        width: 159px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(1) > a:after {\n          right: 0; }\n      .wrapForDiv > ul.tab > li:nth-of-type(2) > a {\n        width: 104px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(2) > a:after {\n          left: 0; }\n  .wrapForDiv > .featuresAndReviews {\n    height: 178px;\n    width: 282px;\n    background-color: #F4F4F4;\n    overflow: auto;\n    box-sizing: border-box; }\n    .wrapForDiv > .featuresAndReviews > .tabcontent {\n      display: none;\n      -webkit-animation: fadeEffect 1s;\n      animation: fadeEffect 1s; }\n      .wrapForDiv > .featuresAndReviews > .tabcontent.active {\n        display: block; }\n    .wrapForDiv > .featuresAndReviews > #reviews {\n      position: relative; }\n      .wrapForDiv > .featuresAndReviews > #reviews > div {\n        padding: 30px 20px 25px 65px;\n        position: relative;\n        border-bottom: 1px solid #E9E9E9; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div:nth-last-of-type(1) {\n          border: none; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > img {\n          position: absolute;\n          height: 30px;\n          width: 25px;\n          top: 35px;\n          left: 20px; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > p {\n          color: #0d0d1e;\n          font: 400 12px / 18px Roboto; }\n\n@-webkit-keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n@keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n.btn-blue {\n  background-color: #1875D0;\n  color: #ffffff;\n  font: 400 14px / 21px Roboto; }\n\n.onOrOffLineUser {\n  background-color: #00C652;\n  border: 1px solid white;\n  box-shadow: 1px 1px 3px 0px rgba(0, 0, 0, 0.65);\n  height: 18px;\n  width: 18px;\n  box-sizing: border-box;\n  border-radius: 50%; }\n\n.error-container {\n  width: 1103px;\n  margin: auto;\n  background-image: url(" + __webpack_require__(17) + ");\n  background-repeat: no-repeat;\n  background-position-x: right;\n  background-position-y: 80px;\n  min-height: 500px; }\n  .error-container h1 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    text-align: center;\n    margin-top: 65px; }\n  .error-container h2 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    margin-top: 189px; }\n  .error-container h3 {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 10px;\n    margin-bottom: 30px; }\n  .error-container > .btn-blue {\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n\n.clicked {\n  box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.4) !important;\n  margin-top: 12px !important; }\n\n.multiple {\n  width: 1150px;\n  margin: 5px auto 0; }\n  .multiple > .bulletin-short {\n    float: left;\n    width: 380px;\n    margin-left: 5px;\n    height: 165px;\n    padding: 15px; }\n    .multiple > .bulletin-short:nth-of-type(3n-2) {\n      margin-left: 0; }\n    .multiple > .bulletin-short:nth-of-type(1) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(2) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(3) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short > .bulletin-center-column {\n      float: none;\n      margin: 0;\n      position: absolute;\n      top: 15px;\n      right: 110px; }\n      .multiple > .bulletin-short > .bulletin-center-column > .bulletin-action {\n        bottom: -43px;\n        width: 110px;\n        right: -30px;\n        z-index: 1; }\n    .multiple > .bulletin-short > .bulletin-left-column > .bulletin-description {\n      color: #0c0c1e;\n      font: 400 12px / 18px Roboto;\n      width: 170px;\n      overflow: hidden;\n      height: 40px; }\n\n.red {\n  background-color: #F74A4A; }\n\n.inputForm-required {\n  position: relative;\n  padding: 6px 2px;\n  font: 400 16px / 24.8px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px solid #9b9b9b;\n  margin-top: 15px; }\n  .inputForm-required label {\n    position: absolute;\n    left: 2px;\n    top: 6px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n  .inputForm-required input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent; }\n\n.inputForm.required {\n  border-bottom: 1px solid #9b9b9b !important; }\n\n.inputForm {\n  position: relative;\n  font: 400 16px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px dotted #999999; }\n  .inputForm label {\n    position: absolute;\n    left: 5px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s;\n    top: -1px; }\n  .inputForm input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent;\n    position: relative;\n    padding: 1px 5px; }\n\n/* Text element animation */\n.textOut {\n  top: -15px !important;\n  font-size: 12px !important;\n  left: 5px !important; }\n\nsection.login, section.register {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  padding: 65px 230px;\n  width: 1100px;\n  margin: 5px auto 0;\n  box-sizing: border-box; }\n  section.login > h2, section.register > h2 {\n    text-align: center;\n    color: #202020;\n    font: 400 22px Roboto;\n    margin-bottom: 55px; }\n  section.login > .inputForm, section.register > .inputForm {\n    margin-bottom: 45px; }\n    section.login > .inputForm > label, section.login > .inputForm input, section.register > .inputForm > label, section.register > .inputForm input {\n      color: #9a9a9a;\n      font: 400 16px Roboto; }\n  section.login > .btn-blue, section.register > .btn-blue {\n    height: 35px;\n    width: 180px;\n    margin-left: 45px;\n    line-height: 35px; }\n    section.login > .btn-blue.vk, section.register > .btn-blue.vk {\n      background: #0186CF url(" + __webpack_require__(18) + ") no-repeat center left 10px;\n      margin-left: 0; }\n    section.login > .btn-blue.facebook, section.register > .btn-blue.facebook {\n      background: #3E50B3 url(" + __webpack_require__(19) + ") no-repeat center left 10px; }\n    section.login > .btn-blue.google, section.register > .btn-blue.google {\n      background: #FD3C00 url(" + __webpack_require__(20) + ") no-repeat center left 10px; }\n\nsection.bulletinAdd {\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #FDFDFD;\n  width: 1100px;\n  margin: 5px auto 0;\n  padding: 65px 225px;\n  box-sizing: border-box; }\n  section.bulletinAdd > h2 {\n    color: #1f1f1f;\n    font: 400 22px Roboto;\n    text-align: center;\n    margin-bottom: 40px; }\n  section.bulletinAdd > .selectBox {\n    margin-bottom: 45px;\n    margin-top: 15px; }\n    section.bulletinAdd > .selectBox > .defaultValue {\n      border-color: grey;\n      min-width: 195px;\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      padding-left: 5px; }\n  section.bulletinAdd > .file {\n    float: right;\n    position: relative;\n    background-color: #FDFDFD;\n    border-bottom: 1px solid #9e9e9e;\n    width: 410px;\n    padding-bottom: 5px; }\n    section.bulletinAdd > .file > input {\n      position: absolute;\n      z-index: -1; }\n    section.bulletinAdd > .file > p {\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      cursor: default;\n      float: left;\n      padding-top: 10px; }\n    section.bulletinAdd > .file > .btn-blue {\n      float: right;\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 30px;\n      width: 85px;\n      line-height: 30px; }\n  section.bulletinAdd > .inputForm {\n    margin-bottom: 45px; }\n    section.bulletinAdd > .inputForm > label, section.bulletinAdd > .inputForm input {\n      color: #999999;\n      font: 400 16px Roboto; }\n  section.bulletinAdd > .color {\n    margin-bottom: 40px; }\n    section.bulletinAdd > .color > p {\n      color: #999999;\n      font: 400 16px Roboto;\n      border-bottom: 1px dotted #999999;\n      width: 160px;\n      float: left;\n      height: 21px;\n      box-sizing: border-box; }\n    section.bulletinAdd > .color > div {\n      float: left;\n      margin-left: 19px;\n      margin-top: 3px;\n      width: 16px;\n      height: 15px;\n      cursor: pointer;\n      box-sizing: border-box;\n      outline: 1px solid transparent;\n      border: 1px solid white; }\n      section.bulletinAdd > .color > div.transparent {\n        border-color: #E9E9E9;\n        overflow: hidden; }\n        section.bulletinAdd > .color > div.transparent > .redStick {\n          -webkit-transform: rotate(-43deg);\n                  transform: rotate(-43deg);\n          background-color: red;\n          height: 2px;\n          margin-top: 5.5px;\n          width: 20px;\n          margin-left: -3px; }\n      section.bulletinAdd > .color > div.red {\n        background-color: red; }\n      section.bulletinAdd > .color > div.orange {\n        background-color: orange; }\n      section.bulletinAdd > .color > div.yellow {\n        background-color: yellow; }\n      section.bulletinAdd > .color > div.green {\n        background-color: green; }\n      section.bulletinAdd > .color > div.lightBlue {\n        background-color: lightBlue; }\n      section.bulletinAdd > .color > div.blue {\n        background-color: blue; }\n      section.bulletinAdd > .color > div.pink {\n        background-color: pink; }\n      section.bulletinAdd > .color > div.purple {\n        background-color: purple; }\n      section.bulletinAdd > .color > div.white {\n        background-color: white;\n        border-color: #E9E9E9; }\n      section.bulletinAdd > .color > div.gray {\n        background-color: gray; }\n      section.bulletinAdd > .color > div.black {\n        background-color: black; }\n      section.bulletinAdd > .color > div.brown {\n        background-color: brown; }\n      section.bulletinAdd > .color > div.multicolor {\n        /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#ff0000+0,ffff00+20,1dff00+40,00ffff+60,0400ff+80,ff00fa+100 */\n        background: #ff0000;\n        /* Old browsers */\n        /* FF3.6-15 */\n        background: -webkit-linear-gradient(left, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* Chrome10-25,Safari5.1-6 */\n        background: linear-gradient(to right, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */\n        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff0000', endColorstr='#ff00fa',GradientType=1 );\n        /* IE6-9 */ }\n      section.bulletinAdd > .color > div.active {\n        outline-color: red; }\n  section.bulletinAdd > .calendar, section.bulletinAdd .addCalendar {\n    width: 230px; }\n  section.bulletinAdd > .addCalendar {\n    background-image: url(" + __webpack_require__(21) + "); }\n    section.bulletinAdd > .addCalendar > .defaultValue {\n      border-bottom-color: blue;\n      background: none; }\n      section.bulletinAdd > .addCalendar > .defaultValue > p {\n        color: #1976d2;\n        font-size: 16px; }\n  section.bulletinAdd > .btn-blue {\n    height: 35px;\n    width: 180px;\n    line-height: 35px;\n    display: block;\n    margin: 0 auto; }\n\n.errors {\n  position: absolute;\n  font: 400 12px / 14px Roboto;\n  color: #dd2c00;\n  bottom: -17px; }\n\nnav {\n  width: 255px;\n  float: left;\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  margin-top: 5px;\n  z-index: 2;\n  position: absolute; }\n  nav > .map {\n    background: url(" + __webpack_require__(22) + ") no-repeat;\n    height: 145px;\n    width: 255px; }\n    nav > .map > .inputForm {\n      width: 215px;\n      margin: 115px auto 0; }\n      nav > .map > .inputForm.required {\n        border-color: black !important; }\n      nav > .map > .inputForm > input, nav > .map > .inputForm label, nav > .map > .inputForm .textOut {\n        color: black !important; }\n  nav > ul {\n    list-style: none; }\n    nav > ul > li > p {\n      padding-left: 75px;\n      color: #263238;\n      font: 500 13px / 40px Roboto;\n      background: url(" + __webpack_require__(23) + ") no-repeat center right 20px;\n      cursor: pointer; }\n    nav > ul > li:hover {\n      background-color: #E6E6E6 !important; }\n    nav > ul > li:nth-of-type(1) {\n      background: url(" + __webpack_require__(24) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(2) {\n      background: url(" + __webpack_require__(25) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(3) {\n      background: url(" + __webpack_require__(26) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(4) {\n      background: url(" + __webpack_require__(27) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(5) {\n      background: url(" + __webpack_require__(28) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(6) {\n      background: url(" + __webpack_require__(29) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(7) {\n      background: url(" + __webpack_require__(30) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(8) {\n      background: url(" + __webpack_require__(31) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(9) {\n      background: url(" + __webpack_require__(32) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(10) {\n      background: url(" + __webpack_require__(33) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(11) {\n      background: url(" + __webpack_require__(34) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(12) {\n      background: url(" + __webpack_require__(35) + ") no-repeat left 25px center; }\n\n.navBtn {\n  width: 43px;\n  height: 49px;\n  position: absolute;\n  left: 0;\n  top: 90px;\n  cursor: pointer;\n  background: url(" + __webpack_require__(36) + ") no-repeat;\n  -webkit-transition: all .25s;\n  transition: all .25s;\n  z-index: 1; }\n  .navBtn:hover {\n    width: 60px;\n    background-image: url(" + __webpack_require__(37) + "); }\n\n.cover {\n  position: absolute;\n  top: 0;\n  right: 0;\n  bottom: 0;\n  left: 0;\n  background-color: rgba(0, 0, 0, 0.9);\n  z-index: 5; }\n  .cover.hide {\n    -webkit-animation: hide 1s;\n            animation: hide 1s; }\n\n@-webkit-keyframes hide {\n  100% {\n    background-color: transparent; } }\n\n@keyframes hide {\n  100% {\n    background-color: transparent; } }\n\nsection.mail {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 1105px;\n  margin: 5px auto 0;\n  padding: 45px 0 80px; }\n  section.mail > .tab {\n    width: 905px;\n    border: 1px solid #E9E9E9;\n    border-bottom: none;\n    margin: 0 auto;\n    background-color: #F4F4F4; }\n    section.mail > .tab > ul {\n      background-color: #FDFDFD;\n      list-style: none;\n      border-bottom: 1px solid #E9E9E9;\n      height: 50px;\n      box-sizing: border-box; }\n      section.mail > .tab > ul > li {\n        color: #9e9e9e;\n        font: 400 14px / 50px Roboto;\n        cursor: pointer;\n        text-align: center;\n        float: left;\n        position: relative; }\n        section.mail > .tab > ul > li:after {\n          content: '';\n          position: absolute;\n          bottom: 0;\n          left: 50%;\n          display: block;\n          height: 2px;\n          width: 0;\n          background-color: #FD5151;\n          -webkit-transition: all .25s;\n          transition: all .25s; }\n        section.mail > .tab > ul > li.active {\n          color: #7eafe1; }\n          section.mail > .tab > ul > li.active:after {\n            width: 100%;\n            left: 0; }\n        section.mail > .tab > ul > li:nth-of-type(1) {\n          width: 110px; }\n        section.mail > .tab > ul > li:nth-of-type(2) {\n          width: 215px; }\n        section.mail > .tab > ul > li:nth-of-type(3) {\n          padding: 0 15px; }\n    section.mail > .tab > .mailTab > .tavleTitle {\n      height: 65px;\n      padding-top: 35px;\n      box-sizing: border-box;\n      border-bottom: 1px solid #999999; }\n      section.mail > .tab > .mailTab > .tavleTitle > p {\n        color: #9e9e9e;\n        font: 400 14px / 20px Roboto;\n        float: left;\n        margin-left: 30px;\n        cursor: default; }\n        section.mail > .tab > .mailTab > .tavleTitle > p:nth-of-type(2) {\n          margin-left: 45px; }\n      section.mail > .tab > .mailTab > .tavleTitle > .selectBox {\n        float: left;\n        margin-left: 45px; }\n        section.mail > .tab > .mailTab > .tavleTitle > .selectBox > .defaultValue {\n          color: #9e9e9e;\n          font: 400 16px Roboto;\n          border-color: #999999;\n          width: 195px; }\n        section.mail > .tab > .mailTab > .tavleTitle > .selectBox:nth-of-type(2) {\n          margin-left: 10px; }\n    section.mail > .tab > .mailTab > .table > .tr {\n      border-bottom: 1px solid #999999;\n      padding: 10px 0;\n      position: relative; }\n      section.mail > .tab > .mailTab > .table > .tr:before, section.mail > .tab > .mailTab > .table > .tr:after {\n        content: \" \";\n        display: table; }\n      section.mail > .tab > .mailTab > .table > .tr:after {\n        clear: both; }\n      section.mail > .tab > .mailTab > .table > .tr:nth-of-type(1) > .td {\n        color: #9e9e9e;\n        font: 400 13px / 27px Roboto;\n        text-align: left;\n        cursor: default !important; }\n        section.mail > .tab > .mailTab > .table > .tr:nth-of-type(1) > .td > .checkBox {\n          position: relative !important;\n          margin-top: 0 !important;\n          margin-left: 30px;\n          left: 0 !important; }\n      section.mail > .tab > .mailTab > .table > .tr > .td {\n        float: left;\n        color: #666666;\n        font: 400 14px / 18px Roboto; }\n        section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(1) {\n          width: 80px;\n          min-height: 1px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(1) > .checkBox {\n            left: 30px;\n            position: absolute;\n            top: 50%;\n            margin-top: -13px; }\n        section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(2) {\n          width: 145px;\n          min-height: 1px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(2) > .trUser {\n            position: absolute;\n            top: 50%;\n            margin-top: -9px; }\n        section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(3) {\n          width: 310px;\n          cursor: pointer; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(3) > .preview {\n            color: #9e9e9e;\n            font: 400 14px Roboto;\n            width: 310px;\n            white-space: nowrap;\n            overflow: hidden;\n            text-overflow: ellipsis;\n            margin-top: 10px; }\n        section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(4) {\n          width: 105px;\n          min-height: 1px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(4) > img {\n            position: absolute;\n            top: 50%;\n            margin-top: -7px;\n            left: 555px; }\n        section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(5) {\n          width: 150px;\n          min-height: 1px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(5) > .status {\n            position: absolute;\n            top: 50%;\n            margin-top: -9px; }\n        section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(6) {\n          min-height: 1px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(6) > .date {\n            position: absolute;\n            top: 50%;\n            margin-top: -9px;\n            left: 790px; }\n", ""]);

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

	module.exports = __webpack_require__.p + "images/calendar.png";

/***/ },
/* 5 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/caretCalendar.png";

/***/ },
/* 6 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/logo.png";

/***/ },
/* 7 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/add.png";

/***/ },
/* 8 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/mail.png";

/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/mail_shadow.png";

/***/ },
/* 10 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/bell.png";

/***/ },
/* 11 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/bell_shadow.png";

/***/ },
/* 12 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/services.png";

/***/ },
/* 13 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/services_shadow.png";

/***/ },
/* 14 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/userName.png";

/***/ },
/* 15 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/next.png";

/***/ },
/* 16 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/prev.png";

/***/ },
/* 17 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/error_bg.png";

/***/ },
/* 18 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/vk.png";

/***/ },
/* 19 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/facebook.png";

/***/ },
/* 20 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/google.png";

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
	exports.push([module.id, ".view-profile-container {\n  background-color: #fff;\n  box-sizing: border-box;\n  padding: 40px 127px 30px;\n  margin: 5px auto 0;\n  width: 1103px;\n  font: 400 16px/24px Roboto;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n  .view-profile-container h1 {\n    padding-bottom: 16px;\n    text-align: center;\n    font: 400 22px/26px Roboto; }\n  .view-profile-container input:-moz-read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n  .view-profile-container input:read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n    .view-profile-container input:-moz-read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n    .view-profile-container input:read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n  .view-profile-container .social-link-container {\n    margin-bottom: 45px; }\n\n.profile-containers-wrap {\n  width: 849px;\n  margin: 0 auto; }\n\n.profile-left-container, .profile-right-container, .profile-info-section-left, .profile-info-section-right, .profile-info {\n  float: left; }\n  .profile-left-container:before, .profile-left-container:after, .profile-right-container:before, .profile-right-container:after, .profile-info-section-left:before, .profile-info-section-left:after, .profile-info-section-right:before, .profile-info-section-right:after, .profile-info:before, .profile-info:after {\n    content: \" \";\n    display: table; }\n  .profile-left-container:after, .profile-right-container:after, .profile-info-section-left:after, .profile-info-section-right:after, .profile-info:after {\n    clear: both; }\n\n.view-profile:before, .view-profile:after, .view-profile-container:before, .view-profile-container:after {\n  content: \" \";\n  display: table; }\n\n.view-profile:after, .view-profile-container:after {\n  clear: both; }\n\n.profile-left-container {\n  width: 190px;\n  padding: 0 52px; }\n  .profile-left-container .btn-blue {\n    width: 180px;\n    height: 36px;\n    line-height: 36px;\n    margin: 0 auto;\n    margin-top: 9px; }\n  .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox {\n    display: block;\n    margin-bottom: 25px; }\n    .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox > .defaultValue {\n      color: #9e9e9e;\n      font: 400 16px / 24px Roboto;\n      border-bottom: 1px solid #9e9e9e; }\n\n.profile-right-container {\n  width: 485px;\n  padding-left: 20px;\n  border-left: 1px solid #ebebeb; }\n  .profile-right-container > .wrapForDiv {\n    width: auto;\n    float: none;\n    margin-bottom: 0; }\n    .profile-right-container > .wrapForDiv > ul.tab > li {\n      width: 50%; }\n      .profile-right-container > .wrapForDiv > ul.tab > li > a {\n        width: auto; }\n    .profile-right-container > .wrapForDiv > .featuresAndReviews {\n      width: 500px; }\n\n.profile-info-section-left, .profile-info-section-right {\n  width: 50%;\n  box-sizing: border-box; }\n\n.profile-avatar {\n  width: 145px;\n  height: 215px;\n  margin: 0 auto 40px;\n  padding-bottom: 5px;\n  background: url(" + __webpack_require__(50) + ") no-repeat center center;\n  background-size: contain;\n  box-sizing: border-box; }\n\n.profile-settings-dropdown .profile-settings-dropdown-title {\n  cursor: pointer;\n  padding: 13px 0; }\n  .profile-settings-dropdown .profile-settings-dropdown-title:before, .profile-settings-dropdown .profile-settings-dropdown-title:after {\n    content: \" \";\n    display: table; }\n  .profile-settings-dropdown .profile-settings-dropdown-title:after {\n    clear: both; }\n  .profile-settings-dropdown .profile-settings-dropdown-title span {\n    color: #929292;\n    float: left; }\n  .profile-settings-dropdown .profile-settings-dropdown-title div {\n    float: right;\n    background: url(" + __webpack_require__(51) + ");\n    width: 13px;\n    height: 7px; }\n\n.social-link-container div {\n  cursor: pointer;\n  width: 24px;\n  height: 24px;\n  float: left; }\n  .social-link-container div:before, .social-link-container div:after {\n    content: \" \";\n    display: table; }\n  .social-link-container div:after {\n    clear: both; }\n  .social-link-container div:not(:last-of-type) {\n    margin-right: 23px; }\n\n.profile-messages-and-notifications {\n  display: block;\n  width: 100%;\n  margin: 30px 0;\n  float: left; }\n  .profile-messages-and-notifications:before, .profile-messages-and-notifications:after {\n    content: \" \";\n    display: table; }\n  .profile-messages-and-notifications:after {\n    clear: both; }\n\n.profile-messages-and-notifications-nav, .profile-messages-and-notifications-content {\n  border: 1px solid #ebebeb;\n  overflow: hidden; }\n\n.profile-messages-and-notifications-nav div {\n  color: #929292;\n  width: 50%;\n  padding: 24px 0;\n  text-align: center;\n  float: left; }\n  .profile-messages-and-notifications-nav div:before, .profile-messages-and-notifications-nav div:after {\n    content: \" \";\n    display: table; }\n  .profile-messages-and-notifications-nav div:after {\n    clear: both; }\n\n.profile-messages-and-notifications-nav .nav-item-selected {\n  color: #7eaee0;\n  border-bottom: 2px solid #ff5252; }\n\n.profile-messages-and-notifications-content {\n  background-color: #f6f6f6;\n  height: 180px;\n  box-sizing: border-box; }\n\n.profile-messages-and-notifications-content-item {\n  width: 100%;\n  border-bottom: 1px solid #ebebeb;\n  height: 123px;\n  position: relative;\n  overflow: hidden; }\n  .profile-messages-and-notifications-content-item .content-item-logo {\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 27px;\n    height: 27px;\n    padding: 0 23px 23px 7px;\n    background: url(" + __webpack_require__(52) + ") no-repeat;\n    display: inline-block;\n    background-position: center;\n    margin-top: 28px; }\n  .profile-messages-and-notifications-content-item .content-item-text {\n    display: inline-block;\n    float: right;\n    width: 373px;\n    margin: 28px 38px 24px 0;\n    font: 400 12px/18px Roboto;\n    color: #0c0c1e; }\n    .profile-messages-and-notifications-content-item .content-item-text:before, .profile-messages-and-notifications-content-item .content-item-text:after {\n      content: \" \";\n      display: table; }\n    .profile-messages-and-notifications-content-item .content-item-text:after {\n      clear: both; }\n\n.profile-info {\n  margin-bottom: 30px; }\n", ""]);

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
	  "selectBox": __webpack_require__(59),
	  "textArea": __webpack_require__(60)
	};

	window.ee = __webpack_require__(61);
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

	var MAX_SYMBOLS = 1000;

	module.exports = function () {
	  return {
	    restrict: "E",
	    require: '^ngModel',
	    scope: {
	      label: "@",
	      ngModel: "="
	    },
	    replace: true,
	    template: '<div class="inputForm">\n                 <label>{{ label }}</label>\n                 <textarea ng-model="ngModel" maxlength=' + MAX_SYMBOLS + '></textarea>\n                 <div class="symbols"></div>\n               </div>',
	    controller: function controller($scope, $element, $timeout) {
	      document.addEventListener('keyup', count);

	      $scope.$on("$destroy", function () {
	        document.removeEventListener('keyup', count);
	      }.bind(this));

	      var defaultBorder = "";

	      var el = $element[0].getElementsByTagName('textarea')[0],
	          label = $element[0].getElementsByTagName('label')[0],
	          symbols = $element[0].getElementsByClassName('symbols')[0];

	      function count() {
	        symbols.innerHTML = $scope.ngModel.length + '/' + MAX_SYMBOLS;
	      }

	      function onBlur(e) {
	        if (!$scope.ngModel.length) hideAnimation();
	      }

	      function onFocus(e) {
	        if (!$scope.ngModel.length) displayAnimation();
	      }

	      function displayAnimation() {
	        label.style.color = "#1875D0";
	        if (!defaultBorder.length) {
	          defaultBorder = window.getComputedStyle(label.parentNode).borderBottom;
	        } else {
	          label.parentNode.style.borderBottom = '2px solid #1875D0';
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

	      count();
	    }
	  };
		};

/***/ },
/* 61 */
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
/* 62 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	module.exports = {
	  "/": {
	    templateUrl: "templates/index.html",
	    controller: __webpack_require__(63),
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
	    controller: __webpack_require__(64),
	    controllerAs: "bdetailed"
	  },
	  '/bulletinAdd': {
	    templateUrl: "templates/authenticated/bulletinAdd.html",
	    controller: __webpack_require__(65),
	    controllerAs: "badd"
	  },
	  '/socketTest': {
	    templateUrl: "templates/socketTest.html",
	    controller: __webpack_require__(66),
	    controllerAs: "socket"
	  },
	  '/login': {
	    templateUrl: "templates/login.html",
	    controller: __webpack_require__(67),
	    controllerAs: "login"
	  },
	  '/register': {
	    templateUrl: "templates/register.html",
	    controller: __webpack_require__(68),
	    controllerAs: "register"
	  },
	  '/editProfile': {
	    templateUrl: "templates/authenticated/edit-profile.html",
	    controller: __webpack_require__(69),
	    controllerAs: "profile"
	  },
	  '/profile': {
	    templateUrl: "templates/authenticated/profile.html",
	    controller: __webpack_require__(70),
	    controllerAs: "profile"
	  },
	  '/favourites': {
	    templateUrl: "templates/authenticated/favourites.html",
	    controller: __webpack_require__(72),
	    controllerAs: "favourite"
	  },
	  '/searchResults': {
	    templateUrl: "templates/searchResults.html",
	    controller: __webpack_require__(73),
	    controllerAs: "searchResults"
	  },
	  '/mail': {
	    templateUrl: "templates/authenticated/mail.html"
	  }
		};

/***/ },
/* 63 */
/***/ function(module, exports) {

	"use strict";

		module.exports = function ($scope) {};

/***/ },
/* 64 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 65 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope, $timeout) {

	  this.init = function () {
	    // if(!db.user)
	    //   return $scope.redirectToUrl('/403')

	    this.types = ["Аренда", "Продажа", "Отдам даром", "Обмен"];
	    this.files = [];
	  };

	  this.showFileUpload = function () {
	    $timeout(function () {
	      document.getElementById('uploadFile').click();
	    }, 100);
	  };
	};

/***/ },
/* 66 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope) {
	  this.message = "";
	  this.messages = [];

	  this.init = function () {
	    /* Connect websockets */
	    this.wsUri = 'ws://' + document.location.host + document.location.pathname + 'whiteboardendpoint';
	    this.websocket = new WebSocket(wsUri);

	    this.websocket.onerror = function (evt) {
	      onError(evt);
	    };

	    function onError(evt) {
	      console.error(evt.data);
	    }
	  };

	  var handler = function (e) {
	    if (e.which == 13) this.send.call(this);
	  }.bind(this);

	  document.addEventListener('keydown', handler);

	  this.send = function () {};
		};

/***/ },
/* 67 */
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
/* 68 */
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
	    if (_this.emailValid && _this.passwordValid && _this.password2Valid) {
	      _this.db.register({
	        "email": _this.email,
	        "password": _this.password
	      }, function (err, data) {
	        _this.deleteListners();
	        if (err) $scope.$parent.redirectToUrl('/500');else {
	          window.db.isLogged = true;
	          $scope.redirectToUrl('/profile');
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
/* 69 */
/***/ function(module, exports) {

	"use strict";

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	var ProfileContact = function ProfileContact() {
	    _classCallCheck(this, ProfileContact);

	    this.contactEmails = [''];
	    this.contactPhones = [''];
	    this.type = "ENTREPRENEUR";

	    this.position = "";
	    this.companyName = "";
	    this.skypeUserName = "";
	    this.linkToWebSite = "";
	};

	var profileCtrl = function () {
	    function profileCtrl($scope) {
	        _classCallCheck(this, profileCtrl);

	        //        if(!$scope.$parent.db.user)
	        //          $scope.$parent.redirectToUrl('/403', true)
	        //        else
	        this.contact = new ProfileContact();
	        this.contactTypes = ["LEGAL_ENTITY", "ENTREPRENEUR", "INDIVIDUAL"];

	        this.email = "";
	        this.fio = "";
	        this.mainPhoneNumber = "";
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
/* 70 */
/***/ function(module, exports, __webpack_require__) {

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

	        // if(!$scope.$parent.db.user)
	        //   $scope.$parent.redirectToUrl('/403', true)
	        // else
	        this.contact = new ProfileContact();

	        this.socialCategories = __webpack_require__(71);
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
/* 71 */
/***/ function(module, exports) {

	module.exports = {
		"items": [
			{
				"key": "FACEBOOK",
				"synonym": "facebook",
				"class": "facebook"
			},
			{
				"key": "VKONTAKTE",
				"synonym": "vkontakte",
				"class": "vkontakte"
			},
			{
				"key": "ODNOKLASSNIKI",
				"synonym": "odnoklassniki",
				"class": "odnoklassniki"
			},
			{
				"key": "LINKEDIN",
				"synonym": "linkedin",
				"class": "linkedin"
			},
			{
				"key": "GOOGLEPLUS",
				"synonym": "googleplus",
				"class": "googleplus"
			},
			{
				"key": "YOUTUBE",
				"synonym": "youtube",
				"class": "youtube"
			},
			{
				"key": "TWITTER",
				"synonym": "twitter",
				"class": "twitter"
			}
		]
	};

/***/ },
/* 72 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope) {};

/***/ },
/* 73 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 74 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var ctx = module.exports = {};
	/* Контроллер для управления  основным скелетом документа */
	module.exports = function ($http, $scope, $location, $timeout, $cookies, $cookieStore) {
	  var _this = this;

	  console.log('Main controller loaded');
	  console.log($cookies);
	  /* Standalone module for bd */
	  $scope.db = __webpack_require__(75);
	  $scope.db.init($http);
	  window.db = $scope.db;

	  /* Initialize data */
	  this.init = function () {
	    /* variables for testing */
	    this.hello = "hi";
	    this.boolean = true;
	    this.list = [1, 2, 3];
	    /* End variables for testing */

	    this.loader = __webpack_require__(78);
	    this.loader($scope, $timeout);

	    console.log("Main controller init");

	    this.sortingCategories = __webpack_require__(79).items;
	    this.currentCategory = "None";
	    this.sortingId = 0;

	    this.showFilters = false;

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

	  this.displayFilters = function () {
	    this.showFilters = true;
	    var nav = document.getElementsByTagName('nav')[0];

	    var handler = function (e) {
	      var x = e.clientX,
	          y = e.clientY,
	          rect = nav.getBoundingClientRect();

	      if (!(x > rect.left && x < rect.right && y > rect.top && y < rect.bottom)) {
	        document.removeEventListener('click', handler);
	        this.showFilters = false;
	        $scope.$apply();
	      }
	    }.bind(this);

	    document.addEventListener('click', handler);
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
/* 75 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var utils = __webpack_require__(76),
	    config = __webpack_require__(77);

	var ctx = module.exports = {};

	var DEFAULT_IMAGE = "";

	module.exports.init = function ($http) {
	  /* init data from database here */
	  ctx.setDefaults();
	  ctx.transport = $http;

	  ctx.checkUserIsLogged(function (err, data) {
	    if (err) console.error(err);else {
	      if (data) ctx.saveUserData(data);else console.log("User is not logged in");
	    }
	  }.bind(this));

	  console.log("Database initialized");
	};

	module.exports.setDefaults = function () {
	  console.log("Database :: defaults set");

	  ctx.isLogged = false;

	  ctx.user = {
	    emailGeneral: "",
	    emailContact: "",
	    phoneGeneral: "",
	    phoneContact: "",
	    workplace: "",
	    position: "",
	    skypeName: "",
	    website: "",
	    social: [],
	    avatar: ""
	  };
	  ctx.unreadMessages = 0;
	  ctx.unreadNotifications = 0;
	};

	module.exports.register = function (data, cb) {
	  ctx.transport({
	    method: config.routes.register.method,
	    url: config.api.auth + config.routes.register.url,
	    data: data,
	    headers: {
	      "Content-Type": "application/json"
	    },
	    withCredentials: true
	  }).then(function (data) {
	    return cb(null, data.data);
	  }).catch(cb);
	};

	module.exports.checkEmail = function (email, cb) {
	  ctx.transport({
	    method: config.routes.checkEmail.method,
	    url: config.api.auth + config.routes.checkEmail.url,
	    data: email,
	    headers: {
	      "Content-Type": "text/plain"
	    },
	    withCredentials: true
	  }).then(function (data) {
	    return cb(null, data.data);
	  }).catch(cb);
	};

	module.exports.login = function (data, cb) {
	  ctx.transport({
	    method: config.routes.login.method,
	    url: config.api.auth + config.routes.login.url,
	    data: data,
	    headers: {
	      "Content-Type": "application/json"
	    },
	    withCredentials: true
	  }).then(function (data) {
	    return cb(null, data.data);
	  }).catch(cb);
	};

	/* This method does saves user data in this module only, no backend communication */
	module.exports.saveUserData = function (data) {
	  if (!data) return this.isLogged = false;

	  data = data || "";

	  this.isLogged = true;
	  this.user.emailGeneral = data.contact.emailGeneral || "";
	  this.user.emailContact = data.contact.contactEmails[0] || "";
	  this.user.phoneGeneral = data.mainPhoneNumber;
	  this.user.phoneContact = data.contact.contactPhones[0] || "";
	  this.user.workplace = data.contact.companyName || "";
	  this.user.position = data.contact.position || "";
	  this.user.skypeName = data.contact.skypeUserName || "";
	  this.user.website = "";
	  this.user.social = data.contact.socNetLink || [];
	  this.user.avatarId = data.imgId || DEFAULT_IMAGE;

	  /* TODO: распарсить данные в осмысленные переменные */

	  console.log("Database:: User data saved successfully( шутка ) ");
	};

	module.exports.checkUserIsLogged = function (cb) {
	  ctx.transport({
	    method: config.routes.checkLogged.method,
	    url: config.api.url + config.routes.checkLogged.url,
	    withCredentials: true
	  }).then(function (data) {
	    return cb(null, data.data);
	  }).catch(cb);
	};

	module.exports.userLogout = function () {
	  ctx.setDefaults();

	  ctx.transport({
	    method: config.routes.logout.method,
	    url: config.api.auth + config.routes.logout.url
	  }).then(function () {}, function () {});
		};

/***/ },
/* 76 */
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
/* 77 */
/***/ function(module, exports) {

	module.exports = {
		"api": {
			"url": "http://93.73.109.38:8081/",
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
				"url": "api/oauth/login",
				"accept": {
					"email": "sss2@gmail.com",
					"password": "123456"
				}
			},
			"logout": {
				"method": "GET",
				"url": "api/oauth/logout"
			},
			"register": {
				"method": "POST",
				"url": "api/oauth/register",
				"accept": {
					"email": "sss2@gmail.com",
					"password": "123456"
				}
			},
			"checkEmail": {
				"method": "POST",
				"url": "api/oauth/login/checkEmail",
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
/* 78 */
/***/ function(module, exports) {

	'use strict';

	var TEMPLATE_RENDER_DELAY = 500,
	    ANIMATION_DELAY = 900;

	module.exports = function ($scope, $timeout) {
	  var cover = document.getElementsByClassName('cover')[0];
	  var content = document.getElementsByClassName('general-content')[0];

	  var display = true;

	  $scope.$on('$routeChangeSuccess', function () {
	    if (display) {
	      $timeout(function () {
	        content.style.display = "";
	        cover.classList.add('hide');
	        $timeout(function () {
	          cover.style.display = "none";
	        }.bind(this), ANIMATION_DELAY);
	      }.bind(this), TEMPLATE_RENDER_DELAY);

	      display = false;
	    }
	  }.bind(this));
	};

/***/ },
/* 79 */
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
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXBwLmpzIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vL3dlYnBhY2svYm9vdHN0cmFwIGNjYjkzZDY0NDA0MGQyMmM5OWVlIiwid2VicGFjazovLy9hcHAuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Jhc2ljLnNjc3M/NGY1MyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvYmFzaWMuc2NzcyIsIndlYnBhY2s6Ly8vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzIiwid2VicGFjazovLy8uL2ltYWdlcy9jYWxlbmRhci5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9sb2dvLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvbWFpbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL21haWxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYmVsbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvc2VydmljZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy91c2VyTmFtZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL25leHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9wcmV2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXJyb3JfYmcucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy92ay5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2ZhY2Vib29rLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZ29vZ2xlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9tYXAucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9Gb3JBbmltYWxzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQnVzaW5lc3MucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL1RyYW5zcG9ydC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvSXNGcmVlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9CYXJ0ZXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9ydXByLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcnVwckhvdmVyLnBuZyIsIndlYnBhY2s6Ly8vLi9+L3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzcz9jZjI3Iiwid2VicGFjazovLy8uL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3MiLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9WLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2RvbGxhckJpbGwucG5nIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzcz9kYzJiIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvcHJvZmlsZS5zY3NzPzAxYTEiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL3Byb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYXZhdGFyLmpwZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9pY29uX3VzZXIucG5nIiwid2VicGFjazovLy9tb2R1bGVzL2xvZ2dlci5qcyIsIndlYnBhY2s6Ly8vLi9kYXRhL2RlYnVnLmpzb24iLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dC5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9zZWxlY3RCb3guanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dEFyZWEuanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2V2ZW50cy5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9yb3V0ZXIuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2luZGV4LmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvYnVsbGV0aW5BZGQuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL3NvY2tldFRlc3QuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2xvZ2luLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9yZWdpc3Rlci5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlLmpzIiwid2VicGFjazovLy8uL2RhdGEvc29jaWFsLmpzb24iLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcy5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvc2VhcmNoUmVzdWx0cy5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvbWFpbi5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9kYi5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy91dGlscy5qcyIsIndlYnBhY2s6Ly8vLi9kYXRhL2NvbmZpZy5qc29uIiwid2VicGFjazovLy9tb2R1bGVzL2xvYWRlci5qcyIsIndlYnBhY2s6Ly8vLi9kYXRhL3NvcnRpbmcuanNvbiJdLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSlcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcblxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0ZXhwb3J0czoge30sXG4gXHRcdFx0aWQ6IG1vZHVsZUlkLFxuIFx0XHRcdGxvYWRlZDogZmFsc2VcbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubG9hZGVkID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCJcIjtcblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXygwKTtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHdlYnBhY2svYm9vdHN0cmFwIGNjYjkzZDY0NDA0MGQyMmM5OWVlXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbnJlcXVpcmUoXCIuL3N0eWxlcy9iYXNpYy5zY3NzXCIpXG5yZXF1aXJlKFwiLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXCIpXG5yZXF1aXJlKFwiLi9zdHlsZXMvZWRpdC1wcm9maWxlLnNjc3NcIilcbnJlcXVpcmUoXCIuL3N0eWxlcy9wcm9maWxlLnNjc3NcIilcblxucmVxdWlyZShcIi4vbW9kdWxlcy9sb2dnZXJcIikoKVxuXG5jb25zdCBtYXRlcmlhbHMgPSByZXF1aXJlKCcuL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzJyksXG4gICAgICByb3V0ZXIgPSByZXF1aXJlKCcuL21vZHVsZXMvcm91dGVyJylcblxubGV0IGFwcCA9IGFuZ3VsYXIubW9kdWxlKCdndXAnLCBbJ25nUm91dGUnLCAnbmdDb29raWVzJ10pXG5cbi8vIEFwcCBjb25maWdcbmFwcFxuICAuY29uZmlnKFsnJHJvdXRlUHJvdmlkZXInLCAnJGxvY2F0aW9uUHJvdmlkZXInLCBmdW5jdGlvbiggJHJvdXRlUHJvdmlkZXIsICRsb2NhdGlvblByb3ZpZGVyKXtcbiAgICBmb3IobGV0IGtleSBpbiByb3V0ZXIpXG4gICAgICAkcm91dGVQcm92aWRlci53aGVuKGtleSwgcm91dGVyW2tleV0pXG5cbiAgICAkcm91dGVQcm92aWRlci5vdGhlcndpc2Uoe1xuICAgICAgcmVkaXJlY3RUbzogJy80MDQnXG4gICAgfSlcblxuICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh7XG4gICAgICBlbmFibGVkIDogdHJ1ZSxcbiAgICAgIHJlcXVpcmVCYXNlIDogZmFsc2VcbiAgICB9KVxuICB9XSlcbiAgLmNvbnRyb2xsZXIoJ21haW5DdHJsJywgcmVxdWlyZSgnLi9jb250cm9sbGVycy9tYWluJykpXG5cbm1hdGVyaWFsc1xuICAuaW5pdChhcHApXG4gIC5ydW4oKVxuXG4gIC8qIEV2ZW50IGVtbWl0dGVyIGV4YW1wbGVzICovXG4gIGxldCBpZCA9IGVlLm9uKCdtdWhhaGFoYScsIGZ1bmN0aW9uKGRhdGEpIHtcbiAgICBjb25zb2xlLmxvZyhcImJ1Z2FnYXNoZWNoa29cIilcbiAgICBjb25zb2xlLmxvZyhkYXRhKVxuICB9KVxuXG4gIGVlLmVtaXQoe1xuICAgIG5hbWUgOiBcIm11aGFoYWhhXCIsXG4gICAgZGF0YSA6IFsxLDIsMyw0LDVdXG4gIH0pXG5cbiAgZWUub2ZmKGlkKVxuXG4gIGVlLmVtaXQoe1xuICAgIG5hbWUgOiBcIm11aGFoYWhhXCIsXG4gICAgZGF0YSA6IFsxLDIsMyw0LDVdXG4gIH0pXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBhcHAuanNcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Jhc2ljLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vYmFzaWMuc2Nzc1wiLCBmdW5jdGlvbigpIHtcblx0XHRcdHZhciBuZXdDb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Jhc2ljLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvYmFzaWMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwiYm9keSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRUNFQ0VDOyB9XFxuXFxuLmNhbGVuZGFyLCAuYWRkQ2FsZW5kYXIge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYWxlbmRhci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0O1xcbiAgcGFkZGluZy1sZWZ0OiA0NXB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIG1hcmdpbi1ib3R0b206IDQwcHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAuY2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlLCAuYWRkQ2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkIGdyZXk7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCA1cHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgcGFkZGluZy1yaWdodDogMTVweDtcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAuY2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlID4gcCwgLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSA+IHAge1xcbiAgICAgIHRleHQtYWxpZ246IGxlZnQ7XFxuICAgICAgY29sb3I6ICMyNjI2MjY7XFxuICAgICAgZm9udDogNDAwIDE0cHggLyAyMHB4IFJvYm90bzsgfVxcbiAgLmNhbGVuZGFyID4gLmxpc3RWYWx1ZSwgLmFkZENhbGVuZGFyID4gLmxpc3RWYWx1ZSB7XFxuICAgIGRpc3BsYXk6IG5vbmU7IH1cXG5cXG5oZWFkZXIge1xcbiAgaGVpZ2h0OiA3MHB4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMxODc1RDA7IH1cXG5cXG4uYnRuLWJsdWUsIC5idG4tZ3JleSB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgYm9yZGVyLXJhZGl1czogNXB4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIHRleHQtYWxpZ246IGNlbnRlcjsgfVxcblxcbi5idG4tZ3JleSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRDhEOEQ4O1xcbiAgY29sb3I6ICM4Njg2ODY7XFxuICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvOyB9XFxuXFxuLmNvbnRhaW5lciB7XFxuICB3aWR0aDogMTI4MHB4O1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgbWFyZ2luOiAwIGF1dG87IH1cXG5cXG4uY2xlYXJmaXg6YmVmb3JlLCAuY2xlYXJmaXg6YWZ0ZXIge1xcbiAgY29udGVudDogXFxcIiBcXFwiO1xcbiAgZGlzcGxheTogdGFibGU7IH1cXG5cXG4uY2xlYXJmaXg6YWZ0ZXIge1xcbiAgY2xlYXI6IGJvdGg7IH1cXG5cXG4uaW5rIHtcXG4gIGRpc3BsYXk6IGJsb2NrO1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgYmFja2dyb3VuZDogcmdiYSgwLCAwLCAwLCAwLjE1KTtcXG4gIGJvcmRlci1yYWRpdXM6IDEwMCU7XFxuICAtd2Via2l0LXRyYW5zZm9ybTogc2NhbGUoMCk7XFxuICAgICAgICAgIHRyYW5zZm9ybTogc2NhbGUoMCk7IH1cXG5cXG4uaW5rLmFuaW1hdGUge1xcbiAgLXdlYmtpdC1hbmltYXRpb246IHJpcHBsZSAuNXMgZWFzZS1pbjtcXG4gICAgICAgICAgYW5pbWF0aW9uOiByaXBwbGUgLjVzIGVhc2UtaW47IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgcmlwcGxlIHtcXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAwO1xcbiAgICAtd2Via2l0LXRyYW5zZm9ybTogc2NhbGUoMi41KTtcXG4gICAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDIuNSk7IH0gfVxcblxcbkBrZXlmcmFtZXMgcmlwcGxlIHtcXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAwO1xcbiAgICAtd2Via2l0LXRyYW5zZm9ybTogc2NhbGUoMi41KTtcXG4gICAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDIuNSk7IH0gfVxcblxcbi5oZWFkTGVmdCB7XFxuICBwYWRkaW5nLXRvcDogNXB4O1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgd2lkdGg6IGNhbGMoMTAwJSAtIDQ5MHB4KTtcXG4gIGhlaWdodDogNzBweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIC5oZWFkTGVmdCA+IC5sb2dvIHtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBib3JkZXItcmFkaXVzOiA1MCU7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBoZWlnaHQ6IDYwcHg7XFxuICAgIHdpZHRoOiA2MHB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2xvZ28ucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgbWFyZ2luLWxlZnQ6IDE1cHg7IH1cXG4gIC5oZWFkTGVmdCA+IC5pbnB1dEZvcm0ge1xcbiAgICBtYXJnaW4tdG9wOiAyMXB4O1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGJvcmRlci1jb2xvcjogI0ZERkRGRDtcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gICAgaGVpZ2h0OiBhdXRvO1xcbiAgICB3aWR0aDogMjAwcHg7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjRkRGREZEOyB9XFxuICAgIC5oZWFkTGVmdCA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgLmhlYWRMZWZ0ID4gLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDE0cHggUm9ib3RvOyB9XFxuICAuaGVhZExlZnQgPiAuc2VsZWN0Qm94IHtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIG1hcmdpbi10b3A6IDIxcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiAyMHB4OyB9XFxuICAgIC5oZWFkTGVmdCA+IC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICBiYWNrZ3JvdW5kOiBub25lO1xcbiAgICAgIHBhZGRpbmc6IDAgNXB4OyB9XFxuICAuaGVhZExlZnQgPiAuYWRkIHtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDIwMHB4O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDIwcHg7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYWRkLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDEwcHg7XFxuICAgIHBhZGRpbmctbGVmdDogMTBweDtcXG4gICAgdGV4dC1hbGlnbjogbGVmdDtcXG4gICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgYm9yZGVyOiAxcHggc29saWQgd2hpdGU7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4xNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMTVzOyB9XFxuICAgIC5oZWFkTGVmdCA+IC5hZGQgPiBwIHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTRweCAvIDM1cHggUm9ib3RvOyB9XFxuXFxuLmhlYWRSaWdodCB7XFxuICBmbG9hdDogcmlnaHQ7XFxuICB3aWR0aDogNDkwcHg7XFxuICBoZWlnaHQ6IDcwcHg7XFxuICBib3JkZXItbGVmdDogMXB4IHNvbGlkIGdyZXk7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgcGFkZGluZy10b3A6IDIycHg7IH1cXG4gIC5oZWFkUmlnaHQgPiAubWFpbCB7XFxuICAgIGhlaWdodDogMjZweDtcXG4gICAgd2lkdGg6IDMzcHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9tYWlsLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAubWFpbCA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxMHB4IC8gMTVweCBSb2JvdG87XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJvdHRvbTogLTNweDtcXG4gICAgICBsZWZ0OiAzMnB4O1xcbiAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLm1haWw6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbWFpbF9zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLmJlbGwge1xcbiAgICBoZWlnaHQ6IDI0cHg7XFxuICAgIHdpZHRoOiAyM3B4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLWxlZnQ6IDMwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmVsbC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIGJvcmRlci1yYWRpdXM6IDE1cHggMCAxNXB4IDEwcHg7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuYmVsbCA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxMHB4IC8gMTVweCBSb2JvdG87XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJvdHRvbTogLTdweDtcXG4gICAgICBsZWZ0OiAyMnB4O1xcbiAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLmJlbGw6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmVsbF9zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzIHtcXG4gICAgaGVpZ2h0OiAyN3B4O1xcbiAgICB3aWR0aDogMjhweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMzBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9zZXJ2aWNlcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXM6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvc2VydmljZXNfc2hhZG93LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjsgfVxcbiAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyNXB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3VzZXJOYW1lLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQ7XFxuICAgIHBhZGRpbmctbGVmdDogMzBweDtcXG4gICAgbWF4LXdpZHRoOiAyNzBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBwIHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTRweCAvIDI3cHggUm9ib3RvO1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICB3aGl0ZS1zcGFjZTogbm93cmFwO1xcbiAgICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgICAgdGV4dC1vdmVyZmxvdzogZWxsaXBzaXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IGRpdiB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gICAgICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB0b3A6IDA7XFxuICAgICAgd2lkdGg6IDEwMCU7XFxuICAgICAgei1pbmRleDogMTsgfVxcbiAgICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBkaXYgPiBwIHtcXG4gICAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICAgIHBhZGRpbmc6IDAgMTVweDtcXG4gICAgICAgIGhlaWdodDogNDhweDtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNnB4IC8gNTBweCBSb2JvdG87XFxuICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBkaXYgPiBwOmhvdmVyIHtcXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI2VlZWVlZTsgfVxcbiAgLmhlYWRSaWdodCA+IC5hdXRoIHtcXG4gICAgY29sb3I6IHdoaXRlO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMjBweDtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyNnB4IFJvYm90bzsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLmF1dGggc3BhbiB7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIG1hcmdpbjogMCAxMHB4OyB9XFxuXFxuLmlucHV0U2VhcmNoIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIG1hcmdpbi10b3A6IDEwcHg7XFxuICBib3JkZXItYm90dG9tOiAxcHggc29saWQgd2hpdGU7IH1cXG4gIC5pbnB1dFNlYXJjaCA+IGlucHV0IHtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBwYWRkaW5nOiAycHggMDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7XFxuICAgIG91dGxpbmU6IG5vbmU7XFxuICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvOyB9XFxuICAuaW5wdXRTZWFyY2ggPiBsYWJlbCB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgdG9wOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiB0ZXh0O1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IC41cztcXG4gICAgdHJhbnNpdGlvbjogLjVzOyB9XFxuXFxuLnNlbGVjdEJveCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gIC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgY29sb3I6ICNmZmZmZmY7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMTlweCBSb2JvdG87XFxuICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCB3aGl0ZTtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDVweDtcXG4gICAgcGFkZGluZy1yaWdodDogMjBweDtcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSBzcGFuIHtcXG4gICAgICBmb250LXNpemU6IDE2cHg7IH1cXG4gIC5zZWxlY3RCb3ggPiAubGlzdE9mVmFsdWVzIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICB0b3A6IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgICB6LWluZGV4OiAxO1xcbiAgICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgICAtd2Via2l0LWFuaW1hdGlvbjogYW5pbWF0ZXRvcCAuMjVzO1xcbiAgICAgICAgICAgIGFuaW1hdGlvbjogYW5pbWF0ZXRvcCAuMjVzOyB9XFxuICAgIC5zZWxlY3RCb3ggPiAubGlzdE9mVmFsdWVzID4gZGl2IHtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgcGFkZGluZzogMCAxNXB4O1xcbiAgICAgIGhlaWdodDogNTBweDtcXG4gICAgICB3aWR0aDogMTIwcHg7XFxuICAgICAgZm9udDogNDAwIDE2cHggLyA1MHB4IFJvYm90bztcXG4gICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgLnNlbGVjdEJveCA+IC5saXN0T2ZWYWx1ZXMgPiBkaXY6aG92ZXIge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI2VlZWVlZTsgfVxcblxcbkAtd2Via2l0LWtleWZyYW1lcyBhbmltYXRldG9wIHtcXG4gIDAlIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDE7IH0gfVxcblxcbkBrZXlmcmFtZXMgYW5pbWF0ZXRvcCB7XFxuICAwJSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG5hc2lkZS5idWxsZXRpbkRldGFpbHMge1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgbWFyZ2luLXRvcDogNXB4OyB9XFxuXFxuc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0IHtcXG4gIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgbWFyZ2luLXJpZ2h0OiAxMHB4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIHdpZHRoOiA3MTVweDtcXG4gIHBhZGRpbmc6IDI1cHggMTAwcHggNDVweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBtYXJnaW4tdG9wOiA1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGgzIHtcXG4gICAgY29sb3I6ICMyMDIwMjA7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjZweCBSb2JvdG87XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucHJpY2Uge1xcbiAgICBjb2xvcjogIzFmMWYxZjtcXG4gICAgZm9udDogNDAwIDE4cHggLyAyNnB4IFJvYm90bztcXG4gICAgZmxvYXQ6IHJpZ2h0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuY2hlY2tCb3gge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMTVweDtcXG4gICAgbWFyZ2luLXRvcDogLTFweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmJyZWFkQ3J1bWJzIHtcXG4gICAgY29sb3I6IHJnYmEoMzEsIDMxLCAzMSwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTRweCBSb2JvdG87XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5pZCB7XFxuICAgIGNvbG9yOiByZ2JhKDMyLCAzMiwgMzIsIDAuNTQpO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE0cHggUm9ib3RvO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGN1cnNvcjogZGVmYXVsdDtcXG4gICAgbWFyZ2luLXRvcDogMTBweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciB7XFxuICAgIGhlaWdodDogMTIwcHg7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBtYXJnaW4tYm90dG9tOiAxMHB4OyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiBkaXYge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAxMHB4O1xcbiAgICAgIGhlaWdodDogMTAwJTtcXG4gICAgICB3aWR0aDogMTY1cHg7IH1cXG4gICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gZGl2Om50aC1vZi10eXBlKDEpIHtcXG4gICAgICAgIG1hcmdpbjogMDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiBkaXYgPiBpbWcge1xcbiAgICAgICAgLW8tb2JqZWN0LWZpdDogY29udGFpbjtcXG4gICAgICAgICAgIG9iamVjdC1maXQ6IGNvbnRhaW47XFxuICAgICAgICB3aWR0aDogMTAwJTtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGNEY0RjQ7XFxuICAgICAgICBoZWlnaHQ6IDEwMCU7XFxuICAgICAgICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IC5uZXh0IHtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9uZXh0LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCBjZW50ZXI7XFxuICAgICAgaGVpZ2h0OiAxNHB4O1xcbiAgICAgIHdpZHRoOiAxMHB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICB0b3A6IDUwJTtcXG4gICAgICBtYXJnaW4tdG9wOiAtN3B4O1xcbiAgICAgIHJpZ2h0OiAtMjVweDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gLnByZXYge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3ByZXYucG5nXCIpICsgXCIpIG5vLXJlcGVhdCByaWdodCBjZW50ZXI7XFxuICAgICAgaGVpZ2h0OiAxNHB4O1xcbiAgICAgIHdpZHRoOiAxMHB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICB0b3A6IDUwJTtcXG4gICAgICBtYXJnaW4tdG9wOiAtN3B4O1xcbiAgICAgIGxlZnQ6IC0yNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCA+ICNtYXBGb3JPbmVBZHZlcnRlcnQge1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgaGVpZ2h0OiAyMzBweDtcXG4gICAgd2lkdGg6IDIyNXB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAyNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCA+IC5kZXNjcmlwdGlvbkFkIHtcXG4gICAgY29sb3I6ICMwYzBjMWU7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMThweCBSb2JvdG87XFxuICAgIG1hcmdpbi10b3A6IDE1cHg7XFxuICAgIG1hcmdpbi1ib3R0b206IDIwcHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0ID4gLmdvVG9NYXAge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogbGVmdDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgPiAuYWxsQ29tbWVudHMge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogNTBweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgPiAud3JpdGVBUmV2aWV3IHtcXG4gICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgZmxvYXQ6IHJpZ2h0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCA+IC5uYW1lVXNlciB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE4cHggUm9ib3RvO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7XFxuICAgIGN1cnNvcjogZGVmYXVsdDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMzVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgPiAub25Pck9mZkxpbmVVc2VyIHtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0IC5idG4tZ3JleSB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTQwcHg7XFxuICAgIG1hcmdpbi1yaWdodDogMjVweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0IC5idG4tYmx1ZSB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucmVudFByb2R1Y3Qge1xcbiAgICBtYXJnaW4tdG9wOiA0NXB4OyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5yZW50UHJvZHVjdCA+IC5yZW50Q2FsZW5kYXIge1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucmVudFByb2R1Y3QgPiAucmVudENhbGVuZGFyID4gcCB7XFxuICAgICAgICBjb2xvcjogIzI2MzIzODtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNnB4IC8gMjFweCBSb2JvdG87XFxuICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnJlbnRQcm9kdWN0ID4gLnJlbnRDYWxlbmRhciA+IHA6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgICAgICBtYXJnaW4tbGVmdDogNTVweDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5yZW50UHJvZHVjdCA+IC5yZW50Q2FsZW5kYXIgPiAuY2FsZW5kYXIge1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICBtYXJnaW4tbGVmdDogNDVweDtcXG4gICAgICAgIHdpZHRoOiAxNjVweDtcXG4gICAgICAgIG1hcmdpbi1ib3R0b206IDA7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnJlbnRQcm9kdWN0ID4gLmlucHV0Rm9ybSB7XFxuICAgICAgY29sb3I6ICM5YTlhOWE7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucmVudFByb2R1Y3QgPiAuYnRuLWJsdWUge1xcbiAgICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgICAgaGVpZ2h0OiAzNXB4O1xcbiAgICAgIHdpZHRoOiAxODBweDtcXG4gICAgICBsaW5lLWhlaWdodDogMzVweDtcXG4gICAgICBmbG9hdDogcmlnaHQ7IH1cXG5cXG4ud3JhcEZvckRpdiB7XFxuICB3aWR0aDogMjY1cHg7XFxuICBmbG9hdDogcmlnaHQ7XFxuICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgbWFyZ2luLWJvdHRvbTogMjVweDtcXG4gIGJvcmRlcjogMXB4IHNvbGlkICNFOUU5RTk7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAud3JhcEZvckRpdiA+IHVsLnRhYiB7XFxuICAgIGxpc3Qtc3R5bGUtdHlwZTogbm9uZTtcXG4gICAgaGVpZ2h0OiA1MHB4O1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNFOUU5RTk7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYSB7XFxuICAgICAgICBjb2xvcjogIzkzOTM5MztcXG4gICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gNTBweCBSb2JvdG87XFxuICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6YWZ0ZXIge1xcbiAgICAgICAgICBjb250ZW50OiAnJztcXG4gICAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgYm90dG9tOiAwO1xcbiAgICAgICAgICB3aWR0aDogMDtcXG4gICAgICAgICAgaGVpZ2h0OiAycHg7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGRDUxNTE7XFxuICAgICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYTpmb2N1cywgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGEuYWN0aXZlIHtcXG4gICAgICAgICAgY29sb3I6ICM3ZWFmZTE7IH1cXG4gICAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6Zm9jdXM6YWZ0ZXIsIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhLmFjdGl2ZTphZnRlciB7XFxuICAgICAgICAgICAgd2lkdGg6IDEwMCU7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDEpID4gYSB7XFxuICAgICAgICB3aWR0aDogMTU5cHg7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGk6bnRoLW9mLXR5cGUoMSkgPiBhOmFmdGVyIHtcXG4gICAgICAgICAgcmlnaHQ6IDA7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDIpID4gYSB7XFxuICAgICAgICB3aWR0aDogMTA0cHg7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGk6bnRoLW9mLXR5cGUoMikgPiBhOmFmdGVyIHtcXG4gICAgICAgICAgbGVmdDogMDsgfVxcbiAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzIHtcXG4gICAgaGVpZ2h0OiAxNzhweDtcXG4gICAgd2lkdGg6IDI4MnB4O1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjRGNEY0O1xcbiAgICBvdmVyZmxvdzogYXV0bztcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAudGFiY29udGVudCB7XFxuICAgICAgZGlzcGxheTogbm9uZTtcXG4gICAgICAtd2Via2l0LWFuaW1hdGlvbjogZmFkZUVmZmVjdCAxcztcXG4gICAgICBhbmltYXRpb246IGZhZGVFZmZlY3QgMXM7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAudGFiY29udGVudC5hY3RpdmUge1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7IH1cXG4gICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3Mge1xcbiAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2IHtcXG4gICAgICAgIHBhZGRpbmc6IDMwcHggMjBweCAyNXB4IDY1cHg7XFxuICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0U5RTlFOTsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXY6bnRoLWxhc3Qtb2YtdHlwZSgxKSB7XFxuICAgICAgICAgIGJvcmRlcjogbm9uZTsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXYgPiBpbWcge1xcbiAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgIGhlaWdodDogMzBweDtcXG4gICAgICAgICAgd2lkdGg6IDI1cHg7XFxuICAgICAgICAgIHRvcDogMzVweDtcXG4gICAgICAgICAgbGVmdDogMjBweDsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXYgPiBwIHtcXG4gICAgICAgICAgY29sb3I6ICMwZDBkMWU7XFxuICAgICAgICAgIGZvbnQ6IDQwMCAxMnB4IC8gMThweCBSb2JvdG87IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgZmFkZUVmZmVjdCB7XFxuICBmcm9tIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgdG8ge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIGZhZGVFZmZlY3Qge1xcbiAgZnJvbSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIHRvIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuLmJ0bi1ibHVlIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMxODc1RDA7XFxuICBjb2xvcjogI2ZmZmZmZjtcXG4gIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87IH1cXG5cXG4ub25Pck9mZkxpbmVVc2VyIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMwMEM2NTI7XFxuICBib3JkZXI6IDFweCBzb2xpZCB3aGl0ZTtcXG4gIGJveC1zaGFkb3c6IDFweCAxcHggM3B4IDBweCByZ2JhKDAsIDAsIDAsIDAuNjUpO1xcbiAgaGVpZ2h0OiAxOHB4O1xcbiAgd2lkdGg6IDE4cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYm9yZGVyLXJhZGl1czogNTAlOyB9XFxuXFxuLmVycm9yLWNvbnRhaW5lciB7XFxuICB3aWR0aDogMTEwM3B4O1xcbiAgbWFyZ2luOiBhdXRvO1xcbiAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9lcnJvcl9iZy5wbmdcIikgKyBcIik7XFxuICBiYWNrZ3JvdW5kLXJlcGVhdDogbm8tcmVwZWF0O1xcbiAgYmFja2dyb3VuZC1wb3NpdGlvbi14OiByaWdodDtcXG4gIGJhY2tncm91bmQtcG9zaXRpb24teTogODBweDtcXG4gIG1pbi1oZWlnaHQ6IDUwMHB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgxIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjYuNHB4IFJvYm90bztcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBtYXJnaW4tdG9wOiA2NXB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgyIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjYuNHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTg5cHg7IH1cXG4gIC5lcnJvci1jb250YWluZXIgaDMge1xcbiAgICBjb2xvcjogcmdiYSgzMSwgMzEsIDMxLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNC40cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAzMHB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyID4gLmJ0bi1ibHVlIHtcXG4gICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuXFxuLmNsaWNrZWQge1xcbiAgYm94LXNoYWRvdzogMHB4IDBweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KSAhaW1wb3J0YW50O1xcbiAgbWFyZ2luLXRvcDogMTJweCAhaW1wb3J0YW50OyB9XFxuXFxuLm11bHRpcGxlIHtcXG4gIHdpZHRoOiAxMTUwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7IH1cXG4gIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCB7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICB3aWR0aDogMzgwcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiA1cHg7XFxuICAgIGhlaWdodDogMTY1cHg7XFxuICAgIHBhZGRpbmc6IDE1cHg7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDNuLTIpIHtcXG4gICAgICBtYXJnaW4tbGVmdDogMDsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQ6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNCQ0U2RTk7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDIpIHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjQkNFNkU5OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgzKSB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0JDRTZFOTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQgPiAuYnVsbGV0aW4tY2VudGVyLWNvbHVtbiB7XFxuICAgICAgZmxvYXQ6IG5vbmU7XFxuICAgICAgbWFyZ2luOiAwO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB0b3A6IDE1cHg7XFxuICAgICAgcmlnaHQ6IDExMHB4OyB9XFxuICAgICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0ID4gLmJ1bGxldGluLWNlbnRlci1jb2x1bW4gPiAuYnVsbGV0aW4tYWN0aW9uIHtcXG4gICAgICAgIGJvdHRvbTogLTQzcHg7XFxuICAgICAgICB3aWR0aDogMTEwcHg7XFxuICAgICAgICByaWdodDogLTMwcHg7XFxuICAgICAgICB6LWluZGV4OiAxOyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCA+IC5idWxsZXRpbi1sZWZ0LWNvbHVtbiA+IC5idWxsZXRpbi1kZXNjcmlwdGlvbiB7XFxuICAgICAgY29sb3I6ICMwYzBjMWU7XFxuICAgICAgZm9udDogNDAwIDEycHggLyAxOHB4IFJvYm90bztcXG4gICAgICB3aWR0aDogMTcwcHg7XFxuICAgICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgICBoZWlnaHQ6IDQwcHg7IH1cXG5cXG4ucmVkIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGNzRBNEE7IH1cXG5cXG4uaW5wdXRGb3JtLXJlcXVpcmVkIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIHBhZGRpbmc6IDZweCAycHg7XFxuICBmb250OiA0MDAgMTZweCAvIDI0LjhweCBSb2JvdG87XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5YjliOWI7XFxuICBtYXJnaW4tdG9wOiAxNXB4OyB9XFxuICAuaW5wdXRGb3JtLXJlcXVpcmVkIGxhYmVsIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBsZWZ0OiAycHg7XFxuICAgIHRvcDogNnB4O1xcbiAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gIC5pbnB1dEZvcm0tcmVxdWlyZWQgaW5wdXQge1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDsgfVxcblxcbi5pbnB1dEZvcm0ucmVxdWlyZWQge1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5YjliOWIgIWltcG9ydGFudDsgfVxcblxcbi5pbnB1dEZvcm0ge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJvcmRlci1ib3R0b206IDFweCBkb3R0ZWQgIzk5OTk5OTsgfVxcbiAgLmlucHV0Rm9ybSBsYWJlbCB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgbGVmdDogNXB4O1xcbiAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRvcDogLTFweDsgfVxcbiAgLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgb3V0bGluZTogbm9uZTtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIHBhZGRpbmc6IDFweCA1cHg7IH1cXG5cXG4vKiBUZXh0IGVsZW1lbnQgYW5pbWF0aW9uICovXFxuLnRleHRPdXQge1xcbiAgdG9wOiAtMTVweCAhaW1wb3J0YW50O1xcbiAgZm9udC1zaXplOiAxMnB4ICFpbXBvcnRhbnQ7XFxuICBsZWZ0OiA1cHggIWltcG9ydGFudDsgfVxcblxcbnNlY3Rpb24ubG9naW4sIHNlY3Rpb24ucmVnaXN0ZXIge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBwYWRkaW5nOiA2NXB4IDIzMHB4O1xcbiAgd2lkdGg6IDExMDBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIHNlY3Rpb24ubG9naW4gPiBoMiwgc2VjdGlvbi5yZWdpc3RlciA+IGgyIHtcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDIycHggUm9ib3RvO1xcbiAgICBtYXJnaW4tYm90dG9tOiA1NXB4OyB9XFxuICBzZWN0aW9uLmxvZ2luID4gLmlucHV0Rm9ybSwgc2VjdGlvbi5yZWdpc3RlciA+IC5pbnB1dEZvcm0ge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuaW5wdXRGb3JtID4gbGFiZWwsIHNlY3Rpb24ubG9naW4gPiAuaW5wdXRGb3JtIGlucHV0LCBzZWN0aW9uLnJlZ2lzdGVyID4gLmlucHV0Rm9ybSA+IGxhYmVsLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgICAgY29sb3I6ICM5YTlhOWE7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvOyB9XFxuICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlIHtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiA0NXB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLnZrLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlLnZrIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjMDE4NkNGIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvdmsucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdCAxMHB4O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAwOyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuYnRuLWJsdWUuZmFjZWJvb2ssIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUuZmFjZWJvb2sge1xcbiAgICAgIGJhY2tncm91bmQ6ICMzRTUwQjMgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9mYWNlYm9vay5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZS5nb29nbGUsIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUuZ29vZ2xlIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjRkQzQzAwIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZ29vZ2xlLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQgMTBweDsgfVxcblxcbnNlY3Rpb24uYnVsbGV0aW5BZGQge1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICB3aWR0aDogMTEwMHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwO1xcbiAgcGFkZGluZzogNjVweCAyMjVweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiBoMiB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMjJweCBSb2JvdG87XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5zZWxlY3RCb3gge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4O1xcbiAgICBtYXJnaW4tdG9wOiAxNXB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgYm9yZGVyLWNvbG9yOiBncmV5O1xcbiAgICAgIG1pbi13aWR0aDogMTk1cHg7XFxuICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgIHBhZGRpbmctbGVmdDogNXB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7XFxuICAgIHdpZHRoOiA0MTBweDtcXG4gICAgcGFkZGluZy1ib3R0b206IDVweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUgPiBpbnB1dCB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHotaW5kZXg6IC0xOyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuZmlsZSA+IHAge1xcbiAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICBjdXJzb3I6IGRlZmF1bHQ7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgcGFkZGluZy10b3A6IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlID4gLmJ0bi1ibHVlIHtcXG4gICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgICBoZWlnaHQ6IDMwcHg7XFxuICAgICAgd2lkdGg6IDg1cHg7XFxuICAgICAgbGluZS1oZWlnaHQ6IDMwcHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuaW5wdXRGb3JtIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmlucHV0Rm9ybSA+IGxhYmVsLCBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgICAgY29sb3I6ICM5OTk5OTk7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvOyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gcCB7XFxuICAgICAgY29sb3I6ICM5OTk5OTk7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBkb3R0ZWQgIzk5OTk5OTtcXG4gICAgICB3aWR0aDogMTYwcHg7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgaGVpZ2h0OiAyMXB4O1xcbiAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdiB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDE5cHg7XFxuICAgICAgbWFyZ2luLXRvcDogM3B4O1xcbiAgICAgIHdpZHRoOiAxNnB4O1xcbiAgICAgIGhlaWdodDogMTVweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gICAgICBvdXRsaW5lOiAxcHggc29saWQgdHJhbnNwYXJlbnQ7XFxuICAgICAgYm9yZGVyOiAxcHggc29saWQgd2hpdGU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnRyYW5zcGFyZW50IHtcXG4gICAgICAgIGJvcmRlci1jb2xvcjogI0U5RTlFOTtcXG4gICAgICAgIG92ZXJmbG93OiBoaWRkZW47IH1cXG4gICAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYudHJhbnNwYXJlbnQgPiAucmVkU3RpY2sge1xcbiAgICAgICAgICAtd2Via2l0LXRyYW5zZm9ybTogcm90YXRlKC00M2RlZyk7XFxuICAgICAgICAgICAgICAgICAgdHJhbnNmb3JtOiByb3RhdGUoLTQzZGVnKTtcXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcmVkO1xcbiAgICAgICAgICBoZWlnaHQ6IDJweDtcXG4gICAgICAgICAgbWFyZ2luLXRvcDogNS41cHg7XFxuICAgICAgICAgIHdpZHRoOiAyMHB4O1xcbiAgICAgICAgICBtYXJnaW4tbGVmdDogLTNweDsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYucmVkIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHJlZDsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYub3JhbmdlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IG9yYW5nZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYueWVsbG93IHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHllbGxvdzsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuZ3JlZW4ge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogZ3JlZW47IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmxpZ2h0Qmx1ZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBsaWdodEJsdWU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmJsdWUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogYmx1ZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYucGluayB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBwaW5rOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5wdXJwbGUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcHVycGxlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi53aGl0ZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gICAgICAgIGJvcmRlci1jb2xvcjogI0U5RTlFOTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuZ3JheSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBncmF5OyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5ibGFjayB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBibGFjazsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuYnJvd24ge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogYnJvd247IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2Lm11bHRpY29sb3Ige1xcbiAgICAgICAgLyogUGVybWFsaW5rIC0gdXNlIHRvIGVkaXQgYW5kIHNoYXJlIHRoaXMgZ3JhZGllbnQ6IGh0dHA6Ly9jb2xvcnppbGxhLmNvbS9ncmFkaWVudC1lZGl0b3IvI2ZmMDAwMCswLGZmZmYwMCsyMCwxZGZmMDArNDAsMDBmZmZmKzYwLDA0MDBmZis4MCxmZjAwZmErMTAwICovXFxuICAgICAgICBiYWNrZ3JvdW5kOiAjZmYwMDAwO1xcbiAgICAgICAgLyogT2xkIGJyb3dzZXJzICovXFxuICAgICAgICAvKiBGRjMuNi0xNSAqL1xcbiAgICAgICAgYmFja2dyb3VuZDogLXdlYmtpdC1saW5lYXItZ3JhZGllbnQobGVmdCwgI2ZmMDAwMCAwJSwgI2ZmZmYwMCAyMCUsICMxZGZmMDAgNDAlLCAjMDBmZmZmIDYwJSwgIzA0MDBmZiA4MCUsICNmZjAwZmEgMTAwJSk7XFxuICAgICAgICAvKiBDaHJvbWUxMC0yNSxTYWZhcmk1LjEtNiAqL1xcbiAgICAgICAgYmFja2dyb3VuZDogbGluZWFyLWdyYWRpZW50KHRvIHJpZ2h0LCAjZmYwMDAwIDAlLCAjZmZmZjAwIDIwJSwgIzFkZmYwMCA0MCUsICMwMGZmZmYgNjAlLCAjMDQwMGZmIDgwJSwgI2ZmMDBmYSAxMDAlKTtcXG4gICAgICAgIC8qIFczQywgSUUxMCssIEZGMTYrLCBDaHJvbWUyNissIE9wZXJhMTIrLCBTYWZhcmk3KyAqL1xcbiAgICAgICAgZmlsdGVyOiBwcm9naWQ6RFhJbWFnZVRyYW5zZm9ybS5NaWNyb3NvZnQuZ3JhZGllbnQoIHN0YXJ0Q29sb3JzdHI9JyNmZjAwMDAnLCBlbmRDb2xvcnN0cj0nI2ZmMDBmYScsR3JhZGllbnRUeXBlPTEgKTtcXG4gICAgICAgIC8qIElFNi05ICovIH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmFjdGl2ZSB7XFxuICAgICAgICBvdXRsaW5lLWNvbG9yOiByZWQ7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY2FsZW5kYXIsIHNlY3Rpb24uYnVsbGV0aW5BZGQgLmFkZENhbGVuZGFyIHtcXG4gICAgd2lkdGg6IDIzMHB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmFkZENhbGVuZGFyIHtcXG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9hZGRDYWxlbmRhci5wbmdcIikgKyBcIik7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJvcmRlci1ib3R0b20tY29sb3I6IGJsdWU7XFxuICAgICAgYmFja2dyb3VuZDogbm9uZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYWRkQ2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlID4gcCB7XFxuICAgICAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgICAgIGZvbnQtc2l6ZTogMTZweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5idG4tYmx1ZSB7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDtcXG4gICAgZGlzcGxheTogYmxvY2s7XFxuICAgIG1hcmdpbjogMCBhdXRvOyB9XFxuXFxuLmVycm9ycyB7XFxuICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICBmb250OiA0MDAgMTJweCAvIDE0cHggUm9ib3RvO1xcbiAgY29sb3I6ICNkZDJjMDA7XFxuICBib3R0b206IC0xN3B4OyB9XFxuXFxubmF2IHtcXG4gIHdpZHRoOiAyNTVweDtcXG4gIGZsb2F0OiBsZWZ0O1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBtYXJnaW4tdG9wOiA1cHg7XFxuICB6LWluZGV4OiAyO1xcbiAgcG9zaXRpb246IGFic29sdXRlOyB9XFxuICBuYXYgPiAubWFwIHtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9tYXAucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgaGVpZ2h0OiAxNDVweDtcXG4gICAgd2lkdGg6IDI1NXB4OyB9XFxuICAgIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtIHtcXG4gICAgICB3aWR0aDogMjE1cHg7XFxuICAgICAgbWFyZ2luOiAxMTVweCBhdXRvIDA7IH1cXG4gICAgICBuYXYgPiAubWFwID4gLmlucHV0Rm9ybS5yZXF1aXJlZCB7XFxuICAgICAgICBib3JkZXItY29sb3I6IGJsYWNrICFpbXBvcnRhbnQ7IH1cXG4gICAgICBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSA+IGlucHV0LCBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSBsYWJlbCwgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0gLnRleHRPdXQge1xcbiAgICAgICAgY29sb3I6IGJsYWNrICFpbXBvcnRhbnQ7IH1cXG4gIG5hdiA+IHVsIHtcXG4gICAgbGlzdC1zdHlsZTogbm9uZTsgfVxcbiAgICBuYXYgPiB1bCA+IGxpID4gcCB7XFxuICAgICAgcGFkZGluZy1sZWZ0OiA3NXB4O1xcbiAgICAgIGNvbG9yOiAjMjYzMjM4O1xcbiAgICAgIGZvbnQ6IDUwMCAxM3B4IC8gNDBweCBSb2JvdG87XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDIwcHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNFNkU2RTYgIWltcG9ydGFudDsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0ZvckNoaWxkcmVuLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDIpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0ZvckFuaW1hbHMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMykge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQnVzaW5lc3MucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNCkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVGhlUHJvcGVydHkucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVHJhbnNwb3J0LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDYpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDcpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1ZvbHVudGVlcmluZy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg4KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Jc0ZyZWUucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoOSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxMCkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvSG9iYmllc0FuZFNwb3J0cy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxMSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQmFydGVyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEyKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuXFxuLm5hdkJ0biB7XFxuICB3aWR0aDogNDNweDtcXG4gIGhlaWdodDogNDlweDtcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIGxlZnQ6IDA7XFxuICB0b3A6IDkwcHg7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3J1cHIucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gIHotaW5kZXg6IDE7IH1cXG4gIC5uYXZCdG46aG92ZXIge1xcbiAgICB3aWR0aDogNjBweDtcXG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9ydXBySG92ZXIucG5nXCIpICsgXCIpOyB9XFxuXFxuLmNvdmVyIHtcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIHRvcDogMDtcXG4gIHJpZ2h0OiAwO1xcbiAgYm90dG9tOiAwO1xcbiAgbGVmdDogMDtcXG4gIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMCwgMCwgMCwgMC45KTtcXG4gIHotaW5kZXg6IDU7IH1cXG4gIC5jb3Zlci5oaWRlIHtcXG4gICAgLXdlYmtpdC1hbmltYXRpb246IGhpZGUgMXM7XFxuICAgICAgICAgICAgYW5pbWF0aW9uOiBoaWRlIDFzOyB9XFxuXFxuQC13ZWJraXQta2V5ZnJhbWVzIGhpZGUge1xcbiAgMTAwJSB7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50OyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIGhpZGUge1xcbiAgMTAwJSB7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50OyB9IH1cXG5cXG5zZWN0aW9uLm1haWwge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICB3aWR0aDogMTEwNXB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwO1xcbiAgcGFkZGluZzogNDVweCAwIDgwcHg7IH1cXG4gIHNlY3Rpb24ubWFpbCA+IC50YWIge1xcbiAgICB3aWR0aDogOTA1cHg7XFxuICAgIGJvcmRlcjogMXB4IHNvbGlkICNFOUU5RTk7XFxuICAgIGJvcmRlci1ib3R0b206IG5vbmU7XFxuICAgIG1hcmdpbjogMCBhdXRvO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjRGNEY0OyB9XFxuICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiB1bCB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gICAgICBsaXN0LXN0eWxlOiBub25lO1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjRTlFOUU5O1xcbiAgICAgIGhlaWdodDogNTBweDtcXG4gICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IHVsID4gbGkge1xcbiAgICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgICBmb250OiA0MDAgMTRweCAvIDUwcHggUm9ib3RvO1xcbiAgICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiB1bCA+IGxpOmFmdGVyIHtcXG4gICAgICAgICAgY29udGVudDogJyc7XFxuICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgYm90dG9tOiAwO1xcbiAgICAgICAgICBsZWZ0OiA1MCU7XFxuICAgICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgICBoZWlnaHQ6IDJweDtcXG4gICAgICAgICAgd2lkdGg6IDA7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGRDUxNTE7XFxuICAgICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gdWwgPiBsaS5hY3RpdmUge1xcbiAgICAgICAgICBjb2xvcjogIzdlYWZlMTsgfVxcbiAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gdWwgPiBsaS5hY3RpdmU6YWZ0ZXIge1xcbiAgICAgICAgICAgIHdpZHRoOiAxMDAlO1xcbiAgICAgICAgICAgIGxlZnQ6IDA7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICAgICAgd2lkdGg6IDExMHB4OyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gdWwgPiBsaTpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgICAgIHdpZHRoOiAyMTVweDsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMykge1xcbiAgICAgICAgICBwYWRkaW5nOiAwIDE1cHg7IH1cXG4gICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhdmxlVGl0bGUge1xcbiAgICAgIGhlaWdodDogNjVweDtcXG4gICAgICBwYWRkaW5nLXRvcDogMzVweDtcXG4gICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOTk5OTk5OyB9XFxuICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhdmxlVGl0bGUgPiBwIHtcXG4gICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgZm9udDogNDAwIDE0cHggLyAyMHB4IFJvYm90bztcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgbWFyZ2luLWxlZnQ6IDMwcHg7XFxuICAgICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YXZsZVRpdGxlID4gcDpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgICAgIG1hcmdpbi1sZWZ0OiA0NXB4OyB9XFxuICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhdmxlVGl0bGUgPiAuc2VsZWN0Qm94IHtcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgbWFyZ2luLWxlZnQ6IDQ1cHg7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YXZsZVRpdGxlID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgICAgICBib3JkZXItY29sb3I6ICM5OTk5OTk7XFxuICAgICAgICAgIHdpZHRoOiAxOTVweDsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhdmxlVGl0bGUgPiAuc2VsZWN0Qm94Om50aC1vZi10eXBlKDIpIHtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyIHtcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzk5OTk5OTtcXG4gICAgICBwYWRkaW5nOiAxMHB4IDA7XFxuICAgICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyOmJlZm9yZSwgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyOmFmdGVyIHtcXG4gICAgICAgIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gICAgICAgIGRpc3BsYXk6IHRhYmxlOyB9XFxuICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyOmFmdGVyIHtcXG4gICAgICAgIGNsZWFyOiBib3RoOyB9XFxuICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyOm50aC1vZi10eXBlKDEpID4gLnRkIHtcXG4gICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgZm9udDogNDAwIDEzcHggLyAyN3B4IFJvYm90bztcXG4gICAgICAgIHRleHQtYWxpZ246IGxlZnQ7XFxuICAgICAgICBjdXJzb3I6IGRlZmF1bHQgIWltcG9ydGFudDsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyOm50aC1vZi10eXBlKDEpID4gLnRkID4gLmNoZWNrQm94IHtcXG4gICAgICAgICAgcG9zaXRpb246IHJlbGF0aXZlICFpbXBvcnRhbnQ7XFxuICAgICAgICAgIG1hcmdpbi10b3A6IDAgIWltcG9ydGFudDtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IDMwcHg7XFxuICAgICAgICAgIGxlZnQ6IDAgIWltcG9ydGFudDsgfVxcbiAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50ciA+IC50ZCB7XFxuICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgIGNvbG9yOiAjNjY2NjY2O1xcbiAgICAgICAgZm9udDogNDAwIDE0cHggLyAxOHB4IFJvYm90bzsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICAgICAgd2lkdGg6IDgwcHg7XFxuICAgICAgICAgIG1pbi1oZWlnaHQ6IDFweDsgfVxcbiAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoMSkgPiAuY2hlY2tCb3gge1xcbiAgICAgICAgICAgIGxlZnQ6IDMwcHg7XFxuICAgICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICAgIHRvcDogNTAlO1xcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IC0xM3B4OyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgICAgICB3aWR0aDogMTQ1cHg7XFxuICAgICAgICAgIG1pbi1oZWlnaHQ6IDFweDsgfVxcbiAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoMikgPiAudHJVc2VyIHtcXG4gICAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgICAgdG9wOiA1MCU7XFxuICAgICAgICAgICAgbWFyZ2luLXRvcDogLTlweDsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDMpIHtcXG4gICAgICAgICAgd2lkdGg6IDMxMHB4O1xcbiAgICAgICAgICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDMpID4gLnByZXZpZXcge1xcbiAgICAgICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgICAgIGZvbnQ6IDQwMCAxNHB4IFJvYm90bztcXG4gICAgICAgICAgICB3aWR0aDogMzEwcHg7XFxuICAgICAgICAgICAgd2hpdGUtc3BhY2U6IG5vd3JhcDtcXG4gICAgICAgICAgICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgICAgICAgICAgIHRleHQtb3ZlcmZsb3c6IGVsbGlwc2lzO1xcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IDEwcHg7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50ciA+IC50ZDpudGgtb2YtdHlwZSg0KSB7XFxuICAgICAgICAgIHdpZHRoOiAxMDVweDtcXG4gICAgICAgICAgbWluLWhlaWdodDogMXB4OyB9XFxuICAgICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50ciA+IC50ZDpudGgtb2YtdHlwZSg0KSA+IGltZyB7XFxuICAgICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICAgIHRvcDogNTAlO1xcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IC03cHg7XFxuICAgICAgICAgICAgbGVmdDogNTU1cHg7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50ciA+IC50ZDpudGgtb2YtdHlwZSg1KSB7XFxuICAgICAgICAgIHdpZHRoOiAxNTBweDtcXG4gICAgICAgICAgbWluLWhlaWdodDogMXB4OyB9XFxuICAgICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50ciA+IC50ZDpudGgtb2YtdHlwZSg1KSA+IC5zdGF0dXMge1xcbiAgICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgICB0b3A6IDUwJTtcXG4gICAgICAgICAgICBtYXJnaW4tdG9wOiAtOXB4OyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoNikge1xcbiAgICAgICAgICBtaW4taGVpZ2h0OiAxcHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDYpID4gLmRhdGUge1xcbiAgICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgICB0b3A6IDUwJTtcXG4gICAgICAgICAgICBtYXJnaW4tdG9wOiAtOXB4O1xcbiAgICAgICAgICAgIGxlZnQ6IDc5MHB4OyB9XFxuXCIsIFwiXCJdKTtcblxuLy8gZXhwb3J0c1xuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vY3NzLWxvYWRlciEuL34vcG9zdGNzcy1sb2FkZXIhLi9+L3Nhc3MtbG9hZGVyIS4vc3R5bGVzL2Jhc2ljLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSAyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvKlxyXG5cdE1JVCBMaWNlbnNlIGh0dHA6Ly93d3cub3BlbnNvdXJjZS5vcmcvbGljZW5zZXMvbWl0LWxpY2Vuc2UucGhwXHJcblx0QXV0aG9yIFRvYmlhcyBLb3BwZXJzIEBzb2tyYVxyXG4qL1xyXG4vLyBjc3MgYmFzZSBjb2RlLCBpbmplY3RlZCBieSB0aGUgY3NzLWxvYWRlclxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG5cdHZhciBsaXN0ID0gW107XHJcblxyXG5cdC8vIHJldHVybiB0aGUgbGlzdCBvZiBtb2R1bGVzIGFzIGNzcyBzdHJpbmdcclxuXHRsaXN0LnRvU3RyaW5nID0gZnVuY3Rpb24gdG9TdHJpbmcoKSB7XHJcblx0XHR2YXIgcmVzdWx0ID0gW107XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgdGhpcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaXRlbSA9IHRoaXNbaV07XHJcblx0XHRcdGlmKGl0ZW1bMl0pIHtcclxuXHRcdFx0XHRyZXN1bHQucHVzaChcIkBtZWRpYSBcIiArIGl0ZW1bMl0gKyBcIntcIiArIGl0ZW1bMV0gKyBcIn1cIik7XHJcblx0XHRcdH0gZWxzZSB7XHJcblx0XHRcdFx0cmVzdWx0LnB1c2goaXRlbVsxXSk7XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHRcdHJldHVybiByZXN1bHQuam9pbihcIlwiKTtcclxuXHR9O1xyXG5cclxuXHQvLyBpbXBvcnQgYSBsaXN0IG9mIG1vZHVsZXMgaW50byB0aGUgbGlzdFxyXG5cdGxpc3QuaSA9IGZ1bmN0aW9uKG1vZHVsZXMsIG1lZGlhUXVlcnkpIHtcclxuXHRcdGlmKHR5cGVvZiBtb2R1bGVzID09PSBcInN0cmluZ1wiKVxyXG5cdFx0XHRtb2R1bGVzID0gW1tudWxsLCBtb2R1bGVzLCBcIlwiXV07XHJcblx0XHR2YXIgYWxyZWFkeUltcG9ydGVkTW9kdWxlcyA9IHt9O1xyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IHRoaXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGlkID0gdGhpc1tpXVswXTtcclxuXHRcdFx0aWYodHlwZW9mIGlkID09PSBcIm51bWJlclwiKVxyXG5cdFx0XHRcdGFscmVhZHlJbXBvcnRlZE1vZHVsZXNbaWRdID0gdHJ1ZTtcclxuXHRcdH1cclxuXHRcdGZvcihpID0gMDsgaSA8IG1vZHVsZXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGl0ZW0gPSBtb2R1bGVzW2ldO1xyXG5cdFx0XHQvLyBza2lwIGFscmVhZHkgaW1wb3J0ZWQgbW9kdWxlXHJcblx0XHRcdC8vIHRoaXMgaW1wbGVtZW50YXRpb24gaXMgbm90IDEwMCUgcGVyZmVjdCBmb3Igd2VpcmQgbWVkaWEgcXVlcnkgY29tYmluYXRpb25zXHJcblx0XHRcdC8vICB3aGVuIGEgbW9kdWxlIGlzIGltcG9ydGVkIG11bHRpcGxlIHRpbWVzIHdpdGggZGlmZmVyZW50IG1lZGlhIHF1ZXJpZXMuXHJcblx0XHRcdC8vICBJIGhvcGUgdGhpcyB3aWxsIG5ldmVyIG9jY3VyIChIZXkgdGhpcyB3YXkgd2UgaGF2ZSBzbWFsbGVyIGJ1bmRsZXMpXHJcblx0XHRcdGlmKHR5cGVvZiBpdGVtWzBdICE9PSBcIm51bWJlclwiIHx8ICFhbHJlYWR5SW1wb3J0ZWRNb2R1bGVzW2l0ZW1bMF1dKSB7XHJcblx0XHRcdFx0aWYobWVkaWFRdWVyeSAmJiAhaXRlbVsyXSkge1xyXG5cdFx0XHRcdFx0aXRlbVsyXSA9IG1lZGlhUXVlcnk7XHJcblx0XHRcdFx0fSBlbHNlIGlmKG1lZGlhUXVlcnkpIHtcclxuXHRcdFx0XHRcdGl0ZW1bMl0gPSBcIihcIiArIGl0ZW1bMl0gKyBcIikgYW5kIChcIiArIG1lZGlhUXVlcnkgKyBcIilcIjtcclxuXHRcdFx0XHR9XHJcblx0XHRcdFx0bGlzdC5wdXNoKGl0ZW0pO1xyXG5cdFx0XHR9XHJcblx0XHR9XHJcblx0fTtcclxuXHRyZXR1cm4gbGlzdDtcclxufTtcclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhbGVuZGFyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvY2FsZW5kYXIucG5nXG4gKiogbW9kdWxlIGlkID0gNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9sb2dvLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbG9nby5wbmdcbiAqKiBtb2R1bGUgaWQgPSA2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYWRkLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYWRkLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9tYWlsLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbWFpbC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvbWFpbF9zaGFkb3cucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9tYWlsX3NoYWRvdy5wbmdcbiAqKiBtb2R1bGUgaWQgPSA5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYmVsbC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2JlbGwucG5nXG4gKiogbW9kdWxlIGlkID0gMTBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9iZWxsX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDExXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvc2VydmljZXMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9zZXJ2aWNlcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3NlcnZpY2VzX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3NlcnZpY2VzX3NoYWRvdy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxM1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3VzZXJOYW1lLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdXNlck5hbWUucG5nXG4gKiogbW9kdWxlIGlkID0gMTRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9uZXh0LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbmV4dC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3ByZXYucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9wcmV2LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZXJyb3JfYmcucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9lcnJvcl9iZy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3ZrLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdmsucG5nXG4gKiogbW9kdWxlIGlkID0gMThcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9mYWNlYm9vay5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2ZhY2Vib29rLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZ29vZ2xlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZ29vZ2xlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYWRkQ2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hZGRDYWxlbmRhci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL21hcC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL21hcC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0UmlnaHROYXYucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvRm9yQ2hpbGRyZW4ucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Gb3JDaGlsZHJlbi5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0ZvckFuaW1hbHMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Gb3JBbmltYWxzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQnVzaW5lc3MucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9CdXNpbmVzcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyNlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RoZVByb3BlcnR5LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVGhlUHJvcGVydHkucG5nXG4gKiogbW9kdWxlIGlkID0gMjdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9UcmFuc3BvcnQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9UcmFuc3BvcnQucG5nXG4gKiogbW9kdWxlIGlkID0gMjhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9DbG90aGluZ0FuZENvc21ldGljcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvSXNGcmVlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvSXNGcmVlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0FIb3VzZUFuZEFHYXJkZW4ucG5nXG4gKiogbW9kdWxlIGlkID0gMzJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Ib2JiaWVzQW5kU3BvcnRzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvSG9iYmllc0FuZFNwb3J0cy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzM1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0JhcnRlci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0JhcnRlci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3J1cHIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9ydXByLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvcnVwckhvdmVyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvcnVwckhvdmVyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvKlxyXG5cdE1JVCBMaWNlbnNlIGh0dHA6Ly93d3cub3BlbnNvdXJjZS5vcmcvbGljZW5zZXMvbWl0LWxpY2Vuc2UucGhwXHJcblx0QXV0aG9yIFRvYmlhcyBLb3BwZXJzIEBzb2tyYVxyXG4qL1xyXG52YXIgc3R5bGVzSW5Eb20gPSB7fSxcclxuXHRtZW1vaXplID0gZnVuY3Rpb24oZm4pIHtcclxuXHRcdHZhciBtZW1vO1xyXG5cdFx0cmV0dXJuIGZ1bmN0aW9uICgpIHtcclxuXHRcdFx0aWYgKHR5cGVvZiBtZW1vID09PSBcInVuZGVmaW5lZFwiKSBtZW1vID0gZm4uYXBwbHkodGhpcywgYXJndW1lbnRzKTtcclxuXHRcdFx0cmV0dXJuIG1lbW87XHJcblx0XHR9O1xyXG5cdH0sXHJcblx0aXNPbGRJRSA9IG1lbW9pemUoZnVuY3Rpb24oKSB7XHJcblx0XHRyZXR1cm4gL21zaWUgWzYtOV1cXGIvLnRlc3Qod2luZG93Lm5hdmlnYXRvci51c2VyQWdlbnQudG9Mb3dlckNhc2UoKSk7XHJcblx0fSksXHJcblx0Z2V0SGVhZEVsZW1lbnQgPSBtZW1vaXplKGZ1bmN0aW9uICgpIHtcclxuXHRcdHJldHVybiBkb2N1bWVudC5oZWFkIHx8IGRvY3VtZW50LmdldEVsZW1lbnRzQnlUYWdOYW1lKFwiaGVhZFwiKVswXTtcclxuXHR9KSxcclxuXHRzaW5nbGV0b25FbGVtZW50ID0gbnVsbCxcclxuXHRzaW5nbGV0b25Db3VudGVyID0gMCxcclxuXHRzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcCA9IFtdO1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbihsaXN0LCBvcHRpb25zKSB7XHJcblx0aWYodHlwZW9mIERFQlVHICE9PSBcInVuZGVmaW5lZFwiICYmIERFQlVHKSB7XHJcblx0XHRpZih0eXBlb2YgZG9jdW1lbnQgIT09IFwib2JqZWN0XCIpIHRocm93IG5ldyBFcnJvcihcIlRoZSBzdHlsZS1sb2FkZXIgY2Fubm90IGJlIHVzZWQgaW4gYSBub24tYnJvd3NlciBlbnZpcm9ubWVudFwiKTtcclxuXHR9XHJcblxyXG5cdG9wdGlvbnMgPSBvcHRpb25zIHx8IHt9O1xyXG5cdC8vIEZvcmNlIHNpbmdsZS10YWcgc29sdXRpb24gb24gSUU2LTksIHdoaWNoIGhhcyBhIGhhcmQgbGltaXQgb24gdGhlICMgb2YgPHN0eWxlPlxyXG5cdC8vIHRhZ3MgaXQgd2lsbCBhbGxvdyBvbiBhIHBhZ2VcclxuXHRpZiAodHlwZW9mIG9wdGlvbnMuc2luZ2xldG9uID09PSBcInVuZGVmaW5lZFwiKSBvcHRpb25zLnNpbmdsZXRvbiA9IGlzT2xkSUUoKTtcclxuXHJcblx0Ly8gQnkgZGVmYXVsdCwgYWRkIDxzdHlsZT4gdGFncyB0byB0aGUgYm90dG9tIG9mIDxoZWFkPi5cclxuXHRpZiAodHlwZW9mIG9wdGlvbnMuaW5zZXJ0QXQgPT09IFwidW5kZWZpbmVkXCIpIG9wdGlvbnMuaW5zZXJ0QXQgPSBcImJvdHRvbVwiO1xyXG5cclxuXHR2YXIgc3R5bGVzID0gbGlzdFRvU3R5bGVzKGxpc3QpO1xyXG5cdGFkZFN0eWxlc1RvRG9tKHN0eWxlcywgb3B0aW9ucyk7XHJcblxyXG5cdHJldHVybiBmdW5jdGlvbiB1cGRhdGUobmV3TGlzdCkge1xyXG5cdFx0dmFyIG1heVJlbW92ZSA9IFtdO1xyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IHN0eWxlcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaXRlbSA9IHN0eWxlc1tpXTtcclxuXHRcdFx0dmFyIGRvbVN0eWxlID0gc3R5bGVzSW5Eb21baXRlbS5pZF07XHJcblx0XHRcdGRvbVN0eWxlLnJlZnMtLTtcclxuXHRcdFx0bWF5UmVtb3ZlLnB1c2goZG9tU3R5bGUpO1xyXG5cdFx0fVxyXG5cdFx0aWYobmV3TGlzdCkge1xyXG5cdFx0XHR2YXIgbmV3U3R5bGVzID0gbGlzdFRvU3R5bGVzKG5ld0xpc3QpO1xyXG5cdFx0XHRhZGRTdHlsZXNUb0RvbShuZXdTdHlsZXMsIG9wdGlvbnMpO1xyXG5cdFx0fVxyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IG1heVJlbW92ZS5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgZG9tU3R5bGUgPSBtYXlSZW1vdmVbaV07XHJcblx0XHRcdGlmKGRvbVN0eWxlLnJlZnMgPT09IDApIHtcclxuXHRcdFx0XHRmb3IodmFyIGogPSAwOyBqIDwgZG9tU3R5bGUucGFydHMubGVuZ3RoOyBqKyspXHJcblx0XHRcdFx0XHRkb21TdHlsZS5wYXJ0c1tqXSgpO1xyXG5cdFx0XHRcdGRlbGV0ZSBzdHlsZXNJbkRvbVtkb21TdHlsZS5pZF07XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHR9O1xyXG59XHJcblxyXG5mdW5jdGlvbiBhZGRTdHlsZXNUb0RvbShzdHlsZXMsIG9wdGlvbnMpIHtcclxuXHRmb3IodmFyIGkgPSAwOyBpIDwgc3R5bGVzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHR2YXIgaXRlbSA9IHN0eWxlc1tpXTtcclxuXHRcdHZhciBkb21TdHlsZSA9IHN0eWxlc0luRG9tW2l0ZW0uaWRdO1xyXG5cdFx0aWYoZG9tU3R5bGUpIHtcclxuXHRcdFx0ZG9tU3R5bGUucmVmcysrO1xyXG5cdFx0XHRmb3IodmFyIGogPSAwOyBqIDwgZG9tU3R5bGUucGFydHMubGVuZ3RoOyBqKyspIHtcclxuXHRcdFx0XHRkb21TdHlsZS5wYXJ0c1tqXShpdGVtLnBhcnRzW2pdKTtcclxuXHRcdFx0fVxyXG5cdFx0XHRmb3IoOyBqIDwgaXRlbS5wYXJ0cy5sZW5ndGg7IGorKykge1xyXG5cdFx0XHRcdGRvbVN0eWxlLnBhcnRzLnB1c2goYWRkU3R5bGUoaXRlbS5wYXJ0c1tqXSwgb3B0aW9ucykpO1xyXG5cdFx0XHR9XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHR2YXIgcGFydHMgPSBbXTtcclxuXHRcdFx0Zm9yKHZhciBqID0gMDsgaiA8IGl0ZW0ucGFydHMubGVuZ3RoOyBqKyspIHtcclxuXHRcdFx0XHRwYXJ0cy5wdXNoKGFkZFN0eWxlKGl0ZW0ucGFydHNbal0sIG9wdGlvbnMpKTtcclxuXHRcdFx0fVxyXG5cdFx0XHRzdHlsZXNJbkRvbVtpdGVtLmlkXSA9IHtpZDogaXRlbS5pZCwgcmVmczogMSwgcGFydHM6IHBhcnRzfTtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGxpc3RUb1N0eWxlcyhsaXN0KSB7XHJcblx0dmFyIHN0eWxlcyA9IFtdO1xyXG5cdHZhciBuZXdTdHlsZXMgPSB7fTtcclxuXHRmb3IodmFyIGkgPSAwOyBpIDwgbGlzdC5sZW5ndGg7IGkrKykge1xyXG5cdFx0dmFyIGl0ZW0gPSBsaXN0W2ldO1xyXG5cdFx0dmFyIGlkID0gaXRlbVswXTtcclxuXHRcdHZhciBjc3MgPSBpdGVtWzFdO1xyXG5cdFx0dmFyIG1lZGlhID0gaXRlbVsyXTtcclxuXHRcdHZhciBzb3VyY2VNYXAgPSBpdGVtWzNdO1xyXG5cdFx0dmFyIHBhcnQgPSB7Y3NzOiBjc3MsIG1lZGlhOiBtZWRpYSwgc291cmNlTWFwOiBzb3VyY2VNYXB9O1xyXG5cdFx0aWYoIW5ld1N0eWxlc1tpZF0pXHJcblx0XHRcdHN0eWxlcy5wdXNoKG5ld1N0eWxlc1tpZF0gPSB7aWQ6IGlkLCBwYXJ0czogW3BhcnRdfSk7XHJcblx0XHRlbHNlXHJcblx0XHRcdG5ld1N0eWxlc1tpZF0ucGFydHMucHVzaChwYXJ0KTtcclxuXHR9XHJcblx0cmV0dXJuIHN0eWxlcztcclxufVxyXG5cclxuZnVuY3Rpb24gaW5zZXJ0U3R5bGVFbGVtZW50KG9wdGlvbnMsIHN0eWxlRWxlbWVudCkge1xyXG5cdHZhciBoZWFkID0gZ2V0SGVhZEVsZW1lbnQoKTtcclxuXHR2YXIgbGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3AgPSBzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcFtzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5sZW5ndGggLSAxXTtcclxuXHRpZiAob3B0aW9ucy5pbnNlcnRBdCA9PT0gXCJ0b3BcIikge1xyXG5cdFx0aWYoIWxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wKSB7XHJcblx0XHRcdGhlYWQuaW5zZXJ0QmVmb3JlKHN0eWxlRWxlbWVudCwgaGVhZC5maXJzdENoaWxkKTtcclxuXHRcdH0gZWxzZSBpZihsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcC5uZXh0U2libGluZykge1xyXG5cdFx0XHRoZWFkLmluc2VydEJlZm9yZShzdHlsZUVsZW1lbnQsIGxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wLm5leHRTaWJsaW5nKTtcclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdGhlYWQuYXBwZW5kQ2hpbGQoc3R5bGVFbGVtZW50KTtcclxuXHRcdH1cclxuXHRcdHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLnB1c2goc3R5bGVFbGVtZW50KTtcclxuXHR9IGVsc2UgaWYgKG9wdGlvbnMuaW5zZXJ0QXQgPT09IFwiYm90dG9tXCIpIHtcclxuXHRcdGhlYWQuYXBwZW5kQ2hpbGQoc3R5bGVFbGVtZW50KTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0dGhyb3cgbmV3IEVycm9yKFwiSW52YWxpZCB2YWx1ZSBmb3IgcGFyYW1ldGVyICdpbnNlcnRBdCcuIE11c3QgYmUgJ3RvcCcgb3IgJ2JvdHRvbScuXCIpO1xyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gcmVtb3ZlU3R5bGVFbGVtZW50KHN0eWxlRWxlbWVudCkge1xyXG5cdHN0eWxlRWxlbWVudC5wYXJlbnROb2RlLnJlbW92ZUNoaWxkKHN0eWxlRWxlbWVudCk7XHJcblx0dmFyIGlkeCA9IHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLmluZGV4T2Yoc3R5bGVFbGVtZW50KTtcclxuXHRpZihpZHggPj0gMCkge1xyXG5cdFx0c3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3Auc3BsaWNlKGlkeCwgMSk7XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiBjcmVhdGVTdHlsZUVsZW1lbnQob3B0aW9ucykge1xyXG5cdHZhciBzdHlsZUVsZW1lbnQgPSBkb2N1bWVudC5jcmVhdGVFbGVtZW50KFwic3R5bGVcIik7XHJcblx0c3R5bGVFbGVtZW50LnR5cGUgPSBcInRleHQvY3NzXCI7XHJcblx0aW5zZXJ0U3R5bGVFbGVtZW50KG9wdGlvbnMsIHN0eWxlRWxlbWVudCk7XHJcblx0cmV0dXJuIHN0eWxlRWxlbWVudDtcclxufVxyXG5cclxuZnVuY3Rpb24gY3JlYXRlTGlua0VsZW1lbnQob3B0aW9ucykge1xyXG5cdHZhciBsaW5rRWxlbWVudCA9IGRvY3VtZW50LmNyZWF0ZUVsZW1lbnQoXCJsaW5rXCIpO1xyXG5cdGxpbmtFbGVtZW50LnJlbCA9IFwic3R5bGVzaGVldFwiO1xyXG5cdGluc2VydFN0eWxlRWxlbWVudChvcHRpb25zLCBsaW5rRWxlbWVudCk7XHJcblx0cmV0dXJuIGxpbmtFbGVtZW50O1xyXG59XHJcblxyXG5mdW5jdGlvbiBhZGRTdHlsZShvYmosIG9wdGlvbnMpIHtcclxuXHR2YXIgc3R5bGVFbGVtZW50LCB1cGRhdGUsIHJlbW92ZTtcclxuXHJcblx0aWYgKG9wdGlvbnMuc2luZ2xldG9uKSB7XHJcblx0XHR2YXIgc3R5bGVJbmRleCA9IHNpbmdsZXRvbkNvdW50ZXIrKztcclxuXHRcdHN0eWxlRWxlbWVudCA9IHNpbmdsZXRvbkVsZW1lbnQgfHwgKHNpbmdsZXRvbkVsZW1lbnQgPSBjcmVhdGVTdHlsZUVsZW1lbnQob3B0aW9ucykpO1xyXG5cdFx0dXBkYXRlID0gYXBwbHlUb1NpbmdsZXRvblRhZy5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCwgc3R5bGVJbmRleCwgZmFsc2UpO1xyXG5cdFx0cmVtb3ZlID0gYXBwbHlUb1NpbmdsZXRvblRhZy5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCwgc3R5bGVJbmRleCwgdHJ1ZSk7XHJcblx0fSBlbHNlIGlmKG9iai5zb3VyY2VNYXAgJiZcclxuXHRcdHR5cGVvZiBVUkwgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIFVSTC5jcmVhdGVPYmplY3RVUkwgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIFVSTC5yZXZva2VPYmplY3RVUkwgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIEJsb2IgPT09IFwiZnVuY3Rpb25cIiAmJlxyXG5cdFx0dHlwZW9mIGJ0b2EgPT09IFwiZnVuY3Rpb25cIikge1xyXG5cdFx0c3R5bGVFbGVtZW50ID0gY3JlYXRlTGlua0VsZW1lbnQob3B0aW9ucyk7XHJcblx0XHR1cGRhdGUgPSB1cGRhdGVMaW5rLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50KTtcclxuXHRcdHJlbW92ZSA9IGZ1bmN0aW9uKCkge1xyXG5cdFx0XHRyZW1vdmVTdHlsZUVsZW1lbnQoc3R5bGVFbGVtZW50KTtcclxuXHRcdFx0aWYoc3R5bGVFbGVtZW50LmhyZWYpXHJcblx0XHRcdFx0VVJMLnJldm9rZU9iamVjdFVSTChzdHlsZUVsZW1lbnQuaHJlZik7XHJcblx0XHR9O1xyXG5cdH0gZWxzZSB7XHJcblx0XHRzdHlsZUVsZW1lbnQgPSBjcmVhdGVTdHlsZUVsZW1lbnQob3B0aW9ucyk7XHJcblx0XHR1cGRhdGUgPSBhcHBseVRvVGFnLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50KTtcclxuXHRcdHJlbW92ZSA9IGZ1bmN0aW9uKCkge1xyXG5cdFx0XHRyZW1vdmVTdHlsZUVsZW1lbnQoc3R5bGVFbGVtZW50KTtcclxuXHRcdH07XHJcblx0fVxyXG5cclxuXHR1cGRhdGUob2JqKTtcclxuXHJcblx0cmV0dXJuIGZ1bmN0aW9uIHVwZGF0ZVN0eWxlKG5ld09iaikge1xyXG5cdFx0aWYobmV3T2JqKSB7XHJcblx0XHRcdGlmKG5ld09iai5jc3MgPT09IG9iai5jc3MgJiYgbmV3T2JqLm1lZGlhID09PSBvYmoubWVkaWEgJiYgbmV3T2JqLnNvdXJjZU1hcCA9PT0gb2JqLnNvdXJjZU1hcClcclxuXHRcdFx0XHRyZXR1cm47XHJcblx0XHRcdHVwZGF0ZShvYmogPSBuZXdPYmopO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0cmVtb3ZlKCk7XHJcblx0XHR9XHJcblx0fTtcclxufVxyXG5cclxudmFyIHJlcGxhY2VUZXh0ID0gKGZ1bmN0aW9uICgpIHtcclxuXHR2YXIgdGV4dFN0b3JlID0gW107XHJcblxyXG5cdHJldHVybiBmdW5jdGlvbiAoaW5kZXgsIHJlcGxhY2VtZW50KSB7XHJcblx0XHR0ZXh0U3RvcmVbaW5kZXhdID0gcmVwbGFjZW1lbnQ7XHJcblx0XHRyZXR1cm4gdGV4dFN0b3JlLmZpbHRlcihCb29sZWFuKS5qb2luKCdcXG4nKTtcclxuXHR9O1xyXG59KSgpO1xyXG5cclxuZnVuY3Rpb24gYXBwbHlUb1NpbmdsZXRvblRhZyhzdHlsZUVsZW1lbnQsIGluZGV4LCByZW1vdmUsIG9iaikge1xyXG5cdHZhciBjc3MgPSByZW1vdmUgPyBcIlwiIDogb2JqLmNzcztcclxuXHJcblx0aWYgKHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0KSB7XHJcblx0XHRzdHlsZUVsZW1lbnQuc3R5bGVTaGVldC5jc3NUZXh0ID0gcmVwbGFjZVRleHQoaW5kZXgsIGNzcyk7XHJcblx0fSBlbHNlIHtcclxuXHRcdHZhciBjc3NOb2RlID0gZG9jdW1lbnQuY3JlYXRlVGV4dE5vZGUoY3NzKTtcclxuXHRcdHZhciBjaGlsZE5vZGVzID0gc3R5bGVFbGVtZW50LmNoaWxkTm9kZXM7XHJcblx0XHRpZiAoY2hpbGROb2Rlc1tpbmRleF0pIHN0eWxlRWxlbWVudC5yZW1vdmVDaGlsZChjaGlsZE5vZGVzW2luZGV4XSk7XHJcblx0XHRpZiAoY2hpbGROb2Rlcy5sZW5ndGgpIHtcclxuXHRcdFx0c3R5bGVFbGVtZW50Lmluc2VydEJlZm9yZShjc3NOb2RlLCBjaGlsZE5vZGVzW2luZGV4XSk7XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHRzdHlsZUVsZW1lbnQuYXBwZW5kQ2hpbGQoY3NzTm9kZSk7XHJcblx0XHR9XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiBhcHBseVRvVGFnKHN0eWxlRWxlbWVudCwgb2JqKSB7XHJcblx0dmFyIGNzcyA9IG9iai5jc3M7XHJcblx0dmFyIG1lZGlhID0gb2JqLm1lZGlhO1xyXG5cclxuXHRpZihtZWRpYSkge1xyXG5cdFx0c3R5bGVFbGVtZW50LnNldEF0dHJpYnV0ZShcIm1lZGlhXCIsIG1lZGlhKVxyXG5cdH1cclxuXHJcblx0aWYoc3R5bGVFbGVtZW50LnN0eWxlU2hlZXQpIHtcclxuXHRcdHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0LmNzc1RleHQgPSBjc3M7XHJcblx0fSBlbHNlIHtcclxuXHRcdHdoaWxlKHN0eWxlRWxlbWVudC5maXJzdENoaWxkKSB7XHJcblx0XHRcdHN0eWxlRWxlbWVudC5yZW1vdmVDaGlsZChzdHlsZUVsZW1lbnQuZmlyc3RDaGlsZCk7XHJcblx0XHR9XHJcblx0XHRzdHlsZUVsZW1lbnQuYXBwZW5kQ2hpbGQoZG9jdW1lbnQuY3JlYXRlVGV4dE5vZGUoY3NzKSk7XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiB1cGRhdGVMaW5rKGxpbmtFbGVtZW50LCBvYmopIHtcclxuXHR2YXIgY3NzID0gb2JqLmNzcztcclxuXHR2YXIgc291cmNlTWFwID0gb2JqLnNvdXJjZU1hcDtcclxuXHJcblx0aWYoc291cmNlTWFwKSB7XHJcblx0XHQvLyBodHRwOi8vc3RhY2tvdmVyZmxvdy5jb20vYS8yNjYwMzg3NVxyXG5cdFx0Y3NzICs9IFwiXFxuLyojIHNvdXJjZU1hcHBpbmdVUkw9ZGF0YTphcHBsaWNhdGlvbi9qc29uO2Jhc2U2NCxcIiArIGJ0b2EodW5lc2NhcGUoZW5jb2RlVVJJQ29tcG9uZW50KEpTT04uc3RyaW5naWZ5KHNvdXJjZU1hcCkpKSkgKyBcIiAqL1wiO1xyXG5cdH1cclxuXHJcblx0dmFyIGJsb2IgPSBuZXcgQmxvYihbY3NzXSwgeyB0eXBlOiBcInRleHQvY3NzXCIgfSk7XHJcblxyXG5cdHZhciBvbGRTcmMgPSBsaW5rRWxlbWVudC5ocmVmO1xyXG5cclxuXHRsaW5rRWxlbWVudC5ocmVmID0gVVJMLmNyZWF0ZU9iamVjdFVSTChibG9iKTtcclxuXHJcblx0aWYob2xkU3JjKVxyXG5cdFx0VVJMLnJldm9rZU9iamVjdFVSTChvbGRTcmMpO1xyXG59XHJcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcbiAqKiBtb2R1bGUgaWQgPSAzOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9mYXZvdXJpdGVzLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZmF2b3VyaXRlcy5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZmF2b3VyaXRlcy5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDM5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcIkBjaGFyc2V0IFxcXCJVVEYtOFxcXCI7XFxuI2Zhdm91cml0ZXMge1xcbiAgd2lkdGg6IDk5MHB4O1xcbiAgbWFyZ2luOiAzcHggYXV0byAwOyB9XFxuICAjZmF2b3VyaXRlcyAucmlnaHQtY29sdW1uIHtcXG4gICAgd2lkdGg6IDQ5MHB4O1xcbiAgICBmbG9hdDogcmlnaHQ7IH1cXG4gICNmYXZvdXJpdGVzIC5sZWZ0LWNvbHVtbiB7XFxuICAgIHdpZHRoOiA0OTBweDtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG5cXG4uYnVsbGV0aW4tc2hvcnQge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgcGFkZGluZzogMjBweCAxNnB4O1xcbiAgd2lkdGg6IDQ5MHB4O1xcbiAgbWFyZ2luLWJvdHRvbTogN3B4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gIC8qINCb0LXQstCw0Y8g0LrQvtC70L7QvdC60LAgKi9cXG4gIC8qINCf0YDQsNCy0LDRjyDQutC+0LvQvtC90LrQsCAqLyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgPiBkaXYge1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tcmlnaHQtY29sdW1uIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICB3aWR0aDogOTBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tcmlnaHQtY29sdW1uID4gLm9uT3JPZmZMaW5lVXNlciB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHRvcDogNXB4O1xcbiAgICAgIGxlZnQ6IDEwcHg7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tY2VudGVyLWNvbHVtbiB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAxNXB4OyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgaDMge1xcbiAgICBtYXJnaW46IDA7XFxuICAgIGZvbnQ6IDQwMCAyMHB4IC8gMjRweCBSb2JvdG87XFxuICAgIGNvbG9yOiAjMjEyMTIxO1xcbiAgICB3aWR0aDogMjA2cHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmJ1bGxldGluLXNob3J0IGgzOmhvdmVyIHtcXG4gICAgICB0ZXh0LWRlY29yYXRpb246IHVuZGVybGluZTtcXG4gICAgICAtd2Via2l0LXRleHQtZGVjb3JhdGlvbi1jb2xvcjogZ3JheTtcXG4gICAgICAgICAgICAgIHRleHQtZGVjb3JhdGlvbi1jb2xvcjogZ3JheTtcXG4gICAgICAtd2Via2l0LXRleHQtZGVjb3JhdGlvbi1zdHlsZTogZGFzaGVkO1xcbiAgICAgICAgICAgICAgdGV4dC1kZWNvcmF0aW9uLXN0eWxlOiBkYXNoZWQ7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tY2F0ZWdvcnkge1xcbiAgICBjb2xvcjogcmdiYSgzMSwgMzEsIDMxLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNC40cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxMXB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAxNnB4OyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWRlc2NyaXB0aW9uIHtcXG4gICAgZm9udDogNDAwIDEycHggLyAxOC42cHggUm9ib3RvO1xcbiAgICB3aWR0aDogMjU0cHg7XFxuICAgIGNvbG9yOiAjMGQwZDFlOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWltYWdlIHtcXG4gICAgd2lkdGg6IDkwcHg7XFxuICAgIGhlaWdodDogOTFweDtcXG4gICAgYmFja2dyb3VuZDogIzE4NzVEMCB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nXCIpICsgXCIpOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLXByaWNlIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBib3R0b206IC01NHB4O1xcbiAgICByaWdodDogMDtcXG4gICAgY29sb3I6ICMyMDIwMjA7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMTYuOHB4IFJvYm90bzsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1hY3Rpb24ge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGJvdHRvbTogMjBweDtcXG4gICAgcmlnaHQ6IDExN3B4O1xcbiAgICBjb2xvcjogIzIxMjEyMTtcXG4gICAgZm9udDogNDAwIDE0cHggLyAxNi44cHggUm9ib3RvOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBib3R0b206IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIHotaW5kZXg6IDE7IH1cXG4gICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uIHtcXG4gICAgICB3aWR0aDogMTAwcHg7XFxuICAgICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGNTkxMUQ7XFxuICAgICAgdGV4dC1hbGlnbjogY2VudGVyOyB9XFxuICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uOmJlZm9yZSB7XFxuICAgICAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgYm90dG9tOiAtMTRweDtcXG4gICAgICAgIGJvcmRlcjogMTNweCBzb2xpZCAjZTU3YjAwO1xcbiAgICAgICAgei1pbmRleDogLTE7XFxuICAgICAgICBsZWZ0OiAtMjNweDtcXG4gICAgICAgIGJvcmRlci1yaWdodC13aWR0aDogMS41ZW07XFxuICAgICAgICBib3JkZXItbGVmdC1jb2xvcjogdHJhbnNwYXJlbnQ7XFxuICAgICAgICBib3gtc2hhZG93OiAycHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpOyB9XFxuICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uOmFmdGVyIHtcXG4gICAgICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICBib3R0b206IDA7XFxuICAgICAgICBib3JkZXI6IDEzcHggc29saWQgI0Y1OTExRDtcXG4gICAgICAgIHJpZ2h0OiAtMTNweDtcXG4gICAgICAgIGJvcmRlci1sZWZ0LXdpZHRoOiAwO1xcbiAgICAgICAgYm9yZGVyLXJpZ2h0LWNvbG9yOiB0cmFuc3BhcmVudDsgfVxcbiAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbiA+IC5yaWJib24tY29udGVudCB7XFxuICAgICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjZweCBSb2JvdG87XFxuICAgICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbiA+IC5yaWJib24tY29udGVudDpiZWZvcmUge1xcbiAgICAgICAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgICAgYm9yZGVyLXN0eWxlOiBzb2xpZDtcXG4gICAgICAgICAgYm9yZGVyLWNvbG9yOiAjMkI0QTY3IHRyYW5zcGFyZW50IHRyYW5zcGFyZW50IHRyYW5zcGFyZW50O1xcbiAgICAgICAgICBib3R0b206IC0xNHB4O1xcbiAgICAgICAgICBsZWZ0OiAwO1xcbiAgICAgICAgICBib3JkZXItd2lkdGg6IDFlbSAwIDAgMWVtOyB9XFxuXFxuLmNoZWNrQm94IHtcXG4gIHdpZHRoOiAyNXB4O1xcbiAgaGVpZ2h0OiAyNXB4O1xcbiAgYm9yZGVyOiAxcHggc29saWQgZ3JleTtcXG4gIGJvcmRlci1yYWRpdXM6IDVweDtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcblxcbi5jaGVja2VkIHtcXG4gIGJhY2tncm91bmQ6ICMxODc1RDAgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9WLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGJvcmRlci1jb2xvcjogIzE4NzVEMCAhaW1wb3J0YW50OyB9XFxuXFxuZGl2LmV4Y2xhbWF0aW9uUG9pbnQge1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9leGNsYW1hdGlvblBvaW50LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGhlaWdodDogMjdweDtcXG4gIHdpZHRoOiAyN3B4O1xcbiAgbWFyZ2luLXRvcDogMTBweDsgfVxcblxcbi5kb2xsYXJCaWxsIHtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZG9sbGFyQmlsbC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICBoZWlnaHQ6IDI3cHg7XFxuICB3aWR0aDogMjdweDtcXG4gIG1hcmdpbi10b3A6IDEwcHg7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9idWxsZXRpbi1kZWZhdWx0LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYnVsbGV0aW4tZGVmYXVsdC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0MVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1YucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9WLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2V4Y2xhbWF0aW9uUG9pbnQucG5nXG4gKiogbW9kdWxlIGlkID0gNDNcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9kb2xsYXJCaWxsLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZG9sbGFyQmlsbC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0NFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9lZGl0LXByb2ZpbGUuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9lZGl0LXByb2ZpbGUuc2Nzc1wiLCBmdW5jdGlvbigpIHtcblx0XHRcdHZhciBuZXdDb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2VkaXQtcHJvZmlsZS5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL2VkaXQtcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwic2VjdGlvbi5lZGl0UHJvZmlsZSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIHdpZHRoOiAxMTA1cHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDQwcHg7XFxuICBwYWRkaW5nOiA2NXB4IDAgNDVweCAwO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSB7XFxuICAgIHdpZHRoOiA2NTBweDtcXG4gICAgbWFyZ2luOiAwIGF1dG87IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IGgyIHtcXG4gICAgICBmb250OiA3MDAgMjJweCBSb2JvdG87XFxuICAgICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5zZWxlY3RCb3gge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWU5ZTllO1xcbiAgICAgIG1hcmdpbi10b3A6IDE1cHg7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICAgIHBhZGRpbmctbGVmdDogNXB4OyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuZWRpdC1wcm9maWxlLWZvcm0tZm90byB7XFxuICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzllOWU5ZTtcXG4gICAgICB3aWR0aDogNDEwcHg7XFxuICAgICAgcGFkZGluZy1ib3R0b206IDVweDsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuZWRpdC1wcm9maWxlLWZvcm0tZm90byA+IGlucHV0IHtcXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgIHotaW5kZXg6IC0xOyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvID4gcCB7XFxuICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgcGFkZGluZy10b3A6IDExcHg7XFxuICAgICAgICBwYWRkaW5nLWxlZnQ6IDVweDtcXG4gICAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuZWRpdC1wcm9maWxlLWZvcm0tZm90byA+IC5idG4tYmx1ZSB7XFxuICAgICAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICAgICAgaGVpZ2h0OiAzMHB4O1xcbiAgICAgICAgd2lkdGg6IDg1cHg7XFxuICAgICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgICBsaW5lLWhlaWdodDogMzBweDsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIC5lZGl0LXByb2ZpbGUtZm9ybS1jb250YWN0cy1jb250YWluZXIgLmlucHV0Rm9ybSB7XFxuICAgICAgd2lkdGg6IDk1JTtcXG4gICAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSAuZWRpdC1wcm9maWxlLWZvcm0tY29udGFjdHMtY29udGFpbmVyIGJ1dHRvbiB7XFxuICAgICAgd2lkdGg6IDE2cHg7XFxuICAgICAgaGVpZ2h0OiAxNnB4O1xcbiAgICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2ljb25fY2xvc2VfYmx1ZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICAgIGJhY2tncm91bmQtc2l6ZTogY29udGFpbjsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIC5zb2NpYWwtbGluay1jb250YWluZXIge1xcbiAgICAgIGhlaWdodDogYXV0bzsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXYge1xcbiAgICAgICAgd2lkdGg6IDI0cHg7XFxuICAgICAgICBoZWlnaHQ6IDI0cHg7XFxuICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgIG1hcmdpbi1yaWdodDogNDdweDsgfVxcbiAgc2VjdGlvbi5lZGl0UHJvZmlsZSAudXBsb2FkRmlsZUZvcm0ge1xcbiAgICB2aXNpYmlsaXR5OiBoaWRkZW47IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSAudXBsb2FkRmlsZUZvcm0gaW5wdXQge1xcbiAgICAgIHdpZHRoOiAwcHg7XFxuICAgICAgaGVpZ2h0OiAwcHg7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvZWRpdC1wcm9maWxlLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0NlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2ljb25fY2xvc2VfYmx1ZS5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2ljb25fY2xvc2VfYmx1ZS5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0N1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vcHJvZmlsZS5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vcHJvZmlsZS5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL3Byb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDQ4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcIi52aWV3LXByb2ZpbGUtY29udGFpbmVyIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNmZmY7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgcGFkZGluZzogNDBweCAxMjdweCAzMHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwO1xcbiAgd2lkdGg6IDExMDNweDtcXG4gIGZvbnQ6IDQwMCAxNnB4LzI0cHggUm9ib3RvO1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaDEge1xcbiAgICBwYWRkaW5nLWJvdHRvbTogMTZweDtcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBmb250OiA0MDAgMjJweC8yNnB4IFJvYm90bzsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaW5wdXQ6LW1vei1yZWFkLW9ubHkge1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lOyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDpyZWFkLW9ubHkge1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lOyB9XFxuICAgIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0Oi1tb3otcmVhZC1vbmx5Om5vdCg6bGFzdC1vZi10eXBlKSB7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDpyZWFkLW9ubHk6bm90KDpsYXN0LW9mLXR5cGUpIHtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciAuc29jaWFsLWxpbmstY29udGFpbmVyIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcblxcbi5wcm9maWxlLWNvbnRhaW5lcnMtd3JhcCB7XFxuICB3aWR0aDogODQ5cHg7XFxuICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5wcm9maWxlLWxlZnQtY29udGFpbmVyLCAucHJvZmlsZS1yaWdodC1jb250YWluZXIsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0LCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQsIC5wcm9maWxlLWluZm8ge1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLWxlZnQtY29udGFpbmVyOmJlZm9yZSwgLnByb2ZpbGUtbGVmdC1jb250YWluZXI6YWZ0ZXIsIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lcjpiZWZvcmUsIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lcjphZnRlciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLWxlZnQ6YmVmb3JlLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tbGVmdDphZnRlciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLXJpZ2h0OmJlZm9yZSwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLXJpZ2h0OmFmdGVyLCAucHJvZmlsZS1pbmZvOmJlZm9yZSwgLnByb2ZpbGUtaW5mbzphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7IH1cXG4gIC5wcm9maWxlLWxlZnQtY29udGFpbmVyOmFmdGVyLCAucHJvZmlsZS1yaWdodC1jb250YWluZXI6YWZ0ZXIsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0OmFmdGVyLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQ6YWZ0ZXIsIC5wcm9maWxlLWluZm86YWZ0ZXIge1xcbiAgICBjbGVhcjogYm90aDsgfVxcblxcbi52aWV3LXByb2ZpbGU6YmVmb3JlLCAudmlldy1wcm9maWxlOmFmdGVyLCAudmlldy1wcm9maWxlLWNvbnRhaW5lcjpiZWZvcmUsIC52aWV3LXByb2ZpbGUtY29udGFpbmVyOmFmdGVyIHtcXG4gIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gIGRpc3BsYXk6IHRhYmxlOyB9XFxuXFxuLnZpZXctcHJvZmlsZTphZnRlciwgLnZpZXctcHJvZmlsZS1jb250YWluZXI6YWZ0ZXIge1xcbiAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciB7XFxuICB3aWR0aDogMTkwcHg7XFxuICBwYWRkaW5nOiAwIDUycHg7IH1cXG4gIC5wcm9maWxlLWxlZnQtY29udGFpbmVyIC5idG4tYmx1ZSB7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgaGVpZ2h0OiAzNnB4O1xcbiAgICBsaW5lLWhlaWdodDogMzZweDtcXG4gICAgbWFyZ2luOiAwIGF1dG87XFxuICAgIG1hcmdpbi10b3A6IDlweDsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXIgPiAuaHotY2VudGVyaW5nLXdyYXBwZXIgPiAucHJvZmlsZS1zZXR0aW5ncyA+IC5zZWxlY3RCb3gge1xcbiAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgbWFyZ2luLWJvdHRvbTogMjVweDsgfVxcbiAgICAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciA+IC5oei1jZW50ZXJpbmctd3JhcHBlciA+IC5wcm9maWxlLXNldHRpbmdzID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IC8gMjRweCBSb2JvdG87XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7IH1cXG5cXG4ucHJvZmlsZS1yaWdodC1jb250YWluZXIge1xcbiAgd2lkdGg6IDQ4NXB4O1xcbiAgcGFkZGluZy1sZWZ0OiAyMHB4O1xcbiAgYm9yZGVyLWxlZnQ6IDFweCBzb2xpZCAjZWJlYmViOyB9XFxuICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiB7XFxuICAgIHdpZHRoOiBhdXRvO1xcbiAgICBmbG9hdDogbm9uZTtcXG4gICAgbWFyZ2luLWJvdHRvbTogMDsgfVxcbiAgICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpIHtcXG4gICAgICB3aWR0aDogNTAlOyB9XFxuICAgICAgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyID4gLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGEge1xcbiAgICAgICAgd2lkdGg6IGF1dG87IH1cXG4gICAgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyID4gLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzIHtcXG4gICAgICB3aWR0aDogNTAwcHg7IH1cXG5cXG4ucHJvZmlsZS1pbmZvLXNlY3Rpb24tbGVmdCwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLXJpZ2h0IHtcXG4gIHdpZHRoOiA1MCU7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuXFxuLnByb2ZpbGUtYXZhdGFyIHtcXG4gIHdpZHRoOiAxNDVweDtcXG4gIGhlaWdodDogMjE1cHg7XFxuICBtYXJnaW46IDAgYXV0byA0MHB4O1xcbiAgcGFkZGluZy1ib3R0b206IDVweDtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYXZhdGFyLmpwZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGJhY2tncm91bmQtc2l6ZTogY29udGFpbjtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG5cXG4ucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZSB7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICBwYWRkaW5nOiAxM3B4IDA7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlOmJlZm9yZSwgLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24gLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24tdGl0bGU6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlOyB9XFxuICAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZTphZnRlciB7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuICAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZSBzcGFuIHtcXG4gICAgY29sb3I6ICM5MjkyOTI7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZSBkaXYge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nXCIpICsgXCIpO1xcbiAgICB3aWR0aDogMTNweDtcXG4gICAgaGVpZ2h0OiA3cHg7IH1cXG5cXG4uc29jaWFsLWxpbmstY29udGFpbmVyIGRpdiB7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICB3aWR0aDogMjRweDtcXG4gIGhlaWdodDogMjRweDtcXG4gIGZsb2F0OiBsZWZ0OyB9XFxuICAuc29jaWFsLWxpbmstY29udGFpbmVyIGRpdjpiZWZvcmUsIC5zb2NpYWwtbGluay1jb250YWluZXIgZGl2OmFmdGVyIHtcXG4gICAgY29udGVudDogXFxcIiBcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTsgfVxcbiAgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXY6YWZ0ZXIge1xcbiAgICBjbGVhcjogYm90aDsgfVxcbiAgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXY6bm90KDpsYXN0LW9mLXR5cGUpIHtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyM3B4OyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMge1xcbiAgZGlzcGxheTogYmxvY2s7XFxuICB3aWR0aDogMTAwJTtcXG4gIG1hcmdpbjogMzBweCAwO1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zOmJlZm9yZSwgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnM6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlOyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9uczphZnRlciB7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2LCAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50IHtcXG4gIGJvcmRlcjogMXB4IHNvbGlkICNlYmViZWI7XFxuICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IGRpdiB7XFxuICBjb2xvcjogIzkyOTI5MjtcXG4gIHdpZHRoOiA1MCU7XFxuICBwYWRkaW5nOiAyNHB4IDA7XFxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IGRpdjpiZWZvcmUsIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLW5hdiBkaXY6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlOyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYgZGl2OmFmdGVyIHtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYgLm5hdi1pdGVtLXNlbGVjdGVkIHtcXG4gIGNvbG9yOiAjN2VhZWUwO1xcbiAgYm9yZGVyLWJvdHRvbTogMnB4IHNvbGlkICNmZjUyNTI7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50IHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNmNmY2ZjY7XFxuICBoZWlnaHQ6IDE4MHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSB7XFxuICB3aWR0aDogMTAwJTtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjZWJlYmViO1xcbiAgaGVpZ2h0OiAxMjNweDtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIG92ZXJmbG93OiBoaWRkZW47IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSAuY29udGVudC1pdGVtLWxvZ28ge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHRvcDogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgd2lkdGg6IDI3cHg7XFxuICAgIGhlaWdodDogMjdweDtcXG4gICAgcGFkZGluZzogMCAyM3B4IDIzcHggN3B4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2ljb25fdXNlci5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgIGJhY2tncm91bmQtcG9zaXRpb246IGNlbnRlcjtcXG4gICAgbWFyZ2luLXRvcDogMjhweDsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIC5jb250ZW50LWl0ZW0tdGV4dCB7XFxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICB3aWR0aDogMzczcHg7XFxuICAgIG1hcmdpbjogMjhweCAzOHB4IDI0cHggMDtcXG4gICAgZm9udDogNDAwIDEycHgvMThweCBSb2JvdG87XFxuICAgIGNvbG9yOiAjMGMwYzFlOyB9XFxuICAgIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSAuY29udGVudC1pdGVtLXRleHQ6YmVmb3JlLCAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS10ZXh0OmFmdGVyIHtcXG4gICAgICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICAgICAgZGlzcGxheTogdGFibGU7IH1cXG4gICAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIC5jb250ZW50LWl0ZW0tdGV4dDphZnRlciB7XFxuICAgICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1pbmZvIHtcXG4gIG1hcmdpbi1ib3R0b206IDMwcHg7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9hdmF0YXIuanBnXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hdmF0YXIuanBnXG4gKiogbW9kdWxlIGlkID0gNTBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9jYXJldF9ibGFjay5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhcmV0X2JsYWNrLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDUxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvaWNvbl91c2VyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvaWNvbl91c2VyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDUyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIndXNlIHN0cmljdCdcblxuY29uc3QgZGVidWcgPSByZXF1aXJlKCcuLi9kYXRhL2RlYnVnJylcblxubGV0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgY3R4LkRFQlVHX0xFVkVMID0gZGVidWcuREVCVUdcblxuICBjb25zb2xlLmxvZyA9IChmdW5jdGlvbigpIHtcbiAgICBsZXQgbG9nID0gY29uc29sZS5sb2dcbiAgICBpZiggY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLkRFQlVHIClcbiAgICAgIHJldHVybiBsb2dcbiAgICBlbHNlIHJldHVybiAoKSA9PiB7fVxuICB9LmJpbmQoY3R4KSkoKVxuXG4gIGNvbnNvbGUuZXJyb3IgPSAoZnVuY3Rpb24oKSB7XG4gICAgbGV0IGVycm9yID0gY29uc29sZS5lcnJvclxuICAgIGlmKCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuREVCVUcgfHwgY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLk9OTFlfRVJST1JTIClcbiAgICAgIHJldHVybiBlcnJvclxuICAgIGVsc2UgcmV0dXJuICgpID0+IHt9XG4gIH0uYmluZChjdHgpKSgpXG5cbiAgY29uc29sZS5pbmZvID0gKGZ1bmN0aW9uKCkge1xuICAgIGxldCBpbmZvID0gY29uc29sZS5pbmZvXG4gICAgaWYoIGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5ERUJVRyB8fCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuT05MWV9FUlJPUlMgKVxuICAgICAgcmV0dXJuIGluZm9cbiAgICBlbHNlIHJldHVybiAoKSA9PiB7fVxuICB9LmJpbmQoY3R4KSkoKVxuXG4gIC8qICDQlNC70Y8g0YXQvtGF0LzRiyAqL1xuICB3aW5kb3cubHMgPSBcIllvdSd2ZSBtaXNzZWQgYSB3aW5kb3csIGxvbCA9KVwiXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2xvZ2dlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcIkRFQlVHXCI6IDAsXG5cdFwiT05MWV9FUlJPUlNcIjogMSxcblx0XCJQUk9EVUNUSU9OXCI6IDJcbn07XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2RhdGEvZGVidWcuanNvblxuICoqIG1vZHVsZSBpZCA9IDU0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIndXNlIHN0cmljdCdcblxuY29uc3QgTU9EVUxFUyA9IHtcbiAgXCJjaGVja2JveFwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL2NoZWNrYm94JyksXG4gIFwibmljZUJ1dHRvblwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL25pY2VCdXR0b24nKSxcbiAgXCJ0ZXh0XCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvdGV4dCcpLFxuICBcInNlbGVjdEJveFwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL3NlbGVjdEJveCcpLFxuICBcInRleHRBcmVhXCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvdGV4dEFyZWEnKVxufVxuXG53aW5kb3cuZWUgPSByZXF1aXJlKCcuL2V2ZW50cycpXG5lZS5pbml0KClcblxubGV0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cblxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGFwcCA9PiB7XG4gIGZvcihsZXQga2V5IGluIE1PRFVMRVMpXG4gICAgYXBwLmRpcmVjdGl2ZShrZXksIE1PRFVMRVNba2V5XSlcblxuICByZXR1cm4gYXBwXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9pbmRleC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4ge1xuICAgIHJlc3RyaWN0OiBcIkVcIixcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxuICAgIHNjb3BlOiB7XG4gICAgICBuZ01vZGVsOiBcIj1cIlxuICAgIH0sXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiY2hlY2tCb3hcIj48L2Rpdj5gLFxuICAgIHJlcGxhY2U6IHRydWUsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCkge1xuICAgICAgbGV0IGVsID0gJGVsZW1lbnRbMF1cbiAgICAgIC8vLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2NoZWNrQm94JylbMF1cblxuICAgICAgaWYoJHNjb3BlLm5nTW9kZWwgJiYgIWVsLmNsYXNzTGlzdC5jb250YWlucygnY2hlY2tlZCcpKVxuICAgICAgICBlbC5jbGFzc0xpc3QuYWRkKCdjaGVja2VkJylcbiAgICAgIGVsc2UgaWYoISRzY29wZS5uZ01vZGVsICYmIGVsLmNsYXNzTGlzdC5jb250YWlucygnY2hlY2tlZCcpKVxuICAgICAgICBlbC5jbGFzc0xpc3QucmVtb3ZlKCdjaGVrZWQnKVxuXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGUgPT4ge1xuICAgICAgICBlbC5jbGFzc0xpc3QudG9nZ2xlKCdjaGVja2VkJylcbiAgICAgICAgJHNjb3BlLm5nTW9kZWwgPSAkc2NvcGUubmdNb2RlbCA/ICBmYWxzZSA6IHRydWVcbiAgICAgIH0pXG4gICAgfVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XG4gIHJldHVybiB7XG4gICAgcmVzdHJpY3Q6IFwiRVwiLFxuICAgIHRyYW5zY2x1ZGU6IHRydWUsXG4gICAgc2NvcGUgOiB7XG4gICAgICBjbGFzczogXCJAXCIsXG4gICAgICBuZ0NsaWNrOiBcIiZcIlxuICAgIH0sXG4gICAgcmVwbGFjZTogdHJ1ZSxcbiAgICB0ZW1wbGF0ZSA6IGA8ZGl2IGNsYXNzPVwie3sgY2xhc3MgfX1cIj5cbiAgICAgICAgICAgICAgICAgIDxzcGFuIGNsYXNzPVwiaW5rXCI+PC9zcGFuPlxuICAgICAgICAgICAgICAgICAgPG5nLXRyYW5zY2x1ZGUgc3R5bGU9XCJkaXNwbGF5OmJsb2NrOyB3aWR0aDoxMDAlOyBoZWlnaHQ6aW5oZXJpdDtcIj48L25nLXRyYW5zY2x1ZGU+XG4gICAgICAgICAgICAgICAgPC9kaXY+YCxcbiAgICBjb250cm9sbGVyOiBmdW5jdGlvbigkc2NvcGUsICRlbGVtZW50KSB7XG4gICAgICBsZXQgb25DbGljayA9IGZ1bmN0aW9uKGUpIHtcbiAgICAgICAgbGV0IGluayA9IHRoaXMuZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnaW5rJylbMF1cbiAgICAgICAgaW5rLmNsYXNzTGlzdC5yZW1vdmUoJ2FuaW1hdGUnKVxuXG4gICAgICAgIGxldCByZWN0ID0gdGhpcy5nZXRCb3VuZGluZ0NsaWVudFJlY3QoKVxuXG4gICAgICAgIGlmKCAhaW5rLmNsaWVudEhlaWdodCAmJiAhaW5rLmNsaWVudFdpZHRoICkge1xuICAgICAgICAgIGxldCBkID0gTWF0aC5tYXgodGhpcy5jbGllbnRXaWR0aCwgdGhpcy5jbGllbnRIZWlnaHQpXG4gICAgICAgICAgaW5rLnN0eWxlLmhlaWdodCA9IGluay5zdHlsZS53aWR0aCA9IGAke2R9cHhgXG4gICAgICAgIH1cblxuICAgICAgICBpbmsuc3R5bGUudG9wID0gYCR7ZS5jbGllbnRZIC0gcmVjdC50b3AgLSBpbmsuY2xpZW50SGVpZ2h0LzJ9cHhgXG4gICAgICAgIGluay5zdHlsZS5sZWZ0ID0gYCR7ZS5jbGllbnRYIC0gcmVjdC5sZWZ0IC1pbmsuY2xpZW50V2lkdGgvMn1weGBcbiAgICAgICAgaW5rLmNsYXNzTGlzdC5hZGQoJ2FuaW1hdGUnKVxuICAgICAgfVxuXG4gICAgICBpZigkc2NvcGUubmdDbGljaylcbiAgICAgICAgJGVsZW1lbnRbMF0uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCAkc2NvcGUubmdDbGljaylcbiAgICAgICAgXG4gICAgICAkZWxlbWVudFswXS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIG9uQ2xpY2spXG4gICAgfVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxuY29uc3QgQ09MT1JTID0ge1xuICAgIGJsdWU6IFwiIzE4NzVEMFwiLFxuICAgIHdoaXRlOiBcIndoaXRlXCJcbn1cblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHtcbiAgICByZXN0cmljdDogXCJFXCIsXG4gICAgcmVxdWlyZTogJ15uZ01vZGVsJyxcbiAgICBzY29wZSA6IHtcbiAgICAgIGxhYmVsOiBcIkBcIixcbiAgICAgIG5nTW9kZWw6IFwiPVwiLFxuICAgICAgY29sb3I6IFwiQFwiLFxuICAgICAgdHlwZTogXCJAXCIsXG4gICAgICB2YWxpZGF0ZTogXCI9XCIsXG4gICAgICBpc1ZhbGlkOiBcIj1cIlxuICAgIH0sXG4gICAgcmVwbGFjZTogdHJ1ZSxcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJpbnB1dEZvcm1cIj5cbiAgICAgICAgICAgICAgICAgPGxhYmVsPnt7IGxhYmVsIH19PC9sYWJlbD5cbiAgICAgICAgICAgICAgICAgPGlucHV0IHR5cGU9XCJ7eyB0eXBlIHx8ICd0ZXh0J319XCIgbmctbW9kZWw9XCJuZ01vZGVsXCI+XG4gICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJlcnJvcnNcIj48L2Rpdj5cbiAgICAgICAgICAgICAgIDwvZGl2PmAsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCwgJHRpbWVvdXQpIHtcbiAgICAgIGxldCBpZCA9IGVlLm9uKCdmb3JtLXN1Ym1pdCcsIHZhbGlkYXRlKVxuICAgICAgJHNjb3BlLiRvbihcIiRkZXN0cm95XCIsIGZ1bmN0aW9uKCkge1xuICAgICAgICBlZS5vZmYoaWQpXG4gICAgICB9LmJpbmQodGhpcykpXG5cbiAgICAgIGxldCBkZWZhdWx0Qm9yZGVyID0gXCJcIlxuXG4gICAgICBsZXQgZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgnaW5wdXQnKVswXSxcbiAgICAgICAgICBsYWJlbCA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlUYWdOYW1lKCdsYWJlbCcpWzBdLFxuICAgICAgICAgIGVycm9yID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnZXJyb3JzJylbMF1cblxuXG4gICAgICBmdW5jdGlvbiB2YWxpZGF0ZSgpIHtcbiAgICAgICAgaWYoJHNjb3BlLnZhbGlkYXRlKSB7XG4gICAgICAgICAgZnVuY3Rpb24gaGFuZGxlKGVycm9yKSB7XG4gICAgICAgICAgICBpZih0eXBlb2YgJHNjb3BlLmlzVmFsaWQgIT09IFwidW5kZWZpbmVkXCIpIHtcbiAgICAgICAgICAgICAgaWYoZXJyb3IubGVuZ3RoKVxuICAgICAgICAgICAgICAgICRzY29wZS5pc1ZhbGlkID0gZmFsc2VcbiAgICAgICAgICAgICAgZWxzZVxuICAgICAgICAgICAgICAgICRzY29wZS5pc1ZhbGlkID0gdHJ1ZVxuXG4gICAgICAgICAgICAgICRzY29wZS4kYXBwbHkoKVxuICAgICAgICAgICAgfVxuICAgICAgICAgIH1cblxuICAgICAgICAgIGxldCByZXNwID0gJHNjb3BlLnZhbGlkYXRlKGVsLnZhbHVlKVxuXG4gICAgICAgICAgaWYoIHR5cGVvZiByZXNwID09PSBcInN0cmluZ1wiKVxuICAgICAgICAgICAgaGFuZGxlKCBlcnJvci5pbm5lckhUTUwgPSByZXNwKVxuICAgICAgICAgIGVsc2VcbiAgICAgICAgICAgIHJlc3AudGhlbiggZnVuY3Rpb24oZGF0YSkge1xuICAgICAgICAgICAgICAgIGVycm9yLmlubmVySFRNTCA9IGRhdGFcbiAgICAgICAgICAgICAgICBoYW5kbGUoZGF0YSlcbiAgICAgICAgICAgICAgfSwgY29uc29sZS5lcnJvcilcblxuXG4gICAgICAgIH1cbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gb25CbHVyKGUpIHtcbiAgICAgICAgaWYoICEkc2NvcGUubmdNb2RlbC5sZW5ndGgpXG4gICAgICAgICAgaGlkZUFuaW1hdGlvbigpXG5cbiAgICAgICAgICB2YWxpZGF0ZSgpXG4gICAgICB9XG5cbiAgICAgIGZ1bmN0aW9uIG9uRm9jdXMoZSkge1xuICAgICAgICBpZighJHNjb3BlLm5nTW9kZWwubGVuZ3RoKVxuICAgICAgICAgIGRpc3BsYXlBbmltYXRpb24oKVxuICAgICAgfVxuXG4gICAgICBmdW5jdGlvbiBkaXNwbGF5QW5pbWF0aW9uKCkge1xuICAgICAgICBsYWJlbC5zdHlsZS5jb2xvciA9IENPTE9SU1skc2NvcGUuY29sb3JdXG4gICAgICAgIGlmKCFkZWZhdWx0Qm9yZGVyLmxlbmd0aCkge1xuICAgICAgICAgIGRlZmF1bHRCb3JkZXIgPSB3aW5kb3cuZ2V0Q29tcHV0ZWRTdHlsZShsYWJlbC5wYXJlbnROb2RlKS5ib3JkZXJCb3R0b21cbiAgICAgICAgfSBlbHNlIHtcbiAgICAgICAgICBsYWJlbC5wYXJlbnROb2RlLnN0eWxlLmJvcmRlckJvdHRvbSA9IGAycHggc29saWQgJHtDT0xPUlNbJHNjb3BlLmNvbG9yXX1gXG4gICAgICAgIH1cblxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QuYWRkKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gaGlkZUFuaW1hdGlvbigpIHtcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXG4gICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gZGVmYXVsdEJvcmRlclxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QucmVtb3ZlKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgJHRpbWVvdXQoICgpID0+IHtcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXG4gICAgICAgICAgZGlzcGxheUFuaW1hdGlvbigpXG4gICAgICAgIGVsc2VcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcbiAgICAgIH0sIDI1MClcblxuXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdibHVyJywgb25CbHVyLmJpbmQodGhpcykpXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdmb2N1cycsIG9uRm9jdXMuYmluZCgkc2NvcGUpKVxuICAgIH1cbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0LmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHtcbiAgICByZXN0cmljdDogXCJFXCIsXG4gICAgcmVxdWlyZTogJ15uZ01vZGVsJyxcbiAgICBzY29wZToge1xuICAgICAgbmdNb2RlbDogXCI9XCIsXG4gICAgICBzaG93OiBcIkBcIlxuICAgIH0sXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwic2VsZWN0Qm94XCI+XG4gICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cImRlZmF1bHRWYWx1ZVwiIG5nLWhpZGU9XCJzaG93XCI+XG4gICAgICAgICAgICAgICAgICA8cD57eyBuZ01vZGVsIH19PC9wPlxuICAgICAgICAgICAgICAgIDwvZGl2PlxuICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJsaXN0T2ZWYWx1ZXNcIiBuZy1zaG93PVwic2hvd1wiPlxuICAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cImxpc3RJdGVtXCIgbmctcmVwZWF0PVwiaXRlbSBpbiBpdGVtc1wiIHZhbHVlPVwie3tpdGVtfX1cIj57eyBpdGVtIH19PC9kaXY+XG4gICAgICAgICAgICAgICAgPC9kaXY+XG4gICAgICAgICAgICAgIDwvZGl2PmAsXG4gICAgcmVwbGFjZTogdHJ1ZSxcbiAgICBjb250cm9sbGVyOiBmdW5jdGlvbigkc2NvcGUsICRlbGVtZW50LCAkdGltZW91dCkge1xuICAgICAgbGV0IGRlZmF1bHRWYWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdkZWZhdWx0VmFsdWUnKVswXSxcbiAgICAgICAgICBsaXN0T2ZWYWx1ZXMgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdsaXN0T2ZWYWx1ZXMnKVswXVxuXG4gICAgICAkdGltZW91dChmdW5jdGlvbigpIHtcbiAgICAgICAgZGVmYXVsdFZhbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGZ1bmN0aW9uKGUpIHtcbiAgICAgICAgICB0aGlzLnNob3cgPSB0cnVlXG5cbiAgICAgICAgICBkb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGhhbmRsZXIpXG4gICAgICAgIH0uYmluZCgkc2NvcGUpKVxuXG4gICAgICAgIGZ1bmN0aW9uIGhhbmRsZXIoZSkge1xuICAgICAgICAgIGlmKCAhKGUudGFyZ2V0ID09IGxpc3RPZlZhbHVlcyB8fFxuICAgICAgICAgICAgICAgIGUudGFyZ2V0LnBhcmVudE5vZGUgPT0gbGlzdE9mVmFsdWVzIHx8XG4gICAgICAgICAgICAgICAgZS50YXJnZXQgPT0gZGVmYXVsdFZhbCB8fFxuICAgICAgICAgICAgICAgIGUudGFyZ2V0LnBhcmVudE5vZGUgPT0gZGVmYXVsdFZhbCkgKSB7XG4gICAgICAgICAgICAkc2NvcGUuc2hvdyA9IGZhbHNlXG4gICAgICAgICAgICAkc2NvcGUuJGFwcGx5KClcbiAgICAgICAgICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcbiAgICAgICAgICB9XG4gICAgICAgIH1cblxuICAgICAgICBmb3IobGV0IHQ9MDt0PGxpc3RPZlZhbHVlcy5jaGlsZHJlbi5sZW5ndGg7IHQrKykge1xuXG4gICAgICAgICAgbGlzdE9mVmFsdWVzLmNoaWxkcmVuW3RdLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgZnVuY3Rpb24oZSkge1xuICAgICAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcignY2xpY2snLCBoYW5kbGVyKVxuICAgICAgICAgICAgdGhpcy5zaG93ID0gZmFsc2VcbiAgICAgICAgICAgIHRoaXMubmdNb2RlbCA9IGUudGFyZ2V0LmlubmVySFRNTFxuICAgICAgICAgIH0uYmluZCgkc2NvcGUpKVxuICAgICAgICB9XG4gICAgICB9LmJpbmQodGhpcyksIDEwMDApXG4gICAgfSxcbiAgICBsaW5rOiBmdW5jdGlvbihzY29wZSwgZWxlbWVudCwgYXR0cnMpIHtcbiAgICAgIHNjb3BlLml0ZW1zID0gSlNPTi5wYXJzZShhdHRycy5pdGVtcylcbiAgICB9XG4gIH1cbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvc2VsZWN0Qm94LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmNvbnN0IE1BWF9TWU1CT0xTID0gMTAwMFxuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4ge1xuICAgIHJlc3RyaWN0OiBcIkVcIixcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxuICAgIHNjb3BlIDoge1xuICAgICAgbGFiZWw6IFwiQFwiLFxuICAgICAgbmdNb2RlbDogXCI9XCJcbiAgICB9LFxuICAgIHJlcGxhY2U6IHRydWUsXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiaW5wdXRGb3JtXCI+XG4gICAgICAgICAgICAgICAgIDxsYWJlbD57eyBsYWJlbCB9fTwvbGFiZWw+XG4gICAgICAgICAgICAgICAgIDx0ZXh0YXJlYSBuZy1tb2RlbD1cIm5nTW9kZWxcIiBtYXhsZW5ndGg9JHtNQVhfU1lNQk9MU30+PC90ZXh0YXJlYT5cbiAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cInN5bWJvbHNcIj48L2Rpdj5cbiAgICAgICAgICAgICAgIDwvZGl2PmAsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCwgJHRpbWVvdXQpIHtcbiAgICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgY291bnQpXG5cbiAgICAgICRzY29wZS4kb24oXCIkZGVzdHJveVwiLCBmdW5jdGlvbigpIHtcbiAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcigna2V5dXAnLCBjb3VudClcbiAgICAgIH0uYmluZCh0aGlzKSlcblxuICAgICAgbGV0IGRlZmF1bHRCb3JkZXIgPSBcIlwiXG5cbiAgICAgIGxldCBlbCA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlUYWdOYW1lKCd0ZXh0YXJlYScpWzBdLFxuICAgICAgICAgIGxhYmVsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeVRhZ05hbWUoJ2xhYmVsJylbMF0sXG4gICAgICAgICAgc3ltYm9scyA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ3N5bWJvbHMnKVswXVxuXG5cbiAgICAgIGZ1bmN0aW9uIGNvdW50KCkge1xuICAgICAgICBzeW1ib2xzLmlubmVySFRNTCA9IGAkeyRzY29wZS5uZ01vZGVsLmxlbmd0aH0vJHtNQVhfU1lNQk9MU31gXG4gICAgICB9XG5cbiAgICAgIGZ1bmN0aW9uIG9uQmx1cihlKSB7XG4gICAgICAgIGlmKCAhJHNjb3BlLm5nTW9kZWwubGVuZ3RoKVxuICAgICAgICAgIGhpZGVBbmltYXRpb24oKVxuICAgICAgfVxuXG4gICAgICBmdW5jdGlvbiBvbkZvY3VzKGUpIHtcbiAgICAgICAgaWYoISRzY29wZS5uZ01vZGVsLmxlbmd0aClcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gZGlzcGxheUFuaW1hdGlvbigpIHtcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIiMxODc1RDBcIlxuICAgICAgICBpZighZGVmYXVsdEJvcmRlci5sZW5ndGgpIHtcbiAgICAgICAgICBkZWZhdWx0Qm9yZGVyID0gd2luZG93LmdldENvbXB1dGVkU3R5bGUobGFiZWwucGFyZW50Tm9kZSkuYm9yZGVyQm90dG9tXG4gICAgICAgIH0gZWxzZSB7XG4gICAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBgMnB4IHNvbGlkICMxODc1RDBgXG4gICAgICAgIH1cblxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QuYWRkKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gaGlkZUFuaW1hdGlvbigpIHtcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXG4gICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gZGVmYXVsdEJvcmRlclxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QucmVtb3ZlKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgJHRpbWVvdXQoICgpID0+IHtcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXG4gICAgICAgICAgZGlzcGxheUFuaW1hdGlvbigpXG4gICAgICAgIGVsc2VcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcbiAgICAgIH0sIDI1MClcblxuXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdibHVyJywgb25CbHVyLmJpbmQodGhpcykpXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdmb2N1cycsIG9uRm9jdXMuYmluZCgkc2NvcGUpKVxuXG4gICAgICBjb3VudCgpXG4gICAgfVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL3RleHRBcmVhLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XG5cbmxldCBwcml2YXRlU2NvcGUgPSB7fVxuXG5tb2R1bGUuZXhwb3J0cy5pbml0ID0gZnVuY3Rpb24oKSB7XG4gIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzID0ge31cbiAgcHJpdmF0ZVNjb3BlLmhhbmRsZXJJZCA9IDBcblxuICBwcml2YXRlU2NvcGUuZ2V0SGFuZGxlcklkID0gZnVuY3Rpb24oKSB7XG4gICAgcmV0dXJuIHByaXZhdGVTY29wZS5oYW5kbGVySWQrK1xuICB9XG5cbiAgcHJpdmF0ZVNjb3BlLnJlZ2lzdGVySGFuZGxlciA9IGZ1bmN0aW9uKG5hbWUsIGhhbmRsZXIpIHtcbiAgICBpZighcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0pXG4gICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXSA9IFtdXG5cbiAgICBsZXQgaWQgPSBwcml2YXRlU2NvcGUuZ2V0SGFuZGxlcklkKClcbiAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXS5wdXNoKHtcbiAgICAgIGlkIDogaWQsXG4gICAgICBoYW5kbGVyIDogaGFuZGxlclxuICAgIH0pXG5cbiAgICByZXR1cm4gaWRcbiAgfVxuXG4gIHByaXZhdGVTY29wZS5oYW5kbGUgPSBmdW5jdGlvbihldmVudE5hbWUsIGRhdGEpIHtcbiAgICBpZihwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tldmVudE5hbWVdKVxuICAgICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbZXZlbnROYW1lXS5mb3JFYWNoKGggPT4gaC5oYW5kbGVyKGRhdGEpKVxuICB9XG5cbiAgcHJpdmF0ZVNjb3BlLnJlbW92ZUhhbmRsZXIgPSBmdW5jdGlvbihpZCkge1xuICAgIGZvcihsZXQga2V5IGluIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzKSB7XG4gICAgICBmb3IobGV0IHQgPTA7IHQ8IHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV0ubGVuZ3RoOyB0KyspIHtcbiAgICAgICAgaWYocHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNba2V5XVt0XS5pZCA9PSBpZCkge1xuICAgICAgICAgIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV0gPSBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldLmZpbHRlcihlbCA9PiBlbC5pZCAhPT0gaWQpXG4gICAgICAgICAgcmV0dXJuIHRydWVcbiAgICAgICAgfVxuICAgICAgfVxuICAgIH1cblxuICAgIHJldHVybiBmYWxzZVxuICB9XG5cbiAgcHJpdmF0ZVNjb3BlLnJlbW92ZUhhbmRsZXJCeU5hbWUgPSBmdW5jdGlvbihuYW1lKSB7XG4gICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0gPSBbXVxuICB9XG59XG5cblxubW9kdWxlLmV4cG9ydHMub24gPSBmdW5jdGlvbihldmVudE5hbWUsIGhhbmRsZXIpIHtcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZWdpc3RlckhhbmRsZXIoZXZlbnROYW1lLCBoYW5kbGVyKVxufVxuXG4vKiBSZW1vdmVzIGhhbmRsZXIgYnkgaWQqL1xubW9kdWxlLmV4cG9ydHMub2ZmID0gZnVuY3Rpb24oaWQpIHtcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyKGlkKVxufVxuXG4vKiBSZW1vdmVzIGFsbCBoYW5kbGVycyBieSBldmVudCBuYW1lICovXG5tb2R1bGUuZXhwb3J0cy5yZW1vdmUgPSBmdW5jdGlvbihuYW1lKSB7XG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlckJ5TmFtZShuYW1lKVxufVxuLypcbiAge1xuICAgIFwibmFtZVwiIDogXCJmb3JtLXN1Ym1pdFwiLFxuICAgIFwiZGF0YVwiIDogXCJ3aGF0ZXZlclwiIDw9PSBvcHRpb25hbFxuICB9XG4qL1xubW9kdWxlLmV4cG9ydHMuZW1pdCA9IGZ1bmN0aW9uKGV2ZW50KSB7XG4gIGxldCBuYW1lID0gZXZlbnQubmFtZSB8fCAoKCkgPT4geyB0aHJvdyBuZXcgRXJyb3IoXCJObyBldmVudCBuYW1lXCIpIH0pKClcbiAgbGV0IGRhdGEgPSBldmVudC5kYXRhIHx8IG51bGxcblxuICBwcml2YXRlU2NvcGUuaGFuZGxlKG5hbWUsIGRhdGEpXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9ldmVudHMuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSB7XG4gIFwiL1wiIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2luZGV4Lmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9pbmRleCcpLFxuICAgIGNvbnRyb2xsZXJBczogXCJpbmRleFwiXG4gIH0sXG4gICcvNDAzJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9lcnJvcjQwMy5odG1sXCJcbiAgfSxcbiAgJy80MDQnIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2Vycm9yNDA0Lmh0bWxcIlxuICB9LFxuICAnLzUwMCcgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvZXJyb3I1MDAuaHRtbFwiXG4gIH0sXG4gICcvYnVsbGV0aW5EZXRhaWxzJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9idWxsZXRpbkRldGFpbHMuaHRtbFwiLFxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2J1bGxldGluRGV0YWlscycpLFxuICAgIGNvbnRyb2xsZXJBczogXCJiZGV0YWlsZWRcIlxuICB9LFxuICAnL2J1bGxldGluQWRkJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkJyksXG4gICAgY29udHJvbGxlckFzOiBcImJhZGRcIlxuICB9LFxuICAnL3NvY2tldFRlc3QnIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL3NvY2tldFRlc3QuaHRtbFwiLFxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL3NvY2tldFRlc3QnKSxcbiAgICBjb250cm9sbGVyQXM6IFwic29ja2V0XCJcbiAgfSxcbiAgJy9sb2dpbicgOiB7XG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL2xvZ2luLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9sb2dpbicpLFxuICAgIGNvbnRyb2xsZXJBczogXCJsb2dpblwiXG4gIH0sXG4gICcvcmVnaXN0ZXInIDoge1xuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9yZWdpc3Rlci5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvcmVnaXN0ZXInKSxcbiAgICBjb250cm9sbGVyQXM6IFwicmVnaXN0ZXJcIlxuICB9LFxuICAnL2VkaXRQcm9maWxlJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9lZGl0LXByb2ZpbGUuaHRtbFwiLFxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZWRpdFByb2ZpbGUnKSxcbiAgICBjb250cm9sbGVyQXM6IFwicHJvZmlsZVwiXG4gIH0sXG4gICcvcHJvZmlsZScgOiB7XG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL2F1dGhlbnRpY2F0ZWQvcHJvZmlsZS5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlJyksXG4gICAgY29udHJvbGxlckFzOiBcInByb2ZpbGVcIlxuICB9LFxuICAnL2Zhdm91cml0ZXMnIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcy5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9mYXZvdXJpdGVzJyksXG4gICAgY29udHJvbGxlckFzOiBcImZhdm91cml0ZVwiXG4gIH0sXG4gICcvc2VhcmNoUmVzdWx0cycgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvc2VhcmNoUmVzdWx0cy5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvc2VhcmNoUmVzdWx0cycpLFxuICAgIGNvbnRyb2xsZXJBczogXCJzZWFyY2hSZXN1bHRzXCJcbiAgfSxcbiAgJy9tYWlsJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9hdXRoZW50aWNhdGVkL21haWwuaHRtbFwiLFxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL3JvdXRlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XG4gIFxuXG59XG5cblxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvaW5kZXguanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkc2NvcGUsICR0aW1lb3V0KSB7XG5cbiAgdGhpcy5pbml0ID0gZnVuY3Rpb24oKSB7XG4gICAgLy8gaWYoIWRiLnVzZXIpXG4gICAgLy8gICByZXR1cm4gJHNjb3BlLnJlZGlyZWN0VG9VcmwoJy80MDMnKVxuXG4gICAgdGhpcy50eXBlcyA9IFtcbiAgICAgIFwi0JDRgNC10L3QtNCwXCIsXG4gICAgICBcItCf0YDQvtC00LDQttCwXCIsXG4gICAgICBcItCe0YLQtNCw0Lwg0LTQsNGA0L7QvFwiLFxuICAgICAgXCLQntCx0LzQtdC9XCJcbiAgICBdXG4gICAgdGhpcy5maWxlcyA9IFtdXG4gIH1cblxuICB0aGlzLnNob3dGaWxlVXBsb2FkID0gZnVuY3Rpb24oKSB7XG4gICAgJHRpbWVvdXQoIGZ1bmN0aW9uKCkge1xuICAgICAgZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ3VwbG9hZEZpbGUnKS5jbGljaygpXG4gICAgfSwgMTAwKVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XG4gIHRoaXMubWVzc2FnZSA9IFwiXCJcbiAgdGhpcy5tZXNzYWdlcyA9IFtdXG5cbiAgdGhpcy5pbml0ID0gZnVuY3Rpb24oKSB7XG4gICAgLyogQ29ubmVjdCB3ZWJzb2NrZXRzICovXG4gICAgdGhpcy53c1VyaSA9IGB3czovLyR7ZG9jdW1lbnQubG9jYXRpb24uaG9zdH0ke2RvY3VtZW50LmxvY2F0aW9uLnBhdGhuYW1lfXdoaXRlYm9hcmRlbmRwb2ludGA7XG4gICAgdGhpcy53ZWJzb2NrZXQgPSBuZXcgV2ViU29ja2V0KHdzVXJpKTtcblxuICAgIHRoaXMud2Vic29ja2V0Lm9uZXJyb3IgPSBmdW5jdGlvbihldnQpIHsgb25FcnJvcihldnQpIH07XG5cbiAgICBmdW5jdGlvbiBvbkVycm9yKGV2dCkge1xuICAgICAgICBjb25zb2xlLmVycm9yKGV2dC5kYXRhKVxuICAgIH1cbiAgfVxuXG4gIGxldCBoYW5kbGVyID0gZnVuY3Rpb24oZSkge1xuICAgIGlmKGUud2hpY2ggPT0gMTMpXG4gICAgICB0aGlzLnNlbmQuY2FsbCh0aGlzKVxuICB9LmJpbmQodGhpcylcblxuICBkb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdrZXlkb3duJywgaGFuZGxlcilcblxuICB0aGlzLnNlbmQgPSBmdW5jdGlvbigpIHtcbiAgICBcbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvc29ja2V0VGVzdC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xuXG4gIHRoaXMuaW5pdCA9ICgpID0+IHtcbiAgICB0aGlzLmRiID0gJHNjb3BlLiRwYXJlbnQuZGJcblxuICAgIHRoaXMuZW1haWwgPSBcIlwiXG4gICAgdGhpcy5wYXNzd29yZCA9IFwiXCJcblxuICAgIHRoaXMuZW1haWxWYWxpZCA9IHRydWVcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSB0cnVlXG5cbiAgICB0aGlzLmxvZ2luRXJyb3IgPSBcIlwiXG5cbiAgICB0aGlzLmhhbmRsZXIgPSBmdW5jdGlvbihlKSB7XG4gICAgICBpZihlLndoaWNoID09IDEzKSB0aGlzLnNlbmQuY2FsbCh0aGlzKVxuICAgIH0uYmluZCh0aGlzKVxuXG4gICAgZG9jdW1lbnQuYWRkRXZlbnRMaXN0ZW5lcigna2V5dXAnLCB0aGlzLmhhbmRsZXIpXG4gIH1cblxuICB0aGlzLmRlbGV0ZUxpc3RuZXJzID0gKCkgPT4ge1xuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxuICB9XG5cbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xuICAgIGVlLmVtaXQoeyBuYW1lIDogXCJmb3JtLXN1Ym1pdFwiIH0pXG5cbiAgICBpZiggdGhpcy5lbWFpbFZhbGlkICYmIHRoaXMucGFzc3dvcmRWYWxpZCApIHtcbiAgICAgIHRoaXMuZGIubG9naW4oe1xuICAgICAgICBcImVtYWlsXCIgOiB0aGlzLmVtYWlsLFxuICAgICAgICBcInBhc3N3b3JkXCI6IHRoaXMucGFzc3dvcmRcbiAgICAgIH0sIChlcnIsIGRhdGEpID0+IHtcbiAgICAgICAgaWYoZXJyKVxuICAgICAgICAgICRzY29wZS4kcGFyZW50LmRpc3BsYXlFcnJvcihcItCe0YjQuNCx0LrQsCDQsNCy0YLQvtGA0LjQt9Cw0YbQuNC4LCDQv9GA0L7QstC10YDRjNGC0LUg0LLQsNGI0Lgg0LTQsNC90L3Ri9C1XCIpXG4gICAgICAgIGVsc2Uge1xuICAgICAgICAgIHRoaXMuZGVsZXRlTGlzdG5lcnMoKVxuICAgICAgICAgIHRoaXMuZGIuc2F2ZVVzZXJEYXRhKGRhdGEpXG4gICAgICAgICAgJHNjb3BlLnJlZGlyZWN0VG9VcmwoJy9wcm9maWxlJylcbiAgICAgICAgfVxuICAgICAgfSlcbiAgICB9XG4gIH1cblxuICB0aGlzLmVtYWlsSXNWYWxpZCA9IGVtYWlsID0+IHtcbiAgICBsZXQgZXJyb3IgPSBcIlwiXG4gICAgaWYoIWVtYWlsLmxlbmd0aCkgIGVycm9yICs9IFwi0J7QsdGP0LfQsNGC0LXQu9GM0L3QvtC1INC/0L7Qu9C1LiBcIlxuICAgIGlmKCEvXigoW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKyhcXC5bXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKSopfChcIi4rXCIpKUAoKFxcW1swLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31dKXwoKFthLXpBLVpcXC0wLTldK1xcLikrW2EtekEtWl17Mix9KSkkLy50ZXN0KGVtYWlsKSlcbiAgICAgIGVycm9yICs9IFwi0J3QtdCy0LXRgNC90YvQuSDRhNC+0YDQvNCw0YIg0L/QvtGH0YLRiy4gXCJcbiAgICByZXR1cm4gZXJyb3JcbiAgfVxuXG4gIHRoaXMucGFzc3dvcmRJc1ZhbGlkID0gcHdkID0+IHtcbiAgICBsZXQgZXJyb3IgPSBcIlwiXG4gICAgaWYoIXB3ZC5sZW5ndGgpIGVycm9yICs9IFwi0J7QsdGP0LfQsNGC0LXQu9GM0L3QvtC1INC/0L7Qu9C1LiBcIlxuICAgIGlmKCBwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxuICAgIHJldHVybiBlcnJvclxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9sb2dpbi5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSwgJHEpIHtcblxuICB0aGlzLmluaXQgPSAoKSA9PiB7XG4gICAgdGhpcy5kYiA9ICRzY29wZS4kcGFyZW50LmRiXG5cbiAgICB0aGlzLmVtYWlsID0gXCJcIlxuICAgIHRoaXMucGFzc3dvcmQgPSBcIlwiXG4gICAgdGhpcy5wYXNzd29yZDIgPSBcIlwiXG5cbiAgICB0aGlzLmVtYWlsVmFsaWQgPSBcIlwiXG4gICAgdGhpcy5wYXNzd29yZFZhbGlkID0gXCJcIlxuICAgIHRoaXMucGFzc3dvcmQyVmFsaWQgPSBcIlwiXG5cbiAgICB0aGlzLmhhbmRsZXIgPSBmdW5jdGlvbihlKSB7XG4gICAgICBpZihlLndoaWNoID09IDEzKSB0aGlzLnNlbmQuY2FsbCh0aGlzKVxuICAgIH0uYmluZCh0aGlzKVxuXG4gICAgZG9jdW1lbnQuYWRkRXZlbnRMaXN0ZW5lcigna2V5dXAnLCB0aGlzLmhhbmRsZXIpXG4gIH1cblxuICB0aGlzLmRlbGV0ZUxpc3RuZXJzID0gKCkgPT4ge1xuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxuICB9XG5cbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xuICAgIGlmKCB0aGlzLmVtYWlsVmFsaWQgJiYgdGhpcy5wYXNzd29yZFZhbGlkICYmIHRoaXMucGFzc3dvcmQyVmFsaWQgKSB7XG4gICAgICB0aGlzLmRiLnJlZ2lzdGVyKHtcbiAgICAgICAgXCJlbWFpbFwiIDogdGhpcy5lbWFpbCxcbiAgICAgICAgXCJwYXNzd29yZFwiOiB0aGlzLnBhc3N3b3JkXG4gICAgICB9LCAoZXJyLCBkYXRhKSA9PiB7XG4gICAgICAgIHRoaXMuZGVsZXRlTGlzdG5lcnMoKVxuICAgICAgICBpZihlcnIpXG4gICAgICAgICAgJHNjb3BlLiRwYXJlbnQucmVkaXJlY3RUb1VybCgnLzUwMCcpXG4gICAgICAgIGVsc2Uge1xuICAgICAgICAgIHdpbmRvdy5kYi5pc0xvZ2dlZCA9IHRydWVcbiAgICAgICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnL3Byb2ZpbGUnKVxuICAgICAgICB9XG4gICAgICB9KVxuICAgIH1cbiAgfVxuXG4gIHRoaXMuZW1haWxJc1ZhbGlkID0gZnVuY3Rpb24oZW1haWwpIHtcbiAgICByZXR1cm4gJHEoIGZ1bmN0aW9uKHJlc29sdmUsIHJlamVjdCkge1xuICAgICAgbGV0IGVycm9yID0gXCJcIlxuICAgICAgaWYoIWVtYWlsLmxlbmd0aCkgIGVycm9yICs9IFwi0J7QsdGP0LfQsNGC0LXQu9GM0L3QvtC1INC/0L7Qu9C1LiBcIlxuICAgICAgaWYoIS9eKChbXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKFxcLltePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSspKil8KFwiLitcIikpQCgoXFxbWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfV0pfCgoW2EtekEtWlxcLTAtOV0rXFwuKStbYS16QS1aXXsyLH0pKSQvLnRlc3QoZW1haWwpKVxuICAgICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXG5cbiAgICAgIHdpbmRvdy5kYi5jaGVja0VtYWlsKGVtYWlsLCBmdW5jdGlvbihlcnIsIGRhdGEpIHtcbiAgICAgICAgaWYoZXJyKSByZWplY3QoZXJyKVxuICAgICAgICBlbHNlIHtcbiAgICAgICAgICBjb25zb2xlLmxvZyhkYXRhKVxuICAgICAgICAgIGlmKGRhdGEgIT09IFwiZmFsc2VcIilcbiAgICAgICAgICAgIGVycm9yICs9IFwi0KLQsNC60LDRjyDQv9C+0YfRgtCwINGD0LbQtSDQuNGB0L/QvtC70YzQt9GD0LXRgtGB0Y8uIFwiXG4gICAgICAgICAgcmVzb2x2ZShlcnJvcilcbiAgICAgICAgfVxuICAgICAgfS5iaW5kKHRoaXMpKVxuICAgIH0uYmluZCh0aGlzKSlcbiAgfVxuXG4gIHRoaXMucGFzc3dvcmRJc1ZhbGlkID0gcHdkID0+IHtcbiAgICBsZXQgZXJyb3IgPSBcIlwiXG4gICAgaWYoIXB3ZC5sZW5ndGgpIGVycm9yICs9IFwi0J7QsdGP0LfQsNGC0LXQu9GM0L3QvtC1INC/0L7Qu9C1LiBcIlxuICAgIGlmKHB3ZC5sZW5ndGggPCA2KSBlcnJvciArPSBcItCf0LDRgNC+0LvRjCDQtNC+0LvQttC10L0g0YHQvtC00LXRgNC20LDRgtGMINC90LUg0LzQtdC90LXQtSA2INGB0LjQvNCy0L7Qu9C+0LIuIFwiXG4gICAgcmV0dXJuIGVycm9yXG4gIH1cblxuICB0aGlzLnBhc3N3b3JkMklzVmFsaWQgPSBwd2QgPT4ge1xuICAgIGxldCBlcnJvciA9IHRoaXMucGFzc3dvcmRJc1ZhbGlkKHB3ZClcbiAgICBpZih0aGlzLnBhc3N3b3JkICE9PSB0aGlzLnBhc3N3b3JkMiApIGVycm9yICs9IFwi0J/QsNGA0L7Qu9C4INC90LUg0YHQvtCy0L/QsNC00LDRjtGCXCJcbiAgICByZXR1cm4gZXJyb3JcbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvcmVnaXN0ZXIuanNcbiAqKi8iLCJcInVzZSBzdHJpY3RcIjtcblxuY2xhc3MgUHJvZmlsZUNvbnRhY3Qge1xuICAgIGNvbnN0cnVjdG9yKCkge1xuICAgICAgICB0aGlzLmNvbnRhY3RFbWFpbHMgPSBbJyddO1xuICAgICAgICB0aGlzLmNvbnRhY3RQaG9uZXMgPSBbJyddO1xuICAgICAgICB0aGlzLnR5cGUgPVwiRU5UUkVQUkVORVVSXCJcblxuICAgICAgICB0aGlzLnBvc2l0aW9uID0gXCJcIlxuICAgICAgICB0aGlzLmNvbXBhbnlOYW1lID0gXCJcIlxuICAgICAgICB0aGlzLnNreXBlVXNlck5hbWUgPSBcIlwiXG4gICAgICAgIHRoaXMubGlua1RvV2ViU2l0ZSA9IFwiXCJcbiAgICB9XG59XG5cbmNsYXNzIHByb2ZpbGVDdHJsIHtcbiAgICBjb25zdHJ1Y3Rvcigkc2NvcGUpe1xuLy8gICAgICAgIGlmKCEkc2NvcGUuJHBhcmVudC5kYi51c2VyKVxuLy8gICAgICAgICAgJHNjb3BlLiRwYXJlbnQucmVkaXJlY3RUb1VybCgnLzQwMycsIHRydWUpXG4vLyAgICAgICAgZWxzZVxuXHRcdFx0dGhpcy5jb250YWN0ID0gbmV3IFByb2ZpbGVDb250YWN0KCk7XG4gICAgICB0aGlzLmNvbnRhY3RUeXBlcyA9IFtcbiAgICAgICAgXCJMRUdBTF9FTlRJVFlcIixcbiAgICAgICAgXCJFTlRSRVBSRU5FVVJcIixcbiAgICAgICAgXCJJTkRJVklEVUFMXCJcbiAgICAgIF1cblxuICAgICAgdGhpcy5lbWFpbCA9IFwiXCJcbiAgICAgIHRoaXMuZmlvID0gXCJcIlxuICAgICAgdGhpcy5tYWluUGhvbmVOdW1iZXIgPSBcIlwiXG4gICAgfVxuICAgIHVwZGF0ZVByb2ZpbGUoKXtcblxuICAgIH1cbiAgICBhZGRDb250YWN0cygkZXZlbnQsIHR5cGUpe1xuICAgICAgICB2YXIgYXJyO1xuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcbiAgICAgICAgZWxzZSBpZih0eXBlID09PSAncGhvbmUnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdFBob25lcztcbiAgICAgICAgZWxzZSByZXR1cm47XG5cbiAgICAgICAgaWYoYXJyLmxlbmd0aCA8IDUgJiYgYXJyW2Fyci5sZW5ndGggLSAxXS50cmltKCkpIGFyci5wdXNoKCcnKTtcbiAgICB9XG5cbiAgICBkZWxldGVDb250YWN0cygkZXZlbnQsICRpbmRleCwgdHlwZSl7XG4gICAgICAgIGlmKHR5cGUgPT09ICdlbWFpbCcpIHRoaXMuY29udGFjdC5jb250YWN0RW1haWxzLnNwbGljZSgkaW5kZXgsIDEpO1xuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xuICAgICAgICBlbHNlIHJldHVybjtcbiAgICB9XG59XG5cbm1vZHVsZS5leHBvcnRzID0gcHJvZmlsZUN0cmw7XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2VkaXRQcm9maWxlLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCI7XG5cbmNsYXNzIFByb2ZpbGVDb250YWN0e1xuICAgIGNvbnN0cnVjdG9yKCkge1xuICAgICAgICB0aGlzLmNvbnRhY3RFbWFpbHMgPSBbJyddXG4gICAgICAgIHRoaXMuY29udGFjdFBob25lcyA9IFsnJ11cbiAgICB9XG59XG5cbmNsYXNzIHByb2ZpbGVDdHJsIHtcbiAgICBjb25zdHJ1Y3Rvcigkc2NvcGUpe1xuICAgICAgICAvLyBpZighJHNjb3BlLiRwYXJlbnQuZGIudXNlcilcbiAgICAgICAgLy8gICAkc2NvcGUuJHBhcmVudC5yZWRpcmVjdFRvVXJsKCcvNDAzJywgdHJ1ZSlcbiAgICAgICAgLy8gZWxzZVxuICAgICAgICB0aGlzLmNvbnRhY3QgPSBuZXcgUHJvZmlsZUNvbnRhY3QoKTtcblxuICAgICAgICB0aGlzLnNvY2lhbENhdGVnb3JpZXMgPSByZXF1aXJlKCcuLi8uLi9kYXRhL3NvY2lhbCcpXG4gICAgfVxuICAgIHVwZGF0ZVByb2ZpbGUoKXtcblxuICAgIH1cbiAgICBhZGRDb250YWN0cygkZXZlbnQsIHR5cGUpe1xuICAgICAgICB2YXIgYXJyO1xuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcbiAgICAgICAgZWxzZSBpZih0eXBlID09PSAncGhvbmUnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdFBob25lcztcbiAgICAgICAgZWxzZSByZXR1cm47XG5cbiAgICAgICAgaWYoYXJyLmxlbmd0aCA8IDUgJiYgYXJyW2Fyci5sZW5ndGggLSAxXS50cmltKCkpIGFyci5wdXNoKCcnKTtcbiAgICB9XG5cbiAgICBkZWxldGVDb250YWN0cygkZXZlbnQsICRpbmRleCwgdHlwZSl7XG4gICAgICAgIGlmKHR5cGUgPT09ICdlbWFpbCcpIHRoaXMuY29udGFjdC5jb250YWN0RW1haWxzLnNwbGljZSgkaW5kZXgsIDEpO1xuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xuICAgICAgICBlbHNlIHJldHVybjtcbiAgICB9XG59XG5cbm1vZHVsZS5leHBvcnRzID0gcHJvZmlsZUN0cmw7XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IHtcblx0XCJpdGVtc1wiOiBbXG5cdFx0e1xuXHRcdFx0XCJrZXlcIjogXCJGQUNFQk9PS1wiLFxuXHRcdFx0XCJzeW5vbnltXCI6IFwiZmFjZWJvb2tcIixcblx0XHRcdFwiY2xhc3NcIjogXCJmYWNlYm9va1wiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImtleVwiOiBcIlZLT05UQUtURVwiLFxuXHRcdFx0XCJzeW5vbnltXCI6IFwidmtvbnRha3RlXCIsXG5cdFx0XHRcImNsYXNzXCI6IFwidmtvbnRha3RlXCJcblx0XHR9LFxuXHRcdHtcblx0XHRcdFwia2V5XCI6IFwiT0ROT0tMQVNTTklLSVwiLFxuXHRcdFx0XCJzeW5vbnltXCI6IFwib2Rub2tsYXNzbmlraVwiLFxuXHRcdFx0XCJjbGFzc1wiOiBcIm9kbm9rbGFzc25pa2lcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJrZXlcIjogXCJMSU5LRURJTlwiLFxuXHRcdFx0XCJzeW5vbnltXCI6IFwibGlua2VkaW5cIixcblx0XHRcdFwiY2xhc3NcIjogXCJsaW5rZWRpblwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImtleVwiOiBcIkdPT0dMRVBMVVNcIixcblx0XHRcdFwic3lub255bVwiOiBcImdvb2dsZXBsdXNcIixcblx0XHRcdFwiY2xhc3NcIjogXCJnb29nbGVwbHVzXCJcblx0XHR9LFxuXHRcdHtcblx0XHRcdFwia2V5XCI6IFwiWU9VVFVCRVwiLFxuXHRcdFx0XCJzeW5vbnltXCI6IFwieW91dHViZVwiLFxuXHRcdFx0XCJjbGFzc1wiOiBcInlvdXR1YmVcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJrZXlcIjogXCJUV0lUVEVSXCIsXG5cdFx0XHRcInN5bm9ueW1cIjogXCJ0d2l0dGVyXCIsXG5cdFx0XHRcImNsYXNzXCI6IFwidHdpdHRlclwiXG5cdFx0fVxuXHRdXG59O1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9kYXRhL3NvY2lhbC5qc29uXG4gKiogbW9kdWxlIGlkID0gNzFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xuXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9zZWFyY2hSZXN1bHRzLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcblxuY29uc3QgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxuLyog0JrQvtC90YLRgNC+0LvQu9C10YAg0LTQu9GPINGD0L/RgNCw0LLQu9C10L3QuNGPICDQvtGB0L3QvtCy0L3Ri9C8INGB0LrQtdC70LXRgtC+0Lwg0LTQvtC60YPQvNC10L3RgtCwICovXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRodHRwLCAkc2NvcGUsICRsb2NhdGlvbiwgJHRpbWVvdXQsICRjb29raWVzLCAkY29va2llU3RvcmUpIHtcbiAgY29uc29sZS5sb2coJ01haW4gY29udHJvbGxlciBsb2FkZWQnKVxuICBjb25zb2xlLmxvZygkY29va2llcylcbiAgLyogU3RhbmRhbG9uZSBtb2R1bGUgZm9yIGJkICovXG4gICRzY29wZS5kYiA9IHJlcXVpcmUoJy4uL21vZHVsZXMvZGInKVxuICAkc2NvcGUuZGIuaW5pdCgkaHR0cClcbiAgd2luZG93LmRiID0gJHNjb3BlLmRiXG5cbiAgLyogSW5pdGlhbGl6ZSBkYXRhICovXG4gIHRoaXMuaW5pdCA9IGZ1bmN0aW9uKCkge1xuICAgIC8qIHZhcmlhYmxlcyBmb3IgdGVzdGluZyAqL1xuICAgIHRoaXMuaGVsbG89XCJoaVwiXG4gICAgdGhpcy5ib29sZWFuID0gdHJ1ZVxuICAgIHRoaXMubGlzdCA9IFsxLDIsM11cbiAgICAvKiBFbmQgdmFyaWFibGVzIGZvciB0ZXN0aW5nICovXG5cbiAgICB0aGlzLmxvYWRlciA9IHJlcXVpcmUoJy4uL21vZHVsZXMvbG9hZGVyJylcbiAgICB0aGlzLmxvYWRlcigkc2NvcGUsICR0aW1lb3V0KVxuXG4gICAgY29uc29sZS5sb2coXCJNYWluIGNvbnRyb2xsZXIgaW5pdFwiKVxuXG4gICAgdGhpcy5zb3J0aW5nQ2F0ZWdvcmllcyA9IChyZXF1aXJlKCcuLi9kYXRhL3NvcnRpbmcnKSkuaXRlbXNcbiAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IFwiTm9uZVwiXG4gICAgdGhpcy5zb3J0aW5nSWQgPSAwXG5cbiAgICB0aGlzLnNob3dGaWx0ZXJzID0gZmFsc2VcblxuICAgIGlmKHRoaXMuc29ydGluZ0NhdGVnb3JpZXMubGVuZ3RoKSB7XG4gICAgICBsZXQgdGl0bGUgPSB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzW3RoaXMuc29ydGluZ0lkXS50aXRsZVxuICAgICAgbGV0IGFyciA9IHRpdGxlLnNwbGl0KFwiXCIpXG4gICAgICB0aGlzLmFycm93ID0gYXJyLnBvcCgpXG4gICAgICBhcnIucG9wKClcblxuICAgICAgdGhpcy5jdXJyZW50Q2F0ZWdvcnkgPSBhcnIuam9pbihcIlwiKVxuICAgIH1cbiAgICBlbHNlIGNvbnNvbGUuZXJyb3IobmV3IEVycm9yKFwiTm8gc29ydGluZyBvcHRpb25zIGZvdW5kXCIpKVxuXG4gICAgdGhpcy5zaG93aW5nQ2F0ZWdvcmllcyA9IGZhbHNlXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gdHJ1ZVxuICB9XG5cbiAgdGhpcy5kaXNwbGF5RmlsdGVycyA9IGZ1bmN0aW9uKCkge1xuICAgIHRoaXMuc2hvd0ZpbHRlcnMgPSB0cnVlXG4gICAgY29uc3QgbmF2ID0gZG9jdW1lbnQuZ2V0RWxlbWVudHNCeVRhZ05hbWUoJ25hdicpWzBdXG5cblxuICAgIGxldCBoYW5kbGVyID0gZnVuY3Rpb24oZSkge1xuICAgICAgbGV0IHggPSBlLmNsaWVudFgsXG4gICAgICAgICAgeSA9IGUuY2xpZW50WSxcbiAgICAgICAgICByZWN0ID0gbmF2LmdldEJvdW5kaW5nQ2xpZW50UmVjdCgpXG5cbiAgICAgIGlmKCAhKHggPiByZWN0LmxlZnQgJiYgeCA8IHJlY3QucmlnaHQgJiYgeSA+IHJlY3QudG9wICYmIHkgPCByZWN0LmJvdHRvbSkgKSB7XG4gICAgICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcbiAgICAgICAgdGhpcy5zaG93RmlsdGVycyA9IGZhbHNlXG4gICAgICAgICRzY29wZS4kYXBwbHkoKVxuICAgICAgfVxuICAgIH0uYmluZCh0aGlzKVxuXG4gICAgZG9jdW1lbnQuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBoYW5kbGVyKVxuICB9XG5cbiAgdGhpcy5zaG93Q2F0ZWdvcmllcyA9ICgpID0+IHtcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gdHJ1ZVxuICAgICR0aW1lb3V0KCAoKSA9PiB7XG4gICAgICB0aGlzLnNldHRpbmdDYXQgPSBmYWxzZVxuICAgIH0sIDI1MClcblxuICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSB0cnVlXG4gIH1cblxuICAvKiBTb3J0aW5nIGluIGhlYWRlciAqL1xuICB0aGlzLnNldENhdGVnb3J5ID0gaWQgPT4ge1xuICAgIHRoaXMuc2V0dGluZ0NhdCA9IGZhbHNlXG5cbiAgICBsZXQgcmVzID0gdGhpcy5zb3J0aW5nQ2F0ZWdvcmllcy5maWx0ZXIoZWwgPT4gZWwuaWQgPT09IGlkIHwgMClbMF1cbiAgICB0aGlzLnNvcnRpbmdJZCA9IGlkXG5cbiAgICBpZihyZXMpIHtcbiAgICAgIGxldCBhcnIgPSByZXMudGl0bGUuc3BsaXQoXCJcIilcbiAgICAgIHRoaXMuYXJyb3cgPSBhcnIucG9wKClcbiAgICAgIGFyci5wb3AoKVxuXG4gICAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IGFyci5qb2luKFwiXCIpXG4gICAgfVxuXG4gIH1cblxuICB0aGlzLmxvZ291dCA9IGZ1bmN0aW9uKCkge1xuICAgICAgZGIudXNlckxvZ291dCgpXG4gICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnLycpXG4gIH1cblxuICAvKiBXYXRjaCBhbGwgY2xpY2sgb24gdGhlIGJvZHkgKi9cbiAgdGhpcy5jbGljayA9ICgpID0+IHtcbiAgICBpZih0aGlzLnNob3dpbmdDYXRlZ29yaWVzICYmICF0aGlzLnNldHRpbmdDYXQpXG4gICAgICB0aGlzLnNob3dpbmdDYXRlZ29yaWVzID0gZmFsc2VcbiAgfVxuXG5cbiAgLyogQ29ycmVjdCByZWRpcmVjdCB0byB1cmwgdGhyb3VnaCBhcHAgcm91dGVyKi9cbiAgJHNjb3BlLnJlZGlyZWN0VG9VcmwgPSAodXJsLCBpbW1lZGlhdGUpID0+IHtcbiAgICBpZihpbW1lZGlhdGUpXG4gICAgICAkbG9jYXRpb24ucGF0aCh1cmwpXG4gICAgZWxzZVxuICAgICAgJHRpbWVvdXQoKCkgPT4ge1xuICAgICAgICAkbG9jYXRpb24ucGF0aCh1cmwpXG4gICAgICB9LCAyNTApXG4gIH1cblxuICAvKiBVc2UgdGhpcyBtZXRob2QgZm9yIGdsb2JhbCBwdXJwb3NlIGVycm9ycyAqL1xuICAkc2NvcGUuZGlzcGxheUVycm9yID0gdGV4dCA9PiB7XG4gICAgYWxlcnQodGV4dClcbiAgICBjb25zb2xlLmVycm9yKG5ldyBFcnJvcihcInRleHRcIikpXG4gIH1cblxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvbWFpbi5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5sZXQgdXRpbHMgPSByZXF1aXJlKCcuL3V0aWxzJyksXG4gICAgY29uZmlnID0gcmVxdWlyZSgnLi4vZGF0YS9jb25maWcnKVxuXG5sZXQgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxuXG5jb25zdCBERUZBVUxUX0lNQUdFID0gXCJcIlxuXG5tb2R1bGUuZXhwb3J0cy5pbml0ID0gZnVuY3Rpb24oJGh0dHApIHtcbiAgLyogaW5pdCBkYXRhIGZyb20gZGF0YWJhc2UgaGVyZSAqL1xuICBjdHguc2V0RGVmYXVsdHMoKVxuICBjdHgudHJhbnNwb3J0ID0gJGh0dHBcblxuICBjdHguY2hlY2tVc2VySXNMb2dnZWQoZnVuY3Rpb24oZXJyLCBkYXRhKSB7XG4gICAgaWYoZXJyKSBjb25zb2xlLmVycm9yKGVycilcbiAgICBlbHNlIHtcbiAgICAgIGlmKGRhdGEpIGN0eC5zYXZlVXNlckRhdGEoZGF0YSlcbiAgICAgIGVsc2UgY29uc29sZS5sb2coXCJVc2VyIGlzIG5vdCBsb2dnZWQgaW5cIilcbiAgICB9XG4gIH0uYmluZCh0aGlzKSlcblxuICBjb25zb2xlLmxvZyhcIkRhdGFiYXNlIGluaXRpYWxpemVkXCIpXG59XG5cbm1vZHVsZS5leHBvcnRzLnNldERlZmF1bHRzID0gZnVuY3Rpb24oKSB7XG4gIGNvbnNvbGUubG9nKFwiRGF0YWJhc2UgOjogZGVmYXVsdHMgc2V0XCIpXG5cbiAgY3R4LmlzTG9nZ2VkID0gZmFsc2VcblxuICBjdHgudXNlciA9IHtcbiAgICBlbWFpbEdlbmVyYWwgOiBcIlwiLFxuICAgIGVtYWlsQ29udGFjdCA6IFwiXCIsXG4gICAgcGhvbmVHZW5lcmFsIDogXCJcIixcbiAgICBwaG9uZUNvbnRhY3QgOiBcIlwiLFxuICAgIHdvcmtwbGFjZSA6IFwiXCIsXG4gICAgcG9zaXRpb24gOiBcIlwiLFxuICAgIHNreXBlTmFtZSA6IFwiXCIsXG4gICAgd2Vic2l0ZSA6IFwiXCIsXG4gICAgc29jaWFsIDogW10sXG4gICAgYXZhdGFyIDogXCJcIlxuICB9XG4gIGN0eC51bnJlYWRNZXNzYWdlcyA9IDBcbiAgY3R4LnVucmVhZE5vdGlmaWNhdGlvbnMgPSAwXG59XG5cbm1vZHVsZS5leHBvcnRzLnJlZ2lzdGVyID0gZnVuY3Rpb24oZGF0YSwgY2IpIHtcbiAgY3R4LnRyYW5zcG9ydCh7XG4gICAgbWV0aG9kIDogY29uZmlnLnJvdXRlcy5yZWdpc3Rlci5tZXRob2QsXG4gICAgdXJsIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5yZWdpc3Rlci51cmwsXG4gICAgZGF0YSA6IGRhdGEsXG4gICAgaGVhZGVycyA6IHtcbiAgICAgIFwiQ29udGVudC1UeXBlXCIgOiBcImFwcGxpY2F0aW9uL2pzb25cIixcbiAgICB9LFxuICAgIHdpdGhDcmVkZW50aWFscyA6IHRydWVcbiAgfSlcbiAgLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhLmRhdGEpKVxuICAuY2F0Y2goY2IpXG59XG5cbm1vZHVsZS5leHBvcnRzLmNoZWNrRW1haWwgPSBmdW5jdGlvbihlbWFpbCwgY2IpIHtcbiAgY3R4LnRyYW5zcG9ydCh7XG4gICAgbWV0aG9kIDogY29uZmlnLnJvdXRlcy5jaGVja0VtYWlsLm1ldGhvZCxcbiAgICB1cmwgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmNoZWNrRW1haWwudXJsLFxuICAgIGRhdGEgOiBlbWFpbCxcbiAgICBoZWFkZXJzIDoge1xuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwidGV4dC9wbGFpblwiXG4gICAgfSxcbiAgICB3aXRoQ3JlZGVudGlhbHMgOiB0cnVlXG4gIH0pXG4gIC50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YS5kYXRhKSlcbiAgLmNhdGNoKGNiKVxufVxuXG5tb2R1bGUuZXhwb3J0cy5sb2dpbiA9IGZ1bmN0aW9uKCBkYXRhLCBjYiApIHtcbiAgY3R4LnRyYW5zcG9ydCh7XG4gICAgbWV0aG9kIDogY29uZmlnLnJvdXRlcy5sb2dpbi5tZXRob2QsXG4gICAgdXJsIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5sb2dpbi51cmwsXG4gICAgZGF0YSA6IGRhdGEsXG4gICAgaGVhZGVycyA6IHtcbiAgICAgIFwiQ29udGVudC1UeXBlXCIgOiBcImFwcGxpY2F0aW9uL2pzb25cIixcbiAgICB9LFxuICAgIHdpdGhDcmVkZW50aWFscyA6IHRydWVcbiAgfSlcbiAgLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhLmRhdGEpKVxuICAuY2F0Y2goY2IpXG59XG5cbi8qIFRoaXMgbWV0aG9kIGRvZXMgc2F2ZXMgdXNlciBkYXRhIGluIHRoaXMgbW9kdWxlIG9ubHksIG5vIGJhY2tlbmQgY29tbXVuaWNhdGlvbiAqL1xubW9kdWxlLmV4cG9ydHMuc2F2ZVVzZXJEYXRhID0gZnVuY3Rpb24oZGF0YSkge1xuICBpZiggIWRhdGEgKSByZXR1cm4gdGhpcy5pc0xvZ2dlZCA9IGZhbHNlXG5cbiAgZGF0YSA9IGRhdGEgfHwgXCJcIlxuXG4gIHRoaXMuaXNMb2dnZWQgPSB0cnVlXG4gIHRoaXMudXNlci5lbWFpbEdlbmVyYWwgPSBkYXRhLmNvbnRhY3QuZW1haWxHZW5lcmFsIHx8IFwiXCJcbiAgdGhpcy51c2VyLmVtYWlsQ29udGFjdCA9IGRhdGEuY29udGFjdC5jb250YWN0RW1haWxzWzBdIHx8IFwiXCJcbiAgdGhpcy51c2VyLnBob25lR2VuZXJhbCA9IGRhdGEubWFpblBob25lTnVtYmVyXG4gIHRoaXMudXNlci5waG9uZUNvbnRhY3QgPSBkYXRhLmNvbnRhY3QuY29udGFjdFBob25lc1swXSB8fCBcIlwiXG4gIHRoaXMudXNlci53b3JrcGxhY2UgPSBkYXRhLmNvbnRhY3QuY29tcGFueU5hbWUgfHwgXCJcIlxuICB0aGlzLnVzZXIucG9zaXRpb24gPSBkYXRhLmNvbnRhY3QucG9zaXRpb24gfHwgXCJcIlxuICB0aGlzLnVzZXIuc2t5cGVOYW1lID0gZGF0YS5jb250YWN0LnNreXBlVXNlck5hbWUgfHwgXCJcIlxuICB0aGlzLnVzZXIud2Vic2l0ZSA9IFwiXCJcbiAgdGhpcy51c2VyLnNvY2lhbCA9IGRhdGEuY29udGFjdC5zb2NOZXRMaW5rIHx8IFtdXG4gIHRoaXMudXNlci5hdmF0YXJJZCA9IGRhdGEuaW1nSWQgfHwgREVGQVVMVF9JTUFHRVxuXG4gIC8qIFRPRE86INGA0LDRgdC/0LDRgNGB0LjRgtGMINC00LDQvdC90YvQtSDQsiDQvtGB0LzRi9GB0LvQtdC90L3Ri9C1INC/0LXRgNC10LzQtdC90L3Ri9C1ICovXG5cbiAgY29uc29sZS5sb2coXCJEYXRhYmFzZTo6IFVzZXIgZGF0YSBzYXZlZCBzdWNjZXNzZnVsbHkoINGI0YPRgtC60LAgKSBcIilcbn1cblxubW9kdWxlLmV4cG9ydHMuY2hlY2tVc2VySXNMb2dnZWQgPSBmdW5jdGlvbiggY2IgKSB7XG4gIGN0eC50cmFuc3BvcnQoe1xuICAgIG1ldGhvZDogY29uZmlnLnJvdXRlcy5jaGVja0xvZ2dlZC5tZXRob2QsXG4gICAgdXJsOiBjb25maWcuYXBpLnVybCArIGNvbmZpZy5yb3V0ZXMuY2hlY2tMb2dnZWQudXJsLFxuICAgIHdpdGhDcmVkZW50aWFscyA6IHRydWVcbiAgfSlcbiAgLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhLmRhdGEpKVxuICAuY2F0Y2goY2IpXG59XG5cbm1vZHVsZS5leHBvcnRzLnVzZXJMb2dvdXQgPSBmdW5jdGlvbigpIHtcbiAgY3R4LnNldERlZmF1bHRzKClcblxuICBjdHgudHJhbnNwb3J0KHtcbiAgICBtZXRob2Q6IGNvbmZpZy5yb3V0ZXMubG9nb3V0Lm1ldGhvZCxcbiAgICB1cmwgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmxvZ291dC51cmxcbiAgfSkudGhlbigoKT0+e30sICgpPT57fSlcbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvZGIuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcbi8qXG4gIEV4cGVjdCBvcHRpb25zIG9iamVjdCBsaWtlIHRoaXM6XG4gIHtcbiAgICBcIm1ldGhvZFwiIDogXCJQT1NUXCIsXG4gICAgXCJ1cmxcIiA6IFwiaHR0cDovL3NvbWV1cmwuY29tL1wiLFxuICAgIFwiZGF0YVwiIDogXCJkYXRhXCIsXG4gICAgXCJoZWFkZXJzXCIgOiB7XG4gICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXG4gICAgICBcIkNvbnRlbnQtTGVuZ3RoXCIgOiBcIjEwMjNcIlxuICAgIH1cbiAgfVxuXG4gIERFRkFVTFRTOlxuICBNZXRob2QgLSBkZWZhdWx0IGlzIEdFVCxcbiAgVVJMIC0gcmVxdWlyZWQsXG4gIGRhdGEgLSBvcHRpb25hbCxcbiAgaGVhZGVycyAtIG9wdGlvbmFsXG4qL1xuXG5tb2R1bGUuZXhwb3J0cy5yZXF1ZXN0ID0gZnVuY3Rpb24ob3B0aW9ucykge1xuICByZXR1cm4gbmV3IFByb21pc2UoIChyZXNvbHZlLCByZWplY3QpID0+IHtcbiAgICAvKiBTZXR0aW5nIGRlZmF1bHRzICovXG4gICAgbGV0IHsgbWV0aG9kPVwiR0VUXCIsIHVybCwgZGF0YSwgaGVhZGVycyB9ID0gb3B0aW9uc1xuXG4gICAgLyogU29tZSB2YWxpZGF0aW9uICovXG4gICAgaWYoIXVybClcbiAgICAgIHJldHVybiBjb25zb2xlLmVycm9yKFwiVXJsIG5vdCBzcGVjaWZpZWRcIilcblxuICAgIGlmKCAobWV0aG9kID09IFwiUE9TVFwiIHx8IG1ldGhvZCA9PSBcIlBVVFwiKSAmJiAhZGF0YSlcbiAgICAgIHJldHVybiBjb25zb2xlLmVycm9yKFwiTm90aGluZyB0byBzZW5kIGhlcmUgPSlcIilcblxuICAgIC8qIFN0YXJ0IGNvbnN0cnVjdGluZyByZXF1ZXN0ICovXG4gICAgdmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpXG4gICAgeGhyLm9wZW4obWV0aG9kLCB1cmwsIHRydWUpXG5cbiAgICBpZihoZWFkZXJzKVxuICAgICAgZm9yKCB2YXIgcHJvcCBpbiBoZWFkZXJzKVxuICAgICAgICB4aHIuc2V0UmVxdWVzdEhlYWRlcihwcm9wLCBoZWFkZXJzW3Byb3BdKVxuXG4gICAgaWYoZGF0YSAmJiBoZWFkZXJzWydDb250ZW50LVR5cGUnXSAhPT0gXCJ0ZXh0L3BsYWluXCIpXG4gICAgICB4aHIuc2VuZChKU09OLnN0cmluZ2lmeShkYXRhKSlcbiAgICBlbHNlIGlmKGRhdGEpXG4gICAgICB4aHIuc2VuZChkYXRhKVxuICAgIGVsc2VcbiAgICAgIHhoci5zZW5kKClcblxuXG5cbiAgICB4aHIub25yZWFkeXN0YXRlY2hhbmdlID0gZnVuY3Rpb24oKSB7XG4gICAgICBpZiAodGhpcy5yZWFkeVN0YXRlICE9IDQpXG4gICAgICAgIHJldHVyblxuXG4gICAgICBpZiAodGhpcy5zdGF0dXMgIT0gMjAwKVxuICAgICAgICByZXR1cm4gcmVqZWN0KCdFcnJvcjogJyArICh0aGlzLnN0YXR1cyA/IGAoJHt0aGlzLnN0YXR1c30pICR7dGhpcy5zdGF0dXNUZXh0fWA6ICdyZXF1ZXN0IGZhaWwnKSlcbiAgICAgIGVsc2VcbiAgICAgICAgcmV0dXJuIHJlc29sdmUodGhpcy5yZXNwb25zZVRleHQpXG5cbiAgICB9XG5cbiAgfSlcbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvdXRpbHMuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IHtcblx0XCJhcGlcIjoge1xuXHRcdFwidXJsXCI6IFwiaHR0cDovLzkzLjczLjEwOS4zODo4MDgxL1wiLFxuXHRcdFwiYXV0aFwiOiBcImh0dHA6Ly85My43My4xMDkuMzg6ODA4My9cIlxuXHR9LFxuXHRcInJvdXRlc1wiOiB7XG5cdFx0XCJnZXRCdWxsZXRpbnNcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImFwaS9yZXN0L29mZmVyc1NlcnZpY2Uvb2ZmZXIvcmVhZC9hbGxcIixcblx0XHRcdFwiYWNjZXB0XCI6IHtcblx0XHRcdFx0XCJza2lwXCI6IDAsXG5cdFx0XHRcdFwibGltaXRcIjogMjBcblx0XHRcdH1cblx0XHR9LFxuXHRcdFwibG9naW5cIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImFwaS9vYXV0aC9sb2dpblwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIixcblx0XHRcdFx0XCJwYXNzd29yZFwiOiBcIjEyMzQ1NlwiXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImxvZ291dFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIkdFVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvb2F1dGgvbG9nb3V0XCJcblx0XHR9LFxuXHRcdFwicmVnaXN0ZXJcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImFwaS9vYXV0aC9yZWdpc3RlclwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIixcblx0XHRcdFx0XCJwYXNzd29yZFwiOiBcIjEyMzQ1NlwiXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImNoZWNrRW1haWxcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImFwaS9vYXV0aC9sb2dpbi9jaGVja0VtYWlsXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwiZW1haWxcIjogXCJzc3MyQGdtYWlsLmNvbVwiXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImNoZWNrTG9nZ2VkXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiR0VUXCIsXG5cdFx0XHRcInVybFwiOiBcImFwaS9yZXN0L3Byb2ZpbGVzU2VydmljZS9wcm9maWxlL3JlYWQvbG9nZ2VkSW5Qcm9maWxlXCJcblx0XHR9XG5cdH1cbn07XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2RhdGEvY29uZmlnLmpzb25cbiAqKiBtb2R1bGUgaWQgPSA3N1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmNvbnN0IFRFTVBMQVRFX1JFTkRFUl9ERUxBWSA9IDUwMCxcbiAgICAgIEFOSU1BVElPTl9ERUxBWSA9IDkwMFxuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSwgJHRpbWVvdXQpIHtcbiAgbGV0IGNvdmVyID0gZG9jdW1lbnQuZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnY292ZXInKVswXVxuICBsZXQgY29udGVudCA9IGRvY3VtZW50LmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2dlbmVyYWwtY29udGVudCcpWzBdXG5cbiAgbGV0IGRpc3BsYXkgPSB0cnVlXG5cbiAgJHNjb3BlLiRvbignJHJvdXRlQ2hhbmdlU3VjY2VzcycsIGZ1bmN0aW9uKCkge1xuICAgIGlmKCBkaXNwbGF5ICkge1xuICAgICAgJHRpbWVvdXQoZnVuY3Rpb24oKSB7XG4gICAgICAgIGNvbnRlbnQuc3R5bGUuZGlzcGxheSA9IFwiXCJcbiAgICAgICAgY292ZXIuY2xhc3NMaXN0LmFkZCgnaGlkZScpXG4gICAgICAgICR0aW1lb3V0KGZ1bmN0aW9uKCkge1xuICAgICAgICAgIGNvdmVyLnN0eWxlLmRpc3BsYXk9XCJub25lXCJcbiAgICAgICAgfS5iaW5kKHRoaXMpLCBBTklNQVRJT05fREVMQVkpXG4gICAgICB9LmJpbmQodGhpcyksIFRFTVBMQVRFX1JFTkRFUl9ERUxBWSlcblxuICAgICAgZGlzcGxheSA9IGZhbHNlXG4gICAgfVxuICB9LmJpbmQodGhpcykpXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2xvYWRlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcIml0ZW1zXCI6IFtcblx0XHR7XG5cdFx0XHRcImlkXCI6IDEsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa+XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDIsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa0XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDMsXG5cdFx0XHRcInRpdGxlXCI6IFwi0LTQsNGC0LAgIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA0LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItC00LDRgtCwIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA1LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA2LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH1cblx0XVxufTtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vZGF0YS9zb3J0aW5nLmpzb25cbiAqKiBtb2R1bGUgaWQgPSA3OVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIl0sIm1hcHBpbmdzIjoiO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDdENBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQURBO0FBSUE7QUFEQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBO0FBRUE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7Ozs7OztBQ2hEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7QUNQQTs7OztBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDaERBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNyUEE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNwQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ1BBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDUEE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FDcEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNQQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDL0JBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ0pBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFMQTtBQUNBO0FBT0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBSUE7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQXJCQTtBQXVCQTs7Ozs7O0FDMUJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQWpDQTtBQW1DQTs7Ozs7O0FDdENBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQU5BO0FBUUE7QUFDQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUlBO0FBQ0E7QUFBQTtBQUFBO0FBRUE7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFwQkE7QUF1QkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFoR0E7QUFrR0E7Ozs7OztBQzFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBUUE7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBbERBO0FBb0RBOzs7Ozs7QUN2REE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQXRFQTtBQXdFQTs7Ozs7O0FDN0VBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FBTUE7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUMxRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFEQTtBQTVEQTs7Ozs7Ozs7QUNGQTs7Ozs7O0FDQUE7QUFDQTtBQUNBOzs7Ozs7QUNGQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFNQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDckJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUdBOzs7Ozs7QUM1QkE7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDMURBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBQ0E7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDekVBO0FBQ0E7Ozs7O0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQUE7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7OztBQUFBOzs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7OztBQUVBO0FBQ0E7QUFHQTs7Ozs7O0FBR0E7Ozs7OztBQ2xEQTtBQUNBOzs7OztBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUFBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7QUFBQTs7O0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBOzs7QUFFQTtBQUNBO0FBR0E7Ozs7OztBQUdBOzs7Ozs7QUNyQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUN0Q0E7QUFDQTtBQUNBOzs7Ozs7QUNGQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7Ozs7OztBQ3hIQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFWQTtBQVlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUdBO0FBUEE7QUFTQTtBQUFBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFHQTtBQVBBO0FBU0E7QUFBQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBR0E7QUFQQTtBQVNBO0FBQUE7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQUE7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTs7Ozs7O0FDaElBO0FBQ0E7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFtQkE7QUFDQTtBQUNBO0FBREE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUNBO0FBR0E7QUFDQTtBQUFBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBREE7QUFDQTtBQVdBO0FBQ0E7QUFDQTtBQUVBO0FBS0E7QUFFQTs7Ozs7OztBQzVEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUM5Q0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDdkJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Iiwic291cmNlUm9vdCI6IiJ9