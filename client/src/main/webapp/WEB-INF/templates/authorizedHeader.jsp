<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- залогиненный -->
<div class="Logged">
    <div id="overlay"></div>
    <!-- Пoдлoжкa -->
    <div class="user">
        <img id="headerProfileImg" src="" alt="face">

        <p id="headerProfileName"></p>

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
            <div class="clearfix"></div>
            <a href="/prioffice">Личный кабинет</a>
            <a href="/dialogues">Мои сообщения</a>
            <a href="/edit-profile">Редактировать страницу</a>
            <!--<a href="/logout">Выход</a>-->
            <ul>
                <li class="err" ng-repeat="error in errors"> {{error}} </li>
            </ul>
            <a ng-click="func()" href="#">Выход</a>
        </div>
        <!-- Выход -->
        <script>
            // (Simple Authentication for Angular.js App) http://beletsky.net/2013/11/simple-authentication-in-angular-dot-js-app.html
            // (window.location.assign) http://stackoverflow.com/questions/27941876/how-to-redirect-to-another-page-using-angular-js
            var app = angular.module('app', []);

            app.controller('AppCtrl', function($scope, $http, $location) {
                $scope.errors = [];

                $scope.func = function() {
                    $http({
                        method  : 'POST',
                        url     : 'http://localhost:8083/logout', //url     : 'http://93.73.109.38:8083/logout',
                        headers : {'Content-Type': 'application/json'},
                        withCredentials : true
                    })
                            .success(function(data) {
                                if (data.errors) {
                                } else {
                                    window.location.assign("/index");
                                }
                            })
                            .error(function(data, status) {
                                //$scope.errors.push(status);
                                window.location.assign("/index");
                            });
                };
            });

            app.config(function($httpProvider) {
                $httpProvider.defaults.useXDomain = true;
                delete $httpProvider.defaults.headers.common['X-Requested-With'];
                /* $httpProvider.defaults.headers.common['Authorization'] = 'Basic YWRtaW46YWRtaW4='; */
            });
        </script>
    </div>







    <div class="mail">
        <img src="/resources/images/mail.png" alt="mail">

        <p id="unreadMessages" style="display: none; font-size: 12px;"></p>
        <%--<p id="unreadMessagesNum" style="display: none;"></p>--%>

        <div class="dropDownMail">
            <%--<div class="mailMessage">
                <img class="msg-avatar" src="about:blank" alt="logo" style="display: none">

                <p class="defaultP">У вас нет непрочитанных сообщений.</p>
            </div>
            <div class="answer">
                <p id="unread-msg-in-answer"></p>
                <img src="/resources/images/logo.png" alt="logo">
                <textarea required id="text-message-answer"></textarea>
                <button id="dialogue-answer-btn">Ответить</button>
            </div>--%>
        </div>
    </div>
    <div class="bell">
        <img src="/resources/images/bell.png" alt="bell">

        <p id="unreadNotificationsNum"></p>

        <div class="dropDownBell">
            <i id="read-all-events">Отметить все как прочитанные</i>
        </div>
    </div>
    <div class="book">
        <img src="/resources/images/book.png" alt="book">

        <div class="dropDownBook">
            <p class="defaultP">У вас нет контактов</p>
        </div>
    </div>
    <div class="money">
        <p>Баланс: <span id="score"></span> грн.</p>

        <div class="dropDownMoney">
            <button>Пополнить баланс</button>
        </div>
        <div class="modal">
            <div class="FillUpBalance">
                <i class="fa fa-times-circle"></i>

                <h2>Пополнить баланс</h2>

                <div>

                    <form method="post" action="https://www.liqpay.com/api/checkout"
                          accept-charset="utf-8">
                        <input id="liq-pay-data" type="hidden" name="data"
                               value="eyJhbW91bnQiOjEwMCwiY3VycmVuY3kiOiJVQUgiLCJkZXNjcmlwdGlvbiI6ItCf0L7Qv9C+0LvQvdC10L3QuNC1INCx0LDQu9Cw0L3RgdCwIiwib3JkZXJfaWQiOiJsM2Q2d1VvR3Rlc3QiLCJwdWJsaWNfa2V5IjoiaTc0MDQ0MTgyODM5Iiwic2FuZGJveCI6IjEiLCJzZXJ2ZXJfdXJsIjoiaHR0cDpcL1wvYmFuay10a2FuaS5yaGNsb3VkLmNvbVwvY2FsbGJhY2siLCJ2ZXJzaW9uIjoiMyJ9"/>
                        <input id="liq-pay-signature" type="hidden" name="signature"
                               value="DxXg8vXCVuw39G1Qvk8hmLyad6o="/>
                        <button type="submit" class="submit">Пополнить</button>
                    </form>


                    <p>Введите сумму:</p>

                    <div class="clearfix"></div>
                    <input id="header_money_amount" type="text" placeholder="350, 000">

                    <p>Пополните счет, с помощью этих банк-систем:</p>

                    <div class="clearfix"></div>
                    <div class="socialBankIcons">
                        <a href="#"><img src="/resources/images/visa.png" alt="visa"></a>
                        <a href="#"><img src="/resources/images/mastercard.png" alt="mastercard"></a>
                        <a href="#"><img src="/resources/images/privat24.png" alt="privat24"></a>
                        <a href="#"><img src="/resources/images/box.png" alt="box"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="adialog"></div>
<%--<div id="dialogs"></div>
<button onclick="ClearCookie();">ClearCookie</button>--%>

<script id="adialogTemplate" type="text/html">
    <div class="dialog">
        <div class="title" style="height: 32px;"></div>
        <div class="panel-buttons">
            <div>
                <div class="minimize" style="display:inline-block; padding-right: 7px;cursor: pointer;">_</div>
                <div class="expand" style="display:inline-block;cursor: pointer;padding-right: 10px;margin-top: 4px;"><i class="fa fa-clone fa-flip-horizontal" aria-hidden="true"></i></div>
                <div class="close" style="display:inline-block;cursor: pointer;opacity: 0.5;font-weight: 100;"><span>x</span></div>
            </div>
            <%--<i class="fa fa-clone" aria-hidden="true"></i>--%>
        </div>
        <div class="messages" style="overflow-y: scroll; height: 235px;"></div>
        <div>
            <textarea id="newMsg" style="width: 100%;position: absolute;bottom: 0; height:30px;" onkeypress="keyCodeAnalyse(event)"></textarea>
        </div>
    </div>
</script>