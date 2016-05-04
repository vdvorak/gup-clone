<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Создание исполнителя</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="shortcut icon" href="/resources/images/favicon.ico" />
  <link rel="stylesheet" href="resources/css/bootstrap.css">
  <link rel="stylesheet" href="resources/css/bootstrap-theme.css">
  <link rel="stylesheet" href="resources/css/jquery.bxslider.css">
  <link rel="stylesheet" href="resources/css/main.css">
  <link rel="stylesheet" href="resources/css/font-awesome.css">
  <link rel="stylesheet" href="resources/css/media-queries.css">
  <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
</head>
<body>

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div id="doer-create-container" class="container2">
  <div class="doerCreate">
    <h1>СОЗДАНИЕ ИСПОЛНИТЕЛЯ</h1>
    <form action="#">
      <label for="doerName">Введите название</label>
      <input id="doerName" type="text">

      <div class="clearfix"></div>

      <label for="doerName">Выберете отрасль</label>
      <div id="selectBox-info-type">
        <select id="select-type" class="form-control">
          <option>Выберете тип</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option>
        </select>
      </div>

      <div class="clearfix"></div>

      <label for="doerDescription">Описание</label>
      <textarea name="doerDescription" id="doerDescription"></textarea>

      <div class="clearfix"></div>
    </form>
    <img class="clip" src="/resources/images/clip.png" alt="clip">
    <img src="/resources/images/doerLogo.png" alt="doerLogo">

    <div class="clearfix"></div>

    <button type="button">Сохранить</button>

    <div class="clearfix"></div>
  </div>
</div>

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

  <script src="/resources/js/doer-create.js"></script>

</body>
</html>