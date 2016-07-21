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
	__webpack_require__(49);

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

	module.exports = __webpack_require__.p + "images/avatar.jpg";

/***/ },
/* 49 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(50);
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
/* 50 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, ".view-profile-container {\n  background-color: #fff;\n  box-sizing: border-box;\n  padding: 40px 127px 30px;\n  margin: 5px auto 0;\n  width: 1103px;\n  font: 400 16px/24px Roboto;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n  .view-profile-container h1 {\n    padding-bottom: 16px;\n    text-align: center;\n    font: 400 22px/26px Roboto; }\n  .view-profile-container input:-moz-read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n  .view-profile-container input:read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n    .view-profile-container input:-moz-read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n    .view-profile-container input:read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n  .view-profile-container .social-link-container {\n    margin-bottom: 45px; }\n\n.profile-containers-wrap {\n  width: 849px;\n  margin: 0 auto; }\n\n.profile-left-container, .profile-right-container, .profile-info-section-left, .profile-info-section-right, .profile-info {\n  float: left; }\n  .profile-left-container::after, .profile-right-container::after, .profile-info-section-left::after, .profile-info-section-right::after, .profile-info::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.view-profile::after, .view-profile-container::after {\n  content: \"\";\n  display: table;\n  clear: both; }\n\n.profile-left-container {\n  width: 190px;\n  padding: 0 52px; }\n  .profile-left-container .btn-blue {\n    width: 180px;\n    height: 36px;\n    line-height: 36px;\n    margin: 0 auto;\n    margin-top: 9px; }\n  .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox {\n    display: block;\n    margin-bottom: 25px; }\n    .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox > .defaultValue {\n      color: #9e9e9e;\n      font: 400 16px / 24px Roboto; }\n\n.profile-right-container {\n  width: 485px;\n  padding-left: 20px;\n  border-left: 1px solid #ebebeb; }\n  .profile-right-container > .wrapForDiv {\n    width: auto;\n    float: none;\n    margin-bottom: 0; }\n    .profile-right-container > .wrapForDiv > ul.tab > li {\n      width: 50%; }\n      .profile-right-container > .wrapForDiv > ul.tab > li > a {\n        width: auto; }\n    .profile-right-container > .wrapForDiv > .featuresAndReviews {\n      width: 500px; }\n\n.profile-info-section-left, .profile-info-section-right {\n  width: 50%;\n  box-sizing: border-box; }\n\n.profile-avatar {\n  width: 145px;\n  height: 215px;\n  margin: 0 auto 40px;\n  padding-bottom: 5px;\n  background: url(" + __webpack_require__(48) + ") no-repeat center center;\n  background-size: contain;\n  box-sizing: border-box; }\n\n.profile-settings-dropdown .profile-settings-dropdown-title {\n  cursor: pointer;\n  padding: 13px 0; }\n  .profile-settings-dropdown .profile-settings-dropdown-title::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .profile-settings-dropdown .profile-settings-dropdown-title span {\n    color: #929292;\n    float: left; }\n  .profile-settings-dropdown .profile-settings-dropdown-title div {\n    float: right;\n    background: url(" + __webpack_require__(51) + ");\n    width: 13px;\n    height: 7px; }\n\n.social-link-container div {\n  cursor: pointer;\n  width: 24px;\n  height: 24px;\n  float: left; }\n  .social-link-container div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n  .social-link-container div:not(:last-of-type) {\n    margin-right: 23px; }\n\n.profile-messages-and-notifications {\n  display: block;\n  width: 100%;\n  margin: 30px 0;\n  float: left; }\n  .profile-messages-and-notifications::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav, .profile-messages-and-notifications-content {\n  border: 1px solid #ebebeb;\n  overflow: hidden; }\n\n.profile-messages-and-notifications-nav div {\n  color: #929292;\n  width: 50%;\n  padding: 24px 0;\n  text-align: center;\n  float: left; }\n  .profile-messages-and-notifications-nav div::after {\n    content: \"\";\n    display: table;\n    clear: both; }\n\n.profile-messages-and-notifications-nav .nav-item-selected {\n  color: #7eaee0;\n  border-bottom: 2px solid #ff5252; }\n\n.profile-messages-and-notifications-content {\n  background-color: #f6f6f6;\n  height: 180px;\n  box-sizing: border-box; }\n\n.profile-messages-and-notifications-content-item {\n  width: 100%;\n  border-bottom: 1px solid #ebebeb;\n  height: 123px;\n  position: relative;\n  overflow: hidden; }\n  .profile-messages-and-notifications-content-item .content-item-logo {\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 27px;\n    height: 27px;\n    padding: 0 23px 23px 7px;\n    background: url(" + __webpack_require__(52) + ") no-repeat;\n    display: inline-block;\n    background-position: center;\n    margin-top: 28px; }\n  .profile-messages-and-notifications-content-item .content-item-text {\n    display: inline-block;\n    float: right;\n    width: 373px;\n    margin: 28px 38px 24px 0;\n    font: 400 12px/18px Roboto;\n    color: #0c0c1e; }\n    .profile-messages-and-notifications-content-item .content-item-text::after {\n      content: \"\";\n      display: table;\n      clear: both; }\n\n.profile-info {\n  margin-bottom: 30px; }\n", ""]);

	// exports


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
	    this.type = "";
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

	        if (!$scope.$parent.db.user) $scope.$parent.redirectToUrl('/403', true);else {
	            this.contact = new ProfileContact();
	        }
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
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXBwLmpzIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vL3dlYnBhY2svYm9vdHN0cmFwIDAyODE5YTkxMzcyZWJmNjRlMGFiIiwid2VicGFjazovLy9hcHAuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Jhc2ljLnNjc3M/NGY1MyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvYmFzaWMuc2NzcyIsIndlYnBhY2s6Ly8vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzIiwid2VicGFjazovLy8uL2ltYWdlcy9sb2dvLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvbWFpbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL21haWxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYmVsbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvc2VydmljZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy91c2VyTmFtZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9uZXh0LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcHJldi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2Vycm9yX2JnLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvdmsucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9mYWNlYm9vay5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2dvb2dsZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhbGVuZGFyLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9tYXAucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9Gb3JBbmltYWxzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQnVzaW5lc3MucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL1RyYW5zcG9ydC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvSXNGcmVlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9CYXJ0ZXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9ydXByLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcnVwckhvdmVyLnBuZyIsIndlYnBhY2s6Ly8vLi9+L3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzcz9jZjI3Iiwid2VicGFjazovLy8uL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3MiLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9WLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2RvbGxhckJpbGwucG5nIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzcz9kYzJiIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYXZhdGFyLmpwZyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvcHJvZmlsZS5zY3NzPzAxYTEiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL3Byb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9pY29uX3VzZXIucG5nIiwid2VicGFjazovLy9tb2R1bGVzL2xvZ2dlci5qcyIsIndlYnBhY2s6Ly8vLi9kYXRhL2RlYnVnLmpzb24iLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dC5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9zZWxlY3RCb3guanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2V2ZW50cy5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9yb3V0ZXIuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2luZGV4LmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvYnVsbGV0aW5BZGQuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2xvZ2luLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9yZWdpc3Rlci5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL3NlYXJjaFJlc3VsdHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL21haW4uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvZGIuanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvdXRpbHMuanMiLCJ3ZWJwYWNrOi8vLy4vZGF0YS9jb25maWcuanNvbiIsIndlYnBhY2s6Ly8vLi9kYXRhL3NvcnRpbmcuanNvbiIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0QXJlYS5qcyJdLCJzb3VyY2VzQ29udGVudCI6WyIgXHQvLyBUaGUgbW9kdWxlIGNhY2hlXG4gXHR2YXIgaW5zdGFsbGVkTW9kdWxlcyA9IHt9O1xuXG4gXHQvLyBUaGUgcmVxdWlyZSBmdW5jdGlvblxuIFx0ZnVuY3Rpb24gX193ZWJwYWNrX3JlcXVpcmVfXyhtb2R1bGVJZCkge1xuXG4gXHRcdC8vIENoZWNrIGlmIG1vZHVsZSBpcyBpbiBjYWNoZVxuIFx0XHRpZihpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSlcbiBcdFx0XHRyZXR1cm4gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0uZXhwb3J0cztcblxuIFx0XHQvLyBDcmVhdGUgYSBuZXcgbW9kdWxlIChhbmQgcHV0IGl0IGludG8gdGhlIGNhY2hlKVxuIFx0XHR2YXIgbW9kdWxlID0gaW5zdGFsbGVkTW9kdWxlc1ttb2R1bGVJZF0gPSB7XG4gXHRcdFx0ZXhwb3J0czoge30sXG4gXHRcdFx0aWQ6IG1vZHVsZUlkLFxuIFx0XHRcdGxvYWRlZDogZmFsc2VcbiBcdFx0fTtcblxuIFx0XHQvLyBFeGVjdXRlIHRoZSBtb2R1bGUgZnVuY3Rpb25cbiBcdFx0bW9kdWxlc1ttb2R1bGVJZF0uY2FsbChtb2R1bGUuZXhwb3J0cywgbW9kdWxlLCBtb2R1bGUuZXhwb3J0cywgX193ZWJwYWNrX3JlcXVpcmVfXyk7XG5cbiBcdFx0Ly8gRmxhZyB0aGUgbW9kdWxlIGFzIGxvYWRlZFxuIFx0XHRtb2R1bGUubG9hZGVkID0gdHJ1ZTtcblxuIFx0XHQvLyBSZXR1cm4gdGhlIGV4cG9ydHMgb2YgdGhlIG1vZHVsZVxuIFx0XHRyZXR1cm4gbW9kdWxlLmV4cG9ydHM7XG4gXHR9XG5cblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGVzIG9iamVjdCAoX193ZWJwYWNrX21vZHVsZXNfXylcbiBcdF9fd2VicGFja19yZXF1aXJlX18ubSA9IG1vZHVsZXM7XG5cbiBcdC8vIGV4cG9zZSB0aGUgbW9kdWxlIGNhY2hlXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLmMgPSBpbnN0YWxsZWRNb2R1bGVzO1xuXG4gXHQvLyBfX3dlYnBhY2tfcHVibGljX3BhdGhfX1xuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5wID0gXCJcIjtcblxuIFx0Ly8gTG9hZCBlbnRyeSBtb2R1bGUgYW5kIHJldHVybiBleHBvcnRzXG4gXHRyZXR1cm4gX193ZWJwYWNrX3JlcXVpcmVfXygwKTtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIHdlYnBhY2svYm9vdHN0cmFwIDAyODE5YTkxMzcyZWJmNjRlMGFiXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbnJlcXVpcmUoXCIuL3N0eWxlcy9iYXNpYy5zY3NzXCIpXG5yZXF1aXJlKFwiLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXCIpXG5yZXF1aXJlKFwiLi9zdHlsZXMvZWRpdC1wcm9maWxlLnNjc3NcIilcbnJlcXVpcmUoXCIuL3N0eWxlcy9wcm9maWxlLnNjc3NcIilcblxucmVxdWlyZShcIi4vbW9kdWxlcy9sb2dnZXJcIikoKVxuXG5jb25zdCBtYXRlcmlhbHMgPSByZXF1aXJlKCcuL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzJyksXG4gICAgICByb3V0ZXIgPSByZXF1aXJlKCcuL21vZHVsZXMvcm91dGVyJylcblxuLy8gbGV0IGFwcCA9IGFuZ3VsYXIubW9kdWxlKCdndXAnLCBbJ25nUm91dGUnLCAnbmdDb29raWVzJ10pXG5sZXQgYXBwID0gYW5ndWxhci5tb2R1bGUoJ2d1cCcsIFsnbmdSb3V0ZSddKVxuXG4vLyBBcHAgY29uZmlnXG5hcHBcbiAgLmNvbmZpZyhbJyRyb3V0ZVByb3ZpZGVyJywgJyRsb2NhdGlvblByb3ZpZGVyJywgZnVuY3Rpb24oICRyb3V0ZVByb3ZpZGVyLCAkbG9jYXRpb25Qcm92aWRlcil7XG4gICAgZm9yKGxldCBrZXkgaW4gcm91dGVyKVxuICAgICAgJHJvdXRlUHJvdmlkZXIud2hlbihrZXksIHJvdXRlcltrZXldKVxuXG4gICAgJHJvdXRlUHJvdmlkZXIub3RoZXJ3aXNlKHtcbiAgICAgIHJlZGlyZWN0VG86ICcvNDA0J1xuICAgIH0pXG5cbiAgICAkbG9jYXRpb25Qcm92aWRlci5odG1sNU1vZGUoe1xuICAgICAgZW5hYmxlZCA6IHRydWUsXG4gICAgICByZXF1aXJlQmFzZSA6IGZhbHNlXG4gICAgfSlcbiAgfV0pXG4gIC5jb250cm9sbGVyKCdtYWluQ3RybCcsIHJlcXVpcmUoJy4vY29udHJvbGxlcnMvbWFpbicpKVxuXG5tYXRlcmlhbHNcbiAgLmluaXQoYXBwKVxuICAucnVuKClcblxuICAvKiBFdmVudCBlbW1pdHRlciBleGFtcGxlcyAqL1xuICBsZXQgaWQgPSBlZS5vbignbXVoYWhhaGEnLCBmdW5jdGlvbihkYXRhKSB7XG4gICAgY29uc29sZS5sb2coXCJidWdhZ2FzaGVjaGtvXCIpXG4gICAgY29uc29sZS5sb2coZGF0YSlcbiAgfSlcblxuICBlZS5lbWl0KHtcbiAgICBuYW1lIDogXCJtdWhhaGFoYVwiLFxuICAgIGRhdGEgOiBbMSwyLDMsNCw1XVxuICB9KVxuXG4gIGVlLm9mZihpZClcblxuICBlZS5lbWl0KHtcbiAgICBuYW1lIDogXCJtdWhhaGFoYVwiLFxuICAgIGRhdGEgOiBbMSwyLDMsNCw1XVxuICB9KVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogYXBwLmpzXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9iYXNpYy5zY3NzXCIpO1xuaWYodHlwZW9mIGNvbnRlbnQgPT09ICdzdHJpbmcnKSBjb250ZW50ID0gW1ttb2R1bGUuaWQsIGNvbnRlbnQsICcnXV07XG4vLyBhZGQgdGhlIHN0eWxlcyB0byB0aGUgRE9NXG52YXIgdXBkYXRlID0gcmVxdWlyZShcIiEuLy4uL25vZGVfbW9kdWxlcy9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXCIpKGNvbnRlbnQsIHt9KTtcbmlmKGNvbnRlbnQubG9jYWxzKSBtb2R1bGUuZXhwb3J0cyA9IGNvbnRlbnQubG9jYWxzO1xuLy8gSG90IE1vZHVsZSBSZXBsYWNlbWVudFxuaWYobW9kdWxlLmhvdCkge1xuXHQvLyBXaGVuIHRoZSBzdHlsZXMgY2hhbmdlLCB1cGRhdGUgdGhlIDxzdHlsZT4gdGFnc1xuXHRpZighY29udGVudC5sb2NhbHMpIHtcblx0XHRtb2R1bGUuaG90LmFjY2VwdChcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Jhc2ljLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9iYXNpYy5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL2Jhc2ljLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSAxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcImJvZHkge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0VDRUNFQzsgfVxcblxcbmhlYWRlciB7XFxuICBoZWlnaHQ6IDcwcHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgYmFja2dyb3VuZC1jb2xvcjogIzE4NzVEMDsgfVxcblxcbi5idG4tYmx1ZSwgLmJ0bi1ncmV5IHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIG92ZXJmbG93OiBoaWRkZW47XFxuICBib3JkZXItcmFkaXVzOiA1cHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgY3Vyc29yOiBwb2ludGVyO1xcbiAgdGV4dC1hbGlnbjogY2VudGVyOyB9XFxuXFxuLmJ0bi1ncmV5IHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNEOEQ4RDg7XFxuICBjb2xvcjogIzg2ODY4NjtcXG4gIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87IH1cXG5cXG4uY29udGFpbmVyIHtcXG4gIHdpZHRoOiAxMjgwcHg7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5jbGVhcmZpeDpiZWZvcmUsIC5jbGVhcmZpeDphZnRlciB7XFxuICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICBkaXNwbGF5OiB0YWJsZTsgfVxcblxcbi5jbGVhcmZpeDphZnRlciB7XFxuICBjbGVhcjogYm90aDsgfVxcblxcbi5pbmsge1xcbiAgZGlzcGxheTogYmxvY2s7XFxuICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICBiYWNrZ3JvdW5kOiByZ2JhKDAsIDAsIDAsIDAuMTUpO1xcbiAgYm9yZGVyLXJhZGl1czogMTAwJTtcXG4gIC13ZWJraXQtdHJhbnNmb3JtOiBzY2FsZSgwKTtcXG4gICAgICAgICAgdHJhbnNmb3JtOiBzY2FsZSgwKTsgfVxcblxcbi5pbmsuYW5pbWF0ZSB7XFxuICAtd2Via2l0LWFuaW1hdGlvbjogcmlwcGxlIC41cyBlYXNlLWluO1xcbiAgICAgICAgICBhbmltYXRpb246IHJpcHBsZSAuNXMgZWFzZS1pbjsgfVxcblxcbkAtd2Via2l0LWtleWZyYW1lcyByaXBwbGUge1xcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDA7XFxuICAgIC13ZWJraXQtdHJhbnNmb3JtOiBzY2FsZSgyLjUpO1xcbiAgICAgICAgICAgIHRyYW5zZm9ybTogc2NhbGUoMi41KTsgfSB9XFxuXFxuQGtleWZyYW1lcyByaXBwbGUge1xcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDA7XFxuICAgIC13ZWJraXQtdHJhbnNmb3JtOiBzY2FsZSgyLjUpO1xcbiAgICAgICAgICAgIHRyYW5zZm9ybTogc2NhbGUoMi41KTsgfSB9XFxuXFxuLmhlYWRMZWZ0IHtcXG4gIHBhZGRpbmctdG9wOiA1cHg7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICB3aWR0aDogY2FsYygxMDAlIC0gNDkwcHgpO1xcbiAgaGVpZ2h0OiA3MHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgLmhlYWRMZWZ0ID4gLmxvZ28ge1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGJvcmRlci1yYWRpdXM6IDUwJTtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGhlaWdodDogNjBweDtcXG4gICAgd2lkdGg6IDYwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbG9nby5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICBtYXJnaW4tbGVmdDogMTVweDsgfVxcbiAgLmhlYWRMZWZ0ID4gLmlucHV0Rm9ybSB7XFxuICAgIG1hcmdpbi10b3A6IDIxcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiAyMHB4O1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgYm9yZGVyLWNvbG9yOiAjRkRGREZEO1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICBoZWlnaHQ6IGF1dG87XFxuICAgIHdpZHRoOiAyMDBweDtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNGREZERkQ7IH1cXG4gICAgLmhlYWRMZWZ0ID4gLmlucHV0Rm9ybSA+IGxhYmVsLCAuaGVhZExlZnQgPiAuaW5wdXRGb3JtIGlucHV0IHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTRweCBSb2JvdG87IH1cXG4gIC5oZWFkTGVmdCA+IC5zZWxlY3RCb3gge1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLXRvcDogMjFweDtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7IH1cXG4gICAgLmhlYWRMZWZ0ID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJhY2tncm91bmQ6IG5vbmU7XFxuICAgICAgcGFkZGluZzogMCA1cHg7IH1cXG4gIC5oZWFkTGVmdCA+IC5hZGQge1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMjAwcHg7XFxuICAgIG1hcmdpbi1yaWdodDogMjBweDtcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9hZGQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgcmlnaHQgMTBweDtcXG4gICAgcGFkZGluZy1sZWZ0OiAxMHB4O1xcbiAgICB0ZXh0LWFsaWduOiBsZWZ0O1xcbiAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICBib3JkZXI6IDFweCBzb2xpZCB3aGl0ZTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjE1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4xNXM7IH1cXG4gICAgLmhlYWRMZWZ0ID4gLmFkZCA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMzVweCBSb2JvdG87IH1cXG5cXG4uaGVhZFJpZ2h0IHtcXG4gIGZsb2F0OiByaWdodDtcXG4gIHdpZHRoOiA0OTBweDtcXG4gIGhlaWdodDogNzBweDtcXG4gIGJvcmRlci1sZWZ0OiAxcHggc29saWQgZ3JleTtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBwYWRkaW5nLXRvcDogMjJweDsgfVxcbiAgLmhlYWRSaWdodCA+IC5tYWlsIHtcXG4gICAgaGVpZ2h0OiAyNnB4O1xcbiAgICB3aWR0aDogMzNweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL21haWwucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5tYWlsID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDEwcHggLyAxNXB4IFJvYm90bztcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYm90dG9tOiAtM3B4O1xcbiAgICAgIGxlZnQ6IDMycHg7XFxuICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgIC5oZWFkUmlnaHQgPiAubWFpbDpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9tYWlsX3NoYWRvdy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7IH1cXG4gIC5oZWFkUmlnaHQgPiAuYmVsbCB7XFxuICAgIGhlaWdodDogMjRweDtcXG4gICAgd2lkdGg6IDIzcHg7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tbGVmdDogMzBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9iZWxsLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gICAgYm9yZGVyLXJhZGl1czogMTVweCAwIDE1cHggMTBweDtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5iZWxsID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDEwcHggLyAxNXB4IFJvYm90bztcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYm90dG9tOiAtN3B4O1xcbiAgICAgIGxlZnQ6IDIycHg7XFxuICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuYmVsbDpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9iZWxsX3NoYWRvdy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7IH1cXG4gIC5oZWFkUmlnaHQgPiAuc2VydmljZXMge1xcbiAgICBoZWlnaHQ6IDI3cHg7XFxuICAgIHdpZHRoOiAyOHB4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAzMHB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3NlcnZpY2VzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5zZXJ2aWNlczpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDI1cHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvdXNlck5hbWUucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdDtcXG4gICAgcGFkZGluZy1sZWZ0OiAzMHB4O1xcbiAgICBtYXgtd2lkdGg6IDI3MHB4O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjdweCBSb2JvdG87XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHdoaXRlLXNwYWNlOiBub3dyYXA7XFxuICAgICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgICB0ZXh0LW92ZXJmbG93OiBlbGxpcHNpczsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gZGl2IHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgICAgIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHRvcDogMDtcXG4gICAgICB3aWR0aDogMTAwJTtcXG4gICAgICB6LWluZGV4OiAxOyB9XFxuICAgICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IGRpdiA+IHAge1xcbiAgICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgICAgcGFkZGluZzogMCAxNXB4O1xcbiAgICAgICAgaGVpZ2h0OiA0OHB4O1xcbiAgICAgICAgZm9udDogNDAwIDE2cHggLyA1MHB4IFJvYm90bztcXG4gICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAgICAgLmhlYWRSaWdodCA+IC51c2VyTmFtZSA+IGRpdiA+IHA6aG92ZXIge1xcbiAgICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjZWVlZWVlOyB9XFxuICAuaGVhZFJpZ2h0ID4gLmF1dGgge1xcbiAgICBjb2xvcjogd2hpdGU7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyMHB4O1xcbiAgICBmb250OiA0MDAgMTRweCAvIDI2cHggUm9ib3RvOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuYXV0aCBzcGFuIHtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgbWFyZ2luOiAwIDEwcHg7IH1cXG5cXG4uaW5wdXRTZWFyY2gge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgbWFyZ2luLXRvcDogMTBweDtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCB3aGl0ZTsgfVxcbiAgLmlucHV0U2VhcmNoID4gaW5wdXQge1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIHBhZGRpbmc6IDJweCAwO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDtcXG4gICAgb3V0bGluZTogbm9uZTtcXG4gICAgY29sb3I6ICNmZmZmZmY7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87IH1cXG4gIC5pbnB1dFNlYXJjaCA+IGxhYmVsIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICB0b3A6IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHRleHQ7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogLjVzO1xcbiAgICB0cmFuc2l0aW9uOiAuNXM7IH1cXG5cXG4uc2VsZWN0Qm94IHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIGN1cnNvcjogcG9pbnRlcjsgfVxcbiAgLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAxOXB4IFJvYm90bztcXG4gICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkIHdoaXRlO1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgcmlnaHQgNXB4O1xcbiAgICBwYWRkaW5nLXJpZ2h0OiAyMHB4O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHNwYW4ge1xcbiAgICAgIGZvbnQtc2l6ZTogMTZweDsgfVxcbiAgLnNlbGVjdEJveCA+IC5saXN0T2ZWYWx1ZXMge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHRvcDogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgIHotaW5kZXg6IDE7XFxuICAgIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICAgIC13ZWJraXQtYW5pbWF0aW9uOiBhbmltYXRldG9wIC4yNXM7XFxuICAgICAgICAgICAgYW5pbWF0aW9uOiBhbmltYXRldG9wIC4yNXM7IH1cXG4gICAgLnNlbGVjdEJveCA+IC5saXN0T2ZWYWx1ZXMgPiBkaXYge1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICBwYWRkaW5nOiAwIDE1cHg7XFxuICAgICAgaGVpZ2h0OiA1MHB4O1xcbiAgICAgIHdpZHRoOiAxMjBweDtcXG4gICAgICBmb250OiA0MDAgMTZweCAvIDUwcHggUm9ib3RvO1xcbiAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgICAuc2VsZWN0Qm94ID4gLmxpc3RPZlZhbHVlcyA+IGRpdjpob3ZlciB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjZWVlZWVlOyB9XFxuXFxuQC13ZWJraXQta2V5ZnJhbWVzIGFuaW1hdGV0b3Age1xcbiAgMCUge1xcbiAgICBvcGFjaXR5OiAwOyB9XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuQGtleWZyYW1lcyBhbmltYXRldG9wIHtcXG4gIDAlIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgMTAwJSB7XFxuICAgIG9wYWNpdHk6IDE7IH0gfVxcblxcbmFzaWRlLmJ1bGxldGluRGV0YWlscyB7XFxuICBmbG9hdDogcmlnaHQ7XFxuICBtYXJnaW4tdG9wOiA1cHg7IH1cXG5cXG5zZWN0aW9uLm9wZW5BZHZlcnRlcnQge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICBmbG9hdDogcmlnaHQ7XFxuICBtYXJnaW4tcmlnaHQ6IDEwcHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgd2lkdGg6IDcxNXB4O1xcbiAgcGFkZGluZzogMjVweCAxMDBweCA0NXB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIG1hcmdpbi10b3A6IDVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gaDMge1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDIycHggLyAyNnB4IFJvYm90bztcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5wcmljZSB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMThweCAvIDI2cHggUm9ib3RvO1xcbiAgICBmbG9hdDogcmlnaHQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5jaGVja0JveCB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAxNXB4O1xcbiAgICBtYXJnaW4tdG9wOiAtMXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuYnJlYWRDcnVtYnMge1xcbiAgICBjb2xvcjogcmdiYSgzMSwgMzEsIDMxLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNHB4IFJvYm90bztcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7XFxuICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmlkIHtcXG4gICAgY29sb3I6IHJnYmEoMzIsIDMyLCAzMiwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTRweCBSb2JvdG87XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyIHtcXG4gICAgaGVpZ2h0OiAxMjBweDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIG1hcmdpbi1ib3R0b206IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IGRpdiB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDEwcHg7XFxuICAgICAgaGVpZ2h0OiAxMDAlO1xcbiAgICAgIHdpZHRoOiAxNjVweDsgfVxcbiAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiBkaXY6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgICAgbWFyZ2luOiAwOyB9XFxuICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IGRpdiA+IGltZyB7XFxuICAgICAgICAtby1vYmplY3QtZml0OiBjb250YWluO1xcbiAgICAgICAgICAgb2JqZWN0LWZpdDogY29udGFpbjtcXG4gICAgICAgIHdpZHRoOiAxMDAlO1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0Y0RjRGNDtcXG4gICAgICAgIGhlaWdodDogMTAwJTtcXG4gICAgICAgIGN1cnNvcjogcG9pbnRlcjsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gLm5leHQge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL25leHQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IGNlbnRlcjtcXG4gICAgICBoZWlnaHQ6IDE0cHg7XFxuICAgICAgd2lkdGg6IDEwcHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHRvcDogNTAlO1xcbiAgICAgIG1hcmdpbi10b3A6IC03cHg7XFxuICAgICAgcmlnaHQ6IC0yNXB4OyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiAucHJldiB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvcHJldi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IHJpZ2h0IGNlbnRlcjtcXG4gICAgICBoZWlnaHQ6IDE0cHg7XFxuICAgICAgd2lkdGg6IDEwcHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHRvcDogNTAlO1xcbiAgICAgIG1hcmdpbi10b3A6IC03cHg7XFxuICAgICAgbGVmdDogLTI1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+ICNtYXBGb3JPbmVBZHZlcnRlcnQge1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgaGVpZ2h0OiAyMzBweDtcXG4gICAgd2lkdGg6IDIyNXB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAyNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuZGVzY3JpcHRpb25BZCB7XFxuICAgIGNvbG9yOiAjMGMwYzFlO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE4cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxNXB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAyMHB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuZ29Ub01hcCB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuYWxsQ29tbWVudHMge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bztcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogNTBweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLndyaXRlQVJldmlldyB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLm5hbWVVc2VyIHtcXG4gICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMThweCBSb2JvdG87XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICBtYXJnaW4tYm90dG9tOiAzNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAub25Pck9mZkxpbmVVc2VyIHtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCAuYnRuLWdyZXkge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE0MHB4O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDI1cHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgLmJ0bi1ibHVlIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7IH1cXG5cXG4ud3JhcEZvckRpdiB7XFxuICB3aWR0aDogMjY1cHg7XFxuICBmbG9hdDogcmlnaHQ7XFxuICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgbWFyZ2luLWJvdHRvbTogMjVweDtcXG4gIGJvcmRlcjogMXB4IHNvbGlkICNFOUU5RTk7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAud3JhcEZvckRpdiA+IHVsLnRhYiB7XFxuICAgIGxpc3Qtc3R5bGUtdHlwZTogbm9uZTtcXG4gICAgaGVpZ2h0OiA1MHB4O1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNFOUU5RTk7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYSB7XFxuICAgICAgICBjb2xvcjogIzkzOTM5MztcXG4gICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gNTBweCBSb2JvdG87XFxuICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6YWZ0ZXIge1xcbiAgICAgICAgICBjb250ZW50OiAnJztcXG4gICAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgYm90dG9tOiAwO1xcbiAgICAgICAgICB3aWR0aDogMDtcXG4gICAgICAgICAgaGVpZ2h0OiAycHg7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGRDUxNTE7XFxuICAgICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYTpmb2N1cywgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGEuYWN0aXZlIHtcXG4gICAgICAgICAgY29sb3I6ICM3ZWFmZTE7IH1cXG4gICAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6Zm9jdXM6YWZ0ZXIsIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhLmFjdGl2ZTphZnRlciB7XFxuICAgICAgICAgICAgd2lkdGg6IDEwMCU7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDEpID4gYSB7XFxuICAgICAgICB3aWR0aDogMTU5cHg7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGk6bnRoLW9mLXR5cGUoMSkgPiBhOmFmdGVyIHtcXG4gICAgICAgICAgcmlnaHQ6IDA7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDIpID4gYSB7XFxuICAgICAgICB3aWR0aDogMTA0cHg7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGk6bnRoLW9mLXR5cGUoMikgPiBhOmFmdGVyIHtcXG4gICAgICAgICAgbGVmdDogMDsgfVxcbiAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzIHtcXG4gICAgaGVpZ2h0OiAxNzhweDtcXG4gICAgd2lkdGg6IDI4MnB4O1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjRGNEY0O1xcbiAgICBvdmVyZmxvdzogYXV0bztcXG4gICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAudGFiY29udGVudCB7XFxuICAgICAgZGlzcGxheTogbm9uZTtcXG4gICAgICAtd2Via2l0LWFuaW1hdGlvbjogZmFkZUVmZmVjdCAxcztcXG4gICAgICBhbmltYXRpb246IGZhZGVFZmZlY3QgMXM7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAudGFiY29udGVudC5hY3RpdmUge1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7IH1cXG4gICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3Mge1xcbiAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2IHtcXG4gICAgICAgIHBhZGRpbmc6IDMwcHggMjBweCAyNXB4IDY1cHg7XFxuICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0U5RTlFOTsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXY6bnRoLWxhc3Qtb2YtdHlwZSgxKSB7XFxuICAgICAgICAgIGJvcmRlcjogbm9uZTsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXYgPiBpbWcge1xcbiAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgIGhlaWdodDogMzBweDtcXG4gICAgICAgICAgd2lkdGg6IDI1cHg7XFxuICAgICAgICAgIHRvcDogMzVweDtcXG4gICAgICAgICAgbGVmdDogMjBweDsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gI3Jldmlld3MgPiBkaXYgPiBwIHtcXG4gICAgICAgICAgY29sb3I6ICMwZDBkMWU7XFxuICAgICAgICAgIGZvbnQ6IDQwMCAxMnB4IC8gMThweCBSb2JvdG87IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgZmFkZUVmZmVjdCB7XFxuICBmcm9tIHtcXG4gICAgb3BhY2l0eTogMDsgfVxcbiAgdG8ge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIGZhZGVFZmZlY3Qge1xcbiAgZnJvbSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIHRvIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuLmJ0bi1ibHVlIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMxODc1RDA7XFxuICBjb2xvcjogI2ZmZmZmZjtcXG4gIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87IH1cXG5cXG4ub25Pck9mZkxpbmVVc2VyIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICMwMEM2NTI7XFxuICBib3JkZXI6IDFweCBzb2xpZCB3aGl0ZTtcXG4gIGJveC1zaGFkb3c6IDFweCAxcHggM3B4IDBweCByZ2JhKDAsIDAsIDAsIDAuNjUpO1xcbiAgaGVpZ2h0OiAxOHB4O1xcbiAgd2lkdGg6IDE4cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYm9yZGVyLXJhZGl1czogNTAlOyB9XFxuXFxuLmVycm9yLWNvbnRhaW5lciB7XFxuICB3aWR0aDogMTEwM3B4O1xcbiAgbWFyZ2luOiBhdXRvO1xcbiAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9lcnJvcl9iZy5wbmdcIikgKyBcIik7XFxuICBiYWNrZ3JvdW5kLXJlcGVhdDogbm8tcmVwZWF0O1xcbiAgYmFja2dyb3VuZC1wb3NpdGlvbi14OiByaWdodDtcXG4gIGJhY2tncm91bmQtcG9zaXRpb24teTogODBweDtcXG4gIG1pbi1oZWlnaHQ6IDUwMHB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgxIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjYuNHB4IFJvYm90bztcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBtYXJnaW4tdG9wOiA2NXB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgyIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IC8gMjYuNHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTg5cHg7IH1cXG4gIC5lcnJvci1jb250YWluZXIgaDMge1xcbiAgICBjb2xvcjogcmdiYSgzMSwgMzEsIDMxLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNC40cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAzMHB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyID4gLmJ0bi1ibHVlIHtcXG4gICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuXFxuLmNsaWNrZWQge1xcbiAgYm94LXNoYWRvdzogMHB4IDBweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KSAhaW1wb3J0YW50O1xcbiAgbWFyZ2luLXRvcDogMTJweCAhaW1wb3J0YW50OyB9XFxuXFxuLm11bHRpcGxlIHtcXG4gIHdpZHRoOiAxMTUwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7IH1cXG4gIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCB7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICB3aWR0aDogMzgwcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiA1cHg7XFxuICAgIGhlaWdodDogMTY1cHg7XFxuICAgIHBhZGRpbmc6IDE1cHg7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDNuLTIpIHtcXG4gICAgICBtYXJnaW4tbGVmdDogMDsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQ6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNCQ0U2RTk7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDIpIHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjQkNFNkU5OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgzKSB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0JDRTZFOTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQgPiAuYnVsbGV0aW4tY2VudGVyLWNvbHVtbiB7XFxuICAgICAgZmxvYXQ6IG5vbmU7XFxuICAgICAgbWFyZ2luOiAwO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB0b3A6IDE1cHg7XFxuICAgICAgcmlnaHQ6IDExMHB4OyB9XFxuICAgICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0ID4gLmJ1bGxldGluLWNlbnRlci1jb2x1bW4gPiAuYnVsbGV0aW4tYWN0aW9uIHtcXG4gICAgICAgIGJvdHRvbTogLTQzcHg7XFxuICAgICAgICB3aWR0aDogMTEwcHg7XFxuICAgICAgICByaWdodDogLTMwcHg7XFxuICAgICAgICB6LWluZGV4OiAxOyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCA+IC5idWxsZXRpbi1sZWZ0LWNvbHVtbiA+IC5idWxsZXRpbi1kZXNjcmlwdGlvbiB7XFxuICAgICAgY29sb3I6ICMwYzBjMWU7XFxuICAgICAgZm9udDogNDAwIDEycHggLyAxOHB4IFJvYm90bztcXG4gICAgICB3aWR0aDogMTcwcHg7XFxuICAgICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgICBoZWlnaHQ6IDQwcHg7IH1cXG5cXG4ucmVkIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGNzRBNEE7IH1cXG5cXG4uaW5wdXRGb3JtLXJlcXVpcmVkIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIHBhZGRpbmc6IDZweCAycHg7XFxuICBmb250OiA0MDAgMTZweCAvIDI0LjhweCBSb2JvdG87XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5YjliOWI7XFxuICBtYXJnaW4tdG9wOiAxNXB4OyB9XFxuICAuaW5wdXRGb3JtLXJlcXVpcmVkIGxhYmVsIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBsZWZ0OiAycHg7XFxuICAgIHRvcDogNnB4O1xcbiAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gIC5pbnB1dEZvcm0tcmVxdWlyZWQgaW5wdXQge1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDsgfVxcblxcbi5pbnB1dEZvcm0ucmVxdWlyZWQge1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5YjliOWIgIWltcG9ydGFudDsgfVxcblxcbi5pbnB1dEZvcm0ge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJvcmRlci1ib3R0b206IDFweCBkb3R0ZWQgIzk5OTk5OTsgfVxcbiAgLmlucHV0Rm9ybSBsYWJlbCB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgbGVmdDogNXB4O1xcbiAgICBjb2xvcjogIzlhOWE5YTtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRvcDogLTFweDsgfVxcbiAgLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgb3V0bGluZTogbm9uZTtcXG4gICAgd2lkdGg6IDEwMCU7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIHBhZGRpbmc6IDFweCA1cHg7IH1cXG5cXG4vKiBUZXh0IGVsZW1lbnQgYW5pbWF0aW9uICovXFxuLnRleHRPdXQge1xcbiAgdG9wOiAtMTVweCAhaW1wb3J0YW50O1xcbiAgZm9udC1zaXplOiAxMnB4ICFpbXBvcnRhbnQ7XFxuICBsZWZ0OiA1cHggIWltcG9ydGFudDsgfVxcblxcbnNlY3Rpb24ubG9naW4sIHNlY3Rpb24ucmVnaXN0ZXIge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBwYWRkaW5nOiA2NXB4IDIzMHB4O1xcbiAgd2lkdGg6IDExMDBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIHNlY3Rpb24ubG9naW4gPiBoMiwgc2VjdGlvbi5yZWdpc3RlciA+IGgyIHtcXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDIycHggUm9ib3RvO1xcbiAgICBtYXJnaW4tYm90dG9tOiA1NXB4OyB9XFxuICBzZWN0aW9uLmxvZ2luID4gLmlucHV0Rm9ybSwgc2VjdGlvbi5yZWdpc3RlciA+IC5pbnB1dEZvcm0ge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuaW5wdXRGb3JtID4gbGFiZWwsIHNlY3Rpb24ubG9naW4gPiAuaW5wdXRGb3JtIGlucHV0LCBzZWN0aW9uLnJlZ2lzdGVyID4gLmlucHV0Rm9ybSA+IGxhYmVsLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgICAgY29sb3I6ICM5YTlhOWE7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvOyB9XFxuICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlIHtcXG4gICAgaGVpZ2h0OiAzNXB4O1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIG1hcmdpbi1sZWZ0OiA0NXB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLnZrLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlLnZrIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjMDE4NkNGIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvdmsucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdCAxMHB4O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAwOyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuYnRuLWJsdWUuZmFjZWJvb2ssIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUuZmFjZWJvb2sge1xcbiAgICAgIGJhY2tncm91bmQ6ICMzRTUwQjMgdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9mYWNlYm9vay5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZS5nb29nbGUsIHNlY3Rpb24ucmVnaXN0ZXIgPiAuYnRuLWJsdWUuZ29vZ2xlIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjRkQzQzAwIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZ29vZ2xlLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQgMTBweDsgfVxcblxcbnNlY3Rpb24uYnVsbGV0aW5BZGQge1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICB3aWR0aDogMTEwMHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwO1xcbiAgcGFkZGluZzogNjVweCAyMjVweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiBoMiB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMjJweCBSb2JvdG87XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5zZWxlY3RCb3gge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4O1xcbiAgICBtYXJnaW4tdG9wOiAxNXB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgYm9yZGVyLWNvbG9yOiBncmV5O1xcbiAgICAgIG1pbi13aWR0aDogMTk1cHg7XFxuICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgIHBhZGRpbmctbGVmdDogNXB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7XFxuICAgIHdpZHRoOiA0MTBweDtcXG4gICAgcGFkZGluZy1ib3R0b206IDVweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUgPiBpbnB1dCB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHotaW5kZXg6IC0xOyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuZmlsZSA+IHAge1xcbiAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICBjdXJzb3I6IGRlZmF1bHQ7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgcGFkZGluZy10b3A6IDEwcHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlID4gLmJ0bi1ibHVlIHtcXG4gICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgYm94LXNoYWRvdzogMHB4IDJweCA0cHggMHB4IHJnYmEoMCwgMCwgMCwgMC40KTtcXG4gICAgICBoZWlnaHQ6IDMwcHg7XFxuICAgICAgd2lkdGg6IDg1cHg7XFxuICAgICAgbGluZS1oZWlnaHQ6IDMwcHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuaW5wdXRGb3JtIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmlucHV0Rm9ybSA+IGxhYmVsLCBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmlucHV0Rm9ybSBpbnB1dCB7XFxuICAgICAgY29sb3I6ICM5OTk5OTk7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvOyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gcCB7XFxuICAgICAgY29sb3I6ICM5OTk5OTk7XFxuICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBkb3R0ZWQgIzk5OTk5OTtcXG4gICAgICB3aWR0aDogMTYwcHg7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgaGVpZ2h0OiAyMXB4O1xcbiAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdiB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDE5cHg7XFxuICAgICAgbWFyZ2luLXRvcDogM3B4O1xcbiAgICAgIHdpZHRoOiAxNnB4O1xcbiAgICAgIGhlaWdodDogMTVweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gICAgICBvdXRsaW5lOiAxcHggc29saWQgdHJhbnNwYXJlbnQ7XFxuICAgICAgYm9yZGVyOiAxcHggc29saWQgd2hpdGU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnRyYW5zcGFyZW50IHtcXG4gICAgICAgIGJvcmRlci1jb2xvcjogI0U5RTlFOTtcXG4gICAgICAgIG92ZXJmbG93OiBoaWRkZW47IH1cXG4gICAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYudHJhbnNwYXJlbnQgPiAucmVkU3RpY2sge1xcbiAgICAgICAgICAtd2Via2l0LXRyYW5zZm9ybTogcm90YXRlKC00M2RlZyk7XFxuICAgICAgICAgICAgICAgICAgdHJhbnNmb3JtOiByb3RhdGUoLTQzZGVnKTtcXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcmVkO1xcbiAgICAgICAgICBoZWlnaHQ6IDJweDtcXG4gICAgICAgICAgbWFyZ2luLXRvcDogNS41cHg7XFxuICAgICAgICAgIHdpZHRoOiAyMHB4O1xcbiAgICAgICAgICBtYXJnaW4tbGVmdDogLTNweDsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYucmVkIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHJlZDsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYub3JhbmdlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IG9yYW5nZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYueWVsbG93IHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHllbGxvdzsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuZ3JlZW4ge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogZ3JlZW47IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmxpZ2h0Qmx1ZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBsaWdodEJsdWU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmJsdWUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogYmx1ZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYucGluayB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBwaW5rOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5wdXJwbGUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcHVycGxlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi53aGl0ZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gICAgICAgIGJvcmRlci1jb2xvcjogI0U5RTlFOTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuZ3JheSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBncmF5OyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5ibGFjayB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBibGFjazsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuYnJvd24ge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogYnJvd247IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2Lm11bHRpY29sb3Ige1xcbiAgICAgICAgLyogUGVybWFsaW5rIC0gdXNlIHRvIGVkaXQgYW5kIHNoYXJlIHRoaXMgZ3JhZGllbnQ6IGh0dHA6Ly9jb2xvcnppbGxhLmNvbS9ncmFkaWVudC1lZGl0b3IvI2ZmMDAwMCswLGZmZmYwMCsyMCwxZGZmMDArNDAsMDBmZmZmKzYwLDA0MDBmZis4MCxmZjAwZmErMTAwICovXFxuICAgICAgICBiYWNrZ3JvdW5kOiAjZmYwMDAwO1xcbiAgICAgICAgLyogT2xkIGJyb3dzZXJzICovXFxuICAgICAgICAvKiBGRjMuNi0xNSAqL1xcbiAgICAgICAgYmFja2dyb3VuZDogLXdlYmtpdC1saW5lYXItZ3JhZGllbnQobGVmdCwgI2ZmMDAwMCAwJSwgI2ZmZmYwMCAyMCUsICMxZGZmMDAgNDAlLCAjMDBmZmZmIDYwJSwgIzA0MDBmZiA4MCUsICNmZjAwZmEgMTAwJSk7XFxuICAgICAgICAvKiBDaHJvbWUxMC0yNSxTYWZhcmk1LjEtNiAqL1xcbiAgICAgICAgYmFja2dyb3VuZDogbGluZWFyLWdyYWRpZW50KHRvIHJpZ2h0LCAjZmYwMDAwIDAlLCAjZmZmZjAwIDIwJSwgIzFkZmYwMCA0MCUsICMwMGZmZmYgNjAlLCAjMDQwMGZmIDgwJSwgI2ZmMDBmYSAxMDAlKTtcXG4gICAgICAgIC8qIFczQywgSUUxMCssIEZGMTYrLCBDaHJvbWUyNissIE9wZXJhMTIrLCBTYWZhcmk3KyAqL1xcbiAgICAgICAgZmlsdGVyOiBwcm9naWQ6RFhJbWFnZVRyYW5zZm9ybS5NaWNyb3NvZnQuZ3JhZGllbnQoIHN0YXJ0Q29sb3JzdHI9JyNmZjAwMDAnLCBlbmRDb2xvcnN0cj0nI2ZmMDBmYScsR3JhZGllbnRUeXBlPTEgKTtcXG4gICAgICAgIC8qIElFNi05ICovIH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmFjdGl2ZSB7XFxuICAgICAgICBvdXRsaW5lLWNvbG9yOiByZWQ7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY2FsZW5kYXIsIHNlY3Rpb24uYnVsbGV0aW5BZGQgLmFkZENhbGVuZGFyIHtcXG4gICAgaGVpZ2h0OiAyMHB4O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIHdpZHRoOiAyMzBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYWxlbmRhci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0O1xcbiAgICBwYWRkaW5nLWxlZnQ6IDQ1cHg7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUsIHNlY3Rpb24uYnVsbGV0aW5BZGQgLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkIGdyZXk7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIHJpZ2h0IDVweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSA+IHAsIHNlY3Rpb24uYnVsbGV0aW5BZGQgLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSA+IHAge1xcbiAgICAgICAgdGV4dC1hbGlnbjogbGVmdDtcXG4gICAgICAgIGNvbG9yOiAjMjYyNjI2O1xcbiAgICAgICAgZm9udDogNDAwIDE0cHggLyAyMHB4IFJvYm90bzsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNhbGVuZGFyID4gLmxpc3RWYWx1ZSwgc2VjdGlvbi5idWxsZXRpbkFkZCAuYWRkQ2FsZW5kYXIgPiAubGlzdFZhbHVlIHtcXG4gICAgICBkaXNwbGF5OiBub25lOyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmFkZENhbGVuZGFyIHtcXG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9hZGRDYWxlbmRhci5wbmdcIikgKyBcIik7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJvcmRlci1ib3R0b20tY29sb3I6IGJsdWU7XFxuICAgICAgYmFja2dyb3VuZDogbm9uZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYWRkQ2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlID4gcCB7XFxuICAgICAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgICAgIGZvbnQtc2l6ZTogMTZweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5idG4tYmx1ZSB7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDtcXG4gICAgZGlzcGxheTogYmxvY2s7XFxuICAgIG1hcmdpbjogMCBhdXRvOyB9XFxuXFxuLmVycm9ycyB7XFxuICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICBmb250OiA0MDAgMTJweCAvIDE0cHggUm9ib3RvO1xcbiAgY29sb3I6ICNkZDJjMDA7XFxuICBib3R0b206IC0xN3B4OyB9XFxuXFxubmF2IHtcXG4gIHdpZHRoOiAyNTVweDtcXG4gIGZsb2F0OiBsZWZ0O1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZERkRGRDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBtYXJnaW4tdG9wOiA1cHg7IH1cXG4gIG5hdiA+IC5tYXAge1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL21hcC5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgICBoZWlnaHQ6IDE0NXB4O1xcbiAgICB3aWR0aDogMjU1cHg7IH1cXG4gICAgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0ge1xcbiAgICAgIHdpZHRoOiAyMTVweDtcXG4gICAgICBtYXJnaW46IDExNXB4IGF1dG8gMDsgfVxcbiAgICAgIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtLnJlcXVpcmVkIHtcXG4gICAgICAgIGJvcmRlci1jb2xvcjogYmxhY2sgIWltcG9ydGFudDsgfVxcbiAgICAgIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtID4gaW5wdXQsIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtIGxhYmVsLCBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSAudGV4dE91dCB7XFxuICAgICAgICBjb2xvcjogYmxhY2sgIWltcG9ydGFudDsgfVxcbiAgbmF2ID4gdWwge1xcbiAgICBsaXN0LXN0eWxlOiBub25lOyB9XFxuICAgIG5hdiA+IHVsID4gbGkgPiBwIHtcXG4gICAgICBwYWRkaW5nLWxlZnQ6IDc1cHg7XFxuICAgICAgY29sb3I6ICMyNjMyMzg7XFxuICAgICAgZm9udDogNTAwIDEzcHggLyA0MHB4IFJvYm90bztcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NhcmV0UmlnaHROYXYucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgcmlnaHQgMjBweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0U2RTZFNiAhaW1wb3J0YW50OyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvRm9yQW5pbWFscy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgzKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9CdXNpbmVzcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg0KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg1KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9UcmFuc3BvcnQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNikge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvQ2xvdGhpbmdBbmRDb3NtZXRpY3MucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoNykge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDgpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0lzRnJlZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg5KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9BSG91c2VBbmRBR2FyZGVuLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDEwKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Ib2JiaWVzQW5kU3BvcnRzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDExKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9CYXJ0ZXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMTIpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1RlY2hub2xvZ2llcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG5cXG4ubmF2QnRuIHtcXG4gIHdpZHRoOiA0M3B4O1xcbiAgaGVpZ2h0OiA0OXB4O1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgbGVmdDogMDtcXG4gIHRvcDogOTBweDtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvcnVwci5wbmdcIikgKyBcIikgbm8tcmVwZWF0O1xcbiAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgei1pbmRleDogMTsgfVxcbiAgLm5hdkJ0bjpob3ZlciB7XFxuICAgIHdpZHRoOiA2MHB4O1xcbiAgICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3J1cHJIb3Zlci5wbmdcIikgKyBcIik7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvYmFzaWMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8qXHJcblx0TUlUIExpY2Vuc2UgaHR0cDovL3d3dy5vcGVuc291cmNlLm9yZy9saWNlbnNlcy9taXQtbGljZW5zZS5waHBcclxuXHRBdXRob3IgVG9iaWFzIEtvcHBlcnMgQHNva3JhXHJcbiovXHJcbi8vIGNzcyBiYXNlIGNvZGUsIGluamVjdGVkIGJ5IHRoZSBjc3MtbG9hZGVyXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcblx0dmFyIGxpc3QgPSBbXTtcclxuXHJcblx0Ly8gcmV0dXJuIHRoZSBsaXN0IG9mIG1vZHVsZXMgYXMgY3NzIHN0cmluZ1xyXG5cdGxpc3QudG9TdHJpbmcgPSBmdW5jdGlvbiB0b1N0cmluZygpIHtcclxuXHRcdHZhciByZXN1bHQgPSBbXTtcclxuXHRcdGZvcih2YXIgaSA9IDA7IGkgPCB0aGlzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBpdGVtID0gdGhpc1tpXTtcclxuXHRcdFx0aWYoaXRlbVsyXSkge1xyXG5cdFx0XHRcdHJlc3VsdC5wdXNoKFwiQG1lZGlhIFwiICsgaXRlbVsyXSArIFwie1wiICsgaXRlbVsxXSArIFwifVwiKTtcclxuXHRcdFx0fSBlbHNlIHtcclxuXHRcdFx0XHRyZXN1bHQucHVzaChpdGVtWzFdKTtcclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdFx0cmV0dXJuIHJlc3VsdC5qb2luKFwiXCIpO1xyXG5cdH07XHJcblxyXG5cdC8vIGltcG9ydCBhIGxpc3Qgb2YgbW9kdWxlcyBpbnRvIHRoZSBsaXN0XHJcblx0bGlzdC5pID0gZnVuY3Rpb24obW9kdWxlcywgbWVkaWFRdWVyeSkge1xyXG5cdFx0aWYodHlwZW9mIG1vZHVsZXMgPT09IFwic3RyaW5nXCIpXHJcblx0XHRcdG1vZHVsZXMgPSBbW251bGwsIG1vZHVsZXMsIFwiXCJdXTtcclxuXHRcdHZhciBhbHJlYWR5SW1wb3J0ZWRNb2R1bGVzID0ge307XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgdGhpcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaWQgPSB0aGlzW2ldWzBdO1xyXG5cdFx0XHRpZih0eXBlb2YgaWQgPT09IFwibnVtYmVyXCIpXHJcblx0XHRcdFx0YWxyZWFkeUltcG9ydGVkTW9kdWxlc1tpZF0gPSB0cnVlO1xyXG5cdFx0fVxyXG5cdFx0Zm9yKGkgPSAwOyBpIDwgbW9kdWxlcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaXRlbSA9IG1vZHVsZXNbaV07XHJcblx0XHRcdC8vIHNraXAgYWxyZWFkeSBpbXBvcnRlZCBtb2R1bGVcclxuXHRcdFx0Ly8gdGhpcyBpbXBsZW1lbnRhdGlvbiBpcyBub3QgMTAwJSBwZXJmZWN0IGZvciB3ZWlyZCBtZWRpYSBxdWVyeSBjb21iaW5hdGlvbnNcclxuXHRcdFx0Ly8gIHdoZW4gYSBtb2R1bGUgaXMgaW1wb3J0ZWQgbXVsdGlwbGUgdGltZXMgd2l0aCBkaWZmZXJlbnQgbWVkaWEgcXVlcmllcy5cclxuXHRcdFx0Ly8gIEkgaG9wZSB0aGlzIHdpbGwgbmV2ZXIgb2NjdXIgKEhleSB0aGlzIHdheSB3ZSBoYXZlIHNtYWxsZXIgYnVuZGxlcylcclxuXHRcdFx0aWYodHlwZW9mIGl0ZW1bMF0gIT09IFwibnVtYmVyXCIgfHwgIWFscmVhZHlJbXBvcnRlZE1vZHVsZXNbaXRlbVswXV0pIHtcclxuXHRcdFx0XHRpZihtZWRpYVF1ZXJ5ICYmICFpdGVtWzJdKSB7XHJcblx0XHRcdFx0XHRpdGVtWzJdID0gbWVkaWFRdWVyeTtcclxuXHRcdFx0XHR9IGVsc2UgaWYobWVkaWFRdWVyeSkge1xyXG5cdFx0XHRcdFx0aXRlbVsyXSA9IFwiKFwiICsgaXRlbVsyXSArIFwiKSBhbmQgKFwiICsgbWVkaWFRdWVyeSArIFwiKVwiO1xyXG5cdFx0XHRcdH1cclxuXHRcdFx0XHRsaXN0LnB1c2goaXRlbSk7XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHR9O1xyXG5cdHJldHVybiBsaXN0O1xyXG59O1xyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvbG9nby5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2xvZ28ucG5nXG4gKiogbW9kdWxlIGlkID0gNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2FkZC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2FkZC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvbWFpbC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL21haWwucG5nXG4gKiogbW9kdWxlIGlkID0gNlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL21haWxfc2hhZG93LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbWFpbF9zaGFkb3cucG5nXG4gKiogbW9kdWxlIGlkID0gN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2JlbGwucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9iZWxsLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9iZWxsX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9zZXJ2aWNlcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3NlcnZpY2VzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDEwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvc2VydmljZXNfc2hhZG93LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvc2VydmljZXNfc2hhZG93LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDExXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvdXNlck5hbWUucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy91c2VyTmFtZS5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYXJldENhbGVuZGFyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDEzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvbmV4dC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL25leHQucG5nXG4gKiogbW9kdWxlIGlkID0gMTRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9wcmV2LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvcHJldi5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2Vycm9yX2JnLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZXJyb3JfYmcucG5nXG4gKiogbW9kdWxlIGlkID0gMTZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy92ay5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3ZrLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZmFjZWJvb2sucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9mYWNlYm9vay5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2dvb2dsZS5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2dvb2dsZS5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxOVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhbGVuZGFyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvY2FsZW5kYXIucG5nXG4gKiogbW9kdWxlIGlkID0gMjBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9hZGRDYWxlbmRhci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2FkZENhbGVuZGFyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvbWFwLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbWFwLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FyZXRSaWdodE5hdi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhcmV0UmlnaHROYXYucG5nXG4gKiogbW9kdWxlIGlkID0gMjNcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Gb3JDaGlsZHJlbi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0ZvckNoaWxkcmVuLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvRm9yQW5pbWFscy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0ZvckFuaW1hbHMucG5nXG4gKiogbW9kdWxlIGlkID0gMjVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9CdXNpbmVzcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0J1c2luZXNzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVGhlUHJvcGVydHkucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RyYW5zcG9ydC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1RyYW5zcG9ydC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvQ2xvdGhpbmdBbmRDb3NtZXRpY3MucG5nXG4gKiogbW9kdWxlIGlkID0gMjlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Wb2x1bnRlZXJpbmcucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Wb2x1bnRlZXJpbmcucG5nXG4gKiogbW9kdWxlIGlkID0gMzBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Jc0ZyZWUucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Jc0ZyZWUucG5nXG4gKiogbW9kdWxlIGlkID0gMzFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9BSG91c2VBbmRBR2FyZGVuLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Ib2JiaWVzQW5kU3BvcnRzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQmFydGVyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvQmFydGVyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVGVjaG5vbG9naWVzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVGVjaG5vbG9naWVzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvcnVwci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3J1cHIucG5nXG4gKiogbW9kdWxlIGlkID0gMzZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9ydXBySG92ZXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9ydXBySG92ZXIucG5nXG4gKiogbW9kdWxlIGlkID0gMzdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8qXHJcblx0TUlUIExpY2Vuc2UgaHR0cDovL3d3dy5vcGVuc291cmNlLm9yZy9saWNlbnNlcy9taXQtbGljZW5zZS5waHBcclxuXHRBdXRob3IgVG9iaWFzIEtvcHBlcnMgQHNva3JhXHJcbiovXHJcbnZhciBzdHlsZXNJbkRvbSA9IHt9LFxyXG5cdG1lbW9pemUgPSBmdW5jdGlvbihmbikge1xyXG5cdFx0dmFyIG1lbW87XHJcblx0XHRyZXR1cm4gZnVuY3Rpb24gKCkge1xyXG5cdFx0XHRpZiAodHlwZW9mIG1lbW8gPT09IFwidW5kZWZpbmVkXCIpIG1lbW8gPSBmbi5hcHBseSh0aGlzLCBhcmd1bWVudHMpO1xyXG5cdFx0XHRyZXR1cm4gbWVtbztcclxuXHRcdH07XHJcblx0fSxcclxuXHRpc09sZElFID0gbWVtb2l6ZShmdW5jdGlvbigpIHtcclxuXHRcdHJldHVybiAvbXNpZSBbNi05XVxcYi8udGVzdCh3aW5kb3cubmF2aWdhdG9yLnVzZXJBZ2VudC50b0xvd2VyQ2FzZSgpKTtcclxuXHR9KSxcclxuXHRnZXRIZWFkRWxlbWVudCA9IG1lbW9pemUoZnVuY3Rpb24gKCkge1xyXG5cdFx0cmV0dXJuIGRvY3VtZW50LmhlYWQgfHwgZG9jdW1lbnQuZ2V0RWxlbWVudHNCeVRhZ05hbWUoXCJoZWFkXCIpWzBdO1xyXG5cdH0pLFxyXG5cdHNpbmdsZXRvbkVsZW1lbnQgPSBudWxsLFxyXG5cdHNpbmdsZXRvbkNvdW50ZXIgPSAwLFxyXG5cdHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wID0gW107XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKGxpc3QsIG9wdGlvbnMpIHtcclxuXHRpZih0eXBlb2YgREVCVUcgIT09IFwidW5kZWZpbmVkXCIgJiYgREVCVUcpIHtcclxuXHRcdGlmKHR5cGVvZiBkb2N1bWVudCAhPT0gXCJvYmplY3RcIikgdGhyb3cgbmV3IEVycm9yKFwiVGhlIHN0eWxlLWxvYWRlciBjYW5ub3QgYmUgdXNlZCBpbiBhIG5vbi1icm93c2VyIGVudmlyb25tZW50XCIpO1xyXG5cdH1cclxuXHJcblx0b3B0aW9ucyA9IG9wdGlvbnMgfHwge307XHJcblx0Ly8gRm9yY2Ugc2luZ2xlLXRhZyBzb2x1dGlvbiBvbiBJRTYtOSwgd2hpY2ggaGFzIGEgaGFyZCBsaW1pdCBvbiB0aGUgIyBvZiA8c3R5bGU+XHJcblx0Ly8gdGFncyBpdCB3aWxsIGFsbG93IG9uIGEgcGFnZVxyXG5cdGlmICh0eXBlb2Ygb3B0aW9ucy5zaW5nbGV0b24gPT09IFwidW5kZWZpbmVkXCIpIG9wdGlvbnMuc2luZ2xldG9uID0gaXNPbGRJRSgpO1xyXG5cclxuXHQvLyBCeSBkZWZhdWx0LCBhZGQgPHN0eWxlPiB0YWdzIHRvIHRoZSBib3R0b20gb2YgPGhlYWQ+LlxyXG5cdGlmICh0eXBlb2Ygb3B0aW9ucy5pbnNlcnRBdCA9PT0gXCJ1bmRlZmluZWRcIikgb3B0aW9ucy5pbnNlcnRBdCA9IFwiYm90dG9tXCI7XHJcblxyXG5cdHZhciBzdHlsZXMgPSBsaXN0VG9TdHlsZXMobGlzdCk7XHJcblx0YWRkU3R5bGVzVG9Eb20oc3R5bGVzLCBvcHRpb25zKTtcclxuXHJcblx0cmV0dXJuIGZ1bmN0aW9uIHVwZGF0ZShuZXdMaXN0KSB7XHJcblx0XHR2YXIgbWF5UmVtb3ZlID0gW107XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgc3R5bGVzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBpdGVtID0gc3R5bGVzW2ldO1xyXG5cdFx0XHR2YXIgZG9tU3R5bGUgPSBzdHlsZXNJbkRvbVtpdGVtLmlkXTtcclxuXHRcdFx0ZG9tU3R5bGUucmVmcy0tO1xyXG5cdFx0XHRtYXlSZW1vdmUucHVzaChkb21TdHlsZSk7XHJcblx0XHR9XHJcblx0XHRpZihuZXdMaXN0KSB7XHJcblx0XHRcdHZhciBuZXdTdHlsZXMgPSBsaXN0VG9TdHlsZXMobmV3TGlzdCk7XHJcblx0XHRcdGFkZFN0eWxlc1RvRG9tKG5ld1N0eWxlcywgb3B0aW9ucyk7XHJcblx0XHR9XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgbWF5UmVtb3ZlLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBkb21TdHlsZSA9IG1heVJlbW92ZVtpXTtcclxuXHRcdFx0aWYoZG9tU3R5bGUucmVmcyA9PT0gMCkge1xyXG5cdFx0XHRcdGZvcih2YXIgaiA9IDA7IGogPCBkb21TdHlsZS5wYXJ0cy5sZW5ndGg7IGorKylcclxuXHRcdFx0XHRcdGRvbVN0eWxlLnBhcnRzW2pdKCk7XHJcblx0XHRcdFx0ZGVsZXRlIHN0eWxlc0luRG9tW2RvbVN0eWxlLmlkXTtcclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdH07XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGFkZFN0eWxlc1RvRG9tKHN0eWxlcywgb3B0aW9ucykge1xyXG5cdGZvcih2YXIgaSA9IDA7IGkgPCBzdHlsZXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdHZhciBpdGVtID0gc3R5bGVzW2ldO1xyXG5cdFx0dmFyIGRvbVN0eWxlID0gc3R5bGVzSW5Eb21baXRlbS5pZF07XHJcblx0XHRpZihkb21TdHlsZSkge1xyXG5cdFx0XHRkb21TdHlsZS5yZWZzKys7XHJcblx0XHRcdGZvcih2YXIgaiA9IDA7IGogPCBkb21TdHlsZS5wYXJ0cy5sZW5ndGg7IGorKykge1xyXG5cdFx0XHRcdGRvbVN0eWxlLnBhcnRzW2pdKGl0ZW0ucGFydHNbal0pO1xyXG5cdFx0XHR9XHJcblx0XHRcdGZvcig7IGogPCBpdGVtLnBhcnRzLmxlbmd0aDsgaisrKSB7XHJcblx0XHRcdFx0ZG9tU3R5bGUucGFydHMucHVzaChhZGRTdHlsZShpdGVtLnBhcnRzW2pdLCBvcHRpb25zKSk7XHJcblx0XHRcdH1cclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHZhciBwYXJ0cyA9IFtdO1xyXG5cdFx0XHRmb3IodmFyIGogPSAwOyBqIDwgaXRlbS5wYXJ0cy5sZW5ndGg7IGorKykge1xyXG5cdFx0XHRcdHBhcnRzLnB1c2goYWRkU3R5bGUoaXRlbS5wYXJ0c1tqXSwgb3B0aW9ucykpO1xyXG5cdFx0XHR9XHJcblx0XHRcdHN0eWxlc0luRG9tW2l0ZW0uaWRdID0ge2lkOiBpdGVtLmlkLCByZWZzOiAxLCBwYXJ0czogcGFydHN9O1xyXG5cdFx0fVxyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gbGlzdFRvU3R5bGVzKGxpc3QpIHtcclxuXHR2YXIgc3R5bGVzID0gW107XHJcblx0dmFyIG5ld1N0eWxlcyA9IHt9O1xyXG5cdGZvcih2YXIgaSA9IDA7IGkgPCBsaXN0Lmxlbmd0aDsgaSsrKSB7XHJcblx0XHR2YXIgaXRlbSA9IGxpc3RbaV07XHJcblx0XHR2YXIgaWQgPSBpdGVtWzBdO1xyXG5cdFx0dmFyIGNzcyA9IGl0ZW1bMV07XHJcblx0XHR2YXIgbWVkaWEgPSBpdGVtWzJdO1xyXG5cdFx0dmFyIHNvdXJjZU1hcCA9IGl0ZW1bM107XHJcblx0XHR2YXIgcGFydCA9IHtjc3M6IGNzcywgbWVkaWE6IG1lZGlhLCBzb3VyY2VNYXA6IHNvdXJjZU1hcH07XHJcblx0XHRpZighbmV3U3R5bGVzW2lkXSlcclxuXHRcdFx0c3R5bGVzLnB1c2gobmV3U3R5bGVzW2lkXSA9IHtpZDogaWQsIHBhcnRzOiBbcGFydF19KTtcclxuXHRcdGVsc2VcclxuXHRcdFx0bmV3U3R5bGVzW2lkXS5wYXJ0cy5wdXNoKHBhcnQpO1xyXG5cdH1cclxuXHRyZXR1cm4gc3R5bGVzO1xyXG59XHJcblxyXG5mdW5jdGlvbiBpbnNlcnRTdHlsZUVsZW1lbnQob3B0aW9ucywgc3R5bGVFbGVtZW50KSB7XHJcblx0dmFyIGhlYWQgPSBnZXRIZWFkRWxlbWVudCgpO1xyXG5cdHZhciBsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcCA9IHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wW3N0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLmxlbmd0aCAtIDFdO1xyXG5cdGlmIChvcHRpb25zLmluc2VydEF0ID09PSBcInRvcFwiKSB7XHJcblx0XHRpZighbGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3ApIHtcclxuXHRcdFx0aGVhZC5pbnNlcnRCZWZvcmUoc3R5bGVFbGVtZW50LCBoZWFkLmZpcnN0Q2hpbGQpO1xyXG5cdFx0fSBlbHNlIGlmKGxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wLm5leHRTaWJsaW5nKSB7XHJcblx0XHRcdGhlYWQuaW5zZXJ0QmVmb3JlKHN0eWxlRWxlbWVudCwgbGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3AubmV4dFNpYmxpbmcpO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0aGVhZC5hcHBlbmRDaGlsZChzdHlsZUVsZW1lbnQpO1xyXG5cdFx0fVxyXG5cdFx0c3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AucHVzaChzdHlsZUVsZW1lbnQpO1xyXG5cdH0gZWxzZSBpZiAob3B0aW9ucy5pbnNlcnRBdCA9PT0gXCJib3R0b21cIikge1xyXG5cdFx0aGVhZC5hcHBlbmRDaGlsZChzdHlsZUVsZW1lbnQpO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR0aHJvdyBuZXcgRXJyb3IoXCJJbnZhbGlkIHZhbHVlIGZvciBwYXJhbWV0ZXIgJ2luc2VydEF0Jy4gTXVzdCBiZSAndG9wJyBvciAnYm90dG9tJy5cIik7XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiByZW1vdmVTdHlsZUVsZW1lbnQoc3R5bGVFbGVtZW50KSB7XHJcblx0c3R5bGVFbGVtZW50LnBhcmVudE5vZGUucmVtb3ZlQ2hpbGQoc3R5bGVFbGVtZW50KTtcclxuXHR2YXIgaWR4ID0gc3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AuaW5kZXhPZihzdHlsZUVsZW1lbnQpO1xyXG5cdGlmKGlkeCA+PSAwKSB7XHJcblx0XHRzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5zcGxpY2UoaWR4LCAxKTtcclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGNyZWF0ZVN0eWxlRWxlbWVudChvcHRpb25zKSB7XHJcblx0dmFyIHN0eWxlRWxlbWVudCA9IGRvY3VtZW50LmNyZWF0ZUVsZW1lbnQoXCJzdHlsZVwiKTtcclxuXHRzdHlsZUVsZW1lbnQudHlwZSA9IFwidGV4dC9jc3NcIjtcclxuXHRpbnNlcnRTdHlsZUVsZW1lbnQob3B0aW9ucywgc3R5bGVFbGVtZW50KTtcclxuXHRyZXR1cm4gc3R5bGVFbGVtZW50O1xyXG59XHJcblxyXG5mdW5jdGlvbiBjcmVhdGVMaW5rRWxlbWVudChvcHRpb25zKSB7XHJcblx0dmFyIGxpbmtFbGVtZW50ID0gZG9jdW1lbnQuY3JlYXRlRWxlbWVudChcImxpbmtcIik7XHJcblx0bGlua0VsZW1lbnQucmVsID0gXCJzdHlsZXNoZWV0XCI7XHJcblx0aW5zZXJ0U3R5bGVFbGVtZW50KG9wdGlvbnMsIGxpbmtFbGVtZW50KTtcclxuXHRyZXR1cm4gbGlua0VsZW1lbnQ7XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGFkZFN0eWxlKG9iaiwgb3B0aW9ucykge1xyXG5cdHZhciBzdHlsZUVsZW1lbnQsIHVwZGF0ZSwgcmVtb3ZlO1xyXG5cclxuXHRpZiAob3B0aW9ucy5zaW5nbGV0b24pIHtcclxuXHRcdHZhciBzdHlsZUluZGV4ID0gc2luZ2xldG9uQ291bnRlcisrO1xyXG5cdFx0c3R5bGVFbGVtZW50ID0gc2luZ2xldG9uRWxlbWVudCB8fCAoc2luZ2xldG9uRWxlbWVudCA9IGNyZWF0ZVN0eWxlRWxlbWVudChvcHRpb25zKSk7XHJcblx0XHR1cGRhdGUgPSBhcHBseVRvU2luZ2xldG9uVGFnLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50LCBzdHlsZUluZGV4LCBmYWxzZSk7XHJcblx0XHRyZW1vdmUgPSBhcHBseVRvU2luZ2xldG9uVGFnLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50LCBzdHlsZUluZGV4LCB0cnVlKTtcclxuXHR9IGVsc2UgaWYob2JqLnNvdXJjZU1hcCAmJlxyXG5cdFx0dHlwZW9mIFVSTCA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgVVJMLmNyZWF0ZU9iamVjdFVSTCA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgVVJMLnJldm9rZU9iamVjdFVSTCA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgQmxvYiA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgYnRvYSA9PT0gXCJmdW5jdGlvblwiKSB7XHJcblx0XHRzdHlsZUVsZW1lbnQgPSBjcmVhdGVMaW5rRWxlbWVudChvcHRpb25zKTtcclxuXHRcdHVwZGF0ZSA9IHVwZGF0ZUxpbmsuYmluZChudWxsLCBzdHlsZUVsZW1lbnQpO1xyXG5cdFx0cmVtb3ZlID0gZnVuY3Rpb24oKSB7XHJcblx0XHRcdHJlbW92ZVN0eWxlRWxlbWVudChzdHlsZUVsZW1lbnQpO1xyXG5cdFx0XHRpZihzdHlsZUVsZW1lbnQuaHJlZilcclxuXHRcdFx0XHRVUkwucmV2b2tlT2JqZWN0VVJMKHN0eWxlRWxlbWVudC5ocmVmKTtcclxuXHRcdH07XHJcblx0fSBlbHNlIHtcclxuXHRcdHN0eWxlRWxlbWVudCA9IGNyZWF0ZVN0eWxlRWxlbWVudChvcHRpb25zKTtcclxuXHRcdHVwZGF0ZSA9IGFwcGx5VG9UYWcuYmluZChudWxsLCBzdHlsZUVsZW1lbnQpO1xyXG5cdFx0cmVtb3ZlID0gZnVuY3Rpb24oKSB7XHJcblx0XHRcdHJlbW92ZVN0eWxlRWxlbWVudChzdHlsZUVsZW1lbnQpO1xyXG5cdFx0fTtcclxuXHR9XHJcblxyXG5cdHVwZGF0ZShvYmopO1xyXG5cclxuXHRyZXR1cm4gZnVuY3Rpb24gdXBkYXRlU3R5bGUobmV3T2JqKSB7XHJcblx0XHRpZihuZXdPYmopIHtcclxuXHRcdFx0aWYobmV3T2JqLmNzcyA9PT0gb2JqLmNzcyAmJiBuZXdPYmoubWVkaWEgPT09IG9iai5tZWRpYSAmJiBuZXdPYmouc291cmNlTWFwID09PSBvYmouc291cmNlTWFwKVxyXG5cdFx0XHRcdHJldHVybjtcclxuXHRcdFx0dXBkYXRlKG9iaiA9IG5ld09iaik7XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHRyZW1vdmUoKTtcclxuXHRcdH1cclxuXHR9O1xyXG59XHJcblxyXG52YXIgcmVwbGFjZVRleHQgPSAoZnVuY3Rpb24gKCkge1xyXG5cdHZhciB0ZXh0U3RvcmUgPSBbXTtcclxuXHJcblx0cmV0dXJuIGZ1bmN0aW9uIChpbmRleCwgcmVwbGFjZW1lbnQpIHtcclxuXHRcdHRleHRTdG9yZVtpbmRleF0gPSByZXBsYWNlbWVudDtcclxuXHRcdHJldHVybiB0ZXh0U3RvcmUuZmlsdGVyKEJvb2xlYW4pLmpvaW4oJ1xcbicpO1xyXG5cdH07XHJcbn0pKCk7XHJcblxyXG5mdW5jdGlvbiBhcHBseVRvU2luZ2xldG9uVGFnKHN0eWxlRWxlbWVudCwgaW5kZXgsIHJlbW92ZSwgb2JqKSB7XHJcblx0dmFyIGNzcyA9IHJlbW92ZSA/IFwiXCIgOiBvYmouY3NzO1xyXG5cclxuXHRpZiAoc3R5bGVFbGVtZW50LnN0eWxlU2hlZXQpIHtcclxuXHRcdHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0LmNzc1RleHQgPSByZXBsYWNlVGV4dChpbmRleCwgY3NzKTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0dmFyIGNzc05vZGUgPSBkb2N1bWVudC5jcmVhdGVUZXh0Tm9kZShjc3MpO1xyXG5cdFx0dmFyIGNoaWxkTm9kZXMgPSBzdHlsZUVsZW1lbnQuY2hpbGROb2RlcztcclxuXHRcdGlmIChjaGlsZE5vZGVzW2luZGV4XSkgc3R5bGVFbGVtZW50LnJlbW92ZUNoaWxkKGNoaWxkTm9kZXNbaW5kZXhdKTtcclxuXHRcdGlmIChjaGlsZE5vZGVzLmxlbmd0aCkge1xyXG5cdFx0XHRzdHlsZUVsZW1lbnQuaW5zZXJ0QmVmb3JlKGNzc05vZGUsIGNoaWxkTm9kZXNbaW5kZXhdKTtcclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHN0eWxlRWxlbWVudC5hcHBlbmRDaGlsZChjc3NOb2RlKTtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGFwcGx5VG9UYWcoc3R5bGVFbGVtZW50LCBvYmopIHtcclxuXHR2YXIgY3NzID0gb2JqLmNzcztcclxuXHR2YXIgbWVkaWEgPSBvYmoubWVkaWE7XHJcblxyXG5cdGlmKG1lZGlhKSB7XHJcblx0XHRzdHlsZUVsZW1lbnQuc2V0QXR0cmlidXRlKFwibWVkaWFcIiwgbWVkaWEpXHJcblx0fVxyXG5cclxuXHRpZihzdHlsZUVsZW1lbnQuc3R5bGVTaGVldCkge1xyXG5cdFx0c3R5bGVFbGVtZW50LnN0eWxlU2hlZXQuY3NzVGV4dCA9IGNzcztcclxuXHR9IGVsc2Uge1xyXG5cdFx0d2hpbGUoc3R5bGVFbGVtZW50LmZpcnN0Q2hpbGQpIHtcclxuXHRcdFx0c3R5bGVFbGVtZW50LnJlbW92ZUNoaWxkKHN0eWxlRWxlbWVudC5maXJzdENoaWxkKTtcclxuXHRcdH1cclxuXHRcdHN0eWxlRWxlbWVudC5hcHBlbmRDaGlsZChkb2N1bWVudC5jcmVhdGVUZXh0Tm9kZShjc3MpKTtcclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIHVwZGF0ZUxpbmsobGlua0VsZW1lbnQsIG9iaikge1xyXG5cdHZhciBjc3MgPSBvYmouY3NzO1xyXG5cdHZhciBzb3VyY2VNYXAgPSBvYmouc291cmNlTWFwO1xyXG5cclxuXHRpZihzb3VyY2VNYXApIHtcclxuXHRcdC8vIGh0dHA6Ly9zdGFja292ZXJmbG93LmNvbS9hLzI2NjAzODc1XHJcblx0XHRjc3MgKz0gXCJcXG4vKiMgc291cmNlTWFwcGluZ1VSTD1kYXRhOmFwcGxpY2F0aW9uL2pzb247YmFzZTY0LFwiICsgYnRvYSh1bmVzY2FwZShlbmNvZGVVUklDb21wb25lbnQoSlNPTi5zdHJpbmdpZnkoc291cmNlTWFwKSkpKSArIFwiICovXCI7XHJcblx0fVxyXG5cclxuXHR2YXIgYmxvYiA9IG5ldyBCbG9iKFtjc3NdLCB7IHR5cGU6IFwidGV4dC9jc3NcIiB9KTtcclxuXHJcblx0dmFyIG9sZFNyYyA9IGxpbmtFbGVtZW50LmhyZWY7XHJcblxyXG5cdGxpbmtFbGVtZW50LmhyZWYgPSBVUkwuY3JlYXRlT2JqZWN0VVJMKGJsb2IpO1xyXG5cclxuXHRpZihvbGRTcmMpXHJcblx0XHRVUkwucmV2b2tlT2JqZWN0VVJMKG9sZFNyYyk7XHJcbn1cclxuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1xuICoqIG1vZHVsZSBpZCA9IDM4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Zhdm91cml0ZXMuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9mYXZvdXJpdGVzLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9mYXZvdXJpdGVzLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXG4gKiogbW9kdWxlIGlkID0gMzlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwiQGNoYXJzZXQgXFxcIlVURi04XFxcIjtcXG4jZmF2b3VyaXRlcyB7XFxuICB3aWR0aDogOTkwcHg7XFxuICBtYXJnaW46IDNweCBhdXRvIDA7IH1cXG4gICNmYXZvdXJpdGVzIC5yaWdodC1jb2x1bW4ge1xcbiAgICB3aWR0aDogNDkwcHg7XFxuICAgIGZsb2F0OiByaWdodDsgfVxcbiAgI2Zhdm91cml0ZXMgLmxlZnQtY29sdW1uIHtcXG4gICAgd2lkdGg6IDQ5MHB4O1xcbiAgICBmbG9hdDogbGVmdDsgfVxcblxcbi5idWxsZXRpbi1zaG9ydCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBwYWRkaW5nOiAyMHB4IDE2cHg7XFxuICB3aWR0aDogNDkwcHg7XFxuICBtYXJnaW4tYm90dG9tOiA3cHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgLyog0JvQtdCy0LDRjyDQutC+0LvQvtC90LrQsCAqL1xcbiAgLyog0J/RgNCw0LLQsNGPINC60L7Qu9C+0L3QutCwICovIH1cXG4gIC5idWxsZXRpbi1zaG9ydCA+IGRpdiB7XFxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jazsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1yaWdodC1jb2x1bW4ge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIHdpZHRoOiA5MHB4O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1yaWdodC1jb2x1bW4gPiAub25Pck9mZkxpbmVVc2VyIHtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgdG9wOiA1cHg7XFxuICAgICAgbGVmdDogMTBweDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1jZW50ZXItY29sdW1uIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDE1cHg7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCBoMyB7XFxuICAgIG1hcmdpbjogMDtcXG4gICAgZm9udDogNDAwIDIwcHggLyAyNHB4IFJvYm90bztcXG4gICAgY29sb3I6ICMyMTIxMjE7XFxuICAgIHdpZHRoOiAyMDZweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAuYnVsbGV0aW4tc2hvcnQgaDM6aG92ZXIge1xcbiAgICAgIHRleHQtZGVjb3JhdGlvbjogdW5kZXJsaW5lO1xcbiAgICAgIC13ZWJraXQtdGV4dC1kZWNvcmF0aW9uLWNvbG9yOiBncmF5O1xcbiAgICAgICAgICAgICAgdGV4dC1kZWNvcmF0aW9uLWNvbG9yOiBncmF5O1xcbiAgICAgIC13ZWJraXQtdGV4dC1kZWNvcmF0aW9uLXN0eWxlOiBkYXNoZWQ7XFxuICAgICAgICAgICAgICB0ZXh0LWRlY29yYXRpb24tc3R5bGU6IGRhc2hlZDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1jYXRlZ29yeSB7XFxuICAgIGNvbG9yOiByZ2JhKDMxLCAzMSwgMzEsIDAuNTQpO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE0LjRweCBSb2JvdG87XFxuICAgIG1hcmdpbi10b3A6IDExcHg7XFxuICAgIG1hcmdpbi1ib3R0b206IDE2cHg7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tZGVzY3JpcHRpb24ge1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE4LjZweCBSb2JvdG87XFxuICAgIHdpZHRoOiAyNTRweDtcXG4gICAgY29sb3I6ICMwZDBkMWU7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4taW1hZ2Uge1xcbiAgICB3aWR0aDogOTBweDtcXG4gICAgaGVpZ2h0OiA5MXB4O1xcbiAgICBiYWNrZ3JvdW5kOiAjMTg3NUQwIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYnVsbGV0aW4tZGVmYXVsdC5wbmdcIikgKyBcIik7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tcHJpY2Uge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGJvdHRvbTogLTU0cHg7XFxuICAgIHJpZ2h0OiAwO1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDE0cHggLyAxNi44cHggUm9ib3RvOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWFjdGlvbiB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgYm90dG9tOiAyMHB4O1xcbiAgICByaWdodDogMTE3cHg7XFxuICAgIGNvbG9yOiAjMjEyMTIxO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE2LjhweCBSb2JvdG87IH1cXG4gIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGJvdHRvbTogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgei1pbmRleDogMTsgfVxcbiAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib24ge1xcbiAgICAgIHdpZHRoOiAxMDBweDtcXG4gICAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0Y1OTExRDtcXG4gICAgICB0ZXh0LWFsaWduOiBjZW50ZXI7IH1cXG4gICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib246YmVmb3JlIHtcXG4gICAgICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICBib3R0b206IC0xNHB4O1xcbiAgICAgICAgYm9yZGVyOiAxM3B4IHNvbGlkICNlNTdiMDA7XFxuICAgICAgICB6LWluZGV4OiAtMTtcXG4gICAgICAgIGxlZnQ6IC0yM3B4O1xcbiAgICAgICAgYm9yZGVyLXJpZ2h0LXdpZHRoOiAxLjVlbTtcXG4gICAgICAgIGJvcmRlci1sZWZ0LWNvbG9yOiB0cmFuc3BhcmVudDtcXG4gICAgICAgIGJveC1zaGFkb3c6IDJweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7IH1cXG4gICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib246YWZ0ZXIge1xcbiAgICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgIGJvdHRvbTogMDtcXG4gICAgICAgIGJvcmRlcjogMTNweCBzb2xpZCAjRjU5MTFEO1xcbiAgICAgICAgcmlnaHQ6IC0xM3B4O1xcbiAgICAgICAgYm9yZGVyLWxlZnQtd2lkdGg6IDA7XFxuICAgICAgICBib3JkZXItcmlnaHQtY29sb3I6IHRyYW5zcGFyZW50OyB9XFxuICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uID4gLnJpYmJvbi1jb250ZW50IHtcXG4gICAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgICAgZm9udDogNDAwIDE0cHggLyAyNnB4IFJvYm90bztcXG4gICAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uID4gLnJpYmJvbi1jb250ZW50OmJlZm9yZSB7XFxuICAgICAgICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgICBib3JkZXItc3R5bGU6IHNvbGlkO1xcbiAgICAgICAgICBib3JkZXItY29sb3I6ICMyQjRBNjcgdHJhbnNwYXJlbnQgdHJhbnNwYXJlbnQgdHJhbnNwYXJlbnQ7XFxuICAgICAgICAgIGJvdHRvbTogLTE0cHg7XFxuICAgICAgICAgIGxlZnQ6IDA7XFxuICAgICAgICAgIGJvcmRlci13aWR0aDogMWVtIDAgMCAxZW07IH1cXG5cXG4uY2hlY2tCb3gge1xcbiAgd2lkdGg6IDI1cHg7XFxuICBoZWlnaHQ6IDI1cHg7XFxuICBib3JkZXI6IDFweCBzb2xpZCBncmV5O1xcbiAgYm9yZGVyLXJhZGl1czogNXB4O1xcbiAgY3Vyc29yOiBwb2ludGVyO1xcbiAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuXFxuLmNoZWNrZWQge1xcbiAgYmFja2dyb3VuZDogIzE4NzVEMCB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1YucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgYm9yZGVyLWNvbG9yOiAjMTg3NUQwICFpbXBvcnRhbnQ7IH1cXG5cXG5kaXYuZXhjbGFtYXRpb25Qb2ludCB7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2V4Y2xhbWF0aW9uUG9pbnQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgaGVpZ2h0OiAyN3B4O1xcbiAgd2lkdGg6IDI3cHg7XFxuICBtYXJnaW4tdG9wOiAxMHB4OyB9XFxuXFxuLmRvbGxhckJpbGwge1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9kb2xsYXJCaWxsLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGhlaWdodDogMjdweDtcXG4gIHdpZHRoOiAyN3B4O1xcbiAgbWFyZ2luLXRvcDogMTBweDsgfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0MFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9idWxsZXRpbi1kZWZhdWx0LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1YucG5nXG4gKiogbW9kdWxlIGlkID0gNDJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9leGNsYW1hdGlvblBvaW50LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0M1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2RvbGxhckJpbGwucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9kb2xsYXJCaWxsLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQ0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2VkaXQtcHJvZmlsZS5zY3NzXCIpO1xuaWYodHlwZW9mIGNvbnRlbnQgPT09ICdzdHJpbmcnKSBjb250ZW50ID0gW1ttb2R1bGUuaWQsIGNvbnRlbnQsICcnXV07XG4vLyBhZGQgdGhlIHN0eWxlcyB0byB0aGUgRE9NXG52YXIgdXBkYXRlID0gcmVxdWlyZShcIiEuLy4uL25vZGVfbW9kdWxlcy9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXCIpKGNvbnRlbnQsIHt9KTtcbmlmKGNvbnRlbnQubG9jYWxzKSBtb2R1bGUuZXhwb3J0cyA9IGNvbnRlbnQubG9jYWxzO1xuLy8gSG90IE1vZHVsZSBSZXBsYWNlbWVudFxuaWYobW9kdWxlLmhvdCkge1xuXHQvLyBXaGVuIHRoZSBzdHlsZXMgY2hhbmdlLCB1cGRhdGUgdGhlIDxzdHlsZT4gdGFnc1xuXHRpZighY29udGVudC5sb2NhbHMpIHtcblx0XHRtb2R1bGUuaG90LmFjY2VwdChcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2VkaXQtcHJvZmlsZS5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZWRpdC1wcm9maWxlLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvZWRpdC1wcm9maWxlLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0NVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiZXhwb3J0cyA9IG1vZHVsZS5leHBvcnRzID0gcmVxdWlyZShcIi4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXCIpKCk7XG4vLyBpbXBvcnRzXG5cblxuLy8gbW9kdWxlXG5leHBvcnRzLnB1c2goW21vZHVsZS5pZCwgXCJzZWN0aW9uLmVkaXRQcm9maWxlIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgd2lkdGg6IDExMDVweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gNDBweDtcXG4gIHBhZGRpbmc6IDY1cHggMCA0NXB4IDA7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIHtcXG4gICAgd2lkdGg6IDY1MHB4O1xcbiAgICBtYXJnaW46IDAgYXV0bzsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gaDIge1xcbiAgICAgIGZvbnQ6IDcwMCAyMnB4IFJvYm90bztcXG4gICAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLnNlbGVjdEJveCB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7XFxuICAgICAgbWFyZ2luLXRvcDogMTVweDtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgICAgcGFkZGluZy1sZWZ0OiA1cHg7IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvIHtcXG4gICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWU5ZTllO1xcbiAgICAgIHdpZHRoOiA0MTBweDtcXG4gICAgICBwYWRkaW5nLWJvdHRvbTogNXB4OyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvID4gaW5wdXQge1xcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgei1pbmRleDogLTE7IH1cXG4gICAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLmVkaXQtcHJvZmlsZS1mb3JtLWZvdG8gPiBwIHtcXG4gICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICBwYWRkaW5nLXRvcDogMTFweDtcXG4gICAgICAgIHBhZGRpbmctbGVmdDogNXB4O1xcbiAgICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvID4gLmJ0bi1ibHVlIHtcXG4gICAgICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgICAgICBoZWlnaHQ6IDMwcHg7XFxuICAgICAgICB3aWR0aDogODVweDtcXG4gICAgICAgIGZsb2F0OiByaWdodDtcXG4gICAgICAgIGxpbmUtaGVpZ2h0OiAzMHB4OyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgLmVkaXQtcHJvZmlsZS1mb3JtLWNvbnRhY3RzLWNvbnRhaW5lciAuaW5wdXRGb3JtIHtcXG4gICAgICB3aWR0aDogOTUlO1xcbiAgICAgIGRpc3BsYXk6IGlubGluZS1ibG9jazsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIC5lZGl0LXByb2ZpbGUtZm9ybS1jb250YWN0cy1jb250YWluZXIgYnV0dG9uIHtcXG4gICAgICB3aWR0aDogMTZweDtcXG4gICAgICBoZWlnaHQ6IDE2cHg7XFxuICAgICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgICAgYmFja2dyb3VuZC1zaXplOiBjb250YWluOyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgLnNvY2lhbC1saW5rLWNvbnRhaW5lciB7XFxuICAgICAgaGVpZ2h0OiBhdXRvOyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSAuc29jaWFsLWxpbmstY29udGFpbmVyIGRpdiB7XFxuICAgICAgICB3aWR0aDogMjRweDtcXG4gICAgICAgIGhlaWdodDogMjRweDtcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgbWFyZ2luLXJpZ2h0OiA0N3B4OyB9XFxuICBzZWN0aW9uLmVkaXRQcm9maWxlIC51cGxvYWRGaWxlRm9ybSB7XFxuICAgIHZpc2liaWxpdHk6IGhpZGRlbjsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlIC51cGxvYWRGaWxlRm9ybSBpbnB1dCB7XFxuICAgICAgd2lkdGg6IDBweDtcXG4gICAgICBoZWlnaHQ6IDBweDsgfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDQ2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQ3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYXZhdGFyLmpwZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYXZhdGFyLmpwZ1xuICoqIG1vZHVsZSBpZCA9IDQ4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL3Byb2ZpbGUuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwiLnZpZXctcHJvZmlsZS1jb250YWluZXIge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI2ZmZjtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBwYWRkaW5nOiA0MHB4IDEyN3B4IDMwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7XFxuICB3aWR0aDogMTEwM3B4O1xcbiAgZm9udDogNDAwIDE2cHgvMjRweCBSb2JvdG87XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpOyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBoMSB7XFxuICAgIHBhZGRpbmctYm90dG9tOiAxNnB4O1xcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgIGZvbnQ6IDQwMCAyMnB4LzI2cHggUm9ib3RvOyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDotbW96LXJlYWQtb25seSB7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7IH1cXG4gIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0OnJlYWQtb25seSB7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7IH1cXG4gICAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaW5wdXQ6LW1vei1yZWFkLW9ubHk6bm90KDpsYXN0LW9mLXR5cGUpIHtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0OnJlYWQtb25seTpub3QoOmxhc3Qtb2YtdHlwZSkge1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIC5zb2NpYWwtbGluay1jb250YWluZXIge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuXFxuLnByb2ZpbGUtY29udGFpbmVycy13cmFwIHtcXG4gIHdpZHRoOiA4NDlweDtcXG4gIG1hcmdpbjogMCBhdXRvOyB9XFxuXFxuLnByb2ZpbGUtbGVmdC1jb250YWluZXIsIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLWxlZnQsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1yaWdodCwgLnByb2ZpbGUtaW5mbyB7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXI6OmFmdGVyLCAucHJvZmlsZS1yaWdodC1jb250YWluZXI6OmFmdGVyLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tbGVmdDo6YWZ0ZXIsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1yaWdodDo6YWZ0ZXIsIC5wcm9maWxlLWluZm86OmFmdGVyIHtcXG4gICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlO1xcbiAgICBjbGVhcjogYm90aDsgfVxcblxcbi52aWV3LXByb2ZpbGU6OmFmdGVyLCAudmlldy1wcm9maWxlLWNvbnRhaW5lcjo6YWZ0ZXIge1xcbiAgY29udGVudDogXFxcIlxcXCI7XFxuICBkaXNwbGF5OiB0YWJsZTtcXG4gIGNsZWFyOiBib3RoOyB9XFxuXFxuLnByb2ZpbGUtbGVmdC1jb250YWluZXIge1xcbiAgd2lkdGg6IDE5MHB4O1xcbiAgcGFkZGluZzogMCA1MnB4OyB9XFxuICAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciAuYnRuLWJsdWUge1xcbiAgICB3aWR0aDogMTgwcHg7XFxuICAgIGhlaWdodDogMzZweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM2cHg7XFxuICAgIG1hcmdpbjogMCBhdXRvO1xcbiAgICBtYXJnaW4tdG9wOiA5cHg7IH1cXG4gIC5wcm9maWxlLWxlZnQtY29udGFpbmVyID4gLmh6LWNlbnRlcmluZy13cmFwcGVyID4gLnByb2ZpbGUtc2V0dGluZ3MgPiAuc2VsZWN0Qm94IHtcXG4gICAgZGlzcGxheTogYmxvY2s7XFxuICAgIG1hcmdpbi1ib3R0b206IDI1cHg7IH1cXG4gICAgLnByb2ZpbGUtbGVmdC1jb250YWluZXIgPiAuaHotY2VudGVyaW5nLXdyYXBwZXIgPiAucHJvZmlsZS1zZXR0aW5ncyA+IC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICBmb250OiA0MDAgMTZweCAvIDI0cHggUm9ib3RvOyB9XFxuXFxuLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyIHtcXG4gIHdpZHRoOiA0ODVweDtcXG4gIHBhZGRpbmctbGVmdDogMjBweDtcXG4gIGJvcmRlci1sZWZ0OiAxcHggc29saWQgI2ViZWJlYjsgfVxcbiAgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyID4gLndyYXBGb3JEaXYge1xcbiAgICB3aWR0aDogYXV0bztcXG4gICAgZmxvYXQ6IG5vbmU7XFxuICAgIG1hcmdpbi1ib3R0b206IDA7IH1cXG4gICAgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyID4gLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSB7XFxuICAgICAgd2lkdGg6IDUwJTsgfVxcbiAgICAgIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciA+IC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhIHtcXG4gICAgICAgIHdpZHRoOiBhdXRvOyB9XFxuICAgIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciA+IC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyB7XFxuICAgICAgd2lkdGg6IDUwMHB4OyB9XFxuXFxuLnByb2ZpbGUtaW5mby1zZWN0aW9uLWxlZnQsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1yaWdodCB7XFxuICB3aWR0aDogNTAlO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcblxcbi5wcm9maWxlLWF2YXRhciB7XFxuICB3aWR0aDogMTQ1cHg7XFxuICBoZWlnaHQ6IDIxNXB4O1xcbiAgbWFyZ2luOiAwIGF1dG8gNDBweDtcXG4gIHBhZGRpbmctYm90dG9tOiA1cHg7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2F2YXRhci5qcGdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICBiYWNrZ3JvdW5kLXNpemU6IGNvbnRhaW47XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuXFxuLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24gLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24tdGl0bGUge1xcbiAgY3Vyc29yOiBwb2ludGVyO1xcbiAgcGFkZGluZzogMTNweCAwOyB9XFxuICAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZTo6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuICAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZSBzcGFuIHtcXG4gICAgY29sb3I6ICM5MjkyOTI7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZSBkaXYge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nXCIpICsgXCIpO1xcbiAgICB3aWR0aDogMTNweDtcXG4gICAgaGVpZ2h0OiA3cHg7IH1cXG5cXG4uc29jaWFsLWxpbmstY29udGFpbmVyIGRpdiB7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICB3aWR0aDogMjRweDtcXG4gIGhlaWdodDogMjRweDtcXG4gIGZsb2F0OiBsZWZ0OyB9XFxuICAuc29jaWFsLWxpbmstY29udGFpbmVyIGRpdjo6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuICAuc29jaWFsLWxpbmstY29udGFpbmVyIGRpdjpub3QoOmxhc3Qtb2YtdHlwZSkge1xcbiAgICBtYXJnaW4tcmlnaHQ6IDIzcHg7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucyB7XFxuICBkaXNwbGF5OiBibG9jaztcXG4gIHdpZHRoOiAxMDAlO1xcbiAgbWFyZ2luOiAzMHB4IDA7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnM6OmFmdGVyIHtcXG4gICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlO1xcbiAgICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLW5hdiwgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudCB7XFxuICBib3JkZXI6IDFweCBzb2xpZCAjZWJlYmViO1xcbiAgb3ZlcmZsb3c6IGhpZGRlbjsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLW5hdiBkaXYge1xcbiAgY29sb3I6ICM5MjkyOTI7XFxuICB3aWR0aDogNTAlO1xcbiAgcGFkZGluZzogMjRweCAwO1xcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLW5hdiBkaXY6OmFmdGVyIHtcXG4gICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlO1xcbiAgICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLW5hdiAubmF2LWl0ZW0tc2VsZWN0ZWQge1xcbiAgY29sb3I6ICM3ZWFlZTA7XFxuICBib3JkZXItYm90dG9tOiAycHggc29saWQgI2ZmNTI1MjsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI2Y2ZjZmNjtcXG4gIGhlaWdodDogMTgwcHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIHtcXG4gIHdpZHRoOiAxMDAlO1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNlYmViZWI7XFxuICBoZWlnaHQ6IDEyM3B4O1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgb3ZlcmZsb3c6IGhpZGRlbjsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIC5jb250ZW50LWl0ZW0tbG9nbyB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgdG9wOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICB3aWR0aDogMjdweDtcXG4gICAgaGVpZ2h0OiAyN3B4O1xcbiAgICBwYWRkaW5nOiAwIDIzcHggMjNweCA3cHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvaWNvbl91c2VyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gICAgYmFja2dyb3VuZC1wb3NpdGlvbjogY2VudGVyO1xcbiAgICBtYXJnaW4tdG9wOiAyOHB4OyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS10ZXh0IHtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIHdpZHRoOiAzNzNweDtcXG4gICAgbWFyZ2luOiAyOHB4IDM4cHggMjRweCAwO1xcbiAgICBmb250OiA0MDAgMTJweC8xOHB4IFJvYm90bztcXG4gICAgY29sb3I6ICMwYzBjMWU7IH1cXG4gICAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIC5jb250ZW50LWl0ZW0tdGV4dDo6YWZ0ZXIge1xcbiAgICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICAgIGRpc3BsYXk6IHRhYmxlO1xcbiAgICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnByb2ZpbGUtaW5mbyB7XFxuICBtYXJnaW4tYm90dG9tOiAzMHB4OyB9XFxuXCIsIFwiXCJdKTtcblxuLy8gZXhwb3J0c1xuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vY3NzLWxvYWRlciEuL34vcG9zdGNzcy1sb2FkZXIhLi9+L3Nhc3MtbG9hZGVyIS4vc3R5bGVzL3Byb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDUwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FyZXRfYmxhY2sucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYXJldF9ibGFjay5wbmdcbiAqKiBtb2R1bGUgaWQgPSA1MVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2ljb25fdXNlci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2ljb25fdXNlci5wbmdcbiAqKiBtb2R1bGUgaWQgPSA1MlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmNvbnN0IGRlYnVnID0gcmVxdWlyZSgnLi4vZGF0YS9kZWJ1ZycpXG5cbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XG4gIGN0eC5ERUJVR19MRVZFTCA9IGRlYnVnLkRFQlVHXG5cbiAgY29uc29sZS5sb2cgPSAoZnVuY3Rpb24oKSB7XG4gICAgbGV0IGxvZyA9IGNvbnNvbGUubG9nXG4gICAgaWYoIGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5ERUJVRyApXG4gICAgICByZXR1cm4gbG9nXG4gICAgZWxzZSByZXR1cm4gKCkgPT4ge31cbiAgfS5iaW5kKGN0eCkpKClcblxuICBjb25zb2xlLmVycm9yID0gKGZ1bmN0aW9uKCkge1xuICAgIGxldCBlcnJvciA9IGNvbnNvbGUuZXJyb3JcbiAgICBpZiggY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLkRFQlVHIHx8IGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5PTkxZX0VSUk9SUyApXG4gICAgICByZXR1cm4gZXJyb3JcbiAgICBlbHNlIHJldHVybiAoKSA9PiB7fVxuICB9LmJpbmQoY3R4KSkoKVxuXG4gIGNvbnNvbGUuaW5mbyA9IChmdW5jdGlvbigpIHtcbiAgICBsZXQgaW5mbyA9IGNvbnNvbGUuaW5mb1xuICAgIGlmKCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuREVCVUcgfHwgY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLk9OTFlfRVJST1JTIClcbiAgICAgIHJldHVybiBpbmZvXG4gICAgZWxzZSByZXR1cm4gKCkgPT4ge31cbiAgfS5iaW5kKGN0eCkpKClcblxuICAvKiAg0JTQu9GPINGF0L7RhdC80YsgKi9cbiAgd2luZG93LmxzID0gXCJZb3UndmUgbWlzc2VkIGEgd2luZG93LCBsb2wgPSlcIlxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9sb2dnZXIuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IHtcblx0XCJERUJVR1wiOiAwLFxuXHRcIk9OTFlfRVJST1JTXCI6IDEsXG5cdFwiUFJPRFVDVElPTlwiOiAyXG59O1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9kYXRhL2RlYnVnLmpzb25cbiAqKiBtb2R1bGUgaWQgPSA1NFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmNvbnN0IE1PRFVMRVMgPSB7XG4gIFwiY2hlY2tib3hcIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9jaGVja2JveCcpLFxuICBcIm5pY2VCdXR0b25cIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9uaWNlQnV0dG9uJyksXG4gIFwidGV4dFwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL3RleHQnKSxcbiAgXCJzZWxlY3RCb3hcIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9zZWxlY3RCb3gnKSxcbiAgXCJ0ZXh0QXJlYVwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL3RleHRBcmVhJylcbn1cblxud2luZG93LmVlID0gcmVxdWlyZSgnLi9ldmVudHMnKVxuZWUuaW5pdCgpXG5cbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XG5cbm1vZHVsZS5leHBvcnRzLmluaXQgPSBhcHAgPT4ge1xuICBmb3IobGV0IGtleSBpbiBNT0RVTEVTKVxuICAgIGFwcC5kaXJlY3RpdmUoa2V5LCBNT0RVTEVTW2tleV0pXG5cbiAgcmV0dXJuIGFwcFxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvaW5kZXguanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHtcbiAgICByZXN0cmljdDogXCJFXCIsXG4gICAgcmVxdWlyZTogJ15uZ01vZGVsJyxcbiAgICBzY29wZToge1xuICAgICAgbmdNb2RlbDogXCI9XCJcbiAgICB9LFxuICAgIHRlbXBsYXRlOiBgPGRpdiBjbGFzcz1cImNoZWNrQm94XCI+PC9kaXY+YCxcbiAgICByZXBsYWNlOiB0cnVlLFxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQpIHtcbiAgICAgIGxldCBlbCA9ICRlbGVtZW50WzBdXG4gICAgICAvLy5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdjaGVja0JveCcpWzBdXG5cbiAgICAgIGlmKCRzY29wZS5uZ01vZGVsICYmICFlbC5jbGFzc0xpc3QuY29udGFpbnMoJ2NoZWNrZWQnKSlcbiAgICAgICAgZWwuY2xhc3NMaXN0LmFkZCgnY2hlY2tlZCcpXG4gICAgICBlbHNlIGlmKCEkc2NvcGUubmdNb2RlbCAmJiBlbC5jbGFzc0xpc3QuY29udGFpbnMoJ2NoZWNrZWQnKSlcbiAgICAgICAgZWwuY2xhc3NMaXN0LnJlbW92ZSgnY2hla2VkJylcblxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBlID0+IHtcbiAgICAgICAgZWwuY2xhc3NMaXN0LnRvZ2dsZSgnY2hlY2tlZCcpXG4gICAgICAgICRzY29wZS5uZ01vZGVsID0gJHNjb3BlLm5nTW9kZWwgPyAgZmFsc2UgOiB0cnVlXG4gICAgICB9KVxuICAgIH1cbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9jaGVja2JveC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4ge1xuICAgIHJlc3RyaWN0OiBcIkVcIixcbiAgICB0cmFuc2NsdWRlOiB0cnVlLFxuICAgIHNjb3BlIDoge1xuICAgICAgY2xhc3M6IFwiQFwiLFxuICAgICAgbmdDbGljazogXCImXCJcbiAgICB9LFxuICAgIHJlcGxhY2U6IHRydWUsXG4gICAgdGVtcGxhdGUgOiBgPGRpdiBjbGFzcz1cInt7IGNsYXNzIH19XCI+XG4gICAgICAgICAgICAgICAgICA8c3BhbiBjbGFzcz1cImlua1wiPjwvc3Bhbj5cbiAgICAgICAgICAgICAgICAgIDxuZy10cmFuc2NsdWRlIHN0eWxlPVwiZGlzcGxheTpibG9jazsgd2lkdGg6MTAwJTsgaGVpZ2h0OmluaGVyaXQ7XCI+PC9uZy10cmFuc2NsdWRlPlxuICAgICAgICAgICAgICAgIDwvZGl2PmAsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCkge1xuICAgICAgbGV0IG9uQ2xpY2sgPSBmdW5jdGlvbihlKSB7XG4gICAgICAgIGxldCBpbmsgPSB0aGlzLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2luaycpWzBdXG4gICAgICAgIGluay5jbGFzc0xpc3QucmVtb3ZlKCdhbmltYXRlJylcblxuICAgICAgICBsZXQgcmVjdCA9IHRoaXMuZ2V0Qm91bmRpbmdDbGllbnRSZWN0KClcblxuICAgICAgICBpZiggIWluay5jbGllbnRIZWlnaHQgJiYgIWluay5jbGllbnRXaWR0aCApIHtcbiAgICAgICAgICBsZXQgZCA9IE1hdGgubWF4KHRoaXMuY2xpZW50V2lkdGgsIHRoaXMuY2xpZW50SGVpZ2h0KVxuICAgICAgICAgIGluay5zdHlsZS5oZWlnaHQgPSBpbmsuc3R5bGUud2lkdGggPSBgJHtkfXB4YFxuICAgICAgICB9XG5cbiAgICAgICAgaW5rLnN0eWxlLnRvcCA9IGAke2UuY2xpZW50WSAtIHJlY3QudG9wIC0gaW5rLmNsaWVudEhlaWdodC8yfXB4YFxuICAgICAgICBpbmsuc3R5bGUubGVmdCA9IGAke2UuY2xpZW50WCAtIHJlY3QubGVmdCAtaW5rLmNsaWVudFdpZHRoLzJ9cHhgXG4gICAgICAgIGluay5jbGFzc0xpc3QuYWRkKCdhbmltYXRlJylcbiAgICAgIH1cblxuICAgICAgaWYoJHNjb3BlLm5nQ2xpY2spXG4gICAgICAgICRlbGVtZW50WzBdLmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgJHNjb3BlLm5nQ2xpY2spXG4gICAgICAgIFxuICAgICAgJGVsZW1lbnRbMF0uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBvbkNsaWNrKVxuICAgIH1cbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9uaWNlQnV0dG9uLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmNvbnN0IENPTE9SUyA9IHtcbiAgICBibHVlOiBcIiMxODc1RDBcIixcbiAgICB3aGl0ZTogXCJ3aGl0ZVwiXG59XG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XG4gIHJldHVybiB7XG4gICAgcmVzdHJpY3Q6IFwiRVwiLFxuICAgIHJlcXVpcmU6ICdebmdNb2RlbCcsXG4gICAgc2NvcGUgOiB7XG4gICAgICBsYWJlbDogXCJAXCIsXG4gICAgICBuZ01vZGVsOiBcIj1cIixcbiAgICAgIGNvbG9yOiBcIkBcIixcbiAgICAgIHR5cGU6IFwiQFwiLFxuICAgICAgdmFsaWRhdGU6IFwiPVwiLFxuICAgICAgaXNWYWxpZDogXCI9XCJcbiAgICB9LFxuICAgIHJlcGxhY2U6IHRydWUsXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiaW5wdXRGb3JtXCI+XG4gICAgICAgICAgICAgICAgIDxsYWJlbD57eyBsYWJlbCB9fTwvbGFiZWw+XG4gICAgICAgICAgICAgICAgIDxpbnB1dCB0eXBlPVwie3sgdHlwZSB8fCAndGV4dCd9fVwiIG5nLW1vZGVsPVwibmdNb2RlbFwiPlxuICAgICAgICAgICAgICAgICA8ZGl2IGNsYXNzPVwiZXJyb3JzXCI+PC9kaXY+XG4gICAgICAgICAgICAgICA8L2Rpdj5gLFxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQsICR0aW1lb3V0KSB7XG4gICAgICBsZXQgaWQgPSBlZS5vbignZm9ybS1zdWJtaXQnLCB2YWxpZGF0ZSlcbiAgICAgICRzY29wZS4kb24oXCIkZGVzdHJveVwiLCBmdW5jdGlvbigpIHtcbiAgICAgICAgZWUub2ZmKGlkKVxuICAgICAgfS5iaW5kKHRoaXMpKVxuXG4gICAgICBsZXQgZGVmYXVsdEJvcmRlciA9IFwiXCJcblxuICAgICAgbGV0IGVsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeVRhZ05hbWUoJ2lucHV0JylbMF0sXG4gICAgICAgICAgbGFiZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgnbGFiZWwnKVswXSxcbiAgICAgICAgICBlcnJvciA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2Vycm9ycycpWzBdXG5cblxuICAgICAgZnVuY3Rpb24gdmFsaWRhdGUoKSB7XG4gICAgICAgIGlmKCRzY29wZS52YWxpZGF0ZSkge1xuICAgICAgICAgIGZ1bmN0aW9uIGhhbmRsZShlcnJvcikge1xuICAgICAgICAgICAgaWYodHlwZW9mICRzY29wZS5pc1ZhbGlkICE9PSBcInVuZGVmaW5lZFwiKSB7XG4gICAgICAgICAgICAgIGlmKGVycm9yLmxlbmd0aClcbiAgICAgICAgICAgICAgICAkc2NvcGUuaXNWYWxpZCA9IGZhbHNlXG4gICAgICAgICAgICAgIGVsc2VcbiAgICAgICAgICAgICAgICAkc2NvcGUuaXNWYWxpZCA9IHRydWVcblxuICAgICAgICAgICAgICAkc2NvcGUuJGFwcGx5KClcbiAgICAgICAgICAgIH1cbiAgICAgICAgICB9XG5cbiAgICAgICAgICBsZXQgcmVzcCA9ICRzY29wZS52YWxpZGF0ZShlbC52YWx1ZSlcblxuICAgICAgICAgIGlmKCB0eXBlb2YgcmVzcCA9PT0gXCJzdHJpbmdcIilcbiAgICAgICAgICAgIGhhbmRsZSggZXJyb3IuaW5uZXJIVE1MID0gcmVzcClcbiAgICAgICAgICBlbHNlXG4gICAgICAgICAgICByZXNwLnRoZW4oIGZ1bmN0aW9uKGRhdGEpIHtcbiAgICAgICAgICAgICAgICBlcnJvci5pbm5lckhUTUwgPSBkYXRhXG4gICAgICAgICAgICAgICAgaGFuZGxlKGRhdGEpXG4gICAgICAgICAgICAgIH0sIGNvbnNvbGUuZXJyb3IpXG5cblxuICAgICAgICB9XG4gICAgICB9XG5cbiAgICAgIGZ1bmN0aW9uIG9uQmx1cihlKSB7XG4gICAgICAgIGlmKCAhJHNjb3BlLm5nTW9kZWwubGVuZ3RoKVxuICAgICAgICAgIGhpZGVBbmltYXRpb24oKVxuXG4gICAgICAgICAgdmFsaWRhdGUoKVxuICAgICAgfVxuXG4gICAgICBmdW5jdGlvbiBvbkZvY3VzKGUpIHtcbiAgICAgICAgaWYoISRzY29wZS5uZ01vZGVsLmxlbmd0aClcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gZGlzcGxheUFuaW1hdGlvbigpIHtcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBDT0xPUlNbJHNjb3BlLmNvbG9yXVxuICAgICAgICBpZighZGVmYXVsdEJvcmRlci5sZW5ndGgpIHtcbiAgICAgICAgICBkZWZhdWx0Qm9yZGVyID0gd2luZG93LmdldENvbXB1dGVkU3R5bGUobGFiZWwucGFyZW50Tm9kZSkuYm9yZGVyQm90dG9tXG4gICAgICAgIH0gZWxzZSB7XG4gICAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBgMnB4IHNvbGlkICR7Q09MT1JTWyRzY29wZS5jb2xvcl19YFxuICAgICAgICB9XG5cbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LmFkZCgndGV4dE91dCcpXG4gICAgICB9XG5cbiAgICAgIGZ1bmN0aW9uIGhpZGVBbmltYXRpb24oKSB7XG4gICAgICAgIGxhYmVsLnN0eWxlLmNvbG9yID0gXCJcIlxuICAgICAgICBsYWJlbC5wYXJlbnROb2RlLnN0eWxlLmJvcmRlckJvdHRvbSA9IGRlZmF1bHRCb3JkZXJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LnJlbW92ZSgndGV4dE91dCcpXG4gICAgICB9XG5cbiAgICAgICR0aW1lb3V0KCAoKSA9PiB7XG4gICAgICAgIGlmKCAkc2NvcGUubmdNb2RlbCAmJiAkc2NvcGUubmdNb2RlbC5sZW5ndGggKVxuICAgICAgICAgIGRpc3BsYXlBbmltYXRpb24oKVxuICAgICAgICBlbHNlXG4gICAgICAgICAgaGlkZUFuaW1hdGlvbigpXG4gICAgICB9LCAyNTApXG5cblxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignYmx1cicsIG9uQmx1ci5iaW5kKHRoaXMpKVxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignZm9jdXMnLCBvbkZvY3VzLmJpbmQoJHNjb3BlKSlcbiAgICB9XG4gIH1cbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dC5qc1xuICoqLyIsIlwidXNlIHN0cmljdFwiXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XG4gIHJldHVybiB7XG4gICAgcmVzdHJpY3Q6IFwiRVwiLFxuICAgIHJlcXVpcmU6ICdebmdNb2RlbCcsXG4gICAgc2NvcGU6IHtcbiAgICAgIG5nTW9kZWw6IFwiPVwiLFxuICAgICAgc2hvdzogXCJAXCJcbiAgICB9LFxuICAgIHRlbXBsYXRlOiBgPGRpdiBjbGFzcz1cInNlbGVjdEJveFwiPlxuICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJkZWZhdWx0VmFsdWVcIiBuZy1oaWRlPVwic2hvd1wiPlxuICAgICAgICAgICAgICAgICAgPHA+e3sgbmdNb2RlbCB9fTwvcD5cbiAgICAgICAgICAgICAgICA8L2Rpdj5cbiAgICAgICAgICAgICAgICA8ZGl2IGNsYXNzPVwibGlzdE9mVmFsdWVzXCIgbmctc2hvdz1cInNob3dcIj5cbiAgICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJsaXN0SXRlbVwiIG5nLXJlcGVhdD1cIml0ZW0gaW4gaXRlbXNcIiB2YWx1ZT1cInt7aXRlbX19XCI+e3sgaXRlbSB9fTwvZGl2PlxuICAgICAgICAgICAgICAgIDwvZGl2PlxuICAgICAgICAgICAgICA8L2Rpdj5gLFxuICAgIHJlcGxhY2U6IHRydWUsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCwgJHRpbWVvdXQpIHtcbiAgICAgIGxldCBkZWZhdWx0VmFsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnZGVmYXVsdFZhbHVlJylbMF0sXG4gICAgICAgICAgbGlzdE9mVmFsdWVzID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnbGlzdE9mVmFsdWVzJylbMF1cblxuICAgICAgJHRpbWVvdXQoZnVuY3Rpb24oKSB7XG4gICAgICAgIGRlZmF1bHRWYWwuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBmdW5jdGlvbihlKSB7XG4gICAgICAgICAgdGhpcy5zaG93ID0gdHJ1ZVxuXG4gICAgICAgICAgZG9jdW1lbnQuYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBoYW5kbGVyKVxuICAgICAgICB9LmJpbmQoJHNjb3BlKSlcblxuICAgICAgICBmdW5jdGlvbiBoYW5kbGVyKGUpIHtcbiAgICAgICAgICBpZiggIShlLnRhcmdldCA9PSBsaXN0T2ZWYWx1ZXMgfHxcbiAgICAgICAgICAgICAgICBlLnRhcmdldC5wYXJlbnROb2RlID09IGxpc3RPZlZhbHVlcyB8fFxuICAgICAgICAgICAgICAgIGUudGFyZ2V0ID09IGRlZmF1bHRWYWwgfHxcbiAgICAgICAgICAgICAgICBlLnRhcmdldC5wYXJlbnROb2RlID09IGRlZmF1bHRWYWwpICkge1xuICAgICAgICAgICAgJHNjb3BlLnNob3cgPSBmYWxzZVxuICAgICAgICAgICAgJHNjb3BlLiRhcHBseSgpXG4gICAgICAgICAgICBkb2N1bWVudC5yZW1vdmVFdmVudExpc3RlbmVyKCdjbGljaycsIGhhbmRsZXIpXG4gICAgICAgICAgfVxuICAgICAgICB9XG5cbiAgICAgICAgZm9yKGxldCB0PTA7dDxsaXN0T2ZWYWx1ZXMuY2hpbGRyZW4ubGVuZ3RoOyB0KyspIHtcblxuICAgICAgICAgIGxpc3RPZlZhbHVlcy5jaGlsZHJlblt0XS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGZ1bmN0aW9uKGUpIHtcbiAgICAgICAgICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcbiAgICAgICAgICAgIHRoaXMuc2hvdyA9IGZhbHNlXG4gICAgICAgICAgICB0aGlzLm5nTW9kZWwgPSBlLnRhcmdldC5pbm5lckhUTUxcbiAgICAgICAgICB9LmJpbmQoJHNjb3BlKSlcbiAgICAgICAgfVxuICAgICAgfS5iaW5kKHRoaXMpLCAxMDAwKVxuICAgIH0sXG4gICAgbGluazogZnVuY3Rpb24oc2NvcGUsIGVsZW1lbnQsIGF0dHJzKSB7XG4gICAgICBzY29wZS5pdGVtcyA9IEpTT04ucGFyc2UoYXR0cnMuaXRlbXMpXG4gICAgfVxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL3NlbGVjdEJveC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5sZXQgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxuXG5sZXQgcHJpdmF0ZVNjb3BlID0ge31cblxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGZ1bmN0aW9uKCkge1xuICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVycyA9IHt9XG4gIHByaXZhdGVTY29wZS5oYW5kbGVySWQgPSAwXG5cbiAgcHJpdmF0ZVNjb3BlLmdldEhhbmRsZXJJZCA9IGZ1bmN0aW9uKCkge1xuICAgIHJldHVybiBwcml2YXRlU2NvcGUuaGFuZGxlcklkKytcbiAgfVxuXG4gIHByaXZhdGVTY29wZS5yZWdpc3RlckhhbmRsZXIgPSBmdW5jdGlvbihuYW1lLCBoYW5kbGVyKSB7XG4gICAgaWYoIXByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW25hbWVdKVxuICAgICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0gPSBbXVxuXG4gICAgbGV0IGlkID0gcHJpdmF0ZVNjb3BlLmdldEhhbmRsZXJJZCgpXG4gICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0ucHVzaCh7XG4gICAgICBpZCA6IGlkLFxuICAgICAgaGFuZGxlciA6IGhhbmRsZXJcbiAgICB9KVxuXG4gICAgcmV0dXJuIGlkXG4gIH1cblxuICBwcml2YXRlU2NvcGUuaGFuZGxlID0gZnVuY3Rpb24oZXZlbnROYW1lLCBkYXRhKSB7XG4gICAgaWYocHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbZXZlbnROYW1lXSlcbiAgICAgIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2V2ZW50TmFtZV0uZm9yRWFjaChoID0+IGguaGFuZGxlcihkYXRhKSlcbiAgfVxuXG4gIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyID0gZnVuY3Rpb24oaWQpIHtcbiAgICBmb3IobGV0IGtleSBpbiBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVycykge1xuICAgICAgZm9yKGxldCB0ID0wOyB0PCBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldLmxlbmd0aDsgdCsrKSB7XG4gICAgICAgIGlmKHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV1bdF0uaWQgPT0gaWQpIHtcbiAgICAgICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldID0gcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNba2V5XS5maWx0ZXIoZWwgPT4gZWwuaWQgIT09IGlkKVxuICAgICAgICAgIHJldHVybiB0cnVlXG4gICAgICAgIH1cbiAgICAgIH1cbiAgICB9XG5cbiAgICByZXR1cm4gZmFsc2VcbiAgfVxuXG4gIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyQnlOYW1lID0gZnVuY3Rpb24obmFtZSkge1xuICAgIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW25hbWVdID0gW11cbiAgfVxufVxuXG5cbm1vZHVsZS5leHBvcnRzLm9uID0gZnVuY3Rpb24oZXZlbnROYW1lLCBoYW5kbGVyKSB7XG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVnaXN0ZXJIYW5kbGVyKGV2ZW50TmFtZSwgaGFuZGxlcilcbn1cblxuLyogUmVtb3ZlcyBoYW5kbGVyIGJ5IGlkKi9cbm1vZHVsZS5leHBvcnRzLm9mZiA9IGZ1bmN0aW9uKGlkKSB7XG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlcihpZClcbn1cblxuLyogUmVtb3ZlcyBhbGwgaGFuZGxlcnMgYnkgZXZlbnQgbmFtZSAqL1xubW9kdWxlLmV4cG9ydHMucmVtb3ZlID0gZnVuY3Rpb24obmFtZSkge1xuICByZXR1cm4gcHJpdmF0ZVNjb3BlLnJlbW92ZUhhbmRsZXJCeU5hbWUobmFtZSlcbn1cbi8qXG4gIHtcbiAgICBcIm5hbWVcIiA6IFwiZm9ybS1zdWJtaXRcIixcbiAgICBcImRhdGFcIiA6IFwid2hhdGV2ZXJcIiA8PT0gb3B0aW9uYWxcbiAgfVxuKi9cbm1vZHVsZS5leHBvcnRzLmVtaXQgPSBmdW5jdGlvbihldmVudCkge1xuICBsZXQgbmFtZSA9IGV2ZW50Lm5hbWUgfHwgKCgpID0+IHsgdGhyb3cgbmV3IEVycm9yKFwiTm8gZXZlbnQgbmFtZVwiKSB9KSgpXG4gIGxldCBkYXRhID0gZXZlbnQuZGF0YSB8fCBudWxsXG5cbiAgcHJpdmF0ZVNjb3BlLmhhbmRsZShuYW1lLCBkYXRhKVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZXZlbnRzLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbm1vZHVsZS5leHBvcnRzID0ge1xuICBcIi9cIiA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9pbmRleC5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvaW5kZXgnKSxcbiAgICBjb250cm9sbGVyQXM6IFwiaW5kZXhcIlxuICB9LFxuICAnLzQwMycgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvZXJyb3I0MDMuaHRtbFwiXG4gIH0sXG4gICcvNDA0JyA6IHtcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9lcnJvcjQwNC5odG1sXCJcbiAgfSxcbiAgJy81MDAnIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2Vycm9yNTAwLmh0bWxcIlxuICB9LFxuICAnL2J1bGxldGluRGV0YWlscycgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYnVsbGV0aW5EZXRhaWxzLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMnKSxcbiAgICBjb250cm9sbGVyQXM6IFwiYmRldGFpbGVkXCJcbiAgfSxcbiAgJy9idWxsZXRpbkFkZCcgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9idWxsZXRpbkFkZC5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9idWxsZXRpbkFkZCcpLFxuICAgIGNvbnRyb2xsZXJBczogXCJiYWRkXCJcbiAgfSxcbiAgJy9sb2dpbicgOiB7XG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL2xvZ2luLmh0bWxcIixcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9sb2dpbicpLFxuICAgIGNvbnRyb2xsZXJBczogXCJsb2dpblwiXG4gIH0sXG4gICcvcmVnaXN0ZXInIDoge1xuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9yZWdpc3Rlci5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvcmVnaXN0ZXInKSxcbiAgICBjb250cm9sbGVyQXM6IFwicmVnaXN0ZXJcIlxuICB9LFxuICAnL2VkaXRQcm9maWxlJyA6IHtcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9lZGl0LXByb2ZpbGUuaHRtbFwiLFxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZWRpdFByb2ZpbGUnKSxcbiAgICBjb250cm9sbGVyQXM6IFwicHJvZmlsZVwiXG4gIH0sXG4gICcvcHJvZmlsZScgOiB7XG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL2F1dGhlbnRpY2F0ZWQvcHJvZmlsZS5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlJyksXG4gICAgY29udHJvbGxlckFzOiBcInByb2ZpbGVcIlxuICB9LFxuICAnL2Zhdm91cml0ZXMnIDoge1xuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcy5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9mYXZvdXJpdGVzJyksXG4gICAgY29udHJvbGxlckFzOiBcImZhdm91cml0ZVwiXG4gIH0sXG4gICcvc2VhcmNoUmVzdWx0cycgOiB7XG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvc2VhcmNoUmVzdWx0cy5odG1sXCIsXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvc2VhcmNoUmVzdWx0cycpLFxuICAgIGNvbnRyb2xsZXJBczogXCJzZWFyY2hSZXN1bHRzXCJcbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9yb3V0ZXIuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xuICBcblxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvaW5kZXguanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcbiAgXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcblxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9idWxsZXRpbkFkZC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xuXG4gIHRoaXMuaW5pdCA9ICgpID0+IHtcbiAgICB0aGlzLmRiID0gJHNjb3BlLiRwYXJlbnQuZGJcblxuICAgIHRoaXMuZW1haWwgPSBcIlwiXG4gICAgdGhpcy5wYXNzd29yZCA9IFwiXCJcblxuICAgIHRoaXMuZW1haWxWYWxpZCA9IHRydWVcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSB0cnVlXG5cbiAgICB0aGlzLmxvZ2luRXJyb3IgPSBcIlwiXG5cbiAgICB0aGlzLmhhbmRsZXIgPSBmdW5jdGlvbihlKSB7XG4gICAgICBpZihlLndoaWNoID09IDEzKSB0aGlzLnNlbmQuY2FsbCh0aGlzKVxuICAgIH0uYmluZCh0aGlzKVxuXG4gICAgZG9jdW1lbnQuYWRkRXZlbnRMaXN0ZW5lcigna2V5dXAnLCB0aGlzLmhhbmRsZXIpXG4gIH1cblxuICB0aGlzLmRlbGV0ZUxpc3RuZXJzID0gKCkgPT4ge1xuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxuICB9XG5cbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xuICAgIGVlLmVtaXQoeyBuYW1lIDogXCJmb3JtLXN1Ym1pdFwiIH0pXG4gICAgLypcbiAgICAgIC0gR2V0IGRhdGFcbiAgICAgIC0gVmFsaWRhdGVcbiAgICAgIC0gU2hvdyBlcnJvcnNcbiAgICAgIC0gb3JcbiAgICAgIC0gR290byBiZCBhbmQgc2VuZCBkYXRhXG4gICAgKi9cbiAgICAvLyB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcblxuICAgIGlmKCB0aGlzLmVtYWlsVmFsaWQgJiYgdGhpcy5wYXNzd29yZFZhbGlkICkge1xuICAgICAgdGhpcy5kYi5sb2dpbih7XG4gICAgICAgIFwiZW1haWxcIiA6IHRoaXMuZW1haWwsXG4gICAgICAgIFwicGFzc3dvcmRcIjogdGhpcy5wYXNzd29yZFxuICAgICAgfSwgKGVyciwgZGF0YSkgPT4ge1xuICAgICAgICBpZihlcnIpXG4gICAgICAgICAgJHNjb3BlLiRwYXJlbnQuZGlzcGxheUVycm9yKFwi0J7RiNC40LHQutCwINCw0LLRgtC+0YDQuNC30LDRhtC40LgsINC/0YDQvtCy0LXRgNGM0YLQtSDQstCw0YjQuCDQtNCw0L3QvdGL0LVcIilcbiAgICAgICAgZWxzZSB7XG4gICAgICAgICAgdGhpcy5kZWxldGVMaXN0bmVycygpXG4gICAgICAgICAgdGhpcy5kYi5zYXZlVXNlckRhdGEoZGF0YSlcbiAgICAgICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnL3Byb2ZpbGUnKVxuICAgICAgICB9XG4gICAgICB9KVxuICAgIH1cbiAgfVxuXG4gIHRoaXMuZW1haWxJc1ZhbGlkID0gZW1haWwgPT4ge1xuICAgIGxldCBlcnJvciA9IFwiXCJcbiAgICBpZighZW1haWwubGVuZ3RoKSAgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXG4gICAgaWYoIS9eKChbXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKFxcLltePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSspKil8KFwiLitcIikpQCgoXFxbWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfV0pfCgoW2EtekEtWlxcLTAtOV0rXFwuKStbYS16QS1aXXsyLH0pKSQvLnRlc3QoZW1haWwpKVxuICAgICAgZXJyb3IgKz0gXCLQndC10LLQtdGA0L3Ri9C5INGE0L7RgNC80LDRgiDQv9C+0YfRgtGLLiBcIlxuICAgIHJldHVybiBlcnJvclxuICB9XG5cbiAgdGhpcy5wYXNzd29yZElzVmFsaWQgPSBwd2QgPT4ge1xuICAgIGxldCBlcnJvciA9IFwiXCJcbiAgICBpZighcHdkLmxlbmd0aCkgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXG4gICAgaWYoIHB3ZC5sZW5ndGggPCA2KSBlcnJvciArPSBcItCf0LDRgNC+0LvRjCDQtNC+0LvQttC10L0g0YHQvtC00LXRgNC20LDRgtGMINC90LUg0LzQtdC90LXQtSA2INGB0LjQvNCy0L7Qu9C+0LIuIFwiXG4gICAgcmV0dXJuIGVycm9yXG4gIH1cbn1cblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2xvZ2luLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlLCAkcSkge1xuXG4gIHRoaXMuaW5pdCA9ICgpID0+IHtcbiAgICB0aGlzLmRiID0gJHNjb3BlLiRwYXJlbnQuZGJcblxuICAgIHRoaXMuZW1haWwgPSBcIlwiXG4gICAgdGhpcy5wYXNzd29yZCA9IFwiXCJcbiAgICB0aGlzLnBhc3N3b3JkMiA9IFwiXCJcblxuICAgIHRoaXMuZW1haWxWYWxpZCA9IFwiXCJcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSBcIlwiXG4gICAgdGhpcy5wYXNzd29yZDJWYWxpZCA9IFwiXCJcblxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcbiAgICAgIGlmKGUud2hpY2ggPT0gMTMpIHRoaXMuc2VuZC5jYWxsKHRoaXMpXG4gICAgfS5iaW5kKHRoaXMpXG5cbiAgICBkb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdrZXl1cCcsIHRoaXMuaGFuZGxlcilcbiAgfVxuXG4gIHRoaXMuZGVsZXRlTGlzdG5lcnMgPSAoKSA9PiB7XG4gICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcigna2V5dXAnLCB0aGlzLmhhbmRsZXIpXG4gIH1cblxuICB0aGlzLnNlbmQgPSAoKSA9PiB7XG4gICAgLypcbiAgICAgIC0gR2V0IGRhdGFcbiAgICAgIC0gVmFsaWRhdGVcbiAgICAgIC0gU2hvdyBlcnJvcnNcbiAgICAgIC0gb3JcbiAgICAgIC0gR290byBiZCBhbmQgc2VuZCBkYXRhXG4gICAgKi9cbiAgICBpZiggdGhpcy5lbWFpbFZhbGlkICYmIHRoaXMucGFzc3dvcmRWYWxpZCAmJiB0aGlzLnBhc3N3b3JkMlZhbGlkICkge1xuICAgICAgdGhpcy5kYi5sb2dpbih7XG4gICAgICAgIFwiZW1haWxcIiA6IHRoaXMuZW1haWwsXG4gICAgICAgIFwicGFzc3dvcmRcIjogdGhpcy5wYXNzd29yZFxuICAgICAgfSwgKGVyciwgZGF0YSkgPT4ge1xuICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcbiAgICAgICAgaWYoZXJyKVxuICAgICAgICAgICRzY29wZS4kcGFyZW50LnJlZGlyZWN0VG9VcmwoJy81MDAnKVxuICAgICAgICBlbHNlIHtcbiAgICAgICAgICAvKiBTYXZlIGRhdGEgdG8gZGIgKi9cbiAgICAgICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnL3Byb2ZpbGUnKVxuICAgICAgICAgIGNvbnNvbGUubG9nKGRhdGEpXG4gICAgICAgIH1cbiAgICAgIH0pXG4gICAgfVxuICB9XG5cbiAgdGhpcy5lbWFpbElzVmFsaWQgPSBmdW5jdGlvbihlbWFpbCkge1xuICAgIHJldHVybiAkcSggZnVuY3Rpb24ocmVzb2x2ZSwgcmVqZWN0KSB7XG4gICAgICBsZXQgZXJyb3IgPSBcIlwiXG4gICAgICBpZighZW1haWwubGVuZ3RoKSAgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXG4gICAgICBpZighL14oKFtePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSsoXFwuW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKykqKXwoXCIuK1wiKSlAKChcXFtbMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XSl8KChbYS16QS1aXFwtMC05XStcXC4pK1thLXpBLVpdezIsfSkpJC8udGVzdChlbWFpbCkpXG4gICAgICAgIGVycm9yICs9IFwi0J3QtdCy0LXRgNC90YvQuSDRhNC+0YDQvNCw0YIg0L/QvtGH0YLRiy4gXCJcblxuICAgICAgd2luZG93LmRiLmNoZWNrRW1haWwoZW1haWwsIGZ1bmN0aW9uKGVyciwgZGF0YSkge1xuICAgICAgICBpZihlcnIpIHJlamVjdChlcnIpXG4gICAgICAgIGVsc2Uge1xuICAgICAgICAgIGNvbnNvbGUubG9nKGRhdGEpXG4gICAgICAgICAgaWYoZGF0YSAhPT0gXCJmYWxzZVwiKVxuICAgICAgICAgICAgZXJyb3IgKz0gXCLQotCw0LrQsNGPINC/0L7Rh9GC0LAg0YPQttC1INC40YHQv9C+0LvRjNC30YPQtdGC0YHRjy4gXCJcbiAgICAgICAgICByZXNvbHZlKGVycm9yKVxuICAgICAgICB9XG4gICAgICB9LmJpbmQodGhpcykpXG4gICAgfS5iaW5kKHRoaXMpKVxuICB9XG5cbiAgdGhpcy5wYXNzd29yZElzVmFsaWQgPSBwd2QgPT4ge1xuICAgIGxldCBlcnJvciA9IFwiXCJcbiAgICBpZighcHdkLmxlbmd0aCkgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXG4gICAgaWYocHdkLmxlbmd0aCA8IDYpIGVycm9yICs9IFwi0J/QsNGA0L7Qu9GMINC00L7Qu9C20LXQvSDRgdC+0LTQtdGA0LbQsNGC0Ywg0L3QtSDQvNC10L3QtdC1IDYg0YHQuNC80LLQvtC70L7Qsi4gXCJcbiAgICByZXR1cm4gZXJyb3JcbiAgfVxuXG4gIHRoaXMucGFzc3dvcmQySXNWYWxpZCA9IHB3ZCA9PiB7XG4gICAgbGV0IGVycm9yID0gdGhpcy5wYXNzd29yZElzVmFsaWQocHdkKVxuICAgIGlmKHRoaXMucGFzc3dvcmQgIT09IHRoaXMucGFzc3dvcmQyICkgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Lgg0L3QtSDRgdC+0LLQv9Cw0LTQsNGO0YJcIlxuICAgIHJldHVybiBlcnJvclxuICB9XG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9yZWdpc3Rlci5qc1xuICoqLyIsIlwidXNlIHN0cmljdFwiO1xuXG5jbGFzcyBQcm9maWxlQ29udGFjdHtcbiAgICBjb25zdHJ1Y3RvcigpIHtcbiAgICAgICAgdGhpcy5jb250YWN0RW1haWxzID0gWycnXTtcbiAgICAgICAgdGhpcy5jb250YWN0UGhvbmVzID0gWycnXTtcbiAgICAgICAgdGhpcy50eXBlID0gXCJcIlxuICAgICAgICB0aGlzLnBvc2l0aW9uID0gXCJcIlxuICAgICAgICB0aGlzLmNvbXBhbnlOYW1lID0gXCJcIlxuICAgICAgICB0aGlzLnNreXBlVXNlck5hbWUgPSBcIlwiXG4gICAgICAgIHRoaXMubGlua1RvV2ViU2l0ZSA9IFwiXCJcbiAgICB9XG59XG5cbmNsYXNzIHByb2ZpbGVDdHJsIHtcbiAgICBjb25zdHJ1Y3Rvcigkc2NvcGUpe1xuLy8gICAgICAgIGlmKCEkc2NvcGUuJHBhcmVudC5kYi51c2VyKVxuLy8gICAgICAgICAgJHNjb3BlLiRwYXJlbnQucmVkaXJlY3RUb1VybCgnLzQwMycsIHRydWUpXG4vLyAgICAgICAgZWxzZVxuXHRcdFx0dGhpcy5jb250YWN0ID0gbmV3IFByb2ZpbGVDb250YWN0KCk7XG4gICAgICB0aGlzLmNvbnRhY3RUeXBlcyA9IFtcbiAgICAgICAgXCJMRUdBTF9FTlRJVFlcIixcbiAgICAgICAgXCJFTlRSRVBSRU5FVVJcIlxuICAgICAgXVxuXG4gICAgICB0aGlzLmVtYWlsID0gXCJcIlxuICAgICAgdGhpcy5maW8gPSBcIlwiXG4gICAgICB0aGlzLm1haW5QaG9uZU51bWJlciA9IFwiXCJcbiAgICB9XG4gICAgdXBkYXRlUHJvZmlsZSgpe1xuXG4gICAgfVxuICAgIGFkZENvbnRhY3RzKCRldmVudCwgdHlwZSl7XG4gICAgICAgIHZhciBhcnI7XG4gICAgICAgIGlmKHR5cGUgPT09ICdlbWFpbCcpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0RW1haWxzO1xuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzO1xuICAgICAgICBlbHNlIHJldHVybjtcblxuICAgICAgICBpZihhcnIubGVuZ3RoIDwgNSAmJiBhcnJbYXJyLmxlbmd0aCAtIDFdLnRyaW0oKSkgYXJyLnB1c2goJycpO1xuICAgIH1cblxuICAgIGRlbGV0ZUNvbnRhY3RzKCRldmVudCwgJGluZGV4LCB0eXBlKXtcbiAgICAgICAgaWYodHlwZSA9PT0gJ2VtYWlsJykgdGhpcy5jb250YWN0LmNvbnRhY3RFbWFpbHMuc3BsaWNlKCRpbmRleCwgMSk7XG4gICAgICAgIGVsc2UgaWYodHlwZSA9PT0gJ3Bob25lJykgdGhpcy5jb250YWN0LmNvbnRhY3RQaG9uZXMuc3BsaWNlKCRpbmRleCwgMSk7XG4gICAgICAgIGVsc2UgcmV0dXJuO1xuICAgIH1cbn1cblxubW9kdWxlLmV4cG9ydHMgPSBwcm9maWxlQ3RybDtcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZWRpdFByb2ZpbGUuanNcbiAqKi8iLCJcInVzZSBzdHJpY3RcIjtcblxuY2xhc3MgUHJvZmlsZUNvbnRhY3R7XG4gICAgY29uc3RydWN0b3IoKSB7XG4gICAgICAgIHRoaXMuY29udGFjdEVtYWlscyA9IFsnJ107XG4gICAgICAgIHRoaXMuY29udGFjdFBob25lcyA9IFsnJ107XG4gICAgfVxufVxuXG5jbGFzcyBwcm9maWxlQ3RybCB7XG4gICAgY29uc3RydWN0b3IoJHNjb3BlKXtcbiAgICAgICAgaWYoISRzY29wZS4kcGFyZW50LmRiLnVzZXIpXG4gICAgICAgICAgJHNjb3BlLiRwYXJlbnQucmVkaXJlY3RUb1VybCgnLzQwMycsIHRydWUpXG4gICAgICAgIGVsc2Uge1xuICAgICAgICAgIHRoaXMuY29udGFjdCA9IG5ldyBQcm9maWxlQ29udGFjdCgpO1xuICAgICAgICB9XG4gICAgfVxuICAgIHVwZGF0ZVByb2ZpbGUoKXtcblxuICAgIH1cbiAgICBhZGRDb250YWN0cygkZXZlbnQsIHR5cGUpe1xuICAgICAgICB2YXIgYXJyO1xuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcbiAgICAgICAgZWxzZSBpZih0eXBlID09PSAncGhvbmUnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdFBob25lcztcbiAgICAgICAgZWxzZSByZXR1cm47XG5cbiAgICAgICAgaWYoYXJyLmxlbmd0aCA8IDUgJiYgYXJyW2Fyci5sZW5ndGggLSAxXS50cmltKCkpIGFyci5wdXNoKCcnKTtcbiAgICB9XG5cbiAgICBkZWxldGVDb250YWN0cygkZXZlbnQsICRpbmRleCwgdHlwZSl7XG4gICAgICAgIGlmKHR5cGUgPT09ICdlbWFpbCcpIHRoaXMuY29udGFjdC5jb250YWN0RW1haWxzLnNwbGljZSgkaW5kZXgsIDEpO1xuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xuICAgICAgICBlbHNlIHJldHVybjtcbiAgICB9XG59XG5cbm1vZHVsZS5leHBvcnRzID0gcHJvZmlsZUN0cmw7XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcblxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkc2NvcGUpIHtcblxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9mYXZvdXJpdGVzLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XG4gIFxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvc2VhcmNoUmVzdWx0cy5qc1xuICoqLyIsIlwidXNlIHN0cmljdFwiXG5cbi8qINCa0L7QvdGC0YDQvtC70LvQtdGAINC00LvRjyDRg9C/0YDQsNCy0LvQtdC90LjRjyAg0L7RgdC90L7QstC90YvQvCDRgdC60LXQu9C10YLQvtC8INC00L7QutGD0LzQtdC90YLQsCAqL1xubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkaHR0cCwgJHNjb3BlLCAkbG9jYXRpb24sICR0aW1lb3V0KSB7XG4gIGNvbnNvbGUubG9nKCdNYWluIGNvbnRyb2xsZXIgbG9hZGVkJylcblxuICAvKiBTdGFuZGFsb25lIG1vZHVsZSBmb3IgYmQgKi9cbiAgJHNjb3BlLmRiID0gcmVxdWlyZSgnLi4vbW9kdWxlcy9kYicpXG4gICRzY29wZS5kYi5pbml0KClcbiAgd2luZG93LmRiID0gJHNjb3BlLmRiXG5cbiAgLyogSW5pdGlhbGl6ZSBkYXRhICovXG4gIHRoaXMuaW5pdCA9IGZ1bmN0aW9uKCkge1xuICAgIC8qIHZhcmlhYmxlcyBmb3IgdGVzdGluZyAqL1xuICAgIHRoaXMuaGVsbG89XCJoaVwiXG4gICAgdGhpcy5ib29sZWFuID0gdHJ1ZVxuICAgIHRoaXMubGlzdCA9IFsxLDIsM11cblxuICAgIGNvbnNvbGUubG9nKFwiTWFpbiBjb250cm9sbGVyIGluaXRcIilcblxuICAgIHRoaXMuc29ydGluZ0NhdGVnb3JpZXMgPSAocmVxdWlyZSgnLi4vZGF0YS9zb3J0aW5nJykpLml0ZW1zXG4gICAgdGhpcy5jdXJyZW50Q2F0ZWdvcnkgPSBcIk5vbmVcIlxuICAgIHRoaXMuc29ydGluZ0lkID0gMFxuXG4gICAgaWYodGhpcy5zb3J0aW5nQ2F0ZWdvcmllcy5sZW5ndGgpIHtcbiAgICAgIGxldCB0aXRsZSA9IHRoaXMuc29ydGluZ0NhdGVnb3JpZXNbdGhpcy5zb3J0aW5nSWRdLnRpdGxlXG4gICAgICBsZXQgYXJyID0gdGl0bGUuc3BsaXQoXCJcIilcbiAgICAgIHRoaXMuYXJyb3cgPSBhcnIucG9wKClcbiAgICAgIGFyci5wb3AoKVxuXG4gICAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IGFyci5qb2luKFwiXCIpXG4gICAgfVxuICAgIGVsc2UgY29uc29sZS5lcnJvcihuZXcgRXJyb3IoXCJObyBzb3J0aW5nIG9wdGlvbnMgZm91bmRcIikpXG5cbiAgICB0aGlzLnNob3dpbmdDYXRlZ29yaWVzID0gZmFsc2VcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXG4gIH1cblxuICB0aGlzLnNob3dDYXRlZ29yaWVzID0gKCkgPT4ge1xuICAgIHRoaXMuc2V0dGluZ0NhdCA9IHRydWVcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXG4gICAgJHRpbWVvdXQoICgpID0+IHtcbiAgICAgIHRoaXMuc2V0dGluZ0NhdCA9IGZhbHNlXG4gICAgfSwgMjUwKVxuXG4gICAgdGhpcy5zaG93aW5nQ2F0ZWdvcmllcyA9IHRydWVcbiAgfVxuXG4gIC8qIFNvcnRpbmcgaW4gaGVhZGVyICovXG4gIHRoaXMuc2V0Q2F0ZWdvcnkgPSBpZCA9PiB7XG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gZmFsc2VcblxuICAgIGxldCByZXMgPSB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzLmZpbHRlcihlbCA9PiBlbC5pZCA9PT0gaWQgfCAwKVswXVxuICAgIHRoaXMuc29ydGluZ0lkID0gaWRcblxuICAgIGlmKHJlcykge1xuICAgICAgbGV0IGFyciA9IHJlcy50aXRsZS5zcGxpdChcIlwiKVxuICAgICAgdGhpcy5hcnJvdyA9IGFyci5wb3AoKVxuICAgICAgYXJyLnBvcCgpXG5cbiAgICAgIHRoaXMuY3VycmVudENhdGVnb3J5ID0gYXJyLmpvaW4oXCJcIilcbiAgICB9XG5cbiAgfVxuXG4gIHRoaXMubG9nb3V0ID0gZnVuY3Rpb24oKSB7XG4gICAgICBkYi51c2VyTG9nb3V0KClcbiAgICAgICRzY29wZS5yZWRpcmVjdFRvVXJsKCcvJylcbiAgfVxuXG4gIC8qIFdhdGNoIGFsbCBjbGljayBvbiB0aGUgYm9keSAqL1xuICB0aGlzLmNsaWNrID0gKCkgPT4ge1xuICAgIGlmKHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgJiYgIXRoaXMuc2V0dGluZ0NhdClcbiAgICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSBmYWxzZVxuICB9XG5cblxuICAvKiBDb3JyZWN0IHJlZGlyZWN0IHRvIHVybCB0aHJvdWdoIGFwcCByb3V0ZXIqL1xuICAkc2NvcGUucmVkaXJlY3RUb1VybCA9ICh1cmwsIGltbWVkaWF0ZSkgPT4ge1xuICAgIGlmKGltbWVkaWF0ZSlcbiAgICAgICRsb2NhdGlvbi5wYXRoKHVybClcbiAgICBlbHNlXG4gICAgICAkdGltZW91dCgoKSA9PiB7XG4gICAgICAgICRsb2NhdGlvbi5wYXRoKHVybClcbiAgICAgIH0sIDI1MClcbiAgfVxuXG4gIC8qIFVzZSB0aGlzIG1ldGhvZCBmb3IgZ2xvYmFsIHB1cnBvc2UgZXJyb3JzICovXG4gICRzY29wZS5kaXNwbGF5RXJyb3IgPSB0ZXh0ID0+IHtcbiAgICBhbGVydCh0ZXh0KVxuICAgIGNvbnNvbGUuZXJyb3IobmV3IEVycm9yKFwidGV4dFwiKSlcbiAgfVxuXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9tYWluLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmxldCB1dGlscyA9IHJlcXVpcmUoJy4vdXRpbHMnKSxcbiAgICBjb25maWcgPSByZXF1aXJlKCcuLi9kYXRhL2NvbmZpZycpXG5cbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XG5cbm1vZHVsZS5leHBvcnRzLmluaXQgPSBmdW5jdGlvbigpIHtcbiAgLyogaW5pdCBkYXRhIGZyb20gZGF0YWJhc2UgaGVyZSAqL1xuICBjdHguc2V0RGVmYXVsdHMoKVxuICAvLyB0aGlzLnRyYW5zcG9ydCA9ICRodHRwXG4gIC8vIGNvbnNvbGUubG9nKHRoaXMudHJhbnNwb3J0KVxuICBjdHguY2hlY2tVc2VySXNMb2dnZWQoZnVuY3Rpb24oZXJyLCBkYXRhKSB7XG4gICAgaWYoZXJyKSBjb25zb2xlLmVycm9yKGVycilcbiAgICBlbHNlIHtcbiAgICAgIGlmKGRhdGEpIGN0eC5zYXZlVXNlckRhdGEoZGF0YSlcbiAgICAgIGVsc2UgY29uc29sZS5sb2coXCJVc2VyIGlzIG5vdCBsb2dnZWQgaW5cIilcbiAgICB9XG4gIH0uYmluZCh0aGlzKSlcblxuICBjb25zb2xlLmxvZyhcIkRhdGFiYXNlIGluaXRpYWxpemVkXCIpXG59XG5cbm1vZHVsZS5leHBvcnRzLnNldERlZmF1bHRzID0gZnVuY3Rpb24oKSB7XG4gIGNvbnNvbGUubG9nKFwiRGF0YWJhc2UgOjogZGVmYXVsdHMgc2V0XCIpXG5cbiAgY3R4LmZhdm91cml0ZXMgPSBudWxsXG4gIGN0eC5tYWlsYm94ID0gbnVsbFxuICBjdHgudXNlciA9IG51bGxcbiAgY3R4Lm5vdGlmaWNhdGlvbnMgPSB7IGhlbGxvIDogXCJwcmV2ZWRcIiB9XG59XG5cblxubW9kdWxlLmV4cG9ydHMuY2hlY2tFbWFpbCA9IGZ1bmN0aW9uKGVtYWlsLCBjYikge1xuICB1dGlscy5yZXF1ZXN0KHtcbiAgICBcIm1ldGhvZFwiIDogY29uZmlnLnJvdXRlcy5jaGVja0VtYWlsLm1ldGhvZCxcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5jaGVja0VtYWlsLnVybCxcbiAgICBcImRhdGFcIiA6IGVtYWlsLFxuICAgIFwiaGVhZGVyc1wiIDoge1xuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwidGV4dC9wbGFpblwiXG4gICAgfVxuICB9KS50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSksIGVyciA9PiBjYihlcnIpKVxufVxuXG5tb2R1bGUuZXhwb3J0cy5sb2dpbiA9IGZ1bmN0aW9uKCBkYXRhLCBjYiApIHtcbiAgdXRpbHMucmVxdWVzdCh7XG4gICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMubG9naW4ubWV0aG9kLFxuICAgIFwidXJsXCIgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmxvZ2luLnVybCxcbiAgICBcImRhdGFcIiA6IGRhdGEsXG4gICAgXCJoZWFkZXJzXCIgOiB7XG4gICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXG4gICAgICBcIndpdGhDcmVkZW50aWFsc1wiIDogXCJ0cnVlXCJcbiAgICB9XG4gIH0pLnRoZW4oZGF0YSA9PiBjYihudWxsLCBkYXRhKSwgZXJyID0+IGNiKGVycikpXG4gIC8vIGN0eC50cmFuc3BvcnQoe1xuICAvLyAgIG1ldGhvZCA6IGNvbmZpZy5yb3V0ZXMubG9naW4ubWV0aG9kLFxuICAvLyAgIHVybCA6IGNvbmZpZy5hcGkuYXV0aCArIGNvbmZpZy5yb3V0ZXMubG9naW4udXJsLFxuICAvLyAgIGRhdGEgOiBkYXRhLFxuICAvLyAgIGhlYWRlcnMgOiB7XG4gIC8vICAgICBcIkNvbnRlbnQtVHlwZVwiIDogXCJhcHBsaWNhdGlvbi9qc29uXCIsXG4gIC8vICAgfSxcbiAgLy8gICB3aXRoQ3JlZGVudGlhbHMgOiB0cnVlXG4gIC8vIH0pXG4gIC8vIC50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSkpXG4gIC8vIC5jYXRjaChjYilcbn1cblxuLyogVGhpcyBtZXRob2QgZG9lcyBzYXZlcyB1c2VyIGRhdGEgaW4gdGhpcyBtb2R1bGUgb25seSwgbm8gYmFja2VuZCBjb21tdW5pY2F0aW9uICovXG5tb2R1bGUuZXhwb3J0cy5zYXZlVXNlckRhdGEgPSBmdW5jdGlvbihkYXRhKSB7XG4gIGRhdGEgPSBkYXRhLmxlbmd0aCA/IEpTT04ucGFyc2UoZGF0YSkgOiBcIlwiXG4gIHRoaXMudXNlciA9IHt9XG4gIC8qIFRPRE86INGA0LDRgdC/0LDRgNGB0LjRgtGMINC00LDQvdC90YvQtSDQsiDQvtGB0LzRi9GB0LvQtdC90L3Ri9C1INC/0LXRgNC10LzQtdC90L3Ri9C1ICovXG5cbiAgY29uc29sZS5sb2coXCJEYXRhYmFzZTo6IFVzZXIgZGF0YSBzYXZlZCBzdWNjZXNzZnVsbHkoINGI0YPRgtC60LAgKSBcIilcbn1cblxubW9kdWxlLmV4cG9ydHMuY2hlY2tVc2VySXNMb2dnZWQgPSBmdW5jdGlvbiggY2IgKSB7XG4gIHV0aWxzLnJlcXVlc3Qoe1xuICAgIFwibWV0aG9kXCIgOiBjb25maWcucm91dGVzLmNoZWNrTG9nZ2VkLm1ldGhvZCxcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS51cmwgKyBjb25maWcucm91dGVzLmNoZWNrTG9nZ2VkLnVybFxuICB9KS50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSksIGVyciA9PiBjYihlcnIpKVxufVxuXG5tb2R1bGUuZXhwb3J0cy51c2VyTG9nb3V0ID0gZnVuY3Rpb24oKSB7XG4gIGN0eC5zZXREZWZhdWx0cygpXG5cbiAgdXRpbHMucmVxdWVzdCh7XG4gICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMubG9nb3V0Lm1ldGhvZCxcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5sb2dvdXQudXJsXG4gIH0pLnRoZW4oKCk9Pnt9LCAoKT0+e30pXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2RiLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG4vKlxuICBFeHBlY3Qgb3B0aW9ucyBvYmplY3QgbGlrZSB0aGlzOlxuICB7XG4gICAgXCJtZXRob2RcIiA6IFwiUE9TVFwiLFxuICAgIFwidXJsXCIgOiBcImh0dHA6Ly9zb21ldXJsLmNvbS9cIixcbiAgICBcImRhdGFcIiA6IFwiZGF0YVwiLFxuICAgIFwiaGVhZGVyc1wiIDoge1xuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwiYXBwbGljYXRpb24vanNvblwiLFxuICAgICAgXCJDb250ZW50LUxlbmd0aFwiIDogXCIxMDIzXCJcbiAgICB9XG4gIH1cblxuICBERUZBVUxUUzpcbiAgTWV0aG9kIC0gZGVmYXVsdCBpcyBHRVQsXG4gIFVSTCAtIHJlcXVpcmVkLFxuICBkYXRhIC0gb3B0aW9uYWwsXG4gIGhlYWRlcnMgLSBvcHRpb25hbFxuKi9cblxubW9kdWxlLmV4cG9ydHMucmVxdWVzdCA9IGZ1bmN0aW9uKG9wdGlvbnMpIHtcbiAgcmV0dXJuIG5ldyBQcm9taXNlKCAocmVzb2x2ZSwgcmVqZWN0KSA9PiB7XG4gICAgLyogU2V0dGluZyBkZWZhdWx0cyAqL1xuICAgIGxldCB7IG1ldGhvZD1cIkdFVFwiLCB1cmwsIGRhdGEsIGhlYWRlcnMgfSA9IG9wdGlvbnNcblxuICAgIC8qIFNvbWUgdmFsaWRhdGlvbiAqL1xuICAgIGlmKCF1cmwpXG4gICAgICByZXR1cm4gY29uc29sZS5lcnJvcihcIlVybCBub3Qgc3BlY2lmaWVkXCIpXG5cbiAgICBpZiggKG1ldGhvZCA9PSBcIlBPU1RcIiB8fCBtZXRob2QgPT0gXCJQVVRcIikgJiYgIWRhdGEpXG4gICAgICByZXR1cm4gY29uc29sZS5lcnJvcihcIk5vdGhpbmcgdG8gc2VuZCBoZXJlID0pXCIpXG5cbiAgICAvKiBTdGFydCBjb25zdHJ1Y3RpbmcgcmVxdWVzdCAqL1xuICAgIHZhciB4aHIgPSBuZXcgWE1MSHR0cFJlcXVlc3QoKVxuICAgIHhoci5vcGVuKG1ldGhvZCwgdXJsLCB0cnVlKVxuXG4gICAgaWYoaGVhZGVycylcbiAgICAgIGZvciggdmFyIHByb3AgaW4gaGVhZGVycylcbiAgICAgICAgeGhyLnNldFJlcXVlc3RIZWFkZXIocHJvcCwgaGVhZGVyc1twcm9wXSlcblxuICAgIGlmKGRhdGEgJiYgaGVhZGVyc1snQ29udGVudC1UeXBlJ10gIT09IFwidGV4dC9wbGFpblwiKVxuICAgICAgeGhyLnNlbmQoSlNPTi5zdHJpbmdpZnkoZGF0YSkpXG4gICAgZWxzZSBpZihkYXRhKVxuICAgICAgeGhyLnNlbmQoZGF0YSlcbiAgICBlbHNlXG4gICAgICB4aHIuc2VuZCgpXG5cblxuXG4gICAgeGhyLm9ucmVhZHlzdGF0ZWNoYW5nZSA9IGZ1bmN0aW9uKCkge1xuICAgICAgaWYgKHRoaXMucmVhZHlTdGF0ZSAhPSA0KVxuICAgICAgICByZXR1cm5cblxuICAgICAgaWYgKHRoaXMuc3RhdHVzICE9IDIwMClcbiAgICAgICAgcmV0dXJuIHJlamVjdCgnRXJyb3I6ICcgKyAodGhpcy5zdGF0dXMgPyBgKCR7dGhpcy5zdGF0dXN9KSAke3RoaXMuc3RhdHVzVGV4dH1gOiAncmVxdWVzdCBmYWlsJykpXG4gICAgICBlbHNlXG4gICAgICAgIHJldHVybiByZXNvbHZlKHRoaXMucmVzcG9uc2VUZXh0KVxuXG4gICAgfVxuXG4gIH0pXG59XG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL3V0aWxzLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSB7XG5cdFwiYXBpXCI6IHtcblx0XHRcInVybFwiOiBcImh0dHA6Ly85My43My4xMDkuMzg6ODA4NC9cIixcblx0XHRcImF1dGhcIjogXCJodHRwOi8vOTMuNzMuMTA5LjM4OjgwODMvXCJcblx0fSxcblx0XCJyb3V0ZXNcIjoge1xuXHRcdFwiZ2V0QnVsbGV0aW5zXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvcmVzdC9vZmZlcnNTZXJ2aWNlL29mZmVyL3JlYWQvYWxsXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwic2tpcFwiOiAwLFxuXHRcdFx0XHRcImxpbWl0XCI6IDIwXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImxvZ2luXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJsb2dpblwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIixcblx0XHRcdFx0XCJwYXNzd29yZFwiOiBcIjEyMzQ1NlwiXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImxvZ291dFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIlBPU1RcIixcblx0XHRcdFwidXJsXCI6IFwibG9nb3V0XCJcblx0XHR9LFxuXHRcdFwicmVnaXN0ZXJcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJQT1NUXCIsXG5cdFx0XHRcInVybFwiOiBcInJlZ2lzdGVyXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwiZW1haWxcIjogXCJzc3MyQGdtYWlsLmNvbVwiLFxuXHRcdFx0XHRcInBhc3N3b3JkXCI6IFwiMTIzNDU2XCJcblx0XHRcdH1cblx0XHR9LFxuXHRcdFwiY2hlY2tFbWFpbFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIlBPU1RcIixcblx0XHRcdFwidXJsXCI6IFwibG9naW4vY2hlY2tFbWFpbFwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIlxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJjaGVja0xvZ2dlZFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIkdFVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvcmVzdC9wcm9maWxlc1NlcnZpY2UvcHJvZmlsZS9yZWFkL2xvZ2dlZEluUHJvZmlsZVwiXG5cdFx0fVxuXHR9XG59O1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9kYXRhL2NvbmZpZy5qc29uXG4gKiogbW9kdWxlIGlkID0gNzRcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcIml0ZW1zXCI6IFtcblx0XHR7XG5cdFx0XHRcImlkXCI6IDEsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa+XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDIsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa0XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDMsXG5cdFx0XHRcInRpdGxlXCI6IFwi0LTQsNGC0LAgIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA0LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItC00LDRgtCwIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA1LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA2LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH1cblx0XVxufTtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vZGF0YS9zb3J0aW5nLmpzb25cbiAqKiBtb2R1bGUgaWQgPSA3NVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiJ3VzZSBzdHJpY3QnXG5cbmNvbnN0IE1BWF9TWU1CT0xTID0gMTAwMFxuXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4ge1xuICAgIHJlc3RyaWN0OiBcIkVcIixcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxuICAgIHNjb3BlIDoge1xuICAgICAgbGFiZWw6IFwiQFwiLFxuICAgICAgbmdNb2RlbDogXCI9XCJcbiAgICB9LFxuICAgIHJlcGxhY2U6IHRydWUsXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiaW5wdXRGb3JtXCI+XG4gICAgICAgICAgICAgICAgIDxsYWJlbD57eyBsYWJlbCB9fTwvbGFiZWw+XG4gICAgICAgICAgICAgICAgIDx0ZXh0YXJlYSBuZy1tb2RlbD1cIm5nTW9kZWxcIiBtYXhsZW5ndGg9JHtNQVhfU1lNQk9MU30+PC90ZXh0YXJlYT5cbiAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cInN5bWJvbHNcIj48L2Rpdj5cbiAgICAgICAgICAgICAgIDwvZGl2PmAsXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCwgJHRpbWVvdXQpIHtcbiAgICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgY291bnQpXG5cbiAgICAgICRzY29wZS4kb24oXCIkZGVzdHJveVwiLCBmdW5jdGlvbigpIHtcbiAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcigna2V5dXAnLCBjb3VudClcbiAgICAgIH0uYmluZCh0aGlzKSlcblxuICAgICAgbGV0IGRlZmF1bHRCb3JkZXIgPSBcIlwiXG5cbiAgICAgIGxldCBlbCA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlUYWdOYW1lKCd0ZXh0YXJlYScpWzBdLFxuICAgICAgICAgIGxhYmVsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeVRhZ05hbWUoJ2xhYmVsJylbMF0sXG4gICAgICAgICAgc3ltYm9scyA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ3N5bWJvbHMnKVswXVxuXG5cbiAgICAgIGZ1bmN0aW9uIGNvdW50KCkge1xuICAgICAgICBzeW1ib2xzLmlubmVySFRNTCA9IGAkeyRzY29wZS5uZ01vZGVsLmxlbmd0aH0vJHtNQVhfU1lNQk9MU31gXG4gICAgICB9XG5cbiAgICAgIGZ1bmN0aW9uIG9uQmx1cihlKSB7XG4gICAgICAgIGlmKCAhJHNjb3BlLm5nTW9kZWwubGVuZ3RoKVxuICAgICAgICAgIGhpZGVBbmltYXRpb24oKVxuICAgICAgfVxuXG4gICAgICBmdW5jdGlvbiBvbkZvY3VzKGUpIHtcbiAgICAgICAgaWYoISRzY29wZS5uZ01vZGVsLmxlbmd0aClcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gZGlzcGxheUFuaW1hdGlvbigpIHtcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIiMxODc1RDBcIlxuICAgICAgICBpZighZGVmYXVsdEJvcmRlci5sZW5ndGgpIHtcbiAgICAgICAgICBkZWZhdWx0Qm9yZGVyID0gd2luZG93LmdldENvbXB1dGVkU3R5bGUobGFiZWwucGFyZW50Tm9kZSkuYm9yZGVyQm90dG9tXG4gICAgICAgIH0gZWxzZSB7XG4gICAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBgMnB4IHNvbGlkICMxODc1RDBgXG4gICAgICAgIH1cblxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QuYWRkKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgZnVuY3Rpb24gaGlkZUFuaW1hdGlvbigpIHtcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXG4gICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gZGVmYXVsdEJvcmRlclxuICAgICAgICBsYWJlbC5jbGFzc0xpc3QucmVtb3ZlKCd0ZXh0T3V0JylcbiAgICAgIH1cblxuICAgICAgJHRpbWVvdXQoICgpID0+IHtcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXG4gICAgICAgICAgZGlzcGxheUFuaW1hdGlvbigpXG4gICAgICAgIGVsc2VcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcbiAgICAgIH0sIDI1MClcblxuXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdibHVyJywgb25CbHVyLmJpbmQodGhpcykpXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdmb2N1cycsIG9uRm9jdXMuYmluZCgkc2NvcGUpKVxuICAgIH1cbiAgfVxufVxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0QXJlYS5qc1xuICoqLyJdLCJtYXBwaW5ncyI6IjtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ3RDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQURBO0FBSUE7QUFEQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBO0FBRUE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7Ozs7OztBQ2pEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7QUNQQTs7OztBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDaERBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNyUEE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNwQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ1BBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDUEE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDUEE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDL0JBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ0pBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFMQTtBQUNBO0FBT0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBSUE7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQXJCQTtBQXVCQTs7Ozs7O0FDMUJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQWpDQTtBQW1DQTs7Ozs7O0FDdENBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQU5BO0FBUUE7QUFDQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUlBO0FBQ0E7QUFBQTtBQUFBO0FBRUE7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFwQkE7QUF1QkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFoR0E7QUFrR0E7Ozs7OztBQzFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBUUE7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBbERBO0FBb0RBOzs7Ozs7QUN2REE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUFNQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQzFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFsREE7Ozs7Ozs7O0FDRkE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTtBQUNBOzs7Ozs7QUNGQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUFPQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDbEVBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUFPQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2pGQTtBQUNBOzs7OztBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFBQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7O0FBQUE7OztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTs7O0FBRUE7QUFDQTtBQUdBOzs7Ozs7QUFHQTs7Ozs7O0FDaERBO0FBQ0E7Ozs7O0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQUE7QUFDQTtBQUFBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7OztBQUFBOzs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7OztBQUVBO0FBQ0E7QUFHQTs7Ozs7O0FBR0E7Ozs7OztBQ3BDQTtBQUNBO0FBQ0E7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7Ozs7OztBQzdGQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBSkE7QUFPQTtBQUFBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFKQTtBQVFBO0FBQUE7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBR0E7QUFBQTtBQUFBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTs7Ozs7O0FDMUZBO0FBQ0E7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFtQkE7QUFDQTtBQUNBO0FBREE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUNBO0FBR0E7QUFDQTtBQUFBO0FBQ0E7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBREE7QUFDQTtBQVdBO0FBQ0E7QUFDQTtBQUVBO0FBS0E7QUFFQTs7Ozs7OztBQzVEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUM5Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ2pDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFwRUE7QUFzRUE7OzsiLCJzb3VyY2VSb290IjoiIn0=