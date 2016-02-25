<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
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
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
    <div class="profile"> <!-- если профиль вип то сюда надо добавлять класс vip-color-border -->
        <p class="online">Online</p>

        <div class="profile-img">
            <!-- если профиль вип то сюда надо добавлять класс vip-color-border, а если организации то organization-color-border -->
            <img class="img-responsive" id="profileImg" src="">

            <div class="vip-profile-img"> <!-- этот блок надо включить когда профиль випа, у обычного он выключен -->
                <div class="rating-vip">

                </div>
                <img class="backgroundSun" src="/resources/images/backgroundSun.png" alt="backgroundSun">
            </div>
            <div class="organization-profile-img">
                <!-- этот блок надо включить когда профиль организации, у обычного он выключен -->
                <div class="rating-organization">
                    <%--<p>000</p>--%>
                </div>
                <img class="backgroundSun" src="/resources/images/backgroundOrganization.png"
                     alt="backgroundOrganization">
            </div>
        </div>
        <p class="firstName" id="profileName"></p>

        <div id="birthDate">
            <%-- подставляется в js --%>
        </div>
        <div class="contacts">
            <div class="map">
                <p class="map-p">Адрес: г. Киев, ул. Артема 11 а, офис 115, этаж 4</p>

                <div class="mapContact">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2538.5440185405746!2d30.327353815253502!3d50.48683199262453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x472b332fd9405241%3A0x82781e1e788c6455!2z0LLRg9C7LiDQkNGA0YLQtdC80LAsIDEx0JAsIDExNSwgMTFBLCDQmtC-0YbRjtCx0LjQvdGB0YzQutC1LCDQmtC40ZfQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1sru!2sua!4v1454520039972"
                            width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
                </div>
                <div class="caretContact"></div>
            </div>
            <div class="phone" style="display:none">
            </div>
            <div class="skypeContact" style="display:none">
            </div>
            <div class="emailContact" style="display:none">
            </div>
        </div>


        <sec:authorize access="isAuthenticated()">
            <div class="contact-btn-group">
                <button class="writeMessage" id="writeMessageToProfile">Написать сообщение</button>
                <button class="addToContact" id="addProfileToContact">Добавить в контакты</button>
                <!-- если профиль вип то сюда надо добавлять класс vip-color-background -->
            </div>
        </sec:authorize>

        <div class="social-icon">
        </div>

        <div class="AboutMe">
            <p class="AboutMe-p2">О себе</p>

            <p class="AboutMe-p"></p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>

<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<sec:authorize var="loggedIn" access="isAuthenticated()"/>
<c:choose>
    <c:when test="${loggedIn}">
        <script src="/resources/js/autorizedHeader.js"></script>
    </c:when>
    <c:otherwise>
        <script src="/resources/js/anonymHeader.js"></script>
    </c:otherwise>
</c:choose>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script src="/resources/js/profile.js"></script>
<script src="/resources/js/profileUtil.js"></script>

<script>
    var profileId = "${profileId}";

    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/read/id/" + profileId,
        statusCode: {
            200: function (profile) {
                if (profile.contact.pic != null && profile.contact.pic != '') {
                    $('#profileImg').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic);
                } else {
                    $('#profileImg').attr('src', '/resources/images/no_photo.jpg');
                }

                if (profile.contact.member == true) {
                    $('.organization-profile-img').show();
                    $('.rating-organization').append('<p>' + profile.point + '</p>');
                }

                if (profile.username != null) {
                    $('#profileName').text(profile.username);
                } else {
                    $('#profileName').text("Безымянный");
                }


                // Leave it until extended version of userprofile will be -----------------

//                        if (profile.userProfile && profile.userProfile.birthDate) {
//                            var birthDate = new Date(profile.userProfile.birthDate);
//                            var readableBirthDate = birthDate.getDate() + '.' + (birthDate.getMonth() + 1) + '.' + birthDate.getFullYear();
//                            $('#birthDate').append(
//                                    '<ul class="DateOfBirth">' +
//                                        '<li>' +
//                                            '<p>Дата рождения:</p>' +
//                                        '</li>' +
//                                        '<li>' +
//                                            '<p>&nbsp;' + readableBirthDate + '</p>' +
//                                        '</li>' +
//                                    '</ul>'
//                            );
//                        }
//                        else {
//                            $('#birthDate').hide();
//                        }

                if (profile.contact.aboutUs != null) {

                    $('.AboutMe-p').append(profile.contact.aboutUs);
                } else {
                    $('.AboutMe-p').append("Пользователь еще ничего на рассказал о себе.");
                }


                if (profile.contact.contactPhones.length > 0) {
                    profile.contact.contactPhones.forEach(function (phoneNumber) {

                        $('.phone').append('<p class="phoneNumber">' + phoneNumber + '</p>');
                    });
                    $('.phone').show();
                }

                if (profile.contact.skypeUserName != null) {
                    $('.skypeContact').append('<p class="skype">Skype: ' + profile.contact.skypeUserName + '</p>');
                    $('.skypeContact').show();
                }

                if (profile.contact.contactEmails.length > 0) {
                    profile.contact.contactEmails.forEach(function (email) {
                        $('.emailContact').append('<p class="email">' + email + '</p>');
                    });
                    $('.emailContact').show();
                }

                // Draw social networks input ----------------------------------
                if (profile.contact.socNetLink) {
                    for (var soc in profile.contact.socNetLink) {
                        key = profile.contact.socNetLink[soc];
                        if (soc === "FACEBOOK") {
                            $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/facebook.png" alt="Facebook"></a>')
                        }
                        if (soc === "VKONTAKTE") {
                            $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/B.png" alt="Vk"></a>')
                        }
                        if (soc === "LINKEDIN") {
                            $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/in.png" alt="LinkedIn"></a>')
                        }
                        if (soc === "GOOGLEPLUS") {
                            $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/g+.png" alt="Google+"></a>')
                        }
                        if (soc === "TWITTER") {
                            $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/twitter.png" alt="Twitter"></a>')
                        }
                    }
                }
                // End draw social networks input ----------------------------------
            },
            404: function () {
//                        alert('Такого пользователя нет');
                window.location.href = "/profileList";
            }
        }
    });

    $('#writeMessageToProfile').on('click', function () {
        window.location.href = "/dialogue/create/with/" + profileId;
    });

    $('#addProfileToContact').on('click', function () {
        $.ajax({
            type: "POST",
            url: '/api/rest/profilesService/profile/id/' + profileId + '/myContactList/add',
            statusCode: {
                200: function () {
                    $('#addProfileToContact').hide();
                    alert('Профиль добавлен в контакты.')
//                          window.location.reload();
                }
            }
        });
    });
</script>
</body>

</html>