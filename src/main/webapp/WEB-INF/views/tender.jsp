<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 30.12.2015
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>GUP</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">

</head>
<body>

<header class="main-head">
    <div class="top-menu">
        <div class="top-menu-userSection">
            <div class="top-menu-userpic">
                <img src="/resources/img/reallySmallUserpic.png">
            </div>
            <div class="top-menu-username">
                <a href="#">
                    <c:choose>
                        <c:when test="${not empty profile.username}">
                            ${profile.username}
                        </c:when>
                        <c:otherwise>
                            Вася Пупкин
                        </c:otherwise>
                    </c:choose>
                </a>
            </div>
            <div class="header-mainMenu">
                <a href="/prioffice">Моя страница</a>
                <a href="/dialogues">Сообщения</a>
                <a href="/dialogues">Уведомления</a>
                <a href="/tender">Тендеры</a>
                <a href="/projectList?pageNumber=0">Проекты</a>
                <a href="/blog/56994ac6e4b0121bb0506b4a">Новости</a>
                <a href="#">Настройки</a>
                <a href="/logout">Выход</a>
                <div class="pageedit">
                    <a href="/edit-profile">Редактировать страницу</a>
                </div>
            </div>
        </div>
        <div class="top-menu-notifications">
            <div class="top-menu-messages">
                <img src="/resources/img/envelopeSmall.png">
            </div>
            <!--<div class="top-menu-actualMessageWrap">
                <img class="message-sender-userpic"src="">
                <div class="top-menu-incomingMessage">
                    <p>
                        <span class="top-menu-incomingMessage_date"></span>
                        Сообщение СообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщение
                    </p>
                    <a  class="top-menu-incomingMessage_answer" href="#">Ответить</a>
                </div>
            </div>
            ТВОРЧЕСКИЙ ПИЗДЕЦ, ДОДЕЛАТЬ! -->
            <div class="top-menu-notification">
                <img src="/resources/img/bellSmall.png">
            </div>

        </div>

        <div class="top-menu-userBallance">
            <div class="ballance">
                <a href="#">${balance/100}<span> грн</span></a>
            </div>
            <div class="ballanceAdd-wraper">
                <a href="#">Пополнить баланс</a>
            </div>
        </div>
    </div>
</header>


<section class="first-sec">
    <div class="logo-wrap">
        <a href="/index">
            <img src="/resources/img/logo-site.png">
        </a>
        <p class="logo-title">global ukrainian portal</p>
    </div>
    <div class="shop-wrap-right">
        <div class="shop-wrap">
            <a class="main-winStore" href="#"><img src="/resources/img/wins-icon.png"></a>
            <a class="main-googlePlay" href="#"><img src="/resources/img/goop-icon.png"></a>
            <a class="main-appStore" href="#"><img src="/resources/img/apps-icon.png"></a>
        </div>
        <div class="join-button-wrap">
            <div class="join-button">
                <a href="#" title="Вступить в организацию"><img src="/resources/img/join-button.png"></a>
            </div>
        </div>
    </div>
    <div class="tender-search-button-wrapper">
        <input type="text" placeholder="Поиск">
        <a href="">Найти<span class="main-search-button-icon"><img src="/resources/img/magnifire.png"></span></a>
        <img class="makeTenderPic" src="/resources/img/makeTenderPic.png">
        <img class="becomeAdoer" src="/resources/img/becomeAdoer.png">
        <div class="tender-search-filter-button"><img src="/resources/img/pointerTriangle.png"></div>

    </div>
</section>

<section>
    <div class="sec-wrap">
        <div class="partials-wrap">
            <div class="main-tender-wrap">
                <p>Тендеры</p>

                <div class="main-tenderPic-wrap">
                    <a href="#"><img src="/resources/img/hammertime.png"></a>
                </div>
                <nav class="main-tender-bottom-menu">
                    <a href="#" class="active-main-menu-link">Участвовать</a>
                    <a href="#">Исполнители</a>
                </nav>
            </div>
            <div class="main-project-wrap">
                <p>Проекты</p>

                <div class="main-projectPic-wrap">
                    <a href="#"><img src="/resources/img/circul.png"></a>
                </div>
                <nav class="main-project-bottom-menu">
                    <a href="#" class="active-main-menu-link">Реструктуризация</a>
                    <a href="#">Готовый прототип</a>
                    <a href="#">Проект на бумаге</a>
                    <a href="#">Ноу-Хау</a>
                </nav>
            </div>
            <div class="main-news-wrap">
                <p>Новости</p>

                <div class="main-newsPic-wrap">
                    <a href="#"><img src="/resources/img/yagazetko.png"></a>
                </div>
                <nav class="main-news-bottom-menu">
                    <a href="#" class="active-main-menu-link">Киев</a>
                    <a href="#">Львов</a>
                    <a href="#">Харьков</a>
                    <a href="#">Запорожье</a>
                </nav>
            </div>
        </div>
        <div class="main-slider-wrap">
            <ul class="bxslider">
                <li><img src="/resources/img/slider-item.png"/></li>
                <li><img src="/resources/img/slider-item1.png"/></li>
                <li><img src="/resources/img/slider-item2.png"/></li>
            </ul>
        </div>
    </div>
