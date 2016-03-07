<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- залогиненный -->

<header class="menu">
    <div class="container2">
        <!-- не залогиненный -->
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
                                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-registration" value="1" name="k"/><span></span></label>
                                <label for="checkbox-registration"><a href="#">Мною были прочитаны все условия</a></label>
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
                                <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-contact" value="1" name="k"/><span></span></label>
                                <label for="checkbox-contact">Запомнить меня</label>
                            </div>
                            <button class="submit" type="submit">Войти</button>
                        </form>
                        <a class="contactA" href="#">Забыли пароль?</a>
                    </div>
                </div>
            </div>
            <div id="overlay"></div><!-- Пoдлoжкa -->
        </div>

        <!-- залогиненный -->
        <div class="Logged">
            <div id="overlay"></div><!-- Пoдлoжкa -->
            <div class="user">
                <img src="/resources/images/face.png" alt="face">
                <p>Петров Василий</p>
                <div>
                    <div class="clearfix"></div>
                    <a href="#">Анкета</a>
                    <a href="#">Сообщения</a>
                    <a href="#">Уведомления</a>
                    <a href="#">Контакты</a>
                    <a href="#">Выход</a>
                    <a href="#">Редактировать страницу</a>
                </div>
            </div>
            <div class="mail">
                <img src="/resources/images/mail.png" alt="mail">
                <p>1</p>
                <div class="dropDownMail">
                    <div class="mailMessage">
                        <img src="/resources/images/logo.png" alt="logo">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellat cupiditate architecto autem officiis ratione, praesentium laborum illum culpa veniam iste commodi aliquam libero quod ducimus, corporis, ipsa inventore earum sint!</p>
                    </div>
                    <div class="mailMessage">
                        <img src="/resources/images/logo.png" alt="logo">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellat cupiditate architecto autem officiis ratione</p>
                    </div>
                    <div class="answer">
                        <img src="/resources/images/logo.png" alt="logo">
                        <textarea required></textarea>
                        <button>Ответить</button>
                    </div>
                </div>
            </div>
            <div class="bell">
                <img src="/resources/images/bell.png" alt="bell">
                <div class="dropDownBell">
                    <div class="bellMessage">
                        <img src="/resources/images/logo.png" alt="logo">
                        <a href="#">Notification name!</a>
                        <p>Description of the notification :0</p>
                    </div>
                    <div class="bellMessage">
                        <img src="/resources/images/logo.png" alt="logo">
                        <a href="#">Notification name!</a>
                        <p>Description of the notification :0</p>
                    </div>
                    <div class="bellMessage">
                        <img src="/resources/images/logo.png" alt="logo">
                        <a href="#">Notification name!</a>
                        <p>Description of the notification :0</p>
                    </div>
                </div>
            </div>
            <div class="book">
                <img src="/resources/images/book.png" alt="book">
                <div class="dropDownBook">
                    <div class="friend">
                        <img src="/resources/images/userBook.png" alt="user">
                        <a href="#">Contact Name</a>
                        <img src="/resources/images/userMessage.png" alt="Message">
                    </div>
                    <div class="friend">
                        <img src="/resources/images/userBook.png" alt="user">
                        <a href="#">Contact Name</a>
                        <img src="/resources/images/userMessage.png" alt="Message">
                    </div>
                    <div class="friend">
                        <img src="/resources/images/userBook.png" alt="user">
                        <a href="#">Contact Name</a>
                        <img src="/resources/images/userMessage.png" alt="Message">
                    </div>
                    <div class="friend">
                        <img src="/resources/images/userBook.png" alt="user">
                        <a href="#">Contact Name</a>
                        <img src="/resources/images/userMessage.png" alt="Message">
                    </div>
                </div>
            </div>
            <div class="money">
                <p>00.00. грн.</p>
                <div class="dropDownMoney">
                    <button>Пополнить баланс</button>
                </div>
            </div>
        </div>
    </div>
</header>

<%--<div class="menu">--%>
<%--<div class="container2">--%>
<%--<div class="Logged">--%>
<%--<ul class="groupLi">--%>
<%--<li class="btnFace">--%>
<%--<img src="" width="32" height="33" alt="face" id="headerProfileImg">--%>
<%--<a class="menuName" href="/prioffice" id="headerProfileName"></a>--%>

<%--<div class="dropFace">--%>
<%--<ul>--%>
<%--<li><a href="/prioffice">Анкета</a></li>--%>
<%--<li><a href="/dialogues">Сообщения</a></li>--%>
<%--<li><a href="#">Уведомления</a></li>--%>
<%--<li><a href="#">Контакты</a></li>--%>
<%--<li><a href="/logout">Выход</a></li>--%>
<%--</ul>--%>
<%--<a class="edit" id="editProfileLink" href="/edit-profile">Редактировать страницу</a>--%>
<%--</div>--%>
<%--</li>--%>
<%--<il class='btnMail'>--%>
<%--<a class="btnMenu" href="#"><img src="/resources/images/mail.png" alt="mail"></a>--%>

