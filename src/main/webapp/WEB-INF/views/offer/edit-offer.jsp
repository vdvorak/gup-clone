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

    <link rel="shortcut icon" href="/resources/images/favicon.ico" />
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/dropdown-multicolumn.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/confirmDeleteAlert.css">
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

<div id="offer-container" class="container2">
    <h1 class="title-h1-blue text-center">Редактирование обьявления</h1>
    <%--<a href="#" class="pull-right">Мои обьявления</a>--%>

    <div class="clearfix"></div>

    <div class="container-fluid new-adv-box">
        <div class="row">
            <div class="col-xs-4">
                <label for="new-label-1">Заголовок<em>*</em></label>
            </div>
            <div class="col-xs-8">
                <input type="text" id="new-label-1" placeholder="Длина заголовка от 5 до 70 символов"
                       value="${offer.title}">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
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

            <div class="col-xs-4">
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
            <div class="col-xs-4">
                <label for="other-options">Дополнительно</label>
            </div>
            <div id="other-options" class="col-xs-8">

            </div>
        </div>

        <div class="row">
            <div class="col-xs-4">
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
            <div class="col-xs-4">
                <label for="new-label-3">Описание<em>*</em></label>
            </div>
            <div class="col-xs-8">
        <textarea name="" id="new-label-3" cols="30" rows="10"
                  placeholder="Длина описания от 50 до 4000 символов">${offer.description}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8 col-xs-offset-4">
                <p id="p-textlength">Количество символов: 0</p>
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
            <div class="col-xs-4">Фотографии</div>
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
            <div class="col-xs-4"></div>
            <div id="drop_zone" class="col-xs-8">
                <ul class="ul-img-container ul-img-container-green">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-4">
                <label for="inpVideo">Добавить видео</label>
            </div>
            <div class="col-xs-8">
                <input type="text" id="inpVideo" value="${offer.videoUrl}">
            </div>
        </div>
        <h2 class="title-h2-blue text-center">Контакты</h2>

        <div class="row">
            <div class="col-xs-4">
                <label for="map">Адрес</label>
            </div>
            <div class="col-xs-8">
                <div id="floating-panel">
                    <input id="address" type="text">
                    <button id="btn-save-adress">Сохранить</button>
                </div>
                <div id="map" class="offer-map"></div>
            </div>

        </div>

        <div class="row">
            <div class="col-xs-4">
                <label for="inpAuthor">Контактное лицо<em>*</em></label>
            </div>
            <div class="col-xs-5">
                <input type="text" id="inpAuthor">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="inpEmail">E-mail<em>*</em></label>
            </div>
            <div class="col-xs-5">
                <input type="text" id="inpEmail">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4">
                <label for="inpSkype">Skype</label>
            </div>
            <div class="col-xs-5">
                <input type="text" id="inpSkype">
            </div>
        </div>
        <div class="row row-telephone">
            <div class="col-xs-4">
                <label>Телефон</label>
            </div>
            <div class="col-xs-5">
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
        <div class="col-xs-4">
            <button id="btn-offer-delete">Удалить</button>
        </div>
        <div class="col-xs-4 col-xs-offset-4">
            <button id="btn-offer-save">Сохранить</button>
        </div>
    </div>

    <div class="confirm" id="confirmOfferDelete" style="display: none">
        <h1>Подтвердите удаление</h1>

        <p>Объявление будет навсегда удалено</p>
        <button id="cancelOfferDelBtn" autofocus>Отмена</button>
        <button id="confirmOfferDelBtn">Удалить</button>
    </div>

    <p>&nbsp;</p>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>
<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var flag = '${flag}';
</script>

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

