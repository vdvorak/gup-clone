<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">


</head>
<body class="center-block">

<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->
<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>
<!--END 2nd section -->


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
            <div class="row" style="background-color: #ffffff; padding: 15px; margin-top: 25px;border: 5px solid #9acc66; border-radius: 10px;">
                <h2 class="dop-inf">Дополнительная информация</h2>

                <form id="mainInput" action="" method="post">
                    <div class="col-xs-8" style="padding-left: 5px; padding-right: 5px;">

                        <div class="input-group">
                            <div class="left-tag">
                                <p>Тип аккаунта</p>
                            </div>
                            <div class="right-tag">
                                <select id="selectCategory">
                                    <option value="INDIVIDUAL">физическое лицо</option>
                                    <option value="ENTREPRENEUR">СПД</option>
                                    <option value="LEGAL_ENTITY">юридическое лицо</option>
                                </select>
                            </div>
                        </div>


                       <!-- <div class="input-group">
                            <div class="left-tag">
                                <p>Сфера деятетльности/ + КВЄДы</p>
                            </div>
                            <div class="right-tag">
                                <select id="selectNace">
                                    <option value="household">торговля (00.02.03)</option>
                                    <option value="electronics">услуги (00.05.01)</option>
                                    <option value="apartments">банковаская деятельность (00.28.09)</option>
                                </select>
                            </div>
                        </div>-->


                        <%--<div class="input-group">--%>
                            <%--<div class="left-tag">--%>
                                <%--<p>Сфера деятетльности/ + КВЄДы</p>--%>
                            <%--</div>--%>
                            <%--<div class="right-tag">--%>
                                <%--<select id="selectNace">--%>
                                    <%--<option value="household">торговля (00.02.03)</option>--%>
                                    <%--<option value="electronics">услуги (00.05.01)</option>--%>
                                    <%--<option value="apartments">банковаская деятельность (00.28.09)</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>


                        <div class="input-group">
                            <div class="left-tag">
                                <p>ФИО/название компании</p>
                             </div>
                            <div class="right-tag">
                                <input id="inputTitle" name="username" type="text" class="form-control input-sm" value="${profile.username}" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <div class="left-tag">
                                <p>Должность</p>
                            </div>
                            <div class="right-tag">
                                <input id="inputPosition" type="text" class="form-control input-sm" value="${profile.contact.position}" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <div id="emailGroup" class="add_email_field_button">
                                <div class="left-tag">
                                    <p>E-mail</p>
                                </div>
                                <div class="right-tag">
                                    <img src="/resources/img/add-icon.png" title="Добавить e-mail" class="input_email_fields_wrap ">
                                    <c:forEach items="${profile.contact.contactEmails}" var="id" varStatus="loopCounter">
                                        <div><input type="email" value="${id}"><a href="#" class="remove_field" required>Удалить</a></div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <div class="left-tag">
                                <p>Основной номер телефона</p>
                            </div>
                            <div class="right-tag">
                                <div style="display: inline-block;">
                                    <input class="ep-mainPhone"type="text" name="mainPhoneNumber" placeholder="Введите номер телефона" value="${profile.mainPhoneNumber}">
                                    <img src="/resources/img/add-icon.png" title="Добавить телефон" class="add_field_button">
                                </div>

                                <div id="phoneGroup" class="input_fields_wrap">

                                    <c:forEach items="${profile.contact.contactPhones}" var="id">
                                        <div><input type="text" value="${id}"><a href="#" class="remove_field" required>Удалить</a></div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <div class="input-group">
                                <div class="left-tag">
                                    <p>Skype</p>
                                </div>
                                <div class="right-tag">
                                    <div><input id="skype" type="text" value="${profile.contact.skypeUserName}"></div>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <div id = "socLinkGroup" class="input_soc_wrap">
                                <div class="left-tag">
                                    <p>Социальные сети</p>
                                </div>
                                <div class="right-tag">
                                    <a class="twit_soc_button"><img src="/resources/images/twit-icon.png"> </a>
                                    <a class="faceb_soc_button"><img src="/resources/images/faceb-icon.png"></a>
                                    <a class="vk_soc_button"><img src="/resources/images/vk-icon.png"></a>
                                    <a class="goo_soc_button"><img src="/resources/images/goo-icon.png"></a>
                                    <a class="lin_soc_button"><img src="/resources/images/link-icon.png"></a>
                                    <c:forEach items="${profile.contact.socNetLink.values()}" var="id">
                                        <div><input type="text" value="${id}"><a href="#" class="remove_field" required>Удалить</a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <div id = "webLinkGroup" class="input-link-wrap">
                                <div class="left-tag">
                                    <p>Web ссылки</p>
                                </div>
                                <div class="right-tag">
                                    <img src="/resources/img/add-icon.png" title="Добавить ссылку" class="add_link_button">
                                    <c:forEach items="${profile.contact.linkToWebSite}" var="id">
                                        <div><input type="text" value="${id}"><a href="#" class="remove_field" required>Удалить</a></div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <div style="vertical-align: top;" class="left-tag">
                                <p>О себе</p>
                            </div>
                            <div class="right-tag">
                                <textarea id="profileDescription" rows="10" cols="45" placeholder="3000 символов" required>${profile.contact.aboutUs}</textarea>
                            </div>
                        </div>

                        <input class="ep-submitButton" type="submit" value="Сохранить">
                    </div>
                </form>

                <c:choose>
                    <c:when test="${not empty profile.contact.pic}">
                        <img id="avatar" src="/api/rest/fileStorage/PROFILE/file/read/id/${profile.contact.pic}" width="200"
                             height="200">
                    </c:when>
                    <c:otherwise>
                        <img id="avatar" src="/resources/img/defaultlogo.png" >
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


    </section>

<!-- script references -->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>

<script src="/resources/js/common.js"></script>

<sec:authorize access="isAuthenticated()">
    <script src="/resources/js/autorizedHeader.js"></script>
</sec:authorize>


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
                $(wrapper).append('<div><input id="phone' + x + '" type="text"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png"></a></div>'); //add input box

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
                $(wrapper).append('<div><input id="email' + x + '" type="text"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png"></a></div>'); //add input box

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
                $(wrapper).append('<div><input id="soc' + x + '" type="text"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png"></a></div>');
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
                $(wrapper).append('<div><input id="soc' + x + '" type="text"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png"></a></div>'); //add input box

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
                imgId = data.id;
                $('#avatar').attr("src", "/api/rest/fileStorage/PROFILE/file/read/id/" + imgId).attr("style", "width:200px; height:200px;");
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
            },
            error: function (response) {
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

        profile.contact = {};
        profile.contact.contactPhones = phoneArr;
        profile.contact.contactEmails = emailArr;
        profile.contact.linkToWebSite = webLinks;
        profile.contact.linkToSocial = socLinks;
        profile.contact.type = $('#selectCategory').value;
        profile.contact.position = $('#inputPosition').val();
        profile.contact.skypeUserName = $('#skype').val();
        profile.contact.aboutUs = $('#profileDescription').val();
        profile.contact.pic = imgId;

        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/update/",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(profile),
            success: function (response) {
                window.location.href = '/prioffice';
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
