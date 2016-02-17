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

    <div class="container2">
        <div class="moreInformation">
            <p class="info-p">Редактирование профиля</p>

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
                        <option value="INDIVIDUAL">Физическое лицо</option>
                        <option value="LEGAL_ENTITY">Юридическое лицо</option>
                        <option value="ENTREPRENEUR">Частный предпрениматель</option>
                    </select>
                </div>

                <div class="clearfix"></div>

                <div id="nameCompanyBlock">
                    <label class="label-form-info" for="nameCompany">Название компании</label>
                    <input id="nameCompany" class="form-info-input" name='name' type="text">
                    <div class="clearfix"></div>
                </div>

                <div id="scopeOfActivityBlock">
                    <label class="label-form-info" for="select-sphere">Cфера деятельности</label>
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
                </div>

                <div id="companyAddressBlock">
                    <label for="address" class="label-form-info">Адрес компании</label>
                    <input type="text" name="address" id="address" class="input-info-normal" placeholder="Добавить ссылку">
                    <div class="clearfix"></div>
                </div>

                <div id="positionBlock">
                    <label for="position" class="label-form-info">Должность</label>
                    <input id="position" type="text" name='position' class="form-info-input">
                    <div class="clearfix"></div>
                </div>

                <div id="userNameBlock">
                    <label class="label-form-info" for="userName">ФИО</label>
                    <input id="userName" class="form-info-input" name='name' type="text">
                    <div class="clearfix"></div>
                </div>


                <label for="main-email-info" class="label-form-info">Основной E-mail</label>
                <input id="main-email-info" type="email" name='email' class="form-info-input">

                <div class="clearfix"></div>

                <div id="contactEmailsBlock">
                    <div id="addEmailImg" class="title-email" data-title="Добавить e-mail">
                        <img class="email-plus" src="resources/images/pluse.png" alt="plus">
                    </div>
                    <label for="email-info-1" class="label-form-info">Контактный e-mail</label>

                    <input id="email-info-1" type="email" name='contactEmail' class="form-info-input">
                    <div class="clearfix"></div>
                </div>

                <label for="main-tel-info" class="label-form-info">Основной Телефон</label>
                <input type="tel" name="tel" id="main-tel-info" class="input-info-min">

                <div class="clearfix"></div>

                <div id="contactPhonesBlock">
                    <div  id="addPhoneImg" class="title-tel" data-title="Добавить телефон">
                        <img class="tel-plus" src="resources/images/pluse.png" alt="plus">
                    </div>
                    <label for="tel-info-1" class="label-form-info">Контактный телефон</label>
                    <input type="tel" name="contactTel" id="tel-info-1" class="input-info-min">
                    <div class="clearfix"></div>
                </div>

                <label for="skype-info" class="label-form-info">Skype</label>
                <input type="text" name="skype" id="skype-info" class="input-info-min">

                <div class="group-info">
                    <label for="social-icon" class="label-form-info">Социальные сети</label>
                    <input type="text" name="social-icon" id="social-icon" class="input-info-normal" placeholder="Добавить ссылку">
                    <div class="social-icon-info">
                        <a href="#"><img class="img-responsive" src="/resources/images/twitter-info.png" alt=""></a>
                        <a href="#"><img class="img-responsive" src="/resources/images/facebook-info.png" alt=""></a>
                        <a href="#"><img class="img-responsive" src="/resources/images/skype-info.png" alt=""></a>
                        <a href="#"><img class="img-responsive" src="/resources/images/vk-info.png" alt=""></a>
                        <a href="#"><img class="img-responsive" src="/resources/images/g+info.png" alt=""></a>
                        <a href="#"><img class="img-responsive" src="/resources/images/in-info.png" alt=""></a>
                    </div>
                </div>

                <label for="web-addresses" class="label-form-info">Ссылка на сайт</label>
                <input type="url" name="web-addresses" id="web-addresses" class="input-info-normal" placeholder="Добавить ссылку">

                <div class="clearfix"></div>

                <label id="aboutMe" for="info-about-me" class="label-form-info">О себе</label>
                <label id="aboutCompany" for="info-about-me" class="label-form-info">Информация о компании</label>
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

    <script src="/resources/js/autorizedHeader.js"></script>
    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/logo-section.js"></script>
    <script src="/resources/js/search-bar.js"></script>

    <script>
        var profileId = "${profileId}";
        var loadedProfile = {};
        var updatedProfile = {};

        var emailCloneCount = 1;
        var contactPhoneCloneCount = 1;

        function setValuesForFieldsFromProfile(profile) {
//            alert('loadedProfile: ' + JSON.stringify(loadedProfile));

            if (profile.contact.pic != null) {
                $('.moreInformation-img').css('background',
                        'url(/api/rest/fileStorage/profile/file/read/id/' + profile.contact.pic + ') no-repeat center center');
            }

            $('#select-type').val(profile.contact.type);
            $('#select-type').change();

            $('#userName').val(profile.username);
            $('#position').val(profile.contact.position);
            $('#nameCompany').val(profile.contact.companyName);
            $('#main-email-info').val(profile.email);
            $('#main-tel-info').val(profile.mainPhoneNumber);
            $('#skype-info').val(profile.contact.skypeUserName);
            $('#info-about-me').val(profile.contact.aboutUs);
            $('#web-addresses').val(profile.contact.linkToWebSite);



            for	(var i = 0; i < profile.contact.contactEmails.length; i++) {
                if (i === 0) {
                    $('#email-info-1').val(profile.contact.contactEmails[i]);
                } else {
                    emailCloneCount++;

                    $('<input/>', {
                        id: 'email-info-' + (i + 1),
                        type: 'email',
                        name: 'contactEmail',
                        class: 'form-info-input',
                        value: profile.contact.contactEmails[i]
                    }).appendTo('#contactEmailsBlock');

                    $('<div/>', {
                        class: 'clearfix'
                    }).appendTo('#contactEmailsBlock');
                }
            }

            for	(var i = 0; i < profile.contact.contactPhones.length; i++) {
                if (i === 0) {
                    $('#tel-info-1').val(profile.contact.contactPhones[i]);
                } else {
                    contactPhoneCloneCount++;

                    $('<input/>', {
                        id: 'tel-info-' + (i + 1),
                        type: 'tel',
                        name: 'contactTel',
                        class: 'input-info-min',
                        value: profile.contact.contactPhones[i]
                    }).appendTo('#contactPhonesBlock');

                    $('<div/>', {
                        class: 'clearfix'
                    }).appendTo('#contactPhonesBlock');
                }
            }

//                    $('#tel-info').attr("placeholder", profile.contact.O);
        }

        function initializeProfileEntityForUpdate() {
            updatedProfile.id = loadedProfile.id;

//            if(loadedProfile.username !== $('#userName').val()) {
                updatedProfile.username = $('#userName').val();
//            }

//            if(loadedProfile.contact.type !== $('#select-type').val() && $('#select-type').val() !== "") {
                updatedProfile.contact.type = $('#select-type').val();
//            }

            updatedProfile.contact.position = $('#position').val();
            updatedProfile.contact.companyName = $('#nameCompany').val();


//            if(loadedProfile.contact.aboutUs !== $('#info-about-me').val()) {
                updatedProfile.contact.aboutUs = $('#info-about-me').val();
//            }

//            if(loadedProfile.contact.skypeUserName !== $('#skype-info').val()) {
                updatedProfile.contact.skypeUserName = $('#skype-info').val();
//            }

//            if(loadedProfile.contact.linkToWebSite !== $('#web-addresses').val()) {
                updatedProfile.contact.linkToWebSite = $('#web-addresses').val();
//            }

//            if(loadedProfile.mainPhoneNumber !== $('#main-tel-info').val()) {
                updatedProfile.mainPhoneNumber = $('#main-tel-info').val();
//            }

            var contactEmails = [];
            $("input[name=contactEmail]").each(function() {
                if($(this).val() !== '') {
                    contactEmails.push($(this).val());
                }
            });
            updatedProfile.contact.contactEmails = contactEmails;

            var contactPhones = [];
            $("input[name=contactTel]").each(function() {
                if($(this).val() !== '') {
                    contactPhones.push($(this).val());
                }
            });
            updatedProfile.contact.contactPhones = contactPhones;

        }

        $(document).ready(function() {
            $.ajax({
                type: "POST",
                url: "/api/rest/profilesService/profile/read/id/" + profileId + "/wholeProfile",
                statusCode: {
                    200: function (profile) {
                        loadedProfile = profile;
                        updatedProfile.contact = loadedProfile.contact;
                        updatedProfile.userProfile = loadedProfile.userProfile;

                        setValuesForFieldsFromProfile(profile);
                    }
                }
            });
        });

        $('#updateProfileBtn').on('click', function () {
            initializeProfileEntityForUpdate();

//            alert('updatedProfile : ' + JSON.stringify(updatedProfile));

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

        $('#select-type').on('change', function () {
            switch ($('#select-type').val()) {
                case "INDIVIDUAL":
                    $('#userNameBlock').show();
                    $('#positionBlock').show();
                    $('#aboutMe').show();
                    $('#companyAddressBlock').hide();
                    $('#scopeOfActivityBlock').hide();
                    $('#aboutCompany').hide();
                    break;
                case "LEGAL_ENTITY":
                    $('#aboutMe').hide();
                    $('#userNameBlock').hide();
                    $('#positionBlock').hide();
                    $('#aboutCompany').show();
                    $('#nameCompanyBlock').show();
                    $('#companyAddressBlock').show();
                    $('#scopeOfActivityBlock').show();
                    break;
                case "ENTREPRENEUR":
                    $('#aboutMe').hide();
                    $('#aboutCompany').show();
                    $('#positionBlock').show();
                    $('#userNameBlock').show();
                    $('#scopeOfActivityBlock').show();
                    $('#nameCompanyBlock').show();
                    $('#companyAddressBlock').show();
                    break;

                default:
                    $('#userName').show();
                    $('#aboutCompany').hide();
                    $('#aboutMe').show();
            }
        });

        $('#addProfileImg').on('click', function () {
            $("#uploadProfilePhotoInput").click();
        });

        $('#addEmailImg').on('click', function () {
            if (emailCloneCount < 5) {
                $("#email-info-" + emailCloneCount).clone()
                        .attr('id', 'email-info-' + (++emailCloneCount)).val("")
                        .insertAfter("#email-info-" + (emailCloneCount - 1));
            } else {
                alert('Максимум 5 контактных телефонов');
            }
        });

        $('#addPhoneImg').on('click', function () {
            if (contactPhoneCloneCount < 5) {
                $("#tel-info-" + contactPhoneCloneCount).clone()
                        .attr('id', 'tel-info-' + (++contactPhoneCloneCount)).val("")
                        .insertAfter("#tel-info-" + (contactPhoneCloneCount - 1));
            } else {
                alert('Максимум 5 контактных email-ов');
            }
        });

        $('#uploadProfilePhotoInput').on('change', function () {
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

