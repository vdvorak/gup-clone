<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="collapse navbar-collapse">
    <ul class="nav navbar-nav">
      <li><a href="#">Ваш логин:</a></li>
      <li><a href="#">Моя страница</a></li>
      <li><a href="#">Друзья</a></li>
      <li><a href="#">Сообщения</a></li>
      <li><a href="#">Сообщения</a></li>
      <li>
        <ul class="nav navbar-nav">
          <li><a href="#">Вступить в организацию</a></li>
          <li>
            <div class="dropdown">
              <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="margin-top: 8px">
                Баланс
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li>
                  <!-- Button trigger modal -->
                  <button type="button" class="btn btn-default btn-md" data-toggle="modal" data-target="#myModal" style="margin-left: 25px">
                    Пополнить баланс
                  </button>

                  <!-- Modal -->
                  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel">Пополнение</h4>
                        </div>
                        <div class="modal-body">
                            <div class="col-xs-2">
                              <select class="form-control-static">
                                <option>Liq Pay</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                              </select>
                            </div>
                            <div class="col-xs-4">
                              <input id="amount" type="text" class="form-control" placeholder="Введите сумму">
                            </div>
                            <form method="post" action="https://www.liqpay.com/api/checkout" accept-charset="utf-8">
                              <input id="liq-pay-data" type="hidden" name="data" value="eyJhbW91bnQiOjEwMCwiY3VycmVuY3kiOiJVQUgiLCJkZXNjcmlwdGlvbiI6ItCf0L7Qv9C+0LvQvdC10L3QuNC1INCx0LDQu9Cw0L3RgdCwIiwib3JkZXJfaWQiOiJsM2Q2d1VvR3Rlc3QiLCJwdWJsaWNfa2V5IjoiaTc0MDQ0MTgyODM5Iiwic2FuZGJveCI6IjEiLCJzZXJ2ZXJfdXJsIjoiaHR0cDpcL1wvYmFuay10a2FuaS5yaGNsb3VkLmNvbVwvY2FsbGJhY2siLCJ2ZXJzaW9uIjoiMyJ9" />
                              <input id="liq-pay-signature" type="hidden" name="signature" value="DxXg8vXCVuw39G1Qvk8hmLyad6o=" />
                              <button type="submit" class="btn btn-primary">Пополнить</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </li>
                <li role="separator" class="divider"></li>
                <li >
                  <!-- Button trigger modal -->
                  <button type="button" class="btn btn-default btn-md" data-toggle="modal" data-target="#charge" style="margin-left: 25px" >
                    Перевести деньги
                  </button>

                  <!-- Modal -->
                  <div class="modal fade" id="charge" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="mModalLabel">Перевод</h4>
                        </div>
                        <div class="modal-body">
                          <form action="/account/transfer">
                            <div class="col-xs-4">
                              <input id="recipient" type="text" class="form-control" placeholder="Получатель">
                            </div>
                            <div class="col-xs-4">
                              <input id="amountSend" type="text" class="form-control" placeholder="Введите сумму">
                            </div>
                            <button type="submit" class="btn btn-primary">Перевести</button>
                          </form>
                        </div>
                        <div class="modal-footer">

                          <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </li>
                <li role="separator" class="divider"></li>
                <li >
                  <!-- Button trigger modal -->
                  <button type="button" class="btn btn-default btn-md" data-toggle="modal" data-target="#invest" style="margin-left: 25px" >
                    Инвестировать в организацию
                  </button>

                  <!-- Modal -->
                  <div class="modal fade" id="invest" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="investModal">Инвестировать в организацию</h4>
                        </div>
                        <div class="modal-body">
                          <form action="/account/investment" method="post">
                            <div class="col-xs-4">
                              <input id="amountInvest" name="amountInvest" type="text" class="form-control" placeholder="Введите сумму">
                            </div>
                            <button type="submit" class="btn btn-primary">Инвестировать</button>
                          </form>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </li>
                <li role="separator" class="divider"></li>
                <li >
              </ul>
            </div>
          </li>
          <li>
            <div class="dropdown">

              <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="margin-top: 8px">
                Меню
                <span class="caret"></span>
              </button>

              <ul class="dropdown-menu " aria-labelledby="dropdownMenu2">
                <li>
                  <a href="/edit-profile">Редактировать профиль</a>

                </li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Укр/Рус</a></li>
                <li >
                  <a href="/logout">Выход</a>
                </li>

              </ul>
            </div>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</nav>