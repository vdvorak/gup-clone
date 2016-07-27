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
	__webpack_require__(43);
	__webpack_require__(49);
	__webpack_require__(52);

	__webpack_require__(57)();

	var materials = __webpack_require__(59),
	    router = __webpack_require__(66);

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
	}]).controller('mainCtrl', __webpack_require__(76));

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
	var update = __webpack_require__(42)(content, {});
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
	exports.push([module.id, "body {\n  background-color: #ECECEC; }\n\n.calendar, .addCalendar {\n  position: relative;\n  background: url(" + __webpack_require__(4) + ") no-repeat center left;\n  padding-left: 45px;\n  box-sizing: border-box;\n  margin-bottom: 40px;\n  box-sizing: border-box; }\n  .calendar > .defaultValue, .addCalendar > .defaultValue {\n    border-bottom: 1px solid grey;\n    background: url(" + __webpack_require__(5) + ") no-repeat center right 5px;\n    cursor: pointer;\n    padding-right: 15px;\n    box-sizing: border-box; }\n    .calendar > .defaultValue > p, .addCalendar > .defaultValue > p {\n      text-align: left;\n      color: #262626;\n      font: 400 14px / 20px Roboto; }\n  .calendar > .listValue, .addCalendar > .listValue {\n    display: none; }\n\nheader {\n  height: 70px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #1875D0; }\n\n.btn-blue, .btn-grey {\n  position: relative;\n  display: inline-block;\n  overflow: hidden;\n  border-radius: 5px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  cursor: pointer;\n  text-align: center; }\n\n.btn-grey {\n  background-color: #D8D8D8;\n  color: #868686;\n  font: 400 14px / 21px Roboto; }\n\n.container {\n  width: 1280px;\n  position: relative;\n  margin: 0 auto; }\n\n.clearfix:before, section.mail > .tab > .contactsTab > .table > .tr:before, section.mail > .tab > .contactsTab > .table > .tr > .td:before, .dialog > .wrapMessages > .messages > .postInterlocutor:before, .dialog > .wrapMessages > .messages > .yourAnswer:before, .dialog > .footer:before, .miniContacts > .header:before, .miniContacts > .table > .tr:before, .miniContacts > .wrapMessages > .messages > .postInterlocutor:before, .miniContacts > .wrapMessages > .messages > .yourAnswer:before, .clearfix:after, section.mail > .tab > .contactsTab > .table > .tr:after, section.mail > .tab > .contactsTab > .table > .tr > .td:after, .dialog > .wrapMessages > .messages > .postInterlocutor:after, .dialog > .wrapMessages > .messages > .yourAnswer:after, .dialog > .footer:after, .miniContacts > .header:after, .miniContacts > .table > .tr:after, .miniContacts > .wrapMessages > .messages > .postInterlocutor:after, .miniContacts > .wrapMessages > .messages > .yourAnswer:after {\n  content: \" \";\n  display: table; }\n\n.clearfix:after, section.mail > .tab > .contactsTab > .table > .tr:after, section.mail > .tab > .contactsTab > .table > .tr > .td:after, .dialog > .wrapMessages > .messages > .postInterlocutor:after, .dialog > .wrapMessages > .messages > .yourAnswer:after, .dialog > .footer:after, .miniContacts > .header:after, .miniContacts > .table > .tr:after, .miniContacts > .wrapMessages > .messages > .postInterlocutor:after, .miniContacts > .wrapMessages > .messages > .yourAnswer:after {\n  clear: both; }\n\n.ink {\n  display: block;\n  position: absolute;\n  background: rgba(0, 0, 0, 0.15);\n  border-radius: 100%;\n  -webkit-transform: scale(0);\n          transform: scale(0); }\n\n.ink.animate {\n  -webkit-animation: ripple .5s ease-in;\n          animation: ripple .5s ease-in; }\n\n@-webkit-keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n@keyframes ripple {\n  100% {\n    opacity: 0;\n    -webkit-transform: scale(2.5);\n            transform: scale(2.5); } }\n\n.headLeft {\n  padding-top: 5px;\n  display: inline-block;\n  width: calc(100% - 490px);\n  height: 70px;\n  box-sizing: border-box; }\n  .headLeft > .logo {\n    cursor: pointer;\n    border-radius: 50%;\n    float: left;\n    height: 60px;\n    width: 60px;\n    background: url(" + __webpack_require__(6) + ") no-repeat;\n    margin-left: 15px; }\n  .headLeft > .inputForm {\n    margin-top: 21px;\n    margin-left: 20px;\n    float: left;\n    border-color: #FDFDFD;\n    box-sizing: border-box;\n    height: auto;\n    width: 200px;\n    border-bottom: 1px solid #FDFDFD; }\n    .headLeft > .inputForm > label, .headLeft > .inputForm input {\n      color: #ffffff;\n      font: 400 14px Roboto; }\n  .headLeft > .selectBox {\n    float: left;\n    margin-top: 21px;\n    margin-left: 20px; }\n    .headLeft > .selectBox > .defaultValue {\n      background: none;\n      padding: 0 5px; }\n  .headLeft > .add {\n    cursor: pointer;\n    float: right;\n    height: 35px;\n    width: 200px;\n    margin-right: 20px;\n    margin-top: 10px;\n    background: url(" + __webpack_require__(7) + ") no-repeat center right 10px;\n    padding-left: 10px;\n    text-align: left;\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    border: 1px solid white;\n    -webkit-transition: all .15s;\n    transition: all .15s; }\n    .headLeft > .add > p {\n      color: #ffffff;\n      font: 400 14px / 35px Roboto; }\n\n.headRight {\n  float: right;\n  width: 490px;\n  height: 70px;\n  border-left: 1px solid grey;\n  box-sizing: border-box;\n  padding-top: 22px; }\n  .headRight > .mail {\n    height: 26px;\n    width: 33px;\n    cursor: pointer;\n    position: relative;\n    float: left;\n    background: url(" + __webpack_require__(8) + ") no-repeat center center;\n    margin-left: 20px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .mail > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -3px;\n      left: 32px;\n      cursor: default; }\n    .headRight > .mail > .chooseYourStyle {\n      background-color: #ffffff;\n      height: 90px;\n      width: 200px;\n      box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n      position: absolute;\n      top: 35px;\n      left: 50%;\n      margin-left: -100px;\n      z-index: 1; }\n      .headRight > .mail > .chooseYourStyle > p {\n        color: #333333;\n        font: 400 14px / 45px Roboto;\n        padding-left: 15px;\n        -webkit-transition: all .25s;\n        transition: all .25s; }\n        .headRight > .mail > .chooseYourStyle > p:hover {\n          cursor: pointer;\n          background-color: #EBEBEB; }\n    .headRight > .mail:hover {\n      background: url(" + __webpack_require__(9) + ") no-repeat center center; }\n  .headRight > .bell {\n    height: 24px;\n    width: 23px;\n    cursor: pointer;\n    position: relative;\n    display: inline-block;\n    float: left;\n    margin-left: 30px;\n    background: url(" + __webpack_require__(10) + ") no-repeat center center;\n    border-radius: 15px 0 15px 10px;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .headRight > .bell > p {\n      color: #ffffff;\n      font: 400 10px / 15px Roboto;\n      position: absolute;\n      bottom: -7px;\n      left: 22px;\n      cursor: default; }\n    .headRight > .bell:hover {\n      background: url(" + __webpack_require__(11) + ") no-repeat center center; }\n  .headRight > .services {\n    height: 27px;\n    width: 28px;\n    cursor: pointer;\n    float: right;\n    margin-right: 30px;\n    background: url(" + __webpack_require__(12) + ") no-repeat center center;\n    -webkit-transition: all .25s;\n    transition: all .25s;\n    position: relative; }\n    .headRight > .services:hover {\n      background: url(" + __webpack_require__(13) + ") no-repeat center center; }\n    .headRight > .services > .vidgets {\n      position: absolute;\n      height: 244px;\n      width: 327px;\n      background: url(" + __webpack_require__(85) + ") no-repeat center center;\n      top: 26px;\n      right: -20px;\n      z-index: 1;\n      cursor: default;\n      padding: 20px 30px;\n      box-sizing: border-box; }\n      .headRight > .services > .vidgets > h2 {\n        color: #212121;\n        font: 700 18px Roboto;\n        text-align: center;\n        margin-bottom: 25px; }\n      .headRight > .services > .vidgets > a {\n        height: 65px;\n        margin-left: 30px;\n        float: left;\n        color: #546e7a;\n        font: 400 14px Roboto;\n        position: relative;\n        margin-bottom: 25px; }\n        .headRight > .services > .vidgets > a > p {\n          position: absolute;\n          bottom: 0; }\n        .headRight > .services > .vidgets > a:nth-of-type(1) {\n          margin-left: 0;\n          background: url(" + __webpack_require__(86) + ") no-repeat top center;\n          width: 80px; }\n        .headRight > .services > .vidgets > a:nth-of-type(2) {\n          background: url(" + __webpack_require__(87) + ") no-repeat top center;\n          width: 60px;\n          opacity: 0.5;\n          cursor: not-allowed; }\n        .headRight > .services > .vidgets > a:nth-of-type(3) {\n          background: url(" + __webpack_require__(88) + ") no-repeat top center;\n          width: 60px;\n          opacity: 0.5;\n          cursor: not-allowed; }\n        .headRight > .services > .vidgets > a:nth-of-type(4) {\n          margin-left: 0;\n          background: url(" + __webpack_require__(89) + ") no-repeat top center;\n          width: 75px;\n          opacity: 0.5;\n          cursor: not-allowed; }\n        .headRight > .services > .vidgets > a:nth-of-type(5) {\n          background: url(" + __webpack_require__(90) + ") no-repeat top center;\n          width: 50px;\n          margin-left: 43px;\n          opacity: 0.5;\n          cursor: not-allowed; }\n        .headRight > .services > .vidgets > a:nth-of-type(6) {\n          background: url(" + __webpack_require__(91) + ") no-repeat top center;\n          width: 60px;\n          margin-left: 36px;\n          opacity: 0.5;\n          cursor: not-allowed; }\n  .headRight > .userName {\n    float: right;\n    margin-right: 25px;\n    background: url(" + __webpack_require__(14) + ") no-repeat center left;\n    padding-left: 30px;\n    max-width: 270px;\n    position: relative; }\n    .headRight > .userName > p {\n      color: #ffffff;\n      font: 400 14px / 27px Roboto;\n      cursor: pointer;\n      white-space: nowrap;\n      overflow: hidden;\n      text-overflow: ellipsis; }\n    .headRight > .userName > div {\n      background-color: #FDFDFD;\n      box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n      position: absolute;\n      top: 0;\n      width: 100%;\n      z-index: 1; }\n      .headRight > .userName > div > p {\n        cursor: pointer;\n        padding: 0 15px;\n        height: 48px;\n        font: 400 16px / 50px Roboto;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        box-sizing: border-box;\n        transition: all .25s; }\n        .headRight > .userName > div > p:hover {\n          background-color: #eeeeee; }\n  .headRight > .auth {\n    color: white;\n    float: right;\n    margin-right: 20px;\n    font: 400 14px / 26px Roboto; }\n    .headRight > .auth span {\n      cursor: pointer;\n      margin: 0 10px; }\n\n.inputSearch {\n  position: relative;\n  display: inline-block;\n  margin-top: 10px;\n  border-bottom: 1px solid white; }\n  .inputSearch > input {\n    border: none;\n    padding: 2px 0;\n    background-color: transparent;\n    outline: none;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto; }\n  .inputSearch > label {\n    position: absolute;\n    top: 0;\n    left: 0;\n    color: #ffffff;\n    font: 400 14px / 21px Roboto;\n    cursor: text;\n    -webkit-transition: .5s;\n    transition: .5s; }\n\n.selectBox {\n  position: relative;\n  display: inline-block;\n  cursor: pointer; }\n  .selectBox > .defaultValue {\n    color: #ffffff;\n    font: 400 14px / 19px Roboto;\n    overflow: hidden;\n    border-bottom: 1px solid white;\n    background: url(" + __webpack_require__(5) + ") no-repeat center right 5px;\n    padding-right: 20px;\n    box-sizing: border-box; }\n    .selectBox > .defaultValue span {\n      font-size: 16px; }\n  .selectBox > .listOfValues {\n    position: absolute;\n    top: 0;\n    left: 0;\n    background-color: white;\n    z-index: 1;\n    box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n    -webkit-animation: animatetop .25s;\n            animation: animatetop .25s; }\n    .selectBox > .listOfValues > div {\n      cursor: pointer;\n      padding: 0 15px;\n      height: 50px;\n      width: 120px;\n      font: 400 16px / 50px Roboto;\n      -webkit-transition: all .25s;\n      transition: all .25s; }\n      .selectBox > .listOfValues > div:hover {\n        background-color: #eeeeee; }\n\n@-webkit-keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\n@keyframes animatetop {\n  0% {\n    opacity: 0; }\n  100% {\n    opacity: 1; } }\n\naside.bulletinDetails {\n  float: right;\n  margin-top: 5px; }\n\nsection.openAdvertert {\n  background-color: white;\n  float: right;\n  margin-right: 10px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 715px;\n  padding: 25px 100px 45px;\n  box-sizing: border-box;\n  margin-top: 5px; }\n  section.openAdvertert > h3 {\n    color: #202020;\n    font: 400 22px / 26px Roboto;\n    float: left; }\n  section.openAdvertert > .price {\n    color: #1f1f1f;\n    font: 400 18px / 26px Roboto;\n    float: right; }\n  section.openAdvertert > .checkBox {\n    float: right;\n    margin-right: 15px;\n    margin-top: -1px; }\n  section.openAdvertert > .breadCrumbs {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: left;\n    margin-top: 10px;\n    cursor: default; }\n  section.openAdvertert > .id {\n    color: rgba(32, 32, 32, 0.54);\n    font: 400 12px / 14px Roboto;\n    float: right;\n    cursor: default;\n    margin-top: 10px; }\n  section.openAdvertert > .slider {\n    height: 120px;\n    position: relative;\n    margin-top: 10px;\n    float: left;\n    width: 100%;\n    margin-bottom: 10px; }\n    section.openAdvertert > .slider > div {\n      float: left;\n      margin-left: 10px;\n      height: 100%;\n      width: 165px; }\n      section.openAdvertert > .slider > div:nth-of-type(1) {\n        margin: 0; }\n      section.openAdvertert > .slider > div > img {\n        -o-object-fit: contain;\n           object-fit: contain;\n        width: 100%;\n        background-color: #F4F4F4;\n        height: 100%;\n        cursor: pointer; }\n    section.openAdvertert > .slider > .next {\n      position: absolute;\n      background: url(" + __webpack_require__(15) + ") no-repeat left center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      right: -25px; }\n    section.openAdvertert > .slider > .prev {\n      position: absolute;\n      background: url(" + __webpack_require__(16) + ") no-repeat right center;\n      height: 14px;\n      width: 10px;\n      cursor: pointer;\n      top: 50%;\n      margin-top: -7px;\n      left: -25px; }\n  section.openAdvertert > div.buyProduct > #mapForOneAdvertert {\n    float: left;\n    height: 230px;\n    width: 225px;\n    margin-bottom: 25px; }\n  section.openAdvertert > div.buyProduct > .descriptionAd {\n    color: #0c0c1e;\n    font: 400 12px / 18px Roboto;\n    margin-top: 15px;\n    margin-bottom: 20px; }\n  section.openAdvertert > div.buyProduct > .goToMap {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: left; }\n  section.openAdvertert > div.buyProduct > .allComments {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right;\n    margin-right: 50px; }\n  section.openAdvertert > div.buyProduct > .writeAReview {\n    color: #1976d2;\n    font: 400 14px / 21px Roboto;\n    cursor: pointer;\n    float: right; }\n  section.openAdvertert > div.buyProduct > .nameUser {\n    color: #1976d2;\n    font: 400 14px / 18px Roboto;\n    float: left;\n    margin-left: 20px;\n    cursor: default;\n    margin-bottom: 35px; }\n  section.openAdvertert > div.buyProduct > .onOrOffLineUser {\n    float: left; }\n  section.openAdvertert > div.buyProduct .btn-grey {\n    float: right;\n    height: 35px;\n    width: 140px;\n    margin-right: 25px;\n    line-height: 35px; }\n  section.openAdvertert > div.buyProduct .btn-blue {\n    float: right;\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n  section.openAdvertert > .rentProduct {\n    margin-top: 45px; }\n    section.openAdvertert > .rentProduct > .rentCalendar {\n      margin-bottom: 45px; }\n      section.openAdvertert > .rentProduct > .rentCalendar > p {\n        color: #263238;\n        font: 400 16px / 21px Roboto;\n        float: left;\n        cursor: default; }\n        section.openAdvertert > .rentProduct > .rentCalendar > p:nth-of-type(2) {\n          margin-left: 55px; }\n      section.openAdvertert > .rentProduct > .rentCalendar > .calendar {\n        float: left;\n        margin-left: 45px;\n        width: 165px;\n        margin-bottom: 0; }\n    section.openAdvertert > .rentProduct > .inputForm {\n      color: #9a9a9a;\n      margin-bottom: 40px; }\n    section.openAdvertert > .rentProduct > .btn-blue {\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 35px;\n      width: 180px;\n      line-height: 35px;\n      float: right; }\n\n.wrapForDiv {\n  width: 265px;\n  float: right;\n  overflow: hidden;\n  margin-bottom: 25px;\n  border: 1px solid #E9E9E9;\n  box-sizing: border-box; }\n  .wrapForDiv > ul.tab {\n    list-style-type: none;\n    height: 50px;\n    background-color: white;\n    border-bottom: 1px solid #E9E9E9;\n    box-sizing: border-box; }\n    .wrapForDiv > ul.tab > li {\n      float: left; }\n      .wrapForDiv > ul.tab > li > a {\n        color: #939393;\n        font: 400 14px / 50px Roboto;\n        display: block;\n        -webkit-transition: all .25s;\n        transition: all .25s;\n        text-align: center;\n        position: relative; }\n        .wrapForDiv > ul.tab > li > a:after {\n          content: '';\n          display: block;\n          position: absolute;\n          bottom: 0;\n          width: 0;\n          height: 2px;\n          background-color: #FD5151;\n          -webkit-transition: all .25s;\n          transition: all .25s; }\n        .wrapForDiv > ul.tab > li > a:focus, .wrapForDiv > ul.tab > li > a.active {\n          color: #7eafe1; }\n          .wrapForDiv > ul.tab > li > a:focus:after, .wrapForDiv > ul.tab > li > a.active:after {\n            width: 100%; }\n      .wrapForDiv > ul.tab > li:nth-of-type(1) > a {\n        width: 159px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(1) > a:after {\n          right: 0; }\n      .wrapForDiv > ul.tab > li:nth-of-type(2) > a {\n        width: 104px; }\n        .wrapForDiv > ul.tab > li:nth-of-type(2) > a:after {\n          left: 0; }\n  .wrapForDiv > .featuresAndReviews {\n    height: 178px;\n    width: 282px;\n    background-color: #F4F4F4;\n    overflow: auto;\n    box-sizing: border-box; }\n    .wrapForDiv > .featuresAndReviews > .tabcontent {\n      display: none;\n      -webkit-animation: fadeEffect 1s;\n      animation: fadeEffect 1s; }\n      .wrapForDiv > .featuresAndReviews > .tabcontent.active {\n        display: block; }\n    .wrapForDiv > .featuresAndReviews > #reviews {\n      position: relative; }\n      .wrapForDiv > .featuresAndReviews > #reviews > div {\n        padding: 30px 20px 25px 65px;\n        position: relative;\n        border-bottom: 1px solid #E9E9E9; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div:nth-last-of-type(1) {\n          border: none; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > img {\n          position: absolute;\n          height: 30px;\n          width: 25px;\n          top: 35px;\n          left: 20px; }\n        .wrapForDiv > .featuresAndReviews > #reviews > div > p {\n          color: #0d0d1e;\n          font: 400 12px / 18px Roboto; }\n\n@-webkit-keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n@keyframes fadeEffect {\n  from {\n    opacity: 0; }\n  to {\n    opacity: 1; } }\n\n.btn-blue {\n  background-color: #1875D0;\n  color: #ffffff;\n  font: 400 14px / 21px Roboto; }\n\n.onOrOffLineUser {\n  background-color: #00C652;\n  border: 1px solid white;\n  box-shadow: 1px 1px 3px 0px rgba(0, 0, 0, 0.65);\n  height: 18px;\n  width: 18px;\n  box-sizing: border-box;\n  border-radius: 50%; }\n\n.error-container {\n  width: 1103px;\n  margin: auto;\n  background-image: url(" + __webpack_require__(17) + ");\n  background-repeat: no-repeat;\n  background-position-x: right;\n  background-position-y: 80px;\n  min-height: 500px; }\n  .error-container h1 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    text-align: center;\n    margin-top: 65px; }\n  .error-container h2 {\n    color: #1f1f1f;\n    font: 400 22px / 26.4px Roboto;\n    margin-top: 189px; }\n  .error-container h3 {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 10px;\n    margin-bottom: 30px; }\n  .error-container > .btn-blue {\n    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n    height: 35px;\n    width: 180px;\n    line-height: 35px; }\n\n.clicked {\n  box-shadow: 0px 0px 4px 0px rgba(0, 0, 0, 0.4) !important;\n  margin-top: 12px !important; }\n\n.multiple {\n  width: 1150px;\n  margin: 5px auto 0; }\n  .multiple > .bulletin-short {\n    float: left;\n    width: 380px;\n    margin-left: 5px;\n    height: 165px;\n    padding: 15px; }\n    .multiple > .bulletin-short:nth-of-type(3n-2) {\n      margin-left: 0; }\n    .multiple > .bulletin-short:nth-of-type(1) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(2) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short:nth-of-type(3) {\n      background-color: #BCE6E9; }\n    .multiple > .bulletin-short > .bulletin-center-column {\n      float: none;\n      margin: 0;\n      position: absolute;\n      top: 15px;\n      right: 110px; }\n      .multiple > .bulletin-short > .bulletin-center-column > .bulletin-action {\n        bottom: -43px;\n        width: 110px;\n        right: -30px;\n        z-index: 1; }\n    .multiple > .bulletin-short > .bulletin-left-column > .bulletin-description {\n      color: #0c0c1e;\n      font: 400 12px / 18px Roboto;\n      width: 170px;\n      overflow: hidden;\n      height: 40px; }\n\n.red {\n  background-color: #F74A4A; }\n\n.inputForm-required {\n  position: relative;\n  padding: 6px 2px;\n  font: 400 16px / 24.8px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px solid #9b9b9b;\n  margin-top: 15px; }\n  .inputForm-required label {\n    position: absolute;\n    left: 2px;\n    top: 6px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n  .inputForm-required input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent; }\n\n.inputForm.required {\n  border-bottom: 1px solid #9b9b9b !important; }\n\n.inputForm {\n  position: relative;\n  font: 400 16px Roboto;\n  box-sizing: border-box;\n  border-bottom: 1px dotted #999999; }\n  .inputForm label {\n    position: absolute;\n    left: 5px;\n    color: #9a9a9a;\n    -webkit-transition: all .25s;\n    transition: all .25s;\n    top: -1px; }\n  .inputForm input {\n    border: none;\n    outline: none;\n    width: 100%;\n    background-color: transparent;\n    position: relative;\n    padding: 1px 5px; }\n\n/* Text element animation */\n.textOut {\n  top: -15px !important;\n  font-size: 12px !important;\n  left: 5px !important; }\n\nsection.login, section.register {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  padding: 65px 230px;\n  width: 1100px;\n  margin: 5px auto 0;\n  box-sizing: border-box; }\n  section.login > h2, section.register > h2 {\n    text-align: center;\n    color: #202020;\n    font: 400 22px Roboto;\n    margin-bottom: 55px; }\n  section.login > .inputForm, section.register > .inputForm {\n    margin-bottom: 45px; }\n    section.login > .inputForm > label, section.login > .inputForm input, section.register > .inputForm > label, section.register > .inputForm input {\n      color: #9a9a9a;\n      font: 400 16px Roboto; }\n  section.login > .btn-blue, section.register > .btn-blue {\n    height: 35px;\n    width: 180px;\n    margin-left: 45px;\n    line-height: 35px; }\n    section.login > .btn-blue.vk, section.register > .btn-blue.vk {\n      background: #0186CF url(" + __webpack_require__(18) + ") no-repeat center left 10px;\n      margin-left: 0; }\n    section.login > .btn-blue.facebook, section.register > .btn-blue.facebook {\n      background: #3E50B3 url(" + __webpack_require__(19) + ") no-repeat center left 10px; }\n    section.login > .btn-blue.google, section.register > .btn-blue.google {\n      background: #FD3C00 url(" + __webpack_require__(20) + ") no-repeat center left 10px; }\n\nsection.bulletinAdd {\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  background-color: #FDFDFD;\n  width: 1100px;\n  margin: 5px auto 0;\n  padding: 65px 225px;\n  box-sizing: border-box; }\n  section.bulletinAdd > h2 {\n    color: #1f1f1f;\n    font: 400 22px Roboto;\n    text-align: center;\n    margin-bottom: 40px; }\n  section.bulletinAdd > .selectBox {\n    margin-bottom: 45px;\n    margin-top: 15px; }\n    section.bulletinAdd > .selectBox > .defaultValue {\n      border-color: grey;\n      min-width: 195px;\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      padding-left: 5px; }\n  section.bulletinAdd > .file {\n    float: right;\n    position: relative;\n    background-color: #FDFDFD;\n    border-bottom: 1px solid #9e9e9e;\n    width: 410px;\n    padding-bottom: 5px; }\n    section.bulletinAdd > .file > input {\n      position: absolute;\n      z-index: -1; }\n    section.bulletinAdd > .file > p {\n      color: #9e9e9e;\n      font: 400 16px Roboto;\n      cursor: default;\n      float: left;\n      padding-top: 10px; }\n    section.bulletinAdd > .file > .btn-blue {\n      float: right;\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 30px;\n      width: 85px;\n      line-height: 30px; }\n  section.bulletinAdd > .inputForm {\n    margin-bottom: 45px; }\n    section.bulletinAdd > .inputForm > label, section.bulletinAdd > .inputForm input {\n      color: #999999;\n      font: 400 16px Roboto; }\n  section.bulletinAdd > .color {\n    margin-bottom: 40px; }\n    section.bulletinAdd > .color > p {\n      color: #999999;\n      font: 400 16px Roboto;\n      border-bottom: 1px dotted #999999;\n      width: 160px;\n      float: left;\n      height: 21px;\n      box-sizing: border-box; }\n    section.bulletinAdd > .color > div {\n      float: left;\n      margin-left: 19px;\n      margin-top: 3px;\n      width: 16px;\n      height: 15px;\n      cursor: pointer;\n      box-sizing: border-box;\n      outline: 1px solid transparent;\n      border: 1px solid white; }\n      section.bulletinAdd > .color > div.transparent {\n        border-color: #E9E9E9;\n        overflow: hidden; }\n        section.bulletinAdd > .color > div.transparent > .redStick {\n          -webkit-transform: rotate(-43deg);\n                  transform: rotate(-43deg);\n          background-color: red;\n          height: 2px;\n          margin-top: 5.5px;\n          width: 20px;\n          margin-left: -3px; }\n      section.bulletinAdd > .color > div.red {\n        background-color: red; }\n      section.bulletinAdd > .color > div.orange {\n        background-color: orange; }\n      section.bulletinAdd > .color > div.yellow {\n        background-color: yellow; }\n      section.bulletinAdd > .color > div.green {\n        background-color: green; }\n      section.bulletinAdd > .color > div.lightBlue {\n        background-color: lightBlue; }\n      section.bulletinAdd > .color > div.blue {\n        background-color: blue; }\n      section.bulletinAdd > .color > div.pink {\n        background-color: pink; }\n      section.bulletinAdd > .color > div.purple {\n        background-color: purple; }\n      section.bulletinAdd > .color > div.white {\n        background-color: white;\n        border-color: #E9E9E9; }\n      section.bulletinAdd > .color > div.gray {\n        background-color: gray; }\n      section.bulletinAdd > .color > div.black {\n        background-color: black; }\n      section.bulletinAdd > .color > div.brown {\n        background-color: brown; }\n      section.bulletinAdd > .color > div.multicolor {\n        /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#ff0000+0,ffff00+20,1dff00+40,00ffff+60,0400ff+80,ff00fa+100 */\n        background: #ff0000;\n        /* Old browsers */\n        /* FF3.6-15 */\n        background: -webkit-linear-gradient(left, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* Chrome10-25,Safari5.1-6 */\n        background: linear-gradient(to right, #ff0000 0%, #ffff00 20%, #1dff00 40%, #00ffff 60%, #0400ff 80%, #ff00fa 100%);\n        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */\n        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff0000', endColorstr='#ff00fa',GradientType=1 );\n        /* IE6-9 */ }\n      section.bulletinAdd > .color > div.active {\n        outline-color: red; }\n  section.bulletinAdd > .calendar, section.bulletinAdd .addCalendar {\n    width: 230px; }\n  section.bulletinAdd > .addCalendar {\n    background-image: url(" + __webpack_require__(21) + "); }\n    section.bulletinAdd > .addCalendar > .defaultValue {\n      border-bottom-color: blue;\n      background: none; }\n      section.bulletinAdd > .addCalendar > .defaultValue > p {\n        color: #1976d2;\n        font-size: 16px; }\n  section.bulletinAdd > .btn-blue {\n    height: 35px;\n    width: 180px;\n    line-height: 35px;\n    display: block;\n    margin: 0 auto; }\n\n.errors {\n  position: absolute;\n  font: 400 12px / 14px Roboto;\n  color: #dd2c00;\n  bottom: -17px; }\n\nnav {\n  width: 255px;\n  float: left;\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  margin-top: 5px;\n  z-index: 2;\n  position: absolute; }\n  nav > .map {\n    background: url(" + __webpack_require__(22) + ") no-repeat;\n    height: 145px;\n    width: 255px; }\n    nav > .map > .inputForm {\n      width: 215px;\n      margin: 115px auto 0; }\n      nav > .map > .inputForm.required {\n        border-color: black !important; }\n      nav > .map > .inputForm > input, nav > .map > .inputForm label, nav > .map > .inputForm .textOut {\n        color: black !important; }\n  nav > ul {\n    list-style: none; }\n    nav > ul > li > p {\n      padding-left: 75px;\n      color: #263238;\n      font: 500 13px / 40px Roboto;\n      background: url(" + __webpack_require__(23) + ") no-repeat center right 20px;\n      cursor: pointer; }\n    nav > ul > li:hover {\n      background-color: #E6E6E6 !important; }\n    nav > ul > li:nth-of-type(1) {\n      background: url(" + __webpack_require__(24) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(2) {\n      background: url(" + __webpack_require__(25) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(3) {\n      background: url(" + __webpack_require__(26) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(4) {\n      background: url(" + __webpack_require__(27) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(5) {\n      background: url(" + __webpack_require__(28) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(6) {\n      background: url(" + __webpack_require__(29) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(7) {\n      background: url(" + __webpack_require__(30) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(8) {\n      background: url(" + __webpack_require__(31) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(9) {\n      background: url(" + __webpack_require__(32) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(10) {\n      background: url(" + __webpack_require__(33) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(11) {\n      background: url(" + __webpack_require__(34) + ") no-repeat left 25px center; }\n    nav > ul > li:nth-of-type(12) {\n      background: url(" + __webpack_require__(35) + ") no-repeat left 25px center; }\n\n.navBtn {\n  width: 43px;\n  height: 49px;\n  position: absolute;\n  left: 0;\n  top: 90px;\n  cursor: pointer;\n  background: url(" + __webpack_require__(36) + ") no-repeat;\n  -webkit-transition: all .25s;\n  transition: all .25s;\n  z-index: 1; }\n  .navBtn:hover {\n    width: 60px;\n    background-image: url(" + __webpack_require__(37) + "); }\n\n.cover {\n  position: absolute;\n  top: 0;\n  right: 0;\n  bottom: 0;\n  left: 0;\n  background-color: rgba(0, 0, 0, 0.9);\n  z-index: 5; }\n  .cover.hide {\n    -webkit-animation: hide 1s;\n            animation: hide 1s; }\n\n@-webkit-keyframes hide {\n  100% {\n    background-color: transparent; } }\n\n@keyframes hide {\n  100% {\n    background-color: transparent; } }\n\nsection.mail {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 1105px;\n  margin: 5px auto 10px;\n  padding: 45px 0 80px; }\n  section.mail > .tab {\n    width: 905px;\n    border: 1px solid #E9E9E9;\n    border-bottom: none;\n    margin: 0 auto;\n    background-color: #F4F4F4; }\n    section.mail > .tab > ul {\n      background-color: #FDFDFD;\n      list-style: none;\n      border-bottom: 1px solid #E9E9E9;\n      height: 50px;\n      box-sizing: border-box; }\n      section.mail > .tab > ul > li {\n        color: #9e9e9e;\n        font: 400 14px / 50px Roboto;\n        cursor: pointer;\n        text-align: center;\n        float: left;\n        position: relative; }\n        section.mail > .tab > ul > li:after {\n          content: '';\n          position: absolute;\n          bottom: 0;\n          left: 50%;\n          display: block;\n          height: 2px;\n          width: 0;\n          background-color: #FD5151;\n          -webkit-transition: all .25s;\n          transition: all .25s; }\n        section.mail > .tab > ul > li.active {\n          color: #7eafe1; }\n          section.mail > .tab > ul > li.active:after {\n            width: 100%;\n            left: 0; }\n        section.mail > .tab > ul > li:nth-of-type(1) {\n          width: 110px; }\n        section.mail > .tab > ul > li:nth-of-type(2) {\n          width: 215px; }\n        section.mail > .tab > ul > li:nth-of-type(3) {\n          padding: 0 15px; }\n    section.mail > .tab > .mailTab {\n      display: none; }\n      section.mail > .tab > .mailTab > .tavleTitle {\n        height: 65px;\n        padding-top: 35px;\n        box-sizing: border-box;\n        border-bottom: 1px solid #999999; }\n        section.mail > .tab > .mailTab > .tavleTitle > p {\n          color: #9e9e9e;\n          font: 400 14px / 20px Roboto;\n          float: left;\n          margin-left: 30px;\n          cursor: default; }\n          section.mail > .tab > .mailTab > .tavleTitle > p:nth-of-type(2) {\n            margin-left: 45px; }\n        section.mail > .tab > .mailTab > .tavleTitle > .selectBox {\n          float: left;\n          margin-left: 45px; }\n          section.mail > .tab > .mailTab > .tavleTitle > .selectBox > .defaultValue {\n            color: #9e9e9e;\n            font: 400 16px Roboto;\n            border-color: #999999;\n            width: 195px; }\n          section.mail > .tab > .mailTab > .tavleTitle > .selectBox:nth-of-type(2) {\n            margin-left: 10px; }\n      section.mail > .tab > .mailTab > .table > .tr {\n        border-bottom: 1px solid #999999;\n        padding: 10px 0;\n        position: relative; }\n        section.mail > .tab > .mailTab > .table > .tr:before, section.mail > .tab > .mailTab > .table > .tr:after {\n          content: \" \";\n          display: table; }\n        section.mail > .tab > .mailTab > .table > .tr:after {\n          clear: both; }\n        section.mail > .tab > .mailTab > .table > .tr:nth-of-type(1) > .td {\n          color: #9e9e9e;\n          font: 400 13px / 27px Roboto;\n          text-align: left;\n          cursor: default !important; }\n          section.mail > .tab > .mailTab > .table > .tr:nth-of-type(1) > .td > .checkBox {\n            position: relative !important;\n            margin-top: 0 !important;\n            margin-left: 30px;\n            left: 0 !important; }\n        section.mail > .tab > .mailTab > .table > .tr > .td {\n          float: left;\n          color: #666666;\n          font: 400 14px / 18px Roboto; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(1) {\n            width: 80px;\n            min-height: 1px; }\n            section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(1) > .checkBox {\n              left: 30px;\n              position: absolute;\n              top: 50%;\n              margin-top: -13px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(2) {\n            width: 145px;\n            min-height: 1px; }\n            section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(2) > .trUser {\n              position: absolute;\n              top: 50%;\n              margin-top: -9px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(3) {\n            width: 310px;\n            cursor: pointer; }\n            section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(3) > .preview {\n              color: #9e9e9e;\n              font: 400 14px Roboto;\n              width: 310px;\n              white-space: nowrap;\n              overflow: hidden;\n              text-overflow: ellipsis;\n              margin-top: 10px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(4) {\n            width: 105px;\n            min-height: 1px; }\n            section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(4) > img {\n              position: absolute;\n              top: 50%;\n              margin-top: -7px;\n              left: 555px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(5) {\n            width: 150px;\n            min-height: 1px; }\n            section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(5) > .status {\n              position: absolute;\n              top: 50%;\n              margin-top: -9px; }\n          section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(6) {\n            min-height: 1px; }\n            section.mail > .tab > .mailTab > .table > .tr > .td:nth-of-type(6) > .date {\n              position: absolute;\n              top: 50%;\n              margin-top: -9px;\n              left: 790px; }\n    section.mail > .tab > .contactsTab {\n      padding: 30px;\n      border-bottom: 1px solid #E9E9E9;\n      display: none; }\n      section.mail > .tab > .contactsTab > .inputForm {\n        float: left;\n        width: 315px;\n        background: url(" + __webpack_require__(38) + ") no-repeat center right 5px;\n        margin-bottom: 30px; }\n      section.mail > .tab > .contactsTab > .table > .tr {\n        margin-bottom: 25px; }\n        section.mail > .tab > .contactsTab > .table > .tr:nth-of-type(1) > .td {\n          height: auto; }\n          section.mail > .tab > .contactsTab > .table > .tr:nth-of-type(1) > .td > p {\n            color: #9e9e9e;\n            font: 400 13px Roboto;\n            text-align: left;\n            cursor: default; }\n          section.mail > .tab > .contactsTab > .table > .tr:nth-of-type(1) > .td:nth-of-type(1) > p {\n            margin-left: 65px; }\n        section.mail > .tab > .contactsTab > .table > .tr:nth-last-of-type(1) {\n          margin-bottom: 0; }\n        section.mail > .tab > .contactsTab > .table > .tr > .td {\n          float: left;\n          height: 40px; }\n          section.mail > .tab > .contactsTab > .table > .tr > .td:nth-of-type(1) {\n            width: 330px; }\n          section.mail > .tab > .contactsTab > .table > .tr > .td:nth-of-type(2) {\n            width: 410px; }\n          section.mail > .tab > .contactsTab > .table > .tr > .td > .username {\n            color: #666666;\n            font: 400 14px / 40px Roboto;\n            display: inline-block;\n            margin-left: 25px;\n            white-space: nowrap;\n            overflow: hidden;\n            text-overflow: ellipsis;\n            width: 260px; }\n          section.mail > .tab > .contactsTab > .table > .tr > .td > .onOrOffLineUser {\n            margin-top: 11px; }\n\n.date, .dialog > .header > .date {\n  color: #666666;\n  font: 400 14px / 40px Roboto; }\n\n.avatar, .dialog > .header > .avatar {\n  border-radius: 50%;\n  height: 40px;\n  width: 40px;\n  cursor: pointer;\n  background: url(" + __webpack_require__(39) + ");\n  float: left; }\n\n.dialog {\n  height: 515px;\n  position: relative;\n  padding-top: 90px;\n  box-sizing: border-box;\n  overflow: hidden;\n  border-bottom: 1px solid #E9E9E9; }\n  .dialog > .header {\n    height: 65px;\n    border-bottom: 1px solid #999999;\n    box-sizing: border-box;\n    padding-top: 15px;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 905px; }\n    .dialog > .header > .avatar {\n      margin-left: 25px; }\n    .dialog > .header > p {\n      color: #263238;\n      font: 400 14px / 40px Roboto;\n      float: left;\n      margin-left: 35px; }\n      .dialog > .header > p > i {\n        color: #9e9e9e;\n        font: 400 14px / 40px Roboto; }\n    .dialog > .header > a {\n      color: #1976d2;\n      font: 400 14px / 40px Roboto;\n      float: left;\n      margin-left: 5px;\n      text-decoration: underline; }\n      .dialog > .header > a:hover {\n        text-decoration: none; }\n    .dialog > .header > .date {\n      float: right;\n      margin-right: 10px; }\n  .dialog > h2 {\n    color: #202020;\n    font: 400 20px Roboto;\n    margin-bottom: 20px;\n    margin-left: 80px; }\n  .dialog > .wrapMessages {\n    width: 645px;\n    overflow: hidden;\n    margin-left: 25px; }\n    .dialog > .wrapMessages > .messages {\n      width: 662px;\n      height: 320px;\n      overflow: auto; }\n      .dialog > .wrapMessages > .messages > .postInterlocutor {\n        margin-bottom: 40px; }\n        .dialog > .wrapMessages > .messages > .postInterlocutor > p {\n          float: left;\n          color: #0c0c1e;\n          font: 400 12px Roboto;\n          margin-left: 15px;\n          width: 535px; }\n      .dialog > .wrapMessages > .messages > .yourAnswer {\n        margin-bottom: 40px; }\n        .dialog > .wrapMessages > .messages > .yourAnswer > .avatar {\n          background: url(" + __webpack_require__(40) + ");\n          float: right; }\n        .dialog > .wrapMessages > .messages > .yourAnswer > p {\n          color: #0c0c1e;\n          font: 400 12px Roboto;\n          float: right;\n          margin-right: 15px;\n          width: 535px;\n          text-align: right; }\n  .dialog > .footer {\n    position: absolute;\n    bottom: 10px;\n    left: 25px;\n    width: 870px; }\n    .dialog > .footer > .avatar {\n      float: left;\n      background: url(" + __webpack_require__(40) + "); }\n    .dialog > .footer > .inputForm {\n      float: left;\n      margin-left: 5px;\n      border-bottom: 1px solid #1875D0;\n      width: 610px;\n      overflow: hidden; }\n      .dialog > .footer > .inputForm > textarea {\n        border: none;\n        resize: none;\n        background-color: transparent;\n        outline: none;\n        height: 30px;\n        color: #0c0c1e;\n        font: 400 12px / 15px Roboto;\n        width: 630px;\n        padding-right: 50px;\n        box-sizing: border-box; }\n      .dialog > .footer > .inputForm > .symbols {\n        display: none; }\n    .dialog > .footer > .clip {\n      background: url(" + __webpack_require__(41) + ") no-repeat center center;\n      height: 20px;\n      width: 20px;\n      position: absolute;\n      bottom: 10px;\n      right: 220px;\n      cursor: pointer;\n      z-index: 1; }\n    .dialog > .footer > .btn-blue {\n      box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n      height: 35px;\n      width: 180px;\n      line-height: 35px;\n      margin-left: 30px; }\n\n.miniContacts {\n  position: absolute;\n  top: 115px;\n  left: 540px;\n  background-color: #FDFDFD;\n  height: 390px;\n  width: 335px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  z-index: 1; }\n  .miniContacts > .header {\n    height: 65px;\n    background-color: #1875D0; }\n    .miniContacts > .header > .back {\n      background: url(" + __webpack_require__(82) + ") no-repeat center center;\n      cursor: pointer;\n      height: 25px;\n      width: 45px;\n      float: left;\n      margin-left: 20px;\n      margin-top: 20px;\n      -webkit-transition: all .25s;\n      transition: all .25s; }\n      .miniContacts > .header > .back.toContacts {\n        background-image: url(" + __webpack_require__(84) + ");\n        height: 30px;\n        -webkit-transition: all .25s;\n        transition: all .25s; }\n    .miniContacts > .header > .close {\n      background: url(" + __webpack_require__(83) + ") no-repeat center center;\n      height: 25px;\n      width: 25px;\n      cursor: pointer;\n      float: right;\n      margin-right: 20px;\n      margin-top: 20px; }\n  .miniContacts > .table {\n    height: 325px;\n    overflow: auto;\n    display: none; }\n    .miniContacts > .table > .tr {\n      padding: 15px 0; }\n      .miniContacts > .table > .tr > .td {\n        float: left;\n        height: 40px;\n        position: relative; }\n        .miniContacts > .table > .tr > .td:nth-of-type(1) {\n          width: 85px; }\n        .miniContacts > .table > .tr > .td:nth-of-type(2) {\n          width: 180px; }\n        .miniContacts > .table > .tr > .td > .avatar {\n          margin-left: 15px; }\n        .miniContacts > .table > .tr > .td > .username {\n          color: #666666;\n          font: 400 14px / 40px Roboto;\n          width: 180px;\n          white-space: nowrap;\n          overflow: hidden;\n          text-overflow: ellipsis; }\n        .miniContacts > .table > .tr > .td > .onOrOffLineUser {\n          margin: 10px 15px; }\n  .miniContacts > .wrapMessages {\n    overflow: hidden; }\n    .miniContacts > .wrapMessages > .messages {\n      overflow: auto;\n      height: 265px;\n      width: 355px;\n      padding: 15px 35px 0 15px;\n      box-sizing: border-box; }\n      .miniContacts > .wrapMessages > .messages > .postInterlocutor {\n        margin-bottom: 40px; }\n        .miniContacts > .wrapMessages > .messages > .postInterlocutor > .avatar {\n          float: left; }\n        .miniContacts > .wrapMessages > .messages > .postInterlocutor > p {\n          margin-left: 15px;\n          color: #0c0c1e;\n          font: 400 12px Roboto;\n          float: left;\n          width: 190px; }\n      .miniContacts > .wrapMessages > .messages > .yourAnswer {\n        margin-bottom: 40px; }\n        .miniContacts > .wrapMessages > .messages > .yourAnswer > .avatar {\n          background: url(" + __webpack_require__(40) + ");\n          float: right; }\n        .miniContacts > .wrapMessages > .messages > .yourAnswer > p {\n          margin-right: 15px;\n          color: #0c0c1e;\n          font: 400 12px Roboto;\n          float: right;\n          width: 190px;\n          text-align: right; }\n    .miniContacts > .wrapMessages > .footer {\n      height: 60px;\n      border-top: 1px solid #DBDBDB;\n      padding: 15px;\n      box-sizing: border-box; }\n      .miniContacts > .wrapMessages > .footer > .inputForm {\n        border-color: #1875D0 !important;\n        color: #0c0c1e;\n        font: 400 12px Roboto;\n        width: 305px;\n        overflow: hidden; }\n        .miniContacts > .wrapMessages > .footer > .inputForm > .symbols {\n          display: none; }\n        .miniContacts > .wrapMessages > .footer > .inputForm > textarea {\n          border: none;\n          outline: none;\n          resize: none;\n          width: 322px;\n          padding-right: 45px;\n          box-sizing: border-box; }\n      .miniContacts > .wrapMessages > .footer > .clip {\n        background: url(" + __webpack_require__(41) + ") no-repeat center center;\n        height: 20px;\n        width: 20px;\n        position: absolute;\n        bottom: 15px;\n        right: 20px;\n        cursor: pointer;\n        z-index: 1; }\n", ""]);

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

	module.exports = __webpack_require__.p + "images/loopSearcg.png";

/***/ },
/* 39 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/cot.png";

/***/ },
/* 40 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/yourAvatar.png";

/***/ },
/* 41 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/clip.png";

/***/ },
/* 42 */
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
/* 43 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(44);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(42)(content, {});
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
/* 44 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, "@charset \"UTF-8\";\n#favourites {\n  width: 990px;\n  margin: 3px auto 0; }\n  #favourites .right-column {\n    width: 490px;\n    float: right; }\n  #favourites .left-column {\n    width: 490px;\n    float: left; }\n\n.bulletin-short {\n  position: relative;\n  padding: 20px 16px;\n  width: 490px;\n  margin-bottom: 7px;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  box-sizing: border-box;\n  background-color: white;\n  /* Левая колонка */\n  /* Правая колонка */ }\n  .bulletin-short > div {\n    display: inline-block; }\n  .bulletin-short .bulletin-right-column {\n    float: right;\n    width: 90px;\n    position: relative; }\n    .bulletin-short .bulletin-right-column > .onOrOffLineUser {\n      position: absolute;\n      top: 5px;\n      left: 10px; }\n  .bulletin-short .bulletin-center-column {\n    float: right;\n    margin-right: 15px; }\n  .bulletin-short h3 {\n    margin: 0;\n    font: 400 20px / 24px Roboto;\n    color: #212121;\n    width: 206px;\n    cursor: pointer;\n    -webkit-transition: all .25s;\n    transition: all .25s; }\n    .bulletin-short h3:hover {\n      text-decoration: underline;\n      -webkit-text-decoration-color: gray;\n              text-decoration-color: gray;\n      -webkit-text-decoration-style: dashed;\n              text-decoration-style: dashed; }\n  .bulletin-short .bulletin-category {\n    color: rgba(31, 31, 31, 0.54);\n    font: 400 12px / 14.4px Roboto;\n    margin-top: 11px;\n    margin-bottom: 16px; }\n  .bulletin-short .bulletin-description {\n    font: 400 12px / 18.6px Roboto;\n    width: 254px;\n    color: #0d0d1e; }\n  .bulletin-short .bulletin-image {\n    width: 90px;\n    height: 91px;\n    background: #1875D0 url(" + __webpack_require__(45) + "); }\n  .bulletin-short .bulletin-price {\n    position: absolute;\n    bottom: -54px;\n    right: 0;\n    color: #202020;\n    font: 400 14px / 16.8px Roboto; }\n  .bulletin-short .bulletin-action {\n    position: absolute;\n    bottom: 20px;\n    right: 117px;\n    color: #212121;\n    font: 400 14px / 16.8px Roboto; }\n  .bulletin-short > .wrapRibbon {\n    position: absolute;\n    position: absolute;\n    bottom: 0;\n    left: 0;\n    z-index: 1; }\n    .bulletin-short > .wrapRibbon > .ribbon {\n      width: 100px;\n      position: relative;\n      background-color: #F5911D;\n      text-align: center; }\n      .bulletin-short > .wrapRibbon > .ribbon:before {\n        content: \"\";\n        position: absolute;\n        display: block;\n        bottom: -14px;\n        border: 13px solid #e57b00;\n        z-index: -1;\n        left: -23px;\n        border-right-width: 1.5em;\n        border-left-color: transparent;\n        box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n      .bulletin-short > .wrapRibbon > .ribbon:after {\n        content: \"\";\n        position: absolute;\n        display: block;\n        bottom: 0;\n        border: 13px solid #F5911D;\n        right: -13px;\n        border-left-width: 0;\n        border-right-color: transparent; }\n      .bulletin-short > .wrapRibbon > .ribbon > .ribbon-content {\n        color: #ffffff;\n        font: 400 14px / 26px Roboto;\n        cursor: default; }\n        .bulletin-short > .wrapRibbon > .ribbon > .ribbon-content:before {\n          content: \"\";\n          position: absolute;\n          display: block;\n          border-style: solid;\n          border-color: #2B4A67 transparent transparent transparent;\n          bottom: -14px;\n          left: 0;\n          border-width: 1em 0 0 1em; }\n\n.checkBox {\n  width: 25px;\n  height: 25px;\n  border: 1px solid grey;\n  border-radius: 5px;\n  cursor: pointer;\n  -webkit-transition: all .25s;\n  transition: all .25s; }\n\n.checked {\n  background: #1875D0 url(" + __webpack_require__(46) + ") no-repeat center center;\n  border-color: #1875D0 !important; }\n\ndiv.exclamationPoint {\n  background: url(" + __webpack_require__(47) + ") no-repeat center center;\n  height: 27px;\n  width: 27px;\n  margin-top: 10px; }\n\n.dollarBill {\n  background: url(" + __webpack_require__(48) + ") no-repeat center center;\n  height: 27px;\n  width: 27px;\n  margin-top: 10px; }\n", ""]);

	// exports


/***/ },
/* 45 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/bulletin-default.png";

/***/ },
/* 46 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/V.png";

/***/ },
/* 47 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/exclamationPoint.png";

/***/ },
/* 48 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/dollarBill.png";

/***/ },
/* 49 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(50);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(42)(content, {});
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
/* 50 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, "section.editProfile {\n  background-color: #FDFDFD;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3);\n  width: 1105px;\n  margin: 5px auto 40px;\n  padding: 65px 0 45px 0;\n  box-sizing: border-box; }\n  section.editProfile > .view-edit-profile {\n    width: 650px;\n    margin: 0 auto; }\n    section.editProfile > .view-edit-profile > h2 {\n      font: 700 22px Roboto;\n      text-align: center;\n      margin-bottom: 40px; }\n    section.editProfile > .view-edit-profile > .edit-profile-form > .selectBox {\n      float: left;\n      border-bottom: 1px solid #9e9e9e;\n      margin-top: 15px;\n      margin-bottom: 45px; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .selectBox > .defaultValue {\n        color: #9e9e9e;\n        font: 400 16px Roboto;\n        padding-left: 5px; }\n    section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto {\n      float: right;\n      position: relative;\n      border-bottom: 1px solid #9e9e9e;\n      width: 410px;\n      padding-bottom: 5px; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto > input {\n        position: absolute;\n        z-index: -1; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto > p {\n        color: #9e9e9e;\n        font: 400 16px Roboto;\n        float: left;\n        padding-top: 11px;\n        padding-left: 5px;\n        cursor: default; }\n      section.editProfile > .view-edit-profile > .edit-profile-form > .edit-profile-form-foto > .btn-blue {\n        box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);\n        height: 30px;\n        width: 85px;\n        float: right;\n        line-height: 30px; }\n    section.editProfile > .view-edit-profile .edit-profile-form-contacts-container .inputForm {\n      width: 95%;\n      display: inline-block; }\n    section.editProfile > .view-edit-profile .edit-profile-form-contacts-container button {\n      width: 16px;\n      height: 16px;\n      display: inline-block;\n      background: url(" + __webpack_require__(51) + ") no-repeat;\n      background-size: contain; }\n    section.editProfile > .view-edit-profile .social-link-container {\n      height: auto; }\n      section.editProfile > .view-edit-profile .social-link-container div {\n        width: 24px;\n        height: 24px;\n        float: left;\n        margin-right: 47px; }\n  section.editProfile .uploadFileForm {\n    visibility: hidden; }\n    section.editProfile .uploadFileForm input {\n      width: 0px;\n      height: 0px; }\n", ""]);

	// exports


/***/ },
/* 51 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/icon_close_blue.png";

/***/ },
/* 52 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag

	// load the styles
	var content = __webpack_require__(53);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(42)(content, {});
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
/* 53 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(3)();
	// imports


	// module
	exports.push([module.id, ".view-profile-container {\n  background-color: #fff;\n  box-sizing: border-box;\n  padding: 40px 127px 30px;\n  margin: 5px auto 0;\n  width: 1103px;\n  font: 400 16px/24px Roboto;\n  box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.3); }\n  .view-profile-container h1 {\n    padding-bottom: 16px;\n    text-align: center;\n    font: 400 22px/26px Roboto; }\n  .view-profile-container input:-moz-read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n  .view-profile-container input:read-only {\n    width: 100%;\n    border: none;\n    outline: none; }\n    .view-profile-container input:-moz-read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n    .view-profile-container input:read-only:not(:last-of-type) {\n      margin-bottom: 45px; }\n  .view-profile-container .social-link-container {\n    margin-bottom: 45px; }\n\n.profile-containers-wrap {\n  width: 849px;\n  margin: 0 auto; }\n\n.profile-left-container, .profile-right-container, .profile-info-section-left, .profile-info-section-right, .profile-info {\n  float: left; }\n  .profile-left-container:before, .profile-left-container:after, .profile-right-container:before, .profile-right-container:after, .profile-info-section-left:before, .profile-info-section-left:after, .profile-info-section-right:before, .profile-info-section-right:after, .profile-info:before, .profile-info:after {\n    content: \" \";\n    display: table; }\n  .profile-left-container:after, .profile-right-container:after, .profile-info-section-left:after, .profile-info-section-right:after, .profile-info:after {\n    clear: both; }\n\n.view-profile:before, .view-profile:after, .view-profile-container:before, .view-profile-container:after {\n  content: \" \";\n  display: table; }\n\n.view-profile:after, .view-profile-container:after {\n  clear: both; }\n\n.profile-left-container {\n  width: 190px;\n  padding: 0 52px; }\n  .profile-left-container .btn-blue {\n    width: 180px;\n    height: 36px;\n    line-height: 36px;\n    margin: 0 auto;\n    margin-top: 9px; }\n  .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox {\n    display: block;\n    margin-bottom: 25px; }\n    .profile-left-container > .hz-centering-wrapper > .profile-settings > .selectBox > .defaultValue {\n      color: #9e9e9e;\n      font: 400 16px / 24px Roboto;\n      border-bottom: 1px solid #9e9e9e; }\n\n.profile-right-container {\n  width: 485px;\n  padding-left: 20px;\n  border-left: 1px solid #ebebeb; }\n  .profile-right-container > .wrapForDiv {\n    width: auto;\n    float: none;\n    margin-bottom: 0; }\n    .profile-right-container > .wrapForDiv > ul.tab > li {\n      width: 50%; }\n      .profile-right-container > .wrapForDiv > ul.tab > li > a {\n        width: auto; }\n    .profile-right-container > .wrapForDiv > .featuresAndReviews {\n      width: 500px; }\n\n.profile-info-section-left, .profile-info-section-right {\n  width: 50%;\n  box-sizing: border-box; }\n\n.profile-avatar {\n  width: 145px;\n  height: 215px;\n  margin: 0 auto 40px;\n  padding-bottom: 5px;\n  background: url(" + __webpack_require__(54) + ") no-repeat center center;\n  background-size: contain;\n  box-sizing: border-box; }\n\n.profile-settings-dropdown .profile-settings-dropdown-title {\n  cursor: pointer;\n  padding: 13px 0; }\n  .profile-settings-dropdown .profile-settings-dropdown-title:before, .profile-settings-dropdown .profile-settings-dropdown-title:after {\n    content: \" \";\n    display: table; }\n  .profile-settings-dropdown .profile-settings-dropdown-title:after {\n    clear: both; }\n  .profile-settings-dropdown .profile-settings-dropdown-title span {\n    color: #929292;\n    float: left; }\n  .profile-settings-dropdown .profile-settings-dropdown-title div {\n    float: right;\n    background: url(" + __webpack_require__(55) + ");\n    width: 13px;\n    height: 7px; }\n\n.social-link-container div {\n  cursor: pointer;\n  width: 24px;\n  height: 24px;\n  float: left; }\n  .social-link-container div:before, .social-link-container div:after {\n    content: \" \";\n    display: table; }\n  .social-link-container div:after {\n    clear: both; }\n  .social-link-container div:not(:last-of-type) {\n    margin-right: 23px; }\n\n.profile-messages-and-notifications {\n  display: block;\n  width: 100%;\n  margin: 30px 0;\n  float: left; }\n  .profile-messages-and-notifications:before, .profile-messages-and-notifications:after {\n    content: \" \";\n    display: table; }\n  .profile-messages-and-notifications:after {\n    clear: both; }\n\n.profile-messages-and-notifications-nav, .profile-messages-and-notifications-content {\n  border: 1px solid #ebebeb;\n  overflow: hidden; }\n\n.profile-messages-and-notifications-nav div {\n  color: #929292;\n  width: 50%;\n  padding: 24px 0;\n  text-align: center;\n  float: left; }\n  .profile-messages-and-notifications-nav div:before, .profile-messages-and-notifications-nav div:after {\n    content: \" \";\n    display: table; }\n  .profile-messages-and-notifications-nav div:after {\n    clear: both; }\n\n.profile-messages-and-notifications-nav .nav-item-selected {\n  color: #7eaee0;\n  border-bottom: 2px solid #ff5252; }\n\n.profile-messages-and-notifications-content {\n  background-color: #f6f6f6;\n  height: 180px;\n  box-sizing: border-box; }\n\n.profile-messages-and-notifications-content-item {\n  width: 100%;\n  border-bottom: 1px solid #ebebeb;\n  height: 123px;\n  position: relative;\n  overflow: hidden; }\n  .profile-messages-and-notifications-content-item .content-item-logo {\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 27px;\n    height: 27px;\n    padding: 0 23px 23px 7px;\n    background: url(" + __webpack_require__(56) + ") no-repeat;\n    display: inline-block;\n    background-position: center;\n    margin-top: 28px; }\n  .profile-messages-and-notifications-content-item .content-item-text {\n    display: inline-block;\n    float: right;\n    width: 373px;\n    margin: 28px 38px 24px 0;\n    font: 400 12px/18px Roboto;\n    color: #0c0c1e; }\n    .profile-messages-and-notifications-content-item .content-item-text:before, .profile-messages-and-notifications-content-item .content-item-text:after {\n      content: \" \";\n      display: table; }\n    .profile-messages-and-notifications-content-item .content-item-text:after {\n      clear: both; }\n\n.profile-info {\n  margin-bottom: 30px; }\n", ""]);

	// exports


/***/ },
/* 54 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/avatar.jpg";

/***/ },
/* 55 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/caret_black.png";

/***/ },
/* 56 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/icon_user.png";

/***/ },
/* 57 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var debug = __webpack_require__(58);

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
/* 58 */
/***/ function(module, exports) {

	module.exports = {
		"DEBUG": 0,
		"ONLY_ERRORS": 1,
		"PRODUCTION": 2
	};

/***/ },
/* 59 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var MODULES = {
	  "checkbox": __webpack_require__(60),
	  "niceButton": __webpack_require__(61),
	  "text": __webpack_require__(62),
	  "selectBox": __webpack_require__(63),
	  "textArea": __webpack_require__(64)
	};

	window.ee = __webpack_require__(65);
	ee.init();

	var ctx = module.exports = {};

	module.exports.init = function (app) {
	  for (var key in MODULES) {
	    app.directive(key, MODULES[key]);
	  }return app;
		};

/***/ },
/* 60 */
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
/* 61 */
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
/* 62 */
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
/* 63 */
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
/* 64 */
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
/* 65 */
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
/* 66 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	module.exports = {
	  "/": {
	    templateUrl: "templates/index.html",
	    controller: __webpack_require__(67),
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
	    controller: __webpack_require__(68),
	    controllerAs: "bdetailed"
	  },
	  '/bulletinAdd': {
	    templateUrl: "templates/authenticated/bulletinAdd.html",
	    controller: __webpack_require__(69),
	    controllerAs: "badd"
	  },
	  '/login': {
	    templateUrl: "templates/login.html",
	    controller: __webpack_require__(70),
	    controllerAs: "login"
	  },
	  '/register': {
	    templateUrl: "templates/register.html",
	    controller: __webpack_require__(71),
	    controllerAs: "register"
	  },
	  '/editProfile': {
	    templateUrl: "templates/authenticated/edit-profile.html",
	    controller: __webpack_require__(72),
	    controllerAs: "profile"
	  },
	  '/profile': {
	    templateUrl: "templates/authenticated/profile.html",
	    controller: __webpack_require__(73),
	    controllerAs: "profile"
	  },
	  '/favourites': {
	    templateUrl: "templates/authenticated/favourites.html",
	    controller: __webpack_require__(74),
	    controllerAs: "favourite"
	  },
	  '/searchResults': {
	    templateUrl: "templates/searchResults.html",
	    controller: __webpack_require__(75),
	    controllerAs: "searchResults"
	  },
	  '/mail': {
	    templateUrl: "templates/authenticated/mail.html"
	  }
		};

/***/ },
/* 67 */
/***/ function(module, exports) {

	"use strict";

		module.exports = function ($scope) {};

/***/ },
/* 68 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 69 */
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
/* 70 */
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
/* 71 */
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
/* 72 */
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
/* 73 */
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

	        // if(!$scope.$parent.db.user)
	        //   $scope.$parent.redirectToUrl('/403', true)
	        // else
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
/* 74 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function ($scope) {};

/***/ },
/* 75 */
/***/ function(module, exports) {

	'use strict';

	module.exports = function () {};

/***/ },
/* 76 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	var ctx = module.exports = {};
	/* Контроллер для управления  основным скелетом документа */
	module.exports = function ($http, $scope, $location, $timeout, $cookies, $cookieStore) {
	  var _this = this;

	  console.log('Main controller loaded');
	  console.log($cookies);
	  /* Standalone module for bd */
	  $scope.db = __webpack_require__(77);
	  $scope.db.init($http);
	  window.db = $scope.db;

	  /* Initialize data */
	  this.init = function () {
	    /* variables for testing */
	    this.hello = "hi";
	    this.boolean = true;
	    this.list = [1, 2, 3];
	    /* End variables for testing */

	    this.loader = __webpack_require__(80);
	    this.loader($scope, $timeout);

	    console.log("Main controller init");

	    this.sortingCategories = __webpack_require__(81).items;
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
/* 77 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var utils = __webpack_require__(78),
	    config = __webpack_require__(79);

	var ctx = module.exports = {};

	module.exports.init = function ($http) {
	  /* init data from database here */
	  ctx.setDefaults();
	  this.transport = $http;

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
	  ctx.transport({
	    method: config.routes.checkLogged.method,
	    url: config.api.url + config.routes.checkLogged.url,
	    withCredentials: true
	  }).then(function (data) {
	    return cb(null, data);
	  }).catch(cb);
	  // utils.request({
	  //   "method" : config.routes.checkLogged.method,
	  //   "url" : config.api.url + config.routes.checkLogged.url
	  // }).then(data => cb(null, data), err => cb(err))
	};

	module.exports.userLogout = function () {
	  ctx.setDefaults();

	  utils.request({
	    "method": config.routes.logout.method,
	    "url": config.api.auth + config.routes.logout.url
	  }).then(function () {}, function () {});
		};

/***/ },
/* 78 */
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
/* 79 */
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
/* 80 */
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
/* 81 */
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
/* 82 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/back.png";

/***/ },
/* 83 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/close.png";

/***/ },
/* 84 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/toContacts.png";

/***/ },
/* 85 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/backgroundVidgets.png";

/***/ },
/* 86 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/rypr.png";

/***/ },
/* 87 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/tender.png";

/***/ },
/* 88 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/frilans.png";

/***/ },
/* 89 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/tehnologii.png";

/***/ },
/* 90 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/rabota.png";

/***/ },
/* 91 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__.p + "images/proekti.png";

/***/ }
/******/ ]);
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXBwLmpzIiwic291cmNlcyI6WyJ3ZWJwYWNrOi8vL3dlYnBhY2svYm9vdHN0cmFwIDA3ZWM2ODRlNTE0OWQyMzkwYzAyIiwid2VicGFjazovLy9hcHAuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Jhc2ljLnNjc3M/NGY1MyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvYmFzaWMuc2NzcyIsIndlYnBhY2s6Ly8vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzIiwid2VicGFjazovLy8uL2ltYWdlcy9jYWxlbmRhci5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9sb2dvLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvbWFpbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL21haWxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYmVsbC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2JlbGxfc2hhZG93LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvc2VydmljZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy91c2VyTmFtZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL25leHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9wcmV2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXJyb3JfYmcucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy92ay5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2ZhY2Vib29rLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZ29vZ2xlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9tYXAucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jYXJldFJpZ2h0TmF2LnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvRm9yQ2hpbGRyZW4ucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9Gb3JBbmltYWxzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQnVzaW5lc3MucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL1RyYW5zcG9ydC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvVm9sdW50ZWVyaW5nLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvSXNGcmVlLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9CYXJ0ZXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9UZWNobm9sb2dpZXMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9ydXByLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvcnVwckhvdmVyLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvbG9vcFNlYXJjZy5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2NvdC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL3lvdXJBdmF0YXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jbGlwLnBuZyIsIndlYnBhY2s6Ly8vLi9+L3N0eWxlLWxvYWRlci9hZGRTdHlsZXMuanMiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL2Zhdm91cml0ZXMuc2Nzcz9jZjI3Iiwid2VicGFjazovLy8uL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3MiLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9WLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2RvbGxhckJpbGwucG5nIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzcz9kYzJiIiwid2VicGFjazovLy8uL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZyIsIndlYnBhY2s6Ly8vLi9zdHlsZXMvcHJvZmlsZS5zY3NzPzAxYTEiLCJ3ZWJwYWNrOi8vLy4vc3R5bGVzL3Byb2ZpbGUuc2NzcyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvYXZhdGFyLmpwZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9pY29uX3VzZXIucG5nIiwid2VicGFjazovLy9tb2R1bGVzL2xvZ2dlci5qcyIsIndlYnBhY2s6Ly8vLi9kYXRhL2RlYnVnLmpzb24iLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL2NoZWNrYm94LmpzIiwid2VicGFjazovLy9tb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL25pY2VCdXR0b24uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dC5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy9zZWxlY3RCb3guanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvdGV4dEFyZWEuanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvbWF0ZXJpYWxzL2V2ZW50cy5qcyIsIndlYnBhY2s6Ly8vbW9kdWxlcy9yb3V0ZXIuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2luZGV4LmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9idWxsZXRpbkRldGFpbHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvYnVsbGV0aW5BZGQuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL2xvZ2luLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9yZWdpc3Rlci5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZS5qcyIsIndlYnBhY2s6Ly8vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlLmpzIiwid2VicGFjazovLy9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2Zhdm91cml0ZXMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL3NlYXJjaFJlc3VsdHMuanMiLCJ3ZWJwYWNrOi8vL2NvbnRyb2xsZXJzL21haW4uanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvZGIuanMiLCJ3ZWJwYWNrOi8vL21vZHVsZXMvdXRpbHMuanMiLCJ3ZWJwYWNrOi8vLy4vZGF0YS9jb25maWcuanNvbiIsIndlYnBhY2s6Ly8vbW9kdWxlcy9sb2FkZXIuanMiLCJ3ZWJwYWNrOi8vLy4vZGF0YS9zb3J0aW5nLmpzb24iLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL2JhY2sucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9jbG9zZS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL3RvQ29udGFjdHMucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9iYWNrZ3JvdW5kVmlkZ2V0cy5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL3J5cHIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy90ZW5kZXIucG5nIiwid2VicGFjazovLy8uL2ltYWdlcy9mcmlsYW5zLnBuZyIsIndlYnBhY2s6Ly8vLi9pbWFnZXMvdGVobm9sb2dpaS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL3JhYm90YS5wbmciLCJ3ZWJwYWNrOi8vLy4vaW1hZ2VzL3Byb2VrdGkucG5nIl0sInNvdXJjZXNDb250ZW50IjpbIiBcdC8vIFRoZSBtb2R1bGUgY2FjaGVcbiBcdHZhciBpbnN0YWxsZWRNb2R1bGVzID0ge307XG5cbiBcdC8vIFRoZSByZXF1aXJlIGZ1bmN0aW9uXG4gXHRmdW5jdGlvbiBfX3dlYnBhY2tfcmVxdWlyZV9fKG1vZHVsZUlkKSB7XG5cbiBcdFx0Ly8gQ2hlY2sgaWYgbW9kdWxlIGlzIGluIGNhY2hlXG4gXHRcdGlmKGluc3RhbGxlZE1vZHVsZXNbbW9kdWxlSWRdKVxuIFx0XHRcdHJldHVybiBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXS5leHBvcnRzO1xuXG4gXHRcdC8vIENyZWF0ZSBhIG5ldyBtb2R1bGUgKGFuZCBwdXQgaXQgaW50byB0aGUgY2FjaGUpXG4gXHRcdHZhciBtb2R1bGUgPSBpbnN0YWxsZWRNb2R1bGVzW21vZHVsZUlkXSA9IHtcbiBcdFx0XHRleHBvcnRzOiB7fSxcbiBcdFx0XHRpZDogbW9kdWxlSWQsXG4gXHRcdFx0bG9hZGVkOiBmYWxzZVxuIFx0XHR9O1xuXG4gXHRcdC8vIEV4ZWN1dGUgdGhlIG1vZHVsZSBmdW5jdGlvblxuIFx0XHRtb2R1bGVzW21vZHVsZUlkXS5jYWxsKG1vZHVsZS5leHBvcnRzLCBtb2R1bGUsIG1vZHVsZS5leHBvcnRzLCBfX3dlYnBhY2tfcmVxdWlyZV9fKTtcblxuIFx0XHQvLyBGbGFnIHRoZSBtb2R1bGUgYXMgbG9hZGVkXG4gXHRcdG1vZHVsZS5sb2FkZWQgPSB0cnVlO1xuXG4gXHRcdC8vIFJldHVybiB0aGUgZXhwb3J0cyBvZiB0aGUgbW9kdWxlXG4gXHRcdHJldHVybiBtb2R1bGUuZXhwb3J0cztcbiBcdH1cblxuXG4gXHQvLyBleHBvc2UgdGhlIG1vZHVsZXMgb2JqZWN0IChfX3dlYnBhY2tfbW9kdWxlc19fKVxuIFx0X193ZWJwYWNrX3JlcXVpcmVfXy5tID0gbW9kdWxlcztcblxuIFx0Ly8gZXhwb3NlIHRoZSBtb2R1bGUgY2FjaGVcbiBcdF9fd2VicGFja19yZXF1aXJlX18uYyA9IGluc3RhbGxlZE1vZHVsZXM7XG5cbiBcdC8vIF9fd2VicGFja19wdWJsaWNfcGF0aF9fXG4gXHRfX3dlYnBhY2tfcmVxdWlyZV9fLnAgPSBcIlwiO1xuXG4gXHQvLyBMb2FkIGVudHJ5IG1vZHVsZSBhbmQgcmV0dXJuIGV4cG9ydHNcbiBcdHJldHVybiBfX3dlYnBhY2tfcmVxdWlyZV9fKDApO1xuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogd2VicGFjay9ib290c3RyYXAgMDdlYzY4NGU1MTQ5ZDIzOTBjMDJcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbnJlcXVpcmUoXCIuL3N0eWxlcy9iYXNpYy5zY3NzXCIpXHJcbnJlcXVpcmUoXCIuL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3NcIilcclxucmVxdWlyZShcIi4vc3R5bGVzL2VkaXQtcHJvZmlsZS5zY3NzXCIpXHJcbnJlcXVpcmUoXCIuL3N0eWxlcy9wcm9maWxlLnNjc3NcIilcclxuXHJcbnJlcXVpcmUoXCIuL21vZHVsZXMvbG9nZ2VyXCIpKClcclxuXHJcbmNvbnN0IG1hdGVyaWFscyA9IHJlcXVpcmUoJy4vbW9kdWxlcy9tYXRlcmlhbHMvaW5kZXguanMnKSxcclxuICAgICAgcm91dGVyID0gcmVxdWlyZSgnLi9tb2R1bGVzL3JvdXRlcicpXHJcblxyXG5sZXQgYXBwID0gYW5ndWxhci5tb2R1bGUoJ2d1cCcsIFsnbmdSb3V0ZScsICduZ0Nvb2tpZXMnXSlcclxuXHJcbi8vIEFwcCBjb25maWdcclxuYXBwXHJcbiAgLmNvbmZpZyhbJyRyb3V0ZVByb3ZpZGVyJywgJyRsb2NhdGlvblByb3ZpZGVyJywgZnVuY3Rpb24oICRyb3V0ZVByb3ZpZGVyLCAkbG9jYXRpb25Qcm92aWRlcil7XHJcbiAgICBmb3IobGV0IGtleSBpbiByb3V0ZXIpXHJcbiAgICAgICRyb3V0ZVByb3ZpZGVyLndoZW4oa2V5LCByb3V0ZXJba2V5XSlcclxuXHJcbiAgICAkcm91dGVQcm92aWRlci5vdGhlcndpc2Uoe1xyXG4gICAgICByZWRpcmVjdFRvOiAnLzQwNCdcclxuICAgIH0pXHJcblxyXG4gICAgJGxvY2F0aW9uUHJvdmlkZXIuaHRtbDVNb2RlKHtcclxuICAgICAgZW5hYmxlZCA6IHRydWUsXHJcbiAgICAgIHJlcXVpcmVCYXNlIDogZmFsc2VcclxuICAgIH0pXHJcbiAgfV0pXHJcbiAgLmNvbnRyb2xsZXIoJ21haW5DdHJsJywgcmVxdWlyZSgnLi9jb250cm9sbGVycy9tYWluJykpXHJcblxyXG5tYXRlcmlhbHNcclxuICAuaW5pdChhcHApXHJcbiAgLnJ1bigpXHJcblxyXG4gIC8qIEV2ZW50IGVtbWl0dGVyIGV4YW1wbGVzICovXHJcbiAgbGV0IGlkID0gZWUub24oJ211aGFoYWhhJywgZnVuY3Rpb24oZGF0YSkge1xyXG4gICAgY29uc29sZS5sb2coXCJidWdhZ2FzaGVjaGtvXCIpXHJcbiAgICBjb25zb2xlLmxvZyhkYXRhKVxyXG4gIH0pXHJcblxyXG4gIGVlLmVtaXQoe1xyXG4gICAgbmFtZSA6IFwibXVoYWhhaGFcIixcclxuICAgIGRhdGEgOiBbMSwyLDMsNCw1XVxyXG4gIH0pXHJcblxyXG4gIGVlLm9mZihpZClcclxuXHJcbiAgZWUuZW1pdCh7XHJcbiAgICBuYW1lIDogXCJtdWhhaGFoYVwiLFxyXG4gICAgZGF0YSA6IFsxLDIsMyw0LDVdXHJcbiAgfSlcclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogYXBwLmpzXG4gKiovIiwiLy8gc3R5bGUtbG9hZGVyOiBBZGRzIHNvbWUgY3NzIHRvIHRoZSBET00gYnkgYWRkaW5nIGEgPHN0eWxlPiB0YWdcblxuLy8gbG9hZCB0aGUgc3R5bGVzXG52YXIgY29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9iYXNpYy5zY3NzXCIpO1xuaWYodHlwZW9mIGNvbnRlbnQgPT09ICdzdHJpbmcnKSBjb250ZW50ID0gW1ttb2R1bGUuaWQsIGNvbnRlbnQsICcnXV07XG4vLyBhZGQgdGhlIHN0eWxlcyB0byB0aGUgRE9NXG52YXIgdXBkYXRlID0gcmVxdWlyZShcIiEuLy4uL25vZGVfbW9kdWxlcy9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXCIpKGNvbnRlbnQsIHt9KTtcbmlmKGNvbnRlbnQubG9jYWxzKSBtb2R1bGUuZXhwb3J0cyA9IGNvbnRlbnQubG9jYWxzO1xuLy8gSG90IE1vZHVsZSBSZXBsYWNlbWVudFxuaWYobW9kdWxlLmhvdCkge1xuXHQvLyBXaGVuIHRoZSBzdHlsZXMgY2hhbmdlLCB1cGRhdGUgdGhlIDxzdHlsZT4gdGFnc1xuXHRpZighY29udGVudC5sb2NhbHMpIHtcblx0XHRtb2R1bGUuaG90LmFjY2VwdChcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Jhc2ljLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9iYXNpYy5zY3NzXCIpO1xuXHRcdFx0aWYodHlwZW9mIG5ld0NvbnRlbnQgPT09ICdzdHJpbmcnKSBuZXdDb250ZW50ID0gW1ttb2R1bGUuaWQsIG5ld0NvbnRlbnQsICcnXV07XG5cdFx0XHR1cGRhdGUobmV3Q29udGVudCk7XG5cdFx0fSk7XG5cdH1cblx0Ly8gV2hlbiB0aGUgbW9kdWxlIGlzIGRpc3Bvc2VkLCByZW1vdmUgdGhlIDxzdHlsZT4gdGFnc1xuXHRtb2R1bGUuaG90LmRpc3Bvc2UoZnVuY3Rpb24oKSB7IHVwZGF0ZSgpOyB9KTtcbn1cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vc3R5bGVzL2Jhc2ljLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSAxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJleHBvcnRzID0gbW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcIikoKTtcbi8vIGltcG9ydHNcblxuXG4vLyBtb2R1bGVcbmV4cG9ydHMucHVzaChbbW9kdWxlLmlkLCBcImJvZHkge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0VDRUNFQzsgfVxcblxcbi5jYWxlbmRhciwgLmFkZENhbGVuZGFyIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FsZW5kYXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdDtcXG4gIHBhZGRpbmctbGVmdDogNDVweDtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBtYXJnaW4tYm90dG9tOiA0MHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgLmNhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSwgLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCBncmV5O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgcmlnaHQgNXB4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIHBhZGRpbmctcmlnaHQ6IDE1cHg7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgLmNhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSA+IHAsIC5hZGRDYWxlbmRhciA+IC5kZWZhdWx0VmFsdWUgPiBwIHtcXG4gICAgICB0ZXh0LWFsaWduOiBsZWZ0O1xcbiAgICAgIGNvbG9yOiAjMjYyNjI2O1xcbiAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjBweCBSb2JvdG87IH1cXG4gIC5jYWxlbmRhciA+IC5saXN0VmFsdWUsIC5hZGRDYWxlbmRhciA+IC5saXN0VmFsdWUge1xcbiAgICBkaXNwbGF5OiBub25lOyB9XFxuXFxuaGVhZGVyIHtcXG4gIGhlaWdodDogNzBweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMTg3NUQwOyB9XFxuXFxuLmJ0bi1ibHVlLCAuYnRuLWdyZXkge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gIGJvcmRlci1yYWRpdXM6IDVweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICB0ZXh0LWFsaWduOiBjZW50ZXI7IH1cXG5cXG4uYnRuLWdyZXkge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI0Q4RDhEODtcXG4gIGNvbG9yOiAjODY4Njg2O1xcbiAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bzsgfVxcblxcbi5jb250YWluZXIge1xcbiAgd2lkdGg6IDEyODBweDtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIG1hcmdpbjogMCBhdXRvOyB9XFxuXFxuLmNsZWFyZml4OmJlZm9yZSwgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5jb250YWN0c1RhYiA+IC50YWJsZSA+IC50cjpiZWZvcmUsIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAuY29udGFjdHNUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6YmVmb3JlLCAuZGlhbG9nID4gLndyYXBNZXNzYWdlcyA+IC5tZXNzYWdlcyA+IC5wb3N0SW50ZXJsb2N1dG9yOmJlZm9yZSwgLmRpYWxvZyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAueW91ckFuc3dlcjpiZWZvcmUsIC5kaWFsb2cgPiAuZm9vdGVyOmJlZm9yZSwgLm1pbmlDb250YWN0cyA+IC5oZWFkZXI6YmVmb3JlLCAubWluaUNvbnRhY3RzID4gLnRhYmxlID4gLnRyOmJlZm9yZSwgLm1pbmlDb250YWN0cyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAucG9zdEludGVybG9jdXRvcjpiZWZvcmUsIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnlvdXJBbnN3ZXI6YmVmb3JlLCAuY2xlYXJmaXg6YWZ0ZXIsIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAuY29udGFjdHNUYWIgPiAudGFibGUgPiAudHI6YWZ0ZXIsIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAuY29udGFjdHNUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6YWZ0ZXIsIC5kaWFsb2cgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnBvc3RJbnRlcmxvY3V0b3I6YWZ0ZXIsIC5kaWFsb2cgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnlvdXJBbnN3ZXI6YWZ0ZXIsIC5kaWFsb2cgPiAuZm9vdGVyOmFmdGVyLCAubWluaUNvbnRhY3RzID4gLmhlYWRlcjphZnRlciwgLm1pbmlDb250YWN0cyA+IC50YWJsZSA+IC50cjphZnRlciwgLm1pbmlDb250YWN0cyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAucG9zdEludGVybG9jdXRvcjphZnRlciwgLm1pbmlDb250YWN0cyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAueW91ckFuc3dlcjphZnRlciB7XFxuICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICBkaXNwbGF5OiB0YWJsZTsgfVxcblxcbi5jbGVhcmZpeDphZnRlciwgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5jb250YWN0c1RhYiA+IC50YWJsZSA+IC50cjphZnRlciwgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5jb250YWN0c1RhYiA+IC50YWJsZSA+IC50ciA+IC50ZDphZnRlciwgLmRpYWxvZyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAucG9zdEludGVybG9jdXRvcjphZnRlciwgLmRpYWxvZyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAueW91ckFuc3dlcjphZnRlciwgLmRpYWxvZyA+IC5mb290ZXI6YWZ0ZXIsIC5taW5pQ29udGFjdHMgPiAuaGVhZGVyOmFmdGVyLCAubWluaUNvbnRhY3RzID4gLnRhYmxlID4gLnRyOmFmdGVyLCAubWluaUNvbnRhY3RzID4gLndyYXBNZXNzYWdlcyA+IC5tZXNzYWdlcyA+IC5wb3N0SW50ZXJsb2N1dG9yOmFmdGVyLCAubWluaUNvbnRhY3RzID4gLndyYXBNZXNzYWdlcyA+IC5tZXNzYWdlcyA+IC55b3VyQW5zd2VyOmFmdGVyIHtcXG4gIGNsZWFyOiBib3RoOyB9XFxuXFxuLmluayB7XFxuICBkaXNwbGF5OiBibG9jaztcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIGJhY2tncm91bmQ6IHJnYmEoMCwgMCwgMCwgMC4xNSk7XFxuICBib3JkZXItcmFkaXVzOiAxMDAlO1xcbiAgLXdlYmtpdC10cmFuc2Zvcm06IHNjYWxlKDApO1xcbiAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDApOyB9XFxuXFxuLmluay5hbmltYXRlIHtcXG4gIC13ZWJraXQtYW5pbWF0aW9uOiByaXBwbGUgLjVzIGVhc2UtaW47XFxuICAgICAgICAgIGFuaW1hdGlvbjogcmlwcGxlIC41cyBlYXNlLWluOyB9XFxuXFxuQC13ZWJraXQta2V5ZnJhbWVzIHJpcHBsZSB7XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMDtcXG4gICAgLXdlYmtpdC10cmFuc2Zvcm06IHNjYWxlKDIuNSk7XFxuICAgICAgICAgICAgdHJhbnNmb3JtOiBzY2FsZSgyLjUpOyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIHJpcHBsZSB7XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMDtcXG4gICAgLXdlYmtpdC10cmFuc2Zvcm06IHNjYWxlKDIuNSk7XFxuICAgICAgICAgICAgdHJhbnNmb3JtOiBzY2FsZSgyLjUpOyB9IH1cXG5cXG4uaGVhZExlZnQge1xcbiAgcGFkZGluZy10b3A6IDVweDtcXG4gIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gIHdpZHRoOiBjYWxjKDEwMCUgLSA0OTBweCk7XFxuICBoZWlnaHQ6IDcwcHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAuaGVhZExlZnQgPiAubG9nbyB7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgYm9yZGVyLXJhZGl1czogNTAlO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgaGVpZ2h0OiA2MHB4O1xcbiAgICB3aWR0aDogNjBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9sb2dvLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgIG1hcmdpbi1sZWZ0OiAxNXB4OyB9XFxuICAuaGVhZExlZnQgPiAuaW5wdXRGb3JtIHtcXG4gICAgbWFyZ2luLXRvcDogMjFweDtcXG4gICAgbWFyZ2luLWxlZnQ6IDIwcHg7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBib3JkZXItY29sb3I6ICNGREZERkQ7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgIGhlaWdodDogYXV0bztcXG4gICAgd2lkdGg6IDIwMHB4O1xcbiAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0ZERkRGRDsgfVxcbiAgICAuaGVhZExlZnQgPiAuaW5wdXRGb3JtID4gbGFiZWwsIC5oZWFkTGVmdCA+IC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxNHB4IFJvYm90bzsgfVxcbiAgLmhlYWRMZWZ0ID4gLnNlbGVjdEJveCB7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICBtYXJnaW4tdG9wOiAyMXB4O1xcbiAgICBtYXJnaW4tbGVmdDogMjBweDsgfVxcbiAgICAuaGVhZExlZnQgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgYmFja2dyb3VuZDogbm9uZTtcXG4gICAgICBwYWRkaW5nOiAwIDVweDsgfVxcbiAgLmhlYWRMZWZ0ID4gLmFkZCB7XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAyMDBweDtcXG4gICAgbWFyZ2luLXJpZ2h0OiAyMHB4O1xcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2FkZC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCAxMHB4O1xcbiAgICBwYWRkaW5nLWxlZnQ6IDEwcHg7XFxuICAgIHRleHQtYWxpZ246IGxlZnQ7XFxuICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgIGJvcmRlcjogMXB4IHNvbGlkIHdoaXRlO1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMTVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjE1czsgfVxcbiAgICAuaGVhZExlZnQgPiAuYWRkID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDE0cHggLyAzNXB4IFJvYm90bzsgfVxcblxcbi5oZWFkUmlnaHQge1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgd2lkdGg6IDQ5MHB4O1xcbiAgaGVpZ2h0OiA3MHB4O1xcbiAgYm9yZGVyLWxlZnQ6IDFweCBzb2xpZCBncmV5O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIHBhZGRpbmctdG9wOiAyMnB4OyB9XFxuICAuaGVhZFJpZ2h0ID4gLm1haWwge1xcbiAgICBoZWlnaHQ6IDI2cHg7XFxuICAgIHdpZHRoOiAzM3B4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbWFpbC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIG1hcmdpbi1sZWZ0OiAyMHB4O1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLm1haWwgPiBwIHtcXG4gICAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgICBmb250OiA0MDAgMTBweCAvIDE1cHggUm9ib3RvO1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBib3R0b206IC0zcHg7XFxuICAgICAgbGVmdDogMzJweDtcXG4gICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5tYWlsID4gLmNob29zZVlvdXJTdHlsZSB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI2ZmZmZmZjtcXG4gICAgICBoZWlnaHQ6IDkwcHg7XFxuICAgICAgd2lkdGg6IDIwMHB4O1xcbiAgICAgIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIHRvcDogMzVweDtcXG4gICAgICBsZWZ0OiA1MCU7XFxuICAgICAgbWFyZ2luLWxlZnQ6IC0xMDBweDtcXG4gICAgICB6LWluZGV4OiAxOyB9XFxuICAgICAgLmhlYWRSaWdodCA+IC5tYWlsID4gLmNob29zZVlvdXJTdHlsZSA+IHAge1xcbiAgICAgICAgY29sb3I6ICMzMzMzMzM7XFxuICAgICAgICBmb250OiA0MDAgMTRweCAvIDQ1cHggUm9ib3RvO1xcbiAgICAgICAgcGFkZGluZy1sZWZ0OiAxNXB4O1xcbiAgICAgICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgICAuaGVhZFJpZ2h0ID4gLm1haWwgPiAuY2hvb3NlWW91clN0eWxlID4gcDpob3ZlciB7XFxuICAgICAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0VCRUJFQjsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLm1haWw6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbWFpbF9zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLmJlbGwge1xcbiAgICBoZWlnaHQ6IDI0cHg7XFxuICAgIHdpZHRoOiAyM3B4O1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLWxlZnQ6IDMwcHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmVsbC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIGJvcmRlci1yYWRpdXM6IDE1cHggMCAxNXB4IDEwcHg7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuYmVsbCA+IHAge1xcbiAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgIGZvbnQ6IDQwMCAxMHB4IC8gMTVweCBSb2JvdG87XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJvdHRvbTogLTdweDtcXG4gICAgICBsZWZ0OiAyMnB4O1xcbiAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLmJlbGw6aG92ZXIge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmVsbF9zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzIHtcXG4gICAgaGVpZ2h0OiAyN3B4O1xcbiAgICB3aWR0aDogMjhweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMzBweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9zZXJ2aWNlcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgLmhlYWRSaWdodCA+IC5zZXJ2aWNlczpob3ZlciB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9zZXJ2aWNlc19zaGFkb3cucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXMgPiAudmlkZ2V0cyB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGhlaWdodDogMjQ0cHg7XFxuICAgICAgd2lkdGg6IDMyN3B4O1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmFja2dyb3VuZFZpZGdldHMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgICAgIHRvcDogMjZweDtcXG4gICAgICByaWdodDogLTIwcHg7XFxuICAgICAgei1pbmRleDogMTtcXG4gICAgICBjdXJzb3I6IGRlZmF1bHQ7XFxuICAgICAgcGFkZGluZzogMjBweCAzMHB4O1xcbiAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzID4gLnZpZGdldHMgPiBoMiB7XFxuICAgICAgICBjb2xvcjogIzIxMjEyMTtcXG4gICAgICAgIGZvbnQ6IDcwMCAxOHB4IFJvYm90bztcXG4gICAgICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgICAgIG1hcmdpbi1ib3R0b206IDI1cHg7IH1cXG4gICAgICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzID4gLnZpZGdldHMgPiBhIHtcXG4gICAgICAgIGhlaWdodDogNjVweDtcXG4gICAgICAgIG1hcmdpbi1sZWZ0OiAzMHB4O1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICBjb2xvcjogIzU0NmU3YTtcXG4gICAgICAgIGZvbnQ6IDQwMCAxNHB4IFJvYm90bztcXG4gICAgICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gICAgICAgIG1hcmdpbi1ib3R0b206IDI1cHg7IH1cXG4gICAgICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXMgPiAudmlkZ2V0cyA+IGEgPiBwIHtcXG4gICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICBib3R0b206IDA7IH1cXG4gICAgICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXMgPiAudmlkZ2V0cyA+IGE6bnRoLW9mLXR5cGUoMSkge1xcbiAgICAgICAgICBtYXJnaW4tbGVmdDogMDtcXG4gICAgICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9yeXByLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgdG9wIGNlbnRlcjtcXG4gICAgICAgICAgd2lkdGg6IDgwcHg7IH1cXG4gICAgICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXMgPiAudmlkZ2V0cyA+IGE6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3RlbmRlci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IHRvcCBjZW50ZXI7XFxuICAgICAgICAgIHdpZHRoOiA2MHB4O1xcbiAgICAgICAgICBvcGFjaXR5OiAwLjU7XFxuICAgICAgICAgIGN1cnNvcjogbm90LWFsbG93ZWQ7IH1cXG4gICAgICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXMgPiAudmlkZ2V0cyA+IGE6bnRoLW9mLXR5cGUoMykge1xcbiAgICAgICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2ZyaWxhbnMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCB0b3AgY2VudGVyO1xcbiAgICAgICAgICB3aWR0aDogNjBweDtcXG4gICAgICAgICAgb3BhY2l0eTogMC41O1xcbiAgICAgICAgICBjdXJzb3I6IG5vdC1hbGxvd2VkOyB9XFxuICAgICAgICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzID4gLnZpZGdldHMgPiBhOm50aC1vZi10eXBlKDQpIHtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IDA7XFxuICAgICAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvdGVobm9sb2dpaS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IHRvcCBjZW50ZXI7XFxuICAgICAgICAgIHdpZHRoOiA3NXB4O1xcbiAgICAgICAgICBvcGFjaXR5OiAwLjU7XFxuICAgICAgICAgIGN1cnNvcjogbm90LWFsbG93ZWQ7IH1cXG4gICAgICAgIC5oZWFkUmlnaHQgPiAuc2VydmljZXMgPiAudmlkZ2V0cyA+IGE6bnRoLW9mLXR5cGUoNSkge1xcbiAgICAgICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3JhYm90YS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IHRvcCBjZW50ZXI7XFxuICAgICAgICAgIHdpZHRoOiA1MHB4O1xcbiAgICAgICAgICBtYXJnaW4tbGVmdDogNDNweDtcXG4gICAgICAgICAgb3BhY2l0eTogMC41O1xcbiAgICAgICAgICBjdXJzb3I6IG5vdC1hbGxvd2VkOyB9XFxuICAgICAgICAuaGVhZFJpZ2h0ID4gLnNlcnZpY2VzID4gLnZpZGdldHMgPiBhOm50aC1vZi10eXBlKDYpIHtcXG4gICAgICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9wcm9la3RpLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgdG9wIGNlbnRlcjtcXG4gICAgICAgICAgd2lkdGg6IDYwcHg7XFxuICAgICAgICAgIG1hcmdpbi1sZWZ0OiAzNnB4O1xcbiAgICAgICAgICBvcGFjaXR5OiAwLjU7XFxuICAgICAgICAgIGN1cnNvcjogbm90LWFsbG93ZWQ7IH1cXG4gIC5oZWFkUmlnaHQgPiAudXNlck5hbWUge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIG1hcmdpbi1yaWdodDogMjVweDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy91c2VyTmFtZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0O1xcbiAgICBwYWRkaW5nLWxlZnQ6IDMwcHg7XFxuICAgIG1heC13aWR0aDogMjcwcHg7XFxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTsgfVxcbiAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gcCB7XFxuICAgICAgY29sb3I6ICNmZmZmZmY7XFxuICAgICAgZm9udDogNDAwIDE0cHggLyAyN3B4IFJvYm90bztcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgd2hpdGUtc3BhY2U6IG5vd3JhcDtcXG4gICAgICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgICAgIHRleHQtb3ZlcmZsb3c6IGVsbGlwc2lzOyB9XFxuICAgIC5oZWFkUmlnaHQgPiAudXNlck5hbWUgPiBkaXYge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICAgICAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgdG9wOiAwO1xcbiAgICAgIHdpZHRoOiAxMDAlO1xcbiAgICAgIHotaW5kZXg6IDE7IH1cXG4gICAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gZGl2ID4gcCB7XFxuICAgICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgICBwYWRkaW5nOiAwIDE1cHg7XFxuICAgICAgICBoZWlnaHQ6IDQ4cHg7XFxuICAgICAgICBmb250OiA0MDAgMTZweCAvIDUwcHggUm9ib3RvO1xcbiAgICAgICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gICAgICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAgICAgICAuaGVhZFJpZ2h0ID4gLnVzZXJOYW1lID4gZGl2ID4gcDpob3ZlciB7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNlZWVlZWU7IH1cXG4gIC5oZWFkUmlnaHQgPiAuYXV0aCB7XFxuICAgIGNvbG9yOiB3aGl0ZTtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDIwcHg7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjZweCBSb2JvdG87IH1cXG4gICAgLmhlYWRSaWdodCA+IC5hdXRoIHNwYW4ge1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICBtYXJnaW46IDAgMTBweDsgfVxcblxcbi5pbnB1dFNlYXJjaCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XFxuICBtYXJnaW4tdG9wOiAxMHB4O1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkIHdoaXRlOyB9XFxuICAuaW5wdXRTZWFyY2ggPiBpbnB1dCB7XFxuICAgIGJvcmRlcjogbm9uZTtcXG4gICAgcGFkZGluZzogMnB4IDA7XFxuICAgIGJhY2tncm91bmQtY29sb3I6IHRyYW5zcGFyZW50O1xcbiAgICBvdXRsaW5lOiBub25lO1xcbiAgICBjb2xvcjogI2ZmZmZmZjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAyMXB4IFJvYm90bzsgfVxcbiAgLmlucHV0U2VhcmNoID4gbGFiZWwge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIHRvcDogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgY29sb3I6ICNmZmZmZmY7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87XFxuICAgIGN1cnNvcjogdGV4dDtcXG4gICAgLXdlYmtpdC10cmFuc2l0aW9uOiAuNXM7XFxuICAgIHRyYW5zaXRpb246IC41czsgfVxcblxcbi5zZWxlY3RCb3gge1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE5cHggUm9ib3RvO1xcbiAgICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgd2hpdGU7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCA1cHg7XFxuICAgIHBhZGRpbmctcmlnaHQ6IDIwcHg7XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUgc3BhbiB7XFxuICAgICAgZm9udC1zaXplOiAxNnB4OyB9XFxuICAuc2VsZWN0Qm94ID4gLmxpc3RPZlZhbHVlcyB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgdG9wOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gICAgei1pbmRleDogMTtcXG4gICAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gICAgLXdlYmtpdC1hbmltYXRpb246IGFuaW1hdGV0b3AgLjI1cztcXG4gICAgICAgICAgICBhbmltYXRpb246IGFuaW1hdGV0b3AgLjI1czsgfVxcbiAgICAuc2VsZWN0Qm94ID4gLmxpc3RPZlZhbHVlcyA+IGRpdiB7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIHBhZGRpbmc6IDAgMTVweDtcXG4gICAgICBoZWlnaHQ6IDUwcHg7XFxuICAgICAgd2lkdGg6IDEyMHB4O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IC8gNTBweCBSb2JvdG87XFxuICAgICAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAgIC5zZWxlY3RCb3ggPiAubGlzdE9mVmFsdWVzID4gZGl2OmhvdmVyIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6ICNlZWVlZWU7IH1cXG5cXG5ALXdlYmtpdC1rZXlmcmFtZXMgYW5pbWF0ZXRvcCB7XFxuICAwJSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIDEwMCUge1xcbiAgICBvcGFjaXR5OiAxOyB9IH1cXG5cXG5Aa2V5ZnJhbWVzIGFuaW1hdGV0b3Age1xcbiAgMCUge1xcbiAgICBvcGFjaXR5OiAwOyB9XFxuICAxMDAlIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuYXNpZGUuYnVsbGV0aW5EZXRhaWxzIHtcXG4gIGZsb2F0OiByaWdodDtcXG4gIG1hcmdpbi10b3A6IDVweDsgfVxcblxcbnNlY3Rpb24ub3BlbkFkdmVydGVydCB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiB3aGl0ZTtcXG4gIGZsb2F0OiByaWdodDtcXG4gIG1hcmdpbi1yaWdodDogMTBweDtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICB3aWR0aDogNzE1cHg7XFxuICBwYWRkaW5nOiAyNXB4IDEwMHB4IDQ1cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgbWFyZ2luLXRvcDogNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBoMyB7XFxuICAgIGNvbG9yOiAjMjAyMDIwO1xcbiAgICBmb250OiA0MDAgMjJweCAvIDI2cHggUm9ib3RvO1xcbiAgICBmbG9hdDogbGVmdDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnByaWNlIHtcXG4gICAgY29sb3I6ICMxZjFmMWY7XFxuICAgIGZvbnQ6IDQwMCAxOHB4IC8gMjZweCBSb2JvdG87XFxuICAgIGZsb2F0OiByaWdodDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLmNoZWNrQm94IHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDE1cHg7XFxuICAgIG1hcmdpbi10b3A6IC0xcHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5icmVhZENydW1icyB7XFxuICAgIGNvbG9yOiByZ2JhKDMxLCAzMSwgMzEsIDAuNTQpO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE0cHggUm9ib3RvO1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuaWQge1xcbiAgICBjb2xvcjogcmdiYSgzMiwgMzIsIDMyLCAwLjU0KTtcXG4gICAgZm9udDogNDAwIDEycHggLyAxNHB4IFJvYm90bztcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBjdXJzb3I6IGRlZmF1bHQ7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIge1xcbiAgICBoZWlnaHQ6IDEyMHB4O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIG1hcmdpbi10b3A6IDEwcHg7XFxuICAgIGZsb2F0OiBsZWZ0O1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgbWFyZ2luLWJvdHRvbTogMTBweDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gZGl2IHtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tbGVmdDogMTBweDtcXG4gICAgICBoZWlnaHQ6IDEwMCU7XFxuICAgICAgd2lkdGg6IDE2NXB4OyB9XFxuICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IGRpdjpudGgtb2YtdHlwZSgxKSB7XFxuICAgICAgICBtYXJnaW46IDA7IH1cXG4gICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAuc2xpZGVyID4gZGl2ID4gaW1nIHtcXG4gICAgICAgIC1vLW9iamVjdC1maXQ6IGNvbnRhaW47XFxuICAgICAgICAgICBvYmplY3QtZml0OiBjb250YWluO1xcbiAgICAgICAgd2lkdGg6IDEwMCU7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjRGNEY0O1xcbiAgICAgICAgaGVpZ2h0OiAxMDAlO1xcbiAgICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5zbGlkZXIgPiAubmV4dCB7XFxuICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbmV4dC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgY2VudGVyO1xcbiAgICAgIGhlaWdodDogMTRweDtcXG4gICAgICB3aWR0aDogMTBweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgdG9wOiA1MCU7XFxuICAgICAgbWFyZ2luLXRvcDogLTdweDtcXG4gICAgICByaWdodDogLTI1cHg7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnNsaWRlciA+IC5wcmV2IHtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9wcmV2LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgcmlnaHQgY2VudGVyO1xcbiAgICAgIGhlaWdodDogMTRweDtcXG4gICAgICB3aWR0aDogMTBweDtcXG4gICAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgICAgdG9wOiA1MCU7XFxuICAgICAgbWFyZ2luLXRvcDogLTdweDtcXG4gICAgICBsZWZ0OiAtMjVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgPiAjbWFwRm9yT25lQWR2ZXJ0ZXJ0IHtcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIGhlaWdodDogMjMwcHg7XFxuICAgIHdpZHRoOiAyMjVweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMjVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgPiAuZGVzY3JpcHRpb25BZCB7XFxuICAgIGNvbG9yOiAjMGMwYzFlO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE4cHggUm9ib3RvO1xcbiAgICBtYXJnaW4tdG9wOiAxNXB4O1xcbiAgICBtYXJnaW4tYm90dG9tOiAyMHB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCA+IC5nb1RvTWFwIHtcXG4gICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0ID4gLmFsbENvbW1lbnRzIHtcXG4gICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgIGZvbnQ6IDQwMCAxNHB4IC8gMjFweCBSb2JvdG87XFxuICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDUwcHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0ID4gLndyaXRlQVJldmlldyB7XFxuICAgIGNvbG9yOiAjMTk3NmQyO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvO1xcbiAgICBjdXJzb3I6IHBvaW50ZXI7XFxuICAgIGZsb2F0OiByaWdodDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gZGl2LmJ1eVByb2R1Y3QgPiAubmFtZVVzZXIge1xcbiAgICBjb2xvcjogIzE5NzZkMjtcXG4gICAgZm9udDogNDAwIDE0cHggLyAxOHB4IFJvYm90bztcXG4gICAgZmxvYXQ6IGxlZnQ7XFxuICAgIG1hcmdpbi1sZWZ0OiAyMHB4O1xcbiAgICBjdXJzb3I6IGRlZmF1bHQ7XFxuICAgIG1hcmdpbi1ib3R0b206IDM1cHg7IH1cXG4gIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IGRpdi5idXlQcm9kdWN0ID4gLm9uT3JPZmZMaW5lVXNlciB7XFxuICAgIGZsb2F0OiBsZWZ0OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCAuYnRuLWdyZXkge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE0MHB4O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDI1cHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNXB4OyB9XFxuICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiBkaXYuYnV5UHJvZHVjdCAuYnRuLWJsdWUge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcbiAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnJlbnRQcm9kdWN0IHtcXG4gICAgbWFyZ2luLXRvcDogNDVweDsgfVxcbiAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucmVudFByb2R1Y3QgPiAucmVudENhbGVuZGFyIHtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnJlbnRQcm9kdWN0ID4gLnJlbnRDYWxlbmRhciA+IHAge1xcbiAgICAgICAgY29sb3I6ICMyNjMyMzg7XFxuICAgICAgICBmb250OiA0MDAgMTZweCAvIDIxcHggUm9ib3RvO1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5yZW50UHJvZHVjdCA+IC5yZW50Q2FsZW5kYXIgPiBwOm50aC1vZi10eXBlKDIpIHtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IDU1cHg7IH1cXG4gICAgICBzZWN0aW9uLm9wZW5BZHZlcnRlcnQgPiAucmVudFByb2R1Y3QgPiAucmVudENhbGVuZGFyID4gLmNhbGVuZGFyIHtcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgbWFyZ2luLWxlZnQ6IDQ1cHg7XFxuICAgICAgICB3aWR0aDogMTY1cHg7XFxuICAgICAgICBtYXJnaW4tYm90dG9tOiAwOyB9XFxuICAgIHNlY3Rpb24ub3BlbkFkdmVydGVydCA+IC5yZW50UHJvZHVjdCA+IC5pbnB1dEZvcm0ge1xcbiAgICAgIGNvbG9yOiAjOWE5YTlhO1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgc2VjdGlvbi5vcGVuQWR2ZXJ0ZXJ0ID4gLnJlbnRQcm9kdWN0ID4gLmJ0bi1ibHVlIHtcXG4gICAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICAgIGhlaWdodDogMzVweDtcXG4gICAgICB3aWR0aDogMTgwcHg7XFxuICAgICAgbGluZS1oZWlnaHQ6IDM1cHg7XFxuICAgICAgZmxvYXQ6IHJpZ2h0OyB9XFxuXFxuLndyYXBGb3JEaXYge1xcbiAgd2lkdGg6IDI2NXB4O1xcbiAgZmxvYXQ6IHJpZ2h0O1xcbiAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gIG1hcmdpbi1ib3R0b206IDI1cHg7XFxuICBib3JkZXI6IDFweCBzb2xpZCAjRTlFOUU5O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgLndyYXBGb3JEaXYgPiB1bC50YWIge1xcbiAgICBsaXN0LXN0eWxlLXR5cGU6IG5vbmU7XFxuICAgIGhlaWdodDogNTBweDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjRTlFOUU5O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkge1xcbiAgICAgIGZsb2F0OiBsZWZ0OyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGEge1xcbiAgICAgICAgY29sb3I6ICM5MzkzOTM7XFxuICAgICAgICBmb250OiA0MDAgMTRweCAvIDUwcHggUm9ib3RvO1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhOmFmdGVyIHtcXG4gICAgICAgICAgY29udGVudDogJyc7XFxuICAgICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgIGJvdHRvbTogMDtcXG4gICAgICAgICAgd2lkdGg6IDA7XFxuICAgICAgICAgIGhlaWdodDogMnB4O1xcbiAgICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkQ1MTUxO1xcbiAgICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaSA+IGE6Zm9jdXMsIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhLmFjdGl2ZSB7XFxuICAgICAgICAgIGNvbG9yOiAjN2VhZmUxOyB9XFxuICAgICAgICAgIC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkgPiBhOmZvY3VzOmFmdGVyLCAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYS5hY3RpdmU6YWZ0ZXIge1xcbiAgICAgICAgICAgIHdpZHRoOiAxMDAlOyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgxKSA+IGEge1xcbiAgICAgICAgd2lkdGg6IDE1OXB4OyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDEpID4gYTphZnRlciB7XFxuICAgICAgICAgIHJpZ2h0OiAwOyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiB1bC50YWIgPiBsaTpudGgtb2YtdHlwZSgyKSA+IGEge1xcbiAgICAgICAgd2lkdGg6IDEwNHB4OyB9XFxuICAgICAgICAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpOm50aC1vZi10eXBlKDIpID4gYTphZnRlciB7XFxuICAgICAgICAgIGxlZnQ6IDA7IH1cXG4gIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyB7XFxuICAgIGhlaWdodDogMTc4cHg7XFxuICAgIHdpZHRoOiAyODJweDtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogI0Y0RjRGNDtcXG4gICAgb3ZlcmZsb3c6IGF1dG87XFxuICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gLnRhYmNvbnRlbnQge1xcbiAgICAgIGRpc3BsYXk6IG5vbmU7XFxuICAgICAgLXdlYmtpdC1hbmltYXRpb246IGZhZGVFZmZlY3QgMXM7XFxuICAgICAgYW5pbWF0aW9uOiBmYWRlRWZmZWN0IDFzOyB9XFxuICAgICAgLndyYXBGb3JEaXYgPiAuZmVhdHVyZXNBbmRSZXZpZXdzID4gLnRhYmNvbnRlbnQuYWN0aXZlIHtcXG4gICAgICAgIGRpc3BsYXk6IGJsb2NrOyB9XFxuICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzIHtcXG4gICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3MgPiAjcmV2aWV3cyA+IGRpdiB7XFxuICAgICAgICBwYWRkaW5nOiAzMHB4IDIwcHggMjVweCA2NXB4O1xcbiAgICAgICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNFOUU5RTk7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2Om50aC1sYXN0LW9mLXR5cGUoMSkge1xcbiAgICAgICAgICBib3JkZXI6IG5vbmU7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2ID4gaW1nIHtcXG4gICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICBoZWlnaHQ6IDMwcHg7XFxuICAgICAgICAgIHdpZHRoOiAyNXB4O1xcbiAgICAgICAgICB0b3A6IDM1cHg7XFxuICAgICAgICAgIGxlZnQ6IDIwcHg7IH1cXG4gICAgICAgIC53cmFwRm9yRGl2ID4gLmZlYXR1cmVzQW5kUmV2aWV3cyA+ICNyZXZpZXdzID4gZGl2ID4gcCB7XFxuICAgICAgICAgIGNvbG9yOiAjMGQwZDFlO1xcbiAgICAgICAgICBmb250OiA0MDAgMTJweCAvIDE4cHggUm9ib3RvOyB9XFxuXFxuQC13ZWJraXQta2V5ZnJhbWVzIGZhZGVFZmZlY3Qge1xcbiAgZnJvbSB7XFxuICAgIG9wYWNpdHk6IDA7IH1cXG4gIHRvIHtcXG4gICAgb3BhY2l0eTogMTsgfSB9XFxuXFxuQGtleWZyYW1lcyBmYWRlRWZmZWN0IHtcXG4gIGZyb20ge1xcbiAgICBvcGFjaXR5OiAwOyB9XFxuICB0byB7XFxuICAgIG9wYWNpdHk6IDE7IH0gfVxcblxcbi5idG4tYmx1ZSB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMTg3NUQwO1xcbiAgY29sb3I6ICNmZmZmZmY7XFxuICBmb250OiA0MDAgMTRweCAvIDIxcHggUm9ib3RvOyB9XFxuXFxuLm9uT3JPZmZMaW5lVXNlciB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMDBDNjUyO1xcbiAgYm9yZGVyOiAxcHggc29saWQgd2hpdGU7XFxuICBib3gtc2hhZG93OiAxcHggMXB4IDNweCAwcHggcmdiYSgwLCAwLCAwLCAwLjY1KTtcXG4gIGhlaWdodDogMThweDtcXG4gIHdpZHRoOiAxOHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJvcmRlci1yYWRpdXM6IDUwJTsgfVxcblxcbi5lcnJvci1jb250YWluZXIge1xcbiAgd2lkdGg6IDExMDNweDtcXG4gIG1hcmdpbjogYXV0bztcXG4gIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZXJyb3JfYmcucG5nXCIpICsgXCIpO1xcbiAgYmFja2dyb3VuZC1yZXBlYXQ6IG5vLXJlcGVhdDtcXG4gIGJhY2tncm91bmQtcG9zaXRpb24teDogcmlnaHQ7XFxuICBiYWNrZ3JvdW5kLXBvc2l0aW9uLXk6IDgwcHg7XFxuICBtaW4taGVpZ2h0OiA1MDBweDsgfVxcbiAgLmVycm9yLWNvbnRhaW5lciBoMSB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMjJweCAvIDI2LjRweCBSb2JvdG87XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgbWFyZ2luLXRvcDogNjVweDsgfVxcbiAgLmVycm9yLWNvbnRhaW5lciBoMiB7XFxuICAgIGNvbG9yOiAjMWYxZjFmO1xcbiAgICBmb250OiA0MDAgMjJweCAvIDI2LjRweCBSb2JvdG87XFxuICAgIG1hcmdpbi10b3A6IDE4OXB4OyB9XFxuICAuZXJyb3ItY29udGFpbmVyIGgzIHtcXG4gICAgY29sb3I6IHJnYmEoMzEsIDMxLCAzMSwgMC41NCk7XFxuICAgIGZvbnQ6IDQwMCAxMnB4IC8gMTQuNHB4IFJvYm90bztcXG4gICAgbWFyZ2luLXRvcDogMTBweDtcXG4gICAgbWFyZ2luLWJvdHRvbTogMzBweDsgfVxcbiAgLmVycm9yLWNvbnRhaW5lciA+IC5idG4tYmx1ZSB7XFxuICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBsaW5lLWhlaWdodDogMzVweDsgfVxcblxcbi5jbGlja2VkIHtcXG4gIGJveC1zaGFkb3c6IDBweCAwcHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCkgIWltcG9ydGFudDtcXG4gIG1hcmdpbi10b3A6IDEycHggIWltcG9ydGFudDsgfVxcblxcbi5tdWx0aXBsZSB7XFxuICB3aWR0aDogMTE1MHB4O1xcbiAgbWFyZ2luOiA1cHggYXV0byAwOyB9XFxuICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQge1xcbiAgICBmbG9hdDogbGVmdDtcXG4gICAgd2lkdGg6IDM4MHB4O1xcbiAgICBtYXJnaW4tbGVmdDogNXB4O1xcbiAgICBoZWlnaHQ6IDE2NXB4O1xcbiAgICBwYWRkaW5nOiAxNXB4OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgzbi0yKSB7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDA7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0Om50aC1vZi10eXBlKDEpIHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjQkNFNkU5OyB9XFxuICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydDpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0JDRTZFOTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQ6bnRoLW9mLXR5cGUoMykge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNCQ0U2RTk7IH1cXG4gICAgLm11bHRpcGxlID4gLmJ1bGxldGluLXNob3J0ID4gLmJ1bGxldGluLWNlbnRlci1jb2x1bW4ge1xcbiAgICAgIGZsb2F0OiBub25lO1xcbiAgICAgIG1hcmdpbjogMDtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgdG9wOiAxNXB4O1xcbiAgICAgIHJpZ2h0OiAxMTBweDsgfVxcbiAgICAgIC5tdWx0aXBsZSA+IC5idWxsZXRpbi1zaG9ydCA+IC5idWxsZXRpbi1jZW50ZXItY29sdW1uID4gLmJ1bGxldGluLWFjdGlvbiB7XFxuICAgICAgICBib3R0b206IC00M3B4O1xcbiAgICAgICAgd2lkdGg6IDExMHB4O1xcbiAgICAgICAgcmlnaHQ6IC0zMHB4O1xcbiAgICAgICAgei1pbmRleDogMTsgfVxcbiAgICAubXVsdGlwbGUgPiAuYnVsbGV0aW4tc2hvcnQgPiAuYnVsbGV0aW4tbGVmdC1jb2x1bW4gPiAuYnVsbGV0aW4tZGVzY3JpcHRpb24ge1xcbiAgICAgIGNvbG9yOiAjMGMwYzFlO1xcbiAgICAgIGZvbnQ6IDQwMCAxMnB4IC8gMThweCBSb2JvdG87XFxuICAgICAgd2lkdGg6IDE3MHB4O1xcbiAgICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgICAgaGVpZ2h0OiA0MHB4OyB9XFxuXFxuLnJlZCB7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRjc0QTRBOyB9XFxuXFxuLmlucHV0Rm9ybS1yZXF1aXJlZCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBwYWRkaW5nOiA2cHggMnB4O1xcbiAgZm9udDogNDAwIDE2cHggLyAyNC44cHggUm9ib3RvO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWI5YjliO1xcbiAgbWFyZ2luLXRvcDogMTVweDsgfVxcbiAgLmlucHV0Rm9ybS1yZXF1aXJlZCBsYWJlbCB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgbGVmdDogMnB4O1xcbiAgICB0b3A6IDZweDtcXG4gICAgY29sb3I6ICM5YTlhOWE7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuICAuaW5wdXRGb3JtLXJlcXVpcmVkIGlucHV0IHtcXG4gICAgYm9yZGVyOiBub25lO1xcbiAgICBvdXRsaW5lOiBub25lO1xcbiAgICB3aWR0aDogMTAwJTtcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7IH1cXG5cXG4uaW5wdXRGb3JtLnJlcXVpcmVkIHtcXG4gIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWI5YjliICFpbXBvcnRhbnQ7IH1cXG5cXG4uaW5wdXRGb3JtIHtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBib3JkZXItYm90dG9tOiAxcHggZG90dGVkICM5OTk5OTk7IH1cXG4gIC5pbnB1dEZvcm0gbGFiZWwge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGxlZnQ6IDVweDtcXG4gICAgY29sb3I6ICM5YTlhOWE7XFxuICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgIHRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0b3A6IC0xcHg7IH1cXG4gIC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDtcXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICBwYWRkaW5nOiAxcHggNXB4OyB9XFxuXFxuLyogVGV4dCBlbGVtZW50IGFuaW1hdGlvbiAqL1xcbi50ZXh0T3V0IHtcXG4gIHRvcDogLTE1cHggIWltcG9ydGFudDtcXG4gIGZvbnQtc2l6ZTogMTJweCAhaW1wb3J0YW50O1xcbiAgbGVmdDogNXB4ICFpbXBvcnRhbnQ7IH1cXG5cXG5zZWN0aW9uLmxvZ2luLCBzZWN0aW9uLnJlZ2lzdGVyIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgcGFkZGluZzogNjVweCAyMzBweDtcXG4gIHdpZHRoOiAxMTAwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICBzZWN0aW9uLmxvZ2luID4gaDIsIHNlY3Rpb24ucmVnaXN0ZXIgPiBoMiB7XFxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgY29sb3I6ICMyMDIwMjA7XFxuICAgIGZvbnQ6IDQwMCAyMnB4IFJvYm90bztcXG4gICAgbWFyZ2luLWJvdHRvbTogNTVweDsgfVxcbiAgc2VjdGlvbi5sb2dpbiA+IC5pbnB1dEZvcm0sIHNlY3Rpb24ucmVnaXN0ZXIgPiAuaW5wdXRGb3JtIHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmlucHV0Rm9ybSA+IGxhYmVsLCBzZWN0aW9uLmxvZ2luID4gLmlucHV0Rm9ybSBpbnB1dCwgc2VjdGlvbi5yZWdpc3RlciA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgc2VjdGlvbi5yZWdpc3RlciA+IC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICAgIGNvbG9yOiAjOWE5YTlhO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bzsgfVxcbiAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZSwgc2VjdGlvbi5yZWdpc3RlciA+IC5idG4tYmx1ZSB7XFxuICAgIGhlaWdodDogMzVweDtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBtYXJnaW4tbGVmdDogNDVweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7IH1cXG4gICAgc2VjdGlvbi5sb2dpbiA+IC5idG4tYmx1ZS52aywgc2VjdGlvbi5yZWdpc3RlciA+IC5idG4tYmx1ZS52ayB7XFxuICAgICAgYmFja2dyb3VuZDogIzAxODZDRiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3ZrLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGxlZnQgMTBweDtcXG4gICAgICBtYXJnaW4tbGVmdDogMDsgfVxcbiAgICBzZWN0aW9uLmxvZ2luID4gLmJ0bi1ibHVlLmZhY2Vib29rLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlLmZhY2Vib29rIHtcXG4gICAgICBiYWNrZ3JvdW5kOiAjM0U1MEIzIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvZmFjZWJvb2sucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgbGVmdCAxMHB4OyB9XFxuICAgIHNlY3Rpb24ubG9naW4gPiAuYnRuLWJsdWUuZ29vZ2xlLCBzZWN0aW9uLnJlZ2lzdGVyID4gLmJ0bi1ibHVlLmdvb2dsZSB7XFxuICAgICAgYmFja2dyb3VuZDogI0ZEM0MwMCB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2dvb2dsZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBsZWZ0IDEwcHg7IH1cXG5cXG5zZWN0aW9uLmJ1bGxldGluQWRkIHtcXG4gIGJveC1zaGFkb3c6IDBweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkRGREZEO1xcbiAgd2lkdGg6IDExMDBweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMDtcXG4gIHBhZGRpbmc6IDY1cHggMjI1cHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gaDIge1xcbiAgICBjb2xvcjogIzFmMWYxZjtcXG4gICAgZm9udDogNDAwIDIycHggUm9ib3RvO1xcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuc2VsZWN0Qm94IHtcXG4gICAgbWFyZ2luLWJvdHRvbTogNDVweDtcXG4gICAgbWFyZ2luLXRvcDogMTVweDsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgIGJvcmRlci1jb2xvcjogZ3JleTtcXG4gICAgICBtaW4td2lkdGg6IDE5NXB4O1xcbiAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICBwYWRkaW5nLWxlZnQ6IDVweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWU5ZTllO1xcbiAgICB3aWR0aDogNDEwcHg7XFxuICAgIHBhZGRpbmctYm90dG9tOiA1cHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5maWxlID4gaW5wdXQge1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICB6LWluZGV4OiAtMTsgfVxcbiAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmZpbGUgPiBwIHtcXG4gICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICBmb250OiA0MDAgMTZweCBSb2JvdG87XFxuICAgICAgY3Vyc29yOiBkZWZhdWx0O1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIHBhZGRpbmctdG9wOiAxMHB4OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuZmlsZSA+IC5idG4tYmx1ZSB7XFxuICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgICAgaGVpZ2h0OiAzMHB4O1xcbiAgICAgIHdpZHRoOiA4NXB4O1xcbiAgICAgIGxpbmUtaGVpZ2h0OiAzMHB4OyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmlucHV0Rm9ybSB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5pbnB1dEZvcm0gPiBsYWJlbCwgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5pbnB1dEZvcm0gaW5wdXQge1xcbiAgICAgIGNvbG9yOiAjOTk5OTk5O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bzsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciB7XFxuICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IHAge1xcbiAgICAgIGNvbG9yOiAjOTk5OTk5O1xcbiAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggZG90dGVkICM5OTk5OTk7XFxuICAgICAgd2lkdGg6IDE2MHB4O1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIGhlaWdodDogMjFweDtcXG4gICAgICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAxOXB4O1xcbiAgICAgIG1hcmdpbi10b3A6IDNweDtcXG4gICAgICB3aWR0aDogMTZweDtcXG4gICAgICBoZWlnaHQ6IDE1cHg7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICAgICAgb3V0bGluZTogMXB4IHNvbGlkIHRyYW5zcGFyZW50O1xcbiAgICAgIGJvcmRlcjogMXB4IHNvbGlkIHdoaXRlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi50cmFuc3BhcmVudCB7XFxuICAgICAgICBib3JkZXItY29sb3I6ICNFOUU5RTk7XFxuICAgICAgICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuICAgICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnRyYW5zcGFyZW50ID4gLnJlZFN0aWNrIHtcXG4gICAgICAgICAgLXdlYmtpdC10cmFuc2Zvcm06IHJvdGF0ZSgtNDNkZWcpO1xcbiAgICAgICAgICAgICAgICAgIHRyYW5zZm9ybTogcm90YXRlKC00M2RlZyk7XFxuICAgICAgICAgIGJhY2tncm91bmQtY29sb3I6IHJlZDtcXG4gICAgICAgICAgaGVpZ2h0OiAycHg7XFxuICAgICAgICAgIG1hcmdpbi10b3A6IDUuNXB4O1xcbiAgICAgICAgICB3aWR0aDogMjBweDtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IC0zcHg7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnJlZCB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZWQ7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2Lm9yYW5nZSB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiBvcmFuZ2U7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnllbGxvdyB7XFxuICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiB5ZWxsb3c7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmdyZWVuIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGdyZWVuOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5saWdodEJsdWUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogbGlnaHRCbHVlOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5ibHVlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGJsdWU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LnBpbmsge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogcGluazsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYucHVycGxlIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IHB1cnBsZTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYud2hpdGUge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGU7XFxuICAgICAgICBib3JkZXItY29sb3I6ICNFOUU5RTk7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmdyYXkge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogZ3JheTsgfVxcbiAgICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuY29sb3IgPiBkaXYuYmxhY2sge1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogYmxhY2s7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNvbG9yID4gZGl2LmJyb3duIHtcXG4gICAgICAgIGJhY2tncm91bmQtY29sb3I6IGJyb3duOyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5tdWx0aWNvbG9yIHtcXG4gICAgICAgIC8qIFBlcm1hbGluayAtIHVzZSB0byBlZGl0IGFuZCBzaGFyZSB0aGlzIGdyYWRpZW50OiBodHRwOi8vY29sb3J6aWxsYS5jb20vZ3JhZGllbnQtZWRpdG9yLyNmZjAwMDArMCxmZmZmMDArMjAsMWRmZjAwKzQwLDAwZmZmZis2MCwwNDAwZmYrODAsZmYwMGZhKzEwMCAqL1xcbiAgICAgICAgYmFja2dyb3VuZDogI2ZmMDAwMDtcXG4gICAgICAgIC8qIE9sZCBicm93c2VycyAqL1xcbiAgICAgICAgLyogRkYzLjYtMTUgKi9cXG4gICAgICAgIGJhY2tncm91bmQ6IC13ZWJraXQtbGluZWFyLWdyYWRpZW50KGxlZnQsICNmZjAwMDAgMCUsICNmZmZmMDAgMjAlLCAjMWRmZjAwIDQwJSwgIzAwZmZmZiA2MCUsICMwNDAwZmYgODAlLCAjZmYwMGZhIDEwMCUpO1xcbiAgICAgICAgLyogQ2hyb21lMTAtMjUsU2FmYXJpNS4xLTYgKi9cXG4gICAgICAgIGJhY2tncm91bmQ6IGxpbmVhci1ncmFkaWVudCh0byByaWdodCwgI2ZmMDAwMCAwJSwgI2ZmZmYwMCAyMCUsICMxZGZmMDAgNDAlLCAjMDBmZmZmIDYwJSwgIzA0MDBmZiA4MCUsICNmZjAwZmEgMTAwJSk7XFxuICAgICAgICAvKiBXM0MsIElFMTArLCBGRjE2KywgQ2hyb21lMjYrLCBPcGVyYTEyKywgU2FmYXJpNysgKi9cXG4gICAgICAgIGZpbHRlcjogcHJvZ2lkOkRYSW1hZ2VUcmFuc2Zvcm0uTWljcm9zb2Z0LmdyYWRpZW50KCBzdGFydENvbG9yc3RyPScjZmYwMDAwJywgZW5kQ29sb3JzdHI9JyNmZjAwZmEnLEdyYWRpZW50VHlwZT0xICk7XFxuICAgICAgICAvKiBJRTYtOSAqLyB9XFxuICAgICAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5jb2xvciA+IGRpdi5hY3RpdmUge1xcbiAgICAgICAgb3V0bGluZS1jb2xvcjogcmVkOyB9XFxuICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmNhbGVuZGFyLCBzZWN0aW9uLmJ1bGxldGluQWRkIC5hZGRDYWxlbmRhciB7XFxuICAgIHdpZHRoOiAyMzBweDsgfVxcbiAgc2VjdGlvbi5idWxsZXRpbkFkZCA+IC5hZGRDYWxlbmRhciB7XFxuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYWRkQ2FsZW5kYXIucG5nXCIpICsgXCIpOyB9XFxuICAgIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYWRkQ2FsZW5kYXIgPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICBib3JkZXItYm90dG9tLWNvbG9yOiBibHVlO1xcbiAgICAgIGJhY2tncm91bmQ6IG5vbmU7IH1cXG4gICAgICBzZWN0aW9uLmJ1bGxldGluQWRkID4gLmFkZENhbGVuZGFyID4gLmRlZmF1bHRWYWx1ZSA+IHAge1xcbiAgICAgICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgICAgICBmb250LXNpemU6IDE2cHg7IH1cXG4gIHNlY3Rpb24uYnVsbGV0aW5BZGQgPiAuYnRuLWJsdWUge1xcbiAgICBoZWlnaHQ6IDM1cHg7XFxuICAgIHdpZHRoOiAxODBweDtcXG4gICAgbGluZS1oZWlnaHQ6IDM1cHg7XFxuICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICBtYXJnaW46IDAgYXV0bzsgfVxcblxcbi5lcnJvcnMge1xcbiAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgZm9udDogNDAwIDEycHggLyAxNHB4IFJvYm90bztcXG4gIGNvbG9yOiAjZGQyYzAwO1xcbiAgYm90dG9tOiAtMTdweDsgfVxcblxcbm5hdiB7XFxuICB3aWR0aDogMjU1cHg7XFxuICBmbG9hdDogbGVmdDtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgbWFyZ2luLXRvcDogNXB4O1xcbiAgei1pbmRleDogMjtcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTsgfVxcbiAgbmF2ID4gLm1hcCB7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbWFwLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgIGhlaWdodDogMTQ1cHg7XFxuICAgIHdpZHRoOiAyNTVweDsgfVxcbiAgICBuYXYgPiAubWFwID4gLmlucHV0Rm9ybSB7XFxuICAgICAgd2lkdGg6IDIxNXB4O1xcbiAgICAgIG1hcmdpbjogMTE1cHggYXV0byAwOyB9XFxuICAgICAgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0ucmVxdWlyZWQge1xcbiAgICAgICAgYm9yZGVyLWNvbG9yOiBibGFjayAhaW1wb3J0YW50OyB9XFxuICAgICAgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0gPiBpbnB1dCwgbmF2ID4gLm1hcCA+IC5pbnB1dEZvcm0gbGFiZWwsIG5hdiA+IC5tYXAgPiAuaW5wdXRGb3JtIC50ZXh0T3V0IHtcXG4gICAgICAgIGNvbG9yOiBibGFjayAhaW1wb3J0YW50OyB9XFxuICBuYXYgPiB1bCB7XFxuICAgIGxpc3Qtc3R5bGU6IG5vbmU7IH1cXG4gICAgbmF2ID4gdWwgPiBsaSA+IHAge1xcbiAgICAgIHBhZGRpbmctbGVmdDogNzVweDtcXG4gICAgICBjb2xvcjogIzI2MzIzODtcXG4gICAgICBmb250OiA1MDAgMTNweCAvIDQwcHggUm9ib3RvO1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2FyZXRSaWdodE5hdi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCAyMHB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOmhvdmVyIHtcXG4gICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRTZFNkU2ICFpbXBvcnRhbnQ7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Gb3JDaGlsZHJlbi5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Gb3JBbmltYWxzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDMpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0J1c2luZXNzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDQpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1RoZVByb3BlcnR5LnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDUpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1RyYW5zcG9ydC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg2KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9DbG90aGluZ0FuZENvc21ldGljcy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSg3KSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9Wb2x1bnRlZXJpbmcucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoOCkge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvSXNGcmVlLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcbiAgICBuYXYgPiB1bCA+IGxpOm50aC1vZi10eXBlKDkpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0FIb3VzZUFuZEFHYXJkZW4ucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMTApIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBsZWZ0IDI1cHggY2VudGVyOyB9XFxuICAgIG5hdiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMTEpIHtcXG4gICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL0JhcnRlci5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGxlZnQgMjVweCBjZW50ZXI7IH1cXG4gICAgbmF2ID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxMikge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvVGVjaG5vbG9naWVzLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgbGVmdCAyNXB4IGNlbnRlcjsgfVxcblxcbi5uYXZCdG4ge1xcbiAgd2lkdGg6IDQzcHg7XFxuICBoZWlnaHQ6IDQ5cHg7XFxuICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICBsZWZ0OiAwO1xcbiAgdG9wOiA5MHB4O1xcbiAgY3Vyc29yOiBwb2ludGVyO1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9ydXByLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICB6LWluZGV4OiAxOyB9XFxuICAubmF2QnRuOmhvdmVyIHtcXG4gICAgd2lkdGg6IDYwcHg7XFxuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvcnVwckhvdmVyLnBuZ1wiKSArIFwiKTsgfVxcblxcbi5jb3ZlciB7XFxuICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICB0b3A6IDA7XFxuICByaWdodDogMDtcXG4gIGJvdHRvbTogMDtcXG4gIGxlZnQ6IDA7XFxuICBiYWNrZ3JvdW5kLWNvbG9yOiByZ2JhKDAsIDAsIDAsIDAuOSk7XFxuICB6LWluZGV4OiA1OyB9XFxuICAuY292ZXIuaGlkZSB7XFxuICAgIC13ZWJraXQtYW5pbWF0aW9uOiBoaWRlIDFzO1xcbiAgICAgICAgICAgIGFuaW1hdGlvbjogaGlkZSAxczsgfVxcblxcbkAtd2Via2l0LWtleWZyYW1lcyBoaWRlIHtcXG4gIDEwMCUge1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDsgfSB9XFxuXFxuQGtleWZyYW1lcyBoaWRlIHtcXG4gIDEwMCUge1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiB0cmFuc3BhcmVudDsgfSB9XFxuXFxuc2VjdGlvbi5tYWlsIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgd2lkdGg6IDExMDVweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gMTBweDtcXG4gIHBhZGRpbmc6IDQ1cHggMCA4MHB4OyB9XFxuICBzZWN0aW9uLm1haWwgPiAudGFiIHtcXG4gICAgd2lkdGg6IDkwNXB4O1xcbiAgICBib3JkZXI6IDFweCBzb2xpZCAjRTlFOUU5O1xcbiAgICBib3JkZXItYm90dG9tOiBub25lO1xcbiAgICBtYXJnaW46IDAgYXV0bztcXG4gICAgYmFja2dyb3VuZC1jb2xvcjogI0Y0RjRGNDsgfVxcbiAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gdWwge1xcbiAgICAgIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICAgICAgbGlzdC1zdHlsZTogbm9uZTtcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0U5RTlFOTtcXG4gICAgICBoZWlnaHQ6IDUwcHg7XFxuICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiB1bCA+IGxpIHtcXG4gICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgZm9udDogNDAwIDE0cHggLyA1MHB4IFJvYm90bztcXG4gICAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICAgIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gdWwgPiBsaTphZnRlciB7XFxuICAgICAgICAgIGNvbnRlbnQ6ICcnO1xcbiAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgIGJvdHRvbTogMDtcXG4gICAgICAgICAgbGVmdDogNTAlO1xcbiAgICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgICAgaGVpZ2h0OiAycHg7XFxuICAgICAgICAgIHdpZHRoOiAwO1xcbiAgICAgICAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkQ1MTUxO1xcbiAgICAgICAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IHVsID4gbGkuYWN0aXZlIHtcXG4gICAgICAgICAgY29sb3I6ICM3ZWFmZTE7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IHVsID4gbGkuYWN0aXZlOmFmdGVyIHtcXG4gICAgICAgICAgICB3aWR0aDogMTAwJTtcXG4gICAgICAgICAgICBsZWZ0OiAwOyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gdWwgPiBsaTpudGgtb2YtdHlwZSgxKSB7XFxuICAgICAgICAgIHdpZHRoOiAxMTBweDsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IHVsID4gbGk6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgICAgICB3aWR0aDogMjE1cHg7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiB1bCA+IGxpOm50aC1vZi10eXBlKDMpIHtcXG4gICAgICAgICAgcGFkZGluZzogMCAxNXB4OyB9XFxuICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiB7XFxuICAgICAgZGlzcGxheTogbm9uZTsgfVxcbiAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YXZsZVRpdGxlIHtcXG4gICAgICAgIGhlaWdodDogNjVweDtcXG4gICAgICAgIHBhZGRpbmctdG9wOiAzNXB4O1xcbiAgICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gICAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOTk5OTk5OyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGF2bGVUaXRsZSA+IHAge1xcbiAgICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgICAgZm9udDogNDAwIDE0cHggLyAyMHB4IFJvYm90bztcXG4gICAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICAgIG1hcmdpbi1sZWZ0OiAzMHB4O1xcbiAgICAgICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhdmxlVGl0bGUgPiBwOm50aC1vZi10eXBlKDIpIHtcXG4gICAgICAgICAgICBtYXJnaW4tbGVmdDogNDVweDsgfVxcbiAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhdmxlVGl0bGUgPiAuc2VsZWN0Qm94IHtcXG4gICAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICAgIG1hcmdpbi1sZWZ0OiA0NXB4OyB9XFxuICAgICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YXZsZVRpdGxlID4gLnNlbGVjdEJveCA+IC5kZWZhdWx0VmFsdWUge1xcbiAgICAgICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgICAgIGZvbnQ6IDQwMCAxNnB4IFJvYm90bztcXG4gICAgICAgICAgICBib3JkZXItY29sb3I6ICM5OTk5OTk7XFxuICAgICAgICAgICAgd2lkdGg6IDE5NXB4OyB9XFxuICAgICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YXZsZVRpdGxlID4gLnNlbGVjdEJveDpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgICAgICAgbWFyZ2luLWxlZnQ6IDEwcHg7IH1cXG4gICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIge1xcbiAgICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5OTk5OTk7XFxuICAgICAgICBwYWRkaW5nOiAxMHB4IDA7XFxuICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50cjpiZWZvcmUsIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50cjphZnRlciB7XFxuICAgICAgICAgIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gICAgICAgICAgZGlzcGxheTogdGFibGU7IH1cXG4gICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAubWFpbFRhYiA+IC50YWJsZSA+IC50cjphZnRlciB7XFxuICAgICAgICAgIGNsZWFyOiBib3RoOyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHI6bnRoLW9mLXR5cGUoMSkgPiAudGQge1xcbiAgICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgICAgZm9udDogNDAwIDEzcHggLyAyN3B4IFJvYm90bztcXG4gICAgICAgICAgdGV4dC1hbGlnbjogbGVmdDtcXG4gICAgICAgICAgY3Vyc29yOiBkZWZhdWx0ICFpbXBvcnRhbnQ7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyOm50aC1vZi10eXBlKDEpID4gLnRkID4gLmNoZWNrQm94IHtcXG4gICAgICAgICAgICBwb3NpdGlvbjogcmVsYXRpdmUgIWltcG9ydGFudDtcXG4gICAgICAgICAgICBtYXJnaW4tdG9wOiAwICFpbXBvcnRhbnQ7XFxuICAgICAgICAgICAgbWFyZ2luLWxlZnQ6IDMwcHg7XFxuICAgICAgICAgICAgbGVmdDogMCAhaW1wb3J0YW50OyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQge1xcbiAgICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgICAgY29sb3I6ICM2NjY2NjY7XFxuICAgICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gMThweCBSb2JvdG87IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICAgICAgICB3aWR0aDogODBweDtcXG4gICAgICAgICAgICBtaW4taGVpZ2h0OiAxcHg7IH1cXG4gICAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoMSkgPiAuY2hlY2tCb3gge1xcbiAgICAgICAgICAgICAgbGVmdDogMzBweDtcXG4gICAgICAgICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICAgICAgICAgIHRvcDogNTAlO1xcbiAgICAgICAgICAgICAgbWFyZ2luLXRvcDogLTEzcHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDIpIHtcXG4gICAgICAgICAgICB3aWR0aDogMTQ1cHg7XFxuICAgICAgICAgICAgbWluLWhlaWdodDogMXB4OyB9XFxuICAgICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDIpID4gLnRyVXNlciB7XFxuICAgICAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgICAgICB0b3A6IDUwJTtcXG4gICAgICAgICAgICAgIG1hcmdpbi10b3A6IC05cHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDMpIHtcXG4gICAgICAgICAgICB3aWR0aDogMzEwcHg7XFxuICAgICAgICAgICAgY3Vyc29yOiBwb2ludGVyOyB9XFxuICAgICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDMpID4gLnByZXZpZXcge1xcbiAgICAgICAgICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgICAgICAgICBmb250OiA0MDAgMTRweCBSb2JvdG87XFxuICAgICAgICAgICAgICB3aWR0aDogMzEwcHg7XFxuICAgICAgICAgICAgICB3aGl0ZS1zcGFjZTogbm93cmFwO1xcbiAgICAgICAgICAgICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgICAgICAgICAgIHRleHQtb3ZlcmZsb3c6IGVsbGlwc2lzO1xcbiAgICAgICAgICAgICAgbWFyZ2luLXRvcDogMTBweDsgfVxcbiAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoNCkge1xcbiAgICAgICAgICAgIHdpZHRoOiAxMDVweDtcXG4gICAgICAgICAgICBtaW4taGVpZ2h0OiAxcHg7IH1cXG4gICAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoNCkgPiBpbWcge1xcbiAgICAgICAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgICAgICAgdG9wOiA1MCU7XFxuICAgICAgICAgICAgICBtYXJnaW4tdG9wOiAtN3B4O1xcbiAgICAgICAgICAgICAgbGVmdDogNTU1cHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDUpIHtcXG4gICAgICAgICAgICB3aWR0aDogMTUwcHg7XFxuICAgICAgICAgICAgbWluLWhlaWdodDogMXB4OyB9XFxuICAgICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDUpID4gLnN0YXR1cyB7XFxuICAgICAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgICAgICB0b3A6IDUwJTtcXG4gICAgICAgICAgICAgIG1hcmdpbi10b3A6IC05cHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5tYWlsVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDYpIHtcXG4gICAgICAgICAgICBtaW4taGVpZ2h0OiAxcHg7IH1cXG4gICAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLm1haWxUYWIgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoNikgPiAuZGF0ZSB7XFxuICAgICAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgICAgICB0b3A6IDUwJTtcXG4gICAgICAgICAgICAgIG1hcmdpbi10b3A6IC05cHg7XFxuICAgICAgICAgICAgICBsZWZ0OiA3OTBweDsgfVxcbiAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLmNvbnRhY3RzVGFiIHtcXG4gICAgICBwYWRkaW5nOiAzMHB4O1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjRTlFOUU5O1xcbiAgICAgIGRpc3BsYXk6IG5vbmU7IH1cXG4gICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLmNvbnRhY3RzVGFiID4gLmlucHV0Rm9ybSB7XFxuICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgIHdpZHRoOiAzMTVweDtcXG4gICAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvbG9vcFNlYXJjZy5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciByaWdodCA1cHg7XFxuICAgICAgICBtYXJnaW4tYm90dG9tOiAzMHB4OyB9XFxuICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5jb250YWN0c1RhYiA+IC50YWJsZSA+IC50ciB7XFxuICAgICAgICBtYXJnaW4tYm90dG9tOiAyNXB4OyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLmNvbnRhY3RzVGFiID4gLnRhYmxlID4gLnRyOm50aC1vZi10eXBlKDEpID4gLnRkIHtcXG4gICAgICAgICAgaGVpZ2h0OiBhdXRvOyB9XFxuICAgICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAuY29udGFjdHNUYWIgPiAudGFibGUgPiAudHI6bnRoLW9mLXR5cGUoMSkgPiAudGQgPiBwIHtcXG4gICAgICAgICAgICBjb2xvcjogIzllOWU5ZTtcXG4gICAgICAgICAgICBmb250OiA0MDAgMTNweCBSb2JvdG87XFxuICAgICAgICAgICAgdGV4dC1hbGlnbjogbGVmdDtcXG4gICAgICAgICAgICBjdXJzb3I6IGRlZmF1bHQ7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5jb250YWN0c1RhYiA+IC50YWJsZSA+IC50cjpudGgtb2YtdHlwZSgxKSA+IC50ZDpudGgtb2YtdHlwZSgxKSA+IHAge1xcbiAgICAgICAgICAgIG1hcmdpbi1sZWZ0OiA2NXB4OyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLmNvbnRhY3RzVGFiID4gLnRhYmxlID4gLnRyOm50aC1sYXN0LW9mLXR5cGUoMSkge1xcbiAgICAgICAgICBtYXJnaW4tYm90dG9tOiAwOyB9XFxuICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLmNvbnRhY3RzVGFiID4gLnRhYmxlID4gLnRyID4gLnRkIHtcXG4gICAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICAgIGhlaWdodDogNDBweDsgfVxcbiAgICAgICAgICBzZWN0aW9uLm1haWwgPiAudGFiID4gLmNvbnRhY3RzVGFiID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICAgICAgICB3aWR0aDogMzMwcHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5jb250YWN0c1RhYiA+IC50YWJsZSA+IC50ciA+IC50ZDpudGgtb2YtdHlwZSgyKSB7XFxuICAgICAgICAgICAgd2lkdGg6IDQxMHB4OyB9XFxuICAgICAgICAgIHNlY3Rpb24ubWFpbCA+IC50YWIgPiAuY29udGFjdHNUYWIgPiAudGFibGUgPiAudHIgPiAudGQgPiAudXNlcm5hbWUge1xcbiAgICAgICAgICAgIGNvbG9yOiAjNjY2NjY2O1xcbiAgICAgICAgICAgIGZvbnQ6IDQwMCAxNHB4IC8gNDBweCBSb2JvdG87XFxuICAgICAgICAgICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICAgICAgICAgIG1hcmdpbi1sZWZ0OiAyNXB4O1xcbiAgICAgICAgICAgIHdoaXRlLXNwYWNlOiBub3dyYXA7XFxuICAgICAgICAgICAgb3ZlcmZsb3c6IGhpZGRlbjtcXG4gICAgICAgICAgICB0ZXh0LW92ZXJmbG93OiBlbGxpcHNpcztcXG4gICAgICAgICAgICB3aWR0aDogMjYwcHg7IH1cXG4gICAgICAgICAgc2VjdGlvbi5tYWlsID4gLnRhYiA+IC5jb250YWN0c1RhYiA+IC50YWJsZSA+IC50ciA+IC50ZCA+IC5vbk9yT2ZmTGluZVVzZXIge1xcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IDExcHg7IH1cXG5cXG4uZGF0ZSwgLmRpYWxvZyA+IC5oZWFkZXIgPiAuZGF0ZSB7XFxuICBjb2xvcjogIzY2NjY2NjtcXG4gIGZvbnQ6IDQwMCAxNHB4IC8gNDBweCBSb2JvdG87IH1cXG5cXG4uYXZhdGFyLCAuZGlhbG9nID4gLmhlYWRlciA+IC5hdmF0YXIge1xcbiAgYm9yZGVyLXJhZGl1czogNTAlO1xcbiAgaGVpZ2h0OiA0MHB4O1xcbiAgd2lkdGg6IDQwcHg7XFxuICBjdXJzb3I6IHBvaW50ZXI7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NvdC5wbmdcIikgKyBcIik7XFxuICBmbG9hdDogbGVmdDsgfVxcblxcbi5kaWFsb2cge1xcbiAgaGVpZ2h0OiA1MTVweDtcXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcXG4gIHBhZGRpbmctdG9wOiA5MHB4O1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIG92ZXJmbG93OiBoaWRkZW47XFxuICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0U5RTlFOTsgfVxcbiAgLmRpYWxvZyA+IC5oZWFkZXIge1xcbiAgICBoZWlnaHQ6IDY1cHg7XFxuICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOTk5OTk5O1xcbiAgICBib3gtc2l6aW5nOiBib3JkZXItYm94O1xcbiAgICBwYWRkaW5nLXRvcDogMTVweDtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICB0b3A6IDA7XFxuICAgIGxlZnQ6IDA7XFxuICAgIHdpZHRoOiA5MDVweDsgfVxcbiAgICAuZGlhbG9nID4gLmhlYWRlciA+IC5hdmF0YXIge1xcbiAgICAgIG1hcmdpbi1sZWZ0OiAyNXB4OyB9XFxuICAgIC5kaWFsb2cgPiAuaGVhZGVyID4gcCB7XFxuICAgICAgY29sb3I6ICMyNjMyMzg7XFxuICAgICAgZm9udDogNDAwIDE0cHggLyA0MHB4IFJvYm90bztcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tbGVmdDogMzVweDsgfVxcbiAgICAgIC5kaWFsb2cgPiAuaGVhZGVyID4gcCA+IGkge1xcbiAgICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgICBmb250OiA0MDAgMTRweCAvIDQwcHggUm9ib3RvOyB9XFxuICAgIC5kaWFsb2cgPiAuaGVhZGVyID4gYSB7XFxuICAgICAgY29sb3I6ICMxOTc2ZDI7XFxuICAgICAgZm9udDogNDAwIDE0cHggLyA0MHB4IFJvYm90bztcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tbGVmdDogNXB4O1xcbiAgICAgIHRleHQtZGVjb3JhdGlvbjogdW5kZXJsaW5lOyB9XFxuICAgICAgLmRpYWxvZyA+IC5oZWFkZXIgPiBhOmhvdmVyIHtcXG4gICAgICAgIHRleHQtZGVjb3JhdGlvbjogbm9uZTsgfVxcbiAgICAuZGlhbG9nID4gLmhlYWRlciA+IC5kYXRlIHtcXG4gICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgbWFyZ2luLXJpZ2h0OiAxMHB4OyB9XFxuICAuZGlhbG9nID4gaDIge1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDIwcHggUm9ib3RvO1xcbiAgICBtYXJnaW4tYm90dG9tOiAyMHB4O1xcbiAgICBtYXJnaW4tbGVmdDogODBweDsgfVxcbiAgLmRpYWxvZyA+IC53cmFwTWVzc2FnZXMge1xcbiAgICB3aWR0aDogNjQ1cHg7XFxuICAgIG92ZXJmbG93OiBoaWRkZW47XFxuICAgIG1hcmdpbi1sZWZ0OiAyNXB4OyB9XFxuICAgIC5kaWFsb2cgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzIHtcXG4gICAgICB3aWR0aDogNjYycHg7XFxuICAgICAgaGVpZ2h0OiAzMjBweDtcXG4gICAgICBvdmVyZmxvdzogYXV0bzsgfVxcbiAgICAgIC5kaWFsb2cgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnBvc3RJbnRlcmxvY3V0b3Ige1xcbiAgICAgICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgICAgICAgLmRpYWxvZyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAucG9zdEludGVybG9jdXRvciA+IHAge1xcbiAgICAgICAgICBmbG9hdDogbGVmdDtcXG4gICAgICAgICAgY29sb3I6ICMwYzBjMWU7XFxuICAgICAgICAgIGZvbnQ6IDQwMCAxMnB4IFJvYm90bztcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IDE1cHg7XFxuICAgICAgICAgIHdpZHRoOiA1MzVweDsgfVxcbiAgICAgIC5kaWFsb2cgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnlvdXJBbnN3ZXIge1xcbiAgICAgICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgICAgICAgLmRpYWxvZyA+IC53cmFwTWVzc2FnZXMgPiAubWVzc2FnZXMgPiAueW91ckFuc3dlciA+IC5hdmF0YXIge1xcbiAgICAgICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL3lvdXJBdmF0YXIucG5nXCIpICsgXCIpO1xcbiAgICAgICAgICBmbG9hdDogcmlnaHQ7IH1cXG4gICAgICAgIC5kaWFsb2cgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnlvdXJBbnN3ZXIgPiBwIHtcXG4gICAgICAgICAgY29sb3I6ICMwYzBjMWU7XFxuICAgICAgICAgIGZvbnQ6IDQwMCAxMnB4IFJvYm90bztcXG4gICAgICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgICAgICBtYXJnaW4tcmlnaHQ6IDE1cHg7XFxuICAgICAgICAgIHdpZHRoOiA1MzVweDtcXG4gICAgICAgICAgdGV4dC1hbGlnbjogcmlnaHQ7IH1cXG4gIC5kaWFsb2cgPiAuZm9vdGVyIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBib3R0b206IDEwcHg7XFxuICAgIGxlZnQ6IDI1cHg7XFxuICAgIHdpZHRoOiA4NzBweDsgfVxcbiAgICAuZGlhbG9nID4gLmZvb3RlciA+IC5hdmF0YXIge1xcbiAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMveW91ckF2YXRhci5wbmdcIikgKyBcIik7IH1cXG4gICAgLmRpYWxvZyA+IC5mb290ZXIgPiAuaW5wdXRGb3JtIHtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tbGVmdDogNXB4O1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjMTg3NUQwO1xcbiAgICAgIHdpZHRoOiA2MTBweDtcXG4gICAgICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuICAgICAgLmRpYWxvZyA+IC5mb290ZXIgPiAuaW5wdXRGb3JtID4gdGV4dGFyZWEge1xcbiAgICAgICAgYm9yZGVyOiBub25lO1xcbiAgICAgICAgcmVzaXplOiBub25lO1xcbiAgICAgICAgYmFja2dyb3VuZC1jb2xvcjogdHJhbnNwYXJlbnQ7XFxuICAgICAgICBvdXRsaW5lOiBub25lO1xcbiAgICAgICAgaGVpZ2h0OiAzMHB4O1xcbiAgICAgICAgY29sb3I6ICMwYzBjMWU7XFxuICAgICAgICBmb250OiA0MDAgMTJweCAvIDE1cHggUm9ib3RvO1xcbiAgICAgICAgd2lkdGg6IDYzMHB4O1xcbiAgICAgICAgcGFkZGluZy1yaWdodDogNTBweDtcXG4gICAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgICAuZGlhbG9nID4gLmZvb3RlciA+IC5pbnB1dEZvcm0gPiAuc3ltYm9scyB7XFxuICAgICAgICBkaXNwbGF5OiBub25lOyB9XFxuICAgIC5kaWFsb2cgPiAuZm9vdGVyID4gLmNsaXAge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvY2xpcC5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgICAgaGVpZ2h0OiAyMHB4O1xcbiAgICAgIHdpZHRoOiAyMHB4O1xcbiAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgICBib3R0b206IDEwcHg7XFxuICAgICAgcmlnaHQ6IDIyMHB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICB6LWluZGV4OiAxOyB9XFxuICAgIC5kaWFsb2cgPiAuZm9vdGVyID4gLmJ0bi1ibHVlIHtcXG4gICAgICBib3gtc2hhZG93OiAwcHggMnB4IDRweCAwcHggcmdiYSgwLCAwLCAwLCAwLjQpO1xcbiAgICAgIGhlaWdodDogMzVweDtcXG4gICAgICB3aWR0aDogMTgwcHg7XFxuICAgICAgbGluZS1oZWlnaHQ6IDM1cHg7XFxuICAgICAgbWFyZ2luLWxlZnQ6IDMwcHg7IH1cXG5cXG4ubWluaUNvbnRhY3RzIHtcXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gIHRvcDogMTE1cHg7XFxuICBsZWZ0OiA1NDBweDtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBoZWlnaHQ6IDM5MHB4O1xcbiAgd2lkdGg6IDMzNXB4O1xcbiAgYm94LXNoYWRvdzogMHB4IDJweCAycHggMHB4IHJnYmEoMCwgMCwgMCwgMC4zKTtcXG4gIHotaW5kZXg6IDE7IH1cXG4gIC5taW5pQ29udGFjdHMgPiAuaGVhZGVyIHtcXG4gICAgaGVpZ2h0OiA2NXB4O1xcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiAjMTg3NUQwOyB9XFxuICAgIC5taW5pQ29udGFjdHMgPiAuaGVhZGVyID4gLmJhY2sge1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYmFjay5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAgIGhlaWdodDogMjVweDtcXG4gICAgICB3aWR0aDogNDVweDtcXG4gICAgICBmbG9hdDogbGVmdDtcXG4gICAgICBtYXJnaW4tbGVmdDogMjBweDtcXG4gICAgICBtYXJnaW4tdG9wOiAyMHB4O1xcbiAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgdHJhbnNpdGlvbjogYWxsIC4yNXM7IH1cXG4gICAgICAubWluaUNvbnRhY3RzID4gLmhlYWRlciA+IC5iYWNrLnRvQ29udGFjdHMge1xcbiAgICAgICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy90b0NvbnRhY3RzLnBuZ1wiKSArIFwiKTtcXG4gICAgICAgIGhlaWdodDogMzBweDtcXG4gICAgICAgIC13ZWJraXQtdHJhbnNpdGlvbjogYWxsIC4yNXM7XFxuICAgICAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAubWluaUNvbnRhY3RzID4gLmhlYWRlciA+IC5jbG9zZSB7XFxuICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jbG9zZS5wbmdcIikgKyBcIikgbm8tcmVwZWF0IGNlbnRlciBjZW50ZXI7XFxuICAgICAgaGVpZ2h0OiAyNXB4O1xcbiAgICAgIHdpZHRoOiAyNXB4O1xcbiAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgbWFyZ2luLXJpZ2h0OiAyMHB4O1xcbiAgICAgIG1hcmdpbi10b3A6IDIwcHg7IH1cXG4gIC5taW5pQ29udGFjdHMgPiAudGFibGUge1xcbiAgICBoZWlnaHQ6IDMyNXB4O1xcbiAgICBvdmVyZmxvdzogYXV0bztcXG4gICAgZGlzcGxheTogbm9uZTsgfVxcbiAgICAubWluaUNvbnRhY3RzID4gLnRhYmxlID4gLnRyIHtcXG4gICAgICBwYWRkaW5nOiAxNXB4IDA7IH1cXG4gICAgICAubWluaUNvbnRhY3RzID4gLnRhYmxlID4gLnRyID4gLnRkIHtcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgaGVpZ2h0OiA0MHB4O1xcbiAgICAgICAgcG9zaXRpb246IHJlbGF0aXZlOyB9XFxuICAgICAgICAubWluaUNvbnRhY3RzID4gLnRhYmxlID4gLnRyID4gLnRkOm50aC1vZi10eXBlKDEpIHtcXG4gICAgICAgICAgd2lkdGg6IDg1cHg7IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAudGFibGUgPiAudHIgPiAudGQ6bnRoLW9mLXR5cGUoMikge1xcbiAgICAgICAgICB3aWR0aDogMTgwcHg7IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAudGFibGUgPiAudHIgPiAudGQgPiAuYXZhdGFyIHtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IDE1cHg7IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAudGFibGUgPiAudHIgPiAudGQgPiAudXNlcm5hbWUge1xcbiAgICAgICAgICBjb2xvcjogIzY2NjY2NjtcXG4gICAgICAgICAgZm9udDogNDAwIDE0cHggLyA0MHB4IFJvYm90bztcXG4gICAgICAgICAgd2lkdGg6IDE4MHB4O1xcbiAgICAgICAgICB3aGl0ZS1zcGFjZTogbm93cmFwO1xcbiAgICAgICAgICBvdmVyZmxvdzogaGlkZGVuO1xcbiAgICAgICAgICB0ZXh0LW92ZXJmbG93OiBlbGxpcHNpczsgfVxcbiAgICAgICAgLm1pbmlDb250YWN0cyA+IC50YWJsZSA+IC50ciA+IC50ZCA+IC5vbk9yT2ZmTGluZVVzZXIge1xcbiAgICAgICAgICBtYXJnaW46IDEwcHggMTVweDsgfVxcbiAgLm1pbmlDb250YWN0cyA+IC53cmFwTWVzc2FnZXMge1xcbiAgICBvdmVyZmxvdzogaGlkZGVuOyB9XFxuICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzIHtcXG4gICAgICBvdmVyZmxvdzogYXV0bztcXG4gICAgICBoZWlnaHQ6IDI2NXB4O1xcbiAgICAgIHdpZHRoOiAzNTVweDtcXG4gICAgICBwYWRkaW5nOiAxNXB4IDM1cHggMCAxNXB4O1xcbiAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgICAubWluaUNvbnRhY3RzID4gLndyYXBNZXNzYWdlcyA+IC5tZXNzYWdlcyA+IC5wb3N0SW50ZXJsb2N1dG9yIHtcXG4gICAgICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnBvc3RJbnRlcmxvY3V0b3IgPiAuYXZhdGFyIHtcXG4gICAgICAgICAgZmxvYXQ6IGxlZnQ7IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnBvc3RJbnRlcmxvY3V0b3IgPiBwIHtcXG4gICAgICAgICAgbWFyZ2luLWxlZnQ6IDE1cHg7XFxuICAgICAgICAgIGNvbG9yOiAjMGMwYzFlO1xcbiAgICAgICAgICBmb250OiA0MDAgMTJweCBSb2JvdG87XFxuICAgICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgICB3aWR0aDogMTkwcHg7IH1cXG4gICAgICAubWluaUNvbnRhY3RzID4gLndyYXBNZXNzYWdlcyA+IC5tZXNzYWdlcyA+IC55b3VyQW5zd2VyIHtcXG4gICAgICAgIG1hcmdpbi1ib3R0b206IDQwcHg7IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLm1lc3NhZ2VzID4gLnlvdXJBbnN3ZXIgPiAuYXZhdGFyIHtcXG4gICAgICAgICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy95b3VyQXZhdGFyLnBuZ1wiKSArIFwiKTtcXG4gICAgICAgICAgZmxvYXQ6IHJpZ2h0OyB9XFxuICAgICAgICAubWluaUNvbnRhY3RzID4gLndyYXBNZXNzYWdlcyA+IC5tZXNzYWdlcyA+IC55b3VyQW5zd2VyID4gcCB7XFxuICAgICAgICAgIG1hcmdpbi1yaWdodDogMTVweDtcXG4gICAgICAgICAgY29sb3I6ICMwYzBjMWU7XFxuICAgICAgICAgIGZvbnQ6IDQwMCAxMnB4IFJvYm90bztcXG4gICAgICAgICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICAgICAgICB3aWR0aDogMTkwcHg7XFxuICAgICAgICAgIHRleHQtYWxpZ246IHJpZ2h0OyB9XFxuICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLmZvb3RlciB7XFxuICAgICAgaGVpZ2h0OiA2MHB4O1xcbiAgICAgIGJvcmRlci10b3A6IDFweCBzb2xpZCAjREJEQkRCO1xcbiAgICAgIHBhZGRpbmc6IDE1cHg7XFxuICAgICAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcbiAgICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLmZvb3RlciA+IC5pbnB1dEZvcm0ge1xcbiAgICAgICAgYm9yZGVyLWNvbG9yOiAjMTg3NUQwICFpbXBvcnRhbnQ7XFxuICAgICAgICBjb2xvcjogIzBjMGMxZTtcXG4gICAgICAgIGZvbnQ6IDQwMCAxMnB4IFJvYm90bztcXG4gICAgICAgIHdpZHRoOiAzMDVweDtcXG4gICAgICAgIG92ZXJmbG93OiBoaWRkZW47IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLmZvb3RlciA+IC5pbnB1dEZvcm0gPiAuc3ltYm9scyB7XFxuICAgICAgICAgIGRpc3BsYXk6IG5vbmU7IH1cXG4gICAgICAgIC5taW5pQ29udGFjdHMgPiAud3JhcE1lc3NhZ2VzID4gLmZvb3RlciA+IC5pbnB1dEZvcm0gPiB0ZXh0YXJlYSB7XFxuICAgICAgICAgIGJvcmRlcjogbm9uZTtcXG4gICAgICAgICAgb3V0bGluZTogbm9uZTtcXG4gICAgICAgICAgcmVzaXplOiBub25lO1xcbiAgICAgICAgICB3aWR0aDogMzIycHg7XFxuICAgICAgICAgIHBhZGRpbmctcmlnaHQ6IDQ1cHg7XFxuICAgICAgICAgIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG4gICAgICAubWluaUNvbnRhY3RzID4gLndyYXBNZXNzYWdlcyA+IC5mb290ZXIgPiAuY2xpcCB7XFxuICAgICAgICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2NsaXAucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgICAgICAgaGVpZ2h0OiAyMHB4O1xcbiAgICAgICAgd2lkdGg6IDIwcHg7XFxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICBib3R0b206IDE1cHg7XFxuICAgICAgICByaWdodDogMjBweDtcXG4gICAgICAgIGN1cnNvcjogcG9pbnRlcjtcXG4gICAgICAgIHotaW5kZXg6IDE7IH1cXG5cIiwgXCJcIl0pO1xuXG4vLyBleHBvcnRzXG5cblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vfi9jc3MtbG9hZGVyIS4vfi9wb3N0Y3NzLWxvYWRlciEuL34vc2Fzcy1sb2FkZXIhLi9zdHlsZXMvYmFzaWMuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8qXHJcblx0TUlUIExpY2Vuc2UgaHR0cDovL3d3dy5vcGVuc291cmNlLm9yZy9saWNlbnNlcy9taXQtbGljZW5zZS5waHBcclxuXHRBdXRob3IgVG9iaWFzIEtvcHBlcnMgQHNva3JhXHJcbiovXHJcbi8vIGNzcyBiYXNlIGNvZGUsIGluamVjdGVkIGJ5IHRoZSBjc3MtbG9hZGVyXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcblx0dmFyIGxpc3QgPSBbXTtcclxuXHJcblx0Ly8gcmV0dXJuIHRoZSBsaXN0IG9mIG1vZHVsZXMgYXMgY3NzIHN0cmluZ1xyXG5cdGxpc3QudG9TdHJpbmcgPSBmdW5jdGlvbiB0b1N0cmluZygpIHtcclxuXHRcdHZhciByZXN1bHQgPSBbXTtcclxuXHRcdGZvcih2YXIgaSA9IDA7IGkgPCB0aGlzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBpdGVtID0gdGhpc1tpXTtcclxuXHRcdFx0aWYoaXRlbVsyXSkge1xyXG5cdFx0XHRcdHJlc3VsdC5wdXNoKFwiQG1lZGlhIFwiICsgaXRlbVsyXSArIFwie1wiICsgaXRlbVsxXSArIFwifVwiKTtcclxuXHRcdFx0fSBlbHNlIHtcclxuXHRcdFx0XHRyZXN1bHQucHVzaChpdGVtWzFdKTtcclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdFx0cmV0dXJuIHJlc3VsdC5qb2luKFwiXCIpO1xyXG5cdH07XHJcblxyXG5cdC8vIGltcG9ydCBhIGxpc3Qgb2YgbW9kdWxlcyBpbnRvIHRoZSBsaXN0XHJcblx0bGlzdC5pID0gZnVuY3Rpb24obW9kdWxlcywgbWVkaWFRdWVyeSkge1xyXG5cdFx0aWYodHlwZW9mIG1vZHVsZXMgPT09IFwic3RyaW5nXCIpXHJcblx0XHRcdG1vZHVsZXMgPSBbW251bGwsIG1vZHVsZXMsIFwiXCJdXTtcclxuXHRcdHZhciBhbHJlYWR5SW1wb3J0ZWRNb2R1bGVzID0ge307XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgdGhpcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaWQgPSB0aGlzW2ldWzBdO1xyXG5cdFx0XHRpZih0eXBlb2YgaWQgPT09IFwibnVtYmVyXCIpXHJcblx0XHRcdFx0YWxyZWFkeUltcG9ydGVkTW9kdWxlc1tpZF0gPSB0cnVlO1xyXG5cdFx0fVxyXG5cdFx0Zm9yKGkgPSAwOyBpIDwgbW9kdWxlcy5sZW5ndGg7IGkrKykge1xyXG5cdFx0XHR2YXIgaXRlbSA9IG1vZHVsZXNbaV07XHJcblx0XHRcdC8vIHNraXAgYWxyZWFkeSBpbXBvcnRlZCBtb2R1bGVcclxuXHRcdFx0Ly8gdGhpcyBpbXBsZW1lbnRhdGlvbiBpcyBub3QgMTAwJSBwZXJmZWN0IGZvciB3ZWlyZCBtZWRpYSBxdWVyeSBjb21iaW5hdGlvbnNcclxuXHRcdFx0Ly8gIHdoZW4gYSBtb2R1bGUgaXMgaW1wb3J0ZWQgbXVsdGlwbGUgdGltZXMgd2l0aCBkaWZmZXJlbnQgbWVkaWEgcXVlcmllcy5cclxuXHRcdFx0Ly8gIEkgaG9wZSB0aGlzIHdpbGwgbmV2ZXIgb2NjdXIgKEhleSB0aGlzIHdheSB3ZSBoYXZlIHNtYWxsZXIgYnVuZGxlcylcclxuXHRcdFx0aWYodHlwZW9mIGl0ZW1bMF0gIT09IFwibnVtYmVyXCIgfHwgIWFscmVhZHlJbXBvcnRlZE1vZHVsZXNbaXRlbVswXV0pIHtcclxuXHRcdFx0XHRpZihtZWRpYVF1ZXJ5ICYmICFpdGVtWzJdKSB7XHJcblx0XHRcdFx0XHRpdGVtWzJdID0gbWVkaWFRdWVyeTtcclxuXHRcdFx0XHR9IGVsc2UgaWYobWVkaWFRdWVyeSkge1xyXG5cdFx0XHRcdFx0aXRlbVsyXSA9IFwiKFwiICsgaXRlbVsyXSArIFwiKSBhbmQgKFwiICsgbWVkaWFRdWVyeSArIFwiKVwiO1xyXG5cdFx0XHRcdH1cclxuXHRcdFx0XHRsaXN0LnB1c2goaXRlbSk7XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHR9O1xyXG5cdHJldHVybiBsaXN0O1xyXG59O1xyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBub2RlX21vZHVsZXMvY3NzLWxvYWRlci9saWIvY3NzLWJhc2UuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FsZW5kYXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jYWxlbmRhci5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FyZXRDYWxlbmRhci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhcmV0Q2FsZW5kYXIucG5nXG4gKiogbW9kdWxlIGlkID0gNVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2xvZ28ucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9sb2dvLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9hZGQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9hZGQucG5nXG4gKiogbW9kdWxlIGlkID0gN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL21haWwucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9tYWlsLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9tYWlsX3NoYWRvdy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL21haWxfc2hhZG93LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9iZWxsLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYmVsbC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxMFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2JlbGxfc2hhZG93LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvYmVsbF9zaGFkb3cucG5nXG4gKiogbW9kdWxlIGlkID0gMTFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9zZXJ2aWNlcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3NlcnZpY2VzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDEyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvc2VydmljZXNfc2hhZG93LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvc2VydmljZXNfc2hhZG93LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDEzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvdXNlck5hbWUucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy91c2VyTmFtZS5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxNFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL25leHQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9uZXh0LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvcHJldi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3ByZXYucG5nXG4gKiogbW9kdWxlIGlkID0gMTZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9lcnJvcl9iZy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2Vycm9yX2JnLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDE3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvdmsucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy92ay5wbmdcbiAqKiBtb2R1bGUgaWQgPSAxOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2ZhY2Vib29rLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZmFjZWJvb2sucG5nXG4gKiogbW9kdWxlIGlkID0gMTlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9nb29nbGUucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9nb29nbGUucG5nXG4gKiogbW9kdWxlIGlkID0gMjBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9hZGRDYWxlbmRhci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2FkZENhbGVuZGFyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvbWFwLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbWFwLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDIyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2FyZXRSaWdodE5hdi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NhcmV0UmlnaHROYXYucG5nXG4gKiogbW9kdWxlIGlkID0gMjNcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Gb3JDaGlsZHJlbi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0ZvckNoaWxkcmVuLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvRm9yQW5pbWFscy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0ZvckFuaW1hbHMucG5nXG4gKiogbW9kdWxlIGlkID0gMjVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9CdXNpbmVzcy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL0J1c2luZXNzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDI2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVGhlUHJvcGVydHkucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9UaGVQcm9wZXJ0eS5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyN1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL1RyYW5zcG9ydC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1RyYW5zcG9ydC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAyOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0Nsb3RoaW5nQW5kQ29zbWV0aWNzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvQ2xvdGhpbmdBbmRDb3NtZXRpY3MucG5nXG4gKiogbW9kdWxlIGlkID0gMjlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Wb2x1bnRlZXJpbmcucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Wb2x1bnRlZXJpbmcucG5nXG4gKiogbW9kdWxlIGlkID0gMzBcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9Jc0ZyZWUucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Jc0ZyZWUucG5nXG4gKiogbW9kdWxlIGlkID0gMzFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9BSG91c2VBbmRBR2FyZGVuLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvQUhvdXNlQW5kQUdhcmRlbi5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzMlxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL0hvYmJpZXNBbmRTcG9ydHMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9Ib2JiaWVzQW5kU3BvcnRzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDMzXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvQmFydGVyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvQmFydGVyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVGVjaG5vbG9naWVzLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvVGVjaG5vbG9naWVzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDM1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvcnVwci5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3J1cHIucG5nXG4gKiogbW9kdWxlIGlkID0gMzZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9ydXBySG92ZXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9ydXBySG92ZXIucG5nXG4gKiogbW9kdWxlIGlkID0gMzdcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9sb29wU2VhcmNnLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvbG9vcFNlYXJjZy5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzOFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NvdC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NvdC5wbmdcbiAqKiBtb2R1bGUgaWQgPSAzOVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3lvdXJBdmF0YXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy95b3VyQXZhdGFyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2xpcC5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2NsaXAucG5nXG4gKiogbW9kdWxlIGlkID0gNDFcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIi8qXHJcblx0TUlUIExpY2Vuc2UgaHR0cDovL3d3dy5vcGVuc291cmNlLm9yZy9saWNlbnNlcy9taXQtbGljZW5zZS5waHBcclxuXHRBdXRob3IgVG9iaWFzIEtvcHBlcnMgQHNva3JhXHJcbiovXHJcbnZhciBzdHlsZXNJbkRvbSA9IHt9LFxyXG5cdG1lbW9pemUgPSBmdW5jdGlvbihmbikge1xyXG5cdFx0dmFyIG1lbW87XHJcblx0XHRyZXR1cm4gZnVuY3Rpb24gKCkge1xyXG5cdFx0XHRpZiAodHlwZW9mIG1lbW8gPT09IFwidW5kZWZpbmVkXCIpIG1lbW8gPSBmbi5hcHBseSh0aGlzLCBhcmd1bWVudHMpO1xyXG5cdFx0XHRyZXR1cm4gbWVtbztcclxuXHRcdH07XHJcblx0fSxcclxuXHRpc09sZElFID0gbWVtb2l6ZShmdW5jdGlvbigpIHtcclxuXHRcdHJldHVybiAvbXNpZSBbNi05XVxcYi8udGVzdCh3aW5kb3cubmF2aWdhdG9yLnVzZXJBZ2VudC50b0xvd2VyQ2FzZSgpKTtcclxuXHR9KSxcclxuXHRnZXRIZWFkRWxlbWVudCA9IG1lbW9pemUoZnVuY3Rpb24gKCkge1xyXG5cdFx0cmV0dXJuIGRvY3VtZW50LmhlYWQgfHwgZG9jdW1lbnQuZ2V0RWxlbWVudHNCeVRhZ05hbWUoXCJoZWFkXCIpWzBdO1xyXG5cdH0pLFxyXG5cdHNpbmdsZXRvbkVsZW1lbnQgPSBudWxsLFxyXG5cdHNpbmdsZXRvbkNvdW50ZXIgPSAwLFxyXG5cdHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wID0gW107XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKGxpc3QsIG9wdGlvbnMpIHtcclxuXHRpZih0eXBlb2YgREVCVUcgIT09IFwidW5kZWZpbmVkXCIgJiYgREVCVUcpIHtcclxuXHRcdGlmKHR5cGVvZiBkb2N1bWVudCAhPT0gXCJvYmplY3RcIikgdGhyb3cgbmV3IEVycm9yKFwiVGhlIHN0eWxlLWxvYWRlciBjYW5ub3QgYmUgdXNlZCBpbiBhIG5vbi1icm93c2VyIGVudmlyb25tZW50XCIpO1xyXG5cdH1cclxuXHJcblx0b3B0aW9ucyA9IG9wdGlvbnMgfHwge307XHJcblx0Ly8gRm9yY2Ugc2luZ2xlLXRhZyBzb2x1dGlvbiBvbiBJRTYtOSwgd2hpY2ggaGFzIGEgaGFyZCBsaW1pdCBvbiB0aGUgIyBvZiA8c3R5bGU+XHJcblx0Ly8gdGFncyBpdCB3aWxsIGFsbG93IG9uIGEgcGFnZVxyXG5cdGlmICh0eXBlb2Ygb3B0aW9ucy5zaW5nbGV0b24gPT09IFwidW5kZWZpbmVkXCIpIG9wdGlvbnMuc2luZ2xldG9uID0gaXNPbGRJRSgpO1xyXG5cclxuXHQvLyBCeSBkZWZhdWx0LCBhZGQgPHN0eWxlPiB0YWdzIHRvIHRoZSBib3R0b20gb2YgPGhlYWQ+LlxyXG5cdGlmICh0eXBlb2Ygb3B0aW9ucy5pbnNlcnRBdCA9PT0gXCJ1bmRlZmluZWRcIikgb3B0aW9ucy5pbnNlcnRBdCA9IFwiYm90dG9tXCI7XHJcblxyXG5cdHZhciBzdHlsZXMgPSBsaXN0VG9TdHlsZXMobGlzdCk7XHJcblx0YWRkU3R5bGVzVG9Eb20oc3R5bGVzLCBvcHRpb25zKTtcclxuXHJcblx0cmV0dXJuIGZ1bmN0aW9uIHVwZGF0ZShuZXdMaXN0KSB7XHJcblx0XHR2YXIgbWF5UmVtb3ZlID0gW107XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgc3R5bGVzLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBpdGVtID0gc3R5bGVzW2ldO1xyXG5cdFx0XHR2YXIgZG9tU3R5bGUgPSBzdHlsZXNJbkRvbVtpdGVtLmlkXTtcclxuXHRcdFx0ZG9tU3R5bGUucmVmcy0tO1xyXG5cdFx0XHRtYXlSZW1vdmUucHVzaChkb21TdHlsZSk7XHJcblx0XHR9XHJcblx0XHRpZihuZXdMaXN0KSB7XHJcblx0XHRcdHZhciBuZXdTdHlsZXMgPSBsaXN0VG9TdHlsZXMobmV3TGlzdCk7XHJcblx0XHRcdGFkZFN0eWxlc1RvRG9tKG5ld1N0eWxlcywgb3B0aW9ucyk7XHJcblx0XHR9XHJcblx0XHRmb3IodmFyIGkgPSAwOyBpIDwgbWF5UmVtb3ZlLmxlbmd0aDsgaSsrKSB7XHJcblx0XHRcdHZhciBkb21TdHlsZSA9IG1heVJlbW92ZVtpXTtcclxuXHRcdFx0aWYoZG9tU3R5bGUucmVmcyA9PT0gMCkge1xyXG5cdFx0XHRcdGZvcih2YXIgaiA9IDA7IGogPCBkb21TdHlsZS5wYXJ0cy5sZW5ndGg7IGorKylcclxuXHRcdFx0XHRcdGRvbVN0eWxlLnBhcnRzW2pdKCk7XHJcblx0XHRcdFx0ZGVsZXRlIHN0eWxlc0luRG9tW2RvbVN0eWxlLmlkXTtcclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdH07XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGFkZFN0eWxlc1RvRG9tKHN0eWxlcywgb3B0aW9ucykge1xyXG5cdGZvcih2YXIgaSA9IDA7IGkgPCBzdHlsZXMubGVuZ3RoOyBpKyspIHtcclxuXHRcdHZhciBpdGVtID0gc3R5bGVzW2ldO1xyXG5cdFx0dmFyIGRvbVN0eWxlID0gc3R5bGVzSW5Eb21baXRlbS5pZF07XHJcblx0XHRpZihkb21TdHlsZSkge1xyXG5cdFx0XHRkb21TdHlsZS5yZWZzKys7XHJcblx0XHRcdGZvcih2YXIgaiA9IDA7IGogPCBkb21TdHlsZS5wYXJ0cy5sZW5ndGg7IGorKykge1xyXG5cdFx0XHRcdGRvbVN0eWxlLnBhcnRzW2pdKGl0ZW0ucGFydHNbal0pO1xyXG5cdFx0XHR9XHJcblx0XHRcdGZvcig7IGogPCBpdGVtLnBhcnRzLmxlbmd0aDsgaisrKSB7XHJcblx0XHRcdFx0ZG9tU3R5bGUucGFydHMucHVzaChhZGRTdHlsZShpdGVtLnBhcnRzW2pdLCBvcHRpb25zKSk7XHJcblx0XHRcdH1cclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHZhciBwYXJ0cyA9IFtdO1xyXG5cdFx0XHRmb3IodmFyIGogPSAwOyBqIDwgaXRlbS5wYXJ0cy5sZW5ndGg7IGorKykge1xyXG5cdFx0XHRcdHBhcnRzLnB1c2goYWRkU3R5bGUoaXRlbS5wYXJ0c1tqXSwgb3B0aW9ucykpO1xyXG5cdFx0XHR9XHJcblx0XHRcdHN0eWxlc0luRG9tW2l0ZW0uaWRdID0ge2lkOiBpdGVtLmlkLCByZWZzOiAxLCBwYXJ0czogcGFydHN9O1xyXG5cdFx0fVxyXG5cdH1cclxufVxyXG5cclxuZnVuY3Rpb24gbGlzdFRvU3R5bGVzKGxpc3QpIHtcclxuXHR2YXIgc3R5bGVzID0gW107XHJcblx0dmFyIG5ld1N0eWxlcyA9IHt9O1xyXG5cdGZvcih2YXIgaSA9IDA7IGkgPCBsaXN0Lmxlbmd0aDsgaSsrKSB7XHJcblx0XHR2YXIgaXRlbSA9IGxpc3RbaV07XHJcblx0XHR2YXIgaWQgPSBpdGVtWzBdO1xyXG5cdFx0dmFyIGNzcyA9IGl0ZW1bMV07XHJcblx0XHR2YXIgbWVkaWEgPSBpdGVtWzJdO1xyXG5cdFx0dmFyIHNvdXJjZU1hcCA9IGl0ZW1bM107XHJcblx0XHR2YXIgcGFydCA9IHtjc3M6IGNzcywgbWVkaWE6IG1lZGlhLCBzb3VyY2VNYXA6IHNvdXJjZU1hcH07XHJcblx0XHRpZighbmV3U3R5bGVzW2lkXSlcclxuXHRcdFx0c3R5bGVzLnB1c2gobmV3U3R5bGVzW2lkXSA9IHtpZDogaWQsIHBhcnRzOiBbcGFydF19KTtcclxuXHRcdGVsc2VcclxuXHRcdFx0bmV3U3R5bGVzW2lkXS5wYXJ0cy5wdXNoKHBhcnQpO1xyXG5cdH1cclxuXHRyZXR1cm4gc3R5bGVzO1xyXG59XHJcblxyXG5mdW5jdGlvbiBpbnNlcnRTdHlsZUVsZW1lbnQob3B0aW9ucywgc3R5bGVFbGVtZW50KSB7XHJcblx0dmFyIGhlYWQgPSBnZXRIZWFkRWxlbWVudCgpO1xyXG5cdHZhciBsYXN0U3R5bGVFbGVtZW50SW5zZXJ0ZWRBdFRvcCA9IHN0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wW3N0eWxlRWxlbWVudHNJbnNlcnRlZEF0VG9wLmxlbmd0aCAtIDFdO1xyXG5cdGlmIChvcHRpb25zLmluc2VydEF0ID09PSBcInRvcFwiKSB7XHJcblx0XHRpZighbGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3ApIHtcclxuXHRcdFx0aGVhZC5pbnNlcnRCZWZvcmUoc3R5bGVFbGVtZW50LCBoZWFkLmZpcnN0Q2hpbGQpO1xyXG5cdFx0fSBlbHNlIGlmKGxhc3RTdHlsZUVsZW1lbnRJbnNlcnRlZEF0VG9wLm5leHRTaWJsaW5nKSB7XHJcblx0XHRcdGhlYWQuaW5zZXJ0QmVmb3JlKHN0eWxlRWxlbWVudCwgbGFzdFN0eWxlRWxlbWVudEluc2VydGVkQXRUb3AubmV4dFNpYmxpbmcpO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0aGVhZC5hcHBlbmRDaGlsZChzdHlsZUVsZW1lbnQpO1xyXG5cdFx0fVxyXG5cdFx0c3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AucHVzaChzdHlsZUVsZW1lbnQpO1xyXG5cdH0gZWxzZSBpZiAob3B0aW9ucy5pbnNlcnRBdCA9PT0gXCJib3R0b21cIikge1xyXG5cdFx0aGVhZC5hcHBlbmRDaGlsZChzdHlsZUVsZW1lbnQpO1xyXG5cdH0gZWxzZSB7XHJcblx0XHR0aHJvdyBuZXcgRXJyb3IoXCJJbnZhbGlkIHZhbHVlIGZvciBwYXJhbWV0ZXIgJ2luc2VydEF0Jy4gTXVzdCBiZSAndG9wJyBvciAnYm90dG9tJy5cIik7XHJcblx0fVxyXG59XHJcblxyXG5mdW5jdGlvbiByZW1vdmVTdHlsZUVsZW1lbnQoc3R5bGVFbGVtZW50KSB7XHJcblx0c3R5bGVFbGVtZW50LnBhcmVudE5vZGUucmVtb3ZlQ2hpbGQoc3R5bGVFbGVtZW50KTtcclxuXHR2YXIgaWR4ID0gc3R5bGVFbGVtZW50c0luc2VydGVkQXRUb3AuaW5kZXhPZihzdHlsZUVsZW1lbnQpO1xyXG5cdGlmKGlkeCA+PSAwKSB7XHJcblx0XHRzdHlsZUVsZW1lbnRzSW5zZXJ0ZWRBdFRvcC5zcGxpY2UoaWR4LCAxKTtcclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGNyZWF0ZVN0eWxlRWxlbWVudChvcHRpb25zKSB7XHJcblx0dmFyIHN0eWxlRWxlbWVudCA9IGRvY3VtZW50LmNyZWF0ZUVsZW1lbnQoXCJzdHlsZVwiKTtcclxuXHRzdHlsZUVsZW1lbnQudHlwZSA9IFwidGV4dC9jc3NcIjtcclxuXHRpbnNlcnRTdHlsZUVsZW1lbnQob3B0aW9ucywgc3R5bGVFbGVtZW50KTtcclxuXHRyZXR1cm4gc3R5bGVFbGVtZW50O1xyXG59XHJcblxyXG5mdW5jdGlvbiBjcmVhdGVMaW5rRWxlbWVudChvcHRpb25zKSB7XHJcblx0dmFyIGxpbmtFbGVtZW50ID0gZG9jdW1lbnQuY3JlYXRlRWxlbWVudChcImxpbmtcIik7XHJcblx0bGlua0VsZW1lbnQucmVsID0gXCJzdHlsZXNoZWV0XCI7XHJcblx0aW5zZXJ0U3R5bGVFbGVtZW50KG9wdGlvbnMsIGxpbmtFbGVtZW50KTtcclxuXHRyZXR1cm4gbGlua0VsZW1lbnQ7XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGFkZFN0eWxlKG9iaiwgb3B0aW9ucykge1xyXG5cdHZhciBzdHlsZUVsZW1lbnQsIHVwZGF0ZSwgcmVtb3ZlO1xyXG5cclxuXHRpZiAob3B0aW9ucy5zaW5nbGV0b24pIHtcclxuXHRcdHZhciBzdHlsZUluZGV4ID0gc2luZ2xldG9uQ291bnRlcisrO1xyXG5cdFx0c3R5bGVFbGVtZW50ID0gc2luZ2xldG9uRWxlbWVudCB8fCAoc2luZ2xldG9uRWxlbWVudCA9IGNyZWF0ZVN0eWxlRWxlbWVudChvcHRpb25zKSk7XHJcblx0XHR1cGRhdGUgPSBhcHBseVRvU2luZ2xldG9uVGFnLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50LCBzdHlsZUluZGV4LCBmYWxzZSk7XHJcblx0XHRyZW1vdmUgPSBhcHBseVRvU2luZ2xldG9uVGFnLmJpbmQobnVsbCwgc3R5bGVFbGVtZW50LCBzdHlsZUluZGV4LCB0cnVlKTtcclxuXHR9IGVsc2UgaWYob2JqLnNvdXJjZU1hcCAmJlxyXG5cdFx0dHlwZW9mIFVSTCA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgVVJMLmNyZWF0ZU9iamVjdFVSTCA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgVVJMLnJldm9rZU9iamVjdFVSTCA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgQmxvYiA9PT0gXCJmdW5jdGlvblwiICYmXHJcblx0XHR0eXBlb2YgYnRvYSA9PT0gXCJmdW5jdGlvblwiKSB7XHJcblx0XHRzdHlsZUVsZW1lbnQgPSBjcmVhdGVMaW5rRWxlbWVudChvcHRpb25zKTtcclxuXHRcdHVwZGF0ZSA9IHVwZGF0ZUxpbmsuYmluZChudWxsLCBzdHlsZUVsZW1lbnQpO1xyXG5cdFx0cmVtb3ZlID0gZnVuY3Rpb24oKSB7XHJcblx0XHRcdHJlbW92ZVN0eWxlRWxlbWVudChzdHlsZUVsZW1lbnQpO1xyXG5cdFx0XHRpZihzdHlsZUVsZW1lbnQuaHJlZilcclxuXHRcdFx0XHRVUkwucmV2b2tlT2JqZWN0VVJMKHN0eWxlRWxlbWVudC5ocmVmKTtcclxuXHRcdH07XHJcblx0fSBlbHNlIHtcclxuXHRcdHN0eWxlRWxlbWVudCA9IGNyZWF0ZVN0eWxlRWxlbWVudChvcHRpb25zKTtcclxuXHRcdHVwZGF0ZSA9IGFwcGx5VG9UYWcuYmluZChudWxsLCBzdHlsZUVsZW1lbnQpO1xyXG5cdFx0cmVtb3ZlID0gZnVuY3Rpb24oKSB7XHJcblx0XHRcdHJlbW92ZVN0eWxlRWxlbWVudChzdHlsZUVsZW1lbnQpO1xyXG5cdFx0fTtcclxuXHR9XHJcblxyXG5cdHVwZGF0ZShvYmopO1xyXG5cclxuXHRyZXR1cm4gZnVuY3Rpb24gdXBkYXRlU3R5bGUobmV3T2JqKSB7XHJcblx0XHRpZihuZXdPYmopIHtcclxuXHRcdFx0aWYobmV3T2JqLmNzcyA9PT0gb2JqLmNzcyAmJiBuZXdPYmoubWVkaWEgPT09IG9iai5tZWRpYSAmJiBuZXdPYmouc291cmNlTWFwID09PSBvYmouc291cmNlTWFwKVxyXG5cdFx0XHRcdHJldHVybjtcclxuXHRcdFx0dXBkYXRlKG9iaiA9IG5ld09iaik7XHJcblx0XHR9IGVsc2Uge1xyXG5cdFx0XHRyZW1vdmUoKTtcclxuXHRcdH1cclxuXHR9O1xyXG59XHJcblxyXG52YXIgcmVwbGFjZVRleHQgPSAoZnVuY3Rpb24gKCkge1xyXG5cdHZhciB0ZXh0U3RvcmUgPSBbXTtcclxuXHJcblx0cmV0dXJuIGZ1bmN0aW9uIChpbmRleCwgcmVwbGFjZW1lbnQpIHtcclxuXHRcdHRleHRTdG9yZVtpbmRleF0gPSByZXBsYWNlbWVudDtcclxuXHRcdHJldHVybiB0ZXh0U3RvcmUuZmlsdGVyKEJvb2xlYW4pLmpvaW4oJ1xcbicpO1xyXG5cdH07XHJcbn0pKCk7XHJcblxyXG5mdW5jdGlvbiBhcHBseVRvU2luZ2xldG9uVGFnKHN0eWxlRWxlbWVudCwgaW5kZXgsIHJlbW92ZSwgb2JqKSB7XHJcblx0dmFyIGNzcyA9IHJlbW92ZSA/IFwiXCIgOiBvYmouY3NzO1xyXG5cclxuXHRpZiAoc3R5bGVFbGVtZW50LnN0eWxlU2hlZXQpIHtcclxuXHRcdHN0eWxlRWxlbWVudC5zdHlsZVNoZWV0LmNzc1RleHQgPSByZXBsYWNlVGV4dChpbmRleCwgY3NzKTtcclxuXHR9IGVsc2Uge1xyXG5cdFx0dmFyIGNzc05vZGUgPSBkb2N1bWVudC5jcmVhdGVUZXh0Tm9kZShjc3MpO1xyXG5cdFx0dmFyIGNoaWxkTm9kZXMgPSBzdHlsZUVsZW1lbnQuY2hpbGROb2RlcztcclxuXHRcdGlmIChjaGlsZE5vZGVzW2luZGV4XSkgc3R5bGVFbGVtZW50LnJlbW92ZUNoaWxkKGNoaWxkTm9kZXNbaW5kZXhdKTtcclxuXHRcdGlmIChjaGlsZE5vZGVzLmxlbmd0aCkge1xyXG5cdFx0XHRzdHlsZUVsZW1lbnQuaW5zZXJ0QmVmb3JlKGNzc05vZGUsIGNoaWxkTm9kZXNbaW5kZXhdKTtcclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHN0eWxlRWxlbWVudC5hcHBlbmRDaGlsZChjc3NOb2RlKTtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIGFwcGx5VG9UYWcoc3R5bGVFbGVtZW50LCBvYmopIHtcclxuXHR2YXIgY3NzID0gb2JqLmNzcztcclxuXHR2YXIgbWVkaWEgPSBvYmoubWVkaWE7XHJcblxyXG5cdGlmKG1lZGlhKSB7XHJcblx0XHRzdHlsZUVsZW1lbnQuc2V0QXR0cmlidXRlKFwibWVkaWFcIiwgbWVkaWEpXHJcblx0fVxyXG5cclxuXHRpZihzdHlsZUVsZW1lbnQuc3R5bGVTaGVldCkge1xyXG5cdFx0c3R5bGVFbGVtZW50LnN0eWxlU2hlZXQuY3NzVGV4dCA9IGNzcztcclxuXHR9IGVsc2Uge1xyXG5cdFx0d2hpbGUoc3R5bGVFbGVtZW50LmZpcnN0Q2hpbGQpIHtcclxuXHRcdFx0c3R5bGVFbGVtZW50LnJlbW92ZUNoaWxkKHN0eWxlRWxlbWVudC5maXJzdENoaWxkKTtcclxuXHRcdH1cclxuXHRcdHN0eWxlRWxlbWVudC5hcHBlbmRDaGlsZChkb2N1bWVudC5jcmVhdGVUZXh0Tm9kZShjc3MpKTtcclxuXHR9XHJcbn1cclxuXHJcbmZ1bmN0aW9uIHVwZGF0ZUxpbmsobGlua0VsZW1lbnQsIG9iaikge1xyXG5cdHZhciBjc3MgPSBvYmouY3NzO1xyXG5cdHZhciBzb3VyY2VNYXAgPSBvYmouc291cmNlTWFwO1xyXG5cclxuXHRpZihzb3VyY2VNYXApIHtcclxuXHRcdC8vIGh0dHA6Ly9zdGFja292ZXJmbG93LmNvbS9hLzI2NjAzODc1XHJcblx0XHRjc3MgKz0gXCJcXG4vKiMgc291cmNlTWFwcGluZ1VSTD1kYXRhOmFwcGxpY2F0aW9uL2pzb247YmFzZTY0LFwiICsgYnRvYSh1bmVzY2FwZShlbmNvZGVVUklDb21wb25lbnQoSlNPTi5zdHJpbmdpZnkoc291cmNlTWFwKSkpKSArIFwiICovXCI7XHJcblx0fVxyXG5cclxuXHR2YXIgYmxvYiA9IG5ldyBCbG9iKFtjc3NdLCB7IHR5cGU6IFwidGV4dC9jc3NcIiB9KTtcclxuXHJcblx0dmFyIG9sZFNyYyA9IGxpbmtFbGVtZW50LmhyZWY7XHJcblxyXG5cdGxpbmtFbGVtZW50LmhyZWYgPSBVUkwuY3JlYXRlT2JqZWN0VVJMKGJsb2IpO1xyXG5cclxuXHRpZihvbGRTcmMpXHJcblx0XHRVUkwucmV2b2tlT2JqZWN0VVJMKG9sZFNyYyk7XHJcbn1cclxuXG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL34vc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1xuICoqIG1vZHVsZSBpZCA9IDQyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2Zhdm91cml0ZXMuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9mYXZvdXJpdGVzLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9mYXZvdXJpdGVzLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvZmF2b3VyaXRlcy5zY3NzXG4gKiogbW9kdWxlIGlkID0gNDNcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwiQGNoYXJzZXQgXFxcIlVURi04XFxcIjtcXG4jZmF2b3VyaXRlcyB7XFxuICB3aWR0aDogOTkwcHg7XFxuICBtYXJnaW46IDNweCBhdXRvIDA7IH1cXG4gICNmYXZvdXJpdGVzIC5yaWdodC1jb2x1bW4ge1xcbiAgICB3aWR0aDogNDkwcHg7XFxuICAgIGZsb2F0OiByaWdodDsgfVxcbiAgI2Zhdm91cml0ZXMgLmxlZnQtY29sdW1uIHtcXG4gICAgd2lkdGg6IDQ5MHB4O1xcbiAgICBmbG9hdDogbGVmdDsgfVxcblxcbi5idWxsZXRpbi1zaG9ydCB7XFxuICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICBwYWRkaW5nOiAyMHB4IDE2cHg7XFxuICB3aWR0aDogNDkwcHg7XFxuICBtYXJnaW4tYm90dG9tOiA3cHg7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDtcXG4gIGJhY2tncm91bmQtY29sb3I6IHdoaXRlO1xcbiAgLyog0JvQtdCy0LDRjyDQutC+0LvQvtC90LrQsCAqL1xcbiAgLyog0J/RgNCw0LLQsNGPINC60L7Qu9C+0L3QutCwICovIH1cXG4gIC5idWxsZXRpbi1zaG9ydCA+IGRpdiB7XFxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jazsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1yaWdodC1jb2x1bW4ge1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIHdpZHRoOiA5MHB4O1xcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7IH1cXG4gICAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1yaWdodC1jb2x1bW4gPiAub25Pck9mZkxpbmVVc2VyIHtcXG4gICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgdG9wOiA1cHg7XFxuICAgICAgbGVmdDogMTBweDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1jZW50ZXItY29sdW1uIHtcXG4gICAgZmxvYXQ6IHJpZ2h0O1xcbiAgICBtYXJnaW4tcmlnaHQ6IDE1cHg7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCBoMyB7XFxuICAgIG1hcmdpbjogMDtcXG4gICAgZm9udDogNDAwIDIwcHggLyAyNHB4IFJvYm90bztcXG4gICAgY29sb3I6ICMyMTIxMjE7XFxuICAgIHdpZHRoOiAyMDZweDtcXG4gICAgY3Vyc29yOiBwb2ludGVyO1xcbiAgICAtd2Via2l0LXRyYW5zaXRpb246IGFsbCAuMjVzO1xcbiAgICB0cmFuc2l0aW9uOiBhbGwgLjI1czsgfVxcbiAgICAuYnVsbGV0aW4tc2hvcnQgaDM6aG92ZXIge1xcbiAgICAgIHRleHQtZGVjb3JhdGlvbjogdW5kZXJsaW5lO1xcbiAgICAgIC13ZWJraXQtdGV4dC1kZWNvcmF0aW9uLWNvbG9yOiBncmF5O1xcbiAgICAgICAgICAgICAgdGV4dC1kZWNvcmF0aW9uLWNvbG9yOiBncmF5O1xcbiAgICAgIC13ZWJraXQtdGV4dC1kZWNvcmF0aW9uLXN0eWxlOiBkYXNoZWQ7XFxuICAgICAgICAgICAgICB0ZXh0LWRlY29yYXRpb24tc3R5bGU6IGRhc2hlZDsgfVxcbiAgLmJ1bGxldGluLXNob3J0IC5idWxsZXRpbi1jYXRlZ29yeSB7XFxuICAgIGNvbG9yOiByZ2JhKDMxLCAzMSwgMzEsIDAuNTQpO1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE0LjRweCBSb2JvdG87XFxuICAgIG1hcmdpbi10b3A6IDExcHg7XFxuICAgIG1hcmdpbi1ib3R0b206IDE2cHg7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tZGVzY3JpcHRpb24ge1xcbiAgICBmb250OiA0MDAgMTJweCAvIDE4LjZweCBSb2JvdG87XFxuICAgIHdpZHRoOiAyNTRweDtcXG4gICAgY29sb3I6ICMwZDBkMWU7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4taW1hZ2Uge1xcbiAgICB3aWR0aDogOTBweDtcXG4gICAgaGVpZ2h0OiA5MXB4O1xcbiAgICBiYWNrZ3JvdW5kOiAjMTg3NUQwIHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvYnVsbGV0aW4tZGVmYXVsdC5wbmdcIikgKyBcIik7IH1cXG4gIC5idWxsZXRpbi1zaG9ydCAuYnVsbGV0aW4tcHJpY2Uge1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGJvdHRvbTogLTU0cHg7XFxuICAgIHJpZ2h0OiAwO1xcbiAgICBjb2xvcjogIzIwMjAyMDtcXG4gICAgZm9udDogNDAwIDE0cHggLyAxNi44cHggUm9ib3RvOyB9XFxuICAuYnVsbGV0aW4tc2hvcnQgLmJ1bGxldGluLWFjdGlvbiB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgYm90dG9tOiAyMHB4O1xcbiAgICByaWdodDogMTE3cHg7XFxuICAgIGNvbG9yOiAjMjEyMTIxO1xcbiAgICBmb250OiA0MDAgMTRweCAvIDE2LjhweCBSb2JvdG87IH1cXG4gIC5idWxsZXRpbi1zaG9ydCA+IC53cmFwUmliYm9uIHtcXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgIGJvdHRvbTogMDtcXG4gICAgbGVmdDogMDtcXG4gICAgei1pbmRleDogMTsgfVxcbiAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib24ge1xcbiAgICAgIHdpZHRoOiAxMDBweDtcXG4gICAgICBwb3NpdGlvbjogcmVsYXRpdmU7XFxuICAgICAgYmFja2dyb3VuZC1jb2xvcjogI0Y1OTExRDtcXG4gICAgICB0ZXh0LWFsaWduOiBjZW50ZXI7IH1cXG4gICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib246YmVmb3JlIHtcXG4gICAgICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgZGlzcGxheTogYmxvY2s7XFxuICAgICAgICBib3R0b206IC0xNHB4O1xcbiAgICAgICAgYm9yZGVyOiAxM3B4IHNvbGlkICNlNTdiMDA7XFxuICAgICAgICB6LWluZGV4OiAtMTtcXG4gICAgICAgIGxlZnQ6IC0yM3B4O1xcbiAgICAgICAgYm9yZGVyLXJpZ2h0LXdpZHRoOiAxLjVlbTtcXG4gICAgICAgIGJvcmRlci1sZWZ0LWNvbG9yOiB0cmFuc3BhcmVudDtcXG4gICAgICAgIGJveC1zaGFkb3c6IDJweCAycHggMnB4IDBweCByZ2JhKDAsIDAsIDAsIDAuMyk7IH1cXG4gICAgICAuYnVsbGV0aW4tc2hvcnQgPiAud3JhcFJpYmJvbiA+IC5yaWJib246YWZ0ZXIge1xcbiAgICAgICAgY29udGVudDogXFxcIlxcXCI7XFxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICBkaXNwbGF5OiBibG9jaztcXG4gICAgICAgIGJvdHRvbTogMDtcXG4gICAgICAgIGJvcmRlcjogMTNweCBzb2xpZCAjRjU5MTFEO1xcbiAgICAgICAgcmlnaHQ6IC0xM3B4O1xcbiAgICAgICAgYm9yZGVyLWxlZnQtd2lkdGg6IDA7XFxuICAgICAgICBib3JkZXItcmlnaHQtY29sb3I6IHRyYW5zcGFyZW50OyB9XFxuICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uID4gLnJpYmJvbi1jb250ZW50IHtcXG4gICAgICAgIGNvbG9yOiAjZmZmZmZmO1xcbiAgICAgICAgZm9udDogNDAwIDE0cHggLyAyNnB4IFJvYm90bztcXG4gICAgICAgIGN1cnNvcjogZGVmYXVsdDsgfVxcbiAgICAgICAgLmJ1bGxldGluLXNob3J0ID4gLndyYXBSaWJib24gPiAucmliYm9uID4gLnJpYmJvbi1jb250ZW50OmJlZm9yZSB7XFxuICAgICAgICAgIGNvbnRlbnQ6IFxcXCJcXFwiO1xcbiAgICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XFxuICAgICAgICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICAgICAgICBib3JkZXItc3R5bGU6IHNvbGlkO1xcbiAgICAgICAgICBib3JkZXItY29sb3I6ICMyQjRBNjcgdHJhbnNwYXJlbnQgdHJhbnNwYXJlbnQgdHJhbnNwYXJlbnQ7XFxuICAgICAgICAgIGJvdHRvbTogLTE0cHg7XFxuICAgICAgICAgIGxlZnQ6IDA7XFxuICAgICAgICAgIGJvcmRlci13aWR0aDogMWVtIDAgMCAxZW07IH1cXG5cXG4uY2hlY2tCb3gge1xcbiAgd2lkdGg6IDI1cHg7XFxuICBoZWlnaHQ6IDI1cHg7XFxuICBib3JkZXI6IDFweCBzb2xpZCBncmV5O1xcbiAgYm9yZGVyLXJhZGl1czogNXB4O1xcbiAgY3Vyc29yOiBwb2ludGVyO1xcbiAgLXdlYmtpdC10cmFuc2l0aW9uOiBhbGwgLjI1cztcXG4gIHRyYW5zaXRpb246IGFsbCAuMjVzOyB9XFxuXFxuLmNoZWNrZWQge1xcbiAgYmFja2dyb3VuZDogIzE4NzVEMCB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL1YucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgYm9yZGVyLWNvbG9yOiAjMTg3NUQwICFpbXBvcnRhbnQ7IH1cXG5cXG5kaXYuZXhjbGFtYXRpb25Qb2ludCB7XFxuICBiYWNrZ3JvdW5kOiB1cmwoXCIgKyByZXF1aXJlKFwiLi4vaW1hZ2VzL2V4Y2xhbWF0aW9uUG9pbnQucG5nXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgaGVpZ2h0OiAyN3B4O1xcbiAgd2lkdGg6IDI3cHg7XFxuICBtYXJnaW4tdG9wOiAxMHB4OyB9XFxuXFxuLmRvbGxhckJpbGwge1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9kb2xsYXJCaWxsLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQgY2VudGVyIGNlbnRlcjtcXG4gIGhlaWdodDogMjdweDtcXG4gIHdpZHRoOiAyN3B4O1xcbiAgbWFyZ2luLXRvcDogMTBweDsgfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9mYXZvdXJpdGVzLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0NFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2J1bGxldGluLWRlZmF1bHQucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9idWxsZXRpbi1kZWZhdWx0LnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQ1XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvVi5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL1YucG5nXG4gKiogbW9kdWxlIGlkID0gNDZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9leGNsYW1hdGlvblBvaW50LnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvZXhjbGFtYXRpb25Qb2ludC5wbmdcbiAqKiBtb2R1bGUgaWQgPSA0N1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2RvbGxhckJpbGwucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9kb2xsYXJCaWxsLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDQ4XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2VkaXQtcHJvZmlsZS5zY3NzXCIpO1xuaWYodHlwZW9mIGNvbnRlbnQgPT09ICdzdHJpbmcnKSBjb250ZW50ID0gW1ttb2R1bGUuaWQsIGNvbnRlbnQsICcnXV07XG4vLyBhZGQgdGhlIHN0eWxlcyB0byB0aGUgRE9NXG52YXIgdXBkYXRlID0gcmVxdWlyZShcIiEuLy4uL25vZGVfbW9kdWxlcy9zdHlsZS1sb2FkZXIvYWRkU3R5bGVzLmpzXCIpKGNvbnRlbnQsIHt9KTtcbmlmKGNvbnRlbnQubG9jYWxzKSBtb2R1bGUuZXhwb3J0cyA9IGNvbnRlbnQubG9jYWxzO1xuLy8gSG90IE1vZHVsZSBSZXBsYWNlbWVudFxuaWYobW9kdWxlLmhvdCkge1xuXHQvLyBXaGVuIHRoZSBzdHlsZXMgY2hhbmdlLCB1cGRhdGUgdGhlIDxzdHlsZT4gdGFnc1xuXHRpZighY29udGVudC5sb2NhbHMpIHtcblx0XHRtb2R1bGUuaG90LmFjY2VwdChcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL2VkaXQtcHJvZmlsZS5zY3NzXCIsIGZ1bmN0aW9uKCkge1xuXHRcdFx0dmFyIG5ld0NvbnRlbnQgPSByZXF1aXJlKFwiISEuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Bvc3Rjc3MtbG9hZGVyL2luZGV4LmpzIS4vLi4vbm9kZV9tb2R1bGVzL3Nhc3MtbG9hZGVyL2luZGV4LmpzIS4vZWRpdC1wcm9maWxlLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvZWRpdC1wcm9maWxlLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA0OVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwiZXhwb3J0cyA9IG1vZHVsZS5leHBvcnRzID0gcmVxdWlyZShcIi4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvbGliL2Nzcy1iYXNlLmpzXCIpKCk7XG4vLyBpbXBvcnRzXG5cblxuLy8gbW9kdWxlXG5leHBvcnRzLnB1c2goW21vZHVsZS5pZCwgXCJzZWN0aW9uLmVkaXRQcm9maWxlIHtcXG4gIGJhY2tncm91bmQtY29sb3I6ICNGREZERkQ7XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpO1xcbiAgd2lkdGg6IDExMDVweDtcXG4gIG1hcmdpbjogNXB4IGF1dG8gNDBweDtcXG4gIHBhZGRpbmc6IDY1cHggMCA0NXB4IDA7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIHtcXG4gICAgd2lkdGg6IDY1MHB4O1xcbiAgICBtYXJnaW46IDAgYXV0bzsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gaDIge1xcbiAgICAgIGZvbnQ6IDcwMCAyMnB4IFJvYm90bztcXG4gICAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgICAgbWFyZ2luLWJvdHRvbTogNDBweDsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLnNlbGVjdEJveCB7XFxuICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICM5ZTllOWU7XFxuICAgICAgbWFyZ2luLXRvcDogMTVweDtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5zZWxlY3RCb3ggPiAuZGVmYXVsdFZhbHVlIHtcXG4gICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgICAgcGFkZGluZy1sZWZ0OiA1cHg7IH1cXG4gICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvIHtcXG4gICAgICBmbG9hdDogcmlnaHQ7XFxuICAgICAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjOWU5ZTllO1xcbiAgICAgIHdpZHRoOiA0MTBweDtcXG4gICAgICBwYWRkaW5nLWJvdHRvbTogNXB4OyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvID4gaW5wdXQge1xcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xcbiAgICAgICAgei1pbmRleDogLTE7IH1cXG4gICAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlID4gLmVkaXQtcHJvZmlsZS1mb3JtID4gLmVkaXQtcHJvZmlsZS1mb3JtLWZvdG8gPiBwIHtcXG4gICAgICAgIGNvbG9yOiAjOWU5ZTllO1xcbiAgICAgICAgZm9udDogNDAwIDE2cHggUm9ib3RvO1xcbiAgICAgICAgZmxvYXQ6IGxlZnQ7XFxuICAgICAgICBwYWRkaW5nLXRvcDogMTFweDtcXG4gICAgICAgIHBhZGRpbmctbGVmdDogNXB4O1xcbiAgICAgICAgY3Vyc29yOiBkZWZhdWx0OyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSA+IC5lZGl0LXByb2ZpbGUtZm9ybSA+IC5lZGl0LXByb2ZpbGUtZm9ybS1mb3RvID4gLmJ0bi1ibHVlIHtcXG4gICAgICAgIGJveC1zaGFkb3c6IDBweCAycHggNHB4IDBweCByZ2JhKDAsIDAsIDAsIDAuNCk7XFxuICAgICAgICBoZWlnaHQ6IDMwcHg7XFxuICAgICAgICB3aWR0aDogODVweDtcXG4gICAgICAgIGZsb2F0OiByaWdodDtcXG4gICAgICAgIGxpbmUtaGVpZ2h0OiAzMHB4OyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgLmVkaXQtcHJvZmlsZS1mb3JtLWNvbnRhY3RzLWNvbnRhaW5lciAuaW5wdXRGb3JtIHtcXG4gICAgICB3aWR0aDogOTUlO1xcbiAgICAgIGRpc3BsYXk6IGlubGluZS1ibG9jazsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlID4gLnZpZXctZWRpdC1wcm9maWxlIC5lZGl0LXByb2ZpbGUtZm9ybS1jb250YWN0cy1jb250YWluZXIgYnV0dG9uIHtcXG4gICAgICB3aWR0aDogMTZweDtcXG4gICAgICBoZWlnaHQ6IDE2cHg7XFxuICAgICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgICAgYmFja2dyb3VuZC1zaXplOiBjb250YWluOyB9XFxuICAgIHNlY3Rpb24uZWRpdFByb2ZpbGUgPiAudmlldy1lZGl0LXByb2ZpbGUgLnNvY2lhbC1saW5rLWNvbnRhaW5lciB7XFxuICAgICAgaGVpZ2h0OiBhdXRvOyB9XFxuICAgICAgc2VjdGlvbi5lZGl0UHJvZmlsZSA+IC52aWV3LWVkaXQtcHJvZmlsZSAuc29jaWFsLWxpbmstY29udGFpbmVyIGRpdiB7XFxuICAgICAgICB3aWR0aDogMjRweDtcXG4gICAgICAgIGhlaWdodDogMjRweDtcXG4gICAgICAgIGZsb2F0OiBsZWZ0O1xcbiAgICAgICAgbWFyZ2luLXJpZ2h0OiA0N3B4OyB9XFxuICBzZWN0aW9uLmVkaXRQcm9maWxlIC51cGxvYWRGaWxlRm9ybSB7XFxuICAgIHZpc2liaWxpdHk6IGhpZGRlbjsgfVxcbiAgICBzZWN0aW9uLmVkaXRQcm9maWxlIC51cGxvYWRGaWxlRm9ybSBpbnB1dCB7XFxuICAgICAgd2lkdGg6IDBweDtcXG4gICAgICBoZWlnaHQ6IDBweDsgfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9lZGl0LXByb2ZpbGUuc2Nzc1xuICoqIG1vZHVsZSBpZCA9IDUwXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvaWNvbl9jbG9zZV9ibHVlLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDUxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCIvLyBzdHlsZS1sb2FkZXI6IEFkZHMgc29tZSBjc3MgdG8gdGhlIERPTSBieSBhZGRpbmcgYSA8c3R5bGU+IHRhZ1xuXG4vLyBsb2FkIHRoZSBzdHlsZXNcbnZhciBjb250ZW50ID0gcmVxdWlyZShcIiEhLi8uLi9ub2RlX21vZHVsZXMvY3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9wb3N0Y3NzLWxvYWRlci9pbmRleC5qcyEuLy4uL25vZGVfbW9kdWxlcy9zYXNzLWxvYWRlci9pbmRleC5qcyEuL3Byb2ZpbGUuc2Nzc1wiKTtcbmlmKHR5cGVvZiBjb250ZW50ID09PSAnc3RyaW5nJykgY29udGVudCA9IFtbbW9kdWxlLmlkLCBjb250ZW50LCAnJ11dO1xuLy8gYWRkIHRoZSBzdHlsZXMgdG8gdGhlIERPTVxudmFyIHVwZGF0ZSA9IHJlcXVpcmUoXCIhLi8uLi9ub2RlX21vZHVsZXMvc3R5bGUtbG9hZGVyL2FkZFN0eWxlcy5qc1wiKShjb250ZW50LCB7fSk7XG5pZihjb250ZW50LmxvY2FscykgbW9kdWxlLmV4cG9ydHMgPSBjb250ZW50LmxvY2Fscztcbi8vIEhvdCBNb2R1bGUgUmVwbGFjZW1lbnRcbmlmKG1vZHVsZS5ob3QpIHtcblx0Ly8gV2hlbiB0aGUgc3R5bGVzIGNoYW5nZSwgdXBkYXRlIHRoZSA8c3R5bGU+IHRhZ3Ncblx0aWYoIWNvbnRlbnQubG9jYWxzKSB7XG5cdFx0bW9kdWxlLmhvdC5hY2NlcHQoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIiwgZnVuY3Rpb24oKSB7XG5cdFx0XHR2YXIgbmV3Q29udGVudCA9IHJlcXVpcmUoXCIhIS4vLi4vbm9kZV9tb2R1bGVzL2Nzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvcG9zdGNzcy1sb2FkZXIvaW5kZXguanMhLi8uLi9ub2RlX21vZHVsZXMvc2Fzcy1sb2FkZXIvaW5kZXguanMhLi9wcm9maWxlLnNjc3NcIik7XG5cdFx0XHRpZih0eXBlb2YgbmV3Q29udGVudCA9PT0gJ3N0cmluZycpIG5ld0NvbnRlbnQgPSBbW21vZHVsZS5pZCwgbmV3Q29udGVudCwgJyddXTtcblx0XHRcdHVwZGF0ZShuZXdDb250ZW50KTtcblx0XHR9KTtcblx0fVxuXHQvLyBXaGVuIHRoZSBtb2R1bGUgaXMgZGlzcG9zZWQsIHJlbW92ZSB0aGUgPHN0eWxlPiB0YWdzXG5cdG1vZHVsZS5ob3QuZGlzcG9zZShmdW5jdGlvbigpIHsgdXBkYXRlKCk7IH0pO1xufVxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9zdHlsZXMvcHJvZmlsZS5zY3NzXG4gKiogbW9kdWxlIGlkID0gNTJcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsImV4cG9ydHMgPSBtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCIuLy4uL25vZGVfbW9kdWxlcy9jc3MtbG9hZGVyL2xpYi9jc3MtYmFzZS5qc1wiKSgpO1xuLy8gaW1wb3J0c1xuXG5cbi8vIG1vZHVsZVxuZXhwb3J0cy5wdXNoKFttb2R1bGUuaWQsIFwiLnZpZXctcHJvZmlsZS1jb250YWluZXIge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI2ZmZjtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7XFxuICBwYWRkaW5nOiA0MHB4IDEyN3B4IDMwcHg7XFxuICBtYXJnaW46IDVweCBhdXRvIDA7XFxuICB3aWR0aDogMTEwM3B4O1xcbiAgZm9udDogNDAwIDE2cHgvMjRweCBSb2JvdG87XFxuICBib3gtc2hhZG93OiAwcHggMnB4IDJweCAwcHggcmdiYSgwLCAwLCAwLCAwLjMpOyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBoMSB7XFxuICAgIHBhZGRpbmctYm90dG9tOiAxNnB4O1xcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XFxuICAgIGZvbnQ6IDQwMCAyMnB4LzI2cHggUm9ib3RvOyB9XFxuICAudmlldy1wcm9maWxlLWNvbnRhaW5lciBpbnB1dDotbW96LXJlYWQtb25seSB7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7IH1cXG4gIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0OnJlYWQtb25seSB7XFxuICAgIHdpZHRoOiAxMDAlO1xcbiAgICBib3JkZXI6IG5vbmU7XFxuICAgIG91dGxpbmU6IG5vbmU7IH1cXG4gICAgLnZpZXctcHJvZmlsZS1jb250YWluZXIgaW5wdXQ6LW1vei1yZWFkLW9ubHk6bm90KDpsYXN0LW9mLXR5cGUpIHtcXG4gICAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuICAgIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIGlucHV0OnJlYWQtb25seTpub3QoOmxhc3Qtb2YtdHlwZSkge1xcbiAgICAgIG1hcmdpbi1ib3R0b206IDQ1cHg7IH1cXG4gIC52aWV3LXByb2ZpbGUtY29udGFpbmVyIC5zb2NpYWwtbGluay1jb250YWluZXIge1xcbiAgICBtYXJnaW4tYm90dG9tOiA0NXB4OyB9XFxuXFxuLnByb2ZpbGUtY29udGFpbmVycy13cmFwIHtcXG4gIHdpZHRoOiA4NDlweDtcXG4gIG1hcmdpbjogMCBhdXRvOyB9XFxuXFxuLnByb2ZpbGUtbGVmdC1jb250YWluZXIsIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLWxlZnQsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1yaWdodCwgLnByb2ZpbGUtaW5mbyB7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXI6YmVmb3JlLCAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lcjphZnRlciwgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyOmJlZm9yZSwgLnByb2ZpbGUtcmlnaHQtY29udGFpbmVyOmFmdGVyLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tbGVmdDpiZWZvcmUsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0OmFmdGVyLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQ6YmVmb3JlLCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQ6YWZ0ZXIsIC5wcm9maWxlLWluZm86YmVmb3JlLCAucHJvZmlsZS1pbmZvOmFmdGVyIHtcXG4gICAgY29udGVudDogXFxcIiBcXFwiO1xcbiAgICBkaXNwbGF5OiB0YWJsZTsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXI6YWZ0ZXIsIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lcjphZnRlciwgLnByb2ZpbGUtaW5mby1zZWN0aW9uLWxlZnQ6YWZ0ZXIsIC5wcm9maWxlLWluZm8tc2VjdGlvbi1yaWdodDphZnRlciwgLnByb2ZpbGUtaW5mbzphZnRlciB7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuXFxuLnZpZXctcHJvZmlsZTpiZWZvcmUsIC52aWV3LXByb2ZpbGU6YWZ0ZXIsIC52aWV3LXByb2ZpbGUtY29udGFpbmVyOmJlZm9yZSwgLnZpZXctcHJvZmlsZS1jb250YWluZXI6YWZ0ZXIge1xcbiAgY29udGVudDogXFxcIiBcXFwiO1xcbiAgZGlzcGxheTogdGFibGU7IH1cXG5cXG4udmlldy1wcm9maWxlOmFmdGVyLCAudmlldy1wcm9maWxlLWNvbnRhaW5lcjphZnRlciB7XFxuICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLWxlZnQtY29udGFpbmVyIHtcXG4gIHdpZHRoOiAxOTBweDtcXG4gIHBhZGRpbmc6IDAgNTJweDsgfVxcbiAgLnByb2ZpbGUtbGVmdC1jb250YWluZXIgLmJ0bi1ibHVlIHtcXG4gICAgd2lkdGg6IDE4MHB4O1xcbiAgICBoZWlnaHQ6IDM2cHg7XFxuICAgIGxpbmUtaGVpZ2h0OiAzNnB4O1xcbiAgICBtYXJnaW46IDAgYXV0bztcXG4gICAgbWFyZ2luLXRvcDogOXB4OyB9XFxuICAucHJvZmlsZS1sZWZ0LWNvbnRhaW5lciA+IC5oei1jZW50ZXJpbmctd3JhcHBlciA+IC5wcm9maWxlLXNldHRpbmdzID4gLnNlbGVjdEJveCB7XFxuICAgIGRpc3BsYXk6IGJsb2NrO1xcbiAgICBtYXJnaW4tYm90dG9tOiAyNXB4OyB9XFxuICAgIC5wcm9maWxlLWxlZnQtY29udGFpbmVyID4gLmh6LWNlbnRlcmluZy13cmFwcGVyID4gLnByb2ZpbGUtc2V0dGluZ3MgPiAuc2VsZWN0Qm94ID4gLmRlZmF1bHRWYWx1ZSB7XFxuICAgICAgY29sb3I6ICM5ZTllOWU7XFxuICAgICAgZm9udDogNDAwIDE2cHggLyAyNHB4IFJvYm90bztcXG4gICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgIzllOWU5ZTsgfVxcblxcbi5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciB7XFxuICB3aWR0aDogNDg1cHg7XFxuICBwYWRkaW5nLWxlZnQ6IDIwcHg7XFxuICBib3JkZXItbGVmdDogMXB4IHNvbGlkICNlYmViZWI7IH1cXG4gIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciA+IC53cmFwRm9yRGl2IHtcXG4gICAgd2lkdGg6IGF1dG87XFxuICAgIGZsb2F0OiBub25lO1xcbiAgICBtYXJnaW4tYm90dG9tOiAwOyB9XFxuICAgIC5wcm9maWxlLXJpZ2h0LWNvbnRhaW5lciA+IC53cmFwRm9yRGl2ID4gdWwudGFiID4gbGkge1xcbiAgICAgIHdpZHRoOiA1MCU7IH1cXG4gICAgICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiA+IHVsLnRhYiA+IGxpID4gYSB7XFxuICAgICAgICB3aWR0aDogYXV0bzsgfVxcbiAgICAucHJvZmlsZS1yaWdodC1jb250YWluZXIgPiAud3JhcEZvckRpdiA+IC5mZWF0dXJlc0FuZFJldmlld3Mge1xcbiAgICAgIHdpZHRoOiA1MDBweDsgfVxcblxcbi5wcm9maWxlLWluZm8tc2VjdGlvbi1sZWZ0LCAucHJvZmlsZS1pbmZvLXNlY3Rpb24tcmlnaHQge1xcbiAgd2lkdGg6IDUwJTtcXG4gIGJveC1zaXppbmc6IGJvcmRlci1ib3g7IH1cXG5cXG4ucHJvZmlsZS1hdmF0YXIge1xcbiAgd2lkdGg6IDE0NXB4O1xcbiAgaGVpZ2h0OiAyMTVweDtcXG4gIG1hcmdpbjogMCBhdXRvIDQwcHg7XFxuICBwYWRkaW5nLWJvdHRvbTogNXB4O1xcbiAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9hdmF0YXIuanBnXCIpICsgXCIpIG5vLXJlcGVhdCBjZW50ZXIgY2VudGVyO1xcbiAgYmFja2dyb3VuZC1zaXplOiBjb250YWluO1xcbiAgYm94LXNpemluZzogYm9yZGVyLWJveDsgfVxcblxcbi5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIHtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIHBhZGRpbmc6IDEzcHggMDsgfVxcbiAgLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24gLnByb2ZpbGUtc2V0dGluZ3MtZHJvcGRvd24tdGl0bGU6YmVmb3JlLCAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93biAucHJvZmlsZS1zZXR0aW5ncy1kcm9wZG93bi10aXRsZTphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlOmFmdGVyIHtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIHNwYW4ge1xcbiAgICBjb2xvcjogIzkyOTI5MjtcXG4gICAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duIC5wcm9maWxlLXNldHRpbmdzLWRyb3Bkb3duLXRpdGxlIGRpdiB7XFxuICAgIGZsb2F0OiByaWdodDtcXG4gICAgYmFja2dyb3VuZDogdXJsKFwiICsgcmVxdWlyZShcIi4uL2ltYWdlcy9jYXJldF9ibGFjay5wbmdcIikgKyBcIik7XFxuICAgIHdpZHRoOiAxM3B4O1xcbiAgICBoZWlnaHQ6IDdweDsgfVxcblxcbi5zb2NpYWwtbGluay1jb250YWluZXIgZGl2IHtcXG4gIGN1cnNvcjogcG9pbnRlcjtcXG4gIHdpZHRoOiAyNHB4O1xcbiAgaGVpZ2h0OiAyNHB4O1xcbiAgZmxvYXQ6IGxlZnQ7IH1cXG4gIC5zb2NpYWwtbGluay1jb250YWluZXIgZGl2OmJlZm9yZSwgLnNvY2lhbC1saW5rLWNvbnRhaW5lciBkaXY6YWZ0ZXIge1xcbiAgICBjb250ZW50OiBcXFwiIFxcXCI7XFxuICAgIGRpc3BsYXk6IHRhYmxlOyB9XFxuICAuc29jaWFsLWxpbmstY29udGFpbmVyIGRpdjphZnRlciB7XFxuICAgIGNsZWFyOiBib3RoOyB9XFxuICAuc29jaWFsLWxpbmstY29udGFpbmVyIGRpdjpub3QoOmxhc3Qtb2YtdHlwZSkge1xcbiAgICBtYXJnaW4tcmlnaHQ6IDIzcHg7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucyB7XFxuICBkaXNwbGF5OiBibG9jaztcXG4gIHdpZHRoOiAxMDAlO1xcbiAgbWFyZ2luOiAzMHB4IDA7XFxuICBmbG9hdDogbGVmdDsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnM6YmVmb3JlLCAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9uczphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zOmFmdGVyIHtcXG4gICAgY2xlYXI6IGJvdGg7IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYsIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQge1xcbiAgYm9yZGVyOiAxcHggc29saWQgI2ViZWJlYjtcXG4gIG92ZXJmbG93OiBoaWRkZW47IH1cXG5cXG4ucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYgZGl2IHtcXG4gIGNvbG9yOiAjOTI5MjkyO1xcbiAgd2lkdGg6IDUwJTtcXG4gIHBhZGRpbmc6IDI0cHggMDtcXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcXG4gIGZsb2F0OiBsZWZ0OyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1uYXYgZGl2OmJlZm9yZSwgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtbmF2IGRpdjphZnRlciB7XFxuICAgIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gICAgZGlzcGxheTogdGFibGU7IH1cXG4gIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLW5hdiBkaXY6YWZ0ZXIge1xcbiAgICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLW5hdiAubmF2LWl0ZW0tc2VsZWN0ZWQge1xcbiAgY29sb3I6ICM3ZWFlZTA7XFxuICBib3JkZXItYm90dG9tOiAycHggc29saWQgI2ZmNTI1MjsgfVxcblxcbi5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQge1xcbiAgYmFja2dyb3VuZC1jb2xvcjogI2Y2ZjZmNjtcXG4gIGhlaWdodDogMTgwcHg7XFxuICBib3gtc2l6aW5nOiBib3JkZXItYm94OyB9XFxuXFxuLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIHtcXG4gIHdpZHRoOiAxMDAlO1xcbiAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNlYmViZWI7XFxuICBoZWlnaHQ6IDEyM3B4O1xcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xcbiAgb3ZlcmZsb3c6IGhpZGRlbjsgfVxcbiAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIC5jb250ZW50LWl0ZW0tbG9nbyB7XFxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcXG4gICAgdG9wOiAwO1xcbiAgICBsZWZ0OiAwO1xcbiAgICB3aWR0aDogMjdweDtcXG4gICAgaGVpZ2h0OiAyN3B4O1xcbiAgICBwYWRkaW5nOiAwIDIzcHggMjNweCA3cHg7XFxuICAgIGJhY2tncm91bmQ6IHVybChcIiArIHJlcXVpcmUoXCIuLi9pbWFnZXMvaWNvbl91c2VyLnBuZ1wiKSArIFwiKSBuby1yZXBlYXQ7XFxuICAgIGRpc3BsYXk6IGlubGluZS1ibG9jaztcXG4gICAgYmFja2dyb3VuZC1wb3NpdGlvbjogY2VudGVyO1xcbiAgICBtYXJnaW4tdG9wOiAyOHB4OyB9XFxuICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS10ZXh0IHtcXG4gICAgZGlzcGxheTogaW5saW5lLWJsb2NrO1xcbiAgICBmbG9hdDogcmlnaHQ7XFxuICAgIHdpZHRoOiAzNzNweDtcXG4gICAgbWFyZ2luOiAyOHB4IDM4cHggMjRweCAwO1xcbiAgICBmb250OiA0MDAgMTJweC8xOHB4IFJvYm90bztcXG4gICAgY29sb3I6ICMwYzBjMWU7IH1cXG4gICAgLnByb2ZpbGUtbWVzc2FnZXMtYW5kLW5vdGlmaWNhdGlvbnMtY29udGVudC1pdGVtIC5jb250ZW50LWl0ZW0tdGV4dDpiZWZvcmUsIC5wcm9maWxlLW1lc3NhZ2VzLWFuZC1ub3RpZmljYXRpb25zLWNvbnRlbnQtaXRlbSAuY29udGVudC1pdGVtLXRleHQ6YWZ0ZXIge1xcbiAgICAgIGNvbnRlbnQ6IFxcXCIgXFxcIjtcXG4gICAgICBkaXNwbGF5OiB0YWJsZTsgfVxcbiAgICAucHJvZmlsZS1tZXNzYWdlcy1hbmQtbm90aWZpY2F0aW9ucy1jb250ZW50LWl0ZW0gLmNvbnRlbnQtaXRlbS10ZXh0OmFmdGVyIHtcXG4gICAgICBjbGVhcjogYm90aDsgfVxcblxcbi5wcm9maWxlLWluZm8ge1xcbiAgbWFyZ2luLWJvdHRvbTogMzBweDsgfVxcblwiLCBcIlwiXSk7XG5cbi8vIGV4cG9ydHNcblxuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9+L2Nzcy1sb2FkZXIhLi9+L3Bvc3Rjc3MtbG9hZGVyIS4vfi9zYXNzLWxvYWRlciEuL3N0eWxlcy9wcm9maWxlLnNjc3NcbiAqKiBtb2R1bGUgaWQgPSA1M1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2F2YXRhci5qcGdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2F2YXRhci5qcGdcbiAqKiBtb2R1bGUgaWQgPSA1NFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2NhcmV0X2JsYWNrLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvY2FyZXRfYmxhY2sucG5nXG4gKiogbW9kdWxlIGlkID0gNTVcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy9pY29uX3VzZXIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9pY29uX3VzZXIucG5nXG4gKiogbW9kdWxlIGlkID0gNTZcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgZGVidWcgPSByZXF1aXJlKCcuLi9kYXRhL2RlYnVnJylcclxuXHJcbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIGN0eC5ERUJVR19MRVZFTCA9IGRlYnVnLkRFQlVHXHJcblxyXG4gIGNvbnNvbGUubG9nID0gKGZ1bmN0aW9uKCkge1xyXG4gICAgbGV0IGxvZyA9IGNvbnNvbGUubG9nXHJcbiAgICBpZiggY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLkRFQlVHIClcclxuICAgICAgcmV0dXJuIGxvZ1xyXG4gICAgZWxzZSByZXR1cm4gKCkgPT4ge31cclxuICB9LmJpbmQoY3R4KSkoKVxyXG5cclxuICBjb25zb2xlLmVycm9yID0gKGZ1bmN0aW9uKCkge1xyXG4gICAgbGV0IGVycm9yID0gY29uc29sZS5lcnJvclxyXG4gICAgaWYoIGN0eC5ERUJVR19MRVZFTCA9PSBkZWJ1Zy5ERUJVRyB8fCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuT05MWV9FUlJPUlMgKVxyXG4gICAgICByZXR1cm4gZXJyb3JcclxuICAgIGVsc2UgcmV0dXJuICgpID0+IHt9XHJcbiAgfS5iaW5kKGN0eCkpKClcclxuXHJcbiAgY29uc29sZS5pbmZvID0gKGZ1bmN0aW9uKCkge1xyXG4gICAgbGV0IGluZm8gPSBjb25zb2xlLmluZm9cclxuICAgIGlmKCBjdHguREVCVUdfTEVWRUwgPT0gZGVidWcuREVCVUcgfHwgY3R4LkRFQlVHX0xFVkVMID09IGRlYnVnLk9OTFlfRVJST1JTIClcclxuICAgICAgcmV0dXJuIGluZm9cclxuICAgIGVsc2UgcmV0dXJuICgpID0+IHt9XHJcbiAgfS5iaW5kKGN0eCkpKClcclxuXHJcbiAgLyogINCU0LvRjyDRhdC+0YXQvNGLICovXHJcbiAgd2luZG93LmxzID0gXCJZb3UndmUgbWlzc2VkIGEgd2luZG93LCBsb2wgPSlcIlxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbG9nZ2VyLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSB7XG5cdFwiREVCVUdcIjogMCxcblx0XCJPTkxZX0VSUk9SU1wiOiAxLFxuXHRcIlBST0RVQ1RJT05cIjogMlxufTtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vZGF0YS9kZWJ1Zy5qc29uXG4gKiogbW9kdWxlIGlkID0gNThcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgTU9EVUxFUyA9IHtcclxuICBcImNoZWNrYm94XCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvY2hlY2tib3gnKSxcclxuICBcIm5pY2VCdXR0b25cIiA6IHJlcXVpcmUoJy4vZGlyZWN0aXZlcy9uaWNlQnV0dG9uJyksXHJcbiAgXCJ0ZXh0XCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvdGV4dCcpLFxyXG4gIFwic2VsZWN0Qm94XCIgOiByZXF1aXJlKCcuL2RpcmVjdGl2ZXMvc2VsZWN0Qm94JyksXHJcbiAgXCJ0ZXh0QXJlYVwiIDogcmVxdWlyZSgnLi9kaXJlY3RpdmVzL3RleHRBcmVhJylcclxufVxyXG5cclxud2luZG93LmVlID0gcmVxdWlyZSgnLi9ldmVudHMnKVxyXG5lZS5pbml0KClcclxuXHJcbmxldCBjdHggPSBtb2R1bGUuZXhwb3J0cyA9IHt9XHJcblxyXG5tb2R1bGUuZXhwb3J0cy5pbml0ID0gYXBwID0+IHtcclxuICBmb3IobGV0IGtleSBpbiBNT0RVTEVTKVxyXG4gICAgYXBwLmRpcmVjdGl2ZShrZXksIE1PRFVMRVNba2V5XSlcclxuXHJcbiAgcmV0dXJuIGFwcFxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2luZGV4LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIHJldHVybiB7XHJcbiAgICByZXN0cmljdDogXCJFXCIsXHJcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxyXG4gICAgc2NvcGU6IHtcclxuICAgICAgbmdNb2RlbDogXCI9XCJcclxuICAgIH0sXHJcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJjaGVja0JveFwiPjwvZGl2PmAsXHJcbiAgICByZXBsYWNlOiB0cnVlLFxyXG4gICAgY29udHJvbGxlcjogZnVuY3Rpb24oJHNjb3BlLCAkZWxlbWVudCkge1xyXG4gICAgICBsZXQgZWwgPSAkZWxlbWVudFswXVxyXG4gICAgICAvLy5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdjaGVja0JveCcpWzBdXHJcblxyXG4gICAgICBpZigkc2NvcGUubmdNb2RlbCAmJiAhZWwuY2xhc3NMaXN0LmNvbnRhaW5zKCdjaGVja2VkJykpXHJcbiAgICAgICAgZWwuY2xhc3NMaXN0LmFkZCgnY2hlY2tlZCcpXHJcbiAgICAgIGVsc2UgaWYoISRzY29wZS5uZ01vZGVsICYmIGVsLmNsYXNzTGlzdC5jb250YWlucygnY2hlY2tlZCcpKVxyXG4gICAgICAgIGVsLmNsYXNzTGlzdC5yZW1vdmUoJ2NoZWtlZCcpXHJcblxyXG4gICAgICBlbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGUgPT4ge1xyXG4gICAgICAgIGVsLmNsYXNzTGlzdC50b2dnbGUoJ2NoZWNrZWQnKVxyXG4gICAgICAgICRzY29wZS5uZ01vZGVsID0gJHNjb3BlLm5nTW9kZWwgPyAgZmFsc2UgOiB0cnVlXHJcbiAgICAgIH0pXHJcbiAgICB9XHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvY2hlY2tib3guanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgcmV0dXJuIHtcclxuICAgIHJlc3RyaWN0OiBcIkVcIixcclxuICAgIHRyYW5zY2x1ZGU6IHRydWUsXHJcbiAgICBzY29wZSA6IHtcclxuICAgICAgY2xhc3M6IFwiQFwiLFxyXG4gICAgICBuZ0NsaWNrOiBcIiZcIlxyXG4gICAgfSxcclxuICAgIHJlcGxhY2U6IHRydWUsXHJcbiAgICB0ZW1wbGF0ZSA6IGA8ZGl2IGNsYXNzPVwie3sgY2xhc3MgfX1cIj5cclxuICAgICAgICAgICAgICAgICAgPHNwYW4gY2xhc3M9XCJpbmtcIj48L3NwYW4+XHJcbiAgICAgICAgICAgICAgICAgIDxuZy10cmFuc2NsdWRlIHN0eWxlPVwiZGlzcGxheTpibG9jazsgd2lkdGg6MTAwJTsgaGVpZ2h0OmluaGVyaXQ7XCI+PC9uZy10cmFuc2NsdWRlPlxyXG4gICAgICAgICAgICAgICAgPC9kaXY+YCxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQpIHtcclxuICAgICAgbGV0IG9uQ2xpY2sgPSBmdW5jdGlvbihlKSB7XHJcbiAgICAgICAgbGV0IGluayA9IHRoaXMuZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnaW5rJylbMF1cclxuICAgICAgICBpbmsuY2xhc3NMaXN0LnJlbW92ZSgnYW5pbWF0ZScpXHJcblxyXG4gICAgICAgIGxldCByZWN0ID0gdGhpcy5nZXRCb3VuZGluZ0NsaWVudFJlY3QoKVxyXG5cclxuICAgICAgICBpZiggIWluay5jbGllbnRIZWlnaHQgJiYgIWluay5jbGllbnRXaWR0aCApIHtcclxuICAgICAgICAgIGxldCBkID0gTWF0aC5tYXgodGhpcy5jbGllbnRXaWR0aCwgdGhpcy5jbGllbnRIZWlnaHQpXHJcbiAgICAgICAgICBpbmsuc3R5bGUuaGVpZ2h0ID0gaW5rLnN0eWxlLndpZHRoID0gYCR7ZH1weGBcclxuICAgICAgICB9XHJcblxyXG4gICAgICAgIGluay5zdHlsZS50b3AgPSBgJHtlLmNsaWVudFkgLSByZWN0LnRvcCAtIGluay5jbGllbnRIZWlnaHQvMn1weGBcclxuICAgICAgICBpbmsuc3R5bGUubGVmdCA9IGAke2UuY2xpZW50WCAtIHJlY3QubGVmdCAtaW5rLmNsaWVudFdpZHRoLzJ9cHhgXHJcbiAgICAgICAgaW5rLmNsYXNzTGlzdC5hZGQoJ2FuaW1hdGUnKVxyXG4gICAgICB9XHJcblxyXG4gICAgICBpZigkc2NvcGUubmdDbGljaylcclxuICAgICAgICAkZWxlbWVudFswXS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsICRzY29wZS5uZ0NsaWNrKVxyXG4gICAgICAgIFxyXG4gICAgICAkZWxlbWVudFswXS5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIG9uQ2xpY2spXHJcbiAgICB9XHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvbWF0ZXJpYWxzL2RpcmVjdGl2ZXMvbmljZUJ1dHRvbi5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgQ09MT1JTID0ge1xyXG4gICAgYmx1ZTogXCIjMTg3NUQwXCIsXHJcbiAgICB3aGl0ZTogXCJ3aGl0ZVwiXHJcbn1cclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgcmV0dXJuIHtcclxuICAgIHJlc3RyaWN0OiBcIkVcIixcclxuICAgIHJlcXVpcmU6ICdebmdNb2RlbCcsXHJcbiAgICBzY29wZSA6IHtcclxuICAgICAgbGFiZWw6IFwiQFwiLFxyXG4gICAgICBuZ01vZGVsOiBcIj1cIixcclxuICAgICAgY29sb3I6IFwiQFwiLFxyXG4gICAgICB0eXBlOiBcIkBcIixcclxuICAgICAgdmFsaWRhdGU6IFwiPVwiLFxyXG4gICAgICBpc1ZhbGlkOiBcIj1cIlxyXG4gICAgfSxcclxuICAgIHJlcGxhY2U6IHRydWUsXHJcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJpbnB1dEZvcm1cIj5cclxuICAgICAgICAgICAgICAgICA8bGFiZWw+e3sgbGFiZWwgfX08L2xhYmVsPlxyXG4gICAgICAgICAgICAgICAgIDxpbnB1dCB0eXBlPVwie3sgdHlwZSB8fCAndGV4dCd9fVwiIG5nLW1vZGVsPVwibmdNb2RlbFwiPlxyXG4gICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJlcnJvcnNcIj48L2Rpdj5cclxuICAgICAgICAgICAgICAgPC9kaXY+YCxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQsICR0aW1lb3V0KSB7XHJcbiAgICAgIGxldCBpZCA9IGVlLm9uKCdmb3JtLXN1Ym1pdCcsIHZhbGlkYXRlKVxyXG4gICAgICAkc2NvcGUuJG9uKFwiJGRlc3Ryb3lcIiwgZnVuY3Rpb24oKSB7XHJcbiAgICAgICAgZWUub2ZmKGlkKVxyXG4gICAgICB9LmJpbmQodGhpcykpXHJcblxyXG4gICAgICBsZXQgZGVmYXVsdEJvcmRlciA9IFwiXCJcclxuXHJcbiAgICAgIGxldCBlbCA9ICRlbGVtZW50WzBdLmdldEVsZW1lbnRzQnlUYWdOYW1lKCdpbnB1dCcpWzBdLFxyXG4gICAgICAgICAgbGFiZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgnbGFiZWwnKVswXSxcclxuICAgICAgICAgIGVycm9yID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnZXJyb3JzJylbMF1cclxuXHJcblxyXG4gICAgICBmdW5jdGlvbiB2YWxpZGF0ZSgpIHtcclxuICAgICAgICBpZigkc2NvcGUudmFsaWRhdGUpIHtcclxuICAgICAgICAgIGZ1bmN0aW9uIGhhbmRsZShlcnJvcikge1xyXG4gICAgICAgICAgICBpZih0eXBlb2YgJHNjb3BlLmlzVmFsaWQgIT09IFwidW5kZWZpbmVkXCIpIHtcclxuICAgICAgICAgICAgICBpZihlcnJvci5sZW5ndGgpXHJcbiAgICAgICAgICAgICAgICAkc2NvcGUuaXNWYWxpZCA9IGZhbHNlXHJcbiAgICAgICAgICAgICAgZWxzZVxyXG4gICAgICAgICAgICAgICAgJHNjb3BlLmlzVmFsaWQgPSB0cnVlXHJcblxyXG4gICAgICAgICAgICAgICRzY29wZS4kYXBwbHkoKVxyXG4gICAgICAgICAgICB9XHJcbiAgICAgICAgICB9XHJcblxyXG4gICAgICAgICAgbGV0IHJlc3AgPSAkc2NvcGUudmFsaWRhdGUoZWwudmFsdWUpXHJcblxyXG4gICAgICAgICAgaWYoIHR5cGVvZiByZXNwID09PSBcInN0cmluZ1wiKVxyXG4gICAgICAgICAgICBoYW5kbGUoIGVycm9yLmlubmVySFRNTCA9IHJlc3ApXHJcbiAgICAgICAgICBlbHNlXHJcbiAgICAgICAgICAgIHJlc3AudGhlbiggZnVuY3Rpb24oZGF0YSkge1xyXG4gICAgICAgICAgICAgICAgZXJyb3IuaW5uZXJIVE1MID0gZGF0YVxyXG4gICAgICAgICAgICAgICAgaGFuZGxlKGRhdGEpXHJcbiAgICAgICAgICAgICAgfSwgY29uc29sZS5lcnJvcilcclxuXHJcblxyXG4gICAgICAgIH1cclxuICAgICAgfVxyXG5cclxuICAgICAgZnVuY3Rpb24gb25CbHVyKGUpIHtcclxuICAgICAgICBpZiggISRzY29wZS5uZ01vZGVsLmxlbmd0aClcclxuICAgICAgICAgIGhpZGVBbmltYXRpb24oKVxyXG5cclxuICAgICAgICAgIHZhbGlkYXRlKClcclxuICAgICAgfVxyXG5cclxuICAgICAgZnVuY3Rpb24gb25Gb2N1cyhlKSB7XHJcbiAgICAgICAgaWYoISRzY29wZS5uZ01vZGVsLmxlbmd0aClcclxuICAgICAgICAgIGRpc3BsYXlBbmltYXRpb24oKVxyXG4gICAgICB9XHJcblxyXG4gICAgICBmdW5jdGlvbiBkaXNwbGF5QW5pbWF0aW9uKCkge1xyXG4gICAgICAgIGxhYmVsLnN0eWxlLmNvbG9yID0gQ09MT1JTWyRzY29wZS5jb2xvcl1cclxuICAgICAgICBpZighZGVmYXVsdEJvcmRlci5sZW5ndGgpIHtcclxuICAgICAgICAgIGRlZmF1bHRCb3JkZXIgPSB3aW5kb3cuZ2V0Q29tcHV0ZWRTdHlsZShsYWJlbC5wYXJlbnROb2RlKS5ib3JkZXJCb3R0b21cclxuICAgICAgICB9IGVsc2Uge1xyXG4gICAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBgMnB4IHNvbGlkICR7Q09MT1JTWyRzY29wZS5jb2xvcl19YFxyXG4gICAgICAgIH1cclxuXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LmFkZCgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIGhpZGVBbmltYXRpb24oKSB7XHJcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXHJcbiAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBkZWZhdWx0Qm9yZGVyXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LnJlbW92ZSgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgICR0aW1lb3V0KCAoKSA9PiB7XHJcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXHJcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcclxuICAgICAgICBlbHNlXHJcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcclxuICAgICAgfSwgMjUwKVxyXG5cclxuXHJcbiAgICAgIGVsLmFkZEV2ZW50TGlzdGVuZXIoJ2JsdXInLCBvbkJsdXIuYmluZCh0aGlzKSlcclxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignZm9jdXMnLCBvbkZvY3VzLmJpbmQoJHNjb3BlKSlcclxuICAgIH1cclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0LmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oKSB7XHJcbiAgcmV0dXJuIHtcclxuICAgIHJlc3RyaWN0OiBcIkVcIixcclxuICAgIHJlcXVpcmU6ICdebmdNb2RlbCcsXHJcbiAgICBzY29wZToge1xyXG4gICAgICBuZ01vZGVsOiBcIj1cIixcclxuICAgICAgc2hvdzogXCJAXCJcclxuICAgIH0sXHJcbiAgICB0ZW1wbGF0ZTogYDxkaXYgY2xhc3M9XCJzZWxlY3RCb3hcIj5cclxuICAgICAgICAgICAgICAgIDxkaXYgY2xhc3M9XCJkZWZhdWx0VmFsdWVcIiBuZy1oaWRlPVwic2hvd1wiPlxyXG4gICAgICAgICAgICAgICAgICA8cD57eyBuZ01vZGVsIH19PC9wPlxyXG4gICAgICAgICAgICAgICAgPC9kaXY+XHJcbiAgICAgICAgICAgICAgICA8ZGl2IGNsYXNzPVwibGlzdE9mVmFsdWVzXCIgbmctc2hvdz1cInNob3dcIj5cclxuICAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cImxpc3RJdGVtXCIgbmctcmVwZWF0PVwiaXRlbSBpbiBpdGVtc1wiIHZhbHVlPVwie3tpdGVtfX1cIj57eyBpdGVtIH19PC9kaXY+XHJcbiAgICAgICAgICAgICAgICA8L2Rpdj5cclxuICAgICAgICAgICAgICA8L2Rpdj5gLFxyXG4gICAgcmVwbGFjZTogdHJ1ZSxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQsICR0aW1lb3V0KSB7XHJcbiAgICAgIGxldCBkZWZhdWx0VmFsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnZGVmYXVsdFZhbHVlJylbMF0sXHJcbiAgICAgICAgICBsaXN0T2ZWYWx1ZXMgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5Q2xhc3NOYW1lKCdsaXN0T2ZWYWx1ZXMnKVswXVxyXG5cclxuICAgICAgJHRpbWVvdXQoZnVuY3Rpb24oKSB7XHJcbiAgICAgICAgZGVmYXVsdFZhbC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGZ1bmN0aW9uKGUpIHtcclxuICAgICAgICAgIHRoaXMuc2hvdyA9IHRydWVcclxuXHJcbiAgICAgICAgICBkb2N1bWVudC5hZGRFdmVudExpc3RlbmVyKCdjbGljaycsIGhhbmRsZXIpXHJcbiAgICAgICAgfS5iaW5kKCRzY29wZSkpXHJcblxyXG4gICAgICAgIGZ1bmN0aW9uIGhhbmRsZXIoZSkge1xyXG4gICAgICAgICAgaWYoICEoZS50YXJnZXQgPT0gbGlzdE9mVmFsdWVzIHx8XHJcbiAgICAgICAgICAgICAgICBlLnRhcmdldC5wYXJlbnROb2RlID09IGxpc3RPZlZhbHVlcyB8fFxyXG4gICAgICAgICAgICAgICAgZS50YXJnZXQgPT0gZGVmYXVsdFZhbCB8fFxyXG4gICAgICAgICAgICAgICAgZS50YXJnZXQucGFyZW50Tm9kZSA9PSBkZWZhdWx0VmFsKSApIHtcclxuICAgICAgICAgICAgJHNjb3BlLnNob3cgPSBmYWxzZVxyXG4gICAgICAgICAgICAkc2NvcGUuJGFwcGx5KClcclxuICAgICAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcignY2xpY2snLCBoYW5kbGVyKVxyXG4gICAgICAgICAgfVxyXG4gICAgICAgIH1cclxuXHJcbiAgICAgICAgZm9yKGxldCB0PTA7dDxsaXN0T2ZWYWx1ZXMuY2hpbGRyZW4ubGVuZ3RoOyB0KyspIHtcclxuXHJcbiAgICAgICAgICBsaXN0T2ZWYWx1ZXMuY2hpbGRyZW5bdF0uYWRkRXZlbnRMaXN0ZW5lcignY2xpY2snLCBmdW5jdGlvbihlKSB7XHJcbiAgICAgICAgICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcclxuICAgICAgICAgICAgdGhpcy5zaG93ID0gZmFsc2VcclxuICAgICAgICAgICAgdGhpcy5uZ01vZGVsID0gZS50YXJnZXQuaW5uZXJIVE1MXHJcbiAgICAgICAgICB9LmJpbmQoJHNjb3BlKSlcclxuICAgICAgICB9XHJcbiAgICAgIH0uYmluZCh0aGlzKSwgMTAwMClcclxuICAgIH0sXHJcbiAgICBsaW5rOiBmdW5jdGlvbihzY29wZSwgZWxlbWVudCwgYXR0cnMpIHtcclxuICAgICAgc2NvcGUuaXRlbXMgPSBKU09OLnBhcnNlKGF0dHJzLml0ZW1zKVxyXG4gICAgfVxyXG4gIH1cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9kaXJlY3RpdmVzL3NlbGVjdEJveC5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgTUFYX1NZTUJPTFMgPSAxMDAwXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIHJldHVybiB7XHJcbiAgICByZXN0cmljdDogXCJFXCIsXHJcbiAgICByZXF1aXJlOiAnXm5nTW9kZWwnLFxyXG4gICAgc2NvcGUgOiB7XHJcbiAgICAgIGxhYmVsOiBcIkBcIixcclxuICAgICAgbmdNb2RlbDogXCI9XCJcclxuICAgIH0sXHJcbiAgICByZXBsYWNlOiB0cnVlLFxyXG4gICAgdGVtcGxhdGU6IGA8ZGl2IGNsYXNzPVwiaW5wdXRGb3JtXCI+XHJcbiAgICAgICAgICAgICAgICAgPGxhYmVsPnt7IGxhYmVsIH19PC9sYWJlbD5cclxuICAgICAgICAgICAgICAgICA8dGV4dGFyZWEgbmctbW9kZWw9XCJuZ01vZGVsXCIgbWF4bGVuZ3RoPSR7TUFYX1NZTUJPTFN9PjwvdGV4dGFyZWE+XHJcbiAgICAgICAgICAgICAgICAgPGRpdiBjbGFzcz1cInN5bWJvbHNcIj48L2Rpdj5cclxuICAgICAgICAgICAgICAgPC9kaXY+YCxcclxuICAgIGNvbnRyb2xsZXI6IGZ1bmN0aW9uKCRzY29wZSwgJGVsZW1lbnQsICR0aW1lb3V0KSB7XHJcbiAgICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgY291bnQpXHJcblxyXG4gICAgICAkc2NvcGUuJG9uKFwiJGRlc3Ryb3lcIiwgZnVuY3Rpb24oKSB7XHJcbiAgICAgICAgZG9jdW1lbnQucmVtb3ZlRXZlbnRMaXN0ZW5lcigna2V5dXAnLCBjb3VudClcclxuICAgICAgfS5iaW5kKHRoaXMpKVxyXG5cclxuICAgICAgbGV0IGRlZmF1bHRCb3JkZXIgPSBcIlwiXHJcblxyXG4gICAgICBsZXQgZWwgPSAkZWxlbWVudFswXS5nZXRFbGVtZW50c0J5VGFnTmFtZSgndGV4dGFyZWEnKVswXSxcclxuICAgICAgICAgIGxhYmVsID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeVRhZ05hbWUoJ2xhYmVsJylbMF0sXHJcbiAgICAgICAgICBzeW1ib2xzID0gJGVsZW1lbnRbMF0uZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnc3ltYm9scycpWzBdXHJcblxyXG5cclxuICAgICAgZnVuY3Rpb24gY291bnQoKSB7XHJcbiAgICAgICAgc3ltYm9scy5pbm5lckhUTUwgPSBgJHskc2NvcGUubmdNb2RlbC5sZW5ndGh9LyR7TUFYX1NZTUJPTFN9YFxyXG4gICAgICB9XHJcblxyXG4gICAgICBmdW5jdGlvbiBvbkJsdXIoZSkge1xyXG4gICAgICAgIGlmKCAhJHNjb3BlLm5nTW9kZWwubGVuZ3RoKVxyXG4gICAgICAgICAgaGlkZUFuaW1hdGlvbigpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIG9uRm9jdXMoZSkge1xyXG4gICAgICAgIGlmKCEkc2NvcGUubmdNb2RlbC5sZW5ndGgpXHJcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcclxuICAgICAgfVxyXG5cclxuICAgICAgZnVuY3Rpb24gZGlzcGxheUFuaW1hdGlvbigpIHtcclxuICAgICAgICBsYWJlbC5zdHlsZS5jb2xvciA9IFwiIzE4NzVEMFwiXHJcbiAgICAgICAgaWYoIWRlZmF1bHRCb3JkZXIubGVuZ3RoKSB7XHJcbiAgICAgICAgICBkZWZhdWx0Qm9yZGVyID0gd2luZG93LmdldENvbXB1dGVkU3R5bGUobGFiZWwucGFyZW50Tm9kZSkuYm9yZGVyQm90dG9tXHJcbiAgICAgICAgfSBlbHNlIHtcclxuICAgICAgICAgIGxhYmVsLnBhcmVudE5vZGUuc3R5bGUuYm9yZGVyQm90dG9tID0gYDJweCBzb2xpZCAjMTg3NUQwYFxyXG4gICAgICAgIH1cclxuXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LmFkZCgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIGZ1bmN0aW9uIGhpZGVBbmltYXRpb24oKSB7XHJcbiAgICAgICAgbGFiZWwuc3R5bGUuY29sb3IgPSBcIlwiXHJcbiAgICAgICAgbGFiZWwucGFyZW50Tm9kZS5zdHlsZS5ib3JkZXJCb3R0b20gPSBkZWZhdWx0Qm9yZGVyXHJcbiAgICAgICAgbGFiZWwuY2xhc3NMaXN0LnJlbW92ZSgndGV4dE91dCcpXHJcbiAgICAgIH1cclxuXHJcbiAgICAgICR0aW1lb3V0KCAoKSA9PiB7XHJcbiAgICAgICAgaWYoICRzY29wZS5uZ01vZGVsICYmICRzY29wZS5uZ01vZGVsLmxlbmd0aCApXHJcbiAgICAgICAgICBkaXNwbGF5QW5pbWF0aW9uKClcclxuICAgICAgICBlbHNlXHJcbiAgICAgICAgICBoaWRlQW5pbWF0aW9uKClcclxuICAgICAgfSwgMjUwKVxyXG5cclxuXHJcbiAgICAgIGVsLmFkZEV2ZW50TGlzdGVuZXIoJ2JsdXInLCBvbkJsdXIuYmluZCh0aGlzKSlcclxuICAgICAgZWwuYWRkRXZlbnRMaXN0ZW5lcignZm9jdXMnLCBvbkZvY3VzLmJpbmQoJHNjb3BlKSlcclxuXHJcbiAgICAgIGNvdW50KClcclxuICAgIH1cclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9tYXRlcmlhbHMvZGlyZWN0aXZlcy90ZXh0QXJlYS5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubGV0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cclxuXHJcbmxldCBwcml2YXRlU2NvcGUgPSB7fVxyXG5cclxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGZ1bmN0aW9uKCkge1xyXG4gIHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzID0ge31cclxuICBwcml2YXRlU2NvcGUuaGFuZGxlcklkID0gMFxyXG5cclxuICBwcml2YXRlU2NvcGUuZ2V0SGFuZGxlcklkID0gZnVuY3Rpb24oKSB7XHJcbiAgICByZXR1cm4gcHJpdmF0ZVNjb3BlLmhhbmRsZXJJZCsrXHJcbiAgfVxyXG5cclxuICBwcml2YXRlU2NvcGUucmVnaXN0ZXJIYW5kbGVyID0gZnVuY3Rpb24obmFtZSwgaGFuZGxlcikge1xyXG4gICAgaWYoIXByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW25hbWVdKVxyXG4gICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXSA9IFtdXHJcblxyXG4gICAgbGV0IGlkID0gcHJpdmF0ZVNjb3BlLmdldEhhbmRsZXJJZCgpXHJcbiAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tuYW1lXS5wdXNoKHtcclxuICAgICAgaWQgOiBpZCxcclxuICAgICAgaGFuZGxlciA6IGhhbmRsZXJcclxuICAgIH0pXHJcblxyXG4gICAgcmV0dXJuIGlkXHJcbiAgfVxyXG5cclxuICBwcml2YXRlU2NvcGUuaGFuZGxlID0gZnVuY3Rpb24oZXZlbnROYW1lLCBkYXRhKSB7XHJcbiAgICBpZihwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tldmVudE5hbWVdKVxyXG4gICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1tldmVudE5hbWVdLmZvckVhY2goaCA9PiBoLmhhbmRsZXIoZGF0YSkpXHJcbiAgfVxyXG5cclxuICBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlciA9IGZ1bmN0aW9uKGlkKSB7XHJcbiAgICBmb3IobGV0IGtleSBpbiBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVycykge1xyXG4gICAgICBmb3IobGV0IHQgPTA7IHQ8IHByaXZhdGVTY29wZS5ldmVudEhhbmRsZXJzW2tleV0ubGVuZ3RoOyB0KyspIHtcclxuICAgICAgICBpZihwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldW3RdLmlkID09IGlkKSB7XHJcbiAgICAgICAgICBwcml2YXRlU2NvcGUuZXZlbnRIYW5kbGVyc1trZXldID0gcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNba2V5XS5maWx0ZXIoZWwgPT4gZWwuaWQgIT09IGlkKVxyXG4gICAgICAgICAgcmV0dXJuIHRydWVcclxuICAgICAgICB9XHJcbiAgICAgIH1cclxuICAgIH1cclxuXHJcbiAgICByZXR1cm4gZmFsc2VcclxuICB9XHJcblxyXG4gIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyQnlOYW1lID0gZnVuY3Rpb24obmFtZSkge1xyXG4gICAgcHJpdmF0ZVNjb3BlLmV2ZW50SGFuZGxlcnNbbmFtZV0gPSBbXVxyXG4gIH1cclxufVxyXG5cclxuXHJcbm1vZHVsZS5leHBvcnRzLm9uID0gZnVuY3Rpb24oZXZlbnROYW1lLCBoYW5kbGVyKSB7XHJcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZWdpc3RlckhhbmRsZXIoZXZlbnROYW1lLCBoYW5kbGVyKVxyXG59XHJcblxyXG4vKiBSZW1vdmVzIGhhbmRsZXIgYnkgaWQqL1xyXG5tb2R1bGUuZXhwb3J0cy5vZmYgPSBmdW5jdGlvbihpZCkge1xyXG4gIHJldHVybiBwcml2YXRlU2NvcGUucmVtb3ZlSGFuZGxlcihpZClcclxufVxyXG5cclxuLyogUmVtb3ZlcyBhbGwgaGFuZGxlcnMgYnkgZXZlbnQgbmFtZSAqL1xyXG5tb2R1bGUuZXhwb3J0cy5yZW1vdmUgPSBmdW5jdGlvbihuYW1lKSB7XHJcbiAgcmV0dXJuIHByaXZhdGVTY29wZS5yZW1vdmVIYW5kbGVyQnlOYW1lKG5hbWUpXHJcbn1cclxuLypcclxuICB7XHJcbiAgICBcIm5hbWVcIiA6IFwiZm9ybS1zdWJtaXRcIixcclxuICAgIFwiZGF0YVwiIDogXCJ3aGF0ZXZlclwiIDw9PSBvcHRpb25hbFxyXG4gIH1cclxuKi9cclxubW9kdWxlLmV4cG9ydHMuZW1pdCA9IGZ1bmN0aW9uKGV2ZW50KSB7XHJcbiAgbGV0IG5hbWUgPSBldmVudC5uYW1lIHx8ICgoKSA9PiB7IHRocm93IG5ldyBFcnJvcihcIk5vIGV2ZW50IG5hbWVcIikgfSkoKVxyXG4gIGxldCBkYXRhID0gZXZlbnQuZGF0YSB8fCBudWxsXHJcblxyXG4gIHByaXZhdGVTY29wZS5oYW5kbGUobmFtZSwgZGF0YSlcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL21hdGVyaWFscy9ldmVudHMuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0ge1xyXG4gIFwiL1wiIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvaW5kZXguaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvaW5kZXgnKSxcclxuICAgIGNvbnRyb2xsZXJBczogXCJpbmRleFwiXHJcbiAgfSxcclxuICAnLzQwMycgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9lcnJvcjQwMy5odG1sXCJcclxuICB9LFxyXG4gICcvNDA0JyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOlwidGVtcGxhdGVzL2Vycm9yNDA0Lmh0bWxcIlxyXG4gIH0sXHJcbiAgJy81MDAnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvZXJyb3I1MDAuaHRtbFwiXHJcbiAgfSxcclxuICAnL2J1bGxldGluRGV0YWlscycgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDpcInRlbXBsYXRlcy9idWxsZXRpbkRldGFpbHMuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYnVsbGV0aW5EZXRhaWxzJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwiYmRldGFpbGVkXCJcclxuICB9LFxyXG4gICcvYnVsbGV0aW5BZGQnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9idWxsZXRpbkFkZC5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwiYmFkZFwiXHJcbiAgfSxcclxuICAnL2xvZ2luJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9sb2dpbi5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9sb2dpbicpLFxyXG4gICAgY29udHJvbGxlckFzOiBcImxvZ2luXCJcclxuICB9LFxyXG4gICcvcmVnaXN0ZXInIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6IFwidGVtcGxhdGVzL3JlZ2lzdGVyLmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL3JlZ2lzdGVyJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwicmVnaXN0ZXJcIlxyXG4gIH0sXHJcbiAgJy9lZGl0UHJvZmlsZScgOiB7XHJcbiAgICB0ZW1wbGF0ZVVybDogXCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9lZGl0LXByb2ZpbGUuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9lZGl0UHJvZmlsZScpLFxyXG4gICAgY29udHJvbGxlckFzOiBcInByb2ZpbGVcIlxyXG4gIH0sXHJcbiAgJy9wcm9maWxlJyA6IHtcclxuICAgIHRlbXBsYXRlVXJsOiBcInRlbXBsYXRlcy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUuaHRtbFwiLFxyXG4gICAgY29udHJvbGxlcjogcmVxdWlyZSgnLi4vY29udHJvbGxlcnMvYXV0aGVudGljYXRlZC9wcm9maWxlJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwicHJvZmlsZVwiXHJcbiAgfSxcclxuICAnL2Zhdm91cml0ZXMnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9mYXZvdXJpdGVzLmh0bWxcIixcclxuICAgIGNvbnRyb2xsZXI6IHJlcXVpcmUoJy4uL2NvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcycpLFxyXG4gICAgY29udHJvbGxlckFzOiBcImZhdm91cml0ZVwiXHJcbiAgfSxcclxuICAnL3NlYXJjaFJlc3VsdHMnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvc2VhcmNoUmVzdWx0cy5odG1sXCIsXHJcbiAgICBjb250cm9sbGVyOiByZXF1aXJlKCcuLi9jb250cm9sbGVycy9zZWFyY2hSZXN1bHRzJyksXHJcbiAgICBjb250cm9sbGVyQXM6IFwic2VhcmNoUmVzdWx0c1wiXHJcbiAgfSxcclxuICAnL21haWwnIDoge1xyXG4gICAgdGVtcGxhdGVVcmw6XCJ0ZW1wbGF0ZXMvYXV0aGVudGljYXRlZC9tYWlsLmh0bWxcIixcclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogbW9kdWxlcy9yb3V0ZXIuanNcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xyXG4gIFxyXG5cclxufVxyXG5cclxuXHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2luZGV4LmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIFxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2J1bGxldGluRGV0YWlscy5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigkc2NvcGUsICR0aW1lb3V0KSB7XHJcblxyXG4gIHRoaXMuaW5pdCA9IGZ1bmN0aW9uKCkge1xyXG4gICAgLy8gaWYoIWRiLnVzZXIpXHJcbiAgICAvLyAgIHJldHVybiAkc2NvcGUucmVkaXJlY3RUb1VybCgnLzQwMycpXHJcblxyXG4gICAgdGhpcy50eXBlcyA9IFtcclxuICAgICAgXCLQkNGA0LXQvdC00LBcIixcclxuICAgICAgXCLQn9GA0L7QtNCw0LbQsFwiLFxyXG4gICAgICBcItCe0YLQtNCw0Lwg0LTQsNGA0L7QvFwiLFxyXG4gICAgICBcItCe0LHQvNC10L1cIlxyXG4gICAgXVxyXG4gICAgdGhpcy5maWxlcyA9IFtdXHJcbiAgfVxyXG5cclxuICB0aGlzLnNob3dGaWxlVXBsb2FkID0gZnVuY3Rpb24oKSB7XHJcbiAgICAkdGltZW91dCggZnVuY3Rpb24oKSB7XHJcbiAgICAgIGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCd1cGxvYWRGaWxlJykuY2xpY2soKVxyXG4gICAgfSwgMTAwKVxyXG4gIH1cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2J1bGxldGluQWRkLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSkge1xyXG5cclxuICB0aGlzLmluaXQgPSAoKSA9PiB7XHJcbiAgICB0aGlzLmRiID0gJHNjb3BlLiRwYXJlbnQuZGJcclxuXHJcbiAgICB0aGlzLmVtYWlsID0gXCJcIlxyXG4gICAgdGhpcy5wYXNzd29yZCA9IFwiXCJcclxuXHJcbiAgICB0aGlzLmVtYWlsVmFsaWQgPSB0cnVlXHJcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSB0cnVlXHJcblxyXG4gICAgdGhpcy5sb2dpbkVycm9yID0gXCJcIlxyXG5cclxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcclxuICAgICAgaWYoZS53aGljaCA9PSAxMykgdGhpcy5zZW5kLmNhbGwodGhpcylcclxuICAgIH0uYmluZCh0aGlzKVxyXG5cclxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5kZWxldGVMaXN0bmVycyA9ICgpID0+IHtcclxuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xyXG4gICAgZWUuZW1pdCh7IG5hbWUgOiBcImZvcm0tc3VibWl0XCIgfSlcclxuICAgIC8qXHJcbiAgICAgIC0gR2V0IGRhdGFcclxuICAgICAgLSBWYWxpZGF0ZVxyXG4gICAgICAtIFNob3cgZXJyb3JzXHJcbiAgICAgIC0gb3JcclxuICAgICAgLSBHb3RvIGJkIGFuZCBzZW5kIGRhdGFcclxuICAgICovXHJcbiAgICAvLyB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuXHJcbiAgICBpZiggdGhpcy5lbWFpbFZhbGlkICYmIHRoaXMucGFzc3dvcmRWYWxpZCApIHtcclxuICAgICAgdGhpcy5kYi5sb2dpbih7XHJcbiAgICAgICAgXCJlbWFpbFwiIDogdGhpcy5lbWFpbCxcclxuICAgICAgICBcInBhc3N3b3JkXCI6IHRoaXMucGFzc3dvcmRcclxuICAgICAgfSwgKGVyciwgZGF0YSkgPT4ge1xyXG4gICAgICAgIGlmKGVycilcclxuICAgICAgICAgICRzY29wZS4kcGFyZW50LmRpc3BsYXlFcnJvcihcItCe0YjQuNCx0LrQsCDQsNCy0YLQvtGA0LjQt9Cw0YbQuNC4LCDQv9GA0L7QstC10YDRjNGC0LUg0LLQsNGI0Lgg0LTQsNC90L3Ri9C1XCIpXHJcbiAgICAgICAgZWxzZSB7XHJcbiAgICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuICAgICAgICAgIHRoaXMuZGIuc2F2ZVVzZXJEYXRhKGRhdGEpXHJcbiAgICAgICAgICAkc2NvcGUucmVkaXJlY3RUb1VybCgnL3Byb2ZpbGUnKVxyXG4gICAgICAgIH1cclxuICAgICAgfSlcclxuICAgIH1cclxuICB9XHJcblxyXG4gIHRoaXMuZW1haWxJc1ZhbGlkID0gZW1haWwgPT4ge1xyXG4gICAgbGV0IGVycm9yID0gXCJcIlxyXG4gICAgaWYoIWVtYWlsLmxlbmd0aCkgIGVycm9yICs9IFwi0J7QsdGP0LfQsNGC0LXQu9GM0L3QvtC1INC/0L7Qu9C1LiBcIlxyXG4gICAgaWYoIS9eKChbXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKFxcLltePD4oKVxcW1xcXVxcXFwuLDs6XFxzQFwiXSspKil8KFwiLitcIikpQCgoXFxbWzAtOV17MSwzfVxcLlswLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfV0pfCgoW2EtekEtWlxcLTAtOV0rXFwuKStbYS16QS1aXXsyLH0pKSQvLnRlc3QoZW1haWwpKVxyXG4gICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXHJcbiAgICByZXR1cm4gZXJyb3JcclxuICB9XHJcblxyXG4gIHRoaXMucGFzc3dvcmRJc1ZhbGlkID0gcHdkID0+IHtcclxuICAgIGxldCBlcnJvciA9IFwiXCJcclxuICAgIGlmKCFwd2QubGVuZ3RoKSBlcnJvciArPSBcItCe0LHRj9C30LDRgtC10LvRjNC90L7QtSDQv9C+0LvQtS4gXCJcclxuICAgIGlmKCBwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxyXG4gICAgcmV0dXJuIGVycm9yXHJcbiAgfVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2xvZ2luLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IGZ1bmN0aW9uKCRzY29wZSwgJHEpIHtcclxuXHJcbiAgdGhpcy5pbml0ID0gKCkgPT4ge1xyXG4gICAgdGhpcy5kYiA9ICRzY29wZS4kcGFyZW50LmRiXHJcblxyXG4gICAgdGhpcy5lbWFpbCA9IFwiXCJcclxuICAgIHRoaXMucGFzc3dvcmQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkMiA9IFwiXCJcclxuXHJcbiAgICB0aGlzLmVtYWlsVmFsaWQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkVmFsaWQgPSBcIlwiXHJcbiAgICB0aGlzLnBhc3N3b3JkMlZhbGlkID0gXCJcIlxyXG5cclxuICAgIHRoaXMuaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcclxuICAgICAgaWYoZS53aGljaCA9PSAxMykgdGhpcy5zZW5kLmNhbGwodGhpcylcclxuICAgIH0uYmluZCh0aGlzKVxyXG5cclxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5kZWxldGVMaXN0bmVycyA9ICgpID0+IHtcclxuICAgIGRvY3VtZW50LnJlbW92ZUV2ZW50TGlzdGVuZXIoJ2tleXVwJywgdGhpcy5oYW5kbGVyKVxyXG4gIH1cclxuXHJcbiAgdGhpcy5zZW5kID0gKCkgPT4ge1xyXG4gICAgLypcclxuICAgICAgLSBHZXQgZGF0YVxyXG4gICAgICAtIFZhbGlkYXRlXHJcbiAgICAgIC0gU2hvdyBlcnJvcnNcclxuICAgICAgLSBvclxyXG4gICAgICAtIEdvdG8gYmQgYW5kIHNlbmQgZGF0YVxyXG4gICAgKi9cclxuICAgIGlmKCB0aGlzLmVtYWlsVmFsaWQgJiYgdGhpcy5wYXNzd29yZFZhbGlkICYmIHRoaXMucGFzc3dvcmQyVmFsaWQgKSB7XHJcbiAgICAgIHRoaXMuZGIubG9naW4oe1xyXG4gICAgICAgIFwiZW1haWxcIiA6IHRoaXMuZW1haWwsXHJcbiAgICAgICAgXCJwYXNzd29yZFwiOiB0aGlzLnBhc3N3b3JkXHJcbiAgICAgIH0sIChlcnIsIGRhdGEpID0+IHtcclxuICAgICAgICB0aGlzLmRlbGV0ZUxpc3RuZXJzKClcclxuICAgICAgICBpZihlcnIpXHJcbiAgICAgICAgICAkc2NvcGUuJHBhcmVudC5yZWRpcmVjdFRvVXJsKCcvNTAwJylcclxuICAgICAgICBlbHNlIHtcclxuICAgICAgICAgIC8qIFNhdmUgZGF0YSB0byBkYiAqL1xyXG4gICAgICAgICAgJHNjb3BlLnJlZGlyZWN0VG9VcmwoJy9wcm9maWxlJylcclxuICAgICAgICAgIGNvbnNvbGUubG9nKGRhdGEpXHJcbiAgICAgICAgfVxyXG4gICAgICB9KVxyXG4gICAgfVxyXG4gIH1cclxuXHJcbiAgdGhpcy5lbWFpbElzVmFsaWQgPSBmdW5jdGlvbihlbWFpbCkge1xyXG4gICAgcmV0dXJuICRxKCBmdW5jdGlvbihyZXNvbHZlLCByZWplY3QpIHtcclxuICAgICAgbGV0IGVycm9yID0gXCJcIlxyXG4gICAgICBpZighZW1haWwubGVuZ3RoKSAgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXHJcbiAgICAgIGlmKCEvXigoW148PigpXFxbXFxdXFxcXC4sOzpcXHNAXCJdKyhcXC5bXjw+KClcXFtcXF1cXFxcLiw7Olxcc0BcIl0rKSopfChcIi4rXCIpKUAoKFxcW1swLTldezEsM31cXC5bMC05XXsxLDN9XFwuWzAtOV17MSwzfVxcLlswLTldezEsM31dKXwoKFthLXpBLVpcXC0wLTldK1xcLikrW2EtekEtWl17Mix9KSkkLy50ZXN0KGVtYWlsKSlcclxuICAgICAgICBlcnJvciArPSBcItCd0LXQstC10YDQvdGL0Lkg0YTQvtGA0LzQsNGCINC/0L7Rh9GC0YsuIFwiXHJcblxyXG4gICAgICB3aW5kb3cuZGIuY2hlY2tFbWFpbChlbWFpbCwgZnVuY3Rpb24oZXJyLCBkYXRhKSB7XHJcbiAgICAgICAgaWYoZXJyKSByZWplY3QoZXJyKVxyXG4gICAgICAgIGVsc2Uge1xyXG4gICAgICAgICAgY29uc29sZS5sb2coZGF0YSlcclxuICAgICAgICAgIGlmKGRhdGEgIT09IFwiZmFsc2VcIilcclxuICAgICAgICAgICAgZXJyb3IgKz0gXCLQotCw0LrQsNGPINC/0L7Rh9GC0LAg0YPQttC1INC40YHQv9C+0LvRjNC30YPQtdGC0YHRjy4gXCJcclxuICAgICAgICAgIHJlc29sdmUoZXJyb3IpXHJcbiAgICAgICAgfVxyXG4gICAgICB9LmJpbmQodGhpcykpXHJcbiAgICB9LmJpbmQodGhpcykpXHJcbiAgfVxyXG5cclxuICB0aGlzLnBhc3N3b3JkSXNWYWxpZCA9IHB3ZCA9PiB7XHJcbiAgICBsZXQgZXJyb3IgPSBcIlwiXHJcbiAgICBpZighcHdkLmxlbmd0aCkgZXJyb3IgKz0gXCLQntCx0Y/Qt9Cw0YLQtdC70YzQvdC+0LUg0L/QvtC70LUuIFwiXHJcbiAgICBpZihwd2QubGVuZ3RoIDwgNikgZXJyb3IgKz0gXCLQn9Cw0YDQvtC70Ywg0LTQvtC70LbQtdC9INGB0L7QtNC10YDQttCw0YLRjCDQvdC1INC80LXQvdC10LUgNiDRgdC40LzQstC+0LvQvtCyLiBcIlxyXG4gICAgcmV0dXJuIGVycm9yXHJcbiAgfVxyXG5cclxuICB0aGlzLnBhc3N3b3JkMklzVmFsaWQgPSBwd2QgPT4ge1xyXG4gICAgbGV0IGVycm9yID0gdGhpcy5wYXNzd29yZElzVmFsaWQocHdkKVxyXG4gICAgaWYodGhpcy5wYXNzd29yZCAhPT0gdGhpcy5wYXNzd29yZDIgKSBlcnJvciArPSBcItCf0LDRgNC+0LvQuCDQvdC1INGB0L7QstC/0LDQtNCw0Y7RglwiXHJcbiAgICByZXR1cm4gZXJyb3JcclxuICB9XHJcbn1cclxuXG5cblxuLyoqIFdFQlBBQ0sgRk9PVEVSICoqXG4gKiogY29udHJvbGxlcnMvcmVnaXN0ZXIuanNcbiAqKi8iLCJcInVzZSBzdHJpY3RcIjtcclxuXHJcbmNsYXNzIFByb2ZpbGVDb250YWN0IHtcclxuICAgIGNvbnN0cnVjdG9yKCkge1xyXG4gICAgICAgIHRoaXMuY29udGFjdEVtYWlscyA9IFsnJ107XHJcbiAgICAgICAgdGhpcy5jb250YWN0UGhvbmVzID0gWycnXTtcclxuICAgICAgICB0aGlzLnR5cGUgPVwiRU5UUkVQUkVORVVSXCJcclxuXHJcbiAgICAgICAgdGhpcy5wb3NpdGlvbiA9IFwiXCJcclxuICAgICAgICB0aGlzLmNvbXBhbnlOYW1lID0gXCJcIlxyXG4gICAgICAgIHRoaXMuc2t5cGVVc2VyTmFtZSA9IFwiXCJcclxuICAgICAgICB0aGlzLmxpbmtUb1dlYlNpdGUgPSBcIlwiXHJcbiAgICB9XHJcbn1cclxuXHJcbmNsYXNzIHByb2ZpbGVDdHJsIHtcclxuICAgIGNvbnN0cnVjdG9yKCRzY29wZSl7XHJcbi8vICAgICAgICBpZighJHNjb3BlLiRwYXJlbnQuZGIudXNlcilcclxuLy8gICAgICAgICAgJHNjb3BlLiRwYXJlbnQucmVkaXJlY3RUb1VybCgnLzQwMycsIHRydWUpXHJcbi8vICAgICAgICBlbHNlXHJcblx0XHRcdHRoaXMuY29udGFjdCA9IG5ldyBQcm9maWxlQ29udGFjdCgpO1xyXG4gICAgICB0aGlzLmNvbnRhY3RUeXBlcyA9IFtcclxuICAgICAgICBcIkxFR0FMX0VOVElUWVwiLFxyXG4gICAgICAgIFwiRU5UUkVQUkVORVVSXCJcclxuICAgICAgXVxyXG5cclxuICAgICAgdGhpcy5lbWFpbCA9IFwiXCJcclxuICAgICAgdGhpcy5maW8gPSBcIlwiXHJcbiAgICAgIHRoaXMubWFpblBob25lTnVtYmVyID0gXCJcIlxyXG4gICAgfVxyXG4gICAgdXBkYXRlUHJvZmlsZSgpe1xyXG5cclxuICAgIH1cclxuICAgIGFkZENvbnRhY3RzKCRldmVudCwgdHlwZSl7XHJcbiAgICAgICAgdmFyIGFycjtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG5cclxuICAgICAgICBpZihhcnIubGVuZ3RoIDwgNSAmJiBhcnJbYXJyLmxlbmd0aCAtIDFdLnRyaW0oKSkgYXJyLnB1c2goJycpO1xyXG4gICAgfVxyXG5cclxuICAgIGRlbGV0ZUNvbnRhY3RzKCRldmVudCwgJGluZGV4LCB0eXBlKXtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscy5zcGxpY2UoJGluZGV4LCAxKTtcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG4gICAgfVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHByb2ZpbGVDdHJsO1xyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL2VkaXRQcm9maWxlLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCI7XHJcblxyXG5jbGFzcyBQcm9maWxlQ29udGFjdHtcclxuICAgIGNvbnN0cnVjdG9yKCkge1xyXG4gICAgICAgIHRoaXMuY29udGFjdEVtYWlscyA9IFsnJ107XHJcbiAgICAgICAgdGhpcy5jb250YWN0UGhvbmVzID0gWycnXTtcclxuICAgIH1cclxufVxyXG5cclxuY2xhc3MgcHJvZmlsZUN0cmwge1xyXG4gICAgY29uc3RydWN0b3IoJHNjb3BlKXtcclxuICAgICAgICAvLyBpZighJHNjb3BlLiRwYXJlbnQuZGIudXNlcilcclxuICAgICAgICAvLyAgICRzY29wZS4kcGFyZW50LnJlZGlyZWN0VG9VcmwoJy80MDMnLCB0cnVlKVxyXG4gICAgICAgIC8vIGVsc2VcclxuICAgICAgICAgIHRoaXMuY29udGFjdCA9IG5ldyBQcm9maWxlQ29udGFjdCgpO1xyXG4gICAgICAgIFxyXG4gICAgfVxyXG4gICAgdXBkYXRlUHJvZmlsZSgpe1xyXG5cclxuICAgIH1cclxuICAgIGFkZENvbnRhY3RzKCRldmVudCwgdHlwZSl7XHJcbiAgICAgICAgdmFyIGFycjtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSBhcnIgPSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscztcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIGFyciA9IHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG5cclxuICAgICAgICBpZihhcnIubGVuZ3RoIDwgNSAmJiBhcnJbYXJyLmxlbmd0aCAtIDFdLnRyaW0oKSkgYXJyLnB1c2goJycpO1xyXG4gICAgfVxyXG5cclxuICAgIGRlbGV0ZUNvbnRhY3RzKCRldmVudCwgJGluZGV4LCB0eXBlKXtcclxuICAgICAgICBpZih0eXBlID09PSAnZW1haWwnKSB0aGlzLmNvbnRhY3QuY29udGFjdEVtYWlscy5zcGxpY2UoJGluZGV4LCAxKTtcclxuICAgICAgICBlbHNlIGlmKHR5cGUgPT09ICdwaG9uZScpIHRoaXMuY29udGFjdC5jb250YWN0UGhvbmVzLnNwbGljZSgkaW5kZXgsIDEpO1xyXG4gICAgICAgIGVsc2UgcmV0dXJuO1xyXG4gICAgfVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cyA9IHByb2ZpbGVDdHJsO1xyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9hdXRoZW50aWNhdGVkL3Byb2ZpbGUuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlKSB7XHJcblxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIGNvbnRyb2xsZXJzL2F1dGhlbnRpY2F0ZWQvZmF2b3VyaXRlcy5qc1xuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxubW9kdWxlLmV4cG9ydHMgPSBmdW5jdGlvbigpIHtcclxuICBcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9zZWFyY2hSZXN1bHRzLmpzXG4gKiovIiwiXCJ1c2Ugc3RyaWN0XCJcclxuXHJcbmNvbnN0IGN0eCA9IG1vZHVsZS5leHBvcnRzID0ge31cclxuLyog0JrQvtC90YLRgNC+0LvQu9C10YAg0LTQu9GPINGD0L/RgNCw0LLQu9C10L3QuNGPICDQvtGB0L3QvtCy0L3Ri9C8INGB0LrQtdC70LXRgtC+0Lwg0LTQvtC60YPQvNC10L3RgtCwICovXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJGh0dHAsICRzY29wZSwgJGxvY2F0aW9uLCAkdGltZW91dCwgJGNvb2tpZXMsICRjb29raWVTdG9yZSkge1xyXG4gIGNvbnNvbGUubG9nKCdNYWluIGNvbnRyb2xsZXIgbG9hZGVkJylcclxuICBjb25zb2xlLmxvZygkY29va2llcylcclxuICAvKiBTdGFuZGFsb25lIG1vZHVsZSBmb3IgYmQgKi9cclxuICAkc2NvcGUuZGIgPSByZXF1aXJlKCcuLi9tb2R1bGVzL2RiJylcclxuICAkc2NvcGUuZGIuaW5pdCgkaHR0cClcclxuICB3aW5kb3cuZGIgPSAkc2NvcGUuZGJcclxuXHJcbiAgLyogSW5pdGlhbGl6ZSBkYXRhICovXHJcbiAgdGhpcy5pbml0ID0gZnVuY3Rpb24oKSB7XHJcbiAgICAvKiB2YXJpYWJsZXMgZm9yIHRlc3RpbmcgKi9cclxuICAgIHRoaXMuaGVsbG89XCJoaVwiXHJcbiAgICB0aGlzLmJvb2xlYW4gPSB0cnVlXHJcbiAgICB0aGlzLmxpc3QgPSBbMSwyLDNdXHJcbiAgICAvKiBFbmQgdmFyaWFibGVzIGZvciB0ZXN0aW5nICovXHJcblxyXG4gICAgdGhpcy5sb2FkZXIgPSByZXF1aXJlKCcuLi9tb2R1bGVzL2xvYWRlcicpXHJcbiAgICB0aGlzLmxvYWRlcigkc2NvcGUsICR0aW1lb3V0KVxyXG5cclxuICAgIGNvbnNvbGUubG9nKFwiTWFpbiBjb250cm9sbGVyIGluaXRcIilcclxuXHJcbiAgICB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzID0gKHJlcXVpcmUoJy4uL2RhdGEvc29ydGluZycpKS5pdGVtc1xyXG4gICAgdGhpcy5jdXJyZW50Q2F0ZWdvcnkgPSBcIk5vbmVcIlxyXG4gICAgdGhpcy5zb3J0aW5nSWQgPSAwXHJcblxyXG4gICAgdGhpcy5zaG93RmlsdGVycyA9IGZhbHNlXHJcblxyXG4gICAgaWYodGhpcy5zb3J0aW5nQ2F0ZWdvcmllcy5sZW5ndGgpIHtcclxuICAgICAgbGV0IHRpdGxlID0gdGhpcy5zb3J0aW5nQ2F0ZWdvcmllc1t0aGlzLnNvcnRpbmdJZF0udGl0bGVcclxuICAgICAgbGV0IGFyciA9IHRpdGxlLnNwbGl0KFwiXCIpXHJcbiAgICAgIHRoaXMuYXJyb3cgPSBhcnIucG9wKClcclxuICAgICAgYXJyLnBvcCgpXHJcblxyXG4gICAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IGFyci5qb2luKFwiXCIpXHJcbiAgICB9XHJcbiAgICBlbHNlIGNvbnNvbGUuZXJyb3IobmV3IEVycm9yKFwiTm8gc29ydGluZyBvcHRpb25zIGZvdW5kXCIpKVxyXG5cclxuICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSBmYWxzZVxyXG4gICAgdGhpcy5zZXR0aW5nQ2F0ID0gdHJ1ZVxyXG4gIH1cclxuXHJcbiAgdGhpcy5kaXNwbGF5RmlsdGVycyA9IGZ1bmN0aW9uKCkge1xyXG4gICAgdGhpcy5zaG93RmlsdGVycyA9IHRydWVcclxuICAgIGNvbnN0IG5hdiA9IGRvY3VtZW50LmdldEVsZW1lbnRzQnlUYWdOYW1lKCduYXYnKVswXVxyXG5cclxuXHJcbiAgICBsZXQgaGFuZGxlciA9IGZ1bmN0aW9uKGUpIHtcclxuICAgICAgbGV0IHggPSBlLmNsaWVudFgsXHJcbiAgICAgICAgICB5ID0gZS5jbGllbnRZLFxyXG4gICAgICAgICAgcmVjdCA9IG5hdi5nZXRCb3VuZGluZ0NsaWVudFJlY3QoKVxyXG5cclxuICAgICAgaWYoICEoeCA+IHJlY3QubGVmdCAmJiB4IDwgcmVjdC5yaWdodCAmJiB5ID4gcmVjdC50b3AgJiYgeSA8IHJlY3QuYm90dG9tKSApIHtcclxuICAgICAgICBkb2N1bWVudC5yZW1vdmVFdmVudExpc3RlbmVyKCdjbGljaycsIGhhbmRsZXIpXHJcbiAgICAgICAgdGhpcy5zaG93RmlsdGVycyA9IGZhbHNlXHJcbiAgICAgICAgJHNjb3BlLiRhcHBseSgpXHJcbiAgICAgIH1cclxuICAgIH0uYmluZCh0aGlzKVxyXG5cclxuICAgIGRvY3VtZW50LmFkZEV2ZW50TGlzdGVuZXIoJ2NsaWNrJywgaGFuZGxlcilcclxuICB9XHJcblxyXG4gIHRoaXMuc2hvd0NhdGVnb3JpZXMgPSAoKSA9PiB7XHJcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXHJcbiAgICB0aGlzLnNldHRpbmdDYXQgPSB0cnVlXHJcbiAgICAkdGltZW91dCggKCkgPT4ge1xyXG4gICAgICB0aGlzLnNldHRpbmdDYXQgPSBmYWxzZVxyXG4gICAgfSwgMjUwKVxyXG5cclxuICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSB0cnVlXHJcbiAgfVxyXG5cclxuICAvKiBTb3J0aW5nIGluIGhlYWRlciAqL1xyXG4gIHRoaXMuc2V0Q2F0ZWdvcnkgPSBpZCA9PiB7XHJcbiAgICB0aGlzLnNldHRpbmdDYXQgPSBmYWxzZVxyXG5cclxuICAgIGxldCByZXMgPSB0aGlzLnNvcnRpbmdDYXRlZ29yaWVzLmZpbHRlcihlbCA9PiBlbC5pZCA9PT0gaWQgfCAwKVswXVxyXG4gICAgdGhpcy5zb3J0aW5nSWQgPSBpZFxyXG5cclxuICAgIGlmKHJlcykge1xyXG4gICAgICBsZXQgYXJyID0gcmVzLnRpdGxlLnNwbGl0KFwiXCIpXHJcbiAgICAgIHRoaXMuYXJyb3cgPSBhcnIucG9wKClcclxuICAgICAgYXJyLnBvcCgpXHJcblxyXG4gICAgICB0aGlzLmN1cnJlbnRDYXRlZ29yeSA9IGFyci5qb2luKFwiXCIpXHJcbiAgICB9XHJcblxyXG4gIH1cclxuXHJcbiAgdGhpcy5sb2dvdXQgPSBmdW5jdGlvbigpIHtcclxuICAgICAgZGIudXNlckxvZ291dCgpXHJcbiAgICAgICRzY29wZS5yZWRpcmVjdFRvVXJsKCcvJylcclxuICB9XHJcblxyXG4gIC8qIFdhdGNoIGFsbCBjbGljayBvbiB0aGUgYm9keSAqL1xyXG4gIHRoaXMuY2xpY2sgPSAoKSA9PiB7XHJcbiAgICBpZih0aGlzLnNob3dpbmdDYXRlZ29yaWVzICYmICF0aGlzLnNldHRpbmdDYXQpXHJcbiAgICAgIHRoaXMuc2hvd2luZ0NhdGVnb3JpZXMgPSBmYWxzZVxyXG4gIH1cclxuXHJcblxyXG4gIC8qIENvcnJlY3QgcmVkaXJlY3QgdG8gdXJsIHRocm91Z2ggYXBwIHJvdXRlciovXHJcbiAgJHNjb3BlLnJlZGlyZWN0VG9VcmwgPSAodXJsLCBpbW1lZGlhdGUpID0+IHtcclxuICAgIGlmKGltbWVkaWF0ZSlcclxuICAgICAgJGxvY2F0aW9uLnBhdGgodXJsKVxyXG4gICAgZWxzZVxyXG4gICAgICAkdGltZW91dCgoKSA9PiB7XHJcbiAgICAgICAgJGxvY2F0aW9uLnBhdGgodXJsKVxyXG4gICAgICB9LCAyNTApXHJcbiAgfVxyXG5cclxuICAvKiBVc2UgdGhpcyBtZXRob2QgZm9yIGdsb2JhbCBwdXJwb3NlIGVycm9ycyAqL1xyXG4gICRzY29wZS5kaXNwbGF5RXJyb3IgPSB0ZXh0ID0+IHtcclxuICAgIGFsZXJ0KHRleHQpXHJcbiAgICBjb25zb2xlLmVycm9yKG5ldyBFcnJvcihcInRleHRcIikpXHJcbiAgfVxyXG5cclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBjb250cm9sbGVycy9tYWluLmpzXG4gKiovIiwiJ3VzZSBzdHJpY3QnXHJcblxyXG5sZXQgdXRpbHMgPSByZXF1aXJlKCcuL3V0aWxzJyksXHJcbiAgICBjb25maWcgPSByZXF1aXJlKCcuLi9kYXRhL2NvbmZpZycpXHJcblxyXG5sZXQgY3R4ID0gbW9kdWxlLmV4cG9ydHMgPSB7fVxyXG5cclxubW9kdWxlLmV4cG9ydHMuaW5pdCA9IGZ1bmN0aW9uKCRodHRwKSB7XHJcbiAgLyogaW5pdCBkYXRhIGZyb20gZGF0YWJhc2UgaGVyZSAqL1xyXG4gIGN0eC5zZXREZWZhdWx0cygpXHJcbiAgdGhpcy50cmFuc3BvcnQgPSAkaHR0cFxyXG5cclxuICBjdHguY2hlY2tVc2VySXNMb2dnZWQoZnVuY3Rpb24oZXJyLCBkYXRhKSB7XHJcbiAgICBpZihlcnIpIGNvbnNvbGUuZXJyb3IoZXJyKVxyXG4gICAgZWxzZSB7XHJcbiAgICAgIGlmKGRhdGEpIGN0eC5zYXZlVXNlckRhdGEoZGF0YSlcclxuICAgICAgZWxzZSBjb25zb2xlLmxvZyhcIlVzZXIgaXMgbm90IGxvZ2dlZCBpblwiKVxyXG4gICAgfVxyXG4gIH0uYmluZCh0aGlzKSlcclxuXHJcbiAgY29uc29sZS5sb2coXCJEYXRhYmFzZSBpbml0aWFsaXplZFwiKVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cy5zZXREZWZhdWx0cyA9IGZ1bmN0aW9uKCkge1xyXG4gIGNvbnNvbGUubG9nKFwiRGF0YWJhc2UgOjogZGVmYXVsdHMgc2V0XCIpXHJcblxyXG4gIGN0eC5mYXZvdXJpdGVzID0gbnVsbFxyXG4gIGN0eC5tYWlsYm94ID0gbnVsbFxyXG4gIGN0eC51c2VyID0gbnVsbFxyXG4gIGN0eC5ub3RpZmljYXRpb25zID0geyBoZWxsbyA6IFwicHJldmVkXCIgfVxyXG59XHJcblxyXG5cclxubW9kdWxlLmV4cG9ydHMuY2hlY2tFbWFpbCA9IGZ1bmN0aW9uKGVtYWlsLCBjYikge1xyXG4gIHV0aWxzLnJlcXVlc3Qoe1xyXG4gICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMuY2hlY2tFbWFpbC5tZXRob2QsXHJcbiAgICBcInVybFwiIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5jaGVja0VtYWlsLnVybCxcclxuICAgIFwiZGF0YVwiIDogZW1haWwsXHJcbiAgICBcImhlYWRlcnNcIiA6IHtcclxuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwidGV4dC9wbGFpblwiXHJcbiAgICB9XHJcbiAgfSkudGhlbihkYXRhID0+IGNiKG51bGwsIGRhdGEpLCBlcnIgPT4gY2IoZXJyKSlcclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMubG9naW4gPSBmdW5jdGlvbiggZGF0YSwgY2IgKSB7XHJcbiAgLy8gdXRpbHMucmVxdWVzdCh7XHJcbiAgLy8gICBcIm1ldGhvZFwiIDogY29uZmlnLnJvdXRlcy5sb2dpbi5tZXRob2QsXHJcbiAgLy8gICBcInVybFwiIDogY29uZmlnLmFwaS5hdXRoICsgY29uZmlnLnJvdXRlcy5sb2dpbi51cmwsXHJcbiAgLy8gICBcImRhdGFcIiA6IGRhdGEsXHJcbiAgLy8gICBcImhlYWRlcnNcIiA6IHtcclxuICAvLyAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwiYXBwbGljYXRpb24vanNvblwiLFxyXG4gIC8vICAgICBcIndpdGhDcmVkZW50aWFsc1wiIDogXCJ0cnVlXCJcclxuICAvLyAgIH1cclxuICAvLyB9KS50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSksIGVyciA9PiBjYihlcnIpKVxyXG4gIGN0eC50cmFuc3BvcnQoe1xyXG4gICAgbWV0aG9kIDogY29uZmlnLnJvdXRlcy5sb2dpbi5tZXRob2QsXHJcbiAgICB1cmwgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmxvZ2luLnVybCxcclxuICAgIGRhdGEgOiBkYXRhLFxyXG4gICAgaGVhZGVycyA6IHtcclxuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwiYXBwbGljYXRpb24vanNvblwiLFxyXG4gICAgfSxcclxuICAgIHdpdGhDcmVkZW50aWFscyA6IHRydWVcclxuICB9KVxyXG4gIC50aGVuKGRhdGEgPT4gY2IobnVsbCwgZGF0YSkpXHJcbiAgLmNhdGNoKGNiKVxyXG59XHJcblxyXG4vKiBUaGlzIG1ldGhvZCBkb2VzIHNhdmVzIHVzZXIgZGF0YSBpbiB0aGlzIG1vZHVsZSBvbmx5LCBubyBiYWNrZW5kIGNvbW11bmljYXRpb24gKi9cclxubW9kdWxlLmV4cG9ydHMuc2F2ZVVzZXJEYXRhID0gZnVuY3Rpb24oZGF0YSkge1xyXG4gIGRhdGEgPSBkYXRhLmxlbmd0aCA/IEpTT04ucGFyc2UoZGF0YSkgOiBcIlwiXHJcbiAgdGhpcy51c2VyID0ge31cclxuICAvKiBUT0RPOiDRgNCw0YHQv9Cw0YDRgdC40YLRjCDQtNCw0L3QvdGL0LUg0LIg0L7RgdC80YvRgdC70LXQvdC90YvQtSDQv9C10YDQtdC80LXQvdC90YvQtSAqL1xyXG5cclxuICBjb25zb2xlLmxvZyhcIkRhdGFiYXNlOjogVXNlciBkYXRhIHNhdmVkIHN1Y2Nlc3NmdWxseSgg0YjRg9GC0LrQsCApIFwiKVxyXG59XHJcblxyXG5tb2R1bGUuZXhwb3J0cy5jaGVja1VzZXJJc0xvZ2dlZCA9IGZ1bmN0aW9uKCBjYiApIHtcclxuICBjdHgudHJhbnNwb3J0KHtcclxuICAgIG1ldGhvZDogY29uZmlnLnJvdXRlcy5jaGVja0xvZ2dlZC5tZXRob2QsXHJcbiAgICB1cmw6IGNvbmZpZy5hcGkudXJsICsgY29uZmlnLnJvdXRlcy5jaGVja0xvZ2dlZC51cmwsXHJcbiAgICB3aXRoQ3JlZGVudGlhbHMgOiB0cnVlXHJcbiAgfSlcclxuICAudGhlbihkYXRhID0+IGNiKG51bGwsIGRhdGEpKVxyXG4gIC5jYXRjaChjYilcclxuICAvLyB1dGlscy5yZXF1ZXN0KHtcclxuICAvLyAgIFwibWV0aG9kXCIgOiBjb25maWcucm91dGVzLmNoZWNrTG9nZ2VkLm1ldGhvZCxcclxuICAvLyAgIFwidXJsXCIgOiBjb25maWcuYXBpLnVybCArIGNvbmZpZy5yb3V0ZXMuY2hlY2tMb2dnZWQudXJsXHJcbiAgLy8gfSkudGhlbihkYXRhID0+IGNiKG51bGwsIGRhdGEpLCBlcnIgPT4gY2IoZXJyKSlcclxufVxyXG5cclxubW9kdWxlLmV4cG9ydHMudXNlckxvZ291dCA9IGZ1bmN0aW9uKCkge1xyXG4gIGN0eC5zZXREZWZhdWx0cygpXHJcblxyXG4gIHV0aWxzLnJlcXVlc3Qoe1xyXG4gICAgXCJtZXRob2RcIiA6IGNvbmZpZy5yb3V0ZXMubG9nb3V0Lm1ldGhvZCxcclxuICAgIFwidXJsXCIgOiBjb25maWcuYXBpLmF1dGggKyBjb25maWcucm91dGVzLmxvZ291dC51cmxcclxuICB9KS50aGVuKCgpPT57fSwgKCk9Pnt9KVxyXG59XHJcblxuXG5cbi8qKiBXRUJQQUNLIEZPT1RFUiAqKlxuICoqIG1vZHVsZXMvZGIuanNcbiAqKi8iLCIndXNlIHN0cmljdCdcclxuLypcclxuICBFeHBlY3Qgb3B0aW9ucyBvYmplY3QgbGlrZSB0aGlzOlxyXG4gIHtcclxuICAgIFwibWV0aG9kXCIgOiBcIlBPU1RcIixcclxuICAgIFwidXJsXCIgOiBcImh0dHA6Ly9zb21ldXJsLmNvbS9cIixcclxuICAgIFwiZGF0YVwiIDogXCJkYXRhXCIsXHJcbiAgICBcImhlYWRlcnNcIiA6IHtcclxuICAgICAgXCJDb250ZW50LVR5cGVcIiA6IFwiYXBwbGljYXRpb24vanNvblwiLFxyXG4gICAgICBcIkNvbnRlbnQtTGVuZ3RoXCIgOiBcIjEwMjNcIlxyXG4gICAgfVxyXG4gIH1cclxuXHJcbiAgREVGQVVMVFM6XHJcbiAgTWV0aG9kIC0gZGVmYXVsdCBpcyBHRVQsXHJcbiAgVVJMIC0gcmVxdWlyZWQsXHJcbiAgZGF0YSAtIG9wdGlvbmFsLFxyXG4gIGhlYWRlcnMgLSBvcHRpb25hbFxyXG4qL1xyXG5cclxubW9kdWxlLmV4cG9ydHMucmVxdWVzdCA9IGZ1bmN0aW9uKG9wdGlvbnMpIHtcclxuICByZXR1cm4gbmV3IFByb21pc2UoIChyZXNvbHZlLCByZWplY3QpID0+IHtcclxuICAgIC8qIFNldHRpbmcgZGVmYXVsdHMgKi9cclxuICAgIGxldCB7IG1ldGhvZD1cIkdFVFwiLCB1cmwsIGRhdGEsIGhlYWRlcnMgfSA9IG9wdGlvbnNcclxuXHJcbiAgICAvKiBTb21lIHZhbGlkYXRpb24gKi9cclxuICAgIGlmKCF1cmwpXHJcbiAgICAgIHJldHVybiBjb25zb2xlLmVycm9yKFwiVXJsIG5vdCBzcGVjaWZpZWRcIilcclxuXHJcbiAgICBpZiggKG1ldGhvZCA9PSBcIlBPU1RcIiB8fCBtZXRob2QgPT0gXCJQVVRcIikgJiYgIWRhdGEpXHJcbiAgICAgIHJldHVybiBjb25zb2xlLmVycm9yKFwiTm90aGluZyB0byBzZW5kIGhlcmUgPSlcIilcclxuXHJcbiAgICAvKiBTdGFydCBjb25zdHJ1Y3RpbmcgcmVxdWVzdCAqL1xyXG4gICAgdmFyIHhociA9IG5ldyBYTUxIdHRwUmVxdWVzdCgpXHJcbiAgICB4aHIub3BlbihtZXRob2QsIHVybCwgdHJ1ZSlcclxuXHJcbiAgICBpZihoZWFkZXJzKVxyXG4gICAgICBmb3IoIHZhciBwcm9wIGluIGhlYWRlcnMpXHJcbiAgICAgICAgeGhyLnNldFJlcXVlc3RIZWFkZXIocHJvcCwgaGVhZGVyc1twcm9wXSlcclxuXHJcbiAgICBpZihkYXRhICYmIGhlYWRlcnNbJ0NvbnRlbnQtVHlwZSddICE9PSBcInRleHQvcGxhaW5cIilcclxuICAgICAgeGhyLnNlbmQoSlNPTi5zdHJpbmdpZnkoZGF0YSkpXHJcbiAgICBlbHNlIGlmKGRhdGEpXHJcbiAgICAgIHhoci5zZW5kKGRhdGEpXHJcbiAgICBlbHNlXHJcbiAgICAgIHhoci5zZW5kKClcclxuXHJcblxyXG5cclxuICAgIHhoci5vbnJlYWR5c3RhdGVjaGFuZ2UgPSBmdW5jdGlvbigpIHtcclxuICAgICAgaWYgKHRoaXMucmVhZHlTdGF0ZSAhPSA0KVxyXG4gICAgICAgIHJldHVyblxyXG5cclxuICAgICAgaWYgKHRoaXMuc3RhdHVzICE9IDIwMClcclxuICAgICAgICByZXR1cm4gcmVqZWN0KCdFcnJvcjogJyArICh0aGlzLnN0YXR1cyA/IGAoJHt0aGlzLnN0YXR1c30pICR7dGhpcy5zdGF0dXNUZXh0fWA6ICdyZXF1ZXN0IGZhaWwnKSlcclxuICAgICAgZWxzZVxyXG4gICAgICAgIHJldHVybiByZXNvbHZlKHRoaXMucmVzcG9uc2VUZXh0KVxyXG5cclxuICAgIH1cclxuXHJcbiAgfSlcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL3V0aWxzLmpzXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSB7XG5cdFwiYXBpXCI6IHtcblx0XHRcInVybFwiOiBcImh0dHA6Ly85My43My4xMDkuMzg6ODA4MS9cIixcblx0XHRcImF1dGhcIjogXCJodHRwOi8vOTMuNzMuMTA5LjM4OjgwODMvXCJcblx0fSxcblx0XCJyb3V0ZXNcIjoge1xuXHRcdFwiZ2V0QnVsbGV0aW5zXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvcmVzdC9vZmZlcnNTZXJ2aWNlL29mZmVyL3JlYWQvYWxsXCIsXG5cdFx0XHRcImFjY2VwdFwiOiB7XG5cdFx0XHRcdFwic2tpcFwiOiAwLFxuXHRcdFx0XHRcImxpbWl0XCI6IDIwXG5cdFx0XHR9XG5cdFx0fSxcblx0XHRcImxvZ2luXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvb2F1dGgvbG9naW5cIixcblx0XHRcdFwiYWNjZXB0XCI6IHtcblx0XHRcdFx0XCJlbWFpbFwiOiBcInNzczJAZ21haWwuY29tXCIsXG5cdFx0XHRcdFwicGFzc3dvcmRcIjogXCIxMjM0NTZcIlxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJsb2dvdXRcIjoge1xuXHRcdFx0XCJtZXRob2RcIjogXCJHRVRcIixcblx0XHRcdFwidXJsXCI6IFwiYXBpL29hdXRoL2xvZ291dFwiXG5cdFx0fSxcblx0XHRcInJlZ2lzdGVyXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvb2F1dGgvcmVnaXN0ZXJcIixcblx0XHRcdFwiYWNjZXB0XCI6IHtcblx0XHRcdFx0XCJlbWFpbFwiOiBcInNzczJAZ21haWwuY29tXCIsXG5cdFx0XHRcdFwicGFzc3dvcmRcIjogXCIxMjM0NTZcIlxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJjaGVja0VtYWlsXCI6IHtcblx0XHRcdFwibWV0aG9kXCI6IFwiUE9TVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvb2F1dGgvbG9naW4vY2hlY2tFbWFpbFwiLFxuXHRcdFx0XCJhY2NlcHRcIjoge1xuXHRcdFx0XHRcImVtYWlsXCI6IFwic3NzMkBnbWFpbC5jb21cIlxuXHRcdFx0fVxuXHRcdH0sXG5cdFx0XCJjaGVja0xvZ2dlZFwiOiB7XG5cdFx0XHRcIm1ldGhvZFwiOiBcIkdFVFwiLFxuXHRcdFx0XCJ1cmxcIjogXCJhcGkvcmVzdC9wcm9maWxlc1NlcnZpY2UvcHJvZmlsZS9yZWFkL2xvZ2dlZEluUHJvZmlsZVwiXG5cdFx0fVxuXHR9XG59O1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9kYXRhL2NvbmZpZy5qc29uXG4gKiogbW9kdWxlIGlkID0gNzlcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIid1c2Ugc3RyaWN0J1xyXG5cclxuY29uc3QgVEVNUExBVEVfUkVOREVSX0RFTEFZID0gNTAwLFxyXG4gICAgICBBTklNQVRJT05fREVMQVkgPSA5MDBcclxuXHJcbm1vZHVsZS5leHBvcnRzID0gZnVuY3Rpb24oJHNjb3BlLCAkdGltZW91dCkge1xyXG4gIGxldCBjb3ZlciA9IGRvY3VtZW50LmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2NvdmVyJylbMF1cclxuICBsZXQgY29udGVudCA9IGRvY3VtZW50LmdldEVsZW1lbnRzQnlDbGFzc05hbWUoJ2dlbmVyYWwtY29udGVudCcpWzBdXHJcblxyXG4gIGxldCBkaXNwbGF5ID0gdHJ1ZVxyXG5cclxuICAkc2NvcGUuJG9uKCckcm91dGVDaGFuZ2VTdWNjZXNzJywgZnVuY3Rpb24oKSB7XHJcbiAgICBpZiggZGlzcGxheSApIHtcclxuICAgICAgJHRpbWVvdXQoZnVuY3Rpb24oKSB7XHJcbiAgICAgICAgY29udGVudC5zdHlsZS5kaXNwbGF5ID0gXCJcIlxyXG4gICAgICAgIGNvdmVyLmNsYXNzTGlzdC5hZGQoJ2hpZGUnKVxyXG4gICAgICAgICR0aW1lb3V0KGZ1bmN0aW9uKCkge1xyXG4gICAgICAgICAgY292ZXIuc3R5bGUuZGlzcGxheT1cIm5vbmVcIlxyXG4gICAgICAgIH0uYmluZCh0aGlzKSwgQU5JTUFUSU9OX0RFTEFZKVxyXG4gICAgICB9LmJpbmQodGhpcyksIFRFTVBMQVRFX1JFTkRFUl9ERUxBWSlcclxuXHJcbiAgICAgIGRpc3BsYXkgPSBmYWxzZVxyXG4gICAgfVxyXG4gIH0uYmluZCh0aGlzKSlcclxufVxyXG5cblxuXG4vKiogV0VCUEFDSyBGT09URVIgKipcbiAqKiBtb2R1bGVzL2xvYWRlci5qc1xuICoqLyIsIm1vZHVsZS5leHBvcnRzID0ge1xuXHRcIml0ZW1zXCI6IFtcblx0XHR7XG5cdFx0XHRcImlkXCI6IDEsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa+XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDIsXG5cdFx0XHRcInRpdGxlXCI6IFwi0YbQtdC90LAg4pa0XCIsXG5cdFx0XHRcInJlcXVlc3RDcml0ZXJpYVwiOiBcIlwiXG5cdFx0fSxcblx0XHR7XG5cdFx0XHRcImlkXCI6IDMsXG5cdFx0XHRcInRpdGxlXCI6IFwi0LTQsNGC0LAgIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA0LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItC00LDRgtCwIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA1LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWvlwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH0sXG5cdFx0e1xuXHRcdFx0XCJpZFwiOiA2LFxuXHRcdFx0XCJ0aXRsZVwiOiBcItGA0LXQudGC0LjQvdCzIOKWtFwiLFxuXHRcdFx0XCJyZXF1ZXN0Q3JpdGVyaWFcIjogXCJcIlxuXHRcdH1cblx0XVxufTtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vZGF0YS9zb3J0aW5nLmpzb25cbiAqKiBtb2R1bGUgaWQgPSA4MVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL2JhY2sucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9iYWNrLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDgyXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvY2xvc2UucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9jbG9zZS5wbmdcbiAqKiBtb2R1bGUgaWQgPSA4M1xuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3RvQ29udGFjdHMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy90b0NvbnRhY3RzLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDg0XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvYmFja2dyb3VuZFZpZGdldHMucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9iYWNrZ3JvdW5kVmlkZ2V0cy5wbmdcbiAqKiBtb2R1bGUgaWQgPSA4NVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3J5cHIucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9yeXByLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDg2XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvdGVuZGVyLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdGVuZGVyLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDg3XG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iLCJtb2R1bGUuZXhwb3J0cyA9IF9fd2VicGFja19wdWJsaWNfcGF0aF9fICsgXCJpbWFnZXMvZnJpbGFucy5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL2ZyaWxhbnMucG5nXG4gKiogbW9kdWxlIGlkID0gODhcbiAqKiBtb2R1bGUgY2h1bmtzID0gMFxuICoqLyIsIm1vZHVsZS5leHBvcnRzID0gX193ZWJwYWNrX3B1YmxpY19wYXRoX18gKyBcImltYWdlcy90ZWhub2xvZ2lpLnBuZ1wiO1xuXG5cbi8qKioqKioqKioqKioqKioqKlxuICoqIFdFQlBBQ0sgRk9PVEVSXG4gKiogLi9pbWFnZXMvdGVobm9sb2dpaS5wbmdcbiAqKiBtb2R1bGUgaWQgPSA4OVxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3JhYm90YS5wbmdcIjtcblxuXG4vKioqKioqKioqKioqKioqKipcbiAqKiBXRUJQQUNLIEZPT1RFUlxuICoqIC4vaW1hZ2VzL3JhYm90YS5wbmdcbiAqKiBtb2R1bGUgaWQgPSA5MFxuICoqIG1vZHVsZSBjaHVua3MgPSAwXG4gKiovIiwibW9kdWxlLmV4cG9ydHMgPSBfX3dlYnBhY2tfcHVibGljX3BhdGhfXyArIFwiaW1hZ2VzL3Byb2VrdGkucG5nXCI7XG5cblxuLyoqKioqKioqKioqKioqKioqXG4gKiogV0VCUEFDSyBGT09URVJcbiAqKiAuL2ltYWdlcy9wcm9la3RpLnBuZ1xuICoqIG1vZHVsZSBpZCA9IDkxXG4gKiogbW9kdWxlIGNodW5rcyA9IDBcbiAqKi8iXSwibWFwcGluZ3MiOiI7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUN0Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTs7QUFFQTtBQUVBO0FBQ0E7QUFEQTtBQUlBO0FBREE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7O0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7Ozs7OztBQ2hEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7Ozs7OztBQ0ZBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7Ozs7O0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ2hEQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ3JQQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7OztBQ3BCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDUEE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7O0FDcEJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNQQTs7Ozs7O0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNwQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ1BBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTs7QUFFQTs7Ozs7OztBQy9CQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNKQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBTEE7QUFDQTtBQU9BO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFEQTtBQUlBOzs7Ozs7QUNwQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBckJBO0FBdUJBOzs7Ozs7QUMxQkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBakNBO0FBbUNBOzs7Ozs7QUN0Q0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBTkE7QUFRQTtBQUNBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBSUE7QUFDQTtBQUFBO0FBQUE7QUFFQTtBQUNBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQXBCQTtBQXVCQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQWhHQTtBQWtHQTs7Ozs7O0FDMUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUZBO0FBSUE7QUFRQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFsREE7QUFvREE7Ozs7OztBQ3ZEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFJQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUlBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBdEVBO0FBd0VBOzs7Ozs7QUM3RUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7Ozs7Ozs7QUFPQTtBQUNBO0FBQUE7QUFBQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQzFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQURBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBQ0E7QUFDQTtBQUhBO0FBS0E7QUFDQTtBQUNBO0FBQ0E7QUFIQTtBQUtBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUNBO0FBREE7QUF2REE7Ozs7Ozs7O0FDRkE7Ozs7OztBQ0FBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7OztBQUlBO0FBTUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7OztBQ3JCQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7O0FBVUE7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBO0FBR0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDbEVBO0FBQ0E7QUFDQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7OztBQVFBO0FBQ0E7QUFDQTtBQUNBO0FBRkE7QUFJQTtBQUNBOztBQUlBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUVBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7Ozs7Ozs7QUNqRkE7QUFDQTs7Ozs7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFBQTtBQUNBOzs7O0FBR0E7QUFDQTtBQUNBO0FBSUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7O0FBQUE7OztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTs7O0FBRUE7QUFDQTtBQUdBOzs7Ozs7QUFHQTs7Ozs7O0FDakRBO0FBQ0E7Ozs7O0FBQ0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQUE7QUFDQTs7OztBQUdBO0FBRUE7QUFDQTs7O0FBQUE7OztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBR0E7QUFDQTs7O0FBRUE7QUFDQTtBQUdBOzs7Ozs7QUFHQTs7Ozs7O0FDcENBO0FBQ0E7QUFDQTs7Ozs7O0FDRkE7QUFDQTtBQUNBOzs7Ozs7QUNGQTtBQUNBO0FBQ0E7O0FBRUE7QUFBQTtBQUNBO0FBQUE7QUFDQTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTs7QUFFQTs7QUFFQTtBQUNBO0FBQ0E7OztBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUFBO0FBQUE7QUFDQTtBQUdBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FBRUE7QUFDQTtBQUVBO0FBQ0E7O0FBR0E7QUFDQTtBQUlBO0FBQ0E7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBRUE7Ozs7OztBQ3hIQTtBQUNBO0FBQ0E7QUFBQTtBQUNBO0FBRUE7QUFDQTtBQUNBOztBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFFQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFKQTtBQU9BO0FBQUE7QUFBQTtBQUFBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7O0FBVUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBREE7QUFHQTtBQVBBO0FBU0E7QUFBQTtBQUVBO0FBQ0E7O0FBRUE7QUFDQTtBQUNBOzs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBSEE7QUFLQTtBQUFBOzs7OztBQU1BO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFGQTtBQUlBOzs7Ozs7QUNqR0E7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBb0JBO0FBQ0E7O0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUNBOzs7QUFJQTtBQUNBO0FBRUE7QUFDQTs7QUFHQTtBQUNBO0FBQ0E7QUFDQTtBQUVBO0FBREE7QUFDQTtBQVdBO0FBQ0E7QUFDQTtBQUVBO0FBS0E7QUFFQTs7Ozs7OztBQzVEQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUM5Q0E7QUFDQTtBQUNBO0FBQUE7QUFDQTtBQUVBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7O0FDdkJBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7QUNqQ0E7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7Ozs7OztBQ0FBOzs7Ozs7QUNBQTs7Ozs7O0FDQUE7OzsiLCJzb3VyY2VSb290IjoiIn0=