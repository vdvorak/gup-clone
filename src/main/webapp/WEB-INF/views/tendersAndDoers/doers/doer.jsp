<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>${doer.title} | Портал GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="/resources/images/favicon.ico" />
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
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
    <div class="profile">
        <div class="profile-img">
            <c:choose>
                <c:when test="${not empty doer.imageId}">
                    <img class="img-responsive"
                         src="/api/rest/fileStorage/DOER/file/read/id/${doer.imageId}" width="200"
                         height="200">
                </c:when>
                <c:otherwise>
                    <img class="img-responsive" alt="artistLogo" src="/resources/images/no_photo.jpg" width="200"
                         height="200">
                </c:otherwise>
            </c:choose>
        </div>
        <p class="firstName">${doer.title}</p>


        <div class="artistData">
            <ul>
                <li>
                    <p>Дата создания: <span class="date-create">${doer.dateOfCreate}</span></p>
                </li>
                <li>
                    <p>Дата обновления: <span class="date-create">${doer.dateOfUpdate}</span></p>
                </li>
                <li>
                    <p>Просмотров: ${doer.countVisit}</p>
                </li>
            </ul>
        </div>
        <div class="contacts">
            <div class="map">
                <p class="map-p">Адрес: г. Киев, ул. Артема 11 а, офис 115, этаж 4</p>

                <div class="mapContact">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2538.5440185405746!2d30.327353815253502!3d50.48683199262453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x472b332fd9405241%3A0x82781e1e788c6455!2z0LLRg9C7LiDQkNGA0YLQtdC80LAsIDEx0JAsIDExNSwgMTFBLCDQmtC-0YbRjtCx0LjQvdGB0YzQutC1LCDQmtC40ZfQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1sru!2sua!4v1454520039972"
                            width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
                </div>
                <div class="caretContact"></div>
            </div>

            <c:if test="${not empty doer.contactPhones}">
                <div class="phone">
                    <c:forEach var="tel" items="${doer.contactPhones}">
                        <p class="phoneNumber">${tel}</p>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${not empty doer.email}">
                <div class="emailContact">
                    <p class="email" title="${doer.email}">E-mail: ${doer.email}</p>
                </div>
            </c:if>
        </div>
        <div class="clearfix"></div>
        <c:if test="${check}">
            <button class="writeMessage">Написать сообщение</button>
            <button class="addToContact">Добавить в контакты</button>
        </c:if>
        <div class="newsRating" style="margin-top: 5px">
            <a class="newsLike" href="#"></a>

            <p class="newsLikeNum">${like}</p>
            <a href="#" class="newsDislike"></a>

            <p class="newsDislikeNum">${dislike}</p>
        </div>
        <div class="social-icon">
            <a href="#"><img class="img-responsive" src="/resources/images/in.png" alt="in"></a>
            <a href="#"><img class="img-responsive" src="/resources/images/g+.png" alt="g+"></a>
            <a href="#"><img class="img-responsive" src="/resources/images/B.png" alt="B"></a>
            <a href="#"><img class="img-responsive" src="/resources/images/skype-icon.png" alt="skype-icon"></a>
            <a href="#"><img class="img-responsive" src="/resources/images/facebook.png" alt="facebook"></a>
            <a href="#"><img class="img-responsive" src="/resources/images/twitter.png" alt="twitter"></a>
        </div>

        <div class="listArtist">
            <p>Список клиентов:</p>
            <ul>
                <li><p>Earthshaker</p></li>
                <li><p>Axe</p></li>
                <li><p>Sven</p></li>
                <li><p>Pudge</p></li>
                <li><p>Tiny</p></li>
                <li><p>Sand King</p></li>
                <li><p>Kunkka</p></li>
                <li><p>Slardar</p></li>
                <li><p>Beastmaster</p></li>
                <li><p>Tidehunter</p></li>
                <li><p>Dragon Knight</p></li>
                <li><p>Wraith King</p></li>
                <li><p>Clockwerk</p></li>
                <li><p>Lifestealer</p></li>
                <li><p>Omniknight</p></li>
                <li><p>Night Stalker</p></li>
                <li><p>Huskar</p></li>
                <li><p>Doom</p></li>
                <li><p>Alchemist</p></li>
                <li><p>Spirit Breaker</p></li>
            </ul>
            <img src="/resources/images/downArtist.png" alt="downArtist">
        </div>

        <div class="listArtist">
            <p>Список клиентов:</p>
            <ul>
                <li><p>Earthshaker</p></li>
                <li><p>Axe</p></li>
                <li><p>Sven</p></li>
                <li><p>Pudge</p></li>
                <li><p>Tiny</p></li>
                <li><p>Sand King</p></li>
                <li><p>Kunkka</p></li>
                <li><p>Slardar</p></li>
                <li><p>Beastmaster</p></li>
                <li><p>Tidehunter</p></li>
                <li><p>Dragon Knight</p></li>
                <li><p>Wraith King</p></li>
                <li><p>Clockwerk</p></li>
                <li><p>Lifestealer</p></li>
                <li><p>Omniknight</p></li>
                <li><p>Night Stalker</p></li>
                <li><p>Huskar</p></li>
                <li><p>Doom</p></li>
                <li><p>Alchemist</p></li>
                <li><p>Spirit Breaker</p></li>
            </ul>
            <img src="/resources/images/downArtist.png" alt="downArtist">
            <div class="modalDoer">
                <div>
                    <p>Вася Пупкин отправил заявку на добавление, подтвердите что он входит в список ваших клиентов</p>
                    <button type="button" id="close">Отмена</button>
                    <button type="button">Добавить</button>
                </div>
            </div>
        </div>

        <div class="clearfix"></div>
        <div class="AboutMe">
            <p class="AboutMe-p">${doer.body}</p>

            <p class="AboutMe-p2">О себе</p>
        </div>

        <div class="downComments maxWidth">
            <p>КОММЕНТАРИИ</p>
        </div>

        <div class="clearfix"></div>

        <div class="colNewsComments">
            <div class="newsComments">
                <div class="clearfix"></div>
                <p class="newsCommentsHeader">КОММЕНТАРИИ</p>

                <form action="#" role="form" id="newsCommentsForm">
                    <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий"
                              maxlength="2000" required></textarea>
                    <button type="submit" class="newsFormSubmit">Отправить</button>
                </form>
                <p id="chars">2000 символов осталось</p>
            </div>
        </div>
        <div class="colComments">
            <div class="comments">
                <img class='likeComments' src="/resources/css/images/newslike.png" alt="likeComments">
                <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                <a class="NameUser" href="#">Вася Петров</a>

                <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Qui quisquam, voluptate
                    at magni neque. Ab illum hic asperiores voluptate voluptatem. Optio alias, numquam sint delectus
                    quod recusandae dolores tempora. Aliquam!</p>
            </div>
            <div class="comments">
                <img class='dislikeComments' src="/resources/css/images/newsDislike.png" alt="dislikeComments">
                <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                <a class="NameUser" href="#">Вася Петров</a>

                <p class="commentUser">Интересно было узнать, история повторяется циклично!</p>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>


<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<script>
  $('.listArtist ul li p.anonymous').click(function (event) {
    event.preventDefault();
    $('#overlay').fadeIn(400,
            function () {
              $('.modalDoer')
                      .css('display', 'block')
                      .animate({
                        opacity: 1,
                        top: '50%'
                      }, 200);
            });
  });
  /* Зaкрытие мoдaльнoгo oкнa, тут делaем тo же сaмoе нo в oбрaтнoм пoрядке */
  $('#close, #overlay').click(function () {
    $('.modalDoer')
            .animate({
              opacity: 0,
              top: '45%'
            }, 200,
            function () {
              $(this).css('display', 'none');
              $('#overlay').fadeOut(400);
            }
    );
  });
</script>

<%--<div>--%>
<%--${doer.title}--%>
<%--</div>--%>
<%--КВЭД <span id="rubrics"></span>--%>
<%--<div>--%>

<%--</div>--%>
<%--<div>--%>
<%--<c:choose>--%>
<%--<c:when test="${not empty doer.imageId}">--%>
<%--<img id="imgPreview" src="/api/rest/fileStorage/DOER/file/read/id/${doer.imageId}" width="200"--%>
<%--height="200">--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</div>--%>
<%--<div>--%>
  <%--Название--%>
  <%--<br>--%>
  <%--${doer.authorId}--%>
<%--</div>--%>

<%--<div>--%>
  <%--Автор: ${username}--%>
<%--</div>--%>
<%--<div>--%>
  <%--Описание--%>
  <%--<br>--%>
  <%--${doer.body}--%>
<%--</div>--%>
<%--<div>--%>
  <%--Количество посещений--%>
  <%--<br>--%>
  <%--${doer.countVisit}--%>
<%--</div>--%>
<%--<div>--%>
  <%--Дата создания--%>
  <%--<br>--%>
  <%--${doer.dateOfCreate}--%>
<%--</div>--%>
<%--<div>--%>
  <%--Дата последнего обноновления--%>
  <%--<br>--%>
  <%--${doer.dateOfUpdate}--%>
<%--</div>--%>

<%--<div>--%>
  <%--Дата последнего обноновления--%>
  <%--<br>--%>
  <%--${doer.dateOfUpdate}--%>
<%--</div>--%>

<c:if test="${check}">
    <a href="/doer/update/${doer.id}">
        <button>Редактировать</button>
    </a>

</c:if>

<script>
    var categories = '${doer.naceIds}'.replace('[', '').replace(']', '').replace(' ', '').split(','); // make array from string

    for (var i = 0; i < categories.length; i++) {
        var rubric = $('#rubrics');
        rubric.append(categories[i]);
    }
</script>

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
</body>
</html>