</section>


<section>
    <div class="tender-wrap">
            <div class="tender-tabs-wrap">
                <div class="tabs">
                    <ul style="margin-bottom: 50px;">
                        <li class="tender-tabs-title">ТЕНДЕРЫ</li>
                        <li class="tender-tabs-title">ИСПОЛНИТЕЛИ</li>
                    </ul>
                    <div>
                        <div class="tender-tabs-items-wrap">
                                <div class="tender-item-wrapper">
                                    <div class="tender-item-leftside">
                                        <div class="tender-pic-wrap">
                                            <img src="/resources/img/tender-example-pic.png">
                                        </div>
                                        <div class="tender-subpic-stuff">
                                            <p style="margin-top: 0px; display: inline-block;">Предложений:<span class="tender-proposal-count"></span></p>
                                            <p style="margin-top: 0px; display: inline-block; float: right;">Просмотров:<span class="tender-veiws"></span></p>
                                        </div>
                                    </div>
                                    <div class="tender-item-rightside">
                                        <div class="tender-item-header-wrap">
                                            <div class="tender-name">
                                                <p>Название тендера</p>
                                            </div>
                                            <div class="tender-item-info">
                                                <p class="tender-publish-date">Опубликовано:<span>22.10.16</span></p>
                                                <p class="tender-number">№<span>1234567893</span></p>
                                            </div>
                                        </div>
                                        <div class="tender-item-text">
                                            <p>Беспилотный летательный аппарат (БПЛА, также иногда сокращается как БЛА; в просторечии иногда используется название «беспилотник» или «дрон» (от англ. drone — трутень)) — летательный аппарат без экипажа на борту.[1]Создан для воздушной съёмки и наблюдения в реальном времени за наземными объектами.</p>
                                        </div>
                                        <div class="tender-item-subtext-stuff">
                                            <div class="tender-time-remain">
                                                <img src="/resources/img/alarm.png">
                                                <p class="tender-time">15</p>
                                            </div>
                                            <div class="tender-cost-wrap">
                                                <p><span class="tender-cost">00 000 000</span>$</p>
                                                <button class="tender-apply-for">Участвовать</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </div>
                    </div>
                   <!-- 2-nd tab-->
                    <div>
                        <div class="doer-items-wrap">
                            <div class="doer-wrapper">
                                <div class="doer-item-left">
                                    <div class="doer-userpic-wrap">
                                        <img src="/resources/img/doer-userpic.png">
                                    </div>
                                    <div class="doer-rating">
                                        <p>523</p>
                                    </div>
                                </div>


                                <div class="doer-item-mid">
                                    <div class="doer-name">
                                        <p>ФИО исполнителя</p>
                                    </div>
                                    <div class="doer-description">
                                        <p class="doer-description-title">Описание</p>
                                        <p class="doer-description-main">Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним. 	Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним.
                                            Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимо</p>
                                    </div>
                                </div>


                                <div class="doer-item-right">
                                    <div class="doer-info">
                                        <p class="doer-creation-date">Дата создания:<span>12.09.15</span></p>
                                        <p class="doer-update-date">Дата обновления:<span>12.09.15</span></p>
                                        <p class="doer-views-count">Просмотры:<span>129</span></p>
                                    </div>
                                    <div class="doer-likes-buttons-wrap">
                                        <img class="doer-like" src="/resources/img/doer-like-icon.png">
                                        <img class="doer-dislike" src="/resources/img/doer-dislike-icon.png">
                                    </div>
                                    <div class="doer-addToClient-button-wrap">
                                        <button class="doer-addToClient-button">Добавить в клиенты</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</section>
<!-- hiden stuff-->

<!--END hiden stuff-->
<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/prioffice.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/js/common.js"></script>


<!--END of libs-->

</body>
</html>