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
                <a href="#">Моя страница</a>
                <a href="#">Сообщения</a>
                <a href="#">Уведомления</a>
                <a href="#">Тендеры</a>
                <a href="#">Проекты</a>
                <a href="#">Новости</a>
                <a href="#">Настройки</a>
                <a href="#">Выход</a>

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
        <img src="/resources/img/logo-site.png">

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
    <div class="main-search-button-wrapper">
        <input type="text" placeholder="Поиск">
        <a href="">Найти<span class="main-search-button-icon"><img src="/resources/img/magnifire.png"></span></a>
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
    <div class="prioffice-wrap">
        <!--LEFT_SIDE-->
        <!-- ЧЕРТИ ЧТО ДОПИСАТЬ И ДОПОЛНИТЬ ИНФОРМАЦИЕЙ-->
        <div class="prioffice-left">
            <div class="prioffice-user">петров василий</div>
            <div class="prioffice-userpic">
                <img src="/resources/img/defaultlogo.png">
            </div>
            <div class="prioffice-mysettings">
                <div class="prioffice-mysettings-title">Мои настройки</div>
                <div class="prioffice-mysettings-menu-wrap">
                    <img class="prioffice-mysettings_open" src="/resources/img/strippeddownbutton.png">
                </div>

                <div id="accordion">
                    <p>Кому видна моя страница</p>

                    <div>
                        Список 1
                        <input type="checkbox"><br>
                        Список 2
                        <input type="checkbox"><br>
                        Список 3
                        <input type="checkbox"><br>
                    </div>
                    <p>Параметры настройки</p>

                    <div>
                        <p>что-то </p>
                    </div>
                    <p>Параметры настройки</p>

                    <div>
                        <p>что-то </p>
                    </div>
                    <p>Параметры настройки</p>

                    <div>
                        <p>что-то </p>
                    </div>
                </div>
                <div class="prioffice-mysettings-submit">
                    <a href="#">Сохранить</a>
                </div>
                <div class="prioffice-mysettings-menu-wrap">
                    <img class="prioffice-mysettings_close" src="/resources/img/strippedupbutton.png">
                </div>
            </div>
        </div>


        <!-- RIGHT_SIDE-->

        <div class="prioffice-right">
            <div class="prioffice-tabs-wrap">
                <div class="tabs">
                    <ul>
                        <li class="prioffice-tabs-title">Сообщения</li>
                        <li class="prioffice-tabs-title">Уведомления</li>
                    </ul>
                    <div class="prioffice-tabs-items-wrap">
                        <div>
                            <div class="prioffice-tabs-items">
                                <div class="prioffice-tabs-items-pic">
                                    <img src="/resources/img/unknownuser-pic.png">
                                </div>
                                <p class="prioffice-tabs-items-text">Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>

                                <p class="prioffice-tabs-items-status">Непрочитано</p>
                            </div>
                            <div class="prioffice-tabs-items">
                                <div class="prioffice-tabs-items-pic">
                                    <img src="/resources/img/unknownuser-pic.png">
                                </div>
                                <p class="prioffice-tabs-items-text">Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>

                                <p class="prioffice-tabs-items-status">Непрочитано</p>
                            </div>
                            <div class="prioffice-tabs-items">
                                <div class="prioffice-tabs-items-pic">
                                    <img src="/resources/img/tendersmall-icon.png">
                                </div>
                                <p class="prioffice-tabs-items-text">Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>

                                <p class="prioffice-tabs-items-status">Непрочитано</p>
                            </div>
                            <div class="prioffice-tabs-items-return">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                        <div>Второе содержимое</div>
                    </div>
                </div>
            </div>

            <div class="myitems-wrapper">
                <div class="myitems-row">
                    <div class="myitems-tenders">
                        <div class="myitems-tenders-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-tenders-wrap-icon" src="/resources/img/tendersmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext">Мои тендеры</p>
                                </div>
                                <img class="prioffice-close-founds-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-tenders-items">
                                <p>Заголовок тендера 1</p>

                                <p>Заголовок тендера 1</p>

                                <p>Заголовок тендера 1</p>
                            </div>

                            <div class="myitems-tenders-footer">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                    </div>
                    <div class="myitems-projects">
                        <div class="myitems-projects-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-projects-wrap-icon" src="/resources/img/projectsmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext">Мои проекты</p>
                                </div>
                                <img class="prioffice-close-founds-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-projects-items">
                                <p>Заголовок проекта 1</p>

                                <p>Заголовок проекта 1</p>

                                <p>Заголовок проекта 1</p>
                            </div>
                            <div class="myitems-projects-footer">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="myitems-row">
                    <div class="myitems-news">
                        <div class="myitems-news-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-news-wrap-icon" src="/resources/img/newssmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext">Мои новости</p>
                                </div>
                                <img class="prioffice-close-founds-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-news-items">
                                <p>Новость 1</p>

                                <p>Новость 2</p>

                                <p>Новость 3</p>
                            </div>
                            <div class="myitems-news-footer">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                    </div>
                    <div class="myitems-founds">
                        <div class="myitems-founds-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-founds-wrap-icon" src="/resources/img/ballancesmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext"> Мой баланс</p>
                                </div>
                                <img class="prioffice-close-founds1-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-founds-items">
                                <p>Новость 1</p>

                                <p>Новость 2</p>

                                <p>Новость 3</p>
                            </div>
                            <div class="myitems-founds-footer">
                                <a href="#"><img src="/resources/img/strippeddownbuttonsmall.png"></a>
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