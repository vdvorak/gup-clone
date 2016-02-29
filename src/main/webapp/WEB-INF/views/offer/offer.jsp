<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 02.11.2015
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Страница объявления</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</head>
<body>

<div class="container2">
    <ul class="page-navigation">
        <li><a href="#">Хлебные крошки</a></li>
        <li><a href="#">хлебные крошки</a></li>
        <li><a href="#">хлебные крошки</a></li>
        <li><a href="#">хлебные крошки</a></li>
        <li><a href="#">хлебные крошки</a></li>
    </ul>

    <h1 class="title-h1-blue">ЗАГОЛОВОК ОБЬЯВЛЕНИЯ</h1>

    <div class="offer-info-wrap">
        <div class="offer-info-box">
            <div class="reservation">
                <div class="col-xs-6">ЗАБРОНИРОВАТЬ</div>
                <div class="col-xs-6 text-right">28 000 грн. <i class="fa fa-lock"></i></div>
            </div>
            <div class="clearfix"></div>
            <div class="row">
                <div class="col-xs-5">
                    <a href="#" class="btn btn-block btn-success">Написать автору</a>
                    <div class="clearfix"></div>
                    <div class="contact">094 786 66 78
                        <br> 094 786 66 78</div>
                    <div class="clearfix"></div>
                    <div class="contact">Skype: Deptors <i class="fa fa-skype"></i></div>
                </div>
                <div class="col-xs-7">
                    Автор: Вася Пупкин
                    <div class="clearfix"></div>
                    <span class="author-rating">20</span>
                    <div class="clearfix"></div>
                    <a href="#">Все обьявления автора</a>
                </div>
            </div>
            <div class="clearfix"></div>
            <img src="images/map.jpg" width="420" alt="">
        </div>
        <div class="offer-info-slider"><img src="images/slider.jpg" width="450" alt=""></div>
    </div>


    <h2 class="title-h2-blue">Описание</h2>

    <div class="descr-text-box">
        <p>Практичный ноутбук с диагональю экрана <strong>17.3</strong> дюйма станет удачным выбором для домашнего использования и решения несложных задач. <strong>LENOVO G7080 (80FF00DAUA)</strong> обладает высоким разрешение 1600 х 900 пикселей, что наверняка придется по душе настоящим киноманам.</p>

        <p>За решение всех поставленных задач отвечает процессор <strong>Intel Pentium 3825U</strong> с частотой <strong>1.9 ГГц</strong>. Объем оперативной памяти составляет 4 Гб. Для хранения всей вашей информации выделено 500 Гб пространства на жестком диске.</p>

        <p>
            <strong>LENOVO G7080 (80FF00DAUA)</strong> оснащен разъемами <strong>VGA, HDMI, USB</strong> версии 2.0 и 3.0. Для беспроводного подключения используется модуль <strong>Wi-Fi и Bluetooth.</strong>
        </p>
    </div>

    <h2 class="title-h2-blue text-center">САМЫЕ ПОСЛЕДНИЕ ДОБАВЛЕНИЯ</h2>

    <ul class="notice-box">
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
    </ul>
</div>

