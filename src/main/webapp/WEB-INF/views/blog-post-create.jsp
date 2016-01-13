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

</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">

<div>Заголовок
    <input id="title" type="text" name="title">
</div>
<br>
<div>Описание
    <textarea id="offerDescription" required></textarea>
</div>
<br>
<div id="category" class="input-group">Категория
    <select  name="category">
        <option>категория 1</option>
        <option>категория 2</option>
        <option>категория 3</option>
    </select>
</div>
<br>


        <form id="photoInput" enctype="multipart/form-data" action="/api/rest/fileStorage/OFFERS/file/read/id/${id}"
              method="post">
            <br>
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

        <div class="row">
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
<br>
<a id="submit" class="btn btn-lg btn-danger">Сохранить</a>
</body>
<!-- script references -->
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>


<script>
    var imgsArr = new Object();
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
        async: false,
        success: function (response) {
            cities = response;
        }
    });




// ---------------   END LOAD RESOURCES    --------------------------//





// --------------------- MAIN FORM CONSTRUCTION ----------------------//

    $('#submit').click(function () {
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
        mainForm.address = new Object();
        mainForm.userInfo = new Object();
        alert(JSON.stringify(mainForm));
        var uriOfferString = decodeURIComponent(mainForm);
        alert(uriOfferString);
        var offer = JSON.parse('{"' + decodeURI(uriOfferString).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
        alert("Цэ" + JSON.stringify(offer));

        offer.imagesIds = imgsArr;
        offer.canBeReserved = $("#inpReserved").is(":checked");
        offer.address = new Object();
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
        offer.userInfo = new Object();
        offer.userInfo.skypeLogin = $('#inptSkype').val();
        offer.userInfo.contactName = $('#inptAuthor').val();
        offer.userInfo.email = $('#inptMail').val();
        offer.videoUrl = $('#inptVideo').val();
        offer.userInfo.phoneNumbers = phones;

        $('#options').find('select').each(function(){
            var prop = new Object();
            prop.key = this.name;
            prop.value = this.value;
            properties.push(prop);
        });

        $('#inputs').find('input').each(function(){
            var prop = new Object();
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
            url: "/api/rest/fileStorage/NEWS/file/upload/",
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
                '<img id="img1" alt="" src="/api/rest/fileStorage/NEWS/file/read/id/' + id + '"' + 'width="150" height="150"> ' +
                '</a> <div onclick=\"deleteImg(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
            }
        });
    });

    function deleteImg(idImg) {
        delete imgsArr[idImg];
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                alert('успех');
                $('#' + idImg).remove();
            }
        });
    }

// -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


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



</script>

</body>
</html>