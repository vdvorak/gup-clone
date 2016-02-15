<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Редактирование профиля</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</head>
<body>

    <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

    <jsp:include page="/WEB-INF/templates/common-header.jsp"/>

    <jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

    <jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

    <jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

    <sec:authorize access="isAuthenticated()">
        <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
    </sec:authorize>

    <div class="container">
        <div class="moreInformation">
            <p class="info-p">Дополнительная информация</p>

            <form id="uploadProfilePhotoForm">
                <input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg" style="display:none">
            </form>
            <div class="moreInformation-img" id="addProfileImg">
                <a><img src="resources/images/pluse.png" alt="plus"></a>
            </div>
            <div id="moreInformation-form">
                <label class="label-form-info" for="select-type">Тип аккаунта</label>
                <div id="selectBox-info-type">
                    <select id="select-type" class="form-control">
                        <option value="">Выберете тип</option>
                        <option value="INDIVIDUAL">Физическое лицо</option>
                        <option value="LEGAL_ENTITY">Юридическое лицо</option>
                        <option value="ENTREPRENEUR">Частный предпрениматель</option>
                    </select>
                </div>

                <div class="clearfix"></div>

                <label class="label-form-info" for="select-sphere">Cфера деятельности/+квэды</label>
                <div id="selectBox-info-sphere">
                    <select id="select-sphere" class="form-control">
                        <option>Выберете сферу</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>

                <div class="clearfix"></div>

                <label class="label-form-info" for="nameCompany">ФИО/Название компании</label>
                <input id="nameCompany" class="form-info-input" name='name' type="text">

                <div class="clearfix"></div>

                <label for="position" class="label-form-info">Должность</label>
                <input id="position" type="text" name='position' class="form-info-input">

                <div class="clearfix"></div>

                <label for="main-email-info" class="label-form-info">Основной E-mail</label>
                <input id="main-email-info" type="email" name='email' class="form-info-input">

                <div class="clearfix"></div>

                <div class="title-email" data-title="Добавить e-mail"><img class="email-plus" src="resources/images/pluse.png" alt="plus"></div>
                <label for="email-info" class="label-form-info">E-mail</label>
                <input id="email-info" type="email" name='email' class="form-info-input">

                <div class="clearfix"></div>

                <label for="main-tel-info" class="label-form-info">Основной Телефон</label>
                <input type="tel" name="tel" id="main-tel-info" class="input-info-min">

                <div class="clearfix"></div>

                <div class="title-tel" data-title="Добавить телефон"><img class="tel-plus" src="resources/images/pluse.png" alt="plus"></div>
                <label for="tel-info" class="label-form-info">Телефон</label>
                <input type="tel" name="tel" id="tel-info" class="input-info-min">

                <div class="clearfix"></div>

                <label for="skype-info" class="label-form-info">Skype</label>
                <input type="text" name="skype" id="skype-info" class="input-info-min">

                <div class="clearfix"></div>

                <label for="social-icon" class="label-form-info">Социальные сети</label>
                <input type="text" name="social-icon" id="social-icon" class="input-info-normal" placeholder="Добавить ссылку">

                <div class="clearfix"></div>
                <div class="group-info">
                    <label for="social-icon" class="label-form-info">Социальные сети</label>
                    <input type="text" name="social-icon" id="social-icon" class="input-info-normal" placeholder="Добавить ссылку">
                    <div class="social-icon-info">
                        <a href="#"><img src="resources/images/twitter-info.png" alt=""></a>
                        <a href="#"><img src="resources/images/facebook-info.png" alt=""></a>
                        <a href="#"><img src="resources/images/skype-info.png" alt=""></a>
                        <a href="#"><img src="resources/images/vk-info.png" alt=""></a>
                        <a href="#"><img src="resources/images/g+info.png" alt=""></a>
                        <a href="#"><img src="resources/images/in-info.png" alt=""></a>
                    </div>
                </div>

                <label for="web-addresses" class="label-form-info">Ссылка на сайт</label>
                <input type="url" name="web-addresses" id="web-addresses" class="input-info-normal" placeholder="Добавить ссылку">

                <div class="clearfix"></div>

                <label for="address" class="label-form-info">Адрес</label>
                <input type="text" name="address" id="address" class="input-info-normal" placeholder="Добавить ссылку">

                <div class="clearfix"></div>

                <label for="info-about-me" class="label-form-info">Описание о себе</label>
                <textarea name="info-about-me" id="info-about-me" class="textarea-about-me" maxlength="3000"></textarea>

                <div class="clearfix"></div>

                <button id="updateProfileBtn" class="info-submit">Сохранить</button>
            </div>
        </div>
    </div>


    <jsp:include page="/WEB-INF/templates/footer.jsp"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
    <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
    <script src="/resources/js/vendor/bootstrap.js"></script>
    <script src="/resources/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
    <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

    <%--<sec:authorize var="loggedIn" access="isAuthenticated()" />--%>
    <%--<c:choose>--%>
        <%--<c:when test="${loggedIn}">--%>
            <script src="/resources/js/autorizedHeader.js"></script>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
            <%--<script src="/resources/js/anonymHeader.js"></script>--%>
        <%--</c:otherwise>--%>
    <%--</c:choose>--%>

    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/logo-section.js"></script>
    <script src="/resources/js/search-bar.js"></script>


    <script>
        var profileId = "${profileId}";
        var loadedProfile = {};
        var updatedProfile = {};

        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/read/id/" + profileId + "/wholeProfile",
            statusCode: {
                200: function (profile) {
                    loadedProfile = profile;

                    updatedProfile.contact = loadedProfile.contact;
                    updatedProfile.userProfile = loadedProfile.userProfile;

//                    alert("profileId: " + profileId + ":: JSON.stringify(loadedProfile): " + JSON.stringify(loadedProfile));
//                    alert( JSON.stringify(updatedProfile));

                    if (profile.contact.pic != null) {
                        $('.moreInformation-img').css('background',
                                'url(/api/rest/fileStorage/profile/file/read/id/' + profile.contact.pic + ') no-repeat center center');
                    }

                    $('#select-type').val(profile.contact.type);
                    $('#nameCompany').val(profile.username);
                    $('#main-email-info').val(profile.email);
                    $('#main-tel-info').val(profile.mainPhoneNumber);
                    $('#skype-info').val(profile.contact.skypeUserName);
                    $('#info-about-me').val(profile.contact.aboutUs);

//                    $('#tel-info').attr("placeholder", profile.contact.O);
//                    $('#email-info').val(profile.contact.email);
                }
            }
        });

        $(document).on('click', '#updateProfileBtn', function () {
            updatedProfile.id = loadedProfile.id;

            if(loadedProfile.username !== $('#nameCompany').val()) {
                updatedProfile.username = $('#nameCompany').val();
            }

            if(loadedProfile.contact.type !== $('#select-type').val() && $('#select-type').val() !== "") {
                updatedProfile.contact.type = $('#select-type').val();
            }

            if(loadedProfile.contact.aboutUs !== $('#info-about-me').val()) {
                updatedProfile.contact.aboutUs = $('#info-about-me').val();
            }

            if(loadedProfile.contact.skypeUserName !== $('#skype-info').val()) {
                updatedProfile.contact.skypeUserName = $('#skype-info').val();
            }

//            if(loadedProfile.contact.linkToWebSite !== $('#web-addresses').val()) {
//                updatedProfile.contact.linkToWebSite.push($('#web-addresses').val());
//            }

            if(loadedProfile.mainPhoneNumber !== $('#main-tel-info').val()) {
                updatedProfile.mainPhoneNumber = $('#main-tel-info').val();
            }

//            alert("JSON.stringify(updatedProfile):: " + JSON.stringify(updatedProfile));

            $.ajax({
                type: "POST",
                url: "/api/rest/profilesService/profile/edit",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(updatedProfile),
                statusCode: {
                    200: function () {
                        window.location.href = '/profile/id/' + updatedProfile.id;
                    }
                }
            });
        });

        $(document).on('click', '#addProfileImg', function () {
            $("#uploadProfilePhotoInput").click();
        });

        $(document).on('change', '#uploadProfilePhotoInput', function (e) {
            $.ajax({
                type: "POST",
                url: "/api/rest/fileStorage/profile/file/upload?cacheImage=1", //
                data: new FormData($("#uploadProfilePhotoForm")[0]),
                enctype: 'multipart/form-data',
//                async: false,
                cache: false,
                contentType: false,
                processData: false,
                statusCode: {
                    201: function (data) {
//                        alert('201');
                        updatedProfile.contact.pic = data.id;
                        $('.moreInformation-img').css('background',
                                'url(/api/rest/fileStorage/profile/file/read/id/' + updatedProfile.contact.pic + ') no-repeat center center');

                    },
                    400: function () {
                        alert('400');
                    }
                }
            });
        });

    </script>
    <script src="/resources/js/edit-profile.js"></script>

</body>
</html>
    <%--
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
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
                                </div>


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
                                    <input id="inputPosition" type="text" class="form-control input-sm" value="${profile.contact.companyDirector}" required>
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
    --%>



    <%--
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
            profile.contact.companyDirector = $('#inputPosition').val();
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
    --%>

