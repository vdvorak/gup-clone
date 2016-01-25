<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 30.12.2015
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru-RU">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>GUP</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/com.css" rel="stylesheet">
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" rel="stylesheet"
          type="text/css"/>
  <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
  <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
  <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
  <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>

</head>
<body>


<sec:authorize access="isAuthenticated()" var="isAuthenticated">
  <jsp:include page="/WEB-INF/templates/authorizedHeader.jsp"/>
</sec:authorize>

<c:if test="${!isAuthenticated}">
  <jsp:include page="/WEB-INF/templates/headerAnonym.jsp"/>
</c:if>

<!--3rd section news timeline-->
<section>
  <div class="tm-main-wrap">
    <div class="">
      <h2>СОЗДАНИЕ ТЕНДЕРА</h2>
    </div>
    <div class="tm-mainForm-wrap">


        <div>
          <p class="tm-tender-name">Введите название</p>
          <input id="title" type="text">
        </div>

       <!-- <div>
          <p class="tm-tender-nace">Выберите отрасль</p>
          <select>
            <option value="household">торговля (00.02.03)</option>
            <option value="electronics">услуги (00.05.01)</option>
            <option value="apartments">банковаская деятельность (00.28.09)</option>
          </select>
        </div>-->

        <div>
          <p class="tm-tender-term">Дата окончания тендера</p>
          <input id="end-date" type="date">
        </div>

        <div>
          <p>Укажите адресс</p>
        </div>

        <input id="areaInp" type="text" name="area" style="visibility: hidden;">
        <input id="cityInp" type="text" name="city" style="visibility: hidden;">

        <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
            <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px; background-color: antiquewhite;">
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
                </div>
            </div>
        </div>

        <div>
          <img class="tm-tender-map" src="">
        </div>

        <div>
          <input type="radio" name="browser" value="open">Открытый<Br>
          <input type="radio" name="browser" value="close">Закрытый<Br>
        </div>

        <div>
          <p>Скрывать учасников тендера</p>
          <input type="checkbox">
        </div>

        <div>
          <p>Пригласить учасников тендера</p>
          <input class="tm-tender-members" type="text" placeholder="Название">
        </div>

        <div>
          <p>Ожидаемая стоимость</p>
          <input class="tm-price" type="number">
          <img class="tm-tender-currency" src="">
        </div>

        <div>
          <p>Номер тендера</p>
          <input id="text"  class="tm-number" type="text" inactive>
          <button id="txt">Нажми меня</button>
        </div>

        <div>
          <p>Описание</p>
          <textarea id="textarea"></textarea>
        </div>
        <div>
          <input type="file"  multiple accept="image/*,image/jpeg">
        </div>
        <button id="#submit" >Save</button>

    </div>
  </div>
</section>
<!--END 3rd section-->
<footer>

</footer>
<!-- hiden stuff-->
<jsp:include page="/WEB-INF/templates/authentification.jsp"/>
<!-- form itself -->

<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>



<!--END of libs-->
<script>

  var tender = {};
  var cities;

  $.ajax({
      type: "GET",
      url: "/resources/json/cities.json",
      async: false,
      success: function (response) {
          cities = response;
      }
  });



  $('#txt').click(function(){
    alert( tinymce.activeEditor.getContent());
  });

  tinymce.init({
    selector: 'textarea',
    height: 500,
    theme: 'modern',
    plugins: [
      'advlist autolink lists link image charmap print preview hr anchor pagebreak',
      'searchreplace wordcount visualblocks visualchars code fullscreen',
      'insertdatetime media nonbreaking save table contextmenu directionality',
      'emoticons template paste textcolor colorpicker textpattern imagetools'
    ],
    toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    toolbar2: 'print preview media | forecolor backcolor emoticons',
    image_advtab: true,
    templates: [
      { title: 'Test template 1', content: 'Test 1' },
      { title: 'Test template 2', content: 'Test 2' }
    ],
    content_css: [
      '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
      '//www.tinymce.com/css/codepen.min.css'
    ]
  });



  $('#submit').click(function(){
     tender.title = $('#title').val();
     tender.end = $('#end-date').val();
  });

  if ($('#cityInp').val() !== 'Выберите город' && $('#cityInp').val() !== '' && $('#cityInp').val() !== 'Все города') {
      offer.address.city = $('#cityInp').val();
  }

  if ($('#areaInp').val() !== 'Выберите область' && $('#areaInp').val() !== '') {
      offer.address.area = $('#areaInp').val();
  }

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

</script>
</body>
</html>