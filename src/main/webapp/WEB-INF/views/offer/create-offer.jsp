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
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Создание объявления</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/com.css" rel="stylesheet">
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" rel="stylesheet"
          type="text/css"/>

</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="#">Моя страница</a></li>
            <li><a href="#">Друзья</a></li>
            <li><a href="#">Сообщения</a></li>
        </ul>
        <div class="col-sm-4 col-md-4 pull-right">
            <ul class="nav navbar-nav">
                <li><a href="#">Вступить в организацию</a></li>
                <li><a href="#">Баланс</a></li>
                <li><a href="#">Укр/Рус</a></li>
                <li><a href="/logout">Выход</a></li>
            </ul>
        </div>
    </div>
</nav>

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



    <!--search begin-->

    <!--search end-->
    <!--offers category-->
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
        <div class="col-xs-9" style="padding-left: 5px; padding-right: 5px; display: flex;">
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">ДЛЯ РЕБЁНКА
                </button>
                <ul class="dropdown-menu" role="menu">
                    <div class="col-xs-12" style="padding-left: 5px; padding-right: 5px;">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Посмотреть все объявления</a>
                        </li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Детская одежда</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Детские коляски</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Детская мебель</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Игрушки</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Товары для школьников</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Прочие детские товары</a></li>
                    </div>

                </ul>
            </div>
           </div>
    </div>
    <!--offers category-->


    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">


        <form id="mainInput" action="" method="post">
            <div class="col-xs-8" style="padding-left: 5px; padding-right: 5px;">
                <div class="input-group">Заголовок
                    <input id="inptTitle" name="title" type="text" class="form-control input-sm"
                           placeholder="Не более 70 символов" required>
                </div>
                <!--multilevel category-->
                <input id="category1inp" type="text" name="category1inp" style="visibility: hidden;">
                <input id="category2inp" type="text" name="category2inp" style="visibility: hidden;">
                <input id="category3inp" type="text" name="category3inp" style="visibility: hidden;">

                <div class="container" style="width: 700px;">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category1lvlText">Выберите категорию<b class="caret"></b></a>
                                <ul id="category1lvl" class="dropdown-menu multi-level">
                                </ul>
                            </li>
                            <li id="container2lvl" style="visibility: hidden">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category2lvlText">Выберите подкатегорию<b class="caret"></b></a>
                                <ul id="category2lvl" class="dropdown-menu multi-level">
                                </ul>
                            </li>
                            <li id="container3lvl" style="visibility: hidden">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category3lvlText">Выберите подкатегорию<b class="caret"></b></a>
                                <ul id="category3lvl" class="dropdown-menu multi-level">
                                </ul>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
                <!--multilevel category-->
                <div id="options">
                    <h3>Тут будут опции</h3>
                </div>

                <div id="description" class="input-group">Описание
                    <textarea id="offerDescription" required></textarea>
                </div>

                <div id="inputs" class="input-group"></div>

                <div id="inptPrice" class="input-group">Цена
                    <input  name="price" type="number" class="form-control input-sm" required>
                </div>
                <div id="selectCurrency" class="input-group">Валюта
                    <select  name="currency">
                        <option>UAH</option>
                        <option>USD</option>
                        <option>EUR</option>
                    </select>
                </div>

                <div class="input-group">Разрешить бронь
                    <input id="inpReserved" type="checkbox" name="canBeReserved">
        <span class="btn btn-info btn-block">
          Забронировать даты
        </span>
                </div>
                <div  class="input-group">Срочное объявление
                    <input id="inpUrgent" type="checkbox" name="urgent"> Только для быстро портящихся продуктов
                </div>

                <div class="input_fields_wrap">Номера телефонов
                    <button class="add_field_button">Добавить ещё номер</button>
                    <div><input id="phone1" type="text" name="mytext[]" required></div>
                </div>

                <div class="input-group">Skype
                    <input id="inptSkype" type="text" class="form-control input-sm" placeholder="Введите ваш логин в Skype">
                </div>

                <div class="input-group">Контактное лицо
                    <input id="inptAuthor" type="text" class="form-control input-sm" placeholder="Введите имя контактного лица">
                </div>
                <div class="input-group">E-mail
                    <input id="inptMail" type="email" class="form-control input-sm" placeholder="Введите email">
                </div>

                <div class="input-group">Добавить видео
                    <input id="inptVideo" type="text" class="form-control input-sm" placeholder="Ссылка на видео Youtube" pattern="(?:https?:\/\/)?(?:www\.)?youtu\.?be(?:\.com)?\/?.*(?:watch|embed)?(?:.*v=|v\/|\/)([\w\-_]+)\&?">
                </div>

                <input id="test" type="submit" value="Отправить Форму" class="btn btn-info btn-block" data-toggle="popover" data-trigger="focus" data-content="Выберите категорию">

                <%--<a id="test" tabindex="0" class="btn btn-lg btn-danger" role="button" data-toggle="popover" data-trigger="focus" title="Dismissible popover" data-content="And here's some amazing content. It's very engaging. Right?">Dismissible popover</a>--%>
            </div>
        </form>

        <form id="photoInput" enctype="multipart/form-data" action="/api/rest/fileStorage/OFFERS/file/read/id/${id}"
              method="post">
            <p>Загрузите ваши фотографии на сервер</p>

            <p><input type="file" name="file" accept="image/*,image/jpeg">
                <input type="submit" value="Добавить"></p>
        </form>

        <div class="imgBlock">
            <!--uploaded images-->
        </div>

        <!-- city chosen -->
        <input id="countryInp" type="text" name="country" style="visibility: hidden;">
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
        <!-- city chosen -->
    </div>

    <div id="floating-panel">
        <input id="address" type="textbox" value="">
        <input id="submit" type="button" value="Сохранить">
    </div>
    <div id="map" style="height: 50%"></div>


