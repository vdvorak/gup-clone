<%--
  Created by IntelliJ IDEA.
  User: RAYANT
  Date: 20.11.2015
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Вход</title>
  <link type="text/css" rel="stylesheet" href="/resources/css/login.css"/>
  <link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
  <script src='https://www.google.com/recaptcha/api.js'></script>
  <link rel="stylesheet" href="http://bootsnipp.com/dist/bootsnipp.min.css?ver=7d23ff901039aef6293954d33d23c066">
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-login">
        <div class="panel-heading">
          <div class="row">
            <div class="col-xs-6">
              <a href="#" class="active" id="login-form-link">Логин</a>
            </div>
            <div class="col-xs-6">
              <a href="#" id="register-form-link">Регистрация</a>
            </div>
          </div>
          <hr>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-lg-12">
              <form id="login-form" action="/login" method="post" role="form" style="display: block;">
                <div class="form-group">
                  <input type="text" name="email" id="login" tabindex="1" class="form-control" placeholder="Email адрес" value="">
                </div>
                <div class="form-group">
                  <input type="password" name="password" id="loginPassword" tabindex="2" class="form-control" placeholder="Пароль">
                </div>
                <input type="hidden" title="${_csrf.parameterName}"  value="${_csrf.token}" />
                ${message}
                <div class="form-group">
                  <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                      <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Вход">
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="text-center">
                        <a href="/recover" tabindex="5" class="forgot-password">Забыл пароль?</a>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
              <form id="regInput" action="/registration" method="post" role="form" style="display: none;">
                <div class="form-group">
                  <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email адрес" value="" onchange="checkEmail()" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$">
                  <span id="responseEmail" style="margin-left: 10px;"></span>
                </div>
                <div class="form-group">
                  <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Пароль">
                </div>
                <div class="form-group">
                  <input type="password" name="confirm-password" id="confirm-password" onkeyup="checkPass()" tabindex="2" class="form-control" placeholder="Подтвердите пароль">
                </div>
                <div class="form-group">
                  Прочитал и согласен с правилами
                  <input id="accept" type="checkbox" placeholder="Подтвердите пароль" required>
                  <div class="g-recaptcha" data-sitekey="6Lc6KxETAAAAAKK9s-YUlVdfAUZx-G3KpohgGqfJ" ></div>
                  <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                      <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Зарегистрироваться" disabled>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/pgwslideshow.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

<script>

  $(function() {
    $('#login-form-link').click(function(e) {
      $("#login-form").delay(100).fadeIn(100);
      $("#regInput").fadeOut(100);
      $('#register-form-link').removeClass('active');
      $(this).addClass('active');
      e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
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

    if(document.getElementById('password').value !== document.getElementById('confirm-password').value){
      alert("Пароли не совпадают");
      return;
    }

    if ($("#email").attr("value") == "no"){
      alert("Введите другой email");
      return;
    }

    var b = decodeURIComponent(mainForm);
    var c = JSON.parse('{"' + decodeURI(b).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
    alert(JSON.stringify(c));
    if (c['g-recaptcha-response']!=='' ){
      $.ajax({
        type: "POST",
        url: "/registration",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(c),
        success: function (response) {
          window.location.href = "/login"
        },
        error : function (response) {
          window.location.href = "/login"
        }
      });
    }else{
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
        }
      }
    });
  }
</script>
</html>
