<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 02.11.2015
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Страница объявления</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">

    <link href="/resources/css/com.css" rel="stylesheet">
    <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">


<div class="container-fluid">

    <!--search-->
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
        <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
            <input id="search" type="text" class="form-control" placeholder="   Строка поиска" value="${search}"
                   style="background-image: url(http://3.bp.blogspot.com/-aJoIaNi5n0w/T4iXxkJvIiI/AAAAAAAAAHc/Umn8urN9rq4/s1600/spyglass.png); background-repeat: no-repeat;">
        </div>
        <div class="form-group">
            <div class="col-xs-2">
                <input class="form-control" id="minPrice" type="text" placeholder="Цена от">
            </div>
            <div class="col-xs-2">
                <input class="form-control" id="maxPrice" type="text" placeholder="Цена до">
            </div>
        </div>
        <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
            <div class="input-group">

                <div class="col-xs-6" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите
                                область<b class="caret"></b></a>
                            <ul class="dropdown-menu multi-column columns-2">
                                <div id="regions" class="row">
                                    <div class="col-sm-6">
                                        <ul class="multi-column-dropdown">
                                            <li><a role="menuitem" tabindex="-1" href="#"><b>Вся Украина</b></a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Винницкая область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Волынская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Донецкая область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Житомирская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Закарпатская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Ивано‑Франковская область</a>
                                            </li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Киевская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Кировоградская область</a>
                                            </li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Крым</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Луганская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Львовская область</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-6">
                                        <ul class="multi-column-dropdown">
                                            <li><a role="menuitem" tabindex="-1" href="#">Николаевская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Одесская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Полтавская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Ровенская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Сумская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Тернопольская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Харьковская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Херсонская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Хмельницкая область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Черкасская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Черниговская область</a></li>
                                            <li><a role="menuitem" tabindex="-1" href="#">Черновицкая область</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </ul>
                        </li>
                    </ul>
                </div>

                <div class="col-xs-6" id="bs-example-navbar-collapse-2">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a id="chosenCity" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите город<b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu multi-column columns-2">
                                <div id="cities" class="row">

                                    <div class="col-sm-6">
                                        <ul id="cities1" class="multi-column-dropdown">
                                        </ul>
                                    </div>

                                    <div class="col-sm-6">
                                        <ul id="cities2" class="multi-column-dropdown">
                                        </ul>
                                    </div>
                                </div>
                            </ul>
                        </li>
                    </ul>
                </div>


                <span class="input-group-btn">
                    <button class="btn btn-warning btn-sm" onclick="goSearch()">
                        Найти
                    </button>
                </span>
            </div>
        </div>
        <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
            <a href="/create-offer">
                <div class="btn btn-info">ПОДАТЬ ОБЪЯВЛЕНИЕ +</div>
            </a>
        </div>
    </div>
    <!--search-->


    <!--left-->
    <div class="col-sm-6">

        <div class="row">
            <div class="col-xs-4">
                <div id="breadcrumbs" class="row">

                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-4">
                <div class="row">
                    <h2 style="color: #ff8e35;">${offer.title}</h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-8">
                <div class="row" style="color: #495d9a;">
                    <p id="createDate" class="pull-left"></p>

                    <p class="pull-right">Просмотров: ${offer.views}</p>
                </div>
                <div class="row panel" style="color: #495d9a; background-color: #68a8de; padding: 5px;">
                    <span style="color: white;">
                        <c:choose>
                            <c:when test="${offer.currency == 'UAH'}">
                                ${offer.price} грн.
                            </c:when>
                            <c:when test="${offer.currency == 'USD'}">
                                ${offer.price} дол.
                            </c:when>
                            <c:when test="${offer.currency == 'EUR'}">
                                ${offer.price} евро.
                            </c:when>
                            <c:otherwise>
                                ${offer.price}
                            </c:otherwise>
                        </c:choose>
                    </span><a class="pull-right">ЗАБРОНИРОВАТЬ</a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">

                <div class="row">
                    <div class="col-xs-8">
                        <div class="row panel" style="color: #495d9a; background-color: #68a8de;">
                            <a class="btn" style="color: #ffffff;">Написать автору</a>
                        </div>
                    </div>
                    <div id="authorUserName" class="col-xs-4" style="color: #495d9a;">

                        <c:choose>
                            <c:when test="${not empty profile.username}">
                                ${profile.username}
                            </c:when>
                            <c:otherwise>
                                Имя автора не указано
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-8">
                        <div class="row panel" style="color: #ffffff; background-color: #68a8de;">
                    <span style="display: inline-block;">
                        <p id="contactPhones" style="margin: 0 10px 5px;" onclick="showContact()">XXX XXX XX XX &nbsp;&nbsp;&nbsp;
                            <u>Показать телефон</u></p>

                    </span>
                            <span class="fa fa-phone fa-3x pull-right"
                                  style="padding-right: 10px; padding-top: 5px"></span>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <p class="fa fa-bullhorn fa-4x" style="color: orange;"></p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-8">
                        <div class="row panel" style="color: #495d9a; background-color: #68a8de;">
                            <a class="btn" style="color: #fdfffd;">Скайп</a>
                            <span class="fa fa-skype fa-2x pull-right"
                                  style="color: white; padding-right: 10px; padding-top: 5px"></span>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <a style="color: #495d9a;">Объявления автора</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <c:if test='${offer.address.coordinates != ""}'>
                <iframe width="300" height="225" frameborder="0" style="border:0"
                        src="https://www.google.com/maps/embed/v1/place?q=place_id:${offer.address.coordinates}&key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww"
                        allowfullscreen></iframe>
            </c:if>
        </div>
    </div>
    <!--/left-->

    <!--right-->
    <div class="col-sm-6" style=" margin-top: 90px;">

        <div class="row">
            <c:if test="${offer.urgent}">
            <span>
            Срочно
            </span>
            </c:if>
        </div>

        <div class="panel panel-default" style="background-color: transparent; border-color: transparent;">
            <div class="panel-body">
                <c:if test="${offer.imagesIds.keySet().size() < 1}">
                    <li><img src="/resources/images/no_photo.jpg"></li>
                </c:if>
                <c:if test="${offer.imagesIds.keySet().size() > 0}">
                    <ul class="pgwSlideshow">
                        <c:forEach items="${offer.imagesIds.keySet()}" var="id">
                            <li><img src="/api/rest/fileStorage/OFFERS/file/read/id/${id}"></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-8">
            <div class="row panel" style="color: #495d9a; background-color: #68a8de; padding: 5px;">
                <textarea id="offerDescription" style="resize: none; width: 700px; height: 350px;"
                          readonly>${offer.description}</textarea>
            </div>
        </div>
    </div>

    <div id="video">

    </div>
    <!--/right-->
