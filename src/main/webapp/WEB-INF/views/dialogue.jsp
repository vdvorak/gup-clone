<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fairy
  Date: 01.12.2015
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>Страница диалога</title>
  <meta name="generator" content="Bootply"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
  <link href="/resources/css/bootstrap.css" rel="stylesheet">

  <link href="/resources/css/com.css" rel="stylesheet">
  <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">

<!--Header-->
<jsp:include page="/WEB-INF/templates/header.jsp"/>
<!--Header-->

<div class="container-fluid">

  <!--category-->
  <div class="row" style="padding: 10px;">
    <div class="col-xs-3" style="padding-left: 5px; padding-right: 5px;">
      <a><img src="/resources/images/logo.png"></a>
    </div>
    <div class="col-xs-3"
         style="padding-left: 5px; padding-right: 5px; color: white; font-size: 25px;  margin-top: 30px; ">
      ПОРТАЛ<br>РОЗВИТКУ<br>УКРАЇНИ
    </div>
    <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

    </div>
    <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

    </div>
    <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

    </div>
  </div>
  <!--category-->

  <!--category-->
  <div class="row">
    <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
      <a href="/offers"><div class="btn btn-info btn-block">Объявления</div></a>
    </div>
    <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
      <div class="btn btn-info btn-block">
        Работа
      </div>
    </div>
    <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
      <div class="btn btn-info btn-block">
        Блог
      </div>
    </div>
    <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
      <div class="btn btn-info btn-block">
        Новости
      </div>
    </div>
    <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
      <div class="btn btn-info btn-block">
        Тендеры
      </div>
    </div>
    <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
      <div class="btn btn-info btn-block">
        Проекты
      </div>
    </div>
  </div>
  <!--category-->

  <!--left-->
  <div class="col-sm-6">


    <div class="row">
      <div class="col-xs-4">
        <div class="row">
          <h2 style="color: #ff8e35;">${dialogue.subject}</h2>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-8">
        <div class="row" style="color: #495d9a;">
          <p class="pull-left">Последнее сообщение: ${dialogue.messages.get(dialogue.messages.size()-1).date}</p>

        </div>
        <div class="row panel" style="color: #495d9a; background-color: #68a8de; padding: 5px;">
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-4">
        <div class="row">
          ${dialogue.subject}
        </div>
      </div>
    </div>

  </div>
  <!--/left-->

  <!--right-->
  <div class="col-sm-6" style=" margin-top: 90px;">

    <div class="row">
            <span>
            Срочно
            </span>
    </div>
    <div>
      dialogue.members.size() = ${dialogue.members.size()}
      dialogue.messages.size() = ${dialogue.messages.size()}
    </div>

    <div class="panel panel-default" style="background-color: transparent; border-color: transparent;">
      <div class="panel-body">
        <c:if test="${dialogue.members.size() < 1}">
          нет мемберов
          <%--<li><img src="/resources/images/user_no_photo.jpg"></li>--%>
          <li><img src="/resources/images/no_photo.jpg"></li>
        </c:if>
        <c:if test="${dialogue.members.size() > 0}">
          <ul class="pgwSlideshow">
            <c:forEach items="${dialogue.members}" var="member">
              <a href="/profile/id/${member.id}">
                <li>${member.name}</li>
                <li><img src="/api/rest/fileStorage/PROFILE/file/read/id/${member.userPicId}"></li>
              </a>
            </c:forEach>
          </ul>
        </c:if>
        <c:if test="${dialogue.messages.size() > 0}">
          OMG message size > 0
          <ul>
            <c:forEach items="${dialogue.messages}" var="mes">
              <li>Текст: ${mes.message}"</li>
              <li>Дата: ${mes.date}"</li>
              <li>От кого: ${mes.authorId}"</li>
            </c:forEach>
          </ul>
        </c:if>
      </div>
    </div>
  </div>

  <%--<div class="row">--%>
    <%--<div class="col-xs-8">--%>
      <%--<div class="row panel" style="color: #495d9a; background-color: #68a8de; padding: 5px;">--%>
        <%--<textarea id="offerDescription" style="resize: none; width: 700px; height: 350px;" readonly>${offer.description}</textarea>--%>
      <%--</div>--%>
    <%--</div>--%>
  <%--</div>--%>


  <!--/right-->
</div>
<!--/container-fluid-->
<!-- script references -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/pgwslideshow.js"></script>


<%--<script>--%>
  <%--var cities;--%>
  <%--$.ajax({--%>
    <%--url: '/resources/json/cities.json',--%>
    <%--dataType: 'json',--%>
    <%--async: false,--%>
    <%--success: function(response) {--%>
      <%--cities = response;--%>
    <%--}--%>
  <%--});--%>


  <%--$(function () {--%>

    <%--var region = $('#chosenRegion').text();--%>
    <%--if (region === 'Выберите область' || region === 'Вся Украина'){--%>
      <%--$('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");--%>
    <%--}--%>

    <%--$('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();--%>
    <%--$('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');--%>
    <%--for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {--%>
      <%--$('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');--%>
    <%--}--%>
    <%--for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {--%>
      <%--$('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');--%>
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
            <%--for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {--%>
              <%--$('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');--%>
            <%--}--%>
            <%--for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {--%>
              <%--$('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');--%>
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
    <%--if (chosenRegion === 'Выберите область' || chosenRegion === 'Вся Украина'){--%>
      <%--chosenRegion = "";--%>
    <%--}--%>
    <%--getParams.chosenRegion = chosenRegion;--%>
    <%--var chosenCity = $('#chosenCity').text();--%>
    <%--if (chosenCity === 'Выберите город'){--%>
      <%--chosenCity = "";--%>
    <%--}--%>
    <%--getParams.chosenCity = chosenCity;--%>
    <%--window.location.href = "/offers/1?" + $.param(getParams);--%>

  <%--};--%>
<%--</script>--%>



<%--<script>--%>
  <%--var mobiPhone;--%>
  <%--var phonesSet = '${offer.userInfo.phoneNumbers}'.substring(1,'${offer.userInfo.phoneNumbers}'.length-1).split(',');--%>

  <%--$(document).ready(function () {--%>

    <%--var id = '${offer.authorId}';--%>
    <%--var videoUrl = '${offer.videoUrl}';--%>
    <%--var idUser;--%>

    <%--$.ajax({--%>
      <%--type: "POST",--%>
      <%--url: "/api/rest/profilesService/profile/read/id/" + id,--%>
      <%--dataType: "json",--%>
      <%--success: function (response) {--%>
        <%--$('#authorUserName').text(response.username);--%>
        <%--mobiPhone = response.contact.contactPhones;--%>
      <%--}--%>
    <%--});--%>

    <%--if (videoUrl !== ''){--%>
      <%--var videoKey = videoUrl.split('=')[1];--%>
      <%--$('#video').append('<iframe width="560" height="315" src="https://www.youtube.com/embed/' + videoKey +'" frameborder="0" allowfullscreen></iframe>');--%>
    <%--}--%>

    <%--$('.pgwSlideshow').pgwSlideshow();--%>

  <%--});--%>

  <%--function showContact() {--%>
    <%--var phoneList = ' ';--%>
    <%--for (var i = 0; i < phonesSet.length; i++) {--%>
      <%--phoneList =  phoneList += phonesSet[i] + "\n";--%>
    <%--}--%>
    <%--$('#contactPhones').text(phoneList);--%>
  <%--}--%>
<%--</script>--%>
</body>
</html>