<%--<div class="container-fluid">--%>

    <%--<!--left-->--%>
    <%--<div class="col-sm-6">--%>

        <%--<div class="row">--%>
            <%--<div class="col-xs-4">--%>
                <%--<div id="breadcrumbs" class="row">--%>

                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="row">--%>
            <%--<div class="col-xs-4">--%>
                <%--<div class="row">--%>
                    <%--<h2 style="color: #ff8e35;">${offer.title}</h2>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="row">--%>
            <%--<div class="col-xs-8">--%>
                <%--<div class="row" style="color: #495d9a;">--%>
                    <%--<p id="createDate" class="pull-left"></p>--%>

                    <%--<p class="pull-right">Просмотров: ${offer.views}</p>--%>
                <%--</div>--%>
                <%--<div class="row panel" style="color: #495d9a; background-color: #68a8de; padding: 5px;">--%>
                    <%--<span style="color: white;">--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${offer.currency == 'UAH'}">--%>
                                <%--${offer.price} грн.--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${offer.currency == 'USD'}">--%>
                                <%--${offer.price} дол.--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${offer.currency == 'EUR'}">--%>
                                <%--${offer.price} евро.--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--${offer.price}--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</span><a class="pull-right">ЗАБРОНИРОВАТЬ</a>--%>
                <%--</div>--%>

                <%--<div id="options" class="row panel"></div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="row">--%>
            <%--<div class="col-xs-12">--%>

                <%--<div class="row">--%>
                    <%--<div class="col-xs-8">--%>
                        <%--<div class="row panel" style="color: #495d9a; background-color: #68a8de;">--%>
                            <%--<a class="btn" style="color: #ffffff;">Написать автору</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div id="authorUserName" class="col-xs-4" style="color: #495d9a;">--%>

                        <%--<c:choose>--%>
                            <%--<c:when test="${not empty profile.username}">--%>
                                <%--${profile.username}--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--Имя автора не указано--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                    <%--<div class="col-xs-8">--%>
                        <%--<div class="row panel" style="color: #ffffff; background-color: #68a8de;">--%>
                    <%--<span style="display: inline-block;">--%>
                        <%--<p id="contactPhones" style="margin: 0 10px 5px;" onclick="showContact()">XXX XXX XX XX &nbsp;&nbsp;&nbsp;--%>
                            <%--<u>Показать телефон</u></p>--%>

                    <%--</span>--%>
                            <%--<span class="fa fa-phone fa-3x pull-right"--%>
                                  <%--style="padding-right: 10px; padding-top: 5px"></span>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-xs-4">--%>
                        <%--<p class="fa fa-bullhorn fa-4x" style="color: orange;"></p>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                    <%--<div class="col-xs-8">--%>
                        <%--<div class="row panel" style="color: #495d9a; background-color: #68a8de;">--%>
                            <%--<a class="btn" style="color: #fdfffd;">Скайп</a>--%>
                            <%--<span class="fa fa-skype fa-2x pull-right"--%>
                                  <%--style="color: white; padding-right: 10px; padding-top: 5px"></span>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-xs-4">--%>
                        <%--<a style="color: #495d9a;">Объявления автора</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="row">--%>
            <%--<c:if test='${offer.address.coordinates != ""}'>--%>
                <%--<iframe width="300" height="225" frameborder="0" style="border:0"--%>
                        <%--src="https://www.google.com/maps/embed/v1/place?q=place_id:${offer.address.coordinates}&key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww"--%>
                        <%--allowfullscreen></iframe>--%>
            <%--</c:if>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<!--/left-->--%>

    <%--<!--right-->--%>
    <%--<div class="col-sm-6" style=" margin-top: 90px;">--%>

        <%--<div class="row">--%>
            <%--<c:if test="${offer.urgent}">--%>
            <%--<span>--%>
            <%--Срочно--%>
            <%--</span>--%>
            <%--</c:if>--%>
        <%--</div>--%>

        <%--<div class="panel panel-default" style="background-color: transparent; border-color: transparent;">--%>
            <%--<div class="panel-body">--%>
                <%--<c:if test="${offer.imagesIds.keySet().size() < 1}">--%>
                    <%--<li><img src="/resources/images/no_photo.jpg"></li>--%>
                <%--</c:if>--%>
                <%--<c:if test="${offer.imagesIds.keySet().size() > 0}">--%>
                    <%--<ul class="pgwSlideshow">--%>
                        <%--<c:forEach items="${offer.imagesIds.keySet()}" var="id">--%>
                            <%--<li><img src="/api/rest/fileStorage/OFFERS/file/read/id/${id}"></li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</c:if>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<!-- Begin Social buttons html-->--%>
    <%--<jsp:include page="/WEB-INF/templates/social-button-html.jsp"/>--%>
    <%--<!-- End Social buttons html -->--%>


    <%--<div class="row">--%>
        <%--<div class="col-xs-8">--%>
            <%--<div class="row panel" style="color: #495d9a; background-color: #68a8de; padding: 5px;">--%>
                <%--<textarea id="offerDescription" style="resize: none; width: 700px; height: 350px;"--%>
                          <%--readonly>${offer.description}</textarea>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div id="video">--%>

    <%--</div>--%>
    <%--<!--/right-->--%>
<%--</div>--%>

<!--/container-fluid-->
<!-- script references -->
<%--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>--%>
<%--<script src="/resources/js/bootstrap.min.js"></script>--%>
<%--<script src="/resources/js/pgwslideshow.js"></script>--%>
<%--<script src="/resources/js/moment-with-locales.js"></script>--%>

