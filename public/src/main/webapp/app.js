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

	// let app = angular.module('gup', ['ngRoute', 'ngCookies'])
	var app = angular.module('gup', ['ngRoute']);

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
	}]).controller('mainCtrl', __webpack_require__(71));

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
	exports.push([module.id, "body {\n  background-color: #ECECEC; }\n\n.calendar, .addCalendar {\n  position: relative;\n  background: url(" + __webpack_require__(20) + ") no-repeat center left;\n  padding-left: 45px;\n  box-sizing: border-box;\n  margin-bottom: 40px;\n  box-sizing: border-box; }\n  .calendar > .defaultValue, .addCalendar > .defaultValue {\n    border-bottom: 1px solid grey;\n    background: url(" + __webpack_require__(13) + ") no-repeat center right 5px;\n    cursor: pointer;\n    padding-right: 15px;\n    box-sizing: border-box; }\n    .calendar > .defaultValue > p, .addCalendar > .defaultValue > p {\n      text-align: left;\n      color: #262626;\n      font: 400 14px / 20px Roboto; }\n  .calendar > .listValue, .addCalendar > .listValue {\n    display: none; }\n\nheader {\n  height: 70px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #1875D0; }\n\n.btn-blue, .btn-grey {\n  position: relative;\n  display: inline-block;\n  overflow: hidden;\n  border-radius: 5px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  cursor: pointer;\n  text-align: center; }\n\n.btn-grey {\n  background-color: #D8D8D8;\n  color: #868686;\n  font: 400 14px / 21px Roboto; }\n\n.container {\n  width: 1280px;\n  position: relative;\n  margin: 0 auto; }\n\n.clearfix:before, .clearfix:after {\n  content: \" \";\n  display: table; }\n\n.clearfix:after {\n  clear: both; }\n\n.ink {\n  display: block;\n  position: absolute;\n  background: rgba(0, 0, 0, 0.15);\n  border-radius: 100%;\n  -webkit-transform: scale(0);\n          transform: scale(0); }\n\n.ink.animate {\n  -webkit-animation: ripple .5s ease-in;\n          animation: ripple .5s ease-in; }\n\n@-webkit-keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n@keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n.headLeft {\n  padding-top: 5px;\n  display: inline-block;\n  width: calc(100% - 490px);\n  height: 70px;\n  box-sizing: border-box; }\n  .headLeft > .logo {\n    cursor: pointer;\n    border-radius: 50%;\n    float: left;\n    height: 60px;\n    width: 60px;\n    background: url(" + __webpack_require__(4) + ") no-repeat;\n    margin-left: 15px; }\n  .headLeft > .inputForm {\n    margin-top: 21px;\n    margin-left: 20px;\n    float: left;\n    border-color: #FDFDFD;\n    box-sizing: border-box;\n    height: auto;\n    width: 200px;\n    border-bottom: 1px solid #FDFDFD; }\n    .headLeft > .inputForm > label, .headLeft > .inputForm input {\n      color: #ffffff;\n      font: 400 14px Roboto; }\n  .headLeft > .selectBox {\n    float: left;\n    margin-top: 21px;\n    margin-left: 20px; }\n    .headLeft > .selectBox > .defaultValue {\n      background: none;\n      padding: 0 5px; }\n  .headLeft > .add {\n    cursor: pointer;\n    float: right;\n    height: 35px;\n    width: 200px;\n    margin-right: 20px;\n    margin-top: 10px;\n    background: url(" + __webpack_require__(5) + ") no-repeat center right 10px;\n    padding-left: 10px;\n    text-align: left;\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    border: 1px solid white;\n    -webkit-transition: all .15s;\n    transition: all .15s; }\n    .headLeft > .add > p {\n      color: #ffffff;\n      font: 400 14px / 35px Roboto; }\n\n.headRight {\n  float: right;\n  width: 490px;\n  height: 70px;\n  border-left: 1px solid grey;\n  box-sizing: border-box;\n  padding-top: 22px; }\n  .headRight > .mail {\n    height: 26px;\n    width: 33px;\n    cursor: pointer;\n    position: relative;\n    float: left;\n    background: url(" + __webpack_require__(6) + ") no-repeat center center;\n    margin-left: 20px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .mail > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -3px;\n      left: 32px;\n      cursor: default; }\n    .headRight > .mail:hover {\n      background: url(" + __webpack_require__(7) + ") no-repeat center center; }\n  .headRight > .bell {\n    height: 24px;\n    width: 23px;\n    cursor: pointer;\n    position: relative;\n    display: inline-block;\n    float: left;\n    margin-left: 30px;\n    background: url(" + __webpack_require__(8) + ") no-repeat center center;\n    border-radius: 15px 0 15px 10px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .bell > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -7px;\n      left: 22px;\n      cursor: default; }\n    .headRight > .bell:hover {\n      background: url(" + __webpack_require__(9) + ") no-repeat center center; }\n  .headRight > .services {\n    height: 27px;\n    width: 28px;\n    cursor: pointer;\n    float: right;\n    margin-right: 30px;\n    background: url(" + __webpack_require__(10) + ") no-repeat center center;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .services:hover {\n      background: url(" + __webpack_require__(11) + ") no-repeat center center; }\n  .headRight > .userName {\n    float: right;\n    margin-right: 25px;\n    background: url(" + __webpack_require__(12) + ") no-repeat center left;\n    padding-left: 30px;\n    max-width: 270px;\n    position: relative; }\n    .headRight > .userName > p {\n      color: #ffffff;\n      font: 400 14px / 27px Roboto;\n      cursor: pointer;\n      white-space: nowrap;\n      overflow: hidden;\n      text-overflow: ellipsis; }\n    .headRight > .userName > div {\n      background-color: #FDFDFD;\n      box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n      position: absolute;\n      top: 0;\n      width: 100%;\n      z-index: 1; }\n      .headRight > .userName > div > p {\n        cursor: pointer;\n        padding: 0 15px;\n        height: 48px;\n        font: 400 16px / 50px Roboto;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        box-sizing: border-box;\n        transition: all .25s; }\n        .headRight > .userName > div > p:hover {\n          background-color: #eeeeee; }\n  .headRight > .auth {\n    color: white;\n    float: right;\n    margin-right: 20px;\n    font: 400 14px / 26px Roboto; }\n    .headRight > .auth span {\n      cursor: pointer;\n      margin: 0 10px; }\n\n.inputSearch {\n  position: relative;\n  display: inline-block;\n  margin-top: 10px;\n  border-bottom: 1px solid white; }\n  .inputSearch > input {\n    border: none;\n    padding: 2px 0;\n    background-color: transparent;\n    outline: none;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto; }\n  .inputSearch > label {\n    position: absolute;\n    top: 0;\n    left: 0;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto;\n    cursor: text;\n    -webkit-transition: .5s;\n    transition: .5s; }\n\n.selectBox {\n  position: relative;\n  display: inline-block;\n  cursor: pointer; }\n  .selectBox > .defaultValue {\n    color: #ffffff;\n    font: 400 14px / 19px Roboto;\n    overflow: hidden;\n    border-bottom: 1px solid white;\n    background: url(" + __webpack_require__(13) + ") no-repeat center right 5px;\n    padding-right: 20px;\n    box-sizing: border-box; }\n    .selectBox > .defaultValue span {\n      font-size: 16px; }\n  .selectBox > .listOfValues {\n    position: absolute;\n    top: 0;\n    left: 0;\n    background-color: white;\n    z-index: 1;\n    box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n    -webkit-animation: animatetop .25s;\n            animation: animatetop .25s; }\n    .selectBox > .listOfValues > div {\n      cursor: pointer;\n      padding: 0 15px;\n      height: 50px;\n      width: 120px;\n      font: 400 16px / 50px Roboto;\n      -webkit-transition: all .25s;\n      transition: all .25s; }\n      .selectBox > .listOfValues > div:hover {\n        background-color: #eeeeee; }\n\n@-webkit-keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\n@keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\naside.bulletinDetails {\n  float: right;\n  margin-top: 5px; }\n\nsection.openAdvertert {\n  background-color: white;\n  float: right;\n  margin-right: 10px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 715px;\n  padding: 25px 100px 45px;\n  box-sizing: border-box;\n  margin-top: 5px; }\n  section.openAdvertert > h3 {\n    color: #202020;\n    font: 400 22px / 26px Roboto;\n    float: left; }\n  section.openAdvertert > .price {\n    color: #1f1f1f;\n    font: 400 18px / 26px Roboto;\n    float: right; }\n  section.openAdvertert > .checkBox {\n    float: right;\n    margin-right: 15px;\n    margin-top: -1px; }\n  section.openAdvertert > .breadCrumbs {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: left;\n    margin-top: 10px;\n    cursor: default; }\n  section.openAdvertert > .id {\n    color: rgba(32, 32, 32, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: right;\n    cursor: default;\n    margin-top: 10px; }\n  section.openAdvertert > .slider {\n    height: 120px;\n    position: relative;\n    margin-top: 10px;\n    float: left;\n    width: 100%;\n    margin-bottom: 10px; }\n    section.openAdvertert > .slider > div {\n      float: left;\n      margin-left: 10px;\n      height: 100%;\n      width: 165px; }\n      section.openAdvertert > .slider > div:nth-of-type(1) {\n        margin: 0; }\n      section.openAdvertert > .slider > div > img {\n        -o-object-fit: contain;\n           object-fit: contain;\n        width: 100%;\n        background-color: #F4F4F4;\n        height: 100%;\n        cursor: pointer; }\n    section.openAdvertert > .slider > .next {\n      position: absolute;\n      background: url(" + __webpack_require__(14) + ") no-repeat left center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      right: -25px; }\n    section.openAdvertert > .slider > .prev {\n      position: absolute;\n      background: url(" + __webpack_require__(15) + ") no-repeat right center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      left: -25px; }\n  section.openAdvertert > div.buyProduct > #mapForOneAdvertert {\n    float: left;\n    height: 230px;\n    width: 225px;\n    margin-bottom: 25px; }\n  section.openAdvertert > div.buyProduct > .descriptionAd {\n    color: #0c0c1e;\n    font: 400 12px / 18px Roboto;\n    margin-top: 15px;\n    margin-bottom: 20px; }\n  section.openAdvertert > div.buyProduct > .goToMap {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: left; }\n  section.openAdvertert > div.buyProduct > .allComments {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right;\n    margin-right: 50px; }\n  section.openAdvertert > div.buyProduct > .writeAReview {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right; }\n  section.openAdvertert > div.buyProduct > .nameUser {\n    color: #1976d2;\n    font: 400 14px / 18px Roboto;\n    float: left;\n    margin-left: 20px;\n    cursor: default;\n    margin-bottom: 35px; }\n  section.openAdvertert > div.buyProduct > .onOrOffLineUser {\n    float: left; }\n  section.openAdvertert > div.buyProduct .btn-grey {\n    float: right;\n    height: 35px;\n    width: 140px;\n    margin-right: 25px;\n    line-height: 35px; }\n  section.openAdvertert > div.buyProduct .btn-blue {\n    float: right;\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n  section.openAdvertert > .rentProduct {\n    margin-top: 45px; }\n    section.openAdvertert > .rentProduct > .rentCalendar {\n      margin-bottom: 45px; }\n      section.openAdvertert > .rentProduct > .rentCalendar > p {\n        color: #263238;\n        font: 400 16px / 21px Roboto;\n        float: left;\n        cursor: default; }\n        section.openAdvertert > .rentProduct > .rentCalendar > p:nth-of-type(2) {\n          margin-left: 55px; }\n      section.openAdvertert > .rentProduct > .rentCalendar > .calendar {\n        float: left;\n        margin-left: 45px;\n        width: 165px;\n        margin-bottom: 0; }\n    section.openAdvertert > .rentProduct > .inputForm {\n      color: #9a9a9a;\n      margin-bottom: 40px; }\n    section.openAdvertert > .rentProduct > .btn-blue {\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 35px;\n      width: 180px;\n      line-height: 35px;\n      float: right; }\n\n.wrapForDiv {\n  width: 265px;\n  float: right;\n  overflow: hidden;\n  margin-bottom: 25px;\n  border: 1px solid #E9E9E9;\n  box-sizing: border-box; }\n  .wrapForDiv > ul.tab {\n    list-style-type: none;\n    height: 50px;\n    background-color: white;\n    border-bottom: 1px solid #E9E9E9;\n    box-sizing: border-box; }\n    .wrapForDiv > ul.tab > li {\n      float: left; }\n      .wrapForDiv > ul.tab > li > a {\n        color: #939393;\n        font: 400 14px / 50px Roboto;\n        display: block;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        text-align: center;\n        position: relative; }\n        .wrapForDiv > ul.tab > li > a:after {\n          content: '';\n          display: block;\n          position: absolute;\n          bottom: 0;\n          width: 0;\n          height: 2px;\n          background-color: #FD5151;\n          -webkit-transition: all .25s;\n          transition: all .25s; }\n        .wrapForDiv > ul.tab > li > a:focus, .wrapForDiv > ul.tab > li > a.active {\n          color: #7eafe1; }\n          .wrapForDiv > ul.tab > li > a:focus:after, .wrapForDiv > ul.tab > li > a.active:after {\n            width: 100%; }\n      .wrapForDiv > ul.tab > li:nth-of-type(1) > a {\n        width: 159px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(1) > a:after {\n          right: 0; }\n      .wrapForDiv > ul.tab > li:nth-of-type(2) > a {\n        width: 104px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(2) > a:after {\n          left: 0; }\n  .wrapForDiv > .featuresAndReviews {\n    height: 178px;\n    width: 282px;\n    background-color: #F4F4F4;\n    overflow: auto;\n    box-sizing: border-box; }\n    .wrapForDiv > .featuresAndReviews > .tabcontent {\n      display: none;\n      -webkit-animation: fadeEffect 1s;\n      animation: fadeEffect 1s; }\n      .wrapForDiv > .featuresAndReviews > .tabcontent.active {\n        display: block; }\n    .wrapForDiv > .featuresAndReviews > #reviews {\n      position: relative; }\n      .wrapForDiv > .featuresAndReviews > #reviews > div {\n        padding: 30px 20px 25px 65px;\n        position: relative;\n        border-bottom: 1px solid #E9E9E9; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div:nth-last-of-type(1) {\n          border: none; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > img {\n          position: absolute;\n          height: 30px;\n          width: 25px;\n          top: 35px;\n          left: 20px; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > p {\n          color: #0d0d1e;\n          font: 400 12px / 18px Roboto; }\n\n@-webkit-keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n@keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n.btn-blue {\n  background-color: #1875D0;\n  color: #ffffff;\n  font: 400 14px / 21px Roboto; }\n\n.onOrOffLineUser {\n  background-color: #00C652;\n  border: 1px solid white;\n  box-shadow: 1px 1px 3px 0px rgba(0, 0, 0, 0.65);\n  height: 18px;\n  width: 18px;\n  box-sizing: border-box;\n  border-radius: 50%; }\n\n.error-container {\n  width: 1103px;\n  margin: auto;\n  background-image: url(" + __webpack_require__(16) + ");\n  background-repeat: no-repeat;\n  background-position-x: right;\n  background-position-y: 80px;\n  min-height: 500px; }\n  .error-container h1 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    text-align: center;\n    margin-top: 65px; }\n  .error-container h2 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    margin-top: 189px; }\n  .error-container h3 {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 10px;\n    margin-bottom: 30px; }\n  .error-container > .btn-blue {\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n\n.clicked {\n  box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.4) !important;\n  margin-top: 12px !important; }\n\n.multiple {\n  width: 1150px;\n  margin: 5px auto 0; }\n  .multiple > .bulletin-short {\n    float: left;\n    width: 380px;\n    margin-left: 5px;\n    height: 165px;\n    padding: 15px; }\n    .multiple > .bulletin-short:nth-of-type(3n-2) {\n      margin-left: 0; }\n    .multiple > .bulletin-short:nth-of-type(1) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(2) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(3) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short > .bulletin-center-column {\n      float: none;\n      margin: 0;\n      position: absolute;\n      top: 15px;\n      right: 110px; }\n      .multiple > .bulletin-short > .bulletin-center-column > .bulletin-action {\n        bottom: -43px;\n        width: 110px;\n        right: -30px;\n        z-index: 1; }\n    .multiple > .bulletin-short > .bulletin-left-column > .bulletin-description {\n      color: #0c0c1e;\n      font: 400 12px / 18px Roboto;\n      width: 170px;\n      overflow: hidden;\n      height: 40px; }\n\n.red {\n  background-color: #F74A4A; }\n\n.inputForm-required {\n  position: relative;\n  padding: 6px 2px;\n  font: 400 16px / 24.8px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px solid #9b9b9b;\n  margin-top: 15px; }\n  .inputForm-required label {\n    position: absolute;\n    left: 2px;\n    top: 6px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n  .inputForm-required input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent; }\n\n.inputForm.required {\n  border-bottom: 1px solid #9b9b9b !important; }\n\n.inputForm {\n  position: relative;\n  font: 400 16px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px dotted #999999; }\n  .inputForm label {\n    position: absolute;\n    left: 5px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s;\n    top: -1px; }\n  .inputForm input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent;\n    position: relative;\n    padding: 1px 5px; }\n\n/* Text element animation */\n.textOut {\n  top: -15px !important;\n  font-size: 12px !important;\n  left: 5px !important; }\n\nsection.login, section.register {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  padding: 65px 230px;\n  width: 1100px;\n  margin: 5px auto 0;\n  box-sizing: border-box; }\n  section.login > h2, section.register > h2 {\n    text-align: center;\n    color: #202020;\n    font: 400 22px Roboto;\n    margin-bottom: 55px; }\n  section.login > .inputForm, section.register > .inputForm {\n    margin-bottom: 45px; }\n    section.login > .inputForm > label, section.login > .inputForm input, section.register > .inputForm > label, section.register > .inputForm input {\n      color: #9a9a9a;\n      font: 400 16px Roboto; }\n  section.login > .btn-blue, section.register > .btn-blue {\n    height: 35px;\n    width: 180px;\n    margin-left: 45px;\n    line-height: 35px; }\n    section.login > .btn-blue.vk, section.register > .btn-blue.vk {\n      background: #0186CF url(" + __webpack_require__(17) + ") no-repeat center left 10px;\n      margin-left: 0; }\n    section.login > .btn-blue.facebook, section.register > .btn-blue.facebook {\n      background: #3E50B3 url(" + __webpack_require__(18) + ") no-repeat center left 10px; }\n    section.login > .btn-blue.google, section.register > .btn-blue.google {\n      background: #FD3C00 url(" + __webpack_require__(19) + ") no-repeat center left 10px; }\n\nsection.bulletinAdd {\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #FDFDFD;\n  width: 1100px;\n  margin: 5px auto 0;\n  padding: 65px 225px;\n  box-sizing: border-box; }\n  section.bulletinAdd > h2 {\n    color: #1f1f1f;\n    font: 400 22px Roboto;\n    text-align: center;\n    margin-bottom: 40px; }\n  section.bulletinAdd > .selectBox {\n    margin-bottom: 45px;\n    margin-top: 15px; }\n    section.bulletinAdd > .selectBox > .defaultValue {\n      border-color: grey;\n      min-width: 195px;\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      padding-left: 5px; }\n  section.bulletinAdd > .file {\n    float: right;\n    position: relative;\n    background-color: #FDFDFD;\n    border-bottom: 1px solid #9e9e9e;\n    width: 410px;\n    padding-bottom: 5px; }\n    section.bulletinAdd > .file > input {\n      position: absolute;\n      z-index: -1; }\n    section.bulletinAdd > .file > p {\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      cursor: default;\n      float: left;\n      padding-top: 10px; }\n    section.bulletinAdd > .file > .btn-blue {\n      float: right;\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 30px;\n      width: 85px;\n      line-height: 30px; }\n  section.bulletinAdd > .inputForm {\n    margin-bottom: 45px; }\n    section.bulletinAdd > .inputForm > label, section.bulletinAdd > .inputForm input {\n      color: #999999;\n      font: 400 16px Roboto; }\n  section.bulletinAdd > .color {\n    margin-bottom: 40px; }\n    section.bulletinAdd > .color > p {\n      color: #999999;\n      font: 400 16px Roboto;\n      border-bottom: 1px dotted #999999;\n      width: 160px;\n      float: left;\n      height: 21px;\n      box-sizing: border-box; }\n    section.bulletinAdd > .color > div {\n      float: left;\n      margin-left: 19px;\n      margin-top: 3px;\n      width: 16px;\n      height: 15px;\n      cursor: pointer;\n      box-sizing: border-box;\n      outline: 1px solid transparent;\n      border: 1px solid white; }\n      section.bulletinAdd > .color > div.transparent {\n        border-color: #E9E9E9;\n        overflow: hidden; }\n        section.bulletinAdd > .color > div.transparent > .redStick {\n          -webkit-transform: rotate(-43deg);\n                  transform: rotate(-43deg);\n          background-color: red;\n          height: 2px;\n          margin-top: 5.5px;\n          width: 20px;\n          margin-left: -3px; }\n      section.bulletinAdd > .color > div.red {\n        background-color: red; }\n      section.bulletinAdd > .color > div.orange {\n        background-color: orange; }\n      section.bulletinAdd > .color > div.yellow {\n        background-color: yellow; }\n      section.bulletinAdd > .color > div.green {\n        background-color: green; }\n      section.bulletinAdd > .color > div.lightBlue {\n        background-color: lightBlue; }\n      section.bulletinAdd > .color > div.blue {\n        background-color: blue; }\n      section.bulletinAdd > .color > div.pink {\n        background-color: pink; }\n      section.bulletinAdd > .color > div.purple {\n        background-color: purple; }\n      section.bulletinAdd > .color > div.white {\n        background-color: white;\n        border-color: #E9E9E9; }\n      section.bulletinAdd > .color > div.gray {\n        background-color: gray; }\n      section.bulletinAdd > .color > div.black {\n        background-color: black; }\n      section.bulletinAdd > .color > div.brown {\n        background-color: brown; }\n      section.bulletinAdd > .color > div.multicolor {\n        /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#ff0000+0,ffff00+20,1dff00+40,00ffff+60,0400ff+80,ff00fa+100 */\n        background: #ff0000;\n        /* Old browsers */\n        /* FF3.6-15 */\n        background: -webkit-linear-gradient(left, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* Chrome10-25,Safari5.1-6 */\n        background: linear-gradient(to right, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */\n        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff0000', endColorstr='#ff00fa',GradientType=1 );\n        /* IE6-9 */ }\n      section.bulletinAdd > .color > div.active {\n        outline-color: red; }\n  section.bulletinAdd > .calendar, section.bulletinAdd .addCalendar {\n    width: 230px; }\n  section.bulletinAdd > .addCalendar {\n    background-image: url(" + __webpack_require__(21) + "); }\n    section.bulletinAdd > .addCalendar > .defaultValue {\n      border-bottom-color: blue;\n      background: none; }\n      section.bulletinAdd > .addCalendar > .defaultValue > p {\n        color: #1976d2;\n        font-size: 16px; }\n  section.bulletinAdd > .btn-blue {\n    height: 35px;\n    width: 180px;\n    line-height: 35px;\n    display: block;\n    margin: 0 auto; }\n\n.errors {\n  position: absolute;\n  font: 400 12px / 14px Roboto;\n  color: #dd2c00;\n  bottom: -17px; }\n\nnav {\n  width: 255px;\n  float: left;\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  margin-top: 5px;\n  z-index: 1; }\n  nav > .map {\n    background: url(" + __webpack_require__(22) + ") no-repeat;\n    height: 145px;\n    width: 255px; }\n    nav > .map > .inputForm {\n      width: 215px;\n      margin: 115px auto 0; }\n      nav > .map > .inputForm.required {\n        border-color: black !important; }\n      nav > .map > .inputForm > input, nav > .map > .inputForm label, nav > .map > .inputForm .textOut {\n        color: black !important; }\n  nav > ul {\n    list-style: none; }\n    nav > ul > li > p {\n      padding-left: 75px;\n      color: #263238;\n      font: 500 13px / 40px Roboto;\n      background: url(" + __webpack_require__(23) + ") no-repeat center right 20px;\n      cursor: pointer; }\n    nav > ul > li:hover {\n      background-color: #E6E6E6 !important; }\n    nav > ul > li:nth-of-type(1) {\n      background: url(" + __webpack_require__(24) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(2) {\n      background: url(" + __webpack_require__(25) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(3) {\n      background: url(" + __webpack_require__(26) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(4) {\n      background: url(" + __webpack_require__(27) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(5) {\n      background: url(" + __webpack_require__(28) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(6) {\n      background: url(" + __webpack_require__(29) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(7) {\n      background: url(" + __webpack_require__(30) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(8) {\n      background: url(" + __webpack_require__(31) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(9) {\n      background: url(" + __webpack_require__(32) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(10) {\n      background: url(" + __webpack_require__(33) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(11) {\n      background: url(" + __webpack_require__(34) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(12) {\n      background: url(" + __webpack_require__(35) + ") no-repeat left 25px center; }\n\n.navBtn {\n  width: 43px;\n  height: 49px;\n  position: absolute;\n  left: 0;\n  top: 90px;\n  cursor: pointer;\n  background: url(" + __webpack_require__(36) + ") no-repeat;\n  -webkit-transition: all .25s;\n  transition: all .25s;\n  z-index: 1; }\n  .navBtn:hover {\n    width: 60px;\n    background-image: url(" + __webpack_require__(37) + "); }\n\n.cover {\n  position: absolute;\n  top: 0;\n  right: 0;\n  bottom: 0;\n  left: 0;\n  background-color: rgba(0, 0, 0, 0.9);\n  z-index: 5; }\n  .cover.hide {\n    -webkit-animation: hide 1s;\n            animation: hide 1s; }\n\n@-webkit-keyframes hide {\n  100% {\n    background-color: transparent; } }\n\n@keyframes hide {\n  100% {\n    background-color: transparent; } }\n", ""]);

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
	exports.push([module.id, ".view-profile-container {\n  background-color: #fff;\n  box-sizing: border-box;\n  padding: 40px 127px 30px;\n  margin: 5px auto 0;\n  width: 1103px;\n  font: 400 16px/24px Roboto;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n  .view-profile-container h1 {\n    padding-bottom: 16px;\n    text-align: center;\n    font: 400 22px/26px Roboto; }\n  .view-profile-container input:-moz-read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n  .view-profile-container input:read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n    .view-profile-container input:-moz-read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n    .view-profile-container input:read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n  .view-profile-container .social-link-container {\n    margin-bottom: 45px; }\n\n.profile-containers-wrap {\n  width: 849px;\n  margin: 0 auto; }\n\n.profile-left-container, .profile-right-container, .profile-info-section-left, .profile-info-section-right, .profile-info {\n  float: left; }\n  .profile-left-container::after, .profile-right-container::after, .profile-info-section-left::after, .profile-info-section-right::after, .profile-info::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.view-profile::after, .view-profile-container::after {\n  content: \"\";\n  display: table;\n  clear: both; }\n\n.profile-left-container {\n  width: 190px;\n  padding: 0 52px; }\n  .profile-left-container .btn-blue {\n    width: 180px;\n    height: 36px;\n    line-height: 36px;\n    margin: 0 auto;\n    margin-top: 9px; }\n  .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox {\n    display: block;\n    margin-bottom: 25px; }\n    .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox > .defaultValue {\n      color: #9e9e9e;\n      font: 400 16px / 24px Roboto;\n      border-bottom: 1px solid #9e9e9e; }\n\n.profile-right-container {\n  width: 485px;\n  padding-left: 20px;\n  border-left: 1px solid #ebebeb; }\n  .profile-right-container > .wrapForDiv {\n    width: auto;\n    float: none;\n    margin-bottom: 0; }\n    .profile-right-container > .wrapForDiv > ul.tab > li {\n      width: 50%; }\n      .profile-right-container > .wrapForDiv > ul.tab > li > a {\n        width: auto; }\n    .profile-right-container > .wrapForDiv > .featuresAndReviews {\n      width: 500px; }\n\n.profile-info-section-left, .profile-info-section-right {\n  width: 50%;\n  box-sizing: border-box; }\n\n.profile-avatar {\n  width: 145px;\n  height: 215px;\n  margin: 0 auto 40px;\n  padding-bottom: 5px;\n  background: url(" + __webpack_require__(50) + ") no-repeat center center;\n  background-size: contain;\n  box-sizing: border-box; }\n\n.profile-settings-dropdown .profile-settings-dropdown-title {\n  cursor: pointer;\n  padding: 13px 0; }\n  .profile-settings-dropdown .profile-settings-dropdown-title::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .profile-settings-dropdown .profile-settings-dropdown-title span {\n    color: #929292;\n    float: left; }\n  .profile-settings-dropdown .profile-settings-dropdown-title div {\n    float: right;\n    background: url(" + __webpack_require__(51) + ");\n    width: 13px;\n    height: 7px; }\n\n.social-link-container div {\n  cursor: pointer;\n  width: 24px;\n  height: 24px;\n  float: left; }\n  .social-link-container div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .social-link-container div:not(:last-of-type) {\n    margin-right: 23px; }\n\n.profile-messages-and-notifications {\n  display: block;\n  width: 100%;\n  margin: 30px 0;\n  float: left; }\n  .profile-messages-and-notifications::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav, .profile-messages-and-notifications-content {\n  border: 1px solid #ebebeb;\n  overflow: hidden; }\n\n.profile-messages-and-notifications-nav div {\n  color: #929292;\n  width: 50%;\n  padding: 24px 0;\n  text-align: center;\n  float: left; }\n  .profile-messages-and-notifications-nav div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav .nav-item-selected {\n  color: #7eaee0;\n  border-bottom: 2px solid #ff5252; }\n\n.profile-messages-and-notifications-content {\n  background-color: #f6f6f6;\n  height: 180px;\n  box-sizing: border-box; }\n\n.profile-messages-and-notifications-content-item {\n  width: 100%;\n  border-bottom: 1px solid #ebebeb;\n  height: 123px;\n  position: relative;\n  overflow: hidden; }\n  .profile-messages-and-notifications-content-item .content-item-logo {\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 27px;\n    height: 27px;\n    padding: 0 23px 23px 7px;\n    background: url(" + __webpack_require__(52) + ") no-repeat;\n    display: inline-block;\n    background-position: center;\n    margin-top: 28px; }\n  .profile-messages-and-notifications-content-item .content-item-text {\n    display: inline-block;\n    float: right;\n    width: 373px;\n    margin: 28px 38px 24px 0;\n    font: 400 12px/18px Roboto;\n    color: #0c0c1e; }\n    .profile-messages-and-notifications-content-item .content-item-text::after {\n      content: \"\";\n      display: table;\n      clear: both; }\n\n.profile-info {\n  margin-bottom: 30px; }\n", ""]);

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
	  "textArea": __webpack_require__(76)
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
	        this.contactTypes = ["LEGAL_ENTITY", "ENTREPRENEUR"];

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

	module.exports = function ($http, $scope, $location, $timeout) {
	  var _this = this;

	  console.log('Main controller loaded');

	  /* Standalone module for bd */
	  $scope.db = __webpack_require__(72);
	  $scope.db.init();
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

	module.exports.init = function () {
	  /* init data from database here */
	  ctx.setDefaults();
	  // this.transport = $http
	  // console.log(this.transport)
	  ctx.checkUserIsLogged(function (err, data) {
	    if (err) console.error(err);else {
	      if (data) ctx.saveUserData(data);else console.log("User is not logged in");
	    }
	  }.bind(this));

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
	  utils.request({
	    "method": config.routes.login.method,
	    "url": config.api.auth + config.routes.login.url,
	    "data": data,
	    "headers": {
	      "Content-Type": "application/json",
	      "withCredentials": "true"
	    }
	  }).then(function (data) {
	    return cb(null, data);
	  }, function (err) {
	    return cb(err);
	  });
	  // ctx.transport({
	  //   method : config.routes.login.method,
	  //   url : config.api.auth + config.routes.login.url,
	  //   data : data,
	  //   headers : {
	  //     "Content-Type" : "application/json",
	  //   },
	  //   withCredentials : true
	  // })
	  // .then(data => cb(null, data))
	  // .catch(cb)
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

/***/ },
/* 76 */
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
	    }
	  };
		};

/***/ }
/******/ ]);
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXBwLmpzIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vL3dlYnBhY2svYm9vdHN0cmFwIDNjZWY1MjNhY2Y2MWY0Y2Y5OTBkIiwid2VicGFjazovLy9hcHAuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Jhc2ljLnNjc3M/NGY1MyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvYmFzaWMuc2NzcyIsIndlYnBhY2s6Ly8vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzIiwid2VicGFjazovLy8uL2ltYWdlcy9sb2dvLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvbWFpbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL21haWxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYmVsbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvc2VydmljZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy91c2VyTmFtZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9uZXh0LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcHJldi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2Vycm9yX2JnLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvdmsucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9mYWNlYm9vay5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2dvb2dsZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhbGVuZGFyLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9tYXAucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9Gb3JBbmltYWxzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQnVzaW5lc3MucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL1RyYW5zcG9ydC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvSXNGcmVlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9CYXJ0ZXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9ydXByLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcnVwckhvdmVyLnBuZyIsIndlYnBhY2s6Ly8vLi9+L3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzcz9jZjI3Iiwid2VicGFjazovLy8uL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3MiLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9WLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2RvbGxhckJpbGwucG5nIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzcz9kYzJiIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvcHJvZmlsZS5zY3NzPzAxYTEiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL3Byb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYXZhdGFyLmpwZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9pY29uX3VzZXIucG5nIiwid2VicGFjazovLy9tb2R1bGVzL2xvZ2dlci5qcyIsIndlYnBhY2s6Ly8vLi9kYXRhL2RlYnVnLmpzb24iLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dC5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9zZWxlY3RCb3guanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2V2ZW50cy5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9yb3V0ZXIuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2luZGV4LmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvYnVsbGV0aW5BZGQuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2xvZ2luLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9yZWdpc3Rlci5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL3NlYXJjaFJlc3VsdHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL21haW4uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvZGIuanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvdXRpbHMuanMiLCJ3ZWJwYWNrOi8vLy4vZGF0YS9jb25maWcuanNvbiIsIndlYnBhY2s6Ly8vLi9kYXRhL3NvcnRpbmcuanNvbiIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0QXJlYS5qcyJdLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSlcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcblxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0ZXhwb3J0czoge30sXG4gXHRcdFx0aWQ6IG1vZHVsZUlkLFxuIFx0XHRcdGxvYWRlZDogZmFsc2VcbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubG9hZGVkID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCJcIjtcblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXygwKTtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHdlYnBhY2svYm9vdHN0cmFwIDNjZWY1MjNhY2Y2MWY0Y2Y5OTBkXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5yZXF1aXJlKFwiLi9zdHlsZXMvYmFzaWMuc2Nzc1wiKVxyXG5yZXF1aXJlKFwiLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXCIpXHJcbnJlcXVpcmUoXCIuL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzc1wiKVxyXG5yZXF1aXJlKFwiLi9zdHlsZXMvcHJvZmlsZS5zY3NzXCIpXHJcblxyXG5yZXF1aXJlKFwiLi9tb2R1bGVzL2xvZ2dlclwiKSgpXHJcblxyXG5jb25zdCBtYXRlcmlhbHMgPSByZXF1aXJlKCcuL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzJyksXHJcbiAgICAgIHJvdXRlciA9IHJlcXVpcmUoJy4vbW9kdWxlcy9yb3V0ZXInKVxyXG5cclxuLy8gbGV0IGFwcCA9IGFuZ3VsYXIubW9kdWxlKCdndXAnLCBbJ25nUm91dGUnLCAnbmdDb29raWVzJ10pXHJcbmxldCBhcHAgPSBhbmd1bGFyLm1vZHVsZSgnZ3VwJywgWyduZ1JvdXRlJ10pXHJcblxyXG4vLyBBcHAgY29uZmlnXHJcbmFwcFxyXG4gIC5jb25maWcoWyckcm91dGVQcm92aWRlcicsICckbG9jYXRpb25Qcm92aWRlcicsIGZ1bmN0aW9uKCAkcm91dGVQcm92aWRlciwgJGxvY2F0aW9uUHJvdmlkZXIpe1xyXG4gICAgZm9yKGxldCBrZXkgaW4gcm91dGVyKVxyXG4gICAgICAkcm91dGVQcm92aWRlci53aGVuKGtleSwgcm91dGVyW2tleV0pXHJcblxyXG4gICAgJHJvdXRlUHJvdmlkZXIub3RoZXJ3aXNlKHtcclxuICAgICAgcmVkaXJlY3RUbzogJy80MDQnXHJcbiAgICB9KVxyXG5cclxuICAgICRsb2NhdGlvblByb3ZpZGVyLmh0bWw1TW9kZSh7XHJcbiAgICAgIGVuYWJsZWQgOiB0cnVlLFxyXG4gICAgICByZXF1aXJlQmFzZSA6IGZhbHNlXHJcbiAgICB9KVxyXG4gIH1dKVxyXG4gIC5jb250cm9sbGVyKCdtYWluQ3RybCcsIHJlcXVpcmUoJy4vY29udHJvbGxlcnMvbWFpbicpKVxyXG5cclxubWF0ZXJpYWxzXHJcbiAgLmluaXQoYXBwKVxyXG4gIC5ydW4oKVxyXG5cclxuICAvKiBFdmVudCBlbW1pdHRlciBleGFtcGxlcyAqL1xyXG4gIGxldCBpZCA9IGVlLm9uKCdtdWhhaGFoYScsIGZ1bmN0aW9uKGRhdGEpIHtcclxuICAgIGNvbnNvbGUubG9nKFwiYnVnYWdhc2hlY2hrb1wiKVxyXG4gICAgY29uc29sZS5sb2coZGF0YSlcclxuICB9KVxyXG5cclxuICBlZS5lbWl0KHtcclxuICAgIG5hbWUgOiBcIm11aGFoYWhhXCIsXHJcbiAgICBkYXRhIDogWzEsMiwzLDQsNV1cclxuICB9KVxyXG5cclxuICBlZS5vZmYoaWQpXHJcblxyXG4gIGVlLmVtaXQoe1xyXG4gICAgbmFtZSA6IFwibXVoYWhhaGFcIixcclxuICAgIGRhdGEgOiBbMSwyLDMsNCw1XVxyXG4gIH0pXHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGFwcC5qc1xuICoqLyIsIi8vIHN0eWxlLWxvYWRlcjogQWRkcyBzb21lIGNzcyB0byB0aGUgRE9NIGJ5IGFkZGluZyBhIDxzdHlsZT4gdGFnXG5cbi8vIGxvYWQgdGhlIHN0eWxlc1xudmFyIGNvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vYmFzaWMuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9iYXNpYy5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vYmFzaWMuc2Nzc1wiKTtcblx0XHRcdGlmKHR5cGVvZiBuZXdDb250ZW50ID09PSAnc3RyaW5nJykgbmV3Q29udGVudCA9IFtbbW9kdWxlLmlkLCBuZXdDb250ZW50LCAnJ11dO1xuXHRcdFx0dXBkYXRlKG5ld0NvbnRlbnQpO1xuXHRcdH0pO1xuXHR9XG5cdC8vIFdoZW4gdGhlIG1vZHVsZSBpcyBkaXNwb3NlZCwgcmVtb3ZlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0bW9kdWxlLmhvdC5kaXNwb3NlKGZ1bmN0aW9uKCkgeyB1cGRhdGUoKTsgfSk7XG59XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL3N0eWxlcy9iYXNpYy5zY3NzXG4gKiogbW9kdWxlIGlkID0gMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiZXhwb3J0cyA9IG1vZHVsZS5leHBvcnRzID0gcmVxdWlyZShcIi4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXCIpKCk7XG4vLyBpbXBvcnRzXG5cblxuLy8gbW9kdWxlXG5leHBvcnRzLnB1c2goW21vZHVsZS5pZCwgXCJib2R5IHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNFQ0VDRUM7IH1cXG5cXG4uY2FsZW5kYXIsIC5hZGRDYWxlbmRhciB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NhbGVuZGFyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQ7XFxuICBwYWRkaW5nLWxlZnQ6IDQ1cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgbWFyZ2luLWJvdHRvbTogNDBweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIC5jYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUsIC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgZ3JleTtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDVweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBwYWRkaW5nLXJpZ2h0OiAxNXB4O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIC5jYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUgPiBwLCAuYWRkQ2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlID4gcCB7XFxuICAgICAgdGV4dC1hbGlnbjogbGVmdDtcXG4gICAgICBjb2xvcjogIzI2MjYyNjtcXG4gICAgICBmb250OiA0MDAgMTRweCAvIDIwcHggUm9ib3RvOyB9XFxuICAuY2FsZW5kYXIgPiAubGlzdFZhbHVlLCAuYWRkQ2FsZW5kYXIgPiAubGlzdFZhbHVlIHtcXG4gICAgZGlzcGxheTogbm9uZTsgfVxcblxcbmhlYWRlciB7XFxuICBoZWlnaHQ6IDcwcHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgYmFja2dyb3VuZC1jb2xvcjogIzE4NzVEMDsgfVxcblxcbi5idG4tYmx1ZSwgLmJ0bi1ncmV5IHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIG92ZXJmbG93OiBoaWRkZW47XFxuICBib3JkZXItcmFkaXVzOiA1cHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgY3Vyc29yOiBwb2ludGVyO1xcbiAgdGV4dC1hbGlnbjogY2VudGVyOyB9XFxuXFxuLmJ0bi1ncmV5IHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNEOEQ4RDg7XFxuICBjb2xvcjogIzg2ODY4NjtcXG4gIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87IH1cXG5cXG4uY29udGFpbmVyIHtcXG4gIHdpZHRoOiAxMjgwcHg7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5jbGVhcmZpeDpiZWZvcmUsIC5jbGVhcmZpeDphZnRlciB7XFxuICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICBkaXNwbGF5OiB0YWJsZTsgfVxcblxcbi5jbGVhcmZpeDphZnRlciB7XFxuICBjbGVhcjogYm90aDsgfVxcblxcbi5pbmsge1xcbiAgZGlzcGxheTogYmxvY2s7XFxuICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICBiYWNrZ3JvdW5kOiByZ2JhKDAsIDAsIDAsIDAuMTUpO1xcbiAgYm9yZGVyLXJhZGl1czogMTAwJTtcXG4gIC13ZWJraXQtdHJhbnNmb3JtOiBzY2FsZSgwKTtcXG4gICAgICAgICAgdHJhbnNmb3JtOiBzY2FsZSgwKTsgfVxcblxcbi5pbmsuYW5pbWF0ZSB7XFxuICAtd2Via2l0LWFuaW1hdGlvbjogcmlwcGxlIC41cyBlYXNlLWluO1xcbiAgICAgICAgICBhbmltYXRpb246IHJpcHBsZSAuNXMgZWFzZS1pbjsgfVxcblxcbkAtd2Via2l0LWtleWZyYW1lcyByaXBwbGUge1xcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDA7XFxuICAgIC13ZWJraXQtdHJhbnNmb3JtOiBzY2FsZSgyLjUpO1xcbiAgICAgICAgICAgIHRyYW5zZm9ybTogc2NhbGUoMi41KTsgfSB9XFxuXFxuQGtleWZyYW1lcyByaXBwbGUge1xcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDA7XFxuICAgIC13ZWJraXQtdHJhbnNmb3JtOiBzY2FsZSgyLjUpO1xcbiAgICAgICAgICAgIHRyYW5zZm9ybTogc2NhbGUoMi41KTsgfSB9XFxuXFxuLmhlYWRMZWZ0IHtcXG4gIHBhZGRpbmctdG9wOiA1cHg7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICB3aWR0aDogY2FsYygxMDAlIC0gNDkwcHgpO1xcbiAgaGVpZ2h0OiA3MHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgLmhlYWRMZWZ0ID4gLmxvZ28ge1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGJvcmRlci1yYWRpdXM6IDUwJTtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGhlaWdodDogNjBweDtcXG4gICAgd2lkdGg6IDYwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbG9nby5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICBtYXJnaW4tbGVmdDogMTVweDsgfVxcbiAgLmhlYWRMZWZ0ID4gLmlucHV0Rm9ybSB7XFxuICAgIG1hcmdpbi10b3A6IDIxcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiAyMHB4O1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgYm9yZGVyLWNvbG9yOiAjRkRGREZEO1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICBoZWlnaHQ6IGF1dG87XFxuICAgIHdpZHRoOiAyMDBweDtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNGREZERkQ7IH1cXG4gICAgLmhlYWRMZWZ0ID4gLmlucHV0Rm9ybSA+IGxhYmVsLCAuaGVhZExlZnQgPiAuaW5wdXRGb3JtIGlucHV0IHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTRweCBSb2JvdG87IH1cXG4gIC5oZWFkTGVmdCA+IC5zZWxlY3RCb3gge1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLXRvcDogMjFweDtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7IH1cXG4gICAgLmhlYWRMZWZ0ID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJhY2tncm91bmQ6IG5vbmU7XFxuICAgICAgcGFkZGluZzogMCA1cHg7IH1cXG4gIC5oZWFkTGVmdCA+IC5hZGQge1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMjAwcHg7XFxuICAgIG1hcmdpbi1yaWdodDogMjBweDtcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9hZGQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgcmlnaHQgMTBweDtcXG4gICAgcGFkZGluZy1sZWZ0OiAxMHB4O1xcbiAgICB0ZXh0LWFsaWduOiBsZWZ0O1xcbiAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICBib3JkZXI6IDFweCBzb2xpZCB3aGl0ZTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjE1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4xNXM7IH1cXG4gICAgLmhlYWRMZWZ0ID4gLmFkZCA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMzVweCBSb2JvdG87IH1cXG5cXG4uaGVhZFJpZ2h0IHtcXG4gIGZsb2F0OiByaWdodDtcXG4gIHdpZHRoOiA0OTBweDtcXG4gIGhlaWdodDogNzBweDtcXG4gIGJvcmRlci1sZWZ0OiAxcHggc29saWQgZ3JleTtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBwYWRkaW5nLXRvcDogMjJweDsgfVxcbiAgLmhlYWRSaWdodCA+IC5tYWlsIHtcXG4gICAgaGVpZ2h0OiAyNnB4O1xcbiAgICB3aWR0aDogMzNweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL21haWwucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5tYWlsID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDEwcHggLyAxNXB4IFJvYm90bztcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYm90dG9tOiAtM3B4O1xcbiAgICAgIGxlZnQ6IDMycHg7XFxuICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgIC5oZWFkUmlnaHQgPiAubWFpbDpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9tYWlsX3NoYWRvdy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7IH1cXG4gIC5oZWFkUmlnaHQgPiAuYmVsbCB7XFxuICAgIGhlaWdodDogMjRweDtcXG4gICAgd2lkdGg6IDIzcHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tbGVmdDogMzBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9iZWxsLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gICAgYm9yZGVyLXJhZGl1czogMTVweCAwIDE1cHggMTBweDtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5iZWxsID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDEwcHggLyAxNXB4IFJvYm90bztcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYm90dG9tOiAtN3B4O1xcbiAgICAgIGxlZnQ6IDIycHg7XFxuICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuYmVsbDpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9iZWxsX3NoYWRvdy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7IH1cXG4gIC5oZWFkUmlnaHQgPiAuc2VydmljZXMge1xcbiAgICBoZWlnaHQ6IDI3cHg7XFxuICAgIHdpZHRoOiAyOHB4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAzMHB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3NlcnZpY2VzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5zZXJ2aWNlczpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDI1cHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvdXNlck5hbWUucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdDtcXG4gICAgcGFkZGluZy1sZWZ0OiAzMHB4O1xcbiAgICBtYXgtd2lkdGg6IDI3MHB4O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjdweCBSb2JvdG87XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHdoaXRlLXNwYWNlOiBub3dyYXA7XFxuICAgICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgICB0ZXh0LW92ZXJmbG93OiBlbGxpcHNpczsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gZGl2IHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgICAgIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHRvcDogMDtcXG4gICAgICB3aWR0aDogMTAwJTtcXG4gICAgICB6LWluZGV4OiAxOyB9XFxuICAgICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IGRpdiA+IHAge1xcbiAgICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgICAgcGFkZGluZzogMCAxNXB4O1xcbiAgICAgICAgaGVpZ2h0OiA0OHB4O1xcbiAgICAgICAgZm9udDogNDAwIDE2cHggLyA1MHB4IFJvYm90bztcXG4gICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAgICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IGRpdiA+IHA6aG92ZXIge1xcbiAgICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjZWVlZWVlOyB9XFxuICAuaGVhZFJpZ2h0ID4gLmF1dGgge1xcbiAgICBjb2xvcjogd2hpdGU7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyMHB4O1xcbiAgICBmb250OiA0MDAgMTRweCAvIDI2cHggUm9ib3RvOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuYXV0aCBzcGFuIHtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgbWFyZ2luOiAwIDEwcHg7IH1cXG5cXG4uaW5wdXRTZWFyY2gge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgbWFyZ2luLXRvcDogMTBweDtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCB3aGl0ZTsgfVxcbiAgLmlucHV0U2VhcmNoID4gaW5wdXQge1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIHBhZGRpbmc6IDJweCAwO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDtcXG4gICAgb3V0bGluZTogbm9uZTtcXG4gICAgY29sb3I6ICNmZmZmZmY7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87IH1cXG4gIC5pbnB1dFNlYXJjaCA+IGxhYmVsIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICB0b3A6IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHRleHQ7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogLjVzO1xcbiAgICB0cmFuc2l0aW9uOiAuNXM7IH1cXG5cXG4uc2VsZWN0Qm94IHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIGN1cnNvcjogcG9pbnRlcjsgfVxcbiAgLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAxOXB4IFJvYm90bztcXG4gICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkIHdoaXRlO1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgcmlnaHQgNXB4O1xcbiAgICBwYWRkaW5nLXJpZ2h0OiAyMHB4O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHNwYW4ge1xcbiAgICAgIGZvbnQtc2l6ZTogMTZweDsgfVxcbiAgLnNlbGVjdEJveCA+IC5saXN0T2ZWYWx1ZXMge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHRvcDogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgIHotaW5kZXg6IDE7XFxuICAgIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICAgIC13ZWJraXQtYW5pbWF0aW9uOiBhbmltYXRldG9wIC4yNXM7XFxuICAgICAgICAgICAgYW5pbWF0aW9uOiBhbmltYXRldG9wIC4yNXM7IH1cXG4gICAgLnNlbGVjdEJveCA+IC5saXN0T2ZWYWx1ZXMgPiBkaXYge1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICBwYWRkaW5nOiAwIDE1cHg7XFxuICAgICAgaGVpZ2h0OiA1MHB4O1xcbiAgICAgIHdpZHRoOiAxMjBweDtcXG4gICAgICBmb250OiA0MDAgMTZweCAvIDUwcHggUm9ib3RvO1xcbiAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgICAuc2VsZWN0Qm94ID4gLmxpc3RPZlZhbHVlcyA+IGRpdjpob3ZlciB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjZWVlZWVlOyB9XFxuXFxuQC13ZWJraXQta2V5ZnJhbWVzIGFuaW1hdGV0b3Age1xcbiAgMCUge1xcbiAgICBvcGFjaXR5OiAwOyB9XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuQGtleWZyYW1lcyBhbmltYXRldG9wIHtcXG4gIDAlIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDE7IH0gfVxcblxcbmFzaWRlLmJ1bGxldGluRGV0YWlscyB7XFxuICBmbG9hdDogcmlnaHQ7XFxuICBtYXJnaW4tdG9wOiA1cHg7IH1cXG5cXG5zZWN0aW9uLm9wZW5BZHZlcnRlcnQge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICBmbG9hdDogcmlnaHQ7XFxuICBtYXJnaW4tcmlnaHQ6IDEwcHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgd2lkdGg6IDcxNXB4O1xcbiAgcGFkZGluZzogMjVweCAxMDBweCA0NXB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIG1hcmdpbi10b3A6IDVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gaDMge1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDIycHggLyAyNnB4IFJvYm90bztcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5wcmljZSB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMThweCAvIDI2cHggUm9ib3RvO1xcbiAgICBmbG9hdDogcmlnaHQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5jaGVja0JveCB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAxNXB4O1xcbiAgICBtYXJnaW4tdG9wOiAtMXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuYnJlYWRDcnVtYnMge1xcbiAgICBjb2xvcjogcmdiYSgzMSwgMzEsIDMxLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNHB4IFJvYm90bztcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7XFxuICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmlkIHtcXG4gICAgY29sb3I6IHJnYmEoMzIsIDMyLCAzMiwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTRweCBSb2JvdG87XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyIHtcXG4gICAgaGVpZ2h0OiAxMjBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIG1hcmdpbi1ib3R0b206IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IGRpdiB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDEwcHg7XFxuICAgICAgaGVpZ2h0OiAxMDAlO1xcbiAgICAgIHdpZHRoOiAxNjVweDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiBkaXY6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgICAgbWFyZ2luOiAwOyB9XFxuICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IGRpdiA+IGltZyB7XFxuICAgICAgICAtby1vYmplY3QtZml0OiBjb250YWluO1xcbiAgICAgICAgICAgb2JqZWN0LWZpdDogY29udGFpbjtcXG4gICAgICAgIHdpZHRoOiAxMDAlO1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0Y0RjRGNDtcXG4gICAgICAgIGhlaWdodDogMTAwJTtcXG4gICAgICAgIGN1cnNvcjogcG9pbnRlcjsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gLm5leHQge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL25leHQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IGNlbnRlcjtcXG4gICAgICBoZWlnaHQ6IDE0cHg7XFxuICAgICAgd2lkdGg6IDEwcHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHRvcDogNTAlO1xcbiAgICAgIG1hcmdpbi10b3A6IC03cHg7XFxuICAgICAgcmlnaHQ6IC0yNXB4OyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiAucHJldiB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvcHJldi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IHJpZ2h0IGNlbnRlcjtcXG4gICAgICBoZWlnaHQ6IDE0cHg7XFxuICAgICAgd2lkdGg6IDEwcHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHRvcDogNTAlO1xcbiAgICAgIG1hcmdpbi10b3A6IC03cHg7XFxuICAgICAgbGVmdDogLTI1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0ID4gI21hcEZvck9uZUFkdmVydGVydCB7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBoZWlnaHQ6IDIzMHB4O1xcbiAgICB3aWR0aDogMjI1cHg7XFxuICAgIG1hcmdpbi1ib3R0b206IDI1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0ID4gLmRlc2NyaXB0aW9uQWQge1xcbiAgICBjb2xvcjogIzBjMGMxZTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxOHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTVweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMjBweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgPiAuZ29Ub01hcCB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCA+IC5hbGxDb21tZW50cyB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiA1MHB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCA+IC53cml0ZUFSZXZpZXcge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0ID4gLm5hbWVVc2VyIHtcXG4gICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMThweCBSb2JvdG87XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICBtYXJnaW4tYm90dG9tOiAzNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCA+IC5vbk9yT2ZmTGluZVVzZXIge1xcbiAgICBmbG9hdDogbGVmdDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgLmJ0bi1ncmV5IHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxNDBweDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyNXB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgLmJ0bi1ibHVlIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5yZW50UHJvZHVjdCB7XFxuICAgIG1hcmdpbi10b3A6IDQ1cHg7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnJlbnRQcm9kdWN0ID4gLnJlbnRDYWxlbmRhciB7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5yZW50UHJvZHVjdCA+IC5yZW50Q2FsZW5kYXIgPiBwIHtcXG4gICAgICAgIGNvbG9yOiAjMjYzMjM4O1xcbiAgICAgICAgZm9udDogNDAwIDE2cHggLyAyMXB4IFJvYm90bztcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucmVudFByb2R1Y3QgPiAucmVudENhbGVuZGFyID4gcDpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgICAgIG1hcmdpbi1sZWZ0OiA1NXB4OyB9XFxuICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnJlbnRQcm9kdWN0ID4gLnJlbnRDYWxlbmRhciA+IC5jYWxlbmRhciB7XFxuICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgIG1hcmdpbi1sZWZ0OiA0NXB4O1xcbiAgICAgICAgd2lkdGg6IDE2NXB4O1xcbiAgICAgICAgbWFyZ2luLWJvdHRvbTogMDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucmVudFByb2R1Y3QgPiAuaW5wdXRGb3JtIHtcXG4gICAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0MHB4OyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5yZW50UHJvZHVjdCA+IC5idG4tYmx1ZSB7XFxuICAgICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgICBoZWlnaHQ6IDM1cHg7XFxuICAgICAgd2lkdGg6IDE4MHB4O1xcbiAgICAgIGxpbmUtaGVpZ2h0OiAzNXB4O1xcbiAgICAgIGZsb2F0OiByaWdodDsgfVxcblxcbi53cmFwRm9yRGl2IHtcXG4gIHdpZHRoOiAyNjVweDtcXG4gIGZsb2F0OiByaWdodDtcXG4gIG92ZXJmbG93OiBoaWRkZW47XFxuICBtYXJnaW4tYm90dG9tOiAyNXB4O1xcbiAgYm9yZGVyOiAxcHggc29saWQgI0U5RTlFOTtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIC53cmFwRm9yRGl2ID4gdWwudGFiIHtcXG4gICAgbGlzdC1zdHlsZS10eXBlOiBub25lO1xcbiAgICBoZWlnaHQ6IDUwcHg7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0U5RTlFOTtcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpIHtcXG4gICAgICBmbG9hdDogbGVmdDsgfVxcbiAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhIHtcXG4gICAgICAgIGNvbG9yOiAjOTM5MzkzO1xcbiAgICAgICAgZm9udDogNDAwIDE0cHggLyA1MHB4IFJvYm90bztcXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICAgICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYTphZnRlciB7XFxuICAgICAgICAgIGNvbnRlbnQ6ICcnO1xcbiAgICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICBib3R0b206IDA7XFxuICAgICAgICAgIHdpZHRoOiAwO1xcbiAgICAgICAgICBoZWlnaHQ6IDJweDtcXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0ZENTE1MTtcXG4gICAgICAgICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhOmZvY3VzLCAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYS5hY3RpdmUge1xcbiAgICAgICAgICBjb2xvcjogIzdlYWZlMTsgfVxcbiAgICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYTpmb2N1czphZnRlciwgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGEuYWN0aXZlOmFmdGVyIHtcXG4gICAgICAgICAgICB3aWR0aDogMTAwJTsgfVxcbiAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGk6bnRoLW9mLXR5cGUoMSkgPiBhIHtcXG4gICAgICAgIHdpZHRoOiAxNTlweDsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgxKSA+IGE6YWZ0ZXIge1xcbiAgICAgICAgICByaWdodDogMDsgfVxcbiAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGk6bnRoLW9mLXR5cGUoMikgPiBhIHtcXG4gICAgICAgIHdpZHRoOiAxMDRweDsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgyKSA+IGE6YWZ0ZXIge1xcbiAgICAgICAgICBsZWZ0OiAwOyB9XFxuICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3Mge1xcbiAgICBoZWlnaHQ6IDE3OHB4O1xcbiAgICB3aWR0aDogMjgycHg7XFxuICAgIGJhY2tncm91bmQtY29sb3I6ICNGNEY0RjQ7XFxuICAgIG92ZXJmbG93OiBhdXRvO1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+IC50YWJjb250ZW50IHtcXG4gICAgICBkaXNwbGF5OiBub25lO1xcbiAgICAgIC13ZWJraXQtYW5pbWF0aW9uOiBmYWRlRWZmZWN0IDFzO1xcbiAgICAgIGFuaW1hdGlvbjogZmFkZUVmZmVjdCAxczsgfVxcbiAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+IC50YWJjb250ZW50LmFjdGl2ZSB7XFxuICAgICAgICBkaXNwbGF5OiBibG9jazsgfVxcbiAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyB7XFxuICAgICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXYge1xcbiAgICAgICAgcGFkZGluZzogMzBweCAyMHB4IDI1cHggNjVweDtcXG4gICAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjRTlFOUU5OyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyA+IGRpdjpudGgtbGFzdC1vZi10eXBlKDEpIHtcXG4gICAgICAgICAgYm9yZGVyOiBub25lOyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyA+IGRpdiA+IGltZyB7XFxuICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgaGVpZ2h0OiAzMHB4O1xcbiAgICAgICAgICB3aWR0aDogMjVweDtcXG4gICAgICAgICAgdG9wOiAzNXB4O1xcbiAgICAgICAgICBsZWZ0OiAyMHB4OyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyA+IGRpdiA+IHAge1xcbiAgICAgICAgICBjb2xvcjogIzBkMGQxZTtcXG4gICAgICAgICAgZm9udDogNDAwIDEycHggLyAxOHB4IFJvYm90bzsgfVxcblxcbkAtd2Via2l0LWtleWZyYW1lcyBmYWRlRWZmZWN0IHtcXG4gIGZyb20ge1xcbiAgICBvcGFjaXR5OiAwOyB9XFxuICB0byB7XFxuICAgIG9wYWNpdHk6IDE7IH0gfVxcblxcbkBrZXlmcmFtZXMgZmFkZUVmZmVjdCB7XFxuICBmcm9tIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgdG8ge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG4uYnRuLWJsdWUge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogIzE4NzVEMDtcXG4gIGNvbG9yOiAjZmZmZmZmO1xcbiAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bzsgfVxcblxcbi5vbk9yT2ZmTGluZVVzZXIge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogIzAwQzY1MjtcXG4gIGJvcmRlcjogMXB4IHNvbGlkIHdoaXRlO1xcbiAgYm94LXNoYWRvdzogMXB4IDFweCAzcHggMHB4IHJnYmEoMCwgMCwgMCwgMC42NSk7XFxuICBoZWlnaHQ6IDE4cHg7XFxuICB3aWR0aDogMThweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBib3JkZXItcmFkaXVzOiA1MCU7IH1cXG5cXG4uZXJyb3ItY29udGFpbmVyIHtcXG4gIHdpZHRoOiAxMTAzcHg7XFxuICBtYXJnaW46IGF1dG87XFxuICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2Vycm9yX2JnLnBuZ1wiKSArIFwiKTtcXG4gIGJhY2tncm91bmQtcmVwZWF0OiBuby1yZXBlYXQ7XFxuICBiYWNrZ3JvdW5kLXBvc2l0aW9uLXg6IHJpZ2h0O1xcbiAgYmFja2dyb3VuZC1wb3NpdGlvbi15OiA4MHB4O1xcbiAgbWluLWhlaWdodDogNTAwcHg7IH1cXG4gIC5lcnJvci1jb250YWluZXIgaDEge1xcbiAgICBjb2xvcjogIzFmMWYxZjtcXG4gICAgZm9udDogNDAwIDIycHggLyAyNi40cHggUm9ib3RvO1xcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgIG1hcmdpbi10b3A6IDY1cHg7IH1cXG4gIC5lcnJvci1jb250YWluZXIgaDIge1xcbiAgICBjb2xvcjogIzFmMWYxZjtcXG4gICAgZm9udDogNDAwIDIycHggLyAyNi40cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxODlweDsgfVxcbiAgLmVycm9yLWNvbnRhaW5lciBoMyB7XFxuICAgIGNvbG9yOiByZ2JhKDMxLCAzMSwgMzEsIDAuNTQpO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE0LjRweCBSb2JvdG87XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7XFxuICAgIG1hcmdpbi1ib3R0b206IDMwcHg7IH1cXG4gIC5lcnJvci1jb250YWluZXIgPiAuYnRuLWJsdWUge1xcbiAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7IH1cXG5cXG4uY2xpY2tlZCB7XFxuICBib3gtc2hhZG93OiAwcHggMHB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpICFpbXBvcnRhbnQ7XFxuICBtYXJnaW4tdG9wOiAxMnB4ICFpbXBvcnRhbnQ7IH1cXG5cXG4ubXVsdGlwbGUge1xcbiAgd2lkdGg6IDExNTBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDsgfVxcbiAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0IHtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIHdpZHRoOiAzODBweDtcXG4gICAgbWFyZ2luLWxlZnQ6IDVweDtcXG4gICAgaGVpZ2h0OiAxNjVweDtcXG4gICAgcGFkZGluZzogMTVweDsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQ6bnRoLW9mLXR5cGUoM24tMikge1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAwOyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgxKSB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0JDRTZFOTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQ6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNCQ0U2RTk7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDMpIHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjQkNFNkU5OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCA+IC5idWxsZXRpbi1jZW50ZXItY29sdW1uIHtcXG4gICAgICBmbG9hdDogbm9uZTtcXG4gICAgICBtYXJnaW46IDA7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHRvcDogMTVweDtcXG4gICAgICByaWdodDogMTEwcHg7IH1cXG4gICAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQgPiAuYnVsbGV0aW4tY2VudGVyLWNvbHVtbiA+IC5idWxsZXRpbi1hY3Rpb24ge1xcbiAgICAgICAgYm90dG9tOiAtNDNweDtcXG4gICAgICAgIHdpZHRoOiAxMTBweDtcXG4gICAgICAgIHJpZ2h0OiAtMzBweDtcXG4gICAgICAgIHotaW5kZXg6IDE7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0ID4gLmJ1bGxldGluLWxlZnQtY29sdW1uID4gLmJ1bGxldGluLWRlc2NyaXB0aW9uIHtcXG4gICAgICBjb2xvcjogIzBjMGMxZTtcXG4gICAgICBmb250OiA0MDAgMTJweCAvIDE4cHggUm9ib3RvO1xcbiAgICAgIHdpZHRoOiAxNzBweDtcXG4gICAgICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgICAgIGhlaWdodDogNDBweDsgfVxcblxcbi5yZWQge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0Y3NEE0QTsgfVxcblxcbi5pbnB1dEZvcm0tcmVxdWlyZWQge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgcGFkZGluZzogNnB4IDJweDtcXG4gIGZvbnQ6IDQwMCAxNnB4IC8gMjQuOHB4IFJvYm90bztcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzliOWI5YjtcXG4gIG1hcmdpbi10b3A6IDE1cHg7IH1cXG4gIC5pbnB1dEZvcm0tcmVxdWlyZWQgbGFiZWwge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGxlZnQ6IDJweDtcXG4gICAgdG9wOiA2cHg7XFxuICAgIGNvbG9yOiAjOWE5YTlhO1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgLmlucHV0Rm9ybS1yZXF1aXJlZCBpbnB1dCB7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgb3V0bGluZTogbm9uZTtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50OyB9XFxuXFxuLmlucHV0Rm9ybS5yZXF1aXJlZCB7XFxuICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzliOWI5YiAhaW1wb3J0YW50OyB9XFxuXFxuLmlucHV0Rm9ybSB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IGRvdHRlZCAjOTk5OTk5OyB9XFxuICAuaW5wdXRGb3JtIGxhYmVsIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBsZWZ0OiA1cHg7XFxuICAgIGNvbG9yOiAjOWE5YTlhO1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdG9wOiAtMXB4OyB9XFxuICAuaW5wdXRGb3JtIGlucHV0IHtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lO1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgcGFkZGluZzogMXB4IDVweDsgfVxcblxcbi8qIFRleHQgZWxlbWVudCBhbmltYXRpb24gKi9cXG4udGV4dE91dCB7XFxuICB0b3A6IC0xNXB4ICFpbXBvcnRhbnQ7XFxuICBmb250LXNpemU6IDEycHggIWltcG9ydGFudDtcXG4gIGxlZnQ6IDVweCAhaW1wb3J0YW50OyB9XFxuXFxuc2VjdGlvbi5sb2dpbiwgc2VjdGlvbi5yZWdpc3RlciB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIHBhZGRpbmc6IDY1cHggMjMwcHg7XFxuICB3aWR0aDogMTEwMHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgc2VjdGlvbi5sb2dpbiA+IGgyLCBzZWN0aW9uLnJlZ2lzdGVyID4gaDIge1xcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgIGNvbG9yOiAjMjAyMDIwO1xcbiAgICBmb250OiA0MDAgMjJweCBSb2JvdG87XFxuICAgIG1hcmdpbi1ib3R0b206IDU1cHg7IH1cXG4gIHNlY3Rpb24ubG9naW4gPiAuaW5wdXRGb3JtLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmlucHV0Rm9ybSB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gICAgc2VjdGlvbi5sb2dpbiA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgc2VjdGlvbi5sb2dpbiA+IC5pbnB1dEZvcm0gaW5wdXQsIHNlY3Rpb24ucmVnaXN0ZXIgPiAuaW5wdXRGb3JtID4gbGFiZWwsIHNlY3Rpb24ucmVnaXN0ZXIgPiAuaW5wdXRGb3JtIGlucHV0IHtcXG4gICAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87IH1cXG4gIHNlY3Rpb24ubG9naW4gPiAuYnRuLWJsdWUsIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUge1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbWFyZ2luLWxlZnQ6IDQ1cHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuYnRuLWJsdWUudmssIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUudmsge1xcbiAgICAgIGJhY2tncm91bmQ6ICMwMTg2Q0YgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy92ay5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0IDEwcHg7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDA7IH1cXG4gICAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZS5mYWNlYm9vaywgc2VjdGlvbi5yZWdpc3RlciA+IC5idG4tYmx1ZS5mYWNlYm9vayB7XFxuICAgICAgYmFja2dyb3VuZDogIzNFNTBCMyB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2ZhY2Vib29rLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQgMTBweDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLmdvb2dsZSwgc2VjdGlvbi5yZWdpc3RlciA+IC5idG4tYmx1ZS5nb29nbGUge1xcbiAgICAgIGJhY2tncm91bmQ6ICNGRDNDMDAgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9nb29nbGUucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdCAxMHB4OyB9XFxuXFxuc2VjdGlvbi5idWxsZXRpbkFkZCB7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIHdpZHRoOiAxMTAwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7XFxuICBwYWRkaW5nOiA2NXB4IDIyNXB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IGgyIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IFJvYm90bztcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBtYXJnaW4tYm90dG9tOiA0MHB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLnNlbGVjdEJveCB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7XFxuICAgIG1hcmdpbi10b3A6IDE1cHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICBib3JkZXItY29sb3I6IGdyZXk7XFxuICAgICAgbWluLXdpZHRoOiAxOTVweDtcXG4gICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICAgICAgcGFkZGluZy1sZWZ0OiA1cHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuZmlsZSB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzllOWU5ZTtcXG4gICAgd2lkdGg6IDQxMHB4O1xcbiAgICBwYWRkaW5nLWJvdHRvbTogNXB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuZmlsZSA+IGlucHV0IHtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgei1pbmRleDogLTE7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlID4gcCB7XFxuICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgIGN1cnNvcjogZGVmYXVsdDtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBwYWRkaW5nLXRvcDogMTBweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUgPiAuYnRuLWJsdWUge1xcbiAgICAgIGZsb2F0OiByaWdodDtcXG4gICAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICAgIGhlaWdodDogMzBweDtcXG4gICAgICB3aWR0aDogODVweDtcXG4gICAgICBsaW5lLWhlaWdodDogMzBweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5pbnB1dEZvcm0ge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuaW5wdXRGb3JtID4gbGFiZWwsIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuaW5wdXRGb3JtIGlucHV0IHtcXG4gICAgICBjb2xvcjogIzk5OTk5OTtcXG4gICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3Ige1xcbiAgICBtYXJnaW4tYm90dG9tOiA0MHB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBwIHtcXG4gICAgICBjb2xvcjogIzk5OTk5OTtcXG4gICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IGRvdHRlZCAjOTk5OTk5O1xcbiAgICAgIHdpZHRoOiAxNjBweDtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBoZWlnaHQ6IDIxcHg7XFxuICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2IHtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tbGVmdDogMTlweDtcXG4gICAgICBtYXJnaW4tdG9wOiAzcHg7XFxuICAgICAgd2lkdGg6IDE2cHg7XFxuICAgICAgaGVpZ2h0OiAxNXB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICAgIG91dGxpbmU6IDFweCBzb2xpZCB0cmFuc3BhcmVudDtcXG4gICAgICBib3JkZXI6IDFweCBzb2xpZCB3aGl0ZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYudHJhbnNwYXJlbnQge1xcbiAgICAgICAgYm9yZGVyLWNvbG9yOiAjRTlFOUU5O1xcbiAgICAgICAgb3ZlcmZsb3c6IGhpZGRlbjsgfVxcbiAgICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi50cmFuc3BhcmVudCA+IC5yZWRTdGljayB7XFxuICAgICAgICAgIC13ZWJraXQtdHJhbnNmb3JtOiByb3RhdGUoLTQzZGVnKTtcXG4gICAgICAgICAgICAgICAgICB0cmFuc2Zvcm06IHJvdGF0ZSgtNDNkZWcpO1xcbiAgICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZWQ7XFxuICAgICAgICAgIGhlaWdodDogMnB4O1xcbiAgICAgICAgICBtYXJnaW4tdG9wOiA1LjVweDtcXG4gICAgICAgICAgd2lkdGg6IDIwcHg7XFxuICAgICAgICAgIG1hcmdpbi1sZWZ0OiAtM3B4OyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5yZWQge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcmVkOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5vcmFuZ2Uge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogb3JhbmdlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi55ZWxsb3cge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogeWVsbG93OyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5ncmVlbiB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBncmVlbjsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYubGlnaHRCbHVlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGxpZ2h0Qmx1ZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuYmx1ZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBibHVlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5waW5rIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHBpbms7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnB1cnBsZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBwdXJwbGU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LndoaXRlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgICAgICAgYm9yZGVyLWNvbG9yOiAjRTlFOUU5OyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5ncmF5IHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGdyYXk7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmJsYWNrIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGJsYWNrOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5icm93biB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBicm93bjsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYubXVsdGljb2xvciB7XFxuICAgICAgICAvKiBQZXJtYWxpbmsgLSB1c2UgdG8gZWRpdCBhbmQgc2hhcmUgdGhpcyBncmFkaWVudDogaHR0cDovL2NvbG9yemlsbGEuY29tL2dyYWRpZW50LWVkaXRvci8jZmYwMDAwKzAsZmZmZjAwKzIwLDFkZmYwMCs0MCwwMGZmZmYrNjAsMDQwMGZmKzgwLGZmMDBmYSsxMDAgKi9cXG4gICAgICAgIGJhY2tncm91bmQ6ICNmZjAwMDA7XFxuICAgICAgICAvKiBPbGQgYnJvd3NlcnMgKi9cXG4gICAgICAgIC8qIEZGMy42LTE1ICovXFxuICAgICAgICBiYWNrZ3JvdW5kOiAtd2Via2l0LWxpbmVhci1ncmFkaWVudChsZWZ0LCAjZmYwMDAwIDAlLCAjZmZmZjAwIDIwJSwgIzFkZmYwMCA0MCUsICMwMGZmZmYgNjAlLCAjMDQwMGZmIDgwJSwgI2ZmMDBmYSAxMDAlKTtcXG4gICAgICAgIC8qIENocm9tZTEwLTI1LFNhZmFyaTUuMS02ICovXFxuICAgICAgICBiYWNrZ3JvdW5kOiBsaW5lYXItZ3JhZGllbnQodG8gcmlnaHQsICNmZjAwMDAgMCUsICNmZmZmMDAgMjAlLCAjMWRmZjAwIDQwJSwgIzAwZmZmZiA2MCUsICMwNDAwZmYgODAlLCAjZmYwMGZhIDEwMCUpO1xcbiAgICAgICAgLyogVzNDLCBJRTEwKywgRkYxNissIENocm9tZTI2KywgT3BlcmExMissIFNhZmFyaTcrICovXFxuICAgICAgICBmaWx0ZXI6IHByb2dpZDpEWEltYWdlVHJhbnNmb3JtLk1pY3Jvc29mdC5ncmFkaWVudCggc3RhcnRDb2xvcnN0cj0nI2ZmMDAwMCcsIGVuZENvbG9yc3RyPScjZmYwMGZhJyxHcmFkaWVudFR5cGU9MSApO1xcbiAgICAgICAgLyogSUU2LTkgKi8gfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuYWN0aXZlIHtcXG4gICAgICAgIG91dGxpbmUtY29sb3I6IHJlZDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jYWxlbmRhciwgc2VjdGlvbi5idWxsZXRpbkFkZCAuYWRkQ2FsZW5kYXIge1xcbiAgICB3aWR0aDogMjMwcHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYWRkQ2FsZW5kYXIge1xcbiAgICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2FkZENhbGVuZGFyLnBuZ1wiKSArIFwiKTsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgYm9yZGVyLWJvdHRvbS1jb2xvcjogYmx1ZTtcXG4gICAgICBiYWNrZ3JvdW5kOiBub25lOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUgPiBwIHtcXG4gICAgICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICAgICAgZm9udC1zaXplOiAxNnB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmJ0bi1ibHVlIHtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4O1xcbiAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgbWFyZ2luOiAwIGF1dG87IH1cXG5cXG4uZXJyb3JzIHtcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIGZvbnQ6IDQwMCAxMnB4IC8gMTRweCBSb2JvdG87XFxuICBjb2xvcjogI2RkMmMwMDtcXG4gIGJvdHRvbTogLTE3cHg7IH1cXG5cXG5uYXYge1xcbiAgd2lkdGg6IDI1NXB4O1xcbiAgZmxvYXQ6IGxlZnQ7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIG1hcmdpbi10b3A6IDVweDtcXG4gIHotaW5kZXg6IDE7IH1cXG4gIG5hdiA+IC5tYXAge1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL21hcC5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICBoZWlnaHQ6IDE0NXB4O1xcbiAgICB3aWR0aDogMjU1cHg7IH1cXG4gICAgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0ge1xcbiAgICAgIHdpZHRoOiAyMTVweDtcXG4gICAgICBtYXJnaW46IDExNXB4IGF1dG8gMDsgfVxcbiAgICAgIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtLnJlcXVpcmVkIHtcXG4gICAgICAgIGJvcmRlci1jb2xvcjogYmxhY2sgIWltcG9ydGFudDsgfVxcbiAgICAgIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtID4gaW5wdXQsIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtIGxhYmVsLCBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSAudGV4dE91dCB7XFxuICAgICAgICBjb2xvcjogYmxhY2sgIWltcG9ydGFudDsgfVxcbiAgbmF2ID4gdWwge1xcbiAgICBsaXN0LXN0eWxlOiBub25lOyB9XFxuICAgIG5hdiA+IHVsID4gbGkgPiBwIHtcXG4gICAgICBwYWRkaW5nLWxlZnQ6IDc1cHg7XFxuICAgICAgY29sb3I6ICMyNjMyMzg7XFxuICAgICAgZm9udDogNTAwIDEzcHggLyA0MHB4IFJvYm90bztcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NhcmV0UmlnaHROYXYucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgcmlnaHQgMjBweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0U2RTZFNiAhaW1wb3J0YW50OyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvRm9yQW5pbWFscy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgzKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9CdXNpbmVzcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg0KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg1KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9UcmFuc3BvcnQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNikge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQ2xvdGhpbmdBbmRDb3NtZXRpY3MucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNykge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDgpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0lzRnJlZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg5KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9BSG91c2VBbmRBR2FyZGVuLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEwKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Ib2JiaWVzQW5kU3BvcnRzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDExKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9CYXJ0ZXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMTIpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG5cXG4ubmF2QnRuIHtcXG4gIHdpZHRoOiA0M3B4O1xcbiAgaGVpZ2h0OiA0OXB4O1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgbGVmdDogMDtcXG4gIHRvcDogOTBweDtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvcnVwci5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgei1pbmRleDogMTsgfVxcbiAgLm5hdkJ0bjpob3ZlciB7XFxuICAgIHdpZHRoOiA2MHB4O1xcbiAgICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3J1cHJIb3Zlci5wbmdcIikgKyBcIik7IH1cXG5cXG4uY292ZXIge1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgdG9wOiAwO1xcbiAgcmlnaHQ6IDA7XFxuICBib3R0b206IDA7XFxuICBsZWZ0OiAwO1xcbiAgYmFja2dyb3VuZC1jb2xvcjogcmdiYSgwLCAwLCAwLCAwLjkpO1xcbiAgei1pbmRleDogNTsgfVxcbiAgLmNvdmVyLmhpZGUge1xcbiAgICAtd2Via2l0LWFuaW1hdGlvbjogaGlkZSAxcztcXG4gICAgICAgICAgICBhbmltYXRpb246IGhpZGUgMXM7IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgaGlkZSB7XFxuICAxMDAlIHtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7IH0gfVxcblxcbkBrZXlmcmFtZXMgaGlkZSB7XFxuICAxMDAlIHtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7IH0gfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9iYXNpYy5zY3NzXG4gKiogbW9kdWxlIGlkID0gMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLypcclxuXHRNSVQgTGljZW5zZSBodHRwOi8vd3d3Lm9wZW5zb3VyY2Uub3JnL2xpY2Vuc2VzL21pdC1saWNlbnNlLnBocFxyXG5cdEF1dGhvciBUb2JpYXMgS29wcGVycyBAc29rcmFcclxuKi9cclxuLy8gY3NzIGJhc2UgY29kZSwgaW5qZWN0ZWQgYnkgdGhlIGNzcy1sb2FkZXJcclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcclxuXHR2YXIgbGlzdCA9IFtdO1xyXG5cclxuXHQvLyByZXR1cm4gdGhlIGxpc3Qgb2YgbW9kdWxlcyBhcyBjc3Mgc3RyaW5nXHJcblx0bGlzdC50b1N0cmluZyA9IGZ1bmN0aW9uIHRvU3RyaW5nKCkge1xyXG5cdFx0dmFyIHJlc3VsdCA9IFtdO1xyXG5cdFx0Zm9yKHZhciBpID0gMDsgaSA8IHRoaXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGl0ZW0gPSB0aGlzW2ldO1xyXG5cdFx0XHRpZihpdGVtWzJdKSB7XHJcblx0XHRcdFx0cmVzdWx0LnB1c2goXCJAbWVkaWEgXCIgKyBpdGVtWzJdICsgXCJ7XCIgKyBpdGVtWzFdICsgXCJ9XCIpO1xyXG5cdFx0XHR9IGVsc2Uge1xyXG5cdFx0XHRcdHJlc3VsdC5wdXNoKGl0ZW1bMV0pO1xyXG5cdFx0XHR9XHJcblx0XHR9XHJcblx0XHRyZXR1cm4gcmVzdWx0LmpvaW4oXCJcIik7XHJcblx0fTtcclxuXHJcblx0Ly8gaW1wb3J0IGEgbGlzdCBvZiBtb2R1bGVzIGludG8gdGhlIGxpc3RcclxuXHRsaXN0LmkgPSBmdW5jdGlvbihtb2R1bGVzLCBtZWRpYVF1ZXJ5KSB7XHJcblx0XHRpZih0eXBlb2YgbW9kdWxlcyA9PT0gXCJzdHJpbmdcIilcclxuXHRcdFx0bW9kdWxlcyA9IFtbbnVsbCwgbW9kdWxlcywgXCJcIl1dO1xyXG5cdFx0dmFyIGFscmVhZHlJbXBvcnRlZE1vZHVsZXMgPSB7fTtcclxuXHRcdGZvcih2YXIgaSA9IDA7IGkgPCB0aGlzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBpZCA9IHRoaXNbaV1bMF07XHJcblx0XHRcdGlmKHR5cGVvZiBpZCA9PT0gXCJudW1iZXJcIilcclxuXHRcdFx0XHRhbHJlYWR5SW1wb3J0ZWRNb2R1bGVzW2lkXSA9IHRydWU7XHJcblx0XHR9XHJcblx0XHRmb3IoaSA9IDA7IGkgPCBtb2R1bGVzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBpdGVtID0gbW9kdWxlc1tpXTtcclxuXHRcdFx0Ly8gc2tpcCBhbHJlYWR5IGltcG9ydGVkIG1vZHVsZVxyXG5cdFx0XHQvLyB0aGlzIGltcGxlbWVudGF0aW9uIGlzIG5vdCAxMDAlIHBlcmZlY3QgZm9yIHdlaXJkIG1lZGlhIHF1ZXJ5IGNvbWJpbmF0aW9uc1xyXG5cdFx0XHQvLyAgd2hlbiBhIG1vZHVsZSBpcyBpbXBvcnRlZCBtdWx0aXBsZSB0aW1lcyB3aXRoIGRpZmZlcmVudCBtZWRpYSBxdWVyaWVzLlxyXG5cdFx0XHQvLyAgSSBob3BlIHRoaXMgd2lsbCBuZXZlciBvY2N1ciAoSGV5IHRoaXMgd2F5IHdlIGhhdmUgc21hbGxlciBidW5kbGVzKVxyXG5cdFx0XHRpZih0eXBlb2YgaXRlbVswXSAhPT0gXCJudW1iZXJcIiB8fCAhYWxyZWFkeUltcG9ydGVkTW9kdWxlc1tpdGVtWzBdXSkge1xyXG5cdFx0XHRcdGlmKG1lZGlhUXVlcnkgJiYgIWl0ZW1bMl0pIHtcclxuXHRcdFx0XHRcdGl0ZW1bMl0gPSBtZWRpYVF1ZXJ5O1xyXG5cdFx0XHRcdH0gZWxzZSBpZihtZWRpYVF1ZXJ5KSB7XHJcblx0XHRcdFx0XHRpdGVtWzJdID0gXCIoXCIgKyBpdGVtWzJdICsgXCIpIGFuZCAoXCIgKyBtZWRpYVF1ZXJ5ICsgXCIpXCI7XHJcblx0XHRcdFx0fVxyXG5cdFx0XHRcdGxpc3QucHVzaChpdGVtKTtcclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdH07XHJcblx0cmV0dXJuIGxpc3Q7XHJcbn07XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG5vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9sb2dvLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbG9nby5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYWRkLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYWRkLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9tYWlsLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbWFpbC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvbWFpbF9zaGFkb3cucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9tYWlsX3NoYWRvdy5wbmdcbiAqKiBtb2R1bGUgaWQgPSA3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYmVsbC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2JlbGwucG5nXG4gKiogbW9kdWxlIGlkID0gOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2JlbGxfc2hhZG93LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYmVsbF9zaGFkb3cucG5nXG4gKiogbW9kdWxlIGlkID0gOVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3NlcnZpY2VzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvc2VydmljZXMucG5nXG4gKiogbW9kdWxlIGlkID0gMTBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nXG4gKiogbW9kdWxlIGlkID0gMTFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy91c2VyTmFtZS5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3VzZXJOYW1lLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDEyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXG4gKiogbW9kdWxlIGlkID0gMTNcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9uZXh0LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbmV4dC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3ByZXYucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9wcmV2LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZXJyb3JfYmcucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9lcnJvcl9iZy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxNlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3ZrLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdmsucG5nXG4gKiogbW9kdWxlIGlkID0gMTdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9mYWNlYm9vay5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2ZhY2Vib29rLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZ29vZ2xlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZ29vZ2xlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE5XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYWxlbmRhci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyMFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2FkZENhbGVuZGFyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nXG4gKiogbW9kdWxlIGlkID0gMjFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9tYXAucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9tYXAucG5nXG4gKiogbW9kdWxlIGlkID0gMjJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvY2FyZXRSaWdodE5hdi5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyM1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0ZvckNoaWxkcmVuLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nXG4gKiogbW9kdWxlIGlkID0gMjRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Gb3JBbmltYWxzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvRm9yQW5pbWFscy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0J1c2luZXNzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvQnVzaW5lc3MucG5nXG4gKiogbW9kdWxlIGlkID0gMjZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9UaGVQcm9wZXJ0eS5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1RoZVByb3BlcnR5LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVHJhbnNwb3J0LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVHJhbnNwb3J0LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQ2xvdGhpbmdBbmRDb3NtZXRpY3MucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9DbG90aGluZ0FuZENvc21ldGljcy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyOVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1ZvbHVudGVlcmluZy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1ZvbHVudGVlcmluZy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzMFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0lzRnJlZS5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0lzRnJlZS5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzMVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0FIb3VzZUFuZEFHYXJkZW4ucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9BSG91c2VBbmRBR2FyZGVuLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvSG9iYmllc0FuZFNwb3J0cy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nXG4gKiogbW9kdWxlIGlkID0gMzNcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9CYXJ0ZXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9CYXJ0ZXIucG5nXG4gKiogbW9kdWxlIGlkID0gMzRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9UZWNobm9sb2dpZXMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nXG4gKiogbW9kdWxlIGlkID0gMzVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9ydXByLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvcnVwci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzNlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3J1cHJIb3Zlci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3J1cHJIb3Zlci5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiLypcclxuXHRNSVQgTGljZW5zZSBodHRwOi8vd3d3Lm9wZW5zb3VyY2Uub3JnL2xpY2Vuc2VzL21pdC1saWNlbnNlLnBocFxyXG5cdEF1dGhvciBUb2JpYXMgS29wcGVycyBAc29rcmFcclxuKi9cclxudmFyIHN0eWxlc0luRG9tID0ge30sXHJcblx0bWVtb2l6ZSA9IGZ1bmN0aW9uKGZuKSB7XHJcblx0XHR2YXIgbWVtbztcclxuXHRcdHJldHVybiBmdW5jdGlvbiAoKSB7XHJcblx0XHRcdGlmICh0eXBlb2YgbWVtbyA9PT0gXCJ1bmRlZmluZWRcIikgbWVtbyA9IGZuLmFwcGx5KHRoaXMsIGFyZ3VtZW50cyk7XHJcblx0XHRcdHJldHVybiBtZW1vO1xyXG5cdFx0fTtcclxuXHR9LFxyXG5cdGlzT2xkSUUgPSBtZW1vaXplKGZ1bmN0aW9uKCkge1xyXG5cdFx0cmV0dXJuIC9tc2llIFs2LTldXFxiLy50ZXN0KHdpbmRvdy5uYXZpZ2F0b3IudXNlckFnZW50LnRvTG93ZXJDYXNlKCkpO1xyXG5cdH0pLFxyXG5cdGdldEhlYWRFbGVtZW50ID0gbWVtb2l6ZShmdW5jdGlvbiAoKSB7XHJcblx0XHRyZXR1cm4gZG9jdW1lbnQuaGVhZCB8fCBkb2N1bWVudC5nZXRFbGVtZW50c0J5VGFnTmFtZShcImhlYWRcIilbMF07XHJcblx0fSksXHJcblx0c2luZ2xldG9uRWxlbWVudCA9IG51bGwsXHJcblx0c2luZ2xldG9uQ291bnRlciA9IDAsXHJcblx0c3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AgPSBbXTtcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24obGlzdCwgb3B0aW9ucykge1xyXG5cdGlmKHR5cGVvZiBERUJVRyAhPT0gXCJ1bmRlZmluZWRcIiAmJiBERUJVRykge1xyXG5cdFx0aWYodHlwZW9mIGRvY3VtZW50ICE9PSBcIm9iamVjdFwiKSB0aHJvdyBuZXcgRXJyb3IoXCJUaGUgc3R5bGUtbG9hZGVyIGNhbm5vdCBiZSB1c2VkIGluIGEgbm9uLWJyb3dzZXIgZW52aXJvbm1lbnRcIik7XHJcblx0fVxyXG5cclxuXHRvcHRpb25zID0gb3B0aW9ucyB8fCB7fTtcclxuXHQvLyBGb3JjZSBzaW5nbGUtdGFnIHNvbHV0aW9uIG9uIElFNi05LCB3aGljaCBoYXMgYSBoYXJkIGxpbWl0IG9uIHRoZSAjIG9mIDxzdHlsZT5cclxuXHQvLyB0YWdzIGl0IHdpbGwgYWxsb3cgb24gYSBwYWdlXHJcblx0aWYgKHR5cGVvZiBvcHRpb25zLnNpbmdsZXRvbiA9PT0gXCJ1bmRlZmluZWRcIikgb3B0aW9ucy5zaW5nbGV0b24gPSBpc09sZElFKCk7XHJcblxyXG5cdC8vIEJ5IGRlZmF1bHQsIGFkZCA8c3R5bGU+IHRhZ3MgdG8gdGhlIGJvdHRvbSBvZiA8aGVhZD4uXHJcblx0aWYgKHR5cGVvZiBvcHRpb25zLmluc2VydEF0ID09PSBcInVuZGVmaW5lZFwiKSBvcHRpb25zLmluc2VydEF0ID0gXCJib3R0b21cIjtcclxuXHJcblx0dmFyIHN0eWxlcyA9IGxpc3RUb1N0eWxlcyhsaXN0KTtcclxuXHRhZGRTdHlsZXNUb0RvbShzdHlsZXMsIG9wdGlvbnMpO1xyXG5cclxuXHRyZXR1cm4gZnVuY3Rpb24gdXBkYXRlKG5ld0xpc3QpIHtcclxuXHRcdHZhciBtYXlSZW1vdmUgPSBbXTtcclxuXHRcdGZvcih2YXIgaSA9IDA7IGkgPCBzdHlsZXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGl0ZW0gPSBzdHlsZXNbaV07XHJcblx0XHRcdHZhciBkb21TdHlsZSA9IHN0eWxlc0luRG9tW2l0ZW0uaWRdO1xyXG5cdFx0XHRkb21TdHlsZS5yZWZzLS07XHJcblx0XHRcdG1heVJlbW92ZS5wdXNoKGRvbVN0eWxlKTtcclxuXHRcdH1cclxuXHRcdGlmKG5ld0xpc3QpIHtcclxuXHRcdFx0dmFyIG5ld1N0eWxlcyA9IGxpc3RUb1N0eWxlcyhuZXdMaXN0KTtcclxuXHRcdFx0YWRkU3R5bGVzVG9Eb20obmV3U3R5bGVzLCBvcHRpb25zKTtcclxuXHRcdH1cclxuXHRcdGZvcih2YXIgaSA9IDA7IGkgPCBtYXlSZW1vdmUubGVuZ3RoOyBpKyspIHtcclxuXHRcdFx0dmFyIGRvbVN0eWxlID0gbWF5UmVtb3ZlW2ldO1xyXG5cdFx0XHRpZihkb21TdHlsZS5yZWZzID09PSAwKSB7XHJcblx0XHRcdFx0Zm9yKHZhciBqID0gMDsgaiA8IGRvbVN0eWxlLnBhcnRzLmxlbmd0aDsgaisrKVxyXG5cdFx0XHRcdFx0ZG9tU3R5bGUucGFydHNbal0oKTtcclxuXHRcdFx0XHRkZWxldGUgc3R5bGVzSW5Eb21bZG9tU3R5bGUuaWRdO1xyXG5cdFx0XHR9XHJcblx0XHR9XHJcblx0fTtcclxufVxyXG5cclxuZnVuY3Rpb24gYWRkU3R5bGVzVG9Eb20oc3R5bGVzLCBvcHRpb25zKSB7XHJcblx0Zm9yKHZhciBpID0gMDsgaSA8IHN0eWxlcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0dmFyIGl0ZW0gPSBzdHlsZXNbaV07XHJcblx0XHR2YXIgZG9tU3R5bGUgPSBzdHlsZXNJbkRvbVtpdGVtLmlkXTtcclxuXHRcdGlmKGRvbVN0eWxlKSB7XHJcblx0XHRcdGRvbVN0eWxlLnJlZnMrKztcclxuXHRcdFx0Zm9yKHZhciBqID0gMDsgaiA8IGRvbVN0eWxlLnBhcnRzLmxlbmd0aDsgaisrKSB7XHJcblx0XHRcdFx0ZG9tU3R5bGUucGFydHNbal0oaXRlbS5wYXJ0c1tqXSk7XHJcblx0XHRcdH1cclxuXHRcdFx0Zm9yKDsgaiA8IGl0ZW0ucGFydHMubGVuZ3RoOyBqKyspIHtcclxuXHRcdFx0XHRkb21TdHlsZS5wYXJ0cy5wdXNoKGFkZFN0eWxlKGl0ZW0ucGFydHNbal0sIG9wdGlvbnMpKTtcclxuXHRcdFx0fVxyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0dmFyIHBhcnRzID0gW107XHJcblx0XHRcdGZvcih2YXIgaiA9IDA7IGogPCBpdGVtLnBhcnRzLmxlbmd0aDsgaisrKSB7XHJcblx0XHRcdFx0cGFydHMucHVzaChhZGRTdHlsZShpdGVtLnBhcnRzW2pdLCBvcHRpb25zKSk7XHJcblx0XHRcdH1cclxuXHRcdFx0c3R5bGVzSW5Eb21baXRlbS5pZF0gPSB7aWQ6IGl0ZW0uaWQsIHJlZnM6IDEsIHBhcnRzOiBwYXJ0c307XHJcblx0XHR9XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiBsaXN0VG9TdHlsZXMobGlzdCkge1xyXG5cdHZhciBzdHlsZXMgPSBbXTtcclxuXHR2YXIgbmV3U3R5bGVzID0ge307XHJcblx0Zm9yKHZhciBpID0gMDsgaSA8IGxpc3QubGVuZ3RoOyBpKyspIHtcclxuXHRcdHZhciBpdGVtID0gbGlzdFtpXTtcclxuXHRcdHZhciBpZCA9IGl0ZW1bMF07XHJcblx0XHR2YXIgY3NzID0gaXRlbVsxXTtcclxuXHRcdHZhciBtZWRpYSA9IGl0ZW1bMl07XHJcblx0XHR2YXIgc291cmNlTWFwID0gaXRlbVszXTtcclxuXHRcdHZhciBwYXJ0ID0ge2NzczogY3NzLCBtZWRpYTogbWVkaWEsIHNvdXJjZU1hcDogc291cmNlTWFwfTtcclxuXHRcdGlmKCFuZXdTdHlsZXNbaWRdKVxyXG5cdFx0XHRzdHlsZXMucHVzaChuZXdTdHlsZXNbaWRdID0ge2lkOiBpZCwgcGFydHM6IFtwYXJ0XX0pO1xyXG5cdFx0ZWxzZVxyXG5cdFx0XHRuZXdTdHlsZXNbaWRdLnBhcnRzLnB1c2gocGFydCk7XHJcblx0fVxyXG5cdHJldHVybiBzdHlsZXM7XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGluc2VydFN0eWxlRWxlbWVudChvcHRpb25zLCBzdHlsZUVsZW1lbnQpIHtcclxuXHR2YXIgaGVhZCA9IGdldEhlYWRFbGVtZW50KCk7XHJcblx0dmFyIGxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wID0gc3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3Bbc3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AubGVuZ3RoIC0gMV07XHJcblx0aWYgKG9wdGlvbnMuaW5zZXJ0QXQgPT09IFwidG9wXCIpIHtcclxuXHRcdGlmKCFsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcCkge1xyXG5cdFx0XHRoZWFkLmluc2VydEJlZm9yZShzdHlsZUVsZW1lbnQsIGhlYWQuZmlyc3RDaGlsZCk7XHJcblx0XHR9IGVsc2UgaWYobGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3AubmV4dFNpYmxpbmcpIHtcclxuXHRcdFx0aGVhZC5pbnNlcnRCZWZvcmUoc3R5bGVFbGVtZW50LCBsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcC5uZXh0U2libGluZyk7XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHRoZWFkLmFwcGVuZENoaWxkKHN0eWxlRWxlbWVudCk7XHJcblx0XHR9XHJcblx0XHRzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5wdXNoKHN0eWxlRWxlbWVudCk7XHJcblx0fSBlbHNlIGlmIChvcHRpb25zLmluc2VydEF0ID09PSBcImJvdHRvbVwiKSB7XHJcblx0XHRoZWFkLmFwcGVuZENoaWxkKHN0eWxlRWxlbWVudCk7XHJcblx0fSBlbHNlIHtcclxuXHRcdHRocm93IG5ldyBFcnJvcihcIkludmFsaWQgdmFsdWUgZm9yIHBhcmFtZXRlciAnaW5zZXJ0QXQnLiBNdXN0IGJlICd0b3AnIG9yICdib3R0b20nLlwiKTtcclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIHJlbW92ZVN0eWxlRWxlbWVudChzdHlsZUVsZW1lbnQpIHtcclxuXHRzdHlsZUVsZW1lbnQucGFyZW50Tm9kZS5yZW1vdmVDaGlsZChzdHlsZUVsZW1lbnQpO1xyXG5cdHZhciBpZHggPSBzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5pbmRleE9mKHN0eWxlRWxlbWVudCk7XHJcblx0aWYoaWR4ID49IDApIHtcclxuXHRcdHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLnNwbGljZShpZHgsIDEpO1xyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gY3JlYXRlU3R5bGVFbGVtZW50KG9wdGlvbnMpIHtcclxuXHR2YXIgc3R5bGVFbGVtZW50ID0gZG9jdW1lbnQuY3JlYXRlRWxlbWVudChcInN0eWxlXCIpO1xyXG5cdHN0eWxlRWxlbWVudC50eXBlID0gXCJ0ZXh0L2Nzc1wiO1xyXG5cdGluc2VydFN0eWxlRWxlbWVudChvcHRpb25zLCBzdHlsZUVsZW1lbnQpO1xyXG5cdHJldHVybiBzdHlsZUVsZW1lbnQ7XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGNyZWF0ZUxpbmtFbGVtZW50KG9wdGlvbnMpIHtcclxuXHR2YXIgbGlua0VsZW1lbnQgPSBkb2N1bWVudC5jcmVhdGVFbGVtZW50KFwibGlua1wiKTtcclxuXHRsaW5rRWxlbWVudC5yZWwgPSBcInN0eWxlc2hlZXRcIjtcclxuXHRpbnNlcnRTdHlsZUVsZW1lbnQob3B0aW9ucywgbGlua0VsZW1lbnQpO1xyXG5cdHJldHVybiBsaW5rRWxlbWVudDtcclxufVxyXG5cclxuZnVuY3Rpb24gYWRkU3R5bGUob2JqLCBvcHRpb25zKSB7XHJcblx0dmFyIHN0eWxlRWxlbWVudCwgdXBkYXRlLCByZW1vdmU7XHJcblxyXG5cdGlmIChvcHRpb25zLnNpbmdsZXRvbikge1xyXG5cdFx0dmFyIHN0eWxlSW5kZXggPSBzaW5nbGV0b25Db3VudGVyKys7XHJcblx0XHRzdHlsZUVsZW1lbnQgPSBzaW5nbGV0b25FbGVtZW50IHx8IChzaW5nbGV0b25FbGVtZW50ID0gY3JlYXRlU3R5bGVFbGVtZW50KG9wdGlvbnMpKTtcclxuXHRcdHVwZGF0ZSA9IGFwcGx5VG9TaW5nbGV0b25UYWcuYmluZChudWxsLCBzdHlsZUVsZW1lbnQsIHN0eWxlSW5kZXgsIGZhbHNlKTtcclxuXHRcdHJlbW92ZSA9IGFwcGx5VG9TaW5nbGV0b25UYWcuYmluZChudWxsLCBzdHlsZUVsZW1lbnQsIHN0eWxlSW5kZXgsIHRydWUpO1xyXG5cdH0gZWxzZSBpZihvYmouc291cmNlTWFwICYmXHJcblx0XHR0eXBlb2YgVVJMID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBVUkwuY3JlYXRlT2JqZWN0VVJMID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBVUkwucmV2b2tlT2JqZWN0VVJMID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBCbG9iID09PSBcImZ1bmN0aW9uXCIgJiZcclxuXHRcdHR5cGVvZiBidG9hID09PSBcImZ1bmN0aW9uXCIpIHtcclxuXHRcdHN0eWxlRWxlbWVudCA9IGNyZWF0ZUxpbmtFbGVtZW50KG9wdGlvbnMpO1xyXG5cdFx0dXBkYXRlID0gdXBkYXRlTGluay5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCk7XHJcblx0XHRyZW1vdmUgPSBmdW5jdGlvbigpIHtcclxuXHRcdFx0cmVtb3ZlU3R5bGVFbGVtZW50KHN0eWxlRWxlbWVudCk7XHJcblx0XHRcdGlmKHN0eWxlRWxlbWVudC5ocmVmKVxyXG5cdFx0XHRcdFVSTC5yZXZva2VPYmplY3RVUkwoc3R5bGVFbGVtZW50LmhyZWYpO1xyXG5cdFx0fTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0c3R5bGVFbGVtZW50ID0gY3JlYXRlU3R5bGVFbGVtZW50KG9wdGlvbnMpO1xyXG5cdFx0dXBkYXRlID0gYXBwbHlUb1RhZy5iaW5kKG51bGwsIHN0eWxlRWxlbWVudCk7XHJcblx0XHRyZW1vdmUgPSBmdW5jdGlvbigpIHtcclxuXHRcdFx0cmVtb3ZlU3R5bGVFbGVtZW50KHN0eWxlRWxlbWVudCk7XHJcblx0XHR9O1xyXG5cdH1cclxuXHJcblx0dXBkYXRlKG9iaik7XHJcblxyXG5cdHJldHVybiBmdW5jdGlvbiB1cGRhdGVTdHlsZShuZXdPYmopIHtcclxuXHRcdGlmKG5ld09iaikge1xyXG5cdFx0XHRpZihuZXdPYmouY3NzID09PSBvYmouY3NzICYmIG5ld09iai5tZWRpYSA9PT0gb2JqLm1lZGlhICYmIG5ld09iai5zb3VyY2VNYXAgPT09IG9iai5zb3VyY2VNYXApXHJcblx0XHRcdFx0cmV0dXJuO1xyXG5cdFx0XHR1cGRhdGUob2JqID0gbmV3T2JqKTtcclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHJlbW92ZSgpO1xyXG5cdFx0fVxyXG5cdH07XHJcbn1cclxuXHJcbnZhciByZXBsYWNlVGV4dCA9IChmdW5jdGlvbiAoKSB7XHJcblx0dmFyIHRleHRTdG9yZSA9IFtdO1xyXG5cclxuXHRyZXR1cm4gZnVuY3Rpb24gKGluZGV4LCByZXBsYWNlbWVudCkge1xyXG5cdFx0dGV4dFN0b3JlW2luZGV4XSA9IHJlcGxhY2VtZW50O1xyXG5cdFx0cmV0dXJuIHRleHRTdG9yZS5maWx0ZXIoQm9vbGVhbikuam9pbignXFxuJyk7XHJcblx0fTtcclxufSkoKTtcclxuXHJcbmZ1bmN0aW9uIGFwcGx5VG9TaW5nbGV0b25UYWcoc3R5bGVFbGVtZW50LCBpbmRleCwgcmVtb3ZlLCBvYmopIHtcclxuXHR2YXIgY3NzID0gcmVtb3ZlID8gXCJcIiA6IG9iai5jc3M7XHJcblxyXG5cdGlmIChzdHlsZUVsZW1lbnQuc3R5bGVTaGVldCkge1xyXG5cdFx0c3R5bGVFbGVtZW50LnN0eWxlU2hlZXQuY3NzVGV4dCA9IHJlcGxhY2VUZXh0KGluZGV4LCBjc3MpO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR2YXIgY3NzTm9kZSA9IGRvY3VtZW50LmNyZWF0ZVRleHROb2RlKGNzcyk7XHJcblx0XHR2YXIgY2hpbGROb2RlcyA9IHN0eWxlRWxlbWVudC5jaGlsZE5vZGVzO1xyXG5cdFx0aWYgKGNoaWxkTm9kZXNbaW5kZXhdKSBzdHlsZUVsZW1lbnQucmVtb3ZlQ2hpbGQoY2hpbGROb2Rlc1tpbmRleF0pO1xyXG5cdFx0aWYgKGNoaWxkTm9kZXMubGVuZ3RoKSB7XHJcblx0XHRcdHN0eWxlRWxlbWVudC5pbnNlcnRCZWZvcmUoY3NzTm9kZSwgY2hpbGROb2Rlc1tpbmRleF0pO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0c3R5bGVFbGVtZW50LmFwcGVuZENoaWxkKGNzc05vZGUpO1xyXG5cdFx0fVxyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gYXBwbHlUb1RhZyhzdHlsZUVsZW1lbnQsIG9iaikge1xyXG5cdHZhciBjc3MgPSBvYmouY3NzO1xyXG5cdHZhciBtZWRpYSA9IG9iai5tZWRpYTtcclxuXHJcblx0aWYobWVkaWEpIHtcclxuXHRcdHN0eWxlRWxlbWVudC5zZXRBdHRyaWJ1dGUoXCJtZWRpYVwiLCBtZWRpYSlcclxuXHR9XHJcblxyXG5cdGlmKHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0KSB7XHJcblx0XHRzdHlsZUVsZW1lbnQuc3R5bGVTaGVldC5jc3NUZXh0ID0gY3NzO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR3aGlsZShzdHlsZUVsZW1lbnQuZmlyc3RDaGlsZCkge1xyXG5cdFx0XHRzdHlsZUVsZW1lbnQucmVtb3ZlQ2hpbGQoc3R5bGVFbGVtZW50LmZpcnN0Q2hpbGQpO1xyXG5cdFx0fVxyXG5cdFx0c3R5bGVFbGVtZW50LmFwcGVuZENoaWxkKGRvY3VtZW50LmNyZWF0ZVRleHROb2RlKGNzcykpO1xyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gdXBkYXRlTGluayhsaW5rRWxlbWVudCwgb2JqKSB7XHJcblx0dmFyIGNzcyA9IG9iai5jc3M7XHJcblx0dmFyIHNvdXJjZU1hcCA9IG9iai5zb3VyY2VNYXA7XHJcblxyXG5cdGlmKHNvdXJjZU1hcCkge1xyXG5cdFx0Ly8gaHR0cDovL3N0YWNrb3ZlcmZsb3cuY29tL2EvMjY2MDM4NzVcclxuXHRcdGNzcyArPSBcIlxcbi8qIyBzb3VyY2VNYXBwaW5nVVJMPWRhdGE6YXBwbGljYXRpb24vanNvbjtiYXNlNjQsXCIgKyBidG9hKHVuZXNjYXBlKGVuY29kZVVSSUNvbXBvbmVudChKU09OLnN0cmluZ2lmeShzb3VyY2VNYXApKSkpICsgXCIgKi9cIjtcclxuXHR9XHJcblxyXG5cdHZhciBibG9iID0gbmV3IEJsb2IoW2Nzc10sIHsgdHlwZTogXCJ0ZXh0L2Nzc1wiIH0pO1xyXG5cclxuXHR2YXIgb2xkU3JjID0gbGlua0VsZW1lbnQuaHJlZjtcclxuXHJcblx0bGlua0VsZW1lbnQuaHJlZiA9IFVSTC5jcmVhdGVPYmplY3RVUkwoYmxvYik7XHJcblxyXG5cdGlmKG9sZFNyYylcclxuXHRcdFVSTC5yZXZva2VPYmplY3RVUkwob2xkU3JjKTtcclxufVxyXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXG4gKiogbW9kdWxlIGlkID0gMzhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8vIHN0eWxlLWxvYWRlcjogQWRkcyBzb21lIGNzcyB0byB0aGUgRE9NIGJ5IGFkZGluZyBhIDxzdHlsZT4gdGFnXG5cbi8vIGxvYWQgdGhlIHN0eWxlc1xudmFyIGNvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZmF2b3VyaXRlcy5zY3NzXCIpO1xuaWYodHlwZW9mIGNvbnRlbnQgPT09ICdzdHJpbmcnKSBjb250ZW50ID0gW1ttb2R1bGUuaWQsIGNvbnRlbnQsICcnXV07XG4vLyBhZGQgdGhlIHN0eWxlcyB0byB0aGUgRE9NXG52YXIgdXBkYXRlID0gcmVxdWlyZShcIiEuLy4uL25vZGVfbW9kdWxlcy9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXCIpKGNvbnRlbnQsIHt9KTtcbmlmKGNvbnRlbnQubG9jYWxzKSBtb2R1bGUuZXhwb3J0cyA9IGNvbnRlbnQubG9jYWxzO1xuLy8gSG90IE1vZHVsZSBSZXBsYWNlbWVudFxuaWYobW9kdWxlLmhvdCkge1xuXHQvLyBXaGVuIHRoZSBzdHlsZXMgY2hhbmdlLCB1cGRhdGUgdGhlIDxzdHlsZT4gdGFnc1xuXHRpZighY29udGVudC5sb2NhbHMpIHtcblx0XHRtb2R1bGUuaG90LmFjY2VwdChcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Zhdm91cml0ZXMuc2Nzc1wiLCBmdW5jdGlvbigpIHtcblx0XHRcdHZhciBuZXdDb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Zhdm91cml0ZXMuc2Nzc1wiKTtcblx0XHRcdGlmKHR5cGVvZiBuZXdDb250ZW50ID09PSAnc3RyaW5nJykgbmV3Q29udGVudCA9IFtbbW9kdWxlLmlkLCBuZXdDb250ZW50LCAnJ11dO1xuXHRcdFx0dXBkYXRlKG5ld0NvbnRlbnQpO1xuXHRcdH0pO1xuXHR9XG5cdC8vIFdoZW4gdGhlIG1vZHVsZSBpcyBkaXNwb3NlZCwgcmVtb3ZlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0bW9kdWxlLmhvdC5kaXNwb3NlKGZ1bmN0aW9uKCkgeyB1cGRhdGUoKTsgfSk7XG59XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSAzOVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiZXhwb3J0cyA9IG1vZHVsZS5leHBvcnRzID0gcmVxdWlyZShcIi4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXCIpKCk7XG4vLyBpbXBvcnRzXG5cblxuLy8gbW9kdWxlXG5leHBvcnRzLnB1c2goW21vZHVsZS5pZCwgXCJAY2hhcnNldCBcXFwiVVRGLThcXFwiO1xcbiNmYXZvdXJpdGVzIHtcXG4gIHdpZHRoOiA5OTBweDtcXG4gIG1hcmdpbjogM3B4IGF1dG8gMDsgfVxcbiAgI2Zhdm91cml0ZXMgLnJpZ2h0LWNvbHVtbiB7XFxuICAgIHdpZHRoOiA0OTBweDtcXG4gICAgZmxvYXQ6IHJpZ2h0OyB9XFxuICAjZmF2b3VyaXRlcyAubGVmdC1jb2x1bW4ge1xcbiAgICB3aWR0aDogNDkwcHg7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuXFxuLmJ1bGxldGluLXNob3J0IHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIHBhZGRpbmc6IDIwcHggMTZweDtcXG4gIHdpZHRoOiA0OTBweDtcXG4gIG1hcmdpbi1ib3R0b206IDdweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAvKiDQm9C10LLQsNGPINC60L7Qu9C+0L3QutCwICovXFxuICAvKiDQn9GA0LDQstCw0Y8g0LrQvtC70L7QvdC60LAgKi8gfVxcbiAgLmJ1bGxldGluLXNob3J0ID4gZGl2IHtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLXJpZ2h0LWNvbHVtbiB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgd2lkdGg6IDkwcHg7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLXJpZ2h0LWNvbHVtbiA+IC5vbk9yT2ZmTGluZVVzZXIge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB0b3A6IDVweDtcXG4gICAgICBsZWZ0OiAxMHB4OyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWNlbnRlci1jb2x1bW4ge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMTVweDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IGgzIHtcXG4gICAgbWFyZ2luOiAwO1xcbiAgICBmb250OiA0MDAgMjBweCAvIDI0cHggUm9ib3RvO1xcbiAgICBjb2xvcjogIzIxMjEyMTtcXG4gICAgd2lkdGg6IDIwNnB4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5idWxsZXRpbi1zaG9ydCBoMzpob3ZlciB7XFxuICAgICAgdGV4dC1kZWNvcmF0aW9uOiB1bmRlcmxpbmU7XFxuICAgICAgLXdlYmtpdC10ZXh0LWRlY29yYXRpb24tY29sb3I6IGdyYXk7XFxuICAgICAgICAgICAgICB0ZXh0LWRlY29yYXRpb24tY29sb3I6IGdyYXk7XFxuICAgICAgLXdlYmtpdC10ZXh0LWRlY29yYXRpb24tc3R5bGU6IGRhc2hlZDtcXG4gICAgICAgICAgICAgIHRleHQtZGVjb3JhdGlvbi1zdHlsZTogZGFzaGVkOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWNhdGVnb3J5IHtcXG4gICAgY29sb3I6IHJnYmEoMzEsIDMxLCAzMSwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTQuNHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTFweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMTZweDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1kZXNjcmlwdGlvbiB7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTguNnB4IFJvYm90bztcXG4gICAgd2lkdGg6IDI1NHB4O1xcbiAgICBjb2xvcjogIzBkMGQxZTsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1pbWFnZSB7XFxuICAgIHdpZHRoOiA5MHB4O1xcbiAgICBoZWlnaHQ6IDkxcHg7XFxuICAgIGJhY2tncm91bmQ6ICMxODc1RDAgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9idWxsZXRpbi1kZWZhdWx0LnBuZ1wiKSArIFwiKTsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1wcmljZSB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgYm90dG9tOiAtNTRweDtcXG4gICAgcmlnaHQ6IDA7XFxuICAgIGNvbG9yOiAjMjAyMDIwO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE2LjhweCBSb2JvdG87IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tYWN0aW9uIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBib3R0b206IDIwcHg7XFxuICAgIHJpZ2h0OiAxMTdweDtcXG4gICAgY29sb3I6ICMyMTIxMjE7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMTYuOHB4IFJvYm90bzsgfVxcbiAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24ge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgYm90dG9tOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICB6LWluZGV4OiAxOyB9XFxuICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbiB7XFxuICAgICAgd2lkdGg6IDEwMHB4O1xcbiAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjU5MTFEO1xcbiAgICAgIHRleHQtYWxpZ246IGNlbnRlcjsgfVxcbiAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbjpiZWZvcmUge1xcbiAgICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgIGJvdHRvbTogLTE0cHg7XFxuICAgICAgICBib3JkZXI6IDEzcHggc29saWQgI2U1N2IwMDtcXG4gICAgICAgIHotaW5kZXg6IC0xO1xcbiAgICAgICAgbGVmdDogLTIzcHg7XFxuICAgICAgICBib3JkZXItcmlnaHQtd2lkdGg6IDEuNWVtO1xcbiAgICAgICAgYm9yZGVyLWxlZnQtY29sb3I6IHRyYW5zcGFyZW50O1xcbiAgICAgICAgYm94LXNoYWRvdzogMnB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTsgfVxcbiAgICAgIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uID4gLnJpYmJvbjphZnRlciB7XFxuICAgICAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgYm90dG9tOiAwO1xcbiAgICAgICAgYm9yZGVyOiAxM3B4IHNvbGlkICNGNTkxMUQ7XFxuICAgICAgICByaWdodDogLTEzcHg7XFxuICAgICAgICBib3JkZXItbGVmdC13aWR0aDogMDtcXG4gICAgICAgIGJvcmRlci1yaWdodC1jb2xvcjogdHJhbnNwYXJlbnQ7IH1cXG4gICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib24gPiAucmliYm9uLWNvbnRlbnQge1xcbiAgICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgICBmb250OiA0MDAgMTRweCAvIDI2cHggUm9ib3RvO1xcbiAgICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib24gPiAucmliYm9uLWNvbnRlbnQ6YmVmb3JlIHtcXG4gICAgICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICAgIGJvcmRlci1zdHlsZTogc29saWQ7XFxuICAgICAgICAgIGJvcmRlci1jb2xvcjogIzJCNEE2NyB0cmFuc3BhcmVudCB0cmFuc3BhcmVudCB0cmFuc3BhcmVudDtcXG4gICAgICAgICAgYm90dG9tOiAtMTRweDtcXG4gICAgICAgICAgbGVmdDogMDtcXG4gICAgICAgICAgYm9yZGVyLXdpZHRoOiAxZW0gMCAwIDFlbTsgfVxcblxcbi5jaGVja0JveCB7XFxuICB3aWR0aDogMjVweDtcXG4gIGhlaWdodDogMjVweDtcXG4gIGJvcmRlcjogMXB4IHNvbGlkIGdyZXk7XFxuICBib3JkZXItcmFkaXVzOiA1cHg7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG5cXG4uY2hlY2tlZCB7XFxuICBiYWNrZ3JvdW5kOiAjMTg3NUQwIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICBib3JkZXItY29sb3I6ICMxODc1RDAgIWltcG9ydGFudDsgfVxcblxcbmRpdi5leGNsYW1hdGlvblBvaW50IHtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICBoZWlnaHQ6IDI3cHg7XFxuICB3aWR0aDogMjdweDtcXG4gIG1hcmdpbi10b3A6IDEwcHg7IH1cXG5cXG4uZG9sbGFyQmlsbCB7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2RvbGxhckJpbGwucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgaGVpZ2h0OiAyN3B4O1xcbiAgd2lkdGg6IDI3cHg7XFxuICBtYXJnaW4tdG9wOiAxMHB4OyB9XFxuXCIsIFwiXCJdKTtcblxuLy8gZXhwb3J0c1xuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vY3NzLWxvYWRlciEuL34vcG9zdGNzcy1sb2FkZXIhLi9+L3Nhc3MtbG9hZGVyIS4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDQwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYnVsbGV0aW4tZGVmYXVsdC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nXG4gKiogbW9kdWxlIGlkID0gNDFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9WLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVi5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0MlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2V4Y2xhbWF0aW9uUG9pbnQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9leGNsYW1hdGlvblBvaW50LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZG9sbGFyQmlsbC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2RvbGxhckJpbGwucG5nXG4gKiogbW9kdWxlIGlkID0gNDRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8vIHN0eWxlLWxvYWRlcjogQWRkcyBzb21lIGNzcyB0byB0aGUgRE9NIGJ5IGFkZGluZyBhIDxzdHlsZT4gdGFnXG5cbi8vIGxvYWQgdGhlIHN0eWxlc1xudmFyIGNvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZWRpdC1wcm9maWxlLnNjc3NcIik7XG5pZih0eXBlb2YgY29udGVudCA9PT0gJ3N0cmluZycpIGNvbnRlbnQgPSBbW21vZHVsZS5pZCwgY29udGVudCwgJyddXTtcbi8vIGFkZCB0aGUgc3R5bGVzIHRvIHRoZSBET01cbnZhciB1cGRhdGUgPSByZXF1aXJlKFwiIS4vLi4vbm9kZV9tb2R1bGVzL3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanNcIikoY29udGVudCwge30pO1xuaWYoY29udGVudC5sb2NhbHMpIG1vZHVsZS5leHBvcnRzID0gY29udGVudC5sb2NhbHM7XG4vLyBIb3QgTW9kdWxlIFJlcGxhY2VtZW50XG5pZihtb2R1bGUuaG90KSB7XG5cdC8vIFdoZW4gdGhlIHN0eWxlcyBjaGFuZ2UsIHVwZGF0ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdGlmKCFjb250ZW50LmxvY2Fscykge1xuXHRcdG1vZHVsZS5ob3QuYWNjZXB0KFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZWRpdC1wcm9maWxlLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9lZGl0LXByb2ZpbGUuc2Nzc1wiKTtcblx0XHRcdGlmKHR5cGVvZiBuZXdDb250ZW50ID09PSAnc3RyaW5nJykgbmV3Q29udGVudCA9IFtbbW9kdWxlLmlkLCBuZXdDb250ZW50LCAnJ11dO1xuXHRcdFx0dXBkYXRlKG5ld0NvbnRlbnQpO1xuXHRcdH0pO1xuXHR9XG5cdC8vIFdoZW4gdGhlIG1vZHVsZSBpcyBkaXNwb3NlZCwgcmVtb3ZlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0bW9kdWxlLmhvdC5kaXNwb3NlKGZ1bmN0aW9uKCkgeyB1cGRhdGUoKTsgfSk7XG59XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDQ1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcInNlY3Rpb24uZWRpdFByb2ZpbGUge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICB3aWR0aDogMTEwNXB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byA0MHB4O1xcbiAgcGFkZGluZzogNjVweCAwIDQ1cHggMDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUge1xcbiAgICB3aWR0aDogNjUwcHg7XFxuICAgIG1hcmdpbjogMCBhdXRvOyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiBoMiB7XFxuICAgICAgZm9udDogNzAwIDIycHggUm9ib3RvO1xcbiAgICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0MHB4OyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuc2VsZWN0Qm94IHtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzllOWU5ZTtcXG4gICAgICBtYXJnaW4tdG9wOiAxNXB4O1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gICAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICAgICAgICBwYWRkaW5nLWxlZnQ6IDVweDsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLmVkaXQtcHJvZmlsZS1mb3JtLWZvdG8ge1xcbiAgICAgIGZsb2F0OiByaWdodDtcXG4gICAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7XFxuICAgICAgd2lkdGg6IDQxMHB4O1xcbiAgICAgIHBhZGRpbmctYm90dG9tOiA1cHg7IH1cXG4gICAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLmVkaXQtcHJvZmlsZS1mb3JtLWZvdG8gPiBpbnB1dCB7XFxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICB6LWluZGV4OiAtMTsgfVxcbiAgICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgPiAuZWRpdC1wcm9maWxlLWZvcm0gPiAuZWRpdC1wcm9maWxlLWZvcm0tZm90byA+IHAge1xcbiAgICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgIHBhZGRpbmctdG9wOiAxMXB4O1xcbiAgICAgICAgcGFkZGluZy1sZWZ0OiA1cHg7XFxuICAgICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLmVkaXQtcHJvZmlsZS1mb3JtLWZvdG8gPiAuYnRuLWJsdWUge1xcbiAgICAgICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgICAgIGhlaWdodDogMzBweDtcXG4gICAgICAgIHdpZHRoOiA4NXB4O1xcbiAgICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgICAgbGluZS1oZWlnaHQ6IDMwcHg7IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSAuZWRpdC1wcm9maWxlLWZvcm0tY29udGFjdHMtY29udGFpbmVyIC5pbnB1dEZvcm0ge1xcbiAgICAgIHdpZHRoOiA5NSU7XFxuICAgICAgZGlzcGxheTogaW5saW5lLWJsb2NrOyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgLmVkaXQtcHJvZmlsZS1mb3JtLWNvbnRhY3RzLWNvbnRhaW5lciBidXR0b24ge1xcbiAgICAgIHdpZHRoOiAxNnB4O1xcbiAgICAgIGhlaWdodDogMTZweDtcXG4gICAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9pY29uX2Nsb3NlX2JsdWUucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgICBiYWNrZ3JvdW5kLXNpemU6IGNvbnRhaW47IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSAuc29jaWFsLWxpbmstY29udGFpbmVyIHtcXG4gICAgICBoZWlnaHQ6IGF1dG87IH1cXG4gICAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIC5zb2NpYWwtbGluay1jb250YWluZXIgZGl2IHtcXG4gICAgICAgIHdpZHRoOiAyNHB4O1xcbiAgICAgICAgaGVpZ2h0OiAyNHB4O1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICBtYXJnaW4tcmlnaHQ6IDQ3cHg7IH1cXG4gIHNlY3Rpb24uZWRpdFByb2ZpbGUgLnVwbG9hZEZpbGVGb3JtIHtcXG4gICAgdmlzaWJpbGl0eTogaGlkZGVuOyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgLnVwbG9hZEZpbGVGb3JtIGlucHV0IHtcXG4gICAgICB3aWR0aDogMHB4O1xcbiAgICAgIGhlaWdodDogMHB4OyB9XFxuXCIsIFwiXCJdKTtcblxuLy8gZXhwb3J0c1xuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vY3NzLWxvYWRlciEuL34vcG9zdGNzcy1sb2FkZXIhLi9+L3Nhc3MtbG9hZGVyIS4vc3R5bGVzL2VkaXQtcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9pY29uX2Nsb3NlX2JsdWUucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9pY29uX2Nsb3NlX2JsdWUucG5nXG4gKiogbW9kdWxlIGlkID0gNDdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8vIHN0eWxlLWxvYWRlcjogQWRkcyBzb21lIGNzcyB0byB0aGUgRE9NIGJ5IGFkZGluZyBhIDxzdHlsZT4gdGFnXG5cbi8vIGxvYWQgdGhlIHN0eWxlc1xudmFyIGNvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vcHJvZmlsZS5zY3NzXCIpO1xuaWYodHlwZW9mIGNvbnRlbnQgPT09ICdzdHJpbmcnKSBjb250ZW50ID0gW1ttb2R1bGUuaWQsIGNvbnRlbnQsICcnXV07XG4vLyBhZGQgdGhlIHN0eWxlcyB0byB0aGUgRE9NXG52YXIgdXBkYXRlID0gcmVxdWlyZShcIiEuLy4uL25vZGVfbW9kdWxlcy9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXCIpKGNvbnRlbnQsIHt9KTtcbmlmKGNvbnRlbnQubG9jYWxzKSBtb2R1bGUuZXhwb3J0cyA9IGNvbnRlbnQubG9jYWxzO1xuLy8gSG90IE1vZHVsZSBSZXBsYWNlbWVudFxuaWYobW9kdWxlLmhvdCkge1xuXHQvLyBXaGVuIHRoZSBzdHlsZXMgY2hhbmdlLCB1cGRhdGUgdGhlIDxzdHlsZT4gdGFnc1xuXHRpZighY29udGVudC5sb2NhbHMpIHtcblx0XHRtb2R1bGUuaG90LmFjY2VwdChcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL3Byb2ZpbGUuc2Nzc1wiLCBmdW5jdGlvbigpIHtcblx0XHRcdHZhciBuZXdDb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL3Byb2ZpbGUuc2Nzc1wiKTtcblx0XHRcdGlmKHR5cGVvZiBuZXdDb250ZW50ID09PSAnc3RyaW5nJykgbmV3Q29udGVudCA9IFtbbW9kdWxlLmlkLCBuZXdDb250ZW50LCAnJ11dO1xuXHRcdFx0dXBkYXRlKG5ld0NvbnRlbnQpO1xuXHRcdH0pO1xuXHR9XG5cdC8vIFdoZW4gdGhlIG1vZHVsZSBpcyBkaXNwb3NlZCwgcmVtb3ZlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0bW9kdWxlLmhvdC5kaXNwb3NlKGZ1bmN0aW9uKCkgeyB1cGRhdGUoKTsgfSk7XG59XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL3N0eWxlcy9wcm9maWxlLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0OFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiZXhwb3J0cyA9IG1vZHVsZS5leHBvcnRzID0gcmVxdWlyZShcIi4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXCIpKCk7XG4vLyBpbXBvcnRzXG5cblxuLy8gbW9kdWxlXG5leHBvcnRzLnB1c2goW21vZHVsZS5pZCwgXCIudmlldy1wcm9maWxlLWNvbnRhaW5lciB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjZmZmO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIHBhZGRpbmc6IDQwcHggMTI3cHggMzBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDtcXG4gIHdpZHRoOiAxMTAzcHg7XFxuICBmb250OiA0MDAgMTZweC8yNHB4IFJvYm90bztcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7IH1cXG4gIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGgxIHtcXG4gICAgcGFkZGluZy1ib3R0b206IDE2cHg7XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgZm9udDogNDAwIDIycHgvMjZweCBSb2JvdG87IH1cXG4gIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0Oi1tb3otcmVhZC1vbmx5IHtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgb3V0bGluZTogbm9uZTsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaW5wdXQ6cmVhZC1vbmx5IHtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgb3V0bGluZTogbm9uZTsgfVxcbiAgICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDotbW96LXJlYWQtb25seTpub3QoOmxhc3Qtb2YtdHlwZSkge1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gICAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaW5wdXQ6cmVhZC1vbmx5Om5vdCg6bGFzdC1vZi10eXBlKSB7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgLnNvY2lhbC1saW5rLWNvbnRhaW5lciB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG5cXG4ucHJvZmlsZS1jb250YWluZXJzLXdyYXAge1xcbiAgd2lkdGg6IDg0OXB4O1xcbiAgbWFyZ2luOiAwIGF1dG87IH1cXG5cXG4ucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciwgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tbGVmdCwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLXJpZ2h0LCAucHJvZmlsZS1pbmZvIHtcXG4gIGZsb2F0OiBsZWZ0OyB9XFxuICAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lcjo6YWZ0ZXIsIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lcjo6YWZ0ZXIsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0OjphZnRlciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLXJpZ2h0OjphZnRlciwgLnByb2ZpbGUtaW5mbzo6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnZpZXctcHJvZmlsZTo6YWZ0ZXIsIC52aWV3LXByb2ZpbGUtY29udGFpbmVyOjphZnRlciB7XFxuICBjb250ZW50OiBcXFwiXFxcIjtcXG4gIGRpc3BsYXk6IHRhYmxlO1xcbiAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciB7XFxuICB3aWR0aDogMTkwcHg7XFxuICBwYWRkaW5nOiAwIDUycHg7IH1cXG4gIC5wcm9maWxlLWxlZnQtY29udGFpbmVyIC5idG4tYmx1ZSB7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgaGVpZ2h0OiAzNnB4O1xcbiAgICBsaW5lLWhlaWdodDogMzZweDtcXG4gICAgbWFyZ2luOiAwIGF1dG87XFxuICAgIG1hcmdpbi10b3A6IDlweDsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXIgPiAuaHotY2VudGVyaW5nLXdyYXBwZXIgPiAucHJvZmlsZS1zZXR0aW5ncyA+IC5zZWxlY3RCb3gge1xcbiAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgbWFyZ2luLWJvdHRvbTogMjVweDsgfVxcbiAgICAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciA+IC5oei1jZW50ZXJpbmctd3JhcHBlciA+IC5wcm9maWxlLXNldHRpbmdzID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IC8gMjRweCBSb2JvdG87XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7IH1cXG5cXG4ucHJvZmlsZS1yaWdodC1jb250YWluZXIge1xcbiAgd2lkdGg6IDQ4NXB4O1xcbiAgcGFkZGluZy1sZWZ0OiAyMHB4O1xcbiAgYm9yZGVyLWxlZnQ6IDFweCBzb2xpZCAjZWJlYmViOyB9XFxuICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiB7XFxuICAgIHdpZHRoOiBhdXRvO1xcbiAgICBmbG9hdDogbm9uZTtcXG4gICAgbWFyZ2luLWJvdHRvbTogMDsgfVxcbiAgICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpIHtcXG4gICAgICB3aWR0aDogNTAlOyB9XFxuICAgICAgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyID4gLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGEge1xcbiAgICAgICAgd2lkdGg6IGF1dG87IH1cXG4gICAgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyID4gLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzIHtcXG4gICAgICB3aWR0aDogNTAwcHg7IH1cXG5cXG4ucHJvZmlsZS1pbmZvLXNlY3Rpb24tbGVmdCwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLXJpZ2h0IHtcXG4gIHdpZHRoOiA1MCU7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuXFxuLnByb2ZpbGUtYXZhdGFyIHtcXG4gIHdpZHRoOiAxNDVweDtcXG4gIGhlaWdodDogMjE1cHg7XFxuICBtYXJnaW46IDAgYXV0byA0MHB4O1xcbiAgcGFkZGluZy1ib3R0b206IDVweDtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYXZhdGFyLmpwZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGJhY2tncm91bmQtc2l6ZTogY29udGFpbjtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG5cXG4ucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZSB7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICBwYWRkaW5nOiAxM3B4IDA7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlOjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIHNwYW4ge1xcbiAgICBjb2xvcjogIzkyOTI5MjtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIGRpdiB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldF9ibGFjay5wbmdcIikgKyBcIik7XFxuICAgIHdpZHRoOiAxM3B4O1xcbiAgICBoZWlnaHQ6IDdweDsgfVxcblxcbi5zb2NpYWwtbGluay1jb250YWluZXIgZGl2IHtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIHdpZHRoOiAyNHB4O1xcbiAgaGVpZ2h0OiAyNHB4O1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5zb2NpYWwtbGluay1jb250YWluZXIgZGl2OjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG4gIC5zb2NpYWwtbGluay1jb250YWluZXIgZGl2Om5vdCg6bGFzdC1vZi10eXBlKSB7XFxuICAgIG1hcmdpbi1yaWdodDogMjNweDsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zIHtcXG4gIGRpc3BsYXk6IGJsb2NrO1xcbiAgd2lkdGg6IDEwMCU7XFxuICBtYXJnaW46IDMwcHggMDtcXG4gIGZsb2F0OiBsZWZ0OyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9uczo6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2LCAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50IHtcXG4gIGJvcmRlcjogMXB4IHNvbGlkICNlYmViZWI7XFxuICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IGRpdiB7XFxuICBjb2xvcjogIzkyOTI5MjtcXG4gIHdpZHRoOiA1MCU7XFxuICBwYWRkaW5nOiAyNHB4IDA7XFxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IGRpdjo6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IC5uYXYtaXRlbS1zZWxlY3RlZCB7XFxuICBjb2xvcjogIzdlYWVlMDtcXG4gIGJvcmRlci1ib3R0b206IDJweCBzb2xpZCAjZmY1MjUyOyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudCB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjZjZmNmY2O1xcbiAgaGVpZ2h0OiAxODBweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0ge1xcbiAgd2lkdGg6IDEwMCU7XFxuICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI2ViZWJlYjtcXG4gIGhlaWdodDogMTIzcHg7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS1sb2dvIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICB0b3A6IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIHdpZHRoOiAyN3B4O1xcbiAgICBoZWlnaHQ6IDI3cHg7XFxuICAgIHBhZGRpbmc6IDAgMjNweCAyM3B4IDdweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9pY29uX3VzZXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdDtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICBiYWNrZ3JvdW5kLXBvc2l0aW9uOiBjZW50ZXI7XFxuICAgIG1hcmdpbi10b3A6IDI4cHg7IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSAuY29udGVudC1pdGVtLXRleHQge1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgd2lkdGg6IDM3M3B4O1xcbiAgICBtYXJnaW46IDI4cHggMzhweCAyNHB4IDA7XFxuICAgIGZvbnQ6IDQwMCAxMnB4LzE4cHggUm9ib3RvO1xcbiAgICBjb2xvcjogIzBjMGMxZTsgfVxcbiAgICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS10ZXh0OjphZnRlciB7XFxuICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgZGlzcGxheTogdGFibGU7XFxuICAgICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1pbmZvIHtcXG4gIG1hcmdpbi1ib3R0b206IDMwcHg7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9hdmF0YXIuanBnXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hdmF0YXIuanBnXG4gKiogbW9kdWxlIGlkID0gNTBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9jYXJldF9ibGFjay5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhcmV0X2JsYWNrLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDUxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvaWNvbl91c2VyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvaWNvbl91c2VyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDUyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbmNvbnN0IGRlYnVnID0gcmVxdWlyZSgnLi4vZGF0YS9kZWJ1ZycpXHJcblxyXG5sZXQgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcclxuICBjdHguREVCVUdfTEVWRUwgPSBkZWJ1Zy5ERUJVR1xyXG5cclxuICBjb25zb2xlLmxvZyA9IChmdW5jdGlvbigpIHtcclxuICAgIGxldCBsb2cgPSBjb25zb2xlLmxvZ1xyXG4gICAgaWYoIGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5ERUJVRyApXHJcbiAgICAgIHJldHVybiBsb2dcclxuICAgIGVsc2UgcmV0dXJuICgpID0+IHt9XHJcbiAgfS5iaW5kKGN0eCkpKClcclxuXHJcbiAgY29uc29sZS5lcnJvciA9IChmdW5jdGlvbigpIHtcclxuICAgIGxldCBlcnJvciA9IGNvbnNvbGUuZXJyb3JcclxuICAgIGlmKCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuREVCVUcgfHwgY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLk9OTFlfRVJST1JTIClcclxuICAgICAgcmV0dXJuIGVycm9yXHJcbiAgICBlbHNlIHJldHVybiAoKSA9PiB7fVxyXG4gIH0uYmluZChjdHgpKSgpXHJcblxyXG4gIGNvbnNvbGUuaW5mbyA9IChmdW5jdGlvbigpIHtcclxuICAgIGxldCBpbmZvID0gY29uc29sZS5pbmZvXHJcbiAgICBpZiggY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLkRFQlVHIHx8IGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5PTkxZX0VSUk9SUyApXHJcbiAgICAgIHJldHVybiBpbmZvXHJcbiAgICBlbHNlIHJldHVybiAoKSA9PiB7fVxyXG4gIH0uYmluZChjdHgpKSgpXHJcblxyXG4gIC8qICDQlNC70Y8g0YXQvtGF0LzRiyAqL1xyXG4gIHdpbmRvdy5scyA9IFwiWW91J3ZlIG1pc3NlZCBhIHdpbmRvdywgbG9sID0pXCJcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2xvZ2dlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcIkRFQlVHXCI6IDAsXG5cdFwiT05MWV9FUlJPUlNcIjogMSxcblx0XCJQUk9EVUNUSU9OXCI6IDJcbn07XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2RhdGEvZGVidWcuanNvblxuICoqIG1vZHVsZSBpZCA9IDU0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbmNvbnN0IE1PRFVMRVMgPSB7XHJcbiAgXCJjaGVja2JveFwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL2NoZWNrYm94JyksXHJcbiAgXCJuaWNlQnV0dG9uXCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvbmljZUJ1dHRvbicpLFxyXG4gIFwidGV4dFwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL3RleHQnKSxcclxuICBcInNlbGVjdEJveFwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL3NlbGVjdEJveCcpLFxyXG4gIFwidGV4dEFyZWFcIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy90ZXh0QXJlYScpXHJcbn1cclxuXHJcbndpbmRvdy5lZSA9IHJlcXVpcmUoJy4vZXZlbnRzJylcclxuZWUuaW5pdCgpXHJcblxyXG5sZXQgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxyXG5cclxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGFwcCA9PiB7XHJcbiAgZm9yKGxldCBrZXkgaW4gTU9EVUxFUylcclxuICAgIGFwcC5kaXJlY3RpdmUoa2V5LCBNT0RVTEVTW2tleV0pXHJcblxyXG4gIHJldHVybiBhcHBcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9pbmRleC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcclxuICByZXR1cm4ge1xyXG4gICAgcmVzdHJpY3Q6IFwiRVwiLFxyXG4gICAgcmVxdWlyZTogJ15uZ01vZGVsJyxcclxuICAgIHNjb3BlOiB7XHJcbiAgICAgIG5nTW9kZWw6IFwiPVwiXHJcbiAgICB9LFxyXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiY2hlY2tCb3hcIj48L2Rpdj5gLFxyXG4gICAgcmVwbGFjZTogdHJ1ZSxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQpIHtcclxuICAgICAgbGV0IGVsID0gJGVsZW1lbnRbMF1cclxuICAgICAgLy8uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnY2hlY2tCb3gnKVswXVxyXG5cclxuICAgICAgaWYoJHNjb3BlLm5nTW9kZWwgJiYgIWVsLmNsYXNzTGlzdC5jb250YWlucygnY2hlY2tlZCcpKVxyXG4gICAgICAgIGVsLmNsYXNzTGlzdC5hZGQoJ2NoZWNrZWQnKVxyXG4gICAgICBlbHNlIGlmKCEkc2NvcGUubmdNb2RlbCAmJiBlbC5jbGFzc0xpc3QuY29udGFpbnMoJ2NoZWNrZWQnKSlcclxuICAgICAgICBlbC5jbGFzc0xpc3QucmVtb3ZlKCdjaGVrZWQnKVxyXG5cclxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBlID0+IHtcclxuICAgICAgICBlbC5jbGFzc0xpc3QudG9nZ2xlKCdjaGVja2VkJylcclxuICAgICAgICAkc2NvcGUubmdNb2RlbCA9ICRzY29wZS5uZ01vZGVsID8gIGZhbHNlIDogdHJ1ZVxyXG4gICAgICB9KVxyXG4gICAgfVxyXG4gIH1cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIHJldHVybiB7XHJcbiAgICByZXN0cmljdDogXCJFXCIsXHJcbiAgICB0cmFuc2NsdWRlOiB0cnVlLFxyXG4gICAgc2NvcGUgOiB7XHJcbiAgICAgIGNsYXNzOiBcIkBcIixcclxuICAgICAgbmdDbGljazogXCImXCJcclxuICAgIH0sXHJcbiAgICByZXBsYWNlOiB0cnVlLFxyXG4gICAgdGVtcGxhdGUgOiBgPGRpdiBjbGFzcz1cInt7IGNsYXNzIH19XCI+XHJcbiAgICAgICAgICAgICAgICAgIDxzcGFuIGNsYXNzPVwiaW5rXCI+PC9zcGFuPlxyXG4gICAgICAgICAgICAgICAgICA8bmctdHJhbnNjbHVkZSBzdHlsZT1cImRpc3BsYXk6YmxvY2s7IHdpZHRoOjEwMCU7IGhlaWdodDppbmhlcml0O1wiPjwvbmctdHJhbnNjbHVkZT5cclxuICAgICAgICAgICAgICAgIDwvZGl2PmAsXHJcbiAgICBjb250cm9sbGVyOiBmdW5jdGlvbigkc2NvcGUsICRlbGVtZW50KSB7XHJcbiAgICAgIGxldCBvbkNsaWNrID0gZnVuY3Rpb24oZSkge1xyXG4gICAgICAgIGxldCBpbmsgPSB0aGlzLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2luaycpWzBdXHJcbiAgICAgICAgaW5rLmNsYXNzTGlzdC5yZW1vdmUoJ2FuaW1hdGUnKVxyXG5cclxuICAgICAgICBsZXQgcmVjdCA9IHRoaXMuZ2V0Qm91bmRpbmdDbGllbnRSZWN0KClcclxuXHJcbiAgICAgICAgaWYoICFpbmsuY2xpZW50SGVpZ2h0ICYmICFpbmsuY2xpZW50V2lkdGggKSB7XHJcbiAgICAgICAgICBsZXQgZCA9IE1hdGgubWF4KHRoaXMuY2xpZW50V2lkdGgsIHRoaXMuY2xpZW50SGVpZ2h0KVxyXG4gICAgICAgICAgaW5rLnN0eWxlLmhlaWdodCA9IGluay5zdHlsZS53aWR0aCA9IGAke2R9cHhgXHJcbiAgICAgICAgfVxyXG5cclxuICAgICAgICBpbmsuc3R5bGUudG9wID0gYCR7ZS5jbGllbnRZIC0gcmVjdC50b3AgLSBpbmsuY2xpZW50SGVpZ2h0LzJ9cHhgXHJcbiAgICAgICAgaW5rLnN0eWxlLmxlZnQgPSBgJHtlLmNsaWVudFggLSByZWN0LmxlZnQgLWluay5jbGllbnRXaWR0aC8yfXB4YFxyXG4gICAgICAgIGluay5jbGFzc0xpc3QuYWRkKCdhbmltYXRlJylcclxuICAgICAgfVxyXG5cclxuICAgICAgaWYoJHNjb3BlLm5nQ2xpY2spXHJcbiAgICAgICAgJGVsZW1lbnRbMF0uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCAkc2NvcGUubmdDbGljaylcclxuICAgICAgICBcclxuICAgICAgJGVsZW1lbnRbMF0uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBvbkNsaWNrKVxyXG4gICAgfVxyXG4gIH1cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbmNvbnN0IENPTE9SUyA9IHtcclxuICAgIGJsdWU6IFwiIzE4NzVEMFwiLFxyXG4gICAgd2hpdGU6IFwid2hpdGVcIlxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIHJldHVybiB7XHJcbiAgICByZXN0cmljdDogXCJFXCIsXHJcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxyXG4gICAgc2NvcGUgOiB7XHJcbiAgICAgIGxhYmVsOiBcIkBcIixcclxuICAgICAgbmdNb2RlbDogXCI9XCIsXHJcbiAgICAgIGNvbG9yOiBcIkBcIixcclxuICAgICAgdHlwZTogXCJAXCIsXHJcbiAgICAgIHZhbGlkYXRlOiBcIj1cIixcclxuICAgICAgaXNWYWxpZDogXCI9XCJcclxuICAgIH0sXHJcbiAgICByZXBsYWNlOiB0cnVlLFxyXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiaW5wdXRGb3JtXCI+XHJcbiAgICAgICAgICAgICAgICAgPGxhYmVsPnt7IGxhYmVsIH19PC9sYWJlbD5cclxuICAgICAgICAgICAgICAgICA8aW5wdXQgdHlwZT1cInt7IHR5cGUgfHwgJ3RleHQnfX1cIiBuZy1tb2RlbD1cIm5nTW9kZWxcIj5cclxuICAgICAgICAgICAgICAgICA8ZGl2IGNsYXNzPVwiZXJyb3JzXCI+PC9kaXY+XHJcbiAgICAgICAgICAgICAgIDwvZGl2PmAsXHJcbiAgICBjb250cm9sbGVyOiBmdW5jdGlvbigkc2NvcGUsICRlbGVtZW50LCAkdGltZW91dCkge1xyXG4gICAgICBsZXQgaWQgPSBlZS5vbignZm9ybS1zdWJtaXQnLCB2YWxpZGF0ZSlcclxuICAgICAgJHNjb3BlLiRvbihcIiRkZXN0cm95XCIsIGZ1bmN0aW9uKCkge1xyXG4gICAgICAgIGVlLm9mZihpZClcclxuICAgICAgfS5iaW5kKHRoaXMpKVxyXG5cclxuICAgICAgbGV0IGRlZmF1bHRCb3JkZXIgPSBcIlwiXHJcblxyXG4gICAgICBsZXQgZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgnaW5wdXQnKVswXSxcclxuICAgICAgICAgIGxhYmVsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeVRhZ05hbWUoJ2xhYmVsJylbMF0sXHJcbiAgICAgICAgICBlcnJvciA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2Vycm9ycycpWzBdXHJcblxyXG5cclxuICAgICAgZnVuY3Rpb24gdmFsaWRhdGUoKSB7XHJcbiAgICAgICAgaWYoJHNjb3BlLnZhbGlkYXRlKSB7XHJcbiAgICAgICAgICBmdW5jdGlvbiBoYW5kbGUoZXJyb3IpIHtcclxuICAgICAgICAgICAgaWYodHlwZW9mICRzY29wZS5pc1ZhbGlkICE9PSBcInVuZGVmaW5lZFwiKSB7XHJcbiAgICAgICAgICAgICAgaWYoZXJyb3IubGVuZ3RoKVxyXG4gICAgICAgICAgICAgICAgJHNjb3BlLmlzVmFsaWQgPSBmYWxzZVxyXG4gICAgICAgICAgICAgIGVsc2VcclxuICAgICAgICAgICAgICAgICRzY29wZS5pc1ZhbGlkID0gdHJ1ZVxyXG5cclxuICAgICAgICAgICAgICAkc2NvcGUuJGFwcGx5KClcclxuICAgICAgICAgICAgfVxyXG4gICAgICAgICAgfVxyXG5cclxuICAgICAgICAgIGxldCByZXNwID0gJHNjb3BlLnZhbGlkYXRlKGVsLnZhbHVlKVxyXG5cclxuICAgICAgICAgIGlmKCB0eXBlb2YgcmVzcCA9PT0gXCJzdHJpbmdcIilcclxuICAgICAgICAgICAgaGFuZGxlKCBlcnJvci5pbm5lckhUTUwgPSByZXNwKVxyXG4gICAgICAgICAgZWxzZVxyXG4gICAgICAgICAgICByZXNwLnRoZW4oIGZ1bmN0aW9uKGRhdGEpIHtcclxuICAgICAgICAgICAgICAgIGVycm9yLmlubmVySFRNTCA9IGRhdGFcclxuICAgICAgICAgICAgICAgIGhhbmRsZShkYXRhKVxyXG4gICAgICAgICAgICAgIH0sIGNvbnNvbGUuZXJyb3IpXHJcblxyXG5cclxuICAgICAgICB9XHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIG9uQmx1cihlKSB7XHJcbiAgICAgICAgaWYoICEkc2NvcGUubmdNb2RlbC5sZW5ndGgpXHJcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcclxuXHJcbiAgICAgICAgICB2YWxpZGF0ZSgpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIG9uRm9jdXMoZSkge1xyXG4gICAgICAgIGlmKCEkc2NvcGUubmdNb2RlbC5sZW5ndGgpXHJcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcclxuICAgICAgfVxyXG5cclxuICAgICAgZnVuY3Rpb24gZGlzcGxheUFuaW1hdGlvbigpIHtcclxuICAgICAgICBsYWJlbC5zdHlsZS5jb2xvciA9IENPTE9SU1skc2NvcGUuY29sb3JdXHJcbiAgICAgICAgaWYoIWRlZmF1bHRCb3JkZXIubGVuZ3RoKSB7XHJcbiAgICAgICAgICBkZWZhdWx0Qm9yZGVyID0gd2luZG93LmdldENvbXB1dGVkU3R5bGUobGFiZWwucGFyZW50Tm9kZSkuYm9yZGVyQm90dG9tXHJcbiAgICAgICAgfSBlbHNlIHtcclxuICAgICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gYDJweCBzb2xpZCAke0NPTE9SU1skc2NvcGUuY29sb3JdfWBcclxuICAgICAgICB9XHJcblxyXG4gICAgICAgIGxhYmVsLmNsYXNzTGlzdC5hZGQoJ3RleHRPdXQnKVxyXG4gICAgICB9XHJcblxyXG4gICAgICBmdW5jdGlvbiBoaWRlQW5pbWF0aW9uKCkge1xyXG4gICAgICAgIGxhYmVsLnN0eWxlLmNvbG9yID0gXCJcIlxyXG4gICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gZGVmYXVsdEJvcmRlclxyXG4gICAgICAgIGxhYmVsLmNsYXNzTGlzdC5yZW1vdmUoJ3RleHRPdXQnKVxyXG4gICAgICB9XHJcblxyXG4gICAgICAkdGltZW91dCggKCkgPT4ge1xyXG4gICAgICAgIGlmKCAkc2NvcGUubmdNb2RlbCAmJiAkc2NvcGUubmdNb2RlbC5sZW5ndGggKVxyXG4gICAgICAgICAgZGlzcGxheUFuaW1hdGlvbigpXHJcbiAgICAgICAgZWxzZVxyXG4gICAgICAgICAgaGlkZUFuaW1hdGlvbigpXHJcbiAgICAgIH0sIDI1MClcclxuXHJcblxyXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdibHVyJywgb25CbHVyLmJpbmQodGhpcykpXHJcbiAgICAgIGVsLmFkZEV2ZW50TGlzdGVuZXIoJ2ZvY3VzJywgb25Gb2N1cy5iaW5kKCRzY29wZSkpXHJcbiAgICB9XHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dC5qc1xuICoqLyIsIlwidXNlIHN0cmljdFwiXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIHJldHVybiB7XHJcbiAgICByZXN0cmljdDogXCJFXCIsXHJcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxyXG4gICAgc2NvcGU6IHtcclxuICAgICAgbmdNb2RlbDogXCI9XCIsXHJcbiAgICAgIHNob3c6IFwiQFwiXHJcbiAgICB9LFxyXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwic2VsZWN0Qm94XCI+XHJcbiAgICAgICAgICAgICAgICA8ZGl2IGNsYXNzPVwiZGVmYXVsdFZhbHVlXCIgbmctaGlkZT1cInNob3dcIj5cclxuICAgICAgICAgICAgICAgICAgPHA+e3sgbmdNb2RlbCB9fTwvcD5cclxuICAgICAgICAgICAgICAgIDwvZGl2PlxyXG4gICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cImxpc3RPZlZhbHVlc1wiIG5nLXNob3c9XCJzaG93XCI+XHJcbiAgICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJsaXN0SXRlbVwiIG5nLXJlcGVhdD1cIml0ZW0gaW4gaXRlbXNcIiB2YWx1ZT1cInt7aXRlbX19XCI+e3sgaXRlbSB9fTwvZGl2PlxyXG4gICAgICAgICAgICAgICAgPC9kaXY+XHJcbiAgICAgICAgICAgICAgPC9kaXY+YCxcclxuICAgIHJlcGxhY2U6IHRydWUsXHJcbiAgICBjb250cm9sbGVyOiBmdW5jdGlvbigkc2NvcGUsICRlbGVtZW50LCAkdGltZW91dCkge1xyXG4gICAgICBsZXQgZGVmYXVsdFZhbCA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2RlZmF1bHRWYWx1ZScpWzBdLFxyXG4gICAgICAgICAgbGlzdE9mVmFsdWVzID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnbGlzdE9mVmFsdWVzJylbMF1cclxuXHJcbiAgICAgICR0aW1lb3V0KGZ1bmN0aW9uKCkge1xyXG4gICAgICAgIGRlZmF1bHRWYWwuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBmdW5jdGlvbihlKSB7XHJcbiAgICAgICAgICB0aGlzLnNob3cgPSB0cnVlXHJcblxyXG4gICAgICAgICAgZG9jdW1lbnQuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBoYW5kbGVyKVxyXG4gICAgICAgIH0uYmluZCgkc2NvcGUpKVxyXG5cclxuICAgICAgICBmdW5jdGlvbiBoYW5kbGVyKGUpIHtcclxuICAgICAgICAgIGlmKCAhKGUudGFyZ2V0ID09IGxpc3RPZlZhbHVlcyB8fFxyXG4gICAgICAgICAgICAgICAgZS50YXJnZXQucGFyZW50Tm9kZSA9PSBsaXN0T2ZWYWx1ZXMgfHxcclxuICAgICAgICAgICAgICAgIGUudGFyZ2V0ID09IGRlZmF1bHRWYWwgfHxcclxuICAgICAgICAgICAgICAgIGUudGFyZ2V0LnBhcmVudE5vZGUgPT0gZGVmYXVsdFZhbCkgKSB7XHJcbiAgICAgICAgICAgICRzY29wZS5zaG93ID0gZmFsc2VcclxuICAgICAgICAgICAgJHNjb3BlLiRhcHBseSgpXHJcbiAgICAgICAgICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcclxuICAgICAgICAgIH1cclxuICAgICAgICB9XHJcblxyXG4gICAgICAgIGZvcihsZXQgdD0wO3Q8bGlzdE9mVmFsdWVzLmNoaWxkcmVuLmxlbmd0aDsgdCsrKSB7XHJcblxyXG4gICAgICAgICAgbGlzdE9mVmFsdWVzLmNoaWxkcmVuW3RdLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgZnVuY3Rpb24oZSkge1xyXG4gICAgICAgICAgICBkb2N1bWVudC5yZW1vdmVFdmVudExpc3RlbmVyKCdjbGljaycsIGhhbmRsZXIpXHJcbiAgICAgICAgICAgIHRoaXMuc2hvdyA9IGZhbHNlXHJcbiAgICAgICAgICAgIHRoaXMubmdNb2RlbCA9IGUudGFyZ2V0LmlubmVySFRNTFxyXG4gICAgICAgICAgfS5iaW5kKCRzY29wZSkpXHJcbiAgICAgICAgfVxyXG4gICAgICB9LmJpbmQodGhpcyksIDEwMDApXHJcbiAgICB9LFxyXG4gICAgbGluazogZnVuY3Rpb24oc2NvcGUsIGVsZW1lbnQsIGF0dHJzKSB7XHJcbiAgICAgIHNjb3BlLml0ZW1zID0gSlNPTi5wYXJzZShhdHRycy5pdGVtcylcclxuICAgIH1cclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9zZWxlY3RCb3guanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XHJcblxyXG5sZXQgcHJpdmF0ZVNjb3BlID0ge31cclxuXHJcbm1vZHVsZS5leHBvcnRzLmluaXQgPSBmdW5jdGlvbigpIHtcclxuICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVycyA9IHt9XHJcbiAgcHJpdmF0ZVNjb3BlLmhhbmRsZXJJZCA9IDBcclxuXHJcbiAgcHJpdmF0ZVNjb3BlLmdldEhhbmRsZXJJZCA9IGZ1bmN0aW9uKCkge1xyXG4gICAgcmV0dXJuIHByaXZhdGVTY29wZS5oYW5kbGVySWQrK1xyXG4gIH1cclxuXHJcbiAgcHJpdmF0ZVNjb3BlLnJlZ2lzdGVySGFuZGxlciA9IGZ1bmN0aW9uKG5hbWUsIGhhbmRsZXIpIHtcclxuICAgIGlmKCFwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXSlcclxuICAgICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0gPSBbXVxyXG5cclxuICAgIGxldCBpZCA9IHByaXZhdGVTY29wZS5nZXRIYW5kbGVySWQoKVxyXG4gICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0ucHVzaCh7XHJcbiAgICAgIGlkIDogaWQsXHJcbiAgICAgIGhhbmRsZXIgOiBoYW5kbGVyXHJcbiAgICB9KVxyXG5cclxuICAgIHJldHVybiBpZFxyXG4gIH1cclxuXHJcbiAgcHJpdmF0ZVNjb3BlLmhhbmRsZSA9IGZ1bmN0aW9uKGV2ZW50TmFtZSwgZGF0YSkge1xyXG4gICAgaWYocHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbZXZlbnROYW1lXSlcclxuICAgICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbZXZlbnROYW1lXS5mb3JFYWNoKGggPT4gaC5oYW5kbGVyKGRhdGEpKVxyXG4gIH1cclxuXHJcbiAgcHJpdmF0ZVNjb3BlLnJlbW92ZUhhbmRsZXIgPSBmdW5jdGlvbihpZCkge1xyXG4gICAgZm9yKGxldCBrZXkgaW4gcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnMpIHtcclxuICAgICAgZm9yKGxldCB0ID0wOyB0PCBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldLmxlbmd0aDsgdCsrKSB7XHJcbiAgICAgICAgaWYocHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNba2V5XVt0XS5pZCA9PSBpZCkge1xyXG4gICAgICAgICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNba2V5XSA9IHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV0uZmlsdGVyKGVsID0+IGVsLmlkICE9PSBpZClcclxuICAgICAgICAgIHJldHVybiB0cnVlXHJcbiAgICAgICAgfVxyXG4gICAgICB9XHJcbiAgICB9XHJcblxyXG4gICAgcmV0dXJuIGZhbHNlXHJcbiAgfVxyXG5cclxuICBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlckJ5TmFtZSA9IGZ1bmN0aW9uKG5hbWUpIHtcclxuICAgIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW25hbWVdID0gW11cclxuICB9XHJcbn1cclxuXHJcblxyXG5tb2R1bGUuZXhwb3J0cy5vbiA9IGZ1bmN0aW9uKGV2ZW50TmFtZSwgaGFuZGxlcikge1xyXG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVnaXN0ZXJIYW5kbGVyKGV2ZW50TmFtZSwgaGFuZGxlcilcclxufVxyXG5cclxuLyogUmVtb3ZlcyBoYW5kbGVyIGJ5IGlkKi9cclxubW9kdWxlLmV4cG9ydHMub2ZmID0gZnVuY3Rpb24oaWQpIHtcclxuICByZXR1cm4gcHJpdmF0ZVNjb3BlLnJlbW92ZUhhbmRsZXIoaWQpXHJcbn1cclxuXHJcbi8qIFJlbW92ZXMgYWxsIGhhbmRsZXJzIGJ5IGV2ZW50IG5hbWUgKi9cclxubW9kdWxlLmV4cG9ydHMucmVtb3ZlID0gZnVuY3Rpb24obmFtZSkge1xyXG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlckJ5TmFtZShuYW1lKVxyXG59XHJcbi8qXHJcbiAge1xyXG4gICAgXCJuYW1lXCIgOiBcImZvcm0tc3VibWl0XCIsXHJcbiAgICBcImRhdGFcIiA6IFwid2hhdGV2ZXJcIiA8PT0gb3B0aW9uYWxcclxuICB9XHJcbiovXHJcbm1vZHVsZS5leHBvcnRzLmVtaXQgPSBmdW5jdGlvbihldmVudCkge1xyXG4gIGxldCBuYW1lID0gZXZlbnQubmFtZSB8fCAoKCkgPT4geyB0aHJvdyBuZXcgRXJyb3IoXCJObyBldmVudCBuYW1lXCIpIH0pKClcclxuICBsZXQgZGF0YSA9IGV2ZW50LmRhdGEgfHwgbnVsbFxyXG5cclxuICBwcml2YXRlU2NvcGUuaGFuZGxlKG5hbWUsIGRhdGEpXHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZXZlbnRzLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHtcclxuICBcIi9cIiA6IHtcclxuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2luZGV4Lmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2luZGV4JyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwiaW5kZXhcIlxyXG4gIH0sXHJcbiAgJy80MDMnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvZXJyb3I0MDMuaHRtbFwiXHJcbiAgfSxcclxuICAnLzQwNCcgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9lcnJvcjQwNC5odG1sXCJcclxuICB9LFxyXG4gICcvNTAwJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2Vycm9yNTAwLmh0bWxcIlxyXG4gIH0sXHJcbiAgJy9idWxsZXRpbkRldGFpbHMnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYnVsbGV0aW5EZXRhaWxzLmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2J1bGxldGluRGV0YWlscycpLFxyXG4gICAgY29udHJvbGxlckFzOiBcImJkZXRhaWxlZFwiXHJcbiAgfSxcclxuICAnL2J1bGxldGluQWRkJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2F1dGhlbnRpY2F0ZWQvYnVsbGV0aW5BZGQuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9idWxsZXRpbkFkZCcpLFxyXG4gICAgY29udHJvbGxlckFzOiBcImJhZGRcIlxyXG4gIH0sXHJcbiAgJy9sb2dpbicgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvbG9naW4uaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvbG9naW4nKSxcclxuICAgIGNvbnRyb2xsZXJBczogXCJsb2dpblwiXHJcbiAgfSxcclxuICAnL3JlZ2lzdGVyJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9yZWdpc3Rlci5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9yZWdpc3RlcicpLFxyXG4gICAgY29udHJvbGxlckFzOiBcInJlZ2lzdGVyXCJcclxuICB9LFxyXG4gICcvZWRpdFByb2ZpbGUnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL2F1dGhlbnRpY2F0ZWQvZWRpdC1wcm9maWxlLmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZWRpdFByb2ZpbGUnKSxcclxuICAgIGNvbnRyb2xsZXJBczogXCJwcm9maWxlXCJcclxuICB9LFxyXG4gICcvcHJvZmlsZScgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9wcm9maWxlLmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvcHJvZmlsZScpLFxyXG4gICAgY29udHJvbGxlckFzOiBcInByb2ZpbGVcIlxyXG4gIH0sXHJcbiAgJy9mYXZvdXJpdGVzJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcy5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMnKSxcclxuICAgIGNvbnRyb2xsZXJBczogXCJmYXZvdXJpdGVcIlxyXG4gIH0sXHJcbiAgJy9zZWFyY2hSZXN1bHRzJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL3NlYXJjaFJlc3VsdHMuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvc2VhcmNoUmVzdWx0cycpLFxyXG4gICAgY29udHJvbGxlckFzOiBcInNlYXJjaFJlc3VsdHNcIlxyXG4gIH1cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL3JvdXRlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XHJcbiAgXHJcblxyXG59XHJcblxyXG5cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvaW5kZXguanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgXHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvYnVsbGV0aW5EZXRhaWxzLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG5cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xyXG5cclxuICB0aGlzLmluaXQgPSAoKSA9PiB7XHJcbiAgICB0aGlzLmRiID0gJHNjb3BlLiRwYXJlbnQuZGJcclxuXHJcbiAgICB0aGlzLmVtYWlsID0gXCJcIlxyXG4gICAgdGhpcy5wYXNzd29yZCA9IFwiXCJcclxuXHJcbiAgICB0aGlzLmVtYWlsVmFsaWQgPSB0cnVlXHJcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSB0cnVlXHJcblxyXG4gICAgdGhpcy5sb2dpbkVycm9yID0gXCJcIlxyXG5cclxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcclxuICAgICAgaWYoZS53aGljaCA9PSAxMykgdGhpcy5zZW5kLmNhbGwodGhpcylcclxuICAgIH0uYmluZCh0aGlzKVxyXG5cclxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5kZWxldGVMaXN0bmVycyA9ICgpID0+IHtcclxuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xyXG4gICAgZWUuZW1pdCh7IG5hbWUgOiBcImZvcm0tc3VibWl0XCIgfSlcclxuICAgIC8qXHJcbiAgICAgIC0gR2V0IGRhdGFcclxuICAgICAgLSBWYWxpZGF0ZVxyXG4gICAgICAtIFNob3cgZXJyb3JzXHJcbiAgICAgIC0gb3JcclxuICAgICAgLSBHb3RvIGJkIGFuZCBzZW5kIGRhdGFcclxuICAgICovXHJcbiAgICAvLyB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuXHJcbiAgICBpZiggdGhpcy5lbWFpbFZhbGlkICYmIHRoaXMucGFzc3dvcmRWYWxpZCApIHtcclxuICAgICAgdGhpcy5kYi5sb2dpbih7XHJcbiAgICAgICAgXCJlbWFpbFwiIDogdGhpcy5lbWFpbCxcclxuICAgICAgICBcInBhc3N3b3JkXCI6IHRoaXMucGFzc3dvcmRcclxuICAgICAgfSwgKGVyciwgZGF0YSkgPT4ge1xyXG4gICAgICAgIGlmKGVycilcclxuICAgICAgICAgICRzY29wZS4kcGFyZW50LmRpc3BsYXlFcnJvcihcItCe0YjQuNCx0LrQsCDQsNCy0YLQvtGA0LjQt9Cw0YbQuNC4LCDQv9GA0L7QstC10YDRjNGC0LUg0LLQsNGI0Lgg0LTQsNC90L3Ri9C1XCIpXHJcbiAgICAgICAgZWxzZSB7XHJcbiAgICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuICAgICAgICAgIHRoaXMuZGIuc2F2ZVVzZXJEYXRhKGRhdGEpXHJcbiAgICAgICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnL3Byb2ZpbGUnKVxyXG4gICAgICAgIH1cclxuICAgICAgfSlcclxuICAgIH1cclxuICB9XHJcblxyXG4gIHRoaXMuZW1haWxJc1ZhbGlkID0gZW1haWwgPT4ge1xyXG4gICAgbGV0IGVycm9yID0gXCJcIlxyXG4gICAgaWYoIWVtYWlsLmxlbmd0aCkgIGVycm9yICs9IFwi0J7QsdGP0LfQsNGC0LXQu9GM0L3QvtC1INC/0L7Qu9C1LiBcIlxyXG4gICAgaWYoIS9eKChbXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKFxcLltePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSspKil8KFwiLitcIikpQCgoXFxbWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfV0pfCgoW2EtekEtWlxcLTAtOV0rXFwuKStbYS16QS1aXXsyLH0pKSQvLnRlc3QoZW1haWwpKVxyXG4gICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXHJcbiAgICByZXR1cm4gZXJyb3JcclxuICB9XHJcblxyXG4gIHRoaXMucGFzc3dvcmRJc1ZhbGlkID0gcHdkID0+IHtcclxuICAgIGxldCBlcnJvciA9IFwiXCJcclxuICAgIGlmKCFwd2QubGVuZ3RoKSBlcnJvciArPSBcItCe0LHRj9C30LDRgtC10LvRjNC90L7QtSDQv9C+0LvQtS4gXCJcclxuICAgIGlmKCBwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxyXG4gICAgcmV0dXJuIGVycm9yXHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2xvZ2luLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSwgJHEpIHtcclxuXHJcbiAgdGhpcy5pbml0ID0gKCkgPT4ge1xyXG4gICAgdGhpcy5kYiA9ICRzY29wZS4kcGFyZW50LmRiXHJcblxyXG4gICAgdGhpcy5lbWFpbCA9IFwiXCJcclxuICAgIHRoaXMucGFzc3dvcmQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkMiA9IFwiXCJcclxuXHJcbiAgICB0aGlzLmVtYWlsVmFsaWQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkMlZhbGlkID0gXCJcIlxyXG5cclxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcclxuICAgICAgaWYoZS53aGljaCA9PSAxMykgdGhpcy5zZW5kLmNhbGwodGhpcylcclxuICAgIH0uYmluZCh0aGlzKVxyXG5cclxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5kZWxldGVMaXN0bmVycyA9ICgpID0+IHtcclxuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xyXG4gICAgLypcclxuICAgICAgLSBHZXQgZGF0YVxyXG4gICAgICAtIFZhbGlkYXRlXHJcbiAgICAgIC0gU2hvdyBlcnJvcnNcclxuICAgICAgLSBvclxyXG4gICAgICAtIEdvdG8gYmQgYW5kIHNlbmQgZGF0YVxyXG4gICAgKi9cclxuICAgIGlmKCB0aGlzLmVtYWlsVmFsaWQgJiYgdGhpcy5wYXNzd29yZFZhbGlkICYmIHRoaXMucGFzc3dvcmQyVmFsaWQgKSB7XHJcbiAgICAgIHRoaXMuZGIubG9naW4oe1xyXG4gICAgICAgIFwiZW1haWxcIiA6IHRoaXMuZW1haWwsXHJcbiAgICAgICAgXCJwYXNzd29yZFwiOiB0aGlzLnBhc3N3b3JkXHJcbiAgICAgIH0sIChlcnIsIGRhdGEpID0+IHtcclxuICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuICAgICAgICBpZihlcnIpXHJcbiAgICAgICAgICAkc2NvcGUuJHBhcmVudC5yZWRpcmVjdFRvVXJsKCcvNTAwJylcclxuICAgICAgICBlbHNlIHtcclxuICAgICAgICAgIC8qIFNhdmUgZGF0YSB0byBkYiAqL1xyXG4gICAgICAgICAgJHNjb3BlLnJlZGlyZWN0VG9VcmwoJy9wcm9maWxlJylcclxuICAgICAgICAgIGNvbnNvbGUubG9nKGRhdGEpXHJcbiAgICAgICAgfVxyXG4gICAgICB9KVxyXG4gICAgfVxyXG4gIH1cclxuXHJcbiAgdGhpcy5lbWFpbElzVmFsaWQgPSBmdW5jdGlvbihlbWFpbCkge1xyXG4gICAgcmV0dXJuICRxKCBmdW5jdGlvbihyZXNvbHZlLCByZWplY3QpIHtcclxuICAgICAgbGV0IGVycm9yID0gXCJcIlxyXG4gICAgICBpZighZW1haWwubGVuZ3RoKSAgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXHJcbiAgICAgIGlmKCEvXigoW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKyhcXC5bXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKSopfChcIi4rXCIpKUAoKFxcW1swLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31dKXwoKFthLXpBLVpcXC0wLTldK1xcLikrW2EtekEtWl17Mix9KSkkLy50ZXN0KGVtYWlsKSlcclxuICAgICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXHJcblxyXG4gICAgICB3aW5kb3cuZGIuY2hlY2tFbWFpbChlbWFpbCwgZnVuY3Rpb24oZXJyLCBkYXRhKSB7XHJcbiAgICAgICAgaWYoZXJyKSByZWplY3QoZXJyKVxyXG4gICAgICAgIGVsc2Uge1xyXG4gICAgICAgICAgY29uc29sZS5sb2coZGF0YSlcclxuICAgICAgICAgIGlmKGRhdGEgIT09IFwiZmFsc2VcIilcclxuICAgICAgICAgICAgZXJyb3IgKz0gXCLQotCw0LrQsNGPINC/0L7Rh9GC0LAg0YPQttC1INC40YHQv9C+0LvRjNC30YPQtdGC0YHRjy4gXCJcclxuICAgICAgICAgIHJlc29sdmUoZXJyb3IpXHJcbiAgICAgICAgfVxyXG4gICAgICB9LmJpbmQodGhpcykpXHJcbiAgICB9LmJpbmQodGhpcykpXHJcbiAgfVxyXG5cclxuICB0aGlzLnBhc3N3b3JkSXNWYWxpZCA9IHB3ZCA9PiB7XHJcbiAgICBsZXQgZXJyb3IgPSBcIlwiXHJcbiAgICBpZighcHdkLmxlbmd0aCkgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXHJcbiAgICBpZihwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxyXG4gICAgcmV0dXJuIGVycm9yXHJcbiAgfVxyXG5cclxuICB0aGlzLnBhc3N3b3JkMklzVmFsaWQgPSBwd2QgPT4ge1xyXG4gICAgbGV0IGVycm9yID0gdGhpcy5wYXNzd29yZElzVmFsaWQocHdkKVxyXG4gICAgaWYodGhpcy5wYXNzd29yZCAhPT0gdGhpcy5wYXNzd29yZDIgKSBlcnJvciArPSBcItCf0LDRgNC+0LvQuCDQvdC1INGB0L7QstC/0LDQtNCw0Y7RglwiXHJcbiAgICByZXR1cm4gZXJyb3JcclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvcmVnaXN0ZXIuanNcbiAqKi8iLCJcInVzZSBzdHJpY3RcIjtcclxuXHJcbmNsYXNzIFByb2ZpbGVDb250YWN0IHtcclxuICAgIGNvbnN0cnVjdG9yKCkge1xyXG4gICAgICAgIHRoaXMuY29udGFjdEVtYWlscyA9IFsnJ107XHJcbiAgICAgICAgdGhpcy5jb250YWN0UGhvbmVzID0gWycnXTtcclxuICAgICAgICB0aGlzLnR5cGUgPVwiRU5UUkVQUkVORVVSXCJcclxuXHJcbiAgICAgICAgdGhpcy5wb3NpdGlvbiA9IFwiXCJcclxuICAgICAgICB0aGlzLmNvbXBhbnlOYW1lID0gXCJcIlxyXG4gICAgICAgIHRoaXMuc2t5cGVVc2VyTmFtZSA9IFwiXCJcclxuICAgICAgICB0aGlzLmxpbmtUb1dlYlNpdGUgPSBcIlwiXHJcbiAgICB9XHJcbn1cclxuXHJcbmNsYXNzIHByb2ZpbGVDdHJsIHtcclxuICAgIGNvbnN0cnVjdG9yKCRzY29wZSl7XHJcbi8vICAgICAgICBpZighJHNjb3BlLiRwYXJlbnQuZGIudXNlcilcclxuLy8gICAgICAgICAgJHNjb3BlLiRwYXJlbnQucmVkaXJlY3RUb1VybCgnLzQwMycsIHRydWUpXHJcbi8vICAgICAgICBlbHNlXHJcblx0XHRcdHRoaXMuY29udGFjdCA9IG5ldyBQcm9maWxlQ29udGFjdCgpO1xyXG4gICAgICB0aGlzLmNvbnRhY3RUeXBlcyA9IFtcclxuICAgICAgICBcIkxFR0FMX0VOVElUWVwiLFxyXG4gICAgICAgIFwiRU5UUkVQUkVORVVSXCJcclxuICAgICAgXVxyXG5cclxuICAgICAgdGhpcy5lbWFpbCA9IFwiXCJcclxuICAgICAgdGhpcy5maW8gPSBcIlwiXHJcbiAgICAgIHRoaXMubWFpblBob25lTnVtYmVyID0gXCJcIlxyXG4gICAgfVxyXG4gICAgdXBkYXRlUHJvZmlsZSgpe1xyXG5cclxuICAgIH1cclxuICAgIGFkZENvbnRhY3RzKCRldmVudCwgdHlwZSl7XHJcbiAgICAgICAgdmFyIGFycjtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG5cclxuICAgICAgICBpZihhcnIubGVuZ3RoIDwgNSAmJiBhcnJbYXJyLmxlbmd0aCAtIDFdLnRyaW0oKSkgYXJyLnB1c2goJycpO1xyXG4gICAgfVxyXG5cclxuICAgIGRlbGV0ZUNvbnRhY3RzKCRldmVudCwgJGluZGV4LCB0eXBlKXtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscy5zcGxpY2UoJGluZGV4LCAxKTtcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG4gICAgfVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHByb2ZpbGVDdHJsO1xyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2VkaXRQcm9maWxlLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCI7XHJcblxyXG5jbGFzcyBQcm9maWxlQ29udGFjdHtcclxuICAgIGNvbnN0cnVjdG9yKCkge1xyXG4gICAgICAgIHRoaXMuY29udGFjdEVtYWlscyA9IFsnJ107XHJcbiAgICAgICAgdGhpcy5jb250YWN0UGhvbmVzID0gWycnXTtcclxuICAgIH1cclxufVxyXG5cclxuY2xhc3MgcHJvZmlsZUN0cmwge1xyXG4gICAgY29uc3RydWN0b3IoJHNjb3BlKXtcclxuLy8gICAgICAgIGlmKCEkc2NvcGUuJHBhcmVudC5kYi51c2VyKVxyXG4vLyAgICAgICAgICAkc2NvcGUuJHBhcmVudC5yZWRpcmVjdFRvVXJsKCcvNDAzJywgdHJ1ZSlcclxuLy8gICAgICAgIGVsc2VcclxuICAgICAgICAgIHRoaXMuY29udGFjdCA9IG5ldyBQcm9maWxlQ29udGFjdCgpO1xyXG4gICAgfVxyXG4gICAgdXBkYXRlUHJvZmlsZSgpe1xyXG5cclxuICAgIH1cclxuICAgIGFkZENvbnRhY3RzKCRldmVudCwgdHlwZSl7XHJcbiAgICAgICAgdmFyIGFycjtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG5cclxuICAgICAgICBpZihhcnIubGVuZ3RoIDwgNSAmJiBhcnJbYXJyLmxlbmd0aCAtIDFdLnRyaW0oKSkgYXJyLnB1c2goJycpO1xyXG4gICAgfVxyXG5cclxuICAgIGRlbGV0ZUNvbnRhY3RzKCRldmVudCwgJGluZGV4LCB0eXBlKXtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscy5zcGxpY2UoJGluZGV4LCAxKTtcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG4gICAgfVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHByb2ZpbGVDdHJsO1xyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XHJcblxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcy5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcclxuICBcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9zZWFyY2hSZXN1bHRzLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcclxuXHJcbi8qINCa0L7QvdGC0YDQvtC70LvQtdGAINC00LvRjyDRg9C/0YDQsNCy0LvQtdC90LjRjyAg0L7RgdC90L7QstC90YvQvCDRgdC60LXQu9C10YLQvtC8INC00L7QutGD0LzQtdC90YLQsCAqL1xyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRodHRwLCAkc2NvcGUsICRsb2NhdGlvbiwgJHRpbWVvdXQpIHtcclxuICBjb25zb2xlLmxvZygnTWFpbiBjb250cm9sbGVyIGxvYWRlZCcpXHJcblxyXG4gIC8qIFN0YW5kYWxvbmUgbW9kdWxlIGZvciBiZCAqL1xyXG4gICRzY29wZS5kYiA9IHJlcXVpcmUoJy4uL21vZHVsZXMvZGInKVxyXG4gICRzY29wZS5kYi5pbml0KClcclxuICB3aW5kb3cuZGIgPSAkc2NvcGUuZGJcclxuXHJcbiAgLyogSW5pdGlhbGl6ZSBkYXRhICovXHJcbiAgdGhpcy5pbml0ID0gZnVuY3Rpb24oKSB7XHJcbiAgICAvKiB2YXJpYWJsZXMgZm9yIHRlc3RpbmcgKi9cclxuICAgIHRoaXMuaGVsbG89XCJoaVwiXHJcbiAgICB0aGlzLmJvb2xlYW4gPSB0cnVlXHJcbiAgICB0aGlzLmxpc3QgPSBbMSwyLDNdXHJcblxyXG4gICAgY29uc29sZS5sb2coXCJNYWluIGNvbnRyb2xsZXIgaW5pdFwiKVxyXG5cclxuICAgIHRoaXMuc29ydGluZ0NhdGVnb3JpZXMgPSAocmVxdWlyZSgnLi4vZGF0YS9zb3J0aW5nJykpLml0ZW1zXHJcbiAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IFwiTm9uZVwiXHJcbiAgICB0aGlzLnNvcnRpbmdJZCA9IDBcclxuXHJcbiAgICBpZih0aGlzLnNvcnRpbmdDYXRlZ29yaWVzLmxlbmd0aCkge1xyXG4gICAgICBsZXQgdGl0bGUgPSB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzW3RoaXMuc29ydGluZ0lkXS50aXRsZVxyXG4gICAgICBsZXQgYXJyID0gdGl0bGUuc3BsaXQoXCJcIilcclxuICAgICAgdGhpcy5hcnJvdyA9IGFyci5wb3AoKVxyXG4gICAgICBhcnIucG9wKClcclxuXHJcbiAgICAgIHRoaXMuY3VycmVudENhdGVnb3J5ID0gYXJyLmpvaW4oXCJcIilcclxuICAgIH1cclxuICAgIGVsc2UgY29uc29sZS5lcnJvcihuZXcgRXJyb3IoXCJObyBzb3J0aW5nIG9wdGlvbnMgZm91bmRcIikpXHJcblxyXG4gICAgdGhpcy5zaG93aW5nQ2F0ZWdvcmllcyA9IGZhbHNlXHJcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXHJcbiAgfVxyXG5cclxuICB0aGlzLnNob3dDYXRlZ29yaWVzID0gKCkgPT4ge1xyXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gdHJ1ZVxyXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gdHJ1ZVxyXG4gICAgJHRpbWVvdXQoICgpID0+IHtcclxuICAgICAgdGhpcy5zZXR0aW5nQ2F0ID0gZmFsc2VcclxuICAgIH0sIDI1MClcclxuXHJcbiAgICB0aGlzLnNob3dpbmdDYXRlZ29yaWVzID0gdHJ1ZVxyXG4gIH1cclxuXHJcbiAgLyogU29ydGluZyBpbiBoZWFkZXIgKi9cclxuICB0aGlzLnNldENhdGVnb3J5ID0gaWQgPT4ge1xyXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gZmFsc2VcclxuXHJcbiAgICBsZXQgcmVzID0gdGhpcy5zb3J0aW5nQ2F0ZWdvcmllcy5maWx0ZXIoZWwgPT4gZWwuaWQgPT09IGlkIHwgMClbMF1cclxuICAgIHRoaXMuc29ydGluZ0lkID0gaWRcclxuXHJcbiAgICBpZihyZXMpIHtcclxuICAgICAgbGV0IGFyciA9IHJlcy50aXRsZS5zcGxpdChcIlwiKVxyXG4gICAgICB0aGlzLmFycm93ID0gYXJyLnBvcCgpXHJcbiAgICAgIGFyci5wb3AoKVxyXG5cclxuICAgICAgdGhpcy5jdXJyZW50Q2F0ZWdvcnkgPSBhcnIuam9pbihcIlwiKVxyXG4gICAgfVxyXG5cclxuICB9XHJcblxyXG4gIHRoaXMubG9nb3V0ID0gZnVuY3Rpb24oKSB7XHJcbiAgICAgIGRiLnVzZXJMb2dvdXQoKVxyXG4gICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnLycpXHJcbiAgfVxyXG5cclxuICAvKiBXYXRjaCBhbGwgY2xpY2sgb24gdGhlIGJvZHkgKi9cclxuICB0aGlzLmNsaWNrID0gKCkgPT4ge1xyXG4gICAgaWYodGhpcy5zaG93aW5nQ2F0ZWdvcmllcyAmJiAhdGhpcy5zZXR0aW5nQ2F0KVxyXG4gICAgICB0aGlzLnNob3dpbmdDYXRlZ29yaWVzID0gZmFsc2VcclxuICB9XHJcblxyXG5cclxuICAvKiBDb3JyZWN0IHJlZGlyZWN0IHRvIHVybCB0aHJvdWdoIGFwcCByb3V0ZXIqL1xyXG4gICRzY29wZS5yZWRpcmVjdFRvVXJsID0gKHVybCwgaW1tZWRpYXRlKSA9PiB7XHJcbiAgICBpZihpbW1lZGlhdGUpXHJcbiAgICAgICRsb2NhdGlvbi5wYXRoKHVybClcclxuICAgIGVsc2VcclxuICAgICAgJHRpbWVvdXQoKCkgPT4ge1xyXG4gICAgICAgICRsb2NhdGlvbi5wYXRoKHVybClcclxuICAgICAgfSwgMjUwKVxyXG4gIH1cclxuXHJcbiAgLyogVXNlIHRoaXMgbWV0aG9kIGZvciBnbG9iYWwgcHVycG9zZSBlcnJvcnMgKi9cclxuICAkc2NvcGUuZGlzcGxheUVycm9yID0gdGV4dCA9PiB7XHJcbiAgICBhbGVydCh0ZXh0KVxyXG4gICAgY29uc29sZS5lcnJvcihuZXcgRXJyb3IoXCJ0ZXh0XCIpKVxyXG4gIH1cclxuXHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvbWFpbi5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubGV0IHV0aWxzID0gcmVxdWlyZSgnLi91dGlscycpLFxyXG4gICAgY29uZmlnID0gcmVxdWlyZSgnLi4vZGF0YS9jb25maWcnKVxyXG5cclxubGV0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cclxuXHJcbm1vZHVsZS5leHBvcnRzLmluaXQgPSBmdW5jdGlvbigpIHtcclxuICAvKiBpbml0IGRhdGEgZnJvbSBkYXRhYmFzZSBoZXJlICovXHJcbiAgY3R4LnNldERlZmF1bHRzKClcclxuICAvLyB0aGlzLnRyYW5zcG9ydCA9ICRodHRwXHJcbiAgLy8gY29uc29sZS5sb2codGhpcy50cmFuc3BvcnQpXHJcbiAgY3R4LmNoZWNrVXNlcklzTG9nZ2VkKGZ1bmN0aW9uKGVyciwgZGF0YSkge1xyXG4gICAgaWYoZXJyKSBjb25zb2xlLmVycm9yKGVycilcclxuICAgIGVsc2Uge1xyXG4gICAgICBpZihkYXRhKSBjdHguc2F2ZVVzZXJEYXRhKGRhdGEpXHJcbiAgICAgIGVsc2UgY29uc29sZS5sb2coXCJVc2VyIGlzIG5vdCBsb2dnZWQgaW5cIilcclxuICAgIH1cclxuICB9LmJpbmQodGhpcykpXHJcblxyXG4gIGNvbnNvbGUubG9nKFwiRGF0YWJhc2UgaW5pdGlhbGl6ZWRcIilcclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMuc2V0RGVmYXVsdHMgPSBmdW5jdGlvbigpIHtcclxuICBjb25zb2xlLmxvZyhcIkRhdGFiYXNlIDo6IGRlZmF1bHRzIHNldFwiKVxyXG5cclxuICBjdHguZmF2b3VyaXRlcyA9IG51bGxcclxuICBjdHgubWFpbGJveCA9IG51bGxcclxuICBjdHgudXNlciA9IG51bGxcclxuICBjdHgubm90aWZpY2F0aW9ucyA9IHsgaGVsbG8gOiBcInByZXZlZFwiIH1cclxufVxyXG5cclxuXHJcbm1vZHVsZS5leHBvcnRzLmNoZWNrRW1haWwgPSBmdW5jdGlvbihlbWFpbCwgY2IpIHtcclxuICB1dGlscy5yZXF1ZXN0KHtcclxuICAgIFwibWV0aG9kXCIgOiBjb25maWcucm91dGVzLmNoZWNrRW1haWwubWV0aG9kLFxyXG4gICAgXCJ1cmxcIiA6IGNvbmZpZy5hcGkuYXV0aCArIGNvbmZpZy5yb3V0ZXMuY2hlY2tFbWFpbC51cmwsXHJcbiAgICBcImRhdGFcIiA6IGVtYWlsLFxyXG4gICAgXCJoZWFkZXJzXCIgOiB7XHJcbiAgICAgIFwiQ29udGVudC1UeXBlXCIgOiBcInRleHQvcGxhaW5cIlxyXG4gICAgfVxyXG4gIH0pLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhKSwgZXJyID0+IGNiKGVycikpXHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzLmxvZ2luID0gZnVuY3Rpb24oIGRhdGEsIGNiICkge1xyXG4gIHV0aWxzLnJlcXVlc3Qoe1xyXG4gICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMubG9naW4ubWV0aG9kLFxyXG4gICAgXCJ1cmxcIiA6IGNvbmZpZy5hcGkuYXV0aCArIGNvbmZpZy5yb3V0ZXMubG9naW4udXJsLFxyXG4gICAgXCJkYXRhXCIgOiBkYXRhLFxyXG4gICAgXCJoZWFkZXJzXCIgOiB7XHJcbiAgICAgIFwiQ29udGVudC1UeXBlXCIgOiBcImFwcGxpY2F0aW9uL2pzb25cIixcclxuICAgICAgXCJ3aXRoQ3JlZGVudGlhbHNcIiA6IFwidHJ1ZVwiXHJcbiAgICB9XHJcbiAgfSkudGhlbihkYXRhID0+IGNiKG51bGwsIGRhdGEpLCBlcnIgPT4gY2IoZXJyKSlcclxuICAvLyBjdHgudHJhbnNwb3J0KHtcclxuICAvLyAgIG1ldGhvZCA6IGNvbmZpZy5yb3V0ZXMubG9naW4ubWV0aG9kLFxyXG4gIC8vICAgdXJsIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5sb2dpbi51cmwsXHJcbiAgLy8gICBkYXRhIDogZGF0YSxcclxuICAvLyAgIGhlYWRlcnMgOiB7XHJcbiAgLy8gICAgIFwiQ29udGVudC1UeXBlXCIgOiBcImFwcGxpY2F0aW9uL2pzb25cIixcclxuICAvLyAgIH0sXHJcbiAgLy8gICB3aXRoQ3JlZGVudGlhbHMgOiB0cnVlXHJcbiAgLy8gfSlcclxuICAvLyAudGhlbihkYXRhID0+IGNiKG51bGwsIGRhdGEpKVxyXG4gIC8vIC5jYXRjaChjYilcclxufVxyXG5cclxuLyogVGhpcyBtZXRob2QgZG9lcyBzYXZlcyB1c2VyIGRhdGEgaW4gdGhpcyBtb2R1bGUgb25seSwgbm8gYmFja2VuZCBjb21tdW5pY2F0aW9uICovXHJcbm1vZHVsZS5leHBvcnRzLnNhdmVVc2VyRGF0YSA9IGZ1bmN0aW9uKGRhdGEpIHtcclxuICBkYXRhID0gZGF0YS5sZW5ndGggPyBKU09OLnBhcnNlKGRhdGEpIDogXCJcIlxyXG4gIHRoaXMudXNlciA9IHt9XHJcbiAgLyogVE9ETzog0YDQsNGB0L/QsNGA0YHQuNGC0Ywg0LTQsNC90L3Ri9C1INCyINC+0YHQvNGL0YHQu9C10L3QvdGL0LUg0L/QtdGA0LXQvNC10L3QvdGL0LUgKi9cclxuXHJcbiAgY29uc29sZS5sb2coXCJEYXRhYmFzZTo6IFVzZXIgZGF0YSBzYXZlZCBzdWNjZXNzZnVsbHkoINGI0YPRgtC60LAgKSBcIilcclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMuY2hlY2tVc2VySXNMb2dnZWQgPSBmdW5jdGlvbiggY2IgKSB7XHJcbiAgdXRpbHMucmVxdWVzdCh7XHJcbiAgICBcIm1ldGhvZFwiIDogY29uZmlnLnJvdXRlcy5jaGVja0xvZ2dlZC5tZXRob2QsXHJcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS51cmwgKyBjb25maWcucm91dGVzLmNoZWNrTG9nZ2VkLnVybFxyXG4gIH0pLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhKSwgZXJyID0+IGNiKGVycikpXHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzLnVzZXJMb2dvdXQgPSBmdW5jdGlvbigpIHtcclxuICBjdHguc2V0RGVmYXVsdHMoKVxyXG5cclxuICB1dGlscy5yZXF1ZXN0KHtcclxuICAgIFwibWV0aG9kXCIgOiBjb25maWcucm91dGVzLmxvZ291dC5tZXRob2QsXHJcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5sb2dvdXQudXJsXHJcbiAgfSkudGhlbigoKT0+e30sICgpPT57fSlcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2RiLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcbi8qXHJcbiAgRXhwZWN0IG9wdGlvbnMgb2JqZWN0IGxpa2UgdGhpczpcclxuICB7XHJcbiAgICBcIm1ldGhvZFwiIDogXCJQT1NUXCIsXHJcbiAgICBcInVybFwiIDogXCJodHRwOi8vc29tZXVybC5jb20vXCIsXHJcbiAgICBcImRhdGFcIiA6IFwiZGF0YVwiLFxyXG4gICAgXCJoZWFkZXJzXCIgOiB7XHJcbiAgICAgIFwiQ29udGVudC1UeXBlXCIgOiBcImFwcGxpY2F0aW9uL2pzb25cIixcclxuICAgICAgXCJDb250ZW50LUxlbmd0aFwiIDogXCIxMDIzXCJcclxuICAgIH1cclxuICB9XHJcblxyXG4gIERFRkFVTFRTOlxyXG4gIE1ldGhvZCAtIGRlZmF1bHQgaXMgR0VULFxyXG4gIFVSTCAtIHJlcXVpcmVkLFxyXG4gIGRhdGEgLSBvcHRpb25hbCxcclxuICBoZWFkZXJzIC0gb3B0aW9uYWxcclxuKi9cclxuXHJcbm1vZHVsZS5leHBvcnRzLnJlcXVlc3QgPSBmdW5jdGlvbihvcHRpb25zKSB7XHJcbiAgcmV0dXJuIG5ldyBQcm9taXNlKCAocmVzb2x2ZSwgcmVqZWN0KSA9PiB7XHJcbiAgICAvKiBTZXR0aW5nIGRlZmF1bHRzICovXHJcbiAgICBsZXQgeyBtZXRob2Q9XCJHRVRcIiwgdXJsLCBkYXRhLCBoZWFkZXJzIH0gPSBvcHRpb25zXHJcblxyXG4gICAgLyogU29tZSB2YWxpZGF0aW9uICovXHJcbiAgICBpZighdXJsKVxyXG4gICAgICByZXR1cm4gY29uc29sZS5lcnJvcihcIlVybCBub3Qgc3BlY2lmaWVkXCIpXHJcblxyXG4gICAgaWYoIChtZXRob2QgPT0gXCJQT1NUXCIgfHwgbWV0aG9kID09IFwiUFVUXCIpICYmICFkYXRhKVxyXG4gICAgICByZXR1cm4gY29uc29sZS5lcnJvcihcIk5vdGhpbmcgdG8gc2VuZCBoZXJlID0pXCIpXHJcblxyXG4gICAgLyogU3RhcnQgY29uc3RydWN0aW5nIHJlcXVlc3QgKi9cclxuICAgIHZhciB4aHIgPSBuZXcgWE1MSHR0cFJlcXVlc3QoKVxyXG4gICAgeGhyLm9wZW4obWV0aG9kLCB1cmwsIHRydWUpXHJcblxyXG4gICAgaWYoaGVhZGVycylcclxuICAgICAgZm9yKCB2YXIgcHJvcCBpbiBoZWFkZXJzKVxyXG4gICAgICAgIHhoci5zZXRSZXF1ZXN0SGVhZGVyKHByb3AsIGhlYWRlcnNbcHJvcF0pXHJcblxyXG4gICAgaWYoZGF0YSAmJiBoZWFkZXJzWydDb250ZW50LVR5cGUnXSAhPT0gXCJ0ZXh0L3BsYWluXCIpXHJcbiAgICAgIHhoci5zZW5kKEpTT04uc3RyaW5naWZ5KGRhdGEpKVxyXG4gICAgZWxzZSBpZihkYXRhKVxyXG4gICAgICB4aHIuc2VuZChkYXRhKVxyXG4gICAgZWxzZVxyXG4gICAgICB4aHIuc2VuZCgpXHJcblxyXG5cclxuXHJcbiAgICB4aHIub25yZWFkeXN0YXRlY2hhbmdlID0gZnVuY3Rpb24oKSB7XHJcbiAgICAgIGlmICh0aGlzLnJlYWR5U3RhdGUgIT0gNClcclxuICAgICAgICByZXR1cm5cclxuXHJcbiAgICAgIGlmICh0aGlzLnN0YXR1cyAhPSAyMDApXHJcbiAgICAgICAgcmV0dXJuIHJlamVjdCgnRXJyb3I6ICcgKyAodGhpcy5zdGF0dXMgPyBgKCR7dGhpcy5zdGF0dXN9KSAke3RoaXMuc3RhdHVzVGV4dH1gOiAncmVxdWVzdCBmYWlsJykpXHJcbiAgICAgIGVsc2VcclxuICAgICAgICByZXR1cm4gcmVzb2x2ZSh0aGlzLnJlc3BvbnNlVGV4dClcclxuXHJcbiAgICB9XHJcblxyXG4gIH0pXHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy91dGlscy5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcImFwaVwiOiB7XG5cdFx0XCJ1cmxcIjogXCJodHRwOi8vOTMuNzMuMTA5LjM4OjgwODQvXCIsXG5cdFx0XCJhdXRoXCI6IFwiaHR0cDovLzkzLjczLjEwOS4zODo4MDgzL1wiXG5cdH0sXG5cdFwicm91dGVzXCI6IHtcblx0XHRcImdldEJ1bGxldGluc1wiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIlBPU1RcIixcblx0XHRcdFwidXJsXCI6IFwiYXBpL3Jlc3Qvb2ZmZXJzU2VydmljZS9vZmZlci9yZWFkL2FsbFwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcInNraXBcIjogMCxcblx0XHRcdFx0XCJsaW1pdFwiOiAyMFxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJsb2dpblwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIlBPU1RcIixcblx0XHRcdFwidXJsXCI6IFwibG9naW5cIixcblx0XHRcdFwiYWNjZXB0XCI6IHtcblx0XHRcdFx0XCJlbWFpbFwiOiBcInNzczJAZ21haWwuY29tXCIsXG5cdFx0XHRcdFwicGFzc3dvcmRcIjogXCIxMjM0NTZcIlxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJsb2dvdXRcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImxvZ291dFwiXG5cdFx0fSxcblx0XHRcInJlZ2lzdGVyXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJyZWdpc3RlclwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIixcblx0XHRcdFx0XCJwYXNzd29yZFwiOiBcIjEyMzQ1NlwiXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImNoZWNrRW1haWxcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcImxvZ2luL2NoZWNrRW1haWxcIixcblx0XHRcdFwiYWNjZXB0XCI6IHtcblx0XHRcdFx0XCJlbWFpbFwiOiBcInNzczJAZ21haWwuY29tXCJcblx0XHRcdH1cblx0XHR9LFxuXHRcdFwiY2hlY2tMb2dnZWRcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJHRVRcIixcblx0XHRcdFwidXJsXCI6IFwiYXBpL3Jlc3QvcHJvZmlsZXNTZXJ2aWNlL3Byb2ZpbGUvcmVhZC9sb2dnZWRJblByb2ZpbGVcIlxuXHRcdH1cblx0fVxufTtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vZGF0YS9jb25maWcuanNvblxuICoqIG1vZHVsZSBpZCA9IDc0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IHtcblx0XCJpdGVtc1wiOiBbXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiAxLFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGG0LXQvdCwIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiAyLFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGG0LXQvdCwIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiAzLFxuXHRcdFx0XCJ0aXRsZVwiOiBcItC00LDRgtCwICDilr5cIixcblx0XHRcdFwicmVxdWVzdENyaXRlcmlhXCI6IFwiXCJcblx0XHR9LFxuXHRcdHtcblx0XHRcdFwiaWRcIjogNCxcblx0XHRcdFwidGl0bGVcIjogXCLQtNCw0YLQsCDilrRcIixcblx0XHRcdFwicmVxdWVzdENyaXRlcmlhXCI6IFwiXCJcblx0XHR9LFxuXHRcdHtcblx0XHRcdFwiaWRcIjogNSxcblx0XHRcdFwidGl0bGVcIjogXCLRgNC10LnRgtC40L3QsyDilr5cIixcblx0XHRcdFwicmVxdWVzdENyaXRlcmlhXCI6IFwiXCJcblx0XHR9LFxuXHRcdHtcblx0XHRcdFwiaWRcIjogNixcblx0XHRcdFwidGl0bGVcIjogXCLRgNC10LnRgtC40L3QsyDilrRcIixcblx0XHRcdFwicmVxdWVzdENyaXRlcmlhXCI6IFwiXCJcblx0XHR9XG5cdF1cbn07XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2RhdGEvc29ydGluZy5qc29uXG4gKiogbW9kdWxlIGlkID0gNzVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgTUFYX1NZTUJPTFMgPSAxMDAwXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIHJldHVybiB7XHJcbiAgICByZXN0cmljdDogXCJFXCIsXHJcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxyXG4gICAgc2NvcGUgOiB7XHJcbiAgICAgIGxhYmVsOiBcIkBcIixcclxuICAgICAgbmdNb2RlbDogXCI9XCJcclxuICAgIH0sXHJcbiAgICByZXBsYWNlOiB0cnVlLFxyXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiaW5wdXRGb3JtXCI+XHJcbiAgICAgICAgICAgICAgICAgPGxhYmVsPnt7IGxhYmVsIH19PC9sYWJlbD5cclxuICAgICAgICAgICAgICAgICA8dGV4dGFyZWEgbmctbW9kZWw9XCJuZ01vZGVsXCIgbWF4bGVuZ3RoPSR7TUFYX1NZTUJPTFN9PjwvdGV4dGFyZWE+XHJcbiAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cInN5bWJvbHNcIj48L2Rpdj5cclxuICAgICAgICAgICAgICAgPC9kaXY+YCxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQsICR0aW1lb3V0KSB7XHJcbiAgICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgY291bnQpXHJcblxyXG4gICAgICAkc2NvcGUuJG9uKFwiJGRlc3Ryb3lcIiwgZnVuY3Rpb24oKSB7XHJcbiAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcigna2V5dXAnLCBjb3VudClcclxuICAgICAgfS5iaW5kKHRoaXMpKVxyXG5cclxuICAgICAgbGV0IGRlZmF1bHRCb3JkZXIgPSBcIlwiXHJcblxyXG4gICAgICBsZXQgZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgndGV4dGFyZWEnKVswXSxcclxuICAgICAgICAgIGxhYmVsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeVRhZ05hbWUoJ2xhYmVsJylbMF0sXHJcbiAgICAgICAgICBzeW1ib2xzID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnc3ltYm9scycpWzBdXHJcblxyXG5cclxuICAgICAgZnVuY3Rpb24gY291bnQoKSB7XHJcbiAgICAgICAgc3ltYm9scy5pbm5lckhUTUwgPSBgJHskc2NvcGUubmdNb2RlbC5sZW5ndGh9LyR7TUFYX1NZTUJPTFN9YFxyXG4gICAgICB9XHJcblxyXG4gICAgICBmdW5jdGlvbiBvbkJsdXIoZSkge1xyXG4gICAgICAgIGlmKCAhJHNjb3BlLm5nTW9kZWwubGVuZ3RoKVxyXG4gICAgICAgICAgaGlkZUFuaW1hdGlvbigpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIG9uRm9jdXMoZSkge1xyXG4gICAgICAgIGlmKCEkc2NvcGUubmdNb2RlbC5sZW5ndGgpXHJcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcclxuICAgICAgfVxyXG5cclxuICAgICAgZnVuY3Rpb24gZGlzcGxheUFuaW1hdGlvbigpIHtcclxuICAgICAgICBsYWJlbC5zdHlsZS5jb2xvciA9IFwiIzE4NzVEMFwiXHJcbiAgICAgICAgaWYoIWRlZmF1bHRCb3JkZXIubGVuZ3RoKSB7XHJcbiAgICAgICAgICBkZWZhdWx0Qm9yZGVyID0gd2luZG93LmdldENvbXB1dGVkU3R5bGUobGFiZWwucGFyZW50Tm9kZSkuYm9yZGVyQm90dG9tXHJcbiAgICAgICAgfSBlbHNlIHtcclxuICAgICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gYDJweCBzb2xpZCAjMTg3NUQwYFxyXG4gICAgICAgIH1cclxuXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LmFkZCgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIGhpZGVBbmltYXRpb24oKSB7XHJcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXHJcbiAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBkZWZhdWx0Qm9yZGVyXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LnJlbW92ZSgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgICR0aW1lb3V0KCAoKSA9PiB7XHJcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXHJcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcclxuICAgICAgICBlbHNlXHJcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcclxuICAgICAgfSwgMjUwKVxyXG5cclxuXHJcbiAgICAgIGVsLmFkZEV2ZW50TGlzdGVuZXIoJ2JsdXInLCBvbkJsdXIuYmluZCh0aGlzKSlcclxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignZm9jdXMnLCBvbkZvY3VzLmJpbmQoJHNjb3BlKSlcclxuICAgIH1cclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0QXJlYS5qc1xuICoqLyJdLCJtYXBwaW5ncyI6IjtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ3RDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7O0FBR0E7QUFDQTs7QUFFQTtBQUVBO0FBQ0E7QUFEQTtBQUlBO0FBREE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7O0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7Ozs7OztBQ2pEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7Ozs7O0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2hEQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDclBBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FDcEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNQQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNwQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ1BBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDUEE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBOztBQUVBOzs7Ozs7O0FDL0JBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ0pBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFMQTtBQUNBO0FBT0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBSUE7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFHQTtBQUNBO0FBQ0E7QUFDQTs7O0FBR0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFyQkE7QUF1QkE7Ozs7OztBQzFCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFqQ0E7QUFtQ0E7Ozs7OztBQ3RDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFOQTtBQVFBO0FBQ0E7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFJQTtBQUNBO0FBQUE7QUFBQTtBQUVBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBcEJBO0FBdUJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBaEdBO0FBa0dBOzs7Ozs7QUMxR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQVFBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQWxEQTtBQW9EQTs7Ozs7O0FDdkRBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOzs7Ozs7O0FBT0E7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUMxRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFEQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBbERBOzs7Ozs7OztBQ0ZBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7Ozs7OztBQVVBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2xFQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7Ozs7QUFRQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTs7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDakZBO0FBQ0E7Ozs7O0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQUE7QUFDQTs7OztBQUdBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7OztBQUFBOzs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7OztBQUVBO0FBQ0E7QUFHQTs7Ozs7O0FBR0E7Ozs7OztBQ2pEQTtBQUNBOzs7OztBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUFBO0FBQ0E7Ozs7QUFHQTtBQUNBO0FBQ0E7OztBQUFBOzs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7OztBQUVBO0FBQ0E7QUFHQTs7Ozs7O0FBR0E7Ozs7OztBQ25DQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTs7O0FBRUE7QUFBQTtBQUNBO0FBQUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUVBO0FBQ0E7O0FBR0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7Ozs7OztBQzdGQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBOztBQUVBOzs7QUFHQTtBQUNBO0FBRUE7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBSkE7QUFPQTtBQUFBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFKQTtBQVFBO0FBQUE7QUFBQTtBQUFBOzs7Ozs7Ozs7Ozs7QUFZQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTs7O0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUdBO0FBQUE7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7Ozs7OztBQzFGQTs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFvQkE7QUFDQTs7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQ0E7OztBQUlBO0FBQ0E7QUFFQTtBQUNBOztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFEQTtBQUNBO0FBV0E7QUFDQTtBQUNBO0FBRUE7QUFLQTtBQUVBOzs7Ozs7O0FDNURBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQzlDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FDakNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBQ0E7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQXBFQTtBQXNFQTs7OyIsInNvdXJjZVJvb3QiOiIifQ==