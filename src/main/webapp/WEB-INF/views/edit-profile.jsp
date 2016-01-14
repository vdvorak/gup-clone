<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 23.11.2015
  Time: 15:30
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
    <link href="/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/com.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
    <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
</head>
<body class="center-block" >
<!--<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
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
                <li><a href="#">Выход</a></li>
            </ul>
        </div>
    </div>
</nav>-->
<header class="main-head">
    <div class="top-menu">
        <div class="top-menu-userSection">
            <div class="top-menu-userpic">
                <img src="/resources/img/reallySmallUserpic.png">
            </div>
            <div class="top-menu-username">
                <a href="#">петров василий</a>
            </div>
            <div class="header-mainMenu">
                <a href="#">Моя страница</a>
                <a href="#">Сообщения</a>
                <a href="#">Уведомления</a>
                <a href="#">Тендеры</a>
                <a href="#">Проекты</a>
                <a href="#">Новости</a>
                <a href="#">Настройки</a>
                <a href="#">Выход</a>
                <div class="pageedit">
                    <a href="/edit-profile">Редактировать страницу</a>
                </div>
            </div>
        </div>
        <div class="top-menu-notifications">
            <div class="top-menu-messages">
                <img src="/resources/img/envelopeSmall.png">
            </div>
            <!--<div class="top-menu-actualMessageWrap">
                <img class="message-sender-userpic"src="">
                <div class="top-menu-incomingMessage">
                    <p>
                        <span class="top-menu-incomingMessage_date"></span>
                        Сообщение СообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщение
                    </p>
                    <a  class="top-menu-incomingMessage_answer" href="#">Ответить</a>
                </div>
            </div>
            ТВОРЧЕСКИЙ ПИЗДЕЦ, ДОДЕЛАТЬ! -->
            <div class="top-menu-notification">
                <img src="/resources/img/bellSmall.png">
            </div>

        </div>

        <div class="top-menu-userBallance">
            <div class="ballance">
                <a href="#">00.00<span>грн</span></a>
            </div>
            <div class="ballanceAdd-wraper">
                <a href="#">Пополнить баланс</a>
            </div>
        </div>
    </div>
</header>
<section class="first-sec">
    <div class="logo-wrap">
        <img src="/resources/img/logo-site.png">
        <p class="logo-title">global ukrainian portal</p>
    </div>
    <div class="shop-wrap-right">
        <div class="shop-wrap">
            <a class="main-winStore" href="#"><img src="/resources/img/wins-icon.png"></a>
            <a class="main-googlePlay" href="#"><img src="/resources/img/goop-icon.png"></a>
            <a class="main-appStore" href="#"><img src="/resources/img/apps-icon.png"></a>
        </div>
        <div class="join-button-wrap">
            <div class="join-button">
                <a href="#" title="Вступить в организацию"><img src="/resources/img/join-button.png"></a>
            </div>
        </div>
    </div>
    <div class="main-search-button-wrapper">
        <input type="text" placeholder="Поиск">
        <a href="">Найти<span class="main-search-button-icon"><img src="/resources/img/magnifire.png"></span></a>
    </div>
</section>
<!-- END1st section -->

<!--2nd section menu+slider -->
<section>
    <div class="sec-wrap">
        <div class="partials-wrap">
            <div class="main-tender-wrap">
                <p>Тендеры</p>
                <div class="main-tenderPic-wrap">
                    <a href="#"><img src="/resources/img/hammertime.png"></a>
                </div>
                <nav class="main-tender-bottom-menu">
                    <a href="#" class="active-main-menu-link">Участвовать</a>
                    <a href="#">Исполнители</a>
                </nav>
            </div>
            <div class="main-project-wrap">
                <p>Проекты</p>
                <div class="main-projectPic-wrap">
                    <a href="#"><img src="/resources/img/circul.png"></a>
                </div>
                <nav class="main-project-bottom-menu">
                    <a href="#" class="active-main-menu-link">Реструктуризация</a>
                    <a href="#">Готовый прототип</a>
                    <a href="#">Проект на бумаге</a>
                    <a href="#">Ноу-Хау</a>
                </nav>
            </div>
            <div class="main-news-wrap">
                <p>Новости</p>
                <div class="main-newsPic-wrap">
                    <a href="#"><img src="/resources/img/yagazetko.png"></a>
                </div>
                <nav class="main-news-bottom-menu">
                    <a href="#" class="active-main-menu-link">Киев</a>
                    <a href="#">Львов</a>
                    <a href="#">Харьков</a>
                    <a href="#">Запорожье</a>
                </nav>
            </div>
        </div>
        <div class="main-slider-wrap">
            <ul class="bxslider">
                <li><img src="/resources/img/slider-item.png" /></li>
                <li><img src="/resources/img/slider-item1.png" /></li>
                <li><img src="/resources/img/slider-item2.png" /></li>
            </ul>
        </div>
    </div>
