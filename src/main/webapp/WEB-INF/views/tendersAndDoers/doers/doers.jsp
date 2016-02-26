<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Исполнители | Портал GUP</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="/resources/css/bootstrap.css">
  <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
  <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/font-awesome.css">
  <link rel="stylesheet" href="/resources/css/media-queries.css">
  <link rel="stylesheet" href="/resources/css/alster.css">
</head>
<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
  <div class="doers">
    <ul class="projectsVSInvestments-block">
      <li class="projectsVSInvestments-btn projects" data-atab="projects"><a href="#tabs1-project">ТЕНДЕРЫ</a></li>
      <!--Add "active" class to show this element as selected-->
      <li class="projectsVSInvestments-btn investments" data-atab="investments"><a href="#tabs1-investment">ИСПОЛНИТЕЛИ</a></li>
    </ul>
    <div id="tabs1-project">

    </div>
    <div id="tabs1-investment">
      <div class="doersFeed"> <!-- если чувак вип, добавь сюда класс vip и ты увидишь магию -->
        <img src="/resources/images/doersLogo.png" alt="doersLogo">
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
        <h2>ФИО исполнителя</h2>
        <p>Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним. 	Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним.
          Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним.</p>
        <div class="doersRang">
          <div></div>
          <div></div>
          <button type="button">Добавить в клиенты</button>
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
        <div class="clearfix"></div>
      </div>

      <div class="doersFeed vip">
        <img src="/resources/images/doersLogo.png" alt="doersLogo">
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
        <h2>ФИО исполнителя</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatibus doloribus ratione rem provident reprehenderit maiores minus vel ab nostrum labore id, amet facere mollitia laboriosam, eaque magnam numquam nam non.</p>
        <div class="doersRang">
          <div></div>
          <div></div>
          <button type="button">Добавить в клиенты</button>
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
        <div class="clearfix"></div>
      </div>

      <div class="doersFeed"> <!-- если чувак вип, добавь сюда класс vip и ты увидишь магию -->
        <img src="/resources/images/doersLogo.png" alt="doersLogo">
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
        <h2>ФИО исполнителя</h2>
        <p>Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним. 	Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним.
          Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним.</p>
        <div class="doersRang">
          <div></div>
          <div></div>
          <button type="button">Добавить в клиенты</button>
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
        <div class="clearfix"></div>
      </div>

      <div class="doersFeed"> <!-- если чувак вип, добавь сюда класс vip и ты увидишь магию -->
        <img src="/resources/images/doersLogo.png" alt="doersLogo">
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
        <h2>ФИО исполнителя</h2>
        <p>Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним. 	Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним.
          Как появляется инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора окружают посредники, которые существенно влияют на процессы взаимодействия с ним.</p>
        <div class="doersRang">
          <div></div>
          <div></div>
          <button type="button">Добавить в клиенты</button>
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
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
</div>

<%--<div>--%>
  <%--<a href="/doer-create"><button>Создать исполнителя</button></a>--%>
  <%--<c:forEach var="doer" items="${doerPages.entities}">--%>
    <%--<h3>--%>
      <%--<a href="/doer/${doer.id}">${doer.title}</a>--%>
    <%--</h3>--%>
    <%--<div>--%>
      <%--<a href="/doer/${doer.id}">--%>
        <%--<c:choose>--%>
          <%--<c:when test="${not empty doer.imageId}">--%>
            <%--<img id="imgPreview" src="/api/rest/fileStorage/DOER/file/read/id/${doer.imageId}" width="200"--%>
                 <%--height="200">--%>
          <%--</c:when>--%>
          <%--<c:otherwise>--%>
            <%--<img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">--%>
          <%--</c:otherwise>--%>
        <%--</c:choose>--%>
      <%--</a>--%>
    <%--</div>--%>
    <%--<br>--%>
    <%--<div>Описание<br>${doer.body}</div>--%>
    <%--<div><br>Дата создания: <span class="date-create"> ${doer.dateOfCreate}</span></div>--%>
    <%--<div><br>Дата последнего обновления: <span class="date-create"> ${doer.dateOfUpdate} </span></div>--%>
    <%--<div><br>Оценка по отзывам: ${doer.recallCount}</div>--%>
    <%--<c:forEach var="recall" items="${doer.recalls}">--%>
      <%--<div><br>Пользователь ${recall.authorId} пославил ${recall.mark} в <div> <span class="date-create"></span> ${doer.createTime}</div></div>--%>
      <%--<c:if test="${not empty recall.body}">--%>
      <%--<div>Отзыв: ${recall.body} </div>--%>
      <%--</c:if>--%>
    <%--</c:forEach>--%>
  <%--</c:forEach>--%>
<%--</div>--%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<sec:authorize var="loggedIn" access="isAuthenticated()" />
<c:choose>
  <c:when test="${loggedIn}">
    <script src="/resources/js/autorizedHeader.js"></script>
  </c:when>
  <c:otherwise>
    <script src="/resources/js/anonymHeader.js"></script>
  </c:otherwise>
</c:choose>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script src="/resources/js/top-news-block.js"></script>
<script src="/resources/js/top-offers-block.js"></script>
<script src="/resources/js/top-tenders-block.js"></script>
<script src="/resources/js/top-projects-block.js"></script>

<script>
  $('.doers').easytabs({
    animate: false
  })
</script>


<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
</body>
</html>
