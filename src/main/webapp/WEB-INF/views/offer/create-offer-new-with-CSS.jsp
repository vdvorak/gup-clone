<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 05.11.2015
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Создание объявления</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <style>

        .dropdown-menu {
            min-width: 300px;
        }
        .dropdown-menu.columns-2 {
            min-width: 400px;
        }

        .dropdown-menu li a {
            padding: 5px 15px;
            font-size: 14px;
        }
        .multi-column-dropdown {
            list-style: none;
            margin: 0px;
            padding: 0px;
        }
        .multi-column-dropdown li a {
            display: block;
            clear: both;
            line-height: 1.428571429;
            color: #333;
            white-space: normal;
        }
        .multi-column-dropdown li a:hover {
            text-decoration: none;
            color: #262626;
            background-color: #999;
        }

    </style>
</head>
<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
    <h1 class="title-h1-blue text-center">Новое обьявление</h1>
    <a href="#" class="pull-right">Мои обьявления</a>

    <div class="clearfix"></div>

    <div class="container-fluid new-adv-box">
        <div class="row">
            <div class="col-xs-4">
                <label for="new-label-1">Заголовок<em>*</em></label>
            </div>
            <div class="col-xs-8">
                <input type="text" id="new-label-1" placeholder="Длина заголовка от 5 до 70 символов">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="categories-row">Рубрика<em>*</em></label>
            </div>
            <div id="categories-row" class="col-xs-8">
                <div id="category1-container" class="dropdown" style="display: inline-block;">
                    <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span
                            id="text-category1">Выберите категорию</span>
                        <span class="caret"></span></button>
                    <ul id="ul-category1" class="dropdown-menu">

                    </ul>
                </div>
                <div id="category2-container" class="dropdown" style="display: none">
                    <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span
                            id="text-category2">Выберите подкатегорию</span>
                        <span class="caret"></span></button>
                    <ul id="ul-category2" class="dropdown-menu">

                    </ul>
                </div>
                <div id="category3-container" class="dropdown" style="display: none">
                    <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span
                            id="text-category3">Выберите подкатегорию</span>
                        <span class="caret"></span></button>
                    <ul id="ul-category3" class="dropdown-menu">

                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="region-row">Регион<em>*</em></label>
            </div>
            <div id="region-row" class="col-xs-8">
                <div id="region-container" class="dropdown" style="display: inline-block">
                    <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span
                        id="text-region">Выберите область</span>
                    <span class="caret"></span></button>
                    <ul class="dropdown-menu  multi-column columns-2">
                        <div class="row">
                            <div class="col-sm-6">
                                <ul class="multi-column-dropdown">
                                    <li><a href="#" style="font-weight: bold">Вся Украина</a></li>
                                    <li><a href="#">Винницкая область</a></li>
                                    <li><a href="#">Волынская область</a></li>
                                    <li><a href="#">Донецкая область</a></li>
                                    <li><a href="#">Житомирская область</a></li>
                                    <li><a href="#">Закарпатская область</a></li>
                                    <li><a href="#">Ивано‑Франковская область</a></li>
                                    <li><a href="#">Киевская область</a></li>
                                    <li><a href="#">Кировоградская область</a></li>
                                    <li><a href="#">Крым</a></li>
                                    <li><a href="#">Луганская область</a></li>
                                    <li><a href="#">Львовская область</a></li>
                                </ul>
                            </div>
                            <div class="col-sm-6">
                                <ul class="multi-column-dropdown">
                                    <li><a href="#">Николаевская область</a></li>
                                    <li><a href="#">Одесская область</a></li>
                                    <li><a href="#">Полтавская область</a></li>
                                    <li><a href="#">Ровенская область</a></li>
                                    <li><a href="#">Сумская область</a></li>
                                    <li><a href="#">Тернопольская область</a></li>
                                    <li><a href="#">Харьковская область</a></li>
                                    <li><a href="#">Херсонская область</a></li>
                                    <li><a href="#">Хмельницкая область</a></li>
                                    <li><a href="#">Черкасская область</a></li>
                                    <li><a href="#">Черниговская область</a></li>
                                    <li><a href="#">Черновицкая область</a>
                                </ul>
                            </div>
                        </div>
                    </ul>
                </div>
                <div id="city-container" class="dropdown" style="display: none">
                    <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span
                            id="text-city">Выберите город</span>
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu  multi-column columns-2">
                        <div class="row">
                            <div class="col-sm-6">
                                <ul class="multi-column-dropdown">

                                </ul>
                            </div>
                            <div class="col-sm-6">
                                <ul class="multi-column-dropdown">

                                </ul>
                            </div>
                        </div>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="new-label-1">Цена<em>*</em></label>
            </div>
            <div class="col-xs-3">
                <select id="selection-price">
                    <option value="arranged">Договорная</option>
                    <option value="price">Цена</option>
                </select>
            </div>
            <div class="col-xs-3" style="display: none">
                <input type="number" id="offer-inpPrice" style="border: 4px solid rgb( 153, 204, 102); border-radius: 5px;">
            </div>
            <div class="col-xs-2" style="display: none">
                <select id="selection-currency">
                    <option value="UAH">UAH</option>
                    <option value="USD">USD</option>
                    <option value="EUR">EUR</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="new-label-3">Описание<em>*</em></label>
            </div>
            <div class="col-xs-8">
        <textarea name="" id="new-label-3" cols="30" rows="10"
                  placeholder="Длина описания от 50 до 4000 символов"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8 col-xs-offset-4">
                <p id="p-textlength" style="text-align: right;">Количество символов: 0</p>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                Срочное обьявление
                <input type="checkbox" id="new-label-check">
                <label for="new-label-check">Только для скоропортящихся продуктов</label>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                Разрешить бронь
                <input type="checkbox" id="reserve-checkbox">
            </div>
        </div>
        <div class="row file-browse-wrap">
            <div class="col-xs-4">Фотографии</div>
            <div class="col-xs-8">
                <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                    <input id="photoInput" type="file" style="display: none;" multiple="multiple" accept="image/*">
                </form>
                <button id="btn-offer-addImg" type="submit" class="file-browse" style="background-color:white"
                        data-toggle="tooltip" data-placement="right" data-original-title="Добавить изображение"><i
                        class="fa fa-plus"></i></button>
            </div>
        </div>
        <div class="row file-browse-wrap">
            <div class="col-xs-4"></div>
            <div id="drop_zone" class="col-xs-8">
                <ul>
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-4">
                <label for="inpVideo">Добавить видео</label>
            </div>
            <div class="col-xs-8">
                <input type="text" id="inpVideo">
            </div>
        </div>
        <h2 class="title-h2-blue text-center">Контакты</h2>

        <div class="row">
            <div class="col-xs-4">
                <label for="inpAuthor">Контактное лицо<em>*</em></label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpAuthor">
            </div>
            <div class="col-xs-2">
                <label for="inpAdress">Адрес</label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpAdress">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="inpEmail">E-mail<em>*</em></label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpEmail">
            </div>
            <div class="col-xs-2">
                <label for="inpSkype">Skype</label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpSkype">
            </div>
        </div>
        <div class="row row-telephone">
            <div class="col-xs-4">
                <label>Телефон</label>
            </div>
            <div class="col-xs-3">
                <input type="text">
            </div>
            <div id="btn-add-tel" class="col-xs-1" data-toggle="tooltip" data-placement="right" title="Добавить телефон"
                 onClick="addTelephone()">
                <img src="resources/images/pluse.png" alt="plus">
            </div>
        </div>
    </div>

    <div class="new-ad-btn">
        <%--<a href="#" class="btn btn-lg btn-success">Посмотреть</a>--%>
        <button id="btn-offer-save" class="btn btn-lg btn-primary">Сохранить</button>
    </div>

    <p>&nbsp;</p>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<sec:authorize var="loggedIn" access="isAuthenticated()"/>
