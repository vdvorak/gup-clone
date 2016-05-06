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


    <link rel="shortcut icon" href="/resources/images/favicon.ico" />
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/cropper.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/gup-custom-modal-window.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
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

<div class="container2">
    <div class="moreInformation">
        <p class="info-p">Редактирование профиля</p>

        <form id="uploadProfilePhotoForm">
            <input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg"
                   style="visibility: hidden;width:0px;height:0px;">
        </form>
        <div class="moreInformation-img" id="addProfileImg"></div>
        <div id="moreInformation-form">
            <label class="label-form-info" for="select-type">Тип аккаунта</label>

            <div id="selectBox-info-type">
                <select id="select-type" class="form-control">
                    <option value="INDIVIDUAL">Физическое лицо</option>
                    <option value="LEGAL_ENTITY">Юридическое лицо</option>
                    <option value="ENTREPRENEUR">Частный предприниматель</option>
                </select>
            </div>

            <div class="clearfix"></div>

            <div id="userNameBlock">
                <label class="label-form-info" for="userName">ФИО</label>
                <input id="userName" class="form-info-input" name='name' type="text">
            </div>
            <div id="usernameError" style="display: none;font:400 12px DroidSans;color: #f3271a">
                Допустимы имена, содержащие кириллические символы, символы латинского алфавита, цифры и дефис.
            </div>

            <div id="nameCompanyBlock">
                <label id="companyType" class="label-form-info" for="nameCompany">Название компании</label>
                <input id="nameCompany" class="form-info-input" name='name' type="text">
            </div>

            <div id="scopeOfActivityBlock">
                <label class="label-form-info" for="select-sphere">Cфера деятельности</label>
                <select id="select-sphere" class="chosen" multiple data-placeholder="Выберите отрасль" style="width: 553px;">
                </select>
                <%--<div id="selectBox-info-sphere">--%>
                    <%--<select id="select-sphere" class="form-control">--%>
                        <%--<option>Выберете сферу</option>--%>
                        <%--<option>2</option>--%>
                        <%--<option>3</option>--%>
                        <%--<option>4</option>--%>
                        <%--<option>5</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            </div>

            <div id="companyAddressBlock">
                <label for="address" class="label-form-info">Адрес компании</label>
                <input type="text" name="address" id="address" class="input-info-normal" placeholder="Добавить ссылку">
            </div>

            <div id="positionBlock">
                <label for="position" class="label-form-info">Должность</label>
                <input id="position" type="text" name='position' class="form-info-input">
            </div>

            <label for="main-email-info" class="label-form-info">Основной E-mail</label>
            <input id="main-email-info" type="email" required name='email' class="form-info-input">

            <div class="clearfix"></div>

            <div id="contactEmailsBlock" class="input_email_fields_wrap">
                <div id="addEmailImg" class="title-email add_email_field_button" data-title="Добавить e-mail">
                    <img class="email-plus" style="z-index: 2;" src="resources/images/pluse.png" alt="plus">
                </div>
                <label class="label-form-info">Контактный e-mail</label>
                <div class="email-input-unit">
                    <input type="email" name="myemail" class="form-info-input">
                    <%--<a href="#" class="remove_field"><img src="/resources/img/minus.png" alt="minus"></a>--%>
                </div>
            </div>
            <div id="emailError" style="display: none;font:400 12px DroidSans;color: #f3271a">
                Введен несуществующий e-mail.
            </div>
            <div>
                <label for="main-tel-info" class="label-form-info">Основной Телефон</label>
                <input type="tel" name="tel" id="main-tel-info" class="input-info-min">
            </div>

            <div class="clearfix"></div>



            <div id="contactPhonesBlock" class="input_tel_fields_wrap">

                <div id="addPhoneImg" class="title-tel add_tel_field_button" data-title="Добавить телефон">
                    <img class="tel-plus" style="z-index: 2;" src="resources/images/pluse.png" alt="plus">
                </div>

                <label class="label-form-info">Контактный телефон</label>

                <div class="tel-wrapper-1 tel-input-unit">
                    <input type="tel" name="mytel" class="input-info-min">
                    <%--<a href="#" class="remove_field"><img class="remove_phone" src="/resources/img/minus.png" alt="minus"></a>--%>
                </div>

            </div>

            <label for="skype-info" class="label-form-info">Skype</label>
            <input type="text" name="skype" id="skype-info" class="input-info-min">

            <div class="clearfix"></div>

            <div class="input-group">
                <div id="socLinkGroup" class="input_soc_wrap">
                    <div class="left-tag">
                        <p>Социальные сети</p>
                    </div>
                    <div class="right-tag">
                        <div class="socialSprite">
                            <a class="FACEBOOK"><img src="/resources/images/faceb-icon.png"></a>
                            <a class="TWITTER"><img src="/resources/images/twit-icon.png"> </a>
                            <a class="VKONTAKTE"><img src="/resources/images/vk-icon.png"></a>
                            <a class="GOOGLEPLUS"><img src="/resources/images/goo-icon.png"></a>
                            <a class="LINKEDIN"><img src="/resources/images/link-icon.png"></a>
                        </div>

                        <div class="soc-input-group">
                            <div class="soc-input-wrap show-inp"><input class="soc-input input-info-min" type="text" name="FACEBOOK"
                                                                        value=""
                                                                        placeholder="Ссылка на FACEBOOK"><span
                                    class="remove_field"><img
                                    src="/resources/img/minus.png" alt="minus"></span>
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

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>
<script src="/resources/js/cropper.js"></script>
<script>
    var flag = '${flag}';
</script>
<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var profileId = "${profileId}";
</script>
<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>
<script src="/resources/js/edit-profile.js"></script>

</body>
</html>