</div>

<!-- script references -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
        async defer></script>


<script>
    var imgsArr = {};
    var mainForm;
    var placeKey = '';
    var phones = [];
    var jsonCategory;
    var jsonSubcategory;
    var options;
    var isComplete = 0; // It indicates whether the user selected the last level category
    var category1Id = '';
    var category2Id = '';
    var category3Id = '';
    var categoryResult = [];
    var parameters = [];
    var properties = [];
    var cities;


// ---------------    LOAD RESOURCES    --------------------------//

    $.ajax({
        type: "GET",
        url: "/resources/json/cities.json",
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (response) {
            cities = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchCategories.json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        async: false,
        success: function (response) {
            jsonCategory = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchSubcategories.json",
        async: false,
        success: function (response) {
            jsonSubcategory = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchValues.json",
        async: false,
        success: function (response) {
            options = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/parameters.json",
        async: false,
        success: function (response) {
            parameters = response;
        }
    });

// ---------------   END LOAD RESOURCES    --------------------------//


// ---------------  HIDE PRICE AND CURRENCY ON PAGE ----------------//

    $('#inptPrice').hide();
    $('#selectCurrency').hide();

// --------------- END HIDE PRICE AND CURRENCY ON PAGE ----------------//


// --------------------- MAIN FORM CONSTRUCTION ----------------------//

    $('#mainInput').submit(function (event) {
        event.preventDefault();

        if (isComplete === 0){
            $('#test').popover('show');
            setTimeout(function () {
            $('#test').popover('hide');
            }, 2000);
            return;
        }

        var mainForm = $('#mainInput').serialize().replace(/\+/g, '%20').replace(/%0D%0A/g, "%5C%6E");
        mainForm.imagesIds = imgsArr;
        mainForm.address = {};
        mainForm.userInfo = {};
        alert(JSON.stringify(mainForm));
        var uriOfferString = decodeURIComponent(mainForm);
        alert(uriOfferString);
        var offer = JSON.parse('{"' + decodeURI(uriOfferString).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
        alert("Цэ" + JSON.stringify(offer));

        offer.imagesIds = imgsArr;
        offer.canBeReserved = $("#inpReserved").is(":checked");
        offer.address = {};
        offer.address.coordinates = placeKey;
        offer.address.country = 'Украина';

        if ($('#cityInp').val() !== 'Выберите город' && $('#cityInp').val() !== '' && $('#cityInp').val() !== 'Все города') {
            offer.address.city = $('#cityInp').val();
        }

        if ($('#areaInp').val() !== 'Выберите область' && $('#areaInp').val() !== '') {
            offer.address.area = $('#areaInp').val();
        }

        if ($('#inpUrgent').is(':checked')) {
            offer.urgent = true;
        }

        phones.push($('#phone1').val());

        if($("#phone2").length) {
            phones.push($('#phone2').val());
        }

        if($("#phone3").length) {
            phones.push($('#phone3').val());
        }

        if (category1Id !==''){
            categoryResult.push(category1Id)
        }
        if (category2Id !==''){
            categoryResult.push(category2Id)
        }
        if (category3Id !==''){
            categoryResult.push(category3Id)
        }

        offer.categories = categoryResult;
        offer.active = true;
        offer.description = $('#offerDescription').val();
        offer.userInfo = {};
        offer.userInfo.skypeLogin = $('#inptSkype').val();
        offer.userInfo.contactName = $('#inptAuthor').val();
        offer.userInfo.email = $('#inptMail').val();
        offer.videoUrl = $('#inptVideo').val();
        offer.userInfo.phoneNumbers = phones;

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

        offer.properties = properties;

        console.log(JSON.stringify(offer));

        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(offer),
            success: function(response){
                window.location.href = '/offer/' + response.id;
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    });

// --------------------- END MAIN FORM CONSTRUCTION ----------------------//


// -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//

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
                var id = data.id;
                imgsArr[id] = "someText";
                $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none">' +
                ' <li style="background-color: white"><a rel="example_group"> ' +
                '<img alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
                '</a> <div onclick=\"deleteImg(' + '\'' + data.id + '\'' + ')">Удалить</div> </li> </ul>');
            }
        });
    });

    function deleteImg(idImg) {
        delete imgsArr[idImg];
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/OFFERS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                $('#' + idImg).remove();
            }
        });
    };