</section>


    <!--category-->
    <!--<div class="row">
        <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
            <div class="btn btn-info btn-block">
                Объявления
            </div>
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
    </div>-->
    <!--category-->

    <section>
        <form id="mainInput" action="" method="post">
            <div class="row" style="background-color: #ffffff; padding: 15px; margin-top: 25px;border: 5px solid #9acc66;">
                <h2 class="dop-inf">Дополнительная информация</h2>

                <form id="mainInput" action="" method="post">
                    <div class="col-xs-8" style="padding-left: 5px; padding-right: 5px;">

                        <div class="input-group">Тип аккаунта
                            <select id="selectCategory">
                                <option value="INDIVIDUAL">физическое лицо</option>
                                <option value="ENTREPRENEUR">СПД</option>
                                <option value="LEGAL_ENTITY">юридическое лицо</option>
                            </select>
                        </div>

                        <div class="input-group">Сфера деятетльности/ + КВЄДы
                            <select id="selectNace">
                                <option value="household">торговля (00.02.03)</option>
                                <option value="electronics">услуги (00.05.01)</option>
                                <option value="apartments">банковаская деятельность (00.28.09)</option>
                            </select>
                        </div>

                        <div class="input-group">
                            <p>ФИО / название компании</p>
                            <input id="inputTitle" name="username" type="text" class="form-control input-sm"
                                   value="${profile.username}" required>
                            <br>
                            <p>Должность</p>
                            <input id="inputPosition" type="text" class="form-control input-sm"
                                   value="${profile.contact.companyDirector}" required>
                            <br>

                            <div id="emailGroup" class="input_email_fields_wrap">E-mail
                                <img src="/resources/img/add-icon.png" title="Добавить e-mail" class="add_email_field_button">
                                <c:forEach items="${profile.contact.contactEmails}" var="id" varStatus="loopCounter">
                                    <div><input type="email" value="${id}"><a href="#" class="remove_field"
                                                                              required>Удалить</a></div>
                                </c:forEach>
                            </div>
                            <br>
                            Основной номер телефона
                            <div><input type="text" name="mainPhoneNumber" placeholder="Введите номер телефона" value="${profile.mainPhoneNumber}"></div>

                            <div id="phoneGroup" class="input_fields_wrap">
                                <img src="/resources/img/add-icon.png" title="Добавить телефон" class="add_field_button">
                                <c:forEach items="${profile.contact.contactPhones}" var="id">
                                    <div><input type="text" value="${id}"><a href="#" class="remove_field"
                                                                             required>Удалить</a></div>
                                </c:forEach>
                            </div>

                            <div class="input-group">Skype
                                <div><input id="skype" type="text" value="${profile.contact.skypeUserName}"></div>
                            </div>

                            <div id = "socLinkGroup" class="input_soc_wrap">Социальные сети

                                <a  class="twit_soc_button"></a>
                                <a class="faceb_soc_button"></a>
                                <a class="vk_soc_button"></a>
                                <a class="goo_soc_button"></a>
                                <a class="lin_soc_button"></a>
                                <c:forEach items="${profile.contact.socNetLink.values()}" var="id">
                                    <div><input type="text" value="${id}"><a href="#"
                                                                             class="remove_field"
                                                                             required>Удалить</a>
                                    </div>
                                </c:forEach>
                            </div>

                            <div id = "webLinkGroup" class="input-link-wrap">Web ссылки
                                <img src="/resources/img/add-icon.png" title="Добавить ссылку" class="add_link_button">
                                <c:forEach items="${profile.contact.linkToWebSite}" var="id">
                                    <div><input type="text" value="${id}"><a href="#"
                                                                             class="remove_field"
                                                                             required>Удалить</a>
                                    </div>
                                </c:forEach>
                            </div>

                            <br>
                            О себе
                            <textarea id="profileDescription" placeholder="3000 символов" required>${profile.contact.aboutUs}</textarea>
                        </div>

                        <input type="submit" value="Сохранить">
                    </div>
                </form>

                <c:choose>
                    <c:when test="${not empty profile.contact.pic}">
                        <img id="avatar" src="/api/rest/fileStorage/PROFILE/file/read/id/${profile.contact.pic}" width="200"
                             height="200">
                    </c:when>
                    <c:otherwise>
                        <img id="avatar" src="/resources/img/pic_logo.png" width="200" height="200">
                    </c:otherwise>
                </c:choose>

                <form id="photoInput" enctype="multipart/form-data" action="/api/rest/imagesStorage/image/upload/"
                      method="post">


                    <p>
                        <label for="photofile">
                            <img class="selectPhoto" src="/resources/img/add-icon.png"/>
                        </label>
                        <input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">
                    </p>
                </form>

                <div class="imgBlock">
                    <!--uploaded images-->
                </div>
            </div>
        </form>

        <c:choose>
            <c:when test="${not empty profile.contact.pic}">
                <img id="avatar" src="/api/rest/fileStorage/PROFILE/file/read/id/${profile.contact.pic}" width="200"
                     height="200">
            </c:when>
            <c:otherwise>
                <img id="avatar" src="/resources/images/no_photo.jpg" width="200" height="200">
            </c:otherwise>
        </c:choose>

        <form id="photoInput" enctype="multipart/form-data" action="/api/rest/imagesStorage/image/upload/"
              method="post">
            <p>Загрузите ваши фотографии на сервер</p>

            <p><input type="file" name="file" multiple accept="image/*,image/jpeg">
                <input type="submit" value="Отправить"></p>
        </form>

        <div class="imgBlock">
            <!--uploaded images-->
        </div>
    </section>

