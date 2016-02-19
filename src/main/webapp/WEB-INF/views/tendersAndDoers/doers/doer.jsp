<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>${doer.title} | Портал GUP</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="/resources/css/bootstrap.css">
  <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
  <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/font-awesome.css">
  <link rel="stylesheet" href="/resources/css/media-queries.css">
</head>
<body>

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
  <div class="profile">
    <div class="profile-img">
      <img class="img-responsive" src="/resources/images/artistLogo.png" alt="artistLogo">
    </div>
    <p class="firstName">ФИО исполнителя</p>
    <div class="artistData">
      <ul>
        <li>
          <p>Дата создания: 12. 09. 15</p>
        </li>
        <li>
          <p>Дата обновления: 12. 09. 15</p>
        </li>
        <li>
          <p>Просмотров: 133</p>
        </li>
      </ul>
    </div>
    <div class="contacts">
      <div class="map">
        <p class="map-p">Адрес: г. Киев, ул. Артема 11 а, офис 115, этаж 4</p>
        <div class="mapContact">
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2538.5440185405746!2d30.327353815253502!3d50.48683199262453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x472b332fd9405241%3A0x82781e1e788c6455!2z0LLRg9C7LiDQkNGA0YLQtdC80LAsIDEx0JAsIDExNSwgMTFBLCDQmtC-0YbRjtCx0LjQvdGB0YzQutC1LCDQmtC40ZfQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1sru!2sua!4v1454520039972" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
        <div class="caretContact"></div>
      </div>
      <div class="phone">
        <p class="phoneNumber">094 786 66 78</p>
        <p class="phoneNumber">094 786 66 78</p>
      </div>
      <div class="skypeContact">
        <p class="skype">Skype: Deptors</p>
      </div>
      <div class="emailContact">
        <p class="email">E-mail: Deptors@ukr.net</p>
      </div>
    </div>
    <div class="clearfix"></div>
    <button class="writeMessage">Написать сообщение</button>
    <button class="addToContact">Добавить в контакты</button>
    <div class="newsRating" style="margin-top: 5px">
      <a class="newsLike" href="#"></a>
      <p class="newsLikeNum">22 000</p>
      <a href="#" class="newsDislike"></a>
      <p class="newsDislikeNum">22 000</p>
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

    <div class="clearfix"></div>
    <div class="AboutMe">
      <p class="AboutMe-p">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere, dolor quisquam aliquid illum consequatur voluptatibus placeat perferendis hic vitae ipsum officiis, maiores, quasi quo cupiditate eius nam doloremque deserunt. Provident!</p>
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
          <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий" maxlength="2000" required></textarea>
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
        <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Qui quisquam, voluptate at magni neque. Ab illum hic asperiores voluptate voluptatem. Optio alias, numquam sint delectus quod recusandae dolores tempora. Aliquam!</p>
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
  <a href="/doer/update/${doer.id}"><button>Редактировать</button></a>

</c:if>



<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>



<script>
  var categories = '${doer.naceIds}'.replace('[', '').replace(']', '').replace(' ', '').split(','); // make array from string

  for (var i = 0; i < categories.length; i++) {
    var rubric = $('#rubrics');
        rubric.append(categories[i]);
  }
</script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>

<script src="/resources/js/vendor/bootstrap.js"></script>

<script src="/resources/js/jquery.bxslider.js"></script>

<script src="/resources/js/main.js"></script>

</body>
</html>