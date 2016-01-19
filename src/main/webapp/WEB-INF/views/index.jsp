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

<sec:authorize access="isAuthenticated()" var="isAuthenticated">
    <jsp:include page="/WEB-INF/templates/authorizedHeader.jsp"/>
</sec:authorize>

<c:if test="${!isAuthenticated}">
    <jsp:include page="/WEB-INF/templates/headerAnonym.jsp"/>
</c:if>

<!--1st section with search-->
<section class="first-sec">
    <div class="logo-wrap">
        <a href="/index">
            <img src="/resources/img/logo-site.png">
        </a>

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
                    <a href="/tender"><img src="/resources/img/hammertime.png"></a>
                </div>
                <nav class="main-tender-bottom-menu">
                    <a href="#" class="active-main-menu-link">Участвовать</a>
                    <a href="/doer-create">Исполнители</a>
                </nav>
            </div>
            <div class="main-project-wrap">
                <p>Проекты</p>

                <div class="main-projectPic-wrap">
                    <a href="/projectList?pageNumber=0"><img src="/resources/img/circul.png"></a>
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
                    <a href="/blog"><img src="/resources/img/yagazetko.png"></a>
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
                <li><img src="/resources/img/slider-item.png"/></li>
                <li><img src="/resources/img/slider-item1.png"/></li>
                <li><img src="/resources/img/slider-item2.png"/></li>
            </ul>
        </div>
    </div>
</section>
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
<footer>

</footer>
<!-- hiden stuff-->

<!-- form itself -->
<jsp:include page="/WEB-INF/templates/authentification.jsp"/>
<!--END hiden stuff-->
<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>
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

//    var url = "loginForm";
        var data = {
            "email": email,
            "password": password
        };

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


    //  $('#login-submit').on('click', function () {
    //
    //    loginServlet($('#loginEmail').val(), $('#loginPassword').val(), function (error) {
    //      if (!error) {
    //        window.location.href = '/prioffice';
    //      } else {
    //        console.log(error);
    //        alert("Пользователь с таким логином и паролем не найден. Проверьте введённые данные.")
    //      }
    //    })
    //  });
    //
    //  var loginServlet = function (email, password, callback) {
    //
    //    var url = "login";
    //    var data = {
    //      "email": email,
    //      "password": password
    //    };
    //
    //    $.ajax({
    //      url: url,
    //      type: "POST",
    //      accept: "application/json",
    //      data: data,
    //      dataType: "json",
    //      success: function (data, textStatus, xhr) {
    //      },
    //      complete: function (xhr, textStatus) {
    //        if (xhr.status == 200) {
    //          window.location.href = '/prioffice';
    //        } else {
    //          alert("Пароль и логин не совпадает!")
    //        }
    //      }
    //    });
    //  }

</script>
</body>
</html>