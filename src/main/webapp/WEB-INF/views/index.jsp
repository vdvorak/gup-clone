
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 30.12.2015
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>GUP</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
</head>
<body>

<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->
<jsp:include page="/WEB-INF/templates/main-search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/main-menu-slider-bar.jsp"/>
<!--END 2nd section -->

<%--<div class="contain">--%>
    <%--<button class="button notificationicon on">Посмотреть уведомления</button>--%>

    <%--<ul id="notificationMenu" class="notifications">--%>
        <%--<li class="titlebar">--%>
            <%--<span class="title" align="center">Уведомления</span>--%>
        <%--<span class="settings"><i class="icon-cog"></i>--%>
        <%--</span>--%>
        <%--</li>--%>
        <%--<div class="notifbox" id="notificationContainer">--%>

        <%--</div>--%>
        <%--<li class="seeall">--%>
            <%--<a>Посмотреть все</a>--%>
        <%--</li>--%>
    <%--</ul>--%>
<%--</div>--%>
<!--3rd section news timeline-->
<section>
    <div class="main-newsTimeline-wrap" id="newsContainer">
        <%--Dynamic news--%>
    </div>
</section>
<!--END 3rd section-->

<!-- BEGIN footer-->
<jsp:include page="/WEB-INF/templates/footer.jsp"/>
<!-- END footer-->

<!-- START authentication modal form -->
<jsp:include page="/WEB-INF/templates/authentification.jsp"/>
<!--END authentication modal form -->

<!-- common libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>

<!-- special libs starts here-->
<script src="/resources/js/common.js"></script>
<script src="/resources/js/index.js"></script>
<script src="/resources/js/notification.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>

<sec:authorize access="isAuthenticated()">
    <script src="/resources/js/autorizedHeader.js"></script>
</sec:authorize>
<!--END of libs-->

<script>

    var blogPostFO = {};
    blogPostFO.createdDateSortDirection = "DESC";
    blogPostFO.skip = 0;
    blogPostFO.limit = 10;

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/newsService/blogPost/read/all",
        data: JSON.stringify(blogPostFO),
        success: function (response) {
            $(document).ready(function () {
                var data = response.entities;
                for (var i = 0; i < data.length; i++) {
                    var createdDate = new Date(data[i].createdDate);
                    data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();

                    var  imagePreviewTag = '';
                    if (data[i].imagesIds !== null) {
                        for (var key in data[i].imagesIds) {
                            if (data[i].imagesIds[key] === "1") {
                                imagePreviewTag = '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + key + '" width="200" height="200">';
                            }
                        }
                    } else {
                        imagePreviewTag = '<img src="/resources/images/no_photo.jpg" width="200" height="200">';
                    }

                    $('#newsContainer').append(
                            '<div class="main-news-item-wrap">' +
                                    imagePreviewTag +
                            '<div class="main-news-text">' +
                                '<span class="main-news-title">' + data[i].title + '</span>' +
                                data[i].text +
                            '</div>' +
                            '<div class="main-news-subtitles">' +
                            '<div class="main-news-view">' + 'Просмотров: ' + data[i].views + '<span></span></div>' +
                            '<div class="main-news-date">' + 'Дата публикации:' + data[i].createdDate + '<span></span></div>' +
                            '</div>' +
                            '</div>');
                }
            });
        }
    });


    $(function () {
        $('#login-form-link').click(function (e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#regInput").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function (e) {
            $("#regInput").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
    });

    function checkPass() {
        var message = document.getElementById('confirmMessage');
        var goodColor = "#66cc66";
        var badColor = "#ff6666";
        var pass1 = document.getElementById('password');
        var pass2 = document.getElementById('confirm-password');
        if (pass1.value == pass2.value) {
            pass2.style.backgroundColor = goodColor;
        } else {
            pass2.style.backgroundColor = badColor;

        }
    }

    $('#regInput').submit(function (event) {
        var mainForm = $(this).serialize();
        console.log($(this).serialize());
        event.preventDefault();

        if (document.getElementById('password').value !== document.getElementById('confirm-password').value) {
            alert("Пароли не совпадают");
            return;
        }

        if ($("#email").attr("value") == "no") {
            alert("Введите другой email");
            return;
        }

        var b = decodeURIComponent(mainForm);
        var c = JSON.parse('{"' + decodeURI(b).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
        if (c['g-recaptcha-response'] !== '') {
            $.ajax({
                type: "POST",
                url: "/registration",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(c),
                success: function (response) {
                    window.location.href = "/index"
                },
                error: function (response) {
                    window.location.href = "/index"
                }
            });
        } else {
            alert("Введите капчу")
        }
    });

    function checkEmail() {
        $.ajax({
            type: "POST",
            url: "/login/checkEmail",
            data: $('#email').val(),
            cache: false,
            success: function (response) {
                if (response == 'true') {
                    $("#responseEmail").text("Такой email уже существует в системе").css("color", "red");
                    $("#email").attr("value", "no");
                    $('#register-submit').attr("disabled", true);
                } else {
                    $("#responseEmail").text("email свободен").css("color", "green");
                    $('#register-submit').removeAttr("disabled");
                    $("#email").removeAttr("value", "no");
                }
            }
        });
    }

    $('#login-submit').on('click', function () {

        loginServlet($('#loginEmail').val(), $('#loginPassword').val(), function (error) {
            if (!error) {
                window.location.href = '/prioffice';
            } else {
                console.log(error);
                alert("Пользователь с таким логином и паролем не найден. Проверьте введённые данные.")
            }
        })
    });

    var loginServlet = function (email, password, callback) {

        var data = {
            "email": $('#login').val(),
            "password": $('#loginPassword').val()
        };

        $.ajax({
            type: "POST",
            url: "/login",
            data: data,
            success: function () {
                window.location.href = '/prioffice';
            },
            error: function (response) {
                console.log(response);
                alert("Пользователь с таким логином и паролем не найден. Проверьте введённые данные.")
            }
        });

    };
</script>
</body>
</html>