<%--<p class="num" style="display: none"></p>--%>

<%--<div class="mailDrop">--%>
<%--<div class="mailDrop-message">--%>
<%--<a href="#"><img src="/resources/images/logo.png" alt="LOGO"></a>--%>

<%--<p class="mailDrop-message-p">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex,--%>
<%--est, officiis dolorem natus asperiores, quam qui id blanditiis sit illum dolor accusamus--%>
<%--autem? Totam rem voluptatem, laborum provident quasi deserunt.</p>--%>
<%--</div>--%>
<%--<div class="mailDrop-message">--%>
<%--<a href="#"><img src="/resources/images/logo.png" alt="LOGO"></a>--%>

<%--<p class="mailDrop-message-p">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex,--%>
<%--est, officiis</p>--%>
<%--</div>--%>
<%--<div class="answer">--%>
<%--<a href="#"><img src="/resources/images/logo.png" alt="LOGO"></a>--%>

<%--<form role="form" action="#">--%>
<%--<textarea class="form-control" rows="7" id="comment"></textarea>--%>
<%--<button type="submit" class="btnSubmit">Ответить</button>--%>
<%--</form>--%>
<%--<p>перейти в</p><a class="answer-a" href="#">диалоги</a>--%>
<%--</div>--%>
<%--</div>--%>
<%--</il>--%>
<%--<il><a class="btnMenu" href="#"><img src="/resources/images/bell.png" alt="bell"></a></il>--%>
<%--<il class='btnbook'>--%>
<%--<a class="btnMenu" href="#"><img src="/resources/images/book.png" alt="book"></a>--%>

<%--<div class="bookDrop">--%>
<%--<ul id="headerProfileContactListUl">--%>

<%--</ul>--%>
<%--</div>--%>
<%--</il>--%>
<%--</ul>--%>
<%--<ul class="money">--%>
<%--<li>--%>
<%--<a class="score" href="#"><span id="score"></span> грн.</a>--%>
<%--<ul>--%>
<%--<li>--%>
<%--<div class="balance">--%>
<%--<button type="button" class="btnBalance" data-toggle="modal" data-target="#myModal">--%>
<%--Пополнить баланс--%>
<%--</button>--%>

<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="myModal" role="dialog">--%>
<%--<div class="modal-dialog">--%>
<%--<div class="modal-content">--%>
<%--<form action="#" id="formModal" role="form">--%>
<%--<div class="selectBox mod">--%>
<%--<select class="form-control">--%>
<%--<option>Liq Pay</option>--%>
<%--<option>2</option>--%>
<%--<option>3</option>--%>
<%--<option>4</option>--%>
<%--<option>5</option>--%>
<%--</select>--%>
<%--</div>--%>
<%--<input id="header_money_amount" type="text" class="form-control"--%>
<%--placeholder="Введите сумму">--%>
<%--</form>--%>
<%--<form method="post" action="https://www.liqpay.com/api/checkout"--%>
<%--accept-charset="utf-8">--%>
<%--<input id="liq-pay-data" type="hidden" name="data"--%>
<%--value="eyJhbW91bnQiOjEwMCwiY3VycmVuY3kiOiJVQUgiLCJkZXNjcmlwdGlvbiI6ItCf0L7Qv9C+0LvQvdC10L3QuNC1INCx0LDQu9Cw0L3RgdCwIiwib3JkZXJfaWQiOiJsM2Q2d1VvR3Rlc3QiLCJwdWJsaWNfa2V5IjoiaTc0MDQ0MTgyODM5Iiwic2FuZGJveCI6IjEiLCJzZXJ2ZXJfdXJsIjoiaHR0cDpcL1wvYmFuay10a2FuaS5yaGNsb3VkLmNvbVwvY2FsbGJhY2siLCJ2ZXJzaW9uIjoiMyJ9"/>--%>
<%--<input id="liq-pay-signature" type="hidden" name="signature"--%>
<%--value="DxXg8vXCVuw39G1Qvk8hmLyad6o="/>--%>
<%--<button type="submit" class="submit">Пополнить</button>--%>
<%--</form>--%>
<%--<div class="modal-footer">--%>
<%--<button type="button" class="btn btn-default" data-dismiss="modal">--%>
<%--Отмена--%>
<%--</button>--%>
<%--</div>--%>

<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<!-- End modal -->--%>

<%--</div>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<div id="overlay"></div>--%>
<%--</div>--%>
<%--</div>--%>