<%--<!-- Begin Social buttons js -->--%>
<%--<jsp:include page="/WEB-INF/templates/social-buttons-js.jsp"/>--%>
<%--<!-- End Social buttons js -->--%>

<%--<script>--%>

    <%--var jsonCategory = '';--%>
    <%--var jsonSubcategory = '';--%>
    <%--var parameters = '';--%>
    <%--var options = '';--%>

    <%--$.ajax({--%>
        <%--type: "GET",--%>
        <%--url: "/resources/json/searchCategories.json",--%>
        <%--dataType: "json",--%>
        <%--async: false,--%>
        <%--success: function (response) {--%>
            <%--jsonCategory = response;--%>
        <%--}--%>
    <%--});--%>

    <%--$.ajax({--%>
        <%--type: "GET",--%>
        <%--url: "/resources/json/searchValues.json",--%>
        <%--dataType: "json",--%>
        <%--async: false,--%>
        <%--success: function (response) {--%>
            <%--options = response;--%>
        <%--}--%>
    <%--});--%>


    <%--$.ajax({--%>
        <%--type: "GET",--%>
        <%--url: "/resources/json/parameters.json",--%>
        <%--dataType: "json",--%>
        <%--async: false,--%>
        <%--success: function (response) {--%>
            <%--parameters = response;--%>
        <%--}--%>
    <%--});--%>

    <%--$.ajax({--%>
        <%--type: "GET",--%>
        <%--url: "/resources/json/searchSubcategories.json",--%>
        <%--dataType: "json",--%>
        <%--async: false,--%>
        <%--success: function (response) {--%>
            <%--jsonSubcategory = response;--%>
        <%--}--%>
    <%--});--%>


    <%--var cities;--%>
    <%--$.ajax({--%>
        <%--url: '/resources/json/cities.json',--%>
        <%--dataType: 'json',--%>
        <%--async: false,--%>
        <%--success: function (response) {--%>
            <%--cities = response;--%>
        <%--}--%>
    <%--});--%>

    <%--var offerProperties = ${properties};--%>
    <%--for (var i in offerProperties){--%>
        <%--var key = offerProperties[i].key;--%>
        <%--var value = offerProperties[i].value;--%>
        <%--var key_ru = '';--%>
        <%--var value_ru = '';--%>
        <%--for (var j in parameters){--%>
            <%--if (parameters[j]["parameter"]["key"] === key){--%>
                <%--key_ru = parameters[j]["parameter"]["label"];--%>
                <%--if(parameters[j]["parameter"]["type"] === 'input'){--%>
                <%--value_ru = value;--%>
                <%--}--%>
            <%--break;--%>
            <%--}--%>
        <%--}--%>
        <%--if (value_ru ===''){--%>
            <%--for (var m in options){--%>
               <%--if(options[m]["k"][key] !== undefined && options[m]["v"][value] !== undefined){--%>
                   <%--value_ru =  options[m]["v"][value];--%>
               <%--}--%>
            <%--}--%>
        <%--}--%>
        <%--if (value_ru!=='Цена' && value_ru!==''){--%>
            <%--$('#options').append('<div class="col-xs-6">'+key_ru+'</div><div class="col-xs-6">'+value_ru+'</div>')--%>
        <%--}--%>

    <%--}--%>

    <%--$(function () {--%>
        <%--var region = $('#chosenRegion').text();--%>
        <%--if (region === 'Выберите область' || region === 'Вся Украина') {--%>
            <%--$('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");--%>
        <%--}--%>

        <%--$('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();--%>
        <%--$('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');--%>

        <%--if (cities[region] !== undefined) {--%>
            <%--for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {--%>
                <%--$('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');--%>
            <%--}--%>
            <%--for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {--%>
                <%--$('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');--%>
            <%--}--%>
        <%--}--%>

        <%--$('#cities').find('li').click(function () {--%>
                    <%--var city = $(this).text();--%>
                    <%--$('#chosenCity').text(city);--%>
                <%--}--%>
        <%--);--%>
    <%--});--%>

    <%--$('#regions').find('li').click(function () {--%>
                <%--var region = $(this).text();--%>
                <%--$('#chosenRegion').text(region);--%>
                <%--if (region !== 'Вся Украина') {--%>
                    <%--$('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");--%>
                <%--} else {--%>
                    <%--$('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");--%>
                <%--}--%>

                <%--$('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();--%>
                <%--$('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');--%>

                <%--if (cities[region] !== undefined) {--%>
                    <%--for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {--%>
                        <%--$('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');--%>
                    <%--}--%>

                    <%--for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {--%>
                        <%--$('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');--%>
                    <%--}--%>
                <%--}--%>
                <%--$('#chosenCity').text('Выберите город');--%>
                <%--$('#cities').find('li').click(function () {--%>
                            <%--var city = $(this).text();--%>
                            <%--$('#chosenCity').text(city);--%>
                        <%--}--%>
                <%--);--%>
            <%--}--%>
    <%--);--%>


    <%--var goSearch = function () {--%>
        <%--var getParams = {};--%>
        <%--getParams.minPrice = $('#minPrice').val();--%>
        <%--getParams.maxPrice = $('#maxPrice').val();--%>
        <%--getParams.search = $('#search').val();--%>
        <%--var chosenRegion = $('#chosenRegion').text();--%>
        <%--if (chosenRegion === 'Выберите область' || chosenRegion === 'Вся Украина') {--%>
            <%--chosenRegion = "";--%>
        <%--}--%>
        <%--getParams.chosenRegion = chosenRegion;--%>
        <%--var chosenCity = $('#chosenCity').text();--%>
        <%--if (chosenCity === 'Выберите город') {--%>
            <%--chosenCity = "";--%>
        <%--}--%>
        <%--getParams.chosenCity = chosenCity;--%>
        <%--window.location.href = "/offers/1?" + $.param(getParams);--%>

    <%--};--%>
<%--</script>--%>

<%--<script>--%>
    <%--var phonesSet = '${offer.userInfo.phoneNumbers}'.substring(1, '${offer.userInfo.phoneNumbers}'.length - 1).split(',');--%>

    <%--$(document).ready(function () {--%>

        <%--var id = '${offer.authorId}';--%>
        <%--var videoUrl = '${offer.videoUrl}';--%>
        <%--var idUser;--%>
        <%--var time = '${offer.createdDate}';--%>
        <%--time = new Date(parseInt(time));--%>
        <%--var now = moment(time).locale("ru"); //using of moment.js library--%>
        <%--$('#createDate').text("Опубликовано: " + now.format('LLL'));--%>

        <%--if (videoUrl !== '') {--%>
            <%--var videoKey = videoUrl.split('=')[1];--%>
            <%--$('#video').append('<iframe width="560" height="315" src="https://www.youtube.com/embed/' + videoKey + '" frameborder="0" allowfullscreen></iframe>');--%>
        <%--}--%>

        <%--$('.pgwSlideshow').pgwSlideshow();--%>

    <%--});--%>

    <%--function showContact() {--%>
        <%--var phoneList = ' ';--%>
        <%--for (var i = 0; i < phonesSet.length; i++) {--%>
            <%--phoneList = phoneList += phonesSet[i] + "\n";--%>
        <%--}--%>
        <%--$('#contactPhones').text(phoneList);--%>
    <%--}--%>

    <%--var breadcrumbs = JSON.parse('${offer.categories}');--%>

    <%--if (breadcrumbs[0] !== undefined) {--%>
        <%--for (var i = 0; i < jsonCategory.length; i++) {--%>
            <%--if (jsonCategory[i].id === breadcrumbs[0]) {--%>
                <%--$('#breadcrumbs').append('<span><a href="#">' + jsonCategory[i].name + '</a>' + "/" + '</span>');--%>

                <%--if (breadcrumbs[1] !== undefined) {--%>
                    <%--for (var m in jsonCategory[i].children) {--%>
                        <%--if (jsonCategory[i].children[m].id == breadcrumbs[1]) {--%>
                            <%--$('#breadcrumbs').append('<span><a href="#">' + jsonCategory[i].children[m].name + '</a>' + "/" + '</span>');--%>
                        <%--}--%>
                    <%--}--%>
                <%--}--%>
                <%--if (breadcrumbs[2] !== undefined) {--%>
                    <%--var obj1 = breadcrumbs[1] + "";--%>
                    <%--var obj2 = breadcrumbs[2] + "";--%>
                    <%--$('#breadcrumbs').append('<span><a href="#">' + jsonSubcategory[obj1].children[obj2].label + '</a>' + '</span>');--%>
                <%--}--%>
            <%--}--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

</body>
</html>