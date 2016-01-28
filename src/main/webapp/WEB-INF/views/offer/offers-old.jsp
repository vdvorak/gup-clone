<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 08.11.2015
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Объявления</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/com.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
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
                <input class="form-control" id="minPrice" type="text" placeholder="Цена от" value="${minPrice}">
            </div>
            <div class="col-xs-2">
                <input class="form-control" id="maxPrice" type="text" placeholder="Цена до" value="${maxPrice}">
            </div>
        </div>
        <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
            <div class="input-group">

                <div class="col-xs-6" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">${chosenRegion}<b class="caret"></b></a>
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
                                    <li><a role="menuitem" tabindex="-1" href="#">Ивано‑Франковская область</a></li>
                                    <li><a role="menuitem" tabindex="-1" href="#">Киевская область</a></li>
                                    <li><a role="menuitem" tabindex="-1" href="#">Кировоградская область</a></li>
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

                <div class="col-xs-6" id="bs-example-navbar-collapse-2" >
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a id="chosenCity" href="#" class="dropdown-toggle" data-toggle="dropdown">${chosenCity}<b class="caret"></b></a>
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
            <a href="/create-offer"> <div class="btn btn-info">ПОДАТЬ ОБЪЯВЛЕНИЕ +</div></a>
        </div>
    </div>
    <!--search-->


    <!--offers category-->
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">

        <div class="col-xs-9" style="padding-left: 5px; padding-right: 5px;">


            <ul style="display: inline-table; list-style-type: none">
                <li style="background-color: white">
                    <a rel="example_group"
                       href="http://nexpro.ru/wp-content/uploads/2015/04/166.png" title="Артикул: ">
                        <img alt="" src="http://nexpro.ru/wp-content/uploads/2015/04/166.png" width="150" height="150">
                    </a>

                    <div>Заголовок</div>

                </li>
            </ul>


            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">
                    Категория
                </button>


                <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">

                    <div class="col-xs-5" style="padding-left: 5px; padding-right: 5px;">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Категория 1 уровня</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                    </div>
                    <div class="col-xs-5" style="padding-left: 5px; padding-right: 5px;">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Категория 1 уровня</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">кат. 2 уровня</a></li>
                    </div>

                    <div>
                        <a role="menuitem" tabindex="-1" href="#">Смотреть все объявления</a>
                    </div>

                </ul>
            </div>

        </div>
    </div>
    <!--offers category-->


    <!--gallery-->
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">

        <div class="col-xs-9" style="padding-left: 5px; padding-right: 5px;">

            <c:choose>
                <c:when test="${offers.entities.size()==0}">
                    <h2>По вашему запросу ничего не найдено</h2>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${offers.entities}" var="offer">
                        <ul style="display: inline-table; list-style-type: none">
                            <li style="background-color: white">
                                <a rel="example_group"
                                   href="/offer/${offer.id}"
                                   title="${offer.title}">
                                    <c:choose>
                                        <c:when test="${not empty offer.imagesIds.keySet()}">
                                            <c:forEach items="${offer.imagesIds.keySet()}" var="id" varStatus="status">
                                                <c:if test="${status.first}">
                                                    <img alt="" width="150" height="150" src="/api/rest/fileStorage/OFFERS/file/read/id/${id}">
                                                </c:if>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <img alt="" width="150" height="150" src="/resources/images/no_photo.jpg">
                                        </c:otherwise>
                                    </c:choose>
                                    <div>${offer.title}</div>
                                    <div>${offer.price}</div>
                                    <div>${offer.views}</div>
                                </a>
                            </li>
                        </ul>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


        </div>
    </div>

    <div id="pages">

    </div>
    <!--gallery-->

</div>

<!-- script references -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/pgwslideshow.js"></script>
<script type="text/javascript" src="/resources/js/jquery.simplePagination.js"></script>

<script>

    var cities;
    $.ajax({
        url: '/resources/json/cities.json',
        dataType: 'json',
        async: false,
        success: function(response) {
            cities = response;
        }
    });


    $(function () {
        $('#pages').pagination({
            cssStyle: 'light-theme',
            currentPage: ${pageNumber},
            pages: ${pages}
        });
        var region = $('#chosenRegion').text();
        if (region === 'Выберите область' || region === 'Вся Украина'){
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


    var storedHash = window.location.hash;
    window.setInterval(function () {
        var getParams = {};
        getParams.minPrice = $('#minPrice').val();
        getParams.maxPrice = $('#maxPrice').val();
        getParams.search = $('#search').val();
        var chosenRegion = $('#chosenRegion').text();
        if (chosenRegion === 'Выберите область' || chosenRegion === 'Вся Украина'){
            chosenRegion = "";
        }
        getParams.chosenRegion = chosenRegion;
        var chosenCity = $('#chosenCity').text();
        if (chosenCity === 'Выберите город'){
            chosenCity = "";
        }
        getParams.chosenCity = chosenCity;
        if (window.location.hash != storedHash) {
            storedHash = window.location.hash;
            window.location.href = "/offers/" + storedHash.substring(6) + "?" + $.param(getParams);
        }
    }, 100);

    var goSearch = function () {
        var getParams = {};
        getParams.minPrice = $('#minPrice').val();
        getParams.maxPrice = $('#maxPrice').val();
        getParams.search = $('#search').val();
        var chosenRegion = $('#chosenRegion').text();
        if (chosenRegion === 'Выберите область' || chosenRegion === 'Вся Украина'){
            chosenRegion = "";
        }
        getParams.chosenRegion = chosenRegion;
        var chosenCity = $('#chosenCity').text();
        if (chosenCity === 'Выберите город'){
            chosenCity = "";
        }
        getParams.chosenCity = chosenCity;
        if (storedHash.substring(6) === "") {
            window.location.href = "/offers/1?" + $.param(getParams);
        } else {
            alert($.param(getParams));
            window.location.href = "/offers/" + storedHash.substring(6) + "?" + $.param(getParams);
        }
    };
</script>
</body>


</html>

