<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- не залогиненный -->

<div class="menu">
  <div class="container">
    <div class="notLogged">
      <ul class="notLogged-ul">
        <li class="notLogged-li"><a id='go' href="#">Регистрация</a></li>
        <li class="notLogged-li"><p>&nbsp;/&nbsp;</p></li>
        <li class="notLogged-li"><a id='goo' href="#">Вход</a></li>
      </ul>

      <div id="modal_form"><!-- Сaмo oкнo -->
        <div id="tab-container" class="tab-container">
          <ul class='etabs'>
            <li class='tab'><a href="#registration">Регистрация</a></li>
            <li class='tab'><a href="#entry-form">Вход</a></li>
          </ul>

          <div id="registration">
            <form class="registration-form" method="post" action="#" role="form">
              <label for="registration-email">Введите логин/ E-mail</label>
              <input type="email" name="registration-email" id='registration-email' required>

              <div class="clearfix"></div>

              <label for="registration-password">Пароль</label>
              <input type="password" name="registration-password" id='registration-password' placeholder="Не менее 6 символов" required>

              <div class="clearfix"></div>

              <label for="repeat-registration-password">Повторите пароль</label>
              <input type="password" name="repeat-registration-password" id='repeat-registration-password' required>

              <div class="clearfix"></div>

              <div class="conditions">
                <label class="label-checkbox"><input type="checkbox" id="checkbox-modal" value="1" name="k"/><span></span></label>
                <label for="checkbox-modal"><a href="#">Мною были прочитаны все условия</a></label>
              </div>
              <button class="registration-submit" type="submit">Зарегистрироваться</button>
            </form>
          </div>

          <div id="entry-form">
            <form class="contact_form" method="post" action="#" role="form">
              <label for="email">Введите логин/ E-mail</label>
              <input type="email" name="email" id='email' required>
              <div class="clearfix"></div>
              <label for="password">Пароль</label>
              <input type="password" name="password" id='password' placeholder="Не менее 6 символов" required>
              <div class="clearfix"></div>
              <div class="rememberMe">
                <label class="label-checkbox"><input type="checkbox" id="checkbox-modal" value="1" name="k"/><span></span></label>
                <label for="checkbox-modal">Запомнить меня</label>
              </div>
              <button class="submit" type="submit">Войти</button>
            </form>
            <a class="contactA" href="#">Забыли пароль?</a>
          </div>
        </div>
      </div>
      <div id="overlay"></div><!-- Пoдлoжкa -->
    </div>
    <%--<div class="notLogged">--%>
      <%--<ul>--%>
        <%--<li><a href="#">Регистрация</a></li>--%>
        <%--<li><p>&nbsp;/&nbsp;</p></li>--%>
        <%--<li><a id='go' href="#">Вход</a></li>--%>
      <%--</ul>--%>

      <%--<div id="modal_form"><!-- Сaмo oкнo -->--%>
        <%--<div id="tab-container" class="tab-container">--%>
          <%--<ul class='etabs'>--%>
            <%--<li class='tab1'><a href="#registration">Регистрация</a></li>--%>
            <%--<li class='tab2'><a href="#entry" id>Вход</a></li>--%>
          <%--</ul>--%>

          <%--<div id="registration">--%>
            <%--<h2>HTML Markup for these tabs</h2>--%>
            <%--<!-- content -->--%>
          <%--</div>--%>

          <%--<div id="entry">--%>
            <%--<div class="contact_form">--%>
              <%--<ul class="contactUl">--%>
                <%--<li class="contactLi">--%>
                  <%--<label for="email">Введите логин/ E-mail</label>--%>
                  <%--<input type="email" name="email" id='email' placeholder="Не менее 6 символов" required>--%>
                <%--</li>--%>
                <%--<li class="contactLi">--%>
                  <%--<label for="password">Пароль</label>--%>
                  <%--<input type="password" name="password" id='password' required>--%>
                <%--</li>--%>
                <%--<li class="contactLi">--%>
                  <%--<label for="checkbox">Remember me</label>--%>
                  <%--<input type="checkbox" id="checkbox">--%>
                <%--</li>--%>
                <%--<li class="contactLi">--%>
                  <%--<button id="loginBtn" class="submit">Войти</button>--%>
                <%--</li>--%>
              <%--</ul>--%>
            <%--</div>--%>
            <%--<a class="contactA" href="/restore">Забыли пароль?</a>--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
      <%--<div id="overlay"></div><!-- Пoдлoжкa -->--%>
    <%--</div>--%>
  </div>
</div>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>

<script>

  $(document).ready(function() {
    $("#loginBtn").click(function(event){
      $.ajax({
        type: 'POST',
        url: '/login?email=' + $('#email').val() + '&password=' + $('#password').val(),
        success: function(){
          window.location.href = '/prioffice';
        },
        error: function () {
          alert("Пользователь с таким логином и паролем не найден. Проверьте введённые данные.")
        }
      });
    });
  });

</script>