<c:choose>
    <c:when test="${loggedIn}">
        <script src="/resources/js/autorizedHeader.js"></script>
    </c:when>
    <c:otherwise>
        <script src="/resources/js/anonymHeader.js"></script>
    </c:otherwise>
</c:choose>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script>
    $(document).ready(function () {
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
    })
</script>

<%--<nav class="navbar navbar-default navbar-fixed-top" role="navigation">--%>
<%--<div class="collapse navbar-collapse">--%>
<%--<ul class="nav navbar-nav">--%>
<%--<li><a href="#">Моя страница</a></li>--%>
<%--<li><a href="#">Друзья</a></li>--%>
<%--<li><a href="#">Сообщения</a></li>--%>
<%--</ul>--%>
<%--<div class="col-sm-4 col-md-4 pull-right">--%>
<%--<ul class="nav navbar-nav">--%>
<%--<li><a href="#">Вступить в организацию</a></li>--%>
<%--<li><a href="#">Баланс</a></li>--%>
<%--<li><a href="#">Укр/Рус</a></li>--%>
<%--<li><a href="/logout">Выход</a></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</nav>--%>

<%--<div class="container-fluid">--%>

<%--<!--category-->--%>
<%--<div class="row" style="padding: 10px;">--%>
<%--<div class="col-xs-3" style="padding-left: 5px; padding-right: 5px;">--%>
<%--<a><img src="/resources/images/logo.png"></a>--%>
<%--</div>--%>
<%--<div class="col-xs-3"--%>
<%--style="padding-left: 5px; padding-right: 5px; color: white; font-size: 25px;  margin-top: 30px; ">--%>
<%--ПОРТАЛ<br>РОЗВИТКУ<br>УКРАЇНИ--%>
<%--</div>--%>
<%--<div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">--%>

