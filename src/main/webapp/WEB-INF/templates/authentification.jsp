<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="test-form" class="mfp-hide white-popup-block">
  <div class="row">
    <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-login">
        <div class="panel-heading">
          <div class="row">
            <div class="login-form-heder-part">
              <a href="#" class="active" id="login-form-link">Логин</a>
            </div>
            <div class="login-form-heder-part">
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
                  <input type="text" name="email" id="login" tabindex="1" class="form-control"
                         placeholder="Email адресс" value="">
                </div>
                <div class="form-group">
                  <input type="password" name="password" id="loginPassword" tabindex="2"
                         class="form-control" placeholder="Пароль">
                </div>
                <div class="form-group">
                  <div class="row">
                    <div style="margin: 20px;" class="col-sm-6 col-sm-offset-3">
                      <div id="login-submit" class="form-control btn btn-login">Вход</div>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="text-center">
                        <a href="/restore" tabindex="5" class="forgot-password">Забыли пароль?</a>
                      </div>
                    </div>
                  </div>
                </div>
              </form>

              <form id="regInput" action="/registration" method="post" role="form" style="display: none;">
                <div class="form-group">
                  <input  style="width: 50%;" type="email" name="email" id="email" tabindex="1" class="form-control"
                          placeholder="Email адрес" value="" onchange="checkEmail()"
                          pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$">
                  <span id="responseEmail"></span>
                </div>
                <div class="form-group">
                  <input style="width: 50%;" type="password" name="password" id="password" tabindex="2"
                         class="form-control" placeholder="Пароль">
                </div>
                <div class="form-group">
                  <input  style="width: 50%;" type="password" name="confirm-password" id="confirm-password"
                          onkeyup="checkPass()" tabindex="2" class="form-control"
                          placeholder="Подтвердите пароль">
                </div>
                <div class="form-group" style="color: #42abe0;font-style: italic;" >
                  Прочитал и согласен с правилами
                  <input id="accept" type="checkbox"  required>

                  <div class="g-recaptcha"
                       data-sitekey="6Lc6KxETAAAAAKK9s-YUlVdfAUZx-G3KpohgGqfJ"></div>
                  <div class="row">
                    <div class="col-sm-6 col-sm-offset-3" style="margin: 20px;">
                      <button  type="submit" id="register-submit" tabindex="4" class="form-control btn btn-register">
                        Зарегистрироваться
                      </button>
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