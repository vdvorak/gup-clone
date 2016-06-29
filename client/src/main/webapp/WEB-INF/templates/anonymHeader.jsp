<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- не залогиненный -->
<div class="notLogged">
    <ul>
        <li class="notLogged-li"><a id='go' href="#">Регистрация</a></li>
        <li class="notLogged-li"><p>&nbsp;/&nbsp;</p></li>
        <li class="notLogged-li"><a id='goo' href="#">Вход</a></li>
    </ul>

    <div id="modal_form"><!-- Сaмo oкнo -->
        <div id="tab-container" class="tab-container">
            <ul class='etabs'>
                <li class='tab'><a href="#entry-form">Вход</a></li>
                <li class='tab'><a href="#registration">Регистрация</a></li>
            </ul>

            <div id="registration">
                <div class="registration-form">
                    <label for="registration-email">Введите логин/ E-mail</label>
                    <input type="email" name="registration-email" id='registration-email' required>

                    <div class="clearfix"></div>

                    <label for="registration-password">Пароль</label>
                    <input type="password" name="registration-password" id='registration-password' placeholder="Не менее 6 символов" required>

                    <div class="clearfix"></div>

                    <label for="repeat-registration-password">Повторите пароль</label>
                    <input type="password" name="repeat-registration-password" id='repeat-registration-password' required>

                    <div class="clearfix"></div>

                    <div class="conditions">
                        <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-registration" value="1" name="k"/><span></span></label>
                        <a href="#">Мною были прочитаны все условия</a>
                    </div>
                    <button class="registration-submit" id="registrationBtn">Зарегистрироваться</button>
                </div>
            </div>





            <!-- Вход -->
            <div id="entry-form" ng-app="app">
                <script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>
                <style>
                    .err{
                        border: 1px solid;
                        margin: 10px 0px;
                        padding:10px;
                        color: #D8000C;
                        background-color: #FFBABA;
                        list-style: none;
                    }
                </style>
                <div class="container" ng-app="app" ng-controller="AppCtrl">
                    <div class="col-sm-4 col-sm-offset-2">
                        <!-- FORM -->
                        <form name="userForm" ng-submit="submitForm()">
                            <ul>
                                <li class="err" ng-repeat="error in errors"> {{error}} </li>
                            </ul>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" ng-model="user.email" placeholder="E-mail" required />
                                <span ng-show="errorEmail">{{errorEmail}}</span>
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control" ng-model="user.password" placeholder="Пароль" ng-minlength="6" ng-maxlength="20" required />
                                <span ng-show="errorPassword">{{errorPassword}}</span>
                            </div>
                            <button type="submit" class="btn btn-primary">Войти</button>
                        </form>
                    </div>
                </div>
                <script>
                    // (Simple Authentication for Angular.js App) http://beletsky.net/2013/11/simple-authentication-in-angular-dot-js-app.html
                    // (window.location.assign) http://stackoverflow.com/questions/27941876/how-to-redirect-to-another-page-using-angular-js
                    var app = angular.module('app', []);

                    app.controller('AppCtrl', function($scope, $http, $location) {
                        $scope.user = {};
                        $scope.errors = [];

                        $scope.submitForm = function() {
                            $http({
                                method  : 'POST',
                                url     : 'http://93.73.109.38:8083/login', //url     : 'http://localhost:8083/login',
                                data    : $scope.user,
                                headers : {'Content-Type': 'application/json'},
                                withCredentials : true
                            })
                            .success(function(data) {
                                if (data.errors) {
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
                    });

                    app.config(function($httpProvider) {
                        $httpProvider.defaults.useXDomain = true;
                        delete $httpProvider.defaults.headers.common['X-Requested-With'];
                        /* $httpProvider.defaults.headers.common['Authorization'] = 'Basic YWRtaW46YWRtaW4='; */
                    });
                </script>
                <%--<div class="view-container">--%>
                    <%--<div ng-view class="view-frame"></div>--%>
                <%--</div>--%>
            </div>




        </div>
    </div>

    <div id="overlay"></div><!-- Пoдлoжкa -->
</div>