<%--</div>--%>
<%--<div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">--%>

<%--</div>--%>
<%--<div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">--%>

<%--</div>--%>
<%--</div>--%>
<%--<!--category-->--%>


<%--<!--search begin-->--%>

<%--<!--search end-->--%>


<%--<!--offers category-->--%>
<%--<div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">--%>
<%--<div class="col-xs-9" style="padding-left: 5px; padding-right: 5px; display: flex;">--%>
<%--<div class="dropdown">--%>
<%--<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">ДЛЯ РЕБЁНКА--%>
<%--</button>--%>
<%--<ul class="dropdown-menu" role="menu">--%>
<%--<div class="col-xs-12" style="padding-left: 5px; padding-right: 5px;">--%>
<%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Посмотреть все объявления</a>--%>
<%--</li>--%>
<%--<li role="presentation" class="divider"></li>--%>
<%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Детская одежда</a></li>--%>
<%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Детские коляски</a></li>--%>
<%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Детская мебель</a></li>--%>
<%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Игрушки</a></li>--%>
<%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Товары для школьников</a></li>--%>
<%--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Прочие детские товары</a></li>--%>
<%--</div>--%>

<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<!--offers category-->--%>

<%--<!-- begin -->--%>
<%--<div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">--%>


<%--<div class="col-xs-8" style="padding-left: 5px; padding-right: 5px;">--%>
<%--<div class="input-group">Заголовок--%>
<%--<input id="inptTitle" name="title" type="text" class="form-control input-sm"--%>
<%--placeholder="Не более 70 символов" required>--%>
<%--</div>--%>
<%--<!--multilevel category-->--%>
<%--<input id="category1inp" type="text" name="category1inp" style="visibility: hidden;">--%>
<%--<input id="category2inp" type="text" name="category2inp" style="visibility: hidden;">--%>
<%--<input id="category3inp" type="text" name="category3inp" style="visibility: hidden;">--%>

<%--<div class="container" style="width: 700px;">--%>
<%--<div class="navbar-header">--%>
<%--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">--%>
<%--<span class="sr-only">Toggle navigation</span>--%>
<%--<span class="icon-bar"></span>--%>
<%--<span class="icon-bar"></span>--%>
<%--<span class="icon-bar"></span>--%>
<%--</button>--%>
<%--</div>--%>
<%--<div class="collapse navbar-collapse">--%>
<%--<ul class="nav navbar-nav">--%>
<%--<li>--%>
<%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category1lvlText">Выберите категорию<b class="caret"></b></a>--%>
<%--<ul id="category1lvl" class="dropdown-menu multi-level">--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li id="container2lvl" style="visibility: hidden">--%>
<%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category2lvlText">Выберите подкатегорию<b class="caret"></b></a>--%>
<%--<ul id="category2lvl" class="dropdown-menu multi-level">--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li id="container3lvl" style="visibility: hidden">--%>
<%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category3lvlText">Выберите подкатегорию<b class="caret"></b></a>--%>
<%--<ul id="category3lvl" class="dropdown-menu multi-level">--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div><!--/.nav-collapse -->--%>
<%--</div>--%>
<%--<!--multilevel category-->--%>
<%--<div id="options">--%>

<%--</div>--%>

<%--<div id="description" class="input-group">Описание--%>
<%--<textarea id="offerDescription" required></textarea>--%>
<%--<div id="textLength"></div>--%>
<%--</div>--%>

