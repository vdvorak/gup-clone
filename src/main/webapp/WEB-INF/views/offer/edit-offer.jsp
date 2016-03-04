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
    <title>Редактирование объявления</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/dropdown-multicolumn.css">
    <style>
        a.explanation-point-tooltip {
            position: relative;
            display: inline-block;
            width: 100%;
        }

        a.explanation-point-tooltip img:hover {
            cursor: default;
        }

        a.explanation-point-tooltip:focus {
            outline: 0;
        }

        a.explanation-point-tooltip img {
            position: absolute;
            width: 23px;
            height: 23px;
            visibility: visible;
            left: 100%;
            top: 0%;
            z-index: 999;
        }

        #btn-offer-save {
            float: right;
            margin-top: 60px;
            color: #ffffff;
            font: 400 16px ArianAMU;
            height: 32px;
            width: 133px;
            border-radius: 5px;
            border: 2px solid rgb(153, 204, 102);
            background-color: rgb(153, 204, 102);
            -webkit-transition: all .25s;
            transition: all .25s;
            margin-right: 15px;
        }

        #btn-offer-save:hover {
            background-color: transparent;
            color: rgb(153, 204, 102);
        }
        .ul-img-container {
            padding: 0px 15px;
            width: 100%;
            background-color: rgba(153,204,102,0.30);
        }

        .ul-img-container li {
            margin: 10px 15px;
            padding: 0px;
            width: 150px;
            height: 150px;
            list-style-type: none;
            display: inline-block;
            border-radius: 5px;
            position: relative;
            overflow: hidden;
        }

        .ul-img-container img {
            width: 100%;
            height: 100%;
            display: block;
        }

        .ul-img-container li:hover .descr {
            opacity: 1;
            -webkit-transform: rotateZ(0deg);
            transform: rotateZ(0deg);
            border-radius: 0;
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
    <h1 class="title-h1-blue text-center">Редактирование обьявления</h1>
    <%--<a href="#" class="pull-right">Мои обьявления</a>--%>

    <div class="clearfix"></div>

    <div class="container-fluid new-adv-box">
        <div class="row">
            <div class="col-xs-3">
                <label for="new-label-1">Заголовок<em>*</em></label>
            </div>
            <div class="col-xs-8">
                <input type="text" id="new-label-1" placeholder="Длина заголовка от 5 до 70 символов"
                       value="${offer.title}">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-3">
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

            <div class="col-xs-3">
                <label for="offer-inpPrice">Цена<em>*</em></label>
            </div>
            <div id="price-options" class="col-xs-3">
                <select class="prop" name="price">

                </select>
            </div>
            <div class="col-xs-3" style="display: none">
                <input id="offer-inpPrice" type="number"
                       style="border: 4px solid #9c6; border-radius: 5px;" value="${offer.price}">
            </div>
            <div class="col-xs-2" style="display: none">
                <select id="selection-currency" name="currency" class="prop" value="${offer.currency}">
                    <option>UAH</option>
                    <option>USD</option>
                    <option>EUR</option>
                </select>
            </div>
        </div>


        <div id="offer-options-row" class="row" style="display: none">
            <div class="col-xs-3">
                <label for="other-options">Дополнительно</label>
            </div>
            <div id="other-options" class="col-xs-8">

            </div>
        </div>

        <div class="row">
            <div class="col-xs-3">
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
            <div class="col-xs-3">
                <label for="new-label-3">Описание<em>*</em></label>
            </div>
            <div class="col-xs-8">
        <textarea name="" id="new-label-3" cols="30" rows="10"
                  placeholder="Длина описания от 50 до 4000 символов">${offer.description}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8 col-xs-offset-3">
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
            <div class="col-xs-3">Фотографии</div>
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
            <div class="col-xs-3"></div>
            <div id="drop_zone" class="col-xs-8">
                <ul class="ul-img-container">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-3">
                <label for="inpVideo">Добавить видео</label>
            </div>
            <div class="col-xs-8">
                <input type="text" id="inpVideo" value="${offer.videoUrl}">
            </div>
        </div>
        <h2 class="title-h2-blue text-center">Контакты</h2>

        <div class="row">
            <div class="col-xs-3">
                <label for="inpAuthor">Контактное лицо<em>*</em></label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpAuthor" value="${offer.userInfo.contactName}">
            </div>
            <div class="col-xs-2">
                <label for="inpAdress">Адрес</label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpAdress">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-3">
                <label for="inpEmail">E-mail<em>*</em></label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpEmail" value="${offer.userInfo.email}">
            </div>
            <div class="col-xs-2">
                <label for="inpSkype">Skype</label>
            </div>
            <div class="col-xs-3">
                <input type="text" id="inpSkype" value="${offer.userInfo.skypeLogin}">
            </div>
        </div>
        <div class="row row-telephone">
            <div class="col-xs-3">
                <label>Телефон</label>
            </div>
            <div class="col-xs-3">
                <input type="text">
            </div>
            <div id="btn-add-tel" class="col-xs-1" data-toggle="tooltip" data-placement="right"
                 title="Добавить телефон"
                 onClick="addTelephone()">
                <img src="/resources/images/pluse.png" alt="plus">
            </div>
        </div>
    </div>

    <%--<div class="new-ad-btn">--%>
    <div class="row">
        <%--<a href="#" class="btn btn-lg btn-success">Посмотреть</a>--%>
        <div class="col-xs-3 col-xs-offset-8">
            <button id="btn-offer-save">Сохранить</button>
        </div>
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


<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 25.11.2015
  Time: 15:02
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>Редактирование объявления</title>
  <meta name="generator" content="Bootply"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  &lt;%&ndash;<link href="../pages/css/bootstrap.css" rel="stylesheet">&ndash;%&gt;
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <link href="/resources/css/com.css" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
  <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
  <link href="/resources/css/jquery-ui.css" rel="stylesheet">
  <link href="/resources/css/mini.css" rel="stylesheet">

</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">
<div class="container-fluid">

  <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">

      <div class="col-xs-8" style="padding-left: 5px; padding-right: 5px;">

        <div class="input-group">Заголовок
          <input id="inputTitle" name="title" type="text" class="form-control input-sm"
                 value="${offer.title}" required>
          <br>

          Описание
          <textarea id="offerDescription" class="inputDescript">${offer.description}</textarea>
          <div id="textLength"></div>
          <br>

          <div id="inputPrice">
            Цена
            <input name="cost" type="number" class="form-control input-sm" value="${offer.price}" required>
            <br>
            Валюта
            <select id="inputCurrency" name="currency">
              <option vlaue="UAH">UAH</option>
              <option value="USD">USD</option>
              <option value="EUR">EUR</option>
            </select>
          </div>

          <div id="categories-container" class="row">
            <div id="category-element" class="element-hidden col-xs-2"></div>
          </div>

          Номера телефонов
          <c:forEach items="${offer.userInfo.phoneNumbers}" var="id">
            <li><input class="phoneInputGroup" type="text" name="mytext[]" value="${id}"></li>
          </c:forEach>

          Skype
          <input id="inputSkype" name="skype" type="text" class="form-control input-sm" value="${offer.userInfo.skypeLogin}">

          <br>
          Разрештиь резервирование
          <c:if test='${offer.canBeReserved}'>
            <input type="checkbox" id="inputReserved" checked="checked">
          </c:if>
          <c:if test='${!offer.canBeReserved}'>
            <input type="checkbox" id="inputReserved">
          </c:if>

          <br>
          Срочное объявление
          <c:if test='${offer.urgent}'>
            <input type="checkbox" id="inputUrgent" checked="checked">
          </c:if>
          <c:if test='${!offer.urgent}'>
            <input type="checkbox" id="inputUrgent">
          </c:if>

          <br>
          Ссылка на видео
          <input id="inputVideo" type="text" class="form-control input-sm"
                 value="${offer.videoUrl}">
          <br>

          <!-- city chosen -->
          <input id="countryInp" type="text" name="country" style="visibility: hidden;">
          <input id="areaInp" type="text" name="area" style="visibility: hidden;">
          <input id="cityInp" type="text" name="city" style="visibility: hidden;">

          <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
            <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
              <div class="input-group">

                <div class="col-xs-6" id="bs-example-navbar-collapse-1">
                  <ul class="nav navbar-nav">
                    <li class="dropdown">
                      <a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите область<b class="caret"></b></a>
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
                              <li><a role="menuitem" tabindex="-1" href="#" >Ивано‑Франковская область</a></li>
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
                              <li><a role="menuitem" tabindex="-1" href="#">Черновицкая область</a>
                              </li>
                            </ul>
                          </div>
                        </div>
                      </ul>
                    </li>
                  </ul>
                </div>

                <div class="col-xs-6" id="bs-example-navbar-collapse-2" style="visibility: hidden">
                  <ul class="nav navbar-nav">
                    <li class="dropdown">
                      <a id="chosenCity" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите город<b class="caret"></b></a>
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
              </div>
            </div>
          </div>
          <!-- city chosen -->

          Контактное лицо
          <input id="inptContactName" type="text" class="form-control input-sm" value="${offer.userInfo.contactName}">
          <br>

          E-mail
          <input id="inptEmail" type="text" class="form-control input-sm" value="${offer.userInfo.email}">
        </div>
      </div>

      <input id="btn-submit" type="submit" value="Сохранить изменения">

    <div id="options" class="row panel"></div>
    <div id="inputs" class="row panel"></div>
    <!--
   <form id="photoInput" enctype="multipart/form-data" action="/api/rest/imagesStorage/image/upload/"
         method="post">
     <p>Загрузите ваши фотографии на сервер</p>

     <p><input type="file" name="file" multiple accept="image/*,image/jpeg">
       <input type="submit" value="Отправить"></p>
   </form>
   -->

   <div id="floating-panel">
     <input id="address" type="textbox" value="">
     <input id="submit" type="button" value="Сохранить">
   </div>
   <div id="map" style="height: 50%"></div>

    <div id="drop_zone">

      <button id="addImg">Загрузить фото</button>
      <form id="uploadProfilePhotoForm" enctype="multipart/form-data"
            method="post" style="display:none">
        <p><input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg" multiple></p>
      </form>

      <div class="imgBlock">
        <!--uploaded images-->
      </div>
      Перетяните файлы сюда
    </div>

  </div>

</div>
<!-- script references -->
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>--%>

<script>

    var placeKey = '';
    var jsonCategory;
    var jsonSubcategory;
    var options;
    var parameters = [];
    var cities;

    var categories = ${categories};
    var category1Id = '';
    var category2Id = '';
    var category3Id = '';
    var isComplete = 0;

    var properties = ${properties};

    var imgsArrResult = {};
    var picMapObj = ${imagesIds};
    var picArrDel = [];
    var picArrNew = [];
    var imgsArr = {};

    if ('${offer.canBeReserved}' === 'true') $('#reserve-checkbox').prop('checked', true);
    if ('${offer.urgent}' === 'true') $('#new-label-check').prop('checked', true);

    // ---------------    LOAD RESOURCES    --------------------------//
    $(document).ready(function () {

        // Setup the dnd listeners.
        var dropZone = document.getElementById('drop_zone');
        dropZone.addEventListener('dragover', handleDragOver, false);
        dropZone.addEventListener('drop', handleFileSelect, false);

        function handleFileSelect(evt) {
            evt.stopPropagation();
            evt.preventDefault();

            var files = evt.dataTransfer.files; // FileList object.

            // files is a FileList of File objects. List some properties.
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
        }

        function handleDragOver(evt) {
            evt.stopPropagation();
            evt.preventDefault();
            evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
        }

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

    var area = '${offer.address.area}';
    if (area) $('#text-region').text(area);

    $.ajax({
        type: "GET",
        url: "/resources/json/cities.json",
        async: false,
        dataType: 'json',
        success: function (response) {
            cities = response;

            var city = '${offer.address.city}';

            if (area && area !== 'Вся Украина') {
                if (city) $('#text-city').text(city);
                drawCities(area);
            }
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

    // --------------------- MAIN FORM CONSTRUCTION ----------------------//
    function showExplanationPointTooltip(elem) {
        var wrapper = $('<a class="explanation-point-tooltip" href="#/"></a>');
        var tooltipImg = $('<img src="/resources/css/images/explanation-point.png">');

        elem.wrap(wrapper)
                .parent()
                .append(tooltipImg)
                .focus()
                .children('img')
                .delay(1500)
                .fadeOut(300, function () {
                    $(this).remove();
                    elem.unwrap();
                });
        return false;
    }

    function validateOffer() {
        var title = $("#new-label-1").val();
        if (title.length < 5 || title.length > 70) {
            return showExplanationPointTooltip($("#new-label-1"));
        }
        if (!isComplete) {
            return showExplanationPointTooltip($('#categories-row'));
        }

        if ($('#offer-price-row').css('display') !== 'none' && $('select[name="price"]').val() === 'price' && !$('#offer-inpPrice').val()) {
            return showExplanationPointTooltip($('#selection-currency'));
        }

        var region = $('#text-region').text();
        var city = $('#text-city').text();
        if (region === 'Выберите область' || (region !== 'Вся Украина' && city === 'Выберите город')) {
            return showExplanationPointTooltip($('#region-row'));
        }

        var description = $('#new-label-3').val();
        if (description.length < 50 || description.length > 4000) {
            return showExplanationPointTooltip($('#new-label-3'));
        }

        if (!$('#inpEmail').val()) {
            return showExplanationPointTooltip($('#inpEmail'));
        }

        if (!$('#inpAuthor').val()) {
            return showExplanationPointTooltip($('#inpAuthor'));
        }
        return true;
    }

    $('#btn-offer-save').click(function () {
        if (validateOffer()) {

            for (var key in imgsArr) {
                if (picArrDel.indexOf(key) === -1) picArrNew.push(key);
            }

            for (var i = 0; i < picArrNew.length; i++) {
                imgsArrResult[picArrNew[i]] = imgsArr[picArrNew[i]];
            }

            for (var i = 0; i < picArrDel.length; i++) {
                deleteImgFromDB(picArrDel[i]);
            }

            checkMainImg();

            var offer = {};
            offer.title = $("#new-label-1").val();
            offer.imagesIds = imgsArrResult;
            offer.canBeReserved = $("#reserve-checkbox").is(":checked");
            offer.address = {};
            //        offer.address.coordinates = placeKey;
            offer.address.country = 'Украина';


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
            $.each($('.row-telephone').find('input'), function (index) {
                var val = $(this).val();
                if (val) phones.push(val);
            });

            categories = [];
            if (category1Id !== '') {
                categories.push(category1Id)
            }
            if (category2Id !== '') {
                categories.push(category2Id)
            }
            if (category3Id !== '') {
                categories.push(category3Id)
            }

            offer.categories = categories;
            offer.active = true;
            offer.description = $('#new-label-3').val();
            offer.userInfo = {};
            offer.userInfo.skypeLogin = $('#inpSkype').val();
            offer.userInfo.contactName = $('#inpAuthor').val();
            offer.userInfo.email = $('#inpEmail').val();
            offer.videoUrl = $('#inpVideo').val();
            offer.userInfo.phoneNumbers = phones;

            properties = [];
            $('#other-options').find('select').each(function () {
                var prop = {};
                prop.key = this.name;
                prop.value = this.value;
                properties.push(prop);
            });

            $('#other-options').find('input').each(function () {
                var prop = {};
                prop.key = this.name;
                prop.value = this.value;
                properties.push(prop);
            });

            if ($('#offer-price-row').css('display') !== 'none') {
                properties.push({
                    key: 'price',
                    value: $('select[name="price"]').val()
                });
                offer.currency = $('select[name="currency"]').val();
                offer.price = $('#offer-inpPrice').val();
            }

            offer.properties = properties;

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
        }
    });

    // --------------------- END MAIN FORM CONSTRUCTION ----------------------//


    // -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//
    // place photo from received model on the page
    for (var id in picMapObj) {
        imgsArr[id] = picMapObj[id];
        if (picMapObj[id] === "image" || picMapObj[id] === "pic1") {
            appendImg(id);
        }
    }

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
                .attr("id", id)
                .click(onClickSetMainImg);
        cloneImg.find('span')
                .click(deleteImg);

        if(imgsArr[key] === "pic1") cloneImg.find('img').addClass('mainImg');

        cloneImg.appendTo('.ul-img-container');
    }

    // delete images before save changes in offer (must be called before update offer)
    function deleteImgFromDB(arr) {
        $.ajax({
            url: '/api/rest/fileStorage/OFFERS/file/delete',
            method: 'POST',
            data: {'fileId': arr},
            traditional: true,
            success: function (response) {

            },
            error: function (response) {
            }
        });
    }

    function deleteImg() {
        var idImg = $(event.currentTarget).parent()
                .find('img')
                .attr('id');
        var block = $(event.currentTarget).parent().parent();
        $('#' + idImg).parent().remove();

        var numberImg = block.find('img').length;
        if (numberImg < 2) {
            block.find(".li-defaultIMG").css("display", "inline-block");
        }
        picArrDel.push(idImg);
    }

    function onClickSetMainImg() {
        var img = $(event.currentTarget);
        var id = img.attr("id");
        var isMain = img.hasClass("mainImg");
        var allImgs = $(".ul-img-container").find("img");
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


    function checkMainImg() {
        var hasMainImg = false;

        for(var key in imgsArrResult) {
            if(imgsArrResult[key] === 'pic1') {
                hasMainImg = true;
                break;
            }
        }

        if(!hasMainImg) {
            for(var key in imgsArrResult) {
                imgsArrResult[key] = 'pic1';
                break;
            }
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

        if (region === 'Вся Украина') {
            $('#city-container').css('display', 'none');
        } else {
            drawCities(region);
        }
    }

    function drawCities(area) {
        var citiesArr = cities[area];

        var parentBlock = $('#city-container').find('.multi-column-dropdown').first();
        var li = $('<li><a href="#" style="font-weight: bold">Все города</a></li>').click(selectCity);
        parentBlock.append(li);

        var numInColumn = citiesArr.length / 2 + (citiesArr.length % 2);
        for (var i = 0; i < citiesArr.length; i++) {
            parentBlock = (i + 2 <= numInColumn) ? $('#city-container').find('.multi-column-dropdown').first() : $('#city-container').find('.multi-column-dropdown').last();
            li = $('<li><a href="#">' + citiesArr[i] + '</a></li>').click(selectCity);
            parentBlock.append(li);
        }

        $('#city-container').css('display', 'inline-block');
    }

    function selectCity(event) {
        event.preventDefault();
        var city = $(event.currentTarget).children('a').text();
        $('#text-city').text(city);
    }

    //--------------------------- END REGIONS LIST --------------------------------------------//

    //----------------------------- PHONES LIST ----------------------------------------------//
    var phonesArr = ${offer.userInfo.phoneNumbers};
    for (var i = 0; i < phonesArr.length; i++) {
        if (i < 3) {
            if (i) addTelephone();
            $('.row-telephone:last').find('input').val(phonesArr[i]);
        } else {
            break;
        }
    }

    function addTelephone() {
        var curNumber = $('.row-telephone').length;
        if (curNumber < 3) {
            var imgDel = $('<a href="#" class="remove_field"><img src="/resources/img/minus.png" style="width: 20px; height: 20px; margin-left: 0px;"></a>').click(deleteTel);
            var row = $('.row-telephone').first().clone();
            row.children('#btn-add-tel').remove();
            row.find('label').parent().remove();
            var inputBlock = row.find('input').val("").parent().addClass('col-xs-offset-3');
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
    var numberCategories = categories.length;
    if(numberCategories > 0) {
        category1Id = categories[0];
        $('#'+category1Id+'').parent().click();
        if(numberCategories > 1) {
            category2Id = categories[1];
            $('#'+category2Id+'').parent().click();
            if(numberCategories > 2) {
                category3Id = categories[2];
                $('#'+category3Id+'').parent().click();
            }
        }
        for(var i = 0; i < properties.length; i++) {
            var curProperty = properties[i];
            $('[name="'+ curProperty.key +'"]').val(curProperty.value);
        }
        if ($('select[name="price"]').val() === 'price') {
            $('#selection-currency').parent().css('display', 'inline-block');
            $('#offer-inpPrice').parent().css('display', 'inline-block');
        }
    }

    function selectCategoryLvl1(event) {
        event.preventDefault();

        $('#ul-category2').html("");
        $('#ul-category3').html("");
        isComplete = 0;
        category2Id = '';
        category3Id = '';

        $('#text-category2').text("Выберите подкатегорию");
        $('#text-category3').text("Выберите подкатегорию");
        $('#category3-container').attr("style", "display: none");
        $('#category2-container').attr("style", "display: inline-block");

        var a1 = $(event.currentTarget).children('a');
        category1Id = a1.attr("id");
        $('#text-category1').text(a1.text());

        var child1 = {};
        var childArr = jsonCategory.filter(function (obj) {
            return obj.id === +category1Id; // Filter out the appropriate one
        });
        if (childArr[0]) {
            child1 = childArr[0].children;

            for (var key in child1) {
                var li = $('<li><a id="' + child1[key].id + '" href="#">' + child1[key].name + '</a></li>')
                        .click(selectCategoryLvl2);
                $('#ul-category2').append(li);
            }
        }
        if (Object.keys(child1).length) {
            erase(category1Id);
        } else {
            isComplete = 1;
            drawOptions(category1Id);
            $('#category2-container').attr("style", "display: none");
        }
    }

    function selectCategoryLvl2(event) {
        event.preventDefault();

        $('#ul-category3').html("");
        $('#text-category3').text("Выберите подкатегорию");
        $('#category3-container').attr("style", "display: inline-block");
        isComplete = 0;
        category3Id = '';

        var a2 = $(event.currentTarget).children('a');
        category2Id = a2.attr("id");
        $('#text-category2').text(a2.text());

        var child2 = {};
        if (jsonSubcategory[category2Id]) {
            child2 = jsonSubcategory[category2Id].children;
            for (var key in child2) {
                var li = $('<li><a id="' + key + '" href="#">' + child2[key].label + '</a></li>')
                        .click(selectCategoryLvl3);
                $('#ul-category3').append(li);
            }
        }
        if (Object.keys(child2).length) {
            erase(category2Id);
        } else {
            isComplete = 1;
            drawOptions(category2Id);
            $('#category3-container').attr("style", "display: none");
        }
    }

    function selectCategoryLvl3(event) {
        event.preventDefault();

        isComplete = 1;
        var a3 = $(event.currentTarget).children('a');
        category3Id = a3.attr("id");
        $('#text-category3').text(a3.text());
        erase(category2Id);
        drawOptions(category3Id);
    }


    //--------------------------------END CATEGORY-------------------------------------------------//

    //--------------------------------- DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//
    $('select[name="price"]').change(selectPrice);

    function selectPrice(event) {
        var selectVal = $(event.currentTarget).val();
        if (selectVal === 'price') {
            $('#selection-currency').parent().css('display', 'inline-block');
            $('#offer-inpPrice').parent().css('display', 'inline-block');
        } else {
            $('#selection-currency').parent().css('display', 'none');
            $('#offer-inpPrice').parent().css('display', 'none');
        }
    }

    function drawOptions(id) {
        erase(id);

        for (var i in options) {
            if (options[i]['c'][id]) {
                var name = "";
                for (j in options[i]['k']) {
                    name = j;
                }

                for (j in parameters) {
                    if (name !== 'price') {
                        var selectWrapper = $('<div style="display: inline-block; margin-bottom: 5px; margin-right: 5px;"></div>');
                        var select = $('<select class="prop" name="' + name + '" id="00' + i + '">' + '</select>');
                        select.appendTo(selectWrapper);
                        if (parameters[j]['parameter']['key'] === name && parameters[j]['parameter']['validators']['required'] === 1) {
                            select.prop("required", true);
                        }
                        $('#other-options').append(selectWrapper);
                        break;
                    }
                }

                for (var j in options[i]['v']) {
                    var option = $('<option value = "' + j + '"  id ="' + j + '">' + options[i]['v'][j] + '</option>');
                    if (name === 'price') {
                        $('select[name="price"]').append(option);
                    } else {
                        $('#00' + i).append(option);
                    }
                }
                if (name === 'price') $('#offer-price-row').css('display', 'block');
            }
        }

        for (j in parameters) {
            if (parameters[j]['parameter']['type'] === "input" && parameters[j]['categories'][id] && parameters[j]['parameter']['key'] !== 'price') {

                var inpWrapper = $('<div style="display: inline-block; margin-bottom: 5px; margin-right: 5px;"></div>');
                var inp = $('<input id="' + parameters[j]['parameter']['key'] + '" type="text"  name="' + parameters[j]['parameter']['key'] + '" placeholder="' + parameters[j]['parameter']['label'] + '"/>');
                inp.appendTo(inpWrapper);
                $('#other-options').append(inpWrapper);
            }
        }
        if ($('#other-options').children().length) $('#offer-options-row').css('display', 'block');
    }

    //---------------------------- END DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

    //------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//
    function erase(id) {
        $('#offer-price-row').css('display', 'none');
        $('#offer-options-row').css('display', 'none');
        $('select[name="price"]').empty();
        $('#other-options').empty();
    }
    //------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//
</script>
<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
        async defer></script>--%>
</body>
</html>

<%--//--------------------------------- OLD!!!!!------------------------------------//

  var imgsArrResult = {};
  var phoneArrResult = [];

  var picArrDel = [];
  var picArrNew = [];
  var picArrIn = {};
  var picMapObj = ${imagesIds};
  var parameters = '';
  var options = '';
  var placeKey = '';
  var offerProperties = ${properties};
  var categories = ${categories};
  var categoryObj1 = {};
  var categoryObj2 = {};
  var categoryObj3 = {};
  var jsonCategory;
  var jsonSubcategory;


  $.ajax({
    type: "GET",
    url: "/resources/json/searchValues.json",
    dataType: 'json',
    success: function (response) {
      options = response;
    }
  });


  $.ajax({
    type: "GET",
    url: "/resources/json/parameters.json",
    dataType: 'json',
    success: function (response) {
      parameters = response;
    }
  });

  function ajaxCategories1() {
  return  $.ajax({
      type: "GET",
      url: "/resources/json/searchCategories.json",
      dataType: "json",
      success: function (response) {
        jsonCategory = response;
      }
    });
  }

  function ajaxCategories2() {
   return $.ajax({
      type: "GET",
      url: "/resources/json/searchSubcategories.json",
      dataType: "json",
      success: function (response) {
        jsonSubcategory = response;
      }

    });
  }


  $.when(ajaxCategories1(), ajaxCategories2()).done(function (resp1, resp2) {
    if(categories.length > 0) {
      var a1 = jsonCategory.filter(function(obj) {
        return parseInt(obj.id) === parseInt(categories[0]);
      });
      if(a1.length) {
        categoryObj1 = a1[0];
        appendCategory(categoryObj1.name);
      }

    }

    if(categories.length > 1) {
      var a2 = categoryObj1.children.filter(function(obj) {
        return parseInt(obj.id) === parseInt(categories[1]);
      });
      if(a2.length) {
        categoryObj2 = a2[0];
        appendCategory(categoryObj2.name);
      }

    }

    if(categories.length > 2) {
      var obj3 = jsonSubcategory[categories[1].toString()];
      if(obj3){
        var child3 = obj3.children;
        if(child3){
          categoryObj3 = child3[categories[2].toString()];
          appendCategory(categoryObj3.label);
        }
      }
    }


  });

  function appendCategory(txt) {
  var el = $('#category-element').clone()
          .removeClass("element-hidden")
          .removeAttr("id")
          .text(txt);

  $("#categories-container").append(el);
}

  //--------------------------------- DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

  var drawOptions = function(id){
    for(var i in options){
      var key_ru;
      if(options[i]['c'][id]!==undefined){
        var name;
        for (j in options[i]['k']){
          name = j;
        }

        for (j in parameters){

          if (parameters[j]['parameter']['key'] === name && parameters[j]['parameter']['validators']['required'] === 1){
            key_ru = parameters[j]["parameter"]["label"];
            $('#options').append('<div class="col-xs-6">'+key_ru+'</div><div class="col-xs-6"><select class="form-control" required name="'+name+'"  id="00'+i+'">'+ '</select></div>');
            break;
          }else if(parameters[j]['parameter']['key'] === name){
            key_ru = parameters[j]["parameter"]["label"];
            $('#options').append('<div class="col-xs-6">'+key_ru+'</div><div class="col-xs-6"><select class="form-control" name="'+name+'"  id="00'+i+'">'+ '</select></div>');
            break;
          }
        }

        $('#00'+i).on('change',function(){
          if(this.value === 'price'){
            $('#inputPrice').attr("style", "display: ");
          }else if (this.value === 'exchange' || this.value === 'arranged' || this.value === 'free') {
            $('#inputPrice').attr("style", "display: none");
          }
        });

        for ( var j in options[i]['v']){
          $('#00'+i).append('<option value = "'+j+'"  id ="'+ j +'">'+ options[i]['v'][j]+'</option>');
        }

      }
    }

    for ( j in parameters){
      key_ru = parameters[j]["parameter"]["label"];
      if (parameters[j]['parameter']['type'] === "input" && parameters[j]['categories'][id]  !== undefined ){
        $('#inputs').append('<div class="col-xs-6">'+key_ru+'</div><div class="col-xs-6"><input class="form-control" id="'+ parameters[j]['parameter']['key'] +'" type="number" name="'+ parameters[j]['parameter']['key'] +'" placeholder="'+parameters[j]['parameter']['key']+'"/></div>');

      }
    }
  };
  drawOptions(categories[categories.length -1]);
  //---------------------------- END DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//
  var offerProperties = ${properties};
  for (var i in offerProperties){
    var key = offerProperties[i].key;
    var value = offerProperties[i].value;
    var key_ru = '';
    var value_ru = '';
    for (var j in parameters){
      if (parameters[j]["parameter"]["key"] === key){
        key_ru = parameters[j]["parameter"]["label"];
        if(parameters[j]["parameter"]["type"] === 'input'){
          value_ru = value;
        }
        break;
      }
    }
    if (value_ru ===''){
      for (var m in options){
        if(options[m]["k"][key] !== undefined && options[m]["v"][value] !== undefined){
          value_ru =  options[m]["v"][value];
        }
      }
    }
      $('input[name="'+key+'"]').val(value_ru);
      $('select[name="'+key+'"]').val(value);
  }

  &lt;%&ndash;$('select[name="price"]').change(function(){&ndash;%&gt;
    &lt;%&ndash;if (this.value === 'price'){&ndash;%&gt;
      &lt;%&ndash;$('#input_price').html('Цена <input id="inputPrice" name="price" type="number" class="form-control input-sm" value="${offer.price}" required><br>Валюта<select id="inputCurrency" name="currency"><option vlaue="UAH">UAH</option><option value="USD">USD</option><option value="EUR">EUR</option></select>');&ndash;%&gt;
    &lt;%&ndash;}else{&ndash;%&gt;
      &lt;%&ndash;$('#input_price').html("");&ndash;%&gt;
    &lt;%&ndash;}&ndash;%&gt;
//  });

  // google map api-----------------------------BEGIN---------------------------------------------
  function initMap() {

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
  }
  // google map api------------------------------END----------------------------------------------

  $(document).ready(function () {

    // selecte currency from options
    var currency = '${offer.currency}';
    $('#inputCurrency').val(currency);

    // place photo from received model on the page
    for (var id in picMapObj) {
      picArrIn[id] = picMapObj[id];
      $('.imgBlock').append('<ul id="' + id + '" '+ ((picMapObj[id] === 'pic1')? 'class="mainImg"': '') +' style="display: inline-table; list-style-type: none" onClick="onClickSetMainImg(' + '\'' + id + '\'' + ')">' +
      ' <li style="background-color: white"><a rel="example_group"> ' +
      '<img alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + id + '"' + 'width="150" height="150"> ' +
      '</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
    }
    // Setup the dnd listeners.
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
              picArrIn[id] = "image";
              $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none" onClick="onClickSetMainImg(' + '\'' + id + '\'' + ')">' +
                      ' <li style="background-color: white">' +
                      '<a rel="example_group"> ' +
                      '<img alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
                      '</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
            }
          }
        });


      }
    }

    function handleDragOver(evt) {
      evt.stopPropagation();
      evt.preventDefault();
      evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }

// delete images before save changes in offer (must be called before update offer)
    function deleteImgFromDB(arr) {
      $.ajax({
        url: '/api/rest/fileStorage/OFFERS/file/delete',
        method: 'POST',
        data: {'fileId': arr},
        traditional: true,
        success: function (response) {
          alert("Успех");
//          window.location.href = '/account';
        },
        error: function (response) {
          alert("Неудача");
//          window.location.href = '/account';
        }
      });
    }

// serialize form and sent it via POST method in JSON --------------------------BEGIN---------------------
    $('#btn-submit').click(function (event) {
      event.preventDefault();

      var c = {};

// subtract deleted imgId from sum of oldImgId and upploaded img---------Begin-----------

      for(var key in picArrIn) {
        if(picArrDel.indexOf(key) === -1) picArrNew.push(key);
      }

      for (var i = 0; i < picArrNew.length; i++) {
        imgsArrResult[picArrNew[i]] = picArrIn[picArrNew[i]];
      }

      var defaultMainImg = "";
      for(var key in imgsArrResult) {
        if(imgsArrResult[key] === "pic1") {
          defaultMainImg = key;
        }
      }
      if(defaultMainImg) imgsArrResult[defaultMainImg] = "pic1";
//      alert(JSON.stringify(imgsArrResult));
// subtract deleted imgId from sum of oldImgId and upploaded img---------End-----------

      c.id = '${offer.id}';
      c.title = $("#inputTitle").val();
      c.imagesIds = imgsArrResult;
      c.canBeReserved = $("#inputReserved").is(":checked");
      c.address = {};
      c.address.coordinates = placeKey;
      c.address.country = 'Украина';
      c.videoUrl = $('#inputVideo').val();

      if ($('#cityInp').val() !== 'Выберите город' && $('#cityInp').val() !== '' && $('#cityInp').val() !== 'Все города') {
        c.address.city = $('#cityInp').val();
      }

      if ($('#areaInp').val() !== 'Выберите область' && $('#areaInp').val() !== '') {
        c.address.area = $('#areaInp').val();
      }

      if ($('#inputUrgent').is(':checked')) {
        c.urgent = true;
      } else {
        c.urgent = false;
      }

      c.description = $('#inputDescript').val();
      c.userInfo = {};
      c.userInfo.skypeLogin = $('#inputSkype').val();
      c.userInfo.contactName = $('#inptContactName').val();
      c.userInfo.email = $('#inptEmail').val();
      c.videoUrl = $('#inputVideo').val();

      $('.phoneInputGroup').each(function (i, obj) {
        phoneArrResult.push($(this).val());
      });

      c.userInfo.phoneNumbers = phoneArrResult;
      console.log(JSON.stringify(c));

      alert("удаляем фото из БД перед отправкой формы");

      if (picArrDel.length !== 0){
        deleteImgFromDB(picArrDel);
      }

      var properties = [];

      $('#options').find('select').each(function(){
        var prop = {};
        prop.key = this.name;
        prop.value = this.value;
        properties.push(prop);
      });

      $('#inputs').find('input').each(function(){
        var prop = {};
        prop.key = this.name;
        prop.value = this.value;
        properties.push(prop);
      });

      c.properties = properties;

      for (var j in properties){
        if (properties[j].price !== 'price'){
          delete c.price;
          break;
        }
      }

      $.ajax({
        type: "POST",
        url: "/api/rest/offersService/offer/edit",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(c),
        success: function (response) {
          window.location.href = '/offer/' + response.id;
        },
        error: function (response) {
          alert("Внутренняя ошибка сервера");
        }
      });
    });
// serialize form and sent it via POST method in JSON --------------------------END---------------------
    countTextLength();
    $("#offerDescription").on('keyup', countTextLength);

    $('#addImg').click(function(){
      $('#uploadProfilePhotoInput').trigger('click');
    });

  });

  function countTextLength() {
    var counter = $("#textLength");
    var currentString = $("#offerDescription").val();
    counter.html(currentString.length);
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

  // delete pictures only from page
  function deleteImgFromPage(idImg) {
    $('#' + idImg).remove();
    picArrDel.push(idImg);
  }

  function onClickSetMainImg(id) {
    var isMain = $('#' + id).find("img").hasClass("mainImg");

    var allImgs = $(".imgBlock").find("img");
    for (var i =0; i < allImgs.length; i++) {
      var curImg = $(allImgs[i]);
      if (curImg.hasClass("mainImg")) {
        curImg.removeClass("mainImg");
      }
    }
    var el = $('#' + id).find("img");
    if(!isMain) el.addClass("mainImg");

    for(var key in picArrIn) {
      if( picArrIn[key] === "pic1") {
        picArrIn[key] = "image";
      }
    }
    if(el.hasClass("mainImg")) {
      picArrIn[id] = "pic1";
    }
  }
  // upload photo to the server and place it into the page-----------------------BEGIN------------------------

  $('#uploadProfilePhotoInput').change(function (event) {
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
            picArrIn[id] = "image";
            $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none" onClick="onClickSetMainImg(' + '\'' + id + '\'' + ')">' +
                    ' <li style="background-color: white">' +
                    '<a rel="example_group"> ' +
                    '<img alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
                    '</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
          }
        }
      });
    }
    event.currentTarget.form.reset();
  });
  // upload photo to the server and place it into the page-----------------------END------------------------

</script>


<script>
  var cities;
  $.ajax({
    url: '/resources/json/cities.json',
    dataType: 'json',
    async: false,
    success: function (response) {
      cities = response;
      var area = '${offer.address.area}';
      var city = '${offer.address.city}';
      if (area !== '') {
        $('#chosenRegion').text(area);
        $('#chosenCity').attr("style", "visibility: visible");

        if (city !== '') {
          $('#chosenCity').text(city);
        } else {
          $('#chosenCity').text("Выберите город");
        }
      }

      $('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();
      $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');
      for (var i = 0; i < Math.floor(cities[area].length / 2); i++) {
        $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[area][i] + '</a></li>');
      }
      for (var j = Math.floor(cities[area].length / 2); j < cities[area].length; j++) {
        $('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[area][j] + '</a></li>');
      }
      $('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");

      $('#cities').find('li').click(function () {
                var city = $(this).text();
                $('#chosenCity').text(city);
                $('#cityInp').val(city);
              }
      );
    }
  });

  // Add/Remove phone Input Fields Dynamically with jQuery
  $(document).ready(function() {
    var max_fields      = 3; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID

    var x = 1; //initial text box count
    $(add_button).click(function(e){ //on add input button click
      e.preventDefault();
      if(x < max_fields){ //max input box allowed
        x++; //text box increment
        $(wrapper).append('<div><input id="phone'+ x +'" type="text" name="mytext[]"/><a href="#" class="remove_field" required>Удалить</a></div>'); //add input box

        //Add mask for some input fields after add new input
        jQuery(function($){
          $("#phone1").mask("(999) 999-9999");
          $("#phone2").mask("(999) 999-9999");
          $("#phone3").mask("(999) 999-9999");
        });
      }
    });

    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
      e.preventDefault(); $(this).parent('div').remove(); x--;
    })
  });

  //Add mask for some input fields on page ready
  jQuery(function($){
    $("#phone1").mask("(999) 999-9999");
    $("#phone2").mask("(999) 999-9999");
    $("#phone3").mask("(999) 999-9999");
  });

  // cities form script
  $('#regions').find('li').click(function () {
    var region = $(this).text();
    $('#chosenRegion').text(region);
    $('#areaInp').val(region);
    if (region !== 'Вся Украина') {
      $('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");
    } else {
      $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
    }
    $('#chosenCity').text("Выберите город");

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
              $('#cityInp').val(city);
            }
    );
  });
</script>--%>
<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
<%--
        async defer></script>&ndash;%&gt;
</body>

</html>--%>
