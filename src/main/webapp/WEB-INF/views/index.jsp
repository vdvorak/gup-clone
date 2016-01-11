<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 30.12.2015
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru-RU">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>GUP</title>
  <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
  <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
</head>
<body>


<sec:authorize access="isAuthenticated()" var="isAuthenticated">
  <header class="main-head">
    <div class="top-menu">
      <div class="top-menu-userSection">
        <div class="top-menu-userpic">
          <img src="/resources/img/reallySmallUserpic.png">
        </div>
        <div class="top-menu-username">
          <a href="#">петров василий</a>
        </div>
        <div class="header-mainMenu">
          <a href="#">Моя страница</a>
          <a href="#">Сообщения</a>
          <a href="#">Уведомления</a>
          <a href="#">Тендеры</a>
          <a href="#">Проекты</a>
          <a href="#">Новости</a>
          <a href="#">Настройки</a>
          <a href="#">Выход</a>
          <div class="pageedit">
            <a href="/edit-profile">Редактировать страницу</a>
          </div>
        </div>
      </div>
      <div class="top-menu-notifications">
        <div class="top-menu-messages">
          <img src="/resources/img/envelopeSmall.png">
        </div>
        <!--<div class="top-menu-actualMessageWrap">
            <img class="message-sender-userpic"src="">
            <div class="top-menu-incomingMessage">
                <p>
                    <span class="top-menu-incomingMessage_date"></span>
                    Сообщение СообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщениеСообщение
                </p>
                <a  class="top-menu-incomingMessage_answer" href="#">Ответить</a>
            </div>
        </div>
        ТВОРЧЕСКИЙ ПИЗДЕЦ, ДОДЕЛАТЬ! -->
        <div class="top-menu-notification">
          <img src="/resources/img/bellSmall.png">
        </div>

      </div>

      <div class="top-menu-userBallance">
        <div class="ballance">
          <a href="#">00.00<span>грн</span></a>
        </div>
        <div class="ballanceAdd-wraper">
          <a href="#">Пополнить баланс</a>
        </div>
      </div>
    </div>
  </header>
</sec:authorize>

<c:if test="${!isAuthenticated}">
  <jsp:include page="/WEB-INF/templates/headerAnonym.jsp"/>
</c:if>

<!--1st section with search-->
<section class="first-sec">
  <div class="logo-wrap">
    <img src="/resources/img/logo-site.png">
    <p class="logo-title">global ukrainian portal</p>
  </div>
  <div class="shop-wrap-right">
    <div class="shop-wrap">
      <a class="main-winStore" href="#"><img src="/resources/img/wins-icon.png"></a>
      <a class="main-googlePlay" href="#"><img src="/resources/img/goop-icon.png"></a>
      <a class="main-appStore" href="#"><img src="/resources/img/apps-icon.png"></a>
    </div>
    <div class="join-button-wrap">
      <div class="join-button">
        <a href="#" title="Вступить в организацию"><img src="/resources/img/join-button.png"></a>
      </div>
    </div>
  </div>
  <div class="main-search-button-wrapper">
    <input type="text" placeholder="Поиск">
    <a href="">Найти<span class="main-search-button-icon"><img src="/resources/img/magnifire.png"></span></a>
  </div>
</section>
<!-- END1st section -->

<!--2nd section menu+slider -->
<section>
  <div class="sec-wrap">
    <div class="partials-wrap">
      <div class="main-tender-wrap">
        <p>Тендеры</p>
        <div class="main-tenderPic-wrap">
          <a href="#"><img src="/resources/img/hammertime.png"></a>
        </div>
        <nav class="main-tender-bottom-menu">
          <a href="#" class="active-main-menu-link">Участвовать</a>
          <a href="#">Исполнители</a>
        </nav>
      </div>
      <div class="main-project-wrap">
        <p>Проекты</p>
        <div class="main-projectPic-wrap">
          <a href="#"><img src="/resources/img/circul.png"></a>
        </div>
        <nav class="main-project-bottom-menu">
          <a href="#" class="active-main-menu-link">Реструктуризация</a>
          <a href="#">Готовый прототип</a>
          <a href="#">Проект на бумаге</a>
          <a href="#">Ноу-Хау</a>
        </nav>
      </div>
      <div class="main-news-wrap">
        <p>Новости</p>
        <div class="main-newsPic-wrap">
          <a href="#"><img src="/resources/img/yagazetko.png"></a>
        </div>
        <nav class="main-news-bottom-menu">
          <a href="#" class="active-main-menu-link">Киев</a>
          <a href="#">Львов</a>
          <a href="#">Харьков</a>
          <a href="#">Запорожье</a>
        </nav>
      </div>
    </div>
    <div class="main-slider-wrap">
      <ul class="bxslider">
        <li><img src="/resources/img/slider-item.png" /></li>
        <li><img src="/resources/img/slider-item1.png" /></li>
        <li><img src="/resources/img/slider-item2.png" /></li>
      </ul>
    </div>
  </div>
</section>
<!--END 2nd section -->

<!--3rd section news timeline-->
<section>
  <div class="main-newsTimeline-wrap">
    <div class="main-news-item-wrap">
      <img src="/resources/img/vegan-pic1.png">

      <div class="main-news-text"><span class="main-news-title">веган фест</span>Дать возможность людям сделать осознанный выбор, который улучшит качество жизни каждого, уделяя внимание простым основам нравственного, экологического и гармоничного образа жизни, основанного на заботе о здоровье, окружающей среде и наших хвостатых соседях по планете.</div>
      <div class="main-news-subtitles">
        <div class="main-news-views">Просмотров:<span></span></div>
        <div class="main-news-date">Дата публикации: <span></span></div>
      </div>
    </div>
    <div class="main-news-item-wrap">
      <img src="/resources/img/vegan-pic1.png">

      <div class="main-news-text"><span class="main-news-title">веган фест</span>Дать возможность людям сделать осознанный выбор, который улучшит качество жизни каждого, уделяя внимание простым основам нравственного, экологического и гармоничного образа жизни, основанного на заботе о здоровье, окружающей среде и наших хвостатых соседях по планете.</div>
      <div class="main-news-subtitles">
        <div class="main-news-views">Просмотров:<span></span></div>
        <div class="main-news-date">Дата публикации: <span></span></div>
      </div>
    </div>
    <div class="main-news-item-wrap">
      <img src="/resources/img/vegan-pic1.png">

      <div class="main-news-text"><span class="main-news-title">веган фест</span>Дать возможность людям сделать осознанный выбор, который улучшит качество жизни каждого, уделяя внимание простым основам нравственного, экологического и гармоничного образа жизни, основанного на заботе о здоровье, окружающей среде и наших хвостатых соседях по планете.</div>
      <div class="main-news-subtitles">
        <div class="main-news-views">Просмотров:<span></span></div>
        <div class="main-news-date">Дата публикации: <span></span></div>
      </div>
    </div>
  </div>
</section>
<!--END 3rd section-->
<footer>

</footer>
<!-- hiden stuff-->

<!--END hiden stuff-->
<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/js/common.js"></script>
<!--END of libs-->

</body>
</html>