<%--<div id="inputs" class="input-group"></div>--%>

<%--<div id="inptPrice" class="input-group element-hidden">Цена--%>
<%--<input  name="price" type="number" class="form-control input-sm" required>--%>
<%--</div>--%>
<%--<div id="selectCurrency" class="input-group element-hidden">Валюта--%>
<%--<select  name="currency">--%>
<%--<option>UAH</option>--%>
<%--<option>USD</option>--%>
<%--<option>EUR</option>--%>
<%--</select>--%>
<%--</div>--%>

<%--<div class="input-group">Разрешить бронь--%>
<%--<input id="inpReserved" type="checkbox" name="canBeReserved">--%>
<%--<span class="btn btn-info btn-block">--%>
<%--Забронировать даты--%>
<%--</span>--%>
<%--</div>--%>
<%--<div  class="input-group">Срочное объявление--%>
<%--<input id="inpUrgent" type="checkbox" name="urgent"> Только для быстро портящихся продуктов--%>
<%--</div>--%>

<%--<div class="input_fields_wrap">Номера телефонов--%>
<%--<button class="add_field_button">Добавить ещё номер</button>--%>
<%--<div><input id="phone1" type="text" name="mytext[]" required></div>--%>
<%--</div>--%>

<%--<div class="input-group">Skype--%>
<%--<input id="inptSkype" type="text" class="form-control input-sm" placeholder="Введите ваш логин в Skype">--%>
<%--</div>--%>

<%--<div class="input-group">Контактное лицо--%>
<%--<input id="inptAuthor" type="text" class="form-control input-sm" placeholder="Введите имя контактного лица">--%>
<%--</div>--%>
<%--<div class="input-group">E-mail--%>
<%--<input id="inptMail" type="email" class="form-control input-sm" placeholder="Введите email">--%>
<%--</div>--%>

<%--<div class="input-group">Добавить видео--%>
<%--<input id="inptVideo" type="text" class="form-control input-sm" placeholder="Ссылка на видео Youtube" pattern="(?:https?:\/\/)?(?:www\.)?youtu\.?be(?:\.com)?\/?.*(?:watch|embed)?(?:.*v=|v\/|\/)([\w\-_]+)\&?">--%>
<%--</div>--%>

<%--<input id="test" type="submit" value="Отправить Форму" class="btn btn-info btn-block" data-toggle="popover" data-trigger="focus" data-content="Выберите категорию">--%>

<%--&lt;%&ndash;<a id="test" tabindex="0" class="btn btn-lg btn-danger" role="button" data-toggle="popover" data-trigger="focus" title="Dismissible popover" data-content="And here's some amazing content. It's very engaging. Right?">Dismissible popover</a>&ndash;%&gt;--%>
<%--</div>--%>

<%--<div id="drop_zone">--%>
<%--<button id="addImg">Загрузить фото</button>--%>
<%--<form id="uploadProfilePhotoForm" enctype="multipart/form-data"--%>
<%--method="post" style="display:none">--%>
<%--<p><input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg" multiple></p>--%>
<%--</form>--%>

<%--<div class="imgBlock">--%>
<%--<!--uploaded images-->--%>
<%--</div>--%>
<%--Перетяните файлы сюда--%>
<%--</div>--%>
<%--<!-- city chosen -->--%>
<%--<input id="countryInp" type="text" name="country" style="visibility: hidden;">--%>
<%--<input id="areaInp" type="text" name="area" style="visibility: hidden;">--%>
<%--<input id="cityInp" type="text" name="city" style="visibility: hidden;">--%>

<%--<div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">--%>
<%--<div class="col-xs-4" style="padding-left: 5px; padding-right: 5px; background-color: antiquewhite;">--%>
<%--<div class="input-group">--%>

<%--<div class="col-xs-6" id="bs-example-navbar-collapse-1">--%>
<%--<ul class="nav navbar-nav">--%>
<%--<li class="dropdown">--%>
<%--<a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите область<b class="caret"></b></a>--%>
<%--<ul class="dropdown-menu multi-column columns-2">--%>
<%--<div id="regions" class="row">--%>
<%--<div class="col-sm-6">--%>
<%--<ul class="multi-column-dropdown">--%>
<%--<li><a role="menuitem" tabindex="-1" href="#"><b>Вся Украина</b></a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Винницкая область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Волынская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Донецкая область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Житомирская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Закарпатская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#" >Ивано‑Франковская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Киевская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Кировоградская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Крым</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Луганская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Львовская область</a></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<div class="col-sm-6">--%>
<%--<ul class="multi-column-dropdown">--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Николаевская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Одесская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Полтавская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Ровенская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Сумская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Тернопольская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Харьковская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Херсонская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Хмельницкая область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Черкасская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Черниговская область</a></li>--%>
<%--<li><a role="menuitem" tabindex="-1" href="#">Черновицкая область</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>