<script>

    var placeKey = '${offer.address.coordinates}';
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
    function validateOffer() {
        $('.error-validation').removeClass('error-validation');

        var arrValidate = [];

        var title = $("#new-label-1").val();
        if (title.length < 5 || title.length > 70) {
            arrValidate.push($("#new-label-1"));
        }
        if (!isComplete) {
            arrValidate.push($('#categories-row'));
        }

        if ($('#offer-price-row').css('display') !== 'none' && $('select[name="price"]').val() === 'price' && !$('#offer-inpPrice').val()) {
            arrValidate.push($('#offer-inpPrice'));
        }

        var region = $('#text-region').text();
        var city = $('#text-city').text();
        if (region === 'Выберите область' || (region !== 'Вся Украина' && city === 'Выберите город')) {
            arrValidate.push($('#region-row'));
        }

        var description = $('#new-label-3').val();
        if (description.length < 50 || description.length > 4000) {
            arrValidate.push($('#new-label-3'));
        }

        if (!$('#inpEmail').val()) {
            arrValidate.push($('#inpEmail'));
        }

        if (!$('#inpAuthor').val()) {
            arrValidate.push($('#inpAuthor'));
        }
        for (var i = 0; i < arrValidate.length; i++) {
            arrValidate[i].addClass('error-validation');
        }
        if (arrValidate.length) {
            return false;
        } else {
            return true;
        }
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
            offer.id = '${offer.id}';
            offer.title = $("#new-label-1").val();
            offer.imagesIds = imgsArrResult;
            offer.canBeReserved = $("#reserve-checkbox").is(":checked");
            offer.address = {};
            offer.address.coordinates = placeKey;
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
                url: "/api/rest/offersService/offer/edit",
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

    $('#btn-offer-delete').click(function () {

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

        if (imgsArr[id] === "pic1") cloneImg.find('img').addClass('mainImg');

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

        for (var key in imgsArrResult) {
            if (imgsArrResult[key] === 'pic1') {
                hasMainImg = true;
                break;
            }
        }

        if (!hasMainImg) {
            for (var key in imgsArrResult) {
                imgsArrResult[key] = 'pic1';
                break;
            }
        }
    }
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
        var infowindow = new google.maps.InfoWindow();

        document.getElementById('btn-save-adress').addEventListener('click', function () {
            geocodeAddress(geocoder, map, infowindow);
        });

        if(placeKey) {
            geocodePlaceId(geocoder, map, infowindow);
        }
    }

    function geocodeAddress(geocoder, resultsMap, infowindow) {
        var address = document.getElementById('address').value;
        geocoder.geocode({'address': address}, function (results, status) {
            placeKey = results[0].place_id;
            if (status === google.maps.GeocoderStatus.OK) {
                resultsMap.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: resultsMap,
                    position: results[0].geometry.location
                });
                infowindow.setContent(results[0].formatted_address);
                infowindow.open(map, marker);
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }

    function geocodePlaceId(geocoder, map, infowindow) {
        var placeId = placeKey;
        console.log(placeId);
        geocoder.geocode({'placeId': placeId}, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    map.setZoom(11);
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location
                    });
                    infowindow.setContent(results[0].formatted_address);
                    infowindow.open(map, marker);

                    document.getElementById('address').value = results[0].formatted_address;
                } else {
                    window.alert('No results found');
                }
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    }

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
            var inputBlock = row.find('input').val("").parent().addClass('col-xs-offset-4');
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
    if (numberCategories > 0) {
        category1Id = categories[0];
        $('#' + category1Id + '').parent().click();
        if (numberCategories > 1) {
            category2Id = categories[1];
            $('#' + category2Id + '').parent().click();
            if (numberCategories > 2) {
                category3Id = categories[2];
                $('#' + category3Id + '').parent().click();
            }
        }
        for (var i = 0; i < properties.length; i++) {
            var curProperty = properties[i];
            $('[name="' + curProperty.key + '"]').val(curProperty.value);
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
            $('select[name="price"]').change();
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
            $('select[name="price"]').change();
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
        $('select[name="price"]').change();
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

    //------------------ BEGIN DELETE OFFER ------------------------------------//
    $('#btn-offer-delete').on('click', function () {
        $("#confirmOfferDelete").show();
    });

    $('#cancelOfferDelBtn').on('click', function () {
        $("#confirmOfferDelete").hide();
    });

    $('#confirmOfferDelBtn').on('click', function () {
        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/id/" + "${offer.id}" + "/delete",
            statusCode: {
                204: function () {
                    window.location.href = '/offers';
                }
            }
        });
    });
    //------------------ END DELETE OFFER ------------------------------------//

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
        async defer></script>
</body>
</html>

