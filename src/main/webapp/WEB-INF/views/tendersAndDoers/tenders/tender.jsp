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
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

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

<div class="container2">
    <div class="contentContainer" style="margin-top: 100px;"> <!-- Add "vip" class for "vip" users :3 -->
        <div class="tenderContent">
            <div class="topSection">
                <div class="statInfo">
                    <div class="publishDate">Опубликовано: 22. 10. 16</div>
                    <span class="number visible">№953745195</span>
                    <div class="sum">00 000 000$</div>
                </div>
                <div class="clearfix"></div>
                <div class="tenderButtons">
                    <div class="participate">
                        <div class="clock">
                            <div class="time">31 января 2016 г., 23:35</div>
                        </div>
                        <button type="button" class="abutton blue">Участвовать</button>
                    </div>
                    <button type="button" class="abutton leaveProposal">Оставить предложение</button>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="title">Название тендера</div>
            <img src="/resources/css/images/sample/tender1.png" alt="" class="mainPhoto">
            <p class="text">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc malesuada molestie euismod. In vulputate mi dictum orci vulputate auctor. Fusce sit amet orci suscipit neque lacinia pulvinar nec in est. Proin neque ante, elementum at maximus a, luctus nec diam. Mauris convallis tellus nec sapien accumsan, quis posuere odio congue. Etiam eu tincidunt arcu, pellentesque euismod odio. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec gravida dolor ac laoreet ultrices.

                Phasellus dignissim accumsan ligula convallis hendrerit. Quisque eu tincidunt sapien. Mauris molestie pharetra faucibus. Sed vestibulum magna ac accumsan pellentesque. Maecenas sit amet magna rhoncus, elementum ligula sed, volutpat neque. In sit amet nibh ante. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse in mauris tempus, pretium odio ac, semper metus. Praesent ut turpis sit amet ante interdum vulputate ultricies ac neque. Maecenas placerat tortor magna, quis consequat turpis feugiat non. Nunc vel ultricies metus. Morbi facilisis maximus augue, sed sagittis ligula tincidunt at. Mauris sed vestibulum sapien. Fusce ut iaculis sem.

                Nulla vitae lacus enim. Etiam malesuada ex at velit faucibus ultricies. Nunc quis tincidunt urna. Sed a erat faucibus, sodales velit at, lacinia massa. Aliquam porta at libero et gravida. Vivamus viverra ut odio blandit posuere. Nam sagittis justo at elit ornare dignissim. Maecenas eros tortor, sollicitudin non aliquet id, blandit id velit. Aenean scelerisque feugiat lacus eget mattis. Etiam convallis lacinia mauris at aliquam. Ut in nibh nisi. Nam tincidunt sollicitudin enim nec aliquet.

                Cras consectetur vitae erat eget porta. Aenean efficitur eget dolor nec suscipit. Curabitur sit amet tellus nec lorem maximus lobortis euismod quis libero. Donec molestie nibh ac consequat auctor. Aliquam sagittis blandit mauris. Phasellus eu ornare nisi. Aenean sagittis vestibulum pellentesque. Nam venenatis condimentum sapien, sed ornare neque porta fringilla. Vestibulum quis eleifend lacus, in molestie nulla. Suspendisse potenti. Sed rutrum tortor sit amet tortor gravida sollicitudin. Phasellus quis condimentum lectus. Quisque sapien eros, tristique et venenatis ultrices, volutpat vitae libero. In velit enim, tristique quis est feugiat, interdum laoreet augue. Sed maximus sollicitudin neque non sodales.

                Vestibulum tempus mauris neque, eu pretium sapien aliquam vitae. Aliquam ac porttitor mi. Vivamus pretium quam id metus venenatis luctus. Aenean ultrices a nunc vel semper. Proin non eleifend dui. Aenean placerat lectus et est porttitor bibendum. Donec at felis nec quam sagittis sagittis convallis eu nisi. Maecenas cursus consequat turpis vitae placerat. Praesent ac sagittis sapien, ut pretium odio. Curabitur et lectus est. Phasellus odio ex, placerat vel nibh in, rutrum porttitor est. Donec eget metus sit amet dolor consequat gravida. Pellentesque posuere orci quis accumsan vestibulum. Suspendisse lobortis eget orci tristique maximus. Quisque sollicitudin, mi ac mattis lacinia, sem turpis tristique metus, ut vehicula lectus justo vel sem. Nam eu imperdiet justo.
            </p>

        </div>
        <div class="sliderTender">
            <ul class="bxsliderTender">
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
            </ul>
        </div>
        <div class="tenderFils">
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
        </div>
        <div class="tenderMap">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2534.961160242289!2d30.466304515735114!3d50.5534922794897!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4d31f1b52061f%3A0x7e2e7b1a78ac5cb7!2z0LLRg9C7LiDQodCw0LTQvtCy0LAsINCa0LjRl9Cy!5e0!3m2!1sru!2sua!4v1456576844954" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
        <div class="downComments"><p>КОММЕНТАРИИ</p></div>
    </div>

    <div class="colNewsComments">
        <div class="newsComments">
            <div class="clearfix"></div>
            <p class="newsCommentsHeader">ПРЕДЛОЖЕНИЯ</p>
            <form action="#" role="form" id="newsCommentsForm">
                <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий" maxlength="2000" required></textarea>
                <button type="submit" class="newsFormSubmit">Отправить</button>
            </form>
            <p id="chars">2000 символов осталось</p>
        </div>
    </div>
    <div class="colComments">
        <div class="comments">
            <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
            <a class="NameUser" href="#">Вася Петров</a>
            <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Qui quisquam, voluptate at magni neque. Ab illum hic asperiores voluptate voluptatem. Optio alias, numquam sint delectus quod recusandae dolores tempora. Aliquam!</p>
        </div>
        <div class="comments">
            <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
            <a class="NameUser" href="#">Вася Петров</a>
            <p class="commentUser">Интересно было узнать, история повторяется циклично!</p>
        </div>
    </div>
    <div class="clearfix"></div>
