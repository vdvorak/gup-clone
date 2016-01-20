<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 30.12.2015
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru-RU">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>GUP</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
</head>
<body>

<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--Section with search bar-->
<jsp:include page="/WEB-INF/templates/main-search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/main-menu-slider-bar.jsp"/>
<!--END 2nd section -->

<!--3rd section news timeline-->
<section>
    <div class="main-newsTimeline-wrap">
        <div class="main-news-item-wrap">
            <a href="/index">
                <img src="/resources/img/vegan-pic1.png">
            </a>

            <div class="main-news-text"><span class="main-news-title">веган фест</span>Дать возможность людям сделать
                осознанный выбор, который улучшит качество жизни каждого, уделяя внимание простым основам нравственного,
                экологического и гармоничного образа жизни, основанного на заботе о здоровье, окружающей среде и наших
                хвостатых соседях по планете.
            </div>
            <div class="main-news-subtitles">
                <div class="main-news-views">Просмотров:<span></span></div>
                <div class="main-news-date">Дата публикации: <span></span></div>
            </div>
        </div>
        <div class="main-news-item-wrap">
            <img src="/resources/img/vegan-pic1.png">

            <div class="main-news-text"><span class="main-news-title">веган фест</span>Дать возможность людям сделать
                осознанный выбор, который улучшит качество жизни каждого, уделяя внимание простым основам нравственного,
                экологического и гармоничного образа жизни, основанного на заботе о здоровье, окружающей среде и наших
                хвостатых соседях по планете.
            </div>
            <div class="main-news-subtitles">
                <div class="main-news-views">Просмотров:<span></span></div>
                <div class="main-news-date">Дата публикации: <span></span></div>
            </div>
        </div>
        <div class="main-news-item-wrap">
            <img src="/resources/img/vegan-pic1.png">

            <div class="main-news-text"><span class="main-news-title">веган фест</span>Дать возможность людям сделать
                осознанный выбор, который улучшит качество жизни каждого, уделяя внимание простым основам нравственного,
                экологического и гармоничного образа жизни, основанного на заботе о здоровье, окружающей среде и наших
                хвостатых соседях по планете.
            </div>
            <div class="main-news-subtitles">
                <div class="main-news-views">Просмотров:<span></span></div>
                <div class="main-news-date">Дата публикации: <span></span></div>
            </div>
        </div>
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
<script src='https://www.google.com/recaptcha/api.js'></script>
<!--END of libs-->

<script>

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