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
        <li class='tab2'><a href="#entry">Вход</a></li>
      </ul>

      <div id="registration">
        <h2>HTML Markup for these tabs</h2>
        <!-- content -->
      </div>

      <div id="entry">
        <form class="contact_form" method="post" action="#" role="form">
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
              <button class="submit" type="submit">Войти</button>
            </li>
          </ul>
        </form>
        <a class="contactA" href="#">Забыли пароль?</a>
      </div>
    </div>
  </div>
  <div id="overlay"></div><!-- Пoдлoжкa -->
</div>
