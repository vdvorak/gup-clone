<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${doer.title} | Портал GUP</title>
</head>
<body>
<div>
  ${doer.title}
</div>
КВЭД <span id="rubrics"></span>
<div>

</div>
<div>
  <c:choose>
    <c:when test="${not empty doer.imageId}">
      <img id="imgPreview" src="/api/rest/fileStorage/DOER/file/read/id/${doer.imageId}" width="200"
           height="200">
    </c:when>
    <c:otherwise>
      <img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">
    </c:otherwise>
  </c:choose>
</div>
<div>
  Название
  <br>
  ${doer.authorId}
</div>

<div>
  Автор: ${username}
</div>
<div>
  Описание
  <br>
  ${doer.body}
</div>
<div>
  Количество посещений
  <br>
  ${doer.countVisit}
</div>
<div>
  Дата создания
  <br>
  ${doer.dateOfCreate}
</div>
<div>
  Дата последнего обноновления
  <br>
  ${doer.dateOfUpdate}
</div>

<div>
  Дата последнего обноновления
  <br>
  ${doer.dateOfUpdate}
</div>

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
</body>
</html>