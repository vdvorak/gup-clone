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
<script src="/resources/js/jquery.maskedinput.min.js"></script>



<script>

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

  <%--$('select[name="price"]').change(function(){--%>
    <%--if (this.value === 'price'){--%>
      <%--$('#input_price').html('Цена <input id="inputPrice" name="price" type="number" class="form-control input-sm" value="${offer.price}" required><br>Валюта<select id="inputCurrency" name="currency"><option vlaue="UAH">UAH</option><option value="USD">USD</option><option value="EUR">EUR</option></select>');--%>
    <%--}else{--%>
      <%--$('#input_price').html("");--%>
    <%--}--%>
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
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
        async defer></script>
</body>

</html>