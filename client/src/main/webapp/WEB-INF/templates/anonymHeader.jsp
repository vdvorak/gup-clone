<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular.min.js"></script>
<script data-require="angular-route@1.4.0" data-semver="1.4.0" src="https://code.angularjs.org/1.4.0/angular-route.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular-cookies.js"></script>
<style>
    .err{
        border: 1px solid;  margin: 10px 0px;
        padding:10px;  color: #D8000C;
        background-color: #FFBABA;   list-style: none;
    }
</style>

<!-- не залогиненный -->
<div class="notLogged" ng-app = "formApp">
    <ul>
        <li class="notLogged-li"><a ng-href = "#/registration">Регистрация</a></li>
        <li class="notLogged-li"><p>&nbsp;/&nbsp;</p></li>
        <li class="notLogged-li"><a ng-href = "#/entry-form">Вход</a></li>
    </ul>
    <div ng-view></div>

    <div id="modal_form"><!-- Сaмo oкнo -->
        <div id="tab-container" class="tab-container">

            <!-- Регистрация -->
            <script type = "text/ng-template" id = "registration">
                <div class="container">
                    <div class="col-sm-8 col-sm-offset-2">
                        <div class="page-header">
                            <h1> Регистрация </h1>
                        </div>
                        <!-- FORM -->
                        <form name="userForm" ng-submit="submitForm2()">
                            <ul>
                                <li class="err" ng-repeat="error in errors"> {{error}} </li>
                            </ul>
                            <div class="form-group">
                                <label>Введите логин/ E-mail</label>
                                <input type="email" name="email" class="form-control" ng-model="user.email" placeholder="Введите логин/ E-mail" required />
                                <span ng-show="errorEmail">{{errorEmail}}</span>
                            </div>
                            <div class="form-group">
                                <label>Пароль</label>
                                <input type="password" name="password" class="form-control" ng-model="user.password" placeholder="Не менее 6 символов" ng-minlength="6" ng-maxlength="20" required />
                                <span ng-show="errorPassword">{{errorPassword}}</span>
                            </div>
                            <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                        </form>
                    </div>
                </div>
            </script>

            <!-- Вход -->
            <script type = "text/ng-template" id = "entry-form">
                <div class="container">
                    <div class="col-sm-8 col-sm-offset-2">
                        <div class="page-header">
                            <h1> Вход </h1>
                        </div>
                        <!-- FORM -->
                        <form name="userForm" ng-submit="submitForm()">
                            <ul>
                                <li class="err" ng-repeat="error in errors"> {{error}} </li>
                            </ul>
                            <div class="form-group">
                                <label>Введите логин/ E-mail</label>
                                <input type="email" name="email" class="form-control" ng-model="user.email" placeholder="Введите логин/ E-mail" required />
                                <span ng-show="errorEmail">{{errorEmail}}</span>
                            </div>
                            <div class="form-group">
                                <label>Пароль</label>
                                <input type="password" name="password" class="form-control" ng-model="user.password" placeholder="Не менее 6 символов" ng-minlength="6" ng-maxlength="20" required />
                                <span ng-show="errorPassword">{{errorPassword}}</span>
                            </div>
                            <button type="submit" class="btn btn-primary">Войти</button>
                        </form>
                    </div>
                </div>
            </script>

            <script>
                // Code goes here
                var app = angular.module('formApp', ['ngRoute', 'ngCookies']);

                app.config(['$routeProvider', function($routeProvider){
                    $routeProvider.when('/entry-form', {
                        templateUrl:'entry-form',
                        controller:'LoginCtrl'
                    })
                            .when('/registration', {
                                templateUrl:'registration',
                                controller:'RegisterCtrl'
                            })
                }]);

                app.controller('LoginCtrl', ['$scope', '$http', '$cookies', '$cookieStore', function($scope, $http, $cookies, $cookieStore){
                    // create a blank object to handle form data.
                    $scope.user = {};
                    $scope.errors = [];

                    // calling our submit function.
                    $scope.submitForm = function() {
                        // Posting data to php file
                        $http({
                            method  : 'POST',
                            url     : 'http://93.73.109.38:8083/login', //url     : 'http://localhost:8083/login',
                            data    : $scope.user, //forms user object
                            headers : {'Content-Type': 'application/json'},
                            withCredentials : true
                        })
                                .success(function(data) {
                                    if (data.errors) {
                                        // Showing errors.
                                        $scope.errorEmail = data.errors.email;
                                        $scope.errorPassword = data.errors.password;
                                    } else {
                                        window.location.assign("/index");
                                    }
                                })
                                .error(function(data, status) {
                                    $scope.errors.push(status);
                                });
                    };
                }]);

                app.controller('RegisterCtrl', ['$scope', '$http', '$cookies', '$cookieStore', function($scope, $http, $cookies, $cookieStore){
                    // create a blank object to handle form data.
                    $scope.user = {};
                    $scope.errors = [];

                    // calling our submit function.
                    $scope.submitForm2 = function() {
                        // Posting data to php file
                        $http({
                            method  : 'POST',
                            url     : 'http://93.73.109.38:8083/register', //url     : 'http://localhost:8083/register',
                            data    : $scope.user, //forms user object
                            headers : {'Content-Type': 'application/json'},
                            withCredentials : true
                        })
                                .success(function(data) {
                                    if (data.errors) {
                                        // Showing errors.
                                        $scope.errorEmail = data.errors.email;
                                        $scope.errorPassword = data.errors.password;
                                    } else {
                                        window.location.assign("/index");
                                    }
                                })
                                .error(function(data, status) {
                                    $scope.errors.push(status);
                                });
                    };
                }]);

                app.config(function($locationProvider, $httpProvider) {
                    $httpProvider.defaults.useXDomain = true;
                    delete $httpProvider.defaults.headers.common['X-Requested-With'];
                    /* $httpProvider.defaults.headers.common['Authorization'] = 'Basic YWRtaW46YWRtaW4='; */
                });

                app.controller()
            </script>



        </div>
    </div>

    <div id="overlay"></div><!-- Пoдлoжкa -->
</div>