<%--
  Created by IntelliJ IDEA.
  User: Zver
  Date: 22.12.2015
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Отказано в доступе</title>
  <link type="text/css" rel="stylesheet" href="/resources/css/login.css"/>
  <link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="http://bootsnipp.com/dist/bootsnipp.min.css?ver=7d23ff901039aef6293954d33d23c066">
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-login">
        <div class="panel-body">
          <div class="row">
            <div class="col-lg-12">
              <div class="form-group">
                Страница недоступна для неавторизованных пользователей
              </div>
              <div class="form-group">
                <a href="/index">
                  <div class="form-control btn btn-login">Вернуться на главную страницу</div>
                </a>
              </div>
              <div class="form-group">
                <a href="/loginForm">
                  <div class="form-control btn btn-login">Войти или зарегистрироваться</div>
                </a>
              </div>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

</html>