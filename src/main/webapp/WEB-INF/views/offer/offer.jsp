<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>${seoMetaTags.title}: ${seoMetaTags.price}${seoMetaTags.currency}
        - ${seoMetaTags.seoCategory} ${seoMetaTags.seoAdress} на Gup</title>
    <meta name="description"
          content="Global Ukrainian Portal - твой украинский портал. Актуальные новости, объявления, интересные проекты и тендеры.">

    <meta property="og:title" content="${seoMetaTags.title}"/>
    <meta property="og:description"
          content="Global Ukrainian Portal - актуальные новости, объявления, интересные проекты и тендеры."/>

    <meta property="og:type" content="other"/>
    <meta property="og:url" content="http://gup.com.ua/obyavlenie/${seoMetaTags.seoUrl}"/>
    <c:choose>
        <c:when test="${seoMetaTags.mainImgId == ''}">
            <meta property="og:image" content="http://gup.com.ua/resources/images/no_photo.jpg"/>
        </c:when>
        <c:otherwise>
            <meta property="og:image"
                  content="http://gup.com.ua/api/rest/fileStorage/OFFERS/file/read/id/${seoMetaTags.mainImgId}"/>
        </c:otherwise>
    </c:choose>
    <meta property="og:site_name" content="Gup.com.ua - Global Ukrainian Portal"/>

    <link rel="icon" type="image/x-icon" href="http://gup.com.ua/resources/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
    <style type="text/css">
        .offer-info-slider {
        }

        .offer-info-slider .bx-wrapper {
            height: 270px;
        }

        .offer-info-slider .bx-wrapper img {
            object-fit: cover;
            height: 100%;
        }

        .offer-info-slider .bx-wrapper {
            margin: 0 auto 160px;
        }

        .offer-info-slider .bx-custom-pager {
            position: absolute;
            font-size: 0px;
            bottom: -80px;
        }

        .offer-info-slider .bx-custom-pager .bx-pager-item {
            position: relative;
            display: inline-block;
            border: 3px solid #9c6;
            border-radius: 5px;
            margin: 4px;
        }

        .offer-info-slider .bx-custom-pager .bx-pager-item a {
            position: relative;
            display: inline-block;
            width: 50px;
            height: 50px;
            overflow: hidden;
        }

        .offer-info-slider .bx-custom-pager .bx-pager-item a div {
            position: relative;
            max-width: 90px;
            width: 100%;
            height: 100%;
            background-position: center;
            background-origin: content-box;
            background-size: cover;
            border-radius: 4px;
        }
    </style>
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
            <div id="urgent" style="color: red;"></div>
            <div class="reservation">
                <div id="make-reserve" class="col-xs-6">ЗАБРОНИРОВАТЬ</div>
                <div class="col-xs-6 text-right"><span class="offer-price"></span><span class="currency"></span> <i
                        class="fa fa-lock"></i></div>
            </div>
            <div class="clearfix"></div>
            <div class="row">
                <div class="col-xs-5">
                    <a id="write-to-author" href="#" class="btn btn-block btn-success">Написать автору</a>

                    <div class="contact phone-numbers">
                        <span class="show-number">Показать номера телефонов</span>
                        <i class="fa fa-2x fa-phone"></i>
                    </div>
                    <div class="clearfix"></div>
                    <div class="contact skype-block"><p class="offer-skype"></p> <i class="fa fa-lg fa-skype"></i></div>
                </div>
                <div class="col-xs-7 no-padd-left">

                    <div class="col-xs-5 no-padd-left">
                        <span class="foto"><img id="avatar-img" src="/resources/images/doersLogo.png" alt=""></span>
                    </div>
                    <div class="col-xs-7 no-padd">
                        <p>Автор: <a class="author-link"><span class="author-name"></span></a></p>
                        <span class="contact-name-block">Контактное лицо: <span class="contact-name-block-unit"></span></span>
                    </div>
                    <span class="author-rating"></span>

                    <div class="clearfix"></div>
                    <p>
                        <a id="a-author-offers" href="#">Все обьявления автора</a>
                    </p>

                    <div class="clearfix"></div>
                </div>
            </div>

            <div class="clearfix"></div>
            <section class="offer-map"></section>
            <section class="offer-video"></section>
        </div>
        <div class="offer-info-slider">
            <div>Количество просмотров: <span class="view-counter"></span></div>
            <div>Дата создания: <span id="create-date" class="date-create"></span></div>
            <ul class="bxslider" id="offer-slider">
                <%--<li><img src="/resources/images/slider.jpg" /></li>--%>
                <%--<li><img src="/resources/images/slider.jpg" /></li>--%>
                <%--<li><img src="/resources/images/logo.png" /></li>--%>
            </ul>

            <div id="bx-pager" class="hide">
                <a data-slide-index="0" href=""><img src="/resources/images/slider.jpg"/></a>
                <a data-slide-index="1" href=""><img src="/resources/images/slider.jpg"/></a>
                <a data-slide-index="2" href=""><img src="/resources/images/slider.jpg"/></a>
            </div>

            <div class="modalSlider">
                <span>×</span>
                <img>
                <a href="" class="super_prev_knopka"></a>
                <a href="" class="super_netxt_knopka"></a>
            </div>

            <div class='tableOffer'>
                <div id="options" class="row p--------anel"></div>
            </div>

            <!-- <img src="/resources/images/slider.jpg" width="450" alt=""> -->
        </div>
    </div>

    <h2 class="title-h2-blue">Описание</h2>

    <div class="descr-text-box">
        <p class="offer-description"></p>
    </div>

    <div class="offerPluso">
        <div class="pluso" data-background="transparent" data-options="small,round,line,horizontal,counter,theme=04"
             data-services="vkontakte,odnoklassniki,facebook,twitter,google,moimir,email,print"></div>
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

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>
<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<%--     <PLUSP>     --%>

<script type="text/javascript">(function () {
    if (window.pluso)if (typeof window.pluso.start == "function") return;
    if (window.ifpluso == undefined) {
        window.ifpluso = 1;
        var d = document, s = d.createElement('script'), g = 'getElementsByTagName';
        s.type = 'text/javascript';
        s.charset = 'UTF-8';
        s.async = true;
        s.src = ('https:' == window.location.protocol ? 'https' : 'http') + '://share.pluso.ru/pluso-like.js';
        var h = d[g]('body')[0];
        h.appendChild(s);
    }
})();
</script>

<%--     </PLUSP>     --%>

<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/moment-with-locales.js"></script>

<script>
    var offerId = "${offerId}";
</script>
<script src="/resources/js/offers/offer.js"></script>
<script>
    $('.offer-info-slider .bx-wrapper img').click(function () {
        $('.modalSlider').css('display', 'block');
        var src = $(this).attr('src');
        $('.modalSlider > img').attr('src', src);
    });
    $('.modalSlider > span, .modalSlider').click(function () {
        $('.modalSlider').css('display', 'none');
    });
    $(".modalSlider > img").click(function (event) {
        event.stopPropagation();
    });
    $(".super_netxt_knopka").click(function (event) {
        event.stopPropagation();
    });
    $(".super_prev_knopka").click(function (event) {
        event.stopPropagation();
    });
</script>
</body>
</html>