<%--<div class="col-xs-6" id="bs-example-navbar-collapse-2" style="visibility: hidden">--%>
<%--<ul class="nav navbar-nav">--%>
<%--<li class="dropdown">--%>
<%--<a id="chosenCity" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите город<b--%>
<%--class="caret"></b></a>--%>
<%--<ul class="dropdown-menu multi-column columns-2">--%>
<%--<div id="cities" class="row">--%>

<%--<div class="col-sm-6">--%>
<%--<ul id="cities1" class="multi-column-dropdown">--%>
<%--</ul>--%>
<%--</div>--%>

<%--<div class="col-sm-6">--%>
<%--<ul id="cities2" class="multi-column-dropdown">--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<!-- city chosen -->--%>
<%--</div>--%>

<%--<div id="floating-panel">--%>
<%--<input id="address" type="textbox" value="">--%>
<%--<input id="submit" type="button" value="Сохранить">--%>
<%--</div>--%>
<%--<div id="map" style="height: 50%"></div>--%>


<%--</div>--%>

<%--<!-- script references -->--%>
<%--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>--%>
<%--<script src="/resources/js/bootstrap.min.js"></script>--%>
<%--<script src="/resources/js/jquery.maskedinput.min.js"></script>--%>


<script>
    var imgsArr = {};
    var placeKey = '';
    var jsonCategory;
    var jsonSubcategory;
    var options;
    var parameters = [];
    var properties = [];
    var cities;

    // ---------------    LOAD RESOURCES    --------------------------//
    $(document).ready(function () {
        /*// Setup the dnd listeners.
         var dropZone = document.getElementById('drop_zone');
         dropZone.addEventListener('dragover', handleDragOver, false);
         dropZone.addEventListener('drop', handleFileSelect, false);

         function handleFileSelect(evt) {
         evt.stopPropagation();
         evt.preventDefault();

         var files = evt.dataTransfer.files; // FileList object.

         // files is a FileList of File objects. List some properties.
         for (var i = 0, f; f = files[i]; i++) {
         var formImg = new FormData($(this)[0]);
         var fd = new FormData();
         fd.append('file', f);
         $.ajax({
         type: "POST",
         url: "/api/rest/fileStorage/OFFERS/file/upload/",
         data: fd,
         async: false,
         cache: false,
         contentType: false,
         processData: false,

         success: function (data, textStatus, request) {
         var id = data.id;
         var isImage = f.type.substring(0, 5) === 'image';
         if (isImage) {
         imgsArr[id] = "image";
         $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none" onClick="onClickSetMainImg(' + '\'' + id + '\'' + ')">' +
         '<li><strong>' + f.name + '</strong></li>' +
         ' <li style="background-color: white">' +
         '<a rel="example_group"> ' +
         '<img alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
         '</a> <div onclick=\"deleteImg(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
         }
         }
         });


         }
         }

         function handleDragOver(evt) {
         evt.stopPropagation();
         evt.preventDefault();
         evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
         }*/

    });

    function countTextLength() {
        var counter = $("#p-textlength");
        var currentString = $("#new-label-3").val();
        counter.text("Количество символов: " + currentString.length);
        if (currentString.length <= 50) {  /*or whatever your number is*/
            counter.css("color", "red");
        } else {
            if (currentString.length > 500) {
                counter.css("color", "red");
            } else {
                counter.css("color", "green");
            }
        }
    }

    countTextLength();
    $("textarea").on('keyup', countTextLength);

    $.ajax({
        type: "GET",
        url: "/resources/json/cities.json",
        async: false,
        dataType: 'json',
        success: function (response) {
            cities = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchCategories.json",
        dataType: 'json',
        async: false,
        success: function (response) {
            jsonCategory = response;

            for (var i in jsonCategory) {
                var li = $('<li><a id="' + jsonCategory[i].id + '" href="#">' + jsonCategory[i].name + '</a></li>')
                        .click(selectCategoryLvl1);
                $('#ul-category1').append(li);
            }

        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchSubcategories.json",
        dataType: 'json',
        async: false,
        success: function (response) {
            jsonSubcategory = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchValues.json",
        dataType: 'json',
        async: false,
        success: function (response) {
            options = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/parameters.json",
        dataType: 'json',
        async: false,
        success: function (response) {
            parameters = response;
        }
    });

    // ---------------   END LOAD RESOURCES    --------------------------//


    // ---------------  HIDE PRICE AND CURRENCY ON PAGE ----------------//
    $('#selection-price').change(function(event) {
        var selectVal = $(event.currentTarget).val();
        if(selectVal === 'price') {
            $('#selection-currency').parent().css('display', 'inline-block');
            $('#offer-inpPrice').parent().css('display', 'inline-block');
        } else {
            $('#selection-currency').parent().css('display', 'none');
            $('#offer-inpPrice').parent().css('display', 'none');
        }

    });

    // --------------- END HIDE PRICE AND CURRENCY ON PAGE ----------------//


    // --------------------- MAIN FORM CONSTRUCTION ----------------------//

    $('#btn-offer-save').click(function () {

        var offer = {};
        offer.title = $("#new-label-1").val();
        offer.imagesIds = imgsArr;
        offer.canBeReserved = $("#reserve-checkbox").is(":checked");
        offer.address = {};
//        offer.address.coordinates = placeKey;
        offer.address.country = 'Украина';

//        if (!$('#inptPrice').hasClass("element-hidden")) {
//            offer.price = $('input[name="price"]').val();
//        }
//
//        if (!$('#selectCurrency').hasClass("element-hidden")) {
//            offer.currency = $('select[name="currency"]').val();
//        }
        var city = $('#text-city').text();
        if (city !== 'Выберите город' && city !== 'Все города') {
            offer.address.city = city;
        }

        var area = $('#text-region').text();
        if (area !== 'Выберите область') {
            offer.address.area = area;
        }

        if ($('#new-label-check').is(':checked')) {
            offer.urgent = true;
        }

        var phones = [];
        $.each($('.row-telephone').find('input'), function(index) {
            var val = $(this).val();
            if(val) phones.push(val);
        });

        var categoryResult = [];
        if($('#text-category1').text() !== 'Выберите категорию') categoryResult.push($('#text-category1').text());
        if($('#text-category2').text() !== 'Выберите подкатегорию' && $('#category2-container').css('display') !== 'none') categoryResult.push($('#text-category2').text());
        if($('#text-category3').text() !== 'Выберите подкатегорию' && $('#category3-container').css('display') !== 'none') categoryResult.push($('#text-category3').text());

        offer.categories = categoryResult;
        offer.active = true;
        offer.description = $('#new-label-3').val();
        offer.userInfo = {};
        offer.userInfo.skypeLogin = $('#inpSkype').val();
        offer.userInfo.contactName = $('#inptAuthor').val();
        offer.userInfo.email = $('#inpEmail').val();
        offer.videoUrl = $('#inpVideo').val();
        offer.userInfo.phoneNumbers = phones;

        $('#options').find('select').each(function () {
            var prop = {};
            prop.key = this.name;
            prop.value = this.value;
            properties.push(prop);
        });

        $('#inputs').find('input').each(function () {
            var prop = {};
            prop.key = this.name;
            prop.value = this.value;
            properties.push(prop);
        });

        offer.properties = properties;

        alert(JSON.stringify(offer));

        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(offer),
            success: function (response) {
                window.location.href = '/offer/' + response.id;
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    });

    // --------------------- END MAIN FORM CONSTRUCTION ----------------------//


    // -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//
    $('#btn-offer-addImg').click(function () {
        $('#photoInput').trigger('click');
    });

    $('#photoInput').change(function (event) {
        event.preventDefault();

        var files = event.currentTarget.files;
        for (var i = 0, f; f = files[i]; i++) {
            var fd = new FormData();
            fd.append('file', f);
            $.ajax({
                type: "POST",
                url: "/api/rest/fileStorage/OFFERS/file/upload/",
                data: fd,
                async: false,
                cache: false,
                contentType: false,
                processData: false,

                success: function (data, textStatus, request) {
                    var id = data.id;
                    var isImage = f.type.substring(0, 5) === 'image';
                    if (isImage) {
                        imgsArr[id] = "image";
                        appendImg(id);
                    }
                }
            });
        }
    });

    function appendImg(id) {
        $(".li-defaultIMG").css("display", "none");
        var cloneImg = $(".li-defaultIMG").clone()
                .removeClass('li-defaultIMG')
                .css("display", "inline-block");
        cloneImg.find('img')
                .attr("alt", "")
                .attr("src", '/api/rest/fileStorage/OFFERS/file/read/id/' + id)
                .attr("id", id);
        cloneImg.find('span')
                .click(deleteImg);
        cloneImg.appendTo('#drop_zone ul');
    }

    /*    $('#uploadProfilePhotoInput').change(function (event) {
     event.preventDefault();

     var files = event.currentTarget.files;
     for (var i = 0, f; f = files[i]; i++) {
     var formImg = new FormData($(this)[0]);
     var fd = new FormData();
     fd.append('file', f);
     $.ajax({
     type: "POST",
     url: "/api/rest/fileStorage/OFFERS/file/upload/",
     data: fd,
     async: false,
     cache: false,
     contentType: false,
     processData: false,

     success: function (data, textStatus, request) {
     var id = data.id;
     var isImage = f.type.substring(0, 5) === 'image';
     if (isImage) {
     imgsArr[id] = "image";
     $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none" onClick="onClickSetMainImg(' + '\'' + id + '\'' + ')">' +
     '<li><strong>' + f.name + '</strong></li>' +
     ' <li style="background-color: white">' +
     '<a rel="example_group"> ' +
     '<img alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
     '</a> <div onclick=\"deleteImg(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
     }
     }
     });
     }
     event.currentTarget.form.reset();
     });*/

    /*function deleteImg(idImg) {
     delete imgsArr[idImg];
     $.ajax({
     type: "POST",
     url: "/api/rest/fileStorage/OFFERS/file/delete/id/" + idImg,
     success: function (data, textStatus, request) {
     $('#' + idImg).remove();
     }
     });
     };*/

    function deleteImg() {
        var idImg = $(event.currentTarget).parent()
                .find('img')
                .attr('id');
        delete imgsArr[idImg];
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/OFFERS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                $('#' + idImg).parent().remove();

                var numberImg = $(".defaultIMG").find('img').length;
                if (numberImg < 2) {
                    $(".li-defaultIMG").css("display", "inline-block");
                }
            }
        });
    }

    function onClickSetMainImg() {
        var img = $(event.currentTarget);
        var id = img.attr("id");
        var isMain = img.hasClass("mainImg");
        var allImgs = $("#tender-img-block").find("img");
        for (var i = 0; i < allImgs.length; i++) {
            var curImg = $(allImgs[i]);
            if (curImg.hasClass("mainImg")) {
                curImg.removeClass("mainImg");
            }
        }
        if (!isMain) img.addClass("mainImg");

        for (var key in imgsArr) {
            if (imgsArr[key] === "pic1") {
                imgsArr[key] = "image";
            }
        }

        if (img.hasClass("mainImg")) {
            imgsArr[id] = "pic1";
        }
    }
    // -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


    //--------------------------- GOOGLE MAP API ---------------------------------------//

    /* function initMap() {

     var input = document.getElementById('address');

     var options = {
     types: []
     };

     var autocomplete = new google.maps.places.Autocomplete(input, options);

     google.maps.event.addListener(autocomplete, 'place_changed', function () {
     var place = autocomplete.getPlace(); //получаем место
     console.log(place);
     console.log(place.name);  //название места
     console.log(place.id);  //уникальный идентификатор места
     });

     var map = new google.maps.Map(document.getElementById('map'), {
     zoom: 17,
     center: {lat: 50.4501, lng: 30.523400000000038}
     });

     var geocoder = new google.maps.Geocoder();

     document.getElementById('submit').addEventListener('click', function() {
     geocodeAddress(geocoder, map);
     });
     }

     function geocodeAddress(geocoder, resultsMap) {
     var address = document.getElementById('address').value;
     geocoder.geocode({'address': address}, function(results, status) {
     placeKey = results[0].place_id;
     if (status === google.maps.GeocoderStatus.OK) {
     resultsMap.setCenter(results[0].geometry.location);
     var marker = new google.maps.Marker({
     map: resultsMap,
     position: results[0].geometry.location
     });
     } else {
     alert('Geocode was not successful for the following reason: ' + status);
     }
     });
     }*/

    //--------------------------- END GOOGLE MAP API ---------------------------------------//

    //--------------------------- REGIONS LIST --------------------------------------------//

    $('#region-container').find('li').click(selectRegion);

    function selectRegion(event) {
        event.preventDefault();

        var region = $(event.currentTarget).children('a').text();

        $('#text-region').text(region);
        $('#city-container').find('li').remove();
        $('#text-city').text('Выберите город');

        if(region === 'Вся Украина') {
            $('#city-container').css('display', 'none');
        } else {
            var citiesArr = cities[region];

            var parentBlock = $('#city-container').find('.multi-column-dropdown').first();
            var li = $('<li><a href="#" style="font-weight: bold">Все города</a></li>').click(selectCity);
            parentBlock.append(li);

            var numInColumn = citiesArr.length / 2 + (citiesArr.length % 2);
            for(var i = 0; i < citiesArr.length; i++) {
                parentBlock = (i + 2 <= numInColumn) ? $('#city-container').find('.multi-column-dropdown').first() : $('#city-container').find('.multi-column-dropdown').last();
                li = $('<li><a href="#">'+ citiesArr[i] +'</a></li>').click(selectCity);
                parentBlock.append(li);
            }
            $('#city-container').css('display', 'inline-block');
        }
    }

    function selectCity(event) {
        event.preventDefault();
        var city = $(event.currentTarget).children('a').text();
        $('#text-city').text(city);
    }

    //--------------------------- END REGIONS LIST --------------------------------------------//

    //----------------------------- PHONES LIST ----------------------------------------------//


    function addTelephone() {
        var curNumber = $('.row-telephone').length;
        if (curNumber < 3) {
            var imgDel = $('<a href="#" class="remove_field"><img src="/resources/img/minus.png" style="width: 20px; height: 20px; margin-left: 0px;"></a>').click(deleteTel);
            var row = $('.row-telephone').first().clone();
            row.children('#btn-add-tel').remove();
            row.find('label').parent().remove();
            var inputBlock = row.find('input').parent().addClass('col-xs-offset-4');
            imgDel.insertAfter(inputBlock);
            row.appendTo('.new-adv-box');
        }
    }

    function deleteTel(event) {
        event.preventDefault();
        $(event.currentTarget).parent().remove();

    }

    //----------------------------- END PHONES LIST ----------------------------------------------//

    //--------------------------------BEGIN CATEGORY-------------------------------------------------//
    function selectCategoryLvl1(event) {
        event.preventDefault();

        $('#ul-category2').html("");
        $('#ul-category3').html("");
        $('#category3-container').attr("style", "display: none");
        $('#text-category2').text("Выберите подкатегорию");
        $('#text-category3').text("Выберите подкатегорию");

        var a1 = $(event.currentTarget).children('a');
        var category1Id = a1.attr("id");
        var category1 = a1.text()
        $('#text-category1').text(category1);
        $('#category2-container').attr("style", "display: inline-block");
        var child1 = jsonCategory.filter(function (obj) {
            return obj.id === +category1Id; // Filter out the appropriate one
        })[0].children;
        for (var key in child1) {
            var li = $('<li><a id="' + child1[key].id + '" href="#">' + child1[key].name + '</a></li>')
                    .click(selectCategoryLvl2);
            $('#ul-category2').append(li);
        }
    }

    function selectCategoryLvl2(event) {
        event.preventDefault();

        $('#ul-category3').html("");
        $('#text-category3').text("Выберите подкатегорию");

        var a2 = $(event.currentTarget).children('a');
        var category2Id = a2.attr("id");
        var category2 = a2.text();
        $('#text-category2').text(category2);
        if (jsonSubcategory[category2Id]) {
            var child2 = jsonSubcategory[category2Id].children;
            $('#category3-container').attr("style", "display: inline-block");
            for (var key in child2) {
                var li = $('<li><a id="' + key + '" href="#">' + child2[key].label + '</a></li>')
                        .click(selectCategoryLvl3);
                $('#ul-category3').append(li);
            }
        }
    }

    function selectCategoryLvl3(event) {
        event.preventDefault();
        var a3 = $(event.currentTarget).children('a');
        category3Id = a3.attr("id");
        var category3 = a3.text();
        $('#text-category3').text(category3);
    }


    //--------------------------------END CATEGORY-------------------------------------------------//

</script>
<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"--%>
<%--async defer></script>--%>
</body>
</html>