// -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


//--------------------------- GOOGLE MAP API ---------------------------------------//

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

//--------------------------- END GOOGLE MAP API ---------------------------------------//

//--------------------------- REGIONS LIST --------------------------------------------//

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

//--------------------------- END REGIONS LIST --------------------------------------------//

//----------------------------- PHONES LIST ----------------------------------------------//

    // Add/Remove Input Fields Dynamically with jQuery
    $(document).ready(function() {
        var max_fields      = 3; //maximum input boxes allowed
        var wrapper         = $(".input_fields_wrap"); //Fields wrapper
        var add_button      = $(".add_field_button"); //Add button ID


        var x = 1; //initlal text box count
        $(add_button).click(function(e){ //on add input button click
            e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input id="phone'+ x +'" type="text" name="mytext[]"/><a href="#" class="remove_field" required>Удалить</a></div>'); //add input box

                //Add mask for some input fields after add new input
                jQuery(function($){
                    $("#phone1").mask("9999999999");
                    $("#phone2").mask("9999999999");
                    $("#phone3").mask("9999999999");
                });
            }
        });

        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); $(this).parent('div').remove(); x--;
        })
    });

//----------------------------- END PHONES LIST ----------------------------------------------//

//------------------------------- PHONE MASK ------------------------------------------------//

    jQuery(function($){
        $("#phone1").mask("9999999999");
        $("#phone2").mask("9999999999");
        $("#phone3").mask("9999999999");
    });

//------------------------------- END PHONE MASK ------------------------------------------------//


//--------------------------------1-LVL CATEGORY-------------------------------------------------//
    for (var i in jsonCategory) {
        $('#category1lvl').append('<li><a id="'+jsonCategory[i].id +'" role="menuitem" tabindex="-1" href="#">' + jsonCategory[i].name + '</a></li>');
    }
//-------------------------------- END 1-LVL CATEGORY-------------------------------------------------//


