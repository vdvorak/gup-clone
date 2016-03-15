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
</head>
<body>

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
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

<%--<div>--%>
  <%--<select id="naceIds" required>--%>
    <%--<option value="nace1">01.11 Вирощування зернових культур (крім рису), бобових культур і насіння олійних культур</option>--%>
    <%--<option value="nace2">01.12 Вирощування рису</option>--%>
    <%--<option value="nace3">01.13 Вирощування овочів та баштанних культур, коренеплодів та бульбоплодів</option>--%>
    <%--<option value="nace4">01.15 Вирощування тютюну</option>--%>
    <%--<option value="nace5">01.16 Вирощування прядивних культур</option>--%>
  <%--</select>--%>
<%--</div>--%>
<%--<div>--%>
  <%--<input id="doerTitle" type="text" name="doerTitle" minlength="2" maxlength="70" required--%>
         <%--placeholder="Название исполнителя">--%>
<%--</div>--%>

<%--<div>--%>
    <%--<textarea id="doerDescription"  minlength="50" maxlength="5000" required--%>
              <%--placeholder="Описание исполнителя"></textarea>--%>
<%--</div>--%>

<%--<div>--%>
  <%--<form id="photoInput" enctype="multipart/form-data" method="post">--%>
    <%--<input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">--%>
  <%--</form>--%>

  <%--<div class="imgBlock">--%>
    <%--<img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">--%>
  <%--</div>--%>

<%--</div>--%>
<%--<button id="createDoer">Создать</button>--%>

<sec:authorize access="isAuthenticated()">
  <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
  var flag = '${flag}';
</script>


<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script src="/resources/js/top-news-block.js"></script>
<script src="/resources/js/top-offers-block.js"></script>
<script src="/resources/js/top-tenders-block.js"></script>
<script src="/resources/js/top-projects-block.js"></script>


<script>

  var imgId = '';
  var doer = {};
  var naceIds = [];


  //----------------------------------------------------- Image form -----------------------------------------------

  $(document).on('change', '#photofile', function (e) {

    var formImg = new FormData($('#photoInput')[0]);

    if (imgId !== '') {
      deleteImgFromDB(imgId);
    }

    $.ajax({
      type: "POST",
      url: "/api/rest/fileStorage/DOER/file/upload/",
      data: formImg,
      async: false,
      cache: false,
      contentType: false,
      processData: false,
      success: function (data, textStatus, request) {
        imgId = data.id;
        $('#imgPreview').attr("src", "/api/rest/fileStorage/DOER/file/read/id/" + imgId);
      }
    });
  });
  //----------------------------------------------------- Image form -----------------------------------------------


  ///----------------------Delete photo from  DB-----------------------------------------
  function deleteImgFromDB(picId) {
    $.ajax({
      url: '/api/rest/fileStorage/DOER/file/delete/id/' + picId,
      method: 'POST',
      success: function (response) {
      },
      error: function (response) {
      }
    });
  }
  ///----------------------Delete photo from  DB-----------------------------------------

  ///------------------------- Upload Doer -----------------------------------------------
  $(document).on('click', '#createDoer', function (event) {

    naceIds.push($('#naceIds').val());

    doer.title = $('#doerTitle').val();
    doer.body = $('#doerDescription').val();
    doer.imageId = imgId;
    doer.naceIds = naceIds;

//    alert(JSON.stringify(doer));

    $.ajax({
      type: "POST",
//      url: "/api/rest/doerService/doer/create",
      url: "/api/rest/doerService/doer/create/",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      data: JSON.stringify(doer),
      success: function (response) {
        window.location.href = '/index';
//               в перспективе должно перекидывать на страницу этого исполнителя - его просмотр
      }
    });
  });
  ///------------------------- Upload Doer -----------------------------------------------
</script>
</body>
</html>