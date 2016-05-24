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

    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/dropdown-multicolumn.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

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

<div id="offer-container" class="container2">


    <h1 class="title-h1-blue text-center">Новое обьявление</h1>
    <%--<a href="#" class="pull-right">Мои обьявления</a>--%>

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
            <div class="col-xs-8">
                <div id="categories-row">
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
        </div>
        <div id="offer-price-row" class="row" style="display: none;">

            <div class="col-xs-4">
                <label for="offer-inpPrice">Цена<em>*</em></label>
            </div>
            <div id="price-options" class="col-xs-3">
                <select id="selection-price" class="prop" name="price">

                </select>
            </div>
            <div class="col-xs-3" style="display: none">
                <input id="offer-inpPrice" name="price" type="number" min="0" max="2147483648"
                       style="border: 4px solid #9c6; border-radius: 5px;">
            </div>
            <div class="col-xs-2" style="display: none">
                <select id="selection-currency" name="currency" class="prop">
                    <option>UAH</option>
                    <option>USD</option>
                    <option>EUR</option>
                </select>
            </div>

        </div>


        <div id="offer-options-row" class="row" style="display: none">
            <div class="col-xs-4">
                <label for="other-options">Дополнительно</label>
            </div>
            <div id="other-options" class="col-xs-8">

            </div>
        </div>

        <div class="row">
            <div class="col-xs-4">
                <label for="region-row">Регион<em>*</em></label>
            </div>
            <div class="col-xs-8">
                <div id="region-row">
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
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="new-label-3">Описание<em>*</em></label>
            </div>
            <div class="col-xs-8">
        <textarea name="" id="new-label-3" height=""
                  placeholder="Длина описания от 50 до 4000 символов"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8 col-xs-offset-4">
                <p id="p-textlength">Количество символов: 0</p>
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
            <div id="drop_zone" class="col-xs-8" style="min-height: 210px;">
                <ul id="offer-img-block" class="ul-img-container ul-img-container-green">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no-photo-available-md.png" alt="defaultIMG">
                    </li>
                </ul>
                <p>Drop Image To Upload</p>
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
                <label for="map">Адрес</label>
            </div>
            <div class="col-xs-8">
                <div id="floating-panel">
                    <input id="address" type="text">
                    <button id="btn-save-adress">Сохранить</button>
                </div>
                <div id="map" class="offer-map"></div>
            </div>

        </div>

        <div class="row">
            <div class="col-xs-4">
                <label for="inpAuthor">Контактное лицо</label>
            </div>
            <div class="col-xs-5">
                <input type="text" id="inpAuthor">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="inpEmail">E-mail</label>
            </div>
            <div class="col-xs-5">
                <input type="text" id="inpEmail">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="inpSkype">Skype</label>
            </div>
            <div class="col-xs-5">
                <input type="text" id="inpSkype">
            </div>
        </div>
        <div class="row row-telephone">
            <div class="col-xs-4">
                <label>Телефон<em>*</em></label>
            </div>
            <div class="col-xs-5">
                <input type="text">
            </div>
            <div id="btn-add-tel" class="col-xs-1" data-toggle="tooltip" data-placement="right"
                 title="Добавить телефон"
                 onClick="addTelephone()">
                <img src="resources/images/pluse.png" alt="plus">
            </div>
        </div>
    </div>

    <%--<div class="new-ad-btn">--%>
    <div class="row">
        <%--<a href="#" class="btn btn-lg btn-success">Посмотреть</a>--%>
        <div class="col-xs-4 col-xs-offset-8">
            <button id="btn-offer-save">Сохранить</button>
        </div>
    </div>

    <p>&nbsp;</p>

</div>

<div id="gup-validator-popup" class="gup-popup-overlay">
    <div class="gup-popup">
        <h2>Ошибка создания объявления</h2>
        <a class="popup-close" href="#">&times;</a>
        <div class="popup-content">

        </div>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>
<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script>
    $(document).ready(function () {
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
    })
</script>

<script src="/resources/js/create-offer.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
        async defer></script>
</body>
</html>