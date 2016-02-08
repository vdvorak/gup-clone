<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 08.11.2015
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Объявления</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/com.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
    <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">

    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
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
</nav>

<div class="container-fluid">

    <!--category-->
    <div class="row" style="padding: 10px;">
        <div class="col-xs-3" style="padding-left: 5px; padding-right: 5px;">
            <a><img src="/resources/images/LOGO.png"></a>
        </div>
        <div class="col-xs-3"
             style="padding-left: 5px; padding-right: 5px; color: white; font-size: 25px;  margin-top: 30px; ">
            ПОРТАЛ<br>РОЗВИТКУ<br>УКРАЇНИ
        </div>
        <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

        </div>
        <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

        </div>
        <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

        </div>
    </div>
    <!--category-->


    <!--registration panel-->
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
        <div class="col-xs-9" style="padding-left: 5px; padding-right: 5px;">
            <form id="regInput" action="/api/rest/profilesService/profile/create" method="post">
                <div class="input-group">E-mail
                    <input id="emailReg" type="email" name="email" required>
                    <br>

                    <div class="fieldWrapper">
                        <label for="pass1">Пароль:</label>
                        <input type="password" name="password" id="pass1" placeholder="Введите пароль">
                    </div>

                    <div class="fieldWrapper">
                        <input type="password" id="pass2" onkeyup="checkPass(); return false;"
                               placeholder="Подтвердите пароль">
                    </div>
                    Прочитал и согласен с правилами
                    <input id="accept" type="checkbox" placeholder="Подтвердите пароль" required>
                    <br>
                    <input type="submit">

                    <div class="g-recaptcha" data-sitekey="6Lc6KxETAAAAAKK9s-YUlVdfAUZx-G3KpohgGqfJ" ></div>
                </div>
            </form>
        </div>
    </div>
    <!--registration panel-->
</div>

<!-- script references -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

<script>
    function checkPass() {
        var pass1 = document.getElementById('pass1');
        var pass2 = document.getElementById('pass2');
        var message = document.getElementById('confirmMessage');
        var goodColor = "#66cc66";
        var badColor = "#ff6666";

        if (pass1.value == pass2.value) {
            pass2.style.backgroundColor = goodColor;
            message.style.color = goodColor;
            message.innerHTML = "Passwords Match!"
        } else {
            pass2.style.backgroundColor = badColor;
            message.style.color = badColor;
            message.innerHTML = "Passwords Do Not Match!"
        }
    }


    $('#regInput').submit(function (event) {
        var mainForm = $(this).serialize();
        console.log($(this).serialize());
        event.preventDefault();

        var b = decodeURIComponent(mainForm);
        var c = JSON.parse('{"' + decodeURI(b).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
//        alert(JSON.stringify(c));
        if (c['g-recaptcha-response']!=='' ){
            $.ajax({
                type: "POST",
                url: "/registration/create",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(c),
                success: function (response) {
//                    alert("Это успех!")
                }
            });
        }else{
            alert("Введите капчу")
        }
    });
</script>
</body>
</html>