</div>

<%--<div class="container2">--%>
    <%--<div class="contentContainer" style="margin-top: 100px;"> <!-- Add "vip" class for "vip" users :3 -->--%>
        <%--<div class="tenderContent">--%>
            <%--<div class="topSection">--%>
                <%--<div class="statInfo">--%>
                    <%--<div class="publishDate tender-publish-date">Опубликовано: <span></span></div>--%>


                    <%--<!--Need fix-->--%>
                    <%--<br>--%>

                    <%--<div class="tender-veiws">Просмотров: <span></span></div>--%>
                    <%--<br>--%>

                    <%--<div class="tender-proposal-count">Предложений: <span></span></div>--%>
                    <%--<br>--%>
                    <%--<!--Need fix-->--%>

                    <%--<span class="number visible">№<span class="tender-number"></span></span>--%>

                    <%--<div class="sum tender-expectedPrice"><span></span>₴</div>--%>
                <%--</div>--%>
                <%--<div class="clearfix"></div>--%>
                <%--<div class="tenderButtons">--%>
                    <%--<div class="participate">--%>
                        <%--<div class="clock">--%>
                            <%--<div class="time date-finish"></div>--%>
                        <%--</div>--%>
                        <%--<button type="button" class="abutton blue">Участвовать</button>--%>
                    <%--</div>--%>
                    <%--<button type="button" class="abutton leaveProposal">Оставить предложение</button>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="clearfix"></div>--%>
            <%--<div class="title tender-name">Название тендера</div>--%>
            <%--&lt;%&ndash;<img src="/resources/css/images/sample/tender1.png" alt="" class="mainPhoto">&ndash;%&gt;--%>
            <%--<p class="text tender-item-text">--%>

            <%--</p>--%>

            <%--<div class="bottomSection">--%>

            <%--</div>--%>

        <%--</div>--%>
        <%--<div class="sliderTender">--%>
            <%--<ul class="bxsliderTender">--%>
                <%--<li><img src="/resources/images/tenderSlider.png" alt="tenderSlider"/></li>--%>
                <%--<li><img src="/resources/images/tenderSlider.png" alt="tenderSlider"/></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="tenderFils">--%>
            <%--<a href="#">doc.exel</a>--%>
            <%--<a href="#">system.docx</a>--%>
            <%--<a href="#">doc.exel</a>--%>
            <%--<a href="#">system.docx</a>--%>
            <%--<a href="#">doc.exel</a>--%>
            <%--<a href="#">system.docx</a>--%>
            <%--<a href="#">doc.exel</a>--%>
            <%--<a href="#">system.docx</a>--%>
        <%--</div>--%>
        <%--<div class="tenderMap">--%>
        <%--</div>--%>
        <%--<div class="downComments"><p>КОММЕНТАРИИ</p></div>--%>
    <%--</div>--%>

    <%--<div class="colNewsComments">--%>
        <%--<div class="newsComments">--%>
            <%--<div class="clearfix"></div>--%>
            <%--<p class="newsCommentsHeader">ПРЕДЛОЖЕНИЯ</p>--%>

            <%--<form action="#" role="form" id="newsCommentsForm">--%>
                <%--<textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий"--%>
                          <%--maxlength="2000" required></textarea>--%>
                <%--<input id="visionSelect" type="checkbox"><label for="visionSelect">Скрыть--%>
                <%--предложение от других участников</label>--%>
                <%--<button id="makePropose" class="newsFormSubmit">Отправить</button>--%>
            <%--</form>--%>
            <%--<p id="chars">2000 символов осталось</p>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="colComments" id="commentStart">--%>



        <%--<div class="comments">--%>
            <%--<a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>--%>
            <%--<a class="NameUser propose-author" href="#">Вася Петров</a>--%>
            <%--<p class="propose-date">Дата публикации: <span></span></p>--%>
            <%--<p class="commentUser poropse-text">Lorem </p>--%>
            <%--<button class="chooseWinner">Выбрать победителем</button>--%>
        <%--</div>--%>


    <%--</div>--%>
    <%--<div class="clearfix"></div>--%>
<%--</div>--%>

<!--PAGE CONTENT END-->

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
    var tenderId = '${id}';
</script>

<script src="/resources/js/tender.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>


</body>
</html>