//--------------------------------- 1-LVL CATEGORY ON CLICK -----------------------------------------//

    $('#category1lvl').find('li').click(function () {

        $('#container2lvl').attr("style", "visibility: hidden");
        $('#container3lvl').attr("style", "visibility: hidden");
        isComplete = 0;
        category2Id = '';
        category3Id = '';
        category1Id = $(this).find('a').attr("id");
        var category1 = $(this).text();
        $('#category1lvlText').text(category1);
        $('#category1inp').val(category1);

        if (category1 !== 'Выберите категорию') {

            $('#container2lvl').attr("style", "visibility: visible");
        } else {
            $('#container2lvl').attr("style", "visibility: hidden");
        }

        $('#category2lvl').empty();

        $('#category2lvlText').text("Выберите подкатегорию");

            for (var j in jsonCategory) {

                if (jsonCategory[j].name === category1) {
                    for (var i in jsonCategory[j].children) {
                        $('#category2lvl').append('<li><a id="' + jsonCategory[j].children[i].id + '" role="menuitem" tabindex="-1" href="#">' + jsonCategory[j].children[i].name + '</a></li>');
                    }
                    if (jsonCategory[j].children.length === 0){
                        $('#container2lvl').attr("style", "visibility: hidden");
                        isComplete = 1;
                        drawOptions(category1Id);
                    }else{
                        erase(category1Id);
                    }
            }}

//--------------------------------- END 1-LVL CATEGORY ON CLICK -----------------------------------------//

//--------------------------------- 2-LVL CATEGORY ON CLICK ---------------------------------------------//

        $('#category2lvl').find('li').click(function () {
            $('#container3lvl').attr("style", "visibility: hidden");
            isComplete = 0;
            category3Id = '';
            category2Id = $(this).find('a').attr("id");
            var category2 = $(this).text();
            $('#category2lvlText').text(category2);
            $('#category2inp').val(category2);

            if (category2 !== 'Выберите подкатегорию' && jsonSubcategory[category2Id]!==undefined && jsonSubcategory[category2Id].children!==undefined) {
                $('#container3lvl').attr("style", "visibility: visible");
            } else {
                $('#container3lvl').attr("style", "visibility: hidden");
            }

            $('#category3lvl').empty();

            $('#category3lvlText').text("Выберите подкатегорию");

                    if(jsonSubcategory[category2Id]!==undefined && jsonSubcategory[category2Id].children!==undefined){
                        for (var i in jsonSubcategory[category2Id].children){
                            $('#category3lvl').append('<li><a id="'+i +'" role="menuitem" tabindex="-1" href="#">' + jsonSubcategory[category2Id].children[i].label + '</a></li>');
                        }
                        erase(category2Id);
                    }else{
                        isComplete = 1;
                        drawOptions(category2Id);
                    }



//------------------------------------------------3-LVL CATEGORY ON CLICK-----------------------------------//
            $('#category3lvl').find('li').click(function () {
                erase(category2Id);
                category3Id = $(this).find('a').attr("id");
                var category3 = $(this).text();
                $('#category3lvlText').text(category3);
                $('#category2inp').val(category3);
                isComplete = 1;
                drawOptions(category3Id);
            });
        });
    });
//------------------------------------END 3-LVL CATEGORY ON CLICK-----------------------------------------//

//--------------------------------- END 2-LVL CATEGORY ON CLICK ------------------------------------------//

//--------------------------------- DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

    var drawOptions = function(id){
        $('#options').empty();
        for(var i in options){
            if(options[i]['c'][id]!==undefined){
                var name;
                for (j in options[i]['k']){
                    name = j;
                }

                for (j in parameters){

                    if (parameters[j]['parameter']['key'] === name && parameters[j]['parameter']['validators']['required'] === 1){
                        $('#options').append('<div><select class="prop" required name="'+name+'"  id="00'+i+'">'+ '</select></div>');
                        break;
                    }else{
                        $('#options').append('<div><select class="prop" name="'+name+'"  id="00'+i+'">'+ '</select></div>');
                        break;
                    }
                }

                $('#00'+i).on('change',function(){
                    if(this.value === 'price'){
                        $('#inptPrice').show(500);
                        $('#selectCurrency').show(500);
                    }else if (this.value === 'exchange' || this.value === 'arranged' || this.value === 'free') {
                        $('#inptPrice').hide(500);
                        $('#selectCurrency').hide(500);
                    }
                });

                for ( var j in options[i]['v']){
                    $('#00'+i).append('<option value = "'+j+'"  id ="'+ j +'">'+ options[i]['v'][j]+'</option>');
                }

            }
        }

        for ( j in parameters){
            if (parameters[j]['parameter']['type'] === "input" && parameters[j]['categories'][id]  !== undefined ){
                $('#inputs').append('<input id="'+ parameters[j]['parameter']['key'] +'" type="number" name="'+ parameters[j]['parameter']['key'] +'" placeholder="'+parameters[j]['parameter']['key']+'"/>');
            }
        }
    };

//---------------------------- END DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

//------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//
    var erase = function(id){
        $('#options').empty();
        $('#inputs').empty();
    };
//------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//

</script>

</body>
</html>