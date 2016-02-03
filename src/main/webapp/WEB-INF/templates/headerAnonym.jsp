<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- не залогиненный -->
<div class="notLogged">
  <ul>
    <li><a href="#">Регистрация</a></li>
    <li><p>&nbsp;/&nbsp;</p></li>
    <li><a id='go' href="#">Вход</a></li>
  </ul>

  <div id="modal_form"><!-- Сaмo oкнo -->
    <div id="tab-container" class="tab-container">
      <ul class='etabs'>
        <li class='tab1'><a href="#registration">Регистрация</a></li>
        <li class='tab2'><a href="#entry" id>Вход</a></li>
      </ul>

      <div id="registration">
        <h2>HTML Markup for these tabs</h2>
        <!-- content -->
      </div>

      <div id="entry">
        <div class="contact_form">
          <ul class="contactUl">
            <li class="contactLi">
              <label for="email">Введите логин/ E-mail</label>
              <input type="email" name="email" id='email' placeholder="Не менее 6 символов" required>
            </li>
            <li class="contactLi">
              <label for="password">Пароль</label>
              <input type="password" name="password" id='password' required>
            </li>
            <li class="contactLi">
              <label for="checkbox">Remember me</label>
              <input type="checkbox" id="checkbox">
            </li>
            <li class="contactLi">
              <button id="loginBtn" class="submit">Войти</button>
            </li>
          </ul>
        </div>
        <a class="contactA" href="/restore">Забыли пароль?</a>
      </div>
    </div>
  </div>
  <div id="overlay"></div><!-- Пoдлoжкa -->
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