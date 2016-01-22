<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Исполнители | Портал GUP</title></head>
<body>

<div>
  <a href="/doer/create"><button>Создать исполнителя</button></a>
  <c:forEach var="doer" items="${doerPages.entities}">
    <h3>
      <a href="/doer/${doer.id}">${doer.title}</a>
    </h3>
    <div>
      <a href="/doer/${doer.id}">
        <c:choose>
          <c:when test="${not empty doer.imageId}">
            <img id="imgPreview" src="/api/rest/fileStorage/DOER/file/read/id/${doer.imageId}" width="200"
                 height="200">
          </c:when>
          <c:otherwise>
            <img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">
          </c:otherwise>
        </c:choose>
      </a>
    </div>
    <br>
    <div>Описание<br>${doer.body}</div>
    <div><br>Дата создания: <span class="date-create"></span> ${doer.dateOfCreate}</div>
    <div><br>Дата последнего обновления: <span class="date-create"></span> ${doer.dateOfUpdate}</div>
    <div><br>Оценка по отзывам: ${doer.recallCount}</div>
    <c:forEach var="recall" items="${doer.recalls}">
      <div><br>Пользователь ${recall.authorId} пославил ${recall.mark} в <div> <span class="date-create"></span> ${doer.createTime}</div></div>
      <c:if test="${not empty recall.body}">
      <div>Отзыв: ${recall.body} </div>
      </c:if>
    </c:forEach>
  </c:forEach>
</div>


<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
</body>
</html>
