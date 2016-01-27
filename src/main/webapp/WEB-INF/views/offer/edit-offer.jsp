<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 25.11.2015
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>Редактирование объявления</title>
  <meta name="generator" content="Bootply"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <link href="/resources/css/com.css" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
  <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">

  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" rel="stylesheet"
        type="text/css"/>
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">
<div class="container-fluid">

  <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">

    <form id="mainInput" action="" method="post">
      <div class="col-xs-8" style="padding-left: 5px; padding-right: 5px;">

        <div class="input-group">Заголовок
          <input id="inputTitle" name="title" type="text" class="form-control input-sm"
                 value="${offer.title}" required>
          <br>

          Описание
          <textarea calss="inputDescript">${offer.description}</textarea>
          <br>

          Цена
          <input id="inputPrice" name="price" type="number" class="form-control input-sm" value="${offer.price}" required>

          <br>
          Валюта
          <select id="inputCurrency" name="currency">
            <option vlaue="UAH">UAH</option>
            <option value="USD">USD</option>
            <option value="EUR">EUR</option>
          </select>

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

      <input type="submit" value="Сохранить изменения">

    </form>
    <form id="photoInput" enctype="multipart/form-data" action="/api/rest/imagesStorage/image/upload/"
          method="post">
      <p>Загрузите ваши фотографии на сервер</p>

      <p><input type="file" name="file" multiple accept="image/*,image/jpeg">
        <input type="submit" value="Отправить"></p>
    </form>

    <div id="floating-panel">
      <input id="address" type="textbox" value="">
      <input id="submit" type="button" value="Сохранить">
    </div>
    <div id="map" style="height: 50%"></div>

    <div class="imgBlock">
      <!--uploaded images-->
    </div>
  </div>

</div>
<!-- script references -->
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
        async defer></script>


<script>

  var imgsArrResult = {};
  var phoneArrResult = [];

  var picArrDel = [];
  var picArrNew = [];
  var picArrIn = [];
  var picMapObj = {};

  var placeKey = '';

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

  if ('${offer.imagesIds}'.length > 5 ){
    picMapObj = JSON.parse('${offer.imagesIds}'.replace('{', '{"').replace(/=/g, '":"').replace(/,/g, '","').replace('}', '"}').replace(/ /g, ''));
  }

  $(document).ready(function () {

    // selecte currency from options
    var currency = '${offer.currency}';
    $('#inputCurrency').val(currency);

    // place photo from received model on the page
    for (var id in picMapObj) {
      picArrIn.push(id);
      $('.imgBlock').append('<ul id="' + id + '" style="display: inline-table; list-style-type: none">' +
      ' <li style="background-color: white"><a rel="example_group"> ' +
      '<img id="img1" alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + id + '"' + 'width="150" height="150"> ' +
      '</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
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
    $('#mainInput').submit(function (event) {
      event.preventDefault();

      var mainForm = $('#mainInput').serialize().replace(/\+/g, '%20').replace(/%0D%0A/g, "%5C%6E");
      mainForm.imagesIds = imgsArrResult;
      mainForm.address = {};
      mainForm.userInfo = {};
//      alert(JSON.stringify(mainForm));
      var b = decodeURIComponent(mainForm);
//      alert('{"' + decodeURI(b).replace(/"/g, '\\"').replace(/:/g, '\\:').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
      var c = JSON.parse('{"' + decodeURI(b).replace(/"/g, '\\"').replace(/:/g, '\\:').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
//      alert("Цэ" + JSON.stringify(c));

// subtract deleted imgId from sum of oldImgId and upploaded img---------Begin-----------
      var i = 0;
      jQuery.grep(picArrIn, function (el) {
        if (jQuery.inArray(el, picArrDel) == -1) picArrNew.push(el);
        i++;
      });

      for (var j = 0; j < picArrNew.length; j++) {
        imgsArrResult[picArrNew[j]] = "someText";
      }
//      alert(JSON.stringify(imgsArrResult));
// subtract deleted imgId from sum of oldImgId and upploaded img---------End-----------

      c.id = '${offer.id}';
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
  });

  // delete pictures only from page
  function deleteImgFromPage(idImg) {
    $('#' + idImg).remove();
    picArrDel.push(idImg);
  }

  // upload photo to the server and place it into the page-----------------------BEGIN------------------------
  $('#photoInput').submit(function (event) {
    event.preventDefault();
    var formImg = new FormData($(this)[0]);

    $.ajax({

      type: "POST",
      url: "/api/rest/fileStorage/OFFERS/file/upload/",
      data: formImg,
      async: false,
      cache: false,
      contentType: false,
      processData: false,

      success: function (data, textStatus, request) {
        picArrIn.push(data.id);
        $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none">' +
        ' <li style="background-color: white"><a rel="example_group"> ' +
        '<img id="img1" alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
        '</a> <div onclick=\"deleteImgFromPage(' + '\'' + data.id + '\'' + ')">Удалить</div> </li> </ul>');
      }
    });
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
</script>

</body>

</html>