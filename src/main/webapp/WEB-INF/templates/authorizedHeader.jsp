<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- залогиненный -->

<div class="menu">
  <div class="container2">
    <div class="Logged">
      <ul class="groupLi">
        <li class="btnFace">
          <img src="" width="32" height="33" alt="face" id="headerProfileImg">
          <a class="menuName" href="/prioffice" id="headerProfileName"></a>
          <div class="dropFace">
            <ul>
              <li><a href="/prioffice">Анкета</a></li>
              <li><a href="/dialogues">Сообщения</a></li>
              <li><a href="#">Уведомления</a></li>
              <li><a href="#">Контакты</a></li>
              <li><a href="/logout">Выход</a></li>
            </ul>
            <a class="edit" id="editProfileLink" href="/edit-profile">Редактировать страницу</a>
          </div>
        </li>
        <il class='btnMail'>
          <a class="btnMenu" href="#"><img src="/resources/images/mail.png" alt="mail"></a>
          <p class="num" style="display: none"></p>
          <div class="mailDrop">
            <div class="mailDrop-message">
              <a href="#"><img src="/resources/images/logo.png" alt="LOGO"></a>
              <p class="mailDrop-message-p">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex, est, officiis dolorem natus asperiores, quam qui id blanditiis sit illum dolor accusamus autem? Totam rem voluptatem, laborum provident quasi deserunt.</p>
            </div>
            <div class="mailDrop-message">
              <a href="#"><img src="/resources/images/logo.png" alt="LOGO"></a>
              <p class="mailDrop-message-p">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex, est, officiis</p>
            </div>
            <div class="answer">
              <a href="#"><img src="/resources/images/logo.png" alt="LOGO"></a>
              <form role="form" action="#">
                <textarea class="form-control" rows="7" id="comment"></textarea>
                <button type="submit" class="btnSubmit">Ответить</button>
              </form>
              <p>перейти в</p><a class="answer-a" href="#">диалоги</a>
            </div>
          </div>
        </il>
        <il><a class="btnMenu" href="#"><img src="/resources/images/bell.png" alt="bell"></a></il>
        <il class='btnbook'>
          <a class="btnMenu" href="#"><img src="/resources/images/book.png" alt="book"></a>
          <div class="bookDrop">
            <ul id="headerProfileContactListUl">

            </ul>
          </div>
        </il>
      </ul>
      <ul class="money">
        <li>
          <a class="score" href="#">00.00. грн.</a>
          <ul>
            <li>
              <div class="balance">
                <button type="button" class="btnBalance" data-toggle="modal" data-target="#myModal">Пополнить баланс</button>

                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <form action="#" id="formModal" role="form">
                        <div class="selectBox mod">
                          <select class="form-control">
                            <option>Liq Pay</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                          </select>
                        </div>
                        <%--<input type="text" class="form-control sear mod1" placeholder="Введите сумму">--%>
                        <input id="money_amount" type="text" class="form-control" placeholder="Введите сумму">
                        <%--<button type="submit" class="submit">Пополнить</button>--%>
                      </form>
                      <form method="post" action="https://www.liqpay.com/api/checkout" accept-charset="utf-8">
                        <input id="liq-pay-data" type="hidden" name="data" value="eyJhbW91bnQiOjEwMCwiY3VycmVuY3kiOiJVQUgiLCJkZXNjcmlwdGlvbiI6ItCf0L7Qv9C+0LvQvdC10L3QuNC1INCx0LDQu9Cw0L3RgdCwIiwib3JkZXJfaWQiOiJsM2Q2d1VvR3Rlc3QiLCJwdWJsaWNfa2V5IjoiaTc0MDQ0MTgyODM5Iiwic2FuZGJveCI6IjEiLCJzZXJ2ZXJfdXJsIjoiaHR0cDpcL1wvYmFuay10a2FuaS5yaGNsb3VkLmNvbVwvY2FsbGJhY2siLCJ2ZXJzaW9uIjoiMyJ9" />
                        <input id="liq-pay-signature" type="hidden" name="signature" value="DxXg8vXCVuw39G1Qvk8hmLyad6o=" />
                        <%--<button type="submit" class="btn btn-primary">Пополнить</button>--%>
                        <button type="submit" class="submit">Пополнить</button>
                      </form>

                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                      </div>

                    </div>
                  </div>
                </div>
                <!-- End modal -->
                
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <div id="overlay"></div>
  </div>
</div>