<!-- script references -->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>

<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/js/common.js"></script>

<script>

    $(function() {
        $('.input-group img').tooltip({
            position: {
                my: "center bottom-20",
                at: "center top",
                using: function( position, feedback ) {
                    $( this ).css( position );
                    $( "<div>" )
                            .addClass( "arrow" )
                            .addClass( feedback.vertical )
                            .addClass( feedback.horizontal )
                            .appendTo( this );
                }
            }
        });
    });
    var imgId = '';
    var imgDel = [];
    var phoneArr = [];
    var emailArr = [];
    var socLinks = [];
    var webLinks = [];


    if ('${profile.contact.pic}'.length > 2) {
        imgId = '${profile.contact.pic}';
    }

    // ------------------------------------------- Phone numbers multi input --------------------------------------------
    // Add/Remove phone Input Fields Dynamically with jQuery
    $(document).ready(function () {
        var max_fields = 3; //maximum input boxes allowed
        var wrapper = $(".input_fields_wrap"); //Fields wrapper
        var add_button = $(".add_field_button"); //Add button ID


        var phoneArr = '${profile.contact.contactPhones}'.replace('[', '').replace(']', '').replace(' ', '').split(','); // make array from string

        var x = phoneArr.length; //initial text box count

        $(add_button).click(function (e) { //on add input button click
            e.preventDefault();
            if (x < max_fields) { //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input id="phone' + x + '" type="text"/><a href="#" class="remove_field" required>Удалить</a></div>'); //add input box

                //Add mask for some input fields after add new input
                jQuery(function ($) {
                    $("#phone1").mask("9999999999");
                    $("#phone2").mask("9999999999");
                    $("#phone3").mask("9999999999");
                });
            }
        });

        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            x--;
        })
    });

    //Add mask for some input fields on page ready
    jQuery(function ($) {
        $("#phone1").mask("9999999999");
        $("#phone2").mask("9999999999");
        $("#phone3").mask("9999999999");
    });
    // ------------------------------------------------------- Phone numbers multi input ----------------------------


    // ------------------------------------------- Email multi input --------------------------------------------
    // Add/Remove phone Input Fields Dynamically with jQuery
    $(document).ready(function () {
        var max_fields = 3; //maximum input boxes allowed
        var wrapper = $(".input_email_fields_wrap"); //Fields wrapper
        var add_button = $(".add_email_field_button"); //Add button ID


        var mailArr = '${profile.contact.contactEmails}'.replace('[', '').replace(']', '').replace(' ', '').split(','); // make array from string

        var x = mailArr.length; //initial text box count

        $(add_button).click(function (e) { //on add input button click
            e.preventDefault();
            if (x < max_fields) { //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input id="email' + x + '" type="text"/><a href="#" class="remove_field" required>Удалить</a></div>'); //add input box

            }
        });

        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            x--;
        })
    });

    // ------------------------------------------------------- Email multi input ----------------------------


    // -------------------------------------------------------Soc network links --------------------------------------------
    // Add/Remove phone Input Fields Dynamically with jQuery
    $(document).ready(function () {
        var max_fields = 5; //maximum input boxes allowed
        var wrapper = $(".input_soc_wrap"); //Fields wrapper

        var socArr = '${profile.contact.socNetLink.values()}'.replace('[', '').replace(']', '').replace(' ', '').split(','); // make array from string

        var x = socArr.length; //initial text box count


        $(".input_soc_wrap a").click(function(e){
            e.preventDefault();

            if (x < max_fields ) { //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input id="soc' + x + '" type="text"/><a href="#" class="remove_field" required>Удалить</a></div>');
            }
        });


        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            x--;
        })
    });

    //Add mask for some input fields on page ready
    jQuery(function ($) {
        $("#soc1").mask("9999999999");
        $("#soc2").mask("9999999999");
        $("#soc3").mask("9999999999");
    });
    //soc buttons options

    // ---------------------------------------------------- Soc network links --------------------------------------------

    // ------------------------------------------------------- web links --------------------------------------------
    // Add/Remove phone Input Fields Dynamically with jQuery
    $(document).ready(function () {
        var max_fields = 3; //maximum input boxes allowed
        var wrapper = $(".input-link-wrap"); //Fields wrapper
        var add_button = $(".add_link_button"); //Add button ID


        var socArr = '${profile.contact.linkToWebSite}'.replace('[', '').replace(']', '').replace(' ', '').split(','); // make array from string

        var x = socArr.length; //initial text box count

        $(add_button).click(function (e) { //on add input button click
            e.preventDefault();
            if (x < max_fields) { //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input id="soc' + x + '" type="text"/><a href="#" class="remove_field" required>Удалить</a></div>'); //add input box

                //Add mask for some input fields after add new input
                jQuery(function ($) {
                    $("#web").mask("9999999999");
                    $("#web").mask("9999999999");
                    $("#web").mask("9999999999");
                });
            }
        });

        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            x--;
        })
    });

    //Add mask for some input fields on page ready
    jQuery(function ($) {
        $("#web1").mask("9999999999");
        $("#web2").mask("9999999999");
        $("#web3").mask("9999999999");
    });
    // ---------------------------------------------------- web links --------------------------------------------

    //----------------------------------------------------- Image form -----------------------------------------------
    $(document).on('change', '#photofile', function(e) {
        var formImg = new FormData($('#photoInput')[0]);

        if (imgId !== '') {
            deleteImgFromDB(imgId);
        }

        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/PROFILE/file/upload/",
            data: formImg,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data, textStatus, request) {

                imgId = data.value;
                $('#avatar').attr("src", "/api/rest/fileStorage/PROFILE/file/read/id/" + imgId);
            }
        });
    });

    //----------------------------------------------------- Image form -----------------------------------------------

    // delete images before save changes in offer (must be called before update profile)
    function deleteImgFromDB(picId) {
        console.log(picId);
        $.ajax({
            url: '/api/rest/fileStorage/PROFILE/file/delete/id/'+ picId ,
            method: 'POST',
            success: function (response) {
                alert("Фото успешно удалены");
            },
            error: function (response) {
                alert("Удаление фоток зафейлилось");
            }
        });
    }

    // serialize form and sent it via POST method in JSON --------------------------BEGIN---------------------
    $('#mainInput').submit(function (event) {
        event.preventDefault();

        var mainForm = $('#mainInput').serialize().replace(/\+/g, '%20').replace(/%0D%0A/g, "%5C%6E");
        mainForm.contact = new Object();

        var uriProfileString = decodeURIComponent(mainForm);
        var profile = JSON.parse('{"' + decodeURI(uriProfileString).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');


        $('#emailGroup').find('input').each(function () {
            if ($(this).val().length){
                emailArr.push($(this).val());
            }
        });

        $('#phoneGroup').find('input').each(function () {
            if ($(this).val().length){
                phoneArr.push($(this).val());
            }
        });

        $('#webLinkGroup').find('input').each(function () {
            if ($(this).val().length){
                webLinks.push($(this).val());
            }
        });
        $('.input_soc_wrap').find('input').each(function () {
            if ($(this).val().length){
                socLinks.push($(this).val());
            }
        });

        profile.contact = new Object();
        profile.contact.contactPhones = phoneArr;
        profile.contact.contactEmails = emailArr;
        profile.contact.linkToWebSite = webLinks;
        profile.contact.linkToSocial = socLinks;
        profile.contact.type = $('#selectCategory').value;
        profile.contact.companyDirector = $('#inputPosition').val();
        profile.contact.skypeUserName = $('#skype').val();
        profile.contact.aboutUs = $('#profileDescription').val();
        profile.contact.pic = imgId;

        alert(JSON.stringify(profile));


        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(profile),
            success: function (response) {
                window.location.href = '/account';
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    });
    // serialize form and sent it via POST method in JSON --------------------------END---------------------
    // social links
    // decoration



</script>
</body>
</html>