</div>
<!--/container-fluid-->
<!-- script references -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/pgwslideshow.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>


<script>

    jsonCategory = ''


    $.ajax({
        type: "GET",
        url: "/resources/json/searchCategories.json",
        async: false,
        success: function (response) {
            jsonCategory = response;
        }
    });

    var cities;
    $.ajax({
        url: '/resources/json/cities.json',
        dataType: 'json',
        async: false,
        success: function (response) {
            cities = response;
        }
    });


    $(function () {
        var region = $('#chosenRegion').text();
        if (region === 'Выберите область' || region === 'Вся Украина') {
            $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
        }

        $('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();
        $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');
        for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {
            $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');
        }
        for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {
            $('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');
        }

        $('#cities').find('li').click(function () {
                    var city = $(this).text();
                    $('#chosenCity').text(city);
                }
        );
    });

    $('#regions').find('li').click(function () {
                var region = $(this).text();
                $('#chosenRegion').text(region);
                if (region !== 'Вся Украина') {
                    $('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");
                } else {
                    $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
                }

                $('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();
                $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');
                for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {
                    $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');
                }
                for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {
                    $('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');
                }
                $('#chosenCity').text('Выберите город');
                $('#cities').find('li').click(function () {
                            var city = $(this).text();
                            $('#chosenCity').text(city);
                        }
                );
            }
    );


    var goSearch = function () {
        var getParams = {};
        getParams.minPrice = $('#minPrice').val();
        getParams.maxPrice = $('#maxPrice').val();
        getParams.search = $('#search').val();
        var chosenRegion = $('#chosenRegion').text();
        if (chosenRegion === 'Выберите область' || chosenRegion === 'Вся Украина') {
            chosenRegion = "";
        }
        getParams.chosenRegion = chosenRegion;
        var chosenCity = $('#chosenCity').text();
        if (chosenCity === 'Выберите город') {
            chosenCity = "";
        }
        getParams.chosenCity = chosenCity;
        window.location.href = "/offers/1?" + $.param(getParams);

    };
</script>

<script>
    var phonesSet = '${offer.userInfo.phoneNumbers}'.substring(1, '${offer.userInfo.phoneNumbers}'.length - 1).split(',');

    $(document).ready(function () {

        var id = '${offer.authorId}';
        var videoUrl = '${offer.videoUrl}';
        var idUser;
        var time = '${offer.createdDate}';
        time = new Date(parseInt(time));
        var now = moment(time).locale("ru"); //using of moment.js library
        $('#createDate').text("Опубликовано: " + now.format('LLL'));

        if (videoUrl !== '') {
            var videoKey = videoUrl.split('=')[1];
            $('#video').append('<iframe width="560" height="315" src="https://www.youtube.com/embed/' + videoKey + '" frameborder="0" allowfullscreen></iframe>');
        }

        $('.pgwSlideshow').pgwSlideshow();

    });

    function showContact() {
        var phoneList = ' ';
        for (var i = 0; i < phonesSet.length; i++) {
            phoneList = phoneList += phonesSet[i] + "\n";
        }
        $('#contactPhones').text(phoneList);
    }


    var breadcrumbs = JSON.parse('${offer.categories}');



    //    alert(JSON.stringify(jsonCategory[2].id));


    for (var j = 0; j < breadcrumbs.length; j++) {


        for (var i = 0; i < jsonCategory.length; i++) {
            if (jsonCategory[i].id === breadcrumbs[j]) {
                $('#breadcrumbs').append('<span><a href="#">' + jsonCategory[i].name + '</a>' + "/" + '</span>');
            }
        }




    }

</script>
</body>
</html>