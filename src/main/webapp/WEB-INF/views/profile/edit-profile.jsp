<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
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
    <link  href="/resources/css/cropper.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/cropper-modal-window.css">
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

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<div class="container2">
    <div class="moreInformation">
        <p class="info-p">Редактирование профиля</p>

        <form id="uploadProfilePhotoForm">
            <input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg"
                   style="display:none">
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

            <div class="input_email_fields_wrap">Контактные e-mail
                <button class="add_email_field_button">Добавить -mail</button>
                <div class="email-input-unit"><input type="text" name="myemail"><a href="#" class="remove_field"><img
                        src="/resources/img/minus.png" with="20" height="20"></a></div>
            </div>

            <%--<div id="contactEmailsBlock">--%>
            <%--<div id="addEmailImg" class="title-email" data-title="Добавить e-mail">--%>
            <%--<img class="email-plus" src="resources/images/pluse.png" alt="plus">--%>
            <%--</div>--%>
            <%--<label for="email-info-1" class="label-form-info">Контактный e-mail</label>--%>

            <%--<input id="email-info-1" type="email" name='contactEmail' class="form-info-input">--%>

            <%--<div class="clearfix"></div>--%>
            <%--</div>--%>

            <label for="main-tel-info" class="label-form-info">Основной Телефон</label>
            <input type="tel" name="tel" id="main-tel-info" class="input-info-min">

            <div class="clearfix"></div>

            <%--<div id="contactPhonesBlock">--%>
            <%--<div id="addPhoneImg" class="title-tel" data-title="Добавить телефон">--%>
            <%--<img class="tel-plus" src="resources/images/pluse.png" alt="plus">--%>
            <%--</div>--%>

            <%--<div class="tel-wrapper-1">--%>
            <%--<label for="tel-info-1" class="label-form-info">Контактный телефон</label>--%>
            <%--<input type="tel" name="contactTel" id="tel-info-1" class="input-info-min">--%>
            <%--<img class="remove_phone" src="/resources/img/minus.png" with="20" height="20"></a>--%>
            <%--<div class="clearfix"></div>--%>
            <%--</div>--%>
            <%--</div>--%>

            <div class="input_tel_fields_wrap">Контактные телефоны
                <button class="add_tel_field_button">Add More Fields</button>
                <div class="tel-input-unit"><input type="text" name="mytel"><a href="#" class="remove_field"><img
                        src="/resources/img/minus.png" with="20" height="20"></a></div>
            </div>

            <label for="skype-info" class="label-form-info">Skype</label>
            <input type="text" name="skype" id="skype-info" class="input-info-min">

            <div class="input-group">
                <div id="socLinkGroup" class="input_soc_wrap">
                    <div class="left-tag">
                        <p>Социальные сети</p>
                    </div>
                    <div class="right-tag">
                        <a class="FACEBOOK"><img src="/resources/images/faceb-icon.png"></a>
                        <a class="TWITTER"><img src="/resources/images/twit-icon.png"> </a>
                        <a class="VKONTAKTE"><img src="/resources/images/vk-icon.png"></a>
                        <a class="GOOGLEPLUS"><img src="/resources/images/goo-icon.png"></a>
                        <a class="LINKEDIN"><img src="/resources/images/link-icon.png"></a>

                        <div class="soc-input-group">
                            <div class="soc-input-wrap show-inp"><input class="soc-input" type="text" name="FACEBOOK"
                                                                        value=""
                                                                        placeholder="Ссылка на FACEBOOK"><span
                                    class="remove_field"><img
                                    src="/resources/img/minus.png" width="15" height="15"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <label for="web-addresses" class="label-form-info">Ссылка на сайт</label>
            <input type="url" name="web-addresses" id="web-addresses" class="input-info-normal"
                   placeholder="Добавить ссылку">

            <div class="clearfix"></div>

            <label id="aboutMe" for="info-about-me" class="label-form-info">О себе</label>
            <label id="aboutCompany" for="info-about-me" class="label-form-info">Информация о компании</label>
            <textarea name="info-about-me" id="info-about-me" class="textarea-about-me" maxlength="3000"></textarea>

            <div class="clearfix"></div>

            <button id="updateProfileBtn" class="info-submit">Сохранить</button>
        </div>
    </div>
</div>

<!-- The Modal -->
<div id="cropperModal" class="cropper-modal">

    <!-- Modal content -->
    <div class="cropper-modal-content">
        <div class="cropper-modal-header">
            <span>Редактирование фотографии</span>
        </div>
        <div class="cropper-modal-body drop_zone">
            <img id="cropper-image" src="/resources/images/no_photo.jpg" style="max-width: 100%">
        </div>
        <div class="cropper-modal-footer">
            <button class="cropper-btn cropper-btn-success">Готово</button>
            <button class="cropper-btn cropper-btn-cancel">Отмена</button>

        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/cropper.js"></script>

<script src="/resources/js/autorizedHeader.js"></script>
<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script>
    var profileId = "${profileId}";
</script>
<script src="/resources/js/edit-profile.js"></script>

</body>
</html>