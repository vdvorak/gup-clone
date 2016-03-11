<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Страница объявления</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="/resources/images/favicon.ico" />
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
</head>
<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>


<div class="container2">
    <ul class="page-navigation" id="breadcrumbs">
    </ul>
    <ul class="page-navigation" id="offer-cities">
    </ul>

    <a href="#" id="edit-offer-link" style="display: none">
        <button class="btn btn-warning">Редактировать объявление</button>
    </a>

    <h1 class="title-h1-blue offer-title"></h1>

    <div class="offer-info-wrap">
        <div class="offer-info-box">
            <div>Колличество просмотров: <span class="view-counter"></span></div>
            <div>Дата создания: <span id="create-date" class="date-create"></span></div>
            <div id="urgent" style="color: red;"></div>
            <div class="reservation">
                <div id="make-reserve" class="col-xs-6">ЗАБРОНИРОВАТЬ</div>
                <div class="col-xs-6 text-right"><span class="offer-price"></span><span class="currency"></span> <i
                        class="fa fa-lock"></i></div>
            </div>
            <div class="clearfix"></div>
            <div class="row">
                <div class="col-xs-5">
                    <a href="#" class="btn btn-block btn-success">Написать автору</a>

                    <div class="clearfix"></div>
                    <div class="contact phone-numbers">
                        <span class="show-number">Показать номера телефонов</span>
                        <i class="fa fa-2x fa-phone"></i>
                    </div>
                    <div class="clearfix"></div>
                    <div class="contact skype-block"><span class="offer-skype"></span> <i class="fa fa-lg fa-skype"></i></div>
                </div>
                <div class="col-xs-7">
                    Автор: <a class="author-link"><span class="author-name"></span></a>
                    <br>
                    <span class="contact-name-block">Контактное лицо: <span
                            class="contact-name-block-unit"></span></span>

                    <div class="clearfix"></div>
                    <span class="author-rating"></span>

                    <div class="clearfix"></div>
                    <a href="#">Все обьявления автора</a>

                    <div class="clearfix"></div>
                    <br>

                    <div id="options" class="row panel"></div>
                </div>
            </div>
            <div class="clearfix"></div>
            <section class="offer-map">
            </section>
            <section class="offer-video">
            </section>
        </div>
        <div class="offer-info-slider">
            
            <ul class="bxslider" id="offer-slider">
              <%--<li><img src="/resources/images/slider.jpg" /></li>--%>
              <%--<li><img src="/resources/images/slider.jpg" /></li>--%>
              <%--<li><img src="/resources/images/logo.png" /></li>--%>
            </ul>

            <div id="bx-pager" class="hide">
              <a data-slide-index="0" href=""><img src="/resources/images/slider.jpg" /></a>
              <a data-slide-index="1" href=""><img src="/resources/images/slider.jpg" /></a>
              <a data-slide-index="2" href=""><img src="/resources/images/slider.jpg" /></a>
            </div>

            <!-- <img src="/resources/images/slider.jpg" width="450" alt=""> -->
        </div>
    </div>


    <h2 class="title-h2-blue">Описание</h2>

    <div class="descr-text-box">
        <p class="offer-description"></p>
    </div>

    <h2 class="title-h2-blue text-center">САМЫЕ ПОСЛЕДНИЕ ДОБАВЛЕНИЯ</h2>


    <!-- Offers in bottom -->
    <div class="container2">
        <ul class="notice-box">
        </ul>
        <!-- li pattern for clone -->
        <li id="li-offer-basic" style="display:none">
            <a href="#" class="image"><img src="/resources/images/no_photo.jpg" alt="">

                <p>Заголовок обьявления</p></a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <!-- li pattern for clone -->
        <div class="feedFooter"></div>
    </div>

</div>

<!-- The Modal -->
<%--<div id="refill">--%>
<%--<h2>Вступить в организацию</h2>--%>
<%--<div class="whichBankYouChoose">--%>
<%--<div class="no-money-reserve">--%>
<%--<!-- когда чувак нищеброд -->--%>
<%--<h2>НА ВАШЕМ СЧЕТУ НЕТ ДЕНЕГ</h2>--%>
<%--<p>Введите сумму:</p>--%>
<%--<form action="#" role="form">--%>
<%--<input type="text" name="bill" placeholder="350, 000">--%>
<%--<p>$</p>--%>
<%--</form>--%>
<%--<p>Пополните счет, с помощью этих банк-систем:</p>--%>
<%--<div class="socialBankIcons">--%>
<%--<a href="#"><img src="/resources/images/visa.png" alt="visa"></a>--%>
<%--<a href="#"><img src="/resources/images/mastercard.png" alt="mastercard"></a>--%>
<%--<a href="#"><img src="/resources/images/payPal.png" alt="payPal"></a>--%>
<%--<a href="#"><img src="/resources/images/box.png" alt="box"></a>--%>
<%--</div>--%>
<%--<a href="#"><img src="/resources/images/privat24.png" alt="privat24"></a>--%>
<%--<button type="button">Отмена</button>--%>
<%--</div>--%>
<%--<div class="yes-money-reserve">--%>
<%--<!-- когда чувак не нищеброд -->--%>
<%--<p>С вашего счета будет снято 000. 00 грн</p>--%>
<%--<form role="form" action="#">--%>
<%--<div class="richAssCheck">--%>
<%--<label for="hustle">Windows XP&nbsp;</label>--%>
<%--<label class="label-checkbox-rich">--%>
<%--<input type="checkbox" id="hustle" value="1" name="k" /><span></span>--%>
<%--</label>--%>
<%--</div>--%>
<%--<div class="clearfix"></div>--%>
<%--<button type="button" id='close'>Отмена</button>--%>
<%--<button type="submit">Подтвердить</button>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<!-- End of the Modal -->


<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>
<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>

<script>
    var offerId = "${offerId}";
</script>
<script src="/resources/js/offer.js"></script>
</body>
</html>