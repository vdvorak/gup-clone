<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 28.01.2016
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>Тендер | GUP</title>
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
</head>
<body>

<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->
<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>
<!--END 2nd section -->


<!--PAGE CONTENT START-->

<div id="tender-container" class="container2">
    <div class="contentContainer" style="margin-top: 100px;"> <!-- Add "vip" class for "vip" users :3 -->
        <div class="tenderContent">
            <div class="topSection">
                <div class="statInfo">
                    <div class="publishDate tender-publish-date">Опубликовано: <span></span></div>
                    <span class="number visible">№<span class="tender-number"></span></span>

                    <div class="sum tender-expectedPrice"></div>
                    <div class="tender-veiws">Просмотров: <span></span></div>
                    <div class="tender-proposal-count">Предложений: <span></span></div>
                    <div class="tender-author-contact">Автор тендера: <a href=""></a></div>
                </div>
                <div class="clearfix"></div>
                <div class="tenderButtons">
                    <div class="participate">
                        <div class="clock">
                            <div class="time date-finish"></div>
                        </div>
                        <button id="wantToComment" type="button" class="abutton blue"
                                title="Для участия в тендере необходимо оставить предлжение.">Участвовать
                        </button>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="title tender-name">Название тендера</div>
            <p class="text tender-item-text">
            </p>

        </div>
        <div class="sliderTender">
            <ul class="bxsliderTender">
            </ul>
        </div>
        <div class="tenderFils">
        </div>
        <div class="downComments" title="Для участия в тендере необходимо оставить предложение."><p>ОСТАВИТЬ
            ПРЕДЛОЖЕНИЕ</p></div>
    </div>

    <div class="colNewsComments">
        <div class="newsComments">
            <div class="clearfix"></div>
            <p class="newsCommentsHeader">Предложение</p>

            <form action="#" role="form" id="newsCommentsForm">
                <textarea name="newsFormComments" id="newsFormComments" placeholder="Напишите своё предложение"
                          maxlength="2000" required></textarea>
                <input id="visionSelect" type="checkbox"><label for="visionSelect">Скрыть
                предложение от других участников</label>
                <button id="makePropose" class="newsFormSubmit">Отправить</button>
            </form>
            <p id="chars">2000 символов осталось</p>
        </div>
    </div>
    <div class="colComments" id="commentStart">
        <div class="comments">
            <a href="#"><img class="member-pic" src="/resources/images/doersLogo.png" alt="logo"></a>
            <a class="NameUser propose-author" href="#"></a>

            <p class="propose-date">Дата публикации: <span></span></p>

            <p class="commentUser poropse-text"></p>
            <button class="chooseWinner">Выбрать победителем</button>
            <%--<p class="commentUser"></p>--%>
        </div>
    </div>
    <div class="clearfix"></div>
</div>

<!--PAGE CONTENT END-->

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
<script>
    var tenderId = '${id}';
</script>

<script src="/resources/js/jquery.bxslider.js"></script>
<script src="/resources/js/jquery.jscrollpane.js"></script>
<script src="/resources/js/tender.js"></script>

</body>
</html>