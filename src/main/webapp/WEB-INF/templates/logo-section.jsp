<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="head">
    <div class="container2">
        <a href="/index">
            <div class="logo">
                <p class="logo-p">Global Ukranian Portal</p>
            </div>
        </a>

        <div class="social">
            <ul class="store">
                <li><a href="#"><img src="/resources/images/windowsStore.png" alt="Windows Store"></a></li>
                <li><a href="#"><img src="/resources/images/GooglePlay.png" alt="Google Play"></a></li>
                <li><a href="#"><img src="/resources/images/appStore.png" alt="App Store"></a></li>
            </ul>
            <button class="socialBtn" id="socialBtn">ВСТУПИТЬ В ОРГАНИЗАЦИЮ <i class="fa fa-plus"></i></button>
            <div id="refill">
                <p class="refill-p">Вступить в организацию</p>

                <div class="whichBankYouChoose">


                    <div class="brokeAss"> <!-- когда чувак нищеброд -->
                        <p class="noMoney-p">НА ВАШЕМ СЧЕТУ НЕДОСТАТОЧНО СРЕДСТВ</p>
                        <p class="noMoney-p">необходимая сумма на балансе: 50 грн</p>

                        <p class="noMoney-p2">Введите сумму:</p>

                        <form id="billForm" action="#" role="form">
                            <input type="text" name="bill" id="modal_money_amount" placeholder="350, 000">
                            <button type="submit" id="billSubmit">$</button>
                            <%-- FixMe Why we need this?--%>
                        </form>

                        <form method="post" action="https://www.liqpay.com/api/checkout"
                              accept-charset="utf-8">
                            <input id="modal_liq-pay-data" type="hidden" name="data"
                                   value="eyJhbW91bnQiOjEwMCwiY3VycmVuY3kiOiJVQUgiLCJkZXNjcmlwdGlvbiI6ItCf0L7Qv9C+0LvQvdC10L3QuNC1INCx0LDQu9Cw0L3RgdCwIiwib3JkZXJfaWQiOiJsM2Q2d1VvR3Rlc3QiLCJwdWJsaWNfa2V5IjoiaTc0MDQ0MTgyODM5Iiwic2FuZGJveCI6IjEiLCJzZXJ2ZXJfdXJsIjoiaHR0cDpcL1wvYmFuay10a2FuaS5yaGNsb3VkLmNvbVwvY2FsbGJhY2siLCJ2ZXJzaW9uIjoiMyJ9"/>
                            <input id="modal_liq-pay-signature" type="hidden" name="signature"
                                   value="DxXg8vXCVuw39G1Qvk8hmLyad6o="/>
                            <button id="modal-bill-submit" type="submit" class="submit" style="display: none;">Пополнить</button>
                        </form>

                        <p class="noMoney-p2">Пополните счет, с помощью этих банк-систем:</p>
                        <a class="choose-a" href="#"><img src="/resources/images/visa.png" alt="visa"></a>
                        <a class="choose-a" href="#"><img src="/resources/images/mastercard.png" alt="mastercard"></a>
                        <a class="choose-a" href="#"><img src="/resources/images/payPal.png" alt="payPal"></a>
                        <a class="choose-a" href="#"><img src="/resources/images/box.png" alt="box"></a>
                        <a class="choose-a2 modal-pay-liq-pay" href="#"><img src="/resources/images/privat24.png"
                                                                             alt="privat24"></a>
                        <button type="button" id="noMoneyClose">Отмена</button>
                        <button type="button" id="noMoneyStart">Вступить</button>
                    </div>


                    <div class="richAss"> <!-- когда чувак не нищеброд -->
                        <p class="rich-p">С вашего счета будет снято 000. 00 грн</p>

                        <form role="form" id="formRich" action="#">
                            <div class="richAssCheck">
                                <p>Windows XP&nbsp;</p>
                                <label class="label-checkbox-rich"><input type="checkbox" id="checkbox-rich" value="1"
                                                                          name="k"/><span></span></label>
                            </div>
                            <div class="clearfix"></div>
                            <button type="submit" id="noMoneyCloseRich">Отмена</button>
                            <button type="submit" id="noMoneyStartRich">Подтвердить</